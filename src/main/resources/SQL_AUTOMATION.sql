CREATE SCHEMA IF NOT EXISTS `city_transport_db` DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `city_transport_db`.`routes` (
  `ROUTE_ID` INT NOT NULL,
  `ROUTE_NAME` VARCHAR(45) NOT NULL,
  `ROUTE_TRANSPORT_TYPE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ROUTE_ID`)) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `city_transport_db`.`transport_units` (
  `UNIT_ID` INT NOT NULL,
  `MODEL_NAME` VARCHAR(45) NOT NULL,
  `TRANSPORT_TYPE` VARCHAR(45) NOT NULL,
  `ROUTE_ID` INT NULL,
  PRIMARY KEY (`UNIT_ID`),
  INDEX `fk_transport_units_routes_idx` (`ROUTE_ID` ASC),
  CONSTRAINT `fk_transport_units_routes`
    FOREIGN KEY (`ROUTE_ID`)
    REFERENCES `city_transport_db`.`routes` (`ROUTE_ID`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `city_transport_db`.`stops` (
  `STOP_ID` INT NOT NULL,
  `STOP_NAME` VARCHAR(45) NOT NULL,
  `ROUTE_ID` INT NULL,
  `STOP_LAT` DOUBLE NOT NULL,
  `STOP_LONG` DOUBLE NOT NULL,
  `ROUTE_PREVIOUS_STOP` INT NULL,
  `ROUTE_NEXT_STOP` INT NULL,
  PRIMARY KEY (`STOP_ID`),
  INDEX `fk_stops_routes1_idx` (`ROUTE_ID` ASC),
  CONSTRAINT `fk_stops_routes1`
    FOREIGN KEY (`ROUTE_ID`)
    REFERENCES `city_transport_db`.`routes` (`ROUTE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `city_transport_db`.`users` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


INSERT INTO routes ( ROUTE_ID, ROUTE_NAME, ROUTE_TRANSPORT_TYPE) values
	(101, 'СТАРОВОКЗАЛЬНАЯ - МИХАЙЛОВСКАЯ БОРЩАГОВКА', 'TRAM'),
	(102, 'МИХАЙЛОВСКАЯ БОРЩАГОВКА - КОЛЬЦЕВАЯ ДОРОГА', 'TRAM'),
	(103, 'СТАРОВОКЗАЛЬНАЯ - КОЛЬЦЕВАЯ ДОРОГА', 'TRAM'),
	(111, 'ЛЫБИДСКАЯ - пр.НАУКИ', 'TROLLEYBUS'),
	(113, 'ЖЕЛ.МАССИВ - ДВ.СПОРТА', 'TROLLEYBUS'),
	(115, 'УЛ.БИЛЕЦКАЯ - ЛЬВА ТОЛСТОГО', 'TROLLEYBUS'),
	(121, 'ГОЛОСЕЕВСКАЯ - ЮЖНАЯ', 'BUS'),
	(122, 'БУЛГАКОВА - ШУЛЯВСКАЯ', 'BUS'),
	(123, 'ДРУЖБЫ НАРОДОВ - ПЕВЧЕСКОЕ ПОЛЕ', 'BUS');

INSERT INTO stops (STOP_ID, STOP_NAME, ROUTE_ID, STOP_LAT, STOP_LONG, ROUTE_PREVIOUS_STOP, ROUTE_NEXT_STOP) values
	(1, 'СТАРОВОКЗАЛЬНАЯ',''