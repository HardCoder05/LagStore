package pe.edu.pucp.lagstore.main;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.compra.model.CarroCompraBO;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.compra.model.CarteraBO;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import pe.edu.pucp.lagstore.compra.model.Recarga;
import pe.edu.pucp.lagstore.compra.model.RecargaBO;

import pe.edu.pucp.lagstore.gestJuegos.Model.BibliotecaBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoAdquiridoBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoBO;

import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestjuegos.model.JuegoAdquirido;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;
import pe.edu.pucp.lagstore.gestusuarios.model.AdministradorBO;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;
import pe.edu.pucp.lagstore.gestusuarios.model.DesarrolladorBO;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;
import pe.edu.pucp.lagstore.gestusuarios.model.JugadorBO;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;

import pe.edu.pucp.lagstore.valoracion.model.Calificacion;
import pe.edu.pucp.lagstore.valoracion.model.CalificacionBO;
import pe.edu.pucp.lagstore.valoracion.model.Resena;
import pe.edu.pucp.lagstore.valoracion.model.ResenaBO;


public class Principal {
    public static void main(String[] args)throws Exception{
//        test_jugadores();//se usan metodos de jugadores
//        test_desarrolladores();//se usan metodos de jugadores
//        test_administradores();//se usan metodos de administradores
        
//        test_bibliotecas();
//        test_juegos();
//        test_juegoAdquiridos();
 //       test_Recarga();
 //       test_Calificacion();
//        test_Resena();
//        JugadorBO jugadorBO=new JugadorBO();
//        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//        Jugador j1=new Jugador("Pedroo","pedro@hotmail.com","789",sdf.parse("2025-04-20"),"969627718","ImagenX","pedro123");
//        int resultado = jugadorBO.insertar(j1);
//        System.out.println(resultado);
    }
    
    private static void test_jugadores()throws ParseException{
        JugadorBO jugadorBO=new JugadorBO();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        //1.JUGADOR
//        inserto un nuevo jugador
        Jugador j1=new Jugador("Miguel","miguel@hotmail.com","789",sdf.parse("2025-04-20"),"969627718","ImagenX","miguel123");
        Jugador j2=new Jugador("cubas ","cubas@hotmail.com","753",sdf.parse("2025-04-20"),"852322","ImagenX","gohanGol");
        Jugador j3=new Jugador("Alesandro ","alesandro@hotmail.com","741",sdf.parse("2025-04-20"),"227423","ImagenX","ales50");
        Jugador j4=new Jugador("Luis ","luis@hotmail.com","785",sdf.parse("2025-04-20"),"785222","ImagenX","luis20");
        jugadorBO.insertar(j1);jugadorBO.insertar(j2);jugadorBO.insertar(j3);jugadorBO.insertar(j4);
//        listar jugadores
        ArrayList<Jugador>jugadores = jugadorBO.listarJugadores();
        for(Jugador j : jugadores){
            System.out.println(j);
        }
//        modificar jugador
        Jugador jugadorAModificar=new Jugador();
        jugadorAModificar.setIdJugador(60);
        jugadorAModificar.setNickname("OTRO NUEVO");
        jugadorAModificar.setNombre("NAME NEW");
        jugadorAModificar.setEmail("aaaa@htomail.com");
        jugadorAModificar.setContrasena("nuevaContra");
        jugadorAModificar.setFechaRegistro(sdf.parse("2025-01-01"));
        jugadorAModificar.setTelefono("999111222");
        jugadorAModificar.setFotoDePerfil("FFF"); 
        jugadorBO.modificar(jugadorAModificar);
        //volvemos a listar para ver la modificacion
        jugadores=jugadorBO.listarJugadores();
        for(Jugador j : jugadores){
            System.out.println(j);
        }
        //eliminar jugador
        jugadorBO.eliminar(200);
        // obtener por ID
        jugadorBO.obtenerPorId(201);
    }
    
