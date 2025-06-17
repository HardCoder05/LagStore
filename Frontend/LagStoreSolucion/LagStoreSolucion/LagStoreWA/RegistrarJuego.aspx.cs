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
                if (Session["Administrador"] != null)
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

            juego juegoAnterior = (juego)Session["juegoSeleccionado"];
            if (juegoAnterior == null)
            {
                lblMensaje.Text = "Error: no se encontró el juego original.";
                return;
            }

            // Actualizar campos modificables
            juegoAnterior.titulo = txtTitulo.Text.Trim();
            juegoAnterior.descripcion = txtDescripcion.Text.Trim();
            juegoAnterior.genero = (Genero)Enum.Parse(typeof(Genero), txtGenero.Text.Trim());
            juegoAnterior.precio = double.TryParse(txtPrecio.Text.Trim(), out double precio) ? precio : 0;
            juegoAnterior.imagen = txtImagen.Text.Trim();

            try
            {
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
