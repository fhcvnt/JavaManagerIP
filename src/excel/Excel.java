package excel;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Excel {

	public Excel() {

	}

	public static void exportExcelTable(Table table, Shell shell, int ngonngu, String title) {

		try {
			TableItem[] itemtableexcel = table.getItems();
			ArrayList<HSSFCell> arraycell = new ArrayList<>(); // xls
			ArrayList<Cell> cellxlxs = new ArrayList<>();// xlsx

			// Save file
			String[] FILTER_NAMES = { "Excel (*.xls)", "Excel (*.xlsx)" };
			// đuôi file có thể mở
			String[] FILTER_EXTS = { "*.xls", "*.xlsx" };

			FileDialog dlg = new FileDialog(shell, SWT.SAVE);
			dlg.setFilterNames(FILTER_NAMES);
			dlg.setFilterExtensions(FILTER_EXTS);

			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			int day = now.get(Calendar.DAY_OF_MONTH);
			dlg.setFileName(title + " " + day + "." + month + "." + year + ".xls");
			String filename = dlg.open(); // ten file luu
			if (dlg.getFileName().substring(dlg.getFileName().length() - 3, dlg.getFileName().length()).equals("xls")) {
				// xls
				if (filename != null) {

					String excelFileName = filename;// name of excel file

					String sheetName = "Sheet1";// name of sheet

					HSSFWorkbook wb = new HSSFWorkbook();
					HSSFSheet sheet = wb.createSheet(sheetName);

					// Tao style cho tieu de
					HSSFCellStyle styletitle = wb.createCellStyle();
					styletitle.setAlignment(HorizontalAlignment.CENTER);
					styletitle.setVerticalAlignment(VerticalAlignment.CENTER);
					styletitle.setBorderTop(BorderStyle.THIN);
					styletitle.setBorderBottom(BorderStyle.THIN);
					styletitle.setBorderRight(BorderStyle.THIN);
					styletitle.setBorderLeft(BorderStyle.THIN);
					styletitle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
					styletitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

					// Font cho tieu de
					HSSFFont fonttitle = wb.createFont();
					fonttitle.setBold(true);
					fonttitle.setFontHeightInPoints((short) 13);
					fonttitle.setFontName("Times New Roman");
					fonttitle.setColor(IndexedColors.RED.getIndex());
					styletitle.setFont(fonttitle);

					HSSFRow rowtieude = sheet.createRow(1);
					rowtieude.setHeightInPoints(30);

					for (int i = 0; i < table.getColumnCount(); i++) {
						HSSFCell cell = rowtieude.createCell(i + 1);
						cell.setCellValue(table.getColumn(i).getText());
						cell.setCellStyle(styletitle);
						arraycell.add(cell);
					}

					// -----------------
					// style cho cac o du lieu, tao border
					HSSFCellStyle style = wb.createCellStyle();
					style.setVerticalAlignment(VerticalAlignment.CENTER);
					style.setBorderTop(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);

					// Font cho cac o du lieu
					HSSFFont fontcell = wb.createFont();
					fontcell.setFontHeightInPoints((short) 13);
					fontcell.setFontName("Times New Roman");
					fontcell.setColor(IndexedColors.BLACK.getIndex());
					style.setFont(fontcell);
					// iterating r number of rows
					int r = 2;
					for (int i = 0; i < itemtableexcel.length; i++) {
						HSSFRow row = sheet.createRow(r);

						for (int j = 0; j < table.getColumnCount(); j++) {
							HSSFCell cell = row.createCell(j + 1);
							cell.setCellValue(itemtableexcel[i].getText(j));
							cell.setCellStyle(style);
							arraycell.add(cell);
						}
						r++;
					}

					for (int j = 0; j < table.getColumnCount(); j++) {
						// thay đổi độ rộng cột cho vừa với nội dung trong cell
						sheet.autoSizeColumn(j + 1);
					}

					FileOutputStream fileOut = new FileOutputStream(excelFileName);
					wb.write(fileOut);
					fileOut.flush();
					fileOut.close();
					MessageBox thongbao = new MessageBox(shell, SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Xuất Excel thành công!");
					} else {
						thongbao.setText("Notice");
						thongbao.setMessage("Export Excel successful!");
					}
					thongbao.open();
					wb.close();
				}

			} else {
				// xlsx
				if (filename != null) {
					try {
						String excelFileName = filename;// name of excel file
						XSSFWorkbook wbxlsx = new XSSFWorkbook();
						XSSFSheet sheet = wbxlsx.createSheet("Sheet1");

						// Tao style cho tieu de
						XSSFCellStyle styletitle = wbxlsx.createCellStyle();
						styletitle.setAlignment(HorizontalAlignment.CENTER);
						styletitle.setVerticalAlignment(VerticalAlignment.CENTER);
						styletitle.setBorderTop(BorderStyle.THIN);
						styletitle.setBorderBottom(BorderStyle.THIN);
						styletitle.setBorderRight(BorderStyle.THIN);
						styletitle.setBorderLeft(BorderStyle.THIN);
						styletitle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
						styletitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

						// Font cho tieu de
						XSSFFont fonttitle = wbxlsx.createFont();
						fonttitle.setBold(true);
						fonttitle.setFontHeightInPoints((short) 13);
						fonttitle.setFontName("Times New Roman");
						fonttitle.setColor(IndexedColors.RED.getIndex());
						styletitle.setFont(fonttitle);

						// Tao tieu de cho file Excel
						XSSFRow rowtieude = sheet.createRow(1);
						rowtieude.setHeightInPoints(30);

						for (int i = 0; i < table.getColumnCount(); i++) {
							Cell cell = rowtieude.createCell(i + 1);
							cell.setCellValue(table.getColumn(i).getText());
							cell.setCellStyle(styletitle);
							cellxlxs.add(cell);
						}

						// style cho cac o du lieu, tao border
						XSSFCellStyle style = wbxlsx.createCellStyle();
						style.setVerticalAlignment(VerticalAlignment.CENTER);
						style.setBorderTop(BorderStyle.THIN);
						style.setBorderBottom(BorderStyle.THIN);
						style.setBorderRight(BorderStyle.THIN);
						style.setBorderLeft(BorderStyle.THIN);

						// Font cho cac o du lieu
						XSSFFont fontcell = wbxlsx.createFont();
						fontcell.setFontHeightInPoints((short) 13);
						fontcell.setFontName("Times New Roman");
						fontcell.setColor(IndexedColors.BLACK.getIndex());
						style.setFont(fontcell);

						// iterating r number of rows
						int r = 2;
						for (int i = 0; i < itemtableexcel.length; i++) {
							XSSFRow row = sheet.createRow(r);

							// iterating c number of columns
							for (int j = 0; j < table.getColumnCount(); j++) {
								Cell cell = row.createCell(j + 1);
								cell.setCellValue(itemtableexcel[i].getText(j));
								cell.setCellStyle(style);
								cellxlxs.add(cell);
							}
							r++;
						}

						for (int j = 0; j < table.getColumnCount(); j++) {
							// thay đổi độ rộng cột cho vừa với nội dung trong cell
							sheet.autoSizeColumn(j + 1);
						}

						FileOutputStream fileOut = new FileOutputStream(excelFileName);
						wbxlsx.write(fileOut);
						fileOut.flush();
						fileOut.close();
						wbxlsx.close();
						MessageBox thongbao = new MessageBox(shell, SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Xuất Excel thành công!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("Export Excel successful!");
						}
						thongbao.open();

					} catch (Exception excep) {
						MessageBox thongbao = new MessageBox(shell, SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Xuất Excel thất bại!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("Export Excel failed!");
						}
						thongbao.open();
					}
				}

			}
		} catch (Exception excep) {

		}
	}
}
