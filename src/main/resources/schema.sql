DROP TABLE IF EXISTS srv_orders CASCADE;
DROP TABLE IF EXISTS srv_users_roles CASCADE;
DROP TABLE IF EXISTS srv_users CASCADE;
DROP TABLE IF EXISTS srv_roles CASCADE;

CREATE TABLE IF NOT EXISTS srv_roles(
    role_id serial,
    role_name varchar(20) UNIQUE NOT NULL,
    PRIMARY KEY (role_id)
    );

CREATE TABLE IF NOT EXISTS srv_users(
    user_id serial,
    email varchar UNIQUE NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS srv_users_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES srv_users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES srv_roles (role_id) ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS srv_orders(
    order_id serial,
    name varchar(100),
    email varchar(100),
    phone varchar(100),
    comment text,
    order_type varchar(20),
    date_of_creation timestamp NOT NULL,
    owner_id integer NOT NULL,
    PRIMARY KEY (order_id),
    CONSTRAINT fk_order_user FOREIGN KEY (owner_id) REFERENCES srv_users (user_id) ON UPDATE CASCADE ON DELETE CASCADE
    );