using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class JuegosMayorCalificacion : System.Web.UI.Page
    {
        private AdministradorWSClient administradorWS = new AdministradorWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            var liGestion = this.Master.FindControl("liGestion") as HtmlGenericControl;
            var liMasVendidos = this.Master.FindControl("liMasVendidos") as HtmlGenericControl;
            var liMayorCalificacion = this.Master.FindControl("liMayorCalificacion") as HtmlGenericControl;
            var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;
            var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as LinkButton;
            var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as HtmlGenericControl;

            if (liGestion != null) liGestion.Visible = true;
            if (liMasVendidos != null) liMasVendidos.Visible = true;
            if (liMayorCalificacion != null) liMayorCalificacion.Visible = true;
            if (liCerrarSesion != null) liCerrarSesion.Visible = true;
            if (lnkIniciarSesion != null) lnkIniciarSesion.Visible = false;
            if (liCrearCuenta != null) liCrearCuenta.Visible = false;

            if (!IsPostBack)
            {
                try
                {
                    var juegos = administradorWS.listarJuegosConMayorCalificacion();
                    gvMayorCalificacion.DataSource = juegos;
                    gvMayorCalificacion.DataBind();
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error al obtener juegos con mayor calificación: " + ex.Message);
                }
            }
        }
    }
}