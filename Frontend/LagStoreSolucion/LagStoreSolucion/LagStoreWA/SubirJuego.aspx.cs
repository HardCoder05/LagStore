using LagStoreWA.ServicesWS;
using System;
using System.Web.UI;

namespace LagStoreWA
{
    public partial class SubirJuego : Page
    {
        private JuegoWSClient juegoWS = new JuegoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Desarrollador"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }

            if (!IsPostBack)
            {
                // Llenar dropdowns
                foreach (var g in Enum.GetValues(typeof(Genero)))
                    ddlGenero.Items.Add(g.ToString());

                foreach (var m in Enum.GetValues(typeof(ModeloNegocio)))
                    ddlModeloNegocio.Items.Add(m.ToString());
            }
        }

        protected void btnSubir_Click(object sender, EventArgs e)
        {
            // Validar campos requeridos uno a uno
            if (string.IsNullOrWhiteSpace(txtTitulo.Text))
            {
                lblMensaje.Text = "El título es obligatorio.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtDescripcion.Text))
            {
                lblMensaje.Text = "La descripción es obligatoria.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtPrecio.Text) || !double.TryParse(txtPrecio.Text, out _))
            {
                lblMensaje.Text = "El precio es obligatorio y debe ser un número válido.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtVersion.Text) || !double.TryParse(txtVersion.Text, out _))
            {
                lblMensaje.Text = "La versión es obligatoria y debe ser un número válido.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtImagen.Text))
            {
                lblMensaje.Text = "La imagen es obligatoria.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtFechaLanzamiento.Text) || !DateTime.TryParse(txtFechaLanzamiento.Text, out _))
            {
                lblMensaje.Text = "La fecha de lanzamiento es obligatoria y debe ser una fecha válida.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtReqMin.Text))
            {
                lblMensaje.Text = "Los requisitos mínimos son obligatorios.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtReqRec.Text))
            {
                lblMensaje.Text = "Los requisitos recomendados son obligatorios.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtEspacio.Text) || !double.TryParse(txtEspacio.Text, out _))
            {
                lblMensaje.Text = "El espacio en disco es obligatorio y debe ser un número válido.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (string.IsNullOrWhiteSpace(txtFechaActualizacion.Text) || !DateTime.TryParse(txtFechaActualizacion.Text, out _))
            {
                lblMensaje.Text = "La fecha de última actualización es obligatoria y debe ser una fecha válida.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (ddlGenero.SelectedIndex == 0)
            {
                lblMensaje.Text = "Debe seleccionar un género.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            if (ddlModeloNegocio.SelectedIndex == 0)
            {
                lblMensaje.Text = "Debe seleccionar un modelo de negocio.";
                lblMensaje.CssClass = "text-danger";
                return;
            }

            try
            {
                desarrollador dev = (desarrollador)Session["Desarrollador"];

                juego nuevoJuego = new juego
                {
                    titulo = txtTitulo.Text.Trim(),
                    descripcion = txtDescripcion.Text.Trim(),
                    precio = double.Parse(txtPrecio.Text),
                    version = double.Parse(txtVersion.Text.Trim()),
                    imagen = txtImagen.Text.Trim(),
                    fechaLanzamiento = DateTime.Parse(txtFechaLanzamiento.Text),
                    requisitosMinimos = txtReqMin.Text.Trim(),
                    requisitosRecomendados = txtReqRec.Text.Trim(),
                    espacioDisco = double.Parse(txtEspacio.Text),
                    fechaUltimaActualizacion = DateTime.Parse(txtFechaActualizacion.Text),
                    genero = (Genero)Enum.Parse(typeof(Genero), ddlGenero.SelectedValue),
                    modeloNegocio = (ModeloNegocio)Enum.Parse(typeof(ModeloNegocio), ddlModeloNegocio.SelectedValue),
                    desarrollador = dev
                };

                nuevoJuego.genero = Genero.Estrategia;

                int id = juegoWS.insertarJuego(nuevoJuego);
                if (id > 0)
                {
                    lblMensaje.Text = "Juego registrado con éxito. ID: " + id;
                    lblMensaje.CssClass = "text-success";
                    LimpiarFormulario();
                }
                else
                {
                    lblMensaje.Text = "No se pudo registrar el juego.";
                    lblMensaje.CssClass = "text-danger";
                }
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error: " + ex.Message;
                lblMensaje.CssClass = "text-danger";
            }
        }

        private void LimpiarFormulario()
        {
            txtTitulo.Text = "";
            txtDescripcion.Text = "";
            txtPrecio.Text = "";
            txtVersion.Text = "";
            txtImagen.Text = "";
            txtFechaLanzamiento.Text = "";
            txtReqMin.Text = "";
            txtReqRec.Text = "";
            txtEspacio.Text = "";
            txtFechaActualizacion.Text = "";
            ddlGenero.SelectedIndex = 0;
            ddlModeloNegocio.SelectedIndex = 0;
        }
    }
}
