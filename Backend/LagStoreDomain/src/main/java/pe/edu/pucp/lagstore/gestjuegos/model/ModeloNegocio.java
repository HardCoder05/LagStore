package pe.edu.pucp.lagstore.gestjuegos.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "ModeloNegocio")
@XmlEnum
public enum ModeloNegocio {
    Free_to_play, Paga, 
    Suscripcion;
}