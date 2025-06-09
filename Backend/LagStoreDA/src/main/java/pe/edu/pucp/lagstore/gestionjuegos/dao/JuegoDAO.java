/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lagstore.gestionjuegos.dao;


import java.util.ArrayList;
import pe.edu.pucp.lagstore.DAO.ICrud;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;

/**
 *
 * @author Luis Rios
 */
public interface JuegoDAO extends ICrud<Juego> {
     ArrayList<Juego> listarJuegosConFiltro(String titulo, Genero genero, ModeloNegocio modelo, Double precioMin, Double precioMax);
}
