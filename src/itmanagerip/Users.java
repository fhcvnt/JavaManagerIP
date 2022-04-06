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

public class Users {

	protected Shell shell;
	private Composite compositeshell;
	private Text textUsername;
	private Text textTennguoidung;
	private Text textID;
	private Text textGroup;
	private Table table;
	private ConnectSQL connect;
	private String usercode = ""; // Mã người dùng
	// Kích thước màn hình
	private int sizemonitorx = Display.getDefault().getBounds().width;
	private int sizemonitory = Display.getDefault().getBounds().height;
	private int vitrixoa = -1;

	public static void main(String[] args) {
		try {
			Users window = new Users();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContentsShell();
		createContents(compositeshell, shell, "", "", 1);
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
		shell.setImage(SWTResourceManager.getImage(Users.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1293, 808);
		shell.setText("Người dùng");
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		shell.setMaximized(true);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell, String grouplogin, String sothe, int ngonngu) {
		usercode = sothe;
		CLabel lbUsername = new CLabel(composite, SWT.NONE);
		lbUsername.setAlignment(SWT.RIGHT);
		lbUsername.setText("Tên Đăng Nhập");
		lbUsername.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lbUsername.setBounds(278, 26, 140, 30);

		textUsername = new Text(composite, SWT.BORDER);
		textUsername.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textUsername.setBounds(424, 26, 138, 30);

		CLabel lbtennguoidung = new CLabel(composite, SWT.NONE);
		lbtennguoidung.setAlignment(SWT.RIGHT);
		lbtennguoidung.setText("Tên Người Dùng");
		lbtennguoidung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lbtennguoidung.setBounds(568, 26, 169, 30);

		textTennguoidung = new Text(composite, SWT.BORDER);
		textTennguoidung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTennguoidung.setBounds(743, 26, 249, 30);

		CLabel lbID = new CLabel(composite, SWT.NONE);
		lbID.setAlignment(SWT.RIGHT);
		lbID.setText("Số Thẻ");
		lbID.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lbID.setBounds(23, 26, 116, 30);

		textID = new Text(composite, SWT.BORDER);
		textID.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textID.setBounds(155, 26, 117, 30);

		CLabel lbGroup = new CLabel(composite, SWT.NONE);
		lbGroup.setAlignment(SWT.RIGHT);
		lbGroup.setText("Nhóm");
		lbGroup.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lbGroup.setBounds(23, 76, 116, 30);

		textGroup = new Text(composite, SWT.BORDER);
		textGroup.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGroup.setBounds(155, 76, 200, 30);

		Button btnSearch = new Button(composite, SWT.NONE);
		btnSearch.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSearch.setImage(SWTResourceManager.getImage(Users.class, "/itmanagerip/Icon/button/user find.png"));
		btnSearch.setText("Search");
		btnSearch.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnSearch.setBounds(376, 76, 179, 35);

		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnAdd.setImage(SWTResourceManager.getImage(Users.class, "/itmanagerip/Icon/button/user add.png"));
		btnAdd.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnAdd.setBounds(561, 76, 140, 35);
		btnAdd.setText("Add");

		Button btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setForeground(SWTResourceManager.getColor(255, 69, 0));
		btnEdit.setImage(SWTResourceManager.getImage(Users.class, "/itmanagerip/Icon/button/user edit.png"));
		btnEdit.setText("Edit");
		btnEdit.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnEdit.setBounds(707, 76, 131, 35);

		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnDelete.setImage(SWTResourceManager.getImage(Users.class, "/itmanagerip/Icon/button/user delete.png"));
		btnDelete.setText("Delete");
		btnDelete.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnDelete.setBounds(844, 76, 150, 35);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderForeground(SWTResourceManager.getColor(0, 0, 0));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(20, 125, 1231, 619);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setSize(sizemonitorx - 40, sizemonitory - 200);

		TableColumn tbcID = new TableColumn(table, SWT.NONE);
		tbcID.setWidth(222);
		tbcID.setText("Số Thẻ");

		TableColumn tbcUsername = new TableColumn(table, SWT.NONE);
		tbcUsername.setWidth(249);
		tbcUsername.setText("Tên Đăng Nhập");

		TableColumn tbcGroup = new TableColumn(table, SWT.NONE);
		tbcGroup.setWidth(190);
		tbcGroup.setText("Nhóm");

		TableColumn tbcTennguoidung = new TableColumn(table, SWT.NONE);
		tbcTennguoidung.setWidth(315);
		tbcTennguoidung.setText("Tên Người Dùng");

		Button btnSave = new Button(composite, SWT.NONE);
		btnSave.setText("Save");
		btnSave.setImage(SWTResourceManager.getImage(Users.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnSave.setBounds(1000, 76, 110, 35);

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setImage(SWTResourceManager.getImage(Users.class, "/itmanagerip/Icon/button/cancel30.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnCancel.setBounds(1116, 76, 116, 35);

		btnSave.setVisible(false);
		btnCancel.setVisible(false);

		// kiem tra xem co phai nhom nguoi dung la: Manager hay Admin hay khong
		if (grouplogin.equals("Manager") || grouplogin.equals("Admin")) {
			btnAdd.setEnabled(true);
			btnEdit.setEnabled(true);
			btnDelete.setEnabled(true);
		} else {
			btnAdd.setEnabled(false);
			btnEdit.setEnabled(false);
			btnDelete.setEnabled(false);
		}

		// Thay đổi ngôn ngữ
		// -------------------------------------------------------------------------
		if (ngonngu == 0) {
			lbUsername.setText("Tên Đăng Nhập");
			lbtennguoidung.setText("Tên Người Dùng");
			lbID.setText("Số Thẻ");
			lbGroup.setText("Nhóm");
			btnSearch.setText("Tìm Kiếm");
			btnAdd.setText("Thêm");
			btnEdit.setText("Sửa");
			btnDelete.setText("Xóa");
			tbcID.setText("Số Thẻ");
			tbcGroup.setText("Nhóm");
			tbcUsername.setText("Tên Đăng Nhập");
			tbcTennguoidung.setText("Tên Người Dùng");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else if (ngonngu == 1) {
			lbUsername.setText("Username");
			lbtennguoidung.setText("Person Name");
			lbID.setText("ID");
			lbGroup.setText("Group");
			btnSearch.setText("Search");
			btnAdd.setText("Add");
			btnEdit.setText("Edit");
			btnDelete.setText("Delete");
			tbcID.setText("ID");
			tbcGroup.setText("Group");
			tbcUsername.setText("Username");
			tbcTennguoidung.setText("Person Name");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
		}

		// sự kiện User chọn dòng chứa thông tin của mình thì cho sửa thông tin
		// Manager thì có toàn quyền
		// Admin thì được sửa hết trừ manager
		// User, Translate thì chỉ được sửa của chính mình
		// Disable là tài khoản đang bị khóa chỉ có Manager và Admin mới đổi được nhóm
		// người dùng cho nhóm này (Mở khóa)
		// Disable thì không thể đăng nhập
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table.getSelection();
				switch (grouplogin) {
				case "Manager":
					btnEdit.setEnabled(true);
					break;
				case "Admin":
					if (item[0].getText(2).equals("Manager")) {
						if (usercode.equals("21608")) {
							btnEdit.setEnabled(true);
							btnDelete.setEnabled(true);
						} else {
							btnEdit.setEnabled(false);
							btnDelete.setEnabled(false);
						}
					} else {
						btnEdit.setEnabled(true);
						btnDelete.setEnabled(true);
					}
					break;
				case "Disable":
					btnEdit.setEnabled(false);
					break;
				case "Translate": {
					if (item[0].getText(2).equals("Translate") && item[0].getText(0).equals(usercode)) {
						btnEdit.setEnabled(true);
					} else {
						btnEdit.setEnabled(false);
					}
				}
					break;
				case "User": {
					if (item[0].getText(2).equals("User") && item[0].getText(0).equals(usercode)) {
						btnEdit.setEnabled(true);
					} else {
						btnEdit.setEnabled(false);
					}
				}
					break;
				default:
					break;
				}
			}
		});

		// Button Search
		// -------------------------------------------------------------------------------------------
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell, ngonngu);
			}
		});

		// Button Add
		// -------------------------------------------------------------------------------------------
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewUser them = new NewUser();
				them.createContents(grouplogin, usercode, ngonngu);
				composite.setEnabled(false);
				them.open();
				composite.setEnabled(true);

				// Cap nhat du lieu
				search(shell, ngonngu);
			}
		});

		// Button Edit
		// -------------------------------------------------------------------------------------------
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] item = table.getSelection();
					EditUser sua = new EditUser();
					sua.createContents(item[0].getText(), item[0].getText(1), item[0].getText(2), item[0].getText(3),
							usercode, grouplogin, ngonngu);
					composite.setEnabled(false);
					sua.open();
					composite.setEnabled(true);
				} catch (Exception exc) {

				}

				// Cập nhật dữ liệu sau khi Edit
				search(shell, ngonngu);
			}
		});

		// Button Delete
		// -------------------------------------------------------------------------------------------
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] item = table.getSelection();
					vitrixoa = table.getSelectionIndex();
					// Mã người dùng
					usercode = item[0].getText();
					btnSave.setVisible(true);
					btnCancel.setVisible(true);
					btnAdd.setEnabled(false);
					btnEdit.setEnabled(false);
					btnSearch.setEnabled(false);
				} catch (Exception exc) {
					btnSave.setVisible(false);
					btnCancel.setVisible(false);
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(true);
					btnSearch.setEnabled(true);
					usercode = "";
				}
			}
		});

		// Button Save
		// -------------------------------------------------------------------------------------------
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					connect = new ConnectSQL();
					connect.setConnection();

					String delete = "DELETE NguoiDung WHERE MaNguoiDung='" + usercode + "'";
					int xoa = connect.execUpdateQuery(delete);
					if (xoa > 0) {
						// Lấy dữ liệu cho table
						table.remove(vitrixoa);

						btnSave.setVisible(false);
						btnCancel.setVisible(false);
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSearch.setEnabled(true);
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
						if (connect.getConnection() != null) {
							connect.closeConnection();
						}
					} catch (SQLException se2) {
					}
				}
			}
		});

		// Button Cancel
		// -------------------------------------------------------------------------------------------
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnSave.setVisible(false);
				btnCancel.setVisible(false);
				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnSearch.setEnabled(true);
			}
		});

		// Sort data
		// **************************************************************************************************************
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(false);

		tbcID.addListener(SWT.Selection, sort.sortListenerCode);
		tbcUsername.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGroup.addListener(SWT.Selection, sort.sortListenerCode);
		tbcTennguoidung.addListener(SWT.Selection, sort.sortListenerCode);

	}

	// ======================================================================================================================
	protected void showTabfolder(CTabFolder tabfolder, Shell shell, String groupname, String sothe, int ngonngu) {
		usercode = sothe;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Người dùng");
		} else {
			itemtab.setText("User");
		}
		Composite composite = new Composite(tabfolder, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);
		createContents(composite, shell, groupname, sothe, ngonngu);
		table.setSize(sizemonitorx - 40, sizemonitory - 240);
	}

	// --------------------------------------------------------------------------------------------------
	public void search(Shell shell, int ngonngu) {

		try {
			connect = new ConnectSQL();
			connect.setConnection();
			connect.setStatement();

			String sothe = textID.getText().isEmpty() ? "" : " AND MaNguoiDung LIKE '%" + textID.getText() + "%'";
			String tendangnhap = textUsername.getText().isEmpty() ? ""
					: " AND TenDangNhap LIKE '%" + textUsername.getText() + "%'";
			String tennguoidung = textTennguoidung.getText().isEmpty() ? ""
					: " AND (TenNguoiDung LIKE N'%" + textTennguoidung.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](TenNguoiDung) LIKE '%" + textTennguoidung.getText()
							+ "%')";
			String nhom = textGroup.getText().isEmpty() ? "" : " AND TenNhom='" + textGroup.getText() + "'";

			String select = "SELECT MaNguoiDung,TenDangNhap,TenNhom,TenNguoiDung FROM NguoiDung WHERE 1=1" + sothe
					+ tendangnhap + tennguoidung + nhom + " ORDER BY TenDangNhap ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			table.removeAll();

			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { result.getString(1), result.getString(2), result.getString(3),
						result.getString(4) });
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

}
