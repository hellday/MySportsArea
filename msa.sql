-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 25 Novembre 2016 à 14:13
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `msa`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `idAdmin` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(255) NOT NULL,
  `logAdmin` varchar(255) NOT NULL,
  `mdpAdmin` varchar(255) NOT NULL,
  PRIMARY KEY (`idAdmin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`idAdmin`, `pseudo`, `logAdmin`, `mdpAdmin`) VALUES
(1, 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `idCommentaire` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idUser` int(10) unsigned NOT NULL,
  `contenu` text,
  `idLieux` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idCommentaire`,`idUser`),
  KEY `Commentaire_FKIndex1` (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `commentaire`
--

INSERT INTO `commentaire` (`idCommentaire`, `idUser`, `contenu`, `idLieux`) VALUES
(1, 1, 'Bon matériel sportif récent avec un personnel à l''écoute.', 1),
(2, 2, 'Déçu du forfait que j''ai acheté, je m''attendais à plus de contenu !', 2);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Lieux_Sport_idSport` int(10) unsigned NOT NULL,
  `Lieux_idLieux` int(10) unsigned NOT NULL,
  `nomEvent` varchar(255) DEFAULT NULL,
  `dateEvent` date DEFAULT NULL,
  `descriptionEvent` text,
  `typeEvent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEvent`,`Lieux_Sport_idSport`,`Lieux_idLieux`),
  KEY `Event_FKIndex1` (`Lieux_idLieux`,`Lieux_Sport_idSport`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `event`
--

INSERT INTO `event` (`idEvent`, `Lieux_Sport_idSport`, `Lieux_idLieux`, `nomEvent`, `dateEvent`, `descriptionEvent`, `typeEvent`) VALUES
(1, 2, 1, 'Tennis Championship', '2016-12-15', 'Venez défier vos amis ou ennemis à notre concours de Tennis. Récompense à la clé !', 'Concours Tennis'),
(2, 1, 2, 'Foot By Night', '2017-01-11', 'Quel joueur de football n''a jamais rêvé de jouer de nuit ? Formez votre équipe et remportez la coupe !', 'Football');

-- --------------------------------------------------------

--
-- Structure de la table `lieux`
--

CREATE TABLE IF NOT EXISTS `lieux` (
  `idLieux` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Sport_idSport` int(10) unsigned NOT NULL,
  `nomLieux` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `typeLieux` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idLieux`,`Sport_idSport`),
  KEY `Lieux_FKIndex1` (`Sport_idSport`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `lieux`
--

INSERT INTO `lieux` (`idLieux`, `Sport_idSport`, `nomLieux`, `latitude`, `longitude`, `typeLieux`) VALUES
(1, 1, 'Beaugrenelle Sports', 48.2, 70, 'Stade'),
(2, 2, 'La Pampa', 77.6, 64.2, 'Gymnase'),
(3, 3, 'SportsLand', 45.2, 65.8, 'Gymnase'),
(4, 4, 'HelloWorld', 75.2, 93.2, 'Stade'),
(5, 5, 'La Gaz', 45.1, 63.7, 'Parc'),
(6, 6, 'Cheekyto', 65.1, 47.2, 'Parc');

-- --------------------------------------------------------

--
-- Structure de la table `partenaire`
--

CREATE TABLE IF NOT EXISTS `partenaire` (
  `idPartenaire` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Utilisateur_idUser` int(10) unsigned NOT NULL,
  `idSport` varchar(255) DEFAULT NULL,
  `préférences` varchar(255) DEFAULT NULL,
  `disponibilités` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPartenaire`,`Utilisateur_idUser`),
  KEY `Partenaire_FKIndex1` (`Utilisateur_idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `partenaire`
--

INSERT INTO `partenaire` (`idPartenaire`, `Utilisateur_idUser`, `idSport`, `préférences`, `disponibilités`) VALUES
(1, 1, '1, 2, 4', '1', 'lundi, mardi, jeudi'),
(2, 2, '1, 5, 6', '6', 'samedi, dimanche');

-- --------------------------------------------------------

--
-- Structure de la table `sport`
--

CREATE TABLE IF NOT EXISTS `sport` (
  `idSport` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomSport` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idSport`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `sport`
--

INSERT INTO `sport` (`idSport`, `nomSport`) VALUES
(1, 'football'),
(2, 'tennis'),
(3, 'badmington'),
(4, 'ping-pong'),
(5, 'basketball'),
(6, 'baseball'),
(7, 'volleyball'),
(8, 'hockey'),
(9, 'skateboarding');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUser` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomUser` varchar(255) DEFAULT NULL,
  `PrenomUser` varchar(255) DEFAULT NULL,
  `Login` varchar(255) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  `AdresseUser` varchar(255) DEFAULT NULL,
  `TelUser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUser`, `nomUser`, `PrenomUser`, `Login`, `mdp`, `AdresseUser`, `TelUser`) VALUES
(1, 'Grosso', 'Terry', 'terry', 'grosso', '3, Villa saint charles', '0659281435'),
(2, 'Olivier', 'Nicolas', 'nicolas', 'olivier', '2, rue jouvenet', '0601457895'),
(3, 'Lagaz', 'Germain', 'germain', 'lagaz', '5, rue de la grosse gaz', '0657489652'),
(4, 'Yamyam', 'Sophie', 'sophie', 'yamyam', '6, rue du chill', '0648752547'),
(5, 'Clémenceau', 'Jean', 'jean', 'clemenceau', '3, rue de la patinoire', '0645789562');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