    private static void test_desarrolladores()throws ParseException{
        DesarrolladorBO desarrolladorBO=new DesarrolladorBO();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//        2.DESARROLLADOR
//        insertamos desarrolladores
        Desarrollador d1=new Desarrollador("Gustavo","gusgus@hotmail.com","555",sdf.parse("2025-04-24"),"961908444","ImagenX","191-500-200",5500.50);
        Desarrollador d2=new Desarrollador("Polar","polar@hotmail.com","444",sdf.parse("2025-04-24"),"991474587","ImagenX","191-555-002",4500.50);
        Desarrollador d3=new Desarrollador("Rex","rex@hotmail.com","666",sdf.parse("2025-04-24"),"992784711","ImagenX","152-200-001",3500.50);
        Desarrollador d4=new Desarrollador("Leocho","leo@hotmail.com","777",sdf.parse("2025-04-24"),"961694521","ImagenX","122-400-911",2500.50);
        desarrolladorBO.insertar(d1);desarrolladorBO.insertar(d2);desarrolladorBO.insertar(d3);desarrolladorBO.insertar(d4);
        //listar desarrolladores
        ArrayList<Desarrollador>desarrolladores = desarrolladorBO.listarDesarrolladores();
        for(Desarrollador d : desarrolladores){
            System.out.println(d);
        }
        //modificar desarrollador
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
        desarrolladorBO.modificar(desarroMod);
        //eliminar desarrollador
        desarrolladorBO.eliminar(100);
        // obtener por ID
        desarrolladorBO.obtenerPorId(101);
    }
    
    private static void test_administradores()throws ParseException{
        AdministradorBO administradorBO=new AdministradorBO();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        //3.ADMINISTRADOR
        //insertamos administrador
        Administrador a1=new Administrador("Tito","tit@hotmail.com","12563",sdf.parse("2025-04-24"),"91324546","ImagenX","SIN_PERMISO");                                       
        Administrador a2=new Administrador("Juan","jjt@hotmail.com","852",sdf.parse("2025-04-24"),"78522","ImagenX","CON_PERMISO");  
        Administrador a3=new Administrador("Luis","tllt@hotmail.com","75321",sdf.parse("2025-04-24"),"9632587","ImagenX","CON_PERMISO");  
        Administrador a4=new Administrador("Johar","johar@hotmail.com","9852",sdf.parse("2025-04-24"),"91324546","ImagenX","CON_PERMISO");  
        administradorBO.insertar(a1);administradorBO.insertar(a2);administradorBO.insertar(a3);administradorBO.insertar(a4);
        //listar Administrador
        ArrayList<Administrador>administradores = administradorBO.listarAdministradores();
        for(Administrador a : administradores){
            System.out.println(a);
        }
        
        //Eliminar Administrador
        administradorBO.eliminar(92);
        
        //Modificar Administrador
        Administrador admiModif=new Administrador();
        admiModif.setIdAdministrador(94);
        admiModif.setNombre("NAME NEW");
        admiModif.setEmail("aaaa@htomail.com");
        admiModif.setContrasena("nuevaContra");
        admiModif.setFechaRegistro(sdf.parse("2025-01-01"));
        admiModif.setTelefono("999111222");
        admiModif.setFotoDePerfil("FFF");
        admiModif.setRolAdministrativo("Nuevo rol aplicado");
        
        administradorBO.modificar(admiModif);
        
        
        //volvemos a listar para ver la modificacion
        administradores=administradorBO.listarAdministradores();
        for(Administrador a : administradores){
            System.out.println(a);
        }
        //eliminar desarrollador
        administradorBO.eliminar(11);
        // obtener por ID
        administradorBO.obtenerPorId(225);
        
        

    }

    public static void test_bibliotecas() {
        Usuario usuario = new Usuario(); // Juan Pérez (id = 1, ya insertado)
        usuario.setIdUsuario(1);

        Biblioteca biblioteca1 = new Biblioteca(20.50, 4);
        biblioteca1.setUsuario(usuario);
        Biblioteca biblioteca2 = new Biblioteca(55.50, 7);
        biblioteca2.setUsuario(usuario);

        BibliotecaBO bibliotecaBO = new BibliotecaBO();

        bibliotecaBO.insertar(biblioteca1);
        bibliotecaBO.insertar(biblioteca2);

        ArrayList<Biblioteca> bibliotecas = bibliotecaBO.listarBibliotecas();
        for (Biblioteca b : bibliotecas) {
            System.out.println(b);
        }

        // Modificar la primera biblioteca
        bibliotecas.get(0).setCantidadDeJuegos(3);
        bibliotecaBO.modificar(bibliotecas.get(0));

        Biblioteca biblio = bibliotecaBO.obtenerPorId(bibliotecas.get(0).getIdBiblioteca());
        System.out.println("Obtenido por ID: " + biblio);

        // Eliminar la segunda biblioteca
        bibliotecaBO.eliminar(bibliotecas.get(1).getIdBiblioteca());
    }


