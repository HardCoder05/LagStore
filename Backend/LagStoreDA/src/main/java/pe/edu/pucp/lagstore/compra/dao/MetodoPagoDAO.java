
package pe.edu.pucp.lagstore.compra.dao;
import pe.edu.pucp.lagstore.compra.model.MetodoPago;
import java.util.ArrayList;
/**
 *
 * @author Jean Pool
 */
public interface MetodoPagoDAO {
    int insertar(MetodoPago metodo);
    int modificar(MetodoPago metodo);
    int eliminar(int metodo);
    ArrayList<MetodoPago>listarTodas();
}
