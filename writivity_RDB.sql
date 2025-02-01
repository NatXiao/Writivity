
-- CREATE RDB

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(20) UNIQUE NOT NULL,
    pseudo VARCHAR(20) NOT NULL,
    mail VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- bcrypt POUR HASH LE MOTS DE PASSE
    isAdmin BOOL DEFAULT FALSE
);

CREATE TABLE rating (
    rate_id SERIAL PRIMARY KEY,
    text_id INTEGER REFERENCES text_p(text_id),
    user_id INTEGER REFERENCES users(user_id),
    rate INTEGER CHECK(rate > 0 AND rate <= 5)
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
    challenge_id INT NOT NULL,
    body TEXT NOT NULL,
    status VARCHAR(30),
    text_submit BOOLEAN,
    submitted_at TIMESTAMP,
    reported BOOLEAN DEFAULT FALSE,
    disqualified BOOLEAN DEFAULT FALSE
);

CREATE TABLE challenge (
    challenge_id SERIAL PRIMARY KEY,
    challenge_name VARCHAR(30),
    word_limit INT NOT NULL,
    open_at  DATE DEFAULT CURRENT_TIMESTAMP,
    close_at  DATE DEFAULT CURRENT_TIMESTAMP,
    conditions VARCHAR(255),
    CONSTRAINT check_open_before_close CHECK (open_at <= close_at)
);

-- Création des clés étrangères après création des tables

ALTER TABLE vote
    ADD CONSTRAINT vote_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;

ALTER TABLE vote
    ADD CONSTRAINT vote_text_id_fkey FOREIGN KEY (text_id) REFERENCES text_p(text_id) ON DELETE CASCADE;

ALTER TABLE comment
    ADD CONSTRAINT comment_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;

ALTER TABLE comment
    ADD CONSTRAINT comment_text_id_fkey FOREIGN KEY (text_id) REFERENCES text_p(text_id) ON DELETE CASCADE;

ALTER TABLE text_p
    ADD CONSTRAINT text_p_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;

ALTER TABLE text_p
    ADD CONSTRAINT text_p_challenge_id_fkey FOREIGN KEY (challenge_id) REFERENCES challenge(challenge_id) ON DELETE CASCADE;



------
-- pour remplir la db pour test


-- ATTENTION :  créer 3 utilisateurs."


INSERT INTO challenge (challenge_name, word_limit, open_at, close_at, conditions)
VALUES
    ('Premier défi', 500, '2025-01-01', '2025-01-31', 'Utiliser au moins un mot en latin'),
    ('Deuxième défi', 300, '2025-02-01', '2025-02-15', 'Inclure une citation célèbre');

INSERT INTO text_p (user_id, challenge_id, body, status, text_submit, submitted_at, reported, disqualified)
VALUES
    (2, 1, 'Lorem ipsum dolor sit amet...', 'En attente', TRUE, '2025-01-05 10:00:00', FALSE, FALSE),
    (3, 1, 'Texte de Bobbybob pour le premier défi...', 'Validé', TRUE, '2025-01-06 14:30:00', FALSE, FALSE),
    (2, 2, 'Participation au deuxième défi...', 'En attente', FALSE, NULL, FALSE, FALSE);

INSERT INTO vote (user_id, text_id, rating)
VALUES
    (3, 1, 5),
    (2, 2, 4);


INSERT INTO comment (user_id, text_id, body, reported)
VALUES
    (3, 1, 'Super texte, bravo !', FALSE),
    (2, 1, 'J aime bien, mais quelques fautes.', TRUE),
    (2, 2, 'Excellent travail sur ce défi !', FALSE);

-- Ajouter un nouveau défi (Challenge)
INSERT INTO challenge (challenge_name, word_limit, open_at, close_at, conditions)
VALUES
    ('Troisième défi', 350, '2024-03-01', '2024-03-15', 'Écrire un texte dhumeur'),
    ('Quatrième défi', 600, '2024-04-01', '2024-04-30', 'Texte en utilisant des dialogues entre deux personnages');


INSERT INTO text_p (user_id, challenge_id, body, status, text_submit, submitted_at, reported, disqualified)
VALUES
    (2, 3, 'Texte sur l"humeur d"une journee pluvieuse...', 'En attente', FALSE, NULL, FALSE, FALSE),
    (3, 3, 'L"humeur dans la vie quotidienne...', 'Validé', TRUE, '2025-03-05 08:00:00', FALSE, FALSE),
    (2, 4, 'Dialogues entre deux amis sur un sujet philosophique...', 'En attente', TRUE, '2025-04-10 12:00:00', FALSE, FALSE);


-- Ajouter des votes pour les textes
INSERT INTO vote (user_id, text_id, rating)
VALUES
    (2, 1, 4),  -- Utilisateur 2 vote sur le texte 1 (Premier défi)
    (3, 2, 5),  -- Utilisateur 3 vote sur le texte 2 (Deuxième défi)
    (2, 3, 3);  -- Utilisateur 2 vote sur le texte 3 (Troisième défi)


ALTER TABLE text_p
    ADD COLUMN text_title VARCHAR(255) NOT NULL DEFAULT 'Sans titre';

UPDATE text_p SET text_title = 'Premier texte' WHERE text_id = 1;
UPDATE text_p SET text_title = 'Deuxième texte' WHERE text_id = 2;
UPDATE text_p SET text_title = 'Troisième texte' WHERE text_id = 3;
UPDATE text_p SET text_title = 'Quatrième texte' WHERE text_id = 4;
UPDATE text_p SET text_title = 'Cinquième texte' WHERE text_id = 5;
UPDATE text_p SET text_title = 'Sixième texte' WHERE text_id = 6;
UPDATE text_p SET text_title = 'Septième texte' WHERE text_id = 7;
UPDATE text_p SET text_title = 'Huitième texte' WHERE text_id = 8;
UPDATE text_p SET text_title = 'Neuvième texte' WHERE text_id = 9;



