package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.compra.dao.RecargaDAO;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import pe.edu.pucp.lagstore.compra.model.Recarga;
import pe.edu.pucp.lagstore.config.DBManager;

public class RecargaMySQL implements RecargaDAO {

    private ResultSet rs;

    @Override
    public int insertar(Recarga recarga) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, java.sql.Types.INTEGER); 
        parametrosEntrada.put(2, new java.sql.Date(recarga.getFechaRecarga().getTime()));
        parametrosEntrada.put(3, recarga.getMonto());
        parametrosEntrada.put(4, recarga.getMetodoPago().name());  
        parametrosEntrada.put(5, recarga.getCartera().getIdCartera());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_RECARGA", parametrosEntrada, parametrosSalida);
        recarga.setIdRecarga((int) parametrosSalida.get(1));

        System.out.println("Se registró Recarga");
        return recarga.getIdRecarga();
    }

    @Override
    public int modificar(Recarga recarga) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, recarga.getIdRecarga());
        parametrosEntrada.put(2, new java.sql.Date(recarga.getFechaRecarga().getTime()));
        parametrosEntrada.put(3, recarga.getMonto());
        parametrosEntrada.put(4, recarga.getMetodoPago().name());
        parametrosEntrada.put(5, recarga.getCartera().getIdCartera());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_RECARGA", parametrosEntrada, null);
        System.out.println("Se modificó Recarga");
        return resultado;
    }

    @Override
    public int eliminar(int idRecarga) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idRecarga);

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_RECARGA", parametrosEntrada, null);
        System.out.println("Se eliminó Recarga");
        return resultado;
    }

    @Override
    public ArrayList<Recarga> listarTodas() {
        ArrayList<Recarga> lista = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_RECARGAS", null);
        System.out.println("Leyendo lista de Recargas...");
        try {
            while (rs.next()) {
                Recarga recarga = new Recarga();
                recarga.setIdRecarga(rs.getInt("idRecarga"));
                recarga.setFechaRecarga(rs.getDate("fechaRecarga"));
                recarga.setMonto(rs.getDouble("monto"));

                String metodo = rs.getString("nombreMetodo");
                recarga.setMetodoPago(MetodoPago.valueOf(metodo));

                Cartera cartera = new Cartera();
                cartera.setIdCartera(rs.getInt("cartera_idCartera"));
                recarga.setCartera(cartera);

                recarga.setActivo(rs.getInt("activo"));
                lista.add(recarga);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Recargas: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return lista;
    }

    @Override
    public Recarga obtenerPorId(int idRecarga) {
        Recarga recarga = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idRecarga);

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_RECARGA_POR_ID", parametrosEntrada);
        System.out.println("Buscando Recarga por ID...");
        try {
            if (rs.next()) {
                recarga = new Recarga();
                recarga.setIdRecarga(rs.getInt("idRecarga"));
                recarga.setFechaRecarga(rs.getDate("fechaRecarga"));
                recarga.setMonto(rs.getDouble("monto"));

                String metodo = rs.getString("nombreMetodo");
                recarga.setMetodoPago(MetodoPago.valueOf(metodo));

                Cartera cartera = new Cartera();
                cartera.setIdCartera(rs.getInt("cartera_idCartera"));
                recarga.setCartera(cartera);

                recarga.setActivo(rs.getInt("activo"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener Recarga: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return recarga;
    }


    @Override
    public ArrayList<Recarga> listarAsociadas(int idCartera) {
        ArrayList<Recarga> lista = new ArrayList<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCartera);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_RECARGAS_X_CARTERA", parametrosEntrada);
        System.out.println("Leyendo lista de Recargas...");
        try {
            while (rs.next()) {
                Recarga recarga = new Recarga();
                recarga.setIdRecarga(rs.getInt("idRecarga"));
                recarga.setFechaRecarga(rs.getDate("fechaRecarga"));
                recarga.setMonto(rs.getDouble("monto"));

                String metodo = rs.getString("nombreMetodo");
                recarga.setMetodoPago(MetodoPago.valueOf(metodo));

                Cartera cartera = new Cartera();
                cartera.setIdCartera(rs.getInt("cartera_idCartera"));
                recarga.setCartera(cartera);

                recarga.setActivo(rs.getInt("activo"));
                lista.add(recarga);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Recargas asociadas al idCartera : " +idCartera+ "\n" + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return lista;   
    }

}
