-- /////////////////////////////////////////////////////////////////////////////////////////////
-- PROCEDUREs PARA TABLA CarroCompra
-- /////////////////////////////////////////////////////////////////////////////////////////////

-- INSERTAR_CARRO_COMPRA
DELIMITER //
CREATE PROCEDURE INSERTAR_CARRO_COMPRA(
    OUT p_idCarroCompra INT,
    IN p_totalEstimado DOUBLE,
    IN p_jugadorId INT
)BEGIN
    INSERT INTO CarroCompra (totalEstimado, jugador_idJugador, activo)
    VALUES (p_totalEstimado, p_jugadorId, 1);
    SET p_idCarroCompra = LAST_INSERT_ID();
END //
DELIMITER ;

-- MODIFICAR_CARRO_COMPRA
DELIMITER //
CREATE PROCEDURE MODIFICAR_CARRO_COMPRA(
    IN p_idCarroCompra INT,
    IN p_totalEstimado DOUBLE,
    IN p_jugadorId INT
)BEGIN
    UPDATE CarroCompra
    SET totalEstimado = p_totalEstimado,
        jugador_idJugador = p_jugadorId
    WHERE idCarroCompra = p_idCarroCompra AND activo = 1;
END //
DELIMITER ;

-- ELIMINAR_CARRO_COMPRA
DELIMITER //
CREATE PROCEDURE ELIMINAR_CARRO_COMPRA(
    IN p_idCarroCompra INT
)BEGIN
    UPDATE CarroCompra
    SET activo = 0
    WHERE idCarroCompra = p_idCarroCompra;
END //
DELIMITER ;

-- LISTAR_CARRO_COMPRAS
DELIMITER //
CREATE PROCEDURE LISTAR_CARRO_COMPRAS()
BEGIN
    SELECT idCarroCompra, totalEstimado, jugador_idJugador, activo
    FROM CarroCompra;
END //
DELIMITER ;

-- OBTENER_CARRO_COMPRA_POR_ID
DELIMITER //
CREATE PROCEDURE OBTENER_CARRO_COMPRA_POR_ID(
    IN p_idCarroCompra INT
)BEGIN
    SELECT idCarroCompra, totalEstimado, jugador_idJugador, activo
    FROM CarroCompra
    WHERE idCarroCompra = p_idCarroCompra;
END //
DELIMITER ;



-- /////////////////////////////////////////////////////////////////////////////////////////////
-- PROCEDUREs PARA TABLA Recarga
-- /////////////////////////////////////////////////////////////////////////////////////////////

--INSERTAR RECARGA
DELIMITER //
CREATE PROCEDURE INSERTAR_RECARGA(
    OUT p_idRecarga INT,
    IN p_fechaRecarga DATE,
    IN p_monto DOUBLE,
    IN p_nombreMetodo VARCHAR(50),
    IN p_carteraId INT
)BEGIN
    DECLARE v_idMetodo INT;

    -- Buscar idMetodo según nombreMetodo
    SELECT idMetodo INTO v_idMetodo
    FROM MetodoPago
    WHERE nombreMetodo = p_nombreMetodo;

    -- Insertar recarga
    INSERT INTO Recarga (fechaRecarga, monto, metodoPago_idMetodo, cartera_idCartera, activo)
    VALUES (p_fechaRecarga, p_monto, v_idMetodo, p_carteraId, 1);

    -- Devolver id generado
    SET p_idRecarga = LAST_INSERT_ID();

    -- Actualizar saldo en cartera
    UPDATE Cartera
    SET saldoActual = saldoActual + p_monto
    WHERE idCartera = p_carteraId;
END //
DELIMITER ;

-- ELIMINAR RECARGA
DELIMITER //
CREATE PROCEDURE ELIMINAR_RECARGA(
    IN p_idRecarga INT
)BEGIN
    UPDATE Recarga
    SET activo = 0
    WHERE idRecarga = p_idRecarga;
END //
DELIMITER ;

-- MODIFICAR RECARGA
DELIMITER //
CREATE PROCEDURE MODIFICAR_RECARGA(
    IN p_idRecarga INT,
    IN p_fechaRecarga DATE,
    IN p_monto DOUBLE,
    IN p_nombreMetodo VARCHAR(50),
    IN p_carteraId INT
)BEGIN
    DECLARE v_idMetodo INT;

    -- Buscar idMetodo segun nombreMetodo
    SELECT idMetodo INTO v_idMetodo
    FROM MetodoPago
    WHERE nombreMetodo = p_nombreMetodo;

    UPDATE Recarga
    SET fechaRecarga = p_fechaRecarga,
        monto = p_monto,
        metodoPago_idMetodo = v_idMetodo,
        cartera_idCartera = p_carteraId
    WHERE idRecarga = p_idRecarga AND activo = 1;
