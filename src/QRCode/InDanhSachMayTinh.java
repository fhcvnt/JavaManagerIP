package QRCode;

import java.awt.image.BufferedImage;
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
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import date.ConvertDate;
import image.ResizeImage;
import it.LyLichMayTinhAdd;
import sort.SortData;
import sql.ConnectSQL;

public class InDanhSachMayTinh {
	private Table table;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Composite compositePrintQRCode;
	private Composite composite;
	private CCombo comboDonVi;
	private CCombo comboLoai;
	private TableColumn tbcCPULoai;
	private TableColumn tbcNgaySuDung;
	private TableColumn tbcManHinhLoai;
	private TableColumn tbcManHinhNgaySuDung;
	private TableColumn tbcUPSLoai;
	private TableColumn tbcUPSNgaySuDung;
	private int ngonngu = 1;
	private String userlogin = "21608";

	public static void main(String[] args) {
		try {
			InDanhSachMayTinh window = new InDanhSachMayTinh();
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
		shell.setImage(SWTResourceManager.getImage(InDanhSachMayTinh.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1810);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Lý lịch máy tính");
		} else {
			shell.setText("Computer profile");
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

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 15, 106, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(126, 15, 230, 30);

		CLabel lbLoai = new CLabel(composite, SWT.RIGHT);
		lbLoai.setText("Loại");
		lbLoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoai.setBounds(362, 15, 85, 30);

		comboLoai = new CCombo(composite, SWT.BORDER);
		comboLoai.setItems(new String[] { "CPU", "Monitor", "UPS" });
		comboLoai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoai.setBounds(453, 15, 138, 30);

		Button btnTimkiem = new Button(composite, SWT.NONE);
		btnTimkiem.setImage(
				SWTResourceManager.getImage(InDanhSachMayTinh.class, "/itmanagerip/Icon/button/search-30.png"));
		btnTimkiem.setText("Tìm kiếm");
		btnTimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btnTimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btnTimkiem.setBounds(597, 15, 149, 32);

		Button btnPrint = new Button(composite, SWT.NONE);
		btnPrint.setImage(SWTResourceManager.getImage(InDanhSachMayTinh.class, "/itmanagerip/Icon/button/print24.png"));
		btnPrint.setBounds(752, 15, 106, 32);
		btnPrint.setText("In");
		btnPrint.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		btnPrint.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		compositePrintQRCode = new Composite(composite, SWT.NONE);
		compositePrintQRCode.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		compositePrintQRCode.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		compositePrintQRCode.setBounds(10, 59, 796, 1122);

//		Composite compositeSubLabel = new Composite(compositePrintQRCode, SWT.NONE);
//		compositeSubLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		compositeSubLabel.setBounds(4, 4, 260, 135);
//
//		CLabel lbc1TenThietBi = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1TenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1TenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1TenThietBi.setBounds(5, 7, 64, 25);
//		lbc1TenThietBi.setText("Tên thiết bị:");
//
//		CLabel lbc1TenThietBi2 = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1TenThietBi2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1TenThietBi2.setText("");
//		lbc1TenThietBi2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1TenThietBi2.setBounds(75, 7, 104, 25);
//
//		CLabel lbc1DonVi = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1DonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1DonVi.setText("Đơn vị:");
//		lbc1DonVi.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1DonVi.setBounds(5, 39, 41, 25);
//
//		CLabel lbc1DonVi2 = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1DonVi2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1DonVi2.setText("");
//		lbc1DonVi2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1DonVi2.setBounds(52, 39, 127, 25);
//
//		CLabel lbc1LogoLHG = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1LogoLHG.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1LogoLHG.setBackground(SWTResourceManager.getImage(InDanhSachMayTinh.class, "/QRCode/image/lacty70x50.png"));
//		lbc1LogoLHG.setBounds(185, 5, 70, 50);
//		lbc1LogoLHG.setText("");
//
//		CLabel lbc1Qrcode = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1Qrcode.setText("QRCode");
//		lbc1Qrcode.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
//		lbc1Qrcode.setBounds(185, 60, 70, 70);
//
//		CLabel lbc1NgaySuDung = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1NgaySuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1NgaySuDung.setText("Ngày sử dụng:");
//		lbc1NgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1NgaySuDung.setBounds(5, 71, 72, 25);
//
//		CLabel lbc1NgaySuDung2 = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1NgaySuDung2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1NgaySuDung2.setText("");
//		lbc1NgaySuDung2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1NgaySuDung2.setBounds(79, 71, 100, 25);
//
//		CLabel lbc1MaSoThietBi = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1MaSoThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1MaSoThietBi.setText("Mã số thiết bị:");
//		lbc1MaSoThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1MaSoThietBi.setBounds(5, 103, 74, 25);
//
//		CLabel lbc1MaSoThietBi2 = new CLabel(compositeSubLabel, SWT.NONE);
//		lbc1MaSoThietBi2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		lbc1MaSoThietBi2.setText("");
//		lbc1MaSoThietBi2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
//		lbc1MaSoThietBi2.setBounds(85, 103, 94, 25);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(821, 59);
		table.setSize(1051, 952);
		// table.setSize(Display.getDefault().getBounds().width - 30,
		// Display.getDefault().getBounds().height - 230);
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

		TableColumn tbcLylich = new TableColumn(table, SWT.NONE);
		tbcLylich.setWidth(153);
		tbcLylich.setText("Mã lý lịch");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(186);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcMaSoDonVi = new TableColumn(table, SWT.NONE);
		tbcMaSoDonVi.setWidth(138);
		tbcMaSoDonVi.setText("Mã số đơn vị");

		tbcCPULoai = new TableColumn(table, SWT.NONE);
		tbcCPULoai.setWidth(210);
		tbcCPULoai.setText("CPU loại");

		tbcNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcNgaySuDung.setWidth(135);
		tbcNgaySuDung.setText("Ngày sử dụng");

		tbcManHinhLoai = new TableColumn(table, SWT.NONE);
		tbcManHinhLoai.setWidth(135);
		tbcManHinhLoai.setText("Màn hình loại");

		tbcManHinhNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcManHinhNgaySuDung.setWidth(155);
		tbcManHinhNgaySuDung.setText("Màn hình ngày sử dụng");

		tbcUPSLoai = new TableColumn(table, SWT.NONE);
		tbcUPSLoai.setWidth(135);
		tbcUPSLoai.setText("UPS loại");

		tbcUPSNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcUPSNgaySuDung.setWidth(135);
		tbcUPSNgaySuDung.setText("UPS ngày sử dụng");

		TableColumn tbcNguoiSuDung = new TableColumn(table, SWT.NONE);
		tbcNguoiSuDung.setWidth(240);
		tbcNguoiSuDung.setText("Người sử dụng");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(85);
		tbcSoThe.setText("Số thẻ");

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbDonVi.setText("Đơn vị");
			lbLoai.setText("Loại");
			btnTimkiem.setText("Tìm kiếm");
			btnPrint.setText("In");

			tbcSTT.setText("STT");
			tbcLylich.setText("Mã lý lịch");
			tbcDonVi.setText("Đơn vị");
			tbcMaSoDonVi.setText("Mã số đơn vị");
			tbcCPULoai.setText("CPU loại");
			tbcNgaySuDung.setText("Ngày sử dụng");
			tbcManHinhLoai.setText("Màn hình loại");
			tbcManHinhNgaySuDung.setText("Màn hình ngày sử dụng");
			tbcUPSLoai.setText("UPS loại");
			tbcUPSNgaySuDung.setText("UPS ngày sử dụng");
			tbcNguoiSuDung.setText("Người sử dụng");
			tbcSoThe.setText("Số thẻ");
		} else {
			lbDonVi.setText("Department");
			lbLoai.setText("Type");
			btnTimkiem.setText("Search");
			btnPrint.setText("Print");

			tbcSTT.setText("Number");
			tbcLylich.setText("Profile id");
			tbcDonVi.setText("Department");
			tbcMaSoDonVi.setText("Department id");
			tbcCPULoai.setText("CPU type");
			tbcNgaySuDung.setText("Use date");
			tbcManHinhLoai.setText("Monitor type");
			tbcManHinhNgaySuDung.setText("Monitor use date");
			tbcUPSLoai.setText("UPS type");
			tbcUPSNgaySuDung.setText("UPS use date");
			tbcNguoiSuDung.setText("User");
			tbcSoThe.setText("ID");
		}

		// lấy dữ liệu cho combo đơn vị
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT DISTINCT [DonVi] FROM [dbo].[IT_LyLichMayTinh] ORDER BY [DonVi] ASC";
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

		// Tìm kiếm
		btnTimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// Tìm kiếm sau khi enter ở button tìm kiếm
		// ******************************************************************************
		btnTimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// -------------------------------------------------------------------------------------------------
		btnPrint.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] FILTER_NAMES = { "JPG (*.jpg)", "PNG (*.png)" };
				// đuôi file có thể mở
				String[] FILTER_EXTS = { "*.jpg", "*.png" };

				FileDialog dlg = new FileDialog(shell, SWT.SAVE);
				dlg.setFilterNames(FILTER_NAMES);
				dlg.setFilterExtensions(FILTER_EXTS);
				dlg.setFileName("QRCode");
				String imgFilePath = dlg.open(); // ten file luu
				if (imgFilePath != null) {
					try {
						// save the screenshot as a png
						GC gc = new GC(compositePrintQRCode);
						Rectangle eclipseWindow = compositePrintQRCode.getBounds();
						Image screenshot = new Image(Display.getDefault(), eclipseWindow);
						gc.copyArea(screenshot, eclipseWindow.x, eclipseWindow.y);
						gc.dispose();

						ImageLoader imgLoader = new ImageLoader();
						imgLoader.data = new ImageData[] { screenshot.getImageData() };
						imgLoader.save(imgFilePath, SWT.IMAGE_PNG);
					} catch (Exception exc) {

					}

				}
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcLylich.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcMaSoDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcCPULoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcManHinhLoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcManHinhNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcUPSLoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcUPSNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNguoiSuDung.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);

