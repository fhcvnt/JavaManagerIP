package broken;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import sort.SortData;
import sql.ConnectSQL;

public class BaoPhe {
	private Table table;
	private TableItem[] itemtable;
	private String[] idxoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private int ngonngu = 1;
	private DateTime dateTimeNgayBaoPhe1;
	private DateTime dateTimeNgayBaoPhe2;
	private String userlogin = "";
	private String grouplogin = ""; // nếu là User thì không cho xóa
	private String filename = "";

	public static void main(String[] args) {
		try {
			BaoPhe window = new BaoPhe();
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
		shell.setImage(SWTResourceManager.getImage(BaoPhe.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Báo phế");
		} else {
			shell.setText("Destruction"); // sự phá hủy
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

		CLabel lbNgayBaoPhe = new CLabel(composite, SWT.RIGHT);
		lbNgayBaoPhe.setText("Ngày báo phế");
		lbNgayBaoPhe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayBaoPhe.setBounds(10, 25, 159, 30);

		dateTimeNgayBaoPhe1 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayBaoPhe1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayBaoPhe1.setBounds(175, 25, 129, 30);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-30);
		dateTimeNgayBaoPhe1.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		CLabel lbdaunga1 = new CLabel(composite, SWT.RIGHT);
		lbdaunga1.setAlignment(SWT.CENTER);
		lbdaunga1.setText("~");
		lbdaunga1.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdaunga1.setBounds(310, 25, 26, 30);

		dateTimeNgayBaoPhe2 = new DateTime(composite, SWT.BORDER);
		dateTimeNgayBaoPhe2.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayBaoPhe2.setBounds(342, 25, 129, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(BaoPhe.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(477, 25, 149, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(BaoPhe.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(632, 25, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(BaoPhe.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(744, 25, 113, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(BaoPhe.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(863, 25, 94, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(BaoPhe.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(963, 25, 103, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 70);
		table.setSize(1900, 893);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 180);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(102, 205, 170));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(50);
		tbcSTT.setText("STT");

		TableColumn tbcMaBaoPhe = new TableColumn(table, SWT.NONE);
		tbcMaBaoPhe.setWidth(155);
		tbcMaBaoPhe.setText("Mã báo phế");

		TableColumn tbcNgayBaoPhe = new TableColumn(table, SWT.NONE);
		tbcNgayBaoPhe.setWidth(186);
		tbcNgayBaoPhe.setText("Ngày báo phế");

		TableColumn tbcTenTep = new TableColumn(table, SWT.NONE);
		tbcTenTep.setWidth(312);
		tbcTenTep.setText("Tên tệp");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(262);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNguoiCapNhat = new TableColumn(table, SWT.NONE);
		tbcNguoiCapNhat.setWidth(225);
		tbcNguoiCapNhat.setText("Người cập nhật");
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbNgayBaoPhe.setText("Ngày báo phế");
			btnthem.setText("Thêm");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			btntimkiem.setText("Tìm kiếm");
			tbcSTT.setText("STT");
			tbcMaBaoPhe.setText("Mã báo phế");
			tbcNgayBaoPhe.setText("Ngày báo phế");
			tbcTenTep.setText("Tên tệp");
			tbcGhiChu.setText("Ghi chú");
			tbcNguoiCapNhat.setText("Người cập nhật");

		} else {
			// Tieng Anh
			lbNgayBaoPhe.setText("Date of destruction");
			btnthem.setText("Add");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			btntimkiem.setText("Search");
			tbcSTT.setText("Num");
			tbcMaBaoPhe.setText("ID");
			tbcNgayBaoPhe.setText("Date of destruction");
			tbcTenTep.setText("File name");
			tbcGhiChu.setText("Note");
			tbcNguoiCapNhat.setText("Person update");

		}

		// nếu là User thì không cho xóa
		if (grouplogin.equals("User")) {
			btnxoa.setVisible(false);
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

		// Thêm-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BaoPheAdd baophe = new BaoPheAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				baophe.setData(ngonngu, userlogin);
				baophe.open();
				composite.setEnabled(true);
				if (baophe.isAddSuccess()) {
					search();
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
					idxoa = new String[itemtable.length];

					for (int i = 0; i < idxoa.length; i++) {
						idxoa[i] = itemtable[i].getText(1);
					}
					if (idxoa.length > 0) {
						btnthem.setEnabled(false);
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
						for (int i = 0; i < idxoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[BP_BaoPhe] WHERE [ID]='" + idxoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							search();
						}
						connect.closeStatement();
						btnthem.setEnabled(true);
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
				}
			}
		});

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnthem.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
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
					dlg.setFileName(item[0].getText(3));
					filename = dlg.open();

					String select = "SELECT [FileDinhKem] FROM [dbo].[BP_BaoPhe] WHERE [ID]='" + item[0].getText(1)
							+ "'";
					ResultSet ketqua = connect.getStatement().executeQuery(select);
					boolean savesuccess = false;
					while (ketqua.next()) {
						FileOutputStream outputStream = new FileOutputStream(filename);
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

		tbcMaBaoPhe.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNgayBaoPhe.addListener(SWT.Selection, sort.sortListenerDate);
		tbcTenTep.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
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

			String monthbaophe = "0" + (dateTimeNgayBaoPhe1.getMonth() + 1);
			monthbaophe = monthbaophe.substring(monthbaophe.length() - 2);
			String daybaophe = "0" + dateTimeNgayBaoPhe1.getDay();
			daybaophe = daybaophe.substring(daybaophe.length() - 2);
			String ngaybatdau = dateTimeNgayBaoPhe1.getYear() + monthbaophe + daybaophe;

			String monthbaophe2 = "0" + (dateTimeNgayBaoPhe2.getMonth() + 1);
			monthbaophe2 = monthbaophe2.substring(monthbaophe2.length() - 2);
			String daybaophe2 = "0" + dateTimeNgayBaoPhe2.getDay();
			daybaophe2 = daybaophe2.substring(daybaophe2.length() - 2);
			String ngayketthuc = dateTimeNgayBaoPhe2.getYear() + monthbaophe2 + daybaophe2;

			String ngaymua = " AND [BP_BaoPhe].[NgayBaoPhe] BETWEEN '" + ngaybatdau + "' AND '" + ngayketthuc + "'";

			String select = "SELECT [BP_BaoPhe].[ID],[BP_BaoPhe].[NgayBaoPhe],[BP_BaoPhe].[TenFile],[BP_BaoPhe].[GhiChu],[NguoiDung].[TenNguoiDung] FROM [dbo].[BP_BaoPhe] LEFT JOIN [dbo].[NguoiDung] ON [BP_BaoPhe].[UserUpdate]=[NguoiDung].[MaNguoiDung] OR [BP_BaoPhe].[UserUpdate]=[NguoiDung].[TenDangNhap] WHERE 1=1"
					+ ngaymua + " ORDER BY [BP_BaoPhe].[NgayBaoPhe] DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {

				// định dạng lại ngày báo phế thành dạng dd/MM/yyyy
				String ngaydata = result.getString(2);
				try {
					ngaydata = ngaydata.substring(8, 10) + "/" + ngaydata.substring(5, 7) + "/"
							+ ngaydata.substring(0, 4);
				} catch (IndexOutOfBoundsException ie) {
					ngaydata = "";
				}

				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), ngaydata, result.getString(3),
						result.getString(4), result.getString(5) });
				stt++;
			}

			result.close();
			state.close();
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
			itemtab.setText("Báo phế");
		} else {
			itemtab.setText("Destruction");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);
		createContents(composite, shell);
	}
}
