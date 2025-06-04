<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="ListarJugadores.aspx.cs" Inherits="LagStoreWA.ListarJugadores" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Lista de Jugadores
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
    <h2 class="mb-4">Lista de Jugadores</h2>

    <!-- Buscador por ID -->
    <div class="input-group search-box">
        <input type="text" class="form-control" placeholder="Buscar por ID" id="txtBuscar" runat="server" />
        <button class="btn btn-primary" type="button" id="btnBuscar" runat="server" onserverclick="btnBuscar_ServerClick">
            <i class="fas fa-search"></i>
        </button>
    </div>

    <!-- Tabla para mostrar jugadores -->
    <asp:GridView ID="gvJugadores" runat="server" AutoGenerateColumns="false" CssClass="table table-striped table-hover"
        OnRowCommand="gvJugadores_RowCommand" DataKeyNames="Id">
        <Columns>
            <asp:BoundField DataField="Id" HeaderText="ID" />
            <asp:BoundField DataField="Nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="Nickname" HeaderText="Nickname" />
            <asp:TemplateField HeaderText="Acciones" ItemStyle-CssClass="text-center">
                <ItemTemplate>
                    <asp:LinkButton ID="btnModificar" runat="server" CommandName="Modificar" CommandArgument='<%# Eval("Id") %>' 
                        CssClass="btn btn-modificar btn-icon me-2" ToolTip="Modificar">
                        <i class="fas fa-edit"></i>
                    </asp:LinkButton>
                    <asp:LinkButton ID="btnEliminar" runat="server" CommandName="Eliminar" CommandArgument='<%# Eval("Id") %>' 
                        CssClass="btn btn-eliminar btn-icon" OnClientClick="return confirm('¿Está seguro que desea eliminar este jugador?');" ToolTip="Eliminar">
                        <i class="fas fa-trash-alt"></i>
                    </asp:LinkButton>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
</asp:Content>

