package pe.edu.pucp.lagstore.valoracion.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.valoracion.dao.CalificacionDAO;
import pe.edu.pucp.lagstore.valoracion.model.Calificacion;


public class CalificacionMySQL implements CalificacionDAO{
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    @Override
    public int insertar(Calificacion calificacion) {
        int resultado=0;
        try{
            //Establecer una conexion con la BD
            //DESCOMENTAR ESTO PARA PROBAR
            //con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "INSERT INTO calificacion(fechaPuntuacion,puntaje,activo) "
                    +    "VALUES('"+calificacion.getFechaPuntuacion()+"','"+calificacion.getPuntuacion()+"')";
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha insertado una calificacion...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            //Cerramos la conexión.
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public int modificar(Calificacion calificacion) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            //DESCOMENTAR ESTO PARA PROBAR
            //con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE calificacion SET puntuacion = '" +calificacion.getPuntuacion()+ "' WHERE" + " idCalificacion = " + calificacion.getIdCalificacion();
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha actualizado la calificacion...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idCalificacion) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            //DESCOMENTAR ESTO PARA PROBAR
            //con = DBManager.getInstance().getConnection();
            
            //Ejecutamos alguna sentencia SQL
            
            String sql = "UPDATE area SET puntuacion=0, fechaPuntuacion = '0000-00-00' WHERE" + " id_area = " + idCalificacion;
            
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha eliminado una calificacion...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
        
    }

    @Override
    public ArrayList<Calificacion> listarTodas() {
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        try{
            //DESCOMENTAR ESTO PARA PROBAR
            //con = DBManager.getInstance().getConnection();
            String sql = "SELECT id_area, nombre FROM area WHERE" + " activa = 1";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Calificacion calificacion = new Calificacion();
                calificacion.setIdCalificacion(rs.getInt("idCalificacion"));
                calificacion.setFechaPuntuacion(rs.getDate("fechaPuntuacion"));
                calificacion.setPuntuacion(rs.getInt("puntuacion"));
                calificaciones.add(calificacion);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return calificaciones;   
    }
    
}