END //
DELIMITER ;




-- LISTAR RECARGAS
DELIMITER //
CREATE PROCEDURE LISTAR_RECARGAS()
BEGIN
    SELECT r.idRecarga, r.fechaRecarga, r.monto,
           mp.nombreMetodo,
           r.cartera_idCartera,
           r.activo
    FROM Recarga r
    INNER JOIN MetodoPago mp ON r.metodoPago_idMetodo = mp.idMetodo;
END //
DELIMITER ;


-- OBTENER RECARGA POR ID
DELIMITER //
CREATE PROCEDURE OBTENER_RECARGA_POR_ID(
    IN p_idRecarga INT
)BEGIN
    SELECT r.idRecarga, r.fechaRecarga, r.monto,
           mp.nombreMetodo,
           r.cartera_idCartera,
           r.activo
    FROM Recarga r
    INNER JOIN MetodoPago mp ON r.metodoPago_idMetodo = mp.idMetodo
    WHERE r.idRecarga = p_idRecarga;
END //
DELIMITER ;


-- /////////////////////////////////////////////////////////////////////////////////////////////
-- PROCEDUREs PARA TABLA Cartera
-- /////////////////////////////////////////////////////////////////////////////////////////////


DELIMITER //

-- INSERTAR_CARTERA
CREATE PROCEDURE INSERTAR_CARTERA(
    OUT p_idCartera INT,
    IN p_saldoActual DOUBLE,
    IN p_jugadorId INT
)BEGIN
    INSERT INTO Cartera (saldoActual, jugador_idJugador, activo)
    VALUES (p_saldoActual, p_jugadorId, 1);

    SET p_idCartera = LAST_INSERT_ID();
END //

-- MODIFICAR_CARTERA
CREATE PROCEDURE MODIFICAR_CARTERA(
    IN p_idCartera INT,
    IN p_saldoActual DOUBLE,
    IN p_jugadorId INT
)BEGIN
    UPDATE Cartera
    SET saldoActual = p_saldoActual,
        jugador_idJugador = p_jugadorId
    WHERE idCartera = p_idCartera AND activo = 1;
END //

-- ELIMINAR_CARTERA (Soft delete)
CREATE PROCEDURE ELIMINAR_CARTERA(
    IN p_idCartera INT
)BEGIN
    UPDATE Cartera
    SET activo = 0
    WHERE idCartera = p_idCartera;
END //

-- LISTAR_CARTERAS
CREATE PROCEDURE LISTAR_CARTERAS()
BEGIN
    SELECT idCartera, saldoActual, jugador_idJugador, activo
    FROM Cartera;
END //

--  OBTENER_CARTERA_POR_ID
CREATE PROCEDURE OBTENER_CARTERA_POR_ID(
    IN p_idCartera INT
)BEGIN
    SELECT idCartera, saldoActual, jugador_idJugador, activo
    FROM Cartera
    WHERE idCartera = p_idCartera;
END //

DELIMITER ;


-- /////////////////////////////////////////////////////////////////////////////////////////////
-- PROCEDUREs PARA TABLA METODOPAGO
-- /////////////////////////////////////////////////////////////////////////////////////////////
DELIMITER //

-- INSERTAR_METODOPAGO
CREATE PROCEDURE INSERTAR_METODOPAGO(
    IN p_nombreMetodo VARCHAR(50)
)BEGIN
    -- Prevención al insertar si ya existe
    IF NOT EXISTS (SELECT 1 FROM MetodoPago WHERE nombreMetodo = p_nombreMetodo) THEN
        INSERT INTO MetodoPago (nombreMetodo, activo)
        VALUES (p_nombreMetodo, 1);
    END IF;
END //

-- MODIFICAR_METODOPAGO
CREATE PROCEDURE MODIFICAR_METODOPAGO(
    IN p_idMetodo INT,
    IN p_nombreMetodo VARCHAR(50)
)BEGIN
    UPDATE MetodoPago
    SET nombreMetodo = p_nombreMetodo
    WHERE idMetodo = p_idMetodo AND activo = 1;
END //

-- ELIMINAR_METODOPAGO (Soft delete)
CREATE PROCEDURE ELIMINAR_METODOPAGO(
    IN p_idMetodo INT
)BEGIN
    UPDATE MetodoPago
    SET activo = 0
    WHERE idMetodo = p_idMetodo;
END //

--  LISTAR_METODOSPAGO
CREATE PROCEDURE LISTAR_METODOSPAGO()
BEGIN
    SELECT idMetodo, nombreMetodo, activo
    FROM MetodoPago;
END //

DELIMITER ;