package pe.edu.pucp.lagstore.valoracion.mysql;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;
import pe.edu.pucp.lagstore.valoracion.dao.ResenaDAO;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;
import pe.edu.pucp.lagstore.valoracion.model.Resena;

public class ResenaMySQL implements ResenaDAO{
    private ResultSet rs;
    @Override
    public int insertar(Resena resena) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);//deberia ser el id de Resena
        parametrosEntrada.put(2, resena.getAutor().getIdJugador());//no se si falta casteo
        parametrosEntrada.put(3, resena.getJuego().getIdJuego());
        parametrosEntrada.put(4,resena.getComentario());
        parametrosEntrada.put(5,resena.getFechaPublicacion());
        parametrosEntrada.put(6,resena.getCalificacion().getIdCalificacion());
        parametrosEntrada.put(7,resena.getActivo());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_RESENA", parametrosEntrada, parametrosSalida);
        resena.setIdResena((int) parametrosSalida.get(1));//SE RESCATA EL ID
        System.out.println("Se ha realizado el registro de la resena");
        return resena.getIdResena();
    }

    @Override
    public int modificar(Resena resena) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, resena.getIdResena());
        parametrosEntrada.put(2, resena.getAutor().getIdJugador());
        parametrosEntrada.put(3, resena.getJuego().getIdJuego());
        parametrosEntrada.put(4,resena.getComentario());
        parametrosEntrada.put(5,resena.getFechaPublicacion());
        parametrosEntrada.put(6,resena.getCalificacion().getIdCalificacion());
        parametrosEntrada.put(7,resena.getActivo());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_RESENA", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion de la resena");
        return resultado;
    }

    @Override
    public int eliminar(int idResena) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idResena);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_RESENA", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion de la resena");
        return resultado;
    }

    @Override
    public ArrayList<Resena> listarTodas() {
         ArrayList<Resena> resenas = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_RESENAS_TODAS", null);
        System.out.println("Lectura de resenas...");
        try{
            while(rs.next()){
                Resena resena = new Resena();
                resena.setIdResena(rs.getInt("idResena"));
                resena.setComentario(rs.getString("comentario"));
                resena.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                //area.setActivo(true);
                 // Calificación
                Calificacion calificacion = new Calificacion();
                calificacion.setIdCalificacion(rs.getInt("idCalificacion")); // o el nombre real de la columna FK
                resena.setCalificacion(calificacion);
                resena.setActivo(rs.getInt("activo"));
                // Jugador (autor)
                Jugador autor = new Jugador();
                autor.setIdJugador(rs.getInt("fidJugador"));
                resena.setAutor(autor);
                // Juego
                Juego juego = new Juego();
                juego.setIdJuego(rs.getInt("fidJuego"));
                resena.setJuego(juego);
                resenas.add(resena);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return resenas;
    }

    @Override
    public Resena obtenerPorId(int idResena) {
        Resena resena = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idResena);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_RESENA_X_ID", parametrosEntrada);
        System.out.println("Lectura de reseña...");
        try{
            if(rs.next()){
                resena = new Resena();
                resena.setIdResena(rs.getInt("idResena"));
                resena.setComentario(rs.getString("comentario"));
                resena.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                //area.setActivo(true);
                 // Calificación
                Calificacion calificacion = new Calificacion();
                calificacion.setIdCalificacion(rs.getInt("idCalificacion")); // o el nombre real de la columna FK
                resena.setCalificacion(calificacion);
                resena.setActivo(rs.getInt("activo"));
                // Jugador (autor)
                Jugador autor = new Jugador();
                autor.setIdJugador(rs.getInt("fidJugador"));
                resena.setAutor(autor);
                // Juego
                Juego juego = new Juego();
                juego.setIdJuego(rs.getInt("fidJuego"));
                resena.setJuego(juego);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return resena;
    } 
}
