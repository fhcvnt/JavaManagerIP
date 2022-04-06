USE ITManagerIP
GO

/*
CREATE TABLE IT_PhanCongXuLyCongViec(
	MaCongViec VARCHAR(10) UNIQUE NOT NULL, -- tự động tạo khi thêm mới, CV10000001
	Ngay DATE DEFAULT GETDATE() NOT NULL,
	DonVi NVARCHAR(50) DEFAULT '' NOT NULL,
	SoThe VARCHAR(6) NOT NULL,
	HoTen NVARCHAR(50) DEFAULT '' NULL,
	TinhTrang NVARCHAR(128) DEFAULT '' NULL,
	NguyenNhan NVARCHAR(64) DEFAULT '' NULL,
    XuLy NVARCHAR(128) DEFAULT '' NULL,
	NgayHoanThanh DATE DEFAULT '19900101',
	KetQua NVARCHAR(24) DEFAULT '' NULL,
	NguoiXuLy NVARCHAR(50) DEFAULT '' NULL,
	NguoiPhanCong VARCHAR(6) DEFAULT '' NOT NULL
)
*/
GO


CREATE TABLE IT_PhanCongXuLyCongViec2(
	MaCongViec VARCHAR(10) UNIQUE NOT NULL, -- tự động tạo khi thêm mới, CV10000001
	Ngay DATETIME DEFAULT GETDATE() NOT NULL,
	DonVi NVARCHAR(50) DEFAULT '' NOT NULL,
	SoThe VARCHAR(6) NOT NULL,
	HoTen NVARCHAR(50) DEFAULT '' NULL,
	TinhTrang NVARCHAR(128) DEFAULT '' NULL,
	NguyenNhan NVARCHAR(64) DEFAULT '' NULL,
    XuLy NVARCHAR(128) DEFAULT '' NULL,
	NgayHoanThanh DATETIME NULL,
	KetQua NVARCHAR(24) DEFAULT '' NULL,
	NguoiXuLy NVARCHAR(50) DEFAULT '' NULL,
	NguoiPhanCong VARCHAR(6) DEFAULT '' NOT NULL
)

CREATE TABLE IT_SlideShowPhanCongXuLyCongViec(
	MaCongViec VARCHAR(10) NOT NULL,
	ThoiGianCapNhat DATETIME DEFAULT GETDATE() NOT NULL
)

GO

-- hiển thị thông báo cho người xử lý sau khi được thêm công việc
CREATE TABLE IT_HienThiThongBao(
	MaCongViec VARCHAR(10) UNIQUE NOT NULL,
	TrangThai BIT DEFAULT 0
)

GO


CREATE TABLE IT_DanhSachThietBiMayTinh(
	MaThietBiMayTinh VARCHAR(9) UNIQUE NOT NULL,
	LoaiThietBi VARCHAR(30) DEFAULT '' NOT NULL, -- DDR2, DDR3, LCD 17", LCD 18.5", LCD 21", 1GB, 2GB, 4GB, 8GB, 16GB, 32GB, 150GB, 240GB, 250GB, 500GB, 1TB, 2TB, 3TB, 4TB, 6TB, 8T, 12TB,....
	TenLoaiThietBi VARCHAR(16) NOT NULL -- RAM, RAM Capacity, Monitor, CPU, Hard Drive,Print,Mainboard, dùng cho combobox LyLichMayTinh chọn loại
)
GO

