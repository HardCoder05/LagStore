package pe.edu.pucp.lagstore.gestionusuarios.mysql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.lagstore.config.DBManager;
import pe.edu.pucp.lagstore.gestionusuarios.dao.AdministradorDAO;
import pe.edu.pucp.lagstore.gestusuarios.model.Administrador;

public class AdministradorMySQL implements AdministradorDAO{
    private Connection con;
    private ResultSet rs;

   
    @Override
    public int insertar(Administrador administrador) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, administrador.getRolAdministrativo());
        parametrosEntrada.put(3, administrador.getNombre());
        parametrosEntrada.put(4, administrador.getEmail());
        parametrosEntrada.put(5, administrador.getContrasena());
        parametrosEntrada.put(6, new Date(administrador.getFechaRegistro().getTime()));
        parametrosEntrada.put(7, administrador.getTelefono());
        parametrosEntrada.put(8, administrador.getFotoDePerfil());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ADMINISTRADOR", parametrosEntrada, parametrosSalida);
        administrador.setIdUsuario((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del administrador");
        return administrador.getIdUsuario();
    }

    @Override
    public int modificar(Administrador administrador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,administrador.getIdAdministrador());
        parametrosEntrada.put(2, administrador.getRolAdministrativo());
        parametrosEntrada.put(3, administrador.getNombre());
        parametrosEntrada.put(4, administrador.getEmail());
        parametrosEntrada.put(5, administrador.getContrasena());
        parametrosEntrada.put(6, new Date(administrador.getFechaRegistro().getTime()));
        parametrosEntrada.put(7, administrador.getTelefono());
        parametrosEntrada.put(8, administrador.getFotoDePerfil());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ADMINISTRADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del administrador");
        return resultado;
    }

    @Override
    public int eliminar(int idAdministrador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idAdministrador);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ADMINISTRADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del administrador");
        return resultado;
    }

    @Override
    public ArrayList<Administrador> listarTodos() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ADMINISTRADOR", null);
        System.out.println("Lectura de administradores...");
        try{
            while(rs.next()){
                Administrador a = new Administrador();
                a.setIdAdministrador(rs.getInt(1));
                a.setNombre(rs.getString(2));    
                a.setEmail(rs.getString(3));
                a.setContrasena(rs.getString(4));
                a.setFechaRegistro(rs.getDate(5));
                a.setTelefono(rs.getString(6));
                a.setFotoDePerfil(rs.getString(7));
                a.setRolAdministrativo(rs.getString(8));
                administradores.add(a);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return administradores;
    }

    @Override
    public Administrador obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
