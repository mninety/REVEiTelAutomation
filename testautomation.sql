-- MySQL dump 10.10
--
-- Host: localhost    Database: testautomation
-- ------------------------------------------------------
-- Server version	5.0.18-nt-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auapilist`
--

DROP TABLE IF EXISTS `auapilist`;
CREATE TABLE `auapilist` (
  `apiID` int(10) NOT NULL auto_increment,
  `apiModuleID` int(10) NOT NULL default '1',
  `apiSubmoduleID` varchar(100) default NULL,
  `apiName` varchar(100) NOT NULL,
  `apiParams` varchar(500) NOT NULL,
  `apiModifTime` decimal(18,0) NOT NULL,
  PRIMARY KEY  (`apiID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auapilist`
--


/*!40000 ALTER TABLE `auapilist` DISABLE KEYS */;
LOCK TABLES `auapilist` WRITE;
INSERT INTO `auapilist` VALUES (1,1,'CallerID API','Get CallerID API','/api/callerIDAPI.jsp?type=get&user=&password=&nonce=','1521533520000'),(2,1,'CallerID API','Add CallerID API','/api/callerIDAPI.jsp?type=add&callerID=&user=&password=&nonce=','1521533520000'),(3,1,'CallerID API','Remove CallerID API','/api/callerIDAPI.jsp?type=remove&callerID=&user=&password=&nonce=','1521533520000');
UNLOCK TABLES;
/*!40000 ALTER TABLE `auapilist` ENABLE KEYS */;

--
-- Table structure for table `auconfigid`
--

DROP TABLE IF EXISTS `auconfigid`;
CREATE TABLE `auconfigid` (
  `ciID` int(10) NOT NULL auto_increment,
  `ciName` varchar(50) NOT NULL,
  `ciUserID` int(10) NOT NULL,
  `ciType` tinyint(1) NOT NULL default '2',
  `ciParamName` varchar(30) NOT NULL,
  `ciParamValue` varchar(30) NOT NULL,
  `ciModifTime` decimal(18,0) NOT NULL,
  PRIMARY KEY  (`ciID`),
  UNIQUE KEY `ciName` (`ciName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auconfigid`
--


/*!40000 ALTER TABLE `auconfigid` DISABLE KEYS */;
LOCK TABLES `auconfigid` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `auconfigid` ENABLE KEYS */;

--
-- Table structure for table `auconfigvar`
--

DROP TABLE IF EXISTS `auconfigvar`;
CREATE TABLE `auconfigvar` (
  `cvID` int(10) NOT NULL auto_increment,
  `cvName` varchar(50) NOT NULL,
  `cvUserID` int(10) NOT NULL,
  `cvExpectedTitle` varchar(100) default NULL,
  `cvExpectedTitle1` varchar(100) default NULL,
  `cvExpectedTitle2` varchar(100) default NULL,
  `cvBillingURL` varchar(100) NOT NULL,
  `cvDBServerIP` varchar(15) NOT NULL,
  `cvDBName` varchar(50) NOT NULL,
  `cvDBuser` varchar(30) NOT NULL,
  `cvDBpass` varchar(30) NOT NULL,
  `cvLocation` varchar(50) NOT NULL default '/usr/local/iTelTestAutomation/WebDriver',
  `cvAdminuser` varchar(30) NOT NULL,
  `cvAdminpass` varchar(30) NOT NULL,
  `cvPinuser` varchar(30) NOT NULL,
  `cvPinpass` varchar(30) NOT NULL,
  `cvModifTime` decimal(18,0) NOT NULL,
  `cvIsNew` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`cvID`),
  UNIQUE KEY `cvName` (`cvName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auconfigvar`
--


/*!40000 ALTER TABLE `auconfigvar` DISABLE KEYS */;
LOCK TABLES `auconfigvar` WRITE;
INSERT INTO `auconfigvar` VALUES (4,'LatestBilling705',1,'iTelSwitchPlus ::Login','Failure!!','jakarta-tomcat-7.0.61 - Error report','http://162.222.186.235:90/brightTelecomBilling','162.222.186.235','iTelBillingbrighttelecom','mizan','reve','/usr/local/iTelTestAutomation/WebDriver','reve','abc1','12345','1','20181014094944',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `auconfigvar` ENABLE KEYS */;

--
-- Table structure for table `auexecutionplan`
--

DROP TABLE IF EXISTS `auexecutionplan`;
CREATE TABLE `auexecutionplan` (
  `expID` int(10) NOT NULL auto_increment,
  `expPlanBatchID` int(10) NOT NULL,
  `expType` tinyint(2) NOT NULL default '1',
  `expModuleID` int(10) NOT NULL,
  `expSubmoduleID` int(10) default NULL,
  `expApiID` varchar(200) default NULL,
  `expTestcaseID` int(10) default NULL,
  PRIMARY KEY  (`expID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auexecutionplan`
--


/*!40000 ALTER TABLE `auexecutionplan` DISABLE KEYS */;
LOCK TABLES `auexecutionplan` WRITE;
INSERT INTO `auexecutionplan` VALUES (6,2,1,2,NULL,NULL,NULL),(7,3,3,3,NULL,'',NULL),(8,2,1,1,NULL,'1',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `auexecutionplan` ENABLE KEYS */;

--
-- Table structure for table `auexecutionplanbatch`
--

DROP TABLE IF EXISTS `auexecutionplanbatch`;
CREATE TABLE `auexecutionplanbatch` (
  `expbID` int(10) NOT NULL auto_increment,
  `expbUserID` int(10) NOT NULL,
  `expbName` varchar(50) NOT NULL,
  `expbConfigVarID` int(10) NOT NULL,
  `expbConfigID` int(10) default NULL,
  `expbType` tinyint(2) NOT NULL default '1',
  `expbExeType` tinyint(1) NOT NULL default '1',
  `expbTime` decimal(18,0) NOT NULL,
  PRIMARY KEY  (`expbID`),
  UNIQUE KEY `expbName` (`expbName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auexecutionplanbatch`
--


/*!40000 ALTER TABLE `auexecutionplanbatch` DISABLE KEYS */;
LOCK TABLES `auexecutionplanbatch` WRITE;
INSERT INTO `auexecutionplanbatch` VALUES (2,1,'Test',4,NULL,1,3,'1540176979869'),(3,2,'Test2',4,NULL,1,3,'1540883202590');
UNLOCK TABLES;
/*!40000 ALTER TABLE `auexecutionplanbatch` ENABLE KEYS */;

--
-- Table structure for table `auexecutionresult`
--

DROP TABLE IF EXISTS `auexecutionresult`;
CREATE TABLE `auexecutionresult` (
  `erID` int(10) NOT NULL auto_increment,
  `erExePlanID` int(10) NOT NULL,
  `erResult` varchar(500) NOT NULL default 'Test Successful',
  `erStatus` tinyint(1) NOT NULL default '1',
  `erExeTime` decimal(18,0) NOT NULL,
  PRIMARY KEY  (`erID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auexecutionresult`
--


/*!40000 ALTER TABLE `auexecutionresult` DISABLE KEYS */;
LOCK TABLES `auexecutionresult` WRITE;
INSERT INTO `auexecutionresult` VALUES (355,7,'PIN Batch:Batch-64040 Add is failed',2,'1540883171486'),(358,7,'PIN Batch:Batch-64040 Delete is failed',2,'1540883171490'),(357,7,'PIN Batch:Batch-64040 Edit is failed',2,'1540883171489'),(356,7,'PIN Batch:Batch-64040 Search is failed',2,'1540883171488');
UNLOCK TABLES;
/*!40000 ALTER TABLE `auexecutionresult` ENABLE KEYS */;

--
-- Table structure for table `aumodulename`
--

DROP TABLE IF EXISTS `aumodulename`;
CREATE TABLE `aumodulename` (
  `mnID` int(10) NOT NULL auto_increment,
  `mnMenuID` int(10) default NULL,
  `mnModuleName` varchar(50) NOT NULL,
  `mnModifTime` decimal(18,0) default NULL,
  PRIMARY KEY  (`mnID`),
  UNIQUE KEY `mnModuleName` (`mnModuleName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `aumodulename`
