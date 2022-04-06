package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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

import it.LyLichMayTinh;
import sort.SortData;
import sql.ConnectSQL;

public class Building {

	protected Shell shell;
	private Composite compositeshell;
	private Text textMabuilding;
	private Text textBuilding;
	private Text textLopIP;
	private Table table;

	private ConnectSQL connect;
	private int ngonngu = 0;
	private boolean them = false, sua = false, xoa = false;
	// Sử dụng cho nút Sửa
	private String mabuilding_sua, building_sua, lopip_sua;
	// vị trí dòng sửa
	private int index_sua = -1;
	private String grouplogin = ""; // nếu là User, Translate thì không cho chỉnh sửa

	public static void main(String[] args) {
		try {
			Building window = new Building();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createShell();
		createContents(compositeshell, shell, ngonngu);
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
		shell.setImage(SWTResourceManager.getImage(LyLichMayTinh.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1366, 720);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Tòa nhà");
		} else {
			shell.setText("Building");
		}

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		CLabel lbMabuilding = new CLabel(composite, SWT.RIGHT);
		lbMabuilding.setBounds(10, 10, 124, 30);
		lbMabuilding.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMabuilding.setText("Mã Tòa Nhà");

		textMabuilding = new Text(composite, SWT.BORDER | SWT.CENTER);
		textMabuilding.setBounds(142, 10, 100, 30);
		textMabuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textMabuilding.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textMabuilding.setLayoutData(new RowData(169, SWT.DEFAULT));

		CLabel lbBuilding = new CLabel(composite, SWT.RIGHT);
		lbBuilding.setBounds(248, 10, 90, 30);
		lbBuilding.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbBuilding.setText("Tòa Nhà");

		textBuilding = new Text(composite, SWT.BORDER | SWT.CENTER);
		textBuilding.setBounds(344, 10, 138, 30);
		textBuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textBuilding.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textBuilding.setLayoutData(new RowData(224, SWT.DEFAULT));

		CLabel lbLopip = new CLabel(composite, SWT.RIGHT);
		lbLopip.setBounds(488, 10, 85, 30);
		lbLopip.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLopip.setText("Lớp IP");

		textLopIP = new Text(composite, SWT.BORDER | SWT.CENTER);
		textLopIP.setBounds(579, 10, 63, 30);
		textLopIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textLopIP.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textLopIP.setLayoutData(new RowData(124, SWT.DEFAULT));

		Button btnThem = new Button(composite, SWT.CENTER);
		btnThem.setImage(SWTResourceManager.getImage(Building.class, "/itmanagerip/Icon/button/add.png"));
		btnThem.setBounds(655, 10, 100, 30);
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnThem.setLayoutData(new RowData(62, SWT.DEFAULT));

		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnThem.setText("Thêm");

		Button btnSua = new Button(composite, SWT.CENTER);
		btnSua.setImage(SWTResourceManager.getImage(Building.class, "/itmanagerip/Icon/button/Edit yellow25.png"));
		btnSua.setBounds(761, 10, 100, 30);
		btnSua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnSua.setLayoutData(new RowData(61, SWT.DEFAULT));

		btnSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnSua.setText("Sửa");

		Button btnXoa = new Button(composite, SWT.CENTER);
		btnXoa.setImage(SWTResourceManager.getImage(Building.class, "/itmanagerip/Icon/button/delete20.png"));
		btnXoa.setBounds(865, 10, 100, 30);
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setLayoutData(new RowData(61, SWT.DEFAULT));

		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXoa.setText("Xóa");

		Button btnTimKiem = new Button(composite, SWT.CENTER);
		btnTimKiem.setImage(SWTResourceManager.getImage(Building.class, "/itmanagerip/Icon/button/search.png"));
		btnTimKiem.setBounds(970, 10, 130, 30);
		btnTimKiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTimKiem.setLayoutData(new RowData(91, SWT.DEFAULT));

		btnTimKiem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTimKiem.setText("Tìm kiếm");

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(Building.class, "/itmanagerip/Icon/button/save25.png"));
		btnLuu.setBounds(1104, 10, 100, 30);
		btnLuu.setLayoutData(new RowData(58, SWT.DEFAULT));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(Building.class, "/itmanagerip/Icon/button/cancel22.png"));
		btnHuy.setBounds(1210, 10, 108, 30);
		btnHuy.setLayoutData(new RowData(80, SWT.DEFAULT));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 56, 1330, 615);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 122);
		table.setLinesVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setLayoutData(new RowData(1330, 580));
		table.setHeaderVisible(true);
		table.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));

		TableColumn tbcolMabuilding = new TableColumn(table, SWT.NONE);
		tbcolMabuilding.setWidth(201);
		tbcolMabuilding.setText("Mã Tòa Nhà");

		TableColumn tbcolbuilding = new TableColumn(table, SWT.NONE);
		tbcolbuilding.setWidth(400);
		tbcolbuilding.setText("Tòa Nhà");

		TableColumn tbcolLopip = new TableColumn(table, SWT.NONE);
		tbcolLopip.setWidth(184);
		tbcolLopip.setText("Lớp IP");

		// Set Language
		// --------------------------------------------------------------------------------
		if (ngonngu == 0) {
			lbMabuilding.setText("Mã Tòa Nhà");
			lbBuilding.setText("Tòa Nhà");
			lbLopip.setText("Lớp IP");
			tbcolMabuilding.setText("Mã Tòa Nhà");
			tbcolbuilding.setText("Tòa Nhà");
			tbcolLopip.setText("Lớp IP");
			btnThem.setText("Thêm");
			btnSua.setText("Sửa");
			btnXoa.setText("Xóa");
			btnTimKiem.setText("Tìm kiếm");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
		} else {
			// English
			lbMabuilding.setText("Building Code");
			lbBuilding.setText("Building");
			lbLopip.setText("IP Class");
			tbcolMabuilding.setText("Building Code");
			tbcolbuilding.setText("Building");
			tbcolLopip.setText("IP Class");
			btnThem.setText("Add");
			btnSua.setText("Edit");
			btnXoa.setText("Delete");
			btnTimKiem.setText("Search");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
		}

		// Ẩn button Luu, button Huy
		// *******************************************************
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// nếu là User, Translate thì không cho chỉnh sửa
		if (grouplogin.equals("User") || grouplogin.equals("Translate")) {
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}

		// Xử lý sự kiện Thêm
		// **************************************************************
		btnThem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				them = true;
				sua = false;
				xoa = false;
				textMabuilding.setText("");
				textBuilding.setText("");
				textLopIP.setText("");

				btnLuu.setVisible(true);
				btnHuy.setVisible(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnTimKiem.setEnabled(false);

				textMabuilding.setBackground(SWTResourceManager.getColor(255, 255, 255));
				textBuilding.setBackground(SWTResourceManager.getColor(255, 255, 255));
				textLopIP.setBackground(SWTResourceManager.getColor(255, 255, 255));
			}
		});

		// Xử lý sự kiện Sửa
		// *****************************************************************
		btnSua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sua = true;
				them = false;
				xoa = false;
				textMabuilding.setText("");
				textBuilding.setText("");
				textLopIP.setText("");
				try {
					TableItem[] item = table.getSelection();
					mabuilding_sua = item[0].getText();
					textMabuilding.setText(mabuilding_sua);
					building_sua = item[0].getText(1);
					textBuilding.setText(building_sua);
					lopip_sua = item[0].getText(2);
					textLopIP.setText(lopip_sua);
					// vị trí của item cần sửa trong bảng
					index_sua = table.indexOf(item[0]);

					btnLuu.setVisible(true);
					btnHuy.setVisible(true);
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					btnTimKiem.setEnabled(false);

					textMabuilding.setBackground(SWTResourceManager.getColor(255, 215, 255));
					textBuilding.setBackground(SWTResourceManager.getColor(255, 215, 255));
					textLopIP.setBackground(SWTResourceManager.getColor(255, 215, 255));
				} catch (Exception ex) {
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);

					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnTimKiem.setEnabled(true);
					sua = false;
					textMabuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
					textBuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
					textLopIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
				}
			}
		});

		// Xử lý sự kiện Xóa
		// *********************************************************************
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();
				if (items.length > 0) {
					xoa = true;
					sua = false;
					them = false;

					btnLuu.setVisible(true);
					btnHuy.setVisible(true);
					btnThem.setEnabled(false);
					btnSua.setEnabled(false);
					btnXoa.setEnabled(true);
					btnTimKiem.setEnabled(false);
				} else {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Không có dòng nào được chọn!");
					} else {
						thongbao.setText("Notice");
						thongbao.setMessage("No row is select!");
					}
					thongbao.open();
				}
			}
		});

		// Xử lý sự kiện Tìm Kiếm
		// ***********************************************************************
		btnTimKiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// Xu ly su kien Huy - Cancel
		// ********************************************************************************************************
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				them = false;
				sua = false;
				xoa = false;
				textMabuilding.setText("");
				textBuilding.setText("");
				textLopIP.setText("");

				// Ẩn button Luu, button Huy
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				btnThem.setEnabled(true);
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnTimKiem.setEnabled(true);
				textMabuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
				textBuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
				textLopIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
			}
		});

		// xu ly su kien Luu - Save
		// *********************************************************************************************************
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Nút Thêm được chọn
				if (them) {
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}
						String insert = "INSERT INTO Building (MaBuilding,Building,LopIP) VALUES ('"
								+ textMabuilding.getText() + "',N'" + textBuilding.getText() + "',"
								+ textLopIP.getText() + ")";
						int result = connect.execUpdateQuery(insert);
						if (result > 0) {
							TableItem item = new TableItem(table, SWT.NONE);
							item.setText(new String[] { textMabuilding.getText(), textBuilding.getText(),
									textLopIP.getText() });
							textMabuilding.setText("");
							textBuilding.setText("");
							textLopIP.setText("");
						}
						connect.closeStatement();

					} catch (Exception se) {
						// se.printStackTrace();
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Connect error!\n" + se.toString());
						}
						thongbao.open();
					}
				} else if (sua) {
					// Nut Sua duoc chon
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}

						try {
							String update = "UPDATE Building SET MaBuilding='" + textMabuilding.getText()
									+ "' , Building=N'" + textBuilding.getText() + "' , LopIP=" + textLopIP.getText()
									+ " WHERE MaBuilding='" + mabuilding_sua + "'";

							int result = connect.execUpdateQuery(update);
							if (result > 0) {
								table.remove(index_sua);
								TableItem item = new TableItem(table, SWT.NONE);
								item.setText(new String[] { textMabuilding.getText(), textBuilding.getText(),
										textLopIP.getText() });
								textMabuilding.setText("");
								textBuilding.setText("");
								textLopIP.setText("");

								them = false;
								sua = false;

								// Ẩn button Luu, button Huy
								btnLuu.setVisible(false);
								btnHuy.setVisible(false);

								btnThem.setEnabled(true);
								btnXoa.setEnabled(true);
								btnTimKiem.setEnabled(true);
								textMabuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
								textBuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
								textLopIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
							}
							connect.closeStatement();
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Sửa không thành công!\n" + ex.toString());
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Edit failed!\n" + ex.toString());
							}
							thongbao.open();
						}

					} catch (Exception se) {
						// se.printStackTrace();
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Connect error!\n" + se.toString());
						}
						thongbao.open();
					}
				} else if (xoa) {
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}

						try {
							// Lấy cột MaBuilding
							TableItem[] item = table.getSelection();
							String mabuilding = item[0].getText();
							String delete = "DELETE Building WHERE MaBuilding='" + mabuilding + "'";
							connect.execUpdateQuery(delete);
							table.remove(table.getSelectionIndices());
							xoa = false;
							sua = false;
							them = false;

							btnLuu.setVisible(false);
							btnHuy.setVisible(false);
							btnThem.setEnabled(true);
							btnSua.setEnabled(true);
							btnXoa.setEnabled(true);
							btnTimKiem.setEnabled(true);
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Không có dòng nào được chọn!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("No row is select!");
							}
							thongbao.open();
						}

					} catch (Exception se) {
						// se.printStackTrace();
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

		// Sort data
		// **********************************************************************************************
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(false);

		tbcolMabuilding.addListener(SWT.Selection, sort.sortListenerCode);
		tbcolbuilding.addListener(SWT.Selection, sort.sortListenerCode);
		tbcolLopip.addListener(SWT.Selection, sort.sortListenerCode);
	}

	public void search(Shell shell) {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();

			String mabuilding = textMabuilding.getText().isEmpty() ? ""
					: " AND MaBuilding LIKE '%" + textMabuilding.getText() + "%'";
			String building = textBuilding.getText().isEmpty() ? ""
					: " AND (Building LIKE N'%" + textBuilding.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](Building) LIKE '%" + textBuilding.getText() + "%')";
			String lopip = textLopIP.getText().isEmpty() ? "" : " AND LopIP = '" + textLopIP.getText() + "'";

			String select = "SELECT MaBuilding,Building,LopIP FROM Building WHERE 1=1" + mabuilding + building + lopip;
			ResultSet result = connect.getStatement().executeQuery(select);
			table.removeAll();
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { result.getString(1), result.getString(2), result.getString(3) });
			}
			result.close();
			connect.closeStatement();

		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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

	// Hien trong CTabFolder
	// ==================================================================================
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String grouplogin, int ngonngu) {
		this.grouplogin = grouplogin;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Tòa nhà");
		} else {
			itemtab.setText("Building");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);

		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 170);
	}
}
