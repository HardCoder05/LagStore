package pe.edu.pucp.lagstore.gestusuarios.model;
import java.util.Date;


public class Usuario {
    private int idUsuario;
    private String nombre;
    private String contrasena;
    private String email;
    private Date fechaRegistro;
    private String telefono;
    private String fotoDePerfil;
    private Rol rol;
    private int activo;
    
    public Usuario(){
        
    }
    
    public Usuario(String nombre,String email ,String contrasena, 
        Date fechaRegistro,String telefono, String fotoDePerfil) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.telefono = telefono;
        this.fotoDePerfil = fotoDePerfil;
        this.activo = 1;
    }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFotoDePerfil() { return fotoDePerfil; }
    public void setFotoDePerfil(String fotoDePerfil) { this.fotoDePerfil = fotoDePerfil; }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    
}