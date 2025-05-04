package pe.edu.pucp.lagstore.valoracion.model;

import java.util.Date;

/**
 *
 * @author rio88
 */
public class Calificacion {
    private int idCalificacion;
    private Date fechaPuntuacion;
    private int puntuacion;
    
    public int getIdCalificacion() {
        return idCalificacion;
    }
    
    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }
    
    public Date getFechaPuntuacion() {
        return fechaPuntuacion;
    }
    
    public void setFechaPuntuacion(Date fechaPuntuacion) {
        this.fechaPuntuacion = fechaPuntuacion;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
    
    
    
}
