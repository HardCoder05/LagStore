using System;
using System.Collections.Generic;
using System.ComponentModel.Design.Serialization;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LagStoreWA.ServicesWS;
//cuando vuelves a compilar el ws, lo tienes que volver a correr
namespace LagStoreWA{
    public partial class DetalleJuego : System.Web.UI.Page
    {
        private JuegoWSClient wsJuego;
        private ResenaWSClient wsResena;
        private CalificacionWSClient wsCalificacion;
        protected void Page_Load(object sender, EventArgs e)
        {
            wsJuego = new JuegoWSClient();
            if (!IsPostBack)
            {
                int idJuego;
                if (int.TryParse(Request.QueryString["id"], out idJuego))
                {
                    CargarDatosJuego(idJuego);
                    CargarComentarios(idJuego);
                }
            }
        }
        private void CargarDatosJuego(int idJuego)
        {

            // Aquí llamas a tu base de datos
            juego juego=wsJuego.obtenerJuegoPorId(idJuego); // trae las clases con minuscula
            if (wsJuego != null)
            {
                imgJuego.ImageUrl = juego.imagen;
                lblTitulo.Text = juego.titulo;
                lblPrecio.Text = juego.precio == 0 ? "Gratis" : "S/ " + juego.precio.ToString("0.00");
                lblFecha.Text = juego.fechaLanzamiento.ToString("dd/MM/yyyy");
                lblDescripcion.Text = juego.descripcion;

                lblVersion.Text = juego.version.ToString();
                lblReqMin.Text = juego.requisitosMinimos;
                lblReqRec.Text = juego.requisitosRecomendados;
                lblEspacio.Text = juego.espacioDisco.ToString("F2");
                lblUltimaAct.Text = juego.fechaUltimaActualizacion.ToString("dd/MM/yyyy");
                //duda
                lblGenero.Text = juego.genero.ToString();
                lblModeloNegocio.Text = juego.modeloNegocio.ToString();
                lblDesarrollador.Text = juego.desarrollador?.nombre ?? "N/A";
            }
        }
        
        private void CargarComentarios(int idJuego)
        {
              wsResena= new ResenaWSClient();
        List<resena> comentarios = wsResena.listarPorJuego(idJuego)?.ToList() ?? new List<resena>();//si esta vacio (devuelve lista vacia)
            rptComentarios.DataSource = comentarios;
            rptComentarios.DataBind();
        }
        
        
        protected void btnComentar_Click(object sender, EventArgs e)
        {
            if (Session["Jugador"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }
            wsResena = new ResenaWSClient();
            wsCalificacion = new CalificacionWSClient();
            int idJuego = int.Parse(Request.QueryString["id"]);
            int calificacionValor = int.Parse(ddlCalificacion.SelectedValue);
            
            // traigo el id del jugador
            jugador jugadorActual = (jugador)Session["Jugador"];
            int idJugador = jugadorActual.idJugador;  // Aquí tienes el ID
            //Objetos Generales
            jugador jugador = new jugador();
            jugador.idJugador = idJugador;
            juego juego = new juego();
            juego.idJuego = idJuego;
            //lleno calificacion
            calificacion calificacion;
            calificacion = new calificacion();
            calificacion.fechaPuntuacion=DateTime.Now;
            calificacion.autor= jugador;
            calificacion.juego = juego;
            calificacion.puntuacion = calificacionValor;
            //en ningun momento se trae el id del objeto calificacion aqui(se inseratr con un id  que se debe recoger

            calificacion.activo = 1;//no me acuerdo si ya se llenaba por defecto
            calificacion.idCalificacion=wsCalificacion.insertarCalificacion(calificacion);//no se llena el id automaticamente en el java se llena la copia local
            //parametro de salida
            // lleno la resena
            resena resena;
            resena = new resena();
            resena.comentario = txtComentario.Text.Trim();
            resena.fechaPublicacion= DateTime.Now;
            resena.calificacion = calificacion;
            resena.juego = juego;
            resena.autor=jugador;
            resena.activo = 1;
            wsResena.insertarResena(resena);


            txtComentario.Text = "";
            CargarComentarios(idJuego);
            
        }

        protected void rptComentarios_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            int idJuego = int.Parse(Request.QueryString["id"]);

            if (e.CommandName == "Eliminar"){
                int idResena = Convert.ToInt32(e.CommandArgument);
                wsResena = new ResenaWSClient();
                resena resena;
                resena = new resena();
                resena = wsResena.obtenerResenaPorId(idResena);//no se si funciona esto
                wsResena.eliminarResena(idResena); // Tu método del WS debe poner activo=0
                wsCalificacion = new CalificacionWSClient();
                wsCalificacion.eliminarCalificacion(resena.calificacion.idCalificacion);
                CargarComentarios(idJuego); // recargar la lista
            }
            else if (e.CommandName == "Editar"){
                int idResena = Convert.ToInt32(e.CommandArgument);
                // Puedes guardar en sesión el ID de la reseña a editar
                Response.Redirect($"EditarComentario.aspx?idResena={idResena}&idJuego={idJuego}"); // o mostrar el comentario en un TextBox para editarlo en la misma página
            }
        }

        protected void rptComentarios_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            //ListItemType.Item: es una fila de datos normal (ítem impar). ListItemType.AlternatingItem: es una fila de datos alterna(ítem par), útil cuando tienes estilos alternos.
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                resena resena = (resena)e.Item.DataItem;

                // Obtiene el jugador actual de la sesión
                jugador jugadorActual = (jugador)Session["Jugador"];

                // Busca el Panel de botones dentro del item
                Panel pnlOpciones = (Panel)e.Item.FindControl("pnlOpciones");

                // Compara el ID del autor con el del jugador actual
                if (jugadorActual != null && resena.autor != null && resena.autor.idJugador == jugadorActual.idJugador){
                    pnlOpciones.Visible = true;
                }
                else{
                    pnlOpciones.Visible = false;
                }
            }
        }
    }
}
