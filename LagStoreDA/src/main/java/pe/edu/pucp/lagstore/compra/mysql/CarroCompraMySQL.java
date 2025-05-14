package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.compra.dao.CarroCompraDAO;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

public class CarroCompraMySQL implements CarroCompraDAO {

    private ResultSet rs;

    @Override
    public int insertar(CarroCompra carro) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, java.sql.Types.INTEGER); // OUT parameter para ID generado
        parametrosEntrada.put(2, carro.getTotalEstimado());
        parametrosEntrada.put(3, carro.getJugador().getIdJugador());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CARRO_COMPRA", parametrosEntrada, parametrosSalida);
        carro.setIdCarroCompra((int) parametrosSalida.get(1));

        System.out.println("Se ha registrado el Carro de Compras");
        return carro.getIdCarroCompra();
    }

    @Override
    public int modificar(CarroCompra carro) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, carro.getIdCarroCompra());
        parametrosEntrada.put(2, carro.getTotalEstimado());
        parametrosEntrada.put(3, carro.getJugador().getIdJugador());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CARRO_COMPRA", parametrosEntrada, null);
        System.out.println("Se ha modificado el Carro de Compras");
        return resultado;
    }

    @Override
    public int eliminar(int idCarroCompra) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCarroCompra);

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_CARRO_COMPRA", parametrosEntrada, null);
        System.out.println("Se ha eliminado  el Carro de Compras");
        return resultado;
    }

    @Override
    public ArrayList<CarroCompra> listarTodas() {
        ArrayList<CarroCompra> lista = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CARRO_COMPRAS", null);
        System.out.println("Leyendo lista de Carros de Compras...");
        try {
            while (rs.next()) {
                CarroCompra carro = new CarroCompra();
                carro.setIdCarroCompra(rs.getInt("idCarroCompra"));
                carro.setTotalEstimado(rs.getDouble("totalEstimado"));
                carro.setActivo(rs.getInt("activo"));

                Jugador jugador = new Jugador();
                jugador.setIdJugador(rs.getInt("jugador_idJugador"));
                carro.setJugador(jugador);

                lista.add(carro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Carros: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return lista;
    }

    @Override
    public CarroCompra obtenerPorId(int idCarroCompra) {
        CarroCompra carro = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCarroCompra);

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_CARRO_COMPRA_POR_ID", parametrosEntrada);
        System.out.println("Buscando Carro de Compras por ID...");
        try {
            if (rs.next()) {
                carro = new CarroCompra();
                carro.setIdCarroCompra(rs.getInt("idCarroCompra"));
                carro.setTotalEstimado(rs.getDouble("totalEstimado"));
                carro.setActivo(rs.getInt("activo"));

                Jugador jugador = new Jugador();
                jugador.setIdJugador(rs.getInt("jugador_idJugador"));
                carro.setJugador(jugador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener Carro: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return carro;
    }
}