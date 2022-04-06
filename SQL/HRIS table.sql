SELECT TOP 1 * FROM dbo.Data_Person  WHERE Person_ID='21608'
SELECT TOP 1 * FROM dbo.Data_Person_Detail WHERE Person_ID='21608'
SELECT COUNT(*) FROM dbo.Data_Department

SELECT TOP 2 * FROM dbo.Data_Person WHERE Person_ID LIKE '%21608%'
SELECT TOP 2 * FROM dbo.Data_Department

SELECT Department_ID,Department_Name FROM Data_Department WHERE Department_ID LIKE '%%' AND Department_Name LIKE N'%%'

SELECT Data_Person.Person_Name,Data_Department.Department_Name FROM Data_Person,Data_Department WHERE Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='21608' OR Data_Person.Person_ID='21608' OR Data_Person.Person_ID='21608') AND 

SELECT Department_Serial_Key FROM dbo.Data_Department WHERE Department_Name=N''



--*******************************************************
CREATE DATABASE HRIS

CREATE TABLE Data_Person(
	Person_Serial_Key VARCHAR(15) PRIMARY KEY NOT NULL,
	Person_ID VARCHAR(10) NULL, -- so the
	Magneticcard_ID VARCHAR(20) NULL,
	Person_Name NVARCHAR(50) NOT NULL,
	Gender CHAR(1) NOT NULL,-- gioi tinh
	Birthday DATETIME NOT NULL,
	ID VARCHAR(20) NOT NULL,-- CMND
	Department_Serial_Key VARCHAR(15) NOT NULL,
	Home_Address NVARCHAR(15) NOT NULL,
	Date_Come_In DATETIME NOT NULL,--ngay vao cong ty
	Date_Work_End DATETIME NULL, -- ngay nghi viec
	Person_Status CHAR(1) NOT NULL,-- trang thai dang lam hay da nghi, 1- dang lam, 0- da nghi viec
	Modify_Time DATETIME NULL,
	Person_Image IMAGE NULL,-- anh cua nhan vien
	faceimg11 IMAGE NULL,
	faceimg21 IMAGE NULL,
	faceimg31 IMAGE NULL,
	faceimg41 IMAGE NULL,
	faceimg51 IMAGE NULL,
	face1 IMAGE NULL,
	face2 IMAGE NULL,
	face3 IMAGE NULL,
	face4 IMAGE NULL,
	face5 IMAGE NULL,
	UserPhoto IMAGE NULL,
)

GO

CREATE TABLE Data_Person_Detail(
	Person_Serial_Key VARCHAR(15) PRIMARY KEY NOT NULL,
	Person_ID VARCHAR(20) NOT NULL,-- so the
	Birth_Place NVARCHAR(200) NULL, -- noi sinh
	Mobilephone_Number VARBINARY(30) NULL,
	Education NVARCHAR(50) NULL,--trinh do hoc van
	Specialize NVARCHAR(50) NULL,-- chuyen nganh
	Address_Live NVARCHAR(200) NULL -- noi song hien tai
)


GO 
CREATE TABLE Data_Department(
	Department_Serial_Key VARCHAR(15) PRIMARY KEY NOT NULL,
	Department_ID VARCHAR(20) NULL,
	Department_Name NVARCHAR(50) NULL,
	Start_Date DATETIME NULL,-- ngay vao cong ty
	NoUse_Date DATETIME NULL, -- ngay nghi viec
)





