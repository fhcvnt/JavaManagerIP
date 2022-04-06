package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class EditDataShare {

	private ConnectSQL connect;
	private String userlogin = ""; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	protected Shell shell;
	private Text textSothe;
	private Text textHoten;
	private Text textDatasharename;

	private int language = 1;
	private Text textDonvi;
	private Text textIP;
	private Text textServername;
	private Text textUsername;
	private Text textPassword;
	private String editsothe = "";
	private String edithoten = "";
	private String editdonvi = "";
	private String editip = "";
	private String editservername = "";
	private String editdatasharename = "";
	private String editusername = "";
	private String editpassword = "";
	private String editpermissions = "";

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int ngonngu, String user) {
		language = ngonngu;
		userlogin = user;
	}

	public static void main(String[] args) {
		try {
			EditDataShare window = new EditDataShare();
			window.open();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
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
		shell.setSize(847, 443);
		shell.setText("Edit data share");
		shell.setImage(SWTResourceManager.getImage(EditDataShare.class, "/itmanagerip/Icon/IP64.png"));

		CLabel lbSothe = new CLabel(shell, SWT.NONE);
		lbSothe.setAlignment(SWT.RIGHT);
		lbSothe.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbSothe.setBounds(10, 29, 101, 35);
		lbSothe.setText("Số Thẻ");

		textSothe = new Text(shell, SWT.BORDER);
		textSothe.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textSothe.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textSothe.setBounds(117, 29, 115, 35);
		textSothe.setTextLimit(12);

		CLabel lbhoten = new CLabel(shell, SWT.NONE);
		lbhoten.setAlignment(SWT.RIGHT);
		lbhoten.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbhoten.setBounds(244, 29, 166, 35);
		lbhoten.setText("Họ Tên");

		textHoten = new Text(shell, SWT.BORDER);
		textHoten.setEnabled(false);
		textHoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textHoten.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textHoten.setBounds(431, 29, 292, 35);

		CLabel lbDonvi = new CLabel(shell, SWT.NONE);
		lbDonvi.setAlignment(SWT.RIGHT);
		lbDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbDonvi.setBounds(10, 83, 136, 35);
		lbDonvi.setText("Đơn Vị");

		textDonvi = new Text(shell, SWT.BORDER);
		textDonvi.setEnabled(false);
		textDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textDonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textDonvi.setBounds(152, 83, 259, 35);

		CLabel lbIP = new CLabel(shell, SWT.NONE);
		lbIP.setAlignment(SWT.RIGHT);
		lbIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbIP.setBounds(431, 83, 52, 35);
		lbIP.setText("IP");

		textIP = new Text(shell, SWT.BORDER);
		textIP.setEnabled(false);
		textIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textIP.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textIP.setBounds(489, 83, 252, 35);

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(EditDataShare.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setForeground(SWTResourceManager.getColor(0, 0, 255));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 19, SWT.BOLD));
		btnSave.setBounds(193, 327, 147, 40);
		btnSave.setText("Save");

		CLabel lbDatasharename = new CLabel(shell, SWT.NONE);
		lbDatasharename.setAlignment(SWT.RIGHT);
		lbDatasharename.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbDatasharename.setBounds(373, 139, 202, 39);
		lbDatasharename.setText("Data Share Name");

		textDatasharename = new Text(shell, SWT.BORDER);
		textDatasharename.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textDatasharename.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textDatasharename.setBounds(592, 139, 215, 35);
		textDatasharename.setTextLimit(3000);

		CLabel lbImage = new CLabel(shell, SWT.NONE);
		lbImage.setBackground(
				SWTResourceManager.getImage(EditDataShare.class, "/itmanagerip/Icon/bieutuong/datashare.png"));
		lbImage.setBounds(567, 250, 240, 147);

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setImage(SWTResourceManager.getImage(EditDataShare.class, "/itmanagerip/Icon/button/cancel30.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 19, SWT.BOLD));
		btnCancel.setBounds(357, 327, 147, 40);

		CLabel lbServername = new CLabel(shell, SWT.NONE);
		lbServername.setText("Server Name");
		lbServername.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbServername.setAlignment(SWT.RIGHT);
		lbServername.setBounds(9, 139, 189, 35);

		textServername = new Text(shell, SWT.BORDER);
		textServername.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textServername.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textServername.setBounds(214, 139, 153, 35);

		CLabel lbUsername = new CLabel(shell, SWT.NONE);
		lbUsername.setText("Username");
		lbUsername.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbUsername.setAlignment(SWT.RIGHT);
		lbUsername.setBounds(10, 196, 182, 39);

		textUsername = new Text(shell, SWT.BORDER);
		textUsername.setTextLimit(3000);
		textUsername.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textUsername.setBounds(209, 196, 173, 35);

		CLabel lbPassword = new CLabel(shell, SWT.NONE);
		lbPassword.setText("Password");
		lbPassword.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbPassword.setAlignment(SWT.RIGHT);
		lbPassword.setBounds(406, 196, 136, 39);

		textPassword = new Text(shell, SWT.BORDER);
		textPassword.setTextLimit(3000);
		textPassword.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textPassword.setBounds(555, 196, 252, 35);

		CLabel lbPermissions = new CLabel(shell, SWT.NONE);
		lbPermissions.setText("Permissions");
		lbPermissions.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbPermissions.setAlignment(SWT.RIGHT);
		lbPermissions.setBounds(10, 252, 160, 39);

		Combo comboPermissions = new Combo(shell, SWT.NONE);
		comboPermissions.setItems(new String[] { "Read", "Write", "Write & Read" });
		comboPermissions.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		comboPermissions.setBounds(193, 252, 189, 35);
		comboPermissions.select(2);

		// gán giá trị ban đầu
		textSothe.setText(editsothe);
		textHoten.setText(edithoten);
		textDonvi.setText(editdonvi);
		textIP.setText(editip);
		textServername.setText(editservername);
		textDatasharename.setText(editdatasharename);
		textUsername.setText(editusername);
		textPassword.setText(editpassword);
		comboPermissions.setText(editpermissions);

		// Gán ngôn ngữ
		if (getLanguage() == 0) {
			// Tieng Viet

			shell.setText("Sửa ổ đĩa dùng chung");
			lbSothe.setText("Số Thẻ");
			lbhoten.setText("Họ Tên");
			lbDonvi.setText("Đơn Vị");
			lbIP.setText("IP");
			lbServername.setText("Tên Máy Chủ");
			lbDatasharename.setText("Tên Chia Sẽ");
			lbUsername.setText("Tên Đăng Nhập");
			lbPassword.setText("Mật Khẩu");
			lbPermissions.setText("Quyền");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else {
			// Tieng Anh

			shell.setText("Edit data share");
			lbSothe.setText("ID");
			lbhoten.setText("Person Name");
			lbDonvi.setText("Department");
			lbIP.setText("IP");
			lbServername.setText("Server Name");
			lbDatasharename.setText("Data Share Name");
			lbUsername.setText("Username");
			lbPassword.setText("Password");
			lbPermissions.setText("Permissions");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
		}

		// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị, IP dựa vào số
		// thẻ
		// ***************************************************************************************************************************
		textSothe.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					String select = "";
					ResultSet result;

					select = "SELECT DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP FROM DanhSachIP,DonVi WHERE DanhSachIP.MaDonVi=DonVi.MaDonVi AND DanhSachIP.SoThe='"
							+ textSothe.getText() + "'";

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = connect.getStatement().executeQuery(select);

					textHoten.setText("");
					textDonvi.setText("");
					textIP.setText("");

					while (result.next()) {
						textHoten.setText(result.getString(1));
						textDonvi.setText(result.getString(2));
						textIP.setText(result.getString(3));
					}

					result.close();
					connect.closeStatement();

				} catch (SQLException se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					if (language == 0) {
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

		// ***************************************************************************************************************************
		// Lưu
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (!textSothe.getText().isEmpty() && !textHoten.getText().isEmpty()
						&& !textServername.getText().isEmpty() && !textDatasharename.getText().isEmpty()) {
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}

						try {
							String update = "UPDATE DataShare SET SoThe='" + textSothe.getText() + "', ServerName=N'"
									+ textServername.getText() + "', DataShareName=N'" + textDatasharename.getText()
									+ "', UserName='" + textUsername.getText() + "', Passwd='" + textPassword.getText()
									+ "', Quyen='" + comboPermissions.getText() + "',NgayCapNhat=GETDATE(),UserUpdate='"
									+ userlogin + "' WHERE SoThe='" + editsothe + "'";

							int result = connect.execUpdateQuery(update);
							if (result > 0) {
								try {
									textSothe.setText("");
									textHoten.setText("");
									textIP.setText("");
									textDatasharename.setText("");
									textServername.setText("");
									textUsername.setText("");
									textPassword.setText("");

									MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
									if (language == 0) {
										thongbao.setText("Thông báo");
										thongbao.setMessage("Lưu thành công!");
									} else {
										thongbao.setText("Notice");
										thongbao.setMessage("Save successful!");
									}
									thongbao.open();
									shell.dispose();
								} catch (Exception ex) {

								}
							}
							connect.closeStatement();
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (language == 0) {
								thongbao.setText("Thông báo lỗi");
								thongbao.setMessage("Lưu không thành công!\n" + ex.toString());
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage("Save failed!\n" + ex.toString());
							}
							thongbao.open();
						}

					} catch (Exception se) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Lỗi!\n" + se.toString());
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Error!\n" + se.toString());
						}
						thongbao.open();
					}
				} else {
					if (textSothe.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Số thẻ trống!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Code is empty!");
						}
						thongbao.open();
					} else if (textHoten.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Số thẻ chưa tồn tại trong danh sách IP!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Code does not exist in the IP list!");
						}
						thongbao.open();
					} else if (textDatasharename.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Tên chia sẽ rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Datashare name is empty!");
						}
						thongbao.open();
					} else if (textServername.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Tên máy chủ rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Server name is empty!");
						}
						thongbao.open();
					}
				}
			}
		});

		// Cancel
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

	// Lấy dữ liệu mặc định từ dòng chọn của cửa sổ Datashare
	public void setDataDataShareDefault(String sothe, String hoten, String donvi, String ip, String servername,
			String datasharename, String username, String password, String permissions) {
		editsothe = sothe;
		edithoten = hoten;
		editdonvi = donvi;
		editip = ip;
		editservername = servername;
		editdatasharename = datasharename;
		editusername = username;
		editpassword = password;
		editpermissions = permissions;
	}
}
