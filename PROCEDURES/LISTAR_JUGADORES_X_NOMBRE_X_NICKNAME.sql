DROP PROCEDURE IF EXISTS LISTAR_JUGADORES_X_NOMBRE_X_NICKNAME;
DROP PROCEDURE IF EXISTS LISTAR_JUGADOR_POR_NOMBRE;

DELIMITER $
CREATE  PROCEDURE LISTAR_JUGADORES_X_NOMBRE_X_NICKNAME(
	IN textoBusqueda VARCHAR(100)
)
BEGIN
	SELECT u.id, u.nombre, u.email, u.fechaRegistro, u.telefono, u.fotoDePerfil, j.nickname AS nicknameJugador
    FROM Jugador j
    INNER JOIN Usuario u ON u.id = j.idJugador
    WHERE u.activo = 1 AND (
        u.nombre LIKE CONCAT('%', textoBusqueda, '%') OR
        j.nickname LIKE CONCAT('%', textoBusqueda, '%')
    );
END$


DELIMITER $
CREATE PROCEDURE LISTAR_JUGADOR_POR_NOMBRE(
	IN _nombre VARCHAR(100)
)
BEGIN
	SELECT u.id, u.nombre, u.email, u.contrasena, u.fechaRegistro, u.telefono, u.fotoDePerfil, j.nickname AS nicknameJugador
    FROM Jugador j
    INNER JOIN Usuario u ON u.id = j.idJugador
    WHERE u.activo = 1 AND u.nombre LIKE CONCAT('%', _nombre, '%');
END$