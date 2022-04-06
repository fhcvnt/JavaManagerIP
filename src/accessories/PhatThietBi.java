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

public class PhatThietBi {
	private Table table;
	private TableItem[] itemtable;
	private String[] maphatthietbixoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Text textSoBPM;
	private int ngonngu = 0;
	private CCombo comboDanhMuc;
	private Button btnCheckThoiGianNhan;
	private DateTime dateTimeThoiGianNhan1;
	private DateTime dateTimeThoiGianNhan2;
	private CCombo comboTinhTrang;
	private CCombo comboDonVi;

	private String userlogin = "21608";
	private String grouplogin = ""; // nếu là User thì không cho xóa
	private Text textTenThietBi;
	private Text textSoThe;
	private Text textNguoiNhan;

	public static void main(String[] args) {
		try {
			PhatThietBi window = new PhatThietBi();
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
		shell.setText("Phát thiết bị");
		shell.setImage(SWTResourceManager.getImage(PhatThietBi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Phát thiết bị");
		} else {
			shell.setText("Give the device");
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
		lbSoBPM.setBounds(10, 15, 101, 30);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setBounds(117, 15, 113, 30);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setTextLimit(20);

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setText("Tên thiết bị");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTenThietBi.setBounds(236, 15, 107, 30);

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setTextLimit(80);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenThietBi.setBounds(349, 15, 134, 30);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(489, 15, 62, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(557, 15, 84, 30);

		CLabel lbNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbNguoiNhan.setText("Người nhận");
		lbNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiNhan.setBounds(647, 15, 106, 30);

		textNguoiNhan = new Text(composite, SWT.BORDER);
		textNguoiNhan.setTextLimit(50);
		textNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguoiNhan.setBounds(761, 15, 81, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(848, 15, 92, 30);

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(946, 15, 140, 30);

		CLabel lbThoiGianNhan = new CLabel(composite, SWT.RIGHT);
		lbThoiGianNhan.setText("Thời gian nhận");
		lbThoiGianNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGianNhan.setBounds(10, 58, 120, 30);

		dateTimeThoiGianNhan1 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGianNhan1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGianNhan1.setBounds(136, 58, 120, 30);
		dateTimeThoiGianNhan1.setEnabled(false);

		CLabel lbdaunga = new CLabel(composite, SWT.RIGHT);
		lbdaunga.setText("~");
		lbdaunga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga.setAlignment(SWT.CENTER);
		lbdaunga.setBounds(261, 58, 26, 30);

		dateTimeThoiGianNhan2 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGianNhan2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGianNhan2.setBounds(293, 58, 120, 30);
		dateTimeThoiGianNhan2.setEnabled(false);

		btnCheckThoiGianNhan = new Button(composite, SWT.CHECK);
		btnCheckThoiGianNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckThoiGianNhan.setBounds(418, 58, 20, 30);

		CLabel lbDanhMuc = new CLabel(composite, SWT.RIGHT);
		lbDanhMuc.setText("Danh mục");
		lbDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDanhMuc.setBounds(444, 58, 107, 30);

		comboDanhMuc = new CCombo(composite, SWT.BORDER);
		comboDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDanhMuc.setBounds(557, 58, 140, 30);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-30);

		CLabel lbTinhTrang = new CLabel(composite, SWT.RIGHT);
		lbTinhTrang.setText("Tình trạng");
		lbTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTinhTrang.setBounds(703, 58, 92, 30);

		comboTinhTrang = new CCombo(composite, SWT.BORDER);
		comboTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTinhTrang.setBounds(801, 58, 75, 30);
		comboTinhTrang.setItems(new String[] { "Cũ", "Mới" });

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(PhatThietBi.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1094, 13, 130, 32);

		Button btnXuat = new Button(composite, SWT.NONE);
		btnXuat.setText("Xuất");
		btnXuat.setImage(SWTResourceManager.getImage(PhatThietBi.class, "/itmanagerip/Icon/button/excel25.png"));
		btnXuat.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnXuat.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXuat.setBounds(1230, 13, 100, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(PhatThietBi.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(882, 58, 100, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(PhatThietBi.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(988, 58, 100, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(PhatThietBi.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1094, 58, 100, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PhatThietBi.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1200, 58, 100, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 100);
		table.setSize(1900, 1061);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 210);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(60, 179, 113));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setResizable(false);
		tbcSTT.setWidth(50);
		tbcSTT.setText("STT");

		TableColumn tbcMaPhatThietBi = new TableColumn(table, SWT.NONE);
		tbcMaPhatThietBi.setWidth(0);
		tbcMaPhatThietBi.setText("Mã phát thiết bị");
		tbcMaPhatThietBi.setResizable(false);

		TableColumn tbcDanhMuc = new TableColumn(table, SWT.NONE);
		tbcDanhMuc.setWidth(123);
		tbcDanhMuc.setText("Danh mục");

		TableColumn tbcTenThietBi = new TableColumn(table, SWT.NONE);
		tbcTenThietBi.setWidth(216);
		tbcTenThietBi.setText("Tên thiết bị");

		TableColumn tbcSoBPM = new TableColumn(table, SWT.NONE);
		tbcSoBPM.setWidth(133);
		tbcSoBPM.setText("Số BPM");

		TableColumn tbcNgayNhapKho = new TableColumn(table, SWT.NONE);
		tbcNgayNhapKho.setWidth(143);
		tbcNgayNhapKho.setText("Ngày nhập kho");

		TableColumn tbcTinhTrang = new TableColumn(table, SWT.NONE);
		tbcTinhTrang.setWidth(100);
		tbcTinhTrang.setText("Tình trạng");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(76);
		tbcSoThe.setText("Số thẻ");

		TableColumn tbcNguoiNhan = new TableColumn(table, SWT.NONE);
		tbcNguoiNhan.setWidth(188);
		tbcNguoiNhan.setText("Người nhận");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(152);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcThoiGianNhan = new TableColumn(table, SWT.NONE);
		tbcThoiGianNhan.setWidth(187);
		tbcThoiGianNhan.setText("Thời gian nhận");

		TableColumn tbcNguoiPhat = new TableColumn(table, SWT.NONE);
		tbcNguoiPhat.setWidth(159);
		tbcNguoiPhat.setText("Người phát");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(115);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(147);
		tbcNguoiCapNhat.setText("Người cập nhật");

		btnxoa.setVisible(false);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbSoBPM.setText("Số BPM");
			lbTenThietBi.setText("Tên thiết bị");
			lbSoThe.setText("Số thẻ");
			lbNguoiNhan.setText("Người nhận");
			lbDonVi.setText("Đơn vị");
			lbThoiGianNhan.setText("Thời gian nhận");
			lbDanhMuc.setText("Danh mục");
			lbTinhTrang.setText("Tình trạng");
			btntimkiem.setText("Tìm kiếm");
			btnXuat.setText("Xuất");
			btnthem.setText("Thêm");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

			tbcSTT.setText("STT");
			tbcMaPhatThietBi.setText("Mã phát thiết bị");
			tbcDanhMuc.setText("Danh mục");
			tbcTenThietBi.setText("Tên thiết bị");
			tbcSoBPM.setText("Số BPM");
			tbcNgayNhapKho.setText("Ngày nhập kho");
			tbcTinhTrang.setText("Tình trạng");
			tbcSoThe.setText("Số thẻ");
			tbcNguoiNhan.setText("Người nhận");
			tbcDonVi.setText("Đơn vị");
			tbcThoiGianNhan.setText("Thời gian nhận");
			tbcNguoiPhat.setText("Người phát");
			tbcGhiChu.setText("Ghi chú");
			tbcNguoiCapNhat.setText("Người cập nhật");

		} else {
			// Tieng Anh
			lbSoBPM.setText("BPM number");
			lbTenThietBi.setText("Device name");
			lbSoThe.setText("ID");
			lbNguoiNhan.setText("Recipient");
			lbDonVi.setText("Department");
			lbThoiGianNhan.setText("Receiving time");
			lbDanhMuc.setText("Catalog");
			lbTinhTrang.setText("State");
			btntimkiem.setText("Search");
			btnXuat.setText("Export");
			btnthem.setText("Add");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Num");
			tbcMaPhatThietBi.setText("Device id");
			tbcDanhMuc.setText("Catalog");
			tbcTenThietBi.setText("Device name");
			tbcSoBPM.setText("BPM number");
			tbcNgayNhapKho.setText("Date input warehouse");
			tbcTinhTrang.setText("State");
			tbcSoThe.setText("ID");
			tbcNguoiNhan.setText("Recipient");
			tbcDonVi.setText("Department");
			tbcThoiGianNhan.setText("Receiving time");
			tbcNguoiPhat.setText("Deliver");
			tbcGhiChu.setText("Note");
			tbcNguoiCapNhat.setText("Person update");

		}

		// nếu là User thì không cho xóa
		if (grouplogin.equals("User")) {
			btnxoa.setVisible(false);
		}

		// lấy dữ liệu cho combo danh mục
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [TenDanhMuc] FROM [dbo].[LK_DanhMucThietBi] ORDER BY [TenDanhMuc] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboDanhMuc.add(result.getString(1));
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

		// Event phím tắt (ctrl + H), ALT + H
		com.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.ALT) {
					btnxoa.setVisible(true);
				} else if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					btnxoa.setVisible(false);
				}
			}
		});
		// Event phím tắt (ctrl + H), ALT + H
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.ALT) {
					btnxoa.setVisible(true);
				} else if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					btnxoa.setVisible(false);
				}
			}
		});

		// bắt sự kiện check vào checkbox thời gian nhận
		btnCheckThoiGianNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckThoiGianNhan.getSelection()) {
					dateTimeThoiGianNhan1.setEnabled(true);
					dateTimeThoiGianNhan2.setEnabled(true);
				} else {
					dateTimeThoiGianNhan1.setEnabled(false);
					dateTimeThoiGianNhan2.setEnabled(false);
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

		// Tìm kiếm sau khi enter ở text tên thiết bị
		// ******************************************************************************
		textTenThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
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

		// Tìm kiếm sau khi enter ở datetime thời gian nhận 1
		// ******************************************************************************
		dateTimeThoiGianNhan1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở datetime thời gian nhận 2
		// ******************************************************************************
		dateTimeThoiGianNhan2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
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

		// Tìm kiếm sau khi enter ở combo danh mục
		// ******************************************************************************
		comboDanhMuc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở combo tình trạng
		// ******************************************************************************
		comboTinhTrang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text số thẻ
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

		// Tìm kiếm sau khi enter ở text người nhận
		// ******************************************************************************
		textNguoiNhan.addKeyListener(new KeyAdapter() {
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

		// Xuất Excel
		// ******************************************************************************
		btnXuat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Phát thiết bị");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Give the device");
				}
			}
		});

		// Thêm-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PhatThietBiAdd phatthietbi = new PhatThietBiAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				phatthietbi.setLanguge(ngonngu, userlogin);
				phatthietbi.createContents();
				phatthietbi.open();
				composite.setEnabled(true);
				if (phatthietbi.isAddSuccess()) {
					search();
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
					maphatthietbixoa = new String[itemtable.length];

					for (int i = 0; i < maphatthietbixoa.length; i++) {
						maphatthietbixoa[i] = itemtable[i].getText(1);
					}
					if (maphatthietbixoa.length > 0) {
						btnthem.setEnabled(false);
						btntimkiem.setEnabled(false);
						btnXuat.setEnabled(false);
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
						for (int i = 0; i < maphatthietbixoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[LK_PhatThietBiIT] WHERE [MaPhatThietBi]='"
									+ maphatthietbixoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							search();
						}
						connect.closeStatement();
						btnthem.setEnabled(true);
						btntimkiem.setEnabled(true);
						btnXuat.setEnabled(true);
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
				btntimkiem.setEnabled(true);
				btnXuat.setEnabled(true);
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

		tbcDanhMuc.addListener(SWT.Selection, sort.sortListenerCode);
		tbcTenThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoBPM.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayNhapKho.addListener(SWT.Selection, sort.sortListenerDate);
		tbcTinhTrang.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiNhan.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThoiGianNhan.addListener(SWT.Selection, sort.sortListenerDateTime);
		tbcNguoiPhat.addListener(SWT.Selection, sort.sortListenerCode);
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

			String sothe = textSoThe.getText().isEmpty() ? ""
					: " AND [LK_PhatThietBiIT].[SoThe] = '" + textSoThe.getText() + "'";
			String donvi = comboDonVi.getText().isEmpty() ? ""
					: " AND [LK_PhatThietBiIT].[DonVi]=N'" + comboDonVi.getText() + "' ";
			String danhmuc = comboDanhMuc.getText().isEmpty() ? ""
					: " AND [LK_DanhMucThietBi].[TenDanhMuc] LIKE N'%" + comboDanhMuc.getText() + "%'";
			String tinhtrang = comboTinhTrang.getText().isEmpty() ? ""
					: " AND [LK_ThietBiKhoIT].[TinhTrang]=" + comboTinhTrang.getSelectionIndex();

			// Thời gian nhận
			String month = "0" + (dateTimeThoiGianNhan1.getMonth() + 1);
			month = month.substring(month.length() - 2);
			String day = "0" + dateTimeThoiGianNhan1.getDay();
			day = day.substring(day.length() - 2);
			String ngaybatdau = dateTimeThoiGianNhan1.getYear() + month + day + " 00:00:00.0";

			String month2 = "0" + (dateTimeThoiGianNhan2.getMonth() + 1);
			month2 = month2.substring(month2.length() - 2);
			String day2 = "0" + dateTimeThoiGianNhan2.getDay();
			day2 = day2.substring(day2.length() - 2);
			String ngayketthuc = dateTimeThoiGianNhan2.getYear() + month2 + day2 + " 23:59:59.999";

			String ngaynhapkho = " AND [LK_PhatThietBiIT].[ThoiGianNhan] BETWEEN '" + ngaybatdau + "' AND '"
					+ ngayketthuc + "'";
			if (!btnCheckThoiGianNhan.getSelection()) {
				ngaynhapkho = ""; // nếu không tìm theo ngày nhập kho
			}

			String select = "SELECT [LK_PhatThietBiIT].[MaPhatThietBi],[LK_DanhMucThietBi].[TenDanhMuc],[LK_ThietBiKhoIT].[TenThietBi],[LK_ThietBiKhoIT].[SoBPM],[LK_ThietBiKhoIT].[NgayNhapKho],CASE WHEN [LK_ThietBiKhoIT].[TinhTrang]=0 THEN N'Cũ' ELSE N'Mới' END AS State,[LK_PhatThietBiIT].[SoThe],[LK_PhatThietBiIT].[NguoiNhan],[LK_PhatThietBiIT].[DonVi],[LK_PhatThietBiIT].[ThoiGianNhan],[LK_PhatThietBiIT].[NguoiPhat],[LK_PhatThietBiIT].[GhiChu],[NguoiDung].[TenNguoiDung] FROM [dbo].[LK_PhatThietBiIT] LEFT JOIN [dbo].[LK_ThietBiKhoIT] ON [LK_PhatThietBiIT].[MaThietBi]=[LK_ThietBiKhoIT].[MaThietBi] LEFT JOIN [dbo].[NguoiDung] ON ([LK_PhatThietBiIT].[NguoiCapNhat]=[NguoiDung].[MaNguoiDung] OR [LK_PhatThietBiIT].[NguoiCapNhat]=[NguoiDung].[TenDangNhap]) LEFT JOIN [dbo].[LK_DanhMucThietBi] ON [LK_ThietBiKhoIT].[MaDanhMuc]=[LK_DanhMucThietBi].[MaDanhMuc] WHERE [LK_ThietBiKhoIT].[SoBPM] LIKE '%"
					+ textSoBPM.getText() + "%' AND [LK_ThietBiKhoIT].[TenThietBi] LIKE N'%" + textTenThietBi.getText()
					+ "%' AND [LK_PhatThietBiIT].[NguoiNhan] LIKE N'%" + textNguoiNhan.getText() + "%'" + sothe + donvi
					+ danhmuc + tinhtrang + ngaynhapkho + " ORDER BY [LK_PhatThietBiIT].[ThoiGianNhan] DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), ConvertDate.convertDate(result.getString(5)), result.getString(6),
						result.getString(7), result.getString(8), result.getString(9),
						ConvertDate.convertDateTime(result.getString(10)), result.getString(11), result.getString(12),
						result.getString(13) });
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

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	public void edit(Composite composite) {
		try {
			TableItem[] itemsua = table.getSelection();
			String soBPM = "";
			soBPM = itemsua[0].getText(2);
			if (!soBPM.isEmpty()) {
				ThietBiTrongKhoITEdit sua = new ThietBiTrongKhoITEdit();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				sua.setLanguage(ngonngu, userlogin);
				sua.createContents();
				sua.setData(itemsua[0].getText(1), itemsua[0].getText(2), itemsua[0].getText(3), itemsua[0].getText(4),
						itemsua[0].getText(6), itemsua[0].getText(7), itemsua[0].getText(8));
				sua.open();
				composite.setEnabled(true);
				if (sua.isEditSuccess()) {
					search();
				}
			}

		} catch (Exception ex) {
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
			itemtab.setText("Phát thiết bị");
		} else {
			itemtab.setText("Give the device");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);
		createContents(composite, shell);
	}
}
