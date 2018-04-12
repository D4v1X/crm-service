DROP SCHEMA IF EXISTS crm_schema;
CREATE SCHEMA crm_schema;

-- SET SCHEMA crm_schema;

---------------------------- User -----------------------------
CREATE TABLE crm_schema.user
(
  id               BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  uuid             UUID                  NOT NULL UNIQUE,
  name             TEXT,
  email            VARCHAR               NOT NULL UNIQUE,
  password         TEXT                  NOT NULL,
  secret           TEXT                  NOT NULL,
  role             CHARACTER(5)          NOT NULL DEFAULT 'USER',
  token            TEXT,
  token_expiration TIMESTAMP,
  active           BOOLEAN               NOT NULL DEFAULT true,

  created          TIMESTAMP             NOT NULL DEFAULT now(),
  modified         TIMESTAMP             NOT NULL DEFAULT now()
);

---------------------------- Customer -----------------------------
CREATE TABLE crm_schema.customer
(
  id               BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name             CHARACTER(50)         NOT NULL,
  surname          CHARACTER(50)         NOT NULL,
  photo            TEXT,
  created_by_user  INTEGER               NOT NULL REFERENCES crm_schema.user (id),
  created          TIMESTAMP             NOT NULL DEFAULT now(),
  modified_by_user INTEGER REFERENCES crm_schema.user (id),
  modified         TIMESTAMP             NOT NULL DEFAULT now()
);

