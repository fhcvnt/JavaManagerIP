package excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 * An example of how to merge regions of cells.
 */
public class MergedCells {
	public static void main(String[] args) throws IOException {
		try (HSSFWorkbook wb = new HSSFWorkbook()) {
			HSSFSheet sheet = wb.createSheet("new sheet");
			
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

			HSSFRow row = sheet.createRow(1);
			HSSFCell cell = row.createCell(1);
			cell.setCellValue("This is a test of merging");

			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
			cell.setCellStyle(styletitle);
			
			//*******************************************************************
			// Merges the cells
			HSSFRow row2 = sheet.createRow(3);
			HSSFCell cell2 = row2.createCell(1);
			cell2.setCellValue("cell2");
			CellRangeAddress cellRangeAddress = new CellRangeAddress(3,3,1,5);
			sheet.addMergedRegion(cellRangeAddress);
			RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, sheet);
			RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, sheet);
	        RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, sheet);
	        RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangeAddress, sheet);



			// Write the output to a file
			try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\gon\\Desktop\\Merged cell.xls")) {
				wb.write(fileOut);
			}
		}
	}
}
