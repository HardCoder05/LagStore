
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema TA
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `TA` ;

-- -----------------------------------------------------
-- Schema TA
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TA`;
USE `TA` ;



-- -----------------------------------------------------
-- Table `TA`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `contrasena` VARCHAR(100) NULL DEFAULT NULL,
  `fechaRegistro` DATE NULL DEFAULT NULL,
  `telefono` VARCHAR(12),
  `fotoDePerfil` BLOB NULL DEFAULT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  `nombreRol` ENUM('Jugador', 'Administrador', 'Desarrollador') NOT NULL,
  PRIMARY KEY (`id`)
  )ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `TA`.`Biblioteca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Biblioteca` (
  `idBiblioteca` INT NOT NULL AUTO_INCREMENT,
  `ingresoTotal` DOUBLE NULL DEFAULT NULL,
  `cantidadDeJuegos` INT NULL DEFAULT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  `usuario_idUsuario` INT NOT NULL,
  foreign key(`usuario_idUsuario`) REFERENCES `TA`.`Usuario` (`id`),
  PRIMARY KEY (`idBiblioteca`)
  )ENGINE = InnoDB;
  
-- -----------------------------------------------------
-- Table `TA`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Administrador` (
  `idAdministrador` INT NOT NULL,
  `rol` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`idAdministrador`),
  FOREIGN KEY (`idAdministrador`) REFERENCES `TA`.`Usuario` (`id`)
    )ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TA`.`Jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Jugador` (
  `idJugador` INT NOT NULL,
  `nickname` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`idJugador`),
  FOREIGN KEY (`idJugador`) REFERENCES `TA`.`Usuario` (`id`)
    )ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TA`.`Desarrollador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Desarrollador` (
  `idDesarrollador` INT NOT NULL,
  `numeroCuenta` VARCHAR(20),
  `ingresoTotal` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idDesarrollador`),
  FOREIGN KEY (`idDesarrollador`) REFERENCES `TA`.`Usuario` (`id`)
    )ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TA`.`Juego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Juego` (
  `idJuego` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NULL DEFAULT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `precio` DOUBLE NULL DEFAULT NULL,
  `version` DOUBLE NULL DEFAULT NULL,
  `imagenJuego` BLOB NULL DEFAULT NULL,
  `fechaLanzamiento` DATE NULL DEFAULT NULL,
  `requisitosMinimos` TEXT NULL DEFAULT NULL,
  `requisitosRecomendados` TEXT NULL DEFAULT NULL,
  `espacioDisco` DOUBLE NULL DEFAULT NULL,
  `fechaUltimaActualizacion` DATE NULL DEFAULT NULL,
  `desarrollador_idDesarrollador` INT NOT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  `nombreGenero` ENUM('Accion', 'Rol', 'Estrategia', 'Shooter (FPS/TPS)', 'Simulación', 'Deportes', 'Carreras') NOT NULL,
  `modelo` ENUM('Free_to_play', 'Paga', 'Suscripcion') NOT NULL,
  PRIMARY KEY (`idJuego`),
	FOREIGN KEY (`desarrollador_idDesarrollador`) REFERENCES `TA`.`Desarrollador` (`idDesarrollador`)
    )ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `TA`.`Calificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Calificacion` (
  `idCalificacion` INT NOT NULL,
  `fidJugador` INT NULL DEFAULT NULL,
  `fidJuego` INT NULL DEFAULT NULL,
  `fechaPuntuacion` DATE NULL DEFAULT NULL,
  `puntaje` INT NULL DEFAULT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idCalificacion`),
  FOREIGN KEY (`fidJugador`) REFERENCES `TA`.`Jugador` (`idJugador`),
  FOREIGN KEY (`fidJuego`) REFERENCES `TA`.`Juego` (`idJuego`)
  )ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TA`.`CarroCompra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`CarroCompra` (
  `totalEstimado` DOUBLE NULL DEFAULT NULL,
  `jugador_idJugador` INT NOT NULL,
  `idCarroCompra` INT NOT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idCarroCompra`),
  FOREIGN KEY (`jugador_idJugador`) REFERENCES `TA`.`Jugador` (`idJugador`)
  )ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `TA`.`Cartera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Cartera` (
  `saldoActual` DOUBLE NULL DEFAULT NULL,
  `jugador_idJugador` INT NOT NULL,
  `idCartera` INT NOT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idCartera`),
  FOREIGN KEY (`jugador_idJugador`) REFERENCES `TA`.`Jugador` (`idJugador`)
  )ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TA`.`JuegoAdquirido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`JuegoAdquirido` (
  `fidBiblioteca` INT NULL DEFAULT NULL,
  `fidJuego` INT NULL DEFAULT NULL,
  `fechaAdquisicion` DATE NULL DEFAULT NULL,
  `ultimaSesion` DATE NULL DEFAULT NULL,
  `tiempoJuego` DOUBLE NULL DEFAULT NULL,
  `actualizado` TINYINT(1) NULL DEFAULT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
    FOREIGN KEY (`fidBiblioteca`) REFERENCES `TA`.`Biblioteca` (`idBiblioteca`),
    FOREIGN KEY (`fidJuego`) REFERENCES `TA`.`Juego` (`idJuego`)
    )ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `TA`.`Recarga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Recarga` (
  `idRecarga` INT NOT NULL AUTO_INCREMENT,
  `fechaRecarga` DATE NULL DEFAULT NULL,
  `monto` DOUBLE NULL DEFAULT NULL,
  `cartera_IdCartera` INT NOT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  `nombreMetodo` ENUM('Visa', 'Mastercard', 'PagoEfectivo', 'PayValido') NOT NULL,
  PRIMARY KEY (`idRecarga`),
  FOREIGN KEY (`cartera_IdCartera`) REFERENCES `TA`.`Cartera` (`idCartera`)
)ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TA`.`Reseña`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`Reseña` (
  idResena INT NOT NULL AUTO_INCREMENT,
  fidJugador INT NOT NULL,
  fidJuego INT NOT NULL,
  comentario TEXT NULL,
  fechaPublicacion DATE NULL,
  calificacion_IdCalificacion INT NOT NULL,
  activo TINYINT(1) DEFAULT 1,
  PRIMARY KEY (idResena),
  FOREIGN KEY (fidJugador) REFERENCES TA.Jugador (idJugador),
  FOREIGN KEY (fidJuego) REFERENCES TA.Juego (idJuego),
  FOREIGN KEY (calificacion_IdCalificacion) REFERENCES TA.Calificacion (idCalificacion)
)ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TA`.`CarroCompraXJuego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TA`.`CarroCompraXJuego` (
  `carroCompra_IdCarroCompra` INT NOT NULL,
  `juego_idJuego` INT NOT NULL,
  PRIMARY KEY (`carroCompra_IdCarroCompra`, `Juego_idJuego`),
  FOREIGN KEY (`carroCompra_IdCarroCompra`) REFERENCES `TA`.`CarroCompra` (`idCarroCompra`),
  FOREIGN KEY (`juego_idJuego`) REFERENCES `TA`.`Juego` (`idJuego`)
)ENGINE = InnoDB;




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
