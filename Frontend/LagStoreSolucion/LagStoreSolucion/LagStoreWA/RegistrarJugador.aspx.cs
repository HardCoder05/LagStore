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

                //panelContrasena.Visible = false;

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
                    panelContrasena.Visible = false;
                    jugador = (jugador)Session["jugadorSeleccionado"];
                    if (jugador != null)
                        CargarDatos();
                }
                else
                {
                    panelContrasena.Visible = true;
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
            txtContrasena.Text = jugador.contrasena;
            txtFotoPerfil.Text = jugador.fotoDePerfil;
            
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boJugador = new JugadorWSClient();

            // Validaciones
            if (string.IsNullOrWhiteSpace(txtNickname.Text) ||
                !System.Text.RegularExpressions.Regex.IsMatch(txtNickname.Text, @"^[a-zA-Z0-9_.]{3,20}$"))
            {
                lblMensaje.Text = "El nickname solo puede contener letras, números, guiones bajos (_) o puntos (.) y debe tener entre 3 y 20 caracteres.";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtNombre.Text) ||
                !System.Text.RegularExpressions.Regex.IsMatch(txtNombre.Text, @"^[a-zA-Z\sáéíóúÁÉÍÓÚñÑ]+$"))
            {
                lblMensaje.Text = "El nombre solo debe contener letras.";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtEmail.Text) ||
                !System.Text.RegularExpressions.Regex.IsMatch(txtEmail.Text, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                lblMensaje.Text = "El email ingresado no es válido.";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtTelefono.Text))
            {
                lblMensaje.Text = "El teléfono no puede estar vacío.";
                return;
            }
            else if (!System.Text.RegularExpressions.Regex.IsMatch(txtTelefono.Text, @"^\d{9}$"))
            {
                lblMensaje.Text = "El teléfono debe tener exactamente 9 dígitos numéricos.";
                return;
            }

            if (!string.IsNullOrWhiteSpace(txtFotoPerfil.Text) &&
                !System.Text.RegularExpressions.Regex.IsMatch(txtFotoPerfil.Text, @"^https?:\/\/[\w\-\.]+(\.[\w\-]+)+[/#?]?.*$"))
            {
                lblMensaje.Text = "La URL ingresada no es válida.";
                return;
            }

            jugador jugadorAnterior = (jugador)Session["jugadorSeleccionado"];
            if (jugadorAnterior == null)
            {
                lblMensaje.Text = "Error: no se encontró el jugador original.";
                return;
            }

            // Asignar valores
            jugadorAnterior.nickname = txtNickname.Text.Trim();
            jugadorAnterior.nombre = txtNombre.Text.Trim();
            jugadorAnterior.email = txtEmail.Text.Trim();
            jugadorAnterior.telefono = txtTelefono.Text.Trim();
            jugadorAnterior.contrasena = txtContrasena.Text.Trim();
            jugadorAnterior.fotoDePerfil = txtFotoPerfil.Text.Trim();
            

            try
            {
                boJugador.modificarJugadorDesdeAdministrador(jugadorAnterior);
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