
package pe.edu.pucp.lagstore.valoracion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;
import pe.edu.pucp.lagstore.valoracion.model.CalificacionBO;

/**
 *
 * @author jeanp
 */
@WebService(serviceName = "CalificacionWS", targetNamespace = "http://services.pucp.edu.pe")
public class CalificacionWS {
    private CalificacionBO calificacionBO;
    
    @WebMethod(operationName = "insertarCalificacion")
    public int insertarCalificacion(@WebParam(name = "calificacion") Calificacion calificacion) {
        calificacionBO = new CalificacionBO();
        return calificacionBO.insertar(calificacion);
    }
    
    @WebMethod(operationName = "modificarCalificacion")
    public int modificarCalificacion(@WebParam(name = "calificacion") Calificacion calificacion) {
        calificacionBO = new CalificacionBO();
        return calificacionBO.modificar(calificacion);
    }
    
    @WebMethod(operationName = "eliminarCalificacion")
    public int eliminarCalificacion(@WebParam(name = "idCalificacion") int idCalificacion) {
        calificacionBO = new CalificacionBO();
        return calificacionBO.eliminar(idCalificacion);
    }
    
    @WebMethod(operationName = "obtenerCalificacionPorId")
    public Calificacion obtenerCalificacionPorId(@WebParam(name = "idCalificacion") int idCalificacion) {
        calificacionBO = new CalificacionBO();
        return calificacionBO.obtenerPorId(idCalificacion);
    }
    
    
}
