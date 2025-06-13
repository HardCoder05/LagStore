<%@ Page Title="Mi Biblioteca" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="Biblioteca.aspx.cs" Inherits="LagStoreWA.Biblioteca" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Biblioteca - Mis Juegos
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        body{
            background: #1a1a2e;
            color: white;
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
   <div class="row mb-4">
        <div class="col-md-4">
            <div class="card bg-dark text-white border-secondary">
                <div class="card-body text-center">
                    <h5 class="card-title">Total de Juegos</h5>
                    <h2 class="text-primary" id="lblTotalJuegos" runat="server">0</h2>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card bg-dark text-white border-secondary">
                <div class="card-body text-center">
                    <h5 class="card-title">Juegos Mostrados</h5>
                    <h2 class="text-success" id="lblJuegosMostrados" runat="server">0</h2>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card bg-dark text-white border-secondary">
                <div class="card-body text-center">
                    <h5 class="card-title">Tiempo Total Jugado</h5>
                    <h2 class="text-warning" id="lblTiempoTotal" runat="server">0 hrs</h2>
                </div>
            </div>
        </div>
    </div>

    <!-- Filtros mejorados -->
    <div class="card bg-dark text-white border-secondary mb-4">
        <div class="card-body">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <label class="text-white fw-bold mb-2">Filtrar por estado:</label>
                    <asp:RadioButtonList ID="rblFiltro" runat="server" AutoPostBack="true"
                        OnSelectedIndexChanged="rblFiltro_SelectedIndexChanged" RepeatDirection="Horizontal"
                        CssClass="text-white d-flex gap-3">
                        <asp:ListItem Text="Todos" Value="todos" Selected="True"></asp:ListItem>
                        <asp:ListItem Text="Mostrados" Value="mostrados"></asp:ListItem>
                        <asp:ListItem Text="Ocultos" Value="ocultos"></asp:ListItem>
                    </asp:RadioButtonList>
                </div>
                <div class="col-md-6">
                    <label class="text-white fw-bold mb-2">Buscar juego:</label>
                    <div class="d-flex gap-2">
                        <asp:TextBox ID="txtBuscar" runat="server" CssClass="form-control bg-secondary text-white border-secondary"
                            placeholder="Buscar por título..." />
                        <asp:Button ID="btnBuscar" runat="server" Text="Buscar"
                            CssClass="btn btn-outline-light" OnClick="btnBuscar_Click" />
                        <asp:Button ID="btnLimpiarBusqueda" runat="server" Text="Limpiar"
                            CssClass="btn btn-outline-secondary" OnClick="btnLimpiarBusqueda_Click" />
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Lista de juegos -->
    <asp:Repeater ID="rptBiblioteca" runat="server">
        <ItemTemplate>
            <div class="card game-card mb-4 p-3 shadow-sm bg-dark text-white border-secondary">
                <div class="row align-items-center">
                    <div class="col-md-2">
                        <img src='<%# Eval("juego.imagen") %>'
                            class="img-fluid rounded" alt="<%# Eval("juego.titulo") %>" style="max-height: 120px; object-fit: cover;" />
                    </div>
                    <div class="col-md-7">
                        <div class="d-flex justify-content-between align-items-start">
                            <div>
                                <h5 class="text-white fw-bold mb-2"><%# Eval("juego.titulo") %></h5>
                                <div class="row">
                                    <div class="col-6">
                                        <p class="text-light mb-1"><strong>Género:</strong> <%# Eval("juego.genero") %></p>
                                        <p class="text-light mb-1"><strong>Modelo:</strong> <%# Eval("juego.modeloNegocio") %></p>
                                        <p class="text-light mb-1"><strong>Precio:</strong> $<%# Eval("juego.precio", "{0:F2}") %></p>
                                    </div>
                                    <div class="col-6">
                                        <p class="text-light mb-1"><strong>Adquirido:</strong> <%# Eval("fechaAdquisicion", "{0:dd/MM/yyyy}") %></p>
                                        <p class="text-light mb-1"><strong>Tiempo Jugado:</strong> <%# Eval("tiempoJuego") %> hrs</p>
                                        <p class="text-light mb-1">
                                            <strong>Última Sesión:</strong>
                                            <%# Eval("ultimaSesion") != null ? Eval("ultimaSesion", "{0:dd/MM/yyyy}") : "Nunca" %>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="badge fs-6 <%# Convert.ToInt32(Eval("activo")) == 1 ? "bg-success" : "bg-warning text-dark" %>">
                                <%# Convert.ToInt32(Eval("activo")) == 1 ? "Visible" : "Oculto" %>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 text-end">
                        <div class="btn-group-vertical gap-2" role="group">
                            <asp:Button ID="btnOcultar" runat="server"
                                CommandArgument='<%# Eval("juego.idJuego") + "," + Eval("biblioteca.idBiblioteca") %>'
                                Text='<%# (Convert.ToInt32(Eval("activo")) == 1) ? "Ocultar" : "Mostrar" %>'
                                CssClass='<%# (Convert.ToInt32(Eval("activo")) == 1) ? "btn btn-outline-warning" : "btn btn-outline-success" %>'
                                OnClick="btnOcultar_Click" />
                            <asp:Button ID="btnEliminar" runat="server"
                                CommandArgument='<%# Eval("juego.idJuego") + "," + Eval("biblioteca.idBiblioteca") %>'
                                Text="Eliminar"
                                CssClass="btn btn-outline-danger"
                                OnClick="btnEliminar_Click"
                                OnClientClick="return confirm('¿Estás seguro de eliminar este juego de tu biblioteca? Esta acción no se puede deshacer.');" />
                        </div>
                    </div>
                </div>
            </div>
        </ItemTemplate>
    </asp:Repeater>

    <!-- Panel de mensaje -->
    <asp:Panel ID="pnlMensaje" runat="server" CssClass="alert alert-info bg-info text-white border-info" Visible="false">
        <asp:Label ID="lblMensaje" runat="server"></asp:Label>
    </asp:Panel>
</asp:Content>
