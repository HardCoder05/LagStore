package pe.edu.pucp.lagstore.valoracion.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.DAO.ICrud;
import pe.edu.pucp.lagstore.valoracion.model.Resena;
public interface ResenaDAO extends ICrud<Resena> {
    ArrayList<Resena> listarPorJuego(int idJuego);
}
