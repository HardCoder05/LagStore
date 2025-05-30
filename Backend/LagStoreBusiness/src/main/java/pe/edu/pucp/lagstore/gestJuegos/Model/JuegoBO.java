/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestJuegos.Model;

import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestionjuegos.dao.JuegoDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.JuegoMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;

/**
 *
 * @author rio88
 */
public class JuegoBO {
    private final JuegoDAO daoJuego;
    
    public JuegoBO(){
        daoJuego = new JuegoMySQL();
    }
    
    public int insertar(Juego juego){
        return daoJuego.insertar(juego);
    }

    public int modificar(Juego juego){
        return daoJuego.modificar(juego);
    }

    public int eliminar(int idJuego){
        return daoJuego.eliminar(idJuego);
    }

    public Juego obtenerPorId(int idJuego){
        return daoJuego.obtenerPorId(idJuego);
    }

    public ArrayList<Juego> listarTodos(){
        return daoJuego.listarTodos();
    }
    
}
