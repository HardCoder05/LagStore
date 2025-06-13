using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class Biblioteca : System.Web.UI.Page
    {
        private JuegoAdquiridoWSClient juegoAdquiridoWS = new JuegoAdquiridoWSClient();
        private JuegoWSClient juegoWS = new JuegoWSClient();
        private BibliotecaWSClient bibliotecaWS = new BibliotecaWSClient(); // Asumiendo que existe

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["usuarioId"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }

            if (!IsPostBack)
            {
                InicializarBiblioteca();
                CargarBiblioteca();
                ActualizarEstadisticas();
                rblFiltro.SelectedValue = "todos";
            }
        }

        private void InicializarBiblioteca()
        {
            int idUsuario = (int)Session["usuarioId"];

            try
            {
                // Verificar si el usuario ya tiene una biblioteca, si no crear una
                var bibliotecaExistente = bibliotecaWS.obtenerBibliotecaPorUsuario(idUsuario);
                if (bibliotecaExistente == null)
                {
                    // Crear el objeto Usuario con solo el id (suficiente para asociar)
                    usuario usuarioAsociado = new usuario
                    {
                        idUsuario = idUsuario
                    };

                    // Crear la nueva biblioteca
                    biblioteca nuevaBiblioteca = new biblioteca
                    {
                        usuario = usuarioAsociado,
                        ingresoTotal = 0,
                        cantidadDeJuegos = 0,
                        activo = 1
                    };

                    // Llamada al Web Service
                    bibliotecaWS.insertarBiblioteca(nuevaBiblioteca);
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al inicializar la biblioteca: " + ex.Message, "danger");
            }
        }

        private void CargarBiblioteca(string filtro = "todos", string busqueda = "")
        {
            int idUsuario = (int)Session["usuarioId"];

            try
            {
                // Obtener la biblioteca del usuario
                var bibliotecaUsuario = bibliotecaWS.obtenerBibliotecaPorUsuario(idUsuario);
                if (bibliotecaUsuario == null)
                {
                    MostrarMensaje("No se pudo cargar la biblioteca del usuario.", "warning");
                    return;
                }

                // Obtener juegos adquiridos de la biblioteca
                juegoAdquirido[] juegosAdquiridos = juegoAdquiridoWS.listarJuegosAdquiridosPorBiblioteca(bibliotecaUsuario.idBiblioteca);

                // Aplicar filtro por estado
                switch (filtro)
                {
                    case "mostrados":
                        juegosAdquiridos = juegosAdquiridos.Where(ja => ja.activo == 1).ToArray();
                        break;
                    case "ocultos":
                        juegosAdquiridos = juegosAdquiridos.Where(ja => ja.activo == 0).ToArray();
                        break;
                        // "todos" no necesita filtro adicional
                }

                // Aplicar filtro de búsqueda
                if (!string.IsNullOrEmpty(busqueda))
                {
                    juegosAdquiridos = juegosAdquiridos.Where(ja =>
                        ja.juego.titulo.ToLower().Contains(busqueda.ToLower()) ||
                        ja.juego.genero.ToString().ToLower().Contains(busqueda.ToLower())
                    ).ToArray();
                }

                rptBiblioteca.DataSource = juegosAdquiridos;
                rptBiblioteca.DataBind();
                
                if (juegosAdquiridos.Length == 0)
                {
                    string mensaje = !string.IsNullOrEmpty(busqueda) ?
                        "No se encontraron juegos que coincidan con la búsqueda." :
                        "No hay juegos en tu biblioteca. ¡Agrega algunos!";
                    MostrarMensaje(mensaje, "info");
                }
                else
                {
                    OcultarMensaje();
                }

                ActualizarEstadisticas();
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al cargar la biblioteca: " + ex.Message, "danger");
            }
        }

        private void ActualizarEstadisticas()
        {
            int idUsuario = (int)Session["usuarioId"];
            try
            {
                var bibliotecaUsuario = bibliotecaWS.obtenerBibliotecaPorUsuario(idUsuario);
                if (bibliotecaUsuario != null)
                {
                    juegoAdquirido[] todosJuegos = juegoAdquiridoWS.listarJuegosAdquiridosPorBiblioteca(bibliotecaUsuario.idBiblioteca);

                    int totalJuegos = todosJuegos.Length;
                    int juegosMostrados = todosJuegos.Count(ja => ja.activo == 1);
                    double tiempoTotal = todosJuegos.Sum(ja => ja.tiempoJuego);

                    lblTotalJuegos.InnerText = totalJuegos.ToString();
                    lblJuegosMostrados.InnerText = juegosMostrados.ToString();
                    lblTiempoTotal.InnerText = $"{tiempoTotal:F1} hrs";

                    // Actualizar biblioteca en base de datos
                    bibliotecaUsuario.cantidadDeJuegos = totalJuegos;
                    bibliotecaWS.modificarBiblioteca(bibliotecaUsuario);
                }
            }
            catch (Exception ex)
            {
                // No mostrar error crítico para estadísticas
                System.Diagnostics.Debug.WriteLine("Error actualizando estadísticas: " + ex.Message);
            }
        }

        protected void btnOcultar_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string[] args = btn.CommandArgument.Split(',');
            int idJuego = int.Parse(args[0]);
            int idBiblioteca = int.Parse(args[1]);

            try
            {
                var juegoAdquirido = juegoAdquiridoWS.obtenerJuegoAdquiridoPorBibliotecaYJuego(idBiblioteca, idJuego);
                if (juegoAdquirido != null)
                {
                    juegoAdquirido.activo = juegoAdquirido.activo == 1 ? 0 : 1;
                    juegoAdquirido.actualizado = true;
                    juegoAdquiridoWS.modificarJuegoAdquirido(juegoAdquirido);

                    string accion = juegoAdquirido.activo == 1 ? "mostrado" : "ocultado";
                    MostrarMensaje($"Juego {accion} correctamente.", "success");

                    CargarBiblioteca(rblFiltro.SelectedValue, txtBuscar.Text);
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al actualizar el estado del juego: " + ex.Message, "danger");
            }
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string[] args = btn.CommandArgument.Split(',');
            int idJuego = int.Parse(args[0]);
            int idBiblioteca = int.Parse(args[1]);

            try
            {
                //juegoAdquiridoWS.eliminarJuegoAdquiridoPorBibliotecaYJuego(idBiblioteca, idJuego);
                MostrarMensaje("Juego eliminado de tu biblioteca correctamente.", "success");
                CargarBiblioteca(rblFiltro.SelectedValue, txtBuscar.Text);
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al eliminar el juego: " + ex.Message, "danger");
            }
        }
        
        // Event handlers para los filtros y búsqueda
        protected void rblFiltro_SelectedIndexChanged(object sender, EventArgs e)
        {
            CargarBiblioteca(rblFiltro.SelectedValue, txtBuscar.Text);
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            CargarBiblioteca(rblFiltro.SelectedValue, txtBuscar.Text);
        }

        protected void btnLimpiarBusqueda_Click(object sender, EventArgs e)
        {
            txtBuscar.Text = "";
            CargarBiblioteca(rblFiltro.SelectedValue, "");
        }
        
        // Métodos auxiliares para mostrar mensajes
        private void MostrarMensaje(string mensaje, string tipo)
        {
            pnlMensaje.Visible = true;
            lblMensaje.Text = mensaje;

            // Limpiar clases CSS anteriores
            pnlMensaje.CssClass = "alert";

            // Agregar clase según el tipo
            switch (tipo.ToLower())
            {
                case "success":
                    pnlMensaje.CssClass += " alert-success";
                    break;
                case "danger":
                case "error":
                    pnlMensaje.CssClass += " alert-danger";
                    break;
                case "warning":
                    pnlMensaje.CssClass += " alert-warning";
                    break;
                case "info":
                default:
                    pnlMensaje.CssClass += " alert-info";
                    break;
            }
        }

        private void OcultarMensaje()
        {
            pnlMensaje.Visible = false;
        }
    }
}
