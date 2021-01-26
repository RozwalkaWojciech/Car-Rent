--liquibase formatted sql
--changeset Wojtek:4

INSERT INTO users
VALUES (1, 'Jan', 'Kowalski');
INSERT INTO users
VALUES (2, 'Stefan', 'Siara');
INSERT INTO users
VALUES (3, 'Jacek', 'Placek');
INSERT INTO users
VALUES (4, 'Ola', 'Sztos');
INSERT INTO users
VALUES (5, 'Iwona', 'Jackowska');

INSERT INTO cars
VALUES (1, 'Mercedes', 'A class', false);
INSERT INTO cars
VALUES (2, 'BMW', 'M3', true);
INSERT INTO cars
VALUES (3, 'Audi', 'A8', true);
INSERT INTO cars
VALUES (4, 'Toyota', 'Prius', true);
INSERT INTO cars
VALUES (5, 'Ford', 'Mustang', false);
INSERT INTO cars
VALUES (6, 'Ferrari', 'F12', false);
INSERT INTO cars
VALUES (7, 'Fiat', 'Panda', true);
INSERT INTO cars
VALUES (8, 'Porsche', '911', false);
INSERT INTO cars
VALUES (9, 'Kia', 'Stinger', true);
INSERT INTO cars
VALUES (10, 'Lamborghini', 'Aventador', false);

INSERT INTO users_cars
VALUES (1, 1);
INSERT INTO users_cars
VALUES (1, 6);
INSERT INTO users_cars
VALUES (2, 5);
INSERT INTO users_cars
VALUES (2, 8);
INSERT INTO users_cars
VALUES (4, 10);
