using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class OrdenaJuego : System.Web.UI.Page
    {
        private JuegoWSClient boJuego;
        private BindingList<juego> juegos;
        private int idDesarrollador;
        protected void Page_Load(object sender, EventArgs e)
        {
            boJuego = new JuegoWSClient();
            idDesarrollador = Convert.ToInt32(Session["usuarioId"]);
            if (!IsPostBack)
            {
                try
                {
                    juegos = new BindingList<juego>(boJuego.listarJuegosPorDesarrollador(idDesarrollador));
                    if (juegos == null)
                    {
                        MostrarMensaje("El desarrollador no tiene juegos en su biblioteca.");
                    }
                }
                catch (Exception ex)
                {
                    //MostrarMensaje("Ocurrió un error al obtener los juegos del desarrollador: " + ex.Message);
                    MostrarMensaje("El desarrollador no tiene juegos en su biblioteca.");

                }
                //juegos = new BindingList<juego>(boJuego.listarJuegos());
                //if (juegos == null)
                //{
                //    MostrarMensaje("El desarrollador no tiene juegos en su biblioteca.");
                //}
                gvJuegos.DataSource = juegos;
                gvJuegos.DataBind();

                var liGestion = this.Master.FindControl("liGestion") as HtmlGenericControl;
                var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as LinkButton;
                var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as HtmlGenericControl;
                var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;

                if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
                {
                    liGestion.Visible = true;
                    liCerrarSesion.Visible = true;
                    lnkIniciarSesion.Visible = false;
                    liCrearCuenta.Visible = false;
                }
            }

        }
        protected void btnBuscar_ServerClick(object sender, EventArgs e)
        {
            string textoBuscar = txtBuscar.Value.Trim();
            boJuego = new JuegoWSClient();

            if (!string.IsNullOrEmpty(textoBuscar))
            {
                try
                {
                    int idBuscar = int.Parse(textoBuscar);
                    juego j = boJuego.obtenerJuegoPorId(idBuscar);

                    if (j != null)
                    {
                        gvJuegos.DataSource = new List<juego> { j };
                        gvJuegos.DataBind();
                    }
                    else
                    {
                        gvJuegos.DataSource = new List<juego>();
                        gvJuegos.DataBind();
                        MostrarMensaje($"No se encontró un juego con ID {idBuscar}");
                    }
                }
                catch
                {
                    gvJuegos.DataSource = new List<juego>();
                    gvJuegos.DataBind();
                    MostrarMensaje("Error al buscar el juego. Asegúrese de ingresar un ID válido.");
                }
            }
            else
            {
                juegos = new BindingList<juego>(boJuego.listarJuegosPorDesarrollador(idDesarrollador));
                gvJuegos.DataSource = juegos;
                gvJuegos.DataBind();
            }
        }

        protected void ddlOrden_SelectedIndexChanged(object sender, EventArgs e)
        {
            juegos = new BindingList<juego>(boJuego.listarJuegosPorDesarrollador(idDesarrollador));
            List<juego> juegosOrdenados = juegos.ToList();
            gvJuegos.DataSource = juegos;
            gvJuegos.DataBind();

            string criterio = ddlOrden.SelectedValue;

            switch (criterio)
            {
                case "precio":
                    juegosOrdenados = juegos.OrderBy(j => j.precio).ToList();
                    break;
                case "fechaLanzamiento":
                    juegosOrdenados = juegos.OrderByDescending(j => j.fechaLanzamiento).ToList();
                    break;
                case "espacioDisco":
                    juegosOrdenados = juegos.OrderByDescending(j => j.espacioDisco).ToList();
                    break;
            }
            BindingList<juego> juegosBinding = new BindingList<juego>(juegosOrdenados);
            gvJuegos.DataSource = juegosBinding;
            gvJuegos.DataBind();
        }

        private void MostrarMensaje(string mensaje)
        {
            lblMensaje.Text = mensaje;
        }


    }
}