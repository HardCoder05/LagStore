using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA{
    public partial class EditarComentario : System.Web.UI.Page
    {
        private JuegoWSClient wsJuego;
        private ResenaWSClient wsResena;
        private CalificacionWSClient wsCalificacion;
        protected void Page_Load(object sender, EventArgs e)
        {
            wsResena = new ResenaWSClient();
            wsJuego = new JuegoWSClient();

            if (!IsPostBack)
            {
                int idResena, idJuego;

                if (int.TryParse(Request.QueryString["idResena"], out idResena) &&
                    int.TryParse(Request.QueryString["idJuego"], out idJuego))
                {
                    CargarDatosResena(idResena);
                    CargarDatosJuego(idJuego); // opcional: si necesitas mostrar datos del juego
                }
            }
        }
        private void CargarDatosJuego(int idJuego)
        {

            // Aquí llamas a tu base de datos
            juego juego = wsJuego.obtenerJuegoPorId(idJuego); // trae las clases con minuscula
            if (wsJuego != null)
            {
                imgJuego.ImageUrl = juego.imagen;
                lblTitulo.Text = juego.titulo;
                lblPrecio.Text = juego.precio == 0 ? "Gratis" : "S/ " + juego.precio.ToString("0.00");
                lblFecha.Text = juego.fechaLanzamiento.ToString("dd/MM/yyyy");
                lblDescripcion.Text = juego.descripcion;

                lblVersion.Text = juego.version.ToString();
                lblReqMin.Text = juego.requisitosMinimos;
                lblReqRec.Text = juego.requisitosRecomendados;
                lblEspacio.Text = juego.espacioDisco.ToString("F2");
                lblUltimaAct.Text = juego.fechaUltimaActualizacion.ToString("dd/MM/yyyy");
                //duda
                lblGenero.Text = juego.genero.ToString();
                lblModeloNegocio.Text = juego.modeloNegocio.ToString();
                lblDesarrollador.Text = juego.desarrollador?.nombre ?? "N/A";
            }
        }
        private void CargarDatosResena(int idResena)
        {
            resena resena = wsResena.obtenerResenaPorId(idResena);
            if (resena != null)
            {
                txtComentario.Text = resena.comentario;
                ddlCalificacion.SelectedValue = resena.calificacion.puntuacion.ToString();
                Session["ResenaEditar"] = resena;
            }
        }

        protected void btnComentar_Click(object sender, EventArgs e)
        {
            wsResena = new ResenaWSClient();
            wsCalificacion = new CalificacionWSClient();

            // Recuperar la reseña desde sesión
            resena resena = (resena)Session["ResenaEditar"];

            if (resena != null)
            {
                // Actualizar comentario
                resena.comentario = txtComentario.Text.Trim();
                // Actualizar calificación
                calificacion calificacion;
                calificacion = wsCalificacion.obtenerCalificacionPorId(resena.calificacion.idCalificacion);
                resena.calificacion.puntuacion = int.Parse(ddlCalificacion.SelectedValue);
                calificacion.puntuacion= int.Parse(ddlCalificacion.SelectedValue);
                // Llamadas a los WS para actualizar
                wsCalificacion.modificarCalificacion(calificacion);
                wsResena.modificarResena(resena);

                // Redireccionar de vuelta al detalle del juego
                Response.Redirect($"DetalleJuego.aspx?id={resena.juego.idJuego}");
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            resena resena = (resena)Session["ResenaEditar"];
            Response.Redirect($"DetalleJuego.aspx?id={resena.juego.idJuego}");
        }
    }
}