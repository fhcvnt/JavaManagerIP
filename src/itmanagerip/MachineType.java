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

import sort.SortData;
import sql.ConnectSQL;

public class MachineType {

	protected Shell shell;
	private Composite compositeshell;
	private Table table;
	private ConnectSQL connect;
	private int ngonngu = 1;
	private boolean them = false, sua = false, xoa = false;
	// Sử dụng cho nút Sửa
	private String maloaimay_sua, loaimay_sua;
	// vị trí dòng sửa
	private int index_sua = -1;
	private String grouplogin = ""; // nếu là User, Translate thì không cho chỉnh sửa

	public static void main(String[] args) {
		try {
			MachineType window = new MachineType();
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
		shell.setImage(SWTResourceManager.getImage(MachineType.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1366, 720);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Loại máy");
		} else {
			shell.setText("Machine type");
		}
		compositeshell = new Composite(shell, SWT.NONE);
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		CLabel lbMaloaimay = new CLabel(composite, SWT.RIGHT);
		lbMaloaimay.setBounds(10, 10, 154, 30);
		lbMaloaimay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMaloaimay.setText("Mã Loại Máy");

		Text textMaloaimay = new Text(composite, SWT.BORDER | SWT.CENTER);
		textMaloaimay.setBounds(170, 10, 130, 30);
		textMaloaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textMaloaimay.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textMaloaimay.setLayoutData(new RowData(169, SWT.DEFAULT));
		textMaloaimay.setTextLimit(15);

		CLabel lbLoaimay = new CLabel(composite, SWT.RIGHT);
		lbLoaimay.setBounds(306, 10, 130, 30);
		lbLoaimay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoaimay.setText("Loại Máy");

		Text textLoaimay = new Text(composite, SWT.BORDER | SWT.CENTER);
		textLoaimay.setBounds(442, 10, 207, 30);
		textLoaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textLoaimay.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textLoaimay.setLayoutData(new RowData(224, SWT.DEFAULT));

		Button btnThem = new Button(composite, SWT.CENTER);
		btnThem.setImage(SWTResourceManager.getImage(MachineType.class, "/itmanagerip/Icon/button/add.png"));
		btnThem.setBounds(655, 10, 100, 30);
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnThem.setLayoutData(new RowData(62, SWT.DEFAULT));

		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnThem.setText("Thêm");

		Button btnSua = new Button(composite, SWT.CENTER);
		btnSua.setImage(SWTResourceManager.getImage(MachineType.class, "/itmanagerip/Icon/button/Edit yellow25.png"));
		btnSua.setBounds(761, 10, 100, 30);
		btnSua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnSua.setLayoutData(new RowData(61, SWT.DEFAULT));

		btnSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnSua.setText("Sửa");

		Button btnXoa = new Button(composite, SWT.CENTER);
		btnXoa.setImage(SWTResourceManager.getImage(MachineType.class, "/itmanagerip/Icon/button/delete20.png"));
		btnXoa.setBounds(865, 10, 100, 30);
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setLayoutData(new RowData(61, SWT.DEFAULT));

		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXoa.setText("Xóa");

		Button btnTimKiem = new Button(composite, SWT.CENTER);
		btnTimKiem.setImage(SWTResourceManager.getImage(MachineType.class, "/itmanagerip/Icon/button/search.png"));
		btnTimKiem.setBounds(970, 10, 130, 30);
		btnTimKiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTimKiem.setLayoutData(new RowData(91, SWT.DEFAULT));

		btnTimKiem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTimKiem.setText("Tìm kiếm");

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(MachineType.class, "/itmanagerip/Icon/button/save25.png"));
		btnLuu.setBounds(1104, 10, 100, 30);
		btnLuu.setLayoutData(new RowData(58, SWT.DEFAULT));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(MachineType.class, "/itmanagerip/Icon/button/cancel22.png"));
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

		TableColumn tbcolMaloaimay = new TableColumn(table, SWT.NONE);
		tbcolMaloaimay.setWidth(201);
		tbcolMaloaimay.setText("Mã Loại Máy");

		TableColumn tbcolLoaimay = new TableColumn(table, SWT.NONE);
		tbcolLoaimay.setWidth(400);
		tbcolLoaimay.setText("Loại Máy");

