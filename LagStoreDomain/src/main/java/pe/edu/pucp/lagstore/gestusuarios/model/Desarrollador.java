package pe.edu.pucp.lagstore.gestusuarios.model;
import java.util.Date;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;

public class Desarrollador extends Usuario {
    private int idDesarrollador;
    private int numeroCuenta;
    private double ingresoTotal;
    
    public Desarrollador(){}
    public Desarrollador(String nombre, String contrasena, 
                   String email, Date fechaRegistro, int telefono, String fotoDePerfil,int activo,Biblioteca biblioteca,int idRol,
                   int numeroCuenta,double ingresoTotal) {
        super(nombre, contrasena, email, fechaRegistro, telefono, fotoDePerfil,activo,biblioteca,idRol);
        this.numeroCuenta = numeroCuenta;
        this.ingresoTotal = ingresoTotal;
    }
    
    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }
    
    public int getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }
    
    @Override
    public String toString() {
        return "Desarrollador: "+ idDesarrollador+" numeroCuenta: " + numeroCuenta+" ingresoTotal: "+ingresoTotal;
    }

}