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
    public partial class Home : System.Web.UI.Page
    {
        private JuegoWSClient juegoWS = new JuegoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if(!IsPostBack)
            {
                // Verificar si el usuario está autenticado
                if (Session["Administrador"] != null)
                {
                    Response.Redirect("Administrador.aspx");
                }

                CargarJuegoDestacado(1);
                CargarJuegos();
            }
        }

        private void CargarJuegoDestacado(int idJuego)
        {
            try
            {
                var juego = juegoWS.obtenerJuegoPorId(idJuego);

                if (juego != null)
                {
                    lblTituloDestacado.Text = juego.titulo;
                    lblGeneroDestacado.Text = juego.genero.ToString();
                    lblDescripcionDestacada.Text = juego.descripcion;
                    lblPrecioDestacado.Text = juego.precio.ToString("F2");
                    litBackgroundUrl.Text = juego.imagen;
                }
            }
            catch (Exception ex)
            {
                // Mostrar error opcional
                lblTituloDestacado.Text = "Error al cargar juego destacado";
            }
        }


        private void CargarJuegos()
        {
            try
            {
                var juegos = juegoWS.listarJuegos();
                rptJuegosDestacados.DataSource = juegos.Skip(3).Take(4);
                rptJuegosDestacados.DataBind();
            }
            catch (Exception ex)
            {
                // Puedes registrar el error si es necesario
            }
        }

        protected void btnVerDetallesDestacado_Click(object sender, EventArgs e)
        {
            Response.Redirect($"DetalleJuego.aspx?id={1}");
        }

        protected void btnAgregarCarrito_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "AgregarCarrito")
            {
                int idJuego = Convert.ToInt32(e.CommandArgument);
                // Obtener la información completa del juego a través del servicio web
                juego juegoSeleccionado = juegoWS.obtenerJuegoPorId(idJuego); // Cambiar 'wsJuego' por 'juegoWS'
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
        private void MostrarMensaje(string mensaje, string cssClass)
        {
            // Crear un control literal para mostrar el mensaje
            Literal litMensaje = new Literal();
            litMensaje.Text = $"<div class='alert {cssClass}' role='alert'>{mensaje}</div>";

            // Buscar un contenedor en la página para agregar el mensaje (puedes ajustar esto según tu diseño)
            var contenedorMensajes = this.FindControl("contenedorMensajes") as PlaceHolder;
            if (contenedorMensajes != null)
            {
                contenedorMensajes.Controls.Add(litMensaje);
            }
        }
    }
}
