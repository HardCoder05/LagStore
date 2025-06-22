using LagStoreWA.ServicesWS;
using System;
using System.Web.UI;
using System.Web.UI.HtmlControls;

namespace LagStoreWA
{
    public partial class RegistrarJugador : System.Web.UI.Page
    {
        private JugadorWSClient boJugador;
        private jugador jugador;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Administrador"] == null)
            {
                // Si no hay un administrador en sesión, redirigir a la página de inicio de sesión
                Response.Redirect("InicioSesion.aspx");
            }

            if (!IsPostBack)
            {

                panelContrasena.Visible = false;

                if (Session["Administrador"] != null)
                {
                    // Accedemos al Master Page
                    var liGestion = this.Master.FindControl("liGestion") as System.Web.UI.HtmlControls.HtmlGenericControl;
                    var liMasVendidos = this.Master.FindControl("liMasVendidos") as HtmlGenericControl;
                    var liMayorCalificacion = this.Master.FindControl("liMayorCalificacion") as HtmlGenericControl;
                    var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as System.Web.UI.WebControls.LinkButton;
                    var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as System.Web.UI.HtmlControls.HtmlGenericControl;
                    var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;
                    if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
                    {
                        // Mostrar menú gestión y cerrar sesión
                        liGestion.Visible = true;
                        liMasVendidos.Visible = true;
                        liMayorCalificacion.Visible = true;
                        liCerrarSesion.Visible = true;

                        // Ocultar iniciar sesión y crear cuenta
                        lnkIniciarSesion.Visible = false;
                        liCrearCuenta.Visible = false;
                    }
                }


                string accion = Request.QueryString["accion"];
                if (accion == "modificar")
                {
                    jugador = (jugador)Session["jugadorSeleccionado"];
                    if (jugador != null)
                        CargarDatos();
                }
            }
        }

        private void CargarDatos()
        {
            hfIdJugador.Value = jugador.idJugador.ToString();
            txtNickname.Text = jugador.nickname;
            txtNombre.Text = jugador.nombre;
            txtEmail.Text = jugador.email;
            txtTelefono.Text = jugador.telefono;
            txtFotoPerfil.Text = jugador.fotoDePerfil;
            txtContrasena.Text = jugador.contrasena;
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boJugador = new JugadorWSClient();

            jugador jugadorAnterior = (jugador)Session["jugadorSeleccionado"];
            if (jugadorAnterior == null)
            {
                lblMensaje.Text = "Error: no se encontró el jugador original.";
                return;
            }

            // Actualizar campos modificables
            jugadorAnterior.nickname = txtNickname.Text.Trim();
            jugadorAnterior.nombre = txtNombre.Text.Trim();
            jugadorAnterior.email = txtEmail.Text.Trim();
            jugadorAnterior.telefono = txtTelefono.Text.Trim();
            jugadorAnterior.fotoDePerfil = txtFotoPerfil.Text.Trim();
            jugadorAnterior.contrasena = txtContrasena.Text.Trim();

            try
            {
                boJugador.modificarJugador(jugadorAnterior);
                Response.Redirect("ListarJugadores.aspx");
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al modificar: " + ex.Message;
            }
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarJugadores.aspx");
        }
    }
}