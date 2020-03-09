-- Create CarPark table
CREATE TABLE CarPark_Table
(
    CarParkID INT IDENTITY PRIMARY KEY,
    TotalSpaces INT NOT NULL
);

-- Create Blacklist table
CREATE TABLE Blacklist_Table
(
    NumberPlate VARCHAR(7) PRIMARY KEY,
    Description VARCHAR(100) NOT NULL
);

-- Create UserGroup_Table
CREATE TABLE UserGroup_Table
(
    UserGroupID SMALLINT PRIMARY KEY,
    Name VARCHAR(6) NOT NULL
);

-- Create User Table
CREATE TABLE User_Table
(
    UserID SMALLINT IDENTITY PRIMARY KEY,
    Username VARCHAR(128) NOT NULL UNIQUE,
    Password VARCHAR(MAX) NOT NULL,
    NumberPlate VARCHAR(7) NOT NULL,
    UserGroup SMALLINT NOT NULL REFERENCES UserGroup_Table (UserGroupID),
    Email VARCHAR(100) NOT NULL
);

-- Create Activity table
CREATE TABLE Activity_Table
(
    ActivityID INT IDENTITY PRIMARY KEY,
    UserID SMALLINT NOT NULL REFERENCES User_Table (UserID),
    DateTimeEntered DATETIME NOT NULL,
    DateTimeExited DATETIME
);