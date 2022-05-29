-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2022 at 09:58 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eitea`
--

-- --------------------------------------------------------

--
-- Table structure for table `bangcongthuc`
--

CREATE TABLE `bangcongthuc` (
  `MASP` varchar(50) NOT NULL,
  `MANVL` varchar(11) NOT NULL,
  `SOLUONG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bangcongthuc`
--

INSERT INTO `bangcongthuc` (`MASP`, `MANVL`, `SOLUONG`) VALUES
('ET27183', 'NVL1647823', 12),
('ET27183', 'NVL4271077', 12),
('SP21847', 'NVL1647823', 10),
('SP82787', 'NVL1647823', 3),
('SP82787', 'NVL4271077', 4),
('SP11060', 'NVL2937041', 3),
('SP11060', 'NVL4271077', 3),
('ET27183', 'NVL7124519', 3),
('SP75489', 'NVL2937041', 5),
('SP88512', 'NVL2155106', 3),
('SP88512', 'NVL2937041', 5),
('SP97349', 'NVL8138825', 55),
('SP18747', 'NVL4007349', 2),
('SP18747', 'NVL2155106', 3);

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MAHD` varchar(50) NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MAHD`, `MASP`, `SOLUONG`) VALUES
('HD32067356', 'ET27183', 10),
('HD32067356', 'SP21847', 5),
('HD32067356', 'SP82787', 3),
('HD23138928', 'ET27183', 2),
('HD23138928', 'SP21847', 3),
('HD70365479', 'SP82787', 2),
('HD70365479', 'ET27183', 3),
('HD70365479', 'SP21847', 4),
('HD12622242', 'ET27183', 1),
('HD12622242', 'SP11060', 3),
('HD12622242', 'SP18747', 4),
('HD12622242', 'SP75489', 60),
('HD12622242', 'SP21847', 31),
('HD12622242', 'SP88512', 5),
('HD12622242', 'SP97349', 7),
('HD49583141', 'SP21847', 1),
('HD59100768', 'SP11060', 2),
('HD23787758', 'SP11060', 1),
('HD28325415', 'SP11060', 2);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MAPN` varchar(10) NOT NULL,
  `MANVL` varchar(10) NOT NULL,
  `DONGIANHAP` double NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `DONVITINH` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MAPN`, `MANVL`, `DONGIANHAP`, `SOLUONG`, `DONVITINH`) VALUES
('PN77891496', 'NVL2937041', 22000, 10, 'kg'),
('PN77891496', 'NVL4007349', 9000, 15, 'Bịch'),
('PN43407340', 'NVL7124519', 46000, 50, 'gram'),
('PN50797335', 'NVL7124519', 46000, 50, 'gram'),
('PN50797335', 'NVL8138825', 789, 75, 'gram'),
('PN64356825', 'NVL1647823', 15000, 10, 'gram'),
('PN64356825', 'NVL2155106', 22000, 90, 'ml'),
('PN72368409', 'NVL2937041', 22000, 10, 'gram'),
('PN49599882', 'NVL2937041', 22000, 10, 'gram'),
('PN49599882', 'NVL4271077', 20000, 10, 'gram'),
('PN40180469', 'NVL2155106', 22000, 100, 'ml'),
('PN40180469', 'NVL2937041', 22000, 30, 'gram');

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `MACN` varchar(10) NOT NULL,
  `TENCN` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`MACN`, `TENCN`) VALUES
('CN01', 'Quản lý sản phẩm'),
('CN02', 'Quản lý nhân viên'),
('CN03', 'Bán hàng'),
('CN04', 'Quản lý công thức'),
('CN05', 'Quản lý nguyên vật liệu'),
('CN06', 'Phân quyền'),
('CN07', 'Tạo khuyến mãi'),
('CN08', 'Nhập hàng'),
('CN09', 'Quản lý nhà cung cấp'),
('CN10', 'Thống kê doanh thu');

-- --------------------------------------------------------

--
-- Table structure for table `chucvu`
--

CREATE TABLE `chucvu` (
  `MACV` varchar(10) NOT NULL,
  `TENCV` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chucvu`
--

INSERT INTO `chucvu` (`MACV`, `TENCV`) VALUES
('NV001', 'Nhân viên thu ngân'),
('NV002', 'Nhân viên pha chế'),
('thanos', 'ADMIN'),
('thanos1', 'ADMIN1'),
('TP', 'Trưởng phòng');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MAHD` varchar(20) NOT NULL,
  `MANV` varchar(20) NOT NULL,
  `NGAYTAO` varchar(30) NOT NULL,
  `MAKM` varchar(20) NOT NULL,
  `TONGTIEN` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MAHD`, `MANV`, `NGAYTAO`, `MAKM`, `TONGTIEN`) VALUES
