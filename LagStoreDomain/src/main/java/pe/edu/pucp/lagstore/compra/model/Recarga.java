package pe.edu.pucp.lagstore.compra.model;

import java.util.Date;

public class Recarga {
    private int idRecarga;
    private Date fechaRecarga;
    private double monto;
    private MetodoPago metodoPago;
    
    public int getIdRecarga() {
        return idRecarga;
    }

    public void setIdRecarga(int idRecarga) {
        this.idRecarga = idRecarga;
    }

    public Date getFechaRecarga() {
        return fechaRecarga;
    }

    public void setFechaRecarga(Date fechaRecarga) {
        this.fechaRecarga = fechaRecarga;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}