/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lagstore.gestionjuegos.dao;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestjuegos.model.JuegoAdquirido;
/**
 *
 * @author Luis Rios
 */
public interface JuegoAdquiridoDAO {
    int insertar(JuegoAdquirido juegoAdquirido);
    int modificar(JuegoAdquirido juegoAdquirido);
    int eliminar (int idJuegoAdquirido);
    ArrayList<JuegoAdquirido>listarTodos();
    JuegoAdquirido obtenerPorId(int idJuegoAdquirido);
}
