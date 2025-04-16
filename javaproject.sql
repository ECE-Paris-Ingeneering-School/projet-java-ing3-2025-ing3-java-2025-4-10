-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : mar. 15 avr. 2025 à 14:04
-- Version du serveur : 11.5.2-MariaDB
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `javaproject`
--

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `ID_Lieu` int(11) NOT NULL AUTO_INCREMENT,
  `Adresse` text NOT NULL,
  `Ville` text NOT NULL,
  `CodePostal` int(11) NOT NULL,
  PRIMARY KEY (`ID_Lieu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `ID_Note` int(11) NOT NULL,
  `Commentaire` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `ID_Patient` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `Email` text NOT NULL,
  `Ancien` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_Patient`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `ID_RDV` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Heure` time NOT NULL,
  `Motif` text NOT NULL,
  PRIMARY KEY (`ID_RDV`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `specialiste`
--

DROP TABLE IF EXISTS `specialiste`;
CREATE TABLE IF NOT EXISTS `specialiste` (
  `ID_Spécialiste` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `Email` text NOT NULL,
  `Spécialité` text NOT NULL,
  `Qualification` text NOT NULL,
  PRIMARY KEY (`ID_Spécialiste`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID_User` int(11) NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `MotDePasse` text NOT NULL,
  `Role` text NOT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
