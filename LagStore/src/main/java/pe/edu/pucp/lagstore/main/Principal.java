package pe.edu.pucp.lagstore.main;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoAdquiridoBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoBO;
import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.BibliotecaMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.AdministradorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.AdministradorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.DesarrolladorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.JugadorMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestjuegos.model.JuegoAdquirido;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;
import pe.edu.pucp.lagstore.valoracion.dao.CalificacionDAO;
import pe.edu.pucp.lagstore.valoracion.dao.ResenaDAO;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;
import pe.edu.pucp.lagstore.valoracion.model.Resena;
import pe.edu.pucp.lagstore.valoracion.mysql.CalificacionMySQL;
import pe.edu.pucp.lagstore.valoracion.mysql.ResenaMySQL;


public class Principal {
    public static void main(String[] args)throws Exception{
        CalificacionDAO calificacionDAO = new CalificacionMySQL();

        // === CREAR OBJETOS PARA LA PRUEBA ===
        Jugador jugador = new Jugador();
        jugador.setIdJugador(2); // Ya debe existir

        Juego juego = new Juego();
        juego.setIdJuego(2); // Ya debe existir

        // === INSERTAR UNA NUEVA CALIFICACIÓN ===
        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setAutor(jugador);
        nuevaCalificacion.setJuego(juego);
        nuevaCalificacion.setFechaPuntuacion(Date.valueOf(LocalDate.now()));
        nuevaCalificacion.setPuntuacion(5);
        nuevaCalificacion.setActivo(1);

        int idCalificacionInsertada = calificacionDAO.insertar(nuevaCalificacion);
        System.out.println("Calificación insertada con ID: " + idCalificacionInsertada);

        
        //1.JUGADOR
        
        
        
        ResenaDAO resenaDAO = new ResenaMySQL();
        // === CREAR OBJETOS PARA LA PRUEBA ===

        Calificacion calificacion = new Calificacion();
        calificacion.setIdCalificacion(1); // Ya debería estar insertada

        // === INSERTAR UNA NUEVA RESEÑA ===
        Resena nuevaResena = new Resena();
        nuevaResena.setAutor(jugador);
        nuevaResena.setJuego(juego);
        nuevaResena.setComentario("¡Juego increible!");
        nuevaResena.setFechaPublicacion(Date.valueOf(LocalDate.now()));
        nuevaResena.setCalificacion(calificacion);
        nuevaResena.setActivo(1);

        int idResenaInsertada = resenaDAO.insertar(nuevaResena);
        System.out.println("Resena insertada con ID: " + idResenaInsertada);

        ////////////////////MODIFICAR///////////////////////////
        ///CALIFICACION//////////////////////////////////////////
        nuevaCalificacion.setIdCalificacion(idCalificacionInsertada);
        nuevaCalificacion.setPuntuacion(2); // Nueva puntuación
        int modResultado = calificacionDAO.modificar(nuevaCalificacion);
        System.out.println("Resultado de modificacion: " + modResultado);
        ///RESEÑA///////////////////////////////////////////////
        nuevaResena.setIdResena(idResenaInsertada);
        nuevaResena.setComentario("Juego increible, mejorado con actualizaciones.");
        nuevaResena.setActivo(1);
        int resultadoMod = resenaDAO.modificar(nuevaResena);
        System.out.println("Resultado de modificacion: " + resultadoMod);
        ////////////////////LISTAR///////////////////////////
        //////CALIFICACION//////////////////////////////////////////
        ArrayList<Calificacion> calificaciones = calificacionDAO.listarTodas();
        System.out.println("\n Lista de calificaciones activas:");
        for (Calificacion c : calificaciones) {
            System.out.println("ID: " + c.getIdCalificacion() +
                               ", Puntaje: " + c.getPuntuacion() +
                               ", Fecha: " + c.getFechaPuntuacion() +
                               ", Jugador ID: " + c.getAutor().getIdJugador() +
                               ", Juego ID: " + c.getJuego().getIdJuego());
        }
        //////RESEÑA/////////////////////////////////////////////////
         ArrayList<Resena> resenas = resenaDAO.listarTodas();
        System.out.println("\n Lista de resenas activas:");
        for (Resena r : resenas){
            System.out.println("ID: " + r.getIdResena() +
                               ", Comentario: " + r.getComentario() +
                               ", Fecha: " + r.getFechaPublicacion() +
                               ", Calificacion ID: " + r.getCalificacion().getIdCalificacion() +
                               ", Autor ID: " + r.getAutor().getIdJugador() +
                               ", Juego ID: " + r.getJuego().getIdJuego());
        }
        ////////////////////OBTENER POR ID///////////////////////////
        //////CALIFICACION//////////////////////////////////////////
        Calificacion buscadaC = calificacionDAO.obtenerPorId(idCalificacionInsertada);
        if (buscadaC != null) {
            System.out.println("\n Calificación obtenida:");
            System.out.println("Puntaje: " + buscadaC.getPuntuacion());
        } else {
            System.out.println("No se encontró la calificación.");
        }


        
        //modificar jugador
        biblioteca.get(0).setCantidadDeJuegos(3);
        daoBiblioteca.modificar(biblioteca.get(0));
        // obtener por ID
        daoBiblioteca.obtenerPorId(2);

        //CRUD para Juego
        Juego juego1 = new Juego("League of Legends","Juego de estrategia",0.0,1.0,"ImagenX",
                               sdf.parse("2025-04-20"),"Requisitos minimos","Requisitos recomendados",
                               10.0,sdf.parse("2025-04-20"),Genero.ESTRATEGIA, ModeloNegocio.FREE_TO_PLAY,
                               d1);

        JuegoBO juegoBO = new JuegoBO();
        juegoBO.insertar(juego1);

        //Listar todos los juegos
        ArrayList<Juego> juegos = juegoBO.listarTodos();
        for(Juego j : juegos){
            System.out.println(j);
        }

        //Modificar un juego
        juegos.get(0).setTitulo("League of Legends Modificado");
        juegoBO.modificar(juegos.get(0));
        //Listar nuevamente para ver la modificacion
        juegos = juegoBO.listarTodos();
        for(Juego j : juegos){
            System.out.println(j);
        }

        //Eliminar un juego
        juegoBO.eliminar(juegos.get(0).getIdJuego());

        //Listar nuevamente para ver la eliminacion
        juegos = juegoBO.listarTodos();

        for(Juego j : juegos){
            System.out.println(j);
        }

        // Obtener un juego por ID
        Juego juegoObtenido = juegoBO.obtenerPorId(juegos.get(0).getIdJuego());
        System.out.println("Juego obtenido por ID: " + juegoObtenido);

        //CRUD para JuegoAdquirido
        //inserto un nuevo juego adquirido
        JuegoAdquirido juegoAdquirido1 = new JuegoAdquirido(b1, juego1, sdf.parse("2025-04-20"), sdf.parse("2025-04-20"), 55, true);
        
        JuegoAdquiridoBO juegoAdquiridoBO = new JuegoAdquiridoBO();
        //Insertar juego adquirido
        juegoAdquiridoBO.insertar(juegoAdquirido1);
        //Listar todos los juegos adquiridos
        ArrayList<JuegoAdquirido> juegosAdquiridos = juegoAdquiridoBO.listarTodos();
        for(JuegoAdquirido ja : juegosAdquiridos){
            System.out.println(ja);
        }
        //Modificar un juego adquirido
        juegosAdquiridos.get(0).setTiempoJuego(100);
        juegoAdquiridoBO.modificar(juegosAdquiridos.get(0));
        //Listar nuevamente para ver la modificacion
        juegosAdquiridos = juegoAdquiridoBO.listarTodos();
        for(JuegoAdquirido ja : juegosAdquiridos){
            System.out.println(ja);
        }
        //Eliminar un juego adquirido
        juegoAdquiridoBO.eliminar(juegosAdquiridos.get(0).getJuego().getIdJuego());
        //Listar nuevamente para ver la eliminacion
        juegosAdquiridos = juegoAdquiridoBO.listarTodos();
        for(JuegoAdquirido ja : juegosAdquiridos){
            System.out.println(ja);
        }
        // Obtener un juego adquirido por ID
        JuegoAdquirido juegoAdquiridoObtenido = juegoAdquiridoBO.obtenerPorId(juegosAdquiridos.get(0).getJuego().getIdJuego());
        System.out.println("Juego adquirido obtenido por ID: " + juegoAdquiridoObtenido);
        /////RESEÑA//////////////////////////////////////////////
        Resena buscadaR = resenaDAO.obtenerPorId(idResenaInsertada);
        if (buscadaR != null) {
            System.out.println("\n Reseña obtenida por ID:");
            System.out.println("Comentario: " + buscadaR.getComentario());
        } else {
            System.out.println("No se encontró la reseña con ID: " + idResenaInsertada);
        }
           ////////////////////ELIMINAR///////////////////////////
        //////CALIFICACION//////////////////////////////////////////
        int elimResultado = calificacionDAO.eliminar(idCalificacionInsertada);
        System.out.println("Resultado de eliminación: " + elimResultado);
        //////RESEÑA//////////////////////////////////////////
        int resultadoElim = resenaDAO.eliminar(idResenaInsertada);
        System.out.println("Resultado de eliminación: " + resultadoElim);
    }
}
