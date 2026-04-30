-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2026 at 05:00 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2411500025`
--

-- --------------------------------------------------------

--
-- Table structure for table `tpendaftaran`
--

CREATE TABLE `tpendaftaran` (
  `id` int(11) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `tempat_lahir` varchar(50) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `nomor_wa` varchar(20) NOT NULL,
  `asal_sekolah` varchar(100) NOT NULL,
  `alamat_lengkap` text NOT NULL,
  `waktu_daftar` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tpengumuman`
--

CREATE TABLE `tpengumuman` (
  `ID_PENG` int(5) NOT NULL,
  `CJDL_PENG` varchar(160) NOT NULL,
  `DPOST` date NOT NULL,
  `DTDKPOST` date NOT NULL,
  `CFOTO` varchar(250) NOT NULL,
  `CDESKRIPSI` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tpengumuman`
--

INSERT INTO `tpengumuman` (`ID_PENG`, `CJDL_PENG`, `DPOST`, `DTDKPOST`, `CFOTO`, `CDESKRIPSI`) VALUES
(1, 'Jangan Lupa Cuci Tangan', '2023-10-01', '2026-12-31', 'http://127.0.0.1/webmobisuts_1811500212/gambar_pengumuman/PENGUMUMAN_1.jpg', 'Adek-adek yang manis-manis, jangan lupa cuci tangan ya sebelum makan.\r\n\r\nApalagi jika baru pipis dan buang air besar dari toilet'),
(2, 'Ini Budi ini Bapak Budi ini Ibu Budi', '2021-12-03', '2026-12-21', 'http://127.0.0.1/webmobisuts_1811500212/gambar_pengumuman/PENGUMUMAN_2.jpg', 'Budi bermain bola. Bapak nonton televisi. Ibu masak di dapur'),
(3, 'Penerimaan Siswa Baru 2024/2025 Dibuka!', '2023-10-01', '2026-09-01', 'http://127.0.0.1/webmobisuts_1811500212/gambar_pengumuman/PENGUMUMAN_3.jpg', 'Penerimaan siswa baru di SMA Entah Ade Entah Dak sudah dibuka untuk tahun ajar 2024/2025! Jangan lupa untuk mendaftar ya!'),
(4, 'Hasil Seleksi Penerimaan Siswa Baru 2023/2024', '2023-09-01', '2026-11-30', 'http://127.0.0.1/webmobisuts_1811500212/gambar_pengumuman/PENGUMUMAN_4.jpg', 'Berikut adalah siswa yang lulus seleksi penerimaan siswa baru 2023/2024:\r\n1. Paijo\r\n2. Ani\r\n3. Budi\r\n4. Yosef');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tpendaftaran`
--
ALTER TABLE `tpendaftaran`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tpengumuman`
--
ALTER TABLE `tpengumuman`
  ADD PRIMARY KEY (`ID_PENG`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tpendaftaran`
--
ALTER TABLE `tpendaftaran`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tpengumuman`
--
ALTER TABLE `tpengumuman`
  MODIFY `ID_PENG` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
