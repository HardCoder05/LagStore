package pe.edu.pucp.lagstore.gestionjuegos.mysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionjuegos.dao.JuegoAdquiridoDAO;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestjuegos.model.JuegoAdquirido;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;

public class JuegoAdquiridoMySQL implements JuegoAdquiridoDAO{
    private ResultSet rs;
    
    @Override
    public int insertar(JuegoAdquirido juegoAdquirido) {
        Map<Integer, Object> inParams = new HashMap<>();
        
        inParams.put(1, juegoAdquirido.getBiblioteca().getIdBiblioteca());
        inParams.put(2, juegoAdquirido.getJuego().getIdJuego());
        inParams.put(3, juegoAdquirido.getFechaAdquisicion());
        inParams.put(4, juegoAdquirido.getUltimaSesion());
        inParams.put(5, juegoAdquirido.getTiempoJuego());
        inParams.put(6, juegoAdquirido.isActualizado());
        inParams.put(7,1);

        return DBManager.getInstance().ejecutarProcedimiento("INSERTAR_JUEGO_ADQUIRIDO", inParams, null);    
    }

    @Override
    public int modificar(JuegoAdquirido juegoAdquirido) {
        Map<Integer, Object> inParams = new HashMap<>();

        inParams.put(1, juegoAdquirido.getBiblioteca().getIdBiblioteca());
        inParams.put(2, juegoAdquirido.getJuego().getIdJuego());
        inParams.put(3, juegoAdquirido.getFechaAdquisicion());
        inParams.put(4, juegoAdquirido.getUltimaSesion());
        inParams.put(5, juegoAdquirido.getTiempoJuego());
        inParams.put(6, juegoAdquirido.isActualizado());

        return DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_JUEGO_ADQUIRIDO", inParams, null);
    }

    @Override
    public int eliminar(int idJuegoAdquirido) {
        Map<Integer, Object> inParams = new HashMap<>();
        inParams.put(1, idJuegoAdquirido);
        return DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_JUEGO_ADQUIRIDO", inParams, null);
    }