CREATE TABLE IT_LyLichMayTinh(
	MaLyLich VARCHAR(10) UNIQUE NOT NULL, -- tự động tạo khi thêm mới, LL10000001
	DonVi NVARCHAR(50) DEFAULT '' NULL,
	MaSoDonVi VARCHAR(12) DEFAULT'' NULL,
	CPU_Loai VARCHAR(30) DEFAULT '' NULL,
	CPU_NgaySuDung DATE DEFAULT '19900101' NULL,
	Mainboard_Loai VARCHAR(30) DEFAULT '' NULL,
	Mainboard_NgaySuDung DATE DEFAULT '19900101' NULL,
	OCung VARCHAR(30) DEFAULT '' NULL,
	RAM_Loai VARCHAR(30) DEFAULT '' NULL,
 	RAM_DungLuong VARCHAR(6) DEFAULT '' NULL,
	RAM_SoLuong SMALLINT DEFAULT 0 NULL,
	ManHinh_Loai VARCHAR(30) DEFAULT '' NULL,
	ManHinh_NgaySuDung DATE DEFAULT '19900101' NULL,
	UPS_Loai VARCHAR(30) DEFAULT '' NULL,
	UPS_NgaySuDung DATE DEFAULT '19900101' NULL,
	MayIn_Loai VARCHAR(30) DEFAULT '' NULL,
	MayIn_NgaySuDung DATE DEFAULT '19900101' NULL,
	NgaySuDung DATE DEFAULT '19900101' NULL,
	ThoiGianBaoHanh SMALLINT DEFAULT 0 NULL,-- tính theo ngày
	NgayHetHanBaoHanh DATE DEFAULT '19900101' NULL,-- ngày hết hạn bảo hành
	NguoiSuDung NVARCHAR(50) DEFAULT '' NULL,
	SoThe VARCHAR(6) DEFAULT '' NULL,
	NguoiCapNhat VARCHAR(6) DEFAULT '' NULL
)
GO

GO

/**
CREATE TABLE IT_ThietBi(
	-- Máy vi tính, màn hình, phím chuột, nẹp sàn, nẹp vuông ....
	MaThietBi VARCHAR(12) PRIMARY KEY NOT NULL, 
	TenThietBi NVARCHAR(64) NOT NULL,
	QuyCach NVARCHAR(12) NOT NULL, -- thùng, hộp, ống, cái, mét, kilogam
	VatTuTieuHao BIT DEFAULT 1, -- loại vật tư, 0- là vật tư bình thường định mức như cpu, ram,...1: là vật tư tiêu hao như dây mạng...
)
**/
GO

CREATE TABLE IT_FileDinhKem(
	SoPhieuBPM VARCHAR(20) UNIQUE NOT NULL,
	TenFile NVARCHAR(128) DEFAULT '',-- tên file của file đính kèm
	FileDinhKem VARBINARY(MAX) NULL
)
GO

--drop table IT_FileDinhKem

/*
CREATE TABLE IT_MuaThietBi(
	-- Máy vi tính, màn hình, phím chuột, nẹp sàn, nẹp vuông ....
	ID VARCHAR(9) PRIMARY KEY NOT NULL, -- -- tự tạo bằng code: M10000001
	SoPhieuBPM VARCHAR(13) NOT NULL,
	TenThietBi NVARCHAR(70) NOT NULL,
	SoLuong SMALLINT NOT NULL,
	NgayMua DATE DEFAULT GETDATE() NOT NULL,
	ThoiGianBaoHanh SMALLINT DEFAULT 0, -- tinh theo don vi la ngay
	NgayNhapKhoIT DATE DEFAULT '19900101', -- Sau khi nhập kho IT thì thêm vào bảng sử dụng tùy vào vật tư tiêu hao hay bình thường
	DaNhapKhoIT BIT DEFAULT 0 NOT NULL,-- 0: chưa nhập kho IT, 1 đã nhập kho IT
	GhiChu NVARCHAR(255) DEFAULT '',
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
*/

	CREATE TABLE IT_MuaThietBiIT(
	-- Máy vi tính, màn hình, phím chuột, nẹp sàn, nẹp vuông ....
	ID VARCHAR(9) PRIMARY KEY NOT NULL, -- -- tự tạo bằng code: M10000001
	SoPhieuBPM VARCHAR(20) NOT NULL,
	TenThietBi NVARCHAR(80) NOT NULL,
	SoLuong SMALLINT NOT NULL,
	NgayMua DATE DEFAULT GETDATE() NOT NULL,
	NgayDongPhieu DATE NULL, -- ngày thu mua đóng phiếu
	NgayNhapKhoIT DATE DEFAULT '19900101', 
	DaNhapKhoIT BIT DEFAULT 0 NOT NULL,-- 0: chưa nhập kho IT, 1 đã nhập kho IT
	GhiChu NVARCHAR(255) DEFAULT '',
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)



