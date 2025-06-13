
package pe.edu.pucp.lagstore.gestjuegos.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.gestJuegos.Model.JuegoAdquiridoBO;
import pe.edu.pucp.lagstore.gestjuegos.model.JuegoAdquirido;

/**
 *
 * @author jeanp
 */
@WebService(serviceName = "JuegoAdquiridoWS", targetNamespace = "http://services.pucp.edu.pe")
public class JuegoAdquiridoWS {

    private JuegoAdquiridoBO juegoAdquiridoBO;
    
    @WebMethod(operationName = "insertarJuegoAdquirido")
    public int insertarJuegoAdquirido(@WebParam(name = "juegoAdquirido") JuegoAdquirido juegoAdquirido) {
        juegoAdquiridoBO=new JuegoAdquiridoBO();
        return juegoAdquiridoBO.insertar(juegoAdquirido);
    }
    
    @WebMethod(operationName = "modificarJuegoAdquirido")
    public int modificarJuegoAdquirido(@WebParam(name = "juegoAdquirido") JuegoAdquirido juegoAdquirido) {
        juegoAdquiridoBO=new JuegoAdquiridoBO();
        return juegoAdquiridoBO.modificar(juegoAdquirido);
    }
    
    @WebMethod(operationName = "eliminarJuegoAdquirido")
    public int eliminarJuegoAdquirido(@WebParam(name = "idJuegoAdquirido") int idJuegoAdquirido) {
        juegoAdquiridoBO=new JuegoAdquiridoBO();
        return juegoAdquiridoBO.eliminar(idJuegoAdquirido);
    }
    
    @WebMethod(operationName = "obtenerJuegoAdquiridoPorId")
    public JuegoAdquirido obtenerJuegoAdquiridoPorId(@WebParam(name = "idJuegoAdquirido") int idJuegoAdquirido) {
        juegoAdquiridoBO=new JuegoAdquiridoBO();
        return juegoAdquiridoBO.obtenerPorId(idJuegoAdquirido);
    }
    
    @WebMethod(operationName = "listarJuegosAdquiridosPorBiblioteca")
    public ArrayList<JuegoAdquirido> listarJuegosAdquiridosPorBiblioteca
        (@WebParam(name = "idBiblioteca") int idBiblioteca) {
        juegoAdquiridoBO = new JuegoAdquiridoBO();
        return juegoAdquiridoBO.listarJuegosAdquiridosPorBiblioteca(idBiblioteca);
    }
    
    @WebMethod(operationName = "obtenerJuegoAdquiridoPorBibliotecaYJuego")
    public JuegoAdquirido obtenerJuegoAdquiridoPorBibliotecaYJuego
        (@WebParam(name = "idBiblioteca") int idBiblioteca,
        @WebParam(name = "idJuego") int idJuego) {
        juegoAdquiridoBO = new JuegoAdquiridoBO();
        return juegoAdquiridoBO.obtenerJuegoAdquiridoPorBibliotecaYJuego(idBiblioteca, idJuego);
    }
}
