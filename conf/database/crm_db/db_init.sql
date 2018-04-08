---------------------------- START ----------------------------
CREATE ROLE crm_user
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;

ALTER ROLE crm_user
PASSWORD 'crm_pass';

CREATE DATABASE crm_db
WITH OWNER = crm_user
ENCODING = 'UTF8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

\connect crm_db

SET ROLE crm_user;

---------------------------- Schema ----------------------------
CREATE SCHEMA crm_schema;

---------------------------- Functions -------------------------
CREATE OR REPLACE FUNCTION crm_schema.update_modified_column()
  RETURNS TRIGGER AS
$BODY$
BEGIN
  NEW.modified := now();
  RETURN NEW;
END
$BODY$
LANGUAGE plpgsql
VOLATILE
COST 100;

---------------------------- Tables ---------------------------

---------------------------- Master Tables --------------------
---------------------------- User -----------------------------
CREATE TABLE crm_schema.user
(
  id               SERIAL                      NOT NULL PRIMARY KEY,
  uuid             UUID                        NOT NULL UNIQUE,
  name             TEXT,
  email            TEXT                        NOT NULL UNIQUE,
  password         TEXT                        NOT NULL,
  secret           TEXT                        NOT NULL,
  role             CHARACTER VARYING(5)        NOT NULL DEFAULT 'USER',
  token            TEXT,
  token_expiration TIMESTAMP WITHOUT TIME ZONE,
  active           BOOLEAN                     NOT NULL DEFAULT true,

  created          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
  modified         TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX users_unique_lower_email_idx
  ON crm_schema.user (lower(email));

CREATE TRIGGER trg_bu_user
  BEFORE UPDATE
  ON crm_schema.user
  FOR EACH ROW
EXECUTE PROCEDURE crm_schema.update_modified_column();

-- Account: User: superadmin, PASS: superadmin
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('42800f3f-718e-4daf-a7b7-40c7e547b5d6', 'superadmin', 'superadmin@gmail.com',
        '26b02e695a0c917d66e3e85580ebb578116b2bd1998cca8028815cc156b5df2a', 'd58a41cf-d882-40be-8278-cd8671141f95',
        'ADMIN');

-- Account: User: user, PASS: user
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('8abdff85-7a3a-41ee-9d4f-59e0bc686b40', 'user', 'user@gmail.com',
        'c0056e49bcdafe79bd823a54f1fb2ec29714b1cf52c62f0cc2fc91acc2318abb', 'dee03675-d8a3-4956-b543-3fb8d96b2c18',
        'USER');

-- Account: User: admin, PASS: admin
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('705477ee-f632-4b5c-b038-04296ef21236', 'admin', 'admin@gmail.com',
        'c7366ebba87d785ff5eaf99acf4750dae6dbdc7c1f711800066138ee3c4725d4', 'a23f232d-8eac-42e1-8c63-097175abec69',
        'ADMIN');

-- Account: User: change, PASS: change
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('ed9e44c5-fde3-465c-a97c-189d053ffc73', 'change', 'change@gmail.com',
        '4f32cbbb66ad3d02171458b970bf4f9c853944d036db439d27931780a56f0933', 'a52475a4-7509-489d-972a-90b5f06cee30',
        'ADMIN');

---------------------------- Customer -----------------------------
CREATE TABLE crm_schema.customer
(
  id               SERIAL                      NOT NULL PRIMARY KEY,
  name             CHARACTER VARYING(50)       NOT NULL,
  surname          CHARACTER VARYING(50)       NOT NULL,
  photo            TEXT,
  created_by_user  INTEGER                     NOT NULL REFERENCES crm_schema.user (id),
  created          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
  modified_by_user INTEGER REFERENCES crm_schema.user (id),
  modified         TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE TRIGGER trg_bu_customer
  BEFORE UPDATE
  ON crm_schema.customer
  FOR EACH ROW
EXECUTE PROCEDURE crm_schema.update_modified_column();