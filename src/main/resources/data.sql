DROP TABLE IF EXISTS users;

CREATE TABLE users (
	login VARCHAR(100) PRIMARY KEY,
	password VARCHAR(100),
	token VARCHAR(100)
);

INSERT INTO users VALUES ('admin', 'C380F833034D60BF035A134094EB538D600DC6F9', '2ZTWcKUyha87-Fht3WqLSvHb3A_17CBW');