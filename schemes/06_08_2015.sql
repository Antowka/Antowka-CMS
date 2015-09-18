CREATE TABLE articles
(
    article_id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    short_description VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    user_owner_id BIGINT NOT NULL
);
CREATE TABLE articles_article_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE articles_categories_rel
(
    article_category_id BIGINT NOT NULL,
    article_id BIGINT NOT NULL
);
CREATE TABLE articles_category
(
    article_category_id SERIAL PRIMARY KEY NOT NULL,
    parent_category_id INT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR NOT NULL
);
CREATE TABLE category_article_category_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE category_tickets_category_tickets_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE role
(
    role_id SERIAL PRIMARY KEY NOT NULL,
    role VARCHAR(55) NOT NULL
);
CREATE TABLE role_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE settings
(
    setting_name VARCHAR(55) PRIMARY KEY NOT NULL,
    value VARCHAR(1024) NOT NULL
);
CREATE TABLE tickets
(
    ticket_id SERIAL PRIMARY KEY NOT NULL,
    user_owner_id BIGINT,
    email VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL
);
CREATE TABLE tickets_categories_rel
(
    ticket_category_id BIGINT NOT NULL,
    ticket_id BIGINT NOT NULL
);
CREATE TABLE tickets_category
(
    ticket_category_id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR NOT NULL,
    parent_category_id INT
);
CREATE TABLE tickets_ticket_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE user_role
(
    user_role_id INT PRIMARY KEY NOT NULL,
    user_id INT,
    role_id INT
);
CREATE TABLE users
(
    user_id INT PRIMARY KEY NOT NULL,
    first_name VARCHAR,
    last_name VARCHAR,
    login VARCHAR,
    email VARCHAR,
    password VARCHAR,
    enable INT NOT NULL
);
ALTER TABLE articles ADD FOREIGN KEY (user_owner_id) REFERENCES users (user_id);
CREATE UNIQUE INDEX unique_article_id ON articles (article_id);
ALTER TABLE articles_categories_rel ADD FOREIGN KEY (article_id) REFERENCES articles (article_id);
ALTER TABLE articles_categories_rel ADD FOREIGN KEY (article_category_id) REFERENCES articles_category (article_category_id);
CREATE UNIQUE INDEX unique_category_id ON articles_category (article_category_id);
CREATE UNIQUE INDEX unique_id ON role (role_id);
CREATE UNIQUE INDEX unique_setting_name ON settings (setting_name);
ALTER TABLE tickets ADD FOREIGN KEY (user_owner_id) REFERENCES users (user_id);
CREATE UNIQUE INDEX unique_ticket_id ON tickets (ticket_id);
ALTER TABLE tickets_categories_rel ADD FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id);
ALTER TABLE tickets_categories_rel ADD FOREIGN KEY (ticket_category_id) REFERENCES tickets_category (category_tickets_id);
CREATE UNIQUE INDEX unique_category_tickets_id ON tickets_category (ticket_category_id);
ALTER TABLE user_role ADD FOREIGN KEY (role_id) REFERENCES role (role_id);
ALTER TABLE user_role ADD FOREIGN KEY (user_id) REFERENCES users (user_id);
CREATE UNIQUE INDEX unique_user_id ON users (user_id);
