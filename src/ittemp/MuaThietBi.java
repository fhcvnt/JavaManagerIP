package ittemp;

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

import sort.SortData;
import sql.ConnectSQL;

public class MuaThietBi {
	private Table table;
	private TableItem[] itemtable;
	private String[] sobpmxoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Text textMaPhieu;
	private Text textSoBPM;
	private CCombo comboThietBi;
	private int ngonngu = 1;
	private Text textSoLuong;
	private Text textThoiGianBH;
	private Button btnCheckNhapKhoIT;
	private DateTime dateTimeNgayMua1;
	private DateTime dateTimeNgayMua2;
	private DateTime dateTimeNgayNhapKhoIT1;
	private DateTime dateTimeNgayNhapKhoIT2;
	private String userlogin = "21608";

	public static void main(String[] args) {
		try {
			MuaThietBi window = new MuaThietBi();
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
		shell.setImage(SWTResourceManager.getImage(MuaThietBi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Buy device");
		if (ngonngu == 0) {
			shell.setText("Mua thi???t b???");
		} else {
			shell.setText("Buy device");
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

		CLabel lbMaPhieu = new CLabel(composite, SWT.RIGHT);
		lbMaPhieu.setBounds(10, 13, 92, 30);
		lbMaPhieu.setText("M?? phi???u");
		lbMaPhieu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textMaPhieu = new Text(composite, SWT.BORDER);
		textMaPhieu.setBounds(108, 13, 104, 30);
		textMaPhieu.setTextLimit(12);
		textMaPhieu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaPhieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setBounds(218, 13, 113, 30);
		lbSoBPM.setText("S??? BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setBounds(337, 13, 169, 30);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setTextLimit(13);

		CLabel lbThietBi = new CLabel(composite, SWT.RIGHT);
		lbThietBi.setBounds(512, 13, 92, 30);
		lbThietBi.setText("Thi???t b???");
		lbThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboThietBi = new CCombo(composite, SWT.BORDER);
		comboThietBi.setBounds(610, 13, 173, 30);
		comboThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));

		CLabel lbSoLuong = new CLabel(composite, SWT.RIGHT);
		lbSoLuong.setText("S??? l?????ng");
		lbSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoLuong.setBounds(789, 13, 92, 30);

		textSoLuong = new Text(composite, SWT.BORDER);
		textSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoLuong.setBounds(887, 13, 72, 30);
		textSoLuong.setTextLimit(4);

		CLabel lbNgayMua = new CLabel(composite, SWT.RIGHT);
		lbNgayMua.setText("Ng??y mua");
		lbNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayMua.setBounds(965, 13, 113, 30);

		dateTimeNgayMua1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayMua1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayMua1.setBounds(1084, 13, 129, 30);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-30);
		dateTimeNgayMua1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbdaunga1 = new CLabel(composite, SWT.RIGHT);
		lbdaunga1.setAlignment(SWT.CENTER);
		lbdaunga1.setText("~");
		lbdaunga1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga1.setBounds(1219, 13, 26, 30);

		dateTimeNgayMua2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayMua2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayMua2.setBounds(1251, 13, 129, 30);

		CLabel lbThoigianBH = new CLabel(composite, SWT.RIGHT);
		lbThoigianBH.setText("Th???i gian BH");
		lbThoigianBH.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoigianBH.setBounds(10, 49, 132, 30);

		textThoiGianBH = new Text(composite, SWT.BORDER);
		textThoiGianBH.setTextLimit(12);
		textThoiGianBH.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textThoiGianBH.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textThoiGianBH.setBounds(148, 49, 64, 30);
		textThoiGianBH.setTextLimit(3);

		CLabel lbThang = new CLabel(composite, SWT.RIGHT);
		lbThang.setAlignment(SWT.LEFT);
		lbThang.setText("ng??y");
		lbThang.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.ITALIC));
		lbThang.setBounds(218, 49, 72, 30);

		btnCheckNhapKhoIT = new Button(composite, SWT.CHECK);
		btnCheckNhapKhoIT.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckNhapKhoIT.setBounds(296, 59, 113, 16);
		btnCheckNhapKhoIT.setText("Nh???p kho IT");

		dateTimeNgayNhapKhoIT1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayNhapKhoIT1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayNhapKhoIT1.setBounds(415, 49, 129, 30);
		dateTimeNgayNhapKhoIT1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbdaunga2 = new CLabel(composite, SWT.RIGHT);
		lbdaunga2.setText("~");
		lbdaunga2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga2.setAlignment(SWT.CENTER);
		lbdaunga2.setBounds(550, 49, 26, 30);

		dateTimeNgayNhapKhoIT2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayNhapKhoIT2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayNhapKhoIT2.setBounds(582, 49, 129, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(MuaThietBi.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("T??m ki???m");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(717, 49, 149, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(MuaThietBi.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(872, 49, 106, 32);
		btnthem.setText("Th??m");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(MuaThietBi.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(984, 49, 92, 32);
		btnsua.setText("S???a");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(MuaThietBi.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(1082, 49, 113, 32);
		btnxoa.setText("X??a");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(MuaThietBi.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1201, 49, 94, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(MuaThietBi.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1301, 49, 103, 32);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 92);
		table.setSize(1900, 919);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 205);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(83);
		tbcSTT.setText("STT");

		TableColumn tbcMaPhieu = new TableColumn(table, SWT.NONE);
		tbcMaPhieu.setWidth(136);
		tbcMaPhieu.setText("M?? phi???u");

		TableColumn tbcSoBPM = new TableColumn(table, SWT.NONE);
		tbcSoBPM.setWidth(165);
		tbcSoBPM.setText("S??? BPM");

		TableColumn tbcThietBi = new TableColumn(table, SWT.NONE);
		tbcThietBi.setWidth(146);
		tbcThietBi.setText("Thi???t b???");

		TableColumn tbcSoLuong = new TableColumn(table, SWT.NONE);
		tbcSoLuong.setWidth(104);
		tbcSoLuong.setText("S??? l?????ng");

		TableColumn tbcNgayMua = new TableColumn(table, SWT.NONE);
		tbcNgayMua.setWidth(157);
		tbcNgayMua.setText("Ng??y mua");

		TableColumn tbcThoiGianBaoHanh = new TableColumn(table, SWT.NONE);
		tbcThoiGianBaoHanh.setWidth(157);
		tbcThoiGianBaoHanh.setText("Th???i gian b???o h??nh");

		TableColumn tbcNgaynhapkhoIT = new TableColumn(table, SWT.NONE);
		tbcNgaynhapkhoIT.setWidth(157);
		tbcNgaynhapkhoIT.setText("Ng??y nh???p kho IT");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(157);
		tbcGhiChu.setText("Ghi ch??");

		TableColumn tbcTenTep = new TableColumn(table, SWT.NONE);
		tbcTenTep.setWidth(157);
		tbcTenTep.setText("T??n t???p");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(157);
		tbcNguoiCapNhat.setText("Ng?????i c???p nh???t");

		dateTimeNgayNhapKhoIT1.setEnabled(false);
		dateTimeNgayNhapKhoIT2.setEnabled(false);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbMaPhieu.setText("M?? phi???u");
			lbSoBPM.setText("S??? BPM");
			lbThietBi.setText("Thi???t b???");
			lbSoLuong.setText("S??? l?????ng");
			lbNgayMua.setText("Ng??y mua");
			lbThoigianBH.setText("Th???i gian BH");
			lbThang.setText("ng??y");
			btnCheckNhapKhoIT.setText("Nh???p kho IT");
			btnthem.setText("Th??m");
			btnsua.setText("S???a");
			btnxoa.setText("X??a");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
			btntimkiem.setText("T??m ki???m");
			tbcSTT.setText("STT");
			tbcMaPhieu.setText("M?? phi???u");
			tbcSoBPM.setText("S??? BPM");
			tbcThietBi.setText("Thi???t b???");
			tbcSoLuong.setText("S??? l?????ng");
			tbcNgayMua.setText("Ng??y mua");
			tbcThoiGianBaoHanh.setText("Th???i gian b???o h??nh");
			tbcNgaynhapkhoIT.setText("Ng??y nh???p kho IT");
			tbcGhiChu.setText("Ghi ch??");
			tbcTenTep.setText("T??n t???p");
			tbcNguoiCapNhat.setText("Ng?????i c???p nh???t");

		} else {
			// Tieng Anh
			lbMaPhieu.setText("ID");
			lbSoBPM.setText("BPM number");
			lbThietBi.setText("Device");
			lbSoLuong.setText("Count");
			lbNgayMua.setText("Buy date");
			lbThoigianBH.setText("Warranty period");
			lbThang.setText("day");
			btnCheckNhapKhoIT.setText("IT warehouse");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			btntimkiem.setText("Search");
			tbcSTT.setText("Number");
			tbcMaPhieu.setText("ID");
			tbcSoBPM.setText("BPM number");
			tbcThietBi.setText("Device");
			tbcSoLuong.setText("Count");
			tbcNgayMua.setText("Buy date");
			tbcThoiGianBaoHanh.setText("Warranty period");
			tbcNgaynhapkhoIT.setText("IT warehouse date");
			tbcGhiChu.setText("Note");
			tbcTenTep.setText("File name");
			tbcNguoiCapNhat.setText("Person update");
		}

		// l???y d??? li???u cho combo thi???t b???
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [TenThietBi] FROM [dbo].[IT_ThietBi] ORDER BY [TenThietBi] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboThietBi.add(result.getString(1));
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

		// text s??? l?????ng ch??? cho nh???p s???
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

		// text th???i gian b???o h??nh ch??? cho nh???p s???
		textThoiGianBH.addVerifyListener(new VerifyListener() {
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

		// b???t s??? ki???n check v??o checkbox nh???p kho IT
		btnCheckNhapKhoIT.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckNhapKhoIT.getSelection()) {
					dateTimeNgayNhapKhoIT1.setEnabled(true);
					dateTimeNgayNhapKhoIT2.setEnabled(true);
				} else {
					dateTimeNgayNhapKhoIT1.setEnabled(false);
					dateTimeNgayNhapKhoIT2.setEnabled(false);
				}
			}
		});

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

		// T??m ki???m
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search();
			}
		});

