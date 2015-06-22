DROP DATABASE `city_transport_db`;

CREATE SCHEMA IF NOT EXISTS `city_transport_db` DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `city_transport_db`.`routes` (
  `ROUTE_ID` INT NOT NULL,
  `ROUTE_NAME_EN` VARCHAR(45) NOT NULL,
  `ROUTE_NAME_RU` VARCHAR(45) NOT NULL,
  `ROUTE_TRANSPORT_TYPE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ROUTE_ID`))
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `city_transport_db`.`transport_units` (
  `UNIT_ID` INT NOT NULL,
  `MODEL_NAME_EN` VARCHAR(45) NOT NULL,
  `MODEL_NAME_RU` VARCHAR(45) NOT NULL,
  `TRANSPORT_TYPE` VARCHAR(45) NOT NULL,
  `ROUTE_ID` INT NULL,
  PRIMARY KEY (`UNIT_ID`),
  INDEX `fk_transport_units_routes_idx` (`ROUTE_ID` ASC),
  CONSTRAINT `fk_transport_units_routes`
  FOREIGN KEY (`ROUTE_ID`)
  REFERENCES `city_transport_db`.`routes` (`ROUTE_ID`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `city_transport_db`.`stops` (
  `STOP_ID` INT NOT NULL,
  `STOP_NAME_EN` VARCHAR(45) NOT NULL,
  `STOP_NAME_RU` VARCHAR(45) NOT NULL,
  `STOP_LAT` DOUBLE NOT NULL,
  `STOP_LONG` DOUBLE NOT NULL,
  PRIMARY KEY (`STOP_ID`))
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `city_transport_db`.`routes_has_stops` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `routes_ROUTE_ID` INT NOT NULL,
  `stops_STOP_ID` INT NOT NULL,
  `STOPS_ORDER_BY_ROUTE` INT NOT NULL,
  PRIMARY KEY (`ID`, `routes_ROUTE_ID`, `stops_STOP_ID`),
  INDEX `fk_routes_has_stops_stops1_idx` (`stops_STOP_ID` ASC),
  INDEX `fk_routes_has_stops_routes1_idx` (`routes_ROUTE_ID` ASC),
  CONSTRAINT `fk_routes_has_stops_routes1`
  FOREIGN KEY (`routes_ROUTE_ID`)
  REFERENCES `city_transport_db`.`routes` (`ROUTE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_routes_has_stops_stops1`
  FOREIGN KEY (`stops_STOP_ID`)
  REFERENCES `city_transport_db`.`stops` (`STOP_ID`)
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

INSERT INTO city_transport_db.routes ( ROUTE_ID, ROUTE_NAME_EN, ROUTE_NAME_RU, ROUTE_TRANSPORT_TYPE) values
	(101, 'STAROVOKZALNA - MIKHAILIVSKA BORSHCHAGOVKA', 'СТАРОВОКЗАЛЬНАЯ - МИХАЙЛОВСКАЯ БОРЩАГОВКА', 'TRAM'),
	(102, 'MIKHAILIVSKA BORSHCHAGOVKA - KILTSEVA ROAD', 'МИХАЙЛОВСКАЯ БОРЩАГОВКА - КОЛЬЦЕВАЯ ДОРОГА', 'TRAM'),
	(103, 'STAROVOKZALNA - KILTSEVA ROAD', 'СТАРОВОКЗАЛЬНАЯ - КОЛЬЦЕВАЯ ДОРОГА', 'TRAM'),
	(111, 'LYBIDSKAYA - NAUKI ave.', 'ЛЫБИДСКАЯ - пр.НАУКИ', 'TROLLEYBUS'),
	(113, 'JEL.MASSIV - DVOREC SPORTA', 'ЖЕЛ.МАССИВ - ДВ.СПОРТА', 'TROLLEYBUS'),
	(115, 'BILECKAYA STR. - LVA TOLSTOGO', 'УЛ.БИЛЕЦКАЯ - ЛЬВА ТОЛСТОГО', 'TROLLEYBUS'),
	(121, 'HOLOSEEVSKAYA - YUJNAYA', 'ГОЛОСЕЕВСКАЯ - ЮЖНАЯ', 'BUS'),
	(122, 'BULGAKOVA - SHULYAVSKAYA', 'БУЛГАКОВА - ШУЛЯВСКАЯ', 'BUS'),
	(123, 'DRUJBI NARODOV - PEVCHESKOE POLE', 'ДРУЖБЫ НАРОДОВ - ПЕВЧЕСКОЕ ПОЛЕ', 'BUS');

INSERT INTO city_transport_db.stops (STOP_ID, STOP_NAME_EN, STOP_NAME_RU, STOP_LAT, STOP_LONG) values
	(229692, 'STAROVOKZALNAYA', 'СТАРОВОКЗАЛЬНАЯ', 50.443677, 30.489220),
	(373281, 'POBEDY SQUARE', 'ПЛОЩАДЬ ПОБЕДЫ', 50.446236, 30.488276),
	(373279, 'POLITEHNICHESKAYA', 'ПОЛИТЕХНИЧЕСКАЯ', 50.446452, 30.467151),
	(373278, 'POLOVAYA', 'ПОЛЕВАЯ', 50.446431, 30.454770),
	(373277, 'INDUSTRIALNAYA', 'ИНДУСТРИАЛЬНАЯ', 50.443939, 30.442206),
	(373276, 'NATIONAL AERO UNIVERSITY', 'НАЦИОНАЛЬНЫЙ АВИАЦИОННЫЙ УНИВЕРСИТЕТ', 50.441195, 30.431256),
	(373275, 'GEROEV SEVASTOPOLA', 'ГЕРОЕВ СЕВАСТОПОЛЯ', 50.438205, 30.419781),
	(373282, 'IVANA LEPSE', 'ИВАНА ЛЕПСЕ', 50.435947, 30.410897),
	(373283, 'SEMII SOSNINYKH', 'СЕМЬИ СОСНИНЫХ', 50.433046, 30.399514),
  (373284, 'GNATA YURI', 'ГНАТА ЮРЫ', 50.429622, 30.386215),
  (135183, 'ROMEN ROLLANA', 'РОМЕН РОЛЛАНА', 50.426591, 30.374472),
	(373273, 'KILTSEVA ROAD', 'КОЛЬЦЕВАЯ ДОРОГА', 50.425234, 30.367354),
	(134990, 'GENERALA POTAPOVA', 'ГЕНЕРАЛА ПОТАПОВА', 50.425991, 30.384668),
	(134988, 'KOLTSOVA BULEVARD', 'БУЛЬВАР КОЛЬЦОВА', 50.420385, 30.381857),
	(134985, 'ZODCHIKH STREET', 'УЛ. ЗОДЧИХ', 50.417343, 30.388119),
	(134983, 'JOLUDEVA STREET', 'УЛ ЖОЛУДЕВА', 50.415080, 30.394246),
	(134981, 'GRIGOROVICHA-BARSKOGO STREET', 'УЛ. ГРИГОРОВИЧА-БАРСКОГО', 50.411737, 30.396788),
	(134979, 'BULGAKOVA STREET', 'УЛ. БУЛГАКОВА', 50.407990, 30.403537),
	(134977, 'MIKHAILIVSKA BORSHCHAGOVKA', 'МИХАЙЛОВСКАЯ БОРЩАГОВКА', 50.407325, 30.407275);

INSERT INTO city_transport_db.transport_units (UNIT_ID, MODEL_NAME_EN, MODEL_NAME_RU, TRANSPORT_TYPE, ROUTE_ID) VALUES
  (501, 'KT3UA', 'KT3UA', 'TRAM', 103),
  (402, 'KT3UA', 'KT3UA', 'TRAM', 103),
  (408, 'KT3UA', 'KT3UA', 'TRAM', 103),
  (413, 'KT3UA', 'KT3UA', 'TRAM', 103),
  (414, 'KT3UA', 'KT3UA', 'TRAM', 103),
  (410, 'KT3UA', 'KT3UA', 'TRAM', 103),
  (341, 'KT3UA', 'KT3UA', 'TRAM', 101),
  (342, 'KT3UA', 'KT3UA', 'TRAM', 101),
  (6003, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (5568, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (5597, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (6030, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (5486, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (5557, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (5453, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (5959, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (6015, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101),
  (5530, 'Tatra-T3', 'Tatra-T3', 'TRAM', 101);

INSERT INTO city_transport_db.routes_has_stops (routes_ROUTE_ID, stops_STOP_ID, STOPS_ORDER_BY_ROUTE) VALUES
  (101, 229692, 1),
  (101, 373281, 2),
  (101, 373279, 3),
  (101, 373278, 4),
  (101, 373277, 5),
  (101, 373276, 6),
  (101, 373275, 7),
  (101, 373282, 8),
  (101, 373283, 9),
  (101, 373284, 10),
  (101, 134990, 11),
  (101, 134988, 12),
  (101, 134985, 13),
  (101, 134983, 14),
  (101, 134981, 15),
  (101, 134979, 16),
  (101, 134977, 17),
  (103, 229692, 1),
  (103, 373281, 2),
  (103, 373279, 3),
  (103, 373278, 4),
  (103, 373277, 5),
  (103, 373276, 6),
  (103, 373275, 7),
  (103, 373282, 8),
  (103, 373283, 9),
  (103, 373284, 10),
  (103, 135183, 11),
  (103, 373273, 12);

INSERT INTO users (username, email, password) VALUES
  ('admin', 'admin@admin.com', 'admin');
