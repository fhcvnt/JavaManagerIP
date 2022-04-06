package it;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import date.ConvertDate;
import sql.ConnectSQL;

public class LyLichMayTinhImportExcel {
	private ConnectSQL connect;

	// Danh sách các cột trong Excel, mỗi cột sẽ đưa vào một danh sách (11 colunm)
	private ArrayList<String> excelDepartment, excelDepartmentID, excelCPUType, exelCPUUseDate, exelMainboardType,
			exelMainboardUseDate, excelHardDrive, excelRAMType, excelRAMCapacity, excelRAMCount, excelMonitorType,
			excelMonitorUseDate, excelUPSType, excelUPSUseDate, excelPrinterType, excelPrinterUseDate, excelUseDate,
			excelWarrantyPeriod, excelWarrantyExpiryDate, excelUser, excelID, excelPersonUpdate;

	public LyLichMayTinhImportExcel() {
	}

	// Đọc file excel ghi vào mảng
	// ====================================================================================================================
	public void readXLSFile(String filename) {
		try {
			InputStream ExcelFileToRead = new FileInputStream(filename);
			HSSFWorkbook worbook = new HSSFWorkbook(ExcelFileToRead);

			// khai bao
			excelDepartment = new ArrayList<String>();
			excelDepartmentID = new ArrayList<String>();
			excelCPUType = new ArrayList<String>();
			exelCPUUseDate = new ArrayList<String>();
			exelMainboardType = new ArrayList<String>();
			exelMainboardUseDate = new ArrayList<String>();
			excelHardDrive = new ArrayList<String>();
			excelRAMType = new ArrayList<String>();
			excelRAMCapacity = new ArrayList<String>();
			excelRAMCount = new ArrayList<String>();
			excelMonitorType = new ArrayList<String>();
			excelMonitorUseDate = new ArrayList<String>();
			excelUPSType = new ArrayList<String>();
			excelUPSUseDate = new ArrayList<String>();
			excelPrinterType = new ArrayList<String>();
			excelPrinterUseDate = new ArrayList<String>();
			excelUseDate = new ArrayList<String>();
			excelWarrantyPeriod = new ArrayList<String>();
			excelWarrantyExpiryDate = new ArrayList<String>();
			excelUser = new ArrayList<String>();
			excelID = new ArrayList<String>();
			excelPersonUpdate = new ArrayList<String>();

			HSSFSheet sheet = worbook.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {
					continue;
				}
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					cell = (HSSFCell) cells.next();

					try {
						if (cell.getColumnIndex() == 3) {
							excelDepartment.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 4) {
							excelDepartmentID.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 5) {
							excelCPUType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 6) {
							exelCPUUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 7) {
							exelMainboardType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 8) {
							exelMainboardUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 9) {
							excelHardDrive.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 10) {
							excelRAMType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 11) {
							excelRAMCapacity.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 12) {
							excelRAMCount.add(cell.getStringCellValue());
						}

						if (cell.getColumnIndex() == 13) {
							excelMonitorType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 14) {
							excelMonitorUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 15) {
							excelUPSType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 16) {
							excelUPSUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 17) {
							excelPrinterType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 18) {
							excelPrinterUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 19) {
							excelUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 20) {
							excelWarrantyPeriod.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 21) {
							excelWarrantyExpiryDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 24) {
							excelUser.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 25) {
							excelID.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 26) {
							excelPersonUpdate.add(cell.getStringCellValue());
						}
					} catch (Exception exc) {

					}
				}
			}
			worbook.close();
		} catch (Exception eee) {

		}
	}

	// Đọc file excel ghi vào mảng
	// ====================================================================================================================
	public void readXLSXFile(String filename) {
		try {
			InputStream ExcelFileToRead = new FileInputStream(filename);
			XSSFWorkbook worbook = new XSSFWorkbook(ExcelFileToRead);

			// khai bao
			excelDepartment = new ArrayList<String>();
			excelDepartmentID = new ArrayList<String>();
			excelCPUType = new ArrayList<String>();
			exelCPUUseDate = new ArrayList<String>();
			exelMainboardType = new ArrayList<String>();
			exelMainboardUseDate = new ArrayList<String>();
			excelHardDrive = new ArrayList<String>();
			excelRAMType = new ArrayList<String>();
			excelRAMCapacity = new ArrayList<String>();
			excelRAMCount = new ArrayList<String>();
			excelMonitorType = new ArrayList<String>();
			excelMonitorUseDate = new ArrayList<String>();
			excelUPSType = new ArrayList<String>();
			excelUPSUseDate = new ArrayList<String>();
			excelPrinterType = new ArrayList<String>();
			excelPrinterUseDate = new ArrayList<String>();
			excelUseDate = new ArrayList<String>();
			excelWarrantyPeriod = new ArrayList<String>();
			excelWarrantyExpiryDate = new ArrayList<String>();
			excelUser = new ArrayList<String>();
			excelID = new ArrayList<String>();
			excelPersonUpdate = new ArrayList<String>();

			XSSFSheet sheet = worbook.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {
					continue;
				}
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					cell = (XSSFCell) cells.next();

					try {
						if (cell.getColumnIndex() == 3) {
							excelDepartment.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 4) {
							excelDepartmentID.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 5) {
							excelCPUType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 6) {
							exelCPUUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 7) {
							exelMainboardType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 8) {
							exelMainboardUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 9) {
							excelHardDrive.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 10) {
							excelRAMType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 11) {
							excelRAMCapacity.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 12) {
							excelRAMCount.add(cell.getStringCellValue());
						}

						if (cell.getColumnIndex() == 13) {
							excelMonitorType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 14) {
							excelMonitorUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 15) {
							excelUPSType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 16) {
							excelUPSUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 17) {
							excelPrinterType.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 18) {
							excelPrinterUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 19) {
							excelUseDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 20) {
							excelWarrantyPeriod.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 21) {
							excelWarrantyExpiryDate.add(ConvertDate.convertDateToString(cell.getStringCellValue()));
						}
						if (cell.getColumnIndex() == 24) {
							excelUser.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 25) {
							excelID.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 26) {
							excelPersonUpdate.add(cell.getStringCellValue());
						}
					} catch (Exception exc) {

					}
				}
			}
			worbook.close();
		} catch (Exception eee) {

		}
	}

	// Import
	// ===================================================================
	public void importExcel(int ngonngu, Shell shell) {
		boolean checkinsert = false;
		try {
			String[] FILTER_NAMES = { "Excel (*.xls)", "Excel (*.xlsx)" };
			// đuôi file có thể mở
			String[] FILTER_EXTS = { "*.xls", "*.xlsx" };

			FileDialog dlg = new FileDialog(shell, SWT.OPEN);
			dlg.setFilterNames(FILTER_NAMES);
			dlg.setFilterExtensions(FILTER_EXTS);
			String filename = dlg.open();

			if (dlg.getFileName().substring(dlg.getFileName().length() - 3, dlg.getFileName().length()).equals("xls")) {
				// xls
				// open file excel save to ArrayList
				readXLSFile(filename);
			} else {
				readXLSXFile(filename);
			}
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}

			connect.setStatement();
			for (int i = 0; i < excelDepartment.size(); i++) {
				try {
					String malylich = "";
					String select = "SELECT TOP 1 (SELECT SUBSTRING([MaLyLich],3,8)) FROM [dbo].[IT_LyLichMayTinh] ORDER BY [MaLyLich] DESC";
					ResultSet resultmacongviec = connect.getStatement().executeQuery(select);
					while (resultmacongviec.next()) {
						malylich = resultmacongviec.getString(1);
					}
					try {
						if (!malylich.isEmpty()) {
							malylich = "LL" + (Integer.parseInt(malylich) + 1);
						} else {
							malylich = "LL10000001";
						}
					} catch (Exception ew) {
						malylich = "LL10000001";
					}

					String insert = "INSERT INTO [dbo].[IT_LyLichMayTinh]([MaLyLich],[DonVi],[MaSoDonVi],[CPU_Loai],[CPU_NgaySuDung]"
							+ ",[Mainboard_Loai],[Mainboard_NgaySuDung],[OCung],[RAM_Loai],[RAM_DungLuong],[RAM_SoLuong],[ManHinh_Loai],[ManHinh_NgaySuDung]"
							+ ",[UPS_Loai],[UPS_NgaySuDung],[MayIn_Loai],[MayIn_NgaySuDung],[NgaySuDung],[ThoiGianBaoHanh],[NgayHetHanBaoHanh],[NguoiSuDung],[SoThe],[NguoiCapNhat]) VALUES ('"
							+ malylich + "',N'" + excelDepartment.get(i).toString() + "','"
							+ excelDepartmentID.get(i).toString() + "','" + excelCPUType.get(i).toString() + "','"
							+ exelCPUUseDate.get(i).toString() + "','" + exelMainboardType.get(i).toString() + "','"
							+ exelMainboardUseDate.get(i).toString() + "','" + excelHardDrive.get(i).toString() + "','"
							+ excelRAMType.get(i).toString() + "','" + excelRAMCapacity.get(i).toString() + "','"
							+ excelRAMCount.get(i).toString() + "','" + excelMonitorType.get(i).toString() + "','"
							+ excelMonitorUseDate.get(i).toString() + "','" + excelUPSType.get(i).toString() + "','"
							+ excelUPSUseDate.get(i).toString() + "','" + excelPrinterType.get(i).toString() + "','"
							+ excelPrinterUseDate.get(i).toString() + "','" + excelUseDate.get(i).toString() + "',"
							+ excelWarrantyPeriod.get(i).toString() + ",'" + excelWarrantyExpiryDate.get(i).toString()
							+ "',N'" + excelUser.get(i).toString() + "','" + excelID.get(i).toString() + "','"
							+ excelPersonUpdate.get(i).toString() + "')";

					if (connect.execUpdateQuery(insert) > 0) {
						checkinsert = true;
					}

				} catch (Exception se) {
					try {
						// insert that bai
						connect.setStatement();

						String malylich = "";
						String select = "SELECT TOP 1 (SELECT SUBSTRING([MaLyLich],3,8)) FROM [dbo].[IT_LyLichMayTinh] ORDER BY [MaLyLich] DESC";
						ResultSet resultmacongviec = connect.getStatement().executeQuery(select);
						while (resultmacongviec.next()) {
							malylich = resultmacongviec.getString(1);
						}
						try {
							if (!malylich.isEmpty()) {
								malylich = "LL" + (Integer.parseInt(malylich) + 1);
							} else {
								malylich = "LL10000001";
							}
						} catch (Exception ew) {
							malylich = "LL10000001";
						}

						String insert = "INSERT INTO [dbo].[IT_LyLichMayTinh]([MaLyLich],[DonVi],[MaSoDonVi],[CPU_Loai],[CPU_NgaySuDung]"
								+ ",[Mainboard_Loai],[Mainboard_NgaySuDung],[OCung],[RAM_Loai],[RAM_DungLuong],[RAM_SoLuong],[ManHinh_Loai],[ManHinh_NgaySuDung]"
								+ ",[UPS_Loai],[UPS_NgaySuDung],[MayIn_Loai],[MayIn_NgaySuDung],[NgaySuDung],[ThoiGianBaoHanh],[NgayHetHanBaoHanh],[NguoiSuDung],[SoThe],[NguoiCapNhat]) VALUES ('"
								+ malylich + "',N'" + excelDepartment.get(i).toString() + "','"
								+ excelDepartmentID.get(i).toString() + "','" + excelCPUType.get(i).toString() + "','"
								+ exelCPUUseDate.get(i).toString() + "','" + exelMainboardType.get(i).toString() + "','"
								+ exelMainboardUseDate.get(i).toString() + "','" + excelHardDrive.get(i).toString()
								+ "','" + excelRAMType.get(i).toString() + "','" + excelRAMCapacity.get(i).toString()
								+ "','" + excelRAMCount.get(i).toString() + "','" + excelMonitorType.get(i).toString()
								+ "','" + excelMonitorUseDate.get(i).toString() + "','" + excelUPSType.get(i).toString()
								+ "','" + excelUPSUseDate.get(i).toString() + "','" + excelPrinterType.get(i).toString()
								+ "','" + excelPrinterUseDate.get(i).toString() + "','" + excelUseDate.get(i).toString()
								+ "'," + excelWarrantyPeriod.get(i).toString() + ",'"
								+ excelWarrantyExpiryDate.get(i).toString() + "',N'" + excelUser.get(i).toString()
								+ "','" + excelID.get(i).toString() + "','" + excelPersonUpdate.get(i).toString()
								+ "')";
						if (connect.execUpdateQuery(insert) > 0) {
							checkinsert = true;
						}
					} catch (Exception exx) {
					}
				}
			}
			connect.closeStatement();
			if (checkinsert) {
				MessageBox thongbao = new MessageBox(shell, SWT.OK);
				if (ngonngu == 0) {
					thongbao.setText("Thông báo");
					thongbao.setMessage("Nhập thành công!");
				} else {
					thongbao.setText("Notice");
					thongbao.setMessage("Import successful!");
				}

				thongbao.open();
			}

		} catch (Exception exce) {
		}
		if (checkinsert == false) {
			MessageBox thongbao = new MessageBox(shell, SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo");
				thongbao.setMessage("Nhập không thành công!");
			} else {
				thongbao.setText("Notice");
				thongbao.setMessage("Import failed!");
			}
		}
	}

}
