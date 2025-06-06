/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.lagstore.gestusuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;
import pe.edu.pucp.lagstore.gestusuarios.model.JugadorBO;


@WebService(serviceName = "JugadorWS",
    targetNamespace = "http://services.pucp.edu.pe")
public class JugadorWS {
    private JugadorBO boJugador;

    @WebMethod(operationName = "insertarJugador")
    public int insertarJugador(@WebParam(name = "jugador") Jugador jugador) {
        boJugador=new JugadorBO();
        return boJugador.insertar(jugador);
    }
    
    @WebMethod(operationName = "modificarJugador")
    public int modificarJugador(@WebParam(name = "jugador") Jugador jugador) {
        boJugador=new JugadorBO();
        return boJugador.modificar(jugador);
    }
    
    @WebMethod(operationName = "eliminarJugador")
    public int eliminarJugador(@WebParam(name = "idJugador") int idJugador) {
        boJugador=new JugadorBO();
        return boJugador.eliminar(idJugador);
    }
    
    @WebMethod(operationName = "listarTodosJugadores")
    public ArrayList<Jugador> listarTodosJugadores() {
        ArrayList<Jugador> jugadores = null;
        try{
            boJugador = new JugadorBO();
            jugadores = boJugador.listarJugadores();
            System.out.println(jugadores.get(0).getEmail());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return jugadores;
    }
    
    @WebMethod(operationName = "obtenerJugadorPorID")
    public Jugador obtenerJugadorPorID(@WebParam(name = "idJugador")int idJugador) {
        boJugador=new JugadorBO();
        return boJugador.obtenerPorId(idJugador);
    }
    
}
