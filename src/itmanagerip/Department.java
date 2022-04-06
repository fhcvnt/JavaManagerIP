package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;

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

import sort.SortData;
import sql.ConnectSQL;

public class Department {

	protected Shell shell;
	private ConnectSQL connect;
	private int ngonngu = 1;
	private Table tableleft;
	private Table tableright;
	private Composite compositeshell;
	private String grouplogin = ""; // là nhóm User, Translate thì không cho chỉnh sửa

	public static void main(String[] args) {
		try {
			Department window = new Department();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		shell.setImage(SWTResourceManager.getImage(Department.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1366, 1035);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Đơn vị");
		} else {
			shell.setText("Department");
		}

		compositeshell = new Composite(shell, SWT.NONE);
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		CLabel lbMadonvi = new CLabel(composite, SWT.RIGHT);
		lbMadonvi.setBounds(10, 10, 130, 30);
		lbMadonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMadonvi.setText("Mã Đơn Vị");

		Text textMadonvi = new Text(composite, SWT.BORDER | SWT.CENTER);
		textMadonvi.setBounds(146, 10, 145, 30);
		textMadonvi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textMadonvi.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textMadonvi.setLayoutData(new RowData(169, SWT.DEFAULT));
		textMadonvi.setTextLimit(20);

		CLabel lbTendonvi = new CLabel(composite, SWT.RIGHT);
		lbTendonvi.setBounds(298, 10, 126, 30);
		lbTendonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTendonvi.setText("Tên Đơn Vị");

		Text textTendonvi = new Text(composite, SWT.BORDER | SWT.CENTER);
		textTendonvi.setBounds(430, 10, 282, 30);
		textTendonvi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textTendonvi.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textTendonvi.setLayoutData(new RowData(224, SWT.DEFAULT));

		Button btnTimKiem = new Button(composite, SWT.CENTER);
		btnTimKiem.setImage(SWTResourceManager.getImage(Department.class, "/itmanagerip/Icon/button/search.png"));
		btnTimKiem.setBounds(731, 10, 155, 32);
		btnTimKiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTimKiem.setLayoutData(new RowData(91, SWT.DEFAULT));

		btnTimKiem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTimKiem.setText("Tìm kiếm");

		CLabel lbDonviHR = new CLabel(composite, SWT.CENTER);
		lbDonviHR.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lbDonviHR.setText("Đơn Vị của Nhân sự");
		lbDonviHR.setFont(SWTResourceManager.getFont("Times New Roman", 16, SWT.BOLD));
		lbDonviHR.setBounds(10, 56, 633, 35);

		CLabel lbDonviIP = new CLabel(composite, SWT.CENTER);
		lbDonviIP.setText("Đơn Vị của Quản lý IP");
		lbDonviIP.setForeground(SWTResourceManager.getColor(0, 128, 0));
		lbDonviIP.setFont(SWTResourceManager.getFont("Times New Roman", 16, SWT.BOLD));
		lbDonviIP.setBounds(690, 56, 633, 35);
		lbDonviIP.setLocation(Display.getDefault().getBounds().width / 2 + 30, 56);

		tableleft = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		tableleft.setBounds(10, 106, 633, 868);
		tableleft.setLinesVisible(true);
		tableleft.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		tableleft.setLayoutData(new RowData(1330, 580));
		tableleft.setHeaderVisible(true);
		tableleft.setSize(Display.getDefault().getBounds().width / 2 - 30,
				Display.getDefault().getBounds().height - 174);

		TableColumn tbcolSTT = new TableColumn(tableleft, SWT.NONE);
		tbcolSTT.setWidth(72);
		tbcolSTT.setText("STT");
		tableleft.setHeaderBackground(SWTResourceManager.getColor(255, 140, 0));

		TableColumn tbcolMadonvi = new TableColumn(tableleft, SWT.NONE);
		tbcolMadonvi.setWidth(168);
		tbcolMadonvi.setText("Mã Đơn Vị");

		TableColumn tbcolTendonvi = new TableColumn(tableleft, SWT.NONE);
		tbcolTendonvi.setWidth(330);
		tbcolTendonvi.setText("Tên Đơn Vị");

		Button btnright = new Button(composite, SWT.NONE);
		btnright.setImage(
				SWTResourceManager.getImage(Department.class, "/itmanagerip/Icon/button/man-green-arrow36.png"));
		btnright.setText("");
		btnright.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		btnright.setBounds(665, 259, 36, 55);
		btnright.setLocation(Display.getDefault().getBounds().width / 2 - 18, 259);

		Button btnleft = new Button(composite, SWT.NONE);
		btnleft.setImage(SWTResourceManager.getImage(Department.class, "/itmanagerip/Icon/button/man-redarrow36.png"));
		btnleft.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		btnleft.setBounds(665, 330, 36, 55);
		btnleft.setText("");
		btnleft.setLocation(Display.getDefault().getBounds().width / 2 - 18, 330);

		tableright = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		tableright.setHeaderBackground(SWTResourceManager.getColor(0, 255, 127));
		tableright.setLinesVisible(true);
		tableright.setHeaderVisible(true);
		tableright.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		tableright.setBounds(690, 106, 633, 868);
		tableright.setSize(Display.getDefault().getBounds().width / 2 - 30,
				Display.getDefault().getBounds().height - 174);
		tableright.setLocation(Display.getDefault().getBounds().width / 2 + 20, 106);

		TableColumn tbcrightSTT = new TableColumn(tableright, SWT.NONE);
		tbcrightSTT.setWidth(72);
		tbcrightSTT.setText("STT");

		TableColumn tbcrightMadonvi = new TableColumn(tableright, SWT.NONE);
		tbcrightMadonvi.setWidth(168);
		tbcrightMadonvi.setText("Mã Đơn Vị");

		TableColumn tbcrightTendonvi = new TableColumn(tableright, SWT.NONE);
		tbcrightTendonvi.setWidth(330);
		tbcrightTendonvi.setText("Tên Đơn Vị");

		// Set Language
		// --------------------------------------------------------------------------------
		if (ngonngu == 0) {
			lbMadonvi.setText("Mã đơn vị");
			lbTendonvi.setText("Tên đơn vị");
			tbcolSTT.setText("STT");
			tbcolMadonvi.setText("Mã đơn vị");
			tbcolTendonvi.setText("Tên đơn vị");
			btnTimKiem.setText("Tìm kiếm");
			lbDonviHR.setText("Đơn Vị của Nhân sự");
			lbDonviIP.setText("Đơn Vị của Quản lý IP");
			tbcrightSTT.setText("STT");
			tbcrightMadonvi.setText("Mã đơn vị");
			tbcrightTendonvi.setText("Tên đơn vị");
		} else {
			// English
			lbMadonvi.setText("Department id");
			lbTendonvi.setText("Department");
			tbcolSTT.setText("Number");
			tbcolMadonvi.setText("Department id");
			tbcolTendonvi.setText("Department");
			btnTimKiem.setText("Search");
			lbDonviHR.setText("Department of HR");
			lbDonviIP.setText("Department of Manager IP");
			tbcrightSTT.setText("Number");
			tbcrightMadonvi.setText("Department id");
			tbcrightTendonvi.setText("Department");
		}

		// là nhóm User thì không cho chỉnh sửa
		if (grouplogin.equals("User") || grouplogin.equals("Translate")) {
			btnright.setVisible(false);
			btnleft.setVisible(false);
		}

		// Event phím tắt (ctrl + A) chọn bản bên trái
		tableleft.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt (ctrl + A) chọn hết bảng
				if (e.keyCode == 'a' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					tableleft.selectAll();
				}
			}
		});

		// Event phím tắt (ctrl + A) chọn bảng bên phải
		tableright.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt (ctrl + A) chọn hết bảng
				if (e.keyCode == 'a' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					tableright.selectAll();
				}
			}
		});

		// Xử lý sự kiện Tìm Kiếm
		// ***********************************************************************
		btnTimKiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Tìm kiếm Đơn vị của HR
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					// Tên đơn vị bắt đầu bằng "Z" thì bỏ đi, bên Nhân sự không còn dùng nữa
					String select = "SELECT Department_Serial_Key,Department_Name FROM [SV4].[HRIS].[dbo].[Data_Department] WHERE Department_Serial_Key LIKE '%"
							+ textMadonvi.getText() + "%' AND Department_Name LIKE N'%" + textTendonvi.getText()
							+ "%' AND Department_Name NOT LIKE N'Z%" + textTendonvi.getText() + "%'";
					ResultSet result = connect.getStatement().executeQuery(select);
					tableleft.removeAll();

					int stt = 1;
					while (result.next()) {
						TableItem item = new TableItem(tableleft, SWT.NONE);
						item.setText(new String[] { stt + "", result.getString(1), result.getString(2) });
						stt++;
					}
					result.close();
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

				// Tìm kiếm đon vị của Quản lý IP
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();

					String select = "SELECT MaDonVi,DonVi FROM DonVi WHERE MaDonVi LIKE '%" + textMadonvi.getText()
							+ "%' AND (DonVi LIKE N'%" + textTendonvi.getText()
							+ "%' OR dbo.convertUnicodetoASCII(DonVi) LIKE '%" + textTendonvi.getText() + "%')";
					ResultSet result = connect.getStatement().executeQuery(select);
					tableright.removeAll();

					int stt = 1;
					while (result.next()) {
						TableItem item = new TableItem(tableright, SWT.NONE);
						item.setText(new String[] { stt + "", result.getString(1), result.getString(2) });
						stt++;
					}
					result.close();
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
		});

		// Sự kiện thêm dữ liệu từ bảng trái qua phải (Thêm dữ liệu cho Table right)
		btnright.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] item = tableleft.getSelection();
					String[] madonviinsert = new String[item.length];
					for (int i = 0; i < madonviinsert.length; i++) {
						madonviinsert[i] = item[i].getText(1);
					}

					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}

					String insert = "";
					for (int i = 0; i < madonviinsert.length; i++) {
						insert = insert + "INSERT INTO DonVi (MaDonVi,DonVi) VALUES ('" + item[i].getText(1) + "',N'"
								+ item[i].getText(2) + "')" + "\n";
					}
					if (connect.execUpdateQuery(insert) > 0) {
						try {
							if (connect == null) {
								connect = new ConnectSQL();
								connect.setConnection();
							}
							connect.setStatement();
							String select = "SELECT MaDonVi,DonVi FROM DonVi WHERE MaDonVi LIKE '%"
									+ textMadonvi.getText() + "%' AND (DonVi LIKE N'%" + textTendonvi.getText()
									+ "%' OR dbo.convertUnicodetoASCII(DonVi) LIKE '%" + textTendonvi.getText() + "%')";
							ResultSet result = connect.getStatement().executeQuery(select);
							tableright.removeAll();

							int stt = 1;
							while (result.next()) {
								TableItem itemadd = new TableItem(tableright, SWT.NONE);
								itemadd.setText(new String[] { stt + "", result.getString(1), result.getString(2) });
								stt++;
							}
							result.close();
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

				} catch (Exception ex) {
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}
						connect.setStatement();

						String select = "SELECT MaDonVi,DonVi FROM DonVi WHERE MaDonVi LIKE '%" + textMadonvi.getText()
								+ "%' AND (DonVi LIKE N'%" + textTendonvi.getText()
								+ "%' OR dbo.convertUnicodetoASCII(DonVi) LIKE '%" + textTendonvi.getText() + "%')";
						ResultSet result = connect.getStatement().executeQuery(select);
						tableright.removeAll();

						int stt = 1;
						while (result.next()) {
							TableItem itemadd = new TableItem(tableright, SWT.NONE);
							itemadd.setText(new String[] { stt + "", result.getString(1), result.getString(2) });
							stt++;
						}
						result.close();
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
			}
		});

		// Sự kiện xóa dữ liệu từ bảng phải (Xóa dữ liệu cho Table right)
		btnleft.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] item = tableright.getSelection();
					String[] madonvidelete = new String[item.length];
					for (int i = 0; i < madonvidelete.length; i++) {
						madonvidelete[i] = item[i].getText(1);
					}

					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}

					String delete = "";
					for (int i = 0; i < madonvidelete.length; i++) {
						delete = delete + "DELETE DonVi WHERE MaDonVi='" + madonvidelete[i] + "'" + "\n";
					}
					int result = connect.execUpdateQuery(delete);
					if (result > 0) {
						tableright.remove(tableright.getSelectionIndices());
					}
					connect.closeStatement();
				} catch (Exception se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo lỗi");
						thongbao.setMessage(se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage(se.toString());
					}
					thongbao.open();
				}
			}
		});

		// Sort data
		// *********************************************************************************************************************************************************************
		// Sắp xếp table left theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(tableleft);
		sort.setNumber(true);

		tbcolMadonvi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcolTendonvi.addListener(SWT.Selection, sort.sortListenerCode);

		// Sắp xếp table right theo một cột đã chọn
		SortData sortright = new SortData();
		sortright.setTable(tableright);
		sortright.setNumber(true);

		tbcrightMadonvi.addListener(SWT.Selection, sortright.sortListenerCode);
		tbcrightTendonvi.addListener(SWT.Selection, sortright.sortListenerCode);
	}

	// Hien trong CTabFolder
	// ==================================================================================
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String groupname, int ngonngu) {
		grouplogin = groupname;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Đơn vị");
		} else {
			itemtab.setText("Department");
		}
		Composite composite = new Composite(tabfolder, SWT.NONE);
		composite.setLayout(null);
		itemtab.setControl(composite);

		createContents(composite, shell, ngonngu);
		tableleft.setSize(Display.getDefault().getBounds().width / 2 - 30,
				Display.getDefault().getBounds().height - 220);
		tableright.setSize(Display.getDefault().getBounds().width / 2 - 30,
				Display.getDefault().getBounds().height - 220);
	}
}
