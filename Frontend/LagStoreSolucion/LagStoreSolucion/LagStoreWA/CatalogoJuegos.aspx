<%@ Page Title="Catálogo de Juegos" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="CatalogoJuegos.aspx.cs" Inherits="LagStoreWA.CatalogoJuegos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    LagStore - Catálogo de Juegos
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .filter-card {
            background: #f8f9fa;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .game-card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border-radius: 12px;
            overflow: hidden;
        }
        .game-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0,0,0,0.15);
        }
        .game-image {
            height: 200px;
            object-fit: cover;
            width: 100%;
        }
        .price-tag {
            background: linear-gradient(45deg, #007bff, #0056b3);
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-weight: bold;
        }
        .free-tag {
            background: linear-gradient(45deg, #28a745, #1e7e34);
        }
        .filter-section {
            background: white;
            border-radius: 8px;
            padding: 1.5rem;
            margin-bottom: 1rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <div class="row">
        <!-- Panel de Filtros -->
        <div class="col-lg-3 col-md-4">
            <div class="filter-card p-4">
                <h5 class="fw-bold mb-4">
                    <i class="fas fa-filter text-primary me-2"></i>Filtros
                </h5>

                <!-- Filtro por título -->
                <div class="filter-section">
                    <label class="form-label fw-semibold">
                        <i class="fas fa-search me-1"></i>Buscar por título
                    </label>
                    <asp:TextBox ID="txtTitulo" runat="server" CssClass="form-control" 
                                placeholder="Nombre del juego..."></asp:TextBox>
                </div>

                <!-- Filtro por género -->
                <div class="filter-section">
                    <label class="form-label fw-semibold">
                        <i class="fas fa-tags me-1"></i>Género
                    </label>
                    <asp:DropDownList ID="ddlGenero" runat="server" CssClass="form-select">
                        <asp:ListItem Value="" Text="Todos los géneros" Selected="True"></asp:ListItem>
                    </asp:DropDownList>
                </div>

                <!-- Filtro por modelo de negocio -->
                <div class="filter-section">
                    <label class="form-label fw-semibold">
                        <i class="fas fa-dollar-sign me-1"></i>Modelo de Negocio
                    </label>
                    <asp:DropDownList ID="ddlModeloNegocio" runat="server" CssClass="form-select">
                        <asp:ListItem Value="" Text="Todos los modelos" Selected="True"></asp:ListItem>
                    </asp:DropDownList>
                </div>

                <!-- Filtro por rango de precio -->
                <div class="filter-section">
                    <label class="form-label fw-semibold">
                        <i class="fas fa-money-bill-wave me-1"></i>Rango de Precio
                    </label>
                    <div class="row">
                        <div class="col-6">
                            <asp:TextBox ID="txtPrecioMin" runat="server" CssClass="form-control" 
                                        TextMode="Number" placeholder="Mín." step="1"></asp:TextBox>
                        </div>
                        <div class="col-6">
                            <asp:TextBox ID="txtPrecioMax" runat="server" CssClass="form-control" 
                                        TextMode="Number" placeholder="Máx." step="1"></asp:TextBox>
                        </div>
                    </div>
                </div>

                <!-- Botones de acción -->
                <div class="d-grid gap-2">
                    <asp:Button ID="btnFiltrar" runat="server" Text="Aplicar Filtros" 
                               CssClass="btn btn-primary" OnClick="btnFiltrar_Click">
                    </asp:Button>
                    <asp:Button ID="btnLimpiar" runat="server" Text="Limpiar Filtros" 
                               CssClass="btn btn-outline-secondary" OnClick="btnLimpiar_Click">
                    </asp:Button>
                </div>
            </div>
        </div>

        <!-- Catálogo de Juegos -->
        <div class="col-lg-9 col-md-8">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="fw-bold text-dark">
                    <i class="fas fa-gamepad text-primary me-2"></i>Catálogo de Juegos
                </h2>
                <asp:Label ID="lblResultados" runat="server" CssClass="text-muted"></asp:Label>
            </div>

            <!-- Mensaje de estado -->
            <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-info">
                <asp:Label ID="lblMensaje" runat="server"></asp:Label>
            </asp:Panel>

            <!-- Grid de juegos -->
            <div class="row" id="gameContainer">
                <asp:Repeater ID="rptJuegos" runat="server">
                    <ItemTemplate>
                        <div class="col-xl-4 col-lg-6 col-md-12 mb-4">
                            <div class="card game-card h-100 border-0 shadow">
                                <!-- Imagen del juego -->
                                <div class="position-relative">
                                    <img src='<%# Eval("imagen") %>' 
                                         alt='<%# Eval("titulo") %>' 
                                         class="game-image">
                                    <div class="position-absolute top-0 end-0 m-2">
                                        <%# GetPriceTag(Eval("precio"), Eval("modeloNegocio")) %>
                                    </div>
                                </div>

                                <div class="card-body d-flex flex-column">
                                    <!-- Título del juego -->
                                    <h5 class="card-title fw-bold text-dark mb-2">
                                        <%# Eval("titulo") %>
                                    </h5>

                                    <!-- Desarrollador -->

                                    <!-- Descripción -->
                                    <p class="card-text text-muted flex-grow-1">
                                        <%# TruncateText(Eval("descripcion").ToString(), 120) %>
                                    </p>

                                    <!-- Información adicional -->
                                    <div class="row small text-muted mb-3">
                                        <div class="col-6">
                                            <i class="fas fa-calendar me-1"></i>
                                            <%# GetFechaLanzamiento(Eval("fechaLanzamiento")) %>
                                        </div>
                                    </div>

                                    <!-- Géneros -->
                                    <div class="mb-3">
                                        <%# GetGenerosHtml(Eval("genero")) %>
                                    </div>

                                    <!-- Botones de acción -->
                                    <div class="mt-auto">
                                        <div class="d-grid gap-2">
                                            <asp:Button ID="btnVerDetalle" runat="server" 
                                                       Text="Ver Detalles" 
                                                       CssClass="btn btn-outline-primary"
                                                       CommandName="VerDetalle"
                                                       CommandArgument='<%# Eval("idJuego") %>'
                                                       OnCommand="btnVerDetalle_Command" />
                                            <asp:Button ID="btnAgregarCarrito" runat="server" 
                                                       Text="Agregar al Carrito" 
                                                       CssClass="btn btn-primary"
                                                       CommandName="AgregarCarrito"
                                                       CommandArgument='<%# Eval("idJuego") %>'
                                                       OnCommand="btnAgregarCarrito_Command" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>

            <!-- Mensaje cuando no hay resultados -->
            <asp:Panel ID="pnlSinResultados" runat="server" Visible="false" CssClass="text-center py-5">
                <div class="mb-4">
                    <i class="fas fa-search text-muted" style="font-size: 4rem;"></i>
                </div>
                <h4 class="text-muted mb-3">No se encontraron juegos</h4>
                <p class="text-muted">Intenta ajustar los filtros para encontrar más resultados.</p>
                <asp:Button ID="btnMostrarTodos" runat="server" Text="Mostrar Todos los Juegos" 
                           CssClass="btn btn-primary" OnClick="btnMostrarTodos_Click" />
            </asp:Panel>
        </div>
    </div>
</asp:Content>
