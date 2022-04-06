package itmanagerip;

import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class NewMail {
	private ConnectSQL connect;
	private String userlogin = ""; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	protected Shell shell;
	private Text textSothe;
	private Text textHoten;
	private Text textNote;

	private int language = 1;
	private Text textDonvi;
	private Text textFullmail;
	private Text textMailname;
	private Text textPassword;
	private Text txtNgaytao;
	private boolean isaddsuccess = false;

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int ngonngu, String user) {
		language = ngonngu;
		userlogin = user;
	}

	public static void main(String[] args) {
		try {
			NewMail window = new NewMail();
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
		shell.setSize(1189, 560);
		shell.setText("New Mail");
		shell.setImage(SWTResourceManager.getImage(NewMail.class, "/itmanagerip/Icon/IP64.png"));

		CLabel lbSothe = new CLabel(shell, SWT.NONE);
		lbSothe.setAlignment(SWT.RIGHT);
		lbSothe.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbSothe.setBounds(10, 29, 115, 35);
		lbSothe.setText("Số Thẻ");

		textSothe = new Text(shell, SWT.BORDER);
		textSothe.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textSothe.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textSothe.setBounds(131, 29, 115, 35);
		textSothe.setTextLimit(12);

		CLabel lbhoten = new CLabel(shell, SWT.NONE);
		lbhoten.setAlignment(SWT.RIGHT);
		lbhoten.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbhoten.setBounds(257, 29, 153, 35);
		lbhoten.setText("Họ Tên");

		textHoten = new Text(shell, SWT.BORDER);
		textHoten.setEnabled(false);
		textHoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textHoten.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textHoten.setBounds(431, 29, 267, 35);

		CLabel lbDonvi = new CLabel(shell, SWT.NONE);
		lbDonvi.setAlignment(SWT.RIGHT);
		lbDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbDonvi.setBounds(700, 29, 171, 35);
		lbDonvi.setText("Đơn Vị");

		textDonvi = new Text(shell, SWT.BORDER);
		textDonvi.setEnabled(false);
		textDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textDonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textDonvi.setBounds(889, 29, 259, 35);

		CLabel lbFullmail = new CLabel(shell, SWT.NONE);
		lbFullmail.setAlignment(SWT.RIGHT);
		lbFullmail.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbFullmail.setBounds(10, 84, 115, 35);
		lbFullmail.setText("Full Mail");

		textFullmail = new Text(shell, SWT.BORDER);
		textFullmail.setForeground(SWTResourceManager.getColor(0, 0, 255));
		textFullmail.setText("@lacty2.com.vn");
		textFullmail.setTextLimit(40);
		textFullmail.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textFullmail.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textFullmail.setBounds(131, 84, 297, 35);

		CLabel lbMailname = new CLabel(shell, SWT.NONE);
		lbMailname.setText("Mail Name");
		lbMailname.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbMailname.setAlignment(SWT.RIGHT);
		lbMailname.setBounds(431, 84, 144, 35);

		textMailname = new Text(shell, SWT.BORDER);
		textMailname.setTextLimit(40);
		textMailname.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textMailname.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textMailname.setBounds(594, 84, 259, 35);

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(NewMail.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setForeground(SWTResourceManager.getColor(0, 0, 255));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 19, SWT.BOLD));
		btnSave.setBounds(864, 84, 130, 37);
		btnSave.setText("Save");

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setImage(SWTResourceManager.getImage(NewMail.class, "/itmanagerip/Icon/button/cancel30.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 19, SWT.BOLD));
		btnCancel.setBounds(1012, 84, 136, 37);

		CLabel lbPassword = new CLabel(shell, SWT.NONE);
		lbPassword.setText("Password");
		lbPassword.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbPassword.setAlignment(SWT.RIGHT);
		lbPassword.setBounds(10, 137, 153, 35);

		textPassword = new Text(shell, SWT.BORDER);
		textPassword.setTextLimit(40);
		textPassword.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textPassword.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textPassword.setBounds(169, 137, 294, 35);

		CLabel lbPermission = new CLabel(shell, SWT.NONE);
		lbPermission.setText("Permission");
		lbPermission.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbPermission.setAlignment(SWT.RIGHT);
		lbPermission.setBounds(469, 137, 171, 35);

		CCombo cbPermission = new CCombo(shell, SWT.BORDER);
		cbPermission.setItems(new String[] { "Mail nội bộ", "Mail ngoài" });
		cbPermission.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbPermission.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbPermission.setBounds(657, 137, 196, 35);
		cbPermission.select(0);

		CLabel lbNgaytao = new CLabel(shell, SWT.NONE);
		lbNgaytao.setText("Ngày Tạo");
		lbNgaytao.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbNgaytao.setAlignment(SWT.RIGHT);
		lbNgaytao.setBounds(10, 193, 166, 35);

		txtNgaytao = new Text(shell, SWT.BORDER);
		txtNgaytao.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		txtNgaytao.setBounds(193, 193, 189, 35);
		txtNgaytao.setTextLimit(20);

		CLabel lbNote = new CLabel(shell, SWT.NONE);
		lbNote.setAlignment(SWT.RIGHT);
		lbNote.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbNote.setBounds(10, 342, 153, 39);
		lbNote.setText("Ghi Chú");

		textNote = new Text(shell, SWT.BORDER | SWT.MULTI);
		textNote.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNote.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textNote.setBounds(169, 251, 684, 244);
		textNote.setTextLimit(3000);

		CLabel lbImage = new CLabel(shell, SWT.NONE);
		lbImage.setBackground(SWTResourceManager.getImage(NewMail.class, "/itmanagerip/Icon/bieutuong/mail290.png"));
		lbImage.setBounds(870, 193, 290, 290);

		// Gán ngôn ngữ
		if (getLanguage() == 0) {
			// Tieng Viet

			shell.setText("Thêm Mail");
			lbSothe.setText("Số Thẻ");
			lbhoten.setText("Họ Tên");
			lbDonvi.setText("Đơn Vị");
			lbFullmail.setText("Mail");
			lbMailname.setText("Tên Mail");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
			lbPassword.setText("Mật Khẩu");
			lbPermission.setText("Quyền");
			lbNgaytao.setText("Ngày Tạo");
			lbNote.setText("Ghi Chú");
		} else {
			// Tieng Anh

			shell.setText("New Mail");
			lbSothe.setText("ID");
			lbhoten.setText("Name");
			lbDonvi.setText("Deparment");
			lbFullmail.setText("Full Mail");
			lbMailname.setText("Mail Name");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
			lbPassword.setText("Password");
			lbPermission.setText("Permission");
			lbNgaytao.setText("Date Created");
			lbNote.setText("Note");
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
					ResultSet result;

					String select = "SELECT Data_Person.Person_Name,Data_Department.Department_Name FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
							+ textSothe.getText() + "' OR Data_Person.Person_ID='0" + textSothe.getText()
							+ "' OR Data_Person.Person_ID='00" + textSothe.getText() + "' OR Data_Person.Person_ID='000"
							+ textSothe.getText() + "')";

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = connect.getStatement().executeQuery(select);

					textHoten.setText("");
					textDonvi.setText("");

					while (result.next()) {
						textHoten.setText(result.getString(1));
						textDonvi.setText(result.getString(2));
					}

					result.close();
					connect.closeStatement();
				} catch (Exception se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (language == 0) {
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

		// ***************************************************************************************************************************
		// Lưu
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (!textSothe.getText().isEmpty() && !textHoten.getText().isEmpty()
						&& !textFullmail.getText().isEmpty()) {
					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}

						try {
							String insert = "INSERT INTO Mail( ID ,FullMail ,MailName ,Passwords,Permission,Note,DateCreated,DateUpdate,UserUpdate ) VALUES  ( '"
									+ textSothe.getText() + "' ,N'" + textFullmail.getText() + "','"
									+ textMailname.getText() + "' ,'" + textPassword.getText() + "',N'"
									+ cbPermission.getText() + "',N'" + textNote.getText() + "','"
									+ txtNgaytao.getText() + "' ,GETDATE(),'" + userlogin + "' )";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								try {
									textSothe.setText("");
									textHoten.setText("");
									textNote.setText("");
									textFullmail.setText("@lacty2.com.vn");
									textMailname.setText("");
									textPassword.setText("");
									textNote.setText("");

									isaddsuccess = true;
									MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
									if (language == 0) {
										thongbao.setText("Thông báo");
										thongbao.setMessage("Lưu thành công!");
									} else {
										thongbao.setText("Notice");
										thongbao.setMessage("Save successful!");
									}
									thongbao.open();
								} catch (Exception ex) {

								}
							}
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
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
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
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
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Số thẻ trống!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("ID is empty!");
						}
						thongbao.open();
					} else if (textHoten.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Số thẻ chưa tồn tại trong danh sách IP!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("ID does not exist in the IP list!");
						}
						thongbao.open();
					} else if (textNote.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Nội dung rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Content is empty!");
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

	// kiểm tra xem có thêm thành công ít nhất 1 mail hay không
	public boolean isAddSuccess() {
		return isaddsuccess;
	}
}
