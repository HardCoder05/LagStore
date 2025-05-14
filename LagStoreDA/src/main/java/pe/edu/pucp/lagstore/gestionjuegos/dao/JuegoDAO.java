/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lagstore.gestionjuegos.dao;

import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestjuegos.model.Juego;

/**
 *
 * @author Luis Rios
 */
public interface JuegoDAO {
    int insertar(Juego juego);
    int modificar(Juego juego);
    int eliminar(int idJuego);
    ArrayList<Juego>listarTodos();
    Juego obtenerPorId(int id);
}
