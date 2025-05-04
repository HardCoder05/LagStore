package pe.edu.pucp.lagstore.gestjuegos.model;

import java.util.Date;

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
    private Genero genero;
    private ModeloNegocio modeloNegocio;

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

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public ModeloNegocio getModeloNegocio() { return modeloNegocio; }
    public void setModeloNegocio(ModeloNegocio modeloNegocio) { this.modeloNegocio = modeloNegocio; }
}