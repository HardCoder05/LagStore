package pe.edu.pucp.lagstore.gestionusuarios.mysql;
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
=======

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
>>>>>>> main
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.UsuarioDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;

public class UsuarioMySQL implements UsuarioDAO{
<<<<<<< HEAD
=======
    private ResultSet rs;
>>>>>>> main

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
    
<<<<<<< HEAD
=======
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

    
>>>>>>> main
    
    
}