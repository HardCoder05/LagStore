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
    public partial class Administrador : System.Web.UI.Page
    {
        private JuegoWSClient juegoWS = new JuegoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Administrador"] == null)
            {
                // Si no hay un administrador en sesión, redirigir a la página de inicio de sesión
                Response.Redirect("InicioSesion.aspx");
            }

            if (!IsPostBack)
            {
                // Accedemos al Master Page
                var liGestion = this.Master.FindControl("liGestion") as System.Web.UI.HtmlControls.HtmlGenericControl;
                //var liReportes = this.Master.FindControl("liReportes") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as System.Web.UI.WebControls.LinkButton;
                var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;
                var liMasVendidos = this.Master.FindControl("liMasVendidos") as HtmlGenericControl;
                var liMayorCalificacion = this.Master.FindControl("liMayorCalificacion") as HtmlGenericControl;
                var liBiblioteca = this.Master.FindControl("liBiblioteca") as HtmlGenericControl;
                var liCarrito = this.Master.FindControl("liCarrito") as HtmlGenericControl;
                
                
                //var liReporteUsuarios = this.Master.FindControl("liReporteUsuarios") as HtmlGenericControl;
                if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
                {
                    // Mostrar menú gestión y cerrar sesión
                    liGestion.Visible = true;
                    liMasVendidos.Visible = true;
                    liMayorCalificacion.Visible = true;
                    liCerrarSesion.Visible = true;
                    liBiblioteca.Visible = false;
                    liCarrito.Visible = false;
                    //liReportes.Visible = true;
                    //liReporteUsuarios.Visible = true;
                    // Ocultar iniciar sesión y crear cuenta
                    lnkIniciarSesion.Visible = false;
                    liCrearCuenta.Visible = false;
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
    }
}