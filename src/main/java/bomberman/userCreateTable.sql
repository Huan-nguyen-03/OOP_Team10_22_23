CREATE TABLE `qrabiloo`.`userbomber`
(
`firstName` VARCHAR(50) NOT NULL ,
`lastName` VARCHAR(50) NOT NULL ,
`userName` VARCHAR(50) NOT NULL ,
`password` VARCHAR(20) NOT NULL ,
`dateOfBirth` DATE NOT NULL ,
`timeRegister` DATETIME NULL ,
`admin` BOOLEAN NULL ,
PRIMARY KEY ( `userName`))
ENGINE = InnoDB;

CREATE TABLE `qrabiloo`.`userhistory`
(
`id` INT(11) NOT NULL AUTO_INCREMENT ,
`userName` VARCHAR(50) NOT NULL ,
`timeStart` DATETIME NOT NULL ,
 `timeEnd` DATETIME NOT NULL ,
 `timePlayed` INT NOT NULL ,
  `score` INT NOT NULL ,
  `status` TEXT NOT NULL
  PRIMARY KEY (`id`)
  CONSTRAINT linked_user FOREIGN KEY userName
  REFERENCES userbomber (userName) ON DELETE CASCADE ON
  UPDATE CASCADE)
ENGINE = InnoDB;