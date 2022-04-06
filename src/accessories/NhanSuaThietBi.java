package accessories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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

public class NhanSuaThietBi {
	private Table table;
	private TableItem[] itemtable;
	private String[] masuathietbixoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Composite composite;
	private int ngonngu = 0;
	private Text textHoTen;
	private Text textSoBPM;
	private Text textSoThe;
	private CCombo comboDonVi;
	private DateTime dateTimeThoiGianGui1;
	private DateTime dateTimeThoiGianGui2;
	private String userlogin = "21608";
	private String grouplogin = ""; // nếu là User thì không cho xóa
	private Button btnXoa;
	private Text textSoTheNguoiNhan;
	private Text textNguoiNhan;

	public static void main(String[] args) {
		try {
			NhanSuaThietBi window = new NhanSuaThietBi();
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
		shell.setText("Nhận sửa thiết bị");
		shell.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Nhận sửa thiết bị");
		} else {
			shell.setText("Get device repair");
		}

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(new FillLayout());
	}

	protected void createContents(Composite com, Shell shell) {
		ScrolledComposite scrolledComposite = new ScrolledComposite(com, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		composite = new Composite(scrolledComposite, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);
		scrolledComposite.setContent(composite);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(10, 13, 70, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(86, 13, 88, 30);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(180, 13, 80, 30);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setTextLimit(30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(266, 13, 159, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(431, 13, 116, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(553, 13, 149, 30);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoBPM.setBounds(708, 13, 80, 30);

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setTextLimit(80);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setBounds(794, 13, 209, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem
				.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1009, 13, 166, 32);

		Button btnExport = new Button(composite, SWT.NONE);
		btnExport.setText("Xuất");
		btnExport.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExport.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnExport.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExport.setBounds(1181, 13, 110, 32);

		CLabel lbThoiGianGui = new CLabel(composite, SWT.RIGHT);
		lbThoiGianGui.setText("Thời gian gửi");
		lbThoiGianGui.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGianGui.setBounds(10, 54, 140, 30);

		dateTimeThoiGianGui1 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGianGui1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGianGui1.setBounds(156, 54, 120, 30);

		CLabel lbDauNga = new CLabel(composite, SWT.CENTER);
		lbDauNga.setText("~");
		lbDauNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDauNga.setBounds(279, 54, 22, 30);

		dateTimeThoiGianGui2 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGianGui2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGianGui2.setBounds(305, 54, 120, 30);

		CLabel lbSoTheNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbSoTheNguoiNhan.setText("Số thẻ người nhận");
		lbSoTheNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoTheNguoiNhan.setBounds(431, 54, 145, 30);

		textSoTheNguoiNhan = new Text(composite, SWT.BORDER);
		textSoTheNguoiNhan.setTextLimit(30);
		textSoTheNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoTheNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoTheNguoiNhan.setBounds(582, 54, 88, 30);

		CLabel lbNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbNguoiNhan.setText("Người nhận");
		lbNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiNhan.setBounds(679, 54, 109, 30);

		textNguoiNhan = new Text(composite, SWT.BORDER);
		textNguoiNhan.setTextLimit(30);
		textNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguoiNhan.setBounds(794, 54, 169, 30);

		Button btnThem = new Button(composite, SWT.NONE);
		btnThem.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/button/add30.png"));
		btnThem.setBounds(969, 54, 100, 32);
		btnThem.setText("Thêm");
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnSua = new Button(composite, SWT.NONE);
		btnSua.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/button/edit.png"));
		btnSua.setBounds(1075, 54, 100, 32);
		btnSua.setText("Sửa");
		btnSua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnXoa = new Button(composite, SWT.NONE);
		btnXoa.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/button/delete.png"));
		btnXoa.setBounds(1181, 54, 110, 32);
		btnXoa.setText("Xóa");
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/button/save25.png"));
		btnLuu.setBounds(1297, 54, 110, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(NhanSuaThietBi.class, "/itmanagerip/Icon/button/cancel22.png"));
		btnHuy.setBounds(1297, 13, 110, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 95);
		table.setSize(1870, 600);
		table.setSize(Display.getDefault().getBounds().width - 30, Display.getDefault().getBounds().height - 210);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 215, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(50);
		tbcSTT.setText("STT");
		tbcSTT.setResizable(false);

		TableColumn tbcMaSuaThietBi = new TableColumn(table, SWT.NONE);
		tbcMaSuaThietBi.setWidth(0);
		tbcMaSuaThietBi.setResizable(false);
		tbcMaSuaThietBi.setText("Mã thiết bị");

		TableColumn tbcSoBPM = new TableColumn(table, SWT.NONE);
		tbcSoBPM.setWidth(169);
		tbcSoBPM.setText("Số BPM");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(87);
		tbcSoThe.setText("Số thẻ");

		TableColumn tbcHoTen = new TableColumn(table, SWT.NONE);
		tbcHoTen.setWidth(207);
		tbcHoTen.setText("Họ tên");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(168);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcNoiDung = new TableColumn(table, SWT.NONE);
		tbcNoiDung.setWidth(159);
		tbcNoiDung.setText("Nội dung");

		TableColumn tbcThoiGianGui = new TableColumn(table, SWT.NONE);
		tbcThoiGianGui.setWidth(172);
		tbcThoiGianGui.setText("Thời gian gửi");

		TableColumn tbcSoTheNguoiNhan = new TableColumn(table, SWT.NONE);
		tbcSoTheNguoiNhan.setWidth(80);
		tbcSoTheNguoiNhan.setText("Số thẻ");

		TableColumn tbcHoTenNguoiNhan = new TableColumn(table, SWT.NONE);
		tbcHoTenNguoiNhan.setWidth(174);
		tbcHoTenNguoiNhan.setText("Người nhận");

		TableColumn tbcDonViNguoiNhan = new TableColumn(table, SWT.NONE);
		tbcDonViNguoiNhan.setWidth(156);
		tbcDonViNguoiNhan.setText("Đơn vị");

		TableColumn tbcThoiGianNhan = new TableColumn(table, SWT.NONE);
		tbcThoiGianNhan.setWidth(172);
		tbcThoiGianNhan.setText("Thời gian nhận");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(109);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(147);
		tbcNguoiCapNhat.setText("Người cập nhật");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			tbcSoBPM.setText("Số BPM");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbSoBPM.setText("Số BPM");
			btntimkiem.setText("Tìm kiếm");
			btnExport.setText("Xuất");
			lbThoiGianGui.setText("Thời gian gửi");
			tbcSoTheNguoiNhan.setText("Số thẻ");
			lbSoTheNguoiNhan.setText("Số thẻ người nhận");
			lbNguoiNhan.setText("Người nhận");
			tbcGhiChu.setText("Ghi chú");
			btnThem.setText("Thêm");
			btnSua.setText("Sửa");
			btnXoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

			tbcSTT.setText("STT");
			tbcMaSuaThietBi.setText("Mã thiết bị");
			tbcSoThe.setText("Số thẻ");
			tbcHoTen.setText("Họ tên");
			tbcDonVi.setText("Đơn vị");
			tbcNoiDung.setText("Nội dung");
			tbcThoiGianGui.setText("Thời gian gửi");
			tbcSoTheNguoiNhan.setText("Số thẻ");
			tbcHoTenNguoiNhan.setText("Người nhận");
			tbcDonViNguoiNhan.setText("Đơn vị");
			tbcThoiGianNhan.setText("Thời gian nhận");
			tbcGhiChu.setText("Ghi chú");
			tbcNguoiCapNhat.setText("Người cập nhật");
		} else {
			tbcSoBPM.setText("BPM number");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbSoBPM.setText("BPM");
			btntimkiem.setText("Search");
			btnExport.setText("Export");
			lbThoiGianGui.setText("Sending time");
			lbSoTheNguoiNhan.setText("Receiver id");
			lbNguoiNhan.setText("Receiver");
			btnThem.setText("Add");
			btnSua.setText("Edit");
			btnXoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Num");
			tbcMaSuaThietBi.setText("Device id");
			tbcSoThe.setText("ID");
			tbcHoTen.setText("Name");
			tbcDonVi.setText("Department");
			tbcNoiDung.setText("Content");
			tbcThoiGianGui.setText("Sending time");
			tbcSoTheNguoiNhan.setText("ID");
			tbcHoTenNguoiNhan.setText("Receiver");
			tbcDonViNguoiNhan.setText("Department");
			tbcThoiGianNhan.setText("Receiving time");
			tbcGhiChu.setText("Note");
			tbcNguoiCapNhat.setText("Person update");
		}

		// nếu là User thì không cho xóa
		if (grouplogin.equals("User")) {
			btnXoa.setVisible(false);
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

		// Tìm kiếm sau khi enter ở text so the
		// ******************************************************************************
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		textHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		textSoBPM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		comboDonVi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text so the nguoi nhan
		// ******************************************************************************
		textSoTheNguoiNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text nguoi nhan
		// ******************************************************************************
		textNguoiNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở datetime ngay gui
		// ******************************************************************************
		dateTimeThoiGianGui1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở datetime ngay gui
		// ******************************************************************************
		dateTimeThoiGianGui2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// Tìm kiếm sau khi enter ở button tìm kiếm
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Add-------------------------------------------------------------------------------------------------
		btnThem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NhanSuaThietBiAdd suathietbi = new NhanSuaThietBiAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				suathietbi.setData(ngonngu, userlogin);
				suathietbi.createContents();
				suathietbi.open();
				composite.setEnabled(true);

				if (suathietbi.isAddSuccess()) {
					search(shell);
				}
			}
		});

		// Edit---------------------------------------------------------------------------------------------------
		btnSua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				edit();
			}
		});

