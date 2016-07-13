PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
DELETE FROM docentes_turmas;
INSERT OR REPLACE INTO "campus" VALUES(1,1,'Darcy Ribeiro');
INSERT OR REPLACE INTO "departamentos" VALUES(1,116,'CIC','Departamento de Ciência de Computação',1);
COMMIT;
