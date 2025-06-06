DROP PROCEDURE IF EXISTS INSERTAR_CALIFICACION;
DROP PROCEDURE IF EXISTS MODIFICAR_CALIFICACION;
DROP PROCEDURE IF EXISTS ELIMINAR_CALIFICACION;
DROP PROCEDURE IF EXISTS LISTAR_CALIFICACIONES_TODAS;
DROP PROCEDURE IF EXISTS OBTENER_CALIFICACION_X_ID;
DELIMITER $
#INSERTAR RESEÑA
CREATE PROCEDURE INSERTAR_CALIFICACION(
    OUT _idCalificacion INT,
    IN _fidJugador INT,
    IN _fidJuego INT,
    IN _fechaPuntuacion DATE,
    IN _puntaje INT,
    IN _activo TINYINT
)
BEGIN
    INSERT INTO Calificacion(fidJugador, fidJuego, fechaPuntuacion, puntaje, activo)
    VALUES (_fidJugador, _fidJuego, _fechaPuntuacion, _puntaje, _activo);
    
    SET _idCalificacion = @@last_insert_id;
END$
#MODIFICAR CALIFIACION
CREATE PROCEDURE MODIFICAR_CALIFICACION(
    IN _idCalificacion INT,
    IN _fidJugador INT,
    IN _fidJuego INT,
    IN _fechaPuntuacion DATE,
    IN _puntaje INT,
    IN _activo TINYINT
)
BEGIN
    UPDATE Calificacion
    SET fidJugador = _fidJugador,
        fidJuego = _fidJuego,
        fechaPuntuacion = _fechaPuntuacion,
        puntaje = _puntaje,
        activo = _activo
    WHERE idCalificacion = _idCalificacion;
END$
#ELIMINAR CALIFICACION

CREATE PROCEDURE ELIMINAR_CALIFICACION(
    IN _idCalificacion INT
)
BEGIN
    UPDATE Calificacion
    SET activo = 0
    WHERE idCalificacion = _idCalificacion;
END$
#LISTAR CALIFICACIONES

CREATE PROCEDURE LISTAR_CALIFICACIONES_TODAS()
BEGIN
    SELECT idCalificacion, fidJugador, fidJuego, fechaPuntuacion, puntaje, activo
    FROM Calificacion
    WHERE activo = 1;
END$
#OBTENER CALIFIACION POR ID
CREATE PROCEDURE OBTENER_CALIFICACION_X_ID(
    IN _idCalificacion INT
)
BEGIN
    SELECT idCalificacion, fidJugador, fidJuego, fechaPuntuacion, puntaje, activo
    FROM Calificacion
    WHERE idCalificacion = _idCalificacion;
END$




