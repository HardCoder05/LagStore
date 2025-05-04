package pe.edu.pucp.lagstore.gestusuarios.model;

import java.util.Date;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;


public class Administrador extends Usuario {
    private int idAdministrador;
    private String rolAdministrativo;
    
    public Administrador(){}

    public Administrador(String nombre, String contrasena,String email, Date fechaRegistro, int telefono, String fotoDePerfil,int activo,Biblioteca biblioteca,int idRol,String rolAdministrativo) {
        super(nombre, contrasena, email, fechaRegistro, telefono, fotoDePerfil,activo,biblioteca,idRol);
        this.rolAdministrativo = rolAdministrativo;
        
    }
    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getRolAdministrativo() {
        return rolAdministrativo;
    }

    public void setRolAdministrativo(String rolAdministrativo) {
        this.rolAdministrativo = rolAdministrativo;
    }

    @Override
    public String toString() {
        return "Administrador: "+ idAdministrador+" rol: " + rolAdministrativo;
    }
}