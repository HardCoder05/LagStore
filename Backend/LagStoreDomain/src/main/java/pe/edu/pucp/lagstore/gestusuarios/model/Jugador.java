package pe.edu.pucp.lagstore.gestusuarios.model;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;
import  pe.edu.pucp.lagstore.valoracion.model.Resena;
import java.util.ArrayList;
import java.util.Date;

public class Jugador extends Usuario {
    private int idJugador;
    private String nickname;
    private CarroCompra carroCompra;
    private Cartera cartera;
    private ArrayList<Resena> resenas;
    private ArrayList<Calificacion> calificaciones;
    
    public Jugador(){}

   
    
    public Jugador(String nombre,String email,String contrasena,Date fechaRegistro,String telefono, 
                   String fotoDePerfil,String nickname) {
        super(nombre, email,contrasena, fechaRegistro, telefono, fotoDePerfil);
        this.nickname = nickname;
        
    }
    
    
    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public CarroCompra getCarroCompra() {
        return carroCompra;
    }

    public void setCarroCompra(CarroCompra carroCompra) {
        this.carroCompra = carroCompra;
    }

    public Cartera getCartera() {
        return cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }

    public ArrayList<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(ArrayList<Resena> resenas) {
        this.resenas = resenas;
    }

    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    @Override
    public String toString() {
        return "Jugador: "+ idJugador+" nickanme: " + nickname;
    }
    
    
}