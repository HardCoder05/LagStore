/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestusuarios.model;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.AdministradorMySQL;
import pe.edu.pucp.lagstore.gestionusuarios.dao.AdministradorDAO;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
/**
 *
 * @author W10
 */
public class AdministradorBO {
    private final AdministradorDAO daoAdministrador;

    public AdministradorBO(){
        daoAdministrador = new AdministradorMySQL();
    }
    
    public int insertar(Administrador administrador){
        return daoAdministrador.insertar(administrador);
    }
    
    public int modificar(Administrador administrador){
        return daoAdministrador.modificar(administrador);
    }

    public int eliminar(int idAdministrador){
        return daoAdministrador.eliminar(idAdministrador);
    }

    public ArrayList<Administrador> listarAdministradores(){
        return daoAdministrador.listarTodos();
    }
    public Administrador obtenerPorId(int idAdministrador){
        return daoAdministrador.obtenerPorId(idAdministrador);
    }
    public ArrayList<Juego> listarJuegosMasVendidos(){
        return daoAdministrador.listarJuegosMasVendidos();
    }
    
    public ArrayList<Juego>listarJuegosCalificacionAltas(){
        return daoAdministrador.listarJuegosCalificacionAltas();
    }
    
}
