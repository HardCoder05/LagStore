using LagStoreWA.ServicesWS;
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
        private JugadorWSClient jugadorWSClient;
        private DesarrolladorWSClient desarrolladorWSClient;
        private AdministradorWSClient administradorWSCLient;

        protected void Page_Load(object sender, EventArgs e)
        {
            jugadorWSClient = new JugadorWSClient();
            desarrolladorWSClient = new DesarrolladorWSClient();
            administradorWSCLient = new AdministradorWSClient();
        }

        protected void ddlTipoUsuario_SelectedIndexChanged(object sender, EventArgs e)
        {
            pnlDesarrollador.Visible = false;
            pnlJugador.Visible = false;
            pnlAdministrador.Visible = false;

            // Mostrar solo el panel correspondiente
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
            string tipoUsuario = ddlTipoUsuario.SelectedValue;

            // Validar tipo de usuario
            if (string.IsNullOrEmpty(tipoUsuario))
            {
                MostrarMensajeError("Por favor, seleccione un tipo de usuario.");
                return;
            }

            // Validar campos comunes
            if (!ValidarCamposComunes())
                return;

            if (tipoUsuario == "Jugador")
            {
                RegistrarJugador();
            }
            else if (tipoUsuario == "Desarrollador")
            {
                RegistrarDesarrollador();
            }
            else if (tipoUsuario == "Administrador")
            {
                RegistrarAdministrador();
            }
        }

        private bool ValidarCamposComunes()
        {
            if (string.IsNullOrWhiteSpace(txtNombre.Text))
            {
                MostrarMensajeError("El nombre completo es obligatorio.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtEmail.Text))
            {
                MostrarMensajeError("El correo electrónico es obligatorio.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtContrasena.Text))
            {
                MostrarMensajeError("La contraseña es obligatoria.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtTelefono.Text))
            {
                MostrarMensajeError("El teléfono es obligatorio.");
                return false;
            }

            return true;
        }

        private void RegistrarJugador()
        {
            try
            {
                if (string.IsNullOrWhiteSpace(txtNickname.Text))
                {
                    MostrarMensajeError("El nickname es obligatorio para jugadores.");
                    return;
                }

                jugador jugadorNuevo = new jugador
                {
                    nombre = txtNombre.Text.Trim(),
                    email = txtEmail.Text.Trim(),
                    contrasena = txtContrasena.Text.Trim(),
                    telefono = txtTelefono.Text.Trim(),
                    fotoDePerfil = txtFoto.Text.Trim(),
                    nickname = txtNickname.Text.Trim(),
                    fechaRegistro = DateTime.Now
                };

                int resultado = jugadorWSClient.insertarJugador(jugadorNuevo);

                if (resultado > 0)
                {
                    MostrarMensajeExito("Registro exitoso del jugador.");
                    LimpiarFormulario();
                }
                else
                {
                    MostrarMensajeError("No se pudo registrar el jugador.");
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError("Error al registrar jugador: " + ex.Message);
            }
        }

        private void RegistrarDesarrollador()
        {
            try
            {
                if (string.IsNullOrWhiteSpace(txtNumeroCuenta.Text))
                {
                    MostrarMensajeError("El número de cuenta es obligatorio para desarrolladores.");
                    return;
                }

                double ingresoTotal = 0;
                if (!string.IsNullOrWhiteSpace(txtIngresoTotal.Text))
                {
                    if (!double.TryParse(txtIngresoTotal.Text, out ingresoTotal))
                    {
                        MostrarMensajeError("El ingreso total debe ser un número válido.");
                        return;
                    }
                }

                desarrollador desarrolladorNuevo = new desarrollador
                {
                    nombre = txtNombre.Text.Trim(),
                    email = txtEmail.Text.Trim(),
                    contrasena = txtContrasena.Text.Trim(),
                    telefono = txtTelefono.Text.Trim(),
                    fotoDePerfil = txtFoto.Text.Trim(),
                    numeroCuenta = txtNumeroCuenta.Text.Trim(),
                    ingresoTotal = ingresoTotal,
                    fechaRegistro = DateTime.Now
                };

                int resultado = desarrolladorWSClient.insertarDesarrollador(desarrolladorNuevo);

                if (resultado > 0)
                {
                    MostrarMensajeExito("Registro exitoso del desarrollador."); // ✅ CORREGIDO
                    LimpiarFormulario();
                }
                else
                {
                    MostrarMensajeError("No se pudo registrar el desarrollador.");
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError("Error al registrar desarrollador: " + ex.Message);
            }
        }

        private void RegistrarAdministrador()
        {
            try
            {
                if (string.IsNullOrWhiteSpace(txtRolAdmin.Text))
                {
                    MostrarMensajeError("El rol administrativo es obligatorio para administradores.");
                    return;
                }

                administrador administradorNuevo = new administrador
                {
                    nombre = txtNombre.Text.Trim(),
                    email = txtEmail.Text.Trim(),
                    contrasena = txtContrasena.Text.Trim(),
                    telefono = txtTelefono.Text.Trim(),
                    fotoDePerfil = txtFoto.Text.Trim(),
                    rolAdministrativo = txtRolAdmin.Text.Trim(),
                    fechaRegistro = DateTime.Now
                };

                int resultado = administradorWSCLient.insertarAdministrador(administradorNuevo);

                if (resultado > 0)
                {
                    MostrarMensajeExito("Registro exitoso del administrador."); // ✅ CORREGIDO
                    LimpiarFormulario();
                }
                else
                {
                    MostrarMensajeError("No se pudo registrar el administrador.");
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError("Error al registrar administrador: " + ex.Message);
            }
        }

        private void MostrarMensajeError(string mensaje)
        {
            lblMensaje.CssClass = "text-danger d-block";
            lblMensaje.Text = mensaje;
        }

        private void MostrarMensajeExito(string mensaje)
        {
            lblMensaje.CssClass = "text-success d-block";
            lblMensaje.Text = mensaje;
        }

        private void LimpiarFormulario()
        {
            txtNombre.Text = "";
            txtEmail.Text = "";
            txtContrasena.Text = "";
            txtTelefono.Text = "";
            txtFoto.Text = "";
            txtNickname.Text = "";
            txtNumeroCuenta.Text = "";
            txtIngresoTotal.Text = "";
            txtRolAdmin.Text = "";
            ddlTipoUsuario.SelectedIndex = 0;

            pnlDesarrollador.Visible = false;
            pnlJugador.Visible = false;
            pnlAdministrador.Visible = false;
        }
    }
}