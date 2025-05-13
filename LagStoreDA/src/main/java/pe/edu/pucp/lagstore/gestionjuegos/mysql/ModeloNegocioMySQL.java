package pe.edu.pucp.lagstore.gestionjuegos.mysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionjuegos.dao.ModeloNegocioDAO;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;


public class ModeloNegocioMySQL implements ModeloNegocioDAO{
    private ResultSet rs;

    @Override
    public int insertar(ModeloNegocio modeloNegocio) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, modeloNegocio.toString());

        return DBManager.getInstance().ejecutarProcedimiento("INSERTAR_MODELO_NEGOCIO", parametrosEntrada, null);
    }

    @Override
    public int modificar(ModeloNegocio modeloNegocio) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, modeloNegocio.toString());

        return DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_MODELO_NEGOCIO", parametrosEntrada, null);
    }

    @Override
    public int eliminar(int idModeloNegocio) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idModeloNegocio);

        return DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_MODELO_NEGOCIO", parametrosEntrada, null);
    }

    @Override
    public ArrayList<ModeloNegocio> listarTodos() {
        ArrayList<ModeloNegocio> modelos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_MODELOS_NEGOCIO_TODOS", null);

        try {
            while (rs.next()) {
                ModeloNegocio modelo;
                modelo = ModeloNegocio.valueOf(rs.getString("modelo"));
                modelos.add(modelo);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return modelos;
    }
}
