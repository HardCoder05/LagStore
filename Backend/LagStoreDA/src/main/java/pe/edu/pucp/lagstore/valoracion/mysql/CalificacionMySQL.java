package pe.edu.pucp.lagstore.valoracion.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;
//import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.valoracion.dao.CalificacionDAO;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;


public class CalificacionMySQL implements CalificacionDAO{
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    @Override
    public int insertar(Calificacion calificacion) {
       Map<Integer,Object> parametrosSalida = new HashMap<>();
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);//deberia ser el id de Resena
        parametrosEntrada.put(2, calificacion.getAutor().getIdJugador());//no se si falta casteo
        parametrosEntrada.put(3, calificacion.getJuego().getIdJuego());
        parametrosEntrada.put(4,calificacion.getFechaPuntuacion());
        parametrosEntrada.put(5,calificacion.getPuntuacion());
        parametrosEntrada.put(6,calificacion.getActivo());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CALIFICACION", parametrosEntrada, parametrosSalida);
        calificacion.setIdCalificacion((int) parametrosSalida.get(1));//SE RESCATA EL ID
        System.out.println("Se ha realizado el registro de la calificacion");
        return calificacion.getIdCalificacion();
    }
    
    @Override
    public int modificar(Calificacion calificacion) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, calificacion.getIdCalificacion());
        parametrosEntrada.put(2, calificacion.getAutor().getIdJugador());
        parametrosEntrada.put(3, calificacion.getJuego().getIdJuego());
        parametrosEntrada.put(4, calificacion.getFechaPuntuacion());
        parametrosEntrada.put(5, calificacion.getPuntuacion());
        parametrosEntrada.put(6, calificacion.getActivo());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CALIFICACION", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion de la calificacion");
        return resultado;
    }

    @Override
    public int eliminar(int idCalificacion) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCalificacion);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_CALIFICACION", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion de la calificacion");
        return resultado;
    }

    @Override
    public ArrayList<Calificacion> listarTodas() {
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
    rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CALIFICACIONES_TODAS", null);
    System.out.println("Lectura de calificaciones...");

    try {
        while (rs.next()) {
            Calificacion calificacion = new Calificacion();
            calificacion.setIdCalificacion(rs.getInt("idCalificacion"));
            calificacion.setFechaPuntuacion(rs.getDate("fechaPuntuacion"));
            calificacion.setPuntuacion(rs.getInt("puntaje"));
            calificacion.setActivo(rs.getInt("activo"));

            Jugador autor = new Jugador();
            autor.setIdJugador(rs.getInt("fidJugador"));
            calificacion.setAutor(autor);

            Juego juego = new Juego();
            juego.setIdJuego(rs.getInt("fidJuego"));
            calificacion.setJuego(juego);

            calificaciones.add(calificacion);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR: " + ex.getMessage());
    } finally {
        DBManager.getInstance().cerrarConexion();
    }

    return calificaciones;
    }

    @Override
    public Calificacion obtenerPorId(int idCalificacion) {
        Calificacion calificacion = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCalificacion);

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_CALIFICACION_X_ID", parametrosEntrada);
        System.out.println("Lectura de calificacion...");

        try {
            if (rs.next()) {
                calificacion = new Calificacion();
                calificacion.setIdCalificacion(rs.getInt("idCalificacion"));
                calificacion.setFechaPuntuacion(rs.getDate("fechaPuntuacion"));
                calificacion.setPuntuacion(rs.getInt("puntaje"));
                calificacion.setActivo(rs.getInt("activo"));

                Jugador autor = new Jugador();
                autor.setIdJugador(rs.getInt("fidJugador"));
                calificacion.setAutor(autor);

                Juego juego = new Juego();
                juego.setIdJuego(rs.getInt("fidJuego"));
                calificacion.setJuego(juego);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return calificacion;
    }
    
}
