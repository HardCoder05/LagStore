<%@ Page Title="Carro de Compras" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="CarroCompra.aspx.cs" Inherits="LagStoreWA.CarroCompra" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        .carro-container {
            max-width: 900px;
            margin: 30px auto;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }
        .carro-title {
            font-size: 2em;
            margin-bottom: 20px;
        }
        .carro-actions {
            margin-top: 20px;
            text-align: right;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="carro-container">
        <div class="carro-title">Gestión del Carro de Compras</div>
        <asp:Label ID="lblMensaje" runat="server" ForeColor="Red" />
        <asp:GridView ID="gvCarro" runat="server" AutoGenerateColumns="False" CssClass="table table-striped" DataKeyNames="IdProducto">
            <Columns>
                <asp:BoundField DataField="NombreProducto" HeaderText="Producto" />
                <asp:BoundField DataField="Cantidad" HeaderText="Cantidad" />
                <asp:BoundField DataField="PrecioUnitario" HeaderText="Precio Unitario" DataFormatString="{0:C}" />
                <asp:BoundField DataField="Subtotal" HeaderText="Subtotal" DataFormatString="{0:C}" />
                <asp:CommandField ShowDeleteButton="True" DeleteText="Quitar" />
            </Columns>
        </asp:GridView>
        <div class="carro-actions">
            <asp:Button ID="btnFinalizarCompra" runat="server" Text="Finalizar Compra" CssClass="btn btn-success" OnClick="btnFinalizarCompra_Click" />
        </div>
    </div>
</asp:Content>