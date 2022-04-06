package accessories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.layout.RowData;
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
import org.eclipse.swt.custom.CCombo;

public class MuaThietBiChoDonViKhac {
	private Table table;
	private TableItem[] itemtable;
	private String[] mamuathietbixoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Text textSoThe;
	private Text textSoBPM;
	private Text textHoTen;
	private int ngonngu = 1;
	private Text textSoLuong;
	private CCombo comboDonVi;

	private Button btnCheckNgayLamPhieu;
	private Button btnCheckNgayVe;
	private Button btnCheckNgayThayThe;

	private DateTime dateTimeNgayLamPhieu1;
	private DateTime dateTimeNgayLamPhieu2;
	private DateTime dateTimeNgayVe1;
	private DateTime dateTimeNgayVe2;
	private DateTime dateTimeNgayNhapThayThe1;
	private DateTime dateTimeNgayNhapThayThe2;

	private String userlogin = "";
	private String grouplogin = ""; // nếu là User thì không cho xóa
	private Text textNoiDung;

	public static void main(String[] args) {
		try {
			MuaThietBiChoDonViKhac window = new MuaThietBiChoDonViKhac();
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

	/**
	 * Create contents of the window.
	 */

	protected void createShell() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Buy device");
		if (ngonngu == 0) {
			shell.setText("Mua thiết bị cho các đơn vị");
		} else {
			shell.setText("Buy device for departments");
		}

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(new FillLayout());
	}

	protected void createContents(Composite com, Shell shell) {
		Composite composite = new Composite(com, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setBounds(10, 15, 107, 30);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setBounds(123, 15, 160, 30);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setTextLimit(20);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(289, 15, 91, 30);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setBounds(387, 15, 80, 30);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(473, 15, 92, 30);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setBounds(571, 15, 212, 30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setTextLimit(50);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(789, 15, 129, 30);

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(924, 15, 248, 30);

		CLabel lbNoiDung = new CLabel(composite, SWT.RIGHT);
		lbNoiDung.setText("Nội dung");
		lbNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNoiDung.setBounds(10, 60, 107, 30);

		textNoiDung = new Text(composite, SWT.BORDER);
		textNoiDung.setTextLimit(80);
		textNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNoiDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNoiDung.setBounds(123, 60, 160, 30);

		CLabel lbSoLuong = new CLabel(composite, SWT.RIGHT);
		lbSoLuong.setText("Số lượng");
		lbSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoLuong.setBounds(289, 60, 92, 30);

		textSoLuong = new Text(composite, SWT.BORDER);
		textSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoLuong.setBounds(387, 60, 80, 30);
		textSoLuong.setTextLimit(4);

		dateTimeNgayLamPhieu1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayLamPhieu1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayLamPhieu1.setBounds(571, 60, 129, 30);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-30);
		dateTimeNgayLamPhieu1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbdaunga1 = new CLabel(composite, SWT.RIGHT);
		lbdaunga1.setAlignment(SWT.CENTER);
		lbdaunga1.setText("~");
		lbdaunga1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga1.setBounds(706, 60, 26, 30);

		dateTimeNgayLamPhieu2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayLamPhieu2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayLamPhieu2.setBounds(738, 60, 129, 30);

		btnCheckNgayLamPhieu = new Button(composite, SWT.CHECK);
		btnCheckNgayLamPhieu.setText("Ngày làm phiếu");
		btnCheckNgayLamPhieu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckNgayLamPhieu.setBounds(880, 60, 176, 30);

		dateTimeNgayVe1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayVe1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayVe1.setBounds(38, 105, 129, 30);
		dateTimeNgayVe1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbdaunga2 = new CLabel(composite, SWT.RIGHT);
		lbdaunga2.setText("~");
		lbdaunga2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga2.setAlignment(SWT.CENTER);
		lbdaunga2.setBounds(173, 105, 26, 30);

		dateTimeNgayVe2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayVe2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayVe2.setBounds(205, 105, 129, 30);

		btnCheckNgayVe = new Button(composite, SWT.CHECK);
		btnCheckNgayVe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckNgayVe.setBounds(350, 105, 190, 30);
		btnCheckNgayVe.setText("Ngày về");

		dateTimeNgayNhapThayThe1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayNhapThayThe1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayNhapThayThe1.setBounds(571, 105, 129, 30);
		dateTimeNgayNhapThayThe1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbdaunga3 = new CLabel(composite, SWT.RIGHT);
		lbdaunga3.setText("~");
		lbdaunga3.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga3.setAlignment(SWT.CENTER);
		lbdaunga3.setBounds(706, 105, 26, 30);

		dateTimeNgayNhapThayThe2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayNhapThayThe2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayNhapThayThe2.setBounds(738, 105, 129, 30);

		btnCheckNgayThayThe = new Button(composite, SWT.CHECK);
		btnCheckNgayThayThe.setText("Ngày thay thế");
		btnCheckNgayThayThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckNgayThayThe.setBounds(880, 105, 176, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1180, 15, 149, 32);

		Button btnXuat = new Button(composite, SWT.NONE);
		btnXuat.setText("Xuất");
		btnXuat.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/button/excel25.png"));
		btnXuat.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnXuat.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXuat.setBounds(1335, 15, 110, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(1062, 60, 110, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(1180, 60, 110, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(1298, 60, 110, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1062, 105, 110, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhac.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1180, 105, 110, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 150);
		table.setSize(1900, 968);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 260);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setResizable(false);
		tbcSTT.setWidth(50);
		tbcSTT.setText("STT");

		TableColumn tbcMaMuaThietBi = new TableColumn(table, SWT.NONE);
		tbcMaMuaThietBi.setWidth(0);
		tbcMaMuaThietBi.setText("Mã mua thiết bị");
		tbcMaMuaThietBi.setResizable(false);

		TableColumn tbcSoBPM = new TableColumn(table, SWT.NONE);
		tbcSoBPM.setWidth(165);
		tbcSoBPM.setText("Số BPM");

		TableColumn tbcNoiDung = new TableColumn(table, SWT.NONE);
		tbcNoiDung.setWidth(146);
		tbcNoiDung.setText("Nội dung");

		TableColumn tbcSoLuong = new TableColumn(table, SWT.NONE);
		tbcSoLuong.setWidth(83);
		tbcSoLuong.setText("Số lượng");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(82);
		tbcSoThe.setText("Số thẻ");

		TableColumn tbcHoTen = new TableColumn(table, SWT.NONE);
		tbcHoTen.setWidth(213);
		tbcHoTen.setText("Họ tên");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(185);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcNgayLamPhieu = new TableColumn(table, SWT.NONE);
		tbcNgayLamPhieu.setWidth(157);
		tbcNgayLamPhieu.setText("Ngày làm phiếu");

		TableColumn tbcNgayTongVuNhanPhieu = new TableColumn(table, SWT.NONE);
		tbcNgayTongVuNhanPhieu.setWidth(220);
		tbcNgayTongVuNhanPhieu.setText("Ngày tổng vụ nhận phiếu");

		TableColumn tbcNgayVe = new TableColumn(table, SWT.NONE);
		tbcNgayVe.setWidth(157);
		tbcNgayVe.setText("Ngày về");

		TableColumn tbcNgayThayThe = new TableColumn(table, SWT.NONE);
		tbcNgayThayThe.setWidth(157);
		tbcNgayThayThe.setText("Ngày thay thế");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(94);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(157);
		tbcNguoiCapNhat.setText("Người cập nhật");

		dateTimeNgayLamPhieu1.setEnabled(false);
		dateTimeNgayLamPhieu2.setEnabled(false);
		dateTimeNgayVe1.setEnabled(false);
		dateTimeNgayVe2.setEnabled(false);
		dateTimeNgayNhapThayThe1.setEnabled(false);
		dateTimeNgayNhapThayThe2.setEnabled(false);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbSoBPM.setText("Số BPM");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbNoiDung.setText("Nội dung");
			lbSoLuong.setText("Số lượng");
			btnCheckNgayLamPhieu.setText("Ngày làm phiếu");
			btnCheckNgayVe.setText("Ngày về");
			btnCheckNgayThayThe.setText("Ngày thay thế");
			btntimkiem.setText("Tìm kiếm");
			btnXuat.setText("Xuất");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

			tbcSTT.setText("STT");
			tbcMaMuaThietBi.setText("Mã mua thiết bị");
			tbcSoBPM.setText("Số BPM");
			tbcNoiDung.setText("Nội dung");
			tbcSoLuong.setText("Số lượng");
			tbcSoThe.setText("Số thẻ");
			tbcHoTen.setText("Họ tên");
			tbcDonVi.setText("Đơn vị");
			tbcNgayLamPhieu.setText("Ngày làm phiếu");
			tbcNgayTongVuNhanPhieu.setText("Ngày tổng vụ nhận phiếu");
			tbcNgayVe.setText("Ngày về");
			tbcNgayThayThe.setText("Ngày thay thế");
			tbcGhiChu.setText("Ghi chú");
			tbcNguoiCapNhat.setText("Người cập nhật");

		} else {
			// Tieng Anh
			lbSoBPM.setText("BPM number");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbNoiDung.setText("Content");
			lbSoLuong.setText("Count");
			btnCheckNgayLamPhieu.setText("Writing date");
			btnCheckNgayVe.setText("Return day");
			btnCheckNgayThayThe.setText("Replacement date");
			btntimkiem.setText("Search");
			btnXuat.setText("Export");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Num");
			tbcMaMuaThietBi.setText("Device purchase code");
			tbcSoBPM.setText("BPM number");
			tbcNoiDung.setText("Content");
			tbcSoLuong.setText("Count");
			tbcSoThe.setText("ID");
			tbcHoTen.setText("Name");
			tbcDonVi.setText("Department");
			tbcNgayLamPhieu.setText("Writing date");
			tbcNgayTongVuNhanPhieu.setText("Purchaser receives the order");
			tbcNgayVe.setText("Return day");
			tbcNgayThayThe.setText("Replacement date");
			tbcGhiChu.setText("Note");
			tbcNguoiCapNhat.setText("Person update");

		}

		// nếu là User thì không cho xóa
		if (grouplogin.equals("User")) {
			btnxoa.setVisible(false);
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

		// text số lượng chỉ cho nhập số
		textSoLuong.addVerifyListener(new VerifyListener() {
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

		// Event phím tắt (ctrl + A)
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt (ctrl + A) chọn hết bảng
				if (e.keyCode == 'a' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					table.selectAll();
				}
			}
		});

		// bắt sự kiện check vào checkbox Ngày làm phiếu
		btnCheckNgayLamPhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckNgayLamPhieu.getSelection()) {
					dateTimeNgayLamPhieu1.setEnabled(true);
					dateTimeNgayLamPhieu2.setEnabled(true);
				} else {
					dateTimeNgayLamPhieu1.setEnabled(false);
					dateTimeNgayLamPhieu2.setEnabled(false);
				}
			}
		});

