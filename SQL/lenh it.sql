USE [ITManagerIP]
GO

SELECT [MaThietBi]
      ,[TenThietBi]
      ,[QuyCach]
      ,CASE [VatTuTieuHao] WHEN 0 THEN N'Định mức' ELSE N'Tiêu hao' END AS 'LoaiVatTu'
  FROM [dbo].[IT_ThietBi]
GO


SELECT [MaThietBi],[TenThietBi],[QuyCach],CASE [VatTuTieuHao] WHEN 0 THEN N'Định mức' ELSE N'Tiêu hao' END AS 'LoaiVatTu' FROM [dbo].[IT_ThietBi] WHERE [MaThietBi] LIKE '%%' AND ([TenThietBi] LIKE N'%%' OR [dbo].[convertUnicodetoASCII]([TenThietBi]) LIKE '%%') AND ([QuyCach] LIKE N'%%' OR [dbo].[convertUnicodetoASCII]([QuyCach]) LIKE '%%') AND [VatTuTieuHao]=0

GO

INSERT INTO [dbo].[IT_ThietBi] ([MaThietBi],[TenThietBi],[QuyCach],[VatTuTieuHao]) VALUES ('',N''',N''',0)
GO

DELETE FROM [dbo].[IT_ThietBi] WHERE [MaThietBi]=''
GO

SELECT * FROM dbo.IT_ThietBi ORDER BY MaThietBi ASC

SELECT [ID],[SoPhieuBPM],[dbo].[IT_ThietBi].[TenThietBi],[SoLuong],[NgayMua],[ThoiGianBaoHanh],[NgayNhapKhoIT],[DaNhapKhoIT],[GhiChu],[TenFile],[FileDinhKem],[UserUpdate] FROM [dbo].[IT_MuaThietBi],[dbo].[IT_ThietBi] WHERE [dbo].[IT_MuaThietBi].[MaThietBi]=[dbo].[IT_ThietBi].[MaThietBi] AND [dbo].[IT_MuaThietBi].[SoPhieuBPM] LIKE '%%' AND [dbo].[IT_ThietBi].[TenThietBi]=N'2dd' AND [dbo].[IT_MuaThietBi].[SoLuong]=2 AND [dbo].[IT_MuaThietBi].[NgayMua] BETWEEN '20201221' AND GETDATE() AND [dbo].[IT_MuaThietBi].[ThoiGianBaoHanh]=12 AND [dbo].[IT_MuaThietBi].[DaNhapKhoIT]=0 AND [dbo].[IT_MuaThietBi].NgayNhapKhoIT BETWEEN '19900101' AND GETDATE() ORDER BY [dbo].[IT_MuaThietBi].[NgayMua] DESC