package pe.edu.pucp.lagstore.gestionusuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;

public interface DesarrolladorDAO {
    int insertar(Desarrollador desarrollador);
    int modificar(Desarrollador desarrollador);
    int eliminar(int idDesarrollador);
    ArrayList<Desarrollador>listarTodas();
    Desarrollador obtenerPorId(int id);
}
