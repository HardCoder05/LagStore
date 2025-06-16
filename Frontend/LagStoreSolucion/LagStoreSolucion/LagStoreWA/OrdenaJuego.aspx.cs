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
            // Verificar que el usuario esté logueado

            if (Session["id"] == null)
            {
                //Session["idDesarrollador"] = desarrollador.idDesarrollador;
                //Response.Redirect("OrdenaJuego.aspx");
            }

            int idDesarrollador = (int)Session["id"];

            // Llamar al servicio web para obtener los juegos del desarrollador
            var juegos = wsJuego.listarJuegosPorDesarrollador(idDesarrollador);

            if (juegos == null || juegos.Length == 0)
            {
                gvJuegos.DataSource = null;
                gvJuegos.DataBind();
                lblMensaje.Text = "No se encontraron juegos publicados.";
                return;
            }

            // Conversión a lista para aplicar ordenamientos
            var lista = juegos.ToList();

            // Aplicar el orden según criterio
            switch (criterio)
            {
                case "precio":
                    lista = lista.OrderBy(j => j.precio).ToList();
                    break;

                //case "calificacion":
                //    // Si el servicio ya incluye calificaciónPromedio:
                //    lista = lista.OrderByDescending(j => j.calificacionPromedio).ToList();
                //    break;

                case "fecha":
                    lista = lista.OrderByDescending(j => j.fechaLanzamiento).ToList();
                    break;
            }

            gvJuegos.DataSource = lista;
            gvJuegos.DataBind();
        }






    }
}