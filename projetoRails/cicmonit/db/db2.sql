PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE "schema_migrations" ("version" varchar NOT NULL);
INSERT INTO "schema_migrations" VALUES('20160409010228');
INSERT INTO "schema_migrations" VALUES('20160409015547');
INSERT INTO "schema_migrations" VALUES('20160417161628');
INSERT INTO "schema_migrations" VALUES('20160419181652');
INSERT INTO "schema_migrations" VALUES('20160419181729');
INSERT INTO "schema_migrations" VALUES('20160419181907');
INSERT INTO "schema_migrations" VALUES('20160419181938');
INSERT INTO "schema_migrations" VALUES('20160419182125');
INSERT INTO "schema_migrations" VALUES('20160512021203');
INSERT INTO "schema_migrations" VALUES('20160512041736');
INSERT INTO "schema_migrations" VALUES('20160512041843');
INSERT INTO "schema_migrations" VALUES('20160606163605');
INSERT INTO "schema_migrations" VALUES('20160609035141');
INSERT INTO "schema_migrations" VALUES('20160609035234');
INSERT INTO "schema_migrations" VALUES('20160609043524');
INSERT INTO "schema_migrations" VALUES('20160627012808');
INSERT INTO "schema_migrations" VALUES('20160627013426');
INSERT INTO "schema_migrations" VALUES('20160705181903');
CREATE TABLE "professors" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "nome" varchar, "matricula" integer, "email" varchar DEFAULT '' NOT NULL, "encrypted_password" varchar DEFAULT '' NOT NULL, "reset_password_token" varchar, "reset_password_sent_at" datetime, "remember_created_at" datetime, "sign_in_count" integer DEFAULT 0 NOT NULL, "current_sign_in_at" datetime, "last_sign_in_at" datetime, "current_sign_in_ip" varchar, "last_sign_in_ip" varchar);
CREATE TABLE "alunos" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "nome" varchar(225) NOT NULL, "matricula" varchar(9) NOT NULL, "semestre" integer NOT NULL, "IRA" float DEFAULT 5.0 NOT NULL, "user_id" integer);
INSERT INTO "alunos" VALUES(1,'LUAN TALLES DA SILVA ARAUJO','100111483',10,5.0,1);
CREATE TABLE "campus" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "codigo" integer, "nome" varchar);
CREATE TABLE "departamentos" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "codigo" integer, "sigla" varchar, "nome" varchar, "campu_id" integer);
CREATE TABLE "disciplinas" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "codigo" integer, "nome" varchar, "departamento_id" integer);
CREATE TABLE "turmas" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "nome" varchar, "disciplina_id" integer, "QtdeAlunos" integer);
CREATE TABLE "horarios" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "nome" varchar, "turma_id" integer);
CREATE TABLE "candidatos" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "mencao" varchar, "aluno_id" integer, "avaliacao" integer);
INSERT INTO "candidatos" VALUES(1,'SS',1,0);
INSERT INTO "candidatos" VALUES(4,'SS',1,0);
CREATE TABLE "docentes_turmas" ("docente_id" integer NOT NULL, "turma_id" integer NOT NULL);
CREATE TABLE "candidatos_turmas" ("candidato_id" integer NOT NULL, "turma_id" integer NOT NULL);
INSERT INTO "candidatos_turmas" VALUES(1,5);
INSERT INTO "candidatos_turmas" VALUES(4,2);
CREATE TABLE "docentes" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "nome" varchar, "user_id" integer);
CREATE TABLE "users" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "email" varchar DEFAULT '' NOT NULL, "encrypted_password" varchar DEFAULT '' NOT NULL, "reset_password_token" varchar, "reset_password_sent_at" datetime, "remember_created_at" datetime, "sign_in_count" integer DEFAULT 0 NOT NULL, "current_sign_in_at" datetime, "last_sign_in_at" datetime, "current_sign_in_ip" varchar, "last_sign_in_ip" varchar, "created_at" datetime NOT NULL, "updated_at" datetime NOT NULL, "confirmation_token" varchar, "confirmed_at" datetime, "confirmation_sent_at" datetime);
INSERT INTO "users" VALUES(1,'100111483@aluno.unb.br','$2a$10$b4ggolh2JCZr87EYAAbJweGLtnGeejqdoaIQcIoKkPNodcBPws1TK',NULL,NULL,NULL,3,'2016-07-05 20:19:05.649102','2016-07-05 19:05:25.959815','127.0.0.1','127.0.0.1','2016-07-05 18:35:55.207145','2016-07-05 20:19:05.650925','5D7o4szKUp7j8yd2R8Dj','2016-07-05 18:39:36.677447','2016-07-05 18:35:55.207490');
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('users',1);
INSERT INTO "sqlite_sequence" VALUES('docentes',7);
INSERT INTO "sqlite_sequence" VALUES('campus',1);
INSERT INTO "sqlite_sequence" VALUES('departamentos',1);
INSERT INTO "sqlite_sequence" VALUES('disciplinas',1);
INSERT INTO "sqlite_sequence" VALUES('turmas',8);
INSERT INTO "sqlite_sequence" VALUES('horarios',34);
INSERT INTO "sqlite_sequence" VALUES('alunos',1);
INSERT INTO "sqlite_sequence" VALUES('candidatos',4);
CREATE UNIQUE INDEX "unique_schema_migrations" ON "schema_migrations" ("version");
CREATE UNIQUE INDEX "index_professors_on_email" ON "professors" ("email");
CREATE UNIQUE INDEX "index_professors_on_reset_password_token" ON "professors" ("reset_password_token");
CREATE INDEX "index_departamentos_on_campu_id" ON "departamentos" ("campu_id");
CREATE INDEX "index_disciplinas_on_departamento_id" ON "disciplinas" ("departamento_id");
CREATE INDEX "index_turmas_on_disciplina_id" ON "turmas" ("disciplina_id");
CREATE INDEX "index_horarios_on_turma_id" ON "horarios" ("turma_id");
CREATE INDEX "index_candidatos_on_aluno_id" ON "candidatos" ("aluno_id");
CREATE INDEX "index_docentes_turmas_on_docente_id" ON "docentes_turmas" ("docente_id");
CREATE INDEX "index_docentes_turmas_on_turma_id" ON "docentes_turmas" ("turma_id");
CREATE INDEX "index_candidatos_turmas_on_candidato_id" ON "candidatos_turmas" ("candidato_id");
CREATE INDEX "index_candidatos_turmas_on_turma_id" ON "candidatos_turmas" ("turma_id");
CREATE UNIQUE INDEX "index_users_on_email" ON "users" ("email");
CREATE UNIQUE INDEX "index_users_on_reset_password_token" ON "users" ("reset_password_token");
CREATE UNIQUE INDEX "index_users_on_confirmation_token" ON "users" ("confirmation_token");
COMMIT;
