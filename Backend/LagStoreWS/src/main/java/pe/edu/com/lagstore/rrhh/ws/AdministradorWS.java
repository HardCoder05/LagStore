/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.com.lagstore.rrhh.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;
import pe.edu.pucp.lagstore.gestusuarios.model.AdministradorBO;


@WebService(serviceName = "AdministradorWS")
public class AdministradorWS {
    private AdministradorBO boAdministrador;

    @WebMethod(operationName = "insertarAdministrador")
    public int insertarAdministrador(@WebParam(name = "administrador") Administrador administrador) {
        boAdministrador=new AdministradorBO();
        return boAdministrador.insertar(administrador);
    }
    
    @WebMethod(operationName = "modificarAdministrador")
    public int modificarAdministrador(@WebParam(name = "administrador") Administrador administrador) {
        boAdministrador=new AdministradorBO();
        return boAdministrador.modificar(administrador);
    }
    
    @WebMethod(operationName = "eliminarAdministrador")
    public int eliminarAdministrador(@WebParam(name = "idAdministrador") int idAdministrador) {
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
    public Administrador obtenerAdministradorPorID(@WebParam(name = "idAdministrador")int idAdministrador) {
        boAdministrador=new AdministradorBO();
        return boAdministrador.obtenerPorId(idAdministrador);
    }
    
    @WebMethod(operationName = "registroAdministrador")
    public int registroAdministrador(Administrador administrador){
        try{
            boAdministrador = new AdministradorBO();
            return boAdministrador.insertar(administrador);
        }catch(Exception e){
            throw new WebServiceException("Error al insertar administrador: " + e.getMessage());
        }
    }
    
    
    
}
