USE spring_security_demo;

DROP TABLE IF EXISTS patient;

SELECT * FROM patient;

INSERT INTO patient(id, patient_username, patient_password, patient_age) VALUES
(1, 'Vanessa1', 'v123', 21),
(2, 'Kate1', 'k123', 21),
(3, 'Benji1', 'b123', 23);