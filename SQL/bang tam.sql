SELECT * FROM Internet,DanhSachIP WHERE Internet.SoThe=DanhSachIP.SoThe



SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat FROM Internet,DanhSachIP,DonVi WHERE Internet.SoThe=DanhSachIP.SoThe AND DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC

SELECT * FROM Internet


SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat FROM Internet,DanhSachIP,DonVi WHERE Internet.SoThe=DanhSachIP.SoThe AND DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC



SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat FROM Internet,DanhSachIP,DonVi WHERE Internet.SoThe=DanhSachIP.SoThe AND DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC




SELECT Internet.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat
FROM Internet
LEFT JOIN DanhSachIP,Donvi ON Internet.SoThe = DanhSachIP.SoThe
 WHERE Donvi.MaDonVi=dbo.DanhSachIP.MaDonVi and DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY Internet.NgayCapNhat DESC



 SELECT DanhSachIP.SoThe,DanhSachIP.HoTen,Donvi.DonVi,DanhSachIP.IP INTO #DSIP FROM DanhSachIP,DonVi WHERE DanhSachIP.MaDonVi=DonVi.MaDonVi
 
 SELECT Internet.SoThe,#DSIP.HoTen,#DSIP.DonVi,#DSIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat FROM Internet LEFT JOIN #DSIP ON Internet.SoThe = #DSIP.SoThe WHERE 1=1 ORDER BY Internet.NgayCapNhat DESC
 





 
 SELECT * FROM #DSIP

 DROP TABLE #DSIP


