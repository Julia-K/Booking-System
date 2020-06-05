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
	login varchar(30) UNIQUE CHECK(LEN(login) > 0) NOT NULL,
	password varchar(100) NOT NULL
);

CREATE TABLE client (
    clientID INT PRIMARY KEY IDENTITY(1, 1),
    first_name VARCHAR(30) CHECK(LEN(first_name) > 0) NOT NULL,
    last_name VARCHAR(30) CHECK(LEN(last_name) > 0) NOT NULL,
    email VARCHAR(45) UNIQUE CHECK(email LIKE '%@%.%') NOT NULL,
    password VARCHAR(100) CHECK(LEN(password) > 0)  NOT NULL,
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

	FOREIGN KEY (airline_id) REFERENCES airline(airlineID) ON DELETE CASCADE
);

CREATE TABLE plane_airline (
	plane_id INT,
	airline_id INT,
	planes_quantity INT
	
	PRIMARY KEY (plane_id, airline_id),
	FOREIGN KEY (plane_id) REFERENCES plane(planeID) ON DELETE CASCADE,
	FOREIGN KEY (airline_id) REFERENCES airline(airlineID) ON DELETE CASCADE,
	UNIQUE (plane_id, airline_id)
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
    
	FOREIGN KEY(address_id) REFERENCES address(addressID) ON DELETE CASCADE
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
    
	FOREIGN KEY (pilot_id) REFERENCES pilot(pilotID) ON DELETE CASCADE,
	FOREIGN KEY (plane_id) REFERENCES plane(planeID) ON DELETE CASCADE,
    FOREIGN KEY(departureAirport_id) REFERENCES airport(airportID) ON DELETE CASCADE,
    FOREIGN KEY(arrivalAirport_id) REFERENCES airport(airportID)
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

    FOREIGN KEY(class_id) REFERENCES class(classID) ON DELETE CASCADE,
    FOREIGN KEY(flight_id) REFERENCES flight(flightID) ON DELETE CASCADE,
    FOREIGN KEY(client_id) REFERENCES client(clientID) ON DELETE CASCADE,
	FOREIGN KEY(luggage_id) REFERENCES luggage(luggageID) ON DELETE CASCADE
);

