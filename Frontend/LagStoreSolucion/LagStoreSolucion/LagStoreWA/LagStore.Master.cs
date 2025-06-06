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