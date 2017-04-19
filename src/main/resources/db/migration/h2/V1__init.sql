CREATE SEQUENCE hibernate_sequence;

CREATE TABLE topic (
  id integer PRIMARY KEY,
  title VARCHAR(100)
);

CREATE TABLE message (
  id integer PRIMARY KEY,
  create_time TIMESTAMP NOT NULL,
  content VARCHAR(1000),
  topic integer NOT NULL REFERENCES topic (id)
);
