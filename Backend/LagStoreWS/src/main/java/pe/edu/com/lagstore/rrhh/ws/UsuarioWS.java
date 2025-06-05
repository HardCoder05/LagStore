/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.com.lagstore.rrhh.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;
import pe.edu.pucp.lagstore.gestusuarios.model.UsuarioBO;

/**
 *
 * @author GUSTAVO
 */
@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {
    private UsuarioBO boUsuario;

        @WebMethod(operationName = "verificaUsuario")
        public int verificaUsuario(Usuario usuario){
            try{
                System.out.println("Email recibido: " + usuario.getEmail());
                System.out.println("Contraseña recibida: " + usuario.getContrasena()); 
                boUsuario = new UsuarioBO();
                return boUsuario.verificar(usuario);
            }catch(Exception e){
                throw new WebServiceException("Error al verificar usuario: " + e.getMessage());
            }
        }
}
