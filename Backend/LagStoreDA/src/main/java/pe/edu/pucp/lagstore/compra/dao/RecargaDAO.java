
package pe.edu.pucp.lagstore.compra.dao;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.model.Recarga;
/**
 *
 * @author Jean Pool
 */
public interface RecargaDAO {
    int insertar(Recarga recarga);
    int modificar(Recarga recarga);
    int eliminar(int idRecarga);
    ArrayList<Recarga>listarTodas();
    Recarga obtenerPorId(int idRecarga);
    ArrayList<Recarga>listarAsociadas(int idCartera);
}
