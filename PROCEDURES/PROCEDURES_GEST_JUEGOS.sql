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
    WHERE idBiblioteca = p_idBiblioteca
    AND activo = 1;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_BIBLIOTECA_X_USUARIO (
  IN p_idUsuario INT
)
BEGIN
  SELECT * FROM Biblioteca
  WHERE usuario_idUsuario = p_idUsuario;
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
        
        -- Datos del desarrollador (usuario)
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
    WHERE j.activo = 1;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE OBTENER_JUEGO_X_ID(IN _idJuego INT)
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
    WHERE j.idJuego = _idJuego
    AND j.activo = 1;
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
    DECLARE _precioJuego DOUBLE;

    -- Obtener el precio del juego
    SELECT precio INTO _precioJuego FROM Juego WHERE idJuego = _idJuego;

    -- Insertar en la tabla JuegoAdquirido
    INSERT INTO JuegoAdquirido (
        fidBiblioteca, fidJuego, fechaAdquisicion, ultimaSesion, tiempoJuego, actualizado, activo
    )
    VALUES (
        _idBiblioteca, _idJuego, _fechaAdquisicion, _ultimaSesion, _tiempoJuego, _actualizado, _activo
    );

    -- Actualizar ingresoTotal y cantidadDeJuegos en la Biblioteca
    UPDATE Biblioteca
    SET 
        ingresoTotal = COALESCE(ingresoTotal, 0) + IFNULL(_precioJuego, 0),
        cantidadDeJuegos = COALESCE(cantidadDeJuegos, 0) + 1
    WHERE idBiblioteca = _idBiblioteca;
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
    SELECT 
        ja.fidBiblioteca,
        ja.fidJuego,
        ja.fechaAdquisicion,
        ja.ultimaSesion,
        ja.tiempoJuego,
        ja.actualizado,
        ja.activo,

        -- Datos del juego
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
        j.activo AS juegoActivo,

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

    FROM JuegoAdquirido ja
    INNER JOIN Juego j ON ja.fidJuego = j.idJuego
    INNER JOIN Desarrollador d ON j.desarrollador_idDesarrollador = d.idDesarrollador
    INNER JOIN Usuario u ON d.idDesarrollador = u.id
    WHERE ja.activo = 1;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_JUEGO_ADQUIRIDO_POR_ID(IN _idJuego INT)
BEGIN
    SELECT 
        ja.fidBiblioteca,
        ja.fidJuego,
        ja.fechaAdquisicion,
        ja.ultimaSesion,
        ja.tiempoJuego,
        ja.actualizado,
        ja.activo,

        -- Datos del juego
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
        j.activo AS juegoActivo,

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

    FROM  ja
    INNER JOIN Juego j ON ja.fidJuego = j.idJuego
    INNER JOIN Desarrollador d ON j.desarrollador_idDesarrollador = d.idDesarrollador
    INNER JOIN Usuario u ON d.idDesarrollador = u.id
    WHERE ja.fidJuego = _idJuego
    AND ja.activo = 1;
END //

DELIMITER //
CREATE PROCEDURE LISTAR_JUEGOS_ADQUIRIDOS_X_BIBLIOTECA (
  IN p_idBiblioteca INT
)
BEGIN
  SELECT ja.*, j.titulo, j.precio, j.modelo, j.nombreGenero, j.imagenJuego, j.idJuego
  FROM JuegoAdquirido ja
  INNER JOIN Juego j ON ja.fidJuego = j.idJuego AND j.activo = 1
  WHERE ja.fidBiblioteca = p_idBiblioteca AND ja.activo = 1;
END //

DELIMITER //
CREATE PROCEDURE OBTENER_JUEGO_ADQUIRIDO_POR_BIBLIOTECA_Y_JUEGO (
  IN p_idBiblioteca INT,
  IN p_idJuego INT
)
BEGIN
  SELECT * FROM JuegoAdquirido
  WHERE fidBiblioteca = p_idBiblioteca AND fidJuego = p_idJuego AND activo = 1;
END //

DELIMITER //
CREATE PROCEDURE ELIMINAR_JUEGO_ADQUIRIDO_POR_BIBLIOTECA_Y_JUEGO (
  IN p_idBiblioteca INT,
  IN p_idJuego INT
)
BEGIN
  UPDATE JuegoAdquirido
  SET activo = 0
  WHERE fidBiblioteca = p_idBiblioteca AND fidJuego = p_idJuego;
END //





