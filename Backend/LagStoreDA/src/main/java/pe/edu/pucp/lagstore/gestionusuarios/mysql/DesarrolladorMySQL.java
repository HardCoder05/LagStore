package pe.edu.pucp.lagstore.gestionusuarios.mysql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;


public class DesarrolladorMySQL implements DesarrolladorDAO{
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    public int insertar(Desarrollador desarrollador) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, desarrollador.getNombre());
        parametrosEntrada.put(3, desarrollador.getEmail());
        parametrosEntrada.put(4, desarrollador.getContrasena());
        if(desarrollador.getFechaRegistro()==null){
            try {
                parametrosEntrada.put(5,sdf.parse("2025-06-04"));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else{
            parametrosEntrada.put(5, desarrollador.getFechaRegistro());
        }
        
        parametrosEntrada.put(6, desarrollador.getTelefono());
        parametrosEntrada.put(7, desarrollador.getFotoDePerfil());
        parametrosEntrada.put(8, desarrollador.getNumeroCuenta());
        parametrosEntrada.put(9, desarrollador.getIngresoTotal());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_DESARROLLADOR", parametrosEntrada, parametrosSalida);
        desarrollador.setIdDesarrollador((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del Desarrollador");
<<<<<<< HEAD
        return desarrollador.getIdUsuario();
=======
        return desarrollador.getIdDesarrollador();
>>>>>>> main
    }

    @Override
    public int modificar(Desarrollador desarrollador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,desarrollador.getIdDesarrollador());
        parametrosEntrada.put(2, desarrollador.getNumeroCuenta());
        parametrosEntrada.put(3, desarrollador.getIngresoTotal());
        parametrosEntrada.put(4, desarrollador.getNombre());
        parametrosEntrada.put(5, desarrollador.getEmail());
        parametrosEntrada.put(6, desarrollador.getContrasena());
        parametrosEntrada.put(7, new Date(desarrollador.getFechaRegistro().getTime()));
        parametrosEntrada.put(8, desarrollador.getTelefono());
        parametrosEntrada.put(9, desarrollador.getFotoDePerfil());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_DESARROLLADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del desarrollador");
        return resultado;
    }

    @Override
    public int eliminar(int idDesarrollador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idDesarrollador);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_DESARROLLADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del desarrollador");
        return resultado;
    }

    @Override
    public ArrayList<Desarrollador> listarTodos() {
        ArrayList<Desarrollador> desarrolladores = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_DESARROLLADOR", null);
        System.out.println("Lectura de desarrolladores...");
        try{
            while(rs.next()){
                Desarrollador d = new Desarrollador();
                d.setIdDesarrollador(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setEmail(rs.getString(3));
                d.setContrasena(rs.getString(4));
                d.setFechaRegistro(rs.getDate(5));
                d.setTelefono(rs.getString(6));
                d.setFotoDePerfil(rs.getString(7));
                d.setNumeroCuenta(rs.getString(8));
                d.setIngresoTotal(rs.getDouble(9));
                desarrolladores.add(d);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return desarrolladores; 
    }

    @Override
    public Desarrollador obtenerPorId(int idDesarrollador) {
        Desarrollador desarrollador = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idDesarrollador);

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_X_ID_DESARROLLADOR", parametrosEntrada);
        System.out.println("Buscando Desarrollador por ID...");
        try {
            if (rs.next()) {
                desarrollador = new Desarrollador();
                desarrollador.setIdDesarrollador(idDesarrollador);
                desarrollador.setNombre(rs.getString(1));
                desarrollador.setEmail(rs.getString(2));
                desarrollador.setContrasena(rs.getString(3));
                desarrollador.setFechaRegistro(rs.getDate(4));
                desarrollador.setTelefono(rs.getString(5));
                desarrollador.setFotoDePerfil(rs.getString(6));
                desarrollador.setNumeroCuenta(rs.getString(7));
                desarrollador.setIngresoTotal(rs.getDouble(8));
                System.out.println(desarrollador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener Desarrollador: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return desarrollador;
    }
    
}