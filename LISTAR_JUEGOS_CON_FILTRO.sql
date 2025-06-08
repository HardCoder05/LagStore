DROP PROCEDURE IF EXISTS LISTAR_JUEGOS_CON_FILTRO;

DELIMITER //
CREATE PROCEDURE LISTAR_JUEGOS_CON_FILTRO(
    IN p_titulo VARCHAR(100),
    IN p_genero ENUM('Accion', 'Rol', 'Estrategia', 'Shooter', 'Simulacion', 'Deportes', 'Carreras'),
    IN p_modelo ENUM('Free_to_play', 'Paga', 'Suscripcion'),
    IN p_precio_min DOUBLE,
    IN p_precio_max DOUBLE
)
BEGIN
    SELECT 
        j.idJuego,
        j.titulo,
        j.descripcion,
        j.precio,
        j.version,
        j.imagenJuego,
        j.fechaLanzamiento,
        j.requisitosMinimos,
        j.requisitosRecomendados,
        j.espacioDisco,
        j.fechaUltimaActualizacion,
        j.nombreGenero,
        j.modelo,
        j.desarrollador_idDesarrollador,
        j.activo,

        -- Datos del desarrollador
        u.id AS idUsuario,
        u.nombre,
        u.email,
        u.contrasena,
        u.fechaRegistro,
        u.telefono,
        u.fotoDePerfil,
        d.numeroCuenta,
        d.ingresoTotal

    FROM Juego j
    INNER JOIN Desarrollador d ON j.desarrollador_idDesarrollador = d.idDesarrollador
    INNER JOIN Usuario u ON d.idDesarrollador = u.id
    WHERE j.activo = 1
      AND (p_titulo IS NULL OR p_titulo = '' OR UPPER(j.titulo) LIKE CONCAT('%', UPPER(p_titulo), '%'))
      AND (p_genero IS NULL OR j.nombreGenero = p_genero)
      AND (p_modelo IS NULL OR j.modelo = p_modelo)
      AND (p_precio_min IS NULL OR j.precio >= p_precio_min)
      AND (p_precio_max IS NULL OR j.precio <= p_precio_max);
END //
DELIMITER ;