insert into address (country, city, postal_code, street, number) values ('Indonesia', 'Rungkam', '44-274', 'Arizona', 93);
insert into address (country, city, postal_code, street, number) values ('Pakistan', 'Mehrabpur', '79-051', 'Dakota', 82);
insert into address (country, city, postal_code, street, number) values ('Ukraine', 'Kopychyntsi', '11-234', 'Stone Corner', 13);
insert into address (country, city, postal_code, street, number) values ('Philippines', 'Alicia', '70-940', 'Bobwhite', 42);
insert into address (country, city, postal_code, street, number) values ('Russia', 'Dmitriyevka', '45-242', 'Starling', 42);
insert into address (country, city, postal_code, street, number) values ('Costa Rica', 'Bel
n', '50-504', 'Lakeland', 43);
insert into address (country, city, postal_code, street, number) values ('Sweden', 'Stockholm', '11-995', 'Oakridge', 81);
insert into address (country, city, postal_code, street, number) values ('North Korea', 'Onsng', '23-432', 'Tony', 96);
insert into address (country, city, postal_code, street, number) values ('China', 'Shaxi', '13-253', 'Pennsylvania', 40);
insert into address (country, city, postal_code, street, number) values ('Argentina', 'Campo Viera', '33-862', 'East', 17);
insert into address (country, city, postal_code, street, number) values ('China', 'Junzhuang', '23-244', 'East', 78);
insert into address (country, city, postal_code, street, number) values ('Brazil', 'Foz do Iguau', '85-000', 'Lerdahl', 78);
insert into address (country, city, postal_code, street, number) values ('United States', 'Seattle', '98-185', 'Corscot', 46);
insert into address (country, city, postal_code, street, number) values ('Ecuador', 'Azogues', '32-345', 'Sheridan', 70);
insert into address (country, city, postal_code, street, number) values ('Philippines', 'San Francisco', '85-501', 'Westridge', 66);
insert into address (country, city, postal_code, street, number) values ('China', 'Sikeshu', '14-546', 'Canary', 77);
insert into address (country, city, postal_code, street, number) values ('Peru', 'Vilque', '52-475', 'Ludington', 4);
insert into address (country, city, postal_code, street, number) values ('China', 'Huyang', '94-342', 'Forest Run', 93);
insert into address (country, city, postal_code, street, number) values ('Russia', 'Kostrovo', '14-028', 'Kingsford', 39);
insert into address (country, city, postal_code, street, number) values ('Mexico', 'Las Flores', '87-395', 'Kinsman', 48);

insert into client ( first_name, last_name, email, password, birth_date) values ( 'Lina', 'Ohms', 'lohms0@google.ru', 'Jv4jTUMjVRN', '1958-03-20');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Gaspar', 'Manvelle', 'gmanvelle1@aboutads.info', 'UzK4lYlttHx0', '1945-05-14');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Nero', 'Puzey', 'npuzey2@icio.us', 'N4lXc34YDXk', '1947-01-17');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Christen', 'Shivell', 'cshivell3@technorati.com', 'xpJGDoMqP', '1950-10-14');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Diahann', 'Dufaur', 'ddufaur4@nba.com', '77gKWsNB1msC', '1946-03-12');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Marissa', 'Freshwater', 'mfreshwater5@yolasite.com', 'BJyQAI1', '1973-09-29');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Barbie', 'Jaskiewicz', 'bjaskiewicz6@sitemeter.com', 'bytncWhZacC', '1984-11-26');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Mace', 'Dearlove', 'mdearlove7@quantcast.com', 'vZ0lA03', '1975-03-10');
insert into client ( first_name, last_name, email, password, birth_date) values ( 'Skipper', 'Spratt', 'sspratt8@nifty.com', '15YSRMDY2Ud', '1956-05-13');
insert into client ( first_name, last_name, email, password, birth_date) values ('Sophi', 'Sumption', 'ssumption9@hc360.com', 'LZ0xxKhQ3N3B', '1967-08-08');

insert into airline (name, code) values ('CATHAY PACIFIC AIRWAYS LTD', 'CX');
insert into airline (name, code) values ('Skrjabin State', 'CO');
insert into airline (name, code) values ('Sidi-Bel-Abb', 'ZH');
insert into airline (name, code) values ('EVA AIRWAYS CORP.', 'BR');
insert into airline (name, code) values ('da Paraiba INC.', 'CR');
insert into airline (name, code) values ('da Madeira Airline', 'VN');
insert into airline (name, code) values ('Antioch AG.', 'LZ');
insert into airline (name, code) values ('LUFTHANSA CARGO AG.', 'LH');
insert into airline (name, code) values ('Azad Yazd PS.', 'HT');
insert into airline (name, code) values ('Tver State', 'RI');
insert into airline (name, code) values ('FlyPlw', 'IH');
insert into airline (name, code) values ('CONTINENTAL AIRLINES, INC.', 'CL');

insert into plane (brand, model) values ('Phoenix', 'Express');
insert into plane (brand, model) values ('GeminiJets', 'Finnair');
insert into plane (brand, model) values ('GeminiJets', 'A330-3');
insert into plane (brand, model) values ('Phoenix', 'ANA Boeing');
insert into plane (brand, model) values ('Aeroclassics', 'TAP Air');
insert into plane (brand, model) values ('Herpa', 'Mriya');
insert into plane (brand, model) values ('Phoenix', 'G-TUIJ');
insert into plane (brand, model) values ('Phoenix', 'Disney');
insert into plane (brand, model) values ('NG', 'Transat');
insert into plane (brand, model) values ('GeminiJets', 'Douglas');

