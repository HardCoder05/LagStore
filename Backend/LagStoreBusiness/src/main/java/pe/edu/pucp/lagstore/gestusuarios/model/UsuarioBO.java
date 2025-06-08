/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestusuarios.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestionusuarios.dao.UsuarioDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.UsuarioMySQL;

/**
 *
 * @author W10
 */
public class UsuarioBO {
    private final UsuarioDAO daoUsuario;
    
    public UsuarioBO(){
        daoUsuario = new UsuarioMySQL();
    }
    
    public int insertar(Usuario usuario){
        return daoUsuario.insertar(usuario);
    }
    
    public int modificar(Usuario usuario){
        return daoUsuario.modificar(usuario);
    }

    public int eliminar(int idUsuario){
        return daoUsuario.eliminar(idUsuario);
    }

    public ArrayList<Usuario> listarUsuarios(){
        return daoUsuario.listarTodos();
    }
    
    public Usuario obtenerPorId(int idUsuario){
        return daoUsuario.obtenerPorId(idUsuario);
    }
    public int verificar(Usuario usuario){
        return daoUsuario.verificar(usuario);
    }
}
