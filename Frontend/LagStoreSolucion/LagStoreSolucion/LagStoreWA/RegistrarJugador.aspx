<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="RegistrarJugador.aspx.cs" Inherits="LagStoreWA.RegistrarJugador" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Modificar Jugador
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .form-label {
            font-weight: bold;
        }
        .btn-primary {
            background-color: #2563eb;
            border-color: #2563eb;
        }
        .btn-primary:hover {
            background-color: #1e40af;
            border-color: #1e40af;
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="mb-4">Modificar Jugador</h2>
    <asp:HiddenField ID="hfIdJugador" runat="server" />

    <div class="mb-3">
        <label for="txtNickname" class="form-label">Nickname</label>
        <asp:TextBox ID="txtNickname" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtNombre" class="form-label">Nombre</label>
        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtEmail" class="form-label">Email</label>
        <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtTelefono" class="form-label">Teléfono</label>
        <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtFotoPerfil" class="form-label">Foto de Perfil</label>
        <asp:TextBox ID="txtFotoPerfil" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtContrasena" class="form-label">Contraseña</label>
        <asp:TextBox ID="txtContrasena" runat="server" CssClass="form-control" TextMode="Password" />
    </div>

    <asp:Button ID="btnGuardar" runat="server" Text="Guardar Cambios" CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
    <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary ms-2" OnClick="btnCancelar_Click" />

    <asp:Label ID="lblMensaje" runat="server" CssClass="mt-3 text-danger"></asp:Label>
</asp:Content>
