package accessories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import date.ConvertDate;
import excel.Excel;
import sort.SortData;
import sql.ConnectSQL;

public class ThayTheLinhKien {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textLinhKienHu;
	private int ngonngu = 1;
	private Table table;
	private TableItem[] itemtable;
	private String[] mathaythexoa;
	private String userlogin = "";
	private String grouplogin = "";// là user thì không cho sửa, xóa, và xuất excel

	private Composite compositeshell;
	private Text textSoThe;
	private Text textHoTen;
	private CCombo comboNguoiThay;
	private CCombo comboDonVi;

	private DateTime dateTimeNgayBiHu1;
	private DateTime dateTimeNgayBiHu2;
	private DateTime dateTimeNgayMua1;
	private DateTime dateTimeNgayMua2;
	private DateTime dateTimeNgayThay1;
	private DateTime dateTimeNgayThay2;

	private Button btnTimKiem;
	private Button btnThem;
	private Button btnSua;
	private Button btnXoa;
	private Button btnLuu;
	private Button btnHuy;

	private Button btnNgayBiHu;
	private Button btnNgayMua;
	private Button btnNgayThay;

	private TableColumn tbcMaThayThe;

	private Text textLinhKienMoi;
	private TableColumn tbcSoBPM;
	private CLabel lbSoBPM;
	private Text textSoBPM;

