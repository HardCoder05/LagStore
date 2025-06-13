using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class Home : System.Web.UI.Page
    {
        private JuegoWSClient juegoWS = new JuegoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if(!IsPostBack)
            {
                // Verificar si el usuario está autenticado
                if (Session["Administrador"] != null)
                {
                    Response.Redirect("Administrador.aspx");
                }

                CargarJuegoDestacado(6);
                CargarJuegos();
            }
        }

        private void CargarJuegoDestacado(int idJuego)
        {
            try
            {
                var juego = juegoWS.obtenerJuegoPorId(idJuego);

                if (juego != null)
                {
                    lblTituloDestacado.Text = juego.titulo;
                    lblGeneroDestacado.Text = juego.genero.ToString();
                    lblDescripcionDestacada.Text = juego.descripcion;
                    lblPrecioDestacado.Text = juego.precio.ToString("F2");
                    litBackgroundUrl.Text = juego.imagen;
                }
            }
            catch (Exception ex)
            {
                // Mostrar error opcional
                lblTituloDestacado.Text = "Error al cargar juego destacado";
            }
        }

        private void CargarJuegos()
        {
            try
            {
                var juegos = juegoWS.listarJuegos();
                rptJuegosDestacados.DataSource = juegos.Skip(4).Take(2);
                rptJuegosDestacados.DataBind();
            }
            catch (Exception ex)
            {
                // Puedes registrar el error si es necesario
            }
        }
    }
}