    @Override
    public ArrayList<JuegoAdquirido> listarTodos() {
        ArrayList<JuegoAdquirido> juegos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_JUEGOS_ADQUIRIDOS_TODOS", null);
        
        try {
            while (rs.next()) {
                JuegoAdquirido ja = new JuegoAdquirido();

                Juego juego = new Juego();
                juego.setIdJuego(rs.getInt("idJuego"));
                juego.setTitulo(rs.getString("titulo"));
                juego.setDescripcion(rs.getString("descripcion"));
                juego.setPrecio(rs.getDouble("precio"));
                juego.setVersion(rs.getDouble("version"));
                juego.setImagen(rs.getString("imagenJuego"));
                juego.setFechaLanzamiento(rs.getDate("fechaLanzamiento"));
                juego.setRequisitosMinimos(rs.getString("requisitosMinimos"));
                juego.setRequisitosRecomendados(rs.getString("requisitosRecomendados"));
                juego.setEspacioDisco(rs.getDouble("espacioDisco"));
                juego.setFechaUltimaActualizacion(rs.getDate("fechaUltimaActualizacion"));
                juego.setGenero(Genero.valueOf(rs.getString("nombreGenero")));
                juego.setModeloNegocio(ModeloNegocio.valueOf(rs.getString("modelo")));
                juego.setActivo(rs.getInt("juegoActivo"));

                // Desarrollador
                Desarrollador desarrollador = new Desarrollador();
                desarrollador.setIdDesarrollador(rs.getInt("desarrollador_idDesarrollador"));
                desarrollador.setIdUsuario(rs.getInt("idUsuario"));
                desarrollador.setNombre(rs.getString("nombre"));
                desarrollador.setEmail(rs.getString("email"));
                desarrollador.setContrasena(rs.getString("contrasena"));
                desarrollador.setFechaRegistro(rs.getDate("fechaRegistro"));
                desarrollador.setTelefono(rs.getString("telefono"));
                desarrollador.setFotoDePerfil(rs.getString("fotoDePerfil"));
                desarrollador.setNumeroCuenta(rs.getString("numeroCuenta"));
                desarrollador.setIngresoTotal(rs.getDouble("ingresoTotal"));

                juego.setDesarrollador(desarrollador);

                // JuegoAdquirido
                ja.setJuego(juego);
                ja.getBiblioteca().setIdBiblioteca(rs.getInt("fidBiblioteca"));
                ja.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                ja.setUltimaSesion(rs.getDate("ultimaSesion"));
                ja.setTiempoJuego(rs.getDouble("tiempoJuego"));
                ja.setActualizado(rs.getBoolean("actualizado"));
                ja.setActivo(rs.getInt("activo"));

                juegos.add(ja);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al listar juegos adquiridos: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return juegos;
    }


    @Override
    public JuegoAdquirido obtenerPorId(int idJuego) {
        JuegoAdquirido ja = null;

        Map<Integer, Object> inParams = new HashMap<>();
        inParams.put(1, idJuego);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_JUEGO_ADQUIRIDO_POR_ID", inParams);

        try {
            if (rs.next()) {
                ja = new JuegoAdquirido();

                // Juego
                Juego juego = new Juego();
                juego.setIdJuego(rs.getInt("idJuego"));
                juego.setTitulo(rs.getString("titulo"));
                juego.setDescripcion(rs.getString("descripcion"));
                juego.setPrecio(rs.getDouble("precio"));
                juego.setVersion(rs.getDouble("version"));
                juego.setImagen(rs.getString("imagenJuego"));
                juego.setFechaLanzamiento(rs.getDate("fechaLanzamiento"));
                juego.setRequisitosMinimos(rs.getString("requisitosMinimos"));
                juego.setRequisitosRecomendados(rs.getString("requisitosRecomendados"));
                juego.setEspacioDisco(rs.getDouble("espacioDisco"));
                juego.setFechaUltimaActualizacion(rs.getDate("fechaUltimaActualizacion"));
                juego.setGenero(Genero.valueOf(rs.getString("nombreGenero")));
                juego.setModeloNegocio(ModeloNegocio.valueOf(rs.getString("modelo")));
                juego.setActivo(rs.getInt("juegoActivo"));

                // Desarrollador
                Desarrollador desarrollador = new Desarrollador();
                desarrollador.setIdDesarrollador(rs.getInt("desarrollador_idDesarrollador"));
                desarrollador.setIdUsuario(rs.getInt("idUsuario"));
                desarrollador.setNombre(rs.getString("nombre"));
                desarrollador.setEmail(rs.getString("email"));
                desarrollador.setContrasena(rs.getString("contrasena"));
                desarrollador.setFechaRegistro(rs.getDate("fechaRegistro"));
                desarrollador.setTelefono(rs.getString("telefono"));
                desarrollador.setFotoDePerfil(rs.getString("fotoDePerfil"));
                desarrollador.setNumeroCuenta(rs.getString("numeroCuenta"));
                desarrollador.setIngresoTotal(rs.getDouble("ingresoTotal"));

                juego.setDesarrollador(desarrollador);

                // JuegoAdquirido
                ja.setJuego(juego);
                ja.getBiblioteca().setIdBiblioteca(rs.getInt("fidBiblioteca"));
                ja.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                ja.setUltimaSesion(rs.getDate("ultimaSesion"));
                ja.setTiempoJuego(rs.getDouble("tiempoJuego"));
                ja.setActualizado(rs.getBoolean("actualizado"));
                ja.setActivo(rs.getInt("activo"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al obtener juego adquirido por ID: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return ja;
    }
    
    @Override
    public ArrayList<JuegoAdquirido> listarJuegosAdquiridosPorBiblioteca(int idBiblioteca){
        ArrayList<JuegoAdquirido> juegos = new ArrayList<>();
        
        Map<Integer, Object> inParams = new HashMap<>();
        inParams.put(1, idBiblioteca);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_JUEGOS_ADQUIRIDOS_X_BIBLIOTECA", inParams);
        
        try {
            while (rs.next()) {
                JuegoAdquirido ja = new JuegoAdquirido();

                Juego juego = new Juego();
                juego.setIdJuego(rs.getInt("idJuego"));
                juego.setTitulo(rs.getString("titulo"));
                juego.setPrecio(rs.getDouble("precio"));
                juego.setImagen(rs.getString("imagenJuego"));
                juego.setGenero(Genero.valueOf(rs.getString("nombreGenero")));
                juego.setModeloNegocio(ModeloNegocio.valueOf(rs.getString("modelo")));
                juego.setActivo(1);
                
                // JuegoAdquirido
                ja.setJuego(juego);
                ja.getBiblioteca().setIdBiblioteca(idBiblioteca);
                ja.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                ja.setUltimaSesion(rs.getDate("ultimaSesion"));
                ja.setTiempoJuego(rs.getDouble("tiempoJuego"));
                ja.setActualizado(rs.getBoolean("actualizado"));
                ja.setActivo(1);

                juegos.add(ja);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al listar juegos adquiridos: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return juegos;
    }
    
    @Override
    public JuegoAdquirido obtenerJuegoAdquiridoPorBibliotecaYJuego(int 
        idBiblioteca,int idJuego){
        JuegoAdquirido ja = null;

        Map<Integer, Object> inParams = new HashMap<>();
        inParams.put(1, idBiblioteca);
        inParams.put(2, idJuego);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_JUEGO_ADQUIRIDO_POR_BIBLIOTECA_Y_JUEGO", inParams);

        try {
            if (rs.next()) {
                ja = new JuegoAdquirido();
                ja.getBiblioteca().setIdBiblioteca(idBiblioteca);
                ja.getJuego().setIdJuego(idJuego);
                ja.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                ja.setUltimaSesion(rs.getDate("ultimaSesion"));
                ja.setTiempoJuego(rs.getDouble("tiempoJuego"));
                ja.setActualizado(rs.getBoolean("actualizado"));
                ja.setActivo(rs.getInt("activo"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al obtener juego adquirido por ID: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return ja;
    }
    
}
