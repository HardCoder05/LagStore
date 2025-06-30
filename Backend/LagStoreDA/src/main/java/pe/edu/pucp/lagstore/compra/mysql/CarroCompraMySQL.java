package pe.edu.pucp.lagstore.compra.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.compra.dao.CarroCompraDAO;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;

import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

public class CarroCompraMySQL implements CarroCompraDAO {

    private ResultSet rs;

    @Override
    public int insertar(CarroCompra carro) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        parametrosSalida.put(1, java.sql.Types.INTEGER); // OUT: ID generado
        parametrosEntrada.put(2, carro.getTotalEstimado());
        parametrosEntrada.put(3, carro.getJugador().getIdJugador());

        // Se Inserta el carro de compras
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CARRO_COMPRA", parametrosEntrada, parametrosSalida);

        // Se Recupera el ID generado
        int idGenerado = (int) parametrosSalida.get(1);
        carro.setIdCarroCompra(idGenerado);

        // Se inserta cada juego asociado
        if (carro.getJuegos() != null) {
            for (Juego juego : carro.getJuegos()) {
                Map<Integer, Object> paramsJuego = new HashMap<>();
                paramsJuego.put(1, idGenerado);
                paramsJuego.put(2, juego.getIdJuego());
                DBManager.getInstance().ejecutarProcedimiento("AGREGAR_JUEGO_A_CARRO", paramsJuego, null);
            }
        }

        System.out.println("Se ha registrado el Carro de Compras con sus juegos.");
        return idGenerado;
    }


    @Override
    public int modificar(CarroCompra carro) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, carro.getIdCarroCompra());
        parametrosEntrada.put(2, carro.getTotalEstimado());
        parametrosEntrada.put(3, carro.getJugador().getIdJugador());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CARRO_COMPRA", parametrosEntrada, null);
        
        if (resultado > 0) {
            // 2. Eliminar los juegos actuales del carro
            Map<Integer, Object> paramsEliminar = new HashMap<>();
            paramsEliminar.put(1, carro.getIdCarroCompra());
            DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_JUEGOS_DE_CARRO", paramsEliminar, null);
         
            if(carro.setCantJuegos()>0){
                // 3. Insertar los nuevos juegos
                for (Juego juego : carro.getJuegos()) {
                    Map<Integer, Object> paramsJuego = new HashMap<>();
                    paramsJuego.put(1, carro.getIdCarroCompra());
                    paramsJuego.put(2, juego.getIdJuego());
                    DBManager.getInstance().ejecutarProcedimiento("AGREGAR_JUEGO_A_CARRO", paramsJuego, null);
                }
            }
            System.out.println("Carro y juegos actualizados correctamente.");
        }
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
                
                // Obtener juegos asociados al carro
                ArrayList<Juego> juegos = new ArrayList<>();
                Map<Integer, Object> parametros = new HashMap<>();
                parametros.put(1, carro.getIdCarroCompra());

                ResultSet rsJuegos = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_JUEGOS_DE_CARRO", parametros);

                while (rsJuegos.next()) {
                    Juego juego = new Juego();
                    juego.setIdJuego(rsJuegos.getInt("idJuego"));
                    juego.setTitulo(rsJuegos.getString("titulo"));
                    juego.setDescripcion(rsJuegos.getString("descripcion"));
                    juego.setPrecio(rsJuegos.getDouble("precio"));
                    juego.setVersion(rsJuegos.getDouble("version"));
                    juego.setImagen(rsJuegos.getString("imagenJuego"));
                    juego.setFechaLanzamiento(rsJuegos.getDate("fechaLanzamiento"));
                    juego.setRequisitosMinimos(rsJuegos.getString("requisitosMinimos"));
                    juego.setRequisitosRecomendados(rsJuegos.getString("requisitosRecomendados"));
                    juego.setEspacioDisco(rsJuegos.getDouble("espacioDisco"));
                    juego.setFechaUltimaActualizacion(rsJuegos.getDate("fechaUltimaActualizacion"));
                    juego.setActivo(rsJuegos.getInt("activo"));
                    juego.setGenero(Genero.valueOf(rsJuegos.getString("nombreGenero")));
                    juego.setModeloNegocio(ModeloNegocio.valueOf(rsJuegos.getString("modelo")));
                    
                    juegos.add(juego);
                }
                carro.setJuegos(juegos);
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
                
                 // Obtener juegos asociados al carro
                ArrayList<Juego> juegos = new ArrayList<>();
                Map<Integer, Object> parametros = new HashMap<>();
                parametros.put(1, carro.getIdCarroCompra());

                ResultSet rsJuegos = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_JUEGOS_DE_CARRO", parametros);

                while (rsJuegos.next()) {
                    Juego juego = new Juego();
                    juego.setIdJuego(rsJuegos.getInt("idJuego"));
                    juego.setTitulo(rsJuegos.getString("titulo"));
                    juego.setDescripcion(rsJuegos.getString("descripcion"));
                    juego.setPrecio(rsJuegos.getDouble("precio"));
                    juego.setVersion(rsJuegos.getDouble("version"));
                    juego.setImagen(rsJuegos.getString("imagenJuego"));
                    juego.setFechaLanzamiento(rsJuegos.getDate("fechaLanzamiento"));
                    juego.setRequisitosMinimos(rsJuegos.getString("requisitosMinimos"));
                    juego.setRequisitosRecomendados(rsJuegos.getString("requisitosRecomendados"));
                    juego.setEspacioDisco(rsJuegos.getDouble("espacioDisco"));
                    juego.setFechaUltimaActualizacion(rsJuegos.getDate("fechaUltimaActualizacion"));
                    juego.setActivo(rsJuegos.getInt("activo"));
                    juego.setGenero(Genero.valueOf(rsJuegos.getString("nombreGenero")));
                    juego.setModeloNegocio(ModeloNegocio.valueOf(rsJuegos.getString("modelo")));
                    
//                    juegos.add(juego);
                }
                carro.setJuegos(juegos);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener Carro: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return carro;
    }

    @Override
    public CarroCompra obtenerPorIdUsuario(int idJugador) {
        
         // 1. Llamar al SP que devuelve el carro activo por usuario
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, idJugador);

        ResultSet rs = DBManager.getInstance()
            .ejecutarProcedimientoLectura("OBTENER_CARRO_POR_USUARIO", params);

        try {
            if (rs.next()) {
                int idCarro = rs.getInt("idCarroCompra");
                // 2. Reutilizar tu método existente para cargar todo el carro
                return this.obtenerPorId(idCarro);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo el carro por jugador: " + e.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        // No existe carrito activo para este jugador
        return null;
        
        
     }
}