		// Set Language
		// --------------------------------------------------------------------------------
		if (ngonngu == 0) {
			lbMaloaimay.setText("Mã Loại Máy");
			lbLoaimay.setText("Loại Máy");
			tbcolMaloaimay.setText("Mã Loại Máy");
			tbcolLoaimay.setText("Loại Máy");
			btnThem.setText("Thêm");
			btnSua.setText("Sửa");
			btnXoa.setText("Xóa");
			btnTimKiem.setText("Tìm kiếm");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
		} else {
			lbMaloaimay.setText("Machine Type Code");
			lbLoaimay.setText("Machine Type");
			tbcolMaloaimay.setText("Machine Type Code");
			tbcolLoaimay.setText("Machine Type");
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
				textMaloaimay.setText("");
				textLoaimay.setText("");

				btnLuu.setVisible(true);
				btnHuy.setVisible(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnTimKiem.setEnabled(false);

				textMaloaimay.setBackground(SWTResourceManager.getColor(255, 255, 255));
				textLoaimay.setBackground(SWTResourceManager.getColor(255, 255, 255));
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
				textMaloaimay.setText("");
				textLoaimay.setText("");
				try {
					TableItem[] item = table.getSelection();
					maloaimay_sua = item[0].getText();
					textMaloaimay.setText(maloaimay_sua);
					loaimay_sua = item[0].getText(1);
					textLoaimay.setText(loaimay_sua);

					// vị trí của item cần sửa trong bảng
					index_sua = table.indexOf(item[0]);

					btnLuu.setVisible(true);
					btnHuy.setVisible(true);
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					btnTimKiem.setEnabled(false);
					textMaloaimay.setBackground(SWTResourceManager.getColor(255, 215, 255));
					textLoaimay.setBackground(SWTResourceManager.getColor(255, 215, 255));
				} catch (Exception ex) {
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);

					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnTimKiem.setEnabled(true);
					sua = false;
					textMaloaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
					textLoaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
				}
			}
		});

		// Xử lý sự kiện Xóa
		// *********************************************************************
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				xoa = true;
				sua = false;
				them = false;

				btnLuu.setVisible(true);
				btnHuy.setVisible(true);
				btnThem.setEnabled(false);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(true);
				btnTimKiem.setEnabled(false);
			}
		});

		// Xử lý sự kiện Tìm Kiếm
		// ***********************************************************************
		btnTimKiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Xu ly SQL
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();

					String maloaimay = textMaloaimay.getText().isEmpty() ? ""
							: " AND MaLoaiMay LIKE '%" + textMaloaimay.getText() + "%'";
					String loaimay = textLoaimay.getText().isEmpty() ? ""
							: " AND (LoaiMay LIKE N'%" + textLoaimay.getText()
									+ "%' OR [dbo].[convertUnicodetoASCII](LoaiMay) LIKE '%" + textLoaimay.getText()
									+ "%')";

					String select = "SELECT MaLoaiMay,LoaiMay FROM LoaiMay WHERE 1=1" + maloaimay + loaimay;
					ResultSet result = connect.getStatement().executeQuery(select);
					table.removeAll();
					while (result.next()) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(new String[] { result.getString(1), result.getString(2) });
					}
					result.close();
					connect.setStatement();
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

		// Xu ly su kien Huy - Cancel
		// ********************************************************************************************************
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				them = false;
				sua = false;
				xoa = false;
				textMaloaimay.setText("");
				textLoaimay.setText("");

				// Ẩn button Luu, button Huy
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				btnThem.setEnabled(true);
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnTimKiem.setEnabled(true);
				textMaloaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
				textLoaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
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

						try {
							String insertLoaimay = "INSERT INTO LoaiMay (MaLoaiMay,LoaiMay) VALUES ('"
									+ textMaloaimay.getText() + "',N'" + textLoaimay.getText() + "')";
							int result = connect.execUpdateQuery(insertLoaimay);
							if (result > 0) {
								TableItem item = new TableItem(table, SWT.NONE);
								item.setText(new String[] { textMaloaimay.getText(), textLoaimay.getText() });
								textMaloaimay.setText("");
								textLoaimay.setText("");
							}
							connect.closeStatement();
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Thêm không thành công!\n" + ex.toString());
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Add failed!\n" + ex.toString());
							}
							thongbao.open();
						}

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
				} else if (sua) {
					// Nut Sua duoc chon
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}

						try {
							String updateLoaimay = "UPDATE LoaiMay SET MaLoaiMay='" + textMaloaimay.getText()
									+ "' , LoaiMay=N'" + textLoaimay.getText() + "' WHERE MaLoaiMay='" + maloaimay_sua
									+ "'";
							int result = connect.execUpdateQuery(updateLoaimay);
							if (result > 0) {
								table.remove(index_sua);
								TableItem item = new TableItem(table, SWT.NONE);
								item.setText(new String[] { textMaloaimay.getText(), textLoaimay.getText() });
								textMaloaimay.setText("");
								textLoaimay.setText("");

								them = false;
								sua = false;

								// Ẩn button Luu, button Huy
								btnLuu.setVisible(false);
								btnHuy.setVisible(false);

								btnThem.setEnabled(true);
								btnXoa.setEnabled(true);
								btnTimKiem.setEnabled(true);
								textMaloaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
								textLoaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
							}
							connect.closeStatement();
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
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
				} else if (xoa) {
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}

						try {
							// Lấy cột MaLoaimay
							TableItem[] item = table.getSelection();
							String maloaimay = item[0].getText();
							String deleteMabuilding = "DELETE LoaiMay WHERE MaLoaiMay='" + maloaimay + "'";
							try {
								connect.execUpdateQuery(deleteMabuilding);
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
							} catch (Exception ee) {
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Thông báo");
								} else {
									thongbao.setText("Notice");
								}
								thongbao.setMessage(ee.toString());
								thongbao.open();
							}
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
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
		// ********************************************************************************************************
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(false);

		tbcolMaloaimay.addListener(SWT.Selection, sort.sortListenerCode);
		tbcolLoaimay.addListener(SWT.Selection, sort.sortListenerCode);
	}

	// Hien trong CTabFolder
	// ==================================================================================
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String grouplogin, int ngonngu) {
		this.grouplogin = grouplogin;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Loại máy");
		} else {
			itemtab.setText("Machine type");
		}
		Composite composite = new Composite(tabfolder, SWT.NONE);
		composite.setLayout(null);
		itemtab.setControl(composite);
		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 170);
	}
}
