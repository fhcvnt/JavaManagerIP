package itmanagerip;

import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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

public class PDNInternet {
	private ConnectSQL connect;
	private Table table;
	private TableItem[] itemtable;
	private String[] sothexoa;
	private String userlogin = ""; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	protected Shell shell;
	private Composite compositeshell;
	private Text textsothe;
	private Text texthoten;
	private Text textdonvi;
	private Text textIP;
	private Text textWebsite;
	private int ngonngu = 1;

	public static void main(String[] args) {
		try {
			PDNInternet window = new PDNInternet();
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
		createContents(compositeshell, shell,ngonngu);
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
		shell.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Internet");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell,int ngonngu) {
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
		lbhoten.setBounds(184, 13, 78, 30);
		lbhoten.setText("Họ tên");
		lbhoten.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		texthoten = new Text(composite, SWT.BORDER);
		texthoten.setBounds(268, 13, 190, 30);
		texthoten.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		texthoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbdonvi = new CLabel(composite, SWT.RIGHT);
		lbdonvi.setBounds(464, 13, 113, 30);
		lbdonvi.setText("Đơn vị");
		lbdonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textdonvi = new Text(composite, SWT.BORDER);
		textdonvi.setBounds(588, 13, 214, 30);
		textdonvi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textdonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(808, 11, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(920, 11, 92, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(1018, 11, 113, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1137, 11, 94, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1237, 11, 103, 32);
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

		CLabel lbWebsite = new CLabel(composite, SWT.RIGHT);
		lbWebsite.setText("Trang Web");
		lbWebsite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbWebsite.setBounds(281, 51, 133, 30);

		textWebsite = new Text(composite, SWT.BORDER);
		textWebsite.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textWebsite.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textWebsite.setBounds(425, 51, 619, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1051, 51, 149, 32);

		Button btnExcel = new Button(composite, SWT.NONE);
		btnExcel.setText("Excel");
		btnExcel.setImage(SWTResourceManager.getImage(PDNInternet.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnExcel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExcel.setBounds(1206, 51, 106, 32);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 87);
		table.setSize(1883, 883);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 152);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(83);
		tbcSTT.setText("STT");

		TableColumn tbcSothe = new TableColumn(table, SWT.NONE);
		tbcSothe.setWidth(102);
		tbcSothe.setText("Số thẻ");

		TableColumn tbcHoten = new TableColumn(table, SWT.NONE);
		tbcHoten.setWidth(196);
		tbcHoten.setText("Họ tên");

		TableColumn tbcDonvi = new TableColumn(table, SWT.NONE);
		tbcDonvi.setWidth(146);
		tbcDonvi.setText("Đơn vị");

		TableColumn tbcIP = new TableColumn(table, SWT.NONE);
		tbcIP.setWidth(131);
		tbcIP.setText("IP");

		TableColumn tbcWebsite = new TableColumn(table, SWT.NONE);
		tbcWebsite.setWidth(413);
		tbcWebsite.setText("Trang Web");

		TableColumn tbcNgaydenghi = new TableColumn(table, SWT.NONE);
		tbcNgaydenghi.setWidth(148);
		tbcNgaydenghi.setText("Ngày đề nghị");

		TableColumn tbcNgaycapnhat = new TableColumn(table, SWT.NONE);
		tbcNgaycapnhat.setWidth(125);
		tbcNgaycapnhat.setText("Ngày cập nhật");

		TableColumn tbcPersonupdate = new TableColumn(table, SWT.NONE);
		tbcPersonupdate.setWidth(162);
		tbcPersonupdate.setText("Người cập nhật");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbsothe.setText("Số thẻ");
			lbhoten.setText("Họ tên");
			lbdonvi.setText("Đơn vị");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			lbIP.setText("IP");
			lbWebsite.setText("Trang Web");
			btntimkiem.setText("Tìm kiếm");
			tbcSTT.setText("STT");
			tbcSothe.setText("Số thẻ");
			tbcHoten.setText("Họ tên");
			tbcDonvi.setText("Đơn vị");
			tbcIP.setText("IP");
			tbcWebsite.setText("Trang Web");
			tbcNgaydenghi.setText("Ngày đề nghị");
			tbcNgaycapnhat.setText("Ngày cập nhật");
			tbcPersonupdate.setText("Người cập nhật");

		} else {
			lbsothe.setText("ID");
			lbhoten.setText("Name");
			lbdonvi.setText("Department");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			lbIP.setText("IP");
			lbWebsite.setText("Website");
			btntimkiem.setText("Search");
			tbcSTT.setText("Number");
			tbcSothe.setText("ID");
			tbcHoten.setText("Name");
			tbcDonvi.setText("Department");
			tbcIP.setText("IP");
			tbcWebsite.setText("Website");
			tbcNgaydenghi.setText("Recommended date");
			tbcNgaycapnhat.setText("Date update");
			tbcPersonupdate.setText("Person update");
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
				search(shell);
			}
		});

		// Xóa-------------------------------------------------------------------------------------------------------------------------------
		btnxoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						table.setEnabled(false);
					}

				} catch (Exception ex) {
					btnthem.setEnabled(true);
					btnsua.setEnabled(true);
					btnExcel.setEnabled(true);
					btntimkiem.setEnabled(true);
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
					table.setEnabled(true);
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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
		});

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnthem.setEnabled(true);
				btnsua.setEnabled(true);
				btnExcel.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					connect = new ConnectSQL();
					connect.setConnection();

					try {
						String delete = "";
						for (int i = 0; i < sothexoa.length; i++) {
							delete = delete + "\n" + "DELETE Internet WHERE SoThe='" + sothexoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						btnthem.setEnabled(true);
						btnsua.setEnabled(true);
						btnExcel.setEnabled(true);
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
				} finally {
					try {
						if (connect.getStatement() != null) {
							connect.closeStatement();
						}
						if (connect != null) {
							connect.closeConnection();
						}
					} catch (Exception se2) {
						// nothing we can do
					}
				}
			}
		});

		// Sửa-----------------------------------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String sothesua = "";
					sothesua = itemsua[0].getText(1);
					if (!sothesua.isEmpty()) {
						EditInternet editinternet = new EditInternet();
						// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
						composite.setEnabled(false);
						editinternet.setDataInternetDefault(itemsua[0].getText(1), itemsua[0].getText(2),
								itemsua[0].getText(3), itemsua[0].getText(4), itemsua[0].getText(5),
								itemsua[0].getText(6));
						editinternet.setLanguage(ngonngu, userlogin);
						editinternet.open();
						composite.setEnabled(true);

						// Cập nhật lại dữ liệu
						search(shell);
					}

				} catch (Exception ex) {
				}
			}
		});

		// Thêm--------------------------------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewInternet newinternet = new NewInternet();
				// mở cửa sổ mới thì cửa sổ củ không cho thao tác
				composite.setEnabled(false);
				newinternet.setLanguage(ngonngu, userlogin);
				newinternet.open();
				composite.setEnabled(true);
				// Cập nhật lại dữ liệu sau khi thêm, gọi lại tìm kiếm
				search(shell);
			}
		});

		// Excel
		// ********************************************************************************************************************************************************************
		btnExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Excel.exportExcelTable(table, shell, ngonngu, "Internet");
			}

		});

		// Sort data
		// *****************************************************************************
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcSothe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoten.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonvi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcIP.addListener(SWT.Selection, sort.sortListenerIP);
		tbcWebsite.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgaydenghi.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgaycapnhat.addListener(SWT.Selection, sort.sortListenerDate);
		tbcPersonupdate.addListener(SWT.Selection, sort.sortListenerCode);
	}

	// Hiện trong CTabFolder, size monitor 1920x1080
	// -------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String userlogin, int ngonngu) {
		this.userlogin=userlogin;
		this.ngonngu=ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Mạng");
		} else {
			itemtab.setText("Internet");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);

		createContents(composite, shell,ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 200);
	}

	// -----------------------------------------------------------------------------------
	public void search(Shell shell) {

		try {
			connect = new ConnectSQL();
			connect.setConnection();
			connect.setStatement();
			String select = "";
			ResultSet result;
			table.removeAll();

			// text find
			String sothe = textsothe.getText().isEmpty() ? ""
					: " AND Internet.SoThe LIKE '%" + textsothe.getText() + "%'";
			String hoten = texthoten.getText().isEmpty() ? ""
					: " AND (#DSIP.HoTen LIKE N'%" + texthoten.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](#DSIP.HoTen) LIKE '%" + texthoten.getText() + "%')";
			String donvi = textdonvi.getText().isEmpty() ? ""
					: " AND (#DSIP.DonVi LIKE N'%" + textdonvi.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](#DSIP.DonVi) LIKE '%" + textdonvi.getText() + "%')";
			String website = textWebsite.getText().isEmpty() ? ""
					: " AND Internet.Website LIKE N'%" + textWebsite.getText() + "%'";
			String ip = textIP.getText().isEmpty() ? "" : " AND #DSIP.IP LIKE '%" + textIP.getText() + "'";

			// xử lý tìm theo lớp IP, nếu textIP nhận vào là lớp IP ví dụ: 30 thay vì nhận
			// được là 30.1 thì ta tìm theo lớp IP chứ không tìm theo IP
			int lopip = -1;
			try {
				lopip = Integer.parseInt(textIP.getText());
			} catch (NumberFormatException ne) {

			}
			if (lopip >= 0) {
				ip = textIP.getText().isEmpty() ? "" : " AND #DSIP.IP LIKE '%" + "192.168." + lopip + "." + "%'";
			}

			// sử dụng bảng tạm và left join
			select = "SELECT DanhSachIP.SoThe,DanhSachIP.HoTen,Donvi.DonVi,DanhSachIP.IP INTO #DSIP FROM DanhSachIP,DonVi WHERE DanhSachIP.MaDonVi=DonVi.MaDonVi";
			select = select + "\n"
					+ "SELECT Internet.SoThe,#DSIP.HoTen,#DSIP.DonVi,#DSIP.IP,Internet.Website,Internet.NgayDeNghi,Internet.NgayCapNhat,NguoiDung.TenNguoiDung FROM Internet LEFT JOIN #DSIP ON Internet.SoThe = #DSIP.SoThe LEFT JOIN NguoiDung ON Internet.UserUpdate=NguoiDung.MaNguoiDung OR Internet.UserUpdate=NguoiDung.TenDangNhap WHERE 1=1"
					+ sothe + hoten + donvi + ip + website + " ORDER BY Internet.NgayCapNhat DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = connect.getStatement().executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), ConvertDate.convertDate(result.getString(6)),
						ConvertDate.convertDate(result.getString(7)), result.getString(8) });
				stt++;
			}

			result.close();

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
		} finally {
			try {
				if (connect.getStatement() != null) {
					connect.closeStatement();
				}
				if (connect != null) {
					connect.closeConnection();
				}
			} catch (Exception se2) {
				// nothing we can do
			}
		}
	}
}
