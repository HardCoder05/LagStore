
package pe.edu.pucp.lagstore.compra.dao;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import java.util.ArrayList;

/**
 *
 * @author Jean Pool
 */
public interface CarroCompraDAO {
    int insertar(CarroCompra carroCompra);
    int modificar(CarroCompra carroCompra);
    int eliminar(int idCarroCompra);
    ArrayList<CarroCompra>listarTodas();
    CarroCompra obtenerPorId(int idCarroCompra);
    
}
