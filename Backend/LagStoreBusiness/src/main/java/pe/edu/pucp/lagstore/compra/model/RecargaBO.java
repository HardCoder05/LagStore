
package pe.edu.pucp.lagstore.compra.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.compra.dao.RecargaDAO;
import pe.edu.pucp.lagstore.compra.mysql.RecargaMySQL;

/**
 *
 * @author JeanPool
 */
public class RecargaBO {
    private final RecargaDAO daoRecarga;
    public RecargaBO(){
        daoRecarga = new RecargaMySQL();
    }
    public int insertar(Recarga recarga){
        return daoRecarga.insertar(recarga);
    }
    
    public int modificar(Recarga recarga){
        return daoRecarga.modificar(recarga);
    }
    
    public int eliminar( int idCartera){
        return daoRecarga.eliminar(idCartera);
    }
    
    public ArrayList<Recarga>listaCarroRecarga(){
        return daoRecarga.listarTodas();
    }
    
    public Recarga obtenerPorId(int id){
        return daoRecarga.obtenerPorId(id);
    }

    public ArrayList<Recarga>listaRecargasAsociadas( int idCartera){
        return daoRecarga.listarAsociadas(idCartera);
    }

}
