<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="JuegosMasVendidos.aspx.cs" Inherits="LagStoreWA.JuegosMasVendidos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Juegos más vendidos
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .table thead {
            background-color: #1f2937;
            color: white;
            font-size: 1.1rem;
            text-transform: uppercase;
        }

        .table tbody tr:hover {
            background-color: #f0f0f0;
            cursor: pointer;
        }

        .titulo-col {
            color: #1d4ed8;
            font-weight: 600;
            font-size: 1.1rem;
        }

        .ventas-col {
            color: #f59e0b;
            font-weight: 600;
        }

        .table-container {
            background-color: #ffffff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="mb-4 text-center">💵 Los 10 primeros juegos con más ventas</h2>

    <asp:GridView ID="gvMasVendidos" runat="server" AutoGenerateColumns="false"
        CssClass="table table-bordered table-hover text-center" EmptyDataText="No hay datos disponibles.">
        <Columns>
            <asp:BoundField DataField="titulo" HeaderText="Título" />
            <asp:BoundField DataField="cantidadVentas" HeaderText="Cantidad de Ventas" />
        </Columns>
    </asp:GridView>
</asp:Content>
