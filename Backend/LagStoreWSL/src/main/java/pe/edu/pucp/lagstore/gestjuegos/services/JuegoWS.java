/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */

package pe.edu.pucp.lagstore.gestjuegos.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoBO;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;


@WebService(serviceName = "JuegoWS")//Se declara un servicio
public class JuegoWS {//Dentro se ponen los metodos
    private JuegoBO juegoBO;
    
    @WebMethod(operationName = "listarJuegos")//se declara un metodo
    public ArrayList<Juego> listarJuegos() {
        ArrayList<Juego> juegos = null;
        
        try{
            juegoBO = new JuegoBO();
            juegos = juegoBO.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return juegos;
    }
    
    @WebMethod(operationName = "insertarJuego")
    public int insertarJuego(@WebParam(name = "juego") Juego juego) {
        int resultado = 0;
        
        try{
            juegoBO = new JuegoBO();
            resultado = juegoBO.insertar(juego);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }
    
    @WebMethod(operationName = "obtenerJuegoPorId")
    public Juego obtenerJuegoPorId(@WebParam(name = "idJuego") int idJuego) {
        Juego juego = null;
        
        try{
            juegoBO = new JuegoBO();
            juego = juegoBO.obtenerPorId(idJuego);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return juego;
    }
    
    @WebMethod(operationName = "listarJuegosConFiltro")
    public ArrayList<Juego> listarJuegosConFiltro(
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "genero") Genero genero,
            @WebParam(name = "modeloNegocio") ModeloNegocio modelo,
            @WebParam(name = "precioMin") Double precioMin,
            @WebParam(name = "precioMax") Double precioMax) {
        ArrayList<Juego> juegos = new ArrayList<>();
        
        try {
            juegoBO = new JuegoBO();
            
            juegos = juegoBO.listarJuegosConFiltro(titulo, genero, 
                modelo, precioMin, precioMax);
        } catch (Exception ex) {
            System.out.println("Error en listarJuegosConFiltro (SOAP): " + ex.getMessage());
        }
        
        return juegos;
    }
}
