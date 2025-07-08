<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="OrdenaJuego.aspx.cs" Inherits="LagStoreWA.OrdenaJuego" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .search-box {
            max-width: 300px;
            margin-bottom: 20px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="mb-4">Lista de Juegos</h2>

    <div class="form-group mb-3">
        <label for="ddlOrden">Ordenar por:</label>
        <asp:DropDownList ID="ddlOrden" runat="server" CssClass="form-control w-25 d-inline"
            AutoPostBack="true" OnSelectedIndexChanged="ddlOrden_SelectedIndexChanged">
            <asp:ListItem Text="Precio" Value="precio" />
            <asp:ListItem Text="Fecha de Lanzamiento" Value="fechaLanzamiento" />
            <asp:ListItem Text="Espacio en Disco" Value="espacioDisco" />
        </asp:DropDownList>
        <asp:HyperLink ID="lnkReporteDesarrollador" runat="server" NavigateUrl="~/ReporteDesarrollador.aspx" CssClass="btn btn-danger ms-2">   <!-- ← aquí el ms-2 -->
<i class="fas fa-file-pdf me-1"></i> Reporte Ventas
        </asp:HyperLink>
    </div>
    <div class="input-group search-box">
        <input type="text" class="form-control" placeholder="Buscar por ID" id="txtBuscar" runat="server" />
        <button class="btn btn-primary" type="button" id="btnBuscar" runat="server" onserverclick="btnBuscar_ServerClick">
            <i class="fas fa-search"></i>
        </button>
    </div>

    <asp:Label ID="lblMensaje" runat="server" ForeColor="Red" CssClass="mt-2 d-block"></asp:Label>

    <asp:GridView ID="gvJuegos" runat="server" AutoGenerateColumns="false" CssClass="table table-striped table-hover"
        DataKeyNames="idJuego">
        <Columns>
            <asp:BoundField DataField="idJuego" HeaderText="ID" />
            <asp:BoundField DataField="titulo" HeaderText="Título" />
            <asp:BoundField DataField="genero" HeaderText="Género" />
            <asp:BoundField DataField="modeloNegocio" HeaderText="Modelo de Negocio" />
            <asp:BoundField DataField="precio" HeaderText="Precio" DataFormatString="{0:C}" />
            <asp:BoundField DataField="fechaLanzamiento" HeaderText="Fecha de Lanzamiento" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:BoundField DataField="espacioDisco" HeaderText="Espacio en Disco (GB)" DataFormatString="{0:F2}" />
        </Columns>
    </asp:GridView>
</asp:Content>
