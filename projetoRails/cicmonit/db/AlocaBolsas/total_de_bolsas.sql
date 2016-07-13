PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE "total_de_bolsas" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "QTE" integer, "QTE_alocadas" integer);
INSERT INTO "total_de_bolsas" VALUES(1,2,0);
COMMIT;
