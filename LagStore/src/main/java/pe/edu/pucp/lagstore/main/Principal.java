package pe.edu.pucp.lagstore.main;
import java.text.SimpleDateFormat;
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
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;


public class Principal {
    public static void main(String[] args)throws Exception{
        
        //1.JUGADOR
        //inserto una biblioteca
        Biblioteca b1=new Biblioteca(20.1,3);
        BibliotecaDAO daoBiblioteca= new BibliotecaMySQL();
        daoBiblioteca.insertar(b1);
        Biblioteca b2=new Biblioteca(15.3,2);
        daoBiblioteca.insertar(b2);
        Biblioteca b3=new Biblioteca(80.0,6);
        daoBiblioteca.insertar(b3);
        Biblioteca b4=new Biblioteca(50.3,4);
        daoBiblioteca.insertar(b4);
        //inserto un nuevo jugador
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Jugador j1=new Jugador("Miguel","789","miguel@hotmail.com",sdf.parse("2025-04-20"),9616945,"ImagenX",1,
                               b1,1,"miguel123");
        Jugador j2=new Jugador("cubas ","753","cubas@hotmail.com",sdf.parse("2025-04-20"),852322,"ImagenX",1,
                               b2,1,"gohanGol");
        Jugador j3=new Jugador("Alesandro ","741","alesandro@hotmail.com",sdf.parse("2025-04-20"),227423,"ImagenX",1,
                               b3,1,"ales50");
        Jugador j4=new Jugador("Luis ","785","luis@hotmail.com",sdf.parse("2025-04-20"),785222,"ImagenX",1,
                               b4,1,"luis20");
        JugadorDAO daoJugador=new JugadorMySQL();
        daoJugador.insertar(j1);
        daoJugador.insertar(j2);
        daoJugador.insertar(j3);
        daoJugador.insertar(j4);
        //listar jugadores
        ArrayList<Jugador>jugadores = daoJugador.listarTodas();
        for(Jugador j : jugadores){
            System.out.println(j);
        }
        //modificar jugador
        jugadores.get(0).setNickname("miguelNuevo");
        daoJugador.modificar(jugadores.get(0));
        //volvemos a listar para ver la modificacion
        jugadores=daoJugador.listarTodas();
        for(Jugador j : jugadores){
            System.out.println(j);
        }
        //eliminar jugador
        daoJugador.eliminar(1);
        // obtener por ID
        daoJugador.obtenerPorId(3);
    
        
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
    }
    
}
