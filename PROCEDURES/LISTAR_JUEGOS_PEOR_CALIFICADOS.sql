DROP PROCEDURE IF EXISTS LISTAR_JUEGOS_PEOR_CALIFICADOS;

DELIMITER $
CREATE PROCEDURE LISTAR_JUEGOS_PEOR_CALIFICADOS()
BEGIN
    SELECT 
        j.idJuego,
        j.titulo,
        ROUND(AVG(c.puntaje), 2) AS promedioCalificacion,
        COUNT(c.idCalificacion) AS cantidadCalificaciones
    FROM Calificacion c
    INNER JOIN Juego j ON c.fidJuego = j.idJuego
    WHERE c.activo = 1
    GROUP BY j.idJuego, j.titulo
    HAVING cantidadCalificaciones >= 1
    ORDER BY promedioCalificacion ASC
    LIMIT 10;
END$