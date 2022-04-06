select count (*) from Mail


select Mail.ID from [30.7].ITManagerIP.dbo.DanhSachIP,Mail where [30.7].[ITManagerIP].[dbo].[DanhSachIP].[SoThe]=Mail.ID


select SoThe,HoTen from [30.7].ITManagerIP.dbo.DanhSachIP,Mail where SoThe=ID


SELECT TOP 10 NV.Person_ID,NV.Person_Name,DV.Department_Name FROM SV4.HRIS.dbo.Data_Person AS NV,SV4.HRIS.dbo.Data_Department AS DV WHERE NV.Department_Serial_Key=DV.Department_Serial_Key



select top 10 Person_ID,Person_Name from SV4.HRIS.dbo.Data_Person



SELECT TOP 10 * FROM SV4.HRIS.dbo.Data_Department



SELECT NV.Person_ID,NV.Person_Name,DV.Department_Name FROM SV4.HRIS.dbo.Data_Person AS NV,SV4.HRIS.dbo.Data_Department AS DV WHERE NV.Department_Serial_Key=DV.Department_Serial_Key




SELECT NV.Person_ID AS SoThe,NV.Person_Name AS Hoten,DV.Department_Name AS Donvi INTO #DSNV FROM SV4.HRIS.dbo.Data_Person AS NV,SV4.HRIS.dbo.Data_Department AS DV WHERE NV.Department_Serial_Key=DV.Department_Serial_Key
		
SELECT Mail.ID,#DSNV.Hoten,#DSNV.Donvi,Mail.FullMail,Mail.MailName,Mail.Passwords,Mail.Permission,Mail.Note,Mail.DateCreated,Mail.DateUpdate,NguoiDung.TenNguoiDung FROM Mail LEFT JOIN #DSNV ON Mail.ID = #DSNV.Sothe COLLATE Chinese_PRC_CI_AS LEFT JOIN NguoiDung ON Mail.UserUpdate=NguoiDung.MaNguoiDung WHERE 1=1
					 ORDER BY Mail.DateUpdate DESC
					  select * from Mail

					 select * from #DSNV3
					 drop table #DSNV


					 select * from Mail

					 select * from SV4.HRIS.dbo.Data_Person where Person_ID like '%00132' order by Person_ID

					

