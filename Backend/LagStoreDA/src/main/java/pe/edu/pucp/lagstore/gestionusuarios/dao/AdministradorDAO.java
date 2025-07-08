package pe.edu.pucp.lagstore.gestionusuarios.dao;
import pe.edu.pucp.lagstore.DAO.ICrud;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestjuegos.model.Juego;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;


public interface AdministradorDAO extends ICrud<Administrador>{
    ArrayList<Juego> listarJuegosMasVendidos();
    ArrayList<Juego> listarJuegosCalificacionAltas();
}
