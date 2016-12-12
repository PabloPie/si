SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jaus
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jaus` DEFAULT CHARACTER SET utf8 ;
USE `jaus` ;

-- -----------------------------------------------------
-- Table `jaus`.`TipoInmueble`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`TipoInmueble` (
  `idTipo` INT NOT NULL AUTO_INCREMENT,
  `nombreTipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idTipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`pais` (
  `idpais` INT NOT NULL AUTO_INCREMENT, 
  `nombrepais` VARCHAR(45) NULL,
  PRIMARY KEY (`idpais`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`provincia` (
  `idpais` INT NOT NULL,
  `idprovincia` INT NOT NULL,
  `nombreprov` VARCHAR(45) NULL,
  PRIMARY KEY (`idpais`, `idprovincia`),
  CONSTRAINT `fk_provincia_pais1`
    FOREIGN KEY (`idpais`)
    REFERENCES `jaus`.`pais` (`idpais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`tipos_de_via`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`tipos_de_via` (
  `idvia` INT NOT NULL AUTO_INCREMENT,
  `nombretipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idvia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`localizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`localizacion` (
  `idpais` INT NOT NULL,
  `idprovincia` INT NOT NULL,
  `poblacion` VARCHAR(45) NOT NULL,
  `nombredir` VARCHAR(45) NOT NULL,
  `numerodir` INT NOT NULL,
  `codigopostal` INT NULL,
  `idvia` INT NOT NULL,
  PRIMARY KEY (`idpais`, `idprovincia`, `poblacion`, `nombredir`, `numerodir`, `idvia`),
  CONSTRAINT `idpais_idprovincia`
    FOREIGN KEY (`idpais`,`idprovincia`)
    REFERENCES `jaus`.`provincia` (`idpais`,`idprovincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_localizacion_tipos_de_via1`
    FOREIGN KEY (`idvia`)
    REFERENCES `jaus`.`tipos_de_via` (`idvia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`usuario_registrado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`usuario_registrado` (
  `idusuario` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telefono` INT(9) NULL,
  `contrasena` VARCHAR(45) NULL,
  `fecha` DATE NULL,
  `idpais` INT NULL,
  `idprovincia` INT NULL,
  `poblacion` VARCHAR(45) NULL,
  `nombredir` VARCHAR(45) NULL,
  `numerodir` INT NULL,
  `idvia` INT NULL,
  PRIMARY KEY (`idusuario`),
  CONSTRAINT `fk_usuario_registrado_localizacion1`
    FOREIGN KEY (`idpais` , `idprovincia` , `poblacion` , `nombredir` , `numerodir` , `idvia`)
    REFERENCES `jaus`.`localizacion` (`idpais` , `idprovincia` , `poblacion` , `nombredir` , `numerodir` , `idvia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`inmueble`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`inmueble` (
  `idInmueble` INT NOT NULL AUTO_INCREMENT,
  `precio` DOUBLE NULL,
  `superficie` INT NULL,
  `planta` INT NULL,
  `num_habitaciones` INT NULL,
  `num_bagnos` INT NULL,
  `descripcion` VARCHAR(200) NULL,
  `sevende` TINYINT(1) NULL,
  `sealquila` TINYINT(1) NULL,
  `idTipo` INT NOT NULL,
  `idusuario` VARCHAR(45) NOT NULL,
  `idpais` INT NOT NULL,
  `idprovincia` INT NOT NULL,
  `poblacion` VARCHAR(45) NOT NULL,
  `nombredir` VARCHAR(45) NOT NULL,
  `numerodir` INT NOT NULL,
  `idvia` INT NOT NULL,
  PRIMARY KEY (`idInmueble`),
  CONSTRAINT `fk_inmueble_TipoInmueble1`
    FOREIGN KEY (`idTipo`)
    REFERENCES `jaus`.`TipoInmueble` (`idTipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inmueble_usuario_registrado1`
    FOREIGN KEY (`idusuario`)
    REFERENCES `jaus`.`usuario_registrado` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inmueble_localizacion1`
    FOREIGN KEY (`idpais` , `idprovincia` , `poblacion` , `nombredir` , `numerodir` , `idvia`)
    REFERENCES `jaus`.`localizacion` (`idpais` , `idprovincia` , `poblacion` , `nombredir` , `numerodir` , `idvia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`extras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`extras` (
  `idExtras` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idExtras`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`inmueble_has_extras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`inmueble_has_extras` (
  `idInmueble` INT NOT NULL,
  `idExtras` INT NOT NULL,
  PRIMARY KEY (`idInmueble`, `idExtras`),
  INDEX `fk_Inmueble_has_Extras_Extras1_idx` (`idExtras` ASC),
  INDEX `fk_Inmueble_has_Extras_Inmueble1_idx` (`idInmueble` ASC),
  CONSTRAINT `fk_Inmueble_has_Extras_Inmueble1`
    FOREIGN KEY (`idInmueble`)
    REFERENCES `jaus`.`inmueble` (`idInmueble`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inmueble_has_Extras_Extras1`
    FOREIGN KEY (`idExtras`)
    REFERENCES `jaus`.`extras` (`idExtras`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jaus`.`imagen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jaus`.`imagen` (
  `idInmueble` INT NOT NULL,
  `idImagen` INT NOT NULL AUTO_INCREMENT,
  `ruta` VARCHAR(45) NULL,
  PRIMARY KEY (`idImagen`),
  INDEX `fk_Imagen_Inmueble1_idx` (`idInmueble` ASC),
  CONSTRAINT `fk_Imagen_Inmueble1`
    FOREIGN KEY (`idInmueble`)
    REFERENCES `jaus`.`inmueble` (`idInmueble`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