GO

CREATE TABLE IT_SuDungTieuHao(
	-- nẹp sàn, nẹp vuông, dây mạng, đầu bấm mạng ....
	ID VARCHAR(12) UNIQUE NOT NULL, -- -- tự tạo bằng code: U202105221
	MaThietBi VARCHAR(12) FOREIGN KEY (MaThietBi) REFERENCES dbo.IT_ThietBi(MaThietBi) NOT NULL,
	SoLuong SMALLINT NOT NULL, -- tính toán cộng dồn vào
	GhiChu NVARCHAR(255) DEFAULT ''
)
GO

CREATE TABLE IT_BanGiaoThietBi(
	ID VARCHAR(12) UNIQUE NOT NULL,
	LoaiBanGiao BIT DEFAULT 0, --0 bàn giao về cho IT, 1: IT bàn giao cho đơn vị khác
	SoThe VARCHAR(6) NOT NULL, --người bàn giao
	HoTen NVARCHAR(50) NOT NULL,
	DonVi NVARCHAR(80) NOT NULL,
	SoLuong SMALLINT NOT NULL, -- tính toán cộng dồn vào
	GhiChu NVARCHAR(255) DEFAULT '',
	NgayBanGiao DATE DEFAULT GETDATE(),
	NhanVienIT VARCHAR(6), -- là số thẻ của nhân viên IT, nếu bàn giao về cho IT thì là người nhận, ngược lại là người phát
	TenFile NVARCHAR(64) DEFAULT '',-- tên file của file đính kèm của phiếu đề nghị
	FileDinhKem VARBINARY(MAX)
)
GO


-- sử dụng, phân phát
CREATE TABLE IT_SuDungPhanPhat(
	-- Máy vi tính, màn hình, phím chuột ....
	ID VARCHAR(12) UNIQUE NOT NULL,-- là ID của MuaThietBi hoặc của đơn vị khác bàn giao về cho IT
	MaThietBi VARCHAR(12) FOREIGN KEY (MaThietBi) REFERENCES dbo.IT_ThietBi(MaThietBi) NOT NULL,
	SoLuong SMALLINT NOT NULL, -- tính toán cộng dồn vào
	GhiChu NVARCHAR(255) DEFAULT ''
)
GO

-- Phát thiết bị cho nhân viên công ty
CREATE TABLE IT_PhatThietBi(
	PhanBiet VARCHAR(12) UNIQUE NOT NULL,-- phân biệt dòng
	SoThe VARCHAR(6) NOT NULL,
	HoTen NVARCHAR(50) NOT NULL,
	DonVi NVARCHAR(50) NOT NULL,
	ID VARCHAR(12) NOT NULL,-- là ID của IT_MuaThietBi hoặc của đơn vị khác bàn giao về cho IT
	SoLuong SMALLINT NOT NULL, -- tính toán cộng dồn vào
	GhiChu NVARCHAR(255) DEFAULT '',
	NgayLanhVatTu DATE DEFAULT GETDATE(),
	NguoiPhatVatTu VARCHAR(6) NOT NULL -- là số thẻ của nhân viên IT đã phát vật tư
)
GO

-- thiết bị cho mượn
CREATE TABLE IT_ThietBiChoMuon(
	-- Máy vi tính, màn hình, phím chuột, camera, máy chiếu...
	MaThietBi VARCHAR(12) UNIQUE NOT NULL, 
	TenThietBi NVARCHAR(40) NOT NULL,
	TrangThai TINYINT DEFAULT 1, -- 0 Đã hư,1: Bình thường, 2 Bị mất
	LoaiThietBi TINYINT DEFAULT 0, -- 0 Linh kiện (Accessories),1 Máy ảnh (Camera),2 Máy ảnh Yeezy (Camera), 3 Phòng họp (Meeting room), 4 Băng đeo tay, 5 Băng đeo tay Yeezy, 6 Laptop
	GhiChu NVARCHAR(255) DEFAULT '',
	DaMuon BIT DEFAULT 0 NOT NULL -- 0: chưa mượn, 1 đã mượn
)
GO