insert into plane_airline (plane_id, airline_id, planes_quantity) values (1, 1, 10);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (3, 1, 10);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (2, 2, 8);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (6, 3, 8);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (1, 4, 10);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (9, 4, 6);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (4, 4, 8);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (8, 5, 2);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (3, 6, 5);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (4, 7, 8);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (1, 8, 3);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (9, 9, 6);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (2, 10, 1);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (10, 10, 3);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (5, 10, 2);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (10, 11, 8);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (9, 12, 1);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (10, 1, 3);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (7, 2, 1);
insert into plane_airline (plane_id, airline_id, planes_quantity) values (8, 8, 3);

insert into airport (name, code, address_id) values ('Grozny North', 'URMG', 1);
insert into airport (name, code, address_id) values ('Clark International', 'RPLC', 3);
insert into airport (name, code, address_id) values ('Teconnet', 'XPME', 4);
insert into airport (name, code, address_id) values ('Gdañsk Lech Wa³êsa', 'EPGD', 5);
insert into airport (name, code, address_id) values ('Crooked Lake', 'INPP', 2);
insert into airport (name, code, address_id) values ('Uiju', 'ZKUJ', 6);
insert into airport (name, code, address_id) values ('Aleppo International', 'OSAP', 8);
insert into airport (name, code, address_id) values ('Layang-Layang', 'LACE', 7);
insert into airport (name, code, address_id) values ('Alenquer', 'SDWQ', 9);
insert into airport (name, code, address_id) values ('Xingcheng Air Base', 'ZYXC', 11);
insert into airport (name, code, address_id) values ('GlaxoSmithKline LLC', 'EEJN', 12);
insert into airport (name, code, address_id) values ('Mylan Institutional Inc.', 'IKDQ', 10);
insert into airport (name, code, address_id) values ('Aidarex LLC', 'RFUH', 14);
insert into airport (name, code, address_id) values ('STAT Rx LLC', 'AQTT',16);
insert into airport (name, code, address_id) values ('Sandoz Inc', 'DKOT', 13);
insert into airport (name, code, address_id) values ('Capitol Welders Co.', 'LZBK', 18);
insert into airport (name, code, address_id) values ('LLC', 'HXEG', 15);
insert into airport (name, code, address_id) values ('The Hain Inc.', 'UNGB', 17);
insert into airport (name, code, address_id) values ('GlaxoSmithKline LP', 'ISXJ', 19);
insert into airport (name, code, address_id) values ('Aidarex ', 'SDER', 20);

insert into pilot (first_name, last_name, employment_date, airline_id) values ('Ruben', 'Bartozzi', '2019-11-25', 3);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Morlee', 'Bodiam', '2012-04-24', 3);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Frasco', 'Poston', '2020-03-31', 5);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Yale', 'Crossby', '2011-05-09', 4);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Matilde', 'Huxtable', '2018-04-10', 2);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Niels', 'Lanigan', '2010-12-13', 1);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Zechariah', 'Taysbil', '2019-11-02', 4);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Katalin', 'Krugmann', '2014-02-27', 10);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Gaye', 'Pfeffer', '2013-11-29', 3);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Janaya', 'Bradnum', '2013-11-23', 1);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Hobart', 'Gaitung', '2018-12-31', 12);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Yoshi', 'Hardy', '2011-08-13', 3);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Bruis', 'Shireff', '2015-02-17', 11);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Cara', 'Rymer', '2019-08-16', 4);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Nicolette', 'Lensch', '2006-05-30', 2);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Diann', 'MacDougal', '2011-04-23', 10);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Giff', 'Jansie', '2019-05-30', 9);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Simmonds', 'Tutin', '2003-04-29', 9);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Levon', 'Levane', '2019-01-05', 7);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Frasier', 'Jellard', '2016-05-28', 8);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Pearle', 'Dumberell', '2015-11-13', 6);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Liuka', 'Witul', '2010-05-23', 11);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Griffin', 'Todeo', '2017-06-14', 3);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Daphne', 'Obal', '2019-07-04', 9);
insert into pilot (first_name, last_name, employment_date, airline_id) values ('Quint', 'Meese', '2002-09-29', 8);

