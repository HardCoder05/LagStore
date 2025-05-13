package pe.edu.pucp.lagstore.gestionjuegos.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionjuegos.dao.BibliotecaDAO;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;


public class BibliotecaMySQL implements BibliotecaDAO{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    @Override
    public int insertar(Biblioteca biblioteca) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO Biblioteca(ingresoTotal,cantidadDeJuegos,activo) "
                        +"VALUES('"+biblioteca.getIngresoTotal() +"','"+biblioteca.getCantidadDeJuegos()+",'1')";
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            
            sql = "SELECT @@last_insert_id AS id";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            biblioteca.setIdBiblioteca(rs.getInt("id"));
            System.out.println("Se ha registrado en tabla biblioteca...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(Biblioteca biblioteca) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE Biblioteca SET cantidadDeJuegos = '" + biblioteca.getCantidadDeJuegos()+ "' WHERE" + " idBiblioteca = " + biblioteca.getIdBiblioteca();
            System.out.println("Se ha actualizado la biblioteca...");
            System.out.println("Id biblioteca: "+biblioteca.getIdBiblioteca()+ " nueva cantidad: "+biblioteca.getCantidadDeJuegos());
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idBiblioteca) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE Biblioteca SET activo = 0 WHERE idBiblioteca = " + idBiblioteca;
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha eliminado la biblioteca...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Biblioteca> listarTodas() {
        ArrayList<Biblioteca> biblioteca = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Biblioteca WHERE activo = 1";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta imprimiendo los ids");
            while(rs.next()){
                Biblioteca biblio  = new Biblioteca();
                biblio.setIdBiblioteca(rs.getInt("idBiblioteca"));
                biblio.setCantidadDeJuegos(rs.getInt("cantidadDeJuegos"));
                biblio.setIngresoTotal(rs.getDouble("ingresoTotal"));
                biblioteca.add(biblio);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return biblioteca;
    }

    @Override
    public Biblioteca obtenerPorId(int id) {
        Biblioteca biblio = new Biblioteca();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Biblioteca WHERE idBiblioteca = " + id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta obteniendo id");
            while(rs.next()){
                biblio.setIdBiblioteca(rs.getInt("idBiblioteca"));
                biblio.setIngresoTotal(rs.getDouble("ingresoTotal"));
                biblio.setCantidadDeJuegos(rs.getInt("cantidadDeJuegos"));
            }
            System.out.println(biblio);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return biblio;
    }
    
}
