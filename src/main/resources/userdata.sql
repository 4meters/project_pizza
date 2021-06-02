DROP TABLE IF EXISTS users;

CREATE TABLE users (
	login VARCHAR(100) PRIMARY KEY,
	password VARCHAR(100),
	token VARCHAR(300)
);

INSERT INTO users VALUES ('admin', 'ad234mintest', 'ddsaweqe213145');
INSERT INTO users VALUES ('admin2', 'ad234mintest', 'ddsaweqe213145');