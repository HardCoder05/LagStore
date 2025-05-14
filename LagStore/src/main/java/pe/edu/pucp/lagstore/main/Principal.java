package pe.edu.pucp.lagstore.main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoAdquiridoBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoBO;
import pe.edu.pucp.lagstore.compra.dao.CarroCompraDAO;
import pe.edu.pucp.lagstore.compra.dao.CarteraDAO;
import pe.edu.pucp.lagstore.compra.dao.MetodoPagoDAO;
import pe.edu.pucp.lagstore.compra.dao.RecargaDAO;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import pe.edu.pucp.lagstore.compra.model.Recarga;
import pe.edu.pucp.lagstore.compra.mysql.CarroCompraMySQL;

import pe.edu.pucp.lagstore.compra.mysql.CarteraMySQL;


import pe.edu.pucp.lagstore.compra.mysql.MetodoPagoMySQL;
import pe.edu.pucp.lagstore.compra.mysql.RecargaMySQL;

import java.util.HashSet;
import java.util.Set;
import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.BibliotecaMySQL;

import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;

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

        
        test_metodosPago(); //los metodos de pago se insertan en la BD
        test4(); // se prueba el funcionamiento de Cartera
        test5(); // se prueba el funcionamiento de Recarga
        test6(); // se prueba el funcionamiento de CarroCompra
           
    }
    
    
    private static void test_metodosPago() throws ParseException{
        /*
        Ejecutar esto en la BD antes de probr el test por primera vez:     
            SET FOREIGN_KEY_CHECKS = 0;
            TRUNCATE TABLE MetodoPago;
            SET FOREIGN_KEY_CHECKS = 1;
        */
        MetodoPagoDAO daoMetodoPago = new MetodoPagoMySQL();
        for (MetodoPago metodo : MetodoPago.values()) {
            daoMetodoPago.insertar(metodo);
        JugadorDAO daoJugador=new JugadorMySQL();
        DesarrolladorDAO daoDesarrollador=new DesarrolladorMySQL();
        AdministradorDAO daoAdministrador=new AdministradorMySQL();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        //1.JUGADOR
//        JugadorDAO daoJugador=new JugadorMySQL();
//        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        //inserto un nuevo jugador
        /*
        Jugador j1=new Jugador("Miguel","miguel@hotmail.com","789",sdf.parse("2025-04-20"),"969627718","ImagenX","miguel123");
        Jugador j2=new Jugador("cubas ","cubas@hotmail.com","753",sdf.parse("2025-04-20"),"852322","ImagenX","gohanGol");
        Jugador j3=new Jugador("Alesandro ","alesandro@hotmail.com","741",sdf.parse("2025-04-20"),"227423","ImagenX","ales50");
        Jugador j4=new Jugador("Luis ","luis@hotmail.com","785",sdf.parse("2025-04-20"),"785222","ImagenX","luis20");
        
        daoJugador.insertar(j1);
        daoJugador.insertar(j2);
        daoJugador.insertar(j3);
        daoJugador.insertar(j4);*/
        //listar jugadores
//        ArrayList<Jugador>jugadores = daoJugador.listar();
//        for(Jugador j : jugadores){
//            System.out.println(j);
//        }
//        //modificar jugador
//        Jugador jugadorAModificar=new Jugador();
//        jugadorAModificar.setIdJugador(60);
//        jugadorAModificar.setNickname("OTRO NUEVO");
//        jugadorAModificar.setNombre("NAME NEW");
//        jugadorAModificar.setEmail("aaaa@htomail.com");
//        jugadorAModificar.setContrasena("nuevaContra");
//        jugadorAModificar.setFechaRegistro(sdf.parse("2025-01-01"));
//        jugadorAModificar.setTelefono("999111222");
//        jugadorAModificar.setFotoDePerfil("FFF"); 
//        daoJugador.modificar(jugadorAModificar);
//        //volvemos a listar para ver la modificacion
//        /*jugadores=daoJugador.listarTodas();
//        for(Jugador j : jugadores){
//            System.out.println(j);
//        }*/
//        //eliminar jugador
//        daoJugador.eliminar(3);
//        // obtener por ID
//        System.out.println(daoJugador.obtenerPorId(3));
    
        
        //2.DESARROLLADOR
        //inserto desarrolladores
        //insertamos desarrollador
//        Desarrollador d1=new Desarrollador("Gustavo","gusgus@hotmail.com","555",sdf.parse("2025-04-24"),"961908444","ImagenX","191-500-200",5500.50);
//        Desarrollador d2=new Desarrollador("Polar","polar@hotmail.com","444",sdf.parse("2025-04-24"),"991474587","ImagenX","191-555-002",4500.50);
//        Desarrollador d3=new Desarrollador("Rex","rex@hotmail.com","666",sdf.parse("2025-04-24"),"992784711","ImagenX","152-200-001",3500.50);
//        Desarrollador d4=new Desarrollador("Leocho","leo@hotmail.com","777",sdf.parse("2025-04-24"),"961694521","ImagenX","122-400-911",2500.50);
////        
//        daoDesarrollador.insertar(d1);
//        daoDesarrollador.insertar(d2);
//        daoDesarrollador.insertar(d3);
//        daoDesarrollador.insertar(d4);
//        //listar desarrolladores
        ArrayList<Desarrollador>desarrolladores = daoDesarrollador.listarTodos();
        for(Desarrollador d : desarrolladores){
            System.out.println(d);
        }
//        //modificar desarrollador
        Desarrollador desarroMod=new Desarrollador();
        desarroMod.setIdDesarrollador(100);
        desarroMod.setNumeroCuenta("999-111-999");
        desarroMod.setIngresoTotal(1005.50);
        desarroMod.setNombre("NAME NEW");
        desarroMod.setEmail("aaaa@htomail.com");
        desarroMod.setContrasena("nuevaContra");
        desarroMod.setFechaRegistro(sdf.parse("2025-01-01"));
        desarroMod.setTelefono("999111222");
        desarroMod.setFotoDePerfil("FFF"); 
        daoDesarrollador.modificar(desarroMod);
        //volvemos a listar para ver la modificacion
        desarrolladores=daoDesarrollador.listarTodos();
        for(Desarrollador d : desarrolladores){
            System.out.println(d);
        }
        //eliminar desarrollador
        daoDesarrollador.eliminar(100);
        // obtener por ID
        daoDesarrollador.obtenerPorId(100);
        
        
        
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
        //3.ADMINISTRADOR
//        //Creo las bibliotecas de cada ADMINISTRADOR
//        Biblioteca b9=new Biblioteca(16.4,3);
//        daoBiblioteca.insertar(b9);
//        Biblioteca b10=new Biblioteca(18.6,3);
//        daoBiblioteca.insertar(b10);
//        Biblioteca b11=new Biblioteca(15.0,2);
//        daoBiblioteca.insertar(b11);
//        Biblioteca b12=new Biblioteca(48.6,4);
//        daoBiblioteca.insertar(b12);
        //insertamos administrador
        
        //    public Administrador(String nombre,String email,String contrasena,Date fechaRegistro,String telefono, String fotoDePerfil,String rolAdministrativo) {

//        Administrador a1=new Administrador("Tito","tit@hotmail.com","12563",sdf.parse("2025-04-24"),"91324546","ImagenX","SIN_PERMISO");                                       
//        Administrador a2=new Administrador("Juan","jjt@hotmail.com","852",sdf.parse("2025-04-24"),"78522","ImagenX","CON_PERMISO");  
//        Administrador a3=new Administrador("Luis","tllt@hotmail.com","75321",sdf.parse("2025-04-24"),"9632587","ImagenX","CON_PERMISO");  
//        Administrador a4=new Administrador("Johar","johar@hotmail.com","9852",sdf.parse("2025-04-24"),"91324546","ImagenX","CON_PERMISO");  
//        
//        
//        daoAdministrador.insertar(a1);
//        daoAdministrador.insertar(a2);
//        daoAdministrador.insertar(a3);
//        daoAdministrador.insertar(a4);


        //listar Administrador
//        ArrayList<Administrador>administradores = daoAdministrador.listarTodos();
//        for(Administrador a : administradores){
//            System.out.println(a);
//        }
        
        //Eliminar Administrador
        //daoAdministrador.eliminar(92);
        
        //Modificar Administrador
//        Administrador admiModif=new Administrador();
//        admiModif.setIdAdministrador(94);
//        admiModif.setNombre("NAME NEW");
//        admiModif.setEmail("aaaa@htomail.com");
//        admiModif.setContrasena("nuevaContra");
//        admiModif.setFechaRegistro(sdf.parse("2025-01-01"));
//        admiModif.setTelefono("999111222");
//        admiModif.setFotoDePerfil("FFF");
//        admiModif.setRolAdministrativo("Nuevo rol aplicado");
//        
//        daoAdministrador.modificar(admiModif);
        
        
//        //modificar desarrollador
//        administradores.get(0).setRolAdministrativo("rol Nuevo");
//        daoAdministrador.modificar(administradores.get(0));
//        //volvemos a listar para ver la modificacion
//        administradores=daoAdministrador.listarTodas();
//        for(Administrador a : administradores){
//            System.out.println(a);
//        }
//        //eliminar desarrollador
//        daoAdministrador.eliminar(11);
//        // obtener por ID
//        daoAdministrador.obtenerPorId(12);
        
        
         //biblioteca
//        Biblioteca b13=new Biblioteca(20.50,4);
//        Biblioteca b14=new Biblioteca(55.50,7);
//        BibliotecaDAO daoBiblioteca2= new BibliotecaMySQL();
        
        //inserto un nueva biblioteca
//        daoBiblioteca.insertar(b13);
//        daoBiblioteca.insertar(b14);
        //listar jugadores
//        ArrayList<Biblioteca>biblioteca = daoBiblioteca.listarTodas();
//        for(Biblioteca j : biblioteca){
//            System.out.println(j);
//        }
//        //modificar jugador
//        biblioteca.get(0).setCantidadDeJuegos(3);
//        daoBiblioteca.modificar(biblioteca.get(0));
//        // obtener por ID
//        daoBiblioteca.obtenerPorId(2);
        
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
    
    
    private static void test4() throws ParseException{
        CarteraDAO daoCartera = new CarteraMySQL();

        
        Cartera cartera = new Cartera();
        cartera.setSaldoActual(100.50);
        
        Biblioteca b1=new Biblioteca(20.1,3);
        BibliotecaDAO daoBiblioteca= new BibliotecaMySQL();
        daoBiblioteca.insertar(b1);
        
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        // ⚠️ Debes reemplazar con un jugador válido existente en tu BD
        Jugador jugador = new Jugador("Pepe", "800", "pepe@outlook.com", sdf.parse("2025-05-13"), 9616945, "ImagenX", 1,
                             b1, 1, "Menphis22");
        jugador.setIdJugador(1);   // ← Cambia este ID según tu BD
        cartera.setJugador(jugador);

        int idInsertado = daoCartera.insertar(cartera);
        System.out.println("Cartera creada con ID: " + idInsertado);

        //Obtener por ID
        Cartera carteraObtenida = daoCartera.obtenerPorId(idInsertado);
        System.out.println("Obtenida: ID=" + carteraObtenida.getIdCartera() +
                           ", Saldo=" + carteraObtenida.getSaldoActual() +
                           ", JugadorID=" + carteraObtenida.getJugador().getIdJugador() +
                           ", Activo=" + carteraObtenida.getActivo());

       //Modificar la cartera
        carteraObtenida.setSaldoActual(200.00);
        carteraObtenida.getJugador().setIdJugador(1);   // (opcional) cambiar jugador si quieres
        daoCartera.modificar(carteraObtenida);
        System.out.println("Cartera modificada.");

        //Consultando de nuevo
        Cartera carteraModificada = daoCartera.obtenerPorId(idInsertado);
        System.out.println("Modificada: ID=" + carteraModificada.getIdCartera() +
                           ", Saldo=" + carteraModificada.getSaldoActual() +
                           ", JugadorID=" + carteraModificada.getJugador().getIdJugador() +
                           ", Activo=" + carteraModificada.getActivo());

        // Eliminar 
        daoCartera.eliminar(idInsertado);
        System.out.println("Cartera eliminada (soft-delete).");

        //Consultar eliminada
        Cartera carteraEliminada = daoCartera.obtenerPorId(idInsertado);
        System.out.println("Eliminada: ID=" + carteraEliminada.getIdCartera() +
                           ", Saldo=" + carteraEliminada.getSaldoActual() +
                           ", JugadorID=" + carteraEliminada.getJugador().getIdJugador() +
                           ", Activo=" + carteraEliminada.getActivo());

        //Listar todas las carteras
        ArrayList<Cartera> lista = daoCartera.listarTodas();
        System.out.println("\nListado de todas las carteras:");
        for (Cartera c : lista) {
            System.out.println("ID=" + c.getIdCartera() +
                               ", Saldo=" + c.getSaldoActual() +
                               ", JugadorID=" + c.getJugador().getIdJugador() +
                               ", Activo=" + c.getActivo());
        }
    }
    
    private static void test5() throws ParseException {
    RecargaDAO daoRecarga = new RecargaMySQL();

    //Crear Recarga
    Recarga recarga = new Recarga();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    recarga.setFechaRecarga(sdf.parse("2025-05-13"));
    recarga.setMonto(50.00);

    //Se usa una cartera esada en el test4()
    CarteraDAO daoCartera = new CarteraMySQL();
    Cartera cartera = daoCartera.obtenerPorId(5);
    
    // ID existente de Cartera
    recarga.setCartera(cartera);

    recarga.setMetodoPago(MetodoPago.Mastercard); // Usa enum (Visa, Mastercard, etc.)

    int idInsertado = daoRecarga.insertar(recarga);
    System.out.println("Recarga creada con ID: " + idInsertado);

    //Obtener por ID
    Recarga recargaObtenida = daoRecarga.obtenerPorId(idInsertado);
    System.out.println("Obtenida: ID=" + recargaObtenida.getIdRecarga() +
                       ", Monto=" + recargaObtenida.getMonto() +
                       ", CarteraID=" + recargaObtenida.getCartera().getIdCartera() +
                       ", MetodoPago=" + recargaObtenida.getMetodoPago() +
                       ", Activo=" + recargaObtenida.getActivo());

    //Modificar
    recargaObtenida.setMonto(89.99);
    recargaObtenida.setMetodoPago(MetodoPago.PagoEfectivo);
    daoRecarga.modificar(recargaObtenida);
    System.out.println("Recarga modificada.");

    //Consultar de nuevo
    Recarga recargaModificada = daoRecarga.obtenerPorId(idInsertado);
    System.out.println("Modificada: ID=" + recargaModificada.getIdRecarga() +
                       ", Monto=" + recargaModificada.getMonto() +
                       ", MetodoPago=" + recargaModificada.getMetodoPago() +
                       ", Activo=" + recargaModificada.getActivo());

    //Eliminar 
    daoRecarga.eliminar(idInsertado);
    System.out.println("Recarga eliminada (soft-delete).");

    //Consultar eliminada
    Recarga recargaEliminada = daoRecarga.obtenerPorId(idInsertado);
    System.out.println("Eliminada: ID=" + recargaEliminada.getIdRecarga() +
                       ", Monto=" + recargaEliminada.getMonto() +
                       ", MetodoPago=" + recargaEliminada.getMetodoPago() +
                       ", Activo=" + recargaEliminada.getActivo());

    //Listar todas
    ArrayList<Recarga> lista = daoRecarga.listarTodas();
    System.out.println("\nListado de todas las recargas:");
    for (Recarga r : lista) {
        System.out.println("ID=" + r.getIdRecarga() +
                           ", Monto=" + r.getMonto() +
                           ", MetodoPago=" + r.getMetodoPago() +
                           ", CarteraID=" + r.getCartera().getIdCartera() +
                           ", Activo=" + r.getActivo());
    }
}
    
    private static void test6() {
    CarroCompraDAO daoCarro = new CarroCompraMySQL();

    //Crear CarroCompra
    CarroCompra carro = new CarroCompra();
    carro.setTotalEstimado(120.00);

    
    JugadorDAO daoJugador = new JugadorMySQL();
    Jugador jugador = daoJugador.obtenerPorId(16);
    carro.setJugador(jugador);

    int idInsertado = daoCarro.insertar(carro);
    System.out.println("CarroCompra creado con ID: " + idInsertado);

    //Obtener por ID
    CarroCompra carroObtenido = daoCarro.obtenerPorId(idInsertado);
    System.out.println("Obtenido: ID=" + carroObtenido.getIdCarroCompra() +
                       ", Total=" + carroObtenido.getTotalEstimado() +
                       ", JugadorID=" + carroObtenido.getJugador().getIdJugador() +
                       ", Activo=" + carroObtenido.getActivo());

    //Modificar
    carroObtenido.setTotalEstimado(200.00);
    daoCarro.modificar(carroObtenido);
    System.out.println("CarroCompra modificado.");

    //Consultar de nuevo
    CarroCompra carroModificado = daoCarro.obtenerPorId(idInsertado);
    System.out.println("Modificado: ID=" + carroModificado.getIdCarroCompra() +
                       ", Total=" + carroModificado.getTotalEstimado() +
                       ", Activo=" + carroModificado.getActivo());

    //Eliminar 
    daoCarro.eliminar(idInsertado);
    System.out.println("CarroCompra eliminado (soft-delete).");

    //Consultar eliminado
    CarroCompra carroEliminado = daoCarro.obtenerPorId(idInsertado);
    System.out.println("Eliminado: ID=" + carroEliminado.getIdCarroCompra() +
                       ", Total=" + carroEliminado.getTotalEstimado() +
                       ", Activo=" + carroEliminado.getActivo());
    //Listar todos
    ArrayList<CarroCompra> lista = daoCarro.listarTodas();
    System.out.println("\nListado de todos los CarroCompra:");
    for (CarroCompra c : lista) {
        System.out.println("ID=" + c.getIdCarroCompra() +
                           ", Total=" + c.getTotalEstimado() +
                           ", JugadorID=" + c.getJugador().getIdJugador() +
                           ", Activo=" + c.getActivo());
    }
}
}



