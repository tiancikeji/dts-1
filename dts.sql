# Source on 127.0.0.1: ... connected.
# Exporting metadata from dts
DROP DATABASE IF EXISTS `dts`;
CREATE DATABASE `dts`;
USE `dts`;
# TABLE: dts.area
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `background` char(255) DEFAULT NULL,
  `scope_start` int(11) DEFAULT NULL,
  `scope_end` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
# TABLE: dts.area_channel
CREATE TABLE `area_channel` (
  `area_id` int(11) NOT NULL,
  `channel_id` int(11) NOT NULL,
  `scope_start` int(11) DEFAULT NULL,
  `scope_end` int(11) DEFAULT NULL,
  PRIMARY KEY (`area_id`,`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# TABLE: dts.cable
CREATE TABLE `cable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `length` char(50) NOT NULL,
  `signal_1` char(255) DEFAULT NULL,
  `signal_2` char(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  PRIMARY KEY (`id`,`length`),
  KEY `created_at` (`created_at`,`length`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
# TABLE: dts.channel
CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `sensor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
# TABLE: dts.sensor
CREATE TABLE `sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
# temperature
CREATE TABLE `temperature` (
	`channel` int(10) NOT NULL,
	`tem` MEDIUMTEXT NOT NULL,
	`stock` MEDIUMTEXT DEFAULT NULL, -- 后台配置1是否存
	`unstock` MEDIUMTEXT DEFAULT NULL, -- 后台配置1是否存
	`refer_tem` double DEFAULT NULL, -- 后台配置2是否存
	`date` datetime NOT NULL,
  KEY `tm_key` (`channel`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
CREATE TABLE `temperature_event` (
	`channel` int(10) NOT NULL,
	`tem` MEDIUMTEXT NOT NULL,
	`stock` MEDIUMTEXT DEFAULT NULL, -- 后台配置1是否存
	`unstock` MEDIUMTEXT DEFAULT NULL, -- 后台配置1是否存
	`refer_tem` double DEFAULT NULL, -- 后台配置2是否存
	`date` datetime NOT NULL,
  KEY `tm_key` (`channel`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
CREATE TABLE `temperature_log` (
	`channel` int(10) NOT NULL,
	`tem` MEDIUMTEXT NOT NULL,
	`stock` MEDIUMTEXT DEFAULT NULL, -- 后台配置1是否存
	`unstock` MEDIUMTEXT DEFAULT NULL, -- 后台配置1是否存
	`refer_tem` double DEFAULT NULL, -- 后台配置2是否存
	`date` datetime NOT NULL,
  KEY `tm_key` (`channel`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
CREATE TABLE `config` (
	`switchcom` int(10) NOT NULL,
	`port` int(10) NOT NULL,
	`date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
CREATE TABLE `channels` (
	`channel` int(10) NOT NULL,
	`length` int(10) NOT NULL,
	`date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
#...done.
