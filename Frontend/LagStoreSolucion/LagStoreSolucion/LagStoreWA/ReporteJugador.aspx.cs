using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class ReporteJugador : System.Web.UI.Page
    {
        private ReporteJugadorWSClient boReporte;
        protected void Page_Load(object sender, EventArgs e)
        {
            int id = (int)Session["usuarioId"];
            boReporte =new ReporteJugadorWSClient();
            byte[] reporte = boReporte.generarReporteEmpleado(id);
            //generar PDF
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content_Disposition", "inline;filename=ReporteJugador.pdf");
            Response.BinaryWrite(reporte);
            Response.End();
        }
    }
}