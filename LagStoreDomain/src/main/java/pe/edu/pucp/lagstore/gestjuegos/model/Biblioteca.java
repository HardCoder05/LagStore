package pe.edu.pucp.lagstore.gestjuegos.model;

import java.util.List;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;

public class Biblioteca {
    private int idBiblioteca;
    //private int numeroCuenta;
    private double ingresoTotal;
    private int cantidadDeJuegos;
    private Usuario usuario;
    private List<JuegoAdquirido> juegos;
    private int activo;
    
    public Biblioteca(){
    }
    
    public Biblioteca(double ingresoTotal, int cantidadDeJuegos) {
        this.ingresoTotal = ingresoTotal;
        this.cantidadDeJuegos = cantidadDeJuegos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    /*public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }*/

    public double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public int getCantidadDeJuegos() {
        return cantidadDeJuegos;
    }

    public void setCantidadDeJuegos(int cantidadDeJuegos) {
        this.cantidadDeJuegos = cantidadDeJuegos;
    }

    public List<JuegoAdquirido> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<JuegoAdquirido> juegos) {
        this.juegos = juegos;
        this.cantidadDeJuegos = juegos.size();
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "idBiblioteca=" + idBiblioteca + ", ingresoTotal=" + ingresoTotal + ", cantidadDeJuegos=" + cantidadDeJuegos;
    }
    
}