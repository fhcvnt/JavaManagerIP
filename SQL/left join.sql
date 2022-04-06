SELECT * FROM Internet,DanhSachIP WHERE Internet.SoThe=DanhSachIP.SoThe



SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat FROM Internet,DanhSachIP,DonVi WHERE Internet.SoThe=DanhSachIP.SoThe AND DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC

SELECT * FROM Internet


SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat FROM Internet,DanhSachIP,DonVi WHERE Internet.SoThe=DanhSachIP.SoThe AND DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC



SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat FROM Internet,DanhSachIP,DonVi WHERE Internet.SoThe=DanhSachIP.SoThe AND DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC




SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat
FROM Internet
LEFT JOIN DanhSachIP ON Internet.SoThe = DanhSachIP.SoThe 
LEFT JOIN Donvi ON Donvi.MaDonVi=dbo.DanhSachIP.MaDonVi WHERE Donvi.MaDonVi=dbo.DanhSachIP.MaDonVi and DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC


