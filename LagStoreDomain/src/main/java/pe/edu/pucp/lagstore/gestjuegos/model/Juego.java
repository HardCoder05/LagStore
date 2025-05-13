package pe.edu.pucp.lagstore.gestjuegos.model;

import java.util.Date;

import pe.edu.pucp.lagstore.gestusuarios.model.Desarrollador;

public class Juego {
    private int idJuego;
    private String titulo;
    private String descripcion;
    private double precio;
    private double version;
    private String imagenJuego;
    private Date fechaLanzamiento;
    private String requisitosMinimos;
    private String requisitosRecomendados;
    private double espacioDisco;
    private Date fechaUltimaActualizacion;
    private int idGenero;
    private int idModeloNegocio;
    private Desarrollador desarrollador;
    private int activo;

    public Juego() {}

    public int getIdJuego() { return idJuego; }
    public void setIdJuego(int idJuego) { this.idJuego = idJuego; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getVersion() { return version; }
    public void setVersion(double version) { this.version = version; }

    public String getImagen() { return imagenJuego; }
    public void setImagen(String imagenJuego) { this.imagenJuego = imagenJuego; }

    public Date getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(Date fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }

    public String getRequisitosMinimos() { return requisitosMinimos; }
    public void setRequisitosMinimos(String requisitosMinimos) { this.requisitosMinimos = requisitosMinimos; }

    public String getRequisitosRecomendados() { return requisitosRecomendados; }
    public void setRequisitosRecomendados(String requisitosRecomendados) { this.requisitosRecomendados = requisitosRecomendados; }

    public double getEspacioDisco() { return espacioDisco; }
    public void setEspacioDisco(double espacioDisco) { this.espacioDisco = espacioDisco; }

    public Date getFechaUltimaActualizacion() { return fechaUltimaActualizacion; }
    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) { this.fechaUltimaActualizacion = fechaUltimaActualizacion; }

    public int getIdGenero() { return idGenero; }
    public void setIdGenero(int idGenero) { this.idGenero = idGenero; }

    public int getIdModeloNegocio() { return idModeloNegocio; }
    public void setIdModeloNegocio(int idModeloNegocio) { this.idModeloNegocio = idModeloNegocio; }

    public Desarrollador getDesarrollador() { return desarrollador; }
    public void setDesarrollador(Desarrollador desarrollador) { this.desarrollador = desarrollador; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }
}