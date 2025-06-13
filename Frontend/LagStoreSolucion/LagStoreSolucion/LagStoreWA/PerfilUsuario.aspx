<%@ Page Title="Mi Perfil" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="PerfilUsuario.aspx.cs" Inherits="LagStoreWA.PerfilUsuario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    LagStore - Mi Perfil
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        body{
            background: #1a1a2e;
            color: white;
        }

        .profile-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-radius: 15px;
            padding: 2rem;
            margin-bottom: 2rem;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }
        .profile-avatar {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            border: 5px solid white;
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
            object-fit: cover;
        }
        .profile-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }
        .profile-card:hover {
            transform: translateY(-2px);
        }
        .section-header {
            background: linear-gradient(45deg, #f8f9fa, #e9ecef);
            border-radius: 10px 10px 0 0;
            padding: 1rem 1.5rem;
            border-bottom: 2px solid #dee2e6;
        }
        .form-floating {
            margin-bottom: 1rem;
        }
        .status-badge {
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-weight: bold;
            text-transform: uppercase;
            font-size: 0.8rem;
        }
        .status-active {
            background: #d4edda;
            color: #155724;
        }
        .status-inactive {
            background: #f8d7da;
            color: #721c24;
        }
        .info-item {
            padding: 0.75rem 0;
            border-bottom: 1px solid #f0f0f0;
        }
        .info-item:last-child {
            border-bottom: none;
        }
        .password-strength {
            height: 5px;
            border-radius: 3px;
            margin-top: 0.5rem;
            transition: all 0.3s ease;
        }
        .strength-weak { background: #dc3545; }
        .strength-medium { background: #ffc107; }
        .strength-strong { background: #28a745; }
    </style>

    <script type="text/javascript">        
        function togglePasswordSection() {
            var passwordSection = document.getElementById('passwordSection');
            var toggleBtn = document.getElementById('togglePasswordBtn');

            if (passwordSection.style.display === 'none' || passwordSection.style.display === '') {
                passwordSection.style.display = 'block';
                toggleBtn.textContent = 'Cancelar cambio de contraseña';
                toggleBtn.className = 'btn btn-outline-secondary btn-sm';
            } else {
                passwordSection.style.display = 'none';
                toggleBtn.textContent = 'Cambiar contraseña';
                toggleBtn.className = 'btn btn-outline-primary btn-sm';

                // Limpiar campos de contraseña
                document.getElementById('<%= txtContrasenaActual.ClientID %>').value = '';
                document.getElementById('<%= txtNuevaContrasena.ClientID %>').value = '';
                document.getElementById('<%= txtConfirmarContrasena.ClientID %>').value = '';

                // Resetear indicador de fortaleza
                resetPasswordStrength();
            }
        }

        function checkPasswordStrength() {
            var password = document.getElementById('<%= txtNuevaContrasena.ClientID %>').value;
            var strengthBar = document.querySelector('.password-strength');
            var strengthText = document.getElementById('strengthText');

            var strength = calculatePasswordStrength(password);

            // Actualizar barra de progreso
            strengthBar.style.width = strength.percentage + '%';
            strengthBar.style.backgroundColor = strength.color;

            // Actualizar texto
            strengthText.textContent = strength.text;
            strengthText.style.color = strength.color;
        }

        function calculatePasswordStrength(password) {
            var score = 0;
            var feedback = {
                percentage: 0,
                text: 'Muy débil',
                color: '#dc3545'
            };

            if (password.length === 0) {
                return { percentage: 0, text: '-', color: '#6c757d' };
            }

            // Longitud
            if (password.length >= 8) score += 25;
            if (password.length >= 12) score += 10;

            // Mayúsculas
            if (/[A-Z]/.test(password)) score += 20;

            // Minúsculas
            if (/[a-z]/.test(password)) score += 20;

            // Números
            if (/[0-9]/.test(password)) score += 20;

            // Caracteres especiales
            if (/[^A-Za-z0-9]/.test(password)) score += 15;

            // Determinar fortaleza
            if (score < 30) {
                feedback = { percentage: score, text: 'Muy débil', color: '#dc3545' };
            } else if (score < 60) {
                feedback = { percentage: score, text: 'Débil', color: '#fd7e14' };
            } else if (score < 80) {
                feedback = { percentage: score, text: 'Buena', color: '#ffc107' };
            } else {
                feedback = { percentage: score, text: 'Fuerte', color: '#198754' };
            }

            return feedback;
        }

        function resetPasswordStrength() {
            var strengthBar = document.querySelector('.password-strength');
            var strengthText = document.getElementById('strengthText');

            strengthBar.style.width = '0%';
            strengthText.textContent = '-';
            strengthText.style.color = '#6c757d';
        }
        
        // Validación en tiempo real para campos de texto
        document.addEventListener('DOMContentLoaded', function () {
            // Validación de email en tiempo real
            var emailInput = document.getElementById('<%= txtEmail.ClientID %>');
            if (emailInput) {
                emailInput.addEventListener('blur', function () {
                    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                    if (this.value && !emailPattern.test(this.value)) {
                        this.classList.add('is-invalid');
                    } else {
                        this.classList.remove('is-invalid');
                    }
                });
            }

            // Validación de teléfono en tiempo real
            var telefonoInput = document.getElementById('<%= txtTelefono.ClientID %>');
            if (telefonoInput) {
                telefonoInput.addEventListener('blur', function () {
                    var telefonoPattern = /^[0-9+\-\s()]{7,15}$/;
                    if (this.value && !telefonoPattern.test(this.value)) {
                        this.classList.add('is-invalid');
                    } else {
                        this.classList.remove('is-invalid');
                    }
                });
            }

            // Auto-hide messages after 5 seconds
            var alertPanel = document.getElementById('<%= pnlMensaje.ClientID %>');
            if (alertPanel && alertPanel.style.display !== 'none') {
                setTimeout(function () {
                    alertPanel.style.display = 'none';
                }, 5000);
            }
        });
    </script>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Header del Perfil -->
    <div class="profile-header">
        <div class="row align-items-center">
            <div class="col-auto">
                <asp:Image ID="imgPerfil" runat="server" CssClass="profile-avatar" 
                          ImageUrl="~/Assets/default-avatar.jpg" />
            </div>
            <div class="col">
                <h2 class="mb-1">
                    <asp:Label ID="lblNombreUsuario" runat="server"></asp:Label>
                </h2>
                <p class="mb-2 opacity-75">
                    <i class="fas fa-envelope me-2"></i>
                    <asp:Label ID="lblEmailUsuario" runat="server"></asp:Label>
                </p>
                <div class="d-flex align-items-center gap-2">
                    <asp:Label ID="lblEstadoUsuario" runat="server" CssClass="status-badge me-3"></asp:Label>
                    <small class="opacity-75">
                        <i class="fas fa-calendar me-1"></i>
                        Miembro desde: <asp:Label ID="lblFechaRegistro" runat="server"></asp:Label>
                    </small>
                </div>
            </div>
        </div>
    </div>

    <!-- Mensajes -->
    <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert mb-4">
        <div class="d-flex align-items-center">
            <i class="fas fa-info-circle me-2"></i>
            <asp:Label ID="lblMensaje" runat="server"></asp:Label>
        </div>
    </asp:Panel>

    <div class="row">
        <!-- Información Personal -->
        <div class="col-lg-8">
            <div class="profile-card mb-4">
                <div class="section-header">
                    <h5 class="mb-0">
                        <i class="fas fa-user text-primary me-2"></i>Información Personal
                    </h5>
                </div>
                <div class="p-4">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-floating">
                                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" 
                                            placeholder="Nombre completo"></asp:TextBox>
                                <label for="<%= txtNombre.ClientID %>">Nombre Completo</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-floating">
                                <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" 
                                            TextMode="Email" placeholder="Email"></asp:TextBox>
                                <label for="<%= txtEmail.ClientID %>">Email</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-floating">
                                <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" 
                                            placeholder="Teléfono"></asp:TextBox>
                                <label for="<%= txtTelefono.ClientID %>">Teléfono</label>
                            </div>
                        </div>
                    </div>

                    <div class="mt-3">
                        <button type="button" id="togglePasswordBtn" class="btn btn-outline-primary btn-sm" 
                                onclick="togglePasswordSection()">
                            Cambiar contraseña
                        </button>
                    </div>

                    <!-- Sección de Cambio de Contraseña -->
                    <div id="passwordSection" style="display: none;" class="mt-4 p-3 bg-light rounded">
                        <h6 class="text-muted mb-3">Cambiar Contraseña</h6>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-floating mb-3">
                                    <asp:TextBox ID="txtContrasenaActual" runat="server" CssClass="form-control" 
                                                TextMode="Password" placeholder="Contraseña actual"></asp:TextBox>
                                    <label for="<%= txtContrasenaActual.ClientID %>">Contraseña Actual</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-floating mb-3">
                                    <asp:TextBox ID="txtNuevaContrasena" runat="server" CssClass="form-control" 
                                                TextMode="Password" placeholder="Nueva contraseña" 
                                                onkeyup="checkPasswordStrength()"></asp:TextBox>
                                    <label for="<%= txtNuevaContrasena.ClientID %>">Nueva Contraseña</label>
                                </div>
                                <div class="mb-2">
                                    <div style="background: #e9ecef; border-radius: 3px; height: 5px; overflow: hidden;">
                                        <div class="password-strength" style="width: 0%;"></div>
                                    </div>
                                    <small class="text-muted">Fortaleza: <span id="strengthText">-</span></small>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-floating">
                                    <asp:TextBox ID="txtConfirmarContrasena" runat="server" CssClass="form-control" 
                                                TextMode="Password" placeholder="Confirmar contraseña"></asp:TextBox>
                                    <label for="<%= txtConfirmarContrasena.ClientID %>">Confirmar Contraseña</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                        <asp:Button ID="btnActualizar" runat="server" Text="Guardar Cambios" 
                                   CssClass="btn btn-primary px-4" OnClick="btnActualizar_Click" />
                    </div>
                </div>
            </div>
        </div>

        <!-- Panel Lateral -->
        <div class="col-lg-4">
            <!-- Información de Cuenta -->
            <div class="profile-card">
                <div class="section-header">
                    <h5 class="mb-0">
                        <i class="fas fa-info-circle text-primary me-2"></i>Información de Cuenta
                    </h5>
                </div>
                <div class="p-4">
                    <div class="info-item">
                        <div class="d-flex justify-content-between">
                            <span class="text-muted">Rol:</span>
                            <asp:Label ID="lblRolUsuario" runat="server" CssClass="fw-bold"></asp:Label>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="d-flex justify-content-between">
                            <span class="text-muted">Estado:</span>
                            <asp:Label ID="lblEstadoCuenta" runat="server"></asp:Label>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="d-flex justify-content-between">
                            <span class="text-muted">ID Usuario:</span>
                            <asp:Label ID="lblIdUsuario" runat="server" CssClass="fw-bold"></asp:Label>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="text-muted">Último acceso:</span>
                            <small class="text-end">
                                <asp:Label ID="lblUltimoAcceso" runat="server"></asp:Label>
                            </small>
                        </div>
                    </div>
                    <div class="info-item" id="RolAdministrativo" runat="server" visible="false">
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="text-muted">Rol administrativo:</span>
                            <small class="text-end">
                                <asp:Label ID="lblRolAdministrativo" runat="server"></asp:Label>
                            </small>
                        </div>
                    </div>
                    <div class="info-item" id="NumCuenta" runat="server" visible="false">
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="text-muted">Numero de cuenta:</span>
                            <small class="text-end">
                                <asp:Label ID="lblNumCuenta" runat="server"></asp:Label>
                            </small>
                        </div>
                    </div>
                    <div class="info-item" id="IngresoTotal" runat="server" visible="false">
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="text-muted">Ingreso total:</span>
                            <small class="text-end">
                                <asp:Label ID="lblIngresoTotal" runat="server"></asp:Label>
                            </small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
