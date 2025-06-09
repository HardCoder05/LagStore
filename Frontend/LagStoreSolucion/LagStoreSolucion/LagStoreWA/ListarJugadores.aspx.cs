using System;
using System.Collections.Generic;
<<<<<<< HEAD
=======
using System.ComponentModel;
>>>>>>> main
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

<<<<<<< HEAD
=======
using LagStoreWA.ServicesWS;

>>>>>>> main
namespace LagStoreWA
{
    public partial class ListarJugadores : System.Web.UI.Page
    {
<<<<<<< HEAD
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
=======
        private JugadorWSClient boJugador;
        private BindingList<jugador> jugadores;
        protected void Page_Load(object sender, EventArgs e)
        {
            boJugador = new JugadorWSClient();
            if (!IsPostBack)
            {
                jugadores = new BindingList<jugador>(boJugador.listarTodosJugadores());
                gvJugadores.DataSource = jugadores;
                gvJugadores.DataBind();
>>>>>>> main
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
<<<<<<< HEAD
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
=======
            }
        }


        protected void btnBuscar_ServerClick(object sender, EventArgs e)
        {
            string textoBuscar = txtBuscar.Value.Trim();
            boJugador = new JugadorWSClient();

            if (!string.IsNullOrEmpty(textoBuscar))
            {
                int idBuscar = int.Parse(textoBuscar); // ← si estás 100% seguro que siempre será un número

                try
                {
                    jugador j = boJugador.obtenerJugadorPorID(idBuscar);
                    if (j != null)
                    {
                        var listaResultado = new List<jugador> { j };
                        gvJugadores.DataSource = listaResultado;
                        gvJugadores.DataBind();
                    }
                    else
                    {
                        gvJugadores.DataSource = new List<jugador>();
                        gvJugadores.DataBind();
                        MostrarMensaje($"No se encontró un jugador con ID {idBuscar}");
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
>>>>>>> main
            }
            else if (e.CommandName == "Eliminar")
            {
                int id = Convert.ToInt32(e.CommandArgument);
<<<<<<< HEAD
                var jugadorEliminar = listaJugadores.FirstOrDefault(j => j.Id == id);
                if (jugadorEliminar != null)
                {
                    listaJugadores.Remove(jugadorEliminar);
                    CargarJugadores(listaJugadores);
                }
            }*/
        }
=======
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
>>>>>>> main
    }
}