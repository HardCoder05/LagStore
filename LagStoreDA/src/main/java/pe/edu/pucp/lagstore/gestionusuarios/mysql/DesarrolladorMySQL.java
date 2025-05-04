package pe.edu.pucp.lagstore.gestionusuarios.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.DesarrolladorDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;


public class DesarrolladorMySQL implements DesarrolladorDAO{
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(Desarrollador desarrollador) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO Usuario(nombre,contrasena,email,fechaRegistro,telefono,"
                    + "fotoDePerfil,activo,biblioteca_idBiblioteca,rol_idrol)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, desarrollador.getNombre());
            pst.setString(2, desarrollador.getContrasena());
            pst.setString(3, desarrollador.getEmail());
            pst.setDate(4, new java.sql.Date(desarrollador.getFechaRegistro().getTime()));
            pst.setInt(5,desarrollador.getTelefono());
            pst.setString(6, desarrollador.getFotoDePerfil());
            pst.setInt(7,desarrollador.getActivo());
            pst.setInt(8,desarrollador.getBiblioteca().getIdBiblioteca());
            pst.setInt(9,3);
            pst.executeUpdate();
            
            sql = "SELECT @@last_insert_id AS id";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            desarrollador.setIdUsuario(rs.getInt("id"));
            desarrollador.setIdDesarrollador(rs.getInt("id"));
            sql = "INSERT INTO Desarrollador(idDesarrollador,numeroCuenta,ingresoTotal)"
                    + " VALUES(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, desarrollador.getIdUsuario());
            pst.setInt(2, desarrollador.getNumeroCuenta());
            pst.setDouble(3, desarrollador.getIngresoTotal());
            pst.executeUpdate();
            resultado = desarrollador.getIdUsuario();
            System.out.println("Se ha registrado el desarrollador...");
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
    public int modificar(Desarrollador desarrollador) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE Desarrollador SET ingresoTotal = '" + desarrollador.getIngresoTotal()+ "' WHERE" + " idDesarrollador = " + desarrollador.getIdDesarrollador();
            System.out.println(desarrollador.getIngresoTotal()+" "+desarrollador.getIdDesarrollador());
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha actualizado el desarrollador...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idDesarrollador) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE Usuario SET activo = 0 WHERE" + " id = " + idDesarrollador;
            //System.out.println(jugador.getNickname()+" "+jugador.getIdJugador());
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha actualizado el desarrollador...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Desarrollador> listarTodas() {
        ArrayList<Desarrollador> desarrolladores = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Desarrollador";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta imprimiendo los ids");
            while(rs.next()){
                Desarrollador desarrollador = new Desarrollador();
                desarrollador.setIdDesarrollador(rs.getInt("idDesarrollador"));
                desarrollador.setNumeroCuenta(rs.getInt("numeroCuenta"));
                desarrollador.setIngresoTotal(rs.getDouble("ingresoTotal"));
                desarrolladores.add(desarrollador);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return desarrolladores; 
    }

    @Override
    public Desarrollador obtenerPorId(int id) {
        Desarrollador desarrollador = new Desarrollador();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Desarrollador WHERE idDesarrollador = " + id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta obteniendo id");
            while(rs.next()){
                desarrollador.setIdDesarrollador(rs.getInt("idDesarrollador"));
                desarrollador.setNumeroCuenta(rs.getInt("numeroCuenta"));
                desarrollador.setIngresoTotal(rs.getDouble("ingresoTotal"));
            }
            System.out.println(desarrollador);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return desarrollador;
    }
    
}
