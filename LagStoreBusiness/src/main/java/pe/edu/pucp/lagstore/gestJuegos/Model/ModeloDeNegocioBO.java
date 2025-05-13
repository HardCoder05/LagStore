/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lagstore.gestJuegos.Model;

import java.util.ArrayList;

import pe.edu.pucp.lagstore.gestionjuegos.dao.ModeloNegocioDAO;
import pe.edu.pucp.lagstore.gestionjuegos.mysql.ModeloNegocioMySQL;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;

/**
 *
 * @author rio88
 */
public class ModeloDeNegocioBO {
    private final ModeloNegocioDAO daoModeloNegocio;

    public ModeloDeNegocioBO(){
        daoModeloNegocio = new ModeloNegocioMySQL();
    }

    public int insertar(ModeloNegocio modeloNegocio){
        return daoModeloNegocio.insertar(modeloNegocio);
    }

    public int modificar(ModeloNegocio modeloNegocio){
        return daoModeloNegocio.modificar(modeloNegocio);
    }

    public int eliminar(int idModeloNegocio){
        return daoModeloNegocio.eliminar(idModeloNegocio);
    }

    public ArrayList<ModeloNegocio> listarTodos(){
        return daoModeloNegocio.listarTodos();
    }
}
