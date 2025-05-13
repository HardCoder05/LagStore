package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.CarteraDAO;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador; 
import pe.edu.pucp.lagstore.config.DBManager;

public class CarteraMySQL implements CarteraDAO {

    private static final String TABLA = "Cartera";

    private Connection        con;
    private PreparedStatement pst;
    private ResultSet         rs;


    @Override
    public int insertar(Cartera cartera) {
        int idGenerado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO " + TABLA +
                         " (saldoActual, activo, jugador_idJugador) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setDouble(1, cartera.getSaldoActual());
            pst.setInt   (2, cartera.getActivo());
            pst.setInt   (3, cartera.getJugador().getIdJugador());  // FK
            pst.executeUpdate();

            sql = "SELECT @@last_insert_id AS id";
            pst = con.prepareStatement(sql);
            rs  = pst.executeQuery();
            if (rs.next()) {
                idGenerado = rs.getInt("id");
                cartera.setIdCartera(idGenerado);
            }
            con.commit();
            System.out.println("Se registró Cartera con ID " + idGenerado);
        } catch (SQLException ex) {
            try { if (con != null) con.rollback(); } catch (SQLException ign) {}
            System.out.println(ex.getMessage());
            idGenerado = 0;
        } finally { cerrar(); }
        return idGenerado;
    }

    @Override
    public int modificar(Cartera cartera) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE " + TABLA +
                         " SET saldoActual = ?, jugador_idJugador = ? " +
                         " WHERE idCartera = ? AND activo = 1";
            pst = con.prepareStatement(sql);
            pst.setDouble(1, cartera.getSaldoActual());
            pst.setInt   (2, cartera.getJugador().getIdJugador());  // FK
            pst.setInt   (3, cartera.getIdCartera());
            resultado = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return resultado;
    }

    @Override
    public int eliminar(int idCartera) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE " + TABLA +
                         " SET activo = 0 WHERE idCartera = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCartera);
            resultado = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return resultado;
    }


    @Override
    public ArrayList<Cartera> listarTodas() {
        ArrayList<Cartera> lista = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT idCartera, saldoActual, activo, jugador_idJugador FROM " + TABLA;
            pst = con.prepareStatement(sql);
            rs  = pst.executeQuery();
            while (rs.next()) {
                Cartera c = new Cartera();
                c.setIdCartera  (rs.getInt("idCartera"));
                c.setSaldoActual(rs.getDouble("saldoActual"));
                c.setActivo     (rs.getInt("activo"));

                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt("jugador_idJugador"));
                c.setJugador(j);                                 // ← asigna el objeto

                lista.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return lista;
    }
    
    @Override
    public Cartera obtenerPorId(int idCartera) {
        Cartera cartera = null;
        try {
            con = DBManager.getInstance().getConnection();

            String sql = "SELECT idCartera, saldoActual, activo, jugador_idJugador "
                       + "FROM " + TABLA
                       + " WHERE idCartera = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCartera);
            rs  = pst.executeQuery();

            if (rs.next()) {
                cartera = new Cartera();
                cartera.setIdCartera (rs.getInt("idCartera"));
                cartera.setSaldoActual(rs.getDouble("saldoActual"));
                cartera.setActivo    (rs.getInt("activo"));

                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt("jugador_idJugador"));
                cartera.setJugador(j);   
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }

        return cartera;    
    }
    private void cerrar() {
        try { if (con != null) con.close(); } catch (SQLException ignored) {}
    }
}