/*
ALTER TABLE plane_airline
DROP CONSTRAINT IF EXISTS plane_airline_pk
ALTER TABLE plane_airline
DROP CONSTRAINT IF EXISTS FK_plane
ALTER TABLE plane_airline
DROP CONSTRAINT IF EXISTS FK_airline
*/
DROP TABLE IF EXISTS plane_airline
DROP TABLE IF EXISTS flight_Pilot;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS pilot;
DROP TABLE IF EXISTS route;
DROP TABLE IF EXISTS client
DROP TABLE IF EXISTS class
DROP TABLE IF EXISTS plane
DROP TABLE IF EXISTS luggage;
DROP TABLE IF EXISTS admin
DROP TABLE IF EXISTS airport;
DROP TABLE IF EXISTS airline;
DROP TABLE IF EXISTS address


CREATE TABLE admin (
	adminID INT PRIMARY KEY IDENTITY(1,1),
	login varchar(30)  CHECK(LEN(login) > 0) NOT NULL,
	password varchar(30) NOT NULL
);

CREATE TABLE client (
    clientID INT PRIMARY KEY IDENTITY(1, 1),
    first_name VARCHAR(30) CHECK(LEN(first_name) > 0) NOT NULL,
    last_name VARCHAR(30) CHECK(LEN(last_name) > 0) NOT NULL,
    email VARCHAR(45) UNIQUE CHECK(email LIKE '%@%.%') NOT NULL,
    password VARCHAR(45) CHECK(LEN(password) > 0)  NOT NULL,
    birth_date DATE CHECK(birth_date <= GETDATE()) NOT NULL,
);

CREATE TABLE airline (
    airlineID INT PRIMARY KEY IDENTITY(1, 1),
    name VARCHAR(60) UNIQUE CHECK(LEN(name) > 0) NOT NULL,
	code CHAR(2) UNIQUE CHECK(LEN(code) = 2) NOT NULL,
);

CREATE TABLE plane (
	planeID INT PRIMARY KEY IDENTITY(1,1),
	brand VARCHAR(30) CHECK(LEN(brand) > 0),
	model VARCHAR(10) CHECK(LEN(model) > 0),
);

CREATE TABLE pilot (
    pilotID INT PRIMARY KEY IDENTITY(1, 1),
    first_name VARCHAR(30) CHECK(LEN(first_name) > 0) NOT NULL,
    last_name VARCHAR(30) CHECK(LEN(last_name) > 0) NOT NULL,
	employment_date DATE CHECK(employment_date <= GETDATE()) DEFAULT GETDATE(),
	airline_id INT,

	FOREIGN KEY (airline_id) REFERENCES airline(airlineID)
);

CREATE TABLE plane_airline (
	plane_id INT,
	airline_id INT,
	planes_quantity INT

	CONSTRAINT plane_airline_pk PRIMARY KEY (plane_id, airline_id),
	CONSTRAINT FK_plane FOREIGN KEY (plane_id) REFERENCES plane(planeID),
	CONSTRAINT FK_airline FOREIGN KEY (airline_id) REFERENCES airline(airlineID)
);

CREATE TABLE address (
	addressID INT PRIMARY KEY IDENTITY(1,1),
	country VARCHAR(30) CHECK(LEN(country) > 0) NOT NULL,
	city VARCHAR (30) CHECK(LEN(city) > 0) NOT NULL,
	postal_code CHAR(6) CHECK(LEN(postal_code) = 6) NOT NULL,
	street VARCHAR(30) CHECK(LEN(street) > 0),
	number INT CHECK(number > 0) NOT NULL
);

CREATE TABLE airport (
    airportID INT PRIMARY KEY IDENTITY(1, 1),
    name VARCHAR(60) UNIQUE CHECK(LEN(name) > 0)  NOT NULL,
	code CHAR(4) UNIQUE CHECK(LEN(code) = 4) NOT NULL,
    address_id INT,
    
	FOREIGN KEY(address_id) REFERENCES address(addressID) ON DELETE SET NULL
);

CREATE TABLE flight (
    flightID INT PRIMARY KEY IDENTITY(1, 1),
    departureAirport_id INT,
    arrivalAirport_id INT,
	pilot_id INT,
	plane_id INT,
    departure_time TIME NOT NULL,
    departure_date DATE NOT NULL,
    arrival_time TIME NOT NULL,
    arrival_date DATE NOT NULL,
	price DECIMAL(7,2) CHECK(price >= 0) NOT NULL,
    
	FOREIGN KEY (pilot_id) REFERENCES pilot(pilotID),
	FOREIGN KEY (plane_id) REFERENCES plane(planeID),
    FOREIGN KEY(departureAirport_id) REFERENCES airport(airportID),   
    FOREIGN KEY(arrivalAirport_id) REFERENCES airport(airportID), 
);

CREATE TABLE luggage (
	luggageID INT PRIMARY KEY IDENTITY(1,1),
	name VARCHAR(20) CHECK(LEN(name) > 0) UNIQUE,
	price DECIMAL(5,2) CHECK(price >= 0) NOT NULL,
	height INT CHECK(height > 0) NOT NULL,
	weight INT CHECK(weight > 0) NOT NULL,
);

CREATE TABLE class ( 
	classID INT PRIMARY KEY IDENTITY(1,1),
	name VARCHAR(30) UNIQUE CHECK(LEN(name) > 0) NOT NULL,
	price DECIMAL(5,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE booking (
    bookingID INT PRIMARY KEY IDENTITY(1, 1),
    flight_id INT,
	client_id INT,
    luggage_id INT,
	class_id INT,
	seat_number INT NOT NULL,
    approval_date DATE NOT NULL DEFAULT GETDATE(),

    FOREIGN KEY(class_id) REFERENCES class(classID),
    FOREIGN KEY(flight_id) REFERENCES flight(flightID) ON DELETE CASCADE,
    FOREIGN KEY(client_id) REFERENCES client(clientID) ON DELETE CASCADE,
	FOREIGN KEY(luggage_id) REFERENCES luggage(luggageID) ON DELETE SET NULL
);




