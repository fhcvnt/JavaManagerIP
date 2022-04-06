package it;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class PhanCongCongViecSlideShow {
	protected Shell shell;
	private ConnectSQL connect;

	private ArrayList<CLabel> arraylabelDonVi;
	private ArrayList<CLabel> arraylabelSoThe;
	private ArrayList<CLabel> arraylabelHoTen;
	private ArrayList<CLabel> arraylabelTinhTrang;
	private ArrayList<CLabel> arraylabelKetQua;
	private ArrayList<CLabel> arraylabelNguoiXuLy;
	private int thoigiantimer = 20000; // thoi gian 20 giay hien thong bao, thay doi thong bao mot lan, su dung Timer

	public static void main(String[] args) {
		try {
			PhanCongCongViecSlideShow window = new PhanCongCongViecSlideShow();
			window.createContents();
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
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(PhanCongCongViecSlideShow.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1930, 1200);
		shell.setText("Slide show");
		shell.setMaximized(true);
		shell.setFullScreen(true);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite compositetop = new Composite(shell, SWT.NONE);
		compositetop.setBackground(SWTResourceManager.getColor(0, 0, 255));
		compositetop.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.NORMAL));

		arraylabelDonVi = new ArrayList<>();
		arraylabelSoThe = new ArrayList<>();
		arraylabelHoTen = new ArrayList<>();
		arraylabelTinhTrang = new ArrayList<>();
		arraylabelKetQua = new ArrayList<>();
		arraylabelNguoiXuLy = new ArrayList<>();

		CLabel lbNgay = new CLabel(compositetop, SWT.NONE);
		lbNgay.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNgay.setAlignment(SWT.RIGHT);
		lbNgay.setBackground(SWTResourceManager.getColor(0, 0, 255));
		lbNgay.setText("Ngày:");
		lbNgay.setFont(SWTResourceManager.getFont("Times New Roman", 33, SWT.BOLD));
		lbNgay.setBounds(1100, 0, 174, 90);

		CLabel laNgaychitiet = new CLabel(compositetop, SWT.NONE);
		laNgaychitiet.setForeground(SWTResourceManager.getColor(255, 255, 255));
		laNgaychitiet.setBackground(SWTResourceManager.getColor(0, 0, 255));
		laNgaychitiet.setFont(SWTResourceManager.getFont("Times New Roman", 33, SWT.BOLD));
		laNgaychitiet.setBounds(1330, 0, 268, 90);
		laNgaychitiet.setText("");
		// Lấy ngày tháng hiện tại
		String ngay = "0" + java.time.LocalDateTime.now().getDayOfMonth();
		ngay = ngay.substring(ngay.length() - 2);
		String thang = "0" + (java.time.LocalDateTime.now().getMonthValue());
		thang = thang.substring(thang.length() - 2);
		String nam = "" + java.time.LocalDateTime.now().getYear();
		String today = ngay + "/" + thang + "/" + nam;
		laNgaychitiet.setText(today);

		CLabel lbDonVi = new CLabel(compositetop, SWT.BORDER | SWT.SHADOW_OUT);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 30, SWT.BOLD));
		lbDonVi.setBackground(SWTResourceManager.getColor(0, 0, 255));
		lbDonVi.setAlignment(SWT.CENTER);
		lbDonVi.setBounds(0, 90, 334, 70);

		CLabel lbSoThe = new CLabel(compositetop, SWT.BORDER | SWT.SHADOW_OUT);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 30, SWT.BOLD));
		lbSoThe.setBackground(SWTResourceManager.getColor(0, 0, 255));
		lbSoThe.setAlignment(SWT.CENTER);
		lbSoThe.setBounds(334, 90, 176, 70);

		CLabel lbHoTen = new CLabel(compositetop, SWT.BORDER | SWT.SHADOW_OUT);
		lbHoTen.setText("Họ tên");
		lbHoTen.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 30, SWT.BOLD));
		lbHoTen.setBackground(SWTResourceManager.getColor(0, 0, 255));
		lbHoTen.setAlignment(SWT.CENTER);
		lbHoTen.setBounds(510, 90, 370, 70);

		CLabel lbTinhTrang = new CLabel(compositetop, SWT.BORDER | SWT.SHADOW_OUT);
		lbTinhTrang.setText("Tình trạng");
		lbTinhTrang.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 30, SWT.BOLD));
		lbTinhTrang.setBackground(SWTResourceManager.getColor(0, 0, 255));
		lbTinhTrang.setAlignment(SWT.CENTER);
		lbTinhTrang.setBounds(880, 90, 500, 70);

		CLabel lbKetQua = new CLabel(compositetop, SWT.BORDER | SWT.SHADOW_OUT);
		lbKetQua.setText("Kết quả");
		lbKetQua.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua.setFont(SWTResourceManager.getFont("Times New Roman", 30, SWT.BOLD));
		lbKetQua.setBackground(SWTResourceManager.getColor(0, 0, 255));
		lbKetQua.setAlignment(SWT.CENTER);
		lbKetQua.setBounds(1380, 90, 245, 70);

		CLabel lbNguoiXuLy = new CLabel(compositetop, SWT.BORDER | SWT.SHADOW_OUT);
		lbNguoiXuLy.setText("Người xử lý");
		lbNguoiXuLy.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy.setFont(SWTResourceManager.getFont("Times New Roman", 30, SWT.BOLD));
		lbNguoiXuLy.setBackground(SWTResourceManager.getColor(0, 0, 255));
		lbNguoiXuLy.setAlignment(SWT.CENTER);
		lbNguoiXuLy.setBounds(1625, 90, 294, 70);

		Composite compositebottom = new Composite(compositetop, SWT.NONE);
		compositebottom.setBounds(0, 160, 1920, 920);

		CLabel lbDonVi1 = new CLabel(compositebottom, SWT.NONE);
		lbDonVi1.setAlignment(SWT.CENTER);
		lbDonVi1.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbDonVi1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi1.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbDonVi1.setBounds(0, 1, 333, 130);

		CLabel lbSoThe1 = new CLabel(compositebottom, SWT.NONE);
		lbSoThe1.setAlignment(SWT.CENTER);
		lbSoThe1.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbSoThe1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe1.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbSoThe1.setBounds(335, 1, 173, 130);
		lbSoThe1.setText("");

		CLabel lbHoTen1 = new CLabel(compositebottom, SWT.NONE);
		lbHoTen1.setText("");
		lbHoTen1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen1.setFont(SWTResourceManager.getFont("Times New Roman", 24, SWT.NORMAL));
		lbHoTen1.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbHoTen1.setAlignment(SWT.CENTER);
		lbHoTen1.setBounds(511, 1, 367, 130);

		CLabel lbTinhTrang1 = new CLabel(compositebottom, SWT.NONE);
		lbTinhTrang1.setText("");
		lbTinhTrang1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang1.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbTinhTrang1.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbTinhTrang1.setAlignment(SWT.CENTER);
		lbTinhTrang1.setBounds(881, 1, 498, 130);

		CLabel lbKetQua1 = new CLabel(compositebottom, SWT.CENTER);
		lbKetQua1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua1.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbKetQua1.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbKetQua1.setBounds(1382, 1, 242, 130);

		CLabel lbNguoiXuLy1 = new CLabel(compositebottom, SWT.NONE);
		lbNguoiXuLy1.setText("");
		lbNguoiXuLy1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy1.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbNguoiXuLy1.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbNguoiXuLy1.setAlignment(SWT.CENTER);
		lbNguoiXuLy1.setBounds(1627, 1, 292, 130);

		CLabel lbDonVi2 = new CLabel(compositebottom, SWT.WRAP);
		lbDonVi2.setAlignment(SWT.CENTER);
		lbDonVi2.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi2.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbDonVi2.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbDonVi2.setBounds(0, 134, 333, 130);

		CLabel lbSoThe2 = new CLabel(compositebottom, SWT.NONE);
		lbSoThe2.setText("");
		lbSoThe2.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe2.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbSoThe2.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbSoThe2.setAlignment(SWT.CENTER);
		lbSoThe2.setBounds(335, 134, 173, 130);

		CLabel lbHoTen2 = new CLabel(compositebottom, SWT.NONE);
		lbHoTen2.setText("");
		lbHoTen2.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen2.setFont(SWTResourceManager.getFont("Times New Roman", 24, SWT.NORMAL));
		lbHoTen2.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbHoTen2.setAlignment(SWT.CENTER);
		lbHoTen2.setBounds(511, 134, 367, 130);

		CLabel lbTinhTrang2 = new CLabel(compositebottom, SWT.NONE);
		lbTinhTrang2.setText("");
		lbTinhTrang2.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang2.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbTinhTrang2.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbTinhTrang2.setAlignment(SWT.CENTER);
		lbTinhTrang2.setBounds(881, 134, 498, 130);

		CLabel lbKetQua2 = new CLabel(compositebottom, SWT.CENTER);
		lbKetQua2.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua2.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbKetQua2.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbKetQua2.setBounds(1382, 134, 242, 130);

		CLabel lbNguoiXuLy2 = new CLabel(compositebottom, SWT.NONE);
		lbNguoiXuLy2.setText("");
		lbNguoiXuLy2.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy2.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbNguoiXuLy2.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbNguoiXuLy2.setAlignment(SWT.CENTER);
		lbNguoiXuLy2.setBounds(1627, 134, 292, 130);

		CLabel lbDonVi3 = new CLabel(compositebottom, SWT.WRAP);
		lbDonVi3.setAlignment(SWT.CENTER);
		lbDonVi3.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi3.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbDonVi3.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbDonVi3.setBounds(0, 267, 333, 130);

		CLabel lbSoThe3 = new CLabel(compositebottom, SWT.NONE);
		lbSoThe3.setText("");
		lbSoThe3.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe3.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbSoThe3.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbSoThe3.setAlignment(SWT.CENTER);
		lbSoThe3.setBounds(335, 267, 173, 130);

		CLabel lbHoTen3 = new CLabel(compositebottom, SWT.NONE);
		lbHoTen3.setText("");
		lbHoTen3.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen3.setFont(SWTResourceManager.getFont("Times New Roman", 24, SWT.NORMAL));
		lbHoTen3.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbHoTen3.setAlignment(SWT.CENTER);
		lbHoTen3.setBounds(511, 267, 367, 130);

		CLabel lbTinhTrang3 = new CLabel(compositebottom, SWT.NONE);
		lbTinhTrang3.setText("");
		lbTinhTrang3.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang3.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbTinhTrang3.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbTinhTrang3.setAlignment(SWT.CENTER);
		lbTinhTrang3.setBounds(881, 267, 498, 130);

		CLabel lbKetQua3 = new CLabel(compositebottom, SWT.CENTER);
		lbKetQua3.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua3.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbKetQua3.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbKetQua3.setBounds(1382, 267, 242, 130);

		CLabel lbNguoiXuLy3 = new CLabel(compositebottom, SWT.NONE);
		lbNguoiXuLy3.setText("");
		lbNguoiXuLy3.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy3.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbNguoiXuLy3.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbNguoiXuLy3.setAlignment(SWT.CENTER);
		lbNguoiXuLy3.setBounds(1627, 267, 292, 130);

		CLabel lbNguoiXuLy4 = new CLabel(compositebottom, SWT.NONE);
		lbNguoiXuLy4.setText("");
		lbNguoiXuLy4.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy4.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbNguoiXuLy4.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbNguoiXuLy4.setAlignment(SWT.CENTER);
		lbNguoiXuLy4.setBounds(1627, 400, 292, 130);

		CLabel lbKetQua4 = new CLabel(compositebottom, SWT.CENTER);
		lbKetQua4.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua4.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbKetQua4.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbKetQua4.setBounds(1382, 400, 242, 130);

		CLabel lbTinhTrang4 = new CLabel(compositebottom, SWT.NONE);
		lbTinhTrang4.setText("");
		lbTinhTrang4.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang4.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbTinhTrang4.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbTinhTrang4.setAlignment(SWT.CENTER);
		lbTinhTrang4.setBounds(881, 400, 498, 130);

		CLabel lbHoTen4 = new CLabel(compositebottom, SWT.NONE);
		lbHoTen4.setText("");
		lbHoTen4.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen4.setFont(SWTResourceManager.getFont("Times New Roman", 24, SWT.NORMAL));
		lbHoTen4.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbHoTen4.setAlignment(SWT.CENTER);
		lbHoTen4.setBounds(511, 400, 367, 130);

		CLabel lbSoThe4 = new CLabel(compositebottom, SWT.NONE);
		lbSoThe4.setText("");
		lbSoThe4.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe4.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbSoThe4.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbSoThe4.setAlignment(SWT.CENTER);
		lbSoThe4.setBounds(335, 400, 173, 130);

		CLabel lbDonVi4 = new CLabel(compositebottom, SWT.WRAP);
		lbDonVi4.setAlignment(SWT.CENTER);
		lbDonVi4.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi4.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbDonVi4.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbDonVi4.setBounds(0, 400, 333, 130);

		CLabel lbDonVi5 = new CLabel(compositebottom, SWT.WRAP);
		lbDonVi5.setAlignment(SWT.CENTER);
		lbDonVi5.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi5.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbDonVi5.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbDonVi5.setBounds(0, 533, 333, 130);

		CLabel lbSoThe5 = new CLabel(compositebottom, SWT.NONE);
		lbSoThe5.setText("");
		lbSoThe5.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe5.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbSoThe5.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbSoThe5.setAlignment(SWT.CENTER);
		lbSoThe5.setBounds(335, 533, 173, 130);

		CLabel lbHoTen5 = new CLabel(compositebottom, SWT.NONE);
		lbHoTen5.setText("");
		lbHoTen5.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen5.setFont(SWTResourceManager.getFont("Times New Roman", 24, SWT.NORMAL));
		lbHoTen5.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbHoTen5.setAlignment(SWT.CENTER);
		lbHoTen5.setBounds(511, 533, 367, 130);

		CLabel lbTinhTrang5 = new CLabel(compositebottom, SWT.NONE);
		lbTinhTrang5.setText("");
		lbTinhTrang5.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang5.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbTinhTrang5.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbTinhTrang5.setAlignment(SWT.CENTER);
		lbTinhTrang5.setBounds(881, 533, 498, 130);

		CLabel lbKetQua5 = new CLabel(compositebottom, SWT.CENTER);
		lbKetQua5.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua5.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbKetQua5.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbKetQua5.setBounds(1382, 533, 242, 130);

		CLabel lbNguoiXuLy5 = new CLabel(compositebottom, SWT.NONE);
		lbNguoiXuLy5.setText("");
		lbNguoiXuLy5.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy5.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbNguoiXuLy5.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbNguoiXuLy5.setAlignment(SWT.CENTER);
		lbNguoiXuLy5.setBounds(1627, 533, 292, 130);

		CLabel lbNguoiXuLy6 = new CLabel(compositebottom, SWT.NONE);
		lbNguoiXuLy6.setText("");
		lbNguoiXuLy6.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy6.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbNguoiXuLy6.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbNguoiXuLy6.setAlignment(SWT.CENTER);
		lbNguoiXuLy6.setBounds(1627, 666, 292, 130);

		CLabel lbKetQua6 = new CLabel(compositebottom, SWT.CENTER);
		lbKetQua6.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua6.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbKetQua6.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbKetQua6.setBounds(1382, 666, 242, 130);

		CLabel lbTinhTrang6 = new CLabel(compositebottom, SWT.NONE);
		lbTinhTrang6.setText("");
		lbTinhTrang6.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang6.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbTinhTrang6.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbTinhTrang6.setAlignment(SWT.CENTER);
		lbTinhTrang6.setBounds(881, 666, 498, 130);

		CLabel lbHoTen6 = new CLabel(compositebottom, SWT.NONE);
		lbHoTen6.setText("");
		lbHoTen6.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen6.setFont(SWTResourceManager.getFont("Times New Roman", 24, SWT.NORMAL));
		lbHoTen6.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbHoTen6.setAlignment(SWT.CENTER);
		lbHoTen6.setBounds(511, 666, 367, 130);

		CLabel lbSoThe6 = new CLabel(compositebottom, SWT.NONE);
		lbSoThe6.setText("");
		lbSoThe6.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe6.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbSoThe6.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbSoThe6.setAlignment(SWT.CENTER);
		lbSoThe6.setBounds(335, 666, 173, 130);

		CLabel lbDonVi6 = new CLabel(compositebottom, SWT.WRAP);
		lbDonVi6.setAlignment(SWT.CENTER);
		lbDonVi6.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi6.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbDonVi6.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbDonVi6.setBounds(0, 666, 333, 130);

		CLabel lbDonVi7 = new CLabel(compositebottom, SWT.WRAP);
		lbDonVi7.setAlignment(SWT.CENTER);
		lbDonVi7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbDonVi7.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbDonVi7.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbDonVi7.setBounds(0, 799, 333, 130);

		CLabel lbSoThe7 = new CLabel(compositebottom, SWT.NONE);
		lbSoThe7.setText("");
		lbSoThe7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbSoThe7.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbSoThe7.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbSoThe7.setAlignment(SWT.CENTER);
		lbSoThe7.setBounds(335, 799, 173, 130);

		CLabel lbHoTen7 = new CLabel(compositebottom, SWT.NONE);
		lbHoTen7.setText("");
		lbHoTen7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbHoTen7.setFont(SWTResourceManager.getFont("Times New Roman", 24, SWT.NORMAL));
		lbHoTen7.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbHoTen7.setAlignment(SWT.CENTER);
		lbHoTen7.setBounds(511, 799, 367, 130);

		CLabel lbTinhTrang7 = new CLabel(compositebottom, SWT.NONE);
		lbTinhTrang7.setText("");
		lbTinhTrang7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbTinhTrang7.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbTinhTrang7.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbTinhTrang7.setAlignment(SWT.CENTER);
		lbTinhTrang7.setBounds(881, 799, 498, 130);

		CLabel lbKetQua7 = new CLabel(compositebottom, SWT.CENTER);
		lbKetQua7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbKetQua7.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbKetQua7.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbKetQua7.setBounds(1382, 799, 242, 130);

		CLabel lbNguoiXuLy7 = new CLabel(compositebottom, SWT.NONE);
		lbNguoiXuLy7.setText("");
		lbNguoiXuLy7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lbNguoiXuLy7.setFont(SWTResourceManager.getFont("Times New Roman", 27, SWT.NORMAL));
		lbNguoiXuLy7.setBackground(SWTResourceManager.getColor(95, 158, 160));
		lbNguoiXuLy7.setAlignment(SWT.CENTER);
		lbNguoiXuLy7.setBounds(1627, 799, 292, 130);

		arraylabelDonVi.add(lbDonVi1);
		arraylabelDonVi.add(lbDonVi2);
		arraylabelDonVi.add(lbDonVi3);
		arraylabelDonVi.add(lbDonVi4);
		arraylabelDonVi.add(lbDonVi5);
		arraylabelDonVi.add(lbDonVi6);
		arraylabelDonVi.add(lbDonVi7);

		arraylabelSoThe.add(lbSoThe1);
		arraylabelSoThe.add(lbSoThe2);
		arraylabelSoThe.add(lbSoThe3);
		arraylabelSoThe.add(lbSoThe4);
		arraylabelSoThe.add(lbSoThe5);
		arraylabelSoThe.add(lbSoThe6);
		arraylabelSoThe.add(lbSoThe7);

		arraylabelHoTen.add(lbHoTen1);
		arraylabelHoTen.add(lbHoTen2);
		arraylabelHoTen.add(lbHoTen3);
		arraylabelHoTen.add(lbHoTen4);
		arraylabelHoTen.add(lbHoTen5);
		arraylabelHoTen.add(lbHoTen6);
		arraylabelHoTen.add(lbHoTen7);

		arraylabelTinhTrang.add(lbTinhTrang1);
		arraylabelTinhTrang.add(lbTinhTrang2);
		arraylabelTinhTrang.add(lbTinhTrang3);
		arraylabelTinhTrang.add(lbTinhTrang4);
		arraylabelTinhTrang.add(lbTinhTrang5);
		arraylabelTinhTrang.add(lbTinhTrang6);
		arraylabelTinhTrang.add(lbTinhTrang7);

		arraylabelKetQua.add(lbKetQua1);
		arraylabelKetQua.add(lbKetQua2);
		arraylabelKetQua.add(lbKetQua3);
		arraylabelKetQua.add(lbKetQua4);
		arraylabelKetQua.add(lbKetQua5);
		arraylabelKetQua.add(lbKetQua6);
		arraylabelKetQua.add(lbKetQua7);

		arraylabelNguoiXuLy.add(lbNguoiXuLy1);
		arraylabelNguoiXuLy.add(lbNguoiXuLy2);
		arraylabelNguoiXuLy.add(lbNguoiXuLy3);
		arraylabelNguoiXuLy.add(lbNguoiXuLy4);
		arraylabelNguoiXuLy.add(lbNguoiXuLy5);
		arraylabelNguoiXuLy.add(lbNguoiXuLy6);
		arraylabelNguoiXuLy.add(lbNguoiXuLy7);

		// xoa thong tin cu
		deleteDataOld();
		// lay du lieu thong bao lan dau
		getData();

		// Xử lý thông báo su dung Timer
		// Sau 20 giay thi doi du lieu mot lan
		Runnable timer = new Runnable() {

			@Override
			public void run() {
				getData();

				Display.getDefault().timerExec(thoigiantimer, this);
			}
		};
		Display.getDefault().timerExec(thoigiantimer, timer);

		// Sự kiện nhấn phím ESC
		// **********************************************************************************************************************
		compositetop.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 27) {
					shell.dispose();
				}
			}
		});

		compositebottom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 27) {
					shell.dispose();
				}
			}
		});

	}

	public void getData() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();

			for (int i = 0; i < arraylabelDonVi.size(); i++) {
				arraylabelDonVi.get(i).setText("");
				arraylabelDonVi.get(i).setBackground(SWTResourceManager.getColor(95, 158, 160));
				arraylabelSoThe.get(i).setText("");
				arraylabelSoThe.get(i).setBackground(SWTResourceManager.getColor(95, 158, 160));
				arraylabelHoTen.get(i).setText("");
				arraylabelHoTen.get(i).setBackground(SWTResourceManager.getColor(95, 158, 160));
				arraylabelTinhTrang.get(i).setText("");
				arraylabelTinhTrang.get(i).setBackground(SWTResourceManager.getColor(95, 158, 160));
				arraylabelKetQua.get(i).setText("");
				arraylabelKetQua.get(i).setBackground(SWTResourceManager.getColor(95, 158, 160));
				arraylabelNguoiXuLy.get(i).setText("");
				arraylabelNguoiXuLy.get(i).setBackground(SWTResourceManager.getColor(95, 158, 160));
			}

			String select = "SELECT TOP 7 [IT_SlideShowPhanCongXuLyCongViec].[MaCongViec],[IT_PhanCongXuLyCongViec2].[DonVi],[IT_PhanCongXuLyCongViec2].[SoThe],[IT_PhanCongXuLyCongViec2].[HoTen],[IT_PhanCongXuLyCongViec2].[TinhTrang],[IT_PhanCongXuLyCongViec2].[KetQua],[IT_PhanCongXuLyCongViec2].[NguoiXuLy] FROM [dbo].[IT_SlideShowPhanCongXuLyCongViec],[dbo].[IT_PhanCongXuLyCongViec2] WHERE [IT_SlideShowPhanCongXuLyCongViec].[MaCongViec]=[IT_PhanCongXuLyCongViec2].[MaCongViec] ORDER BY [ThoiGianCapNhat] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			String capnhat = "";
			int i = 0;
			while (result.next()) {
				try {
					// cap nhat du lieu
					capnhat = capnhat
							+ "UPDATE [dbo].[IT_SlideShowPhanCongXuLyCongViec] SET [ThoiGianCapNhat] = GETDATE() WHERE [MaCongViec]='"
							+ result.getString(1) + "'" + "\n";

					arraylabelDonVi.get(i).setText(result.getString(2));
					arraylabelSoThe.get(i).setText(result.getString(3));
					arraylabelHoTen.get(i).setText(result.getString(4));
					arraylabelTinhTrang.get(i).setText(result.getString(5));
					arraylabelKetQua.get(i).setText(result.getString(6));
					arraylabelNguoiXuLy.get(i).setText(result.getString(7));
					i++;
				} catch (Exception ne) {
				}
			}
			result.close();
			connect.closeStatement();
			connect.execUpdateQuery(capnhat);
			connect.closeStatement();

		} catch (Exception se) {
		}
	}

	// delete data older
	public void deleteDataOld() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			// String delete = "DELETE [dbo].[IT_SlideShowPhanCongXuLyCongViec] WHERE
			// CONVERT(DATE,[ThoiGianCapNhat])<CONVERT(DATE,GETDATE())";
			String delete = "DELETE FROM [dbo].[IT_SlideShowPhanCongXuLyCongViec] WHERE [IT_SlideShowPhanCongXuLyCongViec].[MaCongViec] IN (SELECT [IT_SlideShowPhanCongXuLyCongViec].[MaCongViec] FROM [IT_PhanCongXuLyCongViec2],[IT_SlideShowPhanCongXuLyCongViec] WHERE [IT_SlideShowPhanCongXuLyCongViec].[MaCongViec]=[IT_PhanCongXuLyCongViec2].[MaCongViec] AND CONVERT(DATE,[IT_PhanCongXuLyCongViec2].[Ngay])<CONVERT(DATE,GETDATE()))";
			connect.execUpdateQuery(delete);
			connect.closeStatement();
		} catch (Exception se) {
		}
	}
}
