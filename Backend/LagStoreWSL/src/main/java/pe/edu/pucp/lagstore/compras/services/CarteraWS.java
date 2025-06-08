/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.lagstore.compras.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.lagstore.compra.model.Cartera;
import pe.edu.pucp.lagstore.compra.model.CarteraBO;

/**
 *
 * @author jeanp
 */
@WebService(serviceName = "CarteraWS",
         targetNamespace = "http://services.pucp.edu.pe")
public class CarteraWS {
    public CarteraBO carteraBO;
    @WebMethod(operationName = "obenerCarteraPorId")
    public Cartera obenerCarteraPorId(@WebParam(name = "idCartera") int idCartera) {
        carteraBO = new CarteraBO();
        return carteraBO.obtenerPorId(idCartera);
    }
}
