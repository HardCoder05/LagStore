/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.com.lagstore.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoBO;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;


@WebService(serviceName = "JuegoWS")
public class JuegoWS {
    private JuegoBO juegoBO;
    
    @WebMethod(operationName = "listarJuegos")
    public ArrayList<Juego> listarJuegos() {
        ArrayList<Juego> juegos = null;
        
        try{
            juegoBO = new JuegoBO();
            juegos = juegoBO.listarTodos();
            System.out.println(juegos.get(0).getTitulo());
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
}