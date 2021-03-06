package it;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.DateTime;

public class MuonThietBiPhongHop {
	private Table table;
	private TableItem[] itemtable;
	private String[] mamuoncameraxoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private int ngonngu = 1;
	private Text textHoTen;
	private Text textMayAnh;
	private Text textSoThe;
	private CCombo comboDaTraChua;
	private CCombo comboDonVi;
	private CCombo comboNguoiChoMuon;
	private CCombo comboNguoiNhanTra;
	private DateTime dateTimeThoiGianMuon1;
	private DateTime dateTimeThoiGianMuon2;
	private String userlogin = "21608";
	private Button btnXoa;

	public static void main(String[] args) {
		try {
			MuonThietBiPhongHop window = new MuonThietBiPhongHop();
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
		shell.setText("Borrow meeting room equipment");
		shell.setImage(SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("M?????n thi???t b??? ph??ng h???p");
		} else {
			shell.setText("Borrow meeting room equipment");
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

		Composite composite = new Composite(scrolledComposite, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);
		scrolledComposite.setContent(composite);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("S??? th???");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(10, 13, 70, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(86, 13, 88, 30);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(180, 13, 80, 30);
		lbHoTen.setText("H??? t??n");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setTextLimit(30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(266, 13, 169, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(441, 13, 106, 30);
		lbDonVi.setText("????n v???");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(553, 13, 149, 30);

		CLabel lbThietbi = new CLabel(composite, SWT.RIGHT);
		lbThietbi.setText("Thi???t b???");
		lbThietbi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThietbi.setBounds(708, 13, 80, 30);

		textMayAnh = new Text(composite, SWT.BORDER);
		textMayAnh.setTextLimit(30);
		textMayAnh.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMayAnh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textMayAnh.setBounds(794, 13, 88, 30);

		comboDaTraChua = new CCombo(composite, SWT.BORDER);
		comboDaTraChua.setItems(new String[] { "Ch??a tr???", "???? tr???" });
		comboDaTraChua.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDaTraChua.setBounds(888, 13, 115, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("T??m ki???m");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1009, 13, 131, 32);

		Button btnExport = new Button(composite, SWT.NONE);
		btnExport.setText("Xu???t");
		btnExport.setImage(
				SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExport.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnExport.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExport.setBounds(1144, 13, 106, 32);

		Button btnMuon = new Button(composite, SWT.NONE);
		btnMuon.setImage(
				SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/button/borrow24.png"));
		btnMuon.setBounds(1254, 13, 106, 32);
		btnMuon.setText("M?????n");
		btnMuon.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnMuon.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btntra = new Button(composite, SWT.NONE);
		btntra.setImage(
				SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/button/return24.png"));
		btntra.setBounds(1364, 13, 106, 32);
		btntra.setText("Tr???");
		btntra.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btntra.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		CLabel lbThoiGianMuon = new CLabel(composite, SWT.RIGHT);
		lbThoiGianMuon.setText("Th???i gian m?????n");
		lbThoiGianMuon.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGianMuon.setBounds(10, 56, 140, 30);

		dateTimeThoiGianMuon1 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGianMuon1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGianMuon1.setBounds(156, 56, 120, 30);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-7);
		dateTimeThoiGianMuon1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbDauNga = new CLabel(composite, SWT.CENTER);
		lbDauNga.setText("~");
		lbDauNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDauNga.setBounds(279, 56, 22, 30);

		dateTimeThoiGianMuon2 = new DateTime(composite, SWT.BORDER);
		dateTimeThoiGianMuon2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeThoiGianMuon2.setBounds(305, 56, 120, 30);

		CLabel lbNguoiChoMuon = new CLabel(composite, SWT.RIGHT);
		lbNguoiChoMuon.setText("Ng?????i cho m?????n");
		lbNguoiChoMuon.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiChoMuon.setBounds(431, 56, 139, 30);

		comboNguoiChoMuon = new CCombo(composite, SWT.BORDER);
		comboNguoiChoMuon.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNguoiChoMuon.setBounds(576, 56, 192, 30);

		CLabel lbNguoiNhanTra = new CLabel(composite, SWT.RIGHT);
		lbNguoiNhanTra.setText("Ng?????i nh???n tr???");
		lbNguoiNhanTra.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiNhanTra.setBounds(774, 56, 131, 30);

		comboNguoiNhanTra = new CCombo(composite, SWT.BORDER);
		comboNguoiNhanTra.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNguoiNhanTra.setBounds(911, 56, 192, 30);

		btnXoa = new Button(composite, SWT.NONE);
		btnXoa.setImage(SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/button/delete.png"));
		btnXoa.setBounds(1109, 56, 120, 32);
		btnXoa.setText("X??a");
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1235, 56, 109, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(MuonThietBiPhongHop.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1350, 56, 120, 32);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 95);
		table.setSize(1870, 600);
		table.setSize(Display.getDefault().getBounds().width - 30, Display.getDefault().getBounds().height - 230);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 215, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(83);
		tbcSTT.setText("STT");

		TableColumn tbcMamuonThietBi = new TableColumn(table, SWT.NONE);
		tbcMamuonThietBi.setWidth(0);
		tbcMamuonThietBi.setResizable(false);
		tbcMamuonThietBi.setText("M?? m?????n thi???t b???");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(100);
		tbcSoThe.setText("S??? th???");

		TableColumn tbcHoTen = new TableColumn(table, SWT.NONE);
		tbcHoTen.setWidth(234);
		tbcHoTen.setText("H??? t??n");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(168);
		tbcDonVi.setText("????n v???");

		TableColumn tbcThietBiMuon = new TableColumn(table, SWT.NONE);
		tbcThietBiMuon.setWidth(159);
		tbcThietBiMuon.setText("Thi???t b??? m?????n");

		TableColumn tbcTrangThaiSauMuon = new TableColumn(table, SWT.NONE);
		tbcTrangThaiSauMuon.setWidth(170);
		tbcTrangThaiSauMuon.setText("Tr???ng th??i sau m?????n");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(109);
		tbcGhiChu.setText("Ghi ch??");

		TableColumn tbcNguoiChoMuon = new TableColumn(table, SWT.NONE);
		tbcNguoiChoMuon.setWidth(170);
		tbcNguoiChoMuon.setText("Ng?????i cho m?????n");

		TableColumn tbcNguoiNhanTra = new TableColumn(table, SWT.NONE);
		tbcNguoiNhanTra.setWidth(169);
		tbcNguoiNhanTra.setText("Ng?????i nh???n tr???");

		TableColumn tbcThoiGianMuon = new TableColumn(table, SWT.NONE);
		tbcThoiGianMuon.setWidth(175);
		tbcThoiGianMuon.setText("Th???i gian m?????n");

		TableColumn tbcThoiGianTra = new TableColumn(table, SWT.NONE);
		tbcThoiGianTra.setWidth(172);
		tbcThoiGianTra.setText("Th???i gian tr???");

		TableColumn tbcNguoiTra = new TableColumn(table, SWT.NONE);
		tbcNguoiTra.setWidth(200);
		tbcNguoiTra.setText("Ng?????i tr???");

		TableColumn tbcSoTheTra = new TableColumn(table, SWT.NONE);
		tbcSoTheTra.setWidth(97);
		tbcSoTheTra.setText("S??? th???");

		TableColumn tbcDonViTra = new TableColumn(table, SWT.NONE);
		tbcDonViTra.setWidth(172);
		tbcDonViTra.setText("????n v???");

		btnXoa.setVisible(false);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbSoThe.setText("S??? th???");
			lbHoTen.setText("H??? t??n");
			lbDonVi.setText("????n v???");
			lbThietbi.setText("Thi???t b???");
			btntimkiem.setText("T??m ki???m");
			btnExport.setText("Xu???t");
			btnMuon.setText("M?????n");
			btntra.setText("Tr???");
			lbThoiGianMuon.setText("Th???i gian m?????n");
			lbNguoiChoMuon.setText("Ng?????i cho m?????n");
			lbNguoiNhanTra.setText("Ng?????i nh???n tr???");
			btnXoa.setText("X??a");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");

			tbcSTT.setText("STT");
			tbcMamuonThietBi.setText("M?? m?????n thi???t b???");
			tbcSoThe.setText("S??? th???");
			tbcHoTen.setText("H??? t??n");
			tbcDonVi.setText("????n v???");
			tbcThietBiMuon.setText("Thi???t b??? m?????n");
			tbcTrangThaiSauMuon.setText("Tr???ng th??i sau m?????n");
			tbcGhiChu.setText("Ghi ch??");
			tbcNguoiChoMuon.setText("Ng?????i cho m?????n");
			tbcNguoiNhanTra.setText("Ng?????i nh???n tr???");
			tbcThoiGianMuon.setText("Th???i gian m?????n");
			tbcThoiGianTra.setText("Th???i gian tr???");
			tbcNguoiTra.setText("Ng?????i tr???");
			tbcSoTheTra.setText("S??? th???");
			tbcDonViTra.setText("????n v???");
		} else {
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbThietbi.setText("Device");
			btntimkiem.setText("Search");
			btnExport.setText("Export");
			btnMuon.setText("Borrow");
			btntra.setText("Return");
			lbThoiGianMuon.setText("Borrowed time");
			lbNguoiChoMuon.setText("Person for borrow");
			lbNguoiNhanTra.setText("Recipient return");
			btnXoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Number");
			tbcMamuonThietBi.setText("Borrow device id");
			tbcSoThe.setText("ID");
			tbcHoTen.setText("Name");
			tbcDonVi.setText("Department");
			tbcThietBiMuon.setText("Borrowed equipment");
			tbcTrangThaiSauMuon.setText("Post-borrowed status");
			tbcGhiChu.setText("Note");
			tbcNguoiChoMuon.setText("Person for borrow");
			tbcNguoiNhanTra.setText("Recipient return");
			tbcThoiGianMuon.setText("Borrowed time");
			tbcThoiGianTra.setText("Time return");
			tbcNguoiTra.setText("Sender");
			tbcSoTheTra.setText("ID");
			tbcDonViTra.setText("Department");
		}

		// Event ph??m t???t (ctrl + H), ALT + H
		com.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.ALT) {
					btnXoa.setVisible(true);
				} else if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					btnXoa.setVisible(false);
				}
			}
		});
		// Event ph??m t???t (ctrl + H), ALT + H
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.ALT) {
					btnXoa.setVisible(true);
				} else if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					btnXoa.setVisible(false);
				}
			}
		});

		// l???y d??? li???u cho combo ????n v???
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [DonVi] FROM [dbo].[DonVi] ORDER BY [DonVi] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboDonVi.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Th??ng b??o l???i!");
				thongbao.setMessage("L???i!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// l???y d??? li???u cho combo ng?????i cho m?????n, ng?????i nh???n tr???
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [TenNguoiDung] FROM [dbo].[NguoiDung] WHERE [TenNhom] IN ('Manager','Admin','Translate') ORDER BY [TenNguoiDung] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboNguoiChoMuon.add(result.getString(1));
				comboNguoiNhanTra.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Th??ng b??o l???i!");
				thongbao.setMessage("L???i!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// Event ph??m t???t (ctrl + A)
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t (ctrl + A) ch???n h???t b???ng
				if (e.keyCode == 'a' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					table.selectAll();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text so the
		// ******************************************************************************
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		textHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		textMayAnh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		comboDaTraChua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		comboDonVi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		comboNguoiChoMuon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		comboNguoiNhanTra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// T??m ki???m sau khi enter ??? button t??m ki???m
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Export
		btnExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "M?????n thi???t b??? ph??ng h???p");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Borrow meeting room equipment");
				}
			}
		});

		// Export sau khi enter ??? button Export
		// ******************************************************************************
		btnExport.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					if (ngonngu == 0) {
						Excel.exportExcelTable(table, shell, ngonngu, "M?????n thi???t b??? ph??ng h???p");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "Borrow meeting room equipment");
					}
				}
			}
		});

		// Borrow-------------------------------------------------------------------------------------------------
		btnMuon.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MuonThietBiPhongHopBorrow muonthietbi = new MuonThietBiPhongHopBorrow();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				muonthietbi.setData(ngonngu, userlogin);
				muonthietbi.createContents();
				muonthietbi.open();
				composite.setEnabled(true);

				if (muonthietbi.isBorrowSuccess()) {
					search(shell);
				}
			}
		});

		// Return---------------------------------------------------------------------------------------------------
		btntra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MuonThietBiPhongHopReturn trathietbi = new MuonThietBiPhongHopReturn();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				trathietbi.setData(ngonngu, userlogin);
				trathietbi.createContents();
				trathietbi.open();
				composite.setEnabled(true);

				if (trathietbi.isReturnSuccess()) {
					search(shell);
				}
			}
		});

		// X??a-------------------------------------------------------------------
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// L???y c???t s??? m?? thi???t b???
					itemtable = table.getSelection();
					mamuoncameraxoa = new String[itemtable.length];

					for (int i = 0; i < mamuoncameraxoa.length; i++) {
						mamuoncameraxoa[i] = itemtable[i].getText(1);
					}
					if (mamuoncameraxoa.length > 0) {
						btnMuon.setEnabled(false);
						btntra.setEnabled(false);
						btntimkiem.setEnabled(false);
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						table.setEnabled(false);
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Th??ng b??o");
							thongbao.setMessage("Kh??ng c?? d??ng n??o ???????c ch???n!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("No line is selected!");
						}
						thongbao.open();
					}

				} catch (Exception ex) {
					btnMuon.setEnabled(true);
					btntra.setEnabled(true);
					btntimkiem.setEnabled(true);
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
					table.setEnabled(true);
				}
			}
		});

		// L??u--------------------------------------------------------------------------------------------------------------
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
						for (int i = 0; i < mamuoncameraxoa.length; i++) {
							delete = delete + "\n"
									+ "DELETE FROM [dbo].[IT_ChoMuonThietBiPhongHop] WHERE [IT_ChoMuonThietBiPhongHop].[MaMuonThietBi]='"
									+ mamuoncameraxoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						connect.closeStatement();
						btnMuon.setEnabled(true);
						btntra.setEnabled(true);
						btntimkiem.setEnabled(true);
						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						table.setEnabled(true);
					} catch (Exception ex) {
					}

				} catch (Exception se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Th??ng b??o l???i");
						thongbao.setMessage("L???i!\n" + se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Error!\n" + se.toString());
					}
					thongbao.open();
				}
			}
		});

		// H???y--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnMuon.setEnabled(true);
				btntra.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// mo cua so tra thiet bi phong hop click khi double click vao dong cua bang
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					TableItem[] item = table.getSelection();
					if (item[0].getText(12).isEmpty()) {
						MuonThietBiPhongHopReturnClick muon = new MuonThietBiPhongHopReturnClick();
						muon.setData(ngonngu, userlogin);
						muon.setSoTheMuon(item[0].getText(2));
						muon.createContents();
						muon.open();
						if (muon.isReturnSuccess()) {
							search(shell);
						}
					}
				} catch (Exception exc) {

				}
			}
		});

		// **********************************************************************************
		// S???p x???p table theo m???t c???t ???? ch???n
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoTen.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThietBiMuon.addListener(SWT.Selection, sort.sortListenerCode);
		tbcTrangThaiSauMuon.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiChoMuon.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiNhanTra.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThoiGianMuon.addListener(SWT.Selection, sort.sortListenerDateTime);
		tbcThoiGianTra.addListener(SWT.Selection, sort.sortListenerDateTime);
		tbcNguoiTra.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoTheTra.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonViTra.addListener(SWT.Selection, sort.sortListenerCode);

		// c??i n??y ph???i ??? cu???i c??ng th?? m???i scroll ???????c
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu, String userlogin) {
		this.userlogin = userlogin;
		this.ngonngu = ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("M?????n thi???t b??? ph??ng h???p");
		} else {
			itemtab.setText("Borrow meeting room equipment");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);

		createContents(composite, shell);
		// Event ph??m t???t (ctrl + H), ALT + H
		tabfolder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.ALT) {
					btnXoa.setVisible(true);
				} else if (e.keyCode == 'h' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					btnXoa.setVisible(false);
				}
			}
		});
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

			String camera = textMayAnh.getText().isEmpty() ? ""
					: " AND [IT_ThietBiChoMuon].[TenThietBi] LIKE N'%" + textMayAnh.getText() + "%'";
			String sothe = textSoThe.getText().isEmpty() ? ""
					: " AND [IT_ChoMuonThietBiPhongHop].[SoThe]='" + textSoThe.getText() + "'";
			String donvi = comboDonVi.getText().isEmpty() ? ""
					: " AND [IT_ChoMuonThietBiPhongHop].[DonVi]=N'" + comboDonVi.getText() + "'";

			String datrachua = "";
			if (comboDaTraChua.getText().isEmpty()) {
				datrachua = "";
			} else if (comboDaTraChua.getSelectionIndex() == 0) {
				datrachua = " AND [IT_ChoMuonThietBiPhongHop].[ThoiGianTra] IS NULL";
			} else if (comboDaTraChua.getSelectionIndex() == 1) {
				datrachua = " AND [IT_ChoMuonThietBiPhongHop].[ThoiGianTra] IS NOT NULL";
			}

			String nguoichomuon = comboNguoiChoMuon.getText().isEmpty() ? ""
					: " AND [NguoiDung].[TenNguoiDung]=N'" + comboNguoiChoMuon.getText() + "'";
			String nguoinhantra = comboNguoiNhanTra.getText().isEmpty() ? ""
					: " AND ND.[TenNguoiDung]=N'" + comboNguoiNhanTra.getText() + "'";

			String month = "0" + (dateTimeThoiGianMuon1.getMonth() + 1);
			month = month.substring(month.length() - 2);
			String day = "0" + dateTimeThoiGianMuon1.getDay();
			day = day.substring(day.length() - 2);
			String ngaybatdau = dateTimeThoiGianMuon1.getYear() + month + day;

			String month2 = "0" + (dateTimeThoiGianMuon2.getMonth() + 1);
			month2 = month2.substring(month2.length() - 2);
			String day2 = "0" + dateTimeThoiGianMuon2.getDay();
			day2 = day2.substring(day2.length() - 2);
			String ngayketthuc = dateTimeThoiGianMuon2.getYear() + month2 + day2;
			String ThoiGianMuon = " AND [IT_ChoMuonThietBiPhongHop].[ThoiGianMuon] BETWEEN '" + ngaybatdau
					+ " 00:00:00' AND '" + ngayketthuc + " 23:59:59'";

			String select = "SELECT [IT_ChoMuonThietBiPhongHop].[MaMuonThietBi],[IT_ChoMuonThietBiPhongHop].[SoThe],[IT_ChoMuonThietBiPhongHop].[HoTen],[IT_ChoMuonThietBiPhongHop].[DonVi],[IT_ThietBiChoMuon].[TenThietBi],CASE WHEN [IT_ChoMuonThietBiPhongHop].[TrangThaiSauMuon]=0 THEN N'???? h??' WHEN [IT_ChoMuonThietBiPhongHop].[TrangThaiSauMuon]=1 THEN N'B??nh th?????ng' WHEN [IT_ChoMuonThietBiPhongHop].[TrangThaiSauMuon]=2 THEN N'B??? m???t' ELSE '' END AS TrangThaiSauMuon,[IT_ChoMuonThietBiPhongHop].[GhiChu],[NguoiDung].[TenNguoiDung] AS 'NguoiChoMuon',ND.[TenNguoiDung] AS 'NguoiNhanTra',[IT_ChoMuonThietBiPhongHop].[ThoiGianMuon],[IT_ChoMuonThietBiPhongHop].[ThoiGianTra],[IT_ChoMuonThietBiPhongHop].[HoTenTra],[IT_ChoMuonThietBiPhongHop].[SoTheTra],[IT_ChoMuonThietBiPhongHop].[DonViTra] FROM [dbo].[IT_ChoMuonThietBiPhongHop] LEFT JOIN [dbo].[NguoiDung] ON [dbo].[NguoiDung].[MaNguoiDung]=[dbo].[IT_ChoMuonThietBiPhongHop].[NguoiChoMuon] OR [dbo].[NguoiDung].[TenDangNhap]=[dbo].[IT_ChoMuonThietBiPhongHop].[NguoiChoMuon] LEFT JOIN [dbo].[NguoiDung] AS ND ON ND.[MaNguoiDung]=[dbo].[IT_ChoMuonThietBiPhongHop].[NguoiNhanTra] OR ND.[TenDangNhap]=[dbo].[IT_ChoMuonThietBiPhongHop].[NguoiNhanTra] LEFT JOIN [dbo].[IT_ThietBiChoMuon] ON [dbo].[IT_ThietBiChoMuon].[MaThietBi]=[dbo].[IT_ChoMuonThietBiPhongHop].[MaThietBi] WHERE ([IT_ChoMuonThietBiPhongHop].[HoTen] LIKE N'%"
					+ textHoTen.getText()
					+ "%' OR [dbo].[convertUnicodetoASCII]([IT_ChoMuonThietBiPhongHop].[HoTen]) LIKE '%"
					+ textHoTen.getText() + "%') AND [IT_ThietBiChoMuon].[LoaiThietBi]=3";
			select = select + camera + sothe + donvi + datrachua + nguoichomuon + nguoinhantra + ThoiGianMuon
					+ " ORDER BY [IT_ChoMuonThietBiPhongHop].[ThoiGianMuon] DESC";

			// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						result.getString(8), result.getString(9), ConvertDate.convertDateTime(result.getString(10)),
						ConvertDate.convertDateTime(result.getString(11)), result.getString(12), result.getString(13),
						result.getString(14) });
				stt++;
				if (ConvertDate.getCountDate(result.getString(10)) >= 7 && result.getString(11) == null) {
					item.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				} else if (ConvertDate.getCountDate(result.getString(10)) >= 3 && result.getString(11) == null) {
					item.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				} else if (ConvertDate.getCountDate(result.getString(10)) >= 1 && result.getString(11) == null) {
					item.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
				}
			}

			result.close();
			state.close();
			connect.closeStatement();

		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Th??ng b??o l???i");
				thongbao.setMessage("L???i!\n" + se.toString());
			} else {
				thongbao.setText("Notice error");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}
	}

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String nguoidangnhap) {
		ngonngu = language;
		userlogin = nguoidangnhap;
	}

	// -------------------------------------------------------------------------------------------
	public String getDateString(String chuoi1, String chuoi2) {
		if (chuoi1.isEmpty()) {
			return "";
		} else {
			return ConvertDate.convertDate(chuoi2);
		}
	}
}
