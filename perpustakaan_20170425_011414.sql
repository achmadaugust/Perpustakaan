-- Valentina Studio --
-- MySQL dump --
-- ---------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- ---------------------------------------------------------


-- CREATE TABLE "buku" -------------------------------------
-- CREATE TABLE "buku" -----------------------------------------
CREATE TABLE `buku` ( 
	`ID_BUKU` Int( 6 ) AUTO_INCREMENT NOT NULL,
	`JUDUL` VarChar( 25 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	`PENULIS` VarChar( 50 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	`Kategori` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	PRIMARY KEY ( `ID_BUKU` ),
	CONSTRAINT `ID_BUKU` UNIQUE( `ID_BUKU` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 1002042;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE TABLE "mahasiswa" --------------------------------
-- CREATE TABLE "mahasiswa" ------------------------------------
CREATE TABLE `mahasiswa` ( 
	`NPM` Int( 255 ) AUTO_INCREMENT NOT NULL,
	`NAMA` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`TGL_LAHIR` Date NOT NULL,
	`ALAMAT` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	PRIMARY KEY ( `NPM` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 1403011;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE TABLE "petugas" ----------------------------------
-- CREATE TABLE "petugas" --------------------------------------
CREATE TABLE `petugas` ( 
	`ID` BigInt( 10 ) AUTO_INCREMENT NOT NULL,
	`NAMA` VarChar( 25 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	`PASSWORD` VarChar( 25 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	PRIMARY KEY ( `ID` ),
	CONSTRAINT `NAMA` UNIQUE( `NAMA` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 15;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE TABLE "pinjam" -----------------------------------
-- CREATE TABLE "pinjam" ---------------------------------------
CREATE TABLE `pinjam` ( 
	`ID_TRANS` Int( 255 ) AUTO_INCREMENT NOT NULL,
	`ID_BUKU` Int( 255 ) NOT NULL,
	`NPM` Int( 255 ) NOT NULL,
	`TGL_DIPINJAM` Date NOT NULL,
	`TGL_KEMBALI` Date NOT NULL,
	`TGL_DIKEMBALIKAN` Date NULL,
	PRIMARY KEY ( `ID_TRANS` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 2;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- Dump data of "buku" -------------------------------------
INSERT INTO `buku`(`ID_BUKU`,`JUDUL`,`PENULIS`,`Kategori`) VALUES ( '1002040', 'Algoritma dan Pemograman', 'Rinaldi Munir', 'Komputer' );
-- ---------------------------------------------------------


-- Dump data of "mahasiswa" --------------------------------
INSERT INTO `mahasiswa`(`NPM`,`NAMA`,`TGL_LAHIR`,`ALAMAT`) VALUES ( '1403005', 'Eva Andrea', '1998-03-15', 'Jakarta' );
INSERT INTO `mahasiswa`(`NPM`,`NAMA`,`TGL_LAHIR`,`ALAMAT`) VALUES ( '1403006', 'Achmad Try A.', '1997-08-03', 'Bandung' );
INSERT INTO `mahasiswa`(`NPM`,`NAMA`,`TGL_LAHIR`,`ALAMAT`) VALUES ( '1403007', 'Agung Deli S.', '1996-05-02', 'Bandung' );
INSERT INTO `mahasiswa`(`NPM`,`NAMA`,`TGL_LAHIR`,`ALAMAT`) VALUES ( '1403008', 'Yusuf Firman', '1996-03-15', 'Bandung' );
-- ---------------------------------------------------------


-- Dump data of "petugas" ----------------------------------
INSERT INTO `petugas`(`ID`,`NAMA`,`PASSWORD`) VALUES ( '5', 'administrator', 'adminadmin' );
INSERT INTO `petugas`(`ID`,`NAMA`,`PASSWORD`) VALUES ( '13', 'administrator1', 'superadmin' );
INSERT INTO `petugas`(`ID`,`NAMA`,`PASSWORD`) VALUES ( '14', 'admin', 'admin' );
-- ---------------------------------------------------------


-- Dump data of "pinjam" -----------------------------------
INSERT INTO `pinjam`(`ID_TRANS`,`ID_BUKU`,`NPM`,`TGL_DIPINJAM`,`TGL_KEMBALI`,`TGL_DIKEMBALIKAN`) VALUES ( '1', '1002040', '1403006', '2015-09-09', '2015-09-12', '2015-09-12' );
INSERT INTO `pinjam`(`ID_TRANS`,`ID_BUKU`,`NPM`,`TGL_DIPINJAM`,`TGL_KEMBALI`,`TGL_DIKEMBALIKAN`) VALUES ( '2', '1002040', '1403005', '2017-02-12', '2017-02-15', '2017-02-15' );
-- ---------------------------------------------------------


-- CREATE INDEX "lnk_buku_pinjam" --------------------------
-- CREATE INDEX "lnk_buku_pinjam" ------------------------------
CREATE INDEX `lnk_buku_pinjam` USING BTREE ON `pinjam`( `ID_BUKU` );
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE INDEX "lnk_mahasiswa_pinjam" ---------------------
-- CREATE INDEX "lnk_mahasiswa_pinjam" -------------------------
CREATE INDEX `lnk_mahasiswa_pinjam` USING BTREE ON `pinjam`( `NPM` );
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE LINK "lnk_buku_pinjam" ---------------------------
-- CREATE LINK "lnk_buku_pinjam" -------------------------------
ALTER TABLE `pinjam`
	ADD CONSTRAINT `lnk_buku_pinjam` FOREIGN KEY ( `ID_BUKU` )
	REFERENCES `buku`( `ID_BUKU` )
	ON DELETE Cascade
	ON UPDATE Cascade;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE LINK "lnk_mahasiswa_pinjam" ----------------------
-- CREATE LINK "lnk_mahasiswa_pinjam" --------------------------
ALTER TABLE `pinjam`
	ADD CONSTRAINT `lnk_mahasiswa_pinjam` FOREIGN KEY ( `NPM` )
	REFERENCES `mahasiswa`( `NPM` )
	ON DELETE Cascade
	ON UPDATE Cascade;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- ---------------------------------------------------------


