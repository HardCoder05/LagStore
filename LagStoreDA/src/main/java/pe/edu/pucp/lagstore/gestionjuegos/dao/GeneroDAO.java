/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lagstore.gestionjuegos.dao;

import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestjuegos.model.Genero;

/**
 *
 * @author Luis Rios
 */
public interface GeneroDAO {
    int insertar(Genero genero);
    int modificar(Genero genero);
    int eliminar(int idGenero);
    ArrayList<Genero> listarTodos();
}
