/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestJuegos.Model;

import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestionjuegos.dao.JuegoAdquiridoDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.JuegoAdquiridoMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.JuegoAdquirido;

/**
 *
 * @author rio88
 */
public class JuegoAdquiridoBO {
    private final JuegoAdquiridoDAO daoJuegoAdquirido;

    public JuegoAdquiridoBO(){
        daoJuegoAdquirido = new JuegoAdquiridoMySQL();
    }

    public int insertar(JuegoAdquirido juegoAdquirido){
        return daoJuegoAdquirido.insertar(juegoAdquirido);
    }

    public int modificar(JuegoAdquirido juegoAdquirido){
        return daoJuegoAdquirido.modificar(juegoAdquirido);
    }

    public int eliminar(int idJuegoAdquirido){
        return daoJuegoAdquirido.eliminar(idJuegoAdquirido);
    }

    public ArrayList<JuegoAdquirido> listarTodos(){
        return daoJuegoAdquirido.listarTodos();
    }

    public JuegoAdquirido obtenerPorId(int idJuegoAdquirido){
        return daoJuegoAdquirido.obtenerPorId(idJuegoAdquirido);
    }
    
    public ArrayList<JuegoAdquirido> listarJuegosAdquiridosPorBiblioteca(int idBiblioteca){
        return daoJuegoAdquirido.listarJuegosAdquiridosPorBiblioteca(idBiblioteca);
    }
    
    public JuegoAdquirido obtenerJuegoAdquiridoPorBibliotecaYJuego(int 
        idBiblioteca,int idJuego){
        return daoJuegoAdquirido.obtenerJuegoAdquiridoPorBibliotecaYJuego(idBiblioteca, idJuego);
    }

}