		// bắt sự kiện check vào checkbox Ngày về
		btnCheckNgayVe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckNgayVe.getSelection()) {
					dateTimeNgayVe1.setEnabled(true);
					dateTimeNgayVe2.setEnabled(true);
				} else {
					dateTimeNgayVe1.setEnabled(false);
					dateTimeNgayVe2.setEnabled(false);
				}
			}
		});

		// bắt sự kiện check vào checkbox Ngày thay thế
		btnCheckNgayThayThe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckNgayThayThe.getSelection()) {
					dateTimeNgayNhapThayThe1.setEnabled(true);
					dateTimeNgayNhapThayThe2.setEnabled(true);
				} else {
					dateTimeNgayNhapThayThe1.setEnabled(false);
					dateTimeNgayNhapThayThe2.setEnabled(false);
				}
			}
		});

		// Tìm kiếm
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search();
			}
		});

		// Tìm kiếm sau khi enter ở text So BPM
		// ******************************************************************************
		textSoBPM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở button tìm kiếm
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text so the
		// ******************************************************************************
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text ho ten
		// ******************************************************************************
		textHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở combo đơn vị
		// ******************************************************************************
		comboDonVi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text nội dung
		// ******************************************************************************
		textNoiDung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text Số lượng
		// ******************************************************************************
		textSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Xuất Excel
		// ******************************************************************************
		btnXuat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Mua thiết bị");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Buy device");
				}
			}
		});

		// Thêm-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MuaThietBiChoDonViKhacAdd muathietbimoi = new MuaThietBiChoDonViKhacAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				muathietbimoi.setData(ngonngu, userlogin);
				muathietbimoi.open();
				composite.setEnabled(true);
				if (muathietbimoi.isAddSuccess()) {
					search();
				}
			}
		});

		// Sửa---------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String soBPM = "";
					soBPM = itemsua[0].getText(2);
					if (!soBPM.isEmpty()) {
						MuaThietBiChoDonViKhacEdit sua = new MuaThietBiChoDonViKhacEdit();
						// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
						composite.setEnabled(false);
						sua.setData(ngonngu, userlogin);
						sua.setSoBPMvaNgayLamPhieu(soBPM, itemsua[0].getText(8));
						sua.createContents();
						sua.open();
						composite.setEnabled(true);
						if (sua.isEditSuccess()) {
							search();
						}
					}

				} catch (Exception ex) {
				}
			}
		});

		// Xóa-------------------------------------------------------------------
		btnxoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Lấy cột số mã thiết bị
					itemtable = table.getSelection();
					mamuathietbixoa = new String[itemtable.length];

					for (int i = 0; i < mamuathietbixoa.length; i++) {
						mamuathietbixoa[i] = itemtable[i].getText(1);
					}
					if (mamuathietbixoa.length > 0) {
						btnthem.setEnabled(false);
						btnsua.setEnabled(false);
						btntimkiem.setEnabled(false);
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
					btnthem.setEnabled(true);
					btnsua.setEnabled(true);
					btntimkiem.setEnabled(true);
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
					try {
						String delete = "";
						for (int i = 0; i < mamuathietbixoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[LK_MuaLinhKien] WHERE [MaMuaThietBi]='"
									+ mamuathietbixoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							search();
						}
						connect.closeStatement();
						btnthem.setEnabled(true);
						btnsua.setEnabled(true);
						btntimkiem.setEnabled(true);
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
				btnthem.setEnabled(true);
				btnsua.setEnabled(true);
				btntimkiem.setEnabled(true);
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

		tbcSoBPM.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNoiDung.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoLuong.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoTen.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayLamPhieu.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgayTongVuNhanPhieu.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgayVe.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgayThayThe.addListener(SWT.Selection, sort.sortListenerDate);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiCapNhat.addListener(SWT.Selection, sort.sortListenerCode);

	}

	// search
	// --------------------------------------------------------------------------
	public void search() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();
			table.removeAll();

			String sothe = textSoThe.getText().isEmpty() ? "" : " AND [SoThe]='" + textSoThe.getText() + "'";
			String donvi = comboDonVi.getText().isEmpty() ? "" : " AND [DonVi]=N'" + comboDonVi.getText()+"'";
			String soluong = textSoLuong.getText().isEmpty() ? "" : " AND [SoLuong]=" + textSoLuong.getText();

			// ngày làm phiếu
			String monthlamphieu = "0" + (dateTimeNgayLamPhieu1.getMonth() + 1);
			monthlamphieu = monthlamphieu.substring(monthlamphieu.length() - 2);
			String daylamphieu = "0" + dateTimeNgayLamPhieu1.getDay();
			daylamphieu = daylamphieu.substring(daylamphieu.length() - 2);
			String ngaybatdaubihu = dateTimeNgayLamPhieu1.getYear() + monthlamphieu + daylamphieu;

			String monthlamphieu2 = "0" + (dateTimeNgayLamPhieu2.getMonth() + 1);
			monthlamphieu2 = monthlamphieu2.substring(monthlamphieu2.length() - 2);
			String daylamphieu2 = "0" + dateTimeNgayLamPhieu2.getDay();
			daylamphieu2 = daylamphieu2.substring(daylamphieu2.length() - 2);
			String ngaybihuketthuc = dateTimeNgayLamPhieu2.getYear() + monthlamphieu2 + daylamphieu2;

			String ngaylamphieu = " AND [LK_MuaLinhKien].[NgayLamPhieu] BETWEEN '" + ngaybatdaubihu + "' AND '"
					+ ngaybihuketthuc + "'";

			if (!btnCheckNgayLamPhieu.getSelection()) {
				ngaylamphieu = ""; // nếu không tìm theo ngày làm phiếu
			}

			// ngày về
			String monthve = "0" + (dateTimeNgayVe1.getMonth() + 1);
			monthve = monthve.substring(monthve.length() - 2);
			String dayve = "0" + dateTimeNgayVe1.getDay();
			dayve = dayve.substring(dayve.length() - 2);
			String ngaymuabatdau = dateTimeNgayVe1.getYear() + monthve + dayve;

			String monthve2 = "0" + (dateTimeNgayVe2.getMonth() + 1);
			monthve2 = monthve2.substring(monthve2.length() - 2);
			String dayve2 = "0" + dateTimeNgayVe2.getDay();
			dayve2 = dayve2.substring(dayve2.length() - 2);
			String ngaymuaketthuc = dateTimeNgayVe2.getYear() + monthve2 + dayve2;

			String ngayve = " AND [LK_MuaLinhKien].[NgayVe] BETWEEN '" + ngaymuabatdau + "' AND '" + ngaymuaketthuc
					+ "'";
			if (!btnCheckNgayVe.getSelection()) {
				ngayve = ""; // nếu không tìm theo ngày về
			}

			// ngày thay thế
			String monththaythe = "0" + (dateTimeNgayNhapThayThe1.getMonth() + 1);
			monththaythe = monththaythe.substring(monththaythe.length() - 2);
			String daythaythe = "0" + dateTimeNgayNhapThayThe1.getDay();
			daythaythe = daythaythe.substring(daythaythe.length() - 2);
			String ngaybatdauthaythe = dateTimeNgayNhapThayThe1.getYear() + monththaythe + daythaythe;

			String monththaythe2 = "0" + (dateTimeNgayNhapThayThe2.getMonth() + 1);
			monththaythe2 = monththaythe2.substring(monththaythe2.length() - 2);
			String daythaythe2 = "0" + dateTimeNgayNhapThayThe2.getDay();
			daythaythe2 = daythaythe2.substring(daythaythe2.length() - 2);
			String ngaythaytheketthuc = dateTimeNgayNhapThayThe2.getYear() + monththaythe2 + daythaythe2;

			String ngaythaythe = " AND [dbo].[BP_ThayTheLinhKien].[NgayThayThe] BETWEEN '" + ngaybatdauthaythe
					+ "' AND '" + ngaythaytheketthuc + "'";

			if (!btnCheckNgayThayThe.getSelection()) {
				ngaythaythe = ""; // nếu không tìm theo ngày thay
			}

			String select = "SELECT [MaMuaThietBi],[SoBPM],[NoiDung],[SoLuong],[SoThe],[HoTen],[DonVi],[NgayLamPhieu],[NgayTongVuNhanPhieu],[NgayVe],[NgayThayThe],[GhiChu],[NguoiDung].[TenNguoiDung] FROM [dbo].[LK_MuaLinhKien] LEFT JOIN [dbo].[NguoiDung] ON [LK_MuaLinhKien].[NguoiCapNhat]=[NguoiDung].[MaNguoiDung] OR [LK_MuaLinhKien].[NguoiCapNhat]=[NguoiDung].[TenDangNhap] WHERE [SoBPM] LIKE '%"
					+ textSoBPM.getText() + "%' AND [NoiDung] LIKE N'%" + textNoiDung.getText()
					+ "%' AND [HoTen] LIKE N'%" + textHoTen.getText() + "%'" + sothe + soluong + donvi + ngaylamphieu
					+ ngayve + ngaythaythe + " ORDER BY [MaMuaThietBi] DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						ConvertDate.convertDate(result.getString(8)), ConvertDate.convertDate(result.getString(9)),
						ConvertDate.convertDate(result.getString(10)), ConvertDate.convertDate(result.getString(11)),
						result.getString(12), result.getString(13) });
				stt++;
			}

			result.close();
			state.close();
			connect.closeStatement();

		} catch (Exception se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi");
				thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
			} else {
				thongbao.setText("Notice error");
				thongbao.setMessage("Connect error!\n" + se.toString());
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
			itemtab.setText("Mua thiết bị cho các đơn vị");
		} else {
			itemtab.setText("Buy device for departments");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);
		createContents(composite, shell);
	}
}
