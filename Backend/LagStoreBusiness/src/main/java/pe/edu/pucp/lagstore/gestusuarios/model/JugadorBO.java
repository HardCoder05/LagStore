/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestusuarios.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.JugadorMySQL;

/**
 *
 * @author W10
 */
public class JugadorBO {
    private final JugadorDAO daoJugador;
    
    public JugadorBO(){
        daoJugador = new JugadorMySQL();
    }
    
    public int insertar(Jugador jugador){
        return daoJugador.insertar(jugador);
    }
    
    public int modificar(Jugador jugador){
        return daoJugador.modificar(jugador);
    }

    public int eliminar(int idJugador){
        return daoJugador.eliminar(idJugador);
    }

    public ArrayList<Jugador> listarJugadores(){
        return daoJugador.listarTodos();
    }
    
    public Jugador obtenerPorId(int idJugador){
        return daoJugador.obtenerPorId(idJugador);
    }
}
