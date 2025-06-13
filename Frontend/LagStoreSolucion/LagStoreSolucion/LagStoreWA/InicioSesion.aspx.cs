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
        private rol rolUsuario = rol.Jugador;
        private JugadorWSClient jugadorWSClient = new JugadorWSClient();
        private DesarrolladorWSClient desarrolladorWSClient = new DesarrolladorWSClient();
        private AdministradorWSClient administradorWSClient = new AdministradorWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            usuarioWSClient = new UsuarioWSClient();
        }
        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string email = txtUsername.Text.Trim();
            string password = txtPassword.Text.Trim();

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
                    //// Obtener datos completos del usuario
                    //usuario usuarioCompleto = usuarioWSClient.obte(idUsuario);

                    //if (usuarioCompleto == null)
                    //{
                    //    lblMensaje.Text = "No se pudo cargar la información del usuario.";
                    //    return;
                    //}

                    //// Guardar en sesión
                    //Session["Usuario"] = usuarioCompleto;
                    //Session["UsuarioEmail"] = email;

                    Session["usuarioId"] = idUsuario;
                    rolUsuario = usuarioWSClient.obtenerRol(idUsuario);
                    Session["RolUsuario"] = rolUsuario;

                    switch (rolUsuario)
                    {
                        case rol.Jugador:
                            Session["Jugador"] = jugadorWSClient.obtenerJugadorPorID(idUsuario);
                            break;
                        case rol.Desarrollador:
                            Session["Desarrollador"] = desarrolladorWSClient.obtenerDesarrolladorPorID(idUsuario);
                            break;
                        case rol.Administrador:
                            Session["Administrador"] = administradorWSClient.obtenerAdministradorPorID(idUsuario);
                            break;
                        default:
                            lblMensaje.Text = "Rol de usuario no reconocido.";
                            return;
                    }

                    // Autenticación por cookie
                    FormsAuthenticationTicket ticket = new FormsAuthenticationTicket(
                        1, email, DateTime.Now, DateTime.Now.AddMinutes(30), false,
                        $"id={idUsuario};rol={rolUsuario}"
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
                    Response.Redirect(string.IsNullOrEmpty(returnUrl) ? "Home.aspx" : returnUrl, true);
                }
                else
                {
                    lblMensaje.Text = "Usuario o contraseña incorrectos.";
                }
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Ocurrió un error al iniciar sesión. Intente nuevamente.";
                // Log ex.Message si tienes logger
            }
        }

    }
}