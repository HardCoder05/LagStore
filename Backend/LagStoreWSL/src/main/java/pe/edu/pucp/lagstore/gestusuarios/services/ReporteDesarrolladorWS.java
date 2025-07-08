/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */

package pe.edu.pucp.lagstore.gestusuarios.services;

import jakarta.jws.WebService;

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


@WebService(serviceName = "ReporteDesarrolladorWS", 
        targetNamespace = "http://services.pucp.edu.pe")
public class ReporteDesarrolladorWS {

   public byte[] generarReporteDesarrollador(@WebParam(name = "nombre") String nombre,@WebParam(name = "idDesarrollador") int idDesarrollador){
       byte[]reporte=null;
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/lagstore/reportes/Reporte2.jasper"));
            
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            parametros.put("nombre", nombre);
            parametros.put("idDesarrollador", idDesarrollador); 
            //Referenciamos la imagen del logo
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/lagstore/reportes/LogoLagStore.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            
            //Colocamos los parametros
            parametros.put("logo", logo);//el parametro debe estar asi
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
