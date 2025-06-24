using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;

namespace LagStoreWA
{
    public partial class ListarRecargas : Page
    {
        private JugadorWSClient boJugador;
        private RecargaWSClient boRecarga;
        private CarteraWSClient boCartera;
        private BindingList<recarga> recargas;
        private int usuarioId;
        protected void Page_Load(object sender, EventArgs e)
        {
           
            if (Session["Jugador"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }
            else
            {
                usuarioId = Convert.ToInt32(Session["UsuarioId"]);
            }

            boJugador = new JugadorWSClient();
            boRecarga = new RecargaWSClient();

            if (!IsPostBack)
                CargarRecargas();
        }


        private void CargarRecargas()
        {   
            cartera cartera = boCartera.obenerCarteraPorId(usuarioId);
            
            jugador j = boJugador.obtenerJugadorPorID(usuarioId);
            if (j != null)
            {
                var arreglo = boRecarga
                        .listarRecargasAsociadas(usuarioId)
                        ?? Array.Empty<recarga>();

                recargas = new BindingList<recarga>(arreglo);
                gvRecargas.DataSource = recargas;
                gvRecargas.DataBind();

                lblMensaje.Text = recargas.Count == 0
                    ? "El jugador no tiene recargas registradas."
                    : "";
                return;
            }
            
            MostrarMensaje("No se encontró al jugador o no tiene cartera.");
        }


        protected void btnBuscar_ServerClick(object sender, EventArgs e)
        {
            string texto = txtBuscar.Value.Trim();
            if (recargas == null) CargarRecargas();               

            if (string.IsNullOrEmpty(texto))
            {
                gvRecargas.DataSource = recargas;
            }
            else
            {
                int idFiltrar;
                if (int.TryParse(texto, out idFiltrar))
                    gvRecargas.DataSource =
                        new List<recarga>(recargas).FindAll(r => r.idRecarga == idFiltrar);
                else
                    MostrarMensaje("Ingrese un número válido.");
            }
            gvRecargas.DataBind();
        }

        protected void gvRecargas_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            //  Opcional
        }

        private void MostrarMensaje(string mensaje) => lblMensaje.Text = mensaje;
    }
}