    public static void test_juegos() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Desarrollador ya registrado en la BD con idUsuario = 2
        Usuario u = new Usuario();
        u.setIdUsuario(2);

        Desarrollador d1 = new Desarrollador();
        d1.setIdDesarrollador(u.getIdUsuario());

        Juego juego1 = new Juego(
            "Lag Adventure", "Un nuevo RPG lleno de bugs", 9.99, 1.0, "lag.jpg",
            sdf.parse("2025-06-01"), "Intel i3, 4GB", "Intel i5, 8GB", 15.0,
            sdf.parse("2025-06-15"), Genero.Rol, ModeloNegocio.Paga, d1
        );

        JuegoBO juegoBO = new JuegoBO();
        juegoBO.insertar(juego1);

        ArrayList<Juego> juegos = juegoBO.listarTodos();
        for (Juego j : juegos) {
            System.out.println(j.getIdJuego()+ " " + j.getTitulo());
        }

        // Modificar el primero
        juegos.get(0).setTitulo("Lag Adventure Deluxe");
        juegoBO.modificar(juegos.get(0));

        Juego juegoObtenido = juegoBO.obtenerPorId(juegos.get(0).getIdJuego());
        System.out.println("Juego por ID: " + juegoObtenido.getIdJuego());

        // Eliminar el último juego agregado
//         juegoBO.eliminar(juegos.get(0).getIdJuego());

