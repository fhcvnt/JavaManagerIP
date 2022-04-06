package itmanagerip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class NewIP {
	private ConnectSQL connect;
	private String userlogin = "21608"; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	protected Shell shell;
	private Text textSothe;
	private Text textHoten;
	private Text textEmail;
	private Text textghichu;
	private Combo comboDonvi;
	private Combo cbIP;
	private Combo cbToanha;

	private int language = 1;

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int ngonngu) {
		language = ngonngu;
	}

	public void setUserLogin(String user) {
		userlogin = user;
	}

	public static void main(String[] args) {
		try {
			NewIP window = new NewIP();
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
		shell.setSize(1269, 546);
		shell.setText("Thêm IP");
		shell.setImage(SWTResourceManager.getImage(NewIP.class, "/itmanagerip/Icon/IP64.png"));

		CLabel lbSothe = new CLabel(shell, SWT.NONE);
		lbSothe.setAlignment(SWT.RIGHT);
		lbSothe.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbSothe.setBounds(26, 29, 85, 35);
		lbSothe.setText("Số Thẻ");

		textSothe = new Text(shell, SWT.BORDER);
		textSothe.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textSothe.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textSothe.setBounds(117, 29, 115, 35);
		textSothe.setTextLimit(12);

		CLabel lbhoten = new CLabel(shell, SWT.NONE);
		lbhoten.setAlignment(SWT.RIGHT);
		lbhoten.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbhoten.setBounds(244, 29, 150, 35);
		lbhoten.setText("Họ Tên");

		textHoten = new Text(shell, SWT.BORDER);
		textHoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textHoten.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textHoten.setBounds(420, 29, 334, 35);

		CLabel lbDonvi = new CLabel(shell, SWT.NONE);
		lbDonvi.setAlignment(SWT.RIGHT);
		lbDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbDonvi.setBounds(760, 29, 171, 35);
		lbDonvi.setText("Đơn Vị");

		comboDonvi = new Combo(shell, SWT.NONE);
		comboDonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		comboDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		comboDonvi.setBounds(937, 29, 290, 35);

		CLabel lbToanha = new CLabel(shell, SWT.NONE);
		lbToanha.setAlignment(SWT.RIGHT);
		lbToanha.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbToanha.setBounds(10, 83, 102, 35);
		lbToanha.setText("Tòa Nhà");

		cbToanha = new Combo(shell, SWT.NONE);
		cbToanha.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbToanha.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbToanha.setBounds(117, 83, 225, 35);

		CLabel lbHedieuhanh = new CLabel(shell, SWT.NONE);
		lbHedieuhanh.setAlignment(SWT.RIGHT);
		lbHedieuhanh.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbHedieuhanh.setBounds(360, 83, 200, 35);
		lbHedieuhanh.setText("Hệ Điều Hành");

		Combo cbHedieuhanh = new Combo(shell, SWT.NONE);
		cbHedieuhanh.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbHedieuhanh.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbHedieuhanh.setBounds(572, 83, 251, 35);
		cbHedieuhanh.select(0);

		CLabel lbOffice = new CLabel(shell, SWT.NONE);
		lbOffice.setAlignment(SWT.RIGHT);
		lbOffice.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbOffice.setBounds(829, 83, 123, 35);
		lbOffice.setText("Office");

		Combo cbOffice = new Combo(shell, SWT.NONE);
		cbOffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbOffice.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbOffice.setBounds(958, 83, 269, 35);
		cbOffice.select(0);

		CLabel lbIP = new CLabel(shell, SWT.NONE);
		lbIP.setAlignment(SWT.RIGHT);
		lbIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbIP.setBounds(70, 135, 41, 35);
		lbIP.setText("IP");

		cbIP = new Combo(shell, SWT.NONE);
		cbIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbIP.setBounds(117, 135, 225, 35);
		cbIP.select(0);

		CLabel lbEmail = new CLabel(shell, SWT.NONE);
		lbEmail.setAlignment(SWT.RIGHT);
		lbEmail.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbEmail.setBounds(357, 135, 75, 35);
		lbEmail.setText("Email");

		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textEmail.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textEmail.setBounds(438, 135, 294, 35);
		textEmail.setText("NO");

		CLabel lbLoaimay = new CLabel(shell, SWT.NONE);
		lbLoaimay.setText("Loại Máy");
		lbLoaimay.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbLoaimay.setAlignment(SWT.RIGHT);
		lbLoaimay.setBounds(742, 135, 181, 35);

		Combo cbLoaimay = new Combo(shell, SWT.NONE);
		cbLoaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbLoaimay.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbLoaimay.setBounds(929, 135, 298, 35);
		cbLoaimay.select(0);

		CLabel lbGhichu = new CLabel(shell, SWT.NONE);
		lbGhichu.setAlignment(SWT.RIGHT);
		lbGhichu.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbGhichu.setBounds(10, 291, 101, 39);
		lbGhichu.setText("Ghi Chú");

		textghichu = new Text(shell, SWT.BORDER | SWT.MULTI);
		textghichu.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textghichu.setBounds(117, 190, 541, 291);

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(NewIP.class, "/itmanagerip/Icon/button/save48.png"));
		btnSave.setForeground(SWTResourceManager.getColor(0, 0, 255));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		btnSave.setBounds(681, 318, 171, 50);
		btnSave.setText("Save");

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setImage(SWTResourceManager.getImage(NewIP.class, "/itmanagerip/Icon/button/cancel-45.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		btnCancel.setBounds(681, 394, 171, 50);

		CLabel lbImage = new CLabel(shell, SWT.NONE);
		lbImage.setBackground(SWTResourceManager.getImage(NewIP.class, "/itmanagerip/Icon/bieutuong/search ip.png"));
		lbImage.setBounds(950, 211, 210, 270);

		// Gán ngôn ngữ
		// -------------------------------------------------------------------------------------------------------
		if (getLanguage() == 0) {
			// Tieng Viet

			shell.setText("Thêm IP");
			lbSothe.setText("Số Thẻ");
			lbhoten.setText("Họ Tên");
			lbDonvi.setText("Đơn Vị");
			lbToanha.setText("Tòa Nhà");
			lbIP.setText("IP");
			lbHedieuhanh.setText("Hệ Điều Hành");
			lbOffice.setText("Office");
			lbEmail.setText("Email");
			lbGhichu.setText("Ghi Chú");
			lbLoaimay.setText("Loại Máy");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
		} else {
			// Tieng Anh

			shell.setText("Add IP");
			lbSothe.setText("ID");
			lbhoten.setText("Person Name");
			lbDonvi.setText("Department");
			lbToanha.setText("Building");
			lbIP.setText("IP");
			lbHedieuhanh.setText("Operator System");
			lbOffice.setText("Office");
			lbEmail.setText("Email");
			lbGhichu.setText("Note");
			lbLoaimay.setText("Machine Type");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
		}

		// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị dựa vào số thẻ
		// ***************************************************************************************************************************
		textSothe.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}

					String select = "";
					ResultSet result;

					select = "SELECT Data_Person.Person_Name,Data_Department.Department_Name,Person_Image FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
							+ textSothe.getText() + "' OR Data_Person.Person_ID='0" + textSothe.getText()
							+ "' OR Data_Person.Person_ID='00" + textSothe.getText() + "')";

					PreparedStatement ps = connect.getConnection().prepareStatement(select);

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = ps.executeQuery();

					textHoten.setText("");
					comboDonvi.setText("");
					lbImage.setBackground(
							SWTResourceManager.getImage(NewIP.class, "/itmanagerip/Icon/bieutuong/search ip.png"));

					while (result.next()) {
						textHoten.setText(result.getString(1));
						comboDonvi.setText(result.getString(2));

						try {
							Image image = new Image(null, new ImageData(result.getBinaryStream(3)));
							if (image.getBounds().height < (float) image.getBounds().width) {
								lbImage.setBackground(resize(image, 210, 270));
							} else if (image.getBounds().height / (float) image.getBounds().width >= (float) 4 / 3) {
								lbImage.setBackground(
										resize(image, 210, image.getBounds().height * 210 / image.getBounds().width));
							} else if (image.getBounds().height / (float) image.getBounds().width >= (float) 1.12) {
								lbImage.setBackground(
										resize(image, image.getBounds().width * 270 / image.getBounds().height, 270));
							} else {
								lbImage.setBackground(resize(image, 210, 270));
							}

						} catch (Exception ex) {

						}
					}
					result.close();
					ps.close();

				} catch (SQLException se) {
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

		// ======================================================================================================
		// Thêm dữ liệu cho Combo
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();

			// Don vi
			String selectDonVi = "SELECT DonVi FROM Donvi ORDER BY Donvi ASC";
			ResultSet result = connect.getStatement().executeQuery(selectDonVi);
			while (result.next()) {
				comboDonvi.add(result.getString(1));
			}
			result.close();

			// He dieu hanh
			String selectHedieuhanh = "SELECT HeDieuHanh FROM HeDieuHanh ORDER BY HeDieuHanh ASC";
			ResultSet resultHedieuhanh = connect.getStatement().executeQuery(selectHedieuhanh);
			while (resultHedieuhanh.next()) {
				cbHedieuhanh.add(resultHedieuhanh.getString(1));
			}
			resultHedieuhanh.close();

			// Office
			String selectOffice = "SELECT Office FROM Office ORDER BY Office ASC";
			ResultSet resultOffice = connect.getStatement().executeQuery(selectOffice);
			while (resultOffice.next()) {
				cbOffice.add(resultOffice.getString(1));
			}
			resultOffice.close();

			// Toa nha
			String selecttoanha = "SELECT Building FROM Building ORDER BY Building ASC";
			ResultSet resultToanha = connect.getStatement().executeQuery(selecttoanha);
			while (resultToanha.next()) {
				cbToanha.add(resultToanha.getString(1));
			}
			cbToanha.select(0);
			resultToanha.close();

			// Loai may
			String selectloaimay = "SELECT LoaiMay FROM LoaiMay ORDER BY LoaiMay ASC";
			ResultSet resultLoaimay = connect.getStatement().executeQuery(selectloaimay);
			while (resultLoaimay.next()) {
				cbLoaimay.add(resultLoaimay.getString(1));
			}
			resultLoaimay.close();

			// Lop IP
			String selectlopip = "SELECT LopIP FROM dbo.Building WHERE Building=N'" + cbToanha.getText() + "'";
			String lopiphientai = ""; // lop IP hien tai
			ResultSet resultlopip = connect.getStatement().executeQuery(selectlopip);
			while (resultlopip.next()) {
				lopiphientai = resultlopip.getString(1);
			}
			resultlopip.close();

			// IP
			String selectip = "SELECT IP FROM DanhSachIP WHERE IP LIKE '%." + lopiphientai + ".%' ORDER BY IP ASC";
			ResultSet resultip = connect.getStatement().executeQuery(selectip);
			try {

				ArrayList<String> danhsachip = new ArrayList<String>();
				String chuoiip = "192.168.";
				String lopip = lopiphientai;
				for (int i = 1; i <= 254; i++) {
					danhsachip.add(chuoiip + lopip + "." + i);
				}

				ArrayList<String> danhsachipcsdl = new ArrayList<String>();

				while (resultip.next()) {
					danhsachipcsdl.add(resultip.getString(1));
				}

				danhsachip.removeAll(danhsachipcsdl);
				for (String list : danhsachip) {
					cbIP.add(list);
				}

			} catch (Exception ee) {

			}
			resultip.close();

		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (language == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		} finally {
			try {
				connect.closeStatement();
			} catch (SQLException se2) {
			}
		}

		// Thay đổi dữ liệu cho combo IP khi combo tòa nhà thay đổi
		cbToanha.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					// Lop IP
					String selectlopip = "SELECT LopIP FROM dbo.Building WHERE Building=N'" + cbToanha.getText() + "'";
					String lopiphientai = ""; // lop IP hien tai
					ResultSet resultlopip = connect.getStatement().executeQuery(selectlopip);
					while (resultlopip.next()) {
						lopiphientai = resultlopip.getString(1);
					}
					resultlopip.close();

					// IP
					String selectip = "SELECT IP FROM DanhSachIP WHERE IP LIKE '%." + lopiphientai
							+ ".%' ORDER BY IP ASC";
					ResultSet resultip = connect.getStatement().executeQuery(selectip);
					try {

						ArrayList<String> danhsachip = new ArrayList<String>();
						String chuoiip = "192.168.";
						String lopip = lopiphientai;
						for (int i = 1; i <= 254; i++) {
							danhsachip.add(chuoiip + lopip + "." + i);
						}

						ArrayList<String> danhsachipcsdl = new ArrayList<String>();

						while (resultip.next()) {
							danhsachipcsdl.add(resultip.getString(1));
						}

						danhsachip.removeAll(danhsachipcsdl);
						cbIP.removeAll();
						for (String list : danhsachip) {
							cbIP.add(list);
						}

					} catch (Exception ee) {

					}
					resultip.close();
				} catch (SQLException se) {
					// se.printStackTrace();
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (language == 0) {
						thongbao.setText("Thông báo lỗi!");
						thongbao.setMessage("Lỗi!\n" + se.toString());
					} else {
						thongbao.setText("Notice error!");
						thongbao.setMessage("Error!\n" + se.toString());
					}
					thongbao.open();
				} finally {
					try {
						connect.closeStatement();
					} catch (SQLException se2) {
					}
				}
			}
		});

		// Cancel
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					connect.closeConnection();
				} catch (SQLException e1) {
				}
				shell.dispose();
			}
		});

		// ***************************************************************************************************************************
		// Lưu
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// ***************************************************************
				if (!textSothe.getText().isEmpty() && !textHoten.getText().isEmpty() && !comboDonvi.getText().isEmpty()
						&& !cbToanha.getText().isEmpty() && !cbHedieuhanh.getText().isEmpty()
						&& !cbOffice.getText().isEmpty() && !cbIP.getText().isEmpty() && !textEmail.getText().isEmpty()
						&& !cbLoaimay.getText().isEmpty()) {

					if (validate(cbIP.getText())) {
						// lay ma don vi
						String madonvi = "";
						try {
							if (connect == null) {
								connect = new ConnectSQL();
								connect.setConnection();
							}
							connect.setStatement();

							// Don vi
							String selectdonvi = "SELECT Department_Serial_Key FROM [SV4].[HRIS].[dbo].[Data_Department] WHERE Department_Name=N'"
									+ comboDonvi.getText() + "'";
							ResultSet resultselectdonvi = connect.getStatement().executeQuery(selectdonvi);
							while (resultselectdonvi.next()) {
								madonvi = resultselectdonvi.getString(1);
								break;
							}
							resultselectdonvi.close();
							connect.closeStatement();

							if (!madonvi.isEmpty()) {
								connect.setStatement();
								String selectdv = "SELECT MaDonVi FROM DonVi WHERE MaDonVi='" + madonvi + "'";
								String madv = "";
								ResultSet ketqua = connect.getStatement().executeQuery(selectdv);
								while (ketqua.next()) {
									madv = ketqua.getString(1);
								}
								ketqua.close();
								connect.closeStatement();
								if (madv.isEmpty()) {
									connect.setStatement();
									String insert = "INSERT INTO DonVi ( MaDonVi, DonVi ) VALUES  ( '" + madonvi
											+ "', N'" + comboDonvi.getText() + "')";
									connect.execUpdateQuery(insert);
								}
								connect.closeStatement();

								try {
									if (connect == null) {
										connect = new ConnectSQL();
										connect.setConnection();
									}
									connect.setStatement();

									String mahedieuhanh = "", maloaimay = "", matoanha = "", maoffice = "";

									try {
										// He dieu hanh
										String select = "SELECT  MaHeDieuHanh FROM HeDieuHanh WHERE HeDieuHanh=N'"
												+ cbHedieuhanh.getText() + "'";
										ResultSet resultselect = connect.getStatement().executeQuery(select);
										while (resultselect.next()) {
											mahedieuhanh = resultselect.getString(1);
											break;
										}

										// Office
										select = "SELECT  MaOffice FROM Office WHERE Office=N'" + cbOffice.getText()
												+ "'";
										resultselect = connect.getStatement().executeQuery(select);
										while (resultselect.next()) {
											maoffice = resultselect.getString(1);
											break;
										}

										// Toa nha
										select = "SELECT  MaBuilding FROM Building WHERE Building=N'"
												+ cbToanha.getText() + "'";
										resultselect = connect.getStatement().executeQuery(select);
										while (resultselect.next()) {
											matoanha = resultselect.getString(1);
											break;
										}

										// Loai may
										select = "SELECT MaLoaiMay FROM LoaiMay WHERE LoaiMay=N'" + cbLoaimay.getText()
												+ "'";
										resultselect = connect.getStatement().executeQuery(select);
										while (resultselect.next()) {
											maloaimay = resultselect.getString(1);
											break;
										}

										// Lop IP
										String lopipbuilding = "";
										select = "SELECT LopIP FROM Building WHERE Building=N'" + cbToanha.getText()
												+ "'";
										resultselect = connect.getStatement().executeQuery(select);
										while (resultselect.next()) {
											lopipbuilding = resultselect.getString(1);
											break;
										}

										String lopipofip = cbIP.getText();
										lopipofip = lopipofip.substring(0, lopipofip.lastIndexOf("."));

										if (lopipofip.equals("192.168." + lopipbuilding)) {
											String insertIP = "INSERT INTO DanhSachIP ( SoThe,HoTen,MaDonVi,IP,MaLoaiMay,Email,MaHeDieuHanh,MaOffice,MaBuilding,GhiChu,NgayCapNhat,UserUpdate ) VALUES ('"
													+ textSothe.getText() + "',N'" + textHoten.getText() + "','"
													+ madonvi + "','" + cbIP.getText() + "','" + maloaimay + "','"
													+ textEmail.getText() + "','" + mahedieuhanh + "','" + maoffice
													+ "','" + matoanha + "',N'" + textghichu.getText()
													+ "', GETDATE(),'" + userlogin + "')";

											int result = connect.execUpdateQuery(insertIP);

											if (result > 0) {
												try {
													textSothe.setText("");
													textHoten.setText("");
													textEmail.setText("NO");
													textghichu.setText("");
													cbIP.setText("");

													// Cập nhật lại combo IP
													try {
														if (connect == null) {
															connect = new ConnectSQL();
															connect.setConnection();
														}
														connect.setStatement();
														// Lop IP
														String selectlopip = "SELECT LopIP FROM dbo.Building WHERE Building=N'"
																+ cbToanha.getText() + "'";
														String lopiphientai = ""; // lop IP hien tai
														ResultSet resultlopip = connect.getStatement()
																.executeQuery(selectlopip);
														while (resultlopip.next()) {
															lopiphientai = resultlopip.getString(1);
														}
														resultlopip.close();

														// IP
														String selectip = "SELECT IP FROM DanhSachIP WHERE IP LIKE '%."
																+ lopiphientai + ".%' ORDER BY IP ASC";
														ResultSet resultip = connect.getStatement()
																.executeQuery(selectip);
														try {

															ArrayList<String> danhsachip = new ArrayList<String>();
															String chuoiip = "192.168.";
															String lopip = lopiphientai;
															for (int i = 1; i <= 254; i++) {
																danhsachip.add(chuoiip + lopip + "." + i);
															}

															ArrayList<String> danhsachipcsdl = new ArrayList<String>();

															while (resultip.next()) {
																danhsachipcsdl.add(resultip.getString(1));
															}

															danhsachip.removeAll(danhsachipcsdl);
															cbIP.removeAll();
															for (String list : danhsachip) {
																cbIP.add(list);
															}

														} catch (Exception ee) {
															ee.printStackTrace();
														}
														cbIP.select(0);
														resultip.close();
													} catch (SQLException se) {
														// se.printStackTrace();
														MessageBox thongbao = new MessageBox(shell,
																SWT.ICON_ERROR | SWT.OK);
														if (language == 0) {
															thongbao.setText("Thông báo lỗi!");
															thongbao.setMessage("Lỗi!\n" + se.toString());
														} else {
															thongbao.setText("Notice error!");
															thongbao.setMessage("Error!\n" + se.toString());
														}
														thongbao.open();
													} finally {
														try {
															connect.closeStatement();
														} catch (SQLException se2) {
														}
													}
													cbIP.setText("");
													MessageBox thongbao = new MessageBox(shell,
															SWT.ICON_INFORMATION | SWT.OK);
													if (language == 0) {
														thongbao.setText("Thông báo");
														thongbao.setMessage("Lưu thành công!");
													} else {
														thongbao.setText("Notice");
														thongbao.setMessage("Save successful!");
													}
													thongbao.open();
												} catch (Exception ex) {
													ex.printStackTrace();
												}
											}
										} else {
											MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
											if (language == 0) {
												thongbao.setText("Thông báo");
												thongbao.setMessage("IP: " + cbIP.getText() + " không thuộc lớp IP "
														+ lopipbuilding);
											} else {
												thongbao.setText("Notice");
												thongbao.setMessage("IP " + cbIP.getText() + " is not in the IP "
														+ lopipbuilding + " class");
											}
											thongbao.open();
										}

									} catch (Exception ex) {
										MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
										if (language == 0) {
											thongbao.setText("Thông báo lỗi");
											thongbao.setMessage("Lưu không thành công!" + ex.toString());
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
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
								if (language == 0) {
									thongbao.setText("Thông báo lỗi");
									thongbao.setMessage("Đơn vị rỗng hoặc không tồn tại!");
								} else {
									thongbao.setText("Notice error");
									thongbao.setMessage("Department is empty or not exist!");
								}
								thongbao.open();
							}
						} catch (Exception exp) {

						}

					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Định dạng IP sai!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Wrong ip format!");
						}
						thongbao.open();
					}
				} else {
					if (textSothe.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Số thẻ rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("ID is empty!");
						}
						thongbao.open();
					} else if (textHoten.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Họ tên rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Name is empty!");
						}
						thongbao.open();
					} else if (comboDonvi.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Đơn vị rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Department is empty!");
						}
						thongbao.open();
					} else if (cbToanha.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Tòa nhà rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Building is empty!");
						}
						thongbao.open();
					} else if (cbHedieuhanh.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Hệ điều hành rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Operator system is empty!");
						}
						thongbao.open();
					} else if (cbOffice.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Office rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Office is empty!");
						}
						thongbao.open();
					} else if (cbIP.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("IP rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("IP is empty!");
						}
						thongbao.open();
					} else if (textEmail.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Email rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Email is empty!");
						}
						thongbao.open();
					} else if (cbLoaimay.getText().isEmpty()) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Loại máy rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Machine type is empty!");
						}
						thongbao.open();
					}
				}
			}
		});

		// Ping IP, kiểm tra xem IP đã có ai dùng chưa
		lbImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					String ipping = cbIP.getText();
					if (!ipping.isEmpty()) {

						CheckIP checkip = new CheckIP();
						shell.setEnabled(false);
						checkip.setIP(ipping);
						try {
							checkip.open();
						} catch (Exception ee) {

						}
						shell.setEnabled(true);

					}
				} catch (Exception exc) {

				}
			}
		});
	}

	// Kiểm tra định dạng IP
	public static boolean validate(final String ip) {
		String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

		return ip.matches(PATTERN);
	}

	// ***********************************************************************************************************
	// Resize hinh anh
	public Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		image.dispose(); // don't forget about me!
		return scaled;
	}
}