		// Export
		btnExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Nhận sửa thiết bị");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Get device repair");
				}
			}
		});

		// Export sau khi enter ở button Export
		// ******************************************************************************
		btnExport.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					if (ngonngu == 0) {
						Excel.exportExcelTable(table, shell, ngonngu, "Nhận sửa thiết bị");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "Get device repair");
					}
				}
			}
		});

		// Xóa-------------------------------------------------------------------
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Lấy cột số mã thiết bị
					itemtable = table.getSelection();
					masuathietbixoa = new String[itemtable.length];

					for (int i = 0; i < masuathietbixoa.length; i++) {
						masuathietbixoa[i] = itemtable[i].getText(1);
					}
					if (masuathietbixoa.length > 0) {
						btnThem.setEnabled(false);
						btnSua.setEnabled(false);
						btnExport.setEnabled(false);
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
					btnThem.setEnabled(true);
					btnSua.setEnabled(true);
					btnExport.setEnabled(true);
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
					try {
						String delete = "";
						for (int i = 0; i < masuathietbixoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[LK_SuaThietBi] WHERE [MaSuaThietBi]='"
									+ masuathietbixoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						connect.closeStatement();
						btnThem.setEnabled(true);
						btnSua.setEnabled(true);
						btnExport.setEnabled(true);
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
				btnThem.setEnabled(true);
				btnSua.setEnabled(true);
				btnExport.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// mo cua so sua khi double click vao dong cua bang
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				edit();
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcSoBPM.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoTen.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNoiDung.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThoiGianGui.addListener(SWT.Selection, sort.sortListenerDateTime);

		tbcSoTheNguoiNhan.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoTenNguoiNhan.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonViNguoiNhan.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThoiGianNhan.addListener(SWT.Selection, sort.sortListenerDateTime);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiCapNhat.addListener(SWT.Selection, sort.sortListenerCode);

		// cái này phải ở cuối cùng thì mới scroll được
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu, String userlogin, String grouplogin) {
		this.userlogin = userlogin;
		this.ngonngu = ngonngu;
		this.grouplogin = grouplogin;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Sửa thiết bị");
		} else {
			itemtab.setText("Repair device");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);

		createContents(composite, shell);
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
			String donvi = comboDonVi.getText().isEmpty() ? "" : " AND [DonVi]=N'" + comboDonVi.getText() + "'";

			String month = "0" + (dateTimeThoiGianGui1.getMonth() + 1);
			month = month.substring(month.length() - 2);
			String day = "0" + dateTimeThoiGianGui1.getDay();
			day = day.substring(day.length() - 2);
			String ngaybatdau = dateTimeThoiGianGui1.getYear() + month + day;

			String month2 = "0" + (dateTimeThoiGianGui2.getMonth() + 1);
			month2 = month2.substring(month2.length() - 2);
			String day2 = "0" + dateTimeThoiGianGui2.getDay();
			day2 = day2.substring(day2.length() - 2);
			String ngayketthuc = dateTimeThoiGianGui2.getYear() + month2 + day2;

			String thoigiangui = " AND ([ThoiGianDemLenIT] BETWEEN '" + ngaybatdau + " 00:00:00' AND '" + ngayketthuc
					+ " 23:59:59')";

			String sothenguoinhan = textSoTheNguoiNhan.getText().isEmpty() ? ""
					: " AND [SoTheNguoiNhan]='" + textSoTheNguoiNhan.getText() + "'";

			String nguoinhan = textNguoiNhan.getText().isEmpty() ? ""
					: " AND [HoTenNguoiNhan] LIKE N'%" + textNguoiNhan.getText() + "%'";

			String select = "SELECT [MaSuaThietBi],[SoBPM],[SoThe],[HoTen],[DonVi],[NoiDung],[ThoiGianDemLenIT],[SoTheNguoiNhan],[HoTenNguoiNhan],[DonViNguoiNhan],[ThoiGianNhan],[GhiChu],[NguoiDung].[TenNguoiDung] FROM [dbo].[LK_SuaThietBi] LEFT JOIN [dbo].[NguoiDung] ON [LK_SuaThietBi].[NguoiCapNhat]=[NguoiDung].[MaNguoiDung] OR [LK_SuaThietBi].[NguoiCapNhat]=[NguoiDung].[TenDangNhap] WHERE [HoTen] LIKE N'%"
					+ textHoTen.getText() + "%' AND [SoBPM] LIKE '%" + textSoBPM.getText() + "'" + sothe + donvi
					+ thoigiangui + sothenguoinhan + nguoinhan + " ORDER BY [ThoiGianDemLenIT] DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6),
						ConvertDate.convertDateTime(result.getString(7)), result.getString(8), result.getString(9),
						result.getString(10), ConvertDate.convertDateTime(result.getString(11)), result.getString(12),
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

	// edit-------------------------------------------------------------------------------------
	public void edit() {
		try {
			TableItem[] item = table.getSelection();
			if (item.length > 0 && item[0].getText(8).isEmpty()) {
				NhanSuaThietBiEdit suathietbi = new NhanSuaThietBiEdit();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				suathietbi.setLanguage(ngonngu, userlogin);
				suathietbi.createContents();
				suathietbi.setData(item[0].getText(1), item[0].getText(3), item[0].getText(4), item[0].getText(5),
						item[0].getText(2), item[0].getText(6), item[0].getText(7));
				suathietbi.open();
				composite.setEnabled(true);

				if (suathietbi.isEditSuccess()) {
					search(shell);
				}
			} else {
				NhanSuaThietBiEdit2 suathietbi = new NhanSuaThietBiEdit2();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				suathietbi.setLanguage(ngonngu, userlogin);
				suathietbi.createContents();
				suathietbi.setData(item[0].getText(1), item[0].getText(3), item[0].getText(4), item[0].getText(5),
						item[0].getText(2), item[0].getText(6), item[0].getText(7), item[0].getText(8),
						item[0].getText(9), item[0].getText(10), item[0].getText(11), item[0].getText(12));
				suathietbi.open();
				composite.setEnabled(true);

				if (suathietbi.isEditSuccess()) {
					search(shell);
				}
			}
		} catch (Exception exc) {

		}
	}

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language, String nguoidangnhap) {
		ngonngu = language;
		userlogin = nguoidangnhap;
	}
}
