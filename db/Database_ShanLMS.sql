#--- Create / Restore Database
DROP DATABASE IF EXISTS ShanLMS;
CREATE DATABASE IF NOT EXISTS ShanLMS;

#--- ShanLMS Database
USE ShanLMS;

#--- Create Tables
CREATE TABLE Customer(
CustomerID INT PRIMARY KEY,
FirstName VARCHAR(30),
LastName VARCHAR(30),
NIC VARCHAR(12),
Gender VARCHAR(12),
DOB Date,
Address VARCHAR(100),
MobileNumber VARCHAR(10),
DrivingLicense VARCHAR(10)
);

CREATE TABLE Instructor(
InstructorID INT PRIMARY KEY,
FirstName VARCHAR(30),
LastName VARCHAR(30),
NIC VARCHAR(12),
Gender VARCHAR(12),
DOB Date,
Address VARCHAR(100),
MobileNumber VARCHAR(10),
DrivingLicense VARCHAR(10),
EPFno VARCHAR(5)
);

CREATE TABLE SQ(
SQno INT PRIMARY KEY,
Question VARCHAR(80)
);

CREATE TABLE User(
UserID INT PRIMARY KEY,
FirstName VARCHAR(30),
LastName VARCHAR(30),
NIC VARCHAR(12),
Gender VARCHAR(12),
DOB Date,
Address VARCHAR(100),
MobileNumber VARCHAR(10),
DrivingLicense VARCHAR(10),
Designation VARCHAR(20),
UserName VARCHAR(30),
Password VARCHAR(30),
Q1 INT,
Q2 INT,
Q3 INT,
A1 VARCHAR(50),
A2 VARCHAR(50),
A3 VARCHAR(50),
CONSTRAINT FOREIGN KEY(Q1) REFERENCES SQ(SQno) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(Q2) REFERENCES SQ(SQno) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(Q3) REFERENCES SQ(SQno) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Vehicle(
VehicleID INT PRIMARY KEY,
VehicleNumber CHAR(10) NOT NULL,
Type VARCHAR(20),
Transmission VARCHAR(20),
Model VARCHAR(20),
Year CHAR(5)
);

CREATE TABLE CustomerProgram(
CustomerID INT PRIMARY KEY,
Amount DOUBLE(8,2),
Balance DOUBLE(8,2),
Dates INT,
RemainingDates INT,
CONSTRAINT FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Payment(
PaymentID INT,
CustomerID INT,
Date DATE,
Time VARCHAR(10),
Amount DOUBLE(8,2),
CONSTRAINT PRIMARY KEY (PaymentID,CustomerID),
CONSTRAINT FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Schedule(
ScheduleID INT PRIMARY KEY,
CustomerID INT,
InstructorID INT,
VehicleID INT,
Date DATE,
Time VARCHAR(10),
CONSTRAINT FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(InstructorID) REFERENCES Instructor(InstructorID) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(VehicleID) REFERENCES Vehicle(VehicleID) ON UPDATE CASCADE ON DELETE CASCADE
);

#--- Initial Data
INSERT INTO SQ VALUES
    (0,'Select any question'),
    (1,'What primary school did you attend?'),
    (2,'In what town or city did your parents meet?'),
    (3,'In what city or town was your first job?'),
    (4,'What was your childhood nickname?'),
    (5,'What is the name of your favorite childhood friend?'),
    (6,'Who is your childhood sports hero?'),
    (7,'what was your first pets name?'),
    (8,'What was your first car?'),
    (9,'What is your mother maiden name?'),
    (10,'Where is your favorite place to vacation?');

#--- Demo Data
INSERT INTO Vehicle VALUES
('1', 'PF4047', 'Van', 'Auto', 'Nissan', '2008'),
('2', 'KV4102', 'Car', 'Auto', 'Honda', '2012'),
('3', 'GI8712', 'Car', 'Manual', 'Toyota', '2001'),
('4', 'QZ1815', 'Three-Wheel', 'Manual', 'Bajaj', '2009'),
('5', 'BCU8587', 'Motor-Bicycle', 'Manual', 'Bajaj', '2010'),
('6', 'HL0007', 'Jeep', 'Manual', 'Mitsubishi', '1999'),
('7', '56-7018', 'Van', 'Manual', 'Toyota', '1999');

INSERT INTO User VALUES
('1', 'Pasan', 'Abeysekara', '981280270V', 'Male', '1998-05-07', 'No. 50, Sir Leo Fernando Mawatha, Panadura', '0774866554', 'B2983015', 'Manager', 'pasan', '1234', 1, 2, 3, 'Royal College', 'Panadura', 'Colombo');

INSERT INTO Customer VALUES
('1', 'Lahiru', 'Abeysekara', '981280270V', 'Male', '1998-05-07', 'No. 350, Main Street, Panadura', '0774866554', 'No'),
('2', 'Hashen', 'Abeysekara', '200010201425', 'Male', '2000-10-20', 'No. 350, Athur V. Dias Mawatha, Panadura', '0779090689', 'No');

INSERT INTO CustomerProgram VALUES
('1', 25000.00, 1500.00, 20, 9),
('2', 25000.00, 0.00, 20, 4);

INSERT INTO Instructor VALUES
('1', 'Dilshan', 'Perera', '962758486V', 'Male', '1996-04-17', 'No. 35, Galle Road, Panadura', '0778214593', 'B1458947','001'),
('2', 'Madushan', 'Gunaratne', '954782586V', 'Male', '1995-12-26', 'No. 47, Old Galle Road, Moratuwa', '0710578963', 'B4783574','002');

INSERT INTO Schedule VALUES
('1', '1', '1', '1', '2020-10-03', '10:00'),
('2', '1', '1', '7', '2020-10-08', '14:00'),
('3', '1', '1', '2', '2020-10-09', '11:00'),
('4', '2', '2', '4', '2020-10-10', '09:00'),
('5', '1', '1', '3', '2020-10-10', '12:00'),
('6', '1', '1', '3', '2020-10-13', '13:00'),
('7', '1', '1', '3', '2020-10-14', '11:00'),
('8', '2', '1', '7', '2020-10-16', '15:00'),
('9', '1', '1', '7', '2020-10-17', '10:00'),
('10', '1', '1', '4', '2020-10-17', '16:00'),
('11', '2', '1', '1', '2020-10-17', '09:00'),
('12', '1', '1', '7', '2020-10-19', '15:00'),
('13', '2', '1', '6', '2020-10-20', '13:00');

INSERT INTO Payment VALUES
('1', '1', '2020-10-05', '10:00', 500.00),
('2', '1', '2020-10-06', '14:00', 1000.00);

#---

