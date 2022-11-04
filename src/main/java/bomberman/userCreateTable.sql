CREATE TABLE `qrabiloo`.`userbomber`
( `id` INT(11) NOT NULL AUTO_INCREMENT ,
`firstName` VARCHAR(50) NOT NULL ,
`lastName` VARCHAR(50) NOT NULL ,
`userName` VARCHAR(50) NOT NULL ,
`password` VARCHAR(20) NOT NULL ,
`dateOfBirth` DATE NOT NULL ,
`timeRegister` DATETIME NULL ,
`admin` BOOLEAN NULL ,
PRIMARY KEY (`id`, `userName`))
ENGINE = InnoDB;

CREATE TABLE `qrabiloo`.`userhistory`
( `userName` VARCHAR(50) NOT NULL ,
`timeStart` DATETIME NOT NULL ,
 `timeEnd` DATETIME NOT NULL ,
 `timePlayed` DATETIME NOT NULL ,
  `score` INT NOT NULL ,
  PRIMARY KEY (`userName`))
ENGINE = InnoDB;