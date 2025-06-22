using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class CatalogoJuegos : System.Web.UI.Page
    {
        private JuegoWSClient wsJuego;

        protected void Page_Load(object sender, EventArgs e)
        {
            wsJuego = new JuegoWSClient();

            if (!IsPostBack)
            {
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
                        liMayorCalificacion.Visible = true;
                        liMasVendidos.Visible = true;
                        liCerrarSesion.Visible = true;

                        // Ocultar iniciar sesión y crear cuenta
                        lnkIniciarSesion.Visible = false;
                        liCrearCuenta.Visible = false;
                    }
                }

                CargarFiltros();
                CargarJuegos();
            }
        }

        private void CargarFiltros()
        {
            try
            {
                // Cargar géneros
                ddlGenero.Items.Clear();
                ddlGenero.Items.Add(new ListItem("Todos los géneros", ""));
                ddlGenero.Items.Add(new ListItem("Accion", "Accion"));
                ddlGenero.Items.Add(new ListItem("Shooter", "Shooter"));
                ddlGenero.Items.Add(new ListItem("Rol", "Rol"));
                ddlGenero.Items.Add(new ListItem("Estrategia", "Estrategia"));
                ddlGenero.Items.Add(new ListItem("Deportes", "Deportes"));
                ddlGenero.Items.Add(new ListItem("Simulación", "Simulacion"));
                ddlGenero.Items.Add(new ListItem("Carreras", "Carreras"));

                // Cargar modelos de negocio
                ddlModeloNegocio.Items.Clear();
                ddlModeloNegocio.Items.Add(new ListItem("Todos los modelos", ""));
                ddlModeloNegocio.Items.Add(new ListItem("Free_to_play", "Free_to_play"));
                ddlModeloNegocio.Items.Add(new ListItem("Paga", "Paga"));
                ddlModeloNegocio.Items.Add(new ListItem("Suscripcion", "Suscripcion"));
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al cargar filtros: " + ex.Message, "alert-danger");
            }
        }

        private void CargarJuegos(bool aplicarFiltros = false)
        {
            try
            {
                juego[] juegos;

                if (aplicarFiltros)
                {
                    // Preparar parámetros de filtro
                    string titulo = string.IsNullOrEmpty(txtTitulo.Text.Trim()) ? null : txtTitulo.Text.Trim();

                    // Manejo correcto de géneros
                    Genero? generoFiltro = null;
                    if (!string.IsNullOrEmpty(ddlGenero.SelectedValue))
                    {
                        if (Enum.TryParse<Genero>(ddlGenero.SelectedValue, out Genero g))
                            generoFiltro = g;
                    }

                    // Manejo correcto de modelo de negocio
                    ModeloNegocio? modeloFiltro = null;
                    if (!string.IsNullOrEmpty(ddlModeloNegocio.SelectedValue))
                    {
                        if (Enum.TryParse<ModeloNegocio>(ddlModeloNegocio.SelectedValue, out ModeloNegocio m))
                            modeloFiltro = m;
                    }

                    // CORRECCIÓN: Manejo mejorado de precios
                    double? precioMin = null;
                    double? precioMax = null;

                    if (!string.IsNullOrEmpty(txtPrecioMin.Text))
                    {
                        if (double.TryParse(txtPrecioMin.Text, out double min) && min >= 0)
                            precioMin = min;
                    }

                    if (!string.IsNullOrEmpty(txtPrecioMax.Text))
                    {
                        if (double.TryParse(txtPrecioMax.Text, out double max) && max >= 0)
                            precioMax = max;
                    }

                    // Validar que precio mínimo no sea mayor que precio máximo
                    if (precioMin.HasValue && precioMax.HasValue && precioMin.Value > precioMax.Value)
                    {
                        MostrarMensaje("El precio mínimo no puede ser mayor que el precio máximo", "alert-warning");
                        return;
                    }

                    // CORRECCIÓN: Pasar valores correctos al servicio web
                    if (titulo != null || generoFiltro.HasValue || modeloFiltro.HasValue ||
                        precioMin.HasValue || precioMax.HasValue)
                    {
                        // Usar valores por defecto solo cuando sea necesario
                        Genero gen = generoFiltro ?? default(Genero);
                        ModeloNegocio mod = modeloFiltro ?? default(ModeloNegocio);

                        // IMPORTANTE: Solo usar valores por defecto extremos si no se especifican
                        double precioMinimo = precioMin ?? 0;
                        double precioMaximo = precioMax ?? 999999; // Valor más realista que double.MaxValue

                        // Llamar al servicio web con filtros
                        juegos = wsJuego.listarJuegosConFiltro(titulo, gen, mod, precioMinimo, precioMaximo);
                    }
                    else
                    {
                        // Si no hay filtros, cargar todos los juegos
                        juegos = wsJuego.listarJuegos();
                    }
                }
                else
                {
                    // Cargar todos los juegos
                    juegos = wsJuego.listarJuegos();
                }

                if (juegos != null && juegos.Length > 0)
                {
                    rptJuegos.DataSource = juegos;
                    rptJuegos.DataBind();

                    lblResultados.Text = $"Se encontraron {juegos.Length} juego(s)";
                    pnlSinResultados.Visible = false;
                    rptJuegos.Visible = true;
                }
                else
                {
                    rptJuegos.Visible = false;
                    pnlSinResultados.Visible = true;
                    lblResultados.Text = "No se encontraron juegos";
                }

                OcultarMensaje();
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al cargar juegos: " + ex.Message, "alert-danger");
                rptJuegos.Visible = false;
                pnlSinResultados.Visible = true;
            }
        }


        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            CargarJuegos(aplicarFiltros: true);
        }

        protected void btnLimpiar_Click(object sender, EventArgs e)
        {
            // Limpiar todos los filtros
            txtTitulo.Text = "";
            ddlGenero.SelectedIndex = 0;
            ddlModeloNegocio.SelectedIndex = 0;
            txtPrecioMin.Text = "";
            txtPrecioMax.Text = "";

            // Cargar todos los juegos
            CargarJuegos(aplicarFiltros: false);
        }

        protected void btnMostrarTodos_Click(object sender, EventArgs e)
        {
            btnLimpiar_Click(sender, e);
        }

        protected void btnVerDetalle_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "VerDetalle")
            {
                int idJuego = Convert.ToInt32(e.CommandArgument);
                Response.Redirect($"DetalleJuego.aspx?id={idJuego}");
            }
        }

        protected void btnAgregarCarrito_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "AgregarCarrito")
            {
                int idJuego = Convert.ToInt32(e.CommandArgument);
                // Obtener la información completa del juego a través del servicio web
                juego juegoSeleccionado = wsJuego.obtenerJuegoPorId(idJuego);
                if (juegoSeleccionado != null)
                {
                    // Recuperar o crear la lista de juegos para el carrito
                    List<juego> listaJuegos = Session["ListaJuegosCarro"] as List<juego>;
                    if (listaJuegos == null)
                    {
                        listaJuegos = new List<juego>();
                    }

                    // Agregar el juego seleccionado a la lista
                    listaJuegos.Add(juegoSeleccionado);
                    Session["ListaJuegosCarro"] = listaJuegos;

                    // Actualizar el contador en el master
                    var master = this.Master as LagStoreWA.LagStore;
                    if (master != null)
                    {
                        master.ActualizarContadorCarrito();
                    }

                    // Mostrar mensaje de éxito en la misma página (o mediante AJAX)
                    MostrarMensaje("Juego agregado al carrito exitosamente", "alert-success");

                    // (Opcional) Puedes redireccionar tras unos segundos o permitir que el usuario siga navegando
                    // Response.Redirect("CarroCompra.aspx");
                }
                else
                {
                    MostrarMensaje("No se pudo obtener el juego", "alert-danger");
                }
            }
        }

        // Métodos auxiliares para el binding de datos
        //protected string GetImageUrl(object rutaImagen)
        //{
        //    if (rutaImagen == null || string.IsNullOrEmpty(rutaImagen.ToString()))
        //        return "~/Assets/default-game.jpg"; // Imagen por defecto

        //    return "~/Assets/Games/" + rutaImagen.ToString();
        //}

        protected string GetPriceTag(object precio, object modeloNegocio)
        {
            if (modeloNegocio != null)
            {
                string modelo = modeloNegocio.ToString();
                if (modelo == "Free_to_play" || modelo == "GRATIS")
                    return "<span class='price-tag free-tag'>GRATIS</span>";
            }

            if (precio != null)
            {
                double precioVal = Convert.ToDouble(precio);
                if (precioVal == 0)
                    return "<span class='price-tag free-tag'>GRATIS</span>";
                else
                    return $"<span class='price-tag'>S/. {precioVal:F2}</span>";
            }

            return "<span class='price-tag'>Precio no disponible</span>";
        }

        protected string TruncateText(string text, int maxLength)
        {
            if (string.IsNullOrEmpty(text))
                return "Sin descripción disponible";

            if (text.Length <= maxLength)
                return text;

            return text.Substring(0, maxLength) + "...";
        }

        protected string GetFechaLanzamiento(object fecha)
        {
            if (fecha == null) return "N/A";

            if (DateTime.TryParse(fecha.ToString(), out DateTime fechaLanzamiento))
                return fechaLanzamiento.ToString("MMM yyyy");

            return "N/A";
        }

        protected string GetPuntaje(object puntaje)
        {
            if (puntaje == null) return "N/A";

            if (double.TryParse(puntaje.ToString(), out double valor))
                return valor.ToString("F1") + "/5";

            return "N/A";
        }

        protected string GetGenerosHtml(object genero)
        {
            if (genero == null) return "";

            var generoMap = new Dictionary<string, string>
            {
                ["ACCION"] = "Accion",
                ["SHOOTER"] = "Shooter",
                ["ROL"] = "Rol",
                ["ESTRATEGIA"] = "Estrategia",
                ["DEPORTES"] = "Deportes",
                ["SIMULACION"] = "Simulacion",
                ["CARRERAS"] = "Carreras"
            };

            string generoTexto = genero.ToString().ToUpper();
            return generoMap.TryGetValue(generoTexto, out string displayText) ? displayText : generoTexto;
        }

        private void MostrarMensaje(string mensaje, string tipo)
        {
            lblMensaje.Text = mensaje;
            pnlMensaje.CssClass = $"alert {tipo}";
            pnlMensaje.Visible = true;
        }

        private void OcultarMensaje()
        {
            pnlMensaje.Visible = false;
        }

        protected void Page_UnLoad(object sender, EventArgs e)
        {
            wsJuego?.Close();
        }
    }
}