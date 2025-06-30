using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class ReporteDesarrollador : System.Web.UI.Page
    {
        private ReporteDesarrolladorWSClient boReporteDesarrollador;
        protected void Page_Load(object sender, EventArgs e)
        {
            
            boReporteDesarrollador = new ReporteDesarrolladorWSClient();
            desarrollador desarrollador = (desarrollador)Session["Desarrollador"];
            string nombre = desarrollador.nombre;
            int idDesarrollador = desarrollador.idDesarrollador;
            byte[] reporte = boReporteDesarrollador.generarReporteDesarrollador(nombre,idDesarrollador);
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "inline;filename=ReporteDesarrolladores.pdf");
            Response.BinaryWrite(reporte);
            Response.End();
            
        }
    }
}