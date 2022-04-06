package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
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

public class Office {

	protected Shell shell;
	private Composite compositeshell;
	private Table table;
	private ConnectSQL connect;
	private int ngonngu = 0;
	private boolean them = false, sua = false, xoa = false;
	// Sử dụng cho nút Sửa
	private String maoffice_sua, office_sua;
	// vị trí dòng sửa
	private int index_sua = -1;

	public static void main(String[] args) {
		try {
			Office window = new Office();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContentsShell();
		createContents(compositeshell, shell);
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
		shell.setImage(SWTResourceManager.getImage(Office.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1366, 720);
		shell.setText("Office");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setMaximized(true);

		compositeshell = new Composite(shell, SWT.NONE);
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell) {
		CLabel lbMaoffice = new CLabel(composite, SWT.RIGHT);
		lbMaoffice.setBounds(10, 10, 130, 30);
		lbMaoffice.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMaoffice.setText("Office Code");

		Text textMaoffice = new Text(composite, SWT.BORDER | SWT.CENTER);
		textMaoffice.setBounds(146, 10, 154, 30);
		textMaoffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textMaoffice.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textMaoffice.setLayoutData(new RowData(169, SWT.DEFAULT));
		textMaoffice.setTextLimit(2);

		CLabel lbOffice = new CLabel(composite, SWT.RIGHT);
		lbOffice.setBounds(306, 10, 100, 30);
		lbOffice.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbOffice.setText("Office");

		Text textOffice = new Text(composite, SWT.BORDER | SWT.CENTER);
		textOffice.setBounds(412, 10, 237, 30);
		textOffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textOffice.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textOffice.setLayoutData(new RowData(224, SWT.DEFAULT));

		Button btnThem = new Button(composite, SWT.CENTER);
		btnThem.setImage(SWTResourceManager.getImage(Office.class, "/itmanagerip/Icon/button/add.png"));
		btnThem.setBounds(655, 10, 100, 30);
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnThem.setLayoutData(new RowData(62, SWT.DEFAULT));

		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnThem.setText("Thêm");

		Button btnSua = new Button(composite, SWT.CENTER);
		btnSua.setImage(SWTResourceManager.getImage(Office.class, "/itmanagerip/Icon/button/Edit yellow25.png"));
		btnSua.setBounds(761, 10, 100, 30);
		btnSua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnSua.setLayoutData(new RowData(61, SWT.DEFAULT));

		btnSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnSua.setText("Sửa");

		Button btnXoa = new Button(composite, SWT.CENTER);
		btnXoa.setImage(SWTResourceManager.getImage(Office.class, "/itmanagerip/Icon/button/delete20.png"));
		btnXoa.setBounds(865, 10, 100, 30);
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setLayoutData(new RowData(61, SWT.DEFAULT));

		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnXoa.setText("Xóa");

		Button btnTimKiem = new Button(composite, SWT.CENTER);
		btnTimKiem.setImage(SWTResourceManager.getImage(Office.class, "/itmanagerip/Icon/button/search.png"));
		btnTimKiem.setBounds(970, 10, 130, 30);
		btnTimKiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTimKiem.setLayoutData(new RowData(91, SWT.DEFAULT));

		btnTimKiem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTimKiem.setText("Tìm kiếm");

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(Office.class, "/itmanagerip/Icon/button/save25.png"));
		btnLuu.setBounds(1104, 10, 100, 30);
		btnLuu.setLayoutData(new RowData(58, SWT.DEFAULT));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(Office.class, "/itmanagerip/Icon/button/cancel22.png"));
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

		TableColumn tbcolMaoffice = new TableColumn(table, SWT.NONE);
		tbcolMaoffice.setWidth(201);
		tbcolMaoffice.setText("Mã Office");

		TableColumn tbcolOffice = new TableColumn(table, SWT.NONE);
		tbcolOffice.setWidth(400);
		tbcolOffice.setText("Office");

		// Set Language
		// --------------------------------------------------------------------------------
		if (ngonngu == 0) {
			lbMaoffice.setText("Mã Office");
			lbOffice.setText("Office");
			tbcolMaoffice.setText("Mã Office");
			tbcolOffice.setText("Office");
			btnThem.setText("Thêm");
			btnSua.setText("Sửa");
			btnXoa.setText("Xóa");
			btnTimKiem.setText("Tìm kiếm");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
		} else {
			// English
			lbMaoffice.setText("Office Code");
			lbOffice.setText("Office");
			tbcolMaoffice.setText("Office Code");
			tbcolOffice.setText("Office");
			btnThem.setText("Add");
			btnSua.setText("Edit");
			btnXoa.setText("Delete");
			btnTimKiem.setText("Search");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
		}

		// text Ma Office chỉ cho nhập số
		textMaoffice.addVerifyListener(new VerifyListener() {
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

		// Ẩn button Luu, button Huy
		// *******************************************************
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// Xử lý sự kiện Thêm
		// **************************************************************
		btnThem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				them = true;
				sua = false;
				xoa = false;
				textMaoffice.setText("");
				textOffice.setText("");

				btnLuu.setVisible(true);
				btnHuy.setVisible(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnTimKiem.setEnabled(false);

				textMaoffice.setBackground(SWTResourceManager.getColor(255, 255, 255));
				textOffice.setBackground(SWTResourceManager.getColor(255, 255, 255));
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
				textMaoffice.setText("");
				textOffice.setText("");
				try {
					TableItem[] item = table.getSelection();
					maoffice_sua = item[0].getText();
					textMaoffice.setText(maoffice_sua);
					office_sua = item[0].getText(1);
					textOffice.setText(office_sua);

					// vị trí của item cần sửa trong bảng
					index_sua = table.indexOf(item[0]);

					btnLuu.setVisible(true);
					btnHuy.setVisible(true);
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					btnTimKiem.setEnabled(false);
					textMaoffice.setBackground(SWTResourceManager.getColor(255, 215, 255));
					textOffice.setBackground(SWTResourceManager.getColor(255, 215, 255));
				} catch (Exception ex) {
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);

					btnSua.setEnabled(true);
					btnXoa.setEnabled(true);
					btnTimKiem.setEnabled(true);
					sua = false;
					textMaoffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
					textOffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
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
					connect = new ConnectSQL();
					connect.setConnection();
					connect.setStatement();

					String maoffice = textMaoffice.getText().isEmpty() ? ""
							: " AND MaOffice LIKE '%" + textMaoffice.getText() + "%'";
					String office = textOffice.getText().isEmpty() ? ""
							: " AND (Office LIKE N'%" + textOffice.getText()
									+ "%' OR [dbo].[convertUnicodetoASCII](Office) LIKE '%" + textOffice.getText()
									+ "%')";

					String select = "SELECT MaOffice,Office FROM Office WHERE 1=1" + maoffice + office
							+ " ORDER BY MaOffice ASC";
					ResultSet result = connect.getStatement().executeQuery(select);
					table.removeAll();
					while (result.next()) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(new String[] { result.getString(1), result.getString(2) });
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
						if (connect.getConnection() != null) {
							connect.closeConnection();
						}
					} catch (SQLException se2) {
						// nothing we can do
					}
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
				textMaoffice.setText("");
				textOffice.setText("");

				// Ẩn button Luu, button Huy
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				btnThem.setEnabled(true);
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnTimKiem.setEnabled(true);
				textMaoffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
				textOffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
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
						connect = new ConnectSQL();
						connect.setConnection();

						try {
							String insert = "INSERT INTO Office (MaOffice,Office) VALUES ('" + textMaoffice.getText()
									+ "',N'" + textOffice.getText() + "')";
							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								TableItem item = new TableItem(table, SWT.NONE);
								item.setText(new String[] { textMaoffice.getText(), textOffice.getText() });
								textMaoffice.setText("");
								textOffice.setText("");
							}
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
					} finally {
						try {
							if (connect.getStatement() != null) {
								connect.closeStatement();
							}
							if (connect.getConnection() != null) {
								connect.closeConnection();
							}
						} catch (SQLException se2) {
							// nothing we can do
						}
					}
				} else if (sua) {
					// Nut Sua duoc chon
					try {
						connect = new ConnectSQL();
						connect.setConnection();

						try {
							String update = "UPDATE Office SET MaOffice='" + textMaoffice.getText() + "' , Office=N'"
									+ textOffice.getText() + "' WHERE MaOffice='" + maoffice_sua + "'";
							int result = connect.execUpdateQuery(update);
							if (result > 0) {
								table.remove(index_sua);
								TableItem item = new TableItem(table, SWT.NONE);
								item.setText(new String[] { textMaoffice.getText(), textOffice.getText() });
								textMaoffice.setText("");
								textOffice.setText("");

								them = false;
								sua = false;

								// Ẩn button Luu, button Huy
								btnLuu.setVisible(false);
								btnHuy.setVisible(false);

								btnThem.setEnabled(true);
								btnXoa.setEnabled(true);
								btnTimKiem.setEnabled(true);
								textMaoffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
								textOffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
							}
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
					} finally {
						try {
							if (connect.getStatement() != null) {
								connect.closeStatement();
							}
							if (connect.getConnection() != null) {
								connect.closeConnection();
							}
						} catch (SQLException se2) {
							// nothing we can do
						}
					}
				} else if (xoa) {
					try {
						connect = new ConnectSQL();
						connect.setConnection();

						try {
							// Lấy cột MaLoaimay
							TableItem[] item = table.getSelection();
							String maoffice = item[0].getText();
							String deleteOffice = "DELETE Office WHERE MaOffice='" + maoffice + "'";
							try {
								connect.execUpdateQuery(deleteOffice);
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
					} finally {
						try {
							if (connect.getStatement() != null) {
								connect.closeStatement();
							}
							if (connect.getConnection() != null) {
								connect.closeConnection();
							}
						} catch (SQLException se2) {
							// nothing we can do
						}
					}
				}
			}
		});

		// Sort data
		// ********************************************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(false);

		tbcolMaoffice.addListener(SWT.Selection, sort.sortListenerCode);
		tbcolOffice.addListener(SWT.Selection, sort.sortListenerCode);
	}

	// Hien trong CTabFolder
	// ==================================================================================
	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu) {
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		itemtab.setText("Office");
		Composite composite = new Composite(tabfolder, SWT.NONE);
		composite.setLayout(null);
		itemtab.setControl(composite);
		createContents(composite, shell);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 170);
	}
}
