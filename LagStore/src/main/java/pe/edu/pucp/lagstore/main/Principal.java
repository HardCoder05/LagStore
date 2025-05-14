package pe.edu.pucp.lagstore.main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestJuegos.Model.BibliotecaBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoAdquiridoBO;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoBO;
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
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;


public class Principal {
    public static void main(String[] args)throws Exception{
//        test_jugadores();//se usan metodos de jugadores
//        test_desarrolladores();//se usan metodos de jugadores
//        test_administradores();//se usan metodos de administradores
        
//        test_bibliotecas();
//        test_juegos();
//        test_juegoAdquiridos();
    }
    
    private static void test_jugadores()throws ParseException{
        JugadorDAO daoJugador=new JugadorMySQL();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        //1.JUGADOR
//        inserto un nuevo jugador
        Jugador j1=new Jugador("Miguel","miguel@hotmail.com","789",sdf.parse("2025-04-20"),"969627718","ImagenX","miguel123");
        Jugador j2=new Jugador("cubas ","cubas@hotmail.com","753",sdf.parse("2025-04-20"),"852322","ImagenX","gohanGol");
        Jugador j3=new Jugador("Alesandro ","alesandro@hotmail.com","741",sdf.parse("2025-04-20"),"227423","ImagenX","ales50");
        Jugador j4=new Jugador("Luis ","luis@hotmail.com","785",sdf.parse("2025-04-20"),"785222","ImagenX","luis20");
        daoJugador.insertar(j1);daoJugador.insertar(j2);daoJugador.insertar(j3);daoJugador.insertar(j4);
//        listar jugadores
        ArrayList<Jugador>jugadores = daoJugador.listarTodos();
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
        daoJugador.modificar(jugadorAModificar);
        //volvemos a listar para ver la modificacion
        jugadores=daoJugador.listarTodos();
        for(Jugador j : jugadores){
            System.out.println(j);
        }
        //eliminar jugador
        daoJugador.eliminar(3);
        // obtener por ID
        System.out.println(daoJugador.obtenerPorId(3));
    }
    
    private static void test_desarrolladores()throws ParseException{
        DesarrolladorDAO daoDesarrollador=new DesarrolladorMySQL();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//        2.DESARROLLADOR
//        insertamos desarrolladores
        Desarrollador d1=new Desarrollador("Gustavo","gusgus@hotmail.com","555",sdf.parse("2025-04-24"),"961908444","ImagenX","191-500-200",5500.50);
        Desarrollador d2=new Desarrollador("Polar","polar@hotmail.com","444",sdf.parse("2025-04-24"),"991474587","ImagenX","191-555-002",4500.50);
        Desarrollador d3=new Desarrollador("Rex","rex@hotmail.com","666",sdf.parse("2025-04-24"),"992784711","ImagenX","152-200-001",3500.50);
        Desarrollador d4=new Desarrollador("Leocho","leo@hotmail.com","777",sdf.parse("2025-04-24"),"961694521","ImagenX","122-400-911",2500.50);
        daoDesarrollador.insertar(d1);daoDesarrollador.insertar(d2);daoDesarrollador.insertar(d3);daoDesarrollador.insertar(d4);
        //listar desarrolladores
        ArrayList<Desarrollador>desarrolladores = daoDesarrollador.listarTodos();
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
        daoDesarrollador.modificar(desarroMod);
        //eliminar desarrollador
        daoDesarrollador.eliminar(100);
        // obtener por ID
        daoDesarrollador.obtenerPorId(100);
    }
    
    private static void test_administradores()throws ParseException{
        AdministradorDAO daoAdministrador=new AdministradorMySQL();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        //3.ADMINISTRADOR
        //insertamos administrador
        Administrador a1=new Administrador("Tito","tit@hotmail.com","12563",sdf.parse("2025-04-24"),"91324546","ImagenX","SIN_PERMISO");                                       
        Administrador a2=new Administrador("Juan","jjt@hotmail.com","852",sdf.parse("2025-04-24"),"78522","ImagenX","CON_PERMISO");  
        Administrador a3=new Administrador("Luis","tllt@hotmail.com","75321",sdf.parse("2025-04-24"),"9632587","ImagenX","CON_PERMISO");  
        Administrador a4=new Administrador("Johar","johar@hotmail.com","9852",sdf.parse("2025-04-24"),"91324546","ImagenX","CON_PERMISO");  
        daoAdministrador.insertar(a1);daoAdministrador.insertar(a2);daoAdministrador.insertar(a3);daoAdministrador.insertar(a4);
        //listar Administrador
        ArrayList<Administrador>administradores = daoAdministrador.listarTodos();
        for(Administrador a : administradores){
            System.out.println(a);
        }
        
        //Eliminar Administrador
        daoAdministrador.eliminar(92);
        
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
        
        daoAdministrador.modificar(admiModif);
        
        
        //volvemos a listar para ver la modificacion
        administradores=daoAdministrador.listarTodos();
        for(Administrador a : administradores){
            System.out.println(a);
        }
        //eliminar desarrollador
        daoAdministrador.eliminar(11);
        // obtener por ID
        daoAdministrador.obtenerPorId(12);
        
        

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
    
}



