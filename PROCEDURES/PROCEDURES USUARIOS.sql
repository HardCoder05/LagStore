DELIMITER $$
DROP PROCEDURE IF EXISTS TA.INSERTAR_DESARROLLADOR$$

DELIMITER $$
CREATE PROCEDURE TA.INSERTAR_DESARROLLADOR(
	OUT _idDesarrollador INT,
    IN _nombre VARCHAR(100),
    IN _email VARCHAR(100),
    IN _contrasena VARCHAR(100),
    IN _fechaRegistro DATE,
    IN _telefono VARCHAR(12),
    IN _fotoPerfil BLOB,
    IN _numeroCuenta VARCHAR(50),
    IN _ingresoTotal DOUBLE
)
BEGIN
    INSERT INTO Usuario(nombre,email,contrasena,fechaRegistro,telefono,fotoDePerfil,activo,nombreRol)
    VALUES(_nombre,_email,_contrasena,_fechaRegistro,_telefono,_fotoPerfil,1,"Desarrollador");
    SET _idDesarrollador=@@last_insert_id;
    INSERT INTO Desarrollador(idDesarrollador, numeroCuenta,ingresoTotal)
    VALUES (_idDesarrollador, _numeroCuenta,_ingresoTotal);
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.MODIFICAR_DESARROLLADOR$$
DELIMITER $$
CREATE PROCEDURE TA.MODIFICAR_DESARROLLADOR(
    IN _idDesarrollador INT,
    IN _numeroCuenta VARCHAR(200),
    IN _ingresoTotal DOUBLE,
    IN _nombre VARCHAR(100),
    IN _email VARCHAR(100),
    IN _contrasena VARCHAR(100),
    IN _fechaRegistro DATE,
    IN _telefono VARCHAR(12),
    IN _fotoDePerfil BLOB
)
BEGIN
	UPDATE Usuario SET nombre = _nombre, 
						email=_email, 
						contrasena=_contrasena, 
						fechaRegistro=_fechaRegistro, 
						telefono=_telefono, 
						fotoDePerfil=_fotoDePerfil
                        WHERE id=_idDesarrollador;

    UPDATE Desarrollador SET numeroCuenta=_numeroCuenta, 
							ingresoTotal=_ingresoTotal
						WHERE idDesarrollador=_idDesarrollador;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.ELIMINAR_DESARROLLADOR$$

DELIMITER $$
CREATE PROCEDURE TA.ELIMINAR_DESARROLLADOR(
    IN _idDesarrollador INT
)
BEGIN
	UPDATE Usuario SET activo=0 WHERE id=_idDesarrollador;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.LISTAR_DESARROLLADOR$$
DELIMITER $$
CREATE PROCEDURE TA.LISTAR_DESARROLLADOR()
BEGIN
	SELECT u.id,u.nombre, u.email, u.contrasena, u.fechaRegistro, u.telefono, u.fotoDePerfil, d.numeroCuenta, d.ingresoTotal
    FROM Desarrollador d
    INNER JOIN Usuario u ON u.id = d.idDesarrollador
    WHERE u.activo =1;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.OBTENER_X_ID_DESARROLLADOR$$
DELIMITER $$
CREATE PROCEDURE TA.OBTENER_X_ID_DESARROLLADOR(
    IN _idDesarrollador INT
)
BEGIN
    SELECT u.nombre, u.email, u.contrasena, u.fechaRegistro, u.telefono, u.fotoDePerfil, 
           d.numeroCuenta,d.ingresoTotal
    FROM Desarrollador d
    INNER JOIN Usuario u ON u.id = d.idDesarrollador
    WHERE u.activo=1 AND d.idDesarrollador=_idDesarrollador;
END $$



DELIMITER $$
DROP PROCEDURE IF EXISTS TA.INSERTAR_JUGADOR$$
DELIMITER $
CREATE PROCEDURE TA.INSERTAR_JUGADOR(
	OUT _idJugador INT,
    IN _nombre VARCHAR(100),
    IN _email VARCHAR(100),
    IN _contrasena VARCHAR(100),
    IN _fechaRegistro DATE,
    IN _telefono VARCHAR(12),
    IN _fotoPerfil BLOB,
    IN _nickname VARCHAR(50)
)
BEGIN
    INSERT INTO Usuario(nombre,email,contrasena,fechaRegistro,telefono,fotoDePerfil,activo,nombreRol)
    VALUES(_nombre,_email,_contrasena,_fechaRegistro,_telefono,_fotoPerfil,1,"Jugador");
    SET _idJugador=@@last_insert_id;
    INSERT INTO Jugador(idJugador, nickname)
    VALUES (_idJugador, _nickname);
END $

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.MODIFICAR_JUGADOR$$

DELIMITER $$
CREATE PROCEDURE TA.MODIFICAR_JUGADOR(
    IN _idJugador INT,
    IN _nickname VARCHAR(200),
    IN _nombre VARCHAR(100),
    IN _email VARCHAR(100),
    IN _contrasena VARCHAR(100),
    IN _fechaRegistro DATE,
    IN _telefono VARCHAR(12),
    IN _fotoDePerfil BLOB
)
BEGIN
	UPDATE Usuario SET nombre = _nombre, 
						email=_email, 
						contrasena=_contrasena, 
						fechaRegistro=_fechaRegistro, 
						telefono=_telefono, 
						fotoDePerfil=_fotoDePerfil
                        WHERE id=_idJugador ;

    UPDATE Jugador SET nickname=_nickname
	WHERE idJugador=_idJugador;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.ELIMINAR_JUGADOR$$

