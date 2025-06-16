using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Cryptography;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;


namespace LagStoreWA
{
    public partial class ListarDesarrolladores : System.Web.UI.Page
    {
        private DesarrolladorWSClient boDesarrollador;
        private BindingList<desarrollador> desarrolladores;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Administrador"] == null)
            {
                // Si no hay un administrador en sesión, redirigir a la página de inicio de sesión
                Response.Redirect("InicioSesion.aspx");
            }

            boDesarrollador = new DesarrolladorWSClient();
            if (!IsPostBack)
            {
                
                desarrolladores = new BindingList<desarrollador>(boDesarrollador.listarTodosDesarrolladores());
                gvDesarrolladores.DataSource = desarrolladores;
                gvDesarrolladores.DataBind();
                // Accedemos al Master Page
                var liGestion = this.Master.FindControl("liGestion") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var lnkIniciarSesion = this.Master.FindControl("lnkIniciarSesion") as System.Web.UI.WebControls.LinkButton;
                var liCrearCuenta = this.Master.FindControl("liCrearCuenta") as System.Web.UI.HtmlControls.HtmlGenericControl;
                var liCerrarSesion = this.Master.FindControl("liCerrarSesion") as HtmlGenericControl;
                if (liGestion != null && lnkIniciarSesion != null && liCrearCuenta != null && liCerrarSesion != null)
                {
                    // Mostrar menú gestión y cerrar sesión
                    liGestion.Visible = true;
                    liCerrarSesion.Visible = true;
                    // Ocultar iniciar sesión y crear cuenta
                    lnkIniciarSesion.Visible = false;
                    liCrearCuenta.Visible = false;
                }
            }
        }

        /*protected void btnModificar_Click(object sender, EventArgs e)
        {
            int idDesarrollador = Int32.Parse(((LinkButton)sender).CommandArgument);
            desarrollador desarrolladorSeleccionado = desarrolladores.SingleOrDefault(x => x.idDesarrollador == idDesarrollador);
            Session["desarrolladorSeleccionado"] = desarrolladorSeleccionado;
            Response.Redirect("RegistrarDesarrollador.aspx?accion=modificar");

        }*/

        /*protected void btnEliminar_Click(object sender, EventArgs e)
        {
            int idDesarrollador = Int32.Parse(((LinkButton)sender).CommandArgument);
            boDesarrollador.eliminarDesarrollador(idDesarrollador);
            Response.Redirect("ListarDesarrolladores.aspx");
        }*/




        protected void btnBuscar_ServerClick(object sender, EventArgs e)
        {
            string textoBuscar = txtBuscar.Value.Trim();
            boDesarrollador = new DesarrolladorWSClient();

            if (!string.IsNullOrEmpty(textoBuscar))
            {
                int idBuscar = int.Parse(textoBuscar); // ← si estás seguro que siempre será un número

                try
                {
                    desarrollador d = boDesarrollador.obtenerDesarrolladorPorID(idBuscar);
                    if (d != null)
                    {
                        var listaResultado = new List<desarrollador> { d };
                        gvDesarrolladores.DataSource = listaResultado;
                        gvDesarrolladores.DataBind();
                        lblMensaje.Text = ""; // Limpiar mensaje si hay resultado
                    }
                    else
                    {
                        gvDesarrolladores.DataSource = new List<desarrollador>();
                        gvDesarrolladores.DataBind();
                        MostrarMensaje($"No se encontró un desarrollador con ID {idBuscar}");
                    }
                }
                catch (Exception ex)
                {
                    gvDesarrolladores.DataSource = new List<desarrollador>();
                    gvDesarrolladores.DataBind();
                    MostrarMensaje("Ocurrió un error al buscar el desarrollador.");
                }
            }
            else
            {
                // Si no se ingresó nada, volver a listar todos
                desarrolladores = new BindingList<desarrollador>(boDesarrollador.listarTodosDesarrolladores());
                gvDesarrolladores.DataSource = desarrolladores;
                gvDesarrolladores.DataBind();
                lblMensaje.Text = "";
            }
        }


        protected void gvDesarrolladores_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Modificar")
            {
                int idDesarrollador = Convert.ToInt32(e.CommandArgument);
                desarrollador dentro = boDesarrollador.obtenerDesarrolladorPorID(idDesarrollador);
                Session["desarrolladorSeleccionado"] = dentro;
                Response.Redirect("RegistrarDesarrollador.aspx?accion=modificar");
            }
            else if (e.CommandName == "Eliminar")
            {
                int id = Convert.ToInt32(e.CommandArgument);
                boDesarrollador.eliminarDesarrollador(id);
                Response.Redirect("ListarDesarrolladores.aspx");
            }
        }

        private void MostrarMensaje(string mensaje)
        {
            lblMensaje.Text = mensaje;
        }


    }  
}