/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lagstore.DAO;

import java.util.ArrayList;

/**
 *
 * @author GUSTAVO
 * @param <T>
 */
public interface ICrud <T> {
    int insertar(T modelo);
    int modificar(T modelo);
    int eliminar(int idModelo);
    ArrayList<T> listarTodos();
    T obtenerPorId(int id);
}
