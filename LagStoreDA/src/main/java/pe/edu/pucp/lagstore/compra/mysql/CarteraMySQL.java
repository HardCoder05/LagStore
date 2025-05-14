package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.compra.dao.CarteraDAO;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;
import pe.edu.pucp.lagstore.config.DBManager;

public class CarteraMySQL implements CarteraDAO {

    private ResultSet rs;

    @Override
    public int insertar(Cartera cartera) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, java.sql.Types.INTEGER); // OUT para ID generado
        parametrosEntrada.put(2, cartera.getSaldoActual());
        parametrosEntrada.put(3, cartera.getJugador().getIdJugador());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CARTERA", parametrosEntrada, parametrosSalida);
        cartera.setIdCartera((int) parametrosSalida.get(1));

        System.out.println("Se registró Cartera");
        return cartera.getIdCartera();
    }

    @Override
    public int modificar(Cartera cartera) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, cartera.getIdCartera());
        parametrosEntrada.put(2, cartera.getSaldoActual());
        parametrosEntrada.put(3, cartera.getJugador().getIdJugador());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CARTERA", parametrosEntrada, null);
        System.out.println("Se modificó Cartera");
        return resultado;
    }

    @Override
    public int eliminar(int idCartera) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCartera);

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_CARTERA", parametrosEntrada, null);
        System.out.println("Se eliminó Cartera");
        return resultado;
    }

    @Override
    public ArrayList<Cartera> listarTodas() {
        ArrayList<Cartera> lista = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CARTERAS", null);
        System.out.println("Leyendo lista de Carteras...");
        try {
            while (rs.next()) {
                Cartera cartera = new Cartera();
                cartera.setIdCartera(rs.getInt("idCartera"));
                cartera.setSaldoActual(rs.getDouble("saldoActual"));
                cartera.setActivo(rs.getInt("activo"));

                Jugador jugador = new Jugador();
                jugador.setIdJugador(rs.getInt("jugador_idJugador"));
                cartera.setJugador(jugador);

                lista.add(cartera);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Carteras: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return lista;
    }

    @Override
    public Cartera obtenerPorId(int idCartera) {
        Cartera cartera = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCartera);

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_CARTERA_POR_ID", parametrosEntrada);
        System.out.println("Buscando Cartera por ID...");
        try {
            if (rs.next()) {
                cartera = new Cartera();
                cartera.setIdCartera(rs.getInt("idCartera"));
                cartera.setSaldoActual(rs.getDouble("saldoActual"));
                cartera.setActivo(rs.getInt("activo"));

                Jugador jugador = new Jugador();
                jugador.setIdJugador(rs.getInt("jugador_idJugador"));
                cartera.setJugador(jugador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener Cartera: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return cartera;
    }
}