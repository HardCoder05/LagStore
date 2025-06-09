/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.lagstore.gestjuegos.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.lagstore.gestJuegos.Model.BibliotecaBO;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;

/**
 *
 * @author jeanp
 */
@WebService(serviceName = "BibliotecaWS", targetNamespace = "http://services.pucp.edu.pe")
public class BibliotecaWS {

    private BibliotecaBO bibliotecBO;
    
    @WebMethod(operationName = "insertarBiblioteca")
    public int insertarBiblioteca(@WebParam(name = "biblioteca") Biblioteca biblioteca) {
        bibliotecBO = new BibliotecaBO();
        return bibliotecBO.insertar(biblioteca);
    }
    @WebMethod(operationName = "modificarBiblioteca")
    public int modificarBiblioteca(@WebParam(name = "biblioteca") Biblioteca biblioteca) {
        bibliotecBO = new BibliotecaBO();
        return bibliotecBO.modificar(biblioteca);
    }
    
    @WebMethod(operationName = "eliminarBiblioteca")
    public int eliminarBiblioteca(@WebParam(name = "idBiblioteca") int idBiblioteca) {
        bibliotecBO = new BibliotecaBO();
        return bibliotecBO.eliminar(idBiblioteca);
    }
    
    @WebMethod(operationName = "obtenerBibliotecaPorId")
    public Biblioteca obtenerBibliotecaPorId(@WebParam(name = "idBiblioteca") int idBiblioteca) {
        bibliotecBO = new BibliotecaBO();
        return bibliotecBO.obtenerPorId(idBiblioteca);
    }
}
