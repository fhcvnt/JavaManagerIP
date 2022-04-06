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

public class ThietBiTrongKhoIT {
	private Table table;
	private TableItem[] itemtable;
	private String[] mathietbixoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Text textSoBPM;
	private int ngonngu = 0;
	private CCombo comboDanhMuc;
	private Button btnCheckNgayNhapKho;
	private DateTime dateTimeNgayNhapKho1;
	private DateTime dateTimeNgayNhapKho2;
	private CCombo comboTrangThai;
	private CCombo comboTinhTrang;

	private String userlogin = "21608";
	private String grouplogin = ""; // nếu là User thì không cho xóa
	private Text textTenThietBi;

	public static void main(String[] args) {
		try {
			ThietBiTrongKhoIT window = new ThietBiTrongKhoIT();
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
		shell.setImage(SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Nhập kho IT");
		} else {
			shell.setText("Import IT warehouse");
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

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setText("Tên thiết bị");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTenThietBi.setBounds(289, 15, 107, 30);

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setTextLimit(80);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenThietBi.setBounds(402, 15, 160, 30);

		CLabel lbDanhMuc = new CLabel(composite, SWT.RIGHT);
		lbDanhMuc.setText("Danh mục");
		lbDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDanhMuc.setBounds(568, 15, 107, 30);

		comboDanhMuc = new CCombo(composite, SWT.BORDER);
		comboDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDanhMuc.setBounds(681, 15, 172, 30);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-30);

		CLabel lbTinhTrang = new CLabel(composite, SWT.RIGHT);
		lbTinhTrang.setText("Tình trạng");
		lbTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTinhTrang.setBounds(859, 15, 92, 30);

		comboTinhTrang = new CCombo(composite, SWT.BORDER);
		comboTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTinhTrang.setBounds(957, 15, 75, 30);
		comboTinhTrang.setItems(new String[] { "Cũ", "Mới" });

		CLabel lbNgayNhapKho = new CLabel(composite, SWT.RIGHT);
		lbNgayNhapKho.setText("Date input warehouse");
		lbNgayNhapKho.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayNhapKho.setBounds(10, 56, 176, 30);

		dateTimeNgayNhapKho1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayNhapKho1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayNhapKho1.setBounds(192, 56, 120, 30);
		dateTimeNgayNhapKho1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbdaunga2 = new CLabel(composite, SWT.RIGHT);
		lbdaunga2.setText("~");
		lbdaunga2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga2.setAlignment(SWT.CENTER);
		lbdaunga2.setBounds(317, 56, 26, 30);

		dateTimeNgayNhapKho2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayNhapKho2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayNhapKho2.setBounds(349, 56, 120, 30);

		btnCheckNgayNhapKho = new Button(composite, SWT.CHECK);
		btnCheckNgayNhapKho.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckNgayNhapKho.setBounds(474, 56, 20, 30);

		CLabel lbTrangThai = new CLabel(composite, SWT.RIGHT);
		lbTrangThai.setText("Trạng thái");
		lbTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTrangThai.setBounds(500, 56, 92, 30);

		comboTrangThai = new CCombo(composite, SWT.BORDER);
		comboTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTrangThai.setBounds(598, 56, 120, 30);
		comboTrangThai.setItems(new String[] { "Đã phát", "Chưa phát" });

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1038, 15, 150, 32);

		Button btnXuat = new Button(composite, SWT.NONE);
		btnXuat.setText("Xuất");
		btnXuat.setImage(SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/button/excel25.png"));
		btnXuat.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnXuat.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXuat.setBounds(1193, 15, 110, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(724, 56, 110, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(842, 56, 110, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(960, 56, 110, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1076, 56, 110, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBiTrongKhoIT.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1194, 56, 110, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 98);
		table.setSize(1900, 868);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 210);
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

		TableColumn tbcMaThietBi = new TableColumn(table, SWT.NONE);
		tbcMaThietBi.setWidth(0);
		tbcMaThietBi.setText("Mã thiết bị");
		tbcMaThietBi.setResizable(false);

		TableColumn tbcDanhMuc = new TableColumn(table, SWT.NONE);
		tbcDanhMuc.setWidth(158);
		tbcDanhMuc.setText("Danh mục");

		TableColumn tbcTenThietBi = new TableColumn(table, SWT.NONE);
		tbcTenThietBi.setWidth(283);
		tbcTenThietBi.setText("Tên thiết bị");

		TableColumn tbcSoBPM = new TableColumn(table, SWT.NONE);
		tbcSoBPM.setWidth(187);
		tbcSoBPM.setText("Số BPM");

		TableColumn tbcNgayNhapKho = new TableColumn(table, SWT.NONE);
		tbcNgayNhapKho.setWidth(181);
		tbcNgayNhapKho.setText("Ngày nhập kho");

		TableColumn tbcTinhTrang = new TableColumn(table, SWT.NONE);
		tbcTinhTrang.setWidth(124);
		tbcTinhTrang.setText("Tình trạng");

		TableColumn tbcTrangThai = new TableColumn(table, SWT.NONE);
		tbcTrangThai.setWidth(135);
		tbcTrangThai.setText("Trạng thái");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(99);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(169);
		tbcNguoiCapNhat.setText("Người cập nhật");
		dateTimeNgayNhapKho1.setEnabled(false);
		dateTimeNgayNhapKho2.setEnabled(false);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbSoBPM.setText("Số BPM");
			lbTenThietBi.setText("Tên thiết bị");
			lbDanhMuc.setText("Danh mục");
			lbTrangThai.setText("Trạng thái");
			lbNgayNhapKho.setText("Ngày nhập kho");
			lbTinhTrang.setText("Tình trạng");
			btntimkiem.setText("Tìm kiếm");
			btnXuat.setText("Xuất");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

			tbcSTT.setText("STT");
			tbcMaThietBi.setText("Mã thiết bị");
			tbcDanhMuc.setText("Danh mục");
			tbcTenThietBi.setText("Tên thiết bị");
			tbcSoBPM.setText("Số BPM");
			tbcNgayNhapKho.setText("Ngày nhập kho");
			tbcTinhTrang.setText("Tình trạng");
			tbcTrangThai.setText("Trạng thái");
			tbcGhiChu.setText("Ghi chú");
			tbcNguoiCapNhat.setText("Người cập nhật");

		} else {
			// Tieng Anh
			lbSoBPM.setText("BPM number");
			lbTenThietBi.setText("Device name");
			lbDanhMuc.setText("Catalog");
			lbTrangThai.setText("Status");
			lbNgayNhapKho.setText("Date input warehouse");
			lbTinhTrang.setText("State");
			btntimkiem.setText("Search");
			btnXuat.setText("Export");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Num");
			tbcMaThietBi.setText("Device id");
			tbcDanhMuc.setText("Catalog");
			tbcTenThietBi.setText("Device name");
			tbcSoBPM.setText("BPM number");
			tbcNgayNhapKho.setText("Date input warehouse");
			tbcTinhTrang.setText("State");
			tbcTrangThai.setText("Status");
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

		// bắt sự kiện check vào checkbox Ngày nhập kho
		btnCheckNgayNhapKho.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckNgayNhapKho.getSelection()) {
					dateTimeNgayNhapKho1.setEnabled(true);
					dateTimeNgayNhapKho2.setEnabled(true);
				} else {
					dateTimeNgayNhapKho1.setEnabled(false);
					dateTimeNgayNhapKho2.setEnabled(false);
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

		// Tìm kiếm sau khi enter ở combo trạng thái
		// ******************************************************************************
		comboTrangThai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở datetime ngày nhập kho 1
		// ******************************************************************************
		dateTimeNgayNhapKho1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở datetime ngày nhập kho 2
		// ******************************************************************************
		dateTimeNgayNhapKho2.addKeyListener(new KeyAdapter() {
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
					Excel.exportExcelTable(table, shell, ngonngu, "Thiết bị trong kho IT");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Device in IT warehouse");
				}
			}
		});

		// Thêm-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ThietBiTrongKhoITAdd thietbimoi = new ThietBiTrongKhoITAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				thietbimoi.setData(ngonngu, userlogin);
				thietbimoi.open();
				composite.setEnabled(true);
				if (thietbimoi.isAddSuccess()) {
					search();
				}
			}
		});

		// Sửa---------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				edit(composite);
			}
		});

		// sửa khi double click vào dòng của bảng
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				edit(composite);
			}
		});

		// sửa khi enter vào dòng của bảng
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					edit(composite);
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
					mathietbixoa = new String[itemtable.length];

					for (int i = 0; i < mathietbixoa.length; i++) {
						mathietbixoa[i] = itemtable[i].getText(1);
					}
					if (mathietbixoa.length > 0) {
						btnthem.setEnabled(false);
						btnsua.setEnabled(false);
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
						for (int i = 0; i < mathietbixoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[LK_ThietBiKhoIT]  WHERE [MaThietBi]='"
									+ mathietbixoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							search();
						}
						connect.closeStatement();
						btnthem.setEnabled(true);
						btnsua.setEnabled(true);
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
				btnsua.setEnabled(true);
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
		tbcTrangThai.addListener(SWT.Selection, sort.sortListenerCode);
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

			String sobpm = textSoBPM.getText().isEmpty() ? "" : " AND [SoBPM] LIKE '%" + textSoBPM.getText() + "%'";
			String tenthietbi = textTenThietBi.getText().isEmpty() ? ""
					: " AND [TenThietBi] LIKE N'%" + textTenThietBi.getText() + "%' ";
			String danhmuc = comboDanhMuc.getText().isEmpty() ? ""
					: " AND [LK_DanhMucThietBi].[TenDanhMuc] LIKE N'%" + comboDanhMuc.getText() + "%'";
			String trangthai = comboTrangThai.getText().isEmpty() ? ""
					: " AND [TrangThai]=" + comboTrangThai.getSelectionIndex();
			String tinhtrang = comboTinhTrang.getText().isEmpty() ? ""
					: " AND [TinhTrang]=" + comboTinhTrang.getSelectionIndex();

			// ngày nhập kho
			String monthnhakho = "0" + (dateTimeNgayNhapKho1.getMonth() + 1);
			monthnhakho = monthnhakho.substring(monthnhakho.length() - 2);
			String daynhakho = "0" + dateTimeNgayNhapKho1.getDay();
			daynhakho = daynhakho.substring(daynhakho.length() - 2);
			String ngaybatdau = dateTimeNgayNhapKho1.getYear() + monthnhakho + daynhakho;

			String monthnhakho2 = "0" + (dateTimeNgayNhapKho2.getMonth() + 1);
			monthnhakho2 = monthnhakho2.substring(monthnhakho2.length() - 2);
			String daynhakho2 = "0" + dateTimeNgayNhapKho2.getDay();
			daynhakho2 = daynhakho2.substring(daynhakho2.length() - 2);
			String ngayketthuc = dateTimeNgayNhapKho2.getYear() + monthnhakho2 + daynhakho2;

			String ngaynhapkho = " AND [NgayNhapKho] BETWEEN '" + ngaybatdau + "' AND '" + ngayketthuc + "'";
			if (!btnCheckNgayNhapKho.getSelection()) {
				ngaynhapkho = ""; // nếu không tìm theo ngày nhập kho
			}

			String select = "SELECT [MaThietBi],[LK_DanhMucThietBi].[TenDanhMuc],[TenThietBi],[SoBPM],[NgayNhapKho],CASE WHEN [TinhTrang]=0 THEN N'Cũ' ELSE N'Mới' END,CASE WHEN [TrangThai]=0 THEN N'Đã phát' ELSE N'Chưa phát' END,[GhiChu],[NguoiDung].[TenNguoiDung] FROM [dbo].[LK_ThietBiKhoIT] LEFT JOIN [dbo].[NguoiDung] ON ([LK_ThietBiKhoIT].[NguoiCapNhat]=[NguoiDung].[MaNguoiDung] OR [LK_ThietBiKhoIT].[NguoiCapNhat]=[NguoiDung].[TenDangNhap]) LEFT JOIN [dbo].[LK_DanhMucThietBi] ON [LK_ThietBiKhoIT].[MaDanhMuc]=[LK_DanhMucThietBi].[MaDanhMuc] WHERE 1=1"
					+ sobpm + tenthietbi + danhmuc + trangthai + tinhtrang + ngaynhapkho + " ORDER BY [TrangThai] DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), ConvertDate.convertDate(result.getString(5)), result.getString(6),
						result.getString(7), result.getString(8), result.getString(9) });
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
			itemtab.setText("Thiết bị trong kho IT");
		} else {
			itemtab.setText("Device in IT warehouse");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);
		createContents(composite, shell);
	}
}
