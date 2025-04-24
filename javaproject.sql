-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le : jeu. 24 avr. 2025 à 16:58
-- Version du serveur : 9.1.0
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
  `ID_Lieu` int NOT NULL AUTO_INCREMENT,
  `Adresse` text NOT NULL,
  `Ville` text NOT NULL,
  `CodePostal` int NOT NULL,
  PRIMARY KEY (`ID_Lieu`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`ID_Lieu`, `Adresse`, `Ville`, `CodePostal`) VALUES
(1, '10 rue sextius michel', 'Paris', 75010),
(2, '82 esplanade du general de gaulle', 'Paris', 92934),
(3, '24 rue salomon reinach', 'Lyon', 69007);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `ID_Note` int NOT NULL,
  `Commentaire` text NOT NULL,
  `FK_ID_RDV` int NOT NULL,
  PRIMARY KEY (`ID_Note`),
  KEY `FK_ID_RDV` (`FK_ID_RDV`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`ID_Note`, `Commentaire`, `FK_ID_RDV`) VALUES
(1, 'Patient tousse', 1),
(2, 'Patient eternue', 2),
(3, 'Patient rythme cardiaque elevee', 3);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `ID_Patient` int NOT NULL AUTO_INCREMENT,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `Email` text NOT NULL,
  `Ancien` tinyint(1) DEFAULT NULL,
  `FK_ID_User` int NOT NULL,
  PRIMARY KEY (`ID_Patient`),
  KEY `FK_ID_User` (`FK_ID_User`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`ID_Patient`, `Nom`, `Prenom`, `Email`, `Ancien`, `FK_ID_User`) VALUES
(1, 'Ho', 'Kimi', 'kimi.ho@edu.ece.fr', 0, 1),
(2, 'Ribaute Picard', 'Maxence', 'maxence.ribautepicard@edu.ece.fr', 1, 2),
(3, 'Davroux', 'Henri', 'henri.davroux@edu.ece.fr', 1, 3),
(4, 'Barriere', 'Romain', 'romain.barriere@edu.ece.fr', 0, 4);

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
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
  KEY `FK_ID_Lieu` (`FK_ID_Lieu`),
  KEY `FK_ID_Spécialiste` (`FK_ID_Spécialiste`),
  KEY `FK_ID_Patient` (`FK_ID_Patient`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`ID_RDV`, `Date`, `Heure`, `Motif`, `FK_ID_Lieu`, `FK_ID_Spécialiste`, `FK_ID_Patient`) VALUES
(1, '2025-04-28', '08:30:00', 'Otite', 1, 4, 6),
(2, '2025-04-28', '09:00:00', 'Mal de tete', 2, 4, 3),
(3, '2025-04-28', '09:30:00', 'Rythme cardiaque', 1, 1, 1),
(4, '2025-04-28', '10:00:00', 'Fievre', 3, 5, 5);

-- --------------------------------------------------------

--
-- Structure de la table `specialiste`
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
-- Déchargement des données de la table `specialiste`
--

INSERT INTO `specialiste` (`ID_Spécialiste`, `Nom`, `Prenom`, `Email`, `Spécialité`) VALUES
(1, 'Eutamene', 'Noreddine', 'noreddine.eutamene@edu.ece.fr', 'cardiologue'),
(2, 'Segado', 'Jean Pierre', 'jeanpierre.segado@edu.ece.fr', 'pediatre'),
(3, 'Perry', 'Katy', 'katy.perry@edu.ece.fr', 'chirurgien'),
(4, 'Le Cor', 'Luc', 'luc.lecory@edu.ece.fr', 'generaliste'),
(5, 'Minot', 'Thierry', 'thierry.minot@edu.ece.fr', 'generaliste');

-- --------------------------------------------------------

--
-- Structure de la table `specialiste_lieu`
--

DROP TABLE IF EXISTS `specialiste_lieu`;
CREATE TABLE IF NOT EXISTS `specialiste_lieu` (
  `FK_ID_Lieu` int NOT NULL,
  `FK_ID_Spécialiste` int NOT NULL,
  KEY `FK_ID_Lieu` (`FK_ID_Lieu`),
  KEY `FK_ID_Spécialiste` (`FK_ID_Spécialiste`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID_User` int NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `MotDePasse` text NOT NULL,
  `Role` text NOT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`ID_User`, `email`, `MotDePasse`, `Role`) VALUES
(1, 'kimi.ho@edu.ece.fr', '1234', 'Patient'),
(2, 'maxence.ribautepicard@edu.ece.fr', '5678', 'Admin'),
(3, 'henri.davroux@edu.ece.fr', '0147', 'Patient'),
(4, 'romain.barriere@edu.ece.fr', '0258', 'Admin'),
(5, 'test@mail', '1234', 'Patient'),
(6, 'test@test', '1234', 'Patient');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
