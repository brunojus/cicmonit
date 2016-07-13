PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE "turmas" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "nome" varchar, "disciplina_id" integer, "QtdeAlunos" integer, "obrigatoria" integer);
INSERT INTO "turmas" VALUES(1,'A',1,38,1);
INSERT INTO "turmas" VALUES(2,'B',1,39,1);
INSERT INTO "turmas" VALUES(3,'C',1,38,1);
INSERT INTO "turmas" VALUES(4,'D',1,37,0);
INSERT INTO "turmas" VALUES(5,'E',1,41,1);
INSERT INTO "turmas" VALUES(6,'F',1,36,0);
INSERT INTO "turmas" VALUES(7,'G',1,25,0);
INSERT INTO "turmas" VALUES(8,'H',1,28,1);
INSERT INTO "turmas" VALUES(9,'A',2,19,1);
INSERT INTO "turmas" VALUES(10,'A',3,35,1);
INSERT INTO "turmas" VALUES(11,'A',4,23,1);
INSERT INTO "turmas" VALUES(12,'B',4,26,1);
INSERT INTO "turmas" VALUES(13,'A',5,30,1);
INSERT INTO "turmas" VALUES(14,'B',5,28,1);
INSERT INTO "turmas" VALUES(15,'C',5,25,1);
INSERT INTO "turmas" VALUES(16,'D',5,28,1);
INSERT INTO "turmas" VALUES(17,'A',6,14,1);
INSERT INTO "turmas" VALUES(18,'B',6,18,1);
INSERT INTO "turmas" VALUES(19,'B',7,3,0);
INSERT INTO "turmas" VALUES(20,'C',7,1,0);
INSERT INTO "turmas" VALUES(21,'D',7,2,0);
INSERT INTO "turmas" VALUES(22,'A',8,18,0);
INSERT INTO "turmas" VALUES(23,'A',9,13,0);
INSERT INTO "turmas" VALUES(24,'A',10,39,1);
INSERT INTO "turmas" VALUES(25,'B',10,37,1);
INSERT INTO "turmas" VALUES(26,'C',10,33,1);
INSERT INTO "turmas" VALUES(27,'E',10,26,1);
INSERT INTO "turmas" VALUES(28,'F',10,26,0);
INSERT INTO "turmas" VALUES(29,'B',11,1,0);
INSERT INTO "turmas" VALUES(30,'E',11,1,0);
INSERT INTO "turmas" VALUES(31,'GG',11,1,0);
INSERT INTO "turmas" VALUES(32,'I',11,4,0);
INSERT INTO "turmas" VALUES(33,'J',11,1,0);
INSERT INTO "turmas" VALUES(34,'NN',11,2,0);
INSERT INTO "turmas" VALUES(35,'P',11,1,0);
INSERT INTO "turmas" VALUES(36,'S',11,1,0);
INSERT INTO "turmas" VALUES(37,'A',12,3,0);
INSERT INTO "turmas" VALUES(38,'B',12,1,0);
INSERT INTO "turmas" VALUES(39,'C',12,2,0);
INSERT INTO "turmas" VALUES(40,'CC',12,2,0);
INSERT INTO "turmas" VALUES(41,'E',12,1,0);
INSERT INTO "turmas" VALUES(42,'J',12,1,0);
INSERT INTO "turmas" VALUES(43,'K',12,5,0);
INSERT INTO "turmas" VALUES(44,'L',12,3,0);
INSERT INTO "turmas" VALUES(45,'T',12,1,0);
INSERT INTO "turmas" VALUES(46,'U',12,6,0);
INSERT INTO "turmas" VALUES(47,'X',12,1,0);
INSERT INTO "turmas" VALUES(48,'B',13,1,0);
INSERT INTO "turmas" VALUES(49,'D',13,2,0);
INSERT INTO "turmas" VALUES(50,'DD',13,1,0);
INSERT INTO "turmas" VALUES(51,'II',13,1,0);
INSERT INTO "turmas" VALUES(52,'O',13,4,0);
INSERT INTO "turmas" VALUES(53,'Y',13,3,0);
INSERT INTO "turmas" VALUES(54,'AA',14,2,0);
INSERT INTO "turmas" VALUES(55,'BB',14,2,0);
INSERT INTO "turmas" VALUES(56,'DD',14,1,0);
INSERT INTO "turmas" VALUES(57,'E',14,2,0);
INSERT INTO "turmas" VALUES(58,'EE',14,2,0);
INSERT INTO "turmas" VALUES(59,'FF',14,2,0);
INSERT INTO "turmas" VALUES(60,'H',14,3,0);
INSERT INTO "turmas" VALUES(61,'HH',14,2,0);
INSERT INTO "turmas" VALUES(62,'LL',14,2,0);
INSERT INTO "turmas" VALUES(63,'O',14,4,0);
INSERT INTO "turmas" VALUES(64,'V',14,1,0);
INSERT INTO "turmas" VALUES(65,'X',14,2,0);
INSERT INTO "turmas" VALUES(66,'ZZ',14,1,0);
INSERT INTO "turmas" VALUES(67,'A',15,50,1);
INSERT INTO "turmas" VALUES(68,'A',16,54,1);
INSERT INTO "turmas" VALUES(69,'B',16,51,1);
INSERT INTO "turmas" VALUES(70,'A',17,11,0);
INSERT INTO "turmas" VALUES(71,'A',18,38,1);
INSERT INTO "turmas" VALUES(72,'B',18,46,1);
INSERT INTO "turmas" VALUES(73,'A',19,26,1);
INSERT INTO "turmas" VALUES(74,'B',19,19,1);
INSERT INTO "turmas" VALUES(75,'C',19,25,1);
INSERT INTO "turmas" VALUES(76,'D',19,26,1);
INSERT INTO "turmas" VALUES(77,'E',19,18,1);
INSERT INTO "turmas" VALUES(78,'F',19,23,1);
INSERT INTO "turmas" VALUES(79,'G',19,26,1);
INSERT INTO "turmas" VALUES(80,'H',19,22,1);
INSERT INTO "turmas" VALUES(81,'I',19,21,1);
INSERT INTO "turmas" VALUES(82,'J',19,23,1);
INSERT INTO "turmas" VALUES(83,'K',19,24,1);
INSERT INTO "turmas" VALUES(84,'L',19,25,1);
INSERT INTO "turmas" VALUES(85,'M',19,24,1);
INSERT INTO "turmas" VALUES(86,'N',19,22,1);
INSERT INTO "turmas" VALUES(87,'A',20,16,0);
INSERT INTO "turmas" VALUES(88,'A',21,48,1);
INSERT INTO "turmas" VALUES(89,'I',22,43,1);
INSERT INTO "turmas" VALUES(90,'A',23,17,0);
INSERT INTO "turmas" VALUES(91,'A',24,14,0);
INSERT INTO "turmas" VALUES(92,'A',25,26,1);
INSERT INTO "turmas" VALUES(93,'A',26,50,1);
INSERT INTO "turmas" VALUES(94,'A',27,38,1);
INSERT INTO "turmas" VALUES(95,'B',27,38,1);
INSERT INTO "turmas" VALUES(96,'A',28,29,1);
INSERT INTO "turmas" VALUES(97,'B',28,18,1);
INSERT INTO "turmas" VALUES(98,'D',28,29,0);
INSERT INTO "turmas" VALUES(99,'A',29,26,1);
INSERT INTO "turmas" VALUES(100,'A',30,19,0);
INSERT INTO "turmas" VALUES(101,'A',31,22,1);
INSERT INTO "turmas" VALUES(102,'A',32,25,1);
INSERT INTO "turmas" VALUES(103,'B',32,33,1);
INSERT INTO "turmas" VALUES(104,'C',32,21,1);
INSERT INTO "turmas" VALUES(105,'A',33,30,1);
INSERT INTO "turmas" VALUES(106,'B',33,24,1);
INSERT INTO "turmas" VALUES(107,'C',33,34,1);
INSERT INTO "turmas" VALUES(108,'A',34,13,0);
INSERT INTO "turmas" VALUES(109,'A',35,32,1);
INSERT INTO "turmas" VALUES(110,'A',36,1,0);
INSERT INTO "turmas" VALUES(111,'B',37,35,1);
INSERT INTO "turmas" VALUES(112,'A',38,31,1);
INSERT INTO "turmas" VALUES(113,'B',39,3,0);
INSERT INTO "turmas" VALUES(114,'A',40,11,1);
INSERT INTO "turmas" VALUES(115,'B',40,9,1);
INSERT INTO "turmas" VALUES(116,'EE',41,1,0);
INSERT INTO "turmas" VALUES(117,'FF',41,1,0);
INSERT INTO "turmas" VALUES(118,'H',41,3,0);
INSERT INTO "turmas" VALUES(119,'HH',41,3,0);
INSERT INTO "turmas" VALUES(120,'J',41,1,0);
INSERT INTO "turmas" VALUES(121,'JJ',41,1,0);
INSERT INTO "turmas" VALUES(122,'K',41,1,0);
INSERT INTO "turmas" VALUES(123,'KK',41,1,0);
INSERT INTO "turmas" VALUES(124,'L',41,3,0);
INSERT INTO "turmas" VALUES(125,'OO',41,1,0);
INSERT INTO "turmas" VALUES(126,'S',41,3,0);
INSERT INTO "turmas" VALUES(127,'XX',41,1,0);
INSERT INTO "turmas" VALUES(128,'Z',41,1,0);
INSERT INTO "turmas" VALUES(129,'A',42,27,1);
INSERT INTO "turmas" VALUES(130,'A',43,2,0);
INSERT INTO "turmas" VALUES(131,'B',43,2,0);
INSERT INTO "turmas" VALUES(132,'C',43,2,0);
INSERT INTO "turmas" VALUES(133,'D',43,1,0);
INSERT INTO "turmas" VALUES(134,'E',43,1,0);
INSERT INTO "turmas" VALUES(135,'F',43,2,0);
INSERT INTO "turmas" VALUES(136,'I',43,1,0);
INSERT INTO "turmas" VALUES(137,'K',43,1,0);
INSERT INTO "turmas" VALUES(138,'M',43,1,0);
INSERT INTO "turmas" VALUES(139,'N',43,4,0);
INSERT INTO "turmas" VALUES(140,'O',43,1,0);
INSERT INTO "turmas" VALUES(141,'A',44,1,0);
INSERT INTO "turmas" VALUES(142,'B',44,1,0);
INSERT INTO "turmas" VALUES(143,'C',44,1,0);
INSERT INTO "turmas" VALUES(144,'D',44,2,0);
INSERT INTO "turmas" VALUES(145,'E',44,2,0);
INSERT INTO "turmas" VALUES(146,'F',44,3,0);
INSERT INTO "turmas" VALUES(147,'G',44,1,0);
INSERT INTO "turmas" VALUES(148,'H',44,1,0);
INSERT INTO "turmas" VALUES(149,'J',44,1,0);
INSERT INTO "turmas" VALUES(150,'M',44,1,0);
INSERT INTO "turmas" VALUES(151,'A',45,15,1);
INSERT INTO "turmas" VALUES(152,'A',46,19,0);
INSERT INTO "turmas" VALUES(153,'A',47,37,1);
INSERT INTO "turmas" VALUES(154,'A',48,23,1);
INSERT INTO "turmas" VALUES(155,'B',48,19,1);
INSERT INTO "turmas" VALUES(156,'A',49,32,1);
INSERT INTO "turmas" VALUES(157,'A',50,24,1);
INSERT INTO "turmas" VALUES(158,'B',50,39,1);
INSERT INTO "turmas" VALUES(159,'B',51,3,0);
INSERT INTO "turmas" VALUES(160,'C',51,1,0);
INSERT INTO "turmas" VALUES(161,'D',51,2,0);
INSERT INTO "turmas" VALUES(162,'A',52,44,1);
INSERT INTO "turmas" VALUES(163,'A',53,15,1);
INSERT INTO "turmas" VALUES(164,'A',54,24,1);
INSERT INTO "turmas" VALUES(165,'B',55,15,0);
INSERT INTO "turmas" VALUES(166,'B',56,2,0);
INSERT INTO "turmas" VALUES(167,'A',57,5,0);
INSERT INTO "turmas" VALUES(168,'B',57,1,0);
INSERT INTO "turmas" VALUES(169,'E',57,25,0);
INSERT INTO "turmas" VALUES(170,'H',57,11,0);
INSERT INTO "turmas" VALUES(171,'I',57,2,0);
INSERT INTO "turmas" VALUES(172,'J',57,1,0);
INSERT INTO "turmas" VALUES(173,'N',57,8,0);
INSERT INTO "turmas" VALUES(174,'P',57,4,0);
INSERT INTO "turmas" VALUES(175,'Q',57,7,0);
INSERT INTO "turmas" VALUES(176,'A',58,2,0);
INSERT INTO "turmas" VALUES(177,'C',58,1,0);
INSERT INTO "turmas" VALUES(178,'D',58,1,0);
INSERT INTO "turmas" VALUES(179,'A',59,6,1);
INSERT INTO "turmas" VALUES(180,'B',59,20,1);
INSERT INTO "turmas" VALUES(181,'AA',60,2,0);
INSERT INTO "turmas" VALUES(182,'C',60,1,0);
INSERT INTO "turmas" VALUES(183,'D',60,1,0);
INSERT INTO "turmas" VALUES(184,'DD',60,2,0);
INSERT INTO "turmas" VALUES(185,'G',60,1,0);
INSERT INTO "turmas" VALUES(186,'I',60,2,0);
INSERT INTO "turmas" VALUES(187,'II',60,1,0);
INSERT INTO "turmas" VALUES(188,'MM',60,5,0);
INSERT INTO "turmas" VALUES(189,'NN',60,2,0);
INSERT INTO "turmas" VALUES(190,'U',60,1,0);
INSERT INTO "turmas" VALUES(191,'VV',60,1,0);
INSERT INTO "turmas" VALUES(192,'W',60,1,0);
INSERT INTO "turmas" VALUES(193,'WW',60,3,0);
INSERT INTO "turmas" VALUES(194,'X',60,0,0);
INSERT INTO "turmas" VALUES(195,'A',61,31,1);
INSERT INTO "turmas" VALUES(196,'A',62,37,1);
CREATE INDEX "index_turmas_on_disciplina_id" ON "turmas" ("disciplina_id");
COMMIT;