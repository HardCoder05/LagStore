using System;
using System.Web.UI;
using LagStoreWA.ServicesWS;

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
                //boJuego.(juegoAnterior);
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
