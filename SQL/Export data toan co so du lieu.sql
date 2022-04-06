
-- Don vi
SELECT * FROM DonVi
SELECT MaDonVi,DonVi FROM DonVi

-- Building
SELECT * FROM Building
SELECT MaBuilding,Building,LopIP FROM Building

-- Operator System
SELECT * FROM HeDieuHanh
SELECT MaHeDieuHanh,HeDieuHanh FROM HeDieuHanh

-- Loai may
SELECT * FROM dbo.LoaiMay
SELECT MaLoaiMay,LoaiMay FROM LoaiMay

-- Office
SELECT * FROM dbo.Office
SELECT MaOffice,Office FROM Office

-- Danh sach IP
SELECT * from DanhSachIP
SELECT SoThe,HoTen,MaDonVi,IP,MaLoaiMay,Email,MaHeDieuHanh,MaOffice,MaBuilding,GhiChu,NgayCapNhat from DanhSachIP

-- Data share
SELECT * FROM dbo.DataShare
SELECT SoThe,ServerName,DataShareName,UserName,Passwd,Quyen,NgayCapNhat FROM DataShare

-- Internet
SELECT * FROM dbo.Internet
SELECT SoThe,Website,NgayDeNghi,NgayCapNhat FROM Internet

-- Mail Out
SELECT * FROM dbo.MailOut
SELECT SoThe,LyDo,NgayDeNghi,NgayCapNhat FROM MailOut

-- Software
SELECT * FROM dbo.Software
SELECT SoThe,SoftName,NgayDeNghi,NgayCapNhat FROM Software

-- USB
SELECT * FROM dbo.USB
SELECT SoThe,LyDo,NgayDeNghi,NgayCapNhat FROM USB

-- Wifi
SELECT * FROM dbo.Wifi
SELECT Code,Manufacturer,Floors,WifiName24GHz,Pass24GHz,WifiName5GHz,Pass5GHz,IPLAN,LINK,Username,Pass,NgayCapNhat FROM Wifi

-- nguoi dung
SELECT * FROM dbo.NguoiDung
SELECT MaNguoiDung,TenDangNhap,TenNhom,TenNguoiDung,MatKhau FROM NguoiDung
