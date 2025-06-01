package pe.edu.pucp.lagstore.valoracion.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.valoracion.model.Resena;
public interface ResenaDAO {
    int insertar(Resena resena);
    int modificar(Resena resena);
    int eliminar(int idResena);
    ArrayList<Resena>listarTodas();
    Resena obtenerPorId(int idResena);
}
