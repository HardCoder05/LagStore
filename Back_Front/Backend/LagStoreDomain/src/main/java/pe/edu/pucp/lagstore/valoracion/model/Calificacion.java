package pe.edu.pucp.lagstore.valoracion.model;

import java.util.Date;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

/**
 *
 * @author rio88
 */
public class Calificacion {
    private int idCalificacion;
    private Jugador autor;
    private Juego juego;
    private Date fechaPuntuacion;
    private int puntuacion;
    private int activo;
    
    public Calificacion(){};
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

    public Jugador getAutor() {
        return autor;
    }

    public void setAutor(Jugador autor) {
        this.autor = autor;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
