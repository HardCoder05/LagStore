package pe.edu.pucp.lagstore.gestusuarios.model;
import java.util.Date;


public class Desarrollador extends Usuario {
    private int idDesarrollador;
    private String numeroCuenta;
    private double ingresoTotal;
    
    public Desarrollador(){}
    public Desarrollador(String nombre,String email ,String contrasena, 
                         Date fechaRegistro, String telefono, String fotoDePerfil,String numeroCuenta,double ingresoTotal) {
        super(nombre, contrasena, email, fechaRegistro, telefono, fotoDePerfil);
        this.numeroCuenta = numeroCuenta;
        this.ingresoTotal = ingresoTotal;
    }
    
    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
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