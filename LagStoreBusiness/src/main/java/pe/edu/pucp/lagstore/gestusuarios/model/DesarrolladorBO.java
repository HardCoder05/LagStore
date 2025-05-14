/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestusuarios.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestionusuarios.mysql.DesarrolladorMySQL;

/**
 *
 * @author W10
 */
public class DesarrolladorBO {
    private final DesarrolladorDAO daoDesarrollador;
    
    public DesarrolladorBO(){
        daoDesarrollador = new DesarrolladorMySQL();
    }
    
    public int insertar(Desarrollador desarrollador){
        return daoDesarrollador.insertar(desarrollador);
    }
    
    public int modificar(Desarrollador desarrollador){
        return daoDesarrollador.modificar(desarrollador);
    }

    public int eliminar(int idDesarrollador){
        return daoDesarrollador.eliminar(idDesarrollador);
    }

    public ArrayList<Desarrollador> listarDesarrolladores(){
        return daoDesarrollador.listarTodos();
    }
}