		// cái này phải ở cuối cùng thì mới scroll được
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu, String userlogin) {
		this.userlogin = userlogin;
		this.ngonngu = ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Lý lịch máy tính");
		} else {
			itemtab.setText("Computer profile");
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
			String loai = "";
			if (comboLoai.getText().isEmpty()) {
				loai = "";
			} else if (comboLoai.getText().equals("CPU")) {
				loai = " AND [CPU_Loai]<>'' AND [CPU_Loai] IS NOT NULL";
				tbcCPULoai.setWidth(210);
				tbcNgaySuDung.setWidth(135);
				tbcManHinhLoai.setWidth(0);
				tbcManHinhNgaySuDung.setWidth(0);
				tbcUPSLoai.setWidth(0);
				tbcUPSNgaySuDung.setWidth(0);

				tbcManHinhLoai.setResizable(false);
				tbcManHinhNgaySuDung.setResizable(false);
				tbcUPSLoai.setResizable(false);
				tbcUPSNgaySuDung.setResizable(false);
				tbcCPULoai.setResizable(true);
				tbcNgaySuDung.setResizable(true);
			} else if (comboLoai.getText().equals("Monitor")) {
				loai = " AND [ManHinh_Loai]<>'' AND [ManHinh_Loai] IS NOT NULL";
				tbcCPULoai.setWidth(0);
				tbcNgaySuDung.setWidth(0);
				tbcManHinhLoai.setWidth(135);
				tbcManHinhNgaySuDung.setWidth(155);
				tbcUPSLoai.setWidth(0);
				tbcUPSNgaySuDung.setWidth(0);

				tbcCPULoai.setResizable(false);
				tbcNgaySuDung.setResizable(false);
				tbcUPSLoai.setResizable(false);
				tbcUPSNgaySuDung.setResizable(false);
				tbcManHinhLoai.setResizable(true);
				tbcManHinhNgaySuDung.setResizable(true);
			} else if (comboLoai.getText().equals("UPS")) {
				loai = " AND [UPS_Loai]<>'' AND [UPS_Loai] IS NOT NULL";
				tbcCPULoai.setWidth(0);
				tbcNgaySuDung.setWidth(0);
				tbcManHinhLoai.setWidth(0);
				tbcManHinhNgaySuDung.setWidth(0);
				tbcUPSLoai.setWidth(135);
				tbcUPSNgaySuDung.setWidth(135);

				tbcCPULoai.setResizable(false);
				tbcNgaySuDung.setResizable(false);
				tbcManHinhLoai.setResizable(false);
				tbcManHinhNgaySuDung.setResizable(false);
				tbcUPSLoai.setResizable(true);
				tbcUPSNgaySuDung.setResizable(true);
			}

			String select = "SELECT [MaLyLich],[DonVi],[MaSoDonVi],[CPU_Loai],[NgaySuDung],[ManHinh_Loai],[ManHinh_NgaySuDung],[UPS_Loai],[UPS_NgaySuDung],[NguoiSuDung],[SoThe] FROM [dbo].[IT_LyLichMayTinh] WHERE [DonVi] LIKE N'%"
					+ comboDonVi.getText() + "%'" + loai + " ORDER BY [MaSoDonVi] ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			int vitrix = 0;
			int vitriy = 1;
			int vitridong = 0;
			int countlabelprint = 0;
			while (result.next()) {
				String cpungaysudung = result.getString(4).isEmpty() ? ""
						: ConvertDate.convertDate(result.getString(5));
				String monitorngaysudung = result.getString(6).isEmpty() ? ""
						: ConvertDate.convertDate(result.getString(7));
				String upsngaysudung = result.getString(8).isEmpty() ? ""
						: ConvertDate.convertDate(result.getString(9));

				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), cpungaysudung, result.getString(6), monitorngaysudung, result.getString(8),
						upsngaysudung, result.getString(10), result.getString(11) });
				stt++;

				if (countlabelprint > 0 && countlabelprint % 28 == 0) {

				}

				// điền thông tin cho composite sub label
				if (!result.getString(4).isEmpty()) {
					if (vitriy == 1) {
						vitrix = 0;
						vitriy++;
					} else if (vitriy == 2) {
						vitrix = 264;
						vitriy++;
					} else if (vitriy == 3) {
						vitrix = 528;
						vitriy = 1;
					}
					countlabelprint++;
					createCompositeSubLabel(compositePrintQRCode, vitrix + 4, vitridong + 4, "CPU",
							result.getString(10), result.getString(11), result.getString(2), cpungaysudung,
							"CPU" + result.getString(3));

					if (vitriy == 1) {
						vitridong = vitridong + 139;

					}
				}
				if (!result.getString(6).isEmpty()) {
					if (vitriy == 1) {
						vitrix = 0;
						vitriy++;
					} else if (vitriy == 2) {
						vitrix = 264;
						vitriy++;
					} else if (vitriy == 3) {
						vitrix = 528;
						vitriy = 1;
					}
					countlabelprint++;
					createCompositeSubLabel(compositePrintQRCode, vitrix + 4, vitridong + 4, "Monitor",
							result.getString(10), result.getString(11), result.getString(2), monitorngaysudung,
							"Monitor" + result.getString(3));

					if (vitriy == 1) {
						vitridong = vitridong + 139;
					}
				}
				if (!result.getString(8).isEmpty()) {
					if (vitriy == 1) {
						vitrix = 0;
						vitriy++;
					} else if (vitriy == 2) {
						vitrix = 264;
						vitriy++;
					} else if (vitriy == 3) {
						vitrix = 528;
						vitriy = 1;
					}
					countlabelprint++;
					createCompositeSubLabel(compositePrintQRCode, vitrix + 4, vitridong + 4, "UPS",
							result.getString(10), result.getString(11), result.getString(2), upsngaysudung,
							"UPS" + result.getString(3));

					if (vitriy == 1) {
						vitridong = vitridong + 139;

					}
				}

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
	public void setData(int language, String nguoidangnhap) {
		ngonngu = language;
		userlogin = nguoidangnhap;
	}

	// hàm tạo composite sub label
	// -------------------------------------------------------------------------------------------
	public void createCompositeSubLabel(Composite compositePrintQRCode, int x, int y, String tenthietbi, String hoten,
			String sothe, String donvi, String ngaysudung, String masothietbi) {
		// x,y là vị trí của compositeSubLabel trong compositePrintQRCode
		Composite compositeSubLabel = new Composite(compositePrintQRCode, SWT.NONE);
		compositeSubLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		compositeSubLabel.setBounds(x, y, 260, 135);

		CLabel lbc1TenThietBi = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1TenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1TenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1TenThietBi.setBounds(5, 7, 64, 25);
		lbc1TenThietBi.setText("Tên thiết bị:");

		CLabel lbc1TenThietBi2 = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1TenThietBi2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1TenThietBi2.setText(tenthietbi);
		lbc1TenThietBi2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1TenThietBi2.setBounds(75, 7, 104, 25);

		CLabel lbc1DonVi = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1DonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1DonVi.setText("Đơn vị:");
		lbc1DonVi.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1DonVi.setBounds(5, 39, 41, 25);

		CLabel lbc1DonVi2 = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1DonVi2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1DonVi2.setText(donvi);
		lbc1DonVi2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1DonVi2.setBounds(52, 39, 127, 25);

		CLabel lbc1LogoLHG = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1LogoLHG.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1LogoLHG.setBackground(SWTResourceManager.getImage(InDanhSachMayTinh.class, "/QRCode/image/lacty70x50.png"));
		lbc1LogoLHG.setBounds(185, 5, 70, 50);

		CLabel lbc1Qrcode = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1Qrcode.setBounds(185, 60, 70, 70);

		// tạo ảnh QR code
		BufferedImage image = QRCode
				.QRCodeGenerator(contentQRCode(tenthietbi, hoten, sothe, donvi, ngaysudung, masothietbi), 100, 100);
		Image anotherImage = new Image(Display.getDefault(), AWTBufferedImageSWTImage.convertToSWT(image));
		lbc1Qrcode.setBackgroundImage(ResizeImage.resize(anotherImage, 70, 70));

		CLabel lbc1NgaySuDung = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1NgaySuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1NgaySuDung.setText("Ngày sử dụng:");
		lbc1NgaySuDung.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1NgaySuDung.setBounds(5, 71, 72, 25);

		CLabel lbc1NgaySuDung2 = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1NgaySuDung2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1NgaySuDung2.setText(ngaysudung);
		lbc1NgaySuDung2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1NgaySuDung2.setBounds(79, 71, 100, 25);

		CLabel lbc1MaSoThietBi = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1MaSoThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1MaSoThietBi.setText("Mã số thiết bị:");
		lbc1MaSoThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1MaSoThietBi.setBounds(5, 103, 74, 25);

		CLabel lbc1MaSoThietBi2 = new CLabel(compositeSubLabel, SWT.NONE);
		lbc1MaSoThietBi2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbc1MaSoThietBi2.setText(masothietbi);
		lbc1MaSoThietBi2.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		lbc1MaSoThietBi2.setBounds(85, 103, 94, 25);
	}

	// điền nội dung ảnh QR Code
	// ---------------------------------------------------------------------------------------
	public String contentQRCode(String tenthietbi, String hoten, String sothe, String donvi, String ngaysudung,
			String masothietbi) {
		String result = "Tên thiết bị: ";
		result = result + tenthietbi + "\n" + "Tên-ST: " + hoten + " - " + sothe + "\n" + "Đơn vị: " + donvi + "\n"
				+ "Ngày sử dụng: " + ngaysudung + "\n" + "Mã số thiết bị: " + masothietbi;
		return result;
	}
}
