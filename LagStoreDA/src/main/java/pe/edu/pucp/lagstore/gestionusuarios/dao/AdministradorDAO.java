package pe.edu.pucp.lagstore.gestionusuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;


public interface AdministradorDAO {
    int insertar(Administrador administrador);
    int modificar(Administrador administrador);
    int eliminar(int idAdministrador);
    ArrayList<Administrador>listarTodas();
    Administrador obtenerPorId(int id);
}
