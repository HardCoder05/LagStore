package pe.edu.pucp.lagstore.gestusuarios.model;
import java.util.Date;
import pe.edu.pucp.lagstore.gestjuegos.model.Biblioteca;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String contrasena;
    private String email;
    private Date fechaRegistro;
    private int telefono;
    private String fotoDePerfil;
    private Rol nombreRol;
    private int activo;
    private Biblioteca biblioteca;
    private int idRol;
    public Usuario(){
        
    }
    public Usuario(String nombre, String contrasena, String email, Date fechaRegistro, int telefono, String fotoDePerfil
                    ,int activo,Biblioteca biblioteca,int idRol) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.telefono = telefono;
        this.fotoDePerfil = fotoDePerfil;
        this.activo=activo;
        this.biblioteca=biblioteca;
        this.idRol=idRol;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
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

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public String getFotoDePerfil() { return fotoDePerfil; }
    public void setFotoDePerfil(String fotoDePerfil) { this.fotoDePerfil = fotoDePerfil; }

    public Rol getNombreRol() { return nombreRol; }
    public void setNombreRol(Rol nombreRol) { this.nombreRol = nombreRol; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }

    
    
    
}