-- nhân viên được mượn máy ảnh Yeezy
CREATE TABLE IT_NhanVienMuonCameraYeezy(
	SoThe VARCHAR(6) UNIQUE NOT NULL, 
	HoTen NVARCHAR(50) NOT NULL,
	DonVi NVARCHAR(50) NOT NULL,
	MaTheTu VARCHAR(20) NULL
)
GO

--cho mượn thiết bị theo loại thiết bị: 0 Linh kiện (Accessories),1 Máy ảnh (Camera),2 Máy ảnh Yeezy (Camera), 3 Phòng họp (Meeting room), 4 Băng đeo tay, 5 Băng đeo tay Yeezy, 6 Laptop
CREATE TABLE IT_ChoMuonThietBiMayAnh(
	-- Máy vi tính, màn hình, phím chuột, camera, máy chiếu...
	MaMuonThietBi VARCHAR(12) UNIQUE NOT NULL, -- tự tạo bằng code: B10000001
	SoThe VARCHAR(6) NOT NULL, -- người mượn
	HoTen NVARCHAR(32) NOT NULL,
	DonVi NVARCHAR(50) NOT NULL,
	MaThietBi VARCHAR(12) NOT NULL,-- thiết bị mượn
	Serial VARCHAR(9) NULL,
	TrangThaiSauMuon TINYINT DEFAULT 10 NULL, -- 0 Đã hư,1: Bình thường, 2 Bị mất
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiChoMuon VARCHAR(6) NOT NULL, -- số thẻ nhân viên IT cho mượn thiết bị
	NguoiNhanTra VARCHAR(6) NULL,-- số thẻ nhân viên IT nhận trả thiết bị
	ThoiGianMuon DATETIME DEFAULT GETDATE() NOT NULL,
	ThoiGianTra DATETIME NULL,
	HoTenTra NVARCHAR(32) NULL,
	SoTheTra VARCHAR(6) NULL, -- người trả
	DonViTra NVARCHAR(50) NULL,
)
GO

--cho mượn thiết bị theo loại thiết bị: 0 Linh kiện (Accessories),1 Máy ảnh (Camera),2 Máy ảnh Yeezy (Camera), 3 Phòng họp (Meeting room), 4 Băng đeo tay, 5 Băng đeo tay Yeezy, 6 Laptop
CREATE TABLE IT_ChoMuonThietBiPhongHop(
	-- Máy vi tính, màn hình, phím chuột, camera, máy chiếu...
	MaMuonThietBi VARCHAR(12) UNIQUE NOT NULL, -- tự tạo bằng code: B10000001
	SoThe VARCHAR(6) NOT NULL, -- người mượn
	HoTen NVARCHAR(32) NOT NULL,
	DonVi NVARCHAR(50) NOT NULL,
	MaThietBi VARCHAR(12) NOT NULL,-- thiết bị mượn
	TrangThaiSauMuon TINYINT DEFAULT 10 NULL, -- 0 Đã hư,1: Bình thường, 2 Bị mất
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiChoMuon VARCHAR(6) NOT NULL, -- số thẻ nhân viên IT cho mượn thiết bị
	NguoiNhanTra VARCHAR(6) NULL,-- số thẻ nhân viên IT nhận trả thiết bị
	ThoiGianMuon DATETIME DEFAULT GETDATE() NOT NULL,
	ThoiGianTra DATETIME NULL,
	HoTenTra NVARCHAR(32) NULL,
	SoTheTra VARCHAR(6) NULL, -- người trả
	DonViTra NVARCHAR(50) NULL,
)
GO

