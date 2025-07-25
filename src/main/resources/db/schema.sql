-- schema.sql
CREATE TABLE habits (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_time INT NOT NULL,
    duration INT NOT NULL,
    created_at VARCHAR(100) NOT NULL,
    modified_at VARCHAR(100) NOT NULL,
    user_id INT
);

CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    created_at VARCHAR(100) NOT NULL,
);

CREATE TABLE accounts (
    id INT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    last_login VARCHAR(100) NOT NULL,
    created_at VARCHAR(100) NOT NULL,
    user_id INT NOT NULL
);