/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.valoracion.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.valoracion.dao.CalificacionDAO;
import pe.edu.pucp.lagstore.valoracion.mysql.CalificacionMySQL;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;
public class CalificacionBO {
    private final CalificacionDAO daoCalificacion;
    public CalificacionBO(){
        daoCalificacion = new CalificacionMySQL();
    }
     public int insertar(Calificacion calificacion){
        return daoCalificacion.insertar(calificacion);
    }
     public int modificar(Calificacion calificacion){
        return daoCalificacion.modificar(calificacion);
    }
    public int eliminar(int idCalificacion){
        return daoCalificacion.eliminar(idCalificacion);
    }
        public ArrayList<Calificacion> listarBibliotecas(){
        return daoCalificacion.listarTodas();
    }
    public Calificacion obtenerPorId(int idCalificacion){
        return daoCalificacion.obtenerPorId(idCalificacion);
    }
}
