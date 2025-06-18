<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="JuegosMayorCalificacion.aspx.cs" Inherits="LagStoreWA.JuegosMayorCalificacion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Juegos con Mayor Calificación
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .table thead {
            background-color: #1f2937;
            color: #fff;
        }

        .table tbody tr:hover {
            background-color: #f3f4f6;
        }

        .star-icon {
            color: #facc15;
            font-size: 1.2rem;
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="mb-4">⭐ Juegos con Mayor Calificación</h2>

    <asp:GridView ID="gvMayorCalificacion" runat="server" AutoGenerateColumns="false"
        CssClass="table table-hover text-center table-bordered" EmptyDataText="No hay datos disponibles.">
        <Columns>
            <asp:BoundField DataField="titulo" HeaderText="Título" />
            <asp:BoundField DataField="promedioCalificacion" HeaderText="Promedio de Calificación"
                            DataFormatString="{0:N2}" />
            <asp:BoundField DataField="cantidadCalificaciones" HeaderText="Cantidad de Calificaciones" />
        </Columns>
    </asp:GridView>
</asp:Content>
