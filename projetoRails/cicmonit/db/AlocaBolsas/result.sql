PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
REPLACE INTO "candidatos" VALUES(1,'MS',1,0);
REPLACE INTO "candidatos" VALUES(2,'SS',2,2);
REPLACE INTO "candidatos" VALUES(3,'SS',3,2);
REPLACE INTO "candidatos" VALUES(4,'MS',4,1);
REPLACE INTO "candidatos" VALUES(5,'MS',6,2);
REPLACE INTO "candidatos" VALUES(6,'MS',5,0);
COMMIT;
