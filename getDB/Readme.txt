GetDB: o programa l� a arvore de html do site do Matricula Web, retornando os campus,departamentos e mat�rias.
Est� configurado atualmente para retornar apenas as mat�rias do CIC.
Segue o seguinte padr�o caso seja configurado desde a raiz do MW:

insert into campus values (1,'Darcy Ribeiro');
insert into departamento values (1,116,'CIC','Departamento de Ci�ncia da Computa��o',1);
insert into disciplinas values (1,113476,'ALGORITMOS E PROGRAMA��O DE COMPUTADORES',116,1); <--A Configura�ao atual comessa a partir daqui
insert into turmas values (1,'A',113476);
insert into horarios values (1,'SEG�16:00 - 17:50 PAT AT 117',113476,1);
insert into horarios values (2,'TER�16:00 - 17:50 PJC BT 093',113476,1);
insert into horarios values (3,'QUI�16:00 - 17:50 PJC BT 093',113476,1);
insert into professores values (1,'CARLA DENISE CASTANHO',113476,1);
