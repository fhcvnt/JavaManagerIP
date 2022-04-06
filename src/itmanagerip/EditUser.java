package itmanagerip;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

public class EditUser {

	protected Shell shell;
	private Text textID;
	private Text textTendangnhap;
	private Text textTennguoidung;
	private Text textMatkhau;
	private ConnectSQL connect;

	public static void main(String[] args) {
		try {
			EditUser window = new EditUser();
			window.createContents("", "", "", "", "", "", 0);
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
	protected void createContents(String sothe, String tendangnhap, String nhom, String tennguoidung, String userlogin,
			String grouplogin, int ngonngu) {
		shell = new Shell(SWT.MIN);
		shell.setImage(SWTResourceManager.getImage(EditUser.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(642, 394);
		shell.setText("Sửa người dùng");
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
		textID.setText(sothe);

		CLabel lbNhom = new CLabel(composite, SWT.NONE);
		lbNhom.setText("Nhóm");
		lbNhom.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbNhom.setAlignment(SWT.RIGHT);
		lbNhom.setBounds(21, 133, 169, 30);

		CCombo comboNhom = new CCombo(composite, SWT.BORDER);
		if (grouplogin.equals("Manager")) {
			comboNhom.setItems(new String[] { "Manager", "Admin", "Translate", "User", "Disable" });
		} else if (grouplogin.equals("Admin")) {
			if (userlogin.equals("21608")) {
				comboNhom.setItems(new String[] { "Manager", "Admin", "Translate", "User", "Disable" });
			} else {
				comboNhom.setItems(new String[] { "Admin", "Translate", "User", "Disable" });
			}
		} else {
			comboNhom.setItems(new String[] { grouplogin });
		}
		comboNhom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboNhom.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		comboNhom.setBounds(205, 133, 245, 30);
		comboNhom.setText(nhom);
		comboNhom.setEditable(false);

		CLabel lbTendangnhap = new CLabel(composite, SWT.NONE);
		lbTendangnhap.setText("Tên Đăng Nhập");
		lbTendangnhap.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbTendangnhap.setAlignment(SWT.RIGHT);
		lbTendangnhap.setBounds(21, 80, 169, 30);

		textTendangnhap = new Text(composite, SWT.BORDER);
		textTendangnhap.setEditable(false);
		textTendangnhap.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textTendangnhap.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textTendangnhap.setBounds(205, 80, 245, 30);
		textTendangnhap.setTextLimit(20);
		textTendangnhap.setText(tendangnhap);

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
		textTennguoidung.setText(tennguoidung);

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
		btnSave.setImage(SWTResourceManager.getImage(EditUser.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		btnSave.setBounds(286, 294, 146, 35);
		btnSave.setText("Save");

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setImage(SWTResourceManager.getImage(EditUser.class, "/itmanagerip/Icon/button/cancel.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setText("Cancel");
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		btnCancel.setBounds(451, 294, 146, 35);

		// Thay đổi ngôn ngữ
		// -------------------------------------------------------------------------
		if (ngonngu == 0) {
			shell.setText("Sửa người dùng");
			lbID.setText("Số Thẻ");
			lbNhom.setText("Nhóm");
			lbTendangnhap.setText("Tên Đăng Nhập");
			lbTennguoidung.setText("Tên Người Dùng");
			lbMatkhau.setText("Mật Khẩu");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else if (ngonngu == 1) {
			shell.setText("Edit Users");
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
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}

					if (!textID.getText().isEmpty() && !textTendangnhap.getText().isEmpty()
							&& !textTennguoidung.getText().isEmpty() && !comboNhom.getText().isEmpty()) {
						String pass = textMatkhau.getText().isEmpty() ? ""
								: ",MatKhau='" + getMD5(textMatkhau.getText()) + "'";

						String update = "UPDATE NguoiDung SET MaNguoiDung='" + textID.getText() + "',TenNhom='"
								+ comboNhom.getText() + "',TenNguoiDung=N'" + textTennguoidung.getText() + "'" + pass
								+ " WHERE TenDangNhap='" + textTendangnhap.getText() + "'";
						int ketqua = connect.execUpdateQuery(update);
						if (ketqua > 0) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Sửa thành công!");
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage("Edit successful!");
							}
							thongbao.open();
							connect.closeStatement();
							connect.closeConnection();
							shell.dispose();
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
