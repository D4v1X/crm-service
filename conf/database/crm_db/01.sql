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

\connect ex01_db

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
  id         SERIAL                      NOT NULL PRIMARY KEY,

  login_name CHARACTER VARYING(50)       NOT NULL UNIQUE,
  role       CHARACTER VARYING(5)        NOT NULL,

  created    TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
  modified   TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE TRIGGER trg_bu_user
  BEFORE UPDATE
  ON crm_schema.user
  FOR EACH ROW
EXECUTE PROCEDURE crm_schema.update_modified_column();

---------------------------- Customer -----------------------------
CREATE TABLE crm_schema.customer
(
  id               SERIAL                      NOT NULL PRIMARY KEY,
  name             CHARACTER VARYING(50)       NOT NULL,
  surname          CHARACTER VARYING(50)       NOT NULL,
  photo            TEXT,
  created_by_user  INTEGER                     NOT NULL REFERENCES crm_schema.user (id),
  created          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
  modified_by_user INTEGER                     REFERENCES crm_schema.user (id),
  modified         TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE TRIGGER trg_bu_customer
  BEFORE UPDATE
  ON crm_schema.customer
  FOR EACH ROW
EXECUTE PROCEDURE crm_schema.update_modified_column();