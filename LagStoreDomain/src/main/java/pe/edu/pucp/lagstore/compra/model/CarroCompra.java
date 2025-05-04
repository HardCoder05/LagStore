package pe.edu.pucp.lagstore.compra.model;

import  pe.edu.pucp.lagstore.gestjuegos.model.Juego;

import java.util.List;

public class CarroCompra {
    private int idCarroCompra;
    private List<Juego> juegos;
    private double totalEstimado;

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
}