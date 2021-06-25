CREATE TABLE IF NOT EXISTS `pakiety` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL UNIQUE,
    `cena` int,
    `pakiet` varchar(500)
);

INSERT INTO `pakiety` (`id`,`name`,`cena`,`pakiet`) VALUES (1,"JUNIOR",60,
"COLT 1911 KAL. 22 x 16
HK 416 KAL. 22 x 16");
INSERT INTO `pakiety` (`id`,`name`,`cena`,`pakiet`) VALUES (2,"EL CLASSICO",100,
"REWOLWER x 16
DUBELTÓWKA x 16");
