package itmanagerip;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class NewUser {

	protected Shell shell;
	private Text textID;
	private Text textTendangnhap;
	private Text textTennguoidung;
	private Text textMatkhau;

	private ConnectSQL connect;

	public static void main(String[] args) {
		try {
			NewUser window = new NewUser();
			window.createContents("", "", 0);
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
	protected void createContents(String grouplogin, String userlogin, int ngonngu) {
		shell = new Shell(SWT.MIN);
		shell.setImage(SWTResourceManager.getImage(NewUser.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(642, 394);
		shell.setText("Thêm người dùng");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		CLabel lbID = new CLabel(composite, SWT.NONE);
		lbID.setAlignment(SWT.RIGHT);
		lbID.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbID.setBounds(21, 24, 169, 30);
		lbID.setText("Số Thẻ");

		textID = new Text(composite, SWT.BORDER);
		textID.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textID.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textID.setBounds(205, 24, 245, 30);
		textID.setTextLimit(6);

		CLabel lbTendangnhap = new CLabel(composite, SWT.NONE);
		lbTendangnhap.setText("Tên Đăng Nhập");
		lbTendangnhap.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbTendangnhap.setAlignment(SWT.RIGHT);
		lbTendangnhap.setBounds(21, 80, 169, 30);

		textTendangnhap = new Text(composite, SWT.BORDER);
		textTendangnhap.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTendangnhap.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textTendangnhap.setBounds(205, 80, 245, 30);
		textTendangnhap.setTextLimit(20);

		CLabel lbNhom = new CLabel(composite, SWT.NONE);
		lbNhom.setText("Nhóm");
		lbNhom.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbNhom.setAlignment(SWT.RIGHT);
		lbNhom.setBounds(21, 133, 169, 30);

		CCombo comboNhom = new CCombo(composite, SWT.BORDER);
		if (grouplogin.equals("Manager") || userlogin.equals("21608")) {
			comboNhom.setItems(new String[] { "Manager", "Admin", "Translate", "User", "Disable" });
			comboNhom.select(3);
		} else if (grouplogin.equals("Admin")) {
			comboNhom.setItems(new String[] { "Admin", "Translate", "User", "Disable" });
			comboNhom.select(2);
		}
		comboNhom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboNhom.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		comboNhom.setBounds(205, 133, 245, 30);
		comboNhom.setEditable(false);

		CLabel lbTennguoidung = new CLabel(composite, SWT.NONE);
		lbTennguoidung.setText("Tên Người Dùng");
		lbTennguoidung.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbTennguoidung.setAlignment(SWT.RIGHT);
		lbTennguoidung.setBounds(21, 187, 169, 30);

		textTennguoidung = new Text(composite, SWT.BORDER);
		textTennguoidung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTennguoidung.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textTennguoidung.setBounds(205, 187, 390, 30);
		textTennguoidung.setTextLimit(50);

		CLabel lbMatkhau = new CLabel(composite, SWT.NONE);
		lbMatkhau.setText("Mật Khẩu");
		lbMatkhau.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbMatkhau.setAlignment(SWT.RIGHT);
		lbMatkhau.setBounds(21, 240, 169, 30);

		textMatkhau = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textMatkhau.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textMatkhau.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textMatkhau.setBounds(205, 240, 390, 30);
		textMatkhau.setTextLimit(32);

		Button btnSave = new Button(composite, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(NewUser.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		btnSave.setBounds(286, 294, 146, 35);
		btnSave.setText("Save");

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setImage(SWTResourceManager.getImage(NewUser.class, "/itmanagerip/Icon/button/cancel.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setText("Cancel");
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		btnCancel.setBounds(451, 294, 146, 35);

		// Thay đổi ngôn ngữ
		// -------------------------------------------------------------------------
		if (ngonngu == 0) {
			shell.setText("Thêm người dùng");
			lbID.setText("Số Thẻ");
			lbNhom.setText("Nhóm");
			lbTendangnhap.setText("Tên Đăng Nhập");
			lbTennguoidung.setText("Tên Người Dùng");
			lbMatkhau.setText("Mật Khẩu");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else if (ngonngu == 1) {
			shell.setText("Add Users");
			lbID.setText("ID");
			lbNhom.setText("Group");
			lbTendangnhap.setText("Username");
			lbTennguoidung.setText("Person Name");
			lbMatkhau.setText("Password");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
		}

		// Button Save
		// ---------------------------------------------------------------------------------------------------------
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (grouplogin.equals("Manager") || userlogin.equals("21608")) {
					try {
						connect = new ConnectSQL();
						connect.setConnection();

						if (!textID.getText().isEmpty() && !textMatkhau.getText().isEmpty()
								&& !textTendangnhap.getText().isEmpty() && !textTennguoidung.getText().isEmpty()
								&& !comboNhom.getText().isEmpty()) {
							String insert = "INSERT INTO NguoiDung (MaNguoiDung,TenDangNhap,TenNhom,TenNguoiDung,MatKhau) VALUES ('"
									+ textID.getText() + "','" + textTendangnhap.getText() + "','" + comboNhom.getText()
									+ "',N'" + textTennguoidung.getText() + "','" + getMD5(textMatkhau.getText())
									+ "')";
							int ketqua = connect.execUpdateQuery(insert);
							if (ketqua > 0) {
								textID.setText("");
								textMatkhau.setText("");
								textTendangnhap.setText("");
								textTennguoidung.setText("");
							}
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
				} else if (grouplogin.equals("Admin")) {
					try {
						connect = new ConnectSQL();
						connect.setConnection();

						if (!textID.getText().isEmpty() && !textMatkhau.getText().isEmpty()
								&& !textTendangnhap.getText().isEmpty() && !textTennguoidung.getText().isEmpty()
								&& !comboNhom.getText().isEmpty()) {
							String insert = "INSERT INTO NguoiDung (MaNguoiDung,TenDangNhap,TenNhom,TenNguoiDung,MatKhau) VALUES ('"
									+ textID.getText() + "','" + textTendangnhap.getText() + "','" + comboNhom.getText()
									+ "',N'" + textTennguoidung.getText() + "','" + getMD5(textMatkhau.getText())
									+ "')";
							int ketqua = connect.execUpdateQuery(insert);
							if (ketqua > 0) {
								textID.setText("");
								textMatkhau.setText("");
								textTendangnhap.setText("");
								textTennguoidung.setText("");
							}
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
			}
		});

		// Button Cancel
		// ---------------------------------------------------------------------------------------------------------
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

	// *********************************************************************************
	public String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
