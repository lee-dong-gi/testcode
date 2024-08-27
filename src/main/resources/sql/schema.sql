CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO users
VALUES
(1, '이동기', 'oksk4753@gmail.com'),
(2, '김동기', 'oksk327@naver.com');