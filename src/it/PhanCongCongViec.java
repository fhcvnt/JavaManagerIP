package it;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class PhanCongCongViec {
	private Table table;
	private TableItem[] itemtable;
	private String[] macongviecxoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Text textSoThe;
	private Text textHoTen;
	private CCombo comboDonVi;
	private int ngonngu = 1;
	private Text textNguoiSua;
	private DateTime dateTimeNgay1;
	private DateTime dateTimeNgay2;
	private String userlogin = "21608";
	private String grouplogin = ""; // là nhóm User thì không cho xóa

	public static void main(String[] args) {
		try {
			PhanCongCongViec window = new PhanCongCongViec();
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
		shell.setText("Work assignment");
		if (ngonngu == 0) {
			shell.setText("Phân công công việc");
		} else {
			shell.setText("Work assignment");
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

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(10, 13, 72, 30);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setBounds(88, 13, 104, 30);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(198, 13, 135, 30);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setBounds(339, 13, 184, 30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(529, 13, 120, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setBounds(655, 13, 267, 30);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBackground(SWTResourceManager.getColor(224, 255, 255));

		CLabel lbNguoiSua = new CLabel(composite, SWT.RIGHT);
		lbNguoiSua.setText("Người sửa");
		lbNguoiSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiSua.setBounds(928, 13, 120, 30);

		textNguoiSua = new Text(composite, SWT.BORDER);
		textNguoiSua.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiSua.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguoiSua.setBounds(1054, 13, 180, 30);

		Button btnShow = new Button(composite, SWT.NONE);
		btnShow.setText("Show");
		btnShow.setImage(SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/show30.png"));
		btnShow.setForeground(SWTResourceManager.getColor(0, 0, 255));
		btnShow.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnShow.setBounds(1240, 13, 113, 32);

		CLabel lbTuNgay = new CLabel(composite, SWT.RIGHT);
		lbTuNgay.setText("Từ ngày");
		lbTuNgay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTuNgay.setBounds(10, 51, 114, 30);

		dateTimeNgay1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgay1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgay1.setBounds(130, 51, 129, 30);

		CLabel lbdaunga1 = new CLabel(composite, SWT.RIGHT);
		lbdaunga1.setAlignment(SWT.CENTER);
		lbdaunga1.setText("~");
		lbdaunga1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga1.setBounds(265, 51, 26, 30);

		dateTimeNgay2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgay2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgay2.setBounds(297, 51, 129, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(432, 51, 149, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(587, 51, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(699, 51, 108, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(813, 51, 113, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnXuatExcel = new Button(composite, SWT.NONE);
		btnXuatExcel.setText("Xuất");
		btnXuatExcel
				.setImage(SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/excel25.png"));
		btnXuatExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnXuatExcel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXuatExcel.setBounds(932, 51, 113, 32);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1054, 51, 109, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PhanCongCongViec.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1169, 51, 120, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 92);
		table.setSize(1900, 919);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 200);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(50);
		tbcSTT.setText("STT");
		tbcSTT.setResizable(false);

		TableColumn tbcMaCongViec = new TableColumn(table, SWT.NONE);
		tbcMaCongViec.setWidth(0);
		tbcMaCongViec.setText("Mã công việc");
		tbcMaCongViec.setResizable(false);

		TableColumn tbcNgay = new TableColumn(table, SWT.NONE);
		tbcNgay.setWidth(175);
		tbcNgay.setText("Ngày");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(146);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(76);
		tbcSoThe.setText("Số thẻ");

		TableColumn tbcHoTen = new TableColumn(table, SWT.NONE);
		tbcHoTen.setWidth(238);
		tbcHoTen.setText("Họ tên");

		TableColumn tbcTinhTrang = new TableColumn(table, SWT.NONE);
		tbcTinhTrang.setWidth(143);
		tbcTinhTrang.setText("Tình trạng");

		TableColumn tbcNguyenNhan = new TableColumn(table, SWT.NONE);
		tbcNguyenNhan.setWidth(157);
		tbcNguyenNhan.setText("Nguyên nhân");

		TableColumn tbcXuLy = new TableColumn(table, SWT.NONE);
		tbcXuLy.setWidth(136);
		tbcXuLy.setText("Xử lý");

		TableColumn tbcNgayHoanThanh = new TableColumn(table, SWT.NONE);
		tbcNgayHoanThanh.setWidth(189);
		tbcNgayHoanThanh.setText("Ngày hoàn thành");

		TableColumn tbcKetQua = new TableColumn(table, SWT.NONE);
		tbcKetQua.setWidth(127);
		tbcKetQua.setText("Kết quả");

		TableColumn tbcNguoiXuLy = new TableColumn(table, SWT.NONE);
		tbcNguoiXuLy.setWidth(195);
		tbcNguoiXuLy.setText("Người xử lý");

		TableColumn tbcNguoiPhanCong = new TableColumn(table, SWT.NONE);
		tbcNguoiPhanCong.setWidth(215);
		tbcNguoiPhanCong.setText("Người phân công");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbNguoiSua.setText("Người sửa");
			lbTuNgay.setText("Từ ngày");
			btntimkiem.setText("Tìm kiếm");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnXuatExcel.setText("Xuất");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

			tbcSTT.setText("STT");
			tbcMaCongViec.setText("Mã công việc");
			tbcNgay.setText("Ngày");
			tbcDonVi.setText("Đơn vị");
			tbcSoThe.setText("Số thẻ");
			tbcHoTen.setText("Họ tên");
			tbcTinhTrang.setText("Tình trạng");
			tbcNguyenNhan.setText("Nguyên nhân");
			tbcXuLy.setText("Xử lý");
			tbcNgayHoanThanh.setText("Ngày hoàn thành");
			tbcKetQua.setText("Kết quả");
			tbcNguoiXuLy.setText("Người xử lý");
			tbcNguoiPhanCong.setText("Người phân công");

		} else {
			// Tieng Anh
			lbSoThe.setText("ID");
			lbHoTen.setText("Person name");
			lbDonVi.setText("Department");
			lbNguoiSua.setText("Handler");
			lbTuNgay.setText("From date");
			btntimkiem.setText("Search");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnXuatExcel.setText("Export");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Num");
			tbcMaCongViec.setText("Word ID");
			tbcNgay.setText("Date");
			tbcDonVi.setText("Department");
			tbcSoThe.setText("ID");
			tbcHoTen.setText("Person name");
			tbcTinhTrang.setText("Status");
			tbcNguyenNhan.setText("Cause");
			tbcXuLy.setText("Resovle");
			tbcNgayHoanThanh.setText("Date finish");
			tbcKetQua.setText("Result");
			tbcNguoiXuLy.setText("Handler");
			tbcNguoiPhanCong.setText("Taskmaster");
		}

		// grouplogin là User thì không cho xóa, xuất Excel
		if (grouplogin.equals("User")) {
			btnxoa.setVisible(false);
			btnXuatExcel.setVisible(false);
		}

		// lấy dữ liệu cho combo đơn vị
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

		// Tìm kiếm sau khi enter ở text so the
		// ******************************************************************************
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text Ho ten
		// ******************************************************************************
		textHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở combo don vi
		// ******************************************************************************
		comboDonVi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text nguoi xu ly
		// ******************************************************************************
		textNguoiSua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// slide show
		btnShow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PhanCongCongViecSlideShow slideshow = new PhanCongCongViecSlideShow();
				slideshow.createContents();
				slideshow.open();
			}
		});

		// slide show sau khi enter ở button slide show
		// -----------------------------------------------------------------------------------------------------------
		btnShow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					PhanCongCongViecSlideShow slideshow = new PhanCongCongViecSlideShow();
					slideshow.createContents();
					slideshow.open();
				}
			}
		});

		// Thêm-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PhanCongCongViecAdd phancongmoi = new PhanCongCongViecAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				phancongmoi.setData(ngonngu, userlogin);
				phancongmoi.createContents();
				phancongmoi.open();
				composite.setEnabled(true);
				search();
			}
		});

		// Thêm sau khi enter ở button edit
		// -----------------------------------------------------------------------------------------------------------
		btnthem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					PhanCongCongViecAdd phancongmoi = new PhanCongCongViecAdd();
					// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
					composite.setEnabled(false);
					phancongmoi.setData(ngonngu, userlogin);
					phancongmoi.createContents();
					phancongmoi.open();
					composite.setEnabled(true);
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

		// Sửa sau khi enter ở button edit
		// Sửa---------------------------------------------------------------------------------------------------
		btnsua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					edit(composite);
				}
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
					macongviecxoa = new String[itemtable.length];

					for (int i = 0; i < macongviecxoa.length; i++) {
						macongviecxoa[i] = itemtable[i].getText(1);
					}
					if (macongviecxoa.length > 0) {
						btnthem.setEnabled(false);
						btnsua.setEnabled(false);
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
						for (int i = 0; i < macongviecxoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[IT_PhanCongXuLyCongViec2] WHERE [MaCongViec]='"
									+ macongviecxoa[i] + "'" + "\n"
									+ "DELETE FROM [dbo].[IT_SlideShowPhanCongXuLyCongViec] WHERE [MaCongViec]='"
									+ macongviecxoa[i] + "'" + "\n"
									+ "DELETE FROM [dbo].[IT_HienThiThongBao] WHERE [MaCongViec]='" + macongviecxoa[i]
									+ "'";

						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
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
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// Xuất
		// Excel-------------------------------------------------------------------------------------------------
		btnXuatExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Phân công công việc");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Work assignment");
				}
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcMaCongViec.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgay.addListener(SWT.Selection, sort.sortListenerDate);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcSoThe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoTen.addListener(SWT.Selection, sort.sortListenerCode);
		tbcTinhTrang.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguyenNhan.addListener(SWT.Selection, sort.sortListenerCode);
		tbcXuLy.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayHoanThanh.addListener(SWT.Selection, sort.sortListenerDate);
		tbcKetQua.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiXuLy.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNguoiPhanCong.addListener(SWT.Selection, sort.sortListenerCode);

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

			String donvi = "";
			donvi = comboDonVi.getSelectionIndex() == -1 || comboDonVi.getText().isEmpty() ? ""
					: " AND [DonVi]=N'" + comboDonVi.getText() + "'";
			String sothe = textSoThe.getText().isEmpty() ? "" : "  AND [SoThe]='" + textSoThe.getText() + "'";

			String month = "0" + (dateTimeNgay1.getMonth() + 1);
			month = month.substring(month.length() - 2);
			String day = "0" + dateTimeNgay1.getDay();
			day = day.substring(day.length() - 2);
			String ngaybatdau = dateTimeNgay1.getYear() + month + day;

			String month2 = "0" + (dateTimeNgay2.getMonth() + 1);
			month2 = month2.substring(month2.length() - 2);
			String day2 = "0" + dateTimeNgay2.getDay();
			day2 = day2.substring(day2.length() - 2);
			String ngayketthuc = dateTimeNgay2.getYear() + month2 + day2;

			String ngay = " AND [Ngay] BETWEEN '" + ngaybatdau + " 00:00:00.000' AND '" + ngayketthuc
					+ " 23:59:59.999'";

			String select = "SELECT [MaCongViec],[Ngay],[DonVi],[SoThe],[HoTen],[TinhTrang],[NguyenNhan],[XuLy],[NgayHoanThanh],[KetQua],[NguoiXuLy],[dbo].[NguoiDung].[TenNguoiDung] FROM [dbo].[IT_PhanCongXuLyCongViec2] LEFT JOIN [dbo].[NguoiDung] ON [dbo].[IT_PhanCongXuLyCongViec2].[NguoiPhanCong]=[dbo].[NguoiDung].[MaNguoiDung] WHERE 1=1"
					+ sothe + donvi + ngay + " AND ([HoTen] LIKE N'%" + textHoTen.getText()
					+ "%' OR [dbo].[convertUnicodetoASCII]([HoTen]) LIKE N'%" + textHoTen.getText()
					+ "%') AND ([NguoiXuLy] LIKE N'%" + textNguoiSua.getText()
					+ "%' OR [dbo].[convertUnicodetoASCII]([NguoiXuLy]) LIKE N'%" + textNguoiSua.getText()
					+ "%') ORDER BY [MaCongViec] ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1),
						ConvertDate.convertDateTime(result.getString(2)), result.getString(3), result.getString(4),
						result.getString(5), result.getString(6), result.getString(7), result.getString(8),
						ConvertDate.convertDateTime(result.getString(9)), result.getString(10), result.getString(11),
						result.getString(12) });
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

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	// edit
	// -------------------------------------------------------------------------------------------------------------------
	public void edit(Composite composite) {
		try {
			TableItem[] itemsua = table.getSelection();
			String macongviecsua = "";
			macongviecsua = itemsua[0].getText(1);
			if (!macongviecsua.isEmpty()) {
				PhanCongCongViecEdit suacongviec = new PhanCongCongViecEdit();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);

				suacongviec.setLanguage(ngonngu);
				suacongviec.createContents();
				suacongviec.setData(itemsua[0].getText(1), itemsua[0].getText(4), itemsua[0].getText(5),
						itemsua[0].getText(3), itemsua[0].getText(6), itemsua[0].getText(11), itemsua[0].getText(9),
						itemsua[0].getText(7), itemsua[0].getText(8), itemsua[0].getText(10));
				suacongviec.open();
				composite.setEnabled(true);
				search();
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
			itemtab.setText("Phân công công việc");
		} else {
			itemtab.setText("Work assignment");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);

		createContents(composite, shell);
	}
}