('HD12622242', '231', '03/05/2021', 'Không có', 2966000),
('HD23138928', '231', '03/05/2021', '-50%', 70000),
('HD23787758', '231', '25/11/2021', 'Không có', 15000),
('HD28325415', '231', '25/11/2021', 'Không có', 30000),
('HD32067356', '231', '03/05/2021', 'Không có', 505000),
('HD49583141', '231', '25/11/2021', 'Không có', 30000),
('HD59100768', '231', '25/11/2021', 'Không có', 30000),
('HD70365479', '231', '03/05/2021', 'Không có', 265000);

-- --------------------------------------------------------

--
-- Table structure for table `kho`
--

CREATE TABLE `kho` (
  `MANVL` varchar(10) NOT NULL,
  `MALOAINVL` varchar(10) NOT NULL,
  `TENNVL` varchar(30) NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `DONVITINH` varchar(10) NOT NULL,
  `DONGIANHAP` double NOT NULL,
  `MANCC` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kho`
--

INSERT INTO `kho` (`MANVL`, `MALOAINVL`, `TENNVL`, `SOLUONG`, `DONVITINH`, `DONGIANHAP`, `MANCC`) VALUES
('NVL1647823', 'LNVL876821', 'Trân châu', 80, 'gram', 15000, 'null'),
('NVL2155106', 'LNVL195730', 'Sữa đặc Vinamilk', 20, 'ml', 22000, 'null'),
('NVL2937041', 'LNVL344762', 'Đá bi', 35, 'gram', 22000, 'null'),
('NVL4007349', 'LNVL195730', 'Sữa tươi', 60, 'ml', 9000, 'null'),
('NVL4271077', 'LNVL876821', 'Đường', 75, 'gram', 20000, 'null'),
('NVL7124519', 'LNVL898306', 'Đào ngâm', 400, 'gram', 46000, 'null'),
('NVL8138825', 'LNVL876821', 'Bột năng', 600, 'gram', 789, 'null'),
('NVL9352486', 'LNVL344887', 'Trà sữa không', 7, 'ml', 12000, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MAKM` varchar(10) NOT NULL,
  `TENCTKM` varchar(50) NOT NULL,
  `PHANTRAMKM` float NOT NULL,
  `NGAYBD` varchar(20) NOT NULL,
  `NGAYKT` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`MAKM`, `TENCTKM`, `PHANTRAMKM`, `NGAYBD`, `NGAYKT`) VALUES
('KM20919815', 'tetst', 33, '03/05/2021', '05/05/2021'),
('KM39818758', '6', 22, '04/05/2021', '05/05/2021'),
('KM86181351', '123', 64, '04/05/2021', '01/05/2021'),
('KM86396868', '13213', 11, '03/05/2021', '05/05/2021'),
('KM98752779', 'test', 50, '03/05/2021', '03/05/2021');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MANCC` varchar(10) NOT NULL,
  `TENNCC` varchar(30) NOT NULL,
  `SDT` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MANCC`, `TENNCC`, `SDT`) VALUES
('NCC2826767', 'Jack', '0946168964'),
('NCC6001358', 'Bill', '09003615936'),
('NCC7376140', 'Emma', '0919463412'),
('NCC8853644', 'dd', '0907666333');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MANV` varchar(10) NOT NULL,
  `MACV` varchar(10) NOT NULL,
  `HOTEN` varchar(30) NOT NULL,
  `GIOITINH` varchar(10) NOT NULL,
  `SDT` varchar(255) NOT NULL,
  `NGAYSINH` varchar(30) NOT NULL,
  `MATKHAU` varchar(255) NOT NULL,
  `TRANGTHAI` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MANV`, `MACV`, `HOTEN`, `GIOITINH`, `SDT`, `NGAYSINH`, `MATKHAU`, `TRANGTHAI`) VALUES
('231', 'thanos', 'Tuan', '0', '0123456389', '23/09/2000', '123456', '1'),
('LMAO', 'TP', 'Trường', '1', '0903615936', '24/08/2000', '123456', '0'),
('NV36426102', 'NV002', 'Vinh', '0', '0932911111', '07/11/2000', '123456789', '1'),
('NV91274580', 'thanos1', 'Trực', '0', '0932961035', '22/11/2000', '123456789', '1'),
('siapa', 'NV001', 'Trieu', '0', '0123456789', '27/02/2000', '123456', '1');

-- --------------------------------------------------------

--
-- Table structure for table `phanloainvl`
--

