<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="RegistroUsuario.aspx.cs" Inherits="LagStoreWA.RegistroUsuario" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Registro de usuario</title>

    <link href="Content/bootstrap.css" rel="stylesheet" />
    <link href="Content/Fonts/css/all.css" rel="stylesheet" />
    <link href="Content/site.css" rel="stylesheet" />

    <script src="Scripts/bootstrap.js"></script>
    <script src="Scripts/bootstrap.bundle.js"></script>
    <script src="Scripts/jquery-3.7.1.js"></script>
</head>
<body style="background: #1a1a2e; color: white;">
    <form id="formRegistro" runat="server">
        <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
            <div class="card p-4 w-100" style="max-width: 500px;">
                <h3 class="card-title text-center mb-4">Registro de Usuario</h3>

                <div class="form-group mb-3">
                    <label for="ddlTipoUsuario">Tipo de usuario</label>
                    <asp:DropDownList ID="ddlTipoUsuario" runat="server" CssClass="form-select" AutoPostBack="true" OnSelectedIndexChanged="ddlTipoUsuario_SelectedIndexChanged">
                        <asp:ListItem Text="Seleccionar" Value="" />
                        <asp:ListItem Text="Desarrollador" Value="Desarrollador" />
                        <asp:ListItem Text="Jugador" Value="Jugador" />
                        <asp:ListItem Text="Administrador" Value="Administrador" />
                    </asp:DropDownList>
                </div>

                <div class="form-group mb-3">
                    <label for="txtNombre">Nombre completo<strong class="text-danger">*</strong></label>
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Luis Perez"/>
                </div>

                <div class="form-group mb-3">
                    <label for="txtEmail">Correo electrónico<strong class="text-danger">*</strong></label>
                    <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" TextMode="Email" placeholder="correo@email.com"/>
                </div>

                <div class="form-group mb-3">
                    <label for="txtContrasena">Contraseña<strong class="text-danger">*</strong></label>
                    <asp:TextBox ID="txtContrasena" runat="server" CssClass="form-control" TextMode="Password" />
                </div>

                <div class="form-group mb-3">
                    <label for="txtTelefono">Teléfono<strong class="text-danger">*</strong></label>
                    <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" placeholder="987654321"/>
                </div>


                <div class="form-group mb-3">
                    <label for="txtFoto">Foto de perfil (URL)<strong class="text-danger">*</strong></label>
                    <asp:TextBox ID="txtFoto" runat="server" CssClass="form-control" />
                </div>


                <%--<div class="form-group mb-3">
                    <label for="ddlTipoUsuario">Tipo de usuario</label>
=======
                <div class="form-group mb-3">
                    <label for="ddlTipoUsuario">Tipo de usuario<strong class="text-danger">*</strong></label>
>>>>>>> 8c9027bf97565f084fba51067306ff424c4f6a8e
                    <asp:DropDownList ID="ddlTipoUsuario" runat="server" CssClass="form-select" AutoPostBack="true" OnSelectedIndexChanged="ddlTipoUsuario_SelectedIndexChanged">
                        <asp:ListItem Text="Seleccionar" Value="" />
                        <asp:ListItem Text="Desarrollador" Value="Desarrollador" />
                        <asp:ListItem Text="Jugador" Value="Jugador" />
                        <asp:ListItem Text="Administrador" Value="Administrador" />
                    </asp:DropDownList>
                </div>--%>

                <!-- Panel Desarrollador -->
                <asp:Panel ID="pnlDesarrollador" runat="server" Visible="false">
                    <div class="form-group mb-3">
                        <label for="txtNumeroCuenta">Número de cuenta<strong class="text-danger">*</strong></label>
                        <asp:TextBox ID="txtNumeroCuenta" runat="server" CssClass="form-control" placeholder="01826693009783244336"/>
                    </div>
                    <div class="form-group mb-3">
                        <label for="txtIngresoTotal">Ingreso total<strong class="text-danger">*</strong></label>
                        <asp:TextBox ID="txtIngresoTotal" runat="server" CssClass="form-control" TextMode="Number" Text="0"/>
                    </div>
                </asp:Panel>

                <!-- Panel Jugador -->
                <asp:Panel ID="pnlJugador" runat="server" Visible="false">
                    <div class="form-group mb-3">
                        <label for="txtNickname">Nickname<strong class="text-danger">*</strong></label>
                        <asp:TextBox ID="txtNickname" runat="server" CssClass="form-control" placeholder="Crack25"/>
                    </div>
                </asp:Panel>

                <!-- Panel Administrador -->
                <asp:Panel ID="pnlAdministrador" runat="server" Visible="false">
                    <div class="form-group mb-3">
                        <label for="txtRolAdmin">Rol del administrador<strong class="text-danger">*</strong></label>
                        <asp:TextBox ID="txtRolAdmin" runat="server" CssClass="form-control" placeholder="Supervisor"/>
                    </div>
                </asp:Panel>

                <!-- Mensaje de validación -->
                <div class="form-group text-center mb-2">
                    <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger d-block" EnableViewState="false"></asp:Label>
                </div>

                <!-- Botón Registrar -->
                <div class="text-center">
                    <asp:Button ID="btnRegistrar" runat="server" Text="Registrar" CssClass="btn btn-success w-100" OnClick="btnRegistrar_Click" />
                </div>
            </div>
        </div>
    </form>
</body>
</html>
