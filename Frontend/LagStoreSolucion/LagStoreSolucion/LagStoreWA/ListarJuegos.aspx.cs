using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class ListarJuegos : System.Web.UI.Page
    {
        private JuegoWSClient boJuego;
        private BindingList<juego> juegos;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Administrador"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
            }

            boJuego = new JuegoWSClient();

            if (!IsPostBack)
            {
                juegos = new BindingList<juego>(boJuego.listarJuegos());
                gvJuegos.DataSource = juegos;
                gvJuegos.DataBind();

                var liGestion = this.Master.FindControl("liGestion") as HtmlGenericControl;
                var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as LinkButton;
                var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as HtmlGenericControl;
                var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;
                var liMasVendidos = this.Master.FindControl("liMasVendidos") as HtmlGenericControl;
                var liMayorCalificacion = this.Master.FindControl("liMayorCalificacion") as HtmlGenericControl;
                if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
                {
                    liGestion.Visible = true;
                    liCerrarSesion.Visible = true;
                    liMasVendidos.Visible = true;
                    liMayorCalificacion.Visible = true;
                    lnkIniciarSesion.Visible = false;
                    liCrearCuenta.Visible = false;
                }
            }
        }

        protected void btnBuscar_ServerClick(object sender, EventArgs e)
        {
            string textoBuscar = txtBuscar.Value.Trim().ToLower();
            boJuego = new JuegoWSClient();

            juegos = new BindingList<juego>(boJuego.listarJuegos());

            if (!string.IsNullOrEmpty(textoBuscar))
            {
                var juegosFiltrados = new List<juego>();

                foreach (juego j in juegos)
                {
                    if (!string.IsNullOrEmpty(j.titulo) && j.titulo.ToLower().Contains(textoBuscar))
                    {
                        juegosFiltrados.Add(j);
                    }
                }

                if (juegosFiltrados.Count > 0)
                {
                    gvJuegos.DataSource = juegosFiltrados;
                    gvJuegos.DataBind();
                }
                else
                {
                    gvJuegos.DataSource = new List<juego>();
                    gvJuegos.DataBind();
                    MostrarMensaje($"No se encontraron juegos con el nombre \"{textoBuscar}\".");
                }
            }
            else
            {
                gvJuegos.DataSource = juegos;
                gvJuegos.DataBind();
            }
        }

        protected void gvJuegos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int idJuego = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "Modificar")
            {
                juego j = boJuego.obtenerJuegoPorId(idJuego);
                Session["juegoSeleccionado"] = j;
                Response.Redirect("RegistrarJuego.aspx?accion=modificar");
            }
            else if (e.CommandName == "Eliminar")
            {
                boJuego.eliminarJuego(idJuego);
                Response.Redirect("ListarJuegos.aspx");
            }
        }

        private void MostrarMensaje(string mensaje)
        {
            lblMensaje.Text = mensaje;
        }
    }
}
