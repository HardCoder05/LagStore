package pe.edu.pucp.lagstore.compra.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.MetodoPagoDAO;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import pe.edu.pucp.lagstore.config.DBManager;


public class MetodoPagoMySQL implements MetodoPagoDAO{
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    @Override
    public int insertar(MetodoPago metodo) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();

            // Verificar si ya existe (previene duplicados)
            String checkSql = "SELECT COUNT(*) AS total FROM MetodoPago WHERE nombreMetodo = ?";
            pst = con.prepareStatement(checkSql);
            pst.setString(1, metodo.name());
            rs = pst.executeQuery();
            rs.next();
            if (rs.getInt("total") > 0) {
                System.out.println("MetodoPago " + metodo.name() + " ya existe, no se insertó.");
                return 0;
            }

            // Insertar si no existe
            String sql = "INSERT INTO MetodoPago (nombreMetodo) VALUES (?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, metodo.name());
            resultado = pst.executeUpdate();
            System.out.println("Se ha registrado MetodoPago: " + metodo.name());
        } catch (SQLException ex) {
            System.out.println("Error al insertar MetodoPago: " + ex.getMessage());
            resultado = 0;
        } finally {
            try { if (con != null) con.close(); } catch (SQLException ignored) {}
        }
        return resultado;
}

    @Override
    public int modificar(MetodoPago metodo) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE MetodoPago SET nombreMetodo = ? WHERE idMetodo = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, metodo.name());
            pst.setInt(2, metodo.ordinal());
            resultado = pst.executeUpdate();
            System.out.println("Se ha modificado la tabla MetodoPago...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int metodoId) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE MetodoPago SET activo = 0 WHERE idMetodo = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, metodoId);

            resultado = pst.executeUpdate();              // 1 = se desactivó, 0 = no existía
            System.out.println("Se desactivó MetodoPago con ID " + metodoId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public ArrayList<MetodoPago> listarTodas() {
        ArrayList<MetodoPago> metodos = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT idMetodo FROM MetodoPago";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idMetodo");
                MetodoPago metodo = MetodoPago.values()[id];
                metodos.add(metodo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return metodos;
    }
    
}
