Commit inicial:
GetDB: o programa l� a arvore de html do site do Matricula Web, retornando os campus,departamentos e mat�rias.
Est� configurado atualmente para retornar apenas as mat�rias do CIC.
Segue o seguinte padr�o caso seja configurado desde a raiz do MW:

insert into campus values (1,'Darcy Ribeiro');
insert into departamento values (116,'CIC','Departamento de Ci�ncia da Computa��o',1);
insert into disciplinas values (113476,'ALGORITMOS E PROGRAMA��O DE COMPUTADORES',116); <--A Configura�ao atual comessa a partir daqui
insert into turmas values ('A',113476);
insert into horarios values ('SEG�16:00 - 17:50 PAT AT 117',113476,'A');
insert into horarios values ('TER�16:00 - 17:50 PJC BT 093',113476,'A');
insert into horarios values ('QUI�16:00 - 17:50 PJC BT 093',113476,'A');
insert into professores values ('CARLA DENISE CASTANHO',113476,'A');