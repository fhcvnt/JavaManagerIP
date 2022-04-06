USE [HRIS]
GO

INSERT INTO [dbo].[Data_Person]
           ([Person_Serial_Key]
           ,[Person_ID]
           ,[Magneticcard_ID]
           ,[Person_Name]
           ,[Gender]
           ,[Birthday]
           ,[ID]
           ,[Department_Serial_Key]
           ,[Home_Address]
           ,[Date_Come_In]
           ,[Date_Work_End]
           ,[Person_Status]
           ,[Modify_Time]
           ,[Person_Image])
     VALUES ( '0000000000001'
           ,'gggggg'
           ,'07078078078'
           ,N'ds dgsd gds'
           ,'1'
           ,'20201010'
           ,'515132132'
           ,'it'
           ,N'HG'
           ,'20200210'
           ,'20202020'
           ,'1'
           ,'20200310'
           ,(SELECT * from openrowset (bulk N'D:/soft.png',Single_clob) AS img))

          
GO

SELECT * FROM Data_Person

create table Employees1(
id int,
name varchar(50),
photo varbinary(max))
go
INSERT INTO Employees1(id,name,photo) SELECT 10,'samsung note 10',BulkColumn from openrowset (bulk 'D:/soft.png',Single_blob) as Picture

SELECT * FROM dbo.Employees1
