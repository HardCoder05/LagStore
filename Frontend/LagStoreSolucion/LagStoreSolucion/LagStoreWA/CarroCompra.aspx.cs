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

        protected GridView gvCarro; 
        protected Label lblMensaje; 
        protected int usuarioId;
        protected Panel pnlProcesando;     
        protected Image imgEstadoCompra;   
        protected Label lblProcesando;     

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

                var carro = boCarro.otenerCarroPorUsuario(usuarioId);
                carro.juegos = listaJuegos.ToArray(); // Convertir la lista a un arreglo para resolver el error CS0029

                boCarro.modificarCarroCompra(carro);
                // Guardar la lista en el atributo actual para referencia
                carroCompraActual = listaJuegos;

                // Enlazar la lista al GridView
                gvCarro.DataSource = listaJuegos;
                gvCarro.DataBind();


                double total = listaJuegos.Sum(j => j.precio);
                lblTotal.Text = "Total:S/ " + total.ToString("N2"); //para que salga en soles

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
                //lblMensaje.Text = "Error al cargar el carro de compra: " + ex.Message;
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
                imgEstadoCompra.Visible = true;
                imgEstadoCompra.ImageUrl = "~/Content/processing.png";
                lblProcesando.Text = "Procesando su compra con su método de pago predeterminado...";
                lblProcesando.ForeColor = System.Drawing.Color.Black;

                // Se simula la finalización de la compra; aquí podrías llamar a un método real del servicio
                bool exito = ProcesarCompra(); // boCarro.finalizarCompra(usuarioId);

                if (exito)
                {
                    if (AnhadirJuegosBiblioteca())
                    {

                        mostrarCompraCorrecta();
                        Session["ListaJuegosCarro"] = new List<juego>();
                        Response.Redirect(Request.RawUrl);

                    }
                    else
                    {
                        mostrarErrorCompra();
                    }
                }
                else
                {
                    mostrarErrorCompra();
                }
            }
            catch (Exception ex)
            {
                mostrarErrorCompra();
            }
        }

        protected bool ProcesarCompra()
        {
            try
            {
                bool exito = true; 

                if (exito)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception ex)
            {    
                return false;
            }
        }

        protected bool AnhadirJuegosBiblioteca()
        {
            try
            {
                List<juego> listaJuegos = Session["ListaJuegosCarro"] as List<juego> ?? new List<juego>();

                var boBilioteca = new BibliotecaWSClient();
                var biblotecaUsuario = boBilioteca.obtenerBibliotecaPorUsuario(usuarioId);
                var boJuegoAdquirido = new JuegoAdquiridoWSClient();

                foreach (var juego in listaJuegos)
                {
                    var juegoAdq = new juegoAdquirido
                    {
                        biblioteca = biblotecaUsuario,
                        juego = juego,
                        fechaAdquisicion = DateTime.Now,
                        ultimaSesion = DateTime.Now,
                        tiempoJuego = 0.0,
                        actualizado = true
                    };

                    boJuegoAdquirido.insertarJuegoAdquirido(juegoAdq);
                }
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
            
        }

        protected void mostrarCompraCorrecta()
        {
            imgEstadoCompra.Visible = true;
            imgEstadoCompra.ImageUrl = "~/Content/Imagenes_de_carga/success.png";
            lblProcesando.Text = "¡Compra finalizada con éxito!";
            lblProcesando.ForeColor = System.Drawing.Color.Green;
            lblMensaje.Text = "";
        }

        protected void mostrarErrorCompra()
        {
            imgEstadoCompra.Visible = true;
            imgEstadoCompra.ImageUrl = "~/Content/Imagenes_de_carga/processing.png";
            lblProcesando.Text = "No se pudo finalizar la compra.";
            lblProcesando.ForeColor = System.Drawing.Color.Red;
            lblMensaje.Text = "No se pudo finalizar la compra.";
        }


    }
}