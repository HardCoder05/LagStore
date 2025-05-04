package pe.edu.pucp.lagstore.gestionusuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestusuarios.model.Usuario;


public interface UsuarioDAO {
    int insertar(Usuario usuario);
    int modificar(Usuario usuario);
    int eliminar(int idUsuario);
    ArrayList<Usuario>listarTodas();
}
