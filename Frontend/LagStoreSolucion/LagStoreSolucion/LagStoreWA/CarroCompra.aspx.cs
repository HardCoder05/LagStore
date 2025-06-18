using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class CarroCompra : System.Web.UI.Page
    {
        private JugadorWSClient boJugador;
        private CarroCompraWSClient boCarro;

        // Atributo accesible para el carro de compra actual
        protected object carroCompraActual;

        protected GridView gvCarro; // Ensure gvCarro is properly defined as a GridView control
        protected Label lblMensaje; // Ensure lblMensaje is properly defined as a Label control
        protected int usuarioId; // Removed initialization here

        protected Panel pnlProcesando; // Ensure pnlProcesando is properly defined as a Panel control
        protected Image imgEstadoCompra; // Ensure imgEstadoCompra is properly defined as an Image control
        protected Label lblProcesando; // Ensure lblProcesando is properly defined as a Label control

        protected void Page_Load(object sender, EventArgs e)
        {
            boJugador = new JugadorWSClient();
            boCarro = new CarroCompraWSClient();

            // Initialize usuarioId inside Page_Load where Session is accessible
            if (Session["UsuarioId"] != null)
            {
                usuarioId = Convert.ToInt32(Session["UsuarioId"]);
            }

            if (!IsPostBack)
            {
                // Validar sesión de usuario
                if (Session["UsuarioId"] != null)
                {
                    CargaCarroCompra(usuarioId);
                }
                else
                {
                    // Redirigir a inicio de sesión si no está autenticado
                    Response.Redirect("InicioSesion.aspx");
                }
            }
        }

        protected void CargaCarroCompra(int usuarioId)
        {
            try
            {
                // Intentar obtener el carro de compra del usuario
                var productos = boCarro.otenerCarroPorUsuario(usuarioId);

                // Si no hay productos, se asume que no existe el carro y se crea uno nuevo
                if (productos == null || !((productos is System.Collections.IEnumerable enumerable) && enumerable.GetEnumerator().MoveNext()))
                {
                    // Insertar un nuevo carro de compra para el usuario
                    boCarro.insertarCarroCompra(usuarioId);

                    // Volver a obtener el carro de compra recién creado
                    productos = boCarro.otenerCarroPorUsuario(usuarioId);
                }

                carroCompraActual = productos;

                // Enlazar al GridView si es una colección válida
                if (productos is IEnumerable<object>)
                {
                    gvCarro.DataSource = productos;
                    gvCarro.DataBind();
                }
                else
                {
                    throw new InvalidOperationException("El resultado de 'otenerCarroPorUsuario' no es una colección válida para el control GridView.");
                }
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al cargar el carro de compra: " + ex.Message;
            }
        }

        protected void GvCarro_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            try
            {
                int idProducto = Convert.ToInt32(gvCarro.DataKeys[e.RowIndex].Value);

                // Convert 'carroCompraActual' to the correct type 'carroCompra'
                var carroCompra = carroCompraActual as carroCompra;
                if (carroCompra == null)
                {
                    throw new InvalidOperationException("El objeto 'carroCompraActual' no es del tipo esperado 'carroCompra'.");
                }

                // Lógica para eliminar el producto del carro  
                int resultado = boCarro.modificarCarroCompra(carroCompra);

                if (resultado > 0) // Verificar si el resultado indica éxito  
                {
                    lblMensaje.Text = "Producto eliminado correctamente.";
                }
                else
                {
                    lblMensaje.Text = "No se pudo eliminar el producto.";
                }

                CargaCarroCompra(usuarioId);
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al eliminar el producto: " + ex.Message;
            }
        }

        protected void btnFinalizarCompra_Click(object sender, EventArgs e)
        {
            try
            {
                // Mostrar el panel de procesamiento y el gráfico inicial
                pnlProcesando.Visible = true;
                imgEstadoCompra.ImageUrl = "~/Content/processing.png";
                lblProcesando.Text = "Procesando su compra con su método de pago predeterminado...";
                lblProcesando.ForeColor = System.Drawing.Color.Black;

                // Puedes forzar un update si usas UpdatePanel, si no, sigue el flujo normal

                bool exito = true; //boCarro.finalizarCompra(usuarioId);

                if (exito)
                {
                    imgEstadoCompra.ImageUrl = "~/Content/success.png"; // Icono verde de éxito
                    lblProcesando.Text = "¡Compra finalizada con éxito!";
                    lblProcesando.ForeColor = System.Drawing.Color.Green;
                    lblMensaje.Text = "";
                    CargaCarroCompra(usuarioId);
                }
                else
                {
                    imgEstadoCompra.ImageUrl = "~/Content/processing.png";
                    lblProcesando.Text = "No se pudo finalizar la compra.";
                    lblProcesando.ForeColor = System.Drawing.Color.Red;
                    lblMensaje.Text = "No se pudo finalizar la compra.";
                }
            }
            catch (Exception ex)
            {
                imgEstadoCompra.ImageUrl = "~/Content/processing.png";
                lblProcesando.Text = "Error al finalizar la compra: " + ex.Message;
                lblProcesando.ForeColor = System.Drawing.Color.Red;
                lblMensaje.Text = "Error al finalizar la compra: " + ex.Message;
            }
        }
    }
}