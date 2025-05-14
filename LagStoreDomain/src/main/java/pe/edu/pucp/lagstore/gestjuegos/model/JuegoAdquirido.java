package pe.edu.pucp.lagstore.gestjuegos.model;

import java.util.Date;

public class JuegoAdquirido {
    private Biblioteca biblioteca;
    private Juego juego;
    private Date fechaAdquisicion;
    private Date ultimaSesion;
    private double tiempoJuego;
    private boolean actualizado;
    private int activo;

    public JuegoAdquirido() {
        this.biblioteca = new Biblioteca();
        this.juego = new Juego();
        this.fechaAdquisicion = new Date();
        this.ultimaSesion = new Date();
        this.tiempoJuego = 0;
        this.actualizado = false;
        this.activo = 1;
    }

    public JuegoAdquirido(Biblioteca biblioteca, Juego juego, Date fechaAdquisicion, Date ultimaSesion,
                          double tiempoJuego, boolean actualizado) {
        this.biblioteca = biblioteca;
        this.juego = juego;
        this.fechaAdquisicion = fechaAdquisicion;
        this.ultimaSesion = ultimaSesion;
        this.tiempoJuego = tiempoJuego;
        this.actualizado = actualizado;
        this.activo = 1;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Date getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public double getTiempoJuego() {
        return tiempoJuego;
    }

    public void setTiempoJuego(double tiempoJuego) {
        this.tiempoJuego = tiempoJuego;
    }

    public boolean isActualizado() {
        return actualizado;
    }

    public void setActualizado(boolean actualizado) {
        this.actualizado = actualizado;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}