CREATE TABLE `phanloainvl` (
  `MALOAINVL` varchar(10) NOT NULL,
  `TENLOAINVL` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phanloainvl`
--

INSERT INTO `phanloainvl` (`MALOAINVL`, `TENLOAINVL`) VALUES
('LNVL195730', 'Sữa'),
('LNVL344762', 'Khác'),
('LNVL344887', 'Trà'),
('LNVL876821', 'Bột'),
('LNVL898306', 'Trái cây');

-- --------------------------------------------------------

--
-- Table structure for table `phanloaisp`
--

CREATE TABLE `phanloaisp` (
  `MALOAISP` varchar(10) NOT NULL,
  `TENLOAISP` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phanloaisp`
--

INSERT INTO `phanloaisp` (`MALOAISP`, `TENLOAISP`) VALUES
('LSP13737', 'Đá xay'),
('LSP83272', 'Trà trái cây'),
('LSP98931', 'CAFE');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MAPN` varchar(10) NOT NULL,
  `MANV` varchar(10) NOT NULL,
  `MANCC` varchar(10) NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`MAPN`, `MANV`, `MANCC`, `NGAYNHAP`, `TONGTIEN`) VALUES
('PN40180469', '231', 'NCC6001358', '2021-11-25', 2860000),
('PN43407340', '231', 'NCC6001358', '2021-11-25', 2300000),
('PN49599882', '231', 'NCC7376140', '2021-11-25', 420000),
('PN50797335', '231', 'NCC7376140', '2021-11-25', 2359175),
('PN64356825', '231', 'NCC2826767', '2021-11-25', 2130000),
('PN72368409', '231', 'NCC6001358', '2021-11-25', 220000),
('PN77891496', '231', 'NCC7376140', '2021-11-25', 355000);

-- --------------------------------------------------------

--
-- Table structure for table `quyen_chucnang`
--

CREATE TABLE `quyen_chucnang` (
  `MACN` varchar(10) NOT NULL,
  `MACV` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `quyen_chucnang`
--

INSERT INTO `quyen_chucnang` (`MACN`, `MACV`) VALUES
('CN03', 'NV001'),
('CN01', 'NV002'),
('CN04', 'NV002'),
('CN05', 'NV002'),
('CN01', 'thanos'),
('CN02', 'thanos'),
('CN03', 'thanos'),
('CN04', 'thanos'),
('CN05', 'thanos'),
('CN06', 'thanos'),
('CN07', 'thanos'),
('CN08', 'thanos'),
('CN09', 'thanos'),
('CN10', 'thanos'),
('CN07', 'TP'),
('CN08', 'TP'),
('CN09', 'TP'),
('CN02', 'thanos1'),
('CN06', 'thanos1'),
('CN10', 'thanos1');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MASP` varchar(40) NOT NULL,
  `TENSP` varchar(40) NOT NULL,
  `MALOAISP` varchar(10) NOT NULL,
  `GIABAN` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MASP`, `TENSP`, `MALOAISP`, `GIABAN`) VALUES
('ET27183', 'Trà đào', 'LSP83272', 25000),
('SP11060', 'Cafe Đá', 'LSP98931', 15000),
('SP18747', 'Bạc xỉu', 'LSP98931', 22000),
('SP21847', 'Trà dâu', 'LSP83272', 30000),
('SP75489', 'Đá xay trà xanh', 'LSP13737', 27000),
('SP82787', 'Kiwi đá xay', 'LSP13737', 35000),
('SP88512', 'Cafe Sữa', 'LSP98931', 18000),
('SP97349', 'Trà lài', 'LSP83272', 24000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bangcongthuc`
--
ALTER TABLE `bangcongthuc`
  ADD KEY `MASP` (`MASP`);

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `MASP` (`MASP`),
  ADD KEY `MAHD` (`MAHD`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `MANVL` (`MANVL`),
  ADD KEY `MAPN` (`MAPN`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`MACN`);

--
-- Indexes for table `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`MACV`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MAHD`),
  ADD KEY `MANV` (`MANV`),
  ADD KEY `MAKM` (`MAKM`);

--
-- Indexes for table `kho`
--
ALTER TABLE `kho`
  ADD PRIMARY KEY (`MANVL`),
  ADD KEY `MALOAINVL` (`MALOAINVL`),
  ADD KEY `MANCC` (`MANCC`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MAKM`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MANCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`,`SDT`),
  ADD KEY `MACV` (`MACV`);

--
-- Indexes for table `phanloainvl`
--
ALTER TABLE `phanloainvl`
  ADD PRIMARY KEY (`MALOAINVL`);

--
-- Indexes for table `phanloaisp`
--
ALTER TABLE `phanloaisp`
  ADD PRIMARY KEY (`MALOAISP`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MAPN`),
  ADD KEY `MANV` (`MANV`),
  ADD KEY `MANCC` (`MANCC`);

--
-- Indexes for table `quyen_chucnang`
--
ALTER TABLE `quyen_chucnang`
  ADD KEY `MACN` (`MACN`),
  ADD KEY `MANV` (`MACV`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`),
  ADD KEY `MALOAISP` (`MALOAISP`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`);

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MANVL`) REFERENCES `kho` (`MANVL`);

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MANCC`) REFERENCES `nhacungcap` (`MANCC`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
