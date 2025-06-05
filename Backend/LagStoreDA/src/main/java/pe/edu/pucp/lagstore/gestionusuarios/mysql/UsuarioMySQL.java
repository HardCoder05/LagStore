package pe.edu.pucp.lagstore.gestionusuarios.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.UsuarioDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;

public class UsuarioMySQL implements UsuarioDAO{
    private ResultSet lector;

    @Override
    public int insertar(Usuario modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Usuario modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Usuario> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int verificar(Usuario usuario) {
        int resultado = 0;  
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(0, usuario.getEmail());
        parametrosEntrada.put(1, usuario.getContrasena());
        lector =DBManager.getInstance().ejecutarProcedimientoLectura("VERIFICAR_CUENTA_USUARIO", parametrosEntrada);
        try{
            if(lector.next()){
                resultado=lector.getInt("id");
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            System.out.println("Se ha realizado el verificacion del usuario");
            DBManager.getInstance().cerrarConexion();
        }
        
        return resultado;
    }
    
    
    
}