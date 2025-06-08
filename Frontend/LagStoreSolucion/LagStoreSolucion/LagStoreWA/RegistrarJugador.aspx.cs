using System;
using System.Web.UI;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class RegistrarJugador : System.Web.UI.Page
    {
        private JugadorWSClient boJugador;
        private jugador jugador;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
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