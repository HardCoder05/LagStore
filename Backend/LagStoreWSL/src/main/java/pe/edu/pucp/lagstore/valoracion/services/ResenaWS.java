
package pe.edu.pucp.lagstore.valoracion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import pe.edu.pucp.lagstore.valoracion.model.Resena;
import pe.edu.pucp.lagstore.valoracion.model.ResenaBO;
/**
 *
 * @author jeanp
 */
@WebService(serviceName = "ResenaWS", targetNamespace = "http://services.pucp.edu.pe")
public class ResenaWS {

    private ResenaBO resenaBO;
    
    @WebMethod(operationName = "insertarResena")
    public int insertarResena(@WebParam(name = "resena") Resena resena) {
        resenaBO = new ResenaBO();
        return resenaBO.insertar(resena);
    }
    
    @WebMethod(operationName = "modificarResena")
    public int modificarResena(@WebParam(name = "resena") Resena resena) {
        resenaBO = new ResenaBO();
        return resenaBO.modificar(resena);
    }
    
    @WebMethod(operationName = "eliminarResena")
    public int eliminarResena(@WebParam(name = "idResena") int idResena) {
        resenaBO = new ResenaBO();
        return resenaBO.eliminar(idResena);
    }
    
    @WebMethod(operationName = "obtenerResenaPorId")
    public Resena obtenerResenaPorId(@WebParam(name = "idResena") int idResena) {
        resenaBO = new ResenaBO();
        return resenaBO.obtenerPorId(idResena);
    }
    
}