        juegos = juegoBO.listarTodos();
        for (Juego j : juegos) {
            System.out.println(j.getIdJuego()+ " " + j.getTitulo());
        }
    }


    public static void test_juegoAdquiridos() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Usuario = Juan Pérez (id = 1)
        Usuario u = new Usuario();
        u.setIdUsuario(1);

        // Biblioteca ya creada (por test_bibliotecas o script), usaremos id = 1 por ejemplo
        Biblioteca b1 = new Biblioteca();
        b1.setIdBiblioteca(1);
        b1.setUsuario(u);

        // Juego ya creado (por test_juegos o script), usaremos uno existente id = 1 o 2
        Juego juego = new Juego();
        juego.setIdJuego(1);

        JuegoAdquirido juegoAdquirido1 = new JuegoAdquirido(
            b1,
            juego,
            sdf.parse("2025-05-20"),
            sdf.parse("2025-05-22"),
            4.5,
            true
        );

        JuegoAdquiridoBO juegoAdquiridoBO = new JuegoAdquiridoBO();
        juegoAdquiridoBO.insertar(juegoAdquirido1);

        ArrayList<JuegoAdquirido> juegosAdquiridos = juegoAdquiridoBO.listarTodos();
        for (JuegoAdquirido ja : juegosAdquiridos) {
            System.out.println(ja.getJuego().getTitulo());
        }

        juegosAdquiridos.get(0).setTiempoJuego(10.0);
        juegoAdquiridoBO.modificar(juegosAdquiridos.get(0));

        JuegoAdquirido juegoAdquiridoObtenido = juegoAdquiridoBO.obtenerPorId(juego.getIdJuego());
        System.out.println("Juego adquirido obtenido: " + juegoAdquiridoObtenido.getJuego().getTitulo());

        //juegoAdquiridoBO.eliminar(juego.getIdJuego());

        juegosAdquiridos = juegoAdquiridoBO.listarTodos();
        for (JuegoAdquirido ja : juegosAdquiridos) {
            System.out.println(ja.getJuego().getTitulo());
        }
    }

    
    private static void test_Compras() throws ParseException{
        //test_metodosPago(); //los metodos de pago se insertan en la BD
        test_Cartera(); // se prueba el funcionamiento de Cartera
        test_Recarga(); // se prueba el funcionamiento de Recarga
        test_CarroCompra(); // se prueba el funcionamiento de CarroCompra
    }
    
    
    private static void test_Cartera() throws ParseException {
        CarteraBO BOCartera = new CarteraBO();
        JugadorBO BOJugador = new JugadorBO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Crear jugador y asignar ID real
        Jugador jugador = new Jugador("Mario Bros", "mario@hotmail.com", "789",
                sdf.parse("2025-04-20"), "969627718", "ImagenX", "Mario&Luiggi");
        int idJug = BOJugador.insertar(jugador);
        jugador.setIdJugador(idJug);

        // Crear cartera para jugador
        Cartera cartera = new Cartera();
        cartera.setSaldoActual(100.50);
        cartera.setJugador(jugador);

        int idInsertado = BOCartera.insertar(cartera);
        cartera.setIdCartera(idInsertado);
        System.out.println("Cartera creada con ID: " + idInsertado);

        // Obtener cartera por ID
        Cartera carteraObtenida = BOCartera.obtenerPorId(idInsertado);
        if (carteraObtenida != null) {
            System.out.println(" Cartera obtenida: ID=" + carteraObtenida.getIdCartera() +
                    ", Saldo=" + carteraObtenida.getSaldoActual() +
                    ", JugadorID=" + carteraObtenida.getJugador().getIdJugador() +
                    ", Activo=" + carteraObtenida.getActivo());
        } else {
            System.out.println("⚠️ No se encontró la cartera insertada.");
            return;
        }

        // Modificar cartera (opcionalmente cambiar jugador, se recomienda no hacerlo si no existe)
        carteraObtenida.setSaldoActual(200.00);
        carteraObtenida.getJugador().setIdJugador(idJug);  // asegúrate que el jugador existe
        BOCartera.modificar(carteraObtenida);
        System.out.println(" Cartera modificada.");

        // Consultar cartera modificada
        Cartera carteraModificada = BOCartera.obtenerPorId(idInsertado);
        if (carteraModificada != null) {
            System.out.println(" Cartera modificada: ID=" + carteraModificada.getIdCartera() +
                    ", Saldo=" + carteraModificada.getSaldoActual() +
                    ", JugadorID=" + carteraModificada.getJugador().getIdJugador() +
                    ", Activo=" + carteraModificada.getActivo());
        }

        // Eliminar cartera (soft delete)
        BOCartera.eliminar(idInsertado);
        System.out.println("Cartera eliminada (soft-delete).");

        // Consultar cartera eliminada
        Cartera carteraEliminada = BOCartera.obtenerPorId(idInsertado);
        if (carteraEliminada != null) {
            System.out.println("Cartera eliminada: ID=" + carteraEliminada.getIdCartera() +
                    ", Saldo=" + carteraEliminada.getSaldoActual() +
                    ", JugadorID=" + carteraEliminada.getJugador().getIdJugador() +
                    ", Activo=" + carteraEliminada.getActivo());
        }

        // Listar todas las carteras
        ArrayList<Cartera> lista = BOCartera.listaCartera();
        System.out.println("\nListado de todas las carteras:");
        for (Cartera c : lista) {
            System.out.println("ID=" + c.getIdCartera() +
                    ", Saldo=" + c.getSaldoActual() +
                    ", JugadorID=" + c.getJugador().getIdJugador() +
                    ", Activo=" + c.getActivo());
        }
    }
    
    private static void test_Recarga() throws ParseException {
        RecargaBO BORecarga = new RecargaBO();
        CarteraBO BOCartera = new CarteraBO();

        // Crear nueva Recarga
        Recarga recarga = new Recarga();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        recarga.setFechaRecarga(sdf.parse("2025-05-13"));
        recarga.setMonto(50.00);

        // Obtener Cartera existente (cambia el ID si necesario)
        int idCartera = 2; // ⚠️ Cambia este ID a uno válido en tu BD
        Cartera cartera = BOCartera.obtenerPorId(idCartera);
        if (cartera == null) {
            System.out.println(" No se encontró cartera con ID: " + idCartera);
            return;
        }

        recarga.setCartera(cartera);
        recarga.setMetodoPago(MetodoPago.Mastercard);  // Usa tu enum correctamente

        //  Insertar Recarga
        int idInsertado = BORecarga.insertar(recarga);
        System.out.println(" Recarga creada con ID: " + idInsertado);

        // Obtener por ID
        Recarga recargaObtenida = BORecarga.obtenerPorId(idInsertado);
        if (recargaObtenida != null) {
            System.out.println("Recarga obtenida: ID=" + recargaObtenida.getIdRecarga() +
                    ", Monto=" + recargaObtenida.getMonto() +
                    ", CarteraID=" + recargaObtenida.getCartera().getIdCartera() +
                    ", MetodoPago=" + recargaObtenida.getMetodoPago() +
                    ", Activo=" + recargaObtenida.getActivo());
        }

        // Modificar Recarga
        recargaObtenida.setMonto(89.99);
        recargaObtenida.setMetodoPago(MetodoPago.PagoEfectivo);   // Cambio de método
        BORecarga.modificar(recargaObtenida);
        System.out.println(" Recarga modificada.");

        //  Consultar modificada
        Recarga recargaModificada = BORecarga.obtenerPorId(idInsertado);
        if (recargaModificada != null) {
            System.out.println("Recarga modificada: ID=" + recargaModificada.getIdRecarga() +
                    ", Monto=" + recargaModificada.getMonto() +
                    ", MetodoPago=" + recargaModificada.getMetodoPago() +
                    ", Activo=" + recargaModificada.getActivo());
        }

        // 
        BORecarga.eliminar(idInsertado);
        System.out.println("Recarga eliminada (soft-delete).");

        //  Consultar eliminada
        Recarga recargaEliminada = BORecarga.obtenerPorId(idInsertado);
        if (recargaEliminada != null) {
            System.out.println(" Recarga eliminada: ID=" + recargaEliminada.getIdRecarga() +
                    ", Monto=" + recargaEliminada.getMonto() +
                    ", MetodoPago=" + recargaEliminada.getMetodoPago() +
                    ", Activo=" + recargaEliminada.getActivo());
        }

        // Listar todas
        ArrayList<Recarga> lista = BORecarga.listaCarroRecarga();
        System.out.println("\n Listado de todas las recargas:");
        for (Recarga r : lista) {
            System.out.println("ID=" + r.getIdRecarga() +
                    ", Monto=" + r.getMonto() +
                    ", MetodoPago=" + r.getMetodoPago() +
                    ", CarteraID=" + r.getCartera().getIdCartera() +
                    ", Activo=" + r.getActivo());
        }
    }


    private static void test_CarroCompra() throws ParseException {
        CarroCompraBO BOCarro = new CarroCompraBO();
        JugadorBO BOJugador = new JugadorBO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Crear jugador y obtener ID real
        Jugador jugador = new Jugador("Luigi Bros", "luigi@hotmail.com", "789",
                sdf.parse("2025-04-20"), "969627718", "ImagenX", "Luigi&Mario");
        int idJugador = BOJugador.insertar(jugador);
        jugador.setIdJugador(idJugador);

        //  Crear CarroCompra para el jugador
        CarroCompra carro = new CarroCompra();
        carro.setTotalEstimado(120.00);
        carro.setJugador(jugador);

        int idInsertado = BOCarro.insertar(carro);
        carro.setIdCarroCompra(idInsertado);
        System.out.println("CarroCompra creado con ID: " + idInsertado);

        //  Obtener por ID
        CarroCompra carroObtenido = BOCarro.obtenerPorId(idInsertado);
        if (carroObtenido != null) {
            System.out.println(" CarroCompra obtenido: ID=" + carroObtenido.getIdCarroCompra() +
                    ", Total=" + carroObtenido.getTotalEstimado() +
                    ", JugadorID=" + carroObtenido.getJugador().getIdJugador() +
                    ", Activo=" + carroObtenido.getActivo());
        }

        // Modificar CarroCompra
        carroObtenido.setTotalEstimado(200.00);
        BOCarro.modificar(carroObtenido);
        System.out.println(" CarroCompra modificado.");

        //  Consultar modificado
        CarroCompra carroModificado = BOCarro.obtenerPorId(idInsertado);
        if (carroModificado != null) {
            System.out.println(" CarroCompra modificado: ID=" + carroModificado.getIdCarroCompra() +
                    ", Total=" + carroModificado.getTotalEstimado() +
                    ", JugadorID=" + carroModificado.getJugador().getIdJugador() +
                    ", Activo=" + carroModificado.getActivo());
        }

        // Eliminar CarroCompra (soft-delete)
        BOCarro.eliminar(idInsertado);
        System.out.println(" CarroCompra eliminado (soft-delete).");

        //  Consultar eliminado
        CarroCompra carroEliminado = BOCarro.obtenerPorId(idInsertado);
        if (carroEliminado != null) {
            System.out.println("CarroCompra eliminado: ID=" + carroEliminado.getIdCarroCompra() +
                    ", Total=" + carroEliminado.getTotalEstimado() +
                    ", JugadorID=" + carroEliminado.getJugador().getIdJugador() +
                    ", Activo=" + carroEliminado.getActivo());
        }

        //  Listar todos los CarrosCompra
        ArrayList<CarroCompra> lista = BOCarro.listaCarroCompra();
        System.out.println("\nListado de todos los CarroCompra:");
        for (CarroCompra c : lista) {
            System.out.println("ID=" + c.getIdCarroCompra() +
                    ", Total=" + c.getTotalEstimado() +
                    ", JugadorID=" + c.getJugador().getIdJugador() +
                    ", Activo=" + c.getActivo());
        }
    }
    
    public static void test_Calificacion()throws ParseException{
        CalificacionBO calificacionBO = new CalificacionBO();
        // Crear objetos necesarios
        Jugador jugador = new Jugador();
        jugador.setIdJugador(2); // Debe existir

        Juego juego = new Juego();
        juego.setIdJuego(2); // Debe existir

        // Insertar calificación
        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setAutor(jugador);
        nuevaCalificacion.setJuego(juego);
        nuevaCalificacion.setFechaPuntuacion(Date.valueOf(LocalDate.now()));
        nuevaCalificacion.setPuntuacion(5);
        nuevaCalificacion.setActivo(1);

        int idInsertado = calificacionBO.insertar(nuevaCalificacion);
        System.out.println("Calificacion insertada con ID: " + idInsertado);

        // Modificar calificación
        nuevaCalificacion.setIdCalificacion(idInsertado);
        nuevaCalificacion.setPuntuacion(3);
        int modResultado = calificacionBO.modificar(nuevaCalificacion);
        System.out.println("Resultado de modificacion: " + modResultado);

        // Listar calificaciones
        ArrayList<Calificacion> calificaciones = calificacionBO.listarBibliotecas();
        System.out.println("\nLista de calificaciones activas:");
        for (Calificacion c : calificaciones) {
            System.out.println("ID: " + c.getIdCalificacion() +
                               ", Puntaje: " + c.getPuntuacion() +
                               ", Jugador ID: " + c.getAutor().getIdJugador() +
                               ", Juego ID: " + c.getJuego().getIdJuego());
        }

        // Obtener por ID
        Calificacion calificacion = calificacionBO.obtenerPorId(idInsertado);
        if (calificacion != null) {
            System.out.println("\nCalificacion obtenida: Puntaje = " + calificacion.getPuntuacion());
        }

        // Eliminar calificación
        int elimResultado = calificacionBO.eliminar(idInsertado);
        System.out.println("Resultado de eliminacion: " + elimResultado);
    }
    
    public static void test_Resena()throws ParseException{
         ResenaBO resenaBO = new ResenaBO();

        // Crear objetos necesarios
        Jugador jugador = new Jugador();
        jugador.setIdJugador(2); // Debe existir

        Juego juego = new Juego();
        juego.setIdJuego(2); // Debe existir

        Calificacion calificacion = new Calificacion();
        calificacion.setIdCalificacion(1); // Debe existir

        // Insertar reseña
        Resena nuevaResena = new Resena();
        nuevaResena.setAutor(jugador);
        nuevaResena.setJuego(juego);
        nuevaResena.setComentario("¡Muy divertido!");
        nuevaResena.setFechaPublicacion(Date.valueOf(LocalDate.now()));
        nuevaResena.setCalificacion(calificacion);
        nuevaResena.setActivo(1);

        int idInsertado = resenaBO.insertar(nuevaResena);
        System.out.println("Reseña insertada con ID: " + idInsertado);

        // Modificar reseña
        nuevaResena.setIdResena(idInsertado);
        nuevaResena.setComentario("¡Muy divertido y desafiante!");
        int resultadoMod = resenaBO.modificar(nuevaResena);
        System.out.println("Resultado de modificacion: " + resultadoMod);

        // Listar reseñas
        ArrayList<Resena> resenas = resenaBO.listarBibliotecas();
        System.out.println("\nLista de resenas activas:");
        for (Resena r : resenas) {
            System.out.println("ID: " + r.getIdResena() +
                               ", Comentario: " + r.getComentario() +
                               ", Calificacion ID: " + r.getCalificacion().getIdCalificacion() +
                               ", Jugador ID: " + r.getAutor().getIdJugador() +
                               ", Juego ID: " + r.getJuego().getIdJuego());
        }

        // Obtener por ID
        Resena resena = resenaBO.obtenerPorId(idInsertado);
        if (resena != null) {
            System.out.println("\nResena obtenida: Comentario = " + resena.getComentario());
        }

        // Eliminar reseña
        int resultadoElim = resenaBO.eliminar(idInsertado);
        System.out.println("Resultado de eliminacion: " + resultadoElim);
    }
    
}



