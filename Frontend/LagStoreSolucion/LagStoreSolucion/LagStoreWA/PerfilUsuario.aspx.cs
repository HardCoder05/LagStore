using LagStoreWA.ServicesWS;
using System;
using System.Security.Cryptography;
using System.Text;
using System.Text.RegularExpressions;
using System.Web.UI.HtmlControls;

namespace LagStoreWA
{
    public partial class PerfilUsuario : System.Web.UI.Page
    {
        private JugadorWSClient wsJugador = new JugadorWSClient();
        private DesarrolladorWSClient wsDesarrollador = new DesarrolladorWSClient();
        private AdministradorWSClient wsAdministrador = new AdministradorWSClient();
        private jugador jugadorActual = null;
        private desarrollador desarrolladorActual = null;
        private administrador administradorActual = null;
        private rol rolUsuario = rol.Jugador;
        private int idUsuarioActual = 0;

        protected void Page_Load(object sender, EventArgs e)
        {
            // Verificar si el usuario está logueado
            if (Session["Jugador"] == null && Session["Desarrollador"] == null
                && Session["Administrador"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }

            idUsuarioActual = (int)Session["usuarioId"];
            rolUsuario = (rol)Session["RolUsuario"];

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

                CargarDatosUsuario();
            }
        }

        private void CargarDatosUsuario()
        {
            try
            {
                if (rolUsuario == rol.Jugador)
                {
                    // Cargar datos del jugador
                    jugadorActual = wsJugador.obtenerJugadorPorID(idUsuarioActual);

                    if (jugadorActual != null)
                    {
                        Session["Jugador"] = jugadorActual;
                        lblNombreUsuario.Text = jugadorActual.nickname;

                        lblEmailUsuario.Text = jugadorActual.email;
                        lblFechaRegistro.Text = FormatearFecha(jugadorActual.fechaRegistro);

                        // Estado del usuario
                        if (jugadorActual.activo == 1)
                        {
                            lblEstadoUsuario.Text = "Activo";
                            lblEstadoUsuario.CssClass = "status-badge status-active";
                            lblEstadoCuenta.Text = "Cuenta Activa";
                        }
                        else
                        {
                            lblEstadoUsuario.Text = "Inactivo";
                            lblEstadoUsuario.CssClass = "status-badge status-inactive";
                            lblEstadoCuenta.Text = "Cuenta Inactiva";
                        }


                        // Cargar datos en los formularios
                        txtNombre.Text = jugadorActual.nombre;
                        txtEmail.Text = jugadorActual.email;
                        txtTelefono.Text = jugadorActual.telefono ?? "";

                        // Información adicional
                        lblRolUsuario.Text = jugadorActual.rolUsuario.ToString();
                        lblIdUsuario.Text = jugadorActual.idJugador.ToString();
                        lblUltimoAcceso.Text = DateTime.Now.ToString("dd/MM/yyyy HH:mm");

                        // Cargar foto de perfil
                        imgPerfil.ImageUrl = jugadorActual.fotoDePerfil;
                        txtFotoPerfil.Text = jugadorActual.fotoDePerfil;
                    }
                }
                else if (rolUsuario == rol.Desarrollador)
                {
                    // Cargar datos del desarrollador
                    desarrolladorActual = wsDesarrollador.obtenerDesarrolladorPorID(idUsuarioActual);
                    if (desarrolladorActual != null)
                    {
                        Session["Desarrollador"] = desarrolladorActual;
                        lblNombreUsuario.Text = desarrolladorActual.nombre;

                        lblEmailUsuario.Text = desarrolladorActual.email;
                        lblFechaRegistro.Text = FormatearFecha(desarrolladorActual.fechaRegistro);

                        // Estado del usuario
                        if (desarrolladorActual.activo == 1)
                        {
                            lblEstadoUsuario.Text = "Activo";
                            lblEstadoUsuario.CssClass = "status-badge status-active";
                            lblEstadoCuenta.Text = "Cuenta Activa";
                        }
                        else
                        {
                            lblEstadoUsuario.Text = "Inactivo";
                            lblEstadoUsuario.CssClass = "status-badge status-inactive";
                            lblEstadoCuenta.Text = "Cuenta Inactiva";
                        }


                        // Cargar datos en los formularios
                        txtNombre.Text = desarrolladorActual.nombre;
                        txtEmail.Text = desarrolladorActual.email;
                        txtTelefono.Text = desarrolladorActual.telefono ?? "";

                        // Información adicional
                        lblRolUsuario.Text = desarrolladorActual.rolUsuario.ToString();
                        lblIdUsuario.Text = desarrolladorActual.idDesarrollador.ToString();
                        lblUltimoAcceso.Text = DateTime.Now.ToString("dd/MM/yyyy HH:mm");

                        // Cargar foto de perfil
                        imgPerfil.ImageUrl = desarrolladorActual.fotoDePerfil;
                       // txtFotoPerfil.Text = jugadorActual.fotoDePerfil;
                       //lo comente solo por el chistee
                        //Atributos específicos del desarrollador
                        NumCuenta.Visible = true;
                        lblNumCuenta.Text = desarrolladorActual.numeroCuenta;
                        IngresoTotal.Visible = true;
                        lblIngresoTotal.Text = desarrolladorActual.ingresoTotal.ToString("C2");
                    }
                }
                else if (rolUsuario == rol.Administrador)
                {
                    // Cargar datos del desarrollador
                    administradorActual = wsAdministrador.obtenerAdministradorPorID(idUsuarioActual);
                    if (administradorActual != null)
                    {
                        Session["Administrador"] = administradorActual;
                        lblNombreUsuario.Text = administradorActual.nombre;

                        lblEmailUsuario.Text = administradorActual.email;
                        lblFechaRegistro.Text = FormatearFecha(administradorActual.fechaRegistro);

                        // Estado del usuario
                        if (administradorActual.activo == 1)
                        {
                            lblEstadoUsuario.Text = "Activo";
                            lblEstadoUsuario.CssClass = "status-badge status-active";
                            lblEstadoCuenta.Text = "Cuenta Activa";
                        }
                        else
                        {
                            lblEstadoUsuario.Text = "Inactivo";
                            lblEstadoUsuario.CssClass = "status-badge status-inactive";
                            lblEstadoCuenta.Text = "Cuenta Inactiva";
                        }


                        // Cargar datos en los formularios
                        txtNombre.Text = administradorActual.nombre;
                        txtEmail.Text = administradorActual.email;
                        txtTelefono.Text = administradorActual.telefono ?? "";

                        // Información adicional
                        lblRolUsuario.Text = administradorActual.rolUsuario.ToString();
                        lblIdUsuario.Text = administradorActual.idAdministrador.ToString();
                        lblUltimoAcceso.Text = DateTime.Now.ToString("dd/MM/yyyy HH:mm");

                        // Cargar foto de perfil
                        imgPerfil.ImageUrl = administradorActual.fotoDePerfil;
                        txtFotoPerfil.Text = jugadorActual.fotoDePerfil;

                        // Atributos específicos del administrador
                        RolAdministrativo.Visible = true;
                        lblRolAdministrativo.Text = administradorActual.rolAdministrativo;
                    }
                }
                else
                {
                    MostrarMensaje("Error al cargar los datos del usuario", "alert-danger");
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al cargar el perfil: " + ex.Message, "alert-danger");
            }
        }

        protected void btnActualizar_Click(object sender, EventArgs e)
        {
            try
            {
                // Validar campos obligatorios
                if (string.IsNullOrEmpty(txtNombre.Text.Trim()))
                {
                    MostrarMensaje("El nombre es obligatorio", "alert-warning");
                    return;
                }

                if (string.IsNullOrEmpty(txtEmail.Text.Trim()))
                {
                    MostrarMensaje("El email es obligatorio", "alert-warning");
                    return;
                }

                // Validar formato de email
                if (!ValidarEmail(txtEmail.Text.Trim()))
                {
                    MostrarMensaje("El formato del email no es válido", "alert-warning");
                    return;
                }

                // Validar teléfono si se proporciona
                if (!string.IsNullOrEmpty(txtTelefono.Text.Trim()) && !ValidarTelefono(txtTelefono.Text.Trim()))
                {
                    MostrarMensaje("El formato del teléfono no es válido", "alert-warning");
                    return;
                }

                // Verificar si se está cambiando la contraseña
                bool cambiarContrasena = !string.IsNullOrEmpty(txtContrasenaActual.Text) ||
                                        !string.IsNullOrEmpty(txtNuevaContrasena.Text) ||
                                        !string.IsNullOrEmpty(txtConfirmarContrasena.Text);

                if (cambiarContrasena && !ValidarCambioContrasena())
                    return;

                // Actualizar según el rol del usuario
                bool resultado = false;

                if (rolUsuario == rol.Jugador)
                {
                    resultado = ActualizarJugador(cambiarContrasena);
                }
                else if (rolUsuario == rol.Desarrollador)
                {
                    resultado = ActualizarDesarrollador(cambiarContrasena);
                }
                else if (rolUsuario == rol.Administrador)
                {
                    resultado = ActualizarAdministrador(cambiarContrasena);
                }

                if (resultado)
                {
                    // Limpiar campos de contraseña
                    LimpiarCamposContrasena();
                    MostrarMensaje("Perfil actualizado exitosamente", "alert-success");
                    // Recargar datos
                    CargarDatosUsuario();
                }
                else
                {
                    MostrarMensaje("Error al actualizar el perfil", "alert-danger");
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al actualizar el perfil: " + ex.Message, "alert-danger");
            }
        }

        private bool ActualizarJugador(bool cambiarContrasena)
        {
            jugador jugadorActualizado = new jugador
            {
                idJugador = jugadorActual.idJugador,
                nickname = jugadorActual.nickname, // Mantener nickname original
                nombre = txtNombre.Text.Trim(),
                email = txtEmail.Text.Trim(),
                telefono = txtTelefono.Text.Trim(),
                contrasena = cambiarContrasena ? txtNuevaContrasena.Text : jugadorActual.contrasena,
                fechaRegistro = jugadorActual.fechaRegistro,
                fotoDePerfil = txtFotoPerfil.Text.Trim(),
                rolUsuario = jugadorActual.rolUsuario,
                activo = jugadorActual.activo
            };

            int resultado = wsJugador.modificarJugador(jugadorActualizado);

            if (resultado > 0)
            {
                Session["Jugador"] = jugadorActualizado;
                jugadorActual = jugadorActualizado;
                return true;
            }
            return false;
        }

        private bool ActualizarDesarrollador(bool cambiarContrasena)
        {
            desarrollador desarrolladorActualizado = new desarrollador
            {
                idDesarrollador = desarrolladorActual.idDesarrollador,
                nombre = txtNombre.Text.Trim(),
                email = txtEmail.Text.Trim(),
                telefono = txtTelefono.Text.Trim(),
                contrasena = cambiarContrasena ? txtNuevaContrasena.Text : desarrolladorActual.contrasena,
                fechaRegistro = desarrolladorActual.fechaRegistro,
                fotoDePerfil = txtFotoPerfil.Text.Trim(),
                rolUsuario = desarrolladorActual.rolUsuario,
                numeroCuenta = desarrolladorActual.numeroCuenta,
                ingresoTotal = desarrolladorActual.ingresoTotal,
                activo = desarrolladorActual.activo
            };

            int resultado = wsDesarrollador.modificarDesarrollador(desarrolladorActualizado);

            if (resultado > 0)
            {
                Session["Desarrollador"] = desarrolladorActualizado;
                desarrolladorActual = desarrolladorActualizado;
                return true;
            }
            return false;
        }

        private bool ActualizarAdministrador(bool cambiarContrasena)
        {
            administrador administradorActualizado = new administrador
            {
                idAdministrador = administradorActual.idAdministrador,
                nombre = txtNombre.Text.Trim(),
                email = txtEmail.Text.Trim(),
                telefono = txtTelefono.Text.Trim(),
                contrasena = cambiarContrasena ? txtNuevaContrasena.Text : administradorActual.contrasena,
                fechaRegistro = administradorActual.fechaRegistro,
                fotoDePerfil = txtFotoPerfil.Text.Trim(),
                rolUsuario = administradorActual.rolUsuario,
                rolAdministrativo = administradorActual.rolAdministrativo,
                activo = administradorActual.activo
            };

            int resultado = wsAdministrador.modificarAdministrador(administradorActualizado);

            if (resultado > 0)
            {
                Session["Administrador"] = administradorActualizado;
                administradorActual = administradorActualizado;
                return true;
            }
            return false;
        }

        private string ObtenerMD5(string texto)
        {
            using (MD5 md5 = MD5.Create())
            {
                byte[] inputBytes = Encoding.UTF8.GetBytes(texto);
                byte[] hashBytes = md5.ComputeHash(inputBytes);

                StringBuilder sb = new StringBuilder();
                foreach (byte b in hashBytes)
                    sb.Append(b.ToString("x2")); // formato hexadecimal

                return sb.ToString();
            }
        }

        private bool ValidarCambioContrasena()
        {
            // Verificar que todos los campos estén llenos
            if (string.IsNullOrEmpty(txtContrasenaActual.Text) ||
                string.IsNullOrEmpty(txtNuevaContrasena.Text) ||
                string.IsNullOrEmpty(txtConfirmarContrasena.Text))
            {
                MostrarMensaje("Todos los campos de contraseña son obligatorios", "alert-warning");
                return false;
            }

            // Verificar contraseña actual según el rol
            string contrasenaActual = "";
            if (rolUsuario == rol.Jugador)
            {
                jugadorActual = (jugador)Session["Jugador"];
                contrasenaActual = jugadorActual.contrasena;
            }
            else if (rolUsuario == rol.Desarrollador)
            {
                desarrolladorActual = (desarrollador)Session["Desarrollador"];
                contrasenaActual = desarrolladorActual.contrasena;
            }
            else if (rolUsuario == rol.Administrador)
            {
                administradorActual = (administrador)Session["Administrador"];
                contrasenaActual = administradorActual.contrasena;
            }

            string hashIngresado = ObtenerMD5(txtContrasenaActual.Text);

            if (hashIngresado != contrasenaActual)
            {
                MostrarMensaje("La contraseña actual no es correcta", "alert-warning");
                return false;
            }

            // Verificar que las contraseñas coincidan
            if (txtNuevaContrasena.Text != txtConfirmarContrasena.Text)
            {
                MostrarMensaje("Las contraseñas nuevas no coinciden", "alert-warning");
                return false;
            }

            // Verificar que la nueva contraseña sea diferente a la actual
            if (txtNuevaContrasena.Text == contrasenaActual)
            {
                MostrarMensaje("La nueva contraseña debe ser diferente a la actual", "alert-warning");
                return false;
            }

            // Validar fortaleza de la contraseña
            if (!ValidarFortalezaContrasena(txtNuevaContrasena.Text))
            {
                MostrarMensaje("La contraseña debe tener al menos 8 caracteres, incluyendo mayúsculas, minúsculas y números", "alert-warning");
                return false;
            }

            return true;
        }

        private bool ValidarFortalezaContrasena(string contrasena)
        {
            if (contrasena.Length < 8) return false;
            if (!Regex.IsMatch(contrasena, @"[a-z]")) return false;
            if (!Regex.IsMatch(contrasena, @"[A-Z]")) return false;
            if (!Regex.IsMatch(contrasena, @"[0-9]")) return false;
            return true;
        }

        //Métodos auxiliares
        private string FormatearFecha(DateTime fecha)
        {
            return fecha.ToString("dd 'de' MMMM 'de' yyyy");
        }

        private bool ValidarEmail(string email)
        {
            string patron = @"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$";
            return Regex.IsMatch(email, patron);
        }

        private bool ValidarTelefono(string telefono)
        {
            string patron = @"^[0-9+\-\s()]{7,15}$";
            return Regex.IsMatch(telefono, patron);
        }

        private void LimpiarCamposContrasena()
        {
            txtContrasenaActual.Text = "";
            txtNuevaContrasena.Text = "";
            txtConfirmarContrasena.Text = "";
        }

        private void MostrarMensaje(string mensaje, string tipo)
        {
            lblMensaje.Text = mensaje;
            pnlMensaje.CssClass = $"alert {tipo}";
            pnlMensaje.Visible = true;
        }

    }
}