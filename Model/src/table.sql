DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Reactor;
DROP TABLE IF EXISTS Battery;
DROP TABLE IF EXISTS Fusible_insert;
DROP TABLE IF EXISTS Fuse;
DROP TABLE IF EXISTS Equipment;

CREATE TABLE Person
(
    PersonID SERIAL PRIMARY KEY,
    Name     VARCHAR(255)
);
CREATE TABLE Equipment
(
    EquipmentID SERIAL PRIMARY KEY,
    Performance BOOLEAN,
    Discription VARCHAR(255)
);

CREATE TABLE Reactor
(
    ReactorID       SERIAL PRIMARY KEY,
    Performance     BOOLEAN,
    Type            VARCHAR(20),
    Name            VARCHAR(20),
    EquipmentPathID INT,
    FOREIGN KEY (EquipmentPathID) REFERENCES Equipment (EquipmentID)
);

CREATE TABLE Battery
(
    BatteryID       SERIAL PRIMARY KEY,
    Performance     BOOLEAN,
    Voltage         FLOAT,
    Name            VARCHAR(20),
    EquipmentPathID INT,
    FOREIGN KEY (EquipmentPathID) REFERENCES Equipment (EquipmentID)
);

CREATE TABLE Fuse
(
    FuseID          SERIAL PRIMARY KEY,
    Performance     BOOLEAN,
    Firm            VARCHAR(20),
    Name            VARCHAR(20),
    EquipmentPathID INT,
    FOREIGN KEY (EquipmentPathID) REFERENCES Equipment (EquipmentID)
);
CREATE TABLE Fusible_insert
(
    Fusible_insertID SERIAL PRIMARY KEY,
    Quantity         INT,
    FuseBelongingID  INT,
    FOREIGN KEY (FuseBelongingID) REFERENCES Fuse (FuseId)
);

INSERT INTO Person (PersonID, Name, Actions)
VALUES (1, 'Боумен', 'Боумен догадался');

INSERT INTO Equipment (EquipmentID, Performance, Discription)
VALUES (1, TRUE, 'не пострадало');

INSERT INTO Reactor (ReactorID, Performance, Type, Name, EquipmentPathID)
VALUES (1, FALSE, 'ядерный', 'АЭС', 1);

INSERT INTO Battery (BatteryID, Performance, Voltage, Name, EquipmentPathID)
VALUES (1, TRUE, 10, 'крона', 1);

INSERT INTO Fuse (FuseID, Performance, Firm, Name, EquipmentPathID)
VALUES (1, FALSE, 'КЭАЗ', 'Maxi FX', 1),
       (2, FALSE, 'КАЭЗ', 'FTL Norm', 1);

INSERT INTO Fusible_insert (Fusible_insertID, Quantity, FuseBelongingID)
VALUES (1, 3, 1);



SELECT *
FROM Person;

SELECT *
FROM Equipment;

SELECT *
FROM Reactor;

SELECT *
FROM Battery;

SELECT *
FROM Fuse;

SELECT *
FROM Fusible_insert;