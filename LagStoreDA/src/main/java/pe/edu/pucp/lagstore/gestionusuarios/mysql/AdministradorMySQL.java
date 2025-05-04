package pe.edu.pucp.lagstore.gestionusuarios.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.AdministradorDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;

public class AdministradorMySQL implements AdministradorDAO{

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(Administrador administrador) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO Usuario(nombre,contrasena,email,fechaRegistro,telefono,"
                    + "fotoDePerfil,activo,biblioteca_idBiblioteca,rol_idrol)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, administrador.getNombre());
            pst.setString(2, administrador.getContrasena());
            pst.setString(3, administrador.getEmail());
            pst.setDate(4, new java.sql.Date(administrador.getFechaRegistro().getTime()));
            pst.setInt(5,administrador.getTelefono());
            pst.setString(6, administrador.getFotoDePerfil());
            pst.setInt(7,administrador.getActivo());
            pst.setInt(8,administrador.getBiblioteca().getIdBiblioteca());
            pst.setInt(9,2);
            pst.executeUpdate();
            
            sql = "SELECT @@last_insert_id AS id";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            administrador.setIdUsuario(rs.getInt("id"));
            administrador.setIdAdministrador(rs.getInt("id"));
            sql = "INSERT INTO Administrador(idAdministrador,rol)"
                    + " VALUES(?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, administrador.getIdAdministrador());
            pst.setString(2, administrador.getRolAdministrativo());
            pst.executeUpdate();
            resultado = administrador.getIdUsuario();
            System.out.println("Se ha registrado el administrador...");
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
    public int modificar(Administrador administrador) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE Administrador SET rol = '" + administrador.getRolAdministrativo()+ "' WHERE" + " idAdministrador = " + administrador.getIdAdministrador();
            System.out.println(administrador.getRolAdministrativo()+" "+administrador.getIdAdministrador());
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha actualizado el administrador...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idAdministrador) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE Usuario SET activo = 0 WHERE" + " id = " + idAdministrador;
            //System.out.println(jugador.getNickname()+" "+jugador.getIdJugador());
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
            System.out.println("Se ha actualizado el jugador...");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Administrador> listarTodas() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Administrador";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta imprimiendo los ids");
            while(rs.next()){
                Administrador administrador = new Administrador();
                administrador.setIdAdministrador(rs.getInt("idAdministrador"));
                administrador.setRolAdministrativo(rs.getString("rol"));
                administradores.add(administrador);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return administradores; 
    }

    @Override
    public Administrador obtenerPorId(int id) {
        Administrador administrador = new Administrador();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Administrador WHERE idAdministrador = " + id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta obteniendo id");
            while(rs.next()){
                //Jugador jugador = new Jugador();
                administrador.setIdAdministrador(rs.getInt("idAdministrador"));
                administrador.setRolAdministrativo(rs.getString("rol"));
            }
            System.out.println(administrador);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return administrador; 
    }
    
}
