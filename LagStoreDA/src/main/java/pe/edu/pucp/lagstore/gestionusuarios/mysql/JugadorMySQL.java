package pe.edu.pucp.lagstore.gestionusuarios.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.JugadorDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Jugador;

public class JugadorMySQL implements JugadorDAO{
    
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(Jugador jugador) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO Usuario(nombre,contrasena,email,fechaRegistro,telefono,"
                    + "fotoDePerfil,activo,biblioteca_idBiblioteca,rol_idrol)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, jugador.getNombre());
            pst.setString(2, jugador.getContrasena());
            pst.setString(3, jugador.getEmail());
            pst.setDate(4, new java.sql.Date(jugador.getFechaRegistro().getTime()));
            pst.setInt(5,jugador.getTelefono());
            pst.setString(6, jugador.getFotoDePerfil());
            pst.setInt(7,jugador.getActivo());
            pst.setInt(8,jugador.getBiblioteca().getIdBiblioteca());
            pst.setInt(9,1);
            pst.executeUpdate();
            
            sql = "SELECT @@last_insert_id AS id";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            jugador.setIdUsuario(rs.getInt("id"));
            jugador.setIdJugador(rs.getInt("id"));
            sql = "INSERT INTO Jugador(idJugador,nickname)"
                    + " VALUES(?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, jugador.getIdUsuario());
            pst.setString(2, jugador.getNickname());
            pst.executeUpdate();
            resultado = jugador.getIdUsuario();
            System.out.println("Se ha registrado el jugador...");
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
    public int modificar(Jugador jugador) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE Jugador SET nickname = '" + jugador.getNickname()+ "' WHERE" + " idJugador = " + jugador.getIdJugador();
            System.out.println(jugador.getNickname()+" "+jugador.getIdJugador());
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
    public int eliminar(int idJugador) {
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos alguna sentencia SQL
            String sql = "UPDATE Usuario SET activo = 0 WHERE" + " id = " + idJugador;
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
    public ArrayList<Jugador> listarTodas() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Jugador";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta imprimiendo los ids");
            while(rs.next()){
                Jugador jugador = new Jugador();
                jugador.setIdJugador(rs.getInt("idJugador"));
                jugador.setNickname(rs.getString("nickname"));
                jugadores.add(jugador);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return jugadores; 
    }

    @Override
    public Jugador obtenerPorId(int id) {
        Jugador jugador = new Jugador();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Jugador WHERE idJugador = " + id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Se esta obteniendo id");
            while(rs.next()){
                //Jugador jugador = new Jugador();
                jugador.setIdJugador(rs.getInt("idJugador"));
                jugador.setNickname(rs.getString("nickname"));
            }
            System.out.println(jugador);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return jugador; 
    }
    

    
    
}
