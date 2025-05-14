package pe.edu.pucp.lagstore.main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.BibliotecaMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.dao.AdministradorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.AdministradorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.DesarrolladorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.JugadorMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;


public class Principal {
    public static void main(String[] args)throws Exception{
        
        //1.JUGADOR
        JugadorDAO daoJugador=new JugadorMySQL();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        //inserto una biblioteca
        /*Biblioteca b1=new Biblioteca(20.1,3);
        BibliotecaDAO daoBiblioteca= new BibliotecaMySQL();
        daoBiblioteca.insertar(b1);
        Biblioteca b2=new Biblioteca(15.3,2);
        daoBiblioteca.insertar(b2);
        Biblioteca b3=new Biblioteca(80.0,6);
        daoBiblioteca.insertar(b3);
        Biblioteca b4=new Biblioteca(50.3,4);
        daoBiblioteca.insertar(b4);*/
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
        ArrayList<Jugador>jugadores = daoJugador.listarTodas();
        for(Jugador j : jugadores){
            System.out.println(j);
        }
        //modificar jugador
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
        /*jugadores=daoJugador.listarTodas();
        for(Jugador j : jugadores){
            System.out.println(j);
        }*/
        //eliminar jugador
        daoJugador.eliminar(3);
        // obtener por ID
        System.out.println(daoJugador.obtenerPorId(3));
    
        /*
        //2.DESARROLLADOR
        //Creo las bibliotecas de cada desarrollador
        Biblioteca b5=new Biblioteca(10.2,3);
        daoBiblioteca.insertar(b5);
        Biblioteca b6=new Biblioteca(11.5,4);
        daoBiblioteca.insertar(b6);
        Biblioteca b7=new Biblioteca(12.5,5);
        daoBiblioteca.insertar(b7);
        Biblioteca b8=new Biblioteca(13.0,2);
        daoBiblioteca.insertar(b8);
       
        //inserto desarrolladores
        //insertamos desarrollador
        Desarrollador d1=new Desarrollador("Gustavo","555","gusgus@hotmail.com",sdf.parse("2025-04-24"),9619084,"ImagenX",1,
                                            b5,3,191500,5500.50);
        Desarrollador d2=new Desarrollador("Polar","444","polar@hotmail.com",sdf.parse("2025-04-24"),9914745,"ImagenX",1,
                                            b6,3,191555,4500.50);
        Desarrollador d3=new Desarrollador("Rex","666","rex@hotmail.com",sdf.parse("2025-04-24"),9927847,"ImagenX",1,
                                            b7,3,152200,3500.50);
        Desarrollador d4=new Desarrollador("Leocho","777","leo@hotmail.com",sdf.parse("2025-04-24"),9616945,"ImagenX",1,
                                            b8,3,122400,2500.50);
        DesarrolladorDAO daoDesarrollador=new DesarrolladorMySQL();
        daoDesarrollador.insertar(d1);
        daoDesarrollador.insertar(d2);
        daoDesarrollador.insertar(d3);
        daoDesarrollador.insertar(d4);
        //listar desarrolladores
        ArrayList<Desarrollador>desarrolladores = daoDesarrollador.listarTodas();
        for(Desarrollador d : desarrolladores){
            System.out.println(d);
        }
        //modificar desarrollador
        desarrolladores.get(0).setIngresoTotal(2500.25);
        daoDesarrollador.modificar(desarrolladores.get(0));
        //volvemos a listar para ver la modificacion
        desarrolladores=daoDesarrollador.listarTodas();
        for(Desarrollador d : desarrolladores){
            System.out.println(d);
        }
        //eliminar desarrollador
        daoDesarrollador.eliminar(5);
        // obtener por ID
        daoDesarrollador.obtenerPorId(8);
        
        
        //3.ADMINISTRADOR
        //Creo las bibliotecas de cada ADMINISTRADOR
        Biblioteca b9=new Biblioteca(16.4,3);
        daoBiblioteca.insertar(b9);
        Biblioteca b10=new Biblioteca(18.6,3);
        daoBiblioteca.insertar(b10);
        Biblioteca b11=new Biblioteca(15.0,2);
        daoBiblioteca.insertar(b11);
        Biblioteca b12=new Biblioteca(48.6,4);
        daoBiblioteca.insertar(b12);
        //insertamos administrador
        Administrador a1=new Administrador("Tito","111","tit@hotmail.com",sdf.parse("2025-04-24"),91324546,"ImagenX",1,
                                            b9,2,"General");
        Administrador a2=new Administrador("Loki","666","luck@hotmail.com",sdf.parse("2025-04-24"),9917321,"ImagenX",1,
                                            b10,2,"De contenido");
        Administrador a3=new Administrador("Saitama","666","onepunch@hotmail.com",sdf.parse("2025-04-24"),9884512,"ImagenX",1,
                                            b11,2,"De base de datos");
        Administrador a4=new Administrador("Garfield","000","gar@hotmail.com",sdf.parse("2025-04-24"),9874526,"ImagenX",1,
                                            b12,2,"De sistemas");
        AdministradorDAO daoAdministrador=new AdministradorMySQL();
        daoAdministrador.insertar(a1);
        daoAdministrador.insertar(a2);
        daoAdministrador.insertar(a3);
        daoAdministrador.insertar(a4);
        //listar desarrolladores
        ArrayList<Administrador>administradores = daoAdministrador.listarTodas();
        for(Administrador a : administradores){
            System.out.println(a);
        }
        //modificar desarrollador
        administradores.get(0).setRolAdministrativo("rol Nuevo");
        daoAdministrador.modificar(administradores.get(0));
        //volvemos a listar para ver la modificacion
        administradores=daoAdministrador.listarTodas();
        for(Administrador a : administradores){
            System.out.println(a);
        }
        //eliminar desarrollador
        daoAdministrador.eliminar(11);
        // obtener por ID
        daoAdministrador.obtenerPorId(12);
        
        
         //biblioteca
        Biblioteca b13=new Biblioteca(20.50,4);
        Biblioteca b14=new Biblioteca(55.50,7);
        BibliotecaDAO daoBiblioteca2= new BibliotecaMySQL();
        
        //inserto un nueva biblioteca
        daoBiblioteca.insertar(b13);
        daoBiblioteca.insertar(b14);
        //listar jugadores
        ArrayList<Biblioteca>biblioteca = daoBiblioteca.listarTodas();
        for(Biblioteca j : biblioteca){
            System.out.println(j);
        }
        //modificar jugador
        biblioteca.get(0).setCantidadDeJuegos(3);
        daoBiblioteca.modificar(biblioteca.get(0));
        // obtener por ID
        daoBiblioteca.obtenerPorId(2);
        */
    }
    
}
