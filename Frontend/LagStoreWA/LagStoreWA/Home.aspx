<%@ Page Title="Inicio" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="LagStoreWA.Home" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    LagStore - Inicio
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .hero-section {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 100px 0;
            position: relative;
            overflow: hidden;
        }
        
        .hero-section::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><circle cx="20" cy="20" r="1" fill="white" opacity="0.3"/><circle cx="80" cy="30" r="0.5" fill="white" opacity="0.4"/><circle cx="40" cy="70" r="1.5" fill="white" opacity="0.2"/><circle cx="90" cy="80" r="0.8" fill="white" opacity="0.3"/><circle cx="10" cy="90" r="1.2" fill="white" opacity="0.25"/></svg>') repeat;
            animation: float 20s ease-in-out infinite;
        }
        
        @keyframes float {
            0%, 100% { transform: translateY(0px); }
            50% { transform: translateY(-20px); }
        }
        
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
        
        .heart-icon {
            position: absolute;
            top: 10px;
            right: 10px;
            color: #e74c3c;
            cursor: pointer;
            font-size: 1.2rem;
        }
        
        .heart-icon:hover {
            color: #c0392b;
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Hero Section con Juego Destacado -->
    <div class="featured-game">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h1 class="display-4 fw-bold">Cosmic Odyssey</h1>
                    <span class="badge bg-primary mb-3">ACCIÓN</span>
                    <p class="lead mb-4">
                        Embárcate en una aventura intergaláctica en este juego de rol de acción. Explora planetas desconocidos, 
                        lucha contra criaturas alienígenas y descubre antiguos secretos mientras intentas salvar la galaxia de una 
                        amenaza inminente. Con un mundo abierto y misiones emocionantes, cada decisión cuenta.
                    </p>
                    <div class="d-flex align-items-center">
                        <span class="price me-3">S/50.99</span>
                        <button class="btn btn-add-cart me-2">
                            <i class="fas fa-shopping-cart"></i> Agregar al carrito
                        </button>
                        <button class="btn btn-details">Ver detalles</button>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div style="width: 200px; height: 200px; background: linear-gradient(45deg, #667eea, #764ba2); border-radius: 20px; margin: 0 auto; display: flex; align-items: center; justify-content: center;">
                        <i class="fas fa-rocket fa-5x opacity-50"></i>
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
            <!-- Neon Shadows -->
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card game-card">
                    <div class="game-image" style="background: linear-gradient(45deg, #f39c12, #e67e22);">
                        <div class="game-tag">RPG</div>
                        <i class="fas fa-heart heart-icon"></i>
                        <div class="d-flex align-items-center justify-content-center h-100">
                            <i class="fas fa-user-astronaut fa-4x opacity-75"></i>
                        </div>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Neon Shadows</h5>
                        <div class="price-section">
                            <span class="price">S/49.99</span>
                            <button class="btn btn-add-cart btn-sm">
                                <i class="fas fa-shopping-cart"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Lista de juegos -->
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card game-card">
                    <div class="game-image" style="background: linear-gradient(45deg, #3498db, #2980b9);">
                        <div class="game-tag">Strategy</div>
                        <i class="fas fa-heart heart-icon"></i>
                        <div class="d-flex align-items-center justify-content-center h-100">
                            <i class="fas fa-chess-king fa-4x opacity-75"></i>
                        </div>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Kingdom's Fall</h5>
                        <div class="price-section">
                            <span class="price">S/29.99</span>
                            <button class="btn btn-add-cart btn-sm">
                                <i class="fas fa-shopping-cart"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card game-card">
                    <div class="game-image" style="background: linear-gradient(45deg, #e74c3c, #c0392b);">
                        <div class="game-tag">Puzzle</div>
                        <i class="fas fa-heart heart-icon"></i>
                        <div class="d-flex align-items-center justify-content-center h-100">
                            <i class="fas fa-puzzle-piece fa-4x opacity-75"></i>
                        </div>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Echo of Eternity</h5>
                        <div class="price-section">
                            <span class="price">S/29.99</span>
                            <button class="btn btn-add-cart btn-sm">
                                <i class="fas fa-shopping-cart"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card game-card">
                    <div class="game-image" style="background: linear-gradient(45deg, #9b59b6, #8e44ad);">
                        <div class="game-tag">Racing</div>
                        <i class="fas fa-heart heart-icon"></i>
                        <div class="d-flex align-items-center justify-content-center h-100">
                            <i class="fas fa-car fa-4x opacity-75"></i>
                        </div>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Velocity Surge</h5>
                        <div class="price-section">
                            <span class="price">S/31.49</span>
                            <button class="btn btn-add-cart btn-sm">
                                <i class="fas fa-shopping-cart"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
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

