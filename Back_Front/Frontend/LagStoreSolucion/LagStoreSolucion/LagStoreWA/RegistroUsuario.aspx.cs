using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class RegistroUsuario : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void ddlTipoUsuario_SelectedIndexChanged(object sender, EventArgs e)
        {
            pnlDesarrollador.Visible = false;
            pnlJugador.Visible = false;
            pnlAdministrador.Visible = false;

            // Mostrar solo el panel correspondiente al tipo seleccionado
            switch (ddlTipoUsuario.SelectedValue)
            {
                case "Desarrollador":
                    pnlDesarrollador.Visible = true;
                    break;
                case "Jugador":
                    pnlJugador.Visible = true;
                    break;
                case "Administrador":
                    pnlAdministrador.Visible = true;
                    break;
            }

        }
        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
        }
    }
}