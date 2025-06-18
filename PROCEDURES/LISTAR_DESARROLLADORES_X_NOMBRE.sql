DROP PROCEDURE IF EXISTS LISTAR_DESARROLLADORES_X_NOMBRE;

DELIMITER $
CREATE  PROCEDURE LISTAR_DESARROLLADORES_X_NOMBRE(
	IN textoBusqueda VARCHAR(100)
)
BEGIN
	SELECT u.id, u.nombre, u.email, u.fechaRegistro, u.telefono, u.fotoDePerfil, d.numeroCuenta,d.ingresoTotal
    FROM Desarrollador d
    INNER JOIN Usuario u ON u.id = d.idDesarrollador
    WHERE u.activo = 1 AND (
        u.nombre LIKE CONCAT('%', textoBusqueda, '%') 
    );
END$