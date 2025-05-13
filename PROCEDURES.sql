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
    IN _idGenero INT,
    IN _idModeloNegocio INT,
    IN _idDesarrollador INT
)
BEGIN
    INSERT INTO juego (titulo, descripcion, precio, version, imagenJuego, fechaLanzamiento,
                       requisitosMinimos, requisitosRecomendados, espacioDisco,
                       fechaUltimaActualizacion, idGenero, idModeloNegocio, idDesarrollador)
    VALUES (_titulo, _descripcion, _precio, _version, _imagen, _fechaLanzamiento,
            _requisitosMinimos, _requisitosRecomendados, _espacioDisco,
            _fechaUltimaActualizacion, _idGenero, _idModeloNegocio, _idDesarrollador);

    SET _idJuego = LAST_INSERT_ID();
END //

DDELIMITER //
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
    IN _idGenero INT,
    IN _idModeloNegocio INT,
    IN _idDesarrollador INT
)
BEGIN
    UPDATE juego
    SET titulo = _titulo,
        descripcion = _descripcion,
        precio = _precio,
        version = _version,
        imagen = _imagen,
        fechaLanzamiento = _fechaLanzamiento,
        requisitosMinimos = _requisitosMinimos,
        requisitosRecomendados = _requisitosRecomendados,
        espacioDisco = _espacioDisco,
        fechaUltimaActualizacion = _fechaUltimaActualizacion,
        idGenero = _idGenero,
        idModeloNegocio = _idModeloNegocio,
        idDesarrollador = _idDesarrollador
    WHERE idJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_JUEGO(IN _idJuego INT)
BEGIN
    DELETE FROM juego WHERE idJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_JUEGOS_TODOS()
BEGIN
    SELECT * FROM juego;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_JUEGO_X_ID(IN _idJuego INT)
BEGIN
    SELECT * FROM juego WHERE idJuego = _idJuego;
END //


-- Para JuegoAdquirido
DELIMITER //
CREATE PROCEDURE INSERTAR_JUEGO_ADQUIRIDO(
    IN _idBiblioteca INT,
    IN _idJuego INT,
    IN _fechaAdquisicion DATE,
    IN _ultimaSesion DATE,
    IN _tiempoJuego DOUBLE,
    IN _actualizado BOOLEAN
)
BEGIN
    INSERT INTO juego_adquirido (fidBiblioteca, fidJuego, fechaAdquisicion, ultimaSesion, tiempoJuego, actualizado)
    VALUES (_idBiblioteca, _idJuego, _fechaAdquisicion, _ultimaSesion, _tiempoJuego, _actualizado);
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
    UPDATE juego_adquirido
    SET fechaAdquisicion = _fechaAdquisicion,
        ultimaSesion = _ultimaSesion,
        tiempoJuego = _tiempoJuego,
        actualizado = _actualizado
    WHERE fidBiblioteca = _idBiblioteca AND fidJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_JUEGO_ADQUIRIDO(IN _idJuego INT)
BEGIN
    DELETE FROM juego_adquirido WHERE fidJuego = _idJuego;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_JUEGOS_ADQUIRIDOS_TODOS()
BEGIN
    SELECT * FROM juego_adquirido;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_JUEGO_ADQUIRIDO_POR_ID(IN _idJuego INT)
BEGIN
    SELECT * FROM juego_adquirido WHERE fidJuego = _idJuego;
END //


-- Para Genero
DELIMITER //
CREATE PROCEDURE INSERTAR_GENERO (
    IN _nombreGenero ENUM('ACCCION', 'ROL', 'ESTRATEGIA', 'SHOOTER', 'SIMULACION', 'DEPORTES', 'CARRERAS')
)
BEGIN
    INSERT INTO Genero(nombreGenero) VALUES (_nombreGenero);
END //

DELIMITER //
CREATE PROCEDURE MODIFICAR_GENERO (
    IN _idGenero INT,
    IN _nombreGenero ENUM('ACCCION', 'ROL', 'ESTRATEGIA', 'SHOOTER', 'SIMULACION', 'DEPORTES', 'CARRERAS')
)
BEGIN
    UPDATE Genero SET nombreGenero = _nombreGenero WHERE idGenero = _idGenero;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_GENERO (
    IN _idGenero INT
)
BEGIN
    DELETE FROM Genero WHERE idGenero = _idGenero;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_GENEROS_TODOS ()
BEGIN
    SELECT * FROM Genero;
END //


-- Para ModeloNegocio
DELIMITER //
CREATE PROCEDURE INSERTAR_MODELO_NEGOCIO (
    IN _modelo ENUM('FREE_TO_PLAY', 'PAGA', 'SUSCRIPCION')
)
BEGIN
    INSERT INTO ModeloNegocio(modelo) VALUES (_modelo);
END //

DELIMITER //
CREATE PROCEDURE MODIFICAR_MODELO_NEGOCIO (
    IN _idModeloNegocio INT,
    IN _modelo ENUM('FREE_TO_PLAY', 'PAGA', 'SUSCRIPCION')
)
BEGIN
    UPDATE ModeloNegocio SET modelo = _modelo WHERE idModeloNegocio = _idModeloNegocio;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_MODELO_NEGOCIO (
    IN _idModeloNegocio INT
)
BEGIN
    DELETE FROM ModeloNegocio WHERE idModeloNegocio = _idModeloNegocio;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_MODELOS_NEGOCIO_TODOS ()
BEGIN
    SELECT * FROM ModeloNegocio;
END //


