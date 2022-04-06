package it;

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

public class PasswordError {
	private Table table;
	private TableItem[] itemtable;
	private String[] mamatkhauxoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Composite composite;
	private int ngonngu = 1;
	private Text textHoTen;
	private Text textSoThe;
	private CCombo comboDonVi;
	private DateTime dateTimeThoiGian1;
	private DateTime dateTimeThoiGian2;
	private CCombo comboNoiDung;

	private String userlogin = "21608";
	private String grouplogin = ""; // nếu là User thì không cho xóa
	private Button btnXoa;

	private final String[] arrayNoidung = new String[] { "Nhập sai mật khẩu", "Quên mật khẩu", "Lỗi bàn phím","Quên mật khẩu Mail","Quên mật khẩu Ổ đĩa dùng chung",
			"Quên mật khẩu BPM", "Quên mật khẩu ERP", "Quên mật khẩu HR" };

	public static void main(String[] args) {
		try {
			PasswordError window = new PasswordError();
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
		shell.setText("Sai mật khẩu");
		shell.setImage(SWTResourceManager.getImage(PasswordError.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Sai mật khẩu");
		} else {
			shell.setText("Pasword error");
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

		CLabel lbSoTheKhoa = new CLabel(composite, SWT.RIGHT);
		lbSoTheKhoa.setText("Số thẻ khóa");
		lbSoTheKhoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoTheKhoa.setBounds(10, 13, 100, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(116, 13, 88, 30);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(210, 13, 80, 30);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setTextLimit(30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(296, 13, 159, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(461, 13, 116, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(583, 13, 215, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(PasswordError.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(804, 13, 150, 32);

		Button btnExport = new Button(composite, SWT.NONE);
		btnExport.setText("Xuất");
		btnExport.setImage(SWTResourceManager.getImage(PasswordError.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExport.setForeground(SWTResourceManager.getColor(0, 100, 0));
		btnExport.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExport.setBounds(962, 13, 110, 32);

		CLabel lbThoiGian = new CLabel(composite, SWT.RIGHT);
		lbThoiGian.setText("Thời gian");
		lbThoiGian.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGian.setBounds(10, 54, 100, 30);

		dateTimeThoiGian1 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGian1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGian1.setBounds(116, 54, 120, 30);

		CLabel lbDauNga = new CLabel(composite, SWT.CENTER);
		lbDauNga.setText("~");
		lbDauNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDauNga.setBounds(239, 54, 22, 30);

		dateTimeThoiGian2 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGian2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGian2.setBounds(265, 54, 120, 30);

		CLabel lbNoiDung = new CLabel(composite, SWT.RIGHT);
		lbNoiDung.setText("Nội dung");
		lbNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNoiDung.setBounds(391, 54, 96, 30);

		comboNoiDung = new CCombo(composite, SWT.BORDER);
		comboNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNoiDung.setBounds(493, 54, 237, 30);
		comboNoiDung.setItems(arrayNoidung);

		Button btnThem = new Button(composite, SWT.NONE);
		btnThem.setImage(SWTResourceManager.getImage(PasswordError.class, "/itmanagerip/Icon/button/add30.png"));
		btnThem.setBounds(738, 54, 100, 32);
		btnThem.setText("Thêm");
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnXoa = new Button(composite, SWT.NONE);
		btnXoa.setImage(SWTResourceManager.getImage(PasswordError.class, "/itmanagerip/Icon/button/delete.png"));
		btnXoa.setBounds(844, 54, 110, 32);
		btnXoa.setText("Xóa");
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(PasswordError.class, "/itmanagerip/Icon/button/save25.png"));
		btnLuu.setBounds(962, 54, 110, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PasswordError.class, "/itmanagerip/Icon/button/cancel22.png"));
		btnHuy.setBounds(1078, 54, 110, 32);
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

		TableColumn tbcMaSaiMatKhau = new TableColumn(table, SWT.NONE);
		tbcMaSaiMatKhau.setWidth(0);
		tbcMaSaiMatKhau.setResizable(false);
		tbcMaSaiMatKhau.setText("Mã sai mật khẩu");

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

		TableColumn tbcSoTheKhoa = new TableColumn(table, SWT.NONE);
		tbcSoTheKhoa.setWidth(109);
		tbcSoTheKhoa.setText("Số thẻ khóa");

		TableColumn tbcHoTenKhoa = new TableColumn(table, SWT.NONE);
		tbcHoTenKhoa.setWidth(211);
		tbcHoTenKhoa.setText("Họ tên");

		TableColumn tbcDonViKhoa = new TableColumn(table, SWT.NONE);
		tbcDonViKhoa.setWidth(156);
		tbcDonViKhoa.setText("Đơn vị");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(109);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcThoiGian = new TableColumn(table, SWT.NONE);
		tbcThoiGian.setWidth(172);
		tbcThoiGian.setText("Thời gian");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(147);
		tbcNguoiCapNhat.setText("Người cập nhật");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbSoTheKhoa.setText("Số thẻ khóa");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			btntimkiem.setText("Tìm kiếm");
			btnExport.setText("Xuất");
			lbThoiGian.setText("Thời gian");
			lbNoiDung.setText("Nội dung");
			btnThem.setText("Thêm");
			btnXoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

			tbcSTT.setText("STT");
			tbcMaSaiMatKhau.setText("Mã sai mật khẩu");
			tbcSoThe.setText("Số thẻ");
			tbcHoTen.setText("Họ tên");
			tbcDonVi.setText("Đơn vị");
			tbcNoiDung.setText("Nội dung");
			tbcSoTheKhoa.setText("Số thẻ khóa");
			tbcHoTenKhoa.setText("Họ tên");
			tbcDonViKhoa.setText("Đơn vị");
			tbcGhiChu.setText("Ghi chú");
			tbcThoiGian.setText("Thời gian");
			tbcNguoiCapNhat.setText("Người cập nhật");
		} else {
			lbSoTheKhoa.setText("ID lock");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			btntimkiem.setText("Search");
			btnExport.setText("Export");
			lbThoiGian.setText("Datetime");
			lbNoiDung.setText("Content");
			btnThem.setText("Add");
			btnXoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("STT");
			tbcMaSaiMatKhau.setText("Wrong password id");
			tbcSoThe.setText("ID");
			tbcHoTen.setText("Name");
			tbcDonVi.setText("Department");
			tbcNoiDung.setText("Content");
			tbcSoTheKhoa.setText("ID lock");
			tbcHoTenKhoa.setText("Name");
			tbcDonViKhoa.setText("Department");
			tbcGhiChu.setText("Note");
			tbcThoiGian.setText("Datetime");
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

		// Tìm kiếm sau khi enter ở text họ tên
		// ******************************************************************************
		textHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
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
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở datetime thời gian
		// ******************************************************************************
		dateTimeThoiGian1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở datetime thời gian
		// ******************************************************************************
		dateTimeThoiGian2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở combo nội dung
		// ******************************************************************************
		comboNoiDung.addKeyListener(new KeyAdapter() {
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
				PasswordErrorAdd password = new PasswordErrorAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				password.setData(ngonngu, userlogin);
				password.createContents();
				password.open();
				composite.setEnabled(true);

				if (password.isAddSuccess()) {
					search(shell);
				}
			}
		});

		// Export
		btnExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Sai mật khẩu");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Password error");
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
						Excel.exportExcelTable(table, shell, ngonngu, "Sai mật khẩu");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "Password error");
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
					mamatkhauxoa = new String[itemtable.length];

					for (int i = 0; i < mamatkhauxoa.length; i++) {
						mamatkhauxoa[i] = itemtable[i].getText(1);
					}
					if (mamatkhauxoa.length > 0) {
						btnThem.setEnabled(false);
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
						for (int i = 0; i < mamatkhauxoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[IT_PasswordError] WHERE [MaMatKhau]='"
									+ mamatkhauxoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						connect.closeStatement();
						btnThem.setEnabled(true);
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
				btnExport.setEnabled(true);
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

		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoTen.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNoiDung.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoTheKhoa.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoTenKhoa.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonViKhoa.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThoiGian.addListener(SWT.Selection, sort.sortListenerDateTime);
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
			itemtab.setText("Sai mật khẩu");
		} else {
			itemtab.setText("Password error");
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

			String sothekhoa = textSoThe.getText().isEmpty() ? "" : " AND SoTheKhoa='" + textSoThe.getText() + "'";
			String donvi = comboDonVi.getText().isEmpty() ? "" : " AND DonViKhoa=N'" + comboDonVi.getText() + "'";

			String month = "0" + (dateTimeThoiGian1.getMonth() + 1);
			month = month.substring(month.length() - 2);
			String day = "0" + dateTimeThoiGian1.getDay();
			day = day.substring(day.length() - 2);
			String ngaybatdau = dateTimeThoiGian1.getYear() + month + day;

			String month2 = "0" + (dateTimeThoiGian2.getMonth() + 1);
			month2 = month2.substring(month2.length() - 2);
			String day2 = "0" + dateTimeThoiGian2.getDay();
			day2 = day2.substring(day2.length() - 2);
			String ngayketthuc = dateTimeThoiGian2.getYear() + month2 + day2;

			String thoigian = " AND (ThoiGian BETWEEN '" + ngaybatdau + " 00:00:00' AND '" + ngayketthuc
					+ " 23:59:59')";

			String select = "SELECT MaMatKhau,SoThe,HoTen,DonVi,NoiDung,SoTheKhoa,HoTenKhoa,DonViKhoa,GhiChu,ThoiGian,NguoiDung.TenNguoiDung FROM dbo.IT_PasswordError LEFT JOIN dbo.NguoiDung ON (IT_PasswordError.UserUpdate=NguoiDung.MaNguoiDung OR IT_PasswordError.UserUpdate=NguoiDung.TenDangNhap) WHERE NoiDung LIKE N'%"
					+ comboNoiDung.getText() + "%' AND HoTenKhoa LIKE N'%" + textHoTen.getText() + "%'" + sothekhoa
					+ donvi + thoigian + " ORDER BY ThoiGian DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						result.getString(8), result.getString(9), ConvertDate.convertDateTime(result.getString(10)),
						result.getString(11) });
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

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language, String nguoidangnhap) {
		ngonngu = language;
		userlogin = nguoidangnhap;
	}
}
