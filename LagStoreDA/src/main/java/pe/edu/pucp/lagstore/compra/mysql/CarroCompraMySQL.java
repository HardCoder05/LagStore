
package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.CarroCompraDAO;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

/**
 *
 * @author Jean Pool
 */

public class CarroCompraMySQL implements CarroCompraDAO {

    private Connection        con;
    private PreparedStatement pst;
    private ResultSet         rs;

    private static final String TABLA_CARRO        = "CarroCompra";
    private static final String TABLA_CARRO_X_JUEGO = "CarroCompraXJuego";

    
    @Override
    public int insertar(CarroCompra carro) {
        int idGenerado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO " + TABLA_CARRO +
                         " (totalEstimado, activo, jugador_idJugador) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setDouble(1, carro.getTotalEstimado());
            pst.setInt   (2, carro.getActivo());               
            pst.setInt   (3, carro.getJugador().getIdJugador()); // FK
            pst.executeUpdate();

            sql = "SELECT @@last_insert_id AS id";
            pst = con.prepareStatement(sql);
            rs  = pst.executeQuery();
            if (rs.next()) {
                idGenerado = rs.getInt("id");
                carro.setIdCarroCompra(idGenerado);
            }

            // Inserta juegos en tabla puente
            if (carro.getJuegos() != null && !carro.getJuegos().isEmpty()) {
                sql = "INSERT INTO " + TABLA_CARRO_X_JUEGO +
                      " (idCarroCompra, idJuego) VALUES (?, ?)";
                pst = con.prepareStatement(sql);
                for (Juego j : carro.getJuegos()) {
                    pst.setInt(1, idGenerado);
                    pst.setInt(2, j.getIdJuego());
                    pst.addBatch();
                }
                pst.executeBatch();
            }
            con.commit();
            System.out.println("Se registró CarroCompra con ID " + idGenerado);
        } catch (SQLException ex) {
            try { if (con != null) con.rollback(); } catch (SQLException ign) {}
            System.out.println(ex.getMessage());
            idGenerado = 0;
        } finally { cerrar(); }
        return idGenerado;
    }

    
@Override
public int modificar(CarroCompra carro) {
    int resultado = 0;
    try {
        con = DBManager.getInstance().getConnection();
        con.setAutoCommit(false);

        String sql = "UPDATE " + TABLA_CARRO +
                     " SET totalEstimado = ?, jugador_idJugador = ? " +
                     " WHERE idCarroCompra = ? AND activo = 1";
        pst = con.prepareStatement(sql);
        pst.setDouble(1, carro.getTotalEstimado());
        pst.setInt   (2, carro.getJugador().getIdJugador());
        pst.setInt   (3, carro.getIdCarroCompra());
        resultado = pst.executeUpdate();                    


        sql = "UPDATE " + TABLA_CARRO_X_JUEGO +
              " SET activo = 0 " +
              " WHERE idCarroCompra = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, carro.getIdCarroCompra());
        pst.executeUpdate();

        // Se reactiva o inserta los juegos que siguen en el carro 
        if (carro.getJuegos() != null && !carro.getJuegos().isEmpty()) {
            sql = "INSERT INTO " + TABLA_CARRO_X_JUEGO +
                  " (idCarroCompra, idJuego, activo) VALUES (?, ?, 1) " +
                  " ON DUPLICATE KEY UPDATE activo = 1";
            pst = con.prepareStatement(sql);
            for (Juego j : carro.getJuegos()) {
                pst.setInt(1, carro.getIdCarroCompra());
                pst.setInt(2, j.getIdJuego());
                pst.addBatch();
            }
            pst.executeBatch();
        }

        con.commit();
    } catch (SQLException ex) {
        try { if (con != null) con.rollback(); } catch (SQLException ignored) {}
        System.out.println(ex.getMessage());
    } finally { cerrar(); }
    return resultado;
}

    @Override
    public int eliminar(int idCarroCompra) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);

            String sql = "UPDATE " + TABLA_CARRO +
                         " SET activo = 0 " +
                         " WHERE idCarroCompra = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCarroCompra);
            resultado = pst.executeUpdate();                   
            
            //Desactivamos las resultado de la tabla CarroCompraXJuego
            sql = "UPDATE " + TABLA_CARRO_X_JUEGO +
                  " SET activo = 0 " +
                  " WHERE idCarroCompra = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCarroCompra);
            pst.executeUpdate();                       

            con.commit();
        } catch (SQLException ex) {
            try { if (con != null) con.rollback(); } catch (SQLException ign) {}
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return resultado;  
    }


    @Override
    public ArrayList<CarroCompra> listarTodas() {
        ArrayList<CarroCompra> lista = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT idCarroCompra, totalEstimado, activo, jugador_idJugador " +
                         "FROM " + TABLA_CARRO;
            pst = con.prepareStatement(sql);
            rs  = pst.executeQuery();
            while (rs.next()) {
                lista.add(cargarCarroDesdeRS(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return lista;
    }

    @Override
    public CarroCompra obtenerPorId(int idCarroCompra) {
        CarroCompra carro = null;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT idCarroCompra, totalEstimado, activo, jugador_idJugador " +
                         "FROM " + TABLA_CARRO + " WHERE idCarroCompra = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCarroCompra);
            rs = pst.executeQuery();
            if (rs.next()) carro = cargarCarroDesdeRS(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally { cerrar(); }
        return carro;
    }

    
    private CarroCompra cargarCarroDesdeRS(ResultSet rs) throws SQLException {
        CarroCompra carro = new CarroCompra();
        carro.setIdCarroCompra(rs.getInt("idCarroCompra"));
        carro.setTotalEstimado(rs.getDouble("totalEstimado"));
        carro.setActivo(rs.getInt("activo"));

        // Jugador dueño
        Jugador j = new Jugador();
        j.setIdJugador(rs.getInt("jugador_idJugador"));
        carro.setJugador(j);

        // Juegos asociados
        String sql = "SELECT idJuego FROM " + TABLA_CARRO_X_JUEGO +
                     " WHERE idCarroCompra = ?";
        PreparedStatement pst2 = con.prepareStatement(sql);
        pst2.setInt(1, carro.getIdCarroCompra());
        ResultSet rs2 = pst2.executeQuery();
        ArrayList<Juego> juegos = new ArrayList<>();
        while (rs2.next()) {
            Juego juego = new Juego();
            juego.setIdJuego(rs2.getInt("idJuego"));
            juegos.add(juego);
        }
        carro.setJuegos(juegos);
        return carro;
    }

    private void cerrar() {
        try { if (con != null) con.close(); } catch (SQLException ignored) {}
    }
}