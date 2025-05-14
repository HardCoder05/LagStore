package pe.edu.pucp.lagstore.main;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.BibliotecaMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.AdministradorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.AdministradorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.DesarrolladorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.JugadorMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;
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
