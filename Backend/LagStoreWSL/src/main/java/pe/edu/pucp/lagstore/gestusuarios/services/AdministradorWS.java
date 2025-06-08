
package pe.edu.pucp.lagstore.gestusuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;
import pe.edu.pucp.lagstore.gestusuarios.model.AdministradorBO;

/**
 *
 * @author rio88
 */
@WebService(serviceName = "AdministradorWS",
    targetNamespace = "http://services.pucp.edu.pe")
public class AdministradorWS {
    private AdministradorBO boAdministrador;
    
    @WebMethod(operationName = "insertarAdministrador")
    public int insertarAdministrador(@WebParam(name = "administrador") 
        Administrador administrador) {
        boAdministrador=new AdministradorBO();
        return boAdministrador.insertar(administrador);
    }
    
    @WebMethod(operationName = "modificarAdministrador")
    public int modificarAdministrador(@WebParam(name = "administrador") 
        Administrador administrador) {
        boAdministrador=new AdministradorBO();
        return boAdministrador.modificar(administrador);
    }
    
    @WebMethod(operationName = "eliminarAdministrador")
    public int eliminarAdministrador(@WebParam(name = "idAdministrador") 
        int idAdministrador) {
        boAdministrador=new AdministradorBO();
        return boAdministrador.eliminar(idAdministrador);
    }
    
    @WebMethod(operationName = "listarTodosAdministradores")
    public ArrayList<Administrador> listarTodosAdministradores() {
        ArrayList<Administrador> administradores = null;
        try{
            boAdministrador = new AdministradorBO();
            administradores = boAdministrador.listarAdministradores();
            System.out.println(administradores.get(0).getEmail());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return administradores;
    }
    
    @WebMethod(operationName = "obtenerAdministradorPorID")
    public Administrador obtenerAdministradorPorID(@WebParam(name = 
        "idAdministrador")int idAdministrador) {
        boAdministrador=new AdministradorBO();
        return boAdministrador.obtenerPorId(idAdministrador);
    }
    
}
