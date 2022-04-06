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

public class PDNSoftware {
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
	private Text textSoftware;
	private int ngonngu = 1;

	public static void main(String[] args) {
		try {
			PDNSoftware window = new PDNSoftware();
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
		shell.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Software");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
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
		btnthem.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(808, 11, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(920, 11, 92, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(1018, 11, 113, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1137, 11, 94, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/button/cancel.png"));
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

		CLabel lbSoftware = new CLabel(composite, SWT.RIGHT);
		lbSoftware.setText("Phần mềm");
		lbSoftware.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoftware.setBounds(281, 51, 133, 30);

		textSoftware = new Text(composite, SWT.BORDER);
		textSoftware.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoftware.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textSoftware.setBounds(425, 51, 619, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(1051, 51, 149, 32);

		Button btnExcel = new Button(composite, SWT.NONE);
		btnExcel.setText("Excel");
		btnExcel.setImage(SWTResourceManager.getImage(PDNSoftware.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnExcel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExcel.setBounds(1206, 51, 106, 32);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 87);
		table.setSize(1883, 883);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 152);
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

		TableColumn tbcSoftware = new TableColumn(table, SWT.NONE);
		tbcSoftware.setWidth(413);
		tbcSoftware.setText("Phần mềm");

		TableColumn tbcNgaydenghi = new TableColumn(table, SWT.NONE);
		tbcNgaydenghi.setWidth(148);
		tbcNgaydenghi.setText("Ngày đề nghị");

		TableColumn tbcNgaycapnhat = new TableColumn(table, SWT.NONE);
		tbcNgaycapnhat.setWidth(115);
		tbcNgaycapnhat.setText("Ngày cập nhật");

		TableColumn tbcPersonupdate = new TableColumn(table, SWT.NONE);
		tbcPersonupdate.setWidth(147);
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
			lbSoftware.setText("Phần mềm");
			btntimkiem.setText("Tìm kiếm");
			tbcSTT.setText("STT");
			tbcSothe.setText("Số thẻ");
			tbcHoten.setText("Họ tên");
			tbcDonvi.setText("Đơn vị");
			tbcIP.setText("IP");
			tbcSoftware.setText("Phần mềm");
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
			lbSoftware.setText("Software");
			btntimkiem.setText("Search");
			tbcSTT.setText("Number");
			tbcSothe.setText("ID");
			tbcHoten.setText("Name");
			tbcDonvi.setText("Department");
			tbcIP.setText("IP");
			tbcSoftware.setText("Software");
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
				search(shell,ngonngu);
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
							delete = delete + "\n" + "DELETE Software WHERE SoThe='" + sothexoa[i] + "'";
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
						EditSoftware editsoftware = new EditSoftware();
						// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
						composite.setEnabled(false);
						editsoftware.setDataSoftwareDefault(itemsua[0].getText(1), itemsua[0].getText(2),
								itemsua[0].getText(3), itemsua[0].getText(4), itemsua[0].getText(5),
								itemsua[0].getText(6));
						editsoftware.setLanguage(ngonngu, userlogin);
						editsoftware.open();
						composite.setEnabled(true);

						// Cập nhật lại dữ liệu
						search(shell,ngonngu);
					}

				} catch (Exception ex) {
				}
			}
		});

		// Thêm--------------------------------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewSoftware newsoftware = new NewSoftware();
				// mở cửa sổ mới thì cửa sổ củ không cho thao tác
				composite.setEnabled(false);
				newsoftware.setLanguage(ngonngu, userlogin);
				newsoftware.open();
				composite.setEnabled(true);
				// Cập nhật lại dữ liệu sau khi thêm, gọi lại tìm kiếm
				search(shell,ngonngu);
			}
		});

		// Excel
		// ********************************************************************************************************************************************************************
		btnExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Phần mềm");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "Software");
				}
			}

		});

		// Sort data
		// *********************************************************************************************************************************************************************
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcSothe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcHoten.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDonvi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcIP.addListener(SWT.Selection, sort.sortListenerIP);
		tbcSoftware.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgaydenghi.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgaycapnhat.addListener(SWT.Selection, sort.sortListenerDate);
		tbcPersonupdate.addListener(SWT.Selection, sort.sortListenerCode);

	}

	// Hiện trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String userlogin, int ngonngu) {
		this.userlogin=userlogin;
		this.ngonngu=ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Phần mềm");
		} else {
			itemtab.setText("Software");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);
		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 200);
	}

	// -----------------------------------------------------------------------------------
	public void search(Shell shell,int ngonngu) {
		try {
			connect = new ConnectSQL();
			connect.setConnection();
			connect.setStatement();
			String select = "";
			ResultSet result;
			table.removeAll();

			// text find
			String sothe = textsothe.getText().isEmpty() ? ""
					: " AND Software.SoThe LIKE '%" + textsothe.getText() + "%'";
			String hoten = texthoten.getText().isEmpty() ? ""
					: " AND (#DSIP.HoTen LIKE N'%" + texthoten.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](#DSIP.HoTen) LIKE '%" + texthoten.getText() + "%')";
			String donvi = textdonvi.getText().isEmpty() ? ""
					: " AND (#DSIP.DonVi LIKE N'%" + textdonvi.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](#DSIP.DonVi) LIKE '%" + textdonvi.getText() + "%')";
			String software = textSoftware.getText().isEmpty() ? ""
					: " AND (Software.SoftName LIKE N'%" + textSoftware.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](Software.SoftName) LIKE '%" + textSoftware.getText()
							+ "%')";
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
					+ "SELECT Software.SoThe,#DSIP.HoTen,#DSIP.DonVi,#DSIP.IP,Software.SoftName,Software.NgayDeNghi,Software.NgayCapNhat,NguoiDung.TenNguoiDung FROM Software LEFT JOIN #DSIP ON Software.SoThe = #DSIP.SoThe LEFT JOIN NguoiDung ON Software.UserUpdate=NguoiDung.MaNguoiDung OR Software.UserUpdate=NguoiDung.TenDangNhap WHERE 1=1"
					+ sothe + hoten + donvi + ip + software + " ORDER BY Software.NgayCapNhat DESC";

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
				thongbao.setText("Error");
				thongbao.setMessage("Connect error!\n" + se.toString());
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
