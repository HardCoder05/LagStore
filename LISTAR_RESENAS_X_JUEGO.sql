
DROP PROCEDURE IF EXISTS LISTAR_RESENAS_X_JUEGO;
DELIMITER $
CREATE PROCEDURE LISTAR_RESENAS_X_JUEGO(
    IN _fidJuego INT
)
BEGIN
    SELECT 
        r.idResena,
        r.fidJugador,
        j.nombre AS nombreJugador,
        r.fidJuego,
        r.comentario,
        r.fechaPublicacion,
        r.calificacion_IdCalificacion AS idCalificacion,
        c.puntuacion,
        r.activo
    FROM 
        Reseña r
    INNER JOIN Jugador j ON r.fidJugador = j.idJugador
    LEFT JOIN Calificacion c ON r.calificacion_IdCalificacion = c.idCalificacion
    WHERE 
        r.fidJuego = _fidJuego AND
        r.activo = 1;
END$