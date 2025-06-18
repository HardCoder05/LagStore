using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;


namespace LagStoreWA
{
    public partial class RegistrarDesarrollador : System.Web.UI.Page
    {
        private DesarrolladorWSClient boDesarrollador;
        private desarrollador desarrollador;

        protected void Page_Load(object sender, EventArgs e)
        {
            /*if (Session["Administrador"] == null)
            {
                // Si no hay un administrador en sesión, redirigir a la página de inicio de sesión
                Response.Redirect("InicioSesion.aspx");
            }*/

            if (!IsPostBack)
            {
                if (Session["Administrador"] != null)
                {
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

                string accion = Request.QueryString["accion"];
                if (accion == "modificar")
                {
                    desarrollador = (desarrollador)Session["desarrolladorSeleccionado"];
                    if (desarrollador != null)
                        CargarDatos();
                }
            }
        }

        private void CargarDatos()
        {
            hfIdDesarrollador.Value = desarrollador.idDesarrollador.ToString();
            txtNombre.Text = desarrollador.nombre;
            txtEmail.Text = desarrollador.email;
            txtTelefono.Text = desarrollador.telefono;
            txtNumeroCuenta.Text = desarrollador.numeroCuenta;
            txtIngresoTotal.Text = desarrollador.ingresoTotal.ToString("N2");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boDesarrollador = new DesarrolladorWSClient();
            // Recuperar el desarrollador original desde la sesión
            desarrollador desarrolladorAnterior = (desarrollador)Session["desarrolladorSeleccionado"];
            if (desarrolladorAnterior == null)
            {
                lblMensaje.Text = "Error: no se encontró el desarrollador original.";
                return;
            }

            // Modificar solo los campos que se pueden editar
            desarrolladorAnterior.nombre = txtNombre.Text.Trim();
            desarrolladorAnterior.email = txtEmail.Text.Trim();
            desarrolladorAnterior.telefono = txtTelefono.Text.Trim();
            desarrolladorAnterior.numeroCuenta = txtNumeroCuenta.Text.Trim();

            if (double.TryParse(txtIngresoTotal.Text.Trim(), out double ingreso))
            {
                desarrolladorAnterior.ingresoTotal = ingreso;
            }
            else
            {
                lblMensaje.Text = "El ingreso total no es válido.";
                return;
            }

            try
            {
                boDesarrollador.modificarDesarrollador(desarrolladorAnterior);
                Response.Redirect("ListarDesarrolladores.aspx");
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al modificar: " + ex.Message;
            }
        }




        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarDesarrolladores.aspx");
        }
    }
}