		// T??m ki???m sau khi enter ??? button t??m ki???m
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text MaPhieu
		// ******************************************************************************
		textMaPhieu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text So BPM
		// ******************************************************************************
		textSoBPM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? combo Thiet bi
		// ******************************************************************************
		comboThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Th??m-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MuaThietBiAdd muathietbimoi = new MuaThietBiAdd();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				muathietbimoi.setData(ngonngu, userlogin);
				muathietbimoi.open();
				composite.setEnabled(true);
				if (muathietbimoi.isAddSuccess()) {
					search();
				}
			}
		});

		// S???a---------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String soBPM = "";
					soBPM = itemsua[0].getText(2);
					if (!soBPM.isEmpty()) {
						MuaThietBiEdit sua = new MuaThietBiEdit();
						// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
						composite.setEnabled(false);
						sua.setData(ngonngu, userlogin);
						sua.createContents();
						sua.setSoBPM(itemsua[0].getText(2));
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

		// X??a-------------------------------------------------------------------
		btnxoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// L???y c???t s??? m?? thi???t b???
					itemtable = table.getSelection();
					sobpmxoa = new String[itemtable.length];

					for (int i = 0; i < sobpmxoa.length; i++) {
						sobpmxoa[i] = itemtable[i].getText(2);
					}
					if (sobpmxoa.length > 0) {
						btnthem.setEnabled(false);
						btnsua.setEnabled(false);
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
					btnthem.setEnabled(true);
					btnsua.setEnabled(true);
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
					connect.setStatement();
					try {
						String delete = "";
						for (int i = 0; i < sobpmxoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[IT_MuaThietBi] WHERE [SoPhieuBPM]='"
									+ sobpmxoa[i] + "'" + "\n"
									+ "DELETE FROM [dbo].[IT_FileDinhKem] WHERE [SoPhieuBPM]='" + sobpmxoa[i] + "'";
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
				btnthem.setEnabled(true);
				btnsua.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// **********************************************************************************
		// S???p x???p table theo m???t c???t ???? ch???n
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcMaPhieu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoBPM.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoLuong.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayMua.addListener(SWT.Selection, sort.sortListenerDate);
		tbcThoiGianBaoHanh.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgaynhapkhoIT.addListener(SWT.Selection, sort.sortListenerDate);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcTenTep.addListener(SWT.Selection, sort.sortListenerCode);
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

			String tenthietbi = "";
			tenthietbi = comboThietBi.getSelectionIndex() == -1 ? ""
					: " AND [dbo].[IT_ThietBi].[TenThietBi]=N'" + comboThietBi.getText() + "'";
			String soluong = textSoLuong.getText().isEmpty() ? ""
					: " AND [dbo].[IT_MuaThietBi].[SoLuong]=" + textSoLuong.getText();

			String month = "0" + (dateTimeNgayNhapKhoIT1.getMonth() + 1);
			month = month.substring(month.length() - 2);
			String day = "0" + dateTimeNgayNhapKhoIT1.getDay();
			day = day.substring(day.length() - 2);
			String ngaynhapkhobatdau = dateTimeNgayNhapKhoIT1.getYear() + month + day;

			String month2 = "0" + (dateTimeNgayNhapKhoIT2.getMonth() + 1);
			month2 = month2.substring(month2.length() - 2);
			String day2 = "0" + dateTimeNgayNhapKhoIT2.getDay();
			day2 = day2.substring(day2.length() - 2);
			String ngaynhapkhoketthuc = dateTimeNgayNhapKhoIT2.getYear() + month2 + day2;

			String danhapkhoit = btnCheckNhapKhoIT.getSelection()
					? " AND [dbo].[IT_MuaThietBi].[DaNhapKhoIT]=1 AND [dbo].[IT_MuaThietBi].NgayNhapKhoIT BETWEEN '"
							+ ngaynhapkhobatdau + "' AND '" + ngaynhapkhoketthuc + "'"
					: " AND [dbo].[IT_MuaThietBi].[DaNhapKhoIT]=0";

			String thoigianbaohanh = textThoiGianBH.getText().isEmpty() ? ""
					: "  AND [dbo].[IT_MuaThietBi].[ThoiGianBaoHanh]=" + textThoiGianBH.getText();

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

			String ngaymua = " AND [dbo].[IT_MuaThietBi].[NgayMua] BETWEEN '" + ngaymuabatdau + "' AND '"
					+ ngaymuaketthuc + "'";

			String select = "SELECT [IT_MuaThietBi].[ID],[IT_MuaThietBi].[SoPhieuBPM],[dbo].[IT_ThietBi].[TenThietBi],[IT_MuaThietBi].[SoLuong],[IT_MuaThietBi].[NgayMua],[IT_MuaThietBi].[ThoiGianBaoHanh],[IT_MuaThietBi].[NgayNhapKhoIT],[IT_MuaThietBi].[GhiChu],[IT_FileDinhKem].[TenFile],[dbo].[NguoiDung].[TenNguoiDung] FROM [dbo].[IT_MuaThietBi] LEFT JOIN [dbo].[IT_FileDinhKem] ON [IT_MuaThietBi].[SoPhieuBPM]=[IT_FileDinhKem].[SoPhieuBPM] LEFT JOIN [dbo].[IT_ThietBi] ON [dbo].[IT_MuaThietBi].[MaThietBi]=[dbo].[IT_ThietBi].[MaThietBi] LEFT JOIN [dbo].[NguoiDung]  ON [dbo].[IT_MuaThietBi].[UserUpdate]=[dbo].[NguoiDung].[MaNguoiDung] WHERE [dbo].[IT_MuaThietBi].[SoPhieuBPM] LIKE '%%'"
					+ tenthietbi + soluong + ngaymua + thoigianbaohanh + danhapkhoit
					+ " ORDER BY [dbo].[IT_MuaThietBi].[NgayMua] DESC";

			// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {

				// ?????nh d???ng l???i ng??y mua th??nh d???ng dd/MM/yyyy
				String ngaymuadata = result.getString(5);
				try {
					ngaymuadata = ngaymuadata.substring(8, 10) + "/" + ngaymuadata.substring(5, 7) + "/"
							+ ngaymuadata.substring(0, 4);
				} catch (IndexOutOfBoundsException ie) {
					ngaymuadata = "";
				}

				// ?????nh d???ng l???i ng??y nh???p kho IT th??nh d???ng dd/MM/yyyy
				String ngaymuanhapkhodata = result.getString(7);
				if (ngaymuanhapkhodata.equalsIgnoreCase("1990-01-01")) {
					ngaymuanhapkhodata = "";
				} else {
					try {
						ngaymuanhapkhodata = ngaymuanhapkhodata.substring(8, 10) + "/"
								+ ngaymuanhapkhodata.substring(5, 7) + "/" + ngaymuanhapkhodata.substring(0, 4);
					} catch (IndexOutOfBoundsException ie) {
						ngaymuanhapkhodata = "";
					}
				}

				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), ngaymuadata, result.getString(6), ngaymuanhapkhodata, result.getString(8),
						result.getString(9), result.getString(10) });
				stt++;
			}

			result.close();
			state.close();
			connect.closeStatement();

		} catch (Exception se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Th??ng b??o l???i");
				thongbao.setMessage("L???i k???t n???i!\n" + se.toString());
			} else {
				thongbao.setText("Notice error");
				thongbao.setMessage("Connect error!\n" + se.toString());
			}
			thongbao.open();
		}
	}

	// Hi???n trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu, String userlogin) {
		this.ngonngu = ngonngu;
		this.userlogin = userlogin;

		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Mua thi???t b???");
		} else {
			itemtab.setText("Buy device");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);
		createContents(composite, shell);
	}
}
