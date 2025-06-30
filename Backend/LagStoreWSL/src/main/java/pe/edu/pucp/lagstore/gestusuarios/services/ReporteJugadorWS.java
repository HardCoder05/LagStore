package pe.edu.pucp.lagstore.gestusuarios.services;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.lagstore.config.DBManager;


@WebService(serviceName = "ReporteJugadorWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class ReporteJugadorWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "generarReporteEmpleado")
    public byte[] generarReporteEmpleado(@WebParam(name = "id") int id) {
        byte[]reporte=null;
        
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/lagstore/reportes/Reporte1.jasper"));
            
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            parametros.put("id", id);
            
            //Referenciamos la imagen del logo
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/lagstore/reportes/LogoLagStore.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            
            //Colocamos los parametros
            parametros.put("logo", logo);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //ya no se muestra en pantalla sino se exporta a memoria
            reporte = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
}
