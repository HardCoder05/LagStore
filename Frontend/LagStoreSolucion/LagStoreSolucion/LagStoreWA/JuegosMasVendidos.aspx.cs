using System;
using System.Collections.Generic;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class JuegosMasVendidos : System.Web.UI.Page
    {
        private AdministradorWSClient administradorWS = new AdministradorWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            // Mostrar Gestión y Cerrar Sesión desde Master
            var liGestion = this.Master.FindControl("liGestion") as System.Web.UI.HtmlControls.HtmlGenericControl;
            var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as System.Web.UI.WebControls.LinkButton;
            var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as System.Web.UI.HtmlControls.HtmlGenericControl;
            var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as System.Web.UI.HtmlControls.HtmlGenericControl;
            var liMasVendidos = this.Master.FindControl("liMasVendidos") as HtmlGenericControl;
            if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
            {
                liGestion.Visible = true;
                liMasVendidos.Visible = true;
                liCerrarSesion.Visible = true;
                lnkIniciarSesion.Visible = false;
                liCrearCuenta.Visible = false;
            }

            if (!IsPostBack)
            {
                try
                {
                    var lista = administradorWS.listarJuegosQueMasSeVenden();
                    gvMasVendidos.DataSource = lista;
                    gvMasVendidos.DataBind();
                }
                catch (Exception ex)
                {
                    // Manejar error si falla el WS
                    Console.WriteLine("Error: " + ex.Message);
                }
            }
        }
    }
}