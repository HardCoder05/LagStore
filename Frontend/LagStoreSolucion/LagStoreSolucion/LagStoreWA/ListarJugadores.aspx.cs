using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class ListarJugadores : System.Web.UI.Page
    {
        private JugadorWSClient boJugador;
        private BindingList<jugador> jugadores;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Administrador"] == null)
            {
                // Si no hay un administrador en sesión, redirigir a la página de inicio de sesión
                Response.Redirect("InicioSesion.aspx");
            }

            boJugador = new JugadorWSClient();
            if (!IsPostBack)
            {
                jugadores = new BindingList<jugador>(boJugador.listarTodosJugadores());
                gvJugadores.DataSource = jugadores;
                gvJugadores.DataBind();
                // Accedemos al Master Page
                var liGestion = this.Master.FindControl("liGestion") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as System.Web.UI.WebControls.LinkButton;
                var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;
                var liMasVendidos = this.Master.FindControl("liMasVendidos") as HtmlGenericControl;
                var liMayorCalificacion = this.Master.FindControl("liMayorCalificacion") as HtmlGenericControl;
                
                if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
                {
                    // Mostrar menú gestión y cerrar sesión
                    liGestion.Visible = true;
                    liMayorCalificacion.Visible = true;
                    liCerrarSesion.Visible = true;
                    liMasVendidos.Visible = true;
                    // Ocultar iniciar sesión y crear cuenta
                    lnkIniciarSesion.Visible = false;
                    liCrearCuenta.Visible = false;
                }
            }
        }


        protected void btnBuscar_ServerClick(object sender, EventArgs e)
        {
            string textoBuscar = txtBuscar.Value.Trim();
            if (!string.IsNullOrEmpty(textoBuscar))
            {
                try
                {
                    var listaResultado = boJugador.listarPorNombreONickname(textoBuscar);
                    if (listaResultado != null && listaResultado.Length > 0)
                    {
                        gvJugadores.DataSource = listaResultado;
                        gvJugadores.DataBind();
                    }
                    else
                    {
                        gvJugadores.DataSource = new List<jugador>();
                        gvJugadores.DataBind();
                        MostrarMensaje($"No se encontraron jugadores con el nombre o nickname: '{textoBuscar}'");
                    }
                }
                catch (Exception ex)
                {
                    gvJugadores.DataSource = new List<jugador>();
                    gvJugadores.DataBind();
                    MostrarMensaje("Ocurrió un error al buscar el jugador.");
                }
            }
            else
            {
                jugadores = new BindingList<jugador>(boJugador.listarTodosJugadores());
                gvJugadores.DataSource = jugadores;
                gvJugadores.DataBind();
            }
        }


        protected void gvJugadores_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Modificar")
            {
                int idJugador = Convert.ToInt32(e.CommandArgument);
                jugador dentro = boJugador.obtenerJugadorPorID(idJugador);
                Session["jugadorSeleccionado"] = dentro;
                Response.Redirect("RegistrarJugador.aspx?accion=modificar");
            }
            else if (e.CommandName == "Eliminar")
            {
                int id = Convert.ToInt32(e.CommandArgument);
                boJugador.eliminarJugador(id);
                Response.Redirect("ListarJugadores.aspx");
            }
        }

        private void MostrarMensaje(string mensaje)
        {
            lblMensaje.Text = mensaje;
        }

        /*protected void btnEliminar_Click(object sender, EventArgs e)
        {
            int idJugador = Int32.Parse(((LinkButton)sender).CommandArgument);
            boJugador.eliminarJugador(idJugador);
            Response.Redirect("ListarJugadores.aspx");
        }*/

        //enpaginar jugadores
        protected void gvJugadores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvJugadores.PageIndex = e.NewPageIndex;

            // Si hay búsqueda activa
            string textoBuscar = txtBuscar.Value.Trim();
            if (!string.IsNullOrEmpty(textoBuscar))
            {
                var listaResultado = boJugador.listarPorNombreONickname(textoBuscar);
                gvJugadores.DataSource = listaResultado;
            }
            else
            {
                jugadores = new BindingList<jugador>(boJugador.listarTodosJugadores());
                gvJugadores.DataSource = jugadores;
            }

            gvJugadores.DataBind();
        }

    }

    

}