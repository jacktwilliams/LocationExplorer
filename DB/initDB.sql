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
  `weight_income` DECIMAL(1,1) NOT NULL,
  `weight_hprice` DECIMAL(1,1) NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  UNIQUE INDEX `p_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_profile_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_profile_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`state` ;

CREATE TABLE IF NOT EXISTS `mydb`.`state` (
  `state_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`state_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`county` ;

CREATE TABLE IF NOT EXISTS `mydb`.`county` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `population` INT NULL,
  `avg_income` INT NULL,
  `avg_hprice` INT NULL,
  `state_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`, `state_name`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `state_name_idx` (`state_name` ASC) VISIBLE,
  CONSTRAINT `state_name`
    FOREIGN KEY (`state_name`)
    REFERENCES `mydb`.`state` (`state_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_fav_county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_fav_county` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_fav_county` (
  `user_id` INT UNSIGNED NOT NULL,
  `county_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `county_id`),
  INDEX `fk_user_has_county_county1_idx` (`county_id` ASC) VISIBLE,
  INDEX `fk_user_has_county_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_county_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_county_county1`
    FOREIGN KEY (`county_id`)
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
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`job_county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`job_county` ;

CREATE TABLE IF NOT EXISTS `mydb`.`job_county` (
  `county_id` INT UNSIGNED NOT NULL,
  `job_listing_id` INT NOT NULL,
  PRIMARY KEY (`county_id`, `job_listing_id`),
  INDEX `fk_county_has_job_listing_job_listing1_idx` (`job_listing_id` ASC) VISIBLE,
  INDEX `fk_county_has_job_listing_county1_idx` (`county_id` ASC) VISIBLE,
  CONSTRAINT `fk_county_has_job_listing_county1`
    FOREIGN KEY (`county_id`)
    REFERENCES `mydb`.`county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_county_has_job_listing_job_listing1`
    FOREIGN KEY (`job_listing_id`)
    REFERENCES `mydb`.`job_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`industry_job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`industry_job` ;

CREATE TABLE IF NOT EXISTS `mydb`.`industry_job` (
  `job_id` INT NOT NULL,
  `industry` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`job_id`, `industry`),
  CONSTRAINT `job_id`
    FOREIGN KEY (`job_id`)
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
  `county_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`name`, `county_id`),
  INDEX `fk_school_county1_idx` (`county_id` ASC) VISIBLE,
  CONSTRAINT `fk_school_county1`
    FOREIGN KEY (`county_id`)
    REFERENCES `mydb`.`county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_ban_state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_ban_state` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_ban_state` (
  `user_id` INT UNSIGNED NOT NULL,
  `state_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`, `state_name`),
  INDEX `fk_user_has_state_state1_idx` (`state_name` ASC) VISIBLE,
  INDEX `fk_user_has_state_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_state_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_state_state1`
    FOREIGN KEY (`state_name`)
    REFERENCES `mydb`.`state` (`state_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
