--liquibase formatted sql
--changeset Wojtek:2

CREATE TABLE cars
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand     VARCHAR(50) NOT NULL,
    model     VARCHAR(50) NOT NULL,
    available BIT(1)
);