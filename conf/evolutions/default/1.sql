# --- !Ups

---------------------------- Schema ----------------------------
CREATE SCHEMA crm_schema;

---------------------------- Function -------------------------
CREATE OR REPLACE FUNCTION crm_schema.update_modified_column()
  RETURNS TRIGGER AS
$BODY$
BEGIN
  NEW.modified := now();;
  RETURN NEW;;
END
$BODY$
LANGUAGE plpgsql
VOLATILE
COST 100;;

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


# --- !Downs

DROP TABLE crm_schema.user;
DROP TABLE crm_schema.customer;
DROP FUNCTION crm_schema.update_modified_column();
DROP SCHEMA crm_schema;