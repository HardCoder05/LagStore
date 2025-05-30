
package pe.edu.pucp.lagstore.valoracion.model;

import java.util.ArrayList;
import pe.edu.pucp.lagstore.valoracion.dao.ResenaDAO;
import pe.edu.pucp.lagstore.valoracion.mysql.ResenaMySQL;
import pe.edu.pucp.lagstore.valoracion.model.Resena;
/**
 *
 * @author W10
 */
public class ResenaBO {
    private final ResenaDAO daoResena;
    public ResenaBO(){
        daoResena = new ResenaMySQL();
    }
    public int insertar(Resena resena){
        return daoResena.insertar(resena);
    }
     public int modificar(Resena resena){
        return daoResena.modificar(resena);
    }
    public int eliminar(int idResena){
        return daoResena.eliminar(idResena);
    }
        public ArrayList<Resena> listarBibliotecas(){
        return daoResena.listarTodas();
    }
    public Resena obtenerPorId(int idResena){
        return daoResena.obtenerPorId(idResena);
    }
}
