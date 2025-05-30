package pe.edu.pucp.lagstore.compra.model;

import java.util.Date;

public class Recarga {
    private int idRecarga;
    private Date fechaRecarga;
    private double monto;
    private MetodoPago metodoPago;
    public Cartera cartera;
    
    private int activo;
    
    
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

    /**
     * @return the activo
     */
    public int getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }

    /**
     * @return the cartera
     */
    public Cartera getCartera() {
        return cartera;
    }

    /**
     * @param cartera the cartera to set
     */
    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }
}