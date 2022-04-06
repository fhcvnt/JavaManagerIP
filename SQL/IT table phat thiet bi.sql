
-- Tạo bảng danh mục thiết bị trong phần linh kiện (LK)
CREATE TABLE LK_DanhMucThietBi(
		MaDanhMuc INTEGER PRIMARY KEY NOT NULL,
		TenDanhMuc NVARCHAR(50) UNIQUE NOT NULL
)

GO

-- Tạo bảng thiết bị kho IT trong phần linh kiện (LK): chứa các thiết bị phát cho người dùng, liên kết với bảng LK_DanhMucThietBi
CREATE TABLE LK_ThietBiKhoIT(
		MaThietBi INTEGER PRIMARY KEY NOT NULL,
		MaDanhMuc INTEGER NOT NULL,
		TenThietBi NVARCHAR(100) NOT NULL,
		SoBPM VARCHAR(20) DEFAULT '' NULL,
		NgayNhapKho DATE DEFAULT GETDATE() NOT NULL,
		TinhTrang BIT DEFAULT 1 NOT NULL, -- 0: Cũ, 1: Mới
		TrangThai BIT DEFAULT 1 NOT NULL, -- 0: Đã phát, 1: Chưa phát
		GhiChu NVARCHAR(255) NULL,
		NguoiCapNhat VARCHAR(6) NULL, --- LÀ SỐ THẺ CỦA NGƯỜI ĐĂNG NHẬP PHẦN MỀM
		CONSTRAINT fk_LK_DanhMucThietBi_MaDanhMuc FOREIGN KEY (MaDanhMuc) REFERENCES LK_DanhMucThietBi (MaDanhMuc)
)

GO

-- Tạo bảng phát thiết bị cho người dùng trong phần linh kiện, liên kết với bảng LK_ThietBiKhoIT
CREATE TABLE LK_PhatThietBiIT(
		MaPhatThietBi INTEGER PRIMARY KEY NOT NULL,
		MaThietBi INTEGER NOT NULL,
		SoThe VARCHAR(6) NOT NULL, -- số thẻ người nhận thiết bị từ IT
		NguoiNhan NVARCHAR(50) NOT NULL, -- Họ tên người nhận
		DonVi NVARCHAR (80) NOT NULL,
		ThoiGianNhan DATETIME NOT NULL,
		NguoiPhat NVARCHAR(50) NOT NULL, -- Người phát thiết bị cho người dùng
		GhiChu NVARCHAR(255) NULL,
		NguoiCapNhat VARCHAR(6) NULL, --- LÀ SỐ THẺ CỦA NGƯỜI ĐĂNG NHẬP PHẦN MỀM
)


-- insert data co ban
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (1,N'AIO')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (2,N'Bàn phím')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (3,N'Camera')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (4,N'Cáp HDMI')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (5,N'Cáp VGA')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (6,N'Card mạng')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (7,N'Case máy tính')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (8,N'Chuột')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (9,N'CPU')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (10,N'Dây nguồn')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (11,N'Laptop')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (12,N'Mainboard')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (13,N'Màn hình')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (14,N'Máy in')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (15,N'Máy Scan')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (16,N'Ổ cứng')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (17,N'RAM')
INSERT INTO [dbo].[LK_DanhMucThietBi] ([MaDanhMuc],[TenDanhMuc]) VALUES (18,N'Tivi')












