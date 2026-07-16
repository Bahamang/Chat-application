CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP,
    disabled BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL
);

CREATE TABLE media (
    id BIGSERIAL PRIMARY KEY,
    content BYTEA,
    type VARCHAR(50),
    created TIMESTAMP,
    updated TIMESTAMP,
    disabled BOOLEAN,
    deleted BOOLEAN
);

CREATE TABLE profile (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    username varchar,
    photo_id BIGINT,
    description TEXT,
    language VARCHAR(50),
    friends_id varchar,
    occupation VARCHAR(100),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    birth_date TIMESTAMP,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP,
    is_active BOOLEAN DEFAULT true,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP,
    disabled BOOLEAN,
    deleted BOOLEAN,
    FOREIGN KEY (photo_id) REFERENCES media(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE post (
    id BIGSERIAL PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    text VARCHAR(255),
    media_id BIGINT,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP,
    disabled BOOLEAN,
    deleted BOOLEAN,
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    FOREIGN KEY (media_id) REFERENCES media(id)
);

CREATE TABLE chat_room (
    id BIGSERIAL PRIMARY KEY,
    first_users_id BIGINT,
    second_users_id BIGINT,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP,
    disabled BOOLEAN,
    deleted BOOLEAN,
    FOREIGN KEY (first_users_id) REFERENCES users(id),
    FOREIGN KEY (second_users_id) REFERENCES users(id)
);

CREATE TABLE message (
    id BIGSERIAL PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    sent_to_id BIGINT NOT NULL,
    chat_id BIGINT NOT NULL,
    message_date TIMESTAMP,
    text varchar,
    media_id BIGINT,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP,
    disabled BOOLEAN,
    deleted BOOLEAN,
    FOREIGN KEY (sent_to_id) REFERENCES users(id),
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (chat_id) REFERENCES chat_room(id),
    FOREIGN KEY (media_id) REFERENCES media(id)
);
