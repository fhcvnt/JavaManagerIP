package accessories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;
import date.ConvertDate;

public class ThayTheLinhKienEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoThe;
	private int ngonngu = 0;
	private DateTime dateTimeNgayMua;
	private Text textGhiChu;
	private String userlogin = "";
	private boolean iseditsuccess = false;
	private String mathaythe = "";

	private Text textHoTen;
	private Text textLinhKienHu;
	private Text textLinhKienMoi;
	private CCombo comboDonVi;
	private DateTime dateTimeNgayHu;
	private Button btnCheckNgayMua;
	private DateTime dateTimeNgayThay;
	private Button btnCheckNgayThay;
	private CCombo comboNguoiThay;
	private CLabel lbSoBPM;
	private Text textSoBPM;

	public static void main(String[] args) {
		try {
			ThayTheLinhKienEdit window = new ThayTheLinhKienEdit();
			window.createContents();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(ThayTheLinhKienEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1142, 522);
		shell.setText("Sửa thay thế linh kiện");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(10, 20, 156, 30);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setBounds(172, 20, 100, 30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setTextLimit(6);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHoTen.setBounds(280, 20, 88, 30);

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setTextLimit(50);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(374, 20, 241, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(627, 20, 140, 30);

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(773, 20, 286, 30);
		comboDonVi.setEditable(false);

		CLabel lbLinhKienHu = new CLabel(composite, SWT.RIGHT);
		lbLinhKienHu.setText("Linh kiện hư");
		lbLinhKienHu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLinhKienHu.setBounds(10, 70, 156, 30);

		textLinhKienHu = new Text(composite, SWT.BORDER);
		textLinhKienHu.setTextLimit(80);
		textLinhKienHu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textLinhKienHu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textLinhKienHu.setBounds(172, 70, 443, 30);

		CLabel lbNgayHu = new CLabel(composite, SWT.RIGHT);
		lbNgayHu.setText("Ngày hư");
		lbNgayHu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayHu.setBounds(620, 70, 150, 30);

		dateTimeNgayHu = new DateTime(composite, SWT.BORDER);
		dateTimeNgayHu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayHu.setBounds(776, 70, 130, 30);

		CLabel lbLinhKienMoi = new CLabel(composite, SWT.RIGHT);
		lbLinhKienMoi.setText("Linh kiện mới");
		lbLinhKienMoi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLinhKienMoi.setBounds(10, 120, 156, 30);

		textLinhKienMoi = new Text(composite, SWT.BORDER);
		textLinhKienMoi.setTextLimit(80);
		textLinhKienMoi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textLinhKienMoi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textLinhKienMoi.setBounds(172, 120, 296, 30);

		dateTimeNgayMua = new DateTime(composite, SWT.BORDER);
		dateTimeNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayMua.setBounds(485, 120, 130, 30);

		btnCheckNgayMua = new Button(composite, SWT.CHECK);
		btnCheckNgayMua.setText("Ngày mua");
		btnCheckNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		btnCheckNgayMua.setBounds(630, 120, 140, 30);

		dateTimeNgayThay = new DateTime(composite, SWT.BORDER);
		dateTimeNgayThay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayThay.setBounds(776, 120, 130, 30);

		btnCheckNgayThay = new Button(composite, SWT.CHECK);
		btnCheckNgayThay.setText("Ngày thay");
		btnCheckNgayThay.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		btnCheckNgayThay.setBounds(919, 120, 179, 30);

		CLabel lbNguoiThay = new CLabel(composite, SWT.RIGHT);
		lbNguoiThay.setText("Người thay");
		lbNguoiThay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiThay.setBounds(10, 170, 156, 30);

		comboNguoiThay = new CCombo(composite, SWT.BORDER);
		comboNguoiThay.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNguoiThay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboNguoiThay.setBounds(172, 170, 296, 30);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi chú");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(16, 271, 150, 30);

		textGhiChu = new Text(composite, SWT.BORDER | SWT.MULTI);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(172, 220, 734, 150);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThayTheLinhKienEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(338, 390, 160, 35);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThayTheLinhKienEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(547, 390, 160, 35);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		dateTimeNgayMua.setEnabled(false);
		dateTimeNgayThay.setEnabled(false);

		lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoBPM.setBounds(474, 170, 141, 30);

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setTextLimit(20);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setBounds(620, 170, 286, 30);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Sửa thay thế linh kiện");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbLinhKienHu.setText("Linh kiện hư");
			lbNgayHu.setText("Ngày hư");
			lbLinhKienMoi.setText("Linh kiện mới");
			btnCheckNgayMua.setText("Ngày mua");
			lbSoBPM.setText("Số BPM");
			btnCheckNgayThay.setText("Ngày thay");
			lbNguoiThay.setText("Người thay");
			lbGhiChu.setText("Ghi chú");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

		} else {
			// Tieng Anh
			shell.setText("Edit Replacement accessories");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbLinhKienHu.setText("Broken accessories");
			lbNgayHu.setText("Broken day");
			lbLinhKienMoi.setText("New accessories");
			btnCheckNgayMua.setText("Purchase date");
			lbSoBPM.setText("Numer BPM");
			btnCheckNgayThay.setText("Replacement date");
			lbNguoiThay.setText("Person replace");
			lbGhiChu.setText("Note");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

		}

		// text số thẻ chỉ cho nhập số
		textSoThe.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		// lấy dữ liệu cho combo đơn vị
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [Department_Name] FROM [SV4].[HRIS].[dbo].[Data_Department] WHERE [Department_Name] NOT LIKE N'Z%' ORDER BY [Department_Name] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboDonVi.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// lấy dữ liệu cho combo người thay
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [TenNguoiDung] FROM [dbo].[NguoiDung] WHERE [TenNhom]!='Disable' ORDER BY [TenNguoiDung] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboNguoiThay.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị dựa vào số thẻ
		// ---------------------------------------------------------------------------------------------------
		textSoThe.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					String select = "";
					ResultSet result;

					select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
							+ textSoThe.getText() + "')";

					PreparedStatement ps = connect.getConnection().prepareStatement(select);

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = ps.executeQuery();

					while (result.next()) {
						textHoTen.setText(result.getString(1));
						comboDonVi.setText(result.getString(2));
					}
					result.close();

				} catch (SQLException se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo lỗi");
						thongbao.setMessage("Lỗi!\n" + se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Error!\n" + se.toString());
					}
					thongbao.open();
				}
			}
		});

		// khi chọn button check ngày mua thì cho chọn ngày tháng
		btnCheckNgayMua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckNgayMua.getSelection()) {
					dateTimeNgayMua.setEnabled(true);
				} else {
					dateTimeNgayMua.setEnabled(false);
				}
			}
		});

		// khi chọn button check ngày thay thì cho chọn ngày tháng
		btnCheckNgayThay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckNgayThay.getSelection()) {
					dateTimeNgayThay.setEnabled(true);
				} else {
					dateTimeNgayThay.setEnabled(false);
				}
			}
		});

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!comboDonVi.getText().isEmpty() && !textLinhKienHu.getText().isEmpty()) {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}
						connect.setStatement();

						String ngaymua = btnCheckNgayMua.getSelection()
								? "'" + ConvertDate.saveDateToSQL(dateTimeNgayMua) + "'"
								: "NULL";
						String ngaythay = btnCheckNgayThay.getSelection()
								? "'" + ConvertDate.saveDateToSQL(dateTimeNgayThay) + "'"
								: "NULL";

						String update = "UPDATE [dbo].[LK_ThayTheLinhKien] SET [SoThe] = '" + textSoThe.getText()
								+ "',[HoTen] = N'" + textHoTen.getText() + "',[DonVi] = N'" + comboDonVi.getText()
								+ "',[TenLinhKienHu] = N'" + textLinhKienHu.getText() + "',[NgayBiHu] = '"
								+ ConvertDate.saveDateToSQL(dateTimeNgayHu) + "',[TenLinhKienMoi] = N'"
								+ textLinhKienMoi.getText() + "',[NgayMua] = " + ngaymua + ",[SoBPM]='"
								+ textSoBPM.getText() + "',[NgayThayThe] = " + ngaythay + ",[NguoiThay] = N'"
								+ comboNguoiThay.getText() + "',[GhiChu] = N'" + textGhiChu.getText()
								+ "',[NguoiCapNhat] = '" + userlogin + "' WHERE [MaThayThe] = '" + mathaythe + "'";

						int result = connect.execUpdateQuery(update);
						if (result > 0) {
							iseditsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Sửa thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Edit suscess!");
							}
							thongbao.open();

							shell.dispose();
						}

						connect.closeStatement();
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Đơn vị rỗng hoặc linh kiện hư rỗng!!");
						} else {
							thongbao.setText("Error");
							thongbao.setMessage("Department is empty or Broken accessories is empty!");
						}
						thongbao.open();
					}
				} catch (Exception se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo lỗi");
						thongbao.setMessage("Lỗi!\n" + se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Error!\n" + se.toString());
					}
					thongbao.open();
				}
			}
		});

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}

		});
	}

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------
	public void setData(String mathaythe, String sothe, String hoten, String donvi, String linhkienhu, String ngayhu,
			String linhkienmoi, String ngaymua, String sobpm, String ngaythay, String nguoithay, String ghichu) {
		this.mathaythe = mathaythe;
		try {
			textSoThe.setText(sothe);
			textHoTen.setText(hoten);
			comboDonVi.setText(donvi);
			textLinhKienHu.setText(linhkienhu);
			ConvertDate.setDateTime(dateTimeNgayHu, ngayhu);
			textLinhKienMoi.setText(linhkienmoi);
			if (!ngaymua.isEmpty()) {
				ConvertDate.setDateTime(dateTimeNgayMua, ngaymua);
				dateTimeNgayMua.setEnabled(true);
				btnCheckNgayMua.setSelection(true);
			}
			textSoBPM.setText(sobpm);
			if (!ngaythay.isEmpty()) {
				ConvertDate.setDateTime(dateTimeNgayThay, ngaythay);
				dateTimeNgayThay.setEnabled(true);
				btnCheckNgayThay.setSelection(true);
			}
			comboNguoiThay.setText(nguoithay);
			textGhiChu.setText(ghichu);
		} catch (Exception e) {

		}
	}

	// kiểm tra xem có thêm thành công không
	public boolean isEditSuccess() {
		return iseditsuccess;
	}
}
