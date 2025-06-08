
package pe.edu.pucp.lagstore.compras.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.lagstore.compra.model.CarroCompra;
import pe.edu.pucp.lagstore.compra.model.CarroCompraBO;

/**
 *
 * @author jeanp
 */
@WebService(serviceName = "CarroCompraWS", targetNamespace = "http://services.pucp.edu.pe")
public class CarroCompraWS {
    private CarroCompraBO carroCompraBO;
    
    
    @WebMethod(operationName = "insertarCarroCompra")
    public int insertarCarroCompra(@WebParam(name = "carro") CarroCompra carro) {
        carroCompraBO = new CarroCompraBO();
        return carroCompraBO.insertar(carro);
    }
    
    @WebMethod(operationName = "modificarCarroCompra")
    public int modificarCarroCompra(@WebParam(name = "carro") CarroCompra carro) {
        carroCompraBO = new CarroCompraBO();
        return carroCompraBO.modificar(carro);
    }
    
    @WebMethod(operationName = "eliminarCarroCompra")
    public int eliminarCarroCompra(@WebParam(name = "idCarro") int idCarro) {
        carroCompraBO = new CarroCompraBO();
        return carroCompraBO.eliminar(idCarro);
    }
}