--


/*!40000 ALTER TABLE `aumodulename` DISABLE KEYS */;
LOCK TABLES `aumodulename` WRITE;
INSERT INTO `aumodulename` VALUES (1,1,'API',NULL),(2,2,'Client',NULL),(3,3,'PIN',NULL),(4,4,'DID',NULL),(5,5,'Rounting',NULL),(6,6,'Reporting',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `aumodulename` ENABLE KEYS */;

--
-- Table structure for table `ausubmodule`
--

DROP TABLE IF EXISTS `ausubmodule`;
CREATE TABLE `ausubmodule` (
  `smID` int(10) NOT NULL auto_increment,
  `smMenuID` int(10) default NULL,
  `smModuleID` int(10) default NULL,
  `smSubmoduleName` varchar(50) NOT NULL,
  `smModifTime` decimal(18,0) default NULL,
  PRIMARY KEY  (`smID`),
  UNIQUE KEY `smSubmoduleName` (`smSubmoduleName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ausubmodule`
--


/*!40000 ALTER TABLE `ausubmodule` DISABLE KEYS */;
LOCK TABLES `ausubmodule` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `ausubmodule` ENABLE KEYS */;

--
-- Table structure for table `autestcases`
--

DROP TABLE IF EXISTS `autestcases`;
CREATE TABLE `autestcases` (
  `tcID` int(10) NOT NULL auto_increment,
  `tcModuleID` int(10) default NULL,
  `tcSubmoduleID` int(10) default NULL,
  `tcDescription` varchar(500) NOT NULL,
  `tcExpectedResult` varchar(500) NOT NULL,
  `tcModifTime` decimal(18,0) default NULL,
  PRIMARY KEY  (`tcID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `autestcases`
--


/*!40000 ALTER TABLE `autestcases` DISABLE KEYS */;
LOCK TABLES `autestcases` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `autestcases` ENABLE KEYS */;

--
-- Table structure for table `auusers`
--

DROP TABLE IF EXISTS `auusers`;
CREATE TABLE `auusers` (
  `id` int(11) NOT NULL auto_increment,
  `full_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `permission_type` int(30) NOT NULL COMMENT '1=PM 2=TL 0=Normal',
  `added_date` timestamp NULL default CURRENT_TIMESTAMP,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auusers`
--


/*!40000 ALTER TABLE `auusers` DISABLE KEYS */;
LOCK TABLES `auusers` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `auusers` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

