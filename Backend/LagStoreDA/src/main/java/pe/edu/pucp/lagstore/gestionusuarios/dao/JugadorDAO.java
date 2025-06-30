package pe.edu.pucp.lagstore.gestionusuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.DAO.ICrud;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;


public interface JugadorDAO extends ICrud<Jugador>{
    ArrayList<Jugador> listarPorNombreONickname(String Nombre);
    public int modificarJugadorDesdeAdministrador(Jugador jugador);
}
