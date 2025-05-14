package pe.edu.pucp.lagstore.gestionjuegos.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;

public class BibliotecaMySQL implements BibliotecaDAO {
    private ResultSet rs;

    @Override
    public int insertar(Biblioteca biblioteca) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        Map<Integer, Object> parametrosSalida = new HashMap<>();

        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, biblioteca.getIngresoTotal());
        parametrosEntrada.put(3, biblioteca.getCantidadDeJuegos());
        parametrosEntrada.put(4, biblioteca.getUsuario().getIdUsuario());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_BIBLIOTECA", parametrosEntrada, parametrosSalida);
        biblioteca.setIdBiblioteca((int) parametrosSalida.get(1));
        System.out.println("Se ha registrado la biblioteca correctamente.");
        return biblioteca.getIdBiblioteca();
    }

    @Override
    public int modificar(Biblioteca biblioteca) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, biblioteca.getIdBiblioteca());
        parametrosEntrada.put(2, biblioteca.getIngresoTotal());
        parametrosEntrada.put(3, biblioteca.getCantidadDeJuegos());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_BIBLIOTECA", parametrosEntrada, null);
        System.out.println("Se ha modificado la biblioteca correctamente.");
        return resultado;
    }

    @Override
    public int eliminar(int idBiblioteca) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idBiblioteca);

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_BIBLIOTECA", parametrosEntrada, null);
        System.out.println("Se ha eliminado la biblioteca correctamente.");
        return resultado;
    }

    @Override
    public ArrayList<Biblioteca> listarTodas() {
        ArrayList<Biblioteca> bibliotecas = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_BIBLIOTECAS_TODAS", null);
        System.out.println("Lectura de bibliotecas...");

        try {
            while (rs.next()) {
                Biblioteca biblioteca = new Biblioteca();
                biblioteca.setIdBiblioteca(rs.getInt("idBiblioteca"));
                biblioteca.setIngresoTotal(rs.getDouble("ingresoTotal"));
                biblioteca.setCantidadDeJuegos(rs.getInt("cantidadDeJuegos"));
                bibliotecas.add(biblioteca);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return bibliotecas;
    }

    @Override
    public Biblioteca obtenerPorId(int id) {
        Biblioteca biblioteca = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_BIBLIOTECA_X_ID", parametrosEntrada);
        System.out.println("Lectura de biblioteca por ID...");

        try {
            if (rs.next()) {
                biblioteca = new Biblioteca();
                biblioteca.setIdBiblioteca(rs.getInt("idBiblioteca"));
                biblioteca.setIngresoTotal(rs.getDouble("ingresoTotal"));
                biblioteca.setCantidadDeJuegos(rs.getInt("cantidadDeJuegos"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return biblioteca;
    }
}

