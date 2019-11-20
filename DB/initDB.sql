-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`profile` ;

CREATE TABLE IF NOT EXISTS `mydb`.`profile` (
  `weightIncome` DECIMAL(2,1) NOT NULL,
  `weightHPrice` DECIMAL(2,1) NOT NULL,
  `weightPopulation` DECIMAL(2,1) NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `userId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `userId`),
  CONSTRAINT `fk_profile_user`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`state` ;

CREATE TABLE IF NOT EXISTS `mydb`.`state` (
  `stateName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`stateName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`county` ;

CREATE TABLE IF NOT EXISTS `mydb`.`county` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `population` INT NULL,
  `avgIncome` INT NULL,
  `avgHPrice` INT NULL,
  `stateName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`, `stateName`),
  CONSTRAINT `stateName`
    FOREIGN KEY (`stateName`)
    REFERENCES `mydb`.`state` (`stateName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_fav_county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_fav_county` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_fav_county` (
  `userId` INT UNSIGNED NOT NULL,
  `countyId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`userId`, `countyId`),
  CONSTRAINT `fk_user_has_county_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_county_county1`
    FOREIGN KEY (`countyId`)
    REFERENCES `mydb`.`county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`job_listing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`job_listing` ;

CREATE TABLE IF NOT EXISTS `mydb`.`job_listing` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`job_county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`job_county` ;

CREATE TABLE IF NOT EXISTS `mydb`.`job_county` (
  `countyId` INT UNSIGNED NOT NULL,
  `jobListingId` INT NOT NULL,
  PRIMARY KEY (`countyId`, `jobListingId`),
  CONSTRAINT `fk_county_has_job_listing_county1`
    FOREIGN KEY (`countyId`)
    REFERENCES `mydb`.`county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_county_has_job_listing_job_listing1`
    FOREIGN KEY (`jobListingId`)
    REFERENCES `mydb`.`job_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`industry_job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`industry_job` ;

CREATE TABLE IF NOT EXISTS `mydb`.`industry_job` (
  `jobId` INT NOT NULL,
  `industry` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`jobId`, `industry`),
  CONSTRAINT `jobId`
    FOREIGN KEY (`jobId`)
    REFERENCES `mydb`.`job_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`school`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`school` ;

CREATE TABLE IF NOT EXISTS `mydb`.`school` (
  `name` INT NOT NULL,
  `countyId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`name`, `countyId`),
  CONSTRAINT `fk_school_county1`
    FOREIGN KEY (`countyId`)
    REFERENCES `mydb`.`county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_ban_state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_ban_state` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_ban_state` (
  `userId` INT UNSIGNED NOT NULL,
  `stateName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`userId`, `stateName`),
  CONSTRAINT `fk_user_has_state_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_state_state1`
    FOREIGN KEY (`stateName`)
    REFERENCES `mydb`.`state` (`stateName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
