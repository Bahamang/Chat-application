INSERT INTO users (username, email, password, created, updated, disabled, deleted)
VALUES
    ('john_doe', 'john@example.com', '{noop}password123', CURRENT_TIMESTAMP, NULL, false, false),
    ('jane_smith', 'jane@example.com', '{noop}securepass', CURRENT_TIMESTAMP, NULL, false, false),
    ('alice_green', 'alice@example.com', '{noop}strongpassword', CURRENT_TIMESTAMP, NULL, false, false);

INSERT INTO users (username, email, password, created, updated, disabled, deleted)
VALUES
    ('emma_watson', 'emma_watson@example.com', '{noop}password123', CURRENT_TIMESTAMP, NULL, false, false),
    ('tom_hanks', 'tom_hanks@example.com', '{noop}securepass', CURRENT_TIMESTAMP, NULL, false, false),
    ('jennifer_lawrence', 'jennifer_lawrence@example.com', '{noop}strongpassword', CURRENT_TIMESTAMP, NULL, false, false);


INSERT INTO profile (user_id, username, photo_id, description, language, occupation, first_name, last_name, birth_date, registration_date, last_login, is_active, created, updated, disabled, deleted)
VALUES
    (1, 'john_doe', 1, 'Software Engineer', 'English', 'Software Developer', 'John', 'Doe', '1990-05-15', CURRENT_TIMESTAMP, NULL, true, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 'jane_smith', 2, 'Graphic Designer', 'English', 'Graphic Designer', 'Jane', 'Smith', '1988-09-20', CURRENT_TIMESTAMP, NULL, true, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 'alice_green', 3, 'Marketing Manager', 'English', 'Marketing Manager', 'Alice', 'Green', '1992-03-10', CURRENT_TIMESTAMP, NULL, true, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 'emma_watson', 4, 'Actress', 'English', 'Actress', 'Emma', 'Watson', '1990-04-15', CURRENT_TIMESTAMP, NULL, true, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 'tom_hanks', 5, 'Actor', 'English', 'Actor', 'Tom', 'Hanks', '1956-07-09', CURRENT_TIMESTAMP, NULL, true, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 'jennifer_lawrence', 6, 'Actress', 'English', 'Actress', 'Jennifer', 'Lawrence', '1990-08-15', CURRENT_TIMESTAMP, NULL, true, CURRENT_TIMESTAMP, NULL, false, false);

INSERT INTO post (profile_id, text, media_id, created, updated, disabled, deleted)
VALUES
    (1, 'First post by John Doe', 1, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 'Hello from Jane Smith!', 2, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 'Just posted my first update!', 3, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 'Excited to share my new project!', 4, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 'Hello everyone, it''s Tom Hanks!', 5, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 'Jennifer Lawrence here!', 6, CURRENT_TIMESTAMP, NULL, false, false);

INSERT INTO chat_room (first_users_id, second_users_id, created, updated, disabled, deleted)
VALUES
    (1, 2, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 3, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 4, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 3, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 4, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 4, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 5, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 6, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 6, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 5, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 6, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 5, CURRENT_TIMESTAMP, NULL, false, false);





--------- chats

INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (1, 2, 1, CURRENT_TIMESTAMP, 'Hi there!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 1, 1, CURRENT_TIMESTAMP, 'Hello John!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 2, 1, CURRENT_TIMESTAMP, 'How are you?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 1, 1, CURRENT_TIMESTAMP, 'I''m good, thanks!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 2, 1, CURRENT_TIMESTAMP, 'Do you want to grab lunch tomorrow?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 1, 1, CURRENT_TIMESTAMP, 'Sure, sounds great!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (1, 3)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (1, 3, 2, CURRENT_TIMESTAMP, 'Hey Jane!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 1, 2, CURRENT_TIMESTAMP, 'Hi John!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 3, 2, CURRENT_TIMESTAMP, 'How have you been?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 1, 2, CURRENT_TIMESTAMP, 'I''ve been good, thanks!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 3, 2, CURRENT_TIMESTAMP, 'Would you like to join us for a movie tonight?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 1, 2, CURRENT_TIMESTAMP, 'That sounds fun, count me in!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (1, 4)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (1, 4, 3, CURRENT_TIMESTAMP, 'Hello Alice!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 1, 3, CURRENT_TIMESTAMP, 'Hi John!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 4, 3, CURRENT_TIMESTAMP, 'How''s your day going?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 1, 3, CURRENT_TIMESTAMP, 'It''s been busy but good!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 4, 3, CURRENT_TIMESTAMP, 'Would you like to meet up for coffee later?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 1, 3, CURRENT_TIMESTAMP, 'Sure, I''d love to!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (2, 3)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (2, 3, 4, CURRENT_TIMESTAMP, 'Hi Jane!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 2, 4, CURRENT_TIMESTAMP, 'Hey Tom!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 3, 4, CURRENT_TIMESTAMP, 'What are you up to?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 2, 4, CURRENT_TIMESTAMP, 'Just working on a project.', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 3, 4, CURRENT_TIMESTAMP, 'Do you need any help?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 2, 4, CURRENT_TIMESTAMP, 'Thanks, but I''m good!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (2, 4)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (2, 4, 5, CURRENT_TIMESTAMP, 'Hello Alice!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 2, 5, CURRENT_TIMESTAMP, 'Hi Tom!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 4, 5, CURRENT_TIMESTAMP, 'How''s your day going?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 2, 5, CURRENT_TIMESTAMP, 'Pretty busy, but good!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 4, 5, CURRENT_TIMESTAMP, 'Would you like to grab dinner tonight?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 2, 5, CURRENT_TIMESTAMP, 'Sure, that sounds great!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (3, 4)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (3, 4, 6, CURRENT_TIMESTAMP, 'Hey Alice!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 3, 6, CURRENT_TIMESTAMP, 'Hi Jane!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 4, 6, CURRENT_TIMESTAMP, 'How have you been?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 3, 6, CURRENT_TIMESTAMP, 'I''ve been good, thanks!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 4, 6, CURRENT_TIMESTAMP, 'Would you like to join us for a movie tonight?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 3, 6, CURRENT_TIMESTAMP, 'Sounds fun, count me in!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (4, 5)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (4, 5, 7, CURRENT_TIMESTAMP, 'Hello Tom!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 4, 7, CURRENT_TIMESTAMP, 'Hi Alice!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 5, 7, CURRENT_TIMESTAMP, 'How''s your day going?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 4, 7, CURRENT_TIMESTAMP, 'Busy as usual!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 5, 7, CURRENT_TIMESTAMP, 'Would you like to go for a run this evening?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 4, 7, CURRENT_TIMESTAMP, 'I''d love to!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (4, 6)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (4, 6, 8, CURRENT_TIMESTAMP, 'Hey Jennifer!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 4, 8, CURRENT_TIMESTAMP, 'Hi Tom!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 6, 8, CURRENT_TIMESTAMP, 'How are you?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 4, 8, CURRENT_TIMESTAMP, 'I''m doing well, thank you!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (4, 6, 8, CURRENT_TIMESTAMP, 'Would you like to catch up over coffee?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 4, 8, CURRENT_TIMESTAMP, 'Sure, that sounds great!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (5, 6)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (5, 6, 9, CURRENT_TIMESTAMP, 'Hey Jennifer!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 5, 9, CURRENT_TIMESTAMP, 'Hi Alice!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 6, 9, CURRENT_TIMESTAMP, 'How have you been?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 5, 9, CURRENT_TIMESTAMP, 'I''ve been good, thanks!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 6, 9, CURRENT_TIMESTAMP, 'Would you like to join us for a movie tonight?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 5, 9, CURRENT_TIMESTAMP, 'Sounds fun, count me in!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (1, 5)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (1, 5, 10, CURRENT_TIMESTAMP, 'Hello Tom!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 1, 10, CURRENT_TIMESTAMP, 'Hi John!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 5, 10, CURRENT_TIMESTAMP, 'How are you?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 1, 10, CURRENT_TIMESTAMP, 'I''m good, thank you!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (1, 5, 10, CURRENT_TIMESTAMP, 'Would you like to grab lunch tomorrow?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 1, 10, CURRENT_TIMESTAMP, 'Sure, sounds great!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (2, 6)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (2, 6, 11, CURRENT_TIMESTAMP, 'Hey Jennifer!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 2, 11, CURRENT_TIMESTAMP, 'Hi Jane!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 6, 11, CURRENT_TIMESTAMP, 'How have you been?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 2, 11, CURRENT_TIMESTAMP, 'I''ve been good, thanks!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (2, 6, 11, CURRENT_TIMESTAMP, 'Would you like to join us for a movie tonight?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (6, 2, 11, CURRENT_TIMESTAMP, 'Sounds fun, count me in!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

-- Generating messages for chat room (3, 5)
INSERT INTO message (sender_id, sent_to_id, chat_id, message_date, text, media_id, created, updated, disabled, deleted)
VALUES
    (3, 5, 12, CURRENT_TIMESTAMP, 'Hello Tom!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 3, 12, CURRENT_TIMESTAMP, 'Hi John!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 5, 12, CURRENT_TIMESTAMP, 'How are you?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 3, 12, CURRENT_TIMESTAMP, 'I''m good, thank you!', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (3, 5, 12, CURRENT_TIMESTAMP, 'Would you like to grab lunch tomorrow?', NULL, CURRENT_TIMESTAMP, NULL, false, false),
    (5, 3, 12, CURRENT_TIMESTAMP, 'Sure, sounds great!', NULL, CURRENT_TIMESTAMP, NULL, false, false);

