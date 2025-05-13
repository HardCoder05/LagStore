/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestJuegos.Model;

import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestionjuegos.dao.GeneroDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.GeneroMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.Genero;

/**
 *
 * @author rio88
 */
public class GeneroBO {
    private final GeneroDAO daoGenero;

    public GeneroBO(){
        daoGenero = new GeneroMySQL();
    }

    public int insertar(Genero genero){
        return daoGenero.insertar(genero);
    }

    public int modificar(Genero genero){
        return daoGenero.modificar(genero);
    }

    public int eliminar(int idGenero){
        return daoGenero.eliminar(idGenero);
    }

    public ArrayList<Genero> listarGeneros(){
        return daoGenero.listarTodos();
    }
}
