GetDB: o programa lê a arvore de html do site do Matricula Web, retornando os campus,departamentos e matérias.
Está configurado atualmente para retornar apenas as matérias do CIC.
Segue o seguinte padrão caso seja configurado desde a raiz do MW:

insert into campus values (1,'Darcy Ribeiro');
insert into departamento values (1,116,'CIC','Departamento de Ciência da Computação',1);
insert into disciplinas values (1,113476,'ALGORITMOS E PROGRAMAÇÃO DE COMPUTADORES',116,1); <--A Configuraçao atual comessa a partir daqui
insert into turmas values (1,'A',113476);
insert into horarios values (1,'SEG 16:00 - 17:50 PAT AT 117',113476,1);
insert into horarios values (2,'TER 16:00 - 17:50 PJC BT 093',113476,1);
insert into horarios values (3,'QUI 16:00 - 17:50 PJC BT 093',113476,1);
insert into professores values (1,'CARLA DENISE CASTANHO',113476,1);
