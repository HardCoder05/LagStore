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

        protected GridView gvCarro; // Asegúrate de que gvCarro esté correctamente definido en el markup
        protected Label lblMensaje;  // Asegúrate de que lblMensaje esté definido en el markup
        protected int usuarioId;
        protected Panel pnlProcesando;     // Asegúrate de que pnlProcesando esté definido en el markup
        protected Image imgEstadoCompra;   // Asegúrate de que imgEstadoCompra esté definido en el markup
        protected Label lblProcesando;     // Asegúrate de que lblProcesando esté definido en el markup

        protected void Page_Load(object sender, EventArgs e)
        {
            boJugador = new JugadorWSClient();
            boCarro = new CarroCompraWSClient();

            // Inicializar usuarioId usando la sesión
            if (Session["UsuarioId"] != null)
            {
                usuarioId = Convert.ToInt32(Session["UsuarioId"]);
            }

            if (!IsPostBack)
            {
                if (usuarioId > 0)
                {
                    CargaCarroCompra(usuarioId);
                }
                else
                {
                    // Redirigir a la página de inicio de sesión si no hay un usuario válido
                    Response.Redirect("InicioSesion.aspx");
                }
            }
        }

        protected void CargaCarroCompra(int usuarioId)
        {
            try
            {
                // Recuperar la lista de juegos del carrito desde sesión; si no existe, se inicializa vacía
                List<juego> listaJuegos = Session["ListaJuegosCarro"] as List<juego> ?? new List<juego>();

                // Guardar la lista en el atributo actual para referencia
                carroCompraActual = listaJuegos;

                // Enlazar la lista al GridView
                gvCarro.DataSource = listaJuegos;
                gvCarro.DataBind();

                // Mostrar mensaje según la existencia de juegos en el carrito
                if (listaJuegos.Any())
                {
                    //lblMensaje.Text = "Si deseas agregar más juegos a tu carro, selecciónalos desde la tienda.";
                }
                else
                {
                    lblMensaje.Text = "No hay juegos en el carro de compra.";
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
                int idJuego = Convert.ToInt32(gvCarro.DataKeys[e.RowIndex].Value);
                List<juego> listaJuegos = Session["ListaJuegosCarro"] as List<juego> ?? new List<juego>();

                // Buscar el juego a eliminar usando su identificador
                juego juegoAEliminar = listaJuegos.FirstOrDefault(j => j.idJuego == idJuego);
                if (juegoAEliminar != null)
                {
                    listaJuegos.Remove(juegoAEliminar);
                    Session["ListaJuegosCarro"] = listaJuegos;
                    lblMensaje.Text = "Juego eliminado correctamente.";
                }
                else
                {
                    lblMensaje.Text = "No se encontró el juego para eliminar.";
                }

                // Actualizar el contador en el Master
                var master = this.Master as LagStoreWA.LagStore;
                if (master != null)
                {
                    master.ActualizarContadorCarrito();
                }

                // Recargar los datos en el GridView
                CargaCarroCompra(usuarioId);
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al eliminar el juego: " + ex.Message;
            }
        }
        protected void btnFinalizarCompra_Click(object sender, EventArgs e)
        {
            try
            {
                pnlProcesando.Visible = true;
                imgEstadoCompra.ImageUrl = "~/Content/processing.png";
                lblProcesando.Text = "Procesando su compra con su método de pago predeterminado...";
                lblProcesando.ForeColor = System.Drawing.Color.Black;

                // Se simula la finalización de la compra; aquí podrías llamar a un método real del servicio
                bool exito = true; // boCarro.finalizarCompra(usuarioId);

                if (exito)
                {
                    imgEstadoCompra.ImageUrl = "~/Content/success.png";
                    lblProcesando.Text = "¡Compra finalizada con éxito!";
                    lblProcesando.ForeColor = System.Drawing.Color.Green;
                    lblMensaje.Text = "";
                    // Si es necesario, puedes limpiar la lista del carrito después de la compra
                    Session["ListaJuegosCarro"] = new List<juego>();
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