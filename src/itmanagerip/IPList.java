package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
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

public class IPList {
	private ConnectSQL connect;
	private Table table;
	private TableItem[] itemtable;
	private String[] sothexoa;
	private String userlogin = "21608"; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật
	private String grouplogin = ""; // là nhóm User thì không cho thêm, sửa, xóa, Xuất Excel, chi tiết

	protected Shell shell;
	private Composite compositeshell;
	private Text textsothe;
	private Text texthoten;
	private Text textdonvi;
	private Text texttoanha;
	private Text textIP;
	private Text texthedieuhanh;
	private Text textoffice;
	private Text textloaimay;

	private Button btnthem;
	private Button btnsua;
	private Button btnExcel;
	private Button btntimkiem;
	private Button btnGetIP;
	private Button btnxoa;
	private Button btnLuu;
	private Button btnHuy;
	private Button btnDetail;

	private int ngonngu = 1;
	private Text textEmail;

	public static void main(String[] args) {
		try {
			IPList window = new IPList();
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
		createContentsShell();
		createContents(compositeshell, shell, ngonngu);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContentsShell() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("IP");

		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fillLayout);
		compositeshell = new Composite(shell, SWT.NONE);
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		CLabel lbsothe = new CLabel(composite, SWT.RIGHT);
		lbsothe.setBounds(10, 13, 78, 30);
		lbsothe.setText("Số thẻ");
		lbsothe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textsothe = new Text(composite, SWT.BORDER);
		textsothe.setBounds(100, 13, 78, 30);
		textsothe.setTextLimit(12);
		textsothe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textsothe.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbhoten = new CLabel(composite, SWT.RIGHT);
		lbhoten.setBounds(184, 13, 88, 30);
		lbhoten.setText("Họ tên");
		lbhoten.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		texthoten = new Text(composite, SWT.BORDER);
		texthoten.setBounds(278, 13, 183, 30);
		texthoten.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		texthoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbdonvi = new CLabel(composite, SWT.RIGHT);
		lbdonvi.setBounds(467, 10, 106, 30);
		lbdonvi.setText("Đơn vị");
		lbdonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textdonvi = new Text(composite, SWT.BORDER);
		textdonvi.setBounds(584, 13, 190, 30);
		textdonvi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textdonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbtoanha = new CLabel(composite, SWT.RIGHT);
		lbtoanha.setBounds(784, 13, 94, 30);
		lbtoanha.setText("Tòa nhà");
		lbtoanha.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		texttoanha = new Text(composite, SWT.BORDER);
		texttoanha.setBounds(884, 13, 151, 30);
		texttoanha.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		texttoanha.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(1051, 13, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(1163, 13, 92, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(1261, 13, 113, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnExcel = new Button(composite, SWT.NONE);
		btnExcel.setText("Excel");
		btnExcel.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnExcel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExcel.setBounds(1380, 13, 106, 32);

		btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1492, 13, 94, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1592, 13, 103, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		CLabel lbIP = new CLabel(composite, SWT.RIGHT);
		lbIP.setText("IP");
		lbIP.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbIP.setBounds(28, 51, 60, 30);

		textIP = new Text(composite, SWT.BORDER);
		textIP.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textIP.setBounds(100, 51, 175, 30);

		CLabel lbhedieuhanh = new CLabel(composite, SWT.RIGHT);
		lbhedieuhanh.setText("Hệ điều hành");
		lbhedieuhanh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbhedieuhanh.setBounds(281, 51, 176, 30);

		texthedieuhanh = new Text(composite, SWT.BORDER);
		texthedieuhanh.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		texthedieuhanh.setBackground(SWTResourceManager.getColor(224, 255, 255));
		texthedieuhanh.setBounds(463, 51, 175, 30);

		CLabel lboffice = new CLabel(composite, SWT.RIGHT);
		lboffice.setText("Office");
		lboffice.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lboffice.setBounds(641, 51, 78, 30);

		textoffice = new Text(composite, SWT.BORDER);
		textoffice.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textoffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textoffice.setBounds(725, 51, 166, 30);

		CLabel lbLoaimay = new CLabel(composite, SWT.RIGHT);
		lbLoaimay.setText("Loại máy");
		lbLoaimay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoaimay.setBounds(895, 51, 118, 30);

		textloaimay = new Text(composite, SWT.BORDER);
		textloaimay.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textloaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textloaimay.setBounds(1019, 51, 176, 30);

		CLabel lbEmail = new CLabel(composite, SWT.RIGHT);
		lbEmail.setText("Email");
		lbEmail.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbEmail.setBounds(1201, 51, 78, 30);

		textEmail = new Text(composite, SWT.BORDER);
		textEmail.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textEmail.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textEmail.setBounds(1285, 51, 176, 30);

		btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1467, 49, 149, 32);

		btnGetIP = new Button(composite, SWT.NONE);
		btnGetIP.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/get ip25.png"));
		btnGetIP.setText("Get IP");
		btnGetIP.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnGetIP.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btnGetIP.setBounds(1622, 49, 118, 32);

		btnDetail = new Button(composite, SWT.NONE);
		btnDetail.setImage(SWTResourceManager.getImage(IPList.class, "/itmanagerip/Icon/button/detail30.jpg"));
		btnDetail.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		btnDetail.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btnDetail.setBounds(1746, 49, 125, 32);
		btnDetail.setText("Detail");

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setHeaderBackground(SWTResourceManager.getColor(0, 250, 154));
		table.setLocation(10, 87);
		table.setSize(1883, 905);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(84);
		tbcSTT.setText("STT");

		TableColumn tbcSothe = new TableColumn(table, SWT.NONE);
		tbcSothe.setWidth(102);
		tbcSothe.setText("Số thẻ");

		TableColumn tbcHoten = new TableColumn(table, SWT.NONE);
		tbcHoten.setWidth(194);
		tbcHoten.setText("Họ tên");

		TableColumn tbcDonvi = new TableColumn(table, SWT.NONE);
		tbcDonvi.setWidth(176);
		tbcDonvi.setText("Đơn vị");

		TableColumn tbcIP = new TableColumn(table, SWT.NONE);
		tbcIP.setWidth(139);
		tbcIP.setText("IP");

		TableColumn tbcLoaimay = new TableColumn(table, SWT.NONE);
		tbcLoaimay.setWidth(169);
		tbcLoaimay.setText("Loại máy");

		TableColumn tbcEmail = new TableColumn(table, SWT.NONE);
		tbcEmail.setWidth(171);
		tbcEmail.setText("Email");

		TableColumn tbcHedieuhanh = new TableColumn(table, SWT.NONE);
		tbcHedieuhanh.setWidth(153);
		tbcHedieuhanh.setText("Hệ điều hành");

		TableColumn tbcOffice = new TableColumn(table, SWT.NONE);
		tbcOffice.setWidth(153);
		tbcOffice.setText("Office");

		TableColumn tbcToanha = new TableColumn(table, SWT.NONE);
		tbcToanha.setWidth(147);
		tbcToanha.setText("Tòa nhà");

		TableColumn tbcGhichu = new TableColumn(table, SWT.NONE);
		tbcGhichu.setWidth(85);
		tbcGhichu.setText("Ghi chú");

		TableColumn tbcNgaycapnhat = new TableColumn(table, SWT.NONE);
		tbcNgaycapnhat.setWidth(127);
		tbcNgaycapnhat.setText("Ngày cập nhật");

		TableColumn tbcPersonupdate = new TableColumn(table, SWT.NONE);
		tbcPersonupdate.setWidth(141);
		tbcPersonupdate.setText("Người cập nhật");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbsothe.setText("Số thẻ");
			lbhoten.setText("Họ tên");
			lbdonvi.setText("Đơn vị");
			lbtoanha.setText("Tòa nhà");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			lbIP.setText("IP");
			lbhedieuhanh.setText("Hệ điều hành");
			lboffice.setText("Office");
			lbLoaimay.setText("Loại máy");
			btntimkiem.setText("Tìm kiếm");
			tbcSTT.setText("STT");
			tbcSothe.setText("Số thẻ");
			tbcHoten.setText("Họ tên");
			tbcDonvi.setText("Đơn vị");
			tbcIP.setText("IP");
			tbcLoaimay.setText("Loại máy");
			tbcEmail.setText("Email");
			tbcHedieuhanh.setText("Hệ điều hành");
			tbcOffice.setText("Office");
			tbcToanha.setText("Tòa nhà");
			tbcGhichu.setText("Ghi chú");
			tbcNgaycapnhat.setText("Ngày cập nhật");
			btnGetIP.setText("Lấy IP");
			btnDetail.setText("Chi tiết");
			tbcPersonupdate.setText("Người cập nhật");

		} else {
			// Tieng Anh
			lbsothe.setText("ID");
			lbhoten.setText("Name");
			lbdonvi.setText("Department");
			lbtoanha.setText("Building");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			lbIP.setText("IP");
			lbhedieuhanh.setText("Operator system");
			lboffice.setText("Office");
			lbLoaimay.setText("Machine type");
			btntimkiem.setText("Search");
			tbcSTT.setText("Number");
			tbcSothe.setText("ID");
			tbcHoten.setText("Name");
			tbcDonvi.setText("Department");
			tbcIP.setText("IP");
			tbcLoaimay.setText("Machine type");
			tbcEmail.setText("Email");
			tbcHedieuhanh.setText("Operator system");
			tbcOffice.setText("Office");
			tbcToanha.setText("Building");
			tbcGhichu.setText("Note");
			tbcNgaycapnhat.setText("Date update");
			btnGetIP.setText("Get IP");
			btnDetail.setText("Detail");
			tbcPersonupdate.setText("Person update");
		}

		// là nhóm User thì không cho thêm, sửa, xóa, Xuất Excel, chi tiết
		if (grouplogin.equals("User")) {
			btnthem.setVisible(false);
			btnsua.setVisible(false);
			btnxoa.setVisible(false);
			btnExcel.setVisible(false);
			btnDetail.setVisible(false);
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

		// Tìm kiếm sau khi enter ở text số thẻ
		// ******************************************************************************
		textsothe.addKeyListener(new KeyAdapter() {
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
		texthoten.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text đơn vị
		// ******************************************************************************
		textdonvi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text tòa nhà
		// ******************************************************************************
		texttoanha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text IP
		// ******************************************************************************
		textIP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text hệ điều hành
		// ******************************************************************************
		texthedieuhanh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text office
		// ******************************************************************************
		textoffice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text loại máy
		// ******************************************************************************
		textloaimay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
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
					search(shell);
				}
			}
		});

		// Get IP
		// ---------------------------------------------------------------------------------------------------------------------------
		btnGetIP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GetIP getip = new GetIP();
				getip.setLanguage(ngonngu);
				composite.setEnabled(false);
				getip.open();
				composite.setEnabled(true);
			}
		});

		// enter ở button Get IP
		// ******************************************************************************
		btnGetIP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					GetIP getip = new GetIP();
					getip.setLanguage(ngonngu);
					composite.setEnabled(false);
					getip.open();
					composite.setEnabled(true);
				}
			}
		});

		// Xóa-------------------------------------------------------------------------------------------------------------------------------
		btnxoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteIP();
			}
		});

		// enter ở button Xóa
		// ******************************************************************************
		btnxoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					deleteIP();
				}
			}
		});

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancelIP();
			}
		});

		// enter ở button Hủy
		// ******************************************************************************
		btnHuy.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					cancelIP();
				}
			}
		});

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveIP(shell);
			}
		});

		// enter ở button Lưu
		// ******************************************************************************
		btnLuu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					saveIP(shell);
				}
			}
		});

		// Sửa-----------------------------------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editIP(shell, composite);
			}
		});

		// enter ở button Sửa
		// ******************************************************************************
		btnsua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					editIP(shell, composite);
				}
			}
		});

		// sửa khi double click vào dòng của bảng
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				editIP(shell, composite);
			}
		});

		// sửa khi enter vào dòng của bảng
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					editIP(shell, composite);
				}
			}
		});

		// Thêm--------------------------------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addIP(shell, composite);
			}
		});

		// enter ở button Thêm
		// ******************************************************************************
		btnthem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					addIP(shell, composite);
				}
			}
		});

		// Tìm kiếm
		// --------------------------------------------------------------------------------------------------------------------
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// button Detail
		// *************************************************************************************************************************
		btnDetail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				detail(shell, composite);
			}
		});

		// enter ở button Detail
		// ******************************************************************************
		btnDetail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					detail(shell, composite);
				}
			}
		});

		// Excel
		// ********************************************************************************************************************************************************************
		btnExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Danh sách IP");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "List IP");
				}
			}

		});

		// enter ở button Excel
		// ******************************************************************************
		btnExcel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					if (ngonngu == 0) {
						Excel.exportExcelTable(table, shell, ngonngu, "Danh sách IP");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "List IP");
					}
				}
			}
		});

		// Sort data
		// ************************************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcSothe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoten.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonvi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcIP.addListener(SWT.Selection, sort.sortListenerIP);
		tbcLoaimay.addListener(SWT.Selection, sort.sortListenerCode);
		tbcEmail.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHedieuhanh.addListener(SWT.Selection, sort.sortListenerCode);
		tbcOffice.addListener(SWT.Selection, sort.sortListenerCode);
		tbcToanha.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGhichu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgaycapnhat.addListener(SWT.Selection, sort.sortListenerDate);
		tbcPersonupdate.addListener(SWT.Selection, sort.sortListenerCode);
	}

	// Hiện trong CTabFolder, size monitor 1920x1080
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String groupname, String userlogin, int ngonngu) {
		this.userlogin = userlogin;
		this.ngonngu = ngonngu;
		grouplogin = groupname;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		itemtab.setText("IP");

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);

		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 200);
	}

	// ---------------------------------------------------------------------------------------------
	public void search(Shell shell) {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String selectDanhSachIP = "";
			ResultSet result;
			table.removeAll();

			// text find
			String sothe = textsothe.getText().isEmpty() ? "" : " AND SoThe = '" + textsothe.getText() + "'";
			String hoten = texthoten.getText().isEmpty() ? ""
					: " AND (HoTen LIKE N'%" + texthoten.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](HoTen) LIKE '%" + texthoten.getText() + "%')";
			String donvi = textdonvi.getText().isEmpty() ? ""
					: " AND (DonVi.DonVi LIKE N'%" + textdonvi.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](DonVi.DonVi) LIKE '%" + textdonvi.getText() + "%')";
			String toanha = texttoanha.getText().isEmpty() ? ""
					: " AND (Building.Building LIKE N'%" + texttoanha.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](Building.Building) LIKE '%" + texttoanha.getText()
							+ "%')";
			String email = textEmail.getText().isEmpty() ? "" : " AND Email LIKE '%" + textEmail.getText() + "%'";
			String ip = textIP.getText().isEmpty() ? "" : " AND IP LIKE '%" + textIP.getText() + "'";
			String os = texthedieuhanh.getText().isEmpty() ? ""
					: " AND (HeDieuHanh.HeDieuHanh LIKE N'%" + texthedieuhanh.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](HeDieuHanh.HeDieuHanh) LIKE '%"
							+ texthedieuhanh.getText() + "%')";
			String office = textoffice.getText().isEmpty() ? ""
					: " AND (Office.Office LIKE N'%" + textoffice.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](Office.Office) LIKE '%" + textoffice.getText()
							+ "%')";
			String loaimay = textloaimay.getText().isEmpty() ? ""
					: " AND (LoaiMay.LoaiMay LIKE N'%" + textloaimay.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](LoaiMay.LoaiMay) LIKE '%" + textloaimay.getText()
							+ "%')";

			// xử lý tìm theo lớp IP, nếu textIP nhận vào là lớp IP ví dụ: 30 thay vì nhận
			// được là 30.1 thì ta tìm theo lớp IP chứ không tìm theo IP
			int lopip = -1;
			try {
				lopip = Integer.parseInt(textIP.getText());
			} catch (NumberFormatException ne) {

			}
			if (lopip >= 0) {
				ip = textIP.getText().isEmpty() ? "" : " AND IP LIKE '%" + "192.168." + lopip + "." + "%'";
			}

			selectDanhSachIP = "SELECT SoThe,HoTen ,DonVi.DonVi,IP ,LoaiMay.LoaiMay,Email,HeDieuHanh.HeDieuHanh,Office.Office,Building.Building,GhiChu,NgayCapNhat,NguoiDung.TenNguoiDung FROM DanhSachIP LEFT JOIN DonVi ON DonVi.MaDonVi=DanhSachIP.MaDonVi LEFT JOIN dbo.LoaiMay ON LoaiMay.MaLoaiMay = DanhSachIP.MaLoaiMay LEFT JOIN dbo.HeDieuHanh ON HeDieuHanh.MaHeDieuHanh = DanhSachIP.MaHeDieuHanh LEFT JOIN dbo.Office ON Office.MaOffice = DanhSachIP.MaOffice LEFT JOIN Building ON DanhSachIp.MaBuilding=Building.MaBuilding LEFT JOIN NguoiDung ON DanhSachIP.UserUpdate=NguoiDung.MaNguoiDung OR DanhSachIP.UserUpdate=NguoiDung.TenDangNhap WHERE 1=1"
					+ sothe + hoten + donvi + toanha + email + ip + os + office + loaimay + " ORDER BY NgayCapNhat DESC"
					+ "";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = connect.getStatement().executeQuery(selectDanhSachIP);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						result.getString(8), result.getString(9), result.getString(10),
						ConvertDate.convertDate(result.getString(11)), result.getString(12) });
				stt++;
			}

			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi");
				thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
			} else {
				thongbao.setText("Notice error");
				thongbao.setMessage("Connect error!\n" + se.toString());
			}
		}
	}

	// --------------------------------------------------------------------------------------------
	public void detail(Shell shell, Composite composite) {
		try {
			TableItem[] itemdetail = table.getSelection();
			String sothedetail = itemdetail[0].getText(1);
			String ipwan = itemdetail[0].getText(4);
			String building = itemdetail[0].getText(9);
			String pc_or_wifi = "";

			try {
				if (connect == null) {
					connect = new ConnectSQL();
					connect.setConnection();
				}
				connect.setStatement();

				String select = "SELECT MaLoaiMay FROM LoaiMay WHERE LoaiMay=N'" + itemdetail[0].getText(5) + "'";

				// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
				ResultSet result = connect.getStatement().executeQuery(select);
				while (result.next()) {
					pc_or_wifi = result.getString(1);
				}
				result.close();
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
			}

			if (pc_or_wifi.equals("pc")) {
				DetailPC detail = new DetailPC();
				detail.setLanguage(ngonngu, userlogin);
				detail.createContents();
				detail.setDataDefault(sothedetail);
				composite.setEnabled(false);
				detail.open();
				composite.setEnabled(true);
			} else if (pc_or_wifi.equals("wifi")) {
				DetailWifi detail = new DetailWifi();
				detail.setLanguage(ngonngu);
				detail.createContents();
				detail.setDataDefault(sothedetail, ipwan, building, userlogin);
				composite.setEnabled(false);
				detail.open();
				composite.setEnabled(true);
			}
		} catch (Exception ae) {
		}
	}

	// ------------------------------------------------------------------------------------------
	public void addIP(Shell shell, Composite composite) {

		NewIP newip = new NewIP();
		// mở cửa sổ mới thì cửa sổ củ không cho thao tác
		composite.setEnabled(false);
		newip.setUserLogin(userlogin);
		newip.setLanguage(ngonngu);
		newip.open();
		composite.setEnabled(true);
		// Cập nhật lại dữ liệu sau khi thêm, gọi lại tìm kiếm
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String selectDanhSachIP = "";
			ResultSet result;
			table.removeAll();

			// text find
			String sothe = textsothe.getText().isEmpty() ? "" : " AND SoThe LIKE '%" + textsothe.getText() + "%'";
			String hoten = texthoten.getText().isEmpty() ? ""
					: " AND (HoTen LIKE N'%" + texthoten.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](HoTen) LIKE '%" + texthoten.getText() + "%')";
			String donvi = textdonvi.getText().isEmpty() ? ""
					: " AND (DonVi.DonVi LIKE N'%" + textdonvi.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](DonVi.DonVi) LIKE '%" + textdonvi.getText() + "%')";
			String toanha = texttoanha.getText().isEmpty() ? ""
					: " AND (Building.Building LIKE N'%" + texttoanha.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](Building.Building) LIKE '%" + texttoanha.getText()
							+ "%')";
			String email = textEmail.getText().isEmpty() ? "" : " AND Email LIKE '%" + textEmail.getText() + "%'";
			String ip = textIP.getText().isEmpty() ? "" : " AND IP LIKE '%" + textIP.getText() + "'";
			String os = texthedieuhanh.getText().isEmpty() ? ""
					: " AND (HeDieuHanh.HeDieuHanh LIKE N'%" + texthedieuhanh.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](HeDieuHanh.HeDieuHanh) LIKE '%"
							+ texthedieuhanh.getText() + "%')";
			String office = textoffice.getText().isEmpty() ? ""
					: " AND (Office.Office LIKE N'%" + textoffice.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](Office.Office) LIKE '%" + textoffice.getText()
							+ "%')";
			String loaimay = textloaimay.getText().isEmpty() ? ""
					: " AND (LoaiMay.LoaiMay LIKE N'%" + textloaimay.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](LoaiMay.LoaiMay) LIKE '%" + textloaimay.getText()
							+ "%')";

			// xử lý tìm theo lớp IP, nếu textIP nhận vào là lớp IP ví dụ: 30 thay vì nhận
			// được là 30.1 thì ta tìm theo lớp IP chứ không tìm theo IP
			int lopip = -1;
			try {
				lopip = Integer.parseInt(textIP.getText());
			} catch (NumberFormatException ne) {

			}
			if (lopip >= 0) {
				ip = textIP.getText().isEmpty() ? "" : " AND IP LIKE '%" + "192.168." + lopip + "." + "%'";
			}

			selectDanhSachIP = "SELECT SoThe,HoTen ,DonVi.DonVi,IP ,LoaiMay.LoaiMay,Email,HeDieuHanh.HeDieuHanh,Office.Office,Building.Building,GhiChu,NgayCapNhat,NguoiDung.TenNguoiDung FROM DanhSachIP LEFT JOIN DonVi ON DonVi.MaDonVi=DanhSachIP.MaDonVi LEFT JOIN dbo.LoaiMay ON LoaiMay.MaLoaiMay = DanhSachIP.MaLoaiMay LEFT JOIN dbo.HeDieuHanh ON HeDieuHanh.MaHeDieuHanh = DanhSachIP.MaHeDieuHanh LEFT JOIN dbo.Office ON Office.MaOffice = DanhSachIP.MaOffice LEFT JOIN Building ON DanhSachIp.MaBuilding=Building.MaBuilding LEFT JOIN NguoiDung ON DanhSachIP.UserUpdate=NguoiDung.MaNguoiDung WHERE 1=1"
					+ sothe + hoten + donvi + toanha + email + ip + os + office + loaimay + " ORDER BY NgayCapNhat DESC"
					+ "";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = connect.getStatement().executeQuery(selectDanhSachIP);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						result.getString(8), result.getString(9), result.getString(10),
						ConvertDate.convertDate(result.getString(11)), result.getString(12) });
				stt++;
			}

			result.close();
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
		}
	}

	// ---------------------------------------------------------------------------------------------
	public void editIP(Shell shell, Composite composite) {
		try {
			TableItem[] itemsua = table.getSelection();
			String sothesua = "";
			sothesua = itemsua[0].getText(1);
			if (!sothesua.isEmpty()) {
				EditIP editip = new EditIP();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				editip.setDataIPDefault(itemsua[0].getText(1), itemsua[0].getText(2), itemsua[0].getText(3),
						itemsua[0].getText(4), itemsua[0].getText(5), itemsua[0].getText(6), itemsua[0].getText(7),
						itemsua[0].getText(8), itemsua[0].getText(9), itemsua[0].getText(10));
				editip.setLanguage(ngonngu);
				editip.setUserLogin(userlogin);
				editip.open();
				composite.setEnabled(true);
				if (editip.isEditSuccess()) {
					search(shell);
				}
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

	// --------------------------------------------------------------------------------------------------
	public void saveIP(Shell shell) {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			try {
				String deleteDanhSachIP = "";
				for (int i = 0; i < sothexoa.length; i++) {
					deleteDanhSachIP = deleteDanhSachIP + "\n" + "DELETE DanhSachIP WHERE SoThe='" + sothexoa[i] + "'";
				}

				int result = connect.execUpdateQuery(deleteDanhSachIP);
				if (result > 0) {
					table.remove(table.getSelectionIndices());
				}
				connect.closeStatement();
				btnthem.setEnabled(true);
				btnsua.setEnabled(true);
				btnExcel.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnGetIP.setEnabled(true);
				btnDetail.setEnabled(true);
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

	// ------------------------------------------------------------------------------------------
	public void cancelIP() {
		btnthem.setEnabled(true);
		btnsua.setEnabled(true);
		btnExcel.setEnabled(true);
		btntimkiem.setEnabled(true);
		btnGetIP.setEnabled(true);
		btnDetail.setEnabled(true);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);
		table.setEnabled(true);
	}

	// -----------------------------------------------------------------------------------------------
	public void deleteIP() {
		try {
			// Lấy cột số thẻ Code
			itemtable = table.getSelection();
			sothexoa = new String[itemtable.length];
			for (int i = 0; i < sothexoa.length; i++) {
				sothexoa[i] = itemtable[i].getText(1);
			}
			if (sothexoa.length > 0) {
				btnthem.setEnabled(false);
				btnsua.setEnabled(false);
				btnExcel.setEnabled(false);
				btntimkiem.setEnabled(false);
				btnGetIP.setEnabled(false);
				btnDetail.setEnabled(false);

				btnLuu.setVisible(true);
				btnHuy.setVisible(true);
				table.setEnabled(false);
			}

		} catch (Exception ex) {
			btnthem.setEnabled(true);
			btnsua.setEnabled(true);
			btnExcel.setEnabled(true);
			btntimkiem.setEnabled(true);
			btnGetIP.setEnabled(true);
			btnDetail.setEnabled(true);
			btnLuu.setVisible(false);
			btnHuy.setVisible(false);
			table.setEnabled(true);
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
	}
}
