/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestJuegos.Model;

import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.BibliotecaMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;

/**
 *
 * @author rio88
 */
public class BibliotecaBO {
    private final BibliotecaDAO daoBiblioteca;
    
    public BibliotecaBO(){
        daoBiblioteca = new BibliotecaMySQL();
    }
    
    public int insertar(Biblioteca biblioteca){
        return daoBiblioteca.insertar(biblioteca);
    }
    
    public int modificar(Biblioteca biblioteca){
        return daoBiblioteca.modificar(biblioteca);
    }

    public int eliminar(int idBiblioteca){
        return daoBiblioteca.eliminar(idBiblioteca);
    }

    public ArrayList<Biblioteca> listarBibliotecas(){
        return daoBiblioteca.listarTodos();
    }
    
    public Biblioteca obtenerPorId(int idBiblioteca){
        return daoBiblioteca.obtenerPorId(idBiblioteca);
    }
    
    public Biblioteca obtenerBibliotecaPorUsuario(int idUsuario){
        return daoBiblioteca.obtenerBibliotecaPorUsuario(idUsuario);
    }
}
