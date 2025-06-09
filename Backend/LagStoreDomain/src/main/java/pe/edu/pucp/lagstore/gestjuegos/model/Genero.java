/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pe.edu.pucp.lagstore.gestjuegos.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

/**
 *
 * @author rio88
 */

<<<<<<< HEAD
public enum Genero {
    Accion, Rol, Estrategia, 
    Shooter, Simulación, Deportes, 
=======
@XmlType(name = "Genero")
@XmlEnum
public enum Genero {
    Accion, Rol, Estrategia, 
    Shooter, Simulacion, Deportes, 
>>>>>>> main
    Carreras;
}
