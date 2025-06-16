<%@ Page Title="Ordenar Juegos" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="OrdenaJuego.aspx.cs" Inherits="LagStoreWA.OrdenaJuego" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Ordenar Juegos
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">

    <div class="form-group mb-3">
        <label for="ddlOrden">Ordenar por:</label>
        <asp:DropDownList ID="ddlOrden" runat="server" CssClass="form-control w-25 d-inline" AutoPostBack="true" OnSelectedIndexChanged="ddlOrden_SelectedIndexChanged">
            <asp:ListItem Text="Precio" Value="precio" />
            <asp:ListItem Text="Calificación" Value="calificacion" />
            <asp:ListItem Text="Fecha de lanzamiento" Value="fecha" />
        </asp:DropDownList>
    </div>

    <asp:GridView ID="gvJuegos" runat="server" AutoGenerateColumns="False" CssClass="table table-bordered" EmptyDataText="No hay juegos registrados.">
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="precio" HeaderText="Precio (S/.)" DataFormatString="{0:F2}" />
            <asp:BoundField DataField="calificacionPromedio" HeaderText="Calificación" DataFormatString="{0:F1}" />
            <asp:BoundField DataField="fechaLanzamiento" HeaderText="Lanzamiento" DataFormatString="{0:yyyy-MM-dd}" />
        </Columns>
    </asp:GridView>

</asp:Content>
