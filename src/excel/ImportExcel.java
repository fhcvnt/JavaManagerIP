package excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ImportExcel {
	private static ArrayList<ArrayList<String>> dataexcel; // save data from excel

	public static void in() {
		System.out.println(dataexcel.toString());
	}

	public ImportExcel() {
	}

	public static void main(String[] args) {
		ImportExcel a = new ImportExcel();

	}

	// Đọc file excel ghi vào mảng
	// ====================================================================================================================
	public static void readXLSFile(String filename, int columncount, ArrayList<Integer> countcolumnnotuse) {
		// columncount số lượng cột có trong bảng excel
		// mỗi cột trong excel sẽ lưu vào một dòng trong dataexcel
		// countcolumnnotuse là vị trí số cột trong excel không dùng để import
		// dữ liệu bắt đầu từ cột 1, dòng 1 trong file excel
		dataexcel = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < columncount; i++) {
			ArrayList<String> list = new ArrayList<>();
			dataexcel.add(list);
		}
		try {
			InputStream ExcelFileToRead = new FileInputStream(filename);
			HSSFWorkbook worbook = new HSSFWorkbook(ExcelFileToRead);

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
					for (int i = 0; i < columncount; i++) {
						if (cell.getColumnIndex() == i + 1) {
							dataexcel.get(i).add(cell.getStringCellValue());
						}
					}
				}
			}
			worbook.close();
		} catch (Exception eee) {

		}
	}

	// import
	// ------------------------------------------------------------------------------------------------------------------
//	public static void importData(Shell shell) {
//
//		try {
//			String[] FILTER_NAMES = { "Excel (*.xls)", "Excel (*.xlsx)" };
//			// đuôi file có thể mở
//			String[] FILTER_EXTS = { "*.xls", "*.xlsx" };
//
//			FileDialog dlg = new FileDialog(shell, SWT.OPEN);
//			dlg.setFilterNames(FILTER_NAMES);
//			dlg.setFilterExtensions(FILTER_EXTS);
//			String filename = dlg.open();
//
//			if (dlg.getFileName().substring(dlg.getFileName().length() - 3, dlg.getFileName().length()).equals("xls")) {
//				// xls
//				// open file excel save to ArrayList
//				readXLSFile(filename);
//			}
////			} else {
////				readXLSXFile(filename);
////			}
//			conn = DriverManager.getConnection(db_url);
//
//			for (int i = 0; i < excelID.size(); i++) {
//				try {
//					statement = conn.createStatement();
//
//					String insert = "INSERT INTO Mail ( ID ,FullMail ,MailName ,Passwords,Permission,Note,DateCreated,UserUpdate ) VALUES  ( '"
//							+ excelID.get(i).toString() + "' ,'" + excelMail.get(i).toString() + "' ,N'"
//							+ excelMailname.get(i).toString() + "' ,'" + excelPassword.get(i).toString() + "',N'"
//							+ excelPermission.get(i).toString() + "',N'" + excelNote.get(i).toString() + "','"
//							+ excelDatecreate.get(i).toString() + "','" + excelPersonupdate.get(i).toString() + "' )";
//					statement.executeUpdate(insert);
//
//				} catch (Exception se) {
//					try {
//						// insert that bai
//						statement = conn.createStatement();
//						String insert = "INSERT INTO Mail ( ID ,FullMail ,MailName ,Passwords,Permission,Note,DateCreated ) VALUES  ( '"
//								+ excelID.get(i).toString() + "' ,'" + excelMail.get(i).toString() + "' ,N'"
//								+ excelMailname.get(i).toString() + "' ,'" + excelPassword.get(i).toString() + "',N'"
//								+ excelPermission.get(i).toString() + "',N'" + excelNote.get(i).toString() + "','"
//								+ excelDatecreate.get(i).toString() + "' )";
//						statement.executeUpdate(insert);
//					} catch (Exception exx) {
//						MessageBox thongbao = new MessageBox(shell, SWT.OK);
//						if (ngonngu == 0) {
//							thongbao.setText("Thông báo");
//							thongbao.setMessage("Nhập không thành công!");
//						} else {
//							thongbao.setText("Notice");
//							thongbao.setMessage("Import failed!");
//						}
//					}
//				} finally {
//					try {
//						if (statement != null) {
//							statement.close();
//						}
//					} catch (SQLException se2) {
//					}
//				}
//			}
//
//			MessageBox thongbao = new MessageBox(shell, SWT.OK);
//			if (ngonngu == 0) {
//				thongbao.setText("Thông báo");
//				thongbao.setMessage("Nhập thành công!");
//			} else {
//				thongbao.setText("Notice");
//				thongbao.setMessage("Import successful!");
//			}
//
//			thongbao.open();
//
//		} catch (Exception exce) {
//			MessageBox thongbao = new MessageBox(shell, SWT.OK);
//			if (ngonngu == 0) {
//				thongbao.setText("Thông báo");
//				thongbao.setMessage("Nhập không thành công!");
//			} else {
//				thongbao.setText("Notice");
//				thongbao.setMessage("Import failed!");
//			}
//
//			thongbao.open();
//		} finally {
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException se2) {
//			}
//		}
//	}
//

}
