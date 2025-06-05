/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.com.lagstore.compras.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.model.Recarga;
import pe.edu.pucp.lagstore.compra.model.RecargaBO;

/**
 *
 * @author jeanp
 */
@WebService(serviceName = "RecargaWS")
public class RecargaWS {

    public RecargaBO recargaBO;
    
    @WebMethod(operationName = "insertarRecarga")
    public int insertarRecarga(@WebParam(name = "recarga") Recarga recarga) {
        recargaBO = new RecargaBO();
        return recargaBO.insertar(recarga);
    }
    
    @WebMethod(operationName = "obtenerRecargaPorId")
    public Recarga obtenerRecargaPorId(@WebParam(name = "idRecarga") int idRecarga) {
        recargaBO = new RecargaBO();
        return recargaBO.obtenerPorId(idRecarga);
    }
    
    @WebMethod(operationName = "listarRecargasAsociadas")
    public ArrayList<Recarga> listarRecargasAsociadas(@WebParam(name = "idCartera") int idCartera) {
        recargaBO = new RecargaBO();
        return recargaBO.listaRecargasAsociadas(idCartera);
    }
    
    
}
