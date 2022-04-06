package broken;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import excel.Excel;
import sort.SortData;
import sql.ConnectSQL;

public class DanhSachThietBiHu {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textTenThietBi;
	private int ngonngu = 0;
	private Table table;
	private TableItem[] itemtable;
	private String[] mathietbihuxoa;
	private String mathietbisua;
	private String userlogin = "";
	private String grouplogin = "";// là user thì không cho sửa, xóa, và xuất excel

	private TableEditor tableEditor;
	private Composite compositeshell;

	private ArrayList<Text> arraytextthietbi;
	private ArrayList<CCombo> arraycombodonvitinh;
	private ArrayList<Text> arraytextsoluong;
	private ArrayList<Text> arraytextsothe;
	private ArrayList<Text> arraytextnguoisudung;
	private ArrayList<CCombo> arraycombodonvi;
	private ArrayList<Text> arraytextngaytrangbi;
	private ArrayList<Text> arraytextngaybihu;
	private ArrayList<CCombo> arraycombonguoinhan;
	private ArrayList<Text> arraytextghichu;

	private Text edittextthietbi;
	private CCombo editcombodonvitinh;
	private Text edittextsoluong;
	private Text edittextsothe;
	private Text edittextnguoisudung;
	private CCombo editcombodonvi;
	private Text edittextngaytrangbi;
	private Text edittextngaybihu;
	private CCombo editcombonguoinhan;
	private Text edittextghichu;
	private TableItem edititemvitri;

	private Text textNgayTrangBi1;
	private Text textNgayBiHu1;
	private Text textNgayTrangBi2;
	private Text textNgayBiHu2;
	private Text textSoLuong;
	private Text textSoThe;
	private Text textNguoiSuDung;
	private CCombo comboDonViTinh;
	private CCombo comboNguoiNhan;
	private CCombo comboDonVi;
	private Button btnTepDinhKem;
	private Button btnTimKiem;
	private Button btnXuatExcel;
	private Button btnThem;
	private Button btnSua;
	private Button btnXoa;
	private Button btnLuu;
	private Button btnHuy;

	private TableColumn tbcMaThietBi;

	private String[] arraydonvitinh = { "Bộ", "Cái", "Con", "Cục", "Hộp", "KG", "Mét", "Thùng" };
	private ArrayList<String> arraydonvi = new ArrayList<>();
	private ArrayList<String> arraynguoinhanthietbihu = new ArrayList<>();

	private int status = 0;// 1: Thêm, 2: Sửa, 3: Xóa
	private String filename = ""; // ten tep dinh kem, vd: D:/a.pdf
	private String tentep = "";// vi du: a.pdf
	private String matepsua = "";// mã tệp sửa
	private String matepdinhkemxoa = "";// mã tệp đính kèm xóa, chỉ xóa khi các dòng trong phiếu đề nghị xóa hết