insert into class (name, price) values ('First class', 70);
insert into class (name, price) values ('Business class', 50);
insert into class (name, price) values ('Economy class', 20);

insert into luggage (name, price, height, weight) values ('Carry-On', 0, 40, 10);
insert into luggage (name, price, height, weight) values ('Personal Bag', 20, 55, 10);
insert into luggage (name, price, height, weight) values ('Checked Bag', 80, 70, 10);
insert into luggage (name, price, height, weight) values ('Large Duffel', 180, 120, 20);

insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (3, 1, 11, 4, '16:06', '2021-12-12', '01:04', '2021-12-13', 1226);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (8, 10, 3, 1, '22:12:44', '2021-07-31', '23:19:34', '2021-08-01', 153);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (4, 9, 15, 9, '00:14:33', '2022-10-09', '09:56:36', '2022-10-10', 936);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (2, 7, 1, 8, '09:21:00', '2020-08-16', '12:24:10', '2020-08-17', 380);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (3, 10, 5, 2, '20:19:32', '2020-11-27', '04:39:14', '2020-11-28', 1800);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (8, 6, 3, 1, '03:29', '2022-06-14', '07:45', '2020-06-14', 947.74);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (7, 15, 6, 1, '06:39', '2024-11-08', '04:32', '2024-11-09', 983.11);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (11, 15, 11, 1, '04:00', '2020-10-02', '11:28', '2020-10-02', 194.84);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (13, 3, 3, 1, '18:26', '2024-03-11', '01:34', '2024-03-11', 366.34);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (1, 15, 13, 1, '19:17', '2022-10-03', '03:16', '2022-10-04', 1759.08);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (6, 18, 24, 1, '11:55', '2022-02-21', '10:39', '2022-02-22', 921.24);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (13, 10, 1, 1, '05:22', '2022-09-24', '03:12', '2022-09-25', 1916.72);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (5, 11, 16, 1, '16:44', '2022-03-20', '15:44', '2022-03-21', 1006.8);
insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (7, 6, 5, 1, '06:37', '2023-05-09', '12:10', '2023-05-09', 373.45);

insert into booking (flight_id, client_id, luggage_id, class_id, seat_number) values (5, 1, 2, 3, 6);
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (3, 1, 4, 3, 9, '2019-04-12');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number) values (3, 3, 1, 1, 12);
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (1, 2, 3, 2, 23, '2019-04-04');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (12, 9, 1, 3, 27, '2019-07-02');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (11, 4, 1, 1, 45, '2019-03-12');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (4, 8, 2, 3, 1, '2019-05-25');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (8, 5, 1, 1, 17, '2019-11-26');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number) values (13, 7, 2, 3, 15);
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (3, 9, 1, 3, 7, '2020-04-30');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (8, 2, 3, 2, 19, '2019-10-25');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (9, 5, 2, 3, 31, '2019-11-22');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (10, 3, 1, 2, 2, '2019-01-30');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (10, 9, 2, 1, 37, '2019-10-20');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (7, 4, 2, 2, 38, '2019-11-04');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (4, 1, 2, 1, 20, '2020-03-17');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (14, 2, 3, 2, 44, '2019-10-12');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number) values (3, 7, 1, 2, 18);
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (4, 9, 2, 2, 46, '2019-11-24');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (3, 3, 3, 1, 8, '2019-11-01');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (3, 8, 2, 3, 32, '2019-08-28');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (12, 10, 3, 1, 26, '2019-07-30');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (12, 5, 2, 2, 10, '2019-06-05');
insert into booking (flight_id, client_id, luggage_id, class_id, seat_number, approval_date) values (1, 6, 2, 1, 33, '2020-01-03');

select * from flight