--cho mượn thiết bị theo loại thiết bị: 0 Linh kiện (Accessories),1 Máy ảnh (Camera),2 Máy ảnh Yeezy (Camera), 3 Phòng họp (Meeting room), 4 Băng đeo tay, 5 Băng đeo tay Yeezy, 6 Laptop
CREATE TABLE IT_ChoMuonThietBiLinhKien(
	-- Máy vi tính, màn hình, phím chuột, camera, máy chiếu...
	MaMuonThietBi VARCHAR(12) UNIQUE NOT NULL, -- tự tạo bằng code: B10000001
	SoThe VARCHAR(6) NOT NULL, -- người mượn
	HoTen NVARCHAR(32) NOT NULL,
	DonVi NVARCHAR(50) NOT NULL,
	MaThietBi VARCHAR(12) NOT NULL,-- thiết bị mượn
	TrangThaiSauMuon TINYINT DEFAULT 10 NULL, -- 0 Đã hư,1: Bình thường, 2 Bị mất
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiChoMuon VARCHAR(6) NOT NULL, -- số thẻ nhân viên IT cho mượn thiết bị
	NguoiNhanTra VARCHAR(6) NULL,-- số thẻ nhân viên IT nhận trả thiết bị
	ThoiGianMuon DATETIME DEFAULT GETDATE() NOT NULL,
	ThoiGianTra DATETIME NULL,
	HoTenTra NVARCHAR(32) NULL,
	SoTheTra VARCHAR(6) NULL, -- người trả
	DonViTra NVARCHAR(50) NULL,
)
GO

--cho mượn thiết bị theo loại thiết bị: 0 Linh kiện (Accessories),1 Máy ảnh (Camera),2 Máy ảnh Yeezy (Camera), 3 Phòng họp (Meeting room), 4 Băng đeo tay, 5 Băng đeo tay Yeezy, 6 Laptop
CREATE TABLE IT_ChoMuonThietBiLaptop(
	-- Máy vi tính, màn hình, phím chuột, camera, máy chiếu...
	MaMuonThietBi VARCHAR(12) UNIQUE NOT NULL, -- tự tạo bằng code: B10000001
	SoThe VARCHAR(6) NOT NULL, -- người mượn
	HoTen NVARCHAR(32) NOT NULL,
	DonVi NVARCHAR(50) NOT NULL,
	MaThietBi VARCHAR(12) NOT NULL,-- thiết bị mượn
	TrangThaiSauMuon TINYINT DEFAULT 10 NULL, -- 0 Đã hư,1: Bình thường, 2 Bị mất
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiChoMuon VARCHAR(6) NOT NULL, -- số thẻ nhân viên IT cho mượn thiết bị
	NguoiNhanTra VARCHAR(6) NULL,-- số thẻ nhân viên IT nhận trả thiết bị
	ThoiGianMuon DATETIME DEFAULT GETDATE() NOT NULL,
	ThoiGianTra DATETIME NULL,
	HoTenTra NVARCHAR(32) NULL,
	SoTheTra VARCHAR(6) NULL, -- người trả
	DonViTra NVARCHAR(50) NULL,
)



-- phần báo phế
-- DANH SÁCH THIẾT BỊ HƯ

CREATE TABLE BP_DSThietBiHu(
	MaThietBiHu VARCHAR(10) UNIQUE NOT NULL, -- tự tạo bằng code: BP10000001
	TenThietBiHu NVARCHAR(80) NOT NULL,
	DonViTinh NVARCHAR(10) NOT NULL, -- cái, kg, con, thùng, hộp, bịch, ...
	SoLuong SMALLINT DEFAULT 1 NOT NULL,
	SoThe VARCHAR(6) NULL,
	HoTen NVARCHAR(50) NULL,
	DonVi NVARCHAR(50) DEFAULT '' NOT NULL,
	NgayTrangBi VARCHAR(10) NULL, -- ngày trang bị, cho phép chỉ nhập năm
	NgayBiHu VARCHAR(10) NOT NULL, -- ngày bị hư, cho phép nhập năm
	NguoiNhan NVARCHAR(50) DEFAULT '' NULL, -- người nhận thiết bị hư
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiCapNhat VARCHAR(6) NULL, -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
	MaTepDinhKem VARCHAR(10) NULL, -- tự tạo bằng code: DK10000001
)

CREATE TABLE BP_DSThietBiHu_TepDinhKem(
	MaTepDinhKem VARCHAR(10) UNIQUE NOT NULL, -- tự tạo bằng code: DK10000001
	TenFile NVARCHAR(255) DEFAULT '' NOT NULL,-- tên file của file đính kèm
	FileDinhKem VARBINARY(MAX) NULL
)



