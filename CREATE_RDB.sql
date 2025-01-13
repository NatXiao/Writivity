

-- CREATE RDB

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(20) NOT NULL,
    mail VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL, -- bcrypt POUR HASH LE MOTS DE PASSE
    admin BOOL DEFAULT FALSE
);

CREATE TABLE vote (
    vote_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    text_id INT,
    rating INT NOT NULL
);

CREATE TABLE comment (
    comment_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    text_id INT NOT NULL,
    body VARCHAR(255) NOT NULL,
    reported BOOLEAN DEFAULT FALSE
);

CREATE TABLE text_p (
    text_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    theme_id INT NOT NULL,
    body TEXT NOT NULL,
    status VARCHAR(30),
    text_submit BOOLEAN,
    submited_at TIMESTAMP,
    reported BOOLEAN DEFAULT FALSE,
    disqualified BOOLEAN DEFAULT FALSE
);

CREATE TABLE theme (
    theme_id SERIAL PRIMARY KEY,
    theme_name VARCHAR(30),
    word_limit INT NOT NULL,
    open_at  DATE DEFAULT CURRENT_TIMESTAMP,
    close_at  DATE DEFAULT CURRENT_TIMESTAMP,
  	conditions VARCHAR(255),
    CONSTRAINT check_open_before_close CHECK (open_at <= close_at)
);

-- Création des clés étrangères après création des tables

ALTER TABLE vote
    ADD CONSTRAINT vote_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE vote
    ADD CONSTRAINT vote_text_id_fkey FOREIGN KEY (text_id) REFERENCES text_p(text_id);

ALTER TABLE comment
    ADD CONSTRAINT comment_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE comment
    ADD CONSTRAINT comment_text_id_fkey FOREIGN KEY (text_id) REFERENCES text_p(text_id);

ALTER TABLE text_p
    ADD CONSTRAINT text_p_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE text_p
    ADD CONSTRAINT text_p_theme_id_fkey FOREIGN KEY (theme_id) REFERENCES theme(theme_id);
