<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="LagStoreWA.Home" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    LagStore - Inicio
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .hero-banner {
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 500px;
            position: relative;
            color: white;
            margin-bottom: 10px;
        }

        .hero-overlay {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
        }


        /*@keyframes float {
            0%, 100% {
                transform: translateY(0px);
            }

            50% {
                transform: translateY(-20px);
            }
        }*/

        .game-card {
            background: #2c3e50;
            border: none;
            border-radius: 15px;
            overflow: hidden;
            transition: all 0.3s ease;
            color: white;
        }

        .game-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 40px rgba(0,0,0,0.3);
        }

        .game-image {
            height: 200px;
            background-size: cover;
            background-position: center;
            position: relative;
        }

        .game-tag {
            position: absolute;
            top: 10px;
            left: 10px;
            background: rgba(0,0,0,0.7);
            color: white;
            padding: 5px 10px;
            border-radius: 15px;
            font-size: 12px;
            text-transform: uppercase;
        }

        .price-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 15px;
        }

        .price {
            font-size: 1.5rem;
            font-weight: bold;
            color: #3498db;
        }

        .btn-add-cart {
            background: #6c5ce7;
            border: none;
            padding: 8px 15px;
            border-radius: 20px;
            color: white;
            transition: all 0.3s ease;
        }

        .btn-add-cart:hover {
            background: #5f3dc4;
            color: white;
        }

        .btn-details {
            background: #34495e;
            border: none;
            padding: 8px 15px;
            border-radius: 20px;
            color: white;
            margin-left: 10px;
            transition: all 0.3s ease;
        }

        .btn-details:hover {
            background: #2c3e50;
            color: white;
        }

        .featured-game {
            background: linear-gradient(135deg, #2c3e50 0%, #34495e 100%);
            color: white;
            border-radius: 20px;
            padding: 60px 0;
            margin-bottom: 50px;
            position: relative;
            overflow: hidden;
        }

        .section-title {
            color: white;
            margin-bottom: 30px;
            font-weight: bold;
        }

        .explore-all {
            color: #3498db;
            text-decoration: none;
            font-weight: 500;
        }

        .explore-all:hover {
            color: #2980b9;
            text-decoration: none;
        }

        body {
            background: #1a1a2e;
            color: white;
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Literal ID="litBackgroundUrl" runat="server" Visible="false" />
    <!-- Hero Section con Juego Destacado -->
    <div class="hero-banner" style='<%: "background-image: url(" + litBackgroundUrl.Text + ");" %>'>
        <div class="hero-overlay">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-8">
                        <h1 class="display-4 fw-bold">
                            <asp:Label ID="lblTituloDestacado" runat="server" Text="Juego Destacado"></asp:Label>
                        </h1>
                        <span class="badge bg-primary mb-3">
                            <asp:Label ID="lblGeneroDestacado" runat="server" Text=""></asp:Label>
                        </span>
                        <p class="lead mb-4">
                            <asp:Label ID="lblDescripcionDestacada" runat="server" Text=""></asp:Label>
                        </p>
                        <div class="d-flex flex-column flex-md-row align-items-start align-items-md-center gap-2">
                            <span class="price me-2">S/<asp:Label ID="lblPrecioDestacado" runat="server" Text="0.00"></asp:Label></span>
                            <asp:Button ID="btnAgregarDestacado" runat="server" CssClass="btn btn-add-cart" Text="Agregar al carrito" />
                            <asp:Button ID="btnVerDetallesDestacado" runat="server" CssClass="btn btn-details" Text="Ver detalles" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <div class="container">
        <!-- Sección Explorar Juegos -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="section-title">Explorar juegos</h2>
            <a href="#" class="explore-all">Ver todos</a>
        </div>

        <div class="row">
            <asp:Repeater ID="rptJuegosDestacados" runat="server">
                <ItemTemplate>
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card game-card">
                            <div class="game-image" style='background-image: url(<%# Eval("imagen") %>);'>
                                <div class="game-tag"><%# Eval("genero") %></div>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title"><%# Eval("titulo") %></h5>
                                <div class="price-section">
                                    <span class="price">S/<%# Eval("precio", "{0:F2}") %></span>
                                    <button class="btn btn-add-cart btn-sm">
                                        <i class="fas fa-shopping-cart"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>



        <!-- Sección de Características -->
        <div class="row text-center mt-5 mb-5">
            <div class="col-md-4">
                <div class="card shadow-sm" style="background: #34495e; border: none; border-radius: 15px;">
                    <div class="card-body py-4">
                        <i class="fas fa-gamepad fa-3x mb-3" style="color: #3498db;"></i>
                        <h5 class="card-title text-white">Explora</h5>
                        <p class="card-text text-light">Encuentra juegos por género, tipo o novedades. ¡Nunca fue tan fácil descubrir!</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mt-4 mt-md-0">
                <div class="card shadow-sm" style="background: #34495e; border: none; border-radius: 15px;">
                    <div class="card-body py-4">
                        <i class="fas fa-upload fa-3x mb-3" style="color: #27ae60;"></i>
                        <h5 class="card-title text-white">Publica</h5>
                        <p class="card-text text-light">¿Eres desarrollador? Publica tu juego y hazlo visible para miles de usuarios.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mt-4 mt-md-0">
                <div class="card shadow-sm" style="background: #34495e; border: none; border-radius: 15px;">
                    <div class="card-body py-4">
                        <i class="fas fa-shopping-cart fa-3x mb-3" style="color: #f39c12;"></i>
                        <h5 class="card-title text-white">Compra</h5>
                        <p class="card-text text-light">Adquiere videojuegos digitales de forma rápida, segura y sin comisiones ocultas.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>

