DROP PROCEDURE IF EXISTS INSERTAR_BIBLIOTECA;
DROP PROCEDURE IF EXISTS MODIFICAR_BIBLIOTECA;
DROP PROCEDURE IF EXISTS ELIMINAR_BIBLIOTECA;
DROP PROCEDURE IF EXISTS LISTAR_BIBLIOTECAS_TODAS;
DROP PROCEDURE IF EXISTS OBTENER_BIBLIOTECA_X_ID;
DROP PROCEDURE IF EXISTS INSERTAR_JUEGO;
DROP PROCEDURE IF EXISTS MODIFICAR_JUEGO;
DROP PROCEDURE IF EXISTS ELIMINAR_JUEGO;
DROP PROCEDURE IF EXISTS LISTAR_JUEGOS_TODOS;
DROP PROCEDURE IF EXISTS OBTENER_JUEGO_X_ID;
DROP PROCEDURE IF EXISTS INSERTAR_JUEGO_ADQUIRIDO;
DROP PROCEDURE IF EXISTS MODIFICAR_JUEGO_ADQUIRIDO;
DROP PROCEDURE IF EXISTS ELIMINAR_JUEGO_ADQUIRIDO;
DROP PROCEDURE IF EXISTS LISTAR_JUEGOS_ADQUIRIDOS_TODOS;
DROP PROCEDURE IF EXISTS OBTENER_JUEGO_ADQUIRIDO_POR_ID;


-- Para Biblioteca
DELIMITER //
CREATE PROCEDURE INSERTAR_BIBLIOTECA (
    OUT _idBiblioteca INT,
    IN p_ingresoTotal DOUBLE,
    IN p_cantidadDeJuegos INT,
    IN p_usuario_idUsuario INT
)
BEGIN
    INSERT INTO Biblioteca (ingresoTotal, cantidadDeJuegos, activo, usuario_idUsuario)
    VALUES (p_ingresoTotal, p_cantidadDeJuegos, 1, p_usuario_idUsuario);
    
    SET _idBiblioteca = LAST_INSERT_ID();
END //

DELIMITER //
CREATE PROCEDURE MODIFICAR_BIBLIOTECA (
    IN p_idBiblioteca INT,
    IN p_cantidadDeJuegos INT,
    IN p_ingresoTotal DOUBLE
)
BEGIN
    UPDATE Biblioteca
    SET cantidadDeJuegos = p_cantidadDeJuegos ,
        ingresoTotal = p_ingresoTotal
    WHERE idBiblioteca = p_idBiblioteca;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_BIBLIOTECA (
    IN p_idBiblioteca INT
)
BEGIN
    UPDATE Biblioteca
    SET activo = 0
    WHERE idBiblioteca = p_idBiblioteca;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_BIBLIOTECAS_TODAS()
BEGIN
    SELECT *
    FROM Biblioteca
    WHERE activo = 1;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_BIBLIOTECA_X_ID (
    IN p_idBiblioteca INT
)
BEGIN
    SELECT *
    FROM Biblioteca
    WHERE idBiblioteca = p_idBiblioteca;
END //

-- Para Juego
DELIMITER //
CREATE PROCEDURE INSERTAR_JUEGO(
    OUT _idJuego INT,
    IN _titulo VARCHAR(100),
    IN _descripcion TEXT,
    IN _precio DECIMAL(10,2),
    IN _version DECIMAL(3,1),
    IN _imagen TEXT,
    IN _fechaLanzamiento DATE,
    IN _requisitosMinimos TEXT,
    IN _requisitosRecomendados TEXT,
    IN _espacioDisco DOUBLE,
    IN _fechaUltimaActualizacion DATE,
    IN _nombreGenero ENUM('Accion', 'Rol', 'Estrategia', 'Shooter', 'Simulacion', 'Deportes', 'Carreras'),
    IN _modelo ENUM('Free_To_Play', 'Paga', 'Suscripcion'),
    IN _idDesarrollador INT,
    IN _activo INT
)
BEGIN
    INSERT INTO Juego (titulo, descripcion, precio, version, imagenJuego, fechaLanzamiento,
                       requisitosMinimos, requisitosRecomendados, espacioDisco,
                       fechaUltimaActualizacion, nombreGenero, modelo, desarrollador_idDesarrollador, activo)
    VALUES (_titulo, _descripcion, _precio, _version, _imagen, _fechaLanzamiento,
            _requisitosMinimos, _requisitosRecomendados, _espacioDisco,
            _fechaUltimaActualizacion, _nombreGenero, _modelo, _idDesarrollador, _activo);

    SET _idJuego = LAST_INSERT_ID();
