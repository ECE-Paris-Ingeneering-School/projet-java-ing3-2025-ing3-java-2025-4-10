-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 24, 2025 at 02:34 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javaproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `ID_Lieu` int NOT NULL AUTO_INCREMENT,
  `Adresse` text NOT NULL,
  `Ville` text NOT NULL,
  `CodePostal` int NOT NULL,
  PRIMARY KEY (`ID_Lieu`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lieu`
--

INSERT INTO `lieu` (`ID_Lieu`, `Adresse`, `Ville`, `CodePostal`) VALUES
(1, '10 rue sextius michel', 'Paris', 75010),
(2, '82 esplanade du general de gaulle', 'Paris', 92934),
(3, '24 rue salomon reinach', 'Lyon', 69007);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID_User` int NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `MotDePasse` text NOT NULL,
  `Role` text NOT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID_User`, `email`, `MotDePasse`, `Role`) VALUES
(1, '', '', ''),
(2, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `ID_Patient` int NOT NULL AUTO_INCREMENT,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `Email` text NOT NULL,
  `Ancien` tinyint(1) NOT NULL,
  `FK_ID_User` int NOT NULL,
  PRIMARY KEY (`ID_Patient`),
  FOREIGN KEY (`FK_ID_User`) REFERENCES user (`ID_User`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`ID_Patient`, `Nom`, `Prenom`, `Email`, `Ancien`) VALUES
(1, 'Ho', 'Kimi', 'kimi.ho@edu.ece.fr', 0),
(2, 'Ribaute Picard', 'Maxence', 'maxence.ribautepicard@edu.ece.fr', 1),
(3, 'Davroux', 'Henri', 'henri.davroux@edu.ece.fr', 1),
(4, 'Barriere', 'Romain', 'romain.barriere@edu.ece.fr', 0);

-- --------------------------------------------------------

--
-- Table structure for table `specialiste`
--

DROP TABLE IF EXISTS `specialiste`;
CREATE TABLE IF NOT EXISTS `specialiste` (
  `ID_Spécialiste` int NOT NULL AUTO_INCREMENT,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `Email` text NOT NULL,
  `Spécialité` text NOT NULL,
  PRIMARY KEY (`ID_Spécialiste`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialiste`
--

INSERT INTO `specialiste` (`ID_Spécialiste`, `Nom`, `Prenom`, `Email`, `Spécialité`) VALUES
(1, 'Eutamene', 'Noreddine', 'noreddine.eutamene@edu.ece.fr', 'cardiologue');

-- --------------------------------------------------------

--
-- Table structure for table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `ID_RDV` int NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Heure` time NOT NULL,
  `Motif` text NOT NULL,
  `FK_ID_Lieu` int NOT NULL,
  `FK_ID_Spécialiste` int NOT NULL,
  `FK_ID_Patient` int NOT NULL,
  PRIMARY KEY (`ID_RDV`),
  FOREIGN KEY (`FK_ID_Lieu`) REFERENCES `lieu` (`ID_Lieu`),
  FOREIGN KEY (`FK_ID_Spécialiste`) REFERENCES `specialiste` (`ID_Spécialiste`),
  FOREIGN KEY (`FK_ID_Patient`) REFERENCES `patient` (`ID_Patient`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rdv`
--

INSERT INTO `rdv` (`ID_RDV`, `Date`, `Heure`, `Motif`) VALUES
(1, '2025-04-28', '08:30:00', 'Otite'),
(2, '2025-04-28', '09:00:00', 'Mal de tete');

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `ID_Note` int NOT NULL,
  `Commentaire` text NOT NULL,
  `FK_ID_RDV` int NOT NULL,
  PRIMARY KEY (`ID_Note`),
  FOREIGN KEY (`FK_ID_RDV`) REFERENCES `rdv` (`ID_RDV`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `note`
--

INSERT INTO `note` (`ID_Note`, `Commentaire`) VALUES
(1, 'Patient tousse'),
(2, 'Patient eternue'),
(3, 'Patient rythme cardiaque elevee');

-- --------------------------------------------------------

--
-- Table structure for table `specialiste_lieu`
--

DROP TABLE IF EXISTS `specialiste_lieu`;
CREATE TABLE IF NOT EXISTS `specialiste_lieu` (
  `FK_ID_Lieu` int NOT NULL,
  `FK_ID_Spécialiste` int NOT NULL,
  FOREIGN KEY (`FK_ID_Lieu`) REFERENCES `lieu` (`ID_Lieu`),
  FOREIGN KEY (`FK_ID_Spécialiste`) REFERENCES `specialiste` (`ID_Spécialiste`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialiste_lieu`
--

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
