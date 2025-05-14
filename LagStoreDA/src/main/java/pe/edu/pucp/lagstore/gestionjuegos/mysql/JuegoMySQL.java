package pe.edu.pucp.lagstore.gestionjuegos.mysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionjuegos.dao.JuegoDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.DesarrolladorMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;


public class JuegoMySQL implements JuegoDAO{
    private ResultSet rs;

    @Override
    public int insertar(Juego juego) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        Map<Integer, Object> parametrosSalida = new HashMap<>();

        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, juego.getTitulo());
        parametrosEntrada.put(3, juego.getDescripcion());
        parametrosEntrada.put(4, juego.getPrecio());
        parametrosEntrada.put(5, juego.getVersion());
        parametrosEntrada.put(6, juego.getImagen());
        parametrosEntrada.put(7, juego.getFechaLanzamiento());
        parametrosEntrada.put(8, juego.getRequisitosMinimos());
        parametrosEntrada.put(9, juego.getRequisitosRecomendados());
        parametrosEntrada.put(10, juego.getEspacioDisco());
        parametrosEntrada.put(11, juego.getFechaUltimaActualizacion());
        parametrosEntrada.put(12, juego.getGenero().toString());
        parametrosEntrada.put(13, juego.getModeloNegocio().toString());
        parametrosEntrada.put(14, juego.getDesarrollador().getIdDesarrollador());
        parametrosEntrada.put(15, 1);

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_JUEGO", parametrosEntrada, parametrosSalida);
        juego.setIdJuego((int) parametrosSalida.get(1));
        System.out.println("Se ha registrado el juego correctamente.");
        return juego.getIdJuego();
    }

    @Override
    public int modificar(Juego juego) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        parametrosEntrada.put(1, juego.getIdJuego());
        parametrosEntrada.put(2, juego.getTitulo());
        parametrosEntrada.put(3, juego.getDescripcion());
        parametrosEntrada.put(4, juego.getPrecio());
        parametrosEntrada.put(5, juego.getVersion());
        parametrosEntrada.put(6, juego.getImagen());
        parametrosEntrada.put(7, juego.getFechaLanzamiento());
        parametrosEntrada.put(8, juego.getRequisitosMinimos());
        parametrosEntrada.put(9, juego.getRequisitosRecomendados());
        parametrosEntrada.put(10, juego.getEspacioDisco());
        parametrosEntrada.put(11, juego.getFechaUltimaActualizacion());
        parametrosEntrada.put(12, juego.getGenero().toString());
        parametrosEntrada.put(13, juego.getModeloNegocio().toString());
        parametrosEntrada.put(14, juego.getDesarrollador().getIdDesarrollador());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_JUEGO", parametrosEntrada, null);
        System.out.println("Se ha modificado el juego correctamente.");
        return resultado;
    }

    @Override
    public int eliminar(int idJuego) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idJuego);

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_JUEGO", parametrosEntrada, null);
        System.out.println("Se ha eliminado el juego correctamente.");
        return resultado;
    }

    @Override
    public ArrayList<Juego> listarTodos() {
        ArrayList<Juego> juegos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_JUEGOS_TODOS", null);
        System.out.println("Lectura de juegos...");

        try {
            while (rs.next()) {
                Juego juego = new Juego();
                juego.setIdJuego(rs.getInt("idJuego"));
                juego.setTitulo(rs.getString("titulo"));
                juego.setDescripcion(rs.getString("descripcion"));
                juego.setPrecio(rs.getDouble("precio"));
                juego.setVersion(rs.getDouble("version"));
                juego.setImagen(rs.getString("imagen"));
                juego.setFechaLanzamiento(rs.getDate("fechaLanzamiento"));
                juego.setRequisitosMinimos(rs.getString("requisitosMinimos"));
                juego.setRequisitosRecomendados(rs.getString("requisitosRecomendados"));
                juego.setEspacioDisco(rs.getDouble("espacioDisco"));
                juego.setFechaUltimaActualizacion(rs.getDate("fechaUltimaActualizacion"));
                juego.setGenero(Genero.valueOf(rs.getString("genero")));
                juego.setModeloNegocio(ModeloNegocio.valueOf(rs.getString("modeloNegocio")));
                juego.setDesarrollador(new DesarrolladorMySQL().obtenerPorId(rs.getInt("idDesarrollador")));
                juego.setActivo(1);
                juegos.add(juego);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return juegos;
    }

    @Override
    public Juego obtenerPorId(int idJuego) {
        Juego juego = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idJuego);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_JUEGO_X_ID", parametrosEntrada);
        System.out.println("Lectura del juego...");

        try {
            if (rs.next()) {
                juego = new Juego();
                juego.setIdJuego(rs.getInt("idJuego"));
                juego.setTitulo(rs.getString("titulo"));
                juego.setDescripcion(rs.getString("descripcion"));
                juego.setPrecio(rs.getDouble("precio"));
                juego.setVersion(rs.getDouble("version"));
                juego.setImagen(rs.getString("imagen"));
                juego.setFechaLanzamiento(rs.getDate("fechaLanzamiento"));
                juego.setRequisitosMinimos(rs.getString("requisitosMinimos"));
                juego.setRequisitosRecomendados(rs.getString("requisitosRecomendados"));
                juego.setEspacioDisco(rs.getDouble("espacioDisco"));
                juego.setFechaUltimaActualizacion(rs.getDate("fechaUltimaActualizacion"));
                juego.setGenero(Genero.valueOf(rs.getString("genero")));
                juego.setModeloNegocio(ModeloNegocio.valueOf(rs.getString("modeloNegocio")));
                juego.setDesarrollador(new DesarrolladorMySQL().obtenerPorId(rs.getInt("idDesarrollador")));
                juego.setActivo(rs.getInt("activo"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return juego;
    }
}
