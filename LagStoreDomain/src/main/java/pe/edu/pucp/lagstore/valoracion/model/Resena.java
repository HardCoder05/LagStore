package pe.edu.pucp.lagstore.valoracion.model;

import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import  pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

import java.util.Date;

public class Resena {
    private int idResena;
    private Calificacion calificacion;
    private String comentario;
    private Date fechaPublicacion;
    private Jugador autor;
    private Juego juego;
    private int activo;
    public Resena(){};
    public int getIdResena() {
        return idResena;
    }

    public void setIdResena(int idResena) {
        this.idResena = idResena;
    }

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }


    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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