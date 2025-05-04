package pe.edu.pucp.lagstore.compra.model;

import java.util.ArrayList;

public class Cartera {
    private int idCartera;
    private double saldoActual;
    private ArrayList<Recarga> historial;

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
}