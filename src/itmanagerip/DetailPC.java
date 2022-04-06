package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class DetailPC {

	protected Shell shell;
	private Text textInternet;
	private Text textSoftware;
	private Text textServername;
	private Text textDatasharename;
	private Text textUsername;
	private Text textPassword;
	private Combo comboPermissions;
	private Button checkUSB;

	private ConnectSQL connect;
	private int ngonngu = 1;
	private String userlogin = ""; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	// du lieu ban dau
	private String internet = "";
	private String soft = "";
	private String servername = "";
	private boolean usb;
	private String sothe = "";
	private String datasharename = "";
	private String username = "";
	private String pass = "";
	private String permissions = "";

	public void setLanguage(int ngonngu, String user) {
		this.ngonngu = ngonngu;
		userlogin = user;
	}

	public static void main(String[] args) {
		try {
			DetailPC window = new DetailPC();
			window.createContents();
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
	protected void createContents() {
		shell = new Shell(SWT.MIN);
		shell.setImage(SWTResourceManager.getImage(DetailPC.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1117, 552);
		shell.setText("Detail");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lblInternet = new CLabel(composite, SWT.NONE);
		lblInternet.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblInternet.setBounds(24, 10, 130, 29);
		lblInternet.setText("Internet");

		textInternet = new Text(composite, SWT.BORDER | SWT.MULTI);
		textInternet.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textInternet.setBounds(24, 55, 545, 190);

		CLabel lblSoftware = new CLabel(composite, SWT.NONE);
		lblSoftware.setText("Software");
		lblSoftware.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblSoftware.setBounds(24, 263, 130, 29);

		textSoftware = new Text(composite, SWT.BORDER | SWT.MULTI);
		textSoftware.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textSoftware.setBounds(24, 298, 545, 190);

		CLabel lblDataShare = new CLabel(composite, SWT.NONE);
		lblDataShare.setText("Data Share");
		lblDataShare.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblDataShare.setBounds(618, 10, 226, 29);

		CLabel lblServerName = new CLabel(composite, SWT.RIGHT);
		lblServerName.setText("Server Name");
		lblServerName.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblServerName.setBounds(612, 55, 182, 29);

		textServername = new Text(composite, SWT.BORDER);
		textServername.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textServername.setBounds(808, 55, 263, 30);

		CLabel lblDataShareName = new CLabel(composite, SWT.RIGHT);
		lblDataShareName.setText("Data Share Name");
		lblDataShareName.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblDataShareName.setBounds(612, 110, 182, 29);

		textDatasharename = new Text(composite, SWT.BORDER);
		textDatasharename.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textDatasharename.setBounds(808, 110, 263, 30);

		CLabel lblUsername = new CLabel(composite, SWT.RIGHT);
		lblUsername.setText("Username");
		lblUsername.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblUsername.setBounds(612, 165, 182, 29);

		textUsername = new Text(composite, SWT.BORDER);
		textUsername.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textUsername.setBounds(808, 165, 263, 30);

		CLabel lblPassword = new CLabel(composite, SWT.RIGHT);
		lblPassword.setText("Password");
		lblPassword.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblPassword.setBounds(612, 220, 182, 29);

		textPassword = new Text(composite, SWT.BORDER);
		textPassword.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textPassword.setBounds(808, 220, 263, 30);

		CLabel lbPermissions = new CLabel(composite, SWT.RIGHT);
		lbPermissions.setText("Permissions");
		lbPermissions.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbPermissions.setBounds(612, 275, 182, 29);

		comboPermissions = new Combo(composite, SWT.NONE);
		comboPermissions.setItems(new String[] { "Read", "Write", "Write & Read" });
		comboPermissions.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		comboPermissions.setBounds(808, 275, 263, 30);
		comboPermissions.select(2);

		checkUSB = new Button(composite, SWT.CHECK);
		checkUSB.setText("USB");
		checkUSB.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		checkUSB.setBounds(664, 363, 146, 45);

		Button btnSave = new Button(composite, SWT.NONE);
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnSave.setImage(SWTResourceManager.getImage(DetailPC.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnSave.setBounds(647, 453, 137, 35);
		btnSave.setText("Save");

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnCancel.setImage(SWTResourceManager.getImage(DetailPC.class, "/itmanagerip/Icon/button/cancelxanh30.png"));
		btnCancel.setText("Cancel");
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnCancel.setBounds(816, 453, 137, 35);

		Label label1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label1.setBounds(24, 40, 1047, 2);

		Label label2 = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		label2.setBounds(593, 10, 2, 479);

		Label label3 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label3.setBounds(593, 340, 478, 2);

		Label label4 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);

		label4.setBounds(593, 426, 478, 2);

		// ngon ngu
		// **********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng viet
			shell.setText("Chi tiết");
			lblInternet.setText("Mạng");
			lblSoftware.setText("Phần mềm");
			lblDataShare.setText("Ổ đĩa chung");
			lblServerName.setText("Tên máy chủ");
			lblDataShareName.setText("Tên chia sẽ");
			lblUsername.setText("Tên đăng nhập");
			lblPassword.setText("Mật khẩu");
			lbPermissions.setText("Quyền");
			checkUSB.setText("USB");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else {
			// Tieng anh
			shell.setText("Detail");
			lblInternet.setText("Internet");
			lblSoftware.setText("Software");
			lblDataShare.setText("Data Share");
			lblServerName.setText("Server Name");
			lblDataShareName.setText("Data Share Name");
			lblUsername.setText("Username");
			lblPassword.setText("Password");
			lbPermissions.setText("Permissions");
			checkUSB.setText("USB");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
		}

		// Save
		// ==================================================================================================================
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				boolean saveinternet = true;
				boolean savesoft = true;
				boolean savedatashare = true;
				boolean saveusb = true;
				boolean savemail = true;
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}

					// Xu ly internet
					// ----------------------------------------------------------------
					if (!textInternet.getText().equals(internet) && internet.isEmpty()) {
						// Add
						try {
							String insert = "INSERT INTO Internet( SoThe ,Website ,NgayCapNhat,UserUpdate ) VALUES  ( '"
									+ sothe + "' ,N'" + textInternet.getText() + "' ,GETDATE(),'" + userlogin + "' )";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								saveinternet = true;
							}
							connect.closeStatement();
						} catch (Exception ex) {
							saveinternet = false;
						}

					} else if (!textInternet.getText().equals(internet) && !internet.isEmpty()) {
						// Edit
						try {
							String update = "UPDATE Internet SET Website=N'" + textInternet.getText()
									+ "',NgayCapNhat=GETDATE(),UserUpdate='" + userlogin + "' WHERE SoThe='" + sothe
									+ "'";

							int result = connect.execUpdateQuery(update);
							if (result > 0) {
								saveinternet = true;
							}
						} catch (Exception ex) {
							saveinternet = false;
						}
						connect.closeStatement();
					}

					// Xu ly software
					// ----------------------------------------------------------------
					if (!textSoftware.getText().equals(soft) && soft.isEmpty()) {
						// Add
						try {
							String insert = "INSERT INTO Software( SoThe ,SoftName ,NgayCapNhat,UserUpdate ) VALUES  ( '"
									+ sothe + "' ,N'" + textSoftware.getText() + "' ,GETDATE(),'" + userlogin + "' )";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								savesoft = true;
							}
							connect.closeStatement();
						} catch (Exception ex) {
							savesoft = false;
						}

					} else if (!textSoftware.getText().equals(soft) && !soft.isEmpty()) {
						// Edit
						try {
							String update = "UPDATE Software SET SoftName=N'" + textSoftware.getText()
									+ "',NgayCapNhat=GETDATE(),UserUpdate='" + userlogin + "' WHERE SoThe='" + sothe
									+ "'";

							int result = connect.execUpdateQuery(update);
							if (result > 0) {
								savesoft = true;
							}
						} catch (Exception ex) {
							savesoft = false;
						}
						connect.closeStatement();
					}

					// Xu ly Data share
					// ----------------------------------------------------------------
					if (!textServername.getText().equals(servername) && servername.isEmpty()) {
						// Add
						try {
							String insert = "INSERT INTO DataShare( SoThe ,ServerName ,DataShareName ,UserName ,Passwd ,Quyen ,NgayCapNhat,UserUpdate ) VALUES  ( '"
									+ sothe + "' ,N'" + textServername.getText() + "' ,N'" + textDatasharename.getText()
									+ "' ,'" + textUsername.getText() + "' ,'" + textPassword.getText() + "' ,'"
									+ comboPermissions.getText() + "' ,GETDATE(),'" + userlogin + "')";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								savedatashare = true;
							}
							connect.closeStatement();
						} catch (Exception ex) {
							savedatashare = false;
						}

					} else if (!textServername.getText().equals(servername) && !servername.isEmpty()) {
						// Edit
						try {
							String update = "UPDATE DataShare SET ServerName=N'" + textServername.getText()
									+ "', DataShareName=N'" + textDatasharename.getText() + "', UserName='"
									+ textUsername.getText() + "', Passwd='" + textPassword.getText() + "', Quyen='"
									+ comboPermissions.getText() + "',NgayCapNhat=GETDATE(),UserUpdate='" + userlogin
									+ "' WHERE SoThe='" + sothe + "'";

							int result = connect.execUpdateQuery(update);
							if (result > 0) {
								savedatashare = true;
							}
						} catch (Exception ex) {
							savedatashare = false;
						}
						connect.closeStatement();
					}

					// Xu ly USB
					// ----------------------------------------------------------------
					if (checkUSB.getSelection() == true && !usb) {
						// Add
						try {
							String insert = "INSERT INTO USB( SoThe ,NgayCapNhat,UserUpdate ) VALUES  ( '" + sothe
									+ "' ,GETDATE(),'" + userlogin + "' )";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								saveusb = true;
							}
							connect.closeStatement();
						} catch (Exception ex) {
							saveusb = false;
						}

					} else if (checkUSB.getSelection() == false && usb) {
						// Edit
						try {
							String update = "DELETE USB WHERE SoThe='" + sothe + "'";

							int result = connect.execUpdateQuery(update);
							if (result > 0) {
								saveusb = true;
							}
						} catch (Exception ex) {
							saveusb = false;
						}
						connect.closeStatement();
					} else {
						saveusb = true;
					}

					if (saveinternet && savesoft && savedatashare && saveusb && savemail) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Lưu thành công!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("Save successful!");
						}
						thongbao.open();
					} else if (!saveinternet && !savesoft && !savedatashare && !saveusb && !savemail) {
						if (!saveinternet) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Lưu không thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Save failed!");
							}
							thongbao.open();
						}
					} else {
						if (!saveinternet) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo - Mạng");
								thongbao.setMessage("Lưu không thành công - Mạng!");
							} else {
								thongbao.setText("Notice - Internet");
								thongbao.setMessage("Save failed - Internet!");
							}
							thongbao.open();
						}

						if (!savesoft) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo - Phần mềm");
								thongbao.setMessage("Lưu không thành công - Phần mềm!");
							} else {
								thongbao.setText("Notice - Software");
								thongbao.setMessage("Save failed - Software!");
							}
							thongbao.open();
						}

						if (!savedatashare) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo - Ổ đĩa dùng chung");
								thongbao.setMessage("Lưu không thành công - Ổ đĩa dùng chung!");
							} else {
								thongbao.setText("Notice - Data share");
								thongbao.setMessage("Save failed - Data share!");
							}
							thongbao.open();
						}

						if (!saveusb) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo - USB");
								thongbao.setMessage("Lưu không thành công - USB!");
							} else {
								thongbao.setText("Notice - USB");
								thongbao.setMessage("Save failed - USB!");
							}
							thongbao.open();
						}

						if (!savemail) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo - Mail ngoài");
								thongbao.setMessage("Lưu không thành công - Mail ngoài!");
							} else {
								thongbao.setText("Notice - Mail out");
								thongbao.setMessage("Save failed - Mail out!");
							}
							thongbao.open();
						}
					}

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
		});

		// Cancel
		// ==================================================================================================================
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

	// **********************************************************************************************************************
	// Lay du lieu ban dau
	public void setDataDefault(String sothe) {
		this.sothe = sothe;

		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}

			connect.setStatement();
			String select = "";
			ResultSet result;

			// lay du lieu internet
			select = "SELECT Website FROM Internet WHERE SoThe='" + sothe + "'";
			result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				internet = result.getString(1);
			}
			result.close();
			connect.closeStatement();

			// lay du lieu software
			connect.setStatement();
			select = "SELECT SoftName FROM Software WHERE SoThe='" + sothe + "'";
			result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				soft = result.getString(1);
			}
			result.close();
			connect.closeStatement();

			// lay du lieu Data share
			connect.setStatement();
			select = "SELECT ServerName,DataShareName,UserName,Passwd,Quyen FROM DataShare WHERE SoThe='" + sothe + "'";
			result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				servername = result.getString(1);
				datasharename = result.getString(2);
				username = result.getString(3);
				pass = result.getString(4);
				permissions = result.getString(5);
			}
			result.close();
			connect.closeStatement();

			// lay du lieu USB
			connect.setStatement();
			select = "SELECT SoThe FROM USB WHERE SoThe='" + sothe + "'";
			result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				usb = result.getString(1).isEmpty() ? false : true;
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

		textInternet.setText(internet);
		textSoftware.setText(soft);
		textServername.setText(servername);
		textDatasharename.setText(datasharename);
		textUsername.setText(username);
		textPassword.setText(pass);
		if (permissions.equals("Read")) {
			comboPermissions.select(0);
		} else if (permissions.equals("Write")) {
			comboPermissions.select(1);
		} else {
			comboPermissions.select(2);
		}
		checkUSB.setSelection(usb);
	}

}
