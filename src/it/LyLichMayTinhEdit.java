package it;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
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

import date.ConvertDate;
import sql.ConnectSQL;

public class LyLichMayTinhEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textDonVi;
	private int ngonngu = 1;
	private Text textNguoiSuDung;
	private Text textSoThe;
	private Text textMaSoDonVi;
	private CCombo comboRAMDungLuong;
	private CCombo comboRAMSoLuong;
	private Text textThoiGianBaoHanh;
	private CCombo comboCPULoai;
	private CCombo comboMainboardLoai;
	private CCombo comboOCung;
	private CCombo comboRAMLoai;
	private CCombo comboManHinhLoai;
	private CCombo comboUPSLoai;
	private CCombo comboMayInLoai;
	private DateTime dateTimeCPUNgaySuDung;
	private DateTime dateTimeMainboardNgaySuDung;
	private DateTime dateTimeManHinhNgaySuDung;
	private DateTime dateTimeUPSNgaySuDung;
	private DateTime dateTimeMayInNgaySuDung;
	private DateTime dateTimeNgaySuDung;
	private DateTime dateTimeNgayHetBaoHanh;
	private String userlogin = "21608";
	private String malylichsua = "";
	private boolean iseditsuccess = false;

	public static void main(String[] args) {
		try {
			LyLichMayTinhEdit window = new LyLichMayTinhEdit();
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
		shell.setImage(SWTResourceManager.getImage(LyLichMayTinhEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(996, 581);
		shell.setText("Edit computer profile ");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Composite composite = new Composite(scrolledComposite, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);
		scrolledComposite.setContent(composite);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(10, 25, 129, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textSoThe.setBounds(152, 25, 204, 30);

		CLabel lbNguoiSuDung = new CLabel(composite, SWT.RIGHT);
		lbNguoiSuDung.setText("Người sử dụng");
		lbNguoiSuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiSuDung.setBounds(367, 25, 191, 30);

		textNguoiSuDung = new Text(composite, SWT.BORDER);
		textNguoiSuDung.setTextLimit(50);
		textNguoiSuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiSuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textNguoiSuDung.setBounds(564, 25, 389, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 75, 129, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setBounds(152, 75, 395, 30);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textDonVi.setTextLimit(50);

		CLabel lbMaSoDonVi = new CLabel(composite, SWT.RIGHT);
		lbMaSoDonVi.setText("Mã số đơn vị");
		lbMaSoDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMaSoDonVi.setBounds(553, 75, 179, 30);

		textMaSoDonVi = new Text(composite, SWT.BORDER);
		textMaSoDonVi.setTextLimit(12);
		textMaSoDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaSoDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textMaSoDonVi.setBounds(750, 75, 203, 30);

		CLabel lbCPULoai = new CLabel(composite, SWT.RIGHT);
		lbCPULoai.setBounds(10, 125, 129, 30);
		lbCPULoai.setText("CPU loại");
		lbCPULoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboCPULoai = new CCombo(composite, SWT.BORDER);
		comboCPULoai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboCPULoai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboCPULoai.setBounds(152, 125, 238, 30);

		CLabel lbCPUNgaySuDung = new CLabel(composite, SWT.RIGHT);
		lbCPUNgaySuDung.setText("CPU ngày sử dụng");
		lbCPUNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbCPUNgaySuDung.setBounds(413, 125, 204, 30);

		dateTimeCPUNgaySuDung = new DateTime(composite, SWT.BORDER);
		dateTimeCPUNgaySuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dateTimeCPUNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeCPUNgaySuDung.setBounds(639, 125, 132, 30);

		CLabel lblMainboardLoai = new CLabel(composite, SWT.RIGHT);
		lblMainboardLoai.setText("Mainboard loại");
		lblMainboardLoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lblMainboardLoai.setBounds(10, 175, 132, 30);

		comboMainboardLoai = new CCombo(composite, SWT.BORDER);
		comboMainboardLoai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboMainboardLoai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboMainboardLoai.setBounds(152, 175, 238, 30);

		CLabel lblMainboardNgaySuDung = new CLabel(composite, SWT.RIGHT);
		lblMainboardNgaySuDung.setText("Mainboard ngày sử dụng");
		lblMainboardNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lblMainboardNgaySuDung.setBounds(400, 175, 217, 30);

		dateTimeMainboardNgaySuDung = new DateTime(composite, SWT.BORDER);
		dateTimeMainboardNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeMainboardNgaySuDung.setBounds(639, 175, 132, 30);

		CLabel lbOCung = new CLabel(composite, SWT.RIGHT);
		lbOCung.setText("Ổ cứng");
		lbOCung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbOCung.setBounds(10, 225, 129, 30);

		comboOCung = new CCombo(composite, SWT.BORDER);
		comboOCung.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboOCung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboOCung.setBounds(152, 225, 238, 30);

		CLabel lbRAMLoai = new CLabel(composite, SWT.RIGHT);
		lbRAMLoai.setText("RAM loại");
		lbRAMLoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbRAMLoai.setBounds(10, 275, 129, 30);

		comboRAMLoai = new CCombo(composite, SWT.BORDER);
		comboRAMLoai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboRAMLoai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboRAMLoai.setBounds(152, 275, 238, 30);

		CLabel lblRamDungLuong = new CLabel(composite, SWT.RIGHT);
		lblRamDungLuong.setText("RAM dung lượng");
		lblRamDungLuong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lblRamDungLuong.setBounds(396, 275, 150, 30);

		comboRAMDungLuong = new CCombo(composite, SWT.BORDER);
		comboRAMDungLuong.setItems(new String[] { "1 GB", "2 GB", "4 GB", "8 GB", "16 GB", "32 GB" });
		comboRAMDungLuong.setTextLimit(6);
		comboRAMDungLuong.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboRAMDungLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboRAMDungLuong.setBounds(552, 275, 112, 30);

		CLabel lblRamSoLuong = new CLabel(composite, SWT.RIGHT);
		lblRamSoLuong.setText("RAM số lượng");
		lblRamSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lblRamSoLuong.setBounds(671, 275, 146, 30);

		comboRAMSoLuong = new CCombo(composite, SWT.BORDER);
		comboRAMSoLuong.setItems(new String[] { "1", "2", "3", "4" });
		comboRAMSoLuong.setTextLimit(2);
		comboRAMSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboRAMSoLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboRAMSoLuong.setBounds(823, 275, 130, 30);
		comboRAMSoLuong.setText("1");

		CLabel lbManHinhLoai = new CLabel(composite, SWT.RIGHT);
		lbManHinhLoai.setText("Màn hình loại");
		lbManHinhLoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbManHinhLoai.setBounds(10, 325, 129, 30);

		comboManHinhLoai = new CCombo(composite, SWT.BORDER);
		comboManHinhLoai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboManHinhLoai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboManHinhLoai.setBounds(152, 325, 238, 30);

		CLabel lbManHinhNgaySuDung = new CLabel(composite, SWT.RIGHT);
		lbManHinhNgaySuDung.setText("Màn hình ngày sử dụng");
		lbManHinhNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbManHinhNgaySuDung.setBounds(400, 325, 217, 30);

		dateTimeManHinhNgaySuDung = new DateTime(composite, SWT.BORDER);
		dateTimeManHinhNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeManHinhNgaySuDung.setBounds(639, 325, 132, 30);

		CLabel lbUPSLoai = new CLabel(composite, SWT.RIGHT);
		lbUPSLoai.setText("UPS loại");
		lbUPSLoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbUPSLoai.setBounds(10, 375, 129, 30);

		comboUPSLoai = new CCombo(composite, SWT.BORDER);
		comboUPSLoai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboUPSLoai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboUPSLoai.setBounds(152, 375, 238, 30);

		CLabel lbUPSNgaySuDung = new CLabel(composite, SWT.RIGHT);
		lbUPSNgaySuDung.setText("UPS Ngày sử dụng");
		lbUPSNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbUPSNgaySuDung.setBounds(400, 375, 217, 30);

		dateTimeUPSNgaySuDung = new DateTime(composite, SWT.BORDER);
		dateTimeUPSNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeUPSNgaySuDung.setBounds(639, 375, 132, 30);

		CLabel lbMayInLoai = new CLabel(composite, SWT.RIGHT);
		lbMayInLoai.setText("Máy in loại");
		lbMayInLoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMayInLoai.setBounds(10, 425, 129, 30);

		comboMayInLoai = new CCombo(composite, SWT.BORDER);
		comboMayInLoai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboMayInLoai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboMayInLoai.setBounds(152, 425, 238, 30);

		CLabel lbMayInNgaySuDung = new CLabel(composite, SWT.RIGHT);
		lbMayInNgaySuDung.setText("Máy in ngày sử dụng");
		lbMayInNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMayInNgaySuDung.setBounds(400, 425, 217, 30);

		dateTimeMayInNgaySuDung = new DateTime(composite, SWT.BORDER);
		dateTimeMayInNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeMayInNgaySuDung.setBounds(639, 425, 132, 30);

		CLabel lbNgaySuDung = new CLabel(composite, SWT.RIGHT);
		lbNgaySuDung.setText("Ngày sử dụng");
		lbNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgaySuDung.setBounds(10, 475, 129, 30);

		dateTimeNgaySuDung = new DateTime(composite, SWT.BORDER);
		dateTimeNgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgaySuDung.setBounds(152, 475, 132, 30);

		CLabel lbThoiGianBaoHanh = new CLabel(composite, SWT.RIGHT);
		lbThoiGianBaoHanh.setText("Thời gian bảo hành");
		lbThoiGianBaoHanh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGianBaoHanh.setBounds(297, 475, 185, 30);

		textThoiGianBaoHanh = new Text(composite, SWT.BORDER);
		textThoiGianBaoHanh.setTextLimit(4);
		textThoiGianBaoHanh.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textThoiGianBaoHanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textThoiGianBaoHanh.setBounds(488, 475, 80, 30);
		textThoiGianBaoHanh.setText("0");

		CLabel lblNgay = new CLabel(composite, SWT.RIGHT);
		lblNgay.setAlignment(SWT.LEFT);
		lblNgay.setText("ngày");
		lblNgay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.ITALIC));
		lblNgay.setBounds(574, 475, 64, 30);

		CLabel lbNgayHetBaoHanh = new CLabel(composite, SWT.RIGHT);
		lbNgayHetBaoHanh.setText("Ngày hết bảo hành");
		lbNgayHetBaoHanh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayHetBaoHanh.setBounds(644, 475, 171, 30);

		dateTimeNgayHetBaoHanh = new DateTime(composite, SWT.BORDER);
		dateTimeNgayHetBaoHanh.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgayHetBaoHanh.setBounds(820, 475, 132, 30);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(LyLichMayTinhEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(820, 375, 132, 35);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(LyLichMayTinhEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(820, 425, 132, 35);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Sửa lý lịch máy tính");
			lbSoThe.setText("Số thẻ");
			lbNguoiSuDung.setText("Người sử dụng");
			lbDonVi.setText("Đơn vị");
			lbMaSoDonVi.setText("Mã số đơn vị");
			lbCPULoai.setText("CPU loại");
			lbCPUNgaySuDung.setText("CPU ngày sử dụng");
			lblMainboardLoai.setText("Mainboard loại");
			lblMainboardNgaySuDung.setText("Mainboard ngày sử dụng");
			lbOCung.setText("Ổ cứng");
			lbRAMLoai.setText("RAM loại");
			lblRamDungLuong.setText("RAM dung lượng");
			lblRamSoLuong.setText("RAM số lượng");
			lbManHinhLoai.setText("Màn hình loại");
			lbManHinhNgaySuDung.setText("Màn hình ngày sử dụng");
			lbUPSLoai.setText("UPS loại");
			lbUPSNgaySuDung.setText("UPS Ngày sử dụng");
			lbMayInLoai.setText("Máy in loại");
			lbMayInNgaySuDung.setText("Máy in ngày sử dụng");
			lbNgaySuDung.setText("Ngày sử dụng");
			lbThoiGianBaoHanh.setText("Thời gian bảo hành");
			lblNgay.setText("ngày");
			lbNgayHetBaoHanh.setText("Ngày hết bảo hành");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
		} else {
			// Tieng Anh
			shell.setText("Edit Computer profile");
			lbSoThe.setText("ID");
			lbNguoiSuDung.setText("User");
			lbDonVi.setText("Department");
			lbMaSoDonVi.setText("Department id");
			lbCPULoai.setText("CPU type");
			lbCPUNgaySuDung.setText("CPU use date");
			lblMainboardLoai.setText("Mainboard type");
			lblMainboardNgaySuDung.setText("Mainboard use date");
			lbOCung.setText("Hard drive");
			lbRAMLoai.setText("RAM type");
			lblRamDungLuong.setText("RAM capacity");
			lblRamSoLuong.setText("RAM count");
			lbManHinhLoai.setText("Monitor type");
			lbManHinhNgaySuDung.setText("Monitor use date");
			lbUPSLoai.setText("UPS type");
			lbUPSNgaySuDung.setText("UPS use date");
			lbMayInLoai.setText("Printer type");
			lbMayInNgaySuDung.setText("Printer use date");
			lbNgaySuDung.setText("Use date");
			lbThoiGianBaoHanh.setText("Warranty period");
			lblNgay.setText("day");
			lbNgayHetBaoHanh.setText("Warranty expiry date");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
		}

		// text RAM số lượng chỉ cho nhập số
		comboRAMSoLuong.addVerifyListener(new VerifyListener() {
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

		// text thời gian bảo hành chỉ cho nhập số
		textThoiGianBaoHanh.addVerifyListener(new VerifyListener() {
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
							+ textSoThe.getText() + "' OR Data_Person.Person_ID='0" + textSoThe.getText()
							+ "' OR Data_Person.Person_ID='00" + textSoThe.getText() + "')";

					PreparedStatement ps = connect.getConnection().prepareStatement(select);

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = ps.executeQuery();

					textNguoiSuDung.setText("");
					textDonVi.setText("");

					while (result.next()) {
						textNguoiSuDung.setText(result.getString(1));
						textDonVi.setText(result.getString(2));
					}
					result.close();

				} catch (SQLException se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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

		// lấy dữ liệu cho combo
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [LoaiThietBi],[TenLoaiThietBi] FROM [dbo].[IT_DanhSachThietBiMayTinh] ORDER BY [LoaiThietBi] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				if (result.getString(2).equalsIgnoreCase("CPU")) {
					comboCPULoai.add(result.getString(1));
				} else if (result.getString(2).equalsIgnoreCase("Mainboard")) {
					comboMainboardLoai.add(result.getString(1));
				} else if (result.getString(2).equalsIgnoreCase("Hard Drive")) {
					comboOCung.add(result.getString(1));
				} else if (result.getString(2).equalsIgnoreCase("RAM")) {
					comboRAMLoai.add(result.getString(1));
				} else if (result.getString(2).equalsIgnoreCase("Monitor")) {
					comboManHinhLoai.add(result.getString(1));
				} else if (result.getString(2).equalsIgnoreCase("UPS")) {
					comboUPSLoai.add(result.getString(1));
				} else if (result.getString(2).equalsIgnoreCase("Printer")) {
					comboMayInLoai.add(result.getString(1));
				}
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!textDonVi.getText().isEmpty()) {

					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}

						String update = "UPDATE [dbo].[IT_LyLichMayTinh] SET [DonVi] = N'" + textDonVi.getText()
								+ "',[MaSoDonVi] = '" + textMaSoDonVi.getText() + "',[CPU_Loai] = '"
								+ comboCPULoai.getText() + "',[CPU_NgaySuDung] = '"
								+ ConvertDate.saveDateToSQL(dateTimeCPUNgaySuDung) + "',[Mainboard_Loai] = '"
								+ comboMainboardLoai.getText() + "',[Mainboard_NgaySuDung] = '"
								+ ConvertDate.saveDateToSQL(dateTimeMainboardNgaySuDung) + "',[OCung] = '"
								+ comboOCung.getText() + "',[RAM_Loai] = '" + comboRAMLoai.getText()
								+ "',[RAM_DungLuong] = '" + comboRAMDungLuong.getText() + "',[RAM_SoLuong] = "
								+ comboRAMSoLuong.getText() + ",[ManHinh_Loai] = '" + comboManHinhLoai.getText()
								+ "',[ManHinh_NgaySuDung] = '" + ConvertDate.saveDateToSQL(dateTimeManHinhNgaySuDung)
								+ "',[UPS_Loai] = '" + comboUPSLoai.getText() + "',[UPS_NgaySuDung] = '"
								+ ConvertDate.saveDateToSQL(dateTimeUPSNgaySuDung) + "',[MayIn_Loai] = '"
								+ comboMayInLoai.getText() + "',[MayIn_NgaySuDung] = '"
								+ ConvertDate.saveDateToSQL(dateTimeMayInNgaySuDung) + "',[NgaySuDung] = '"
								+ ConvertDate.saveDateToSQL(dateTimeNgaySuDung) + "',[ThoiGianBaoHanh] = '"
								+ textThoiGianBaoHanh.getText() + "',[NgayHetHanBaoHanh] = '"
								+ ConvertDate.saveDateToSQL(dateTimeNgayHetBaoHanh) + "',[NguoiSuDung] = N'"
								+ textNguoiSuDung.getText() + "',[SoThe] = '" + textSoThe.getText()
								+ "',[NguoiCapNhat] = '" + userlogin + "' WHERE [MaLyLich] = '" + malylichsua + "'";

						int result = connect.execUpdateQuery(update);
						if (result > 0) {
							iseditsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Sửa thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Edit suscessful");
							}
							thongbao.open();
							shell.dispose();
						}
						connect.closeStatement();
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
				} else {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Sửa không thành công!");
					} else {
						thongbao.setText("Notice");
						thongbao.setMessage("Edit failed!");
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
	public void setData(int language, String user, String malylich, String id, String nguoisudung, String donvi,
			String masodonvi, String cpuloai, String cpungaysudung, String mainboardloai, String mainboardngaysudung,
			String ocung, String ramloai, String ramdungluong, String ramsoluong, String manhinhloai,
			String manhinhngaysudung, String upsloai, String upsngaysudung, String mayinloai, String mayinngaysudung,
			String ngaysudung, String thoigianbaohanh, String ngayhetbaohanh) {
		ngonngu = language;
		userlogin = user;
		malylichsua = malylich;
		textSoThe.setText(id);
		textNguoiSuDung.setText(nguoisudung);
		textDonVi.setText(donvi);
		textMaSoDonVi.setText(masodonvi);
		comboCPULoai.setText(cpuloai);
		ConvertDate.setDateTime(dateTimeCPUNgaySuDung, cpungaysudung);
		comboMainboardLoai.setText(mainboardloai);
		ConvertDate.setDateTime(dateTimeMainboardNgaySuDung, mainboardngaysudung);
		comboOCung.setText(ocung);
		comboRAMLoai.setText(ramloai);
		comboRAMDungLuong.setText(ramdungluong);
		comboRAMSoLuong.setText(ramsoluong);
		comboManHinhLoai.setText(manhinhloai);
		ConvertDate.setDateTime(dateTimeManHinhNgaySuDung, manhinhngaysudung);
		comboUPSLoai.setText(upsloai);
		ConvertDate.setDateTime(dateTimeUPSNgaySuDung, upsngaysudung);
		comboMayInLoai.setText(mayinloai);
		ConvertDate.setDateTime(dateTimeMayInNgaySuDung, mayinngaysudung);
		ConvertDate.setDateTime(dateTimeNgaySuDung, ngaysudung);
		textThoiGianBaoHanh.setText(thoigianbaohanh);
		ConvertDate.setDateTime(dateTimeNgayHetBaoHanh, ngayhetbaohanh);
	}

	// kiểm tra xem có sửa thành công hay không
	public boolean isEditSuccess() {
		return iseditsuccess;
	}
}
