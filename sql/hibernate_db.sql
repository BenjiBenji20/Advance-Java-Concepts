CREATE SCHEMA hibernate_db;

USE hibernate_db;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id INT AUTO_INCREMENT PRIMARY KEY,
    username NVARCHAR(255) NOT NULL,
    complete_name NVARCHAR(255) NOT NULL,
    password NVARCHAR(255) NOT NULL
);

INSERT INTO 
	users (username, complete_name, password)
	VALUES ('qwerty', 'Benji C', 'qwerty123');
    
SELECT * FROM users;

-- DROP TABLE myfriends;

SELECT * FROM myfriends;

SELECT * FROM mgakaibigan;

SHOW TABLES;

DROP TABLE IF EXISTS student, laptop, student_laptop;

SELECT * FROM student;
SELECT * FROM laptop;
-- SELECT * FROM student_laptop;


