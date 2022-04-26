CREATE TABLE "attribute_tag"
(
  "id_tag" bigint NOT NULL,
  "id_user" bigint NOT NULL
);

ALTER TABLE "attribute_tag" ADD CONSTRAINT "pk_attribute_tag"
  PRIMARY KEY ("id_tag", "id_user");

CREATE TABLE "attribute_team"
(
  "id_user" bigint NOT NULL,
  "id_team" bigint NOT NULL,
  "join_date" date NOT NULL
);

ALTER TABLE "attribute_team" ADD CONSTRAINT "pk_attribute_team"
  PRIMARY KEY ("id_user", "id_team");

CREATE TABLE "book"
(
  "id_user" bigint NOT NULL,
  "id_workplace" integer NOT NULL,
  "reservation_date" date NOT NULL,
  "id_ref_reservation_status" smallint NOT NULL,
  "manager_comment" text,
  "specific_need" text,
  "is_recurent" boolean DEFAULT false NOT NULL
);

ALTER TABLE "book" ADD CONSTRAINT "pk_book"
  PRIMARY KEY ("id_user", "id_workplace");

CREATE TABLE "manage"
(
  "id_managed" bigint NOT NULL,
  "id_manager" bigint NOT NULL
);

ALTER TABLE "manage" ADD CONSTRAINT "pk_manage"
  PRIMARY KEY ("id_managed", "id_manager");

CREATE TABLE "recurrent_slot"
(
  "id_user" bigint NOT NULL,
  "id_workplace" integer NOT NULL,
  "day" smallint NOT NULL,
  "valid" boolean DEFAULT false NOT NULL
);

ALTER TABLE "recurrent_slot" ADD CONSTRAINT "pk_recurrent_slot"
  PRIMARY KEY ("id_user", "id_workplace", "day");

CREATE TABLE "ref_reservation_status"
(
  "id_ref_reservation_status" smallint NOT NULL,
  "label_ref_reservation" varchar(10) NOT NULL
);

ALTER TABLE "ref_reservation_status" ADD CONSTRAINT "pk_ref_reservation_status"
  PRIMARY KEY ("id_ref_reservation_status");

CREATE TABLE "slot"
(
  "id_workplace" integer NOT NULL,
  "reservation_date" date NOT NULL,
  "is_block" boolean DEFAULT false NOT NULL,
  "block_comment" text
);

ALTER TABLE "slot" ADD CONSTRAINT "pk_reservation"
  PRIMARY KEY ("id_workplace", "reservation_date");

CREATE TABLE "tag"
(
  "id_tag" bigint NOT NULL,
  "label_tag" bigint NOT NULL
);

ALTER TABLE "tag" ADD CONSTRAINT "pk_tag"
  PRIMARY KEY ("id_tag");

CREATE TABLE "team"
(
  "id_team" bigint NOT NULL,
  "name" bigint NOT NULL,
  "avatar" bigint,
  "id_captain" bigint NOT NULL
);

ALTER TABLE "team" ADD CONSTRAINT "pk_team"
  PRIMARY KEY ("id_team");

CREATE TABLE "user"
(
  "id_user" bigint NOT NULL,
  "avatar" varchar(1000)
);

ALTER TABLE "user" ADD CONSTRAINT "pk_user"
  PRIMARY KEY ("id_user");

CREATE TABLE "workplace"
(
  "id_workplace" integer NOT NULL,
  "name" varchar(255) NOT NULL
);

ALTER TABLE "workplace" ADD CONSTRAINT "pk_workplace"
  PRIMARY KEY ("id_workplace");

ALTER TABLE "attribute_tag" ADD CONSTRAINT "fk_attribute_tag_tag"
  FOREIGN KEY ("id_tag") REFERENCES "tag" ("id_tag");

ALTER TABLE "attribute_tag" ADD CONSTRAINT "fk_attribute_tag_user"
  FOREIGN KEY ("id_user") REFERENCES "user" ("id_user");

ALTER TABLE "attribute_team" ADD CONSTRAINT "fk_attribute_team_team"
  FOREIGN KEY ("id_team") REFERENCES "team" ("id_team");

ALTER TABLE "attribute_team" ADD CONSTRAINT "fk_attribute_team_user"
  FOREIGN KEY ("id_user") REFERENCES "user" ("id_user");

ALTER TABLE "book" ADD CONSTRAINT "fk_book_ref_reservation_status"
  FOREIGN KEY ("id_ref_reservation_status") REFERENCES "ref_reservation_status" ("id_ref_reservation_status");

ALTER TABLE "book" ADD CONSTRAINT "fk_book_reservation"
  FOREIGN KEY ("id_workplace", "reservation_date") REFERENCES "slot" ("id_workplace", "reservation_date");

ALTER TABLE "book" ADD CONSTRAINT "fk_book_user"
  FOREIGN KEY ("id_user") REFERENCES "user" ("id_user");

ALTER TABLE "manage" ADD CONSTRAINT "fk_manage_manageer"
  FOREIGN KEY ("id_manager") REFERENCES "user" ("id_user");

ALTER TABLE "manage" ADD CONSTRAINT "fk_manage_user_managed"
  FOREIGN KEY ("id_managed") REFERENCES "user" ("id_user");

ALTER TABLE "recurrent_slot" ADD CONSTRAINT "fk_recurrent_slot_user"
  FOREIGN KEY ("id_user") REFERENCES "user" ("id_user");

ALTER TABLE "recurrent_slot" ADD CONSTRAINT "fk_recurrent_slot_workplace"
  FOREIGN KEY ("id_workplace") REFERENCES "workplace" ("id_workplace");

ALTER TABLE "slot" ADD CONSTRAINT "fk_reservation_workplace"
  FOREIGN KEY ("id_workplace") REFERENCES "workplace" ("id_workplace");

ALTER TABLE "team" ADD CONSTRAINT "fk_team_user_captain"
  FOREIGN KEY ("id_captain") REFERENCES "user" ("id_user");
