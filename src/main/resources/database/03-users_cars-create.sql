--liquibase formatted sql
--changeset Wojtek:3

CREATE TABLE users_cars
(
    user_id BIGINT,
    cars_id BIGINT,
    PRIMARY KEY (cars_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (cars_id) REFERENCES cars (id)
);