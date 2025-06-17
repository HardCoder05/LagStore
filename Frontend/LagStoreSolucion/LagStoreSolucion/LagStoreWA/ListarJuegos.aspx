<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="ListarJuegos.aspx.cs" Inherits="LagStoreWA.ListarJuegos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Lista de Juegos
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .search-box {
            max-width: 300px;
            margin-bottom: 20px;
        }

        .btn-icon {
            padding: 6px 10px;
            border-radius: 5px;
        }

        .btn-modificar {
            background-color: #2563eb;
            color: white;
        }

        .btn-modificar:hover {
            background-color: #1e40af;
            color: white;
        }

        .btn-eliminar {
            background-color: #dc2626;
            color: white;
        }

        .btn-eliminar:hover {
            background-color: #991b1b;
            color: white;
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="mb-4">Lista de Juegos</h2>

    <div class="input-group search-box">
        <input type="text" class="form-control" placeholder="Buscar por Nombre" id="txtBuscar" runat="server" />
        <button class="btn btn-primary" type="button" id="btnBuscar" runat="server" onserverclick="btnBuscar_ServerClick">
            <i class="fas fa-search"></i>
        </button>
    </div>

    <asp:Label ID="lblMensaje" runat="server" ForeColor="Red" CssClass="mt-2 d-block"></asp:Label>

    <asp:GridView ID="gvJuegos" runat="server" AutoGenerateColumns="false" CssClass="table table-striped table-hover"
        OnRowCommand="gvJuegos_RowCommand" DataKeyNames="idJuego">
        <Columns>
            <asp:BoundField DataField="idJuego" HeaderText="ID" />
            <asp:BoundField DataField="titulo" HeaderText="Título" />
            <asp:BoundField DataField="genero" HeaderText="Género" />
            <asp:BoundField DataField="modeloNegocio" HeaderText="Modelo de Negocio" />
            <asp:BoundField DataField="precio" HeaderText="Precio" DataFormatString="{0:C}" />
            <asp:BoundField DataField="fechaLanzamiento" HeaderText="Fecha de Lanzamiento" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:TemplateField HeaderText="Acciones" ItemStyle-CssClass="text-center">
                <ItemTemplate>
                    <asp:LinkButton ID="btnModificar" runat="server" CommandName="Modificar" CommandArgument='<%# Eval("idJuego") %>'
                        CssClass="btn btn-modificar btn-icon me-2" ToolTip="Modificar">
                    <i class="fas fa-edit"></i>
                    </asp:LinkButton>
                    <asp:LinkButton ID="btnEliminar" runat="server" CommandName="Eliminar" CommandArgument='<%# Eval("idJuego") %>'
                        CssClass="btn btn-eliminar btn-icon" ToolTip="Eliminar">
                    <i class="fas fa-trash-alt"></i>
                    </asp:LinkButton>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
</asp:Content>

