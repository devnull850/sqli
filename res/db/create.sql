CREATE TABLE users(
	id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	fname VARCHAR(20) NOT NULL,
	lname VARCHAR(20) NOT NULL,
	password VARCHAR(70) NOT NULL,
	age INT
);

INSERT INTO users (fname, lname, password, age)
VALUES ('Kara', 'Zor-El', '11d2909f978d54f60625c91a5beb0c9829142d5e3633318fd40668781bb78897', 33);

INSERT INTO users (fname, lname, password, age)
VALUES ('Tony', 'Stark', '3c61fde1d3c4782b8e2fdb4dfc59e2ca9187e5fdeb75c43f5949626c0b44ac5f', 52);

INSERT INTO users (fname, lname, password)
VALUES ('Clark', 'Kent', '27d5233ddd8968526735078ca9efa95ec03a22ea942420df0841f78ca9d9c272');

INSERT INTO users (fname, lname, password, age)
VALUES ('Bruce', 'Wayne', 'bd9b74a682cd757611805f86371a5a277b2941fa42345ce4c87a7c9e28244c2c', 42);

INSERT INTO users (fname, lname, password)
VALUES ('Diana', 'Themyscira', '3c5b5f037b8808a5763fb721dd56d3dae85055545e7694ede8046c80e062c46c');

