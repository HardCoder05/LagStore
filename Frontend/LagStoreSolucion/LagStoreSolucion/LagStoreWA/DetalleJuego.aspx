<%@ Page Title="" Language="C#" MasterPageFile="~/LagStore.Master" AutoEventWireup="true" CodeBehind="DetalleJuego.aspx.cs" Inherits="LagStoreWA.DetalleJuego" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Detalle del Juego
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
        <style>
        body {
            background: #1a1a2e;
            color: white;
        }

        /* Cards de filtros y juegos */
        .filter-card,
        .filter-section,
        .card {
            background: #2c2f48;
            color: white;
        }

        /* Etiquetas */
        .text-dark {
            color: white !important;
        }

        .text-muted {
            color: #a6a6c0 !important;
        }

        /* Títulos */
        h2, h5, h4 {
            color: white;
        }

        /* Estilo del input y select para modo oscuro */
        .form-control,
        .form-select {
            background-color: #1a1a2e;
            color: white;
            border: 1px solid #4c4c6d;
        }

        .form-control::placeholder {
            color: #cccccc;
        }

        .form-control:focus,
        .form-select:focus {
            background-color: #1a1a2e;
            color: white;
            border-color: #007bff;
            box-shadow: none;
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
            height: 100%;
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
            background: #484f85;
            border-radius: 8px;
            padding: 1.5rem;
            margin-bottom: 1rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        body{
            background: #1a1a2e;
            color: white;
        }
        .comentario-container {
            background-color: #2c2f48;
            border-radius: 8px;
            padding: 1rem;
            margin-bottom: 1rem;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .comentario-container:hover {
            background-color: #3a3f6e;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container py-5">
        <div class="row">
            <div class="col-md-6">
                <asp:Image ID="imgJuego" runat="server" CssClass="img-fluid rounded shadow" />
                <h4 class="text-primary mt-3">
                    <asp:Label ID="lblPrecio" runat="server" /></h4>
                    <asp:Button ID="btnAgregarCarrito" runat="server" 
                       Text="Agregar al Carrito" 
                       CssClass="btn btn-primary"
                       OnClick="btnAgregarCarrito_Click" />
                 <p class="text-muted">
                    Fecha de lanzamiento:<asp:Label ID="lblFecha" runat="server" />
                </p>
                <!--solo es hasta aqui-->
                <hr class="my-5" />
                <h4>Reseñas</h4>
                <asp:Repeater ID="rptComentarios" runat="server" OnItemCommand="rptComentarios_ItemCommand" OnItemDataBound= "rptComentarios_ItemDataBound">
                    <ItemTemplate>
                        <div class="comentario-container">
                            <strong><%# Eval("autor.nombre") %></strong> - <%# new string('★', Convert.ToInt32(Eval("calificacion.puntuacion"))) %>
                            <br />
                            <div class="border rounded p-3 mb-3">
                                <p class="mb-0"><%# Eval("comentario") %></p>
                            </div>
                            <%-- Mostrar botones solo si es del jugador actual --%>
                            <div class="d-flex justify-content-between mt-2">
                                <span class="text-muted" style="font-size: 0.85rem;"><%# Eval("fechaPublicacion", "{0:dd/MM/yyyy}") %></span>
                                <asp:Panel ID="pnlOpciones" runat="server" Visible="false">
                                    <asp:Button runat="server" CommandName="Editar" CommandArgument='<%# Eval("idResena") %>' Text="Editar" CssClass="btn btn-primary btn-sm me-2" />
                                    <asp:Button runat="server" CommandName="Eliminar" CommandArgument='<%# Eval("idResena") %>' Text="Eliminar" CssClass="btn btn-danger btn-sm" />
                                </asp:Panel>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>

                <h5 class="mt-4">Dejar un comentario</h5>
                <div class="mb-3">
                    <asp:TextBox ID="txtComentario" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="3" placeholder="Escribe tu comentario..."></asp:TextBox>
                </div>
                <div class="mb-3">
                    <asp:DropDownList ID="ddlCalificacion" runat="server" CssClass="form-select">
                        <asp:ListItem Text=" ★" Value="1" />
                        <asp:ListItem Text="★★" Value="2" />
                        <asp:ListItem Text="★★★" Value="3" />
                        <asp:ListItem Text="★★★★" Value="4" />
                        <asp:ListItem Text="★★★★★" Value="5" />
                    </asp:DropDownList>
                </div>
                <div class="text-end">
                <asp:Button ID="btnComentar" runat="server" Text="Enviar Comentario" CssClass="btn btn-primary" OnClick="btnComentar_Click" />
                </div>
            </div>
            <div class="col-md-6">
                <h2 class="fw-bold">
                    <asp:Label ID="lblTitulo" runat="server" /></h2>
                <hr />
                <p>
                    <asp:Label ID="lblDescripcion" runat="server" /></p>

                <div class="row">
                    <div class="col-6">
                        <p><strong>Versión:</strong><br />
                            <asp:Label ID="lblVersion" runat="server" /></p>
                        <p><strong>Requisitos mínimos:</strong><br />
                            <asp:Label ID="lblReqMin" runat="server" /></p>
                        <p><strong>Espacio en disco:</strong><br />
                            <asp:Label ID="lblEspacio" runat="server" />
                            GB</p>
                        <p><strong>Modelo de negocio:</strong><br />
                            <asp:Label ID="lblModeloNegocio" runat="server" /></p>
                    </div>
                    <div class="col-6">
                        <p><strong>Requisitos recomendados:</strong><br />
                            <asp:Label ID="lblReqRec" runat="server" /></p>
                        <p><strong>Última actualización:</strong><br />
                            <asp:Label ID="lblUltimaAct" runat="server" /></p>
                        <p><strong>Género:</strong><br />
                            <asp:Label ID="lblGenero" runat="server" /></p>
                        <p><strong>Desarrollador:</strong><br /><asp:Label ID="lblDesarrollador" runat="server" /></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