-- SỬA BẢNG NguoiDung
--ALTER TABLE NguoiDung
--ALTER COLUMN TenNhom VARCHAR(9);

-- báo phế

CREATE TABLE BP_BaoPhe(
	-- Máy vi tính, màn hình, phím chuột, nẹp sàn, nẹp vuông ....
	ID VARCHAR(10) UNIQUE NOT NULL, -- -- tự tạo bằng code: BP10000001
	NgayBaoPhe DATE DEFAULT GETDATE() NOT NULL,
	TenFile NVARCHAR(128) DEFAULT '' NOT NULL,-- tên file của file đính kèm
	FileDinhKem VARBINARY(MAX) NULL,
	GhiChu NVARCHAR(255) DEFAULT '',
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)

-- DANH SÁCH thay thế linh kiện
CREATE TABLE LK_ThayTheLinhKien(
	MaThayThe VARCHAR(10) UNIQUE NOT NULL, -- tự tạo bằng code: TT10000001
	SoThe VARCHAR(6) NULL,
	HoTen NVARCHAR(50) NULL,
	DonVi NVARCHAR(50) DEFAULT '' NOT NULL,
	TenLinhKienHu NVARCHAR(80) NOT NULL,
	NgayBiHu Date NOT NULL, 
	TenLinhKienMoi NVARCHAR(80) NULL,
	NgayMua Date NULL,
	SoBPM VARCHAR(20) NULL,
	NgayThayThe Date NULL, 
	NguoiThay NVARCHAR(50) NULL,-- nguoi thay the linh kien bi hu
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiCapNhat VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)

-- Linh kiện
-- Mua thiết bị mới cho các đơn vị khác
CREATE TABLE LK_MuaLinhKien(
	MaMuaThietBi VARCHAR(10) UNIQUE NOT NULL, -- tự tạo bằng code: LK10000001
	SoBPM VARCHAR(20) NOT NULL,
	NoiDung NVARCHAR(80) NOT NULL,
	SoLuong SMALLINT DEFAULT 1 NOT NULL,
	SoThe VARCHAR(6) NULL,
	HoTen NVARCHAR(50) NULL,
	DonVi NVARCHAR(50) DEFAULT '' NOT NULL,
	NgayLamPhieu Date NOT NULL, 
	NgayTongVuNhanPhieu Date NULL, 
	NgayVe Date NULL,
	NgayThayThe Date NULL,
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiCapNhat VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)

-- sửa thiết bị
CREATE TABLE LK_SuaThietBi(
	MaSuaThietBi VARCHAR(10) UNIQUE NOT NULL, -- tự tạo bằng code: TB10000001
	SoBPM VARCHAR(20) NULL,
	SoThe VARCHAR(6) NOT NULL, -- người đem thiết bị lên IT
	HoTen NVARCHAR(50) NOT NULL,
	DonVi NVARCHAR(50) NOT NULL,
	NoiDung NVARCHAR(80) NOT NULL,
	ThoiGianDemLenIT DATETIME NOT NULL, -- thời gian đem lên IT
	SoTheNguoiNhan VARCHAR(6) NULL,
	HoTenNguoiNhan NVARCHAR(50) NULL,
	DonViNguoiNhan NVARCHAR(50) NULL,
	ThoiGianNhan DATETIME NULL, 
	GhiChu NVARCHAR(255) DEFAULT '' NULL,
	NguoiCapNhat VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)


GO

-- phiếu nhập sai mật khẩu
CREATE TABLE IT_PasswordError(
	MaMatKhau INTEGER UNIQUE NOT NULL,
	SoThe VARCHAR(6) NOT NULL,
	HoTen NVARCHAR(50) NOT NULL,
	DonVi NVARCHAR(80) NOT NULL,
	NoiDung NVARCHAR(150) NOT NULL,
	SoTheKhoa VARCHAR(6) NOT NULL,
	HoTenKhoa NVARCHAR(50) NOT NULL,
	DonViKhoa NVARCHAR(80) NOT NULL,
	GhiChu NVARCHAR(255) NULL,
	ThoiGian DATETIME DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)



