<%@ Page Title="Recargas del jugador"
    Language="C#"
    MasterPageFile="~/LagStore.Master"
    CodeBehind="ListarRecargas.aspx.cs"
    Inherits="LagStoreWA.ListarRecargas" %>   

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Lista de Recargas
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <!-- Aquí puedes agregar scripts o CSS adicionales -->
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- TODO: Tu buscador y GridView van aquí, dentro del form de la Master -->
    <div class="input-group mb-3">
        <input id="txtBuscar" runat="server" type="text" class="form-control"
               placeholder="ID de recarga o vacío = todas" />
        <button id="btnBuscar" runat="server" 
                onserverclick="btnBuscar_ServerClick"
                class="btn btn-primary">
            Buscar
        </button>
    </div>

    <asp:Label ID="lblMensaje" runat="server"
               CssClass="text-danger fw-bold"></asp:Label>

    <asp:GridView ID="gvRecargas" runat="server"
        CssClass="table table-striped table-bordered"
        AutoGenerateColumns="false"
        OnRowCommand="gvRecargas_RowCommand">
        <Columns>
            <asp:BoundField DataField="idRecarga" HeaderText="ID" />
            <asp:BoundField DataField="fechaRecarga" HeaderText="Fecha"
                DataFormatString="{0:dd/MM/yyyy}" HtmlEncode="false" />
            <asp:BoundField DataField="monto" HeaderText="Monto"
                DataFormatString="{0:C}" HtmlEncode="false" />
            <asp:BoundField DataField="metodoPago" HeaderText="Método de pago" />
        </Columns>
    </asp:GridView>
</asp:Content>