	public static void main(String[] args) {
		try {
			DanhSachThietBiHu window = new DanhSachThietBiHu();
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

	protected void createShell() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1640, 804);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Thiết bị hư");
		} else {
			shell.setText("Broken device");
		}

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		compositeshell.setLayout(new FillLayout());
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Composite com, Shell shell) {
		Composite composite = new Composite(com, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setToolTipText("Tên thiết bị");
		lbTenThietBi.setBounds(10, 20, 140, 30);
		lbTenThietBi.setText("Tên thiết bị");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setToolTipText("Tên thiết bị");
		textTenThietBi.setBounds(156, 20, 180, 30);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenThietBi.setTextLimit(13);

		CLabel lbDonViTinh = new CLabel(composite, SWT.RIGHT);
		lbDonViTinh.setToolTipText("Đơn vị tính");
		lbDonViTinh.setText("Đơn vị tính");
		lbDonViTinh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonViTinh.setBounds(348, 20, 114, 30);

		comboDonViTinh = new CCombo(composite, SWT.BORDER);
		comboDonViTinh.setToolTipText("Đơn vị tính");
		comboDonViTinh.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonViTinh.setBounds(468, 20, 110, 30);
		comboDonViTinh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbSoLuong = new CLabel(composite, SWT.RIGHT);
		lbSoLuong.setToolTipText("Số lượng");
		lbSoLuong.setText("Số lượng");
		lbSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoLuong.setBounds(584, 20, 124, 30);

		textSoLuong = new Text(composite, SWT.BORDER);
		textSoLuong.setToolTipText("Số lượng");
		textSoLuong.setTextLimit(13);
		textSoLuong.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoLuong.setBounds(714, 20, 70, 30);

		CLabel lbNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbNguoiNhan.setToolTipText("Người nhận");
		lbNguoiNhan.setText("Người nhận");
		lbNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiNhan.setBounds(790, 20, 147, 30);

		comboNguoiNhan = new CCombo(composite, SWT.BORDER);
		comboNguoiNhan.setToolTipText("Người nhận");
		comboNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNguoiNhan.setBounds(944, 20, 210, 30);
		comboNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setToolTipText("Số thẻ");
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(10, 60, 84, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setToolTipText("Số thẻ");
		textSoThe.setTextLimit(13);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(100, 60, 80, 30);

		CLabel lbNguoiSuDung = new CLabel(composite, SWT.RIGHT);
		lbNguoiSuDung.setToolTipText("Người sử dụng");
		lbNguoiSuDung.setText("Người sử dụng");
		lbNguoiSuDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiSuDung.setBounds(186, 60, 158, 30);

		textNguoiSuDung = new Text(composite, SWT.BORDER);
		textNguoiSuDung.setToolTipText("Người sử dụng");
		textNguoiSuDung.setTextLimit(13);
		textNguoiSuDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiSuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguoiSuDung.setBounds(350, 60, 200, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setToolTipText("Đơn vị");
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(560, 60, 147, 30);

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setToolTipText("Đơn vị");
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBounds(714, 60, 284, 30);
		comboDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbNgayTrangBi = new CLabel(composite, SWT.RIGHT);
		lbNgayTrangBi.setToolTipText("Ngày trang bị");
		lbNgayTrangBi.setText("Ngày trang bị");
		lbNgayTrangBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayTrangBi.setBounds(10, 100, 131, 30);

		textNgayTrangBi1 = new Text(composite, SWT.BORDER);
		textNgayTrangBi1.setToolTipText("Ngày trang bị 1");
		textNgayTrangBi1.setTextLimit(13);
		textNgayTrangBi1.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNgayTrangBi1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNgayTrangBi1.setBounds(147, 100, 120, 30);

		CLabel lbNgayTrangBiDauNga = new CLabel(composite, SWT.RIGHT);
		lbNgayTrangBiDauNga.setAlignment(SWT.CENTER);
		lbNgayTrangBiDauNga.setText("~");
		lbNgayTrangBiDauNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayTrangBiDauNga.setBounds(273, 100, 20, 30);

		textNgayTrangBi2 = new Text(composite, SWT.BORDER);
		textNgayTrangBi2.setToolTipText("Ngày trang bị 2");
		textNgayTrangBi2.setTextLimit(13);
		textNgayTrangBi2.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNgayTrangBi2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNgayTrangBi2.setBounds(299, 100, 120, 30);

		CLabel lbNgayBiHu = new CLabel(composite, SWT.RIGHT);
		lbNgayBiHu.setToolTipText("Ngày bị hư");
		lbNgayBiHu.setText("Ngày bị hư");
		lbNgayBiHu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayBiHu.setBounds(425, 100, 129, 30);

		textNgayBiHu1 = new Text(composite, SWT.BORDER);
		textNgayBiHu1.setToolTipText("Ngày bị hư 1");
		textNgayBiHu1.setTextLimit(13);
		textNgayBiHu1.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNgayBiHu1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNgayBiHu1.setBounds(560, 100, 120, 30);

		CLabel lbNgayBiHuDaNga = new CLabel(composite, SWT.RIGHT);
		lbNgayBiHuDaNga.setText("~");
		lbNgayBiHuDaNga.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayBiHuDaNga.setAlignment(SWT.CENTER);
		lbNgayBiHuDaNga.setBounds(688, 100, 20, 30);

		textNgayBiHu2 = new Text(composite, SWT.BORDER);
		textNgayBiHu2.setToolTipText("Ngày bị hư 2");
		textNgayBiHu2.setTextLimit(13);
		textNgayBiHu2.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNgayBiHu2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNgayBiHu2.setBounds(714, 100, 120, 30);

		btnTepDinhKem = new Button(composite, SWT.NONE);
		btnTepDinhKem.setVisible(true);
		btnTepDinhKem.setToolTipText("");
		btnTepDinhKem.setText("Tệp đính kèm");
		btnTepDinhKem.setImage(
				SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/attachment24.png"));
		btnTepDinhKem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTepDinhKem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTepDinhKem.setBounds(1160, 20, 160, 32);

		btnTimKiem = new Button(composite, SWT.NONE);
		btnTimKiem.setToolTipText("Tìm kiếm");
		btnTimKiem.setVisible(true);
		btnTimKiem.setText("Tìm kiếm");
		btnTimKiem.setImage(
				SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/search-30.png"));
		btnTimKiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btnTimKiem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTimKiem.setBounds(1004, 60, 150, 32);

		btnXuatExcel = new Button(composite, SWT.NONE);
		btnXuatExcel.setVisible(true);
		btnXuatExcel.setToolTipText("Xuất Excel");
		btnXuatExcel.setText("Xuất Excel");
		btnXuatExcel
				.setImage(SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/excel25.png"));
		btnXuatExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnXuatExcel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXuatExcel.setBounds(1160, 60, 160, 32);

		btnThem = new Button(composite, SWT.NONE);
		btnThem.setToolTipText("Thêm");
		btnThem.setVisible(true);
		btnThem.setText("Thêm");
		btnThem.setImage(SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/add30.png"));
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnThem.setBounds(840, 100, 110, 32);

		btnSua = new Button(composite, SWT.NONE);
		btnSua.setToolTipText("Sửa");
		btnSua.setVisible(true);
		btnSua.setText("Sửa");
		btnSua.setImage(SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/edit.png"));
		btnSua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		btnSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnSua.setBounds(956, 100, 110, 32);

		btnXoa = new Button(composite, SWT.NONE);
		btnXoa.setToolTipText("Xóa");
		btnXoa.setVisible(true);
		btnXoa.setText("Xóa");
		btnXoa.setImage(SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/delete20.png"));
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXoa.setBounds(1072, 100, 110, 32);

		btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1188, 100, 110, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);

		btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(DanhSachThietBiHu.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1304, 100, 110, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnHuy.setVisible(true);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setHeaderBackground(SWTResourceManager.getColor(154, 205, 50));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(10, 140, 1900, 830);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setToolTipText("Số thứ tự");
		tbcSTT.setWidth(45);
		tbcSTT.setText("STT");
		tbcSTT.setResizable(false);

		tbcMaThietBi = new TableColumn(table, SWT.NONE);
		tbcMaThietBi.setToolTipText("Mã thiết bị");
		tbcMaThietBi.setWidth(0);
		tbcMaThietBi.setResizable(false);
		tbcMaThietBi.setText("Mã thiết bị");

		TableColumn tbcTenThietBi = new TableColumn(table, SWT.NONE);
		tbcTenThietBi.setToolTipText("Tên thiết bị");
		tbcTenThietBi.setWidth(236);
		tbcTenThietBi.setText("Tên thiết bị");

		TableColumn tbcDonViTinh = new TableColumn(table, SWT.NONE);
		tbcDonViTinh.setToolTipText("Đơn vị tính");
		tbcDonViTinh.setWidth(112);
		tbcDonViTinh.setText("Đơn vị tính");

		TableColumn tbcSoLuong = new TableColumn(table, SWT.NONE);
		tbcSoLuong.setToolTipText("Số lượng");
		tbcSoLuong.setWidth(92);
		tbcSoLuong.setText("Số lượng");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setToolTipText("Số thẻ");
		tbcSoThe.setWidth(95);
		tbcSoThe.setText("Số thẻ");

		TableColumn tbcNguoiSuDung = new TableColumn(table, SWT.NONE);
		tbcNguoiSuDung.setToolTipText("Người sử dụng");
		tbcNguoiSuDung.setWidth(160);
		tbcNguoiSuDung.setText("Người sử dụng");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setToolTipText("Đơn vị");
		tbcDonVi.setWidth(169);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcNgayTrangBi = new TableColumn(table, SWT.NONE);
		tbcNgayTrangBi.setToolTipText("Ngày trang bị");
		tbcNgayTrangBi.setWidth(129);
		tbcNgayTrangBi.setText("Ngày trang bị");

		TableColumn tbcNgayBiHu = new TableColumn(table, SWT.NONE);
		tbcNgayBiHu.setToolTipText("Ngày bị hư");
		tbcNgayBiHu.setWidth(108);
		tbcNgayBiHu.setText("Ngày bị hư");

		TableColumn tbcNguoiNhan = new TableColumn(table, SWT.NONE);
		tbcNguoiNhan.setToolTipText("Người nhận");
		tbcNguoiNhan.setWidth(170);
		tbcNguoiNhan.setText("Người nhận");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setToolTipText("Ghi chú");
		tbcGhiChu.setWidth(166);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(160);
		tbcNguoiCapNhat.setToolTipText("Người cập nhật");
		tbcNguoiCapNhat.setText("Người cập nhật");

		TableColumn tbcMaTepDinhKem = new TableColumn(table, SWT.NONE);
		tbcMaTepDinhKem.setWidth(0);
		tbcMaTepDinhKem.setText("Mã tệp đính kèm");
		tbcMaTepDinhKem.setResizable(false);

		btnTepDinhKem.setVisible(false);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbTenThietBi.setText("Tên thiết bị");
			lbDonViTinh.setText("Đơn vị tính");
			lbSoLuong.setText("Số lượng");
			lbNguoiNhan.setText("Người nhận");
			lbSoThe.setText("Số thẻ");
			lbNguoiSuDung.setText("Người sử dụng");
			lbDonVi.setText("Đơn vị");
			btnTimKiem.setText("Tìm kiếm");
			lbNgayTrangBi.setText("Ngày trang bị");
			lbNgayBiHu.setText("Ngày bị hư");
			btnTepDinhKem.setText("Tệp đính kèm");
			btnThem.setText("Thêm");
			btnSua.setText("Sửa");
			btnXoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			btnXuatExcel.setText("Xuất Excel");

			tbcSTT.setText("STT");
			tbcMaThietBi.setText("Mã thiết bị");
			tbcTenThietBi.setText("Tên thiết bị");
			tbcDonViTinh.setText("Đơn vị tính");
			tbcSoLuong.setText("Số lượng");
			tbcSoThe.setText("Số thẻ");
			tbcNguoiSuDung.setText("Người sử dụng");
			tbcDonVi.setText("Đơn vị");
			tbcNgayTrangBi.setText("Ngày trang bị");
			tbcNgayBiHu.setText("Ngày bị hư");
			tbcNguoiNhan.setText("Người nhận");
			tbcGhiChu.setText("Ghi chú");
			tbcNguoiCapNhat.setText("Người cập nhật");
		} else {
			// Tieng Anh
			lbTenThietBi.setText("Device name");
			lbDonViTinh.setText("Unit");
			lbSoLuong.setText("Count");
			lbNguoiNhan.setText("Receiver");
			lbSoThe.setText("ID");
			lbNguoiSuDung.setText("User");
			lbDonVi.setText("Department");
			btnTepDinhKem.setText("Attachments");
			btnTimKiem.setText("Search");
			lbNgayTrangBi.setText("Equip date");
			lbNgayBiHu.setText("Damaged date");
			btnThem.setText("Add");
			btnSua.setText("Edit");
			btnXoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			btnXuatExcel.setText("Export Excel");

			tbcSTT.setText("Num");
			tbcMaThietBi.setText("Device id");
			tbcTenThietBi.setText("Device name");
			tbcDonViTinh.setText("Unit");
			tbcSoLuong.setText("Count");
			tbcSoThe.setText("ID");
			tbcNguoiSuDung.setText("User");
			tbcDonVi.setText("Department");
			tbcNgayTrangBi.setText("Equip date");
			tbcNgayBiHu.setText("Damaged date");
			tbcNguoiNhan.setText("Receiver");
			tbcGhiChu.setText("Note");
			tbcNguoiCapNhat.setText("Person update");
		}

		// kiểm tra nếu grouplogin là User thì không cho sửa, xóa, xuất Excel
		if (grouplogin.equals("User")) {
			btnSua.setVisible(false);
			btnXoa.setVisible(false);
			btnXuatExcel.setVisible(false);
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

		// text ngày trang bị chỉ cho nhập số và dấu /
		textNgayTrangBi1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		// text ngày trang bị chỉ cho nhập số và dấu /
		textNgayTrangBi2.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		// text ngày bị hư chỉ cho nhập số và dấu /
		textNgayBiHu1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		// text ngày bị hư chỉ cho nhập số và dấu /
		textNgayBiHu2.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		// lấy dữ liệu cho combo đơn vị tính
		comboDonViTinh.setItems(arraydonvitinh);

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
				arraydonvi.add(result.getString(1));
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

		// lấy dữ liệu cho combo người nhận thiết bị hư
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [TenNguoiDung] FROM [dbo].[NguoiDung] WHERE [TenNhom]!='Disable' ORDER BY [TenNguoiDung] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				arraynguoinhanthietbihu.add(result.getString(1));
				comboNguoiNhan.add(result.getString(1));
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

		// tìm kiếm
		// ----------------------------------------------------------------------------------------------------------
		btnTimKiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// tìm kiếm sau khi Enter ở btn tìm kiếm
		// ----------------------------------------------------------------------------------------------------------
		btnTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text tên thiết bị
		// ----------------------------------------------------------------------------------------------------------
		textTenThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở combo đơn vị tính
		// ----------------------------------------------------------------------------------------------------------
		comboDonViTinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text số lượng
		// ----------------------------------------------------------------------------------------------------------
		textSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở combo người nhận
		// ----------------------------------------------------------------------------------------------------------
		comboNguoiNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text số thẻ
		// ----------------------------------------------------------------------------------------------------------
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text người sử dụng
		// ----------------------------------------------------------------------------------------------------------
		textNguoiSuDung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở combo đơn vị
		// ----------------------------------------------------------------------------------------------------------
		comboDonVi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text ngày trang bị 1
		// ----------------------------------------------------------------------------------------------------------
		textNgayTrangBi1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text ngày trang bị 2
		// ----------------------------------------------------------------------------------------------------------
		textNgayTrangBi2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text ngày bị hư 1
		// ----------------------------------------------------------------------------------------------------------
		textNgayBiHu1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// tìm kiếm sau khi Enter ở text ngày bị hư 2
		// ----------------------------------------------------------------------------------------------------------
		textNgayBiHu2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Excel
		// ----------------------------------------------------------------------------------------------------------
		btnXuatExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Thiết bị hư");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Broken device");
				}
			}
		});

		// Export sau khi enter ở button Export Excel
		// ******************************************************************************
		btnXuatExcel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (ngonngu == 0) {
						Excel.exportExcelTable(table, shell, ngonngu, "Thiết bị hư");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "Broken device");
					}
				}
			}
		});

		// thêm
		// -----------------------------------------------------------------------------------------------------------
		btnThem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (status != 1) {
					table.removeAll();
					table.update();
					btnTepDinhKem.setVisible(true);
					btnTimKiem.setEnabled(false);
					btnXuatExcel.setEnabled(false);
					btnSua.setEnabled(false);
					btnXoa.setEnabled(false);
					btnLuu.setVisible(true);
					btnHuy.setVisible(true);
					loadDataTable();
					status = 1;
				}
			}
		});

		// upload file
		btnTepDinhKem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] FILTER_NAMES = { "All Files (*.*)" };
				// đuôi file có thể mở
				String[] FILTER_EXTS = { "*.*" };

				FileDialog dlg = new FileDialog(shell, SWT.OPEN);
				dlg.setFilterNames(FILTER_NAMES);
				dlg.setFilterExtensions(FILTER_EXTS);
				filename = dlg.open();
				if (filename != null) {
					Path path = Paths.get(filename);
					Path tenfile = path.getFileName();
					tentep = tenfile.toString();
				}
			}
		});

		// Sửa
		// ------------------------------------------------------------------------------------------------------------
		btnSua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (status != 2) {
					try {
						TableItem[] itemsua = table.getSelection();
						mathietbisua = "";
						mathietbisua = itemsua[0].getText(1);
						edititemvitri = itemsua[0]; // sử dụng cho lưu sửa cập nhật dữ liệu mới tại dòng đã sửa
						filename = "";
						tentep = "";
						matepsua = itemsua[0].getText(13);
						if (!mathietbisua.isEmpty()) {
							btnTepDinhKem.setVisible(true);
							btnTimKiem.setEnabled(false);
							btnXuatExcel.setEnabled(false);
							btnThem.setEnabled(false);
							btnXoa.setEnabled(false);
							btnLuu.setVisible(true);
							btnHuy.setVisible(true);
							status = 2;

							// cho phép sửa dòng đã chọn
							// tên thiết bị
							tableEditor = new TableEditor(table);
							edittextthietbi = new Text(table, SWT.BORDER);
							edittextthietbi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							edittextthietbi.setBackground(SWTResourceManager.getColor(127, 255, 212));
							edittextthietbi.setTextLimit(80);
							edittextthietbi.setText(itemsua[0].getText(2));

							tableEditor.grabHorizontal = true;
							tableEditor.minimumWidth = edittextthietbi.getSize().x;
							tableEditor.horizontalAlignment = SWT.CENTER;
							tableEditor.setEditor(edittextthietbi, itemsua[0], 2);

							// đơn vị tính
							tableEditor = new TableEditor(table);
							editcombodonvitinh = new CCombo(table, SWT.NONE | SWT.BORDER);
							editcombodonvitinh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							editcombodonvitinh.setBackground(SWTResourceManager.getColor(127, 255, 212));
							editcombodonvitinh.setTextLimit(10);
							editcombodonvitinh.setItems(arraydonvitinh);
							editcombodonvitinh.setText(itemsua[0].getText(3));
							editcombodonvitinh.setEditable(false);
							tableEditor.grabHorizontal = true;
							tableEditor.setEditor(editcombodonvitinh, itemsua[0], 3);

							// Số lượng
							tableEditor = new TableEditor(table);
							edittextsoluong = new Text(table, SWT.BORDER | SWT.RIGHT);
							edittextsoluong.setText(itemsua[0].getText(4));
							edittextsoluong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							edittextsoluong.setBackground(SWTResourceManager.getColor(127, 255, 212));
							edittextsoluong.setTextLimit(5);
							// text số lượng chỉ cho nhập số
							edittextsoluong.addVerifyListener(new VerifyListener() {
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
							tableEditor.grabHorizontal = true;
							tableEditor.setEditor(edittextsoluong, itemsua[0], 4);

							// số thẻ
							tableEditor = new TableEditor(table);
							edittextsothe = new Text(table, SWT.BORDER | SWT.RIGHT);
							edittextsothe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							edittextsothe.setBackground(SWTResourceManager.getColor(127, 255, 212));
							edittextsothe.setTextLimit(6);
							edittextsothe.setText(itemsua[0].getText(5));
							// text số thẻ chỉ cho nhập số
							edittextsothe.addVerifyListener(new VerifyListener() {
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

							tableEditor.grabHorizontal = true;
							tableEditor.setEditor(edittextsothe, itemsua[0], 5);

							// người sử dụng
							tableEditor = new TableEditor(table);
							edittextnguoisudung = new Text(table, SWT.BORDER);
							edittextnguoisudung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							edittextnguoisudung.setBackground(SWTResourceManager.getColor(127, 255, 212));
							edittextnguoisudung.setTextLimit(50);
							edittextnguoisudung.setText(itemsua[0].getText(6));

							tableEditor.grabHorizontal = true;
							tableEditor.minimumWidth = edittextnguoisudung.getSize().x;
							tableEditor.horizontalAlignment = SWT.CENTER;
							tableEditor.setEditor(edittextnguoisudung, itemsua[0], 6);

							// đơn vị
							tableEditor = new TableEditor(table);
							editcombodonvi = new CCombo(table, SWT.BORDER);
							editcombodonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							editcombodonvi.setBackground(SWTResourceManager.getColor(127, 255, 212));
							editcombodonvi.setTextLimit(50);
							editcombodonvi.setText(itemsua[0].getText(7));
							for (int k = 0; k < arraydonvi.size(); k++) {
								editcombodonvi.add(arraydonvi.get(k));
							}
							editcombodonvi.setEditable(false);
							tableEditor.grabHorizontal = true;
							tableEditor.setEditor(editcombodonvi, itemsua[0], 7);

							// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị dựa vào số thẻ
							// ---------------------------------------------------------------------------------------------------
							edittextsothe.addModifyListener(new ModifyListener() {
								public void modifyText(ModifyEvent arg0) {
									try {
										if (connect == null) {
											connect = new ConnectSQL();
											connect.setConnection();
										}
										String select = "";
										ResultSet result;

										select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
												+ edittextsothe.getText() + "')";

										PreparedStatement ps = connect.getConnection().prepareStatement(select);

										// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
										result = ps.executeQuery();

										while (result.next()) {
											edittextnguoisudung.setText(result.getString(1));
											editcombodonvi.setText(result.getString(2));
										}
										result.close();

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
							});

							// ngày trang bị
							tableEditor = new TableEditor(table);
							edittextngaytrangbi = new Text(table, SWT.BORDER);
							edittextngaytrangbi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							edittextngaytrangbi.setBackground(SWTResourceManager.getColor(127, 255, 212));
							edittextngaytrangbi.setTextLimit(10);
							edittextngaytrangbi.setText(itemsua[0].getText(8));
							// text ngày trang bị chỉ cho nhập số và dấu /
							edittextngaytrangbi.addVerifyListener(new VerifyListener() {
								public void verifyText(VerifyEvent e) {
									String string = e.text;
									char[] chars = new char[string.length()];
									string.getChars(0, chars.length, chars, 0);
									for (int i = 0; i < chars.length; i++) {
										if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
											e.doit = false;
											return;
										}
									}
								}
							});

							tableEditor.grabHorizontal = true;
							tableEditor.minimumWidth = edittextngaytrangbi.getSize().x;
							tableEditor.horizontalAlignment = SWT.CENTER;
							tableEditor.setEditor(edittextngaytrangbi, itemsua[0], 8);

							// ngày bị hư
							tableEditor = new TableEditor(table);
							edittextngaybihu = new Text(table, SWT.BORDER);
							edittextngaybihu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							edittextngaybihu.setBackground(SWTResourceManager.getColor(127, 255, 212));
							edittextngaybihu.setTextLimit(10);
							edittextngaybihu.setText(itemsua[0].getText(9));
							// text ngày bị hư chỉ cho nhập số và dấu /
							edittextngaybihu.addVerifyListener(new VerifyListener() {
								public void verifyText(VerifyEvent e) {
									String string = e.text;
									char[] chars = new char[string.length()];
									string.getChars(0, chars.length, chars, 0);
									for (int i = 0; i < chars.length; i++) {
										if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
											e.doit = false;
											return;
										}
									}
								}
							});

							tableEditor.grabHorizontal = true;
							tableEditor.minimumWidth = edittextngaybihu.getSize().x;
							tableEditor.horizontalAlignment = SWT.CENTER;
							tableEditor.setEditor(edittextngaybihu, itemsua[0], 9);

							// người nhận
							tableEditor = new TableEditor(table);
							editcombonguoinhan = new CCombo(table, SWT.BORDER);
							editcombonguoinhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							editcombonguoinhan.setBackground(SWTResourceManager.getColor(127, 255, 212));
							editcombonguoinhan.setTextLimit(50);
							editcombonguoinhan.setText(itemsua[0].getText(10));
							for (int j = 0; j < arraynguoinhanthietbihu.size(); j++) {
								editcombonguoinhan.add(arraynguoinhanthietbihu.get(j));
							}
							editcombonguoinhan.setEditable(false);

							tableEditor.grabHorizontal = true;
							tableEditor.minimumWidth = editcombonguoinhan.getSize().x;
							tableEditor.horizontalAlignment = SWT.CENTER;
							tableEditor.setEditor(editcombonguoinhan, itemsua[0], 10);

							// ghi chú
							tableEditor = new TableEditor(table);
							edittextghichu = new Text(table, SWT.BORDER);
							edittextghichu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
							edittextghichu.setBackground(SWTResourceManager.getColor(127, 255, 212));
							edittextghichu.setTextLimit(255);
							edittextghichu.setText(itemsua[0].getText(11));

							tableEditor.grabHorizontal = true;
							tableEditor.minimumWidth = edittextghichu.getSize().x;
							tableEditor.horizontalAlignment = SWT.CENTER;
							tableEditor.setEditor(edittextghichu, itemsua[0], 11);

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
					}
				}
			}
		});

		// Xóa
		// ------------------------------------------------------------------------------------------------------------
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (status != 3) {

					try {
						matepdinhkemxoa="";
						// Lấy cột mã thiết bị hư
						itemtable = table.getSelection();
						mathietbihuxoa = new String[itemtable.length];

						for (int i = 0; i < mathietbihuxoa.length; i++) {
							mathietbihuxoa[i] = itemtable[i].getText(1);
						}
						if (mathietbihuxoa.length > 0) {
							matepdinhkemxoa=itemtable[0].getText(13);
							
							btnTepDinhKem.setVisible(false);
							btnTimKiem.setEnabled(false);
							btnXuatExcel.setEnabled(false);
							btnThem.setEnabled(false);
							btnSua.setEnabled(false);
							btnLuu.setVisible(true);
							btnHuy.setVisible(true);
							status = 3;
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
						btnTepDinhKem.setVisible(false);
						btnTimKiem.setEnabled(true);
						btnThem.setEnabled(true);
						btnXuatExcel.setEnabled(true);
						btnSua.setEnabled(true);
						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						table.setEnabled(true);
					}
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

					if (status == 1) {
						// thêm tệp đính kèm
						String matepdinhkem = "";
						int idfileincrease = 0;
						try {
							if (filename != null || !filename.isEmpty()) {
								String selectfile = "SELECT TOP 1 (SELECT SUBSTRING([MaTepDinhKem],3,8)) FROM [dbo].[BP_DSThietBiHu_TepDinhKem] ORDER BY [MaTepDinhKem] DESC";
								ResultSet resultid = connect.getStatement().executeQuery(selectfile);
								while (resultid.next()) {
									matepdinhkem = resultid.getString(1);
								}
								try {
									if (!matepdinhkem.isEmpty()) {
										idfileincrease = Integer.parseInt(matepdinhkem) + 1;
									} else {
										idfileincrease = 10000001;
									}
								} catch (Exception ew) {
									idfileincrease = 10000001;
								}
								// đọc hết một lần
								byte[] content = Files.readAllBytes(Paths.get(filename));

								String insert = "INSERT INTO [dbo].[BP_DSThietBiHu_TepDinhKem] ([MaTepDinhKem],[TenFile],[FileDinhKem]) VALUES ('DK"
										+ idfileincrease + "',N'" + tentep + "',?)";

								PreparedStatement stmt = connect.getConnection().prepareStatement(insert);
								stmt.setBytes(1, content);
								stmt.executeUpdate();
								stmt.close();
							}
						} catch (Exception exc) {
						}
						// thêm
						TableItem[] items = table.getItems();
						String insertdsthietbihu = "";
						String id = ""; // ma thiet bi hu
						int idincrease = 0;
						String select = "SELECT TOP 1 (SELECT SUBSTRING([MaThietBiHu],3,8)) FROM [dbo].[BP_DSThietBiHu] ORDER BY [MaThietBiHu] DESC";
						ResultSet resultset = connect.getStatement().executeQuery(select);
						while (resultset.next()) {
							id = resultset.getString(1);
						}
						try {
							if (!id.isEmpty()) {
								idincrease = Integer.parseInt(id) + 1;
							} else {
								idincrease = 10000001;
							}
						} catch (Exception ew) {
							idincrease = 10000001;
						}
						for (int i = 0; i < items.length; i++) {
							if (!arraytextthietbi.get(i).getText().isEmpty()
									&& !arraycombodonvitinh.get(i).getText().isEmpty()
									&& !arraytextsoluong.get(i).getText().isEmpty()
									&& !arraycombodonvi.get(i).getText().isEmpty()
									&& !arraytextngaybihu.get(i).getText().isEmpty()) {
								insertdsthietbihu = insertdsthietbihu
										+ "INSERT INTO [dbo].[BP_DSThietBiHu] ([MaThietBiHu],[TenThietBiHu],[DonViTinh],[SoLuong],[SoThe],[HoTen],[DonVi],[NgayTrangBi],[NgayBiHu],[NguoiNhan],[GhiChu],[NguoiCapNhat],[MaTepDinhKem]) VALUES ('BP"
										+ idincrease + "',N'" + arraytextthietbi.get(i).getText() + "',N'"
										+ arraycombodonvitinh.get(i).getText() + "',"
										+ arraytextsoluong.get(i).getText() + ",'" + arraytextsothe.get(i).getText()
										+ "',N'" + arraytextnguoisudung.get(i).getText() + "',N'"
										+ arraycombodonvi.get(i).getText() + "','"
										+ arraytextngaytrangbi.get(i).getText() + "','"
										+ arraytextngaybihu.get(i).getText() + "',N'"
										+ arraycombonguoinhan.get(i).getText() + "',N'"
										+ arraytextghichu.get(i).getText() + "','" + userlogin + "','DK" + idfileincrease
										+ "')" + "\n";
								idincrease++;
							}
						}

						int resultthemthietbihu = connect.execUpdateQuery(insertdsthietbihu);
						if (resultthemthietbihu > 0) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Thêm thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Add suscess!");
							}
							thongbao.open();
						}
						connect.closeStatement();

						// gọi giống button hủy
						cancel();
						search(shell);

					} else if (status == 2) {
						// sửa tệp đính kèm
						try {
							if (filename != null || !filename.isEmpty()) {
								// đọc hết một lần
								byte[] content = Files.readAllBytes(Paths.get(filename));

								String update = "UPDATE [dbo].[BP_DSThietBiHu_TepDinhKem] SET [TenFile]=N'" + tentep
										+ "',[FileDinhKem]=? WHERE [MaTepDinhKem]='" + matepsua + "'";

								PreparedStatement stmt = connect.getConnection().prepareStatement(update);
								stmt.setBytes(1, content);
								stmt.executeUpdate();
								stmt.close();
							}
						} catch (Exception exc) {
						}

						// sửa
						String updatedsthietbihu = "UPDATE [dbo].[BP_DSThietBiHu] SET [TenThietBiHu] = N'"
								+ edittextthietbi.getText() + "',[DonViTinh] = N'" + editcombodonvitinh.getText()
								+ "',[SoLuong] = " + edittextsoluong.getText() + ",[SoThe] = '"
								+ edittextsothe.getText() + "',[HoTen] = N'" + edittextnguoisudung.getText()
								+ "',[DonVi] = N'" + editcombodonvi.getText() + "',[NgayTrangBi] = '"
								+ edittextngaybihu.getText() + "',[NgayBiHu] = '" + edittextngaybihu.getText()
								+ "',[NguoiNhan] = N'" + editcombonguoinhan.getText() + "',[GhiChu] = N'"
								+ edittextghichu.getText() + "',[NguoiCapNhat]='" + userlogin
								+ "' WHERE [MaThietBiHu]='" + mathietbisua + "'";

						int resultthemthietbihu = connect.execUpdateQuery(updatedsthietbihu);
						if (resultthemthietbihu > 0) {

							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Sửa thành công!");
								edititemvitri.setText(2, edittextthietbi.getText());
								edititemvitri.setText(3, editcombodonvitinh.getText());
								edititemvitri.setText(4, edittextsoluong.getText());
								edititemvitri.setText(5, edittextsothe.getText());
								edititemvitri.setText(6, edittextnguoisudung.getText());
								edititemvitri.setText(7, editcombodonvi.getText());
								edititemvitri.setText(8, edittextngaytrangbi.getText());
								edititemvitri.setText(9, edittextngaybihu.getText());
								edititemvitri.setText(10, editcombonguoinhan.getText());
								edititemvitri.setText(11, edittextghichu.getText());
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Edit suscess!");
							}
							thongbao.open();
						}
						connect.closeStatement();

						btnTepDinhKem.setVisible(false);
						filename = "";
						tentep = "";
						btnTimKiem.setEnabled(true);
						btnXuatExcel.setEnabled(true);
						btnThem.setEnabled(true);
						btnSua.setEnabled(true);
						btnXoa.setEnabled(true);
						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						table.setEnabled(true);
						status = 0;
						try {
							// sửa
							edittextthietbi.dispose();
							editcombodonvitinh.dispose();
							edittextsoluong.dispose();
							edittextsothe.dispose();
							edittextnguoisudung.dispose();
							editcombodonvi.dispose();
							edittextngaytrangbi.dispose();
							edittextngaybihu.dispose();
							editcombonguoinhan.dispose();
							edittextghichu.dispose();
						} catch (Exception ex) {
						}

					} else if (status == 3) {
						// Xóa
						try {
							String delete = "";
							for (int i = 0; i < mathietbihuxoa.length; i++) {
								delete = delete + "\n" + "DELETE FROM [dbo].[BP_DSThietBiHu] WHERE [MaThietBiHu]='"
										+ mathietbihuxoa[i] + "'";
							}

							int result = connect.execUpdateQuery(delete);
							if (result > 0) {
								table.remove(table.getSelectionIndices());
								
								// kiểm tra xem có còn dòng nào trong bảng thiết bị hư có chứa mã thiết bị hư hay không? nếu không thì xóa nó ra khỏi bảng file đính kèm
								connect.setStatement();
								String select="SELECT TOP 1 [MaTepDinhKem] FROM [dbo].[BP_DSThietBiHu] WHERE [MaTepDinhKem]='"+matepdinhkemxoa+"'";
								ResultSet resultselect=connect.getStatement().executeQuery(select);
								boolean check=false; // kiem tra xem ma tep dinh kem co ton tai trong bang thiet bi hu khong
								while(resultselect.next()) {
									check=true;
								}
								if(!check) {
									String deletefile="DELETE FROM [dbo].[BP_DSThietBiHu_TepDinhKem] WHERE [MaTepDinhKem]='"+matepdinhkemxoa+"'";
									connect.getStatement().execute(deletefile);
								}
							}
							connect.closeStatement();
							btnTepDinhKem.setVisible(false);
							btnTimKiem.setEnabled(true);
							btnXuatExcel.setEnabled(true);
							btnThem.setEnabled(true);
							btnSua.setEnabled(true);
							btnXoa.setEnabled(true);
							btnLuu.setVisible(false);
							btnHuy.setVisible(false);
							table.setEnabled(true);
							status = 0;

						} catch (Exception ex) {
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
		});

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// sửa
				if (status == 2) {
					btnTepDinhKem.setVisible(false);
					btnTimKiem.setEnabled(true);
					btnThem.setEnabled(true);
					btnXuatExcel.setEnabled(true);
					btnSua.setEnabled(true);
					btnXoa.setEnabled(true);
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
					table.setEnabled(true);
					status = 0;
					filename = "";
					tentep = "";
					try {
						// sửa
						edittextthietbi.dispose();
						editcombodonvitinh.dispose();
						edittextsoluong.dispose();
						edittextsothe.dispose();
						edittextnguoisudung.dispose();
						editcombodonvi.dispose();
						edittextngaytrangbi.dispose();
						edittextngaybihu.dispose();
						editcombonguoinhan.dispose();
						edittextghichu.dispose();
					} catch (Exception ex) {
					}
				} else {
					// các trường hợp hủy khác
					cancel();
					search(shell);
				}
			}
		});

		// mo cua so luu file khi double click vao dong cua bang
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					TableItem[] item = table.getSelection();
					connect = new ConnectSQL();
					connect.setConnection();
					connect.setStatement();

					String[] FILTER_NAMES = { "All Files (*.*)" };
					// đuôi file có thể mở
					String[] FILTER_EXTS = { "*.*" };

					FileDialog dlg = new FileDialog(shell, SWT.SAVE);
					dlg.setFilterNames(FILTER_NAMES);
					dlg.setFilterExtensions(FILTER_EXTS);
					

					String select = "SELECT [FileDinhKem],[TenFile] FROM [dbo].[BP_DSThietBiHu_TepDinhKem] WHERE [MaTepDinhKem]='" + item[0].getText(13)
							+ "'";
					ResultSet ketqua = connect.getStatement().executeQuery(select);
					boolean savesuccess = false;
					while (ketqua.next()) {
						dlg.setFileName(ketqua.getString(2));
						String filenamedownload = dlg.open();
						
						FileOutputStream outputStream = new FileOutputStream(filenamedownload);
						byte[] strToBytes = ketqua.getBytes(1);
						outputStream.write(strToBytes);
						outputStream.close();
						savesuccess = true;
					}
					ketqua.close();
					connect.closeStatement();
					if (savesuccess) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Tệp được lưu thành công!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("File saved successfully!");
						}
						thongbao.open();
					}
				} catch (Exception ex) {
				}
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcMaThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcTenThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonViTinh.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoLuong.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiSuDung.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayTrangBi.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgayBiHu.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNguoiNhan.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiCapNhat.addListener(SWT.Selection, sort.sortListenerCode);
	}

	public void cancel() {
		table.removeAll();
		btnTepDinhKem.setVisible(false);
		btnTimKiem.setEnabled(true);
		btnXuatExcel.setEnabled(true);
		btnThem.setEnabled(true);
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);
		table.setEnabled(true);
		status = 0;
		filename = "";
		tentep = "";
		try {
			int sizetable = arraytextthietbi.size();
			for (int i = 0; i < sizetable; i++) {
				arraytextthietbi.get(i).dispose();
				arraycombodonvitinh.get(i).dispose();
				arraytextsoluong.get(i).dispose();
				arraytextsothe.get(i).dispose();
				arraytextnguoisudung.get(i).dispose();
				arraycombodonvi.get(i).dispose();
				arraytextngaytrangbi.get(i).dispose();
				arraytextngaybihu.get(i).dispose();
				arraycombonguoinhan.get(i).dispose();
				arraytextghichu.get(i).dispose();
			}

		} catch (Exception ex) {
		}
	}

	// load dữ liệu cho table
	public void loadDataTable() {
		try {
			int sizetable = arraytextthietbi.size();
			for (int i = 0; i < sizetable; i++) {
				arraytextthietbi.get(i).dispose();
				arraycombodonvitinh.get(i).dispose();
				arraytextsoluong.get(i).dispose();
				arraytextsothe.get(i).dispose();
				arraytextnguoisudung.get(i).dispose();
				arraycombodonvi.get(i).dispose();
				arraytextngaytrangbi.get(i).dispose();
				arraytextngaybihu.get(i).dispose();
				arraycombonguoinhan.get(i).dispose();
				arraytextghichu.get(i).dispose();
			}
		} catch (Exception ex) {
		}
		arraytextthietbi = new ArrayList<>();
		arraycombodonvitinh = new ArrayList<>();
		arraytextsoluong = new ArrayList<>();
		arraytextsothe = new ArrayList<>();
		arraytextnguoisudung = new ArrayList<>();
		arraycombodonvi = new ArrayList<>();
		arraytextngaytrangbi = new ArrayList<>();
		arraytextngaybihu = new ArrayList<>();
		arraycombonguoinhan = new ArrayList<>();
		arraytextghichu = new ArrayList<>();

		try {
			int stt = 1;
			for (int i = 0; i < 20; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", "", "", "", "", "", "", "", "", "", "", "" });
				stt++;

				// tên thiết bị
				tableEditor = new TableEditor(table);
				Text textthietbi = new Text(table, SWT.BORDER);
				textthietbi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textthietbi.setBackground(SWTResourceManager.getColor(224, 255, 255));
				textthietbi.setTextLimit(80);
				textthietbi.setText("");
				arraytextthietbi.add(textthietbi);

				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = textthietbi.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(textthietbi, item, 2);

				// đơn vị tính
				tableEditor = new TableEditor(table);
				CCombo combo = new CCombo(table, SWT.NONE | SWT.BORDER);
				combo.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				combo.setBackground(SWTResourceManager.getColor(224, 255, 255));
				combo.setTextLimit(10);
				combo.setItems(arraydonvitinh);
				combo.setEditable(false);
				arraycombodonvitinh.add(combo);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(combo, item, 3);

				// Số lượng
				tableEditor = new TableEditor(table);
				Text textsoluong = new Text(table, SWT.BORDER | SWT.RIGHT);
				textsoluong.setText("");
				textsoluong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textsoluong.setBackground(SWTResourceManager.getColor(224, 255, 255));
				textsoluong.setTextLimit(5);
				// text số lượng chỉ cho nhập số
				textsoluong.addVerifyListener(new VerifyListener() {
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
				arraytextsoluong.add(textsoluong);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(textsoluong, item, 4);

				// số thẻ
				tableEditor = new TableEditor(table);
				Text textst = new Text(table, SWT.BORDER | SWT.RIGHT);
				textst.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textst.setTextLimit(6);
				textst.setText("");
				// text số thẻ chỉ cho nhập số
				textst.addVerifyListener(new VerifyListener() {
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
				arraytextsothe.add(textst);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(textst, item, 5);

				// người sử dụng
				tableEditor = new TableEditor(table);
				Text textuser = new Text(table, SWT.BORDER);
				textuser.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textuser.setTextLimit(50);
				textuser.setText("");
				arraytextnguoisudung.add(textuser);

				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = textuser.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(textuser, item, 6);

				// đơn vị
				tableEditor = new TableEditor(table);
				CCombo combodepartment = new CCombo(table, SWT.BORDER);
				combodepartment.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				combodepartment.setBackground(SWTResourceManager.getColor(224, 255, 255));
				combodepartment.setTextLimit(50);
				for (int k = 0; k < arraydonvi.size(); k++) {
					combodepartment.add(arraydonvi.get(k));
				}
				combodepartment.setEditable(false);
				arraycombodonvi.add(combodepartment);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(combodepartment, item, 7);

				// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị dựa vào số thẻ
				// ---------------------------------------------------------------------------------------------------
				textst.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent arg0) {
						try {
							if (connect == null) {
								connect = new ConnectSQL();
								connect.setConnection();
							}
							String select = "";
							ResultSet result;

							select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
									+ textst.getText() + "')";

							PreparedStatement ps = connect.getConnection().prepareStatement(select);

							// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
							result = ps.executeQuery();
							boolean hasdata = false;
							while (result.next()) {
								textuser.setText(result.getString(1));
								combodepartment.setText(result.getString(2));
								hasdata = true;
							}
							result.close();
							if (!hasdata) {
								textuser.setText("");
								combodepartment.setText("");
							}

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
				});

				// ngày trang bị
				tableEditor = new TableEditor(table);
				Text textngaytb = new Text(table, SWT.BORDER);
				textngaytb.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textngaytb.setTextLimit(10);
				textngaytb.setText("");
				// text ngày trang bị chỉ cho nhập số và dấu /
				textngaytb.addVerifyListener(new VerifyListener() {
					public void verifyText(VerifyEvent e) {
						String string = e.text;
						char[] chars = new char[string.length()];
						string.getChars(0, chars.length, chars, 0);
						for (int i = 0; i < chars.length; i++) {
							if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
								e.doit = false;
								return;
							}
						}
					}
				});
				arraytextngaytrangbi.add(textngaytb);

				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = textngaytb.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(textngaytb, item, 8);

				// ngày bị hư
				tableEditor = new TableEditor(table);
				Text textngaybh = new Text(table, SWT.BORDER);
				textngaybh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textngaybh.setBackground(SWTResourceManager.getColor(224, 255, 255));
				textngaybh.setTextLimit(10);
				textngaybh.setText("");
				// text ngày bị hư chỉ cho nhập số và dấu /
				textngaybh.addVerifyListener(new VerifyListener() {
					public void verifyText(VerifyEvent e) {
						String string = e.text;
						char[] chars = new char[string.length()];
						string.getChars(0, chars.length, chars, 0);
						for (int i = 0; i < chars.length; i++) {
							if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '/')) {
								e.doit = false;
								return;
							}
						}
					}
				});
				arraytextngaybihu.add(textngaybh);

				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = textngaybh.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(textngaybh, item, 9);

				// người nhận
				tableEditor = new TableEditor(table);
				CCombo combonn = new CCombo(table, SWT.BORDER);
				combonn.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				combonn.setTextLimit(50);
				for (int j = 0; j < arraynguoinhanthietbihu.size(); j++) {
					combonn.add(arraynguoinhanthietbihu.get(j));
				}
				combonn.setEditable(false);
				arraycombonguoinhan.add(combonn);

				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = combonn.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(combonn, item, 10);

				// ghi chú
				tableEditor = new TableEditor(table);
				Text texttgc = new Text(table, SWT.BORDER);
				texttgc.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				texttgc.setTextLimit(255);
				texttgc.setText("");
				arraytextghichu.add(texttgc);

				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = texttgc.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(texttgc, item, 11);
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

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String user) {
		ngonngu = language;
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

			String tenthietbihu = textTenThietBi.getText().isEmpty() ? ""
					: " AND [TenThietBiHu] LIKE N'%" + textTenThietBi.getText() + "%'";
			String donvitinh = comboDonViTinh.getText().isEmpty() ? ""
					: " AND [DonViTinh]=N'" + comboDonViTinh.getText() + "'";
			String soluong = textSoLuong.getText().isEmpty() ? "" : " AND [SoLuong]=" + textSoLuong.getText() + "";
			String sothe = textSoThe.getText().isEmpty() ? "" : " AND [SoThe]='" + textSoThe.getText() + "'";
			String hoten = textNguoiSuDung.getText().isEmpty() ? ""
					: " AND [HoTen] LIKE N'%" + textNguoiSuDung.getText() + "%' ";
			String donvi = comboDonVi.getText().isEmpty() ? "" : " AND [DonVi]=N'" + comboDonVi.getText() + "' ";
			String nguoinhan = comboNguoiNhan.getText().isEmpty() ? ""
					: " AND [NguoiNhan]=N'" + comboNguoiNhan.getText() + "'";

			String ngaybatdau = textNgayTrangBi1.getText();
			String ngayketthuc = textNgayTrangBi2.getText();
			ngayketthuc = ngayketthuc.length() == 4 ? "31/12/" + ngayketthuc : ngayketthuc;
			// dd/mm/yyyy, dd-mm-yyyy (103 trong convert cua SQL)
			String ngaytrangbi = "";
			if (!ngaybatdau.isEmpty() && !ngayketthuc.isEmpty()) {
				ngaytrangbi = " AND (CONVERT(DATE, [NgayTrangBi],103) BETWEEN CONVERT(DATE, '" + ngaybatdau
						+ "',103) AND CONVERT(DATE, '" + ngayketthuc + "',103))";
			}

			ngaybatdau = textNgayBiHu1.getText();
			ngayketthuc = textNgayBiHu2.getText();
			ngayketthuc = ngayketthuc.length() == 4 ? "31/12/" + ngayketthuc : ngayketthuc;
			// dd/mm/yyyy, dd-mm-yyyy (103 trong convert cua SQL)
			String ngaybihu = "";
			if (!ngaybatdau.isEmpty() && !ngayketthuc.isEmpty()) {
				ngaybihu = " AND (CONVERT(DATE, [NgayBiHu],103) BETWEEN CONVERT(DATE, '" + ngaybatdau
						+ "',103) AND CONVERT(DATE, '" + ngayketthuc + "',103))";
			}

			String select = "SELECT [MaThietBiHu],[TenThietBiHu],[DonViTinh],[SoLuong],[SoThe],[HoTen],[DonVi],[NgayTrangBi],[NgayBiHu],[NguoiNhan],[GhiChu],[NguoiDung].[TenNguoiDung],[MaTepDinhKem] FROM [dbo].[BP_DSThietBiHu] LEFT JOIN [dbo].[NguoiDung] ON [dbo].[BP_DSThietBiHu].[NguoiCapNhat]=[dbo].[NguoiDung].[MaNguoiDung] OR [dbo].[BP_DSThietBiHu].[NguoiCapNhat]=[dbo].[NguoiDung].[TenDangNhap] WHERE 1=1"
					+ tenthietbihu + donvitinh + soluong + sothe + hoten + donvi + nguoinhan + ngaytrangbi + ngaybihu
					+ " ORDER BY [MaThietBiHu] ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						result.getString(8), result.getString(9), result.getString(10), result.getString(11),
						result.getString(12), result.getString(13) });
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

	// Hiện trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu, String userlogin, String grouplogin) {
		this.ngonngu = ngonngu;
		this.userlogin = userlogin;
		this.grouplogin = grouplogin;

		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Thiết bị hư");
		} else {
			itemtab.setText("Broken device");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);
		createContents(composite, shell);
	}
}