DELIMITER $$
CREATE PROCEDURE TA.ELIMINAR_JUGADOR(
    IN _idJugador INT
)
BEGIN
	UPDATE Usuario SET activo = 0 WHERE id=_idJugador;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.LISTAR_JUGADOR$$
DELIMITER $$
CREATE PROCEDURE TA.LISTAR_JUGADOR()
BEGIN
	SELECT u.id,u.nombre, u.email, u.contrasena, u.fechaRegistro, u.telefono, u.fotoDePerfil, j.nickname as nicknameJugador
    FROM Jugador j
    INNER JOIN Usuario u ON u.id = j.idJugador
    WHERE u.activo =1;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS TA.OBTENER_X_ID_JUGADOR$$
DELIMITER $$
CREATE PROCEDURE TA.OBTENER_X_ID_JUGADOR(
    IN _idJugador INT
)
BEGIN
    SELECT u.nombre, u.email, u.contrasena, u.fechaRegistro, u.telefono, u.fotoDePerfil, 
           j.nickname AS nombreJugador
    FROM Jugador j
    INNER JOIN Usuario u ON u.id = j.idJugador
    WHERE j.idJugador=_idJugador;
END $$
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

DELIMITER $$
DROP PROCEDURE IF EXISTS INSERTAR_ADMINISTRADOR$$
DELIMITER $$
CREATE PROCEDURE TA.INSERTAR_ADMINISTRADOR(
    OUT _idAdministrador INT,
    IN _rol VARCHAR(50),
    
    IN _nombre VARCHAR(100),
    IN _email VARCHAR(100),
    IN _contrasena VARCHAR(100),
    IN _fechaRegistro DATE,
    IN _telefono VARCHAR(12),
    IN _fotoPerfil BLOB
)
BEGIN
	INSERT INTO Usuario(nombre, email, contrasena, fechaRegistro, telefono, fotoDePerfil, activo,nombreRol)
    VALUES (_nombre, _email, _contrasena, _fechaRegistro, _telefono, _fotoPerfil, 1,"Administrador");
    SET _idAdministrador = @@last_insert_id;
    INSERT INTO Administrador(idAdministrador, rol)
    VALUES (_idAdministrador, _rol);
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS MODIFICAR_ADMINISTRADOR$$
DELIMITER $$
CREATE PROCEDURE TA.MODIFICAR_ADMINISTRADOR(
    IN _idAdministrador INT,
    IN _rol VARCHAR(50),
    IN _nombre VARCHAR(100),
    IN _email VARCHAR(100),
    IN _contrasena VARCHAR(100),
    IN _fechaRegistro DATE,
    IN _telefono VARCHAR(12),
    IN _fotoPerfil BLOB
)
BEGIN
	UPDATE Usuario SET nombre = _nombre, 
						email=_email, 
						contrasena=_contrasena, 
						fechaRegistro=_fechaRegistro, 
						telefono=_telefono, 
						fotoDePerfil=_fotoPerfil
                        WHERE id=_idAdministrador;

    UPDATE Administrador SET rol=_rol 
						WHERE idAdministrador=_idAdministrador;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS ELIMINAR_ADMINISTRADOR$$
CREATE PROCEDURE TA.ELIMINAR_ADMINISTRADOR(
    IN _idAdministrador INT
)
BEGIN
	UPDATE Usuario SET activo=0 WHERE id=_idAdministrador;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS LISTAR_ADMINISTRADOR$$
DELIMITER $$
CREATE PROCEDURE TA.LISTAR_ADMINISTRADOR()
BEGIN
	SELECT u.id,u.nombre, u.email, u.contrasena, u.fechaRegistro, u.telefono, u.fotoDePerfil, a.rol as nombre_administrador
    FROM Administrador a
    INNER JOIN Usuario u ON u.id = a.idAdministrador
    WHERE u.activo =1;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS OBTENER_X_ID_ADMINISTRADOR$$

DELIMITER $$
CREATE PROCEDURE TA.OBTENER_X_ID_ADMINISTRADOR(
	IN _idAdministrador INT
)
BEGIN
	SELECT u.nombre, u.email, u.contrasena, u.fechaRegistro, u.telefono, u.fotoDePerfil, a.rol as nombre_administrador
    FROM Administrador a
    INNER JOIN Usuario u ON u.id = a.idAdministrador
    WHERE  _idAdministrador=a.idAdministrador;
END $$

-- CALL TA.INSERTAR_JUGADOR(@idJugador,'Carlos','carlos.gomez@mail.com','password123','2025-05-13','999888777', NULL,'buba20');
-- CALL TA.MODIFICAR_JUGADOR(2,'OTRO','XX','XX@HTOA.COM','NUEVA CONTRA','2022-01-25','999999',NULL);
-- CALL TA.ELIMINAR_JUGADOR(2);
-- CALL TA.LISTAR_JUGADOR();
-- CALL TA.OBTENER_X_ID_JUGADOR(2);