END //

DELIMITER //
CREATE PROCEDURE MODIFICAR_JUEGO(
	IN _idJuego INT,
    IN _titulo VARCHAR(100),
    IN _descripcion TEXT,
    IN _precio DECIMAL(10,2),
    IN _version DECIMAL(3,1),
    IN _imagen TEXT,
    IN _fechaLanzamiento DATE,
    IN _requisitosMinimos TEXT,
    IN _requisitosRecomendados TEXT,
    IN _espacioDisco DOUBLE,
    IN _fechaUltimaActualizacion DATE,
    IN _nombreGenero ENUM('Accion', 'Rol', 'Estrategia', 'Shooter', 'Simulacion', 'Deportes', 'Carreras'),
    IN _modelo ENUM('Free_To_Play', 'Paga', 'Suscripcion'),
    IN _idDesarrollador INT
)
BEGIN
    UPDATE Juego
    SET titulo = _titulo,
        descripcion = _descripcion,
        precio = _precio,
        version = _version,
        imagenJuego = _imagen,
        fechaLanzamiento = _fechaLanzamiento,
        requisitosMinimos = _requisitosMinimos,
        requisitosRecomendados = _requisitosRecomendados,
        espacioDisco = _espacioDisco,
        fechaUltimaActualizacion = _fechaUltimaActualizacion,
        nombreGenero = _nombreGenero,
        modelo = _modelo,
        desarrollador_idDesarrollador = _idDesarrollador
    WHERE idJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_JUEGO(IN _idJuego INT)
BEGIN
    UPDATE Juego SET activo = 0 WHERE idJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_JUEGOS_TODOS()
BEGIN
    SELECT * FROM Juego WHERE activo = 1;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_JUEGO_X_ID(IN _idJuego INT)
BEGIN
    SELECT * FROM Juego WHERE idJuego = _idJuego;
END //


-- Para JuegoAdquirido
DELIMITER //
CREATE PROCEDURE INSERTAR_JUEGO_ADQUIRIDO(
    IN _idBiblioteca INT,
    IN _idJuego INT,
    IN _fechaAdquisicion DATE,
    IN _ultimaSesion DATE,
    IN _tiempoJuego DOUBLE,
    IN _actualizado BOOLEAN,
    IN _activo INT
)
BEGIN
    INSERT INTO JuegoAdquirido (fidBiblioteca, fidJuego, fechaAdquisicion, ultimaSesion, tiempoJuego, actualizado, activo)
    VALUES (_idBiblioteca, _idJuego, _fechaAdquisicion, _ultimaSesion, _tiempoJuego, _actualizado, _activo);
END //

DELIMITER //
CREATE PROCEDURE MODIFICAR_JUEGO_ADQUIRIDO(
    IN _idBiblioteca INT,
    IN _idJuego INT,
    IN _fechaAdquisicion DATE,
    IN _ultimaSesion DATE,
    IN _tiempoJuego DOUBLE,
    IN _actualizado BOOLEAN
)
BEGIN
    UPDATE JuegoAdquirido
    SET fechaAdquisicion = _fechaAdquisicion,
        ultimaSesion = _ultimaSesion,
        tiempoJuego = _tiempoJuego,
        actualizado = _actualizado
    WHERE fidBiblioteca = _idBiblioteca AND fidJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_JUEGO_ADQUIRIDO(IN _idJuego INT)
BEGIN
    UPDATE JuegoAdquirido SET activo = 0 WHERE fidJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_JUEGOS_ADQUIRIDOS_TODOS()
BEGIN
    SELECT * FROM JuegoAdquirido WHERE activo = 1;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_JUEGO_ADQUIRIDO_POR_ID(IN _idJuego INT)
BEGIN
    SELECT * FROM JuegoAdquirido WHERE fidJuego = _idJuego;
END //



