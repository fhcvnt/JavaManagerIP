package date;

import java.time.LocalDate;

import org.eclipse.swt.widgets.DateTime;

public class ConvertDate {

	public ConvertDate() {

	}

	// -------------------------------------------------------------------------------------------
	// định dạng ngày lấy từ sql (yyyy-MM-dd) thành dạng dd/MM/yyyy
	public static String convertDate(String ngayccanchuyen) {
		String result = "";
		try {
			result = ngayccanchuyen.substring(8, 10) + "/" + ngayccanchuyen.substring(5, 7) + "/"
					+ ngayccanchuyen.substring(0, 4);
		} catch (Exception ie) {
			result = "";
		}
		return result;
	}

	// -------------------------------------------------------------------------------------------
	// định dạng ngày giờ lấy từ sql (yyyy-MM-dd hh:mm:ss.ttt) thành dạng dd/MM/yyyy
	// hh:mm:ss.ttt
	public static String convertDateTime(String ngayccanchuyen) {
		String result = "";
		try {
			result = ngayccanchuyen.substring(8, 10) + "/" + ngayccanchuyen.substring(5, 7) + "/"
					+ ngayccanchuyen.substring(0, 4) + " " + ngayccanchuyen.substring(11, 19);
		} catch (Exception ie) {
			result = "";
		}
		return result;
	}

	// -------------------------------------------------------------------------------------------
	// date hien tai - date1 = ?bao nhiêu năm, date1, date2 có dạng yyyy-MM-dd -
	// trong SQL
	public static String getThoiGianSuDung(String date1) {
		// trả về năm sử dụng

		String result = "";
		int ngay1 = 0, thang1 = 0, nam1 = 0, ngay2 = 0, thang2 = 0, nam2 = 0;

		LocalDate date = java.time.LocalDate.now();
		nam2 = date.getYear();
		thang2 = date.getMonthValue();
		ngay2 = date.getDayOfMonth();

		double nam = 0;
		try {
			ngay1 = Integer.parseInt(date1.substring(8, 10));
			thang1 = Integer.parseInt(date1.substring(5, 7));
			nam1 = Integer.parseInt(date1.substring(0, 4));

			nam = (double) ((nam2 - nam1) * 365 + (nam2 - nam1) / 4 + (thang2 - thang1) * 30 + (ngay2 - ngay1)) / 365;
			nam = Math.ceil(nam);
			result = (int) nam + "";
		} catch (Exception ie) {
			result = "";
		}

		return result;
	}

	public static String saveDateToSQL(DateTime datetime) {
		String month = "0" + (datetime.getMonth() + 1);
		month = month.substring(month.length() - 2);

		String day = "0" + datetime.getDay();
		day = day.substring(day.length() - 2);

		String ngay = datetime.getYear() + month + day;

		return ngay;
	}

	// set date for DateTime
	public static void setDateTime(DateTime datetime, String date) {
		// date có dạng dd/MM/yyyy 03/06/2021
		try {
			datetime.setDate(Integer.parseInt(date.substring(6, 10)), Integer.parseInt(date.substring(3, 5)) - 1,
					Integer.parseInt(date.substring(0, 2)));
		} catch (Exception e) {

		}
	}

	// set date for DateTime from Date SQL
	public static void setDateTimefromSQL(DateTime datetime, String datesql) {
		// date có dạng yyyy-MM-dd 2021-06-12
		try {
			datetime.setDate(Integer.parseInt(datesql.substring(0, 4)), Integer.parseInt(datesql.substring(5, 7)) - 1,
					Integer.parseInt(datesql.substring(8, 10)));
		} catch (Exception e) {

		}
	}

	// chuyen ngay thanh chuoi de insert sql
	public static String convertDateToString(String date) {
		// date có dạng dd/MM/yyyy 03/06/2021 chuyển sang chuỗi yyyyMMdd 20210603 để lưu
		// vào SQL
		try {
			return date.substring(6, 10) + date.substring(3, 5) + date.substring(0, 2);
		} catch (Exception e) {
			return "19900101";
		}
	}

	// trả về số ngày, lấy ngày hiện tại trừ đi ngày date
	public static int getCountDate(String date) {
		// date có dạng yyyy-MM-dd, ví sụ: 2021-06-28
		int result = 0;
		try {
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));

			LocalDate datenow = java.time.LocalDate.now();
			int yearnow = datenow.getYear();
			int monthnow = datenow.getMonthValue();
			int daynow = datenow.getDayOfMonth();

			result = (yearnow - year) * 365 + (monthnow - month) * 30 + (daynow - day);
		} catch (Exception e) {
			return -1;
		}
		return result;
	}
}
