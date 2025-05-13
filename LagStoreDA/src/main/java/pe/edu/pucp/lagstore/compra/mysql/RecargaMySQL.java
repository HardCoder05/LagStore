package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.RecargaDAO;
import pe.edu.pucp.lagstore.compra.model.Recarga;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.config.DBManager;

public class RecargaMySQL implements RecargaDAO {

    private static final String TABLA = "Recarga";

    private Connection        con;
    private PreparedStatement pst;
    private ResultSet         rs;


    @Override
    public int insertar(Recarga recarga) {
        int idGenerado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO " + TABLA +
                         " (fechaRecarga, monto, metodoPago_idMetodo, cartera_idCartera, activo) " +
                         "VALUES (?,?,?,?,1)";
            pst = con.prepareStatement(sql);
            pst.setDate  (1, new java.sql.Date(recarga.getFechaRecarga().getTime()));
            pst.setDouble(2, recarga.getMonto());
            pst.setInt   (3, recarga.getMetodoPago().ordinal());
            pst.setInt   (4, recarga.getCartera().getIdCartera());
            pst.executeUpdate();

            sql = "SELECT @@last_insert_id AS id";
            pst = con.prepareStatement(sql);
            rs  = pst.executeQuery();
            if (rs.next()) {
                idGenerado = rs.getInt("id");
                recarga.setIdRecarga(idGenerado);
            }

            //Actualizar el saldo de la cartera
            sql = "UPDATE Cartera SET saldoActual = saldoActual + ? WHERE idCartera = ?";
            pst = con.prepareStatement(sql);
            pst.setDouble(1, recarga.getMonto());
            pst.setInt   (2, recarga.getCartera().getIdCartera());
            pst.executeUpdate();

            con.commit();
            System.out.println("Se registró Recarga con ID " + idGenerado);
        } catch (SQLException ex) {
            try { if (con != null) con.rollback(); } catch (SQLException ignored) {}
            System.out.println(ex.getMessage());
            idGenerado = 0;
        } finally { cerrar(); }
        return idGenerado;
    }

    
    @Override
    public int modificar(Recarga recarga) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE " + TABLA +
                         " SET fechaRecarga = ?, monto = ?, metodoPago_idMetodo = ?, " +
                         "     cartera_idCartera = ? " +
                         " WHERE idRecarga = ? AND activo = 1";
            pst = con.prepareStatement(sql);
            pst.setDate  (1, new java.sql.Date(recarga.getFechaRecarga().getTime()));
            pst.setDouble(2, recarga.getMonto());
            pst.setInt   (3, recarga.getMetodoPago().ordinal());
            pst.setInt   (4, recarga.getCartera().getIdCartera());
            pst.setInt   (5, recarga.getIdRecarga());
            resultado = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return resultado;
    }


    @Override
    public int eliminar(int idRecarga) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE " + TABLA + " SET activo = 0 WHERE idRecarga = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idRecarga);
            resultado = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return resultado;
    }


    @Override
    public ArrayList<Recarga> listarTodas() {
        ArrayList<Recarga> lista = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT idRecarga, fechaRecarga, monto, metodoPago_idMetodo, " +
                         "       cartera_idCartera, activo " +
                         "FROM " + TABLA;
            pst = con.prepareStatement(sql);
            rs  = pst.executeQuery();
            while (rs.next()) {
                Recarga r = new Recarga();
                r.setIdRecarga  (rs.getInt("idRecarga"));
                r.setFechaRecarga(rs.getDate("fechaRecarga"));
                r.setMonto      (rs.getDouble("monto"));
                r.setMetodoPago (MetodoPago.values()[rs.getInt("metodoPago_idMetodo")]);

                Cartera c = new Cartera();
                c.setIdCartera(rs.getInt("cartera_idCartera"));
                r.setCartera(c);

                r.setActivo     (rs.getInt("activo"));
                lista.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return lista;
    }

    @Override
    public Recarga obtenerPorId(int idRecarga) {
        Recarga recarga = null;
        try {
            con = DBManager.getInstance().getConnection();

            String sql = "SELECT idRecarga, fechaRecarga, monto, " +
                         "       metodoPago_idMetodo, cartera_idCartera, activo " +
                         "FROM Recarga WHERE idRecarga = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idRecarga);

            rs = pst.executeQuery();
            if (rs.next()) {
                recarga = new Recarga();
                recarga.setIdRecarga   (rs.getInt   ("idRecarga"));
                recarga.setFechaRecarga(rs.getDate  ("fechaRecarga"));
                recarga.setMonto       (rs.getDouble("monto"));
                recarga.setMetodoPago  (MetodoPago.values()[
                                         rs.getInt("metodoPago_idMetodo")]);

                Cartera cartera = new Cartera();
                cartera.setIdCartera(rs.getInt("cartera_idCartera"));
                recarga.setCartera(cartera);

                recarga.setActivo      (rs.getInt("activo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException ignored) {}
        }
        return recarga;  
    }
    private void cerrar() {
        try { if (con != null) con.close(); } catch (SQLException ignored) {}
    }
}
