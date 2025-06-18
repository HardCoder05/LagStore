-- ////////////////////////////////////////////////////////////////////////////////////
-- PROCEDIMIENTOS DE CARRO COMPRA
-- ////////////////////////////////////////////////////////////////////////////////////
USE TA;


DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_CARRO_COMPRA`(
    OUT p_idCarroCompra INT,
    IN p_totalEstimado DOUBLE,
    IN p_jugadorId INT
)
BEGIN
    INSERT INTO CarroCompra (totalEstimado, jugador_idJugador, activo)
    VALUES (p_totalEstimado, p_jugadorId, 1);
    SET p_idCarroCompra = LAST_INSERT_ID();
END $$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `AGREGAR_JUEGO_A_CARRO`(
    IN p_idCarroCompra INT,
    IN p_idJuego INT
)
BEGIN
    INSERT INTO CarroCompraXJuego (
        carroCompra_idCarroCompra, juego_idJuego
    ) VALUES (
        p_idCarroCompra, p_idJuego
    );
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_CARRO_COMPRA`(
    IN p_idCarroCompra INT
)
BEGIN
    UPDATE CarroCompra
    SET activo = 0
    WHERE idCarroCompra = p_idCarroCompra;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_JUEGOS_DE_CARRO`(
    IN p_idCarroCompra INT
)
BEGIN
    DELETE FROM CarroCompraXJuego WHERE carroCompra_idCarroCompra = p_idCarroCompra;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_CARRO_COMPRAS`()
BEGIN
    SELECT idCarroCompra, totalEstimado, jugador_idJugador, activo
    FROM CarroCompra;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_JUEGOS_DE_CARRO`(
    IN p_idCarroCompra INT
)
BEGIN
    SELECT j.*
    FROM Juego j
    INNER JOIN CarroCompraXJuego cxj ON j.idJuego = cxj.juego_idJuego
    WHERE cxj.carroCompra_idCarroCompra = p_idCarroCompra AND j.activo = 1;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_CARRO_COMPRA`(
    IN p_idCarroCompra INT,
    IN p_totalEstimado DOUBLE,
    IN p_jugadorId INT
)
BEGIN
    UPDATE CarroCompra
    SET totalEstimado = p_totalEstimado,
        jugador_idJugador = p_jugadorId 
    WHERE idCarroCompra = p_idCarroCompra AND activo = 1;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_CARRO_COMPRA_POR_ID`(
    IN p_idCarroCompra INT
)
BEGIN
    SELECT idCarroCompra, totalEstimado, jugador_idJugador, activo
    FROM CarroCompra
    WHERE idCarroCompra = p_idCarroCompra;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_JUEGO_DE_CARRO`(
    IN p_idCarroCompra INT,
    IN p_idJuego INT
)
BEGIN
    DELETE FROM CarroCompraXJuego WHERE carroCompra_idCarroCompra = p_idCarroCompra AND juego_idJuego = p_idJuego;
END $$
DELIMITER ;


-- ////////////////////////////////////////////////////////////////////////////////////
-- PROCEDIMIENTOS DE CARTERA
-- ////////////////////////////////////////////////////////////////////////////////////
DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_CARTERA`(
    IN p_idCartera INT
)
BEGIN
    UPDATE Cartera
    SET activo = 0
    WHERE idCartera = p_idCartera;
END $$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_CARTERA`(
    OUT p_idCartera INT,
    IN p_saldoActual DOUBLE,
    IN p_jugadorId INT
)
BEGIN
    INSERT INTO Cartera (saldoActual, jugador_idJugador, activo)
    VALUES (p_saldoActual, p_jugadorId, 1);

    SET p_idCartera = LAST_INSERT_ID();
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_CARTERAS`()
BEGIN
    SELECT idCartera, saldoActual, jugador_idJugador, activo
    FROM Cartera;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_CARTERA`(
    IN p_idCartera INT,
    IN p_saldoActual DOUBLE,
    IN p_jugadorId INT
)
BEGIN
    UPDATE Cartera
    SET saldoActual = p_saldoActual,
        jugador_idJugador = p_jugadorId
    WHERE idCartera = p_idCartera AND activo = 1;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_CARTERA_POR_ID`(
    IN p_idCartera INT
)
BEGIN
    SELECT idCartera, saldoActual, jugador_idJugador, activo
    FROM Cartera
    WHERE idCartera = p_idCartera;
END $$
DELIMITER ;



-- ////////////////////////////////////////////////////////////////////////////////////
-- PROCEDIMIENTOS DE RECARGA
-- ////////////////////////////////////////////////////////////////////////////////////



DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_RECARGA`(
    OUT p_idRecarga INT,
    IN p_fechaRecarga DATE,
    IN p_monto DOUBLE,
    IN p_nombreMetodo VARCHAR(50),
    IN p_carteraId INT
)
BEGIN
    -- Insertar recarga directamente con el ENUM
    INSERT INTO Recarga (
        fechaRecarga,
        monto,
        nombreMetodo,
        cartera_idCartera,
        activo
    )
    VALUES (
        p_fechaRecarga,
        p_monto,
        p_nombreMetodo,
        p_carteraId,
        1
    );

    -- Obtener el ID generado
    SET p_idRecarga = LAST_INSERT_ID();

    -- Actualizar el saldo en la cartera
    UPDATE Cartera
    SET saldoActual = saldoActual + p_monto
    WHERE idCartera = p_carteraId;
END $$
DELIMITER $$

DELIMITER $$

 $$

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_RECARGAS`()
BEGIN
    SELECT 
        r.idRecarga, 
        r.fechaRecarga, 
        r.monto,
        r.nombreMetodo,         
        r.cartera_idCartera,
        r.activo
    FROM Recarga r;
END $$


DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_RECARGA`(
    IN p_idRecarga INT
)
BEGIN
    UPDATE Recarga
    SET activo = 0
    WHERE idRecarga = p_idRecarga;
END

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_RECARGA`(
    IN p_idRecarga INT,
    IN p_fechaRecarga DATE,
    IN p_monto DOUBLE,
    IN p_nombreMetodo VARCHAR(50),
    IN p_carteraId INT
)
BEGIN
    DECLARE v_monto_anterior DOUBLE;
    DECLARE v_cartera_anterior INT;

    SELECT monto, cartera_IdCartera INTO v_monto_anterior, v_cartera_anterior
    FROM Recarga
    WHERE idRecarga = p_idRecarga;

    UPDATE Cartera
    SET saldoActual = saldoActual - v_monto_anterior
    WHERE idCartera = v_cartera_anterior;

    UPDATE Recarga 
    SET 
        fechaRecarga = p_fechaRecarga,
        monto = p_monto,
        nombreMetodo = p_nombreMetodo,
        cartera_IdCartera = p_carteraId
    WHERE 
        idRecarga = p_idRecarga
        AND activo = 1;

    UPDATE Cartera
    SET saldoActual = saldoActual + p_monto
    WHERE idCartera = p_carteraId;
END $$


DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_RECARGA_POR_ID`(
    IN p_idRecarga INT
)
BEGIN
    SELECT 
        r.idRecarga, 
        r.fechaRecarga, 
        r.monto,
        r.nombreMetodo,           
        r.cartera_idCartera,
        r.activo
    FROM Recarga r
    WHERE r.idRecarga = p_idRecarga;
END $$
DELIMITER $$