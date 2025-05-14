package pe.edu.pucp.lagstore.valoracion.dao;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;

public interface CalificacionDAO {
    int insertar(Calificacion calificacion);
    int modificar(Calificacion calificacion);
    int eliminar(int idCalificacion);
    ArrayList<Calificacion>listarTodas();
    Calificacion obtenerPorId(int idCalificacion);
}
