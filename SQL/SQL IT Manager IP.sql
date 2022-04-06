CREATE DATABASE ITManagerIP

GO 

USE ITManagerIP

GO

CREATE TABLE LoaiMay(
	-- Loại Máy: PC, Laptop, Server, Máy quẹt thẻ, máy quẹt thẻ nhà ăn, Tường lửa, Switch,Router,Wifi, Máy in, Photocopy, khác
	MaLoaiMay VARCHAR(15) PRIMARY KEY NOT NULL,
	LoaiMay NVARCHAR(30) UNIQUE NOT NULL
)
GO

INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('pc',N'Máy vi tính')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('laptop',N'Laptop')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('server',N'Server')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('mayquetthe',N'Máy quẹt thẻ')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('mayquethena',N'Máy quẹt thẻ nhà ăn')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('firewall',N'Tường lửa, Gateway')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('switch',N'Switch')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('router',N'Router')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('wifi',N'Wifi')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('print',N'Máy in')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('photo',N'Photocopy')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('nas',N'NAS')
INSERT INTO dbo.LoaiMay ( MaLoaiMay, LoaiMay ) VALUES  ('other',N'Khác')
GO 

CREATE TABLE HeDieuHanh(
	-- Mã hệ điều hành, 0: Window XP, 1: Window 7, 2: Window 8, 3: Window 10, 4: Window Server, 5: Linux, 6: Khác
	MaHeDieuHanh TINYINT PRIMARY KEY NOT NULL,
	HeDieuHanh NVARCHAR(20) UNIQUE NOT NULL
)
GO

INSERT INTO dbo.HeDieuHanh ( MaHeDieuHanh, HeDieuHanh ) VALUES  ( 0,N'Window XP')
INSERT INTO dbo.HeDieuHanh ( MaHeDieuHanh, HeDieuHanh ) VALUES  ( 1,N'Window 7')
INSERT INTO dbo.HeDieuHanh ( MaHeDieuHanh, HeDieuHanh ) VALUES  ( 2,N'Window 8')
INSERT INTO dbo.HeDieuHanh ( MaHeDieuHanh, HeDieuHanh ) VALUES  ( 3,N'Window 10')
INSERT INTO dbo.HeDieuHanh ( MaHeDieuHanh, HeDieuHanh ) VALUES  ( 4,N'Window Server')
INSERT INTO dbo.HeDieuHanh ( MaHeDieuHanh, HeDieuHanh ) VALUES  ( 5,N'Linux Mint')
INSERT INTO dbo.HeDieuHanh ( MaHeDieuHanh, HeDieuHanh ) VALUES  ( 6,N'Khác')
GO

CREATE TABLE DonVi(
	MaDonVi VARCHAR(15) UNIQUE NOT NULL,
	DonVi NVARCHAR(50) UNIQUE NOT NULL
)
GO


GO

CREATE TABLE Office(
	MaOffice TINYINT PRIMARY KEY NOT NULL,
	Office NVARCHAR(20) UNIQUE NOT NULL
)
GO

INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 0, N'Office 2003' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 1, N'Office 2007' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 2, N'Office 2010' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 3, N'Office 2013' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 4, N'Office 2016' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 5, N'Office 2019' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 6, N'Office 365' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 7, N'WPS' )
INSERT INTO dbo.Office ( MaOffice, Office ) VALUES  ( 8, N'Khác' )
GO

CREATE TABLE Building(
	MaBuilding VARCHAR(10) PRIMARY KEY NOT NULL,
	Building NVARCHAR(30) UNIQUE NOT NULL,
	-- LopIP: 30, 31, 32, 33, 34,...
	LopIP TINYINT NOT NULL
)
GO

INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'a', N'A', 33 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'b', N'B', 32 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'c', N'C', 35 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'd', N'D', 35 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'e', N'E', 36 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'f', N'F', 36 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'g', N'G', 36 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'r1', N'R1', 34 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'r2', N'R2', 34 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'vpct', N'VPCT', 30 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'kvp', N'Khu Văn Phòng', 31 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'nhaan', N'Nhà Ăn', 34 )
INSERT INTO dbo.Building ( MaBuilding, Building, LopIP ) VALUES  ( 'baove', N'Bảo Vệ', 36 )


GO 

SELECT * FROM dbo.Building


CREATE TABLE DanhSachIP(
	SoThe VARCHAR(12) UNIQUE NOT NULL,
	CONSTRAINT unique_SoThe UNIQUE (SoThe),
	HoTen NVARCHAR(50) NOT NULL,
	MaDonVi VARCHAR(15) NOT NULL,
	IP VARCHAR(15) UNIQUE NOT NULL,
	-- Loại Máy: PC, Laptop, Server, Máy quẹt thẻ, máy quẹt thẻ nhà ăn, Tường lửa, Switch,Router,Wifi, Máy in, Photocopy, khác
	MaLoaiMay VARCHAR(15) FOREIGN KEY (MaLoaiMay) REFERENCES dbo.LoaiMay(MaLoaiMay) NOT NULL,
	Email VARCHAR(50),
	-- Mã hệ điều hành, 0: Window XP, 1: Window 7, 2: Window 8, 3: Window 10, 4: Window Server, 5: Linux, 6: Khác
	MaHeDieuHanh TINYINT FOREIGN KEY (MaHeDieuHanh) REFERENCES dbo.HeDieuHanh(MaHeDieuHanh) NOT NULL,
	MaOffice TINYINT FOREIGN KEY (MaOffice) REFERENCES dbo.Office(MaOffice) NOT NULL,
	MaBuilding VARCHAR(10) FOREIGN KEY (MaBuilding) REFERENCES dbo.Building(MaBuilding) NOT NULL,
	GhiChu NVARCHAR(255),
	NgayCapNhat DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
GO 



CREATE TABLE Internet(
	SoThe VARCHAR(6) UNIQUE NOT NULL,
	Website NVARCHAR(3000) NOT NULL, -- cho luu khoan 30 trang web, FULL la mo full internet
	NgayDeNghi DATE NULL,
	NgayCapNhat DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
GO 



CREATE TABLE Software(
	SoThe VARCHAR(6) UNIQUE NOT NULL,
	SoftName NVARCHAR(250) NOT NULL,
	NgayDeNghi DATE NULL,
	NgayCapNhat DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
GO 


CREATE TABLE DataShare(
	SoThe VARCHAR(6) UNIQUE NOT NULL,
	ServerName NVARCHAR(20) NOT NULL,
	DataShareName NVARCHAR(50) NOT NULL,
	UserName VARCHAR(20) NULL,
	Passwd VARCHAR(50) NULL,
	Quyen VARCHAR(20) NULL, --Permissions
	NgayCapNhat DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
GO 

CREATE TABLE USB(
	SoThe VARCHAR(6) UNIQUE NOT NULL,
	LyDo NVARCHAR(250) NULL,
	NgayDeNghi DATE NULL,
	NgayCapNhat DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
GO 

-- Mail ngoai
CREATE TABLE MailOut(
	SoThe VARCHAR(6) UNIQUE NOT NULL,
	LyDo NVARCHAR(250) NULL,
	NgayDeNghi DATE NULL,
	NgayCapNhat DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
GO 

-- Nguoi dung, phai dang nhap moi duoc su dung phan mem
CREATE TABLE NguoiDung(
	-- MaNguoiDung Là số thẻ của người dùng
	MaNguoiDung VARCHAR(6) UNIQUE NOT NULL,
	TenDangNhap VARCHAR(12) UNIQUE NOT NULL,
	TenNhom VARCHAR(9) NOT NULL, -- Manager, Admin, User, Translate
	TenNguoiDung NVARCHAR(50) NOT NULL,
	MatKhau VARCHAR(32) NOT NULL
)

GO

INSERT INTO NguoiDung( MaNguoiDung ,TenDangNhap ,TenNhom ,TenNguoiDung ,MatKhau)
VALUES  ( '21608' ,
          '21608' ,
          'Admin' ,
          N'Tô Ngọc Trí' ,
          '202cb962ac59075b964b07152d234b70' --123
        )

CREATE TABLE Wifi(
	-- Code Là mã số của Wifi (Là cột số thẻ)
	Code VARCHAR(12) UNIQUE NOT NULL,
	Manufacturer VARCHAR(20) NULL, -- APTEK, TP-LINK, DRAYTEK,...
	Floors VARCHAR(2) NULL, -- Wifi lap tang may,1.2.3,...
	WifiName24GHz NVARCHAR(50) NULL,
	Pass24GHz VARCHAR(50) NOT NULL,
	WifiName5GHz NVARCHAR(50) NULL,
	Pass5GHz VARCHAR(50) NULL,
	IPLAN VARCHAR(15) NULL,
	LINK VARCHAR(120) NULL,
	Username VARCHAR(10) NULL,
	Pass VARCHAR(50) NOT NULL,
	NgayCapNhat DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)

GO

CREATE TABLE CapNhatPhanMem(
	FileID VARCHAR(6) UNIQUE NOT NULL,-- ma cua file de phan biet dong
	TenFile NVARCHAR(30) NOT NULL,
	FileCode VARBINARY(MAX) NOT NULL,
	-- He dieu hanh: Window 32, Window 64, Linux 32, Linux 64
	HeDieuHanh VARCHAR(10) NOT NULL,
	-- Phien ban: V2.7.2020.10.16, la ngay tao ra chuong trinh hoan tat
	PhienBan VARCHAR(20) NOT NULL,
	ThoiGianCapNhat DATETIME DEFAULT GETDATE() NOT NULL
)
GO



--***********************************************************************

CREATE FUNCTION convertUnicodetoASCII (@text nvarchar(max))
RETURNS nvarchar(max)
AS
BEGIN
	SET @text = LOWER(@text)
	DECLARE @textLen int = LEN(@text)
	IF @textLen > 0
	BEGIN
		DECLARE @index int = 1
		DECLARE @lPos int
		DECLARE @SIGN_CHARS nvarchar(100) = N'ăâđêôơưàảãạáằẳẵặắầẩẫậấèẻẽẹéềểễệếìỉĩịíòỏõọóồổỗộốờởỡợớùủũụúừửữựứỳỷỹỵýđð'
		DECLARE @UNSIGN_CHARS varchar(100) = 'aadeoouaaaaaaaaaaaaaaaeeeeeeeeeeiiiiiooooooooooooooouuuuuuuuuuyyyyydd'

		WHILE @index <= @textLen
		BEGIN
			SET @lPos = CHARINDEX(SUBSTRING(@text,@index,1),@SIGN_CHARS)
			IF @lPos > 0
			BEGIN
				SET @text = STUFF(@text,@index,1,SUBSTRING(@UNSIGN_CHARS,@lPos,1))
			END
			SET @index = @index + 1
		END
	END
	RETURN @text
END



--******************************************************

-- Mail 
CREATE TABLE Mail(
	ID VARCHAR(6) UNIQUE NOT NULL,
	FullMail VARCHAR(40) NOT NULL,
	MailName NVARCHAR(40) NULL,
	Passwords VARCHAR(40) NULL,
	Permission NVARCHAR(40) NULL, -- Nội bộ, mail ngoài
	Note NVARCHAR(250) NULL,
	DateCreated VARCHAR(20) NULL,-- date created
	DateUpdate DATE DEFAULT GETDATE(),
	UserUpdate VARCHAR(6) NULL -- MaNguoiDung trong table NguoiDung, la nguoi cap nhat du lieu
)
GO 

