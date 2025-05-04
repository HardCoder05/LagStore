package pe.edu.pucp.lagstore.gestionusuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;


public interface JugadorDAO {
    int insertar(Jugador jugador);
    int modificar(Jugador jugador);
    int eliminar(int idJugador);
    ArrayList<Jugador>listarTodas();
    Jugador obtenerPorId(int id);
}
