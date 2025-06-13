/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.lagstore.gestusuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.lagstore.gestusuarios.model.Rol;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;
import pe.edu.pucp.lagstore.gestusuarios.model.UsuarioBO;

@WebService(serviceName = "UsuarioWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class UsuarioWS {
    private UsuarioBO boUsuario;
    
    @WebMethod(operationName = "verificaUsuario")
    public int verificaUsuario(@WebParam(name = "usuario") Usuario usuario){
        int resultado = 0;
        
        try{ 
            boUsuario = new UsuarioBO();
            resultado = boUsuario.verificar(usuario);
        }catch(Exception e){
            System.out.println(e.getMessage());            
        }
        
        return resultado;
    }
    
    @WebMethod(operationName = "obtenerRol")
    public Rol obtenerRol(@WebParam(name = "idUsuario") int idUsuario){
        Rol resultado = null;
        
        try{ 
            boUsuario = new UsuarioBO();
            resultado = boUsuario.obtenerRol(idUsuario);
        }catch(Exception e){
            System.out.println(e.getMessage());            
        }
        
        return resultado;
    }
    
}
