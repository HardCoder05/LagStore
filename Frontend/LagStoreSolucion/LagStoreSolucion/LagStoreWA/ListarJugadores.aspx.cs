using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class ListarJugadores : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Accedemos al Master Page
                var liGestion = this.Master.FindControl("liGestion") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as System.Web.UI.WebControls.LinkButton;
                var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;
                if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
                {
                    // Mostrar menú gestión y cerrar sesión
                    liGestion.Visible = true;
                    liCerrarSesion.Visible = true;

                    // Ocultar iniciar sesión y crear cuenta
                    lnkIniciarSesion.Visible = false;
                    liCrearCuenta.Visible = false;
                }
                //CargarJugadores(listaJugadores);
            }
        }
        protected void btnBuscar_ServerClick(object sender, EventArgs e)
        {
            /*string textoBuscar = txtBuscar.Value.Trim();
            if (int.TryParse(textoBuscar, out int idBuscar))
            {
                var resultado = listaJugadores.Where(j => j.Id == idBuscar).ToList();
                CargarJugadores(resultado);
            }
            else if (string.IsNullOrEmpty(textoBuscar))
            {
                CargarJugadores(listaJugadores);
            }
            else
            {
                CargarJugadores(new List<Jugador>());
            }*/
        }

        protected void gvJugadores_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            /*if (e.CommandName == "Modificar")
            {
                int id = Convert.ToInt32(e.CommandArgument);
                Response.Redirect($"ModificarJugador.aspx?id={id}");
            }
            else if (e.CommandName == "Eliminar")
            {
                int id = Convert.ToInt32(e.CommandArgument);
                var jugadorEliminar = listaJugadores.FirstOrDefault(j => j.Id == id);
                if (jugadorEliminar != null)
                {
                    listaJugadores.Remove(jugadorEliminar);
                    CargarJugadores(listaJugadores);
                }
            }*/
        }
    }
}