<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="RegistrarJuego.aspx.cs" Inherits="LagStoreWA.RegistrarJuego" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Modificar Juego
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
    <h2 class="mb-4">Modificar Juego</h2>
    <asp:HiddenField ID="hfIdJuego" runat="server" />

    <div class="mb-3">
        <label for="txtTitulo" class="form-label">Título</label>
        <asp:TextBox ID="txtTitulo" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtDescripcion" class="form-label">Descripción</label>
        <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="3" />
    </div>

    <div class="mb-3">
        <label for="txtGenero" class="form-label">Género</label>
        <asp:TextBox ID="txtGenero" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtPrecio" class="form-label">Precio</label>
        <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label for="txtImagen" class="form-label">Imagen (URL)</label>
        <asp:TextBox ID="txtImagen" runat="server" CssClass="form-control" />
    </div>

    <asp:Button ID="btnGuardar" runat="server" Text="Guardar Cambios" CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
    <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary ms-2" OnClick="btnCancelar_Click" />

    <asp:Label ID="lblMensaje" runat="server" CssClass="mt-3 text-danger"></asp:Label>
</asp:Content>

