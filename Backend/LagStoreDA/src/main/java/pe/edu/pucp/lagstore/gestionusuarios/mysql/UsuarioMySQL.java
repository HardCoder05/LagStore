package pe.edu.pucp.lagstore.gestionusuarios.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.UsuarioDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Rol;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;

public class UsuarioMySQL implements UsuarioDAO{
    private ResultSet rs;

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
        
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, usuario.getEmail());         // índice 1
        parametrosEntrada.put(2, usuario.getContrasena());    // índice 2

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("VERIFICAR_CUENTA_USUARIO", parametrosEntrada);

        try {
            if (rs.next()) {
                resultado = rs.getInt("id");  // Devuelve el ID del usuario si existe
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
//            System.out.println("Se ha realizado la verificación del usuario");
            DBManager.getInstance().cerrarConexion();
        }

        return resultado; // Si no encontró al usuario, retorna 0
    }

    @Override
    public Rol obtenerRol(int idUsuario) {
        Rol rol = null;

        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idUsuario);

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_ROL_X_ID", parametrosEntrada);

        try {
            if (rs.next()) {
                String nombreRol = rs.getString("nombreRol");
                rol = Rol.valueOf(nombreRol); // Convierte el texto al enum Rol
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al obtener el rol: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return rol;
    }

    
    
}