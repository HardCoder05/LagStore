
package pe.edu.pucp.lagstore.gestionjuegos.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionjuegos.dao.GeneroDAO;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;

public class GeneroMySQL implements GeneroDAO{
    private ResultSet rs;

    @Override
    public int insertar(Genero genero) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, genero.toString());

        return DBManager.getInstance().ejecutarProcedimiento("INSERTAR_GENERO", parametrosEntrada, null);
    }

    @Override
    public int modificar(Genero genero) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, genero.toString());

        return DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_GENERO", parametrosEntrada, null);
    }

    @Override
    public int eliminar(int idGenero) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idGenero);

        return DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_GENERO", parametrosEntrada, null);
    }

    @Override
    public ArrayList<Genero> listarTodos() {
        ArrayList<Genero> generos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_GENEROS_TODOS", null);

        try {
            while (rs.next()) {
                Genero genero;
                genero = Genero.valueOf(rs.getString("nombreGenero"));
                generos.add(genero);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return generos;
    }
}
