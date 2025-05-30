
package pe.edu.pucp.lagstore.compra.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.model.Cartera;
/**
 *
 * @author Jean Pool
 */
public interface CarteraDAO {
    int insertar(Cartera cartera);
    int modificar(Cartera cartera);
    int eliminar(int idCartera);
    ArrayList<Cartera>listarTodas();
    Cartera obtenerPorId(int idCartera);
}
