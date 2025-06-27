package pe.edu.pucp.lagstore.compra.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

public class Cartera {
    private int idCartera;
    private double saldoActual;
    private ArrayList<Recarga> historial;
    private int activo;
    private Jugador jugador;
    
    public Cartera(){
        activo=1;
    }

    public int getIdCartera() {
        return idCartera;
    }
    
    public void setIdCartera(int idCartera) {
        this.idCartera = idCartera;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public ArrayList<Recarga> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Recarga> historial) {
        this.historial = historial;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public Jugador getJugador() {
        return jugador;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}