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

public class LyLichMayTinh {
	private Table table;
	private TableItem[] itemtable;
	private String[] malylichxoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;

	private CCombo comboDonVi;
	private int ngonngu = 1;
	private Text textCPU;
	private Text textMainboard;
	private Text textOCung;
	private Text textRAM;
	private Text textManHinh;
	private Text textUPS;
	private Text textMayIn;
	private Text textNguoiSuDung;
	private Text textSoThe;
	private CCombo comboNguoiCapNhat;
	private String userlogin = "21608";

	public static void main(String[] args) {
		try {
			LyLichMayTinh window = new LyLichMayTinh();
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
		shell.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("L?? l???ch m??y t??nh");
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

		Composite composite = new Composite(scrolledComposite, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);
		scrolledComposite.setContent(composite);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 13, 106, 30);
		lbDonVi.setText("????n v???");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setBounds(122, 13, 149, 30);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboDonVi.setTextLimit(50);

		CLabel lbCPU = new CLabel(composite, SWT.RIGHT);
		lbCPU.setBounds(277, 13, 65, 30);
		lbCPU.setText("CPU");
		lbCPU.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textCPU = new Text(composite, SWT.BORDER);
		textCPU.setTextLimit(30);
		textCPU.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textCPU.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textCPU.setBounds(348, 13, 112, 30);

		CLabel lblMainboard = new CLabel(composite, SWT.RIGHT);
		lblMainboard.setText("Mainboard");
		lblMainboard.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lblMainboard.setBounds(466, 13, 106, 30);

		textMainboard = new Text(composite, SWT.BORDER);
		textMainboard.setTextLimit(30);
		textMainboard.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMainboard.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textMainboard.setBounds(579, 13, 149, 30);

		CLabel lbOCung = new CLabel(composite, SWT.RIGHT);
		lbOCung.setText("??? c???ng");
		lbOCung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbOCung.setBounds(734, 13, 97, 30);

		textOCung = new Text(composite, SWT.BORDER);
		textOCung.setTextLimit(30);
		textOCung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textOCung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textOCung.setBounds(838, 13, 97, 30);

		CLabel lbRAM = new CLabel(composite, SWT.RIGHT);
		lbRAM.setText("RAM");
		lbRAM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbRAM.setBounds(941, 13, 65, 30);

		textRAM = new Text(composite, SWT.BORDER);
		textRAM.setTextLimit(30);
		textRAM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textRAM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textRAM.setBounds(1012, 13, 88, 30);

		CLabel lbManHinh = new CLabel(composite, SWT.RIGHT);
		lbManHinh.setText("M??n h??nh");
		lbManHinh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbManHinh.setBounds(1106, 13, 97, 30);

		textManHinh = new Text(composite, SWT.BORDER);
		textManHinh.setTextLimit(30);
		textManHinh.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textManHinh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textManHinh.setBounds(1209, 13, 112, 30);

		CLabel lbUPS = new CLabel(composite, SWT.RIGHT);
		lbUPS.setText("UPS");
		lbUPS.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbUPS.setBounds(1327, 13, 65, 30);

		textUPS = new Text(composite, SWT.BORDER);
		textUPS.setTextLimit(30);
		textUPS.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textUPS.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textUPS.setBounds(1398, 13, 96, 30);

		Button btnThemThietBi = new Button(composite, SWT.NONE);
		btnThemThietBi
				.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/add-book-25.png"));
		btnThemThietBi.setText("Th??m thi???t b???");
		btnThemThietBi.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnThemThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnThemThietBi.setBounds(1500, 13, 167, 32);

		Button btnCapNhatMaDonVi = new Button(composite, SWT.NONE);
		btnCapNhatMaDonVi.setText("C???p nh???t m?? ????n v???");
		btnCapNhatMaDonVi.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnCapNhatMaDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnCapNhatMaDonVi.setBounds(1673, 13, 189, 32);

		CLabel lbMayIn = new CLabel(composite, SWT.RIGHT);
		lbMayIn.setText("M??y in");
		lbMayIn.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMayIn.setBounds(10, 54, 106, 30);

		textMayIn = new Text(composite, SWT.BORDER);
		textMayIn.setTextLimit(30);
		textMayIn.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMayIn.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textMayIn.setBounds(122, 54, 149, 30);

		CLabel lbNguoiSuDung = new CLabel(composite, SWT.RIGHT);
		lbNguoiSuDung.setText("Ng?????i s??? d???ng");
		lbNguoiSuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiSuDung.setBounds(277, 54, 120, 30);

		textNguoiSuDung = new Text(composite, SWT.BORDER);
		textNguoiSuDung.setTextLimit(30);
		textNguoiSuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiSuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguoiSuDung.setBounds(403, 54, 135, 30);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("S??? th???");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(544, 54, 70, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(620, 54, 88, 30);

		comboNguoiCapNhat = new CCombo(composite, SWT.BORDER);
		comboNguoiCapNhat.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNguoiCapNhat.setBounds(714, 54, 156, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("T??m ki???m");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(877, 54, 149, 32);

		Button btnExport = new Button(composite, SWT.NONE);
		btnExport.setText("Xu???t");
		btnExport.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExport.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnExport.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExport.setBounds(1032, 54, 120, 32);

		Button btnImport = new Button(composite, SWT.NONE);
		btnImport.setText("Nh???p");
		btnImport.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/menu/import.png"));
		btnImport.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		btnImport.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnImport.setBounds(1158, 54, 120, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(1284, 54, 106, 32);
		btnthem.setText("Th??m");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(1397, 54, 97, 32);
		btnsua.setText("S???a");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(1500, 54, 120, 32);
		btnxoa.setText("X??a");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1626, 54, 109, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1741, 54, 120, 32);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 97);
		table.setSize(1870, 600);
		table.setSize(Display.getDefault().getBounds().width - 30, Display.getDefault().getBounds().height - 230);
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
		tbcLylich.setText("M?? l?? l???ch");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(136);
		tbcDonVi.setText("????n v???");

		TableColumn tbcMaSoDonVi = new TableColumn(table, SWT.NONE);
		tbcMaSoDonVi.setWidth(138);
		tbcMaSoDonVi.setText("M?? s??? ????n v???");

		TableColumn tbcCPULoai = new TableColumn(table, SWT.NONE);
		tbcCPULoai.setWidth(118);
		tbcCPULoai.setText("CPU lo???i");

		TableColumn tbcCPUNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcCPUNgaySuDung.setWidth(138);
		tbcCPUNgaySuDung.setText("CPU ng??y s??? d???ng");

		TableColumn tbcMainboardLoai = new TableColumn(table, SWT.NONE);
		tbcMainboardLoai.setWidth(135);
		tbcMainboardLoai.setText("Mainboard lo???i");

		TableColumn tbcmainboardNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcmainboardNgaySuDung.setWidth(185);
		tbcmainboardNgaySuDung.setText("Mainboard ng??y s??? d???ng");

		TableColumn tbcOCung = new TableColumn(table, SWT.NONE);
		tbcOCung.setWidth(135);
		tbcOCung.setText("??? c???ng");

		TableColumn tbcRAMLoai = new TableColumn(table, SWT.NONE);
		tbcRAMLoai.setWidth(135);
		tbcRAMLoai.setText("RAM lo???i");

		TableColumn tbcRAMDungLuong = new TableColumn(table, SWT.NONE);
		tbcRAMDungLuong.setWidth(135);
		tbcRAMDungLuong.setText("RAM dung l?????ng");

		TableColumn tbcRAMSoLuong = new TableColumn(table, SWT.NONE);
		tbcRAMSoLuong.setWidth(135);
		tbcRAMSoLuong.setText("RAM s??? l?????ng");

		TableColumn tbcManHinhLoai = new TableColumn(table, SWT.NONE);
		tbcManHinhLoai.setWidth(135);
		tbcManHinhLoai.setText("M??n h??nh lo???i");

		TableColumn tbcManHinhNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcManHinhNgaySuDung.setWidth(175);
		tbcManHinhNgaySuDung.setText("M??n h??nh ng??y s??? d???ng");

		TableColumn tbcUPSLoai = new TableColumn(table, SWT.NONE);
		tbcUPSLoai.setWidth(135);
		tbcUPSLoai.setText("UPS lo???i");

		TableColumn tbcUPSNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcUPSNgaySuDung.setWidth(185);
		tbcUPSNgaySuDung.setText("UPS ng??y s??? d???ng");

		TableColumn tbcMayInLoai = new TableColumn(table, SWT.NONE);
		tbcMayInLoai.setWidth(135);
		tbcMayInLoai.setText("M??y in lo???i");

		TableColumn tbcMayInNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcMayInNgaySuDung.setWidth(165);
		tbcMayInNgaySuDung.setText("M??y in ng??y s??? d???ng");

		TableColumn tbcNgaySuDung = new TableColumn(table, SWT.NONE);
		tbcNgaySuDung.setWidth(165);
		tbcNgaySuDung.setText("Ng??y s??? d???ng");

		TableColumn tbcThoiGianBaoHanh = new TableColumn(table, SWT.NONE);
		tbcThoiGianBaoHanh.setWidth(155);
		tbcThoiGianBaoHanh.setText("Th???i gian b???o h??nh");

		TableColumn tbcNgayHetBaoHanh = new TableColumn(table, SWT.NONE);
		tbcNgayHetBaoHanh.setWidth(135);
		tbcNgayHetBaoHanh.setText("Ng??y h???t b???o h??nh");

		TableColumn tbcThoiGianSuDungMay = new TableColumn(table, SWT.NONE);
		tbcThoiGianSuDungMay.setWidth(220);
		tbcThoiGianSuDungMay.setText("Th???i gian s??? d???ng m??y (n??m)");

		TableColumn tbcThoiGianSuDungManHinh = new TableColumn(table, SWT.NONE);
		tbcThoiGianSuDungManHinh.setWidth(250);
		tbcThoiGianSuDungManHinh.setText("Th???i gian s??? d???ng m??n h??nh (n??m)");

		TableColumn tbcNguoiSuDung = new TableColumn(table, SWT.NONE);
		tbcNguoiSuDung.setWidth(135);
		tbcNguoiSuDung.setText("Ng?????i s??? d???ng");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(135);
		tbcSoThe.setText("S??? th???");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(135);
		tbcNguoiCapNhat.setText("Ng?????i c???p nh???t");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbDonVi.setText("????n v???");
			lbCPU.setText("CPU");
			lblMainboard.setText("Mainboard");
			lbOCung.setText("??? c???ng");
			lbRAM.setText("RAM");
			lbManHinh.setText("M??n h??nh");
			lbUPS.setText("UPS");
			btnThemThietBi.setText("Th??m thi???t b???");
			btnCapNhatMaDonVi.setText("C???p nh???t m?? ????n v???");
			lbMayIn.setText("M??y in");
			lbNguoiSuDung.setText("Ng?????i s??? d???ng");
			lbSoThe.setText("S??? th???");
			btntimkiem.setText("T??m ki???m");
			btnExport.setText("Xu???t");
			btnImport.setText("Nh???p");
			btnthem.setText("Th??m");
			btnsua.setText("S???a");
			btnxoa.setText("X??a");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");

			tbcSTT.setText("STT");
			tbcLylich.setText("M?? l?? l???ch");
			tbcDonVi.setText("????n v???");
			tbcMaSoDonVi.setText("M?? s??? ????n v???");
			tbcCPULoai.setText("CPU lo???i");
			tbcCPUNgaySuDung.setText("CPU ng??y s??? d???ng");
			tbcMainboardLoai.setText("Mainboard lo???i");
			tbcmainboardNgaySuDung.setText("Mainboard ng??y s??? d???ng");
			tbcOCung.setText("??? c???ng");
			tbcRAMLoai.setText("RAM lo???i");
			tbcRAMDungLuong.setText("RAM dung l?????ng");
			tbcRAMSoLuong.setText("RAM s??? l?????ng");
			tbcManHinhLoai.setText("M??n h??nh lo???i");
			tbcManHinhNgaySuDung.setText("M??n h??nh ng??y s??? d???ng");
			tbcUPSLoai.setText("UPS lo???i");
			tbcUPSNgaySuDung.setText("UPS ng??y s??? d???ng");
			tbcMayInLoai.setText("M??y in lo???i");
			tbcMayInNgaySuDung.setText("M??y in ng??y s??? d???ng");
			tbcNgaySuDung.setText("Ng??y s??? d???ng");
			tbcThoiGianBaoHanh.setText("Th???i gian b???o h??nh");
			tbcNgayHetBaoHanh.setText("Ng??y h???t b???o h??nh");
			tbcThoiGianSuDungMay.setText("Th???i gian s??? d???ng m??y (n??m)");
			tbcThoiGianSuDungManHinh.setText("Th???i gian s??? d???ng m??n h??nh (n??m)");
			tbcNguoiSuDung.setText("Ng?????i s??? d???ng");
			tbcSoThe.setText("S??? th???");
			tbcNguoiCapNhat.setText("Ng?????i c???p nh???t");

		} else {
			// Tieng Anh
			lbDonVi.setText("Department");
			lbCPU.setText("CPU");
			lblMainboard.setText("Mainboard");
			lbOCung.setText("Hard drive");
			lbRAM.setText("RAM");
			lbManHinh.setText("Monitor");
			lbUPS.setText("UPS");
			btnThemThietBi.setText("Add device");
			btnCapNhatMaDonVi.setText("Update department id");
			lbMayIn.setText("Printer");
			lbNguoiSuDung.setText("User");
			lbSoThe.setText("ID");
			btntimkiem.setText("Search");
			btnExport.setText("Export");
			btnImport.setText("Import");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Number");
			tbcLylich.setText("Profile id");
			tbcDonVi.setText("Department");
			tbcMaSoDonVi.setText("Department id");
			tbcCPULoai.setText("CPU type");
			tbcCPUNgaySuDung.setText("CPU use date");
			tbcMainboardLoai.setText("Mainboard type");
			tbcmainboardNgaySuDung.setText("Mainboard use date");
			tbcOCung.setText("Hard drive");
			tbcRAMLoai.setText("RAM type");
			tbcRAMDungLuong.setText("RAM capacity");
			tbcRAMSoLuong.setText("RAM count");
			tbcManHinhLoai.setText("Monitor type");
			tbcManHinhNgaySuDung.setText("Monitor use date");
			tbcUPSLoai.setText("UPS type");
			tbcUPSNgaySuDung.setText("UPS use date");
			tbcMayInLoai.setText("Printer type");
			tbcMayInNgaySuDung.setText("Printer use date");
			tbcNgaySuDung.setText("Use date");
			tbcThoiGianBaoHanh.setText("Warranty period");
			tbcNgayHetBaoHanh.setText("Warranty expiry date");
			tbcThoiGianSuDungMay.setText("Machine use time (year)");
			tbcThoiGianSuDungManHinh.setText("Monitor use time (year)");
			tbcNguoiSuDung.setText("User");
			tbcSoThe.setText("ID");
			tbcNguoiCapNhat.setText("Person update");

		}

		// l???y d??? li???u cho combo ????n v???
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
				thongbao.setText("Th??ng b??o l???i!");
				thongbao.setMessage("L???i!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// l???y d??? li???u cho combo ng?????i c???p nh???t
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [TenNguoiDung] FROM [dbo].[NguoiDung] ORDER BY [TenNguoiDung] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboNguoiCapNhat.add(result.getString(1));
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

		// T??m ki???m sau khi enter ??? text department
		// ******************************************************************************
		comboDonVi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m sau khi enter ??? text cpu
		// ******************************************************************************
		textCPU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m sau khi enter ??? text mainboard
		// ******************************************************************************
		textMainboard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m sau khi enter ??? text o cung
		// ******************************************************************************
		textOCung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m sau khi enter ??? text man hinh
		// ******************************************************************************
		textManHinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m sau khi enter ??? text UPS
		// ******************************************************************************
		textUPS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m sau khi enter ??? text may in
		// ******************************************************************************
		textMayIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// T??m ki???m sau khi enter ??? text nguoi su dung
		// ******************************************************************************
		textNguoiSuDung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search(shell);
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

		// Export
		btnExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "L?? l???ch m??y t??nh");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Coputer profile");
				}
			}
		});

		// Export sau khi enter ??? button Export
		// ******************************************************************************
		btnExport.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (ngonngu == 0) {
						Excel.exportExcelTable(table, shell, ngonngu, "L?? l???ch m??y t??nh");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "Coputer profile");
					}
				}
			}
		});

		// Import
		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LyLichMayTinhImportExcel importexcel = new LyLichMayTinhImportExcel();
				importexcel.importExcel(ngonngu, shell);
			}
		});

		// Th??m thi???t b???
		// ---------------------------------------------------------------------------------------
		btnThemThietBi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DanhSachThietBiMayTinh thietbimoi = new DanhSachThietBiMayTinh();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				thietbimoi.setLanguage(ngonngu);
				thietbimoi.createContents();
				thietbimoi.open();
				composite.setEnabled(true);
				search(shell);
			}
		});

		// C???p nh???t m?? s??? ????n v???
		// ---------------------------------------------------------------------------------------
		btnCapNhatMaDonVi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LyLichMayTinhUpdateMaDonVi thietbimoi = new LyLichMayTinhUpdateMaDonVi();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				thietbimoi.setLanguage(ngonngu);
				thietbimoi.open();
				composite.setEnabled(true);
				search(shell);
			}
		});

		// Th??m-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LyLichMayTinhAdd lylichmaytinhmoi = new LyLichMayTinhAdd();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				lylichmaytinhmoi.setData(ngonngu, userlogin);
				lylichmaytinhmoi.createContents();
				lylichmaytinhmoi.open();
				composite.setEnabled(true);

				if (lylichmaytinhmoi.isAddSuccess()) {
					search(shell);
				}
			}
		});

		// S???a---------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String mathietbisua = "";
					mathietbisua = itemsua[0].getText(1);
					if (!mathietbisua.isEmpty()) {
						LyLichMayTinhEdit sualylichmaytinh = new LyLichMayTinhEdit();
						// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
						composite.setEnabled(false);
						sualylichmaytinh.createContents();
						sualylichmaytinh.setData(ngonngu, userlogin, itemsua[0].getText(1), itemsua[0].getText(24),
								itemsua[0].getText(23), itemsua[0].getText(2), itemsua[0].getText(3),
								itemsua[0].getText(4), itemsua[0].getText(5), itemsua[0].getText(6),
								itemsua[0].getText(7), itemsua[0].getText(8), itemsua[0].getText(9),
								itemsua[0].getText(10), itemsua[0].getText(11), itemsua[0].getText(12),
								itemsua[0].getText(13), itemsua[0].getText(14), itemsua[0].getText(15),
								itemsua[0].getText(16), itemsua[0].getText(17), itemsua[0].getText(18),
								itemsua[0].getText(19), itemsua[0].getText(20));
						sualylichmaytinh.open();
						composite.setEnabled(true);
						if (sualylichmaytinh.isEditSuccess()) {
							search(shell);
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
					malylichxoa = new String[itemtable.length];

					for (int i = 0; i < malylichxoa.length; i++) {
						malylichxoa[i] = itemtable[i].getText(1);
					}
					if (malylichxoa.length > 0) {
						btnThemThietBi.setEnabled(false);
						btnthem.setEnabled(false);
						btnsua.setEnabled(false);
						btntimkiem.setEnabled(false);
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						table.setEnabled(false);
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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
					btnThemThietBi.setEnabled(true);
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
					try {
						String delete = "";
						for (int i = 0; i < malylichxoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[IT_LyLichMayTinh] WHERE [MaLyLich]='"
									+ malylichxoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						connect.closeStatement();
						btnThemThietBi.setEnabled(true);
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
				btnThemThietBi.setEnabled(true);
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

		tbcLylich.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcMaSoDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcCPULoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcCPUNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcMainboardLoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcmainboardNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcOCung.addListener(SWT.Selection, sort.sortListenerCode);
		tbcRAMLoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcRAMDungLuong.addListener(SWT.Selection, sort.sortListenerCode);
		tbcRAMSoLuong.addListener(SWT.Selection, sort.sortListenerCode);
		tbcManHinhLoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcManHinhNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcUPSLoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcUPSNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcMayInLoai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcMayInNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgaySuDung.addListener(SWT.Selection, sort.sortListenerDate);
		tbcThoiGianBaoHanh.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayHetBaoHanh.addListener(SWT.Selection, sort.sortListenerDate);
		tbcThoiGianSuDungMay.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThoiGianSuDungManHinh.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiSuDung.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiCapNhat.addListener(SWT.Selection, sort.sortListenerCode);

		// c??i n??y ph???i ??? cu???i c??ng th?? m???i scroll ???????c
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu, String userlogin) {
		this.userlogin = userlogin;
		this.ngonngu = ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("L?? l???ch m??y t??nh");
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
			String sothe = "";
			if (textSoThe.getText().isEmpty()) {
				sothe = "";
			} else {
				sothe = " AND [dbo].[IT_LyLichMayTinh].[SoThe]='" + textSoThe.getText() + "'";
			}
			String donvi = comboDonVi.getText().isEmpty() ? ""
					: " AND ([dbo].[IT_LyLichMayTinh].[DonVi]=N'" + comboDonVi.getText()
							+ "' OR [dbo].[convertUnicodetoASCII]([dbo].[IT_LyLichMayTinh].[DonVi])='"
							+ comboDonVi.getText() + "')";

			String nguoicapnhat = "";
			if (!comboNguoiCapNhat.getText().isEmpty()) {
				nguoicapnhat = " AND [NguoiDung].[TenNguoiDung] LIKE N'%" + comboNguoiCapNhat.getText() + "%'";
			}

			String select = "SELECT [MaLyLich],[DonVi],[MaSoDonVi],[CPU_Loai],[CPU_NgaySuDung],[Mainboard_Loai],[Mainboard_NgaySuDung],[OCung],[RAM_Loai],[RAM_DungLuong],[RAM_SoLuong],[ManHinh_Loai],[ManHinh_NgaySuDung],[UPS_Loai],[UPS_NgaySuDung],[MayIn_Loai],[MayIn_NgaySuDung],[NgaySuDung],[ThoiGianBaoHanh],[NgayHetHanBaoHanh],[NguoiSuDung],[SoThe],[NguoiDung].[TenNguoiDung] FROM [dbo].[IT_LyLichMayTinh] LEFT JOIN [dbo].[NguoiDung] ON [dbo].[IT_LyLichMayTinh].[NguoiCapNhat]=[dbo].[NguoiDung].[MaNguoiDung] OR [dbo].[IT_LyLichMayTinh].[NguoiCapNhat]=[dbo].[NguoiDung].[TenDangNhap] WHERE [dbo].[IT_LyLichMayTinh].[CPU_Loai] LIKE '%"
					+ textCPU.getText() + "%' AND [dbo].[IT_LyLichMayTinh].[Mainboard_Loai] LIKE '%"
					+ textMainboard.getText() + "%' AND [dbo].[IT_LyLichMayTinh].[OCung] LIKE '%" + textOCung.getText()
					+ "%' AND [dbo].[IT_LyLichMayTinh].[ManHinh_Loai] LIKE '%" + textManHinh.getText()
					+ "%' AND [dbo].[IT_LyLichMayTinh].[UPS_Loai] LIKE '%" + textUPS.getText()
					+ "%' AND [dbo].[IT_LyLichMayTinh].[MayIn_Loai] LIKE '%" + textMayIn.getText()
					+ "%' AND ([dbo].[IT_LyLichMayTinh].[NguoiSuDung] LIKE N'%" + textNguoiSuDung.getText()
					+ "%' OR [dbo].[convertUnicodetoASCII]([dbo].[IT_LyLichMayTinh].[NguoiSuDung]) LIKE '%"
					+ textNguoiSuDung.getText() + "%')" + sothe + donvi + nguoicapnhat;

			// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), getDateString(result.getString(4), result.getString(5)),
						result.getString(6), getDateString(result.getString(6), result.getString(7)),
						result.getString(8), result.getString(9), result.getString(10), result.getString(11),
						result.getString(12), getDateString(result.getString(12), result.getString(13)),
						result.getString(14), getDateString(result.getString(14), result.getString(15)),
						result.getString(16), getDateString(result.getString(16), result.getString(17)),
						getDateString(result.getString(17), result.getString(18)), result.getString(19),
						getDateString(result.getString(19), result.getString(20)),
						ConvertDate.getThoiGianSuDung(result.getString(18)),
						ConvertDate.getThoiGianSuDung(result.getString(13)), result.getString(21), result.getString(22),
						result.getString(23) });
				stt++;
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
		if (chuoi1.isEmpty()||chuoi2.equals("1990-01-01")) {
			return "";
		} else {
			return ConvertDate.convertDate(chuoi2);
		}
	}
}
