USE [ITManagerIP]
GO

-- 1: IF ELSE
BEGIN 
	DECLARE @count INT
	SELECT @count=10
	SELECT @count=@count/10
	IF @count>1
	BEGIN
		PRINT 'count > 1'
	END
	ELSE 
	BEGIN
		PRINT 'count <= 1'
	END
END

-- tao procedure: thủ tục, luu doan code vao procedure
CREATE PROCEDURE namea
AS
BEGIN
	SELECT * FROM dbo.DanhSachIP
END

-- thuc thi procedure
 EXECUTE namea
 EXEC namea -- viet tat cung duoc

 -- tao procedure co tham so
 Alter PROCEDURE classip(@ip AS VARCHAR(15))
 AS 
 BEGIN
	SELECT * FROM dbo.DanhSachIP WHERE IP LIKE CONCAT(@ip,'%')
 END

 EXECUTE classip '192.168.30'

 ALTER PROCEDURE Hoten (@sothe AS VARCHAR(12))
 AS 
 BEGIN
	SELECT SoThe,Hoten FROM dbo.DanhSachIP WHERE SoThe=@sothe
 END;

 EXEC HoTen @sothe='21608'
 EXEC HoTen '21608'

 alter PROCEDURE countrow(@coutrow INT OUTPUT) 
 AS 
 BEGIN
	SELECT * FROM dbo.DanhSachIP WHERE IP LIKE '192.168.30.%';
	SELECT @coutrow=@@ROWCOUNT;
 END
 
 DECLARE @sodong INT;
 EXEC countrow @coutrow=@sodong OUTPUT
 SELECT @sodong AS 'SO DONG'


 --  tao view, luu ket qua thuc thi vao bang view, khi dung thi select tu view
 CREATE VIEW listip
 AS 
 SELECT * FROM dbo.DanhSachIP

 -- su dung bang view nhu bang thuong khong the insert, delete, update
 SELECT * FROM listip

 begin
 DECLARE @ten VARCHAR(max)
 SET @ten='to ngoc tri'
 SELECT @ten as ten2
 END
 
 -- function
 CREATE FUNCTION TinhTongAB(@a INT,@b INT)
 RETURNS INT
 AS
 BEGIN
	RETURN @a+@b
 END

 SELECT dbo.TinhTongAB(2,10) total

 SELECT dbo.convertUnicodetoASCII(N'người')