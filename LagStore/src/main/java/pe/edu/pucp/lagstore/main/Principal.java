package pe.edu.pucp.lagstore.main;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.CarroCompraDAO;
import pe.edu.pucp.lagstore.compra.dao.CarteraDAO;
import pe.edu.pucp.lagstore.compra.dao.RecargaDAO;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.compra.model.CarroCompraBO;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.compra.model.CarteraBO;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import pe.edu.pucp.lagstore.compra.model.Recarga;
import pe.edu.pucp.lagstore.compra.model.RecargaBO;
import pe.edu.pucp.lagstore.compra.mysql.CarroCompraMySQL;
import pe.edu.pucp.lagstore.compra.mysql.CarteraMySQL;
import pe.edu.pucp.lagstore.compra.mysql.RecargaMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestJuegos.Model.BibliotecaBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoAdquiridoBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoBO;
import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.BibliotecaMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.dao.AdministradorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.AdministradorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.DesarrolladorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.JugadorMySQL;
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

    public static void test_bibliotecas()throws ParseException{
        Biblioteca biblioteca1 = new Biblioteca(20.50,4);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(5);
        biblioteca1.setUsuario(usuario);
        
        Biblioteca biblioteca2 = new Biblioteca(55.50,7);
        biblioteca2.setUsuario(usuario);

        BibliotecaBO bibliotecaBO = new BibliotecaBO();
        
        //inserto un nueva biblioteca
        bibliotecaBO.insertar(biblioteca1);
        bibliotecaBO.insertar(biblioteca2);

        //listar bibliotecas
        ArrayList<Biblioteca>biblioteca = bibliotecaBO.listarBibliotecas();
        for(Biblioteca j : biblioteca){
            System.out.println(j);
        }

        //modificar biblioteca
        biblioteca.get(0).setCantidadDeJuegos(3);
        bibliotecaBO.modificar(biblioteca.get(0));

        // obtener por ID
        Biblioteca biblio = bibliotecaBO.obtenerPorId(2);
        System.out.println(biblio);

        //eliminar biblioteca
        bibliotecaBO.eliminar(2);
    }

    public static void test_juegos() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Desarrollador d1 = new Desarrollador("Gustavo","gusgus@hotmail.com","555",sdf.parse("2025-04-24"),"961908444","ImagenX","191-500-200",5500.50);

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
    }

    public static void test_juegoAdquiridos() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Desarrollador d1 = new Desarrollador("Gustavo","gusgus@hotmail.com","555",sdf.parse("2025-04-24"),"961908444","ImagenX","191-500-200",5500.50);
        Biblioteca b1 = new Biblioteca(20.50,4);
        Juego juego1 = new Juego("League of Legends","Juego de estrategia",0.0,1.0,"ImagenX",
                               sdf.parse("2025-04-20"),"Requisitos minimos","Requisitos recomendados",
                               10.0,sdf.parse("2025-04-20"),Genero.ESTRATEGIA, ModeloNegocio.FREE_TO_PLAY,
                               d1);

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



