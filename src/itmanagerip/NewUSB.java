package itmanagerip;

import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class NewUSB {
	private ConnectSQL connect;
	private String userlogin = ""; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	protected Shell shell;
	private Text textSothe;
	private Text textHoten;
	private Text textContent;

	private int language = 1;
	private Text textDonvi;
	private Text textIP;

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int ngonngu, String user) {
		language = ngonngu;
		userlogin = user;
	}

	public static void main(String[] args) {
		try {
			NewUSB window = new NewUSB();
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
		shell.setText("New USB");
		shell.setImage(SWTResourceManager.getImage(NewUSB.class, "/itmanagerip/Icon/IP64.png"));

		CLabel lbSothe = new CLabel(shell, SWT.NONE);
		lbSothe.setAlignment(SWT.RIGHT);
		lbSothe.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbSothe.setBounds(26, 29, 85, 35);
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

		CLabel lbIP = new CLabel(shell, SWT.NONE);
		lbIP.setAlignment(SWT.RIGHT);
		lbIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbIP.setBounds(44, 84, 67, 35);
		lbIP.setText("IP");

		textIP = new Text(shell, SWT.BORDER);
		textIP.setEnabled(false);
		textIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textIP.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textIP.setBounds(117, 84, 216, 35);

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(NewUSB.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setForeground(SWTResourceManager.getColor(0, 0, 255));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 19, SWT.BOLD));
		btnSave.setBounds(851, 84, 130, 37);
		btnSave.setText("Save");

		CLabel lbContent = new CLabel(shell, SWT.NONE);
		lbContent.setAlignment(SWT.RIGHT);
		lbContent.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbContent.setBounds(10, 291, 153, 39);
		lbContent.setText("Nội Dung");

		textContent = new Text(shell, SWT.BORDER | SWT.MULTI);
		textContent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textContent.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textContent.setBounds(169, 139, 684, 356);
		textContent.setTextLimit(3000);

		CLabel lbImage = new CLabel(shell, SWT.NONE);
		lbImage.setBackground(SWTResourceManager.getImage(NewUSB.class, "/itmanagerip/Icon/bieutuong/usb290.png"));
		lbImage.setBounds(870, 193, 290, 251);

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setImage(SWTResourceManager.getImage(NewUSB.class, "/itmanagerip/Icon/button/cancel30.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 19, SWT.BOLD));
		btnCancel.setBounds(999, 84, 136, 37);

		DateTime datetimeNgaydenghi = new DateTime(shell, SWT.BORDER);
		datetimeNgaydenghi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		datetimeNgaydenghi.setBounds(617, 84, 189, 35);

		CLabel lbNgaydenghi = new CLabel(shell, SWT.NONE);
		lbNgaydenghi.setText("Ngày Đề Nghị");
		lbNgaydenghi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbNgaydenghi.setAlignment(SWT.RIGHT);
		lbNgaydenghi.setBounds(353, 84, 247, 35);

		// Gán ngôn ngữ
		if (getLanguage() == 0) {
			// Tieng Viet

			shell.setText("Thêm USB");
			lbSothe.setText("Số Thẻ");
			lbhoten.setText("Họ Tên");
			lbDonvi.setText("Đơn Vị");
			lbIP.setText("IP");
			lbNgaydenghi.setText("Ngày Đề Nghị");
			lbContent.setText("Nội Dung");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else {
			// Tieng Anh

			shell.setText("New USB");
			lbSothe.setText("ID");
			lbhoten.setText("Person Name");
			lbDonvi.setText("Department");
			lbIP.setText("IP");
			lbNgaydenghi.setText("Recommended Date");
			lbContent.setText("Content");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
		}

		// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị, IP dựa vào số
		// thẻ
		// ***************************************************************************************************************************
		textSothe.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					connect = new ConnectSQL();
					connect.setConnection();
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
				} finally {
					try {
						if (connect.getStatement() != null) {
							connect.closeStatement();
						}
						if (connect.getConnection() != null) {
							connect.closeConnection();
						}

					} catch (Exception se2) {
						// nothing we can do
					}
				}
			}
		});

		// ***************************************************************************************************************************
		// Lưu
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String month = "0" + (datetimeNgaydenghi.getMonth() + 1);
				month = month.substring(month.length() - 2);

				String day = "0" + datetimeNgaydenghi.getDay();
				day = day.substring(day.length() - 2);

				String ngaydenghi = datetimeNgaydenghi.getYear() + month + day;

				if (!textSothe.getText().isEmpty() && !textHoten.getText().isEmpty() && !textIP.getText().isEmpty()
						&& !textContent.getText().isEmpty()) {
					try {
						connect = new ConnectSQL();
						connect.setConnection();

						try {
							String insert = "INSERT INTO USB( SoThe ,LyDo ,NgayDeNghi ,NgayCapNhat,UserUpdate ) VALUES  ( '"
									+ textSothe.getText() + "' ,N'" + textContent.getText() + "' ,'" + ngaydenghi
									+ "' ,GETDATE(),'" + userlogin + "' )";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								try {
									textSothe.setText("");
									textHoten.setText("");
									textIP.setText("");
									textContent.setText("");

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
					} finally {
						try {
							if (connect.getStatement() != null) {
								connect.closeStatement();
							}
							if (connect.getConnection() != null) {
								connect.closeConnection();
							}

						} catch (Exception se2) {
							// nothing we can do
						}
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
					} else if (textContent.getText().isEmpty()) {
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
}
