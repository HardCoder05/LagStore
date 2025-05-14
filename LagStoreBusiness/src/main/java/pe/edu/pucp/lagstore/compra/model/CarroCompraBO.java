
package pe.edu.pucp.lagstore.compra.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.CarroCompraDAO;
import pe.edu.pucp.lagstore.compra.mysql.CarroCompraMySQL;

/**
 *
 * @author JeanPool
 */
public class CarroCompraBO {
    private final CarroCompraDAO daoCarroCompra;
    
    public CarroCompraBO(){
        daoCarroCompra = new CarroCompraMySQL();
    }
    public int insertar(CarroCompra carro){
        return daoCarroCompra.insertar(carro);
    }
    
    public int modificar(CarroCompra carro){
        return daoCarroCompra.modificar(carro);
    }
    
    public int eliminar( int idCarroCompra){
        return daoCarroCompra.eliminar(idCarroCompra);
    }
    
    public ArrayList<CarroCompra>listaCarroCompra(){
        return daoCarroCompra.listarTodas();
    }
}
