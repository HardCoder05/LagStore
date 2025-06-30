using LagStoreWA.ServicesWS;
using System;
using System.Web.UI;
using System.Web.UI.HtmlControls;

namespace LagStoreWA
{
    public partial class RegistrarJuego : System.Web.UI.Page
    {
        private JuegoWSClient boJuego;
        private juego game;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["Administrador"] == null)
                {
                    // Si no hay un administrador en sesión, redirigir a la página de inicio de sesión
                    Response.Redirect("InicioSesion.aspx");
                }

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
                    game = (juego)Session["juegoSeleccionado"];
                    if (game != null)
                        CargarDatos();
                }
            }
        }

        private void CargarDatos()
        {
            hfIdJuego.Value = game.idJuego.ToString();
            txtTitulo.Text = game.titulo;
            txtDescripcion.Text = game.descripcion;
            txtGenero.Text = game.genero.ToString();
            txtPrecio.Text = game.precio.ToString("F2");
            txtImagen.Text = game.imagen;
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boJuego = new JuegoWSClient();

            // Validación de campos obligatorios
            if (string.IsNullOrWhiteSpace(txtTitulo.Text))
            {
                lblMensaje.Text = "El título es obligatorio.";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtDescripcion.Text))
            {
                lblMensaje.Text = "La descripción es obligatoria.";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtGenero.Text))
            {
                lblMensaje.Text = "El género es obligatorio.";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtPrecio.Text) || !double.TryParse(txtPrecio.Text.Trim(), out double precio) || precio < 0)
            {
                lblMensaje.Text = "El precio debe ser un número válido y no negativo.";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtImagen.Text) || !Uri.IsWellFormedUriString(txtImagen.Text.Trim(), UriKind.Absolute))
            {
                lblMensaje.Text = "La URL de la imagen no es válida.";
                return;
            }

            juego juegoAnterior = (juego)Session["juegoSeleccionado"];
            if (juegoAnterior == null)
            {
                lblMensaje.Text = "Error: no se encontró el juego original.";
                return;
            }

            if (juegoAnterior.modeloNegocio == ModeloNegocio.Free_to_play && double.Parse(txtPrecio.Text) > 0)
            {
                lblMensaje.Text = "Los juegos Free to Play no deben tener un precio mayor a 0.";
                return;
            }

            if (juegoAnterior.modeloNegocio != ModeloNegocio.Free_to_play && double.Parse(txtPrecio.Text) == 0)
            {
                lblMensaje.Text = "Inserte un precio mayor a 0 para juegos que no son Free to Play.";
                return;
            }

            try
            {
                // Actualizar campos
                juegoAnterior.titulo = txtTitulo.Text.Trim();
                juegoAnterior.descripcion = txtDescripcion.Text.Trim();
                juegoAnterior.genero = (Genero)Enum.Parse(typeof(Genero), txtGenero.Text.Trim(), true);
                juegoAnterior.precio = precio;
                juegoAnterior.imagen = txtImagen.Text.Trim();

                boJuego.modificarJuego(juegoAnterior);
                Response.Redirect("ListarJuegos.aspx");
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al modificar: " + ex.Message;
            }
        }


        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarJuegos.aspx");
        }
    }
}
