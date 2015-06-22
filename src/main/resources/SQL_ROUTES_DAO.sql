select * from city_transport_db.routes;

select * from city_transport_db.routes where city_transport_db.routes.ROUTE_ID=101;

DELETE FROM city_transport_db.routes where city_transport_db.routes.ROUTE_ID=234;

select * from city_transport_db.users;

select * from city_transport_db.transport_units;

SELECT * FROM city_transport_db.transport_units WHERE ROUTE_ID=101;

INSERT INTO city_transport_db.transport_units (UNIT_ID, MODEL_NAME, TRANSPORT_TYPE, ROUTE_ID)
values (?, ?, ?, ?);

INSERT INTO `city_transport_db`.`users` (`username`, `email`, `password`, `create_time`) VALUES ('admin', 'admin@admin.com', 'admin', '');



SELECT * FROM city_transport_db.routes_has_stops;
SELECT * FROM city_transport_db.routes_has_stops order by STOPS_ORDER_BY_ROUTE;

SELECT * FROM city_transport_db.stops 
JOIN city_transport_db.routes_has_stops ON 
 city_transport_db.stops.STOP_ID = city_transport_db.routes_has_stops.stops_STOP_ID 
 WHERE routes_ROUTE_ID=101 order by STOPS_ORDER_BY_ROUTE;