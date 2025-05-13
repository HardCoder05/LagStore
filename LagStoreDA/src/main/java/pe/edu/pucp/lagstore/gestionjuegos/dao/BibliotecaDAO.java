package pe.edu.pucp.lagstore.gestionjuegos.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;


public interface BibliotecaDAO {
    int insertar(Biblioteca biblioteca);
    int modificar(Biblioteca biblioteca);
    int eliminar(int idBiblioteca);
    ArrayList<Biblioteca>listarTodas();
    Biblioteca obtenerPorId(int id);
}
