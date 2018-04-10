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