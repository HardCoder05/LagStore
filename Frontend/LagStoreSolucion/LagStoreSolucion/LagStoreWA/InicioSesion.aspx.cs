using LagStoreWA.ServicesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LagStoreWA
{
    public partial class InicioSesion : System.Web.UI.Page
    {
        private UsuarioWSClient usuarioWSClient;
        protected void Page_Load(object sender, EventArgs e)
        {
            usuarioWSClient = new UsuarioWSClient();
        }
        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string email = txtUsername.Text.Trim();
            string password = txtPassword.Text.Trim();

            // Validaciones mínimas
            if (string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
            {
                lblMensaje.Text = "Por favor, ingrese su usuario y contraseña.";
                return;
            }

            try
            {
                usuario usuarioIngresado = new usuario
                {
                    email = email,
                    contrasena = password
                };

                int idUsuario = usuarioWSClient.verificaUsuario(usuarioIngresado);

                if (idUsuario > 0)
                {
                    // Guardar el ID del usuario y email en sesión
                    Session["usuarioId"] = idUsuario;
                    Session["usuarioEmail"] = email;
                    Session["Usuario"] = usuarioIngresado;

                    // Crear cookie de autenticación
                    FormsAuthenticationTicket ticket = new FormsAuthenticationTicket(
                        1,
                        email,
                        DateTime.Now,
                        DateTime.Now.AddMinutes(30),
                        false,
                        "rol=usuario" // Aquí podrías agregar más datos si quieres
                    );

                    string encryptedTicket = FormsAuthentication.Encrypt(ticket);
                    HttpCookie authCookie = new HttpCookie(FormsAuthentication.FormsCookieName, encryptedTicket)
                    {
                        Expires = ticket.Expiration,
                        Path = FormsAuthentication.FormsCookiePath
                    };
                    Response.Cookies.Add(authCookie);

                    // Redirección
                    string returnUrl = Request.QueryString["ReturnUrl"];
                    if (!string.IsNullOrEmpty(returnUrl))
                        Response.Redirect(returnUrl, true);
                    else
                        Response.Redirect("Home.aspx", true);
                }
                else
                {
                    lblMensaje.Text = "Usuario o contraseña incorrectos.";
                }
            }
            catch (Exception ex)
            {
                // Registro opcional de errores internos
                lblMensaje.Text = "Ocurrió un error al iniciar sesión. Intente nuevamente.";
                // Log error: ex.Message
            }
        
        }
    }
}