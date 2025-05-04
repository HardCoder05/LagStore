package pe.edu.pucp.lagstore.gestionusuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Rol;


public interface RolDAO {
    int insertar(Rol rol);
    int modificar(Rol rol);
    int eliminar(int rol);
    ArrayList<Rol>listarTodas();
}
