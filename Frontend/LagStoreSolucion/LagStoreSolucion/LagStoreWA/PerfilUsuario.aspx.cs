using LagStoreWA.ServicesWS;
using System;
using System.IO;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Text.RegularExpressions;

namespace LagStoreWA
{
    public partial class PerfilUsuario : System.Web.UI.Page
    {
        private UsuarioWSClient wsUsuario;
        private usuario usuarioActual;
        private JugadorWSClient wsJugador;

        protected void Page_Load(object sender, EventArgs e)
        {
            // Verificar si el usuario está logueado
            if (Session["Usuario"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }

            wsUsuario = new UsuarioWSClient();
            usuarioActual = (usuario)Session["Usuario"];
            wsJugador = new JugadorWSClient();

            if (!IsPostBack)
            {
                CargarDatosUsuario();
            }
        }

        private void CargarDatosUsuario()
        {
            try
            {
                // Obtener datos actualizados del usuario
                jugador juga = wsJugador.obtenerJugadorPorID(11);
                //Session["Jugador"] = juga;
                usuario usu = new usuario
                {
                    idUsuario = juga.idJugador,
                    nombre = juga.nombre,
                    email = juga.email,
                    telefono = juga.telefono,
                    contrasena = juga.contrasena,
                    fechaRegistro = juga.fechaRegistro,
                    fotoDePerfil = juga.fotoDePerfil,
                    rolUsuario = juga.rolUsuario,
                    activo = juga.activo
                };

                if (usu != null)
                {
                    // Actualizar información en la sesión
                    Session["Usuario"] = usu;
                    usuarioActual = usu;

                    // Cargar datos en el header
                    lblNombreUsuario.Text = juga.nickname;
                    lblEmailUsuario.Text = usu.email;
                    lblFechaRegistro.Text = FormatearFecha(usu.fechaRegistro);

                    // Estado del usuario
                    if (usu.activo == 1)
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
                    txtNombre.Text = usu.nombre;
                    txtEmail.Text = usu.email;
                    txtTelefono.Text = usu.telefono ?? "";

                    // Información adicional
                    lblRolUsuario.Text = usu.rolUsuario.ToString();
                    lblIdUsuario.Text = usu.idUsuario.ToString();
                    lblUltimoAcceso.Text = DateTime.Now.ToString("dd/MM/yyyy HH:mm");

                    // Cargar foto de perfil
                    //CargarFotoPerfil();
                    imgPerfil.ImageUrl = usu.fotoDePerfil;
                    imgPerfilGrande.ImageUrl = usu.fotoDePerfil;
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

        private void CargarFotoPerfil(string rutaFoto)
        {
            string urlFoto = "~/Assets/default-avatar.jpg";

            if (!string.IsNullOrEmpty(rutaFoto))
            {
                string rutaCompleta = Server.MapPath("~/Assets/ProfilePictures/" + rutaFoto);
                if (File.Exists(rutaCompleta))
                {
                    urlFoto = "~/Assets/ProfilePictures/" + rutaFoto;
                }
            }

            imgPerfil.ImageUrl = urlFoto;
            imgPerfilGrande.ImageUrl = urlFoto;
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

                // Crear objeto usuario con datos actualizados
                usuario usuarioActualizado = new usuario
                {
                    idUsuario = usuarioActual.idUsuario,
                    nombre = txtNombre.Text.Trim(),
                    email = txtEmail.Text.Trim(),
                    telefono = txtTelefono.Text.Trim(),
                    contrasena = usuarioActual.contrasena, // Mantener la contraseña actual
                    fechaRegistro = usuarioActual.fechaRegistro,
                    fotoDePerfil = usuarioActual.fotoDePerfil,
                    rolUsuario = usuarioActual.rolUsuario,
                    activo = usuarioActual.activo
                };

                // Verificar si se está cambiando la contraseña
                if (!string.IsNullOrEmpty(txtContrasenaActual.Text) ||
                    !string.IsNullOrEmpty(txtNuevaContrasena.Text) ||
                    !string.IsNullOrEmpty(txtConfirmarContrasena.Text))
                {
                    if (!ValidarCambioContrasena())
                        return;

                    usuarioActualizado.contrasena = txtNuevaContrasena.Text;
                }

                // Actualizar usuario
                jugador jug = new jugador();
                wsJugador = new JugadorWSClient();
                jug.idUsuario = usuarioActualizado.idUsuario;
                jug.nombre = usuarioActualizado.nombre;
                jug.email = usuarioActualizado.email;
                jug.telefono = usuarioActualizado.telefono;
                jug.contrasena = usuarioActualizado.contrasena;
                jug.fechaRegistro = usuarioActualizado.fechaRegistro;
                jug.fotoDePerfil = usuarioActualizado.fotoDePerfil;
                jug.rolUsuario = usuarioActualizado.rolUsuario;
                jug.activo = usuarioActualizado.activo;

                int resultado = wsJugador.modificarJugador(jug);

                if (resultado > 0)
                {
                    // Actualizar sesión
                    Session["Usuario"] = usuarioActualizado;

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

            // Verificar contraseña actual
            if (txtContrasenaActual.Text != usuarioActual.contrasena)
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

        protected void btnSubirFoto_Click(object sender, EventArgs e)
        {
            try
            {
                if (fuFotoPerfil.HasFile)
                {
                    // Validar tipo de archivo
                    string extension = Path.GetExtension(fuFotoPerfil.FileName).ToLower();
                    if (extension != ".jpg" && extension != ".jpeg" && extension != ".png")
                    {
                        MostrarMensaje("Solo se permiten archivos JPG y PNG", "alert-warning");
                        return;
                    }

                    // Validar tamaño (2MB máximo)
                    if (fuFotoPerfil.PostedFile.ContentLength > 2 * 1024 * 1024)
                    {
                        MostrarMensaje("El archivo no puede ser mayor a 2MB", "alert-warning");
                        return;
                    }

                    // Crear directorio si no existe
                    string directorioFotos = Server.MapPath("~/Assets/ProfilePictures/");
                    if (!Directory.Exists(directorioFotos))
                    {
                        Directory.CreateDirectory(directorioFotos);
                    }

                    // Generar nombre único para el archivo
                    string nombreArchivo = "perfil_" + usuarioActual.idUsuario + "_" +
                                         DateTime.Now.ToString("yyyyMMddHHmmss") + extension;
                    string rutaCompleta = Path.Combine(directorioFotos, nombreArchivo);

                    // Eliminar foto anterior si existe
                    if (!string.IsNullOrEmpty(usuarioActual.fotoDePerfil))
                    {
                        string fotoAnterior = Path.Combine(directorioFotos, usuarioActual.fotoDePerfil);
                        if (File.Exists(fotoAnterior))
                        {
                            File.Delete(fotoAnterior);
                        }
                    }

                    // Guardar nueva foto
                    fuFotoPerfil.SaveAs(rutaCompleta);

                    // Actualizar base de datos
                    usuario usuarioActualizado = usuarioActual;
                    usuarioActualizado.fotoDePerfil = nombreArchivo;

                    //bool resultado = wsUsuario.actualizarUsuario(usuarioActualizado);

                    //if (resultado)
                    //{
                    //    Session["Usuario"] = usuarioActualizado;
                    //    CargarFotoPerfil(nombreArchivo);
                    //    MostrarMensaje("Foto de perfil actualizada exitosamente", "alert-success");
                    //}
                    //else
                    //{
                    //    MostrarMensaje("Error al actualizar la foto de perfil", "alert-danger");
                    //}
                }
                else
                {
                    MostrarMensaje("Por favor seleccione una imagen", "alert-warning");
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al subir la foto: " + ex.Message, "alert-danger");
            }
        }

        // Métodos auxiliares
        private string FormatearFecha(DateTime fecha)
        {
            return fecha.ToString("dd 'de' MMMM 'de' yyyy");
        }

        private string FormatearRol(rol rolUsu)
        {
            switch (rolUsu)
            {
                case rol.Administrador:
                    return "Administrador";
                case rol.Jugador:
                    return "Jugador";
                default:
                    return "Usuario";
            }
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

        protected void Page_UnLoad(object sender, EventArgs e)
        {
            wsUsuario?.Close();
        }
    }
}