	public static void main(String[] args) {
		try {
			ThayTheLinhKien window = new ThayTheLinhKien();
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
		createShell();
		createContents(compositeshell, shell);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createShell() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1782, 804);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Thay thế linh kiện");
		} else {
			shell.setText("Replacement accessories");
		}

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		compositeshell.setLayout(new FillLayout());
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Composite com, Shell shell) {
		Composite composite = new Composite(com, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(10, 20, 61, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(77, 20, 61, 30);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHoTen.setBounds(144, 20, 112, 30);

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setTextLimit(50);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(262, 20, 134, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(402, 20, 112, 30);

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(521, 20, 178, 30);
		comboDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbNguoiThay = new CLabel(composite, SWT.RIGHT);
		lbNguoiThay.setText("Người thay");
		lbNguoiThay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiThay.setBounds(705, 20, 140, 30);

		comboNguoiThay = new CCombo(composite, SWT.BORDER);
		comboNguoiThay.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNguoiThay.setBounds(852, 20, 150, 30);
		comboNguoiThay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoBPM.setBounds(1008, 20, 107, 30);

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setTextLimit(50);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setBounds(1121, 20, 134, 30);

		Button btnExcel = new Button(composite, SWT.NONE);
		btnExcel.setVisible(true);
		btnExcel.setText("Xuất");
		btnExcel.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnExcel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExcel.setBounds(1261, 20, 112, 32);

		CLabel lbLinhKienHu = new CLabel(composite, SWT.RIGHT);
		lbLinhKienHu.setBounds(10, 60, 140, 30);
		lbLinhKienHu.setText("Linh kiện hư");
		lbLinhKienHu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textLinhKienHu = new Text(composite, SWT.BORDER);
		textLinhKienHu.setBounds(156, 60, 164, 30);
		textLinhKienHu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textLinhKienHu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textLinhKienHu.setTextLimit(80);

		dateTimeNgayBiHu1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayBiHu1.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgayBiHu1.setBounds(326, 60, 120, 30);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-365);
		dateTimeNgayBiHu1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel btnNgayBiHuDauNga = new CLabel(composite, SWT.RIGHT);
		btnNgayBiHuDauNga.setText("~");
		btnNgayBiHuDauNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnNgayBiHuDauNga.setAlignment(SWT.CENTER);
		btnNgayBiHuDauNga.setBounds(454, 60, 20, 30);

		dateTimeNgayBiHu2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayBiHu2.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgayBiHu2.setBounds(480, 60, 120, 30);

		btnNgayBiHu = new Button(composite, SWT.CHECK);
		btnNgayBiHu.setText("Ngày hư");
		btnNgayBiHu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnNgayBiHu.setBounds(610, 60, 145, 30);

		dateTimeNgayMua1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayMua1.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgayMua1.setBounds(761, 60, 134, 30);
		dateTimeNgayMua1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel btnNgayMuaDauNga = new CLabel(composite, SWT.RIGHT);
		btnNgayMuaDauNga.setText("~");
		btnNgayMuaDauNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnNgayMuaDauNga.setAlignment(SWT.CENTER);
		btnNgayMuaDauNga.setBounds(901, 60, 20, 30);

		dateTimeNgayMua2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayMua2.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgayMua2.setBounds(927, 60, 129, 30);

		btnNgayMua = new Button(composite, SWT.CHECK);
		btnNgayMua.setText("Ngày mua");
		btnNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnNgayMua.setBounds(1064, 60, 129, 30);

		btnTimKiem = new Button(composite, SWT.NONE);
		btnTimKiem.setVisible(true);
		btnTimKiem.setText("Tìm kiếm");
		btnTimKiem
				.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/button/search-30.png"));
		btnTimKiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnTimKiem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTimKiem.setBounds(1223, 58, 150, 32);

		CLabel lbLinhKienMoi = new CLabel(composite, SWT.RIGHT);
		lbLinhKienMoi.setText("Linh kiện mới");
		lbLinhKienMoi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLinhKienMoi.setBounds(10, 100, 140, 30);

		textLinhKienMoi = new Text(composite, SWT.BORDER);
		textLinhKienMoi.setTextLimit(80);
		textLinhKienMoi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textLinhKienMoi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textLinhKienMoi.setBounds(156, 100, 164, 30);

		dateTimeNgayThay1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayThay1.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgayThay1.setBounds(326, 100, 120, 30);
		dateTimeNgayThay1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel btnNgayThayDauNga = new CLabel(composite, SWT.RIGHT);
		btnNgayThayDauNga.setText("~");
		btnNgayThayDauNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnNgayThayDauNga.setAlignment(SWT.CENTER);
		btnNgayThayDauNga.setBounds(454, 100, 20, 30);

		dateTimeNgayThay2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayThay2.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		dateTimeNgayThay2.setBounds(480, 100, 120, 30);

		btnNgayThay = new Button(composite, SWT.CHECK);
		btnNgayThay.setText("Ngày thay");
		btnNgayThay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnNgayThay.setBounds(610, 100, 145, 30);

		btnThem = new Button(composite, SWT.NONE);
		btnThem.setVisible(true);
		btnThem.setText("Thêm");
		btnThem.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/button/add30.png"));
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnThem.setBounds(758, 100, 120, 32);

		btnSua = new Button(composite, SWT.NONE);
		btnSua.setVisible(true);
		btnSua.setText("Sửa");
		btnSua.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/button/edit.png"));
		btnSua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		btnSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnSua.setBounds(883, 100, 120, 32);

		btnXoa = new Button(composite, SWT.NONE);
		btnXoa.setVisible(true);
		btnXoa.setText("Xóa");
		btnXoa.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/button/delete20.png"));
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXoa.setBounds(1009, 100, 120, 32);

		btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1135, 100, 120, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);

		btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThayTheLinhKien.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1260, 100, 131, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnHuy.setVisible(true);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setHeaderBackground(SWTResourceManager.getColor(154, 205, 50));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(10, 140, 1900, 830);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(45);
		tbcSTT.setText("STT");
		tbcSTT.setResizable(false);

		tbcMaThayThe = new TableColumn(table, SWT.NONE);
		tbcMaThayThe.setWidth(0);
		tbcMaThayThe.setResizable(false);
		tbcMaThayThe.setText("Mã thay thế");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(72);
		tbcSoThe.setText("Số Thẻ");

		TableColumn tbcHoten = new TableColumn(table, SWT.NONE);
		tbcHoten.setWidth(198);
		tbcHoten.setText("Họ tên");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(169);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcLinhKienHu = new TableColumn(table, SWT.NONE);
		tbcLinhKienHu.setWidth(177);
		tbcLinhKienHu.setText("Linh kiện hư");

		TableColumn tbcNgayHu = new TableColumn(table, SWT.NONE);
		tbcNgayHu.setWidth(130);
		tbcNgayHu.setText("Ngày hư");

		TableColumn tbcLinhKienMoi = new TableColumn(table, SWT.NONE);
		tbcLinhKienMoi.setWidth(184);
		tbcLinhKienMoi.setText("Linh kiện mới");

		TableColumn tbcNgayMua = new TableColumn(table, SWT.NONE);
		tbcNgayMua.setWidth(129);
		tbcNgayMua.setText("Ngày mua");

		tbcSoBPM = new TableColumn(table, SWT.NONE);
		tbcSoBPM.setWidth(129);
		tbcSoBPM.setText("Số BPM");

		TableColumn tbcNgayThayThe = new TableColumn(table, SWT.NONE);
		tbcNgayThayThe.setWidth(155);
		tbcNgayThayThe.setText("Ngày thay thế");

		TableColumn tbcNguoiThay = new TableColumn(table, SWT.NONE);
		tbcNguoiThay.setWidth(170);
		tbcNguoiThay.setText("Người thay");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(166);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(154);
		tbcNguoiCapNhat.setText("Người cập nhật");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);
		dateTimeNgayBiHu1.setEnabled(false);
		dateTimeNgayBiHu2.setEnabled(false);
		dateTimeNgayMua1.setEnabled(false);
		dateTimeNgayMua2.setEnabled(false);
		dateTimeNgayThay1.setEnabled(false);
		dateTimeNgayThay2.setEnabled(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbNguoiThay.setText("Người thay");
			lbSoBPM.setText("Số BPM");
			btnExcel.setText("Xuất");
			lbLinhKienHu.setText("Linh kiện hư");
			btnNgayBiHu.setText("Ngày hư");
			btnNgayMua.setText("Ngày mua");
			btnTimKiem.setText("Tìm kiếm");
			lbLinhKienMoi.setText("Linh kiện mới");
			btnNgayThay.setText("Ngày thay");
			btnThem.setText("Thêm");
			btnSua.setText("Sửa");
			btnXoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			
			tbcSTT.setText("STT");
			tbcMaThayThe.setText("Mã thay thế");
			tbcSoThe.setText("Số Thẻ");
			tbcHoten.setText("Họ tên");
			tbcDonVi.setText("Đơn vị");
			tbcLinhKienHu.setText("Linh kiện hư");
			tbcNgayHu.setText("Ngày hư");
			tbcLinhKienMoi.setText("Linh kiện mới");
			tbcNgayMua.setText("Ngày mua");
			tbcSoBPM.setText("Số BPM");
			tbcNgayThayThe.setText("Ngày thay thế");
			tbcNguoiThay.setText("Người thay");
			tbcGhiChu.setText("Ghi chú");
			tbcNguoiCapNhat.setText("Người cập nhật");

		} else {
			// Tieng Anh
			lbSoThe.setText("ID");
			lbHoTen.setText("Person name");
			lbDonVi.setText("Department");
			lbNguoiThay.setText("Person replace");
			lbSoBPM.setText("Number BPM");
			btnExcel.setText("Export");
			lbLinhKienHu.setText("Broken accessories");
			btnNgayBiHu.setText("Broken day");
			btnNgayMua.setText("Purchase date");
			btnTimKiem.setText("Search");
			lbLinhKienMoi.setText("New accessories");
			btnNgayThay.setText("Replacement date");
			btnThem.setText("Add");
			btnSua.setText("Edit");
			btnXoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			
			tbcSTT.setText("Num");
			tbcMaThayThe.setText("ID replace");
			tbcSoThe.setText("ID");
			tbcHoten.setText("Person name");
			tbcDonVi.setText("Department");
			tbcLinhKienHu.setText("Broken accessories");
			tbcNgayHu.setText("Broken day");
			tbcLinhKienMoi.setText("New accessories");
			tbcNgayMua.setText("Purchase date");
			tbcSoBPM.setText("Number BPM");
			tbcNgayThayThe.setText("Replacement date");
			tbcNguoiThay.setText("Person replace");
			tbcGhiChu.setText("Note");
			tbcNguoiCapNhat.setText("Person update");

		}

		// kiểm tra nếu grouplogin là User thì không cho thêm, sửa, xóa, xuất Excel
		if (grouplogin.equals("User")) {
			btnSua.setVisible(false);
			btnXoa.setVisible(false);
			btnExcel.setVisible(false);
		}

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

		// khi chọn button check ngày hư thì cho chọn ngày tháng
		btnNgayBiHu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNgayBiHu.getSelection()) {
					dateTimeNgayBiHu1.setEnabled(true);
					dateTimeNgayBiHu2.setEnabled(true);
				} else {
					dateTimeNgayBiHu1.setEnabled(false);
					dateTimeNgayBiHu2.setEnabled(false);
				}
			}
		});

		// khi chọn button check ngày mua thì cho chọn ngày tháng
		btnNgayMua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNgayMua.getSelection()) {
					dateTimeNgayMua1.setEnabled(true);
					dateTimeNgayMua2.setEnabled(true);
				} else {
					dateTimeNgayMua1.setEnabled(false);
					dateTimeNgayMua2.setEnabled(false);
				}
			}
		});

		// khi chọn button check ngày thay thì cho chọn ngày tháng
		btnNgayThay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNgayThay.getSelection()) {
					dateTimeNgayThay1.setEnabled(true);
					dateTimeNgayThay2.setEnabled(true);
				} else {
					dateTimeNgayThay1.setEnabled(false);
					dateTimeNgayThay2.setEnabled(false);
				}
			}
		});

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

		// tìm kiếm sau khi Enter ở text số thẻ
		// ----------------------------------------------------------------------------------------------------------
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text họ tên
		// ----------------------------------------------------------------------------------------------------------
		textHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở combo đơn vị
		// ----------------------------------------------------------------------------------------------------------
		comboDonVi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở combo người thay
		// ----------------------------------------------------------------------------------------------------------
		comboNguoiThay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm
		// ----------------------------------------------------------------------------------------------------------
		btnTimKiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// tìm kiếm sau khi Enter ở btn tìm kiếm
		// ----------------------------------------------------------------------------------------------------------
		btnTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text linh kiện hư
		// ----------------------------------------------------------------------------------------------------------
		textLinhKienHu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text linh kiện mới
		// ----------------------------------------------------------------------------------------------------------
		textLinhKienMoi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Excel
		// ----------------------------------------------------------------------------------------------------------
		btnExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Thay thế linh kiện");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Replacement accessories");
				}
			}
		});

		// Export sau khi enter ở button Export Excel
		// ******************************************************************************
		btnExcel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (ngonngu == 0) {
						Excel.exportExcelTable(table, shell, ngonngu, "Thay thế linh kiện");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "Replacement accessories");
					}
				}
			}
		});

		// thêm
		// -----------------------------------------------------------------------------------------------------------
		btnThem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ThayTheLinhKienAdd thaythelinhkien = new ThayTheLinhKienAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				thaythelinhkien.setData(ngonngu, userlogin);
				thaythelinhkien.createContents();
				thaythelinhkien.open();
				composite.setEnabled(true);

				if (thaythelinhkien.isAddSuccess()) {
					search(shell);
				}
			}
		});

		// Sửa
		// ------------------------------------------------------------------------------------------------------------
		btnSua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table.getSelection();
				if (item.length > 0) {
					composite.setEnabled(false);
					ThayTheLinhKienEdit thaythelinhkien = new ThayTheLinhKienEdit();
					thaythelinhkien.setLanguage(ngonngu, userlogin);
					thaythelinhkien.createContents();
					thaythelinhkien.setData(item[0].getText(1), item[0].getText(2), item[0].getText(3),
							item[0].getText(4), item[0].getText(5), item[0].getText(6), item[0].getText(7),
							item[0].getText(8), item[0].getText(9), item[0].getText(10), item[0].getText(11),
							item[0].getText(12));
					thaythelinhkien.open();
					composite.setEnabled(true);
					if (thaythelinhkien.isEditSuccess()) {
						search(shell);
					}
				}
			}
		});

		// Xóa
		// ------------------------------------------------------------------------------------------------------------
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Lấy cột mã thay thế
					itemtable = table.getSelection();
					mathaythexoa = new String[itemtable.length];

					for (int i = 0; i < mathaythexoa.length; i++) {
						mathaythexoa[i] = itemtable[i].getText(1);
					}
					if (mathaythexoa.length > 0) {
						btnTimKiem.setEnabled(false);
						btnExcel.setEnabled(false);
						btnThem.setEnabled(false);
						btnSua.setEnabled(false);
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						table.setEnabled(false);
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Không có dòng nào được chọn!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("No line is selected!");
						}
						thongbao.open();
					}

				} catch (Exception ex) {
					btnTimKiem.setEnabled(true);
					btnExcel.setEnabled(true);
					btnThem.setEnabled(true);
					btnSua.setEnabled(true);
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
					table.setEnabled(true);
				}
			}
		});

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();

					// Xóa
					try {
						String delete = "";
						for (int i = 0; i < mathaythexoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[LK_ThayTheLinhKien] WHERE [MaThayThe]='"
									+ mathaythexoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						connect.closeStatement();
						btnTimKiem.setEnabled(true);
						btnExcel.setEnabled(true);
						btnThem.setEnabled(true);
						btnSua.setEnabled(true);
						btnXoa.setEnabled(true);
						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						table.setEnabled(true);
					} catch (Exception ex) {
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
				btnTimKiem.setEnabled(true);
				btnExcel.setEnabled(true);
				btnThem.setEnabled(true);
				btnSua.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoten.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcLinhKienHu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayHu.addListener(SWT.Selection, sort.sortListenerDate);
		tbcLinhKienMoi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayMua.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgayThayThe.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNguoiThay.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiCapNhat.addListener(SWT.Selection, sort.sortListenerCode);
	}

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String user) {
		ngonngu = language;
	}

	// search
	// --------------------------------------------------------------------------
	public void search(Shell shell) {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();
			table.removeAll();

			String sothe = textSoThe.getText().isEmpty() ? "" : " AND [SoThe]='" + textSoThe.getText() + "'";
			String hoten = textHoTen.getText().isEmpty() ? "" : " AND [HoTen] LIKE N'%" + textHoTen.getText() + "%' ";
			String donvi = comboDonVi.getText().isEmpty() ? "" : " AND [DonVi]=N'" + comboDonVi.getText() + "' ";
			String nguoithay = comboNguoiThay.getText().isEmpty() ? ""
					: " AND [NguoiThay]=N'" + comboNguoiThay.getText() + "'";
			String linhkienhu = textLinhKienHu.getText().isEmpty() ? ""
					: " AND [LK_ThayTheLinhKien].[TenLinhKienHu] LIKE N'%" + textLinhKienHu.getText() + "%'";
			String linhkienmoi = textLinhKienMoi.getText().isEmpty() ? ""
					: " AND [LK_ThayTheLinhKien].[TenLinhKienMoi] LIKE N'%" + textLinhKienMoi.getText() + "%'";

			// ngày mua
			String monthbuy = "0" + (dateTimeNgayMua1.getMonth() + 1);
			monthbuy = monthbuy.substring(monthbuy.length() - 2);
			String daybuy = "0" + dateTimeNgayMua1.getDay();
			daybuy = daybuy.substring(daybuy.length() - 2);
			String ngaymuabatdau = dateTimeNgayMua1.getYear() + monthbuy + daybuy;

			String monthbuy2 = "0" + (dateTimeNgayMua2.getMonth() + 1);
			monthbuy2 = monthbuy2.substring(monthbuy2.length() - 2);
			String daybuy2 = "0" + dateTimeNgayMua2.getDay();
			daybuy2 = daybuy2.substring(daybuy2.length() - 2);
			String ngaymuaketthuc = dateTimeNgayMua2.getYear() + monthbuy2 + daybuy2;

			String ngaymua = " AND [dbo].[LK_ThayTheLinhKien].[NgayMua] BETWEEN '" + ngaymuabatdau + "' AND '"
					+ ngaymuaketthuc + "'";
			if (!btnNgayMua.getSelection()) {
				ngaymua = ""; // nếu không tìm theo ngày mua
			}

			// ngày bị hư
			String monthbihu = "0" + (dateTimeNgayBiHu1.getMonth() + 1);
			monthbihu = monthbihu.substring(monthbihu.length() - 2);
			String daybihu = "0" + dateTimeNgayBiHu1.getDay();
			daybihu = daybihu.substring(daybihu.length() - 2);
			String ngaybatdaubihu = dateTimeNgayBiHu1.getYear() + monthbihu + daybihu;

			String monthbihu2 = "0" + (dateTimeNgayBiHu2.getMonth() + 1);
			monthbihu2 = monthbihu2.substring(monthbihu2.length() - 2);
			String daybihu2 = "0" + dateTimeNgayBiHu2.getDay();
			daybihu2 = daybihu2.substring(daybihu2.length() - 2);
			String ngaybihuketthuc = dateTimeNgayBiHu2.getYear() + monthbihu2 + daybihu2;

			String ngaybihu = " AND [dbo].[LK_ThayTheLinhKien].[NgayBiHu] BETWEEN '" + ngaybatdaubihu + "' AND '"
					+ ngaybihuketthuc + "'";

			if (!btnNgayBiHu.getSelection()) {
				ngaybihu = ""; // nếu không tìm theo ngày hư
			}

			// ngày thay thế
			String monththaythe = "0" + (dateTimeNgayThay1.getMonth() + 1);
			monththaythe = monththaythe.substring(monththaythe.length() - 2);
			String daythaythe = "0" + dateTimeNgayThay1.getDay();
			daythaythe = daythaythe.substring(daythaythe.length() - 2);
			String ngaybatdauthaythe = dateTimeNgayThay1.getYear() + monththaythe + daythaythe;

			String monththaythe2 = "0" + (dateTimeNgayThay2.getMonth() + 1);
			monththaythe2 = monththaythe2.substring(monththaythe2.length() - 2);
			String daythaythe2 = "0" + dateTimeNgayThay2.getDay();
			daythaythe2 = daythaythe2.substring(daythaythe2.length() - 2);
			String ngaythaytheketthuc = dateTimeNgayThay2.getYear() + monththaythe2 + daythaythe2;

			String ngaythaythe = " AND [dbo].[LK_ThayTheLinhKien].[NgayThayThe] BETWEEN '" + ngaybatdauthaythe
					+ "' AND '" + ngaythaytheketthuc + "'";

			if (!btnNgayThay.getSelection()) {
				ngaythaythe = ""; // nếu không tìm theo ngày thay
			}

			String select = "SELECT [LK_ThayTheLinhKien].[MaThayThe],[LK_ThayTheLinhKien].[SoThe],[LK_ThayTheLinhKien].[HoTen],[LK_ThayTheLinhKien].[DonVi],[LK_ThayTheLinhKien].[TenLinhKienHu],[LK_ThayTheLinhKien].[NgayBiHu],[LK_ThayTheLinhKien].[TenLinhKienMoi],[LK_ThayTheLinhKien].[NgayMua],[LK_ThayTheLinhKien].[SoBPM],[LK_ThayTheLinhKien].[NgayThayThe],[LK_ThayTheLinhKien].[NguoiThay],[LK_ThayTheLinhKien].[GhiChu],[NguoiDung].[TenNguoiDung] FROM [dbo].[LK_ThayTheLinhKien] LEFT JOIN [dbo].[NguoiDung] ON [NguoiDung].[MaNguoiDung]=[LK_ThayTheLinhKien].[NguoiCapNhat] OR [NguoiDung].[TenDangNhap]=[LK_ThayTheLinhKien].[NguoiCapNhat] WHERE [LK_ThayTheLinhKien].[SoBPM] LIKE '%"
					+ textSoBPM.getText() + "%'" + sothe + hoten + donvi + nguoithay + linhkienhu + linhkienmoi
					+ ngaymua + ngaybihu + ngaythaythe + " ORDER BY [LK_ThayTheLinhKien].[NgayBiHu] DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), ConvertDate.convertDate(result.getString(6)),
						result.getString(7), ConvertDate.convertDate(result.getString(8)), result.getString(9),
						ConvertDate.convertDate(result.getString(10)), result.getString(11), result.getString(12),
						result.getString(13) });
				stt++;
			}

			result.close();
			state.close();
			connect.closeStatement();

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

	// Hiện trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu, String userlogin, String grouplogin) {
		this.ngonngu = ngonngu;
		this.userlogin = userlogin;
		this.grouplogin = grouplogin;

		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Thay thế linh kiện");
		} else {
			itemtab.setText("Replacement accessories");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);
		createContents(composite, shell);
	}
}
