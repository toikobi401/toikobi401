-- Create the database
CREATE DATABASE NewYearWishes;

-- Use the database
USE NewYearWishes;

-- Create userName table
CREATE TABLE userName (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100) NOT NULL
);

-- Create wishes table
CREATE TABLE wishes (
    id INT PRIMARY KEY IDENTITY(1,1),
    userId INT FOREIGN KEY REFERENCES userName(id),
    wish TEXT NOT NULL
);
CREATE TABLE UserInput (
    id INT PRIMARY KEY IDENTITY(1,1),
    text NVARCHAR(MAX)
);
