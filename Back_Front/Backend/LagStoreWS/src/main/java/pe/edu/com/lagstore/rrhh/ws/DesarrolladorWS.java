/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.com.lagstore.rrhh.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;
import pe.edu.pucp.lagstore.gestusuarios.model.DesarrolladorBO;

/**
 *
 * @author Luis Rios
 */
@WebService(serviceName = "DesarrolladorWS")
public class DesarrolladorWS {
    private DesarrolladorBO boDesarrollador;

    @WebMethod(operationName = "insertarDesarrollador")
    public int insertarDesarrollador(@WebParam(name = "desarrollador") Desarrollador desarrollador) {
        boDesarrollador=new DesarrolladorBO();
        return boDesarrollador.insertar(desarrollador);
    }
    
    @WebMethod(operationName = "modificarDesarrollador")
    public int modificarDesarrollador(@WebParam(name = "desarrollador") Desarrollador desarrollador) {
        boDesarrollador=new DesarrolladorBO();
        return boDesarrollador.modificar(desarrollador);
    }
    
    @WebMethod(operationName = "eliminarDesarrollador")
    public int eliminarDesarrollador(@WebParam(name = "idDesarrollador") int idDesarrollador) {
        boDesarrollador=new DesarrolladorBO();
        return boDesarrollador.eliminar(idDesarrollador);
    }
    
    @WebMethod(operationName = "listarTodosDesarrolladores")
    public ArrayList<Desarrollador> listarTodosDesarrolladores() {
        ArrayList<Desarrollador> desarrolladores = null;
        try{
            boDesarrollador = new DesarrolladorBO();
            desarrolladores = boDesarrollador.listarDesarrolladores();
            System.out.println(desarrolladores.get(0).getEmail());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return desarrolladores;
    }
    
    @WebMethod(operationName = "obtenerDesarrolladorPorID")
    public Desarrollador obtenerDesarrolladorPorID(@WebParam(name = "idDesarrollador")int idDesarrollador) {
        boDesarrollador=new DesarrolladorBO();
        return boDesarrollador.obtenerPorId(idDesarrollador);
    }
    
}
