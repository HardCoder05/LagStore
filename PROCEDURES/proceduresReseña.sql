DROP PROCEDURE IF EXISTS INSERTAR_RESENA;
DROP PROCEDURE IF EXISTS MODIFICAR_RESENA;
DROP PROCEDURE IF EXISTS ELIMINAR_RESENA;
DROP PROCEDURE IF EXISTS LISTAR_RESENAS_TODAS;
DROP PROCEDURE IF EXISTS OBTENER_RESENA_X_ID;
#Falta poner automatica la fecha actual
DELIMITER $
#INSERTAR RESENA
CREATE PROCEDURE INSERTAR_RESENA(
	OUT _idResena INT,
	IN _fidJugador INT,
    IN _fidJuego INT,
    IN _comentario TEXT,
    IN _fechaPublicacion DATE,
    IN _calificacion_IdCalificacion INT,
    IN _activo TINYINT
)
BEGIN
	# INSERT INTO area(nombre,activa) VALUES(_nombre,1);
    INSERT INTO Reseña(fidJugador,fidJuego,comentario,fechaPublicacion, calificacion_IdCalificacion, activo) VALUES (_fidJugador,_fidJuego, _comentario,_fechaPublicacion,_calificacion_IdCalificacion,1);
    SET _idResena = @@last_insert_id;
END$
#MOFIFICAR RESENA
CREATE PROCEDURE MODIFICAR_RESENA(
    IN _idResena INT,
    IN _fidJugador INT,
    IN _fidJuego INT,
    IN _comentario TEXT,
    IN _fechaPublicacion DATE,
    IN _calificacion_IdCalificacion INT,
    IN _activo TINYINT
)
BEGIN
    UPDATE Reseña
    SET fidJugador = _fidJugador,
        fidJuego = _fidJuego,
        comentario = _comentario,
        fechaPublicacion = _fechaPublicacion,
        calificacion_IdCalificacion = _calificacion_IdCalificacion,
        activo = _activo
    WHERE idResena = _idResena;
END$

#ELIMINAR RESENA
CREATE PROCEDURE ELIMINAR_RESENA(
    IN _idResena INT
)
BEGIN
    UPDATE Reseña
    SET activo = 0
    WHERE idResena = _idResena;
END$

#LISTAR RESENA_TODAS
CREATE PROCEDURE LISTAR_RESENAS_TODAS()
BEGIN
    SELECT idResena,fidJugador,fidJuego,comentario,fechaPublicacion,calificacion_IdCalificacion AS idCalificacion,activo
    FROM Reseña
    WHERE activo = 1;
END$

#OBTENER RESENA POR ID
CREATE PROCEDURE OBTENER_RESENA_X_ID(
    IN _idResena INT
)
BEGIN
    SELECT idResena,fidJugador,fidJuego,comentario,fechaPublicacion,calificacion_IdCalificacion AS idCalificacion,activo
    FROM Reseña
    WHERE idResena = _idResena;
END$
