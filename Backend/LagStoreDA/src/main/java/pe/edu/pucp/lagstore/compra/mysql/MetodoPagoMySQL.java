package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.compra.dao.MetodoPagoDAO;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import pe.edu.pucp.lagstore.config.DBManager;

public class MetodoPagoMySQL implements MetodoPagoDAO {

    private ResultSet rs;

    @Override
    public int insertar(MetodoPago metodo) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, metodo.name());   

        int resultado = DBManager.getInstance().ejecutarProcedimiento("INSERTAR_METODOPAGO", parametrosEntrada, null);
        if (resultado > 0) {
            System.out.println("Se registró MetodoPago: " + metodo.name());
        } else {
            System.out.println("MetodoPago " + metodo.name() + " ya existía o no se insertó.");
        }
        return resultado;
    }

    @Override
    public int modificar(MetodoPago metodo) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, metodo.ordinal());   
        parametrosEntrada.put(2, metodo.name());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_METODOPAGO", parametrosEntrada, null);
        System.out.println("Se modificó MetodoPago: " + metodo.name());
        return resultado;
    }

    @Override
    public int eliminar(int metodoId) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, metodoId);

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_METODOPAGO", parametrosEntrada, null);
        System.out.println("Se eliminó (soft-delete) MetodoPago con ID: " + metodoId);
        return resultado;
    }

    @Override
    public ArrayList<MetodoPago> listarTodas() {
        ArrayList<MetodoPago> metodos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_METODOSPAGO", null);
        System.out.println("Leyendo lista de MetodoPago...");
        try {
            while (rs.next()) {
                String nombreMetodo = rs.getString("nombreMetodo");
                try {
                    MetodoPago metodo = MetodoPago.valueOf(nombreMetodo);
                    metodos.add(metodo);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Método de pago desconocido en BD: " + nombreMetodo);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar MetodoPago: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return metodos;
    }
}