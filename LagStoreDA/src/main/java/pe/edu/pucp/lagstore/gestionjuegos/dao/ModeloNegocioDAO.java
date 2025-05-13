/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lagstore.gestionjuegos.dao;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestjuegos.model.ModeloNegocio;

/**
 *
 * @author Luis Rios
 */
public interface ModeloNegocioDAO {
    int insertar(ModeloNegocio modeloNegocio);
    int modificar(ModeloNegocio modeloNegocio);
    int eliminar(int idModeloNegocio);
    ArrayList<ModeloNegocio> listarTodos();
}
