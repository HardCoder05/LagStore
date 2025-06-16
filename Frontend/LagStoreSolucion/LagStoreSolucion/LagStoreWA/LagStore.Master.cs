using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class LagStore : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Jugador"] != null || Session["Desarrollador"] != null
                || Session["Administrador"] != null)
            {
                // Si el usuario está autenticado, mostrar su email y el enlace de cerrar sesión
                //var usu = (usuario)Session["Usuario"];
                //lblUsuario.Text = $"Bienvenido, {usuario.email}";
                lnkIniciarSesion.Visible = false;
                liCerrarSesion.Visible = true;
                liCrearCuenta.Visible = false; // Ocultar el enlace de crear cuenta si ya está autenticado
            }
            else
            {
                // Si no hay usuario autenticado, ocultar el nombre de usuario y mostrar el enlace de inicio de sesión
                //lblUsuario.Text = "Bienvenido, invitado";
                lnkIniciarSesion.Visible = true;
                liCerrarSesion.Visible = false;
            }
        }
        protected void lnkIniciarSesion_Click(object sender, EventArgs e)
        {
            Response.Redirect("InicioSesion.aspx");
        }

        protected void lnkCerrarSesion_Click(object sender, EventArgs e)
        {
            // Limpiar sesión y redirigir a página de login o inicio
            Session.Clear();
            Session.Abandon();

            // Redirigir (cambia la ruta si usas otra página)
            Response.Redirect("Home.aspx");
        }
    }
}