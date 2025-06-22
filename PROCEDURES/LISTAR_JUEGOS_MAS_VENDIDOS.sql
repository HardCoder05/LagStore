DROP PROCEDURE IF EXISTS LISTAR_JUEGOS_MAS_VENDIDOS;

DELIMITER $
CREATE PROCEDURE LISTAR_JUEGOS_MAS_VENDIDOS()
BEGIN
    SELECT 
        j.idJuego,
        j.titulo,
        COUNT(ja.fidJuego) AS cantidadVentas
    FROM JuegoAdquirido ja
    INNER JOIN Juego j ON ja.fidJuego = j.idJuego
    WHERE ja.activo = 1
    GROUP BY j.idJuego, j.titulo
    ORDER BY cantidadVentas DESC
    LIMIT 10;
END$