package pe.edu.pucp.lagstore.gestionjuegos.mysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionjuegos.dao.JuegoAdquiridoDAO;
import pe.edu.pucp.lagstore.gestjuegos.model.JuegoAdquirido;

public class JuegoAdquiridoMySQL implements JuegoAdquiridoDAO{
    private ResultSet rs;

    @Override
    public int insertar(JuegoAdquirido juegoAdquirido) {
        Map<Integer, Object> inParams = new HashMap<>();

        inParams.put(1, juegoAdquirido.getBiblioteca().getIdBiblioteca());
        inParams.put(2, juegoAdquirido.getJuego().getIdJuego());
        inParams.put(3, juegoAdquirido.getFechaAdquisicion());
        inParams.put(4, juegoAdquirido.getUltimaSesion());
        inParams.put(5, juegoAdquirido.getTiempoJuego());
        inParams.put(6, juegoAdquirido.isActualizado());

        return DBManager.getInstance().ejecutarProcedimiento("INSERTAR_JUEGO_ADQUIRIDO", inParams, null);    
    }

    @Override
    public int modificar(JuegoAdquirido juegoAdquirido) {
        Map<Integer, Object> inParams = new HashMap<>();

        inParams.put(1, juegoAdquirido.getBiblioteca().getIdBiblioteca());
        inParams.put(2, juegoAdquirido.getJuego().getIdJuego());
        inParams.put(3, juegoAdquirido.getFechaAdquisicion());
        inParams.put(4, juegoAdquirido.getUltimaSesion());
        inParams.put(5, juegoAdquirido.getTiempoJuego());
        inParams.put(6, juegoAdquirido.isActualizado());

        return DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_JUEGO_ADQUIRIDO", inParams, null);
    }

    @Override
    public int eliminar(int idJuegoAdquirido) {
        Map<Integer, Object> inParams = new HashMap<>();
        inParams.put(1, idJuegoAdquirido);
        return DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_JUEGO_ADQUIRIDO", inParams, null);
    }

    @Override
    public ArrayList<JuegoAdquirido> listarTodos() {
        ArrayList<JuegoAdquirido> juegos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_JUEGOS_ADQUIRIDOS_TODOS", null);

        try {
            while (rs.next()) {
                JuegoAdquirido ja = new JuegoAdquirido();
                
                ja.getBiblioteca().setIdBiblioteca(rs.getInt("fidBiblioteca"));
                ja.getJuego().setIdJuego(rs.getInt("fidJuego"));
                ja.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                ja.setUltimaSesion(rs.getDate("ultimaSesion"));
                ja.setTiempoJuego(rs.getDouble("tiempoJuego"));
                ja.setActualizado(rs.getBoolean("actualizado"));

                juegos.add(ja);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al listar juegos adquiridos: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return juegos;
    }

    @Override
    public JuegoAdquirido obtenerPorId(int idJuego){
        JuegoAdquirido ja = null;
        
        Map<Integer, Object> inParams = new HashMap<>();
        inParams.put(1, idJuego);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_JUEGO_ADQUIRIDO_POR_ID", inParams);

        try {
            if (rs.next()) {
                ja = new JuegoAdquirido();
                
                ja.getBiblioteca().setIdBiblioteca(rs.getInt("fidBiblioteca"));
                ja.getJuego().setIdJuego(rs.getInt("fidJuego"));
                ja.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                ja.setUltimaSesion(rs.getDate("ultimaSesion"));
                ja.setTiempoJuego(rs.getDouble("tiempoJuego"));
                ja.setActualizado(rs.getBoolean("actualizado"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al obtener juego adquirido por ID: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return ja;
    }
}
