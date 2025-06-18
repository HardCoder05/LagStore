
<%@ Page Title="Carro de Compras" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="CarroCompra.aspx.cs" Inherits="LagStoreWA.CarroCompra" %>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
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
    <div class="carro-container">
        <div class="carro-title">Gestión del Carro de Compras</div>
        <asp:Label ID="lblMensaje" runat="server" ForeColor="Red" />
        <asp:GridView ID="gvCarro" runat="server" AutoGenerateColumns="False" CssClass="table table-striped" DataKeyNames="idJuego" OnRowDeleting="GvCarro_RowDeleting">
            <Columns>
                <%-- Columna de miniatura de imagen del juego --%>
                <asp:TemplateField HeaderText="Imagen">
                    <ItemTemplate>
                        <img src='<%# Eval("imagen") %>' alt="miniatura" style="max-width: 100px; height: auto;" />
                    </ItemTemplate>
                </asp:TemplateField>
                <%-- Se utiliza 'titulo' en vez de 'nombre' ya que es la propiedad correcta en el objeto juego --%>
                <asp:BoundField DataField="titulo" HeaderText="Juego" />
                <asp:TemplateField HeaderText="Cantidad">
                    <ItemTemplate>
                        1
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:BoundField DataField="precio" HeaderText="Precio Unitario" DataFormatString="{0:C}" />
                <asp:TemplateField HeaderText="Subtotal">
                    <ItemTemplate>
                        <%# Eval("precio", "{0:C}") %>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:CommandField ShowDeleteButton="True" DeleteText="Quitar" />
            </Columns>
        </asp:GridView>
        
        <!-- Label para mostrar el total de la compra -->
        <div class="text-end" style="margin-top:20px;">
            <asp:Label ID="lblTotal" runat="server" CssClass="h4" Text=""></asp:Label>
        </div>

         <asp:Panel ID="pnlProcesando" runat="server" Visible="false">
             <asp:Image ID="imgEstadoCompra" runat="server" />
             <asp:Label ID="lblProcesando" runat="server" Text=""></asp:Label>
         </asp:Panel>

        <div class="carro-actions" style="margin-top:20px;">
            <asp:Button ID="btnFinalizarCompra" runat="server" Text="Finalizar Compra" CssClass="btn btn-success" OnClick="btnFinalizarCompra_Click" />
        </div>
    </div>
</asp:Content>