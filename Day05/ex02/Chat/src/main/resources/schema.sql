CREATE TABLE IF NOT EXISTS users (
    id          SERIAL PRIMARY KEY,
    login       TEXT NOT NULL,
    password    TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS rooms (
    id          SERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    admin       INTEGER REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id          SERIAL PRIMARY KEY,
    sender      INTEGER REFERENCES users(id),
    room        INTEGER REFERENCES rooms(id),
    message     TEXT NOT NULL,
    time        TIMESTAMP
);