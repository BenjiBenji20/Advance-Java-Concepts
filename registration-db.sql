USE servlet_registration_web_app;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id INT AUTO_INCREMENT PRIMARY KEY,
    complete_name NVARCHAR(255) NOT NULL,
    username NVARCHAR(255) NOT NULL,
    password NVARCHAR(50) NOT NULL
);

INSERT INTO users (complete_name, username, password)
VALUES
('Jomari Delector', 'Jomari', '123');

SELECT * FROM users;
