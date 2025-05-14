package pe.edu.pucp.lagstore.gestionusuarios.mysql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

public class JugadorMySQL implements JugadorDAO{
    
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(Jugador jugador) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, jugador.getNombre());
        parametrosEntrada.put(3, jugador.getEmail());
        parametrosEntrada.put(4, jugador.getContrasena());
        parametrosEntrada.put(5, jugador.getFechaRegistro());
        parametrosEntrada.put(6, jugador.getTelefono());
        parametrosEntrada.put(7, jugador.getFotoDePerfil());
        parametrosEntrada.put(8, jugador.getNickname());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_JUGADOR", parametrosEntrada, parametrosSalida);
        jugador.setIdJugador((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del jugador");
        
        return jugador.getIdUsuario();
    }

    @Override
    public int modificar(Jugador jugador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,jugador.getIdJugador());
        parametrosEntrada.put(2, jugador.getNickname());
        parametrosEntrada.put(3, jugador.getNombre());
        parametrosEntrada.put(4, jugador.getEmail());
        parametrosEntrada.put(5, jugador.getContrasena());
        parametrosEntrada.put(6, new Date(jugador.getFechaRegistro().getTime()));
        parametrosEntrada.put(7, jugador.getTelefono());
        parametrosEntrada.put(8, jugador.getFotoDePerfil());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_JUGADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del jugador");
        return resultado;
    }

    @Override
    public int eliminar(int idJugador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idJugador);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_JUGADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del jugador");
        return resultado;
    }

    @Override
    public ArrayList<Jugador> listarTodos() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_JUGADOR", null);
        System.out.println("Lectura de jugadores...");
        try{
            while(rs.next()){
                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt(1));
                j.setNombre(rs.getString(2));
                j.setEmail(rs.getString(3));
                j.setContrasena(rs.getString(4));
                j.setFechaRegistro(rs.getDate(5));
                j.setTelefono(rs.getString(6));
                j.setFotoDePerfil(rs.getString(7));
                j.setNickname(rs.getString(8));
                jugadores.add(j);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return jugadores; 
    }

    @Override
    public Jugador obtenerPorId(int id) {
            ArrayList<Jugador> jugadores = new ArrayList<>();

            // Crear un mapa para pasar los parámetros de entrada al procedimiento almacenado
            Map<Integer, Object> parametrosEntrada = new HashMap<>();
            parametrosEntrada.put(1, id);  // Asegúrate de pasar el ID al procedimiento almacenado

            // Ejecutar el procedimiento almacenado y obtener el ResultSet
            rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_X_ID_JUGADOR", parametrosEntrada);

            System.out.println("Lectura de jugadores...");

            try {
                    while (rs.next()) {
                            Jugador j = new Jugador();
                            j.setIdJugador(rs.getInt(1));
                            j.setNombre(rs.getString(2));
                            j.setEmail(rs.getString(3));
                            j.setContrasena(rs.getString(4));
                            j.setFechaRegistro(rs.getDate(5));
                            j.setTelefono(rs.getString(6));
                            j.setFotoDePerfil(rs.getString(7));
                            j.setNickname(rs.getString(8));
                            jugadores.add(j);
                    }
            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            } finally {
                    DBManager.getInstance().cerrarConexion();
            }

            // Asegurarse de que la lista no esté vacía antes de intentar acceder al primer elemento
            if (!jugadores.isEmpty()) {
                    return jugadores.get(0);  // Obtener el primer jugador de la lista
            } else {
                    return null;  // Si no se encontró el jugador, retornar null
            }
    }

    

    
    
}
