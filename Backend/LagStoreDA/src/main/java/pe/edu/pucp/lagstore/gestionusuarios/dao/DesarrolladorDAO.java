package pe.edu.pucp.lagstore.gestionusuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.DAO.ICrud;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;

public interface DesarrolladorDAO extends ICrud<Desarrollador>{
    ArrayList<Desarrollador> listarPorNombre(String Nombre);
}
