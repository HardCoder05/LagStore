
package pe.edu.pucp.lagstore.compra.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.CarteraDAO;
import pe.edu.pucp.lagstore.compra.mysql.CarteraMySQL;

/**
 *
 * @author JeanPool
 */
public class CarteraBO {
    private final CarteraDAO daoCartera;
    public CarteraBO(){
        daoCartera = new CarteraMySQL();
    }
    public int insertar(Cartera cartera){
        return daoCartera.insertar(cartera);
    } 
    public int modificar(Cartera cartera){
        return daoCartera.modificar(cartera);
    } 
    public int eliminar( int idCartera){
        return daoCartera.eliminar(idCartera);
    }  
    public ArrayList<Cartera>listaCartera(){
        return daoCartera.listarTodas();
    }
}
