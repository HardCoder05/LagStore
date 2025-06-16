using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class OrdenaJuego : System.Web.UI.Page
    {
        //private JuegoWSClient wsJuego;
        private JuegoWSClient wsJuego = new JuegoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarJuegos("precio");
            }
        }

        protected void ddlOrden_SelectedIndexChanged(object sender, EventArgs e)
        {
            string criterio = ddlOrden.SelectedValue;
            CargarJuegos(criterio);
        }
        private void CargarJuegos(string criterio)
        {
            // Puedes usar sesión para obtener el ID del desarrollador logueado
            // Suponiendo: Session["idDesarrollador"] contiene el ID
            ////int idDesarrollador = (int)Session["idDesarrollador"];

            ////var juegos = wsJuego.listarJuegosPorDesarrollador(idDesarrollador);

            ////var listaOrdenada = juegos.AsQueryable();

            ////switch (criterio)
            ////{
            ////    case "precio":
            ////        listaOrdenada = listaOrdenada.OrderBy(j => j.precio);
            ////        break;
            ////    case "calificacion":
            ////        listaOrdenada = listaOrdenada.OrderByDescending(j => j.calificacionPromedio);
            ////        break;
            ////    case "fecha":
            ////        listaOrdenada = listaOrdenada.OrderByDescending(j => j.fechaLanzamiento);
            ////        break;
            ////}

            ////gvJuegos.DataSource = listaOrdenada.ToList();
            ////gvJuegos.DataBind();
        }



    }
}