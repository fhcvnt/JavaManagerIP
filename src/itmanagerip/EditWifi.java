package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class EditWifi {

	protected Shell shell;
	private Text textManufacturer;
	private Text textBuilding;
	private Text textFloor;
	private Text textWifiname24;

	private ConnectSQL connect;
	private int ngonngu = 0;
	private String sothe = "";
	private String userlogin = ""; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	private Text textPass24;
	private Text textWifiname5;
	private Text textPass5;
	private Text textIPWAN;
	private Text textIPLAN;
	private Text textLink;
	private Text textUsername;
	private Text textPassword;
	private Text textID;

	public void setLanguage(int ngonngu, String user) {
		this.ngonngu = ngonngu;
		userlogin = user;
	}

	public static void main(String[] args) {
		try {
			EditWifi window = new EditWifi();
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
		shell.setImage(SWTResourceManager.getImage(EditWifi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(807, 436);
		shell.setText("Edit wifi");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbID = new CLabel(composite, SWT.RIGHT);
		lbID.setText("ID");
		lbID.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbID.setBounds(15, 20, 145, 30);

		textID = new Text(composite, SWT.BORDER);
		textID.setTextLimit(20);
		textID.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textID.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textID.setBounds(166, 20, 222, 30);

		CLabel lbManufacturer = new CLabel(composite, SWT.NONE);
		lbManufacturer.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbManufacturer.setBounds(630, 20, 145, 30);
		lbManufacturer.setText("Manufacturer");

		textManufacturer = new Text(composite, SWT.BORDER);
		textManufacturer.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textManufacturer.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textManufacturer.setBounds(403, 20, 222, 30);
		textManufacturer.setTextLimit(20);

		CLabel lbBuilding = new CLabel(composite, SWT.RIGHT);
		lbBuilding.setText("Building");
		lbBuilding.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbBuilding.setBounds(9, 67, 145, 30);

		textBuilding = new Text(composite, SWT.BORDER);
		textBuilding.setEnabled(false);
		textBuilding.setBackground(SWTResourceManager.getColor(255, 255, 255));
		textBuilding.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textBuilding.setBounds(166, 67, 222, 30);

		textFloor = new Text(composite, SWT.BORDER);
		textFloor.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textFloor.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textFloor.setBounds(403, 67, 105, 30);
		textFloor.setTextLimit(2);

		CLabel lbFloor = new CLabel(composite, SWT.NONE);
		lbFloor.setText("Floor");
		lbFloor.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbFloor.setBounds(523, 67, 94, 30);

		CLabel lbWifiname24 = new CLabel(composite, SWT.RIGHT);
		lbWifiname24.setText("SSID 2.4GHz");
		lbWifiname24.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbWifiname24.setBounds(9, 112, 182, 30);

		textWifiname24 = new Text(composite, SWT.BORDER);
		textWifiname24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textWifiname24.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textWifiname24.setBounds(206, 112, 182, 30);
		textWifiname24.setTextLimit(50);

		textPass24 = new Text(composite, SWT.BORDER);
		textPass24.setBackground(SWTResourceManager.getColor(240, 230, 140));
		textPass24.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textPass24.setBounds(403, 112, 230, 30);
		textPass24.setTextLimit(50);

		CLabel lbPassword24 = new CLabel(composite, SWT.NONE);
		lbPassword24.setText("Password");
		lbPassword24.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbPassword24.setBounds(650, 112, 137, 30);

		CLabel lbWifiname5 = new CLabel(composite, SWT.RIGHT);
		lbWifiname5.setText("SSID 5GHz");
		lbWifiname5.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbWifiname5.setBounds(-15, 157, 206, 30);

		textWifiname5 = new Text(composite, SWT.BORDER);
		textWifiname5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textWifiname5.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textWifiname5.setBounds(206, 157, 182, 30);
		textWifiname5.setTextLimit(50);

		textPass5 = new Text(composite, SWT.BORDER);
		textPass5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textPass5.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textPass5.setBounds(403, 157, 230, 30);
		textPass5.setTextLimit(50);

		CLabel lbPassword5 = new CLabel(composite, SWT.NONE);
		lbPassword5.setText("Password");
		lbPassword5.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbPassword5.setBounds(650, 157, 137, 30);

		CLabel lbIPWAN = new CLabel(composite, SWT.RIGHT);
		lbIPWAN.setText("IP WAN");
		lbIPWAN.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbIPWAN.setBounds(9, 202, 105, 30);

		textIPWAN = new Text(composite, SWT.BORDER);
		textIPWAN.setEnabled(false);
		textIPWAN.setBackground(SWTResourceManager.getColor(255, 255, 255));
		textIPWAN.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textIPWAN.setBounds(129, 202, 259, 30);

		textIPLAN = new Text(composite, SWT.BORDER);
		textIPLAN.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textIPLAN.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textIPLAN.setBounds(403, 202, 230, 30);
		textIPLAN.setTextLimit(15);

		CLabel lbIPLAN = new CLabel(composite, SWT.NONE);
		lbIPLAN.setText("IP LAN");
		lbIPLAN.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbIPLAN.setBounds(650, 202, 94, 30);

		CLabel lbLink = new CLabel(composite, SWT.RIGHT);
		lbLink.setText("Link");
		lbLink.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbLink.setBounds(9, 247, 105, 30);

		textLink = new Text(composite, SWT.BORDER);
		textLink.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textLink.setForeground(SWTResourceManager.getColor(0, 0, 255));
		textLink.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textLink.setBounds(129, 247, 615, 30);
		textLink.setTextLimit(120);

		CLabel lbUsername = new CLabel(composite, SWT.RIGHT);
		lbUsername.setText("Username");
		lbUsername.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbUsername.setBounds(9, 292, 182, 30);

		textUsername = new Text(composite, SWT.BORDER);
		textUsername.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textUsername.setBounds(206, 292, 182, 30);
		textUsername.setTextLimit(10);

		textPassword = new Text(composite, SWT.BORDER);
		textPassword.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textPassword.setBackground(SWTResourceManager.getColor(240, 230, 140));
		textPassword.setBounds(403, 292, 230, 30);
		textPassword.setTextLimit(50);

		CLabel lbPassword = new CLabel(composite, SWT.NONE);
		lbPassword.setText("Password");
		lbPassword.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbPassword.setBounds(650, 292, 137, 30);

		Button btnSave = new Button(composite, SWT.NONE);
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSave.setImage(SWTResourceManager.getImage(EditWifi.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnSave.setBounds(251, 337, 137, 35);
		btnSave.setText("Save");

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setImage(SWTResourceManager.getImage(EditWifi.class, "/itmanagerip/Icon/button/cancel.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnCancel.setBounds(403, 337, 137, 35);

		// ngon ngu
		// **********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng viet
			shell.setText("Sửa wifi");
			lbID.setText("Mã Wifi");
			lbManufacturer.setText("Nhà Sản Xuất");
			lbBuilding.setText("Tòa Nhà");
			lbFloor.setText("Tầng");
			lbWifiname24.setText("Tên Wifi 2.4GHz");
			lbPassword24.setText("Mật Khẩu");
			lbWifiname5.setText("Tên Wifi 5GHz");
			lbPassword5.setText("Mật Khẩu");
			lbIPWAN.setText("IP WAN");
			lbIPLAN.setText("IP LAN");
			lbLink.setText("Liên Kết");
			lbUsername.setText("Tên Đăng Nhập");
			lbPassword.setText("Mật Khẩu");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else {
			// Tieng anh
			shell.setText("Edit wifi");
			lbID.setText("ID");
			lbManufacturer.setText("Manufacturer");
			lbBuilding.setText("Building");
			lbFloor.setText("Floor");
			lbWifiname24.setText("SSID 2.4GHz");
			lbPassword24.setText("Password");
			lbWifiname5.setText("SSID 5GHz");
			lbPassword5.setText("Password");
			lbIPWAN.setText("IP WAN");
			lbIPLAN.setText("IP LAN");
			lbLink.setText("Link");
			lbUsername.setText("Username");
			lbPassword.setText("Password");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
		}

		// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu Building, IP dựa vào số
		// thẻ
		// ***************************************************************************************************************************
		textID.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					String select = "";
					ResultSet result;
					select = "SELECT Building.Building,DanhSachIP.IP FROM DanhSachIP,Building WHERE DanhSachIP.MaBuilding=Building.MaBuilding AND DanhSachIP.SoThe='"
							+ textID.getText() + "'";
					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = connect.getStatement().executeQuery(select);
					textBuilding.setText("");
					textIPWAN.setText("");
					while (result.next()) {
						textBuilding.setText(result.getString(1));
						textIPWAN.setText(result.getString(2));
					}
					result.close();
					connect.closeStatement();
					;

					// lay du lieu cac o con lai neu co du lieu
					connect.setStatement();
					select = "";

					// lay du lieu
					textManufacturer.setText("");
					textFloor.setText("");
					textWifiname24.setText("");
					textPass24.setText("");
					textWifiname5.setText("");
					textPass5.setText("");
					textIPLAN.setText("");
					textLink.setText("");
					textUsername.setText("");
					textPassword.setText("");
					select = "SELECT Manufacturer,Floors,WifiName24GHz,Pass24GHz,WifiName5GHz,Pass5GHz,IPLAN,LINK,Username,Pass FROM Wifi WHERE Code='"
							+ textID.getText() + "'";
					result = connect.getStatement().executeQuery(select);
					while (result.next()) {
						textManufacturer.setText(result.getString(1));
						textFloor.setText(result.getString(2));
						textWifiname24.setText(result.getString(3));
						textPass24.setText(result.getString(4));
						textWifiname5.setText(result.getString(5));
						textPass5.setText(result.getString(6));
						textIPLAN.setText(result.getString(7));
						textLink.setText(result.getString(8));
						textUsername.setText(result.getString(9));
						textPassword.setText(result.getString(10));
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
						thongbao.setMessage("Crror!\n" + se.toString());
					}
					thongbao.open();
				}
			}
		});

		// tầng chỉ cho nhập số
		textFloor.addVerifyListener(new VerifyListener() {
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

		// Cancel
		// ==================================================================================================================
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					connect.closeConnection();
				} catch (SQLException e) {
				}
				shell.dispose();
			}
		});

		// Save
		// ==================================================================================================================
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}

					if (validateIP(textIPLAN.getText()) || textIPLAN.getText().isEmpty()) {
						if (!textPass24.getText().isEmpty() && !textPassword.getText().isEmpty()
								&& !textIPWAN.getText().isEmpty()) {
							try {
								String update = "UPDATE Wifi SET Code='" + textID.getText() + "',Manufacturer='"
										+ textManufacturer.getText() + "',Floors='" + textFloor.getText()
										+ "',WifiName24GHz=N'" + textWifiname24.getText() + "',Pass24GHz='"
										+ textPass24.getText() + "',WifiName5GHz=N'" + textWifiname5.getText()
										+ "',Pass5GHz='" + textPass5.getText() + "',IPLAN='" + textIPLAN.getText()
										+ "',LINK='" + textLink.getText() + "',Username='" + textUsername.getText()
										+ "',Pass='" + textPassword.getText() + "',UserUpdate='" + userlogin
										+ "' WHERE Code='" + sothe + "'";
								sothe = textID.getText();
								int result = connect.execUpdateQuery(update);
								if (result > 0) {
									MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
									if (ngonngu == 0) {
										thongbao.setText("Thông báo");
										thongbao.setMessage("Lưu thành công!");
									} else {
										thongbao.setText("Notice");
										thongbao.setMessage("Save successful!");
									}
									thongbao.open();
									connect.closeStatement();
									connect.closeConnection();
									shell.dispose();
								}
							} catch (Exception ex) {
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Thông báo lỗi");
									thongbao.setMessage("Lưu Không thành công!");
								} else {
									thongbao.setText("Notice error");
									thongbao.setMessage("Save failed!");
								}
								thongbao.open();
							}
							connect.closeStatement();
						}
					} else if (!validateIP(textIPLAN.getText())) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("IP LAN không đúng định dạng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("IP LAN: wrong format!");
						}
						thongbao.open();
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
	}

	// **********************************************************************************************************************
	// Lay du lieu ban dau
	public void setDataDefault(String sothe, String building, String ipwan) {
		this.sothe = sothe;
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();

			String select = "";
			ResultSet result;
			// lay du lieu
			select = "SELECT Manufacturer,Floors,WifiName24GHz,Pass24GHz,WifiName5GHz,Pass5GHz,IPLAN,LINK,Username,Pass FROM Wifi WHERE Code='"
					+ sothe + "'";
			result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				textManufacturer.setText(result.getString(1));
				textFloor.setText(result.getString(2));
				textWifiname24.setText(result.getString(3));
				textPass24.setText(result.getString(4));
				textWifiname5.setText(result.getString(5));
				textPass5.setText(result.getString(6));
				textIPLAN.setText(result.getString(7));
				textLink.setText(result.getString(8));
				textUsername.setText(result.getString(9));
				textPassword.setText(result.getString(10));
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
		textID.setText(sothe);
		textIPWAN.setText(ipwan);
		textBuilding.setText(building);
	}

	// Kiểm tra định dạng IP
	// ==============================================================================================================
	public static boolean validateIP(final String ip) {
		String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

		return ip.matches(PATTERN);
	}
}
