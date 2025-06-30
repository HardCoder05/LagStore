<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="SubirJuego.aspx.cs" Inherits="LagStoreWA.SubirJuego" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Subir Juego
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="mb-4">Subir un Nuevo Juego</h2>

    <div class="mb-3">
        <label>Título *</label>
        <asp:TextBox ID="txtTitulo" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Descripción *</label>
        <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="3" />
    </div>

    <div class="mb-3">
        <label>Versión *</label>
        <asp:TextBox ID="txtVersion" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Precio *</label>
        <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Fecha de lanzamiento *</label>
        <asp:TextBox ID="txtFechaLanzamiento" runat="server" CssClass="form-control" placeholder="yyyy-MM-dd" />
    </div>

    <div class="mb-3">
        <label>Requisitos mínimos *</label>
        <asp:TextBox ID="txtReqMin" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Requisitos recomendados *</label>
        <asp:TextBox ID="txtReqRec" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Espacio en disco (GB) *</label>
        <asp:TextBox ID="txtEspacio" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Fecha última actualización *</label>
        <asp:TextBox ID="txtFechaActualizacion" runat="server" CssClass="form-control" placeholder="yyyy-MM-dd" />
    </div>

    <div class="mb-3">
        <label>Género *</label>
        <asp:DropDownList ID="ddlGenero" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Modelo de negocio *</label>
        <asp:DropDownList ID="ddlModeloNegocio" runat="server" CssClass="form-control" />
    </div>

    <div class="mb-3">
        <label>Imagen (URL) *</label>
        <asp:TextBox ID="txtImagen" runat="server" CssClass="form-control" />
    </div>

    <asp:Button ID="btnSubir" runat="server" Text="Subir Juego" CssClass="btn btn-primary" OnClick="btnSubir_Click" />

    <asp:Label ID="lblMensaje" runat="server" CssClass="mt-3 d-block fw-bold" />
</asp:Content>

