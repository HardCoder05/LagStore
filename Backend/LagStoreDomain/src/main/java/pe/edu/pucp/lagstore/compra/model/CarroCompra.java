package pe.edu.pucp.lagstore.compra.model;

import  pe.edu.pucp.lagstore.gestjuegos.model.Juego;

import java.util.List;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

public class CarroCompra {

    private int idCarroCompra;
    private List<Juego> juegos;
    private double totalEstimado;
    private int activo;
    private Jugador jugador;

    public int getIdCarroCompra(){
        return idCarroCompra;
    }

    public void setIdCarroCompra(int idCarroCompra){
        this.idCarroCompra = idCarroCompra;
    }

    public List<Juego> getJuegos(){
        return juegos;
    }

    public void setJuegos(List<Juego> juegos){
        this.juegos = juegos;
    }

    public double getTotalEstimado(){
        return totalEstimado;
    }

    public void setTotalEstimado(double totalEstimado){
        this.totalEstimado = totalEstimado;
    }
    
    public void setJuegos(Juego juego){
        juegos.add(juego);
    }
    
    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    /**
     * @return the jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * @param jugador the jugador to set
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

}