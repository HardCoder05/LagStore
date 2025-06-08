<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="RegistrarDesarrollador.aspx.cs" Inherits="LagStoreWA.RegistrarDesarrollador" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Modificar Desarrollador
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
    <h2 class="mb-4">Modificar Desarrollador</h2>
    <asp:HiddenField ID="hfIdDesarrollador" runat="server" />

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
        <label for="txtNumeroCuenta" class="form-label">Número de Cuenta</label>
        <asp:TextBox ID="txtNumeroCuenta" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtIngresoTotal" class="form-label">Ingreso Total (S/.)</label>
        <asp:TextBox ID="txtIngresoTotal" runat="server" CssClass="form-control" />
    </div>

    <asp:Button ID="btnGuardar" runat="server" Text="Guardar Cambios" CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
    <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary ms-2" OnClick="btnCancelar_Click" />

    <asp:Label ID="lblMensaje" runat="server" CssClass="mt-3 text-danger"></asp:Label>
</asp:Content>
