package itmanagerip;

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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import image.ResizeImage;
import sort.SortData;
import sql.ConnectSQL;

public class EditIP {
	private ConnectSQL connect;
	private String userlogin = "21608"; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	protected Shell shell;
	private Text textSothe;
	private Text textHoten;
	private Text textEmail;
	private Text textghichu;

	private int language = 1;
	private String editsothe = "", edithoten = "", editdonvi = "", editip = "", editloaimay = "", editemail = "",
			edithedieuhanh = "", editoffice = "", edittoanha = "", editghichu = "";
	private boolean iseditsuccess = false;

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
			EditIP window = new EditIP();
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
		shell.setSize(1257, 546);
		shell.setText("Sửa IP");
		shell.setImage(SWTResourceManager.getImage(EditIP.class, "/itmanagerip/Icon/IP64.png"));

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
		textHoten.setBounds(420, 29, 312, 35);

		CLabel lbDonvi = new CLabel(shell, SWT.NONE);
		lbDonvi.setAlignment(SWT.RIGHT);
		lbDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbDonvi.setBounds(747, 29, 175, 35);
		lbDonvi.setText("Đơn Vị");

		Combo comboDonvi = new Combo(shell, SWT.NONE);
		comboDonvi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		comboDonvi.setBounds(928, 29, 281, 35);

		CLabel lbToanha = new CLabel(shell, SWT.NONE);
		lbToanha.setAlignment(SWT.RIGHT);
		lbToanha.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbToanha.setBounds(10, 83, 102, 35);
		lbToanha.setText("Tòa Nhà");

		Combo cbToanha = new Combo(shell, SWT.NONE);
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
		cbHedieuhanh.setBounds(572, 83, 210, 35);

		CLabel lbOffice = new CLabel(shell, SWT.NONE);
		lbOffice.setAlignment(SWT.RIGHT);
		lbOffice.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbOffice.setBounds(800, 83, 125, 35);
		lbOffice.setText("Office");

		Combo cbOffice = new Combo(shell, SWT.NONE);
		cbOffice.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbOffice.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbOffice.setBounds(931, 83, 278, 35);

		CLabel lbIP = new CLabel(shell, SWT.NONE);
		lbIP.setAlignment(SWT.RIGHT);
		lbIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbIP.setBounds(70, 135, 41, 35);
		lbIP.setText("IP");

		Combo cbIP = new Combo(shell, SWT.NONE);
		cbIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbIP.setBounds(117, 135, 225, 35);
		cbIP.setText(editip);

		CLabel lbEmail = new CLabel(shell, SWT.NONE);
		lbEmail.setAlignment(SWT.RIGHT);
		lbEmail.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbEmail.setBounds(357, 135, 75, 35);
		lbEmail.setText("Email");

		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textEmail.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textEmail.setBounds(438, 135, 294, 35);

		CLabel lbLoaimay = new CLabel(shell, SWT.NONE);
		// lbLoaimay.setLeftMargin(0);
		lbLoaimay.setText("Loại Máy");
		lbLoaimay.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbLoaimay.setAlignment(SWT.RIGHT);
		lbLoaimay.setBounds(742, 135, 183, 35);

		Combo cbLoaimay = new Combo(shell, SWT.NONE);
		cbLoaimay.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbLoaimay.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		cbLoaimay.setBounds(931, 135, 278, 35);
		cbLoaimay.setText(editloaimay);

		CLabel lbGhichu = new CLabel(shell, SWT.NONE);
		lbGhichu.setAlignment(SWT.RIGHT);
		lbGhichu.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbGhichu.setBounds(10, 291, 101, 39);
		lbGhichu.setText("Ghi Chú");

		textghichu = new Text(shell, SWT.BORDER | SWT.MULTI);
		textghichu.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textghichu.setBounds(117, 190, 565, 284);

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(EditIP.class, "/itmanagerip/Icon/button/save48.png"));
		btnSave.setForeground(SWTResourceManager.getColor(0, 0, 255));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		btnSave.setBounds(697, 318, 166, 50);
		btnSave.setText("Save");

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setImage(SWTResourceManager.getImage(EditIP.class, "/itmanagerip/Icon/button/cancel-45.png"));
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		btnCancel.setBounds(697, 394, 166, 50);

		CLabel lbImage = new CLabel(shell, SWT.NONE);
		lbImage.setBackground(SWTResourceManager.getImage(EditIP.class, "/itmanagerip/Icon/bieutuong/ip search.png"));
		lbImage.setBounds(950, 202, 210, 270);

		textSothe.setText(editsothe);
		textHoten.setText(edithoten);
		comboDonvi.setText(editdonvi);
		textEmail.setText(editemail);
		cbHedieuhanh.setText(edithedieuhanh);
		cbOffice.setText(editoffice);
		cbToanha.setText(edittoanha);
		textghichu.setText(editghichu);

		// gan du lieu ban dau
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "";
			ResultSet result;

			select = "SELECT Data_Person.Person_Name,Data_Department.Department_Name,Person_Image FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
					+ textSothe.getText() + "' OR Data_Person.Person_ID='0" + textSothe.getText()
					+ "' OR Data_Person.Person_ID='00" + textSothe.getText() + "')";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = connect.getStatement().executeQuery(select);

			textHoten.setText(edithoten);
			comboDonvi.setText(editdonvi);
			lbImage.setBackground(
					SWTResourceManager.getImage(EditIP.class, "/itmanagerip/Icon/bieutuong/ip search.png"));

			while (result.next()) {
				textHoten.setText(result.getString(1));
				comboDonvi.setText(result.getString(2));
				try {
					Image image = new Image(null, new ImageData(result.getBinaryStream(3)));
					if (image.getBounds().height < (float) image.getBounds().width) {
						lbImage.setBackground(ResizeImage.resize(image, 210, 270));
					} else if (image.getBounds().height / (float) image.getBounds().width >= (float) 4 / 3) {
						lbImage.setBackground(ResizeImage.resize(image, 210,
								image.getBounds().height * 210 / image.getBounds().width));
					} else if (image.getBounds().height / (float) image.getBounds().width >= (float) 1.12) {
						lbImage.setBackground(ResizeImage.resize(image,
								image.getBounds().width * 270 / image.getBounds().height, 270));
					} else {
						lbImage.setBackground(ResizeImage.resize(image, 210, 270));
					}
				} catch (Exception ex) {

				}
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

		// Gán ngôn ngữ
		// -------------------------------------------------------------------------------------------------------
		if (getLanguage() == 0) {
			// Tieng Viet

			shell.setText("Sửa IP");
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

			shell.setText("Edit IP");
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
					connect.setStatement();
					String select = "";
					ResultSet result;

					select = "SELECT Data_Person.Person_Name,Data_Department.Department_Name,Person_Image FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
							+ textSothe.getText() + "' OR Data_Person.Person_ID='0" + textSothe.getText()
							+ "' OR Data_Person.Person_ID='00" + textSothe.getText() + "')";

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = connect.getStatement().executeQuery(select);

					textHoten.setText("");
					comboDonvi.setText("");
					lbImage.setBackground(
							SWTResourceManager.getImage(EditIP.class, "/itmanagerip/Icon/bieutuong/ip search.png"));

					while (result.next()) {
						textHoten.setText(result.getString(1));
						comboDonvi.setText(result.getString(2));
						try {
							Image image = new Image(null, new ImageData(result.getBinaryStream(3)));
							if (image.getBounds().height < (float) image.getBounds().width) {
								lbImage.setBackground(ResizeImage.resize(image, 210, 270));
							} else if (image.getBounds().height / (float) image.getBounds().width >= (float) 27 / 21) {
								lbImage.setBackground(ResizeImage.resize(image, 210,
										image.getBounds().height * 210 / image.getBounds().width));
							} else {
								lbImage.setBackground(ResizeImage.resize(image,
										image.getBounds().width * 270 / image.getBounds().height, 270));
							}
						} catch (Exception ex) {

						}
					}

					result.close();
					connect.closeStatement();
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
		// Thêm dữ liệu cho Combox
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
			connect.closeStatement();

			// He dieu hanh
			connect.setStatement();
			String selectHedieuhanh = "SELECT HeDieuHanh FROM HeDieuHanh ORDER BY HeDieuHanh ASC";
			ResultSet resultHedieuhanh = connect.getStatement().executeQuery(selectHedieuhanh);
			while (resultHedieuhanh.next()) {
				cbHedieuhanh.add(resultHedieuhanh.getString(1));
			}
			resultHedieuhanh.close();

			// Office
			connect.setStatement();
			String selectOffice = "SELECT Office FROM Office ORDER BY Office ASC";
			ResultSet resultOffice = connect.getStatement().executeQuery(selectOffice);
			while (resultOffice.next()) {
				cbOffice.add(resultOffice.getString(1));
			}
			resultOffice.close();
			connect.closeStatement();

			// Toa nha
			connect.setStatement();
			String selecttoanha = "SELECT Building FROM Building ORDER BY Building ASC";
			ResultSet resultToanha = connect.getStatement().executeQuery(selecttoanha);
			while (resultToanha.next()) {
				cbToanha.add(resultToanha.getString(1));
			}
			resultToanha.close();
			connect.closeStatement();

			// Loai may
			connect.setStatement();
			String selectloaimay = "SELECT LoaiMay FROM LoaiMay ORDER BY LoaiMay ASC";
			ResultSet resultLoaimay = connect.getStatement().executeQuery(selectloaimay);
			while (resultLoaimay.next()) {
				cbLoaimay.add(resultLoaimay.getString(1));
			}
			resultLoaimay.close();
			connect.closeStatement();

			// Lop IP
			connect.setStatement();
			String selectlopip = "SELECT LopIP FROM dbo.Building WHERE Building=N'" + cbToanha.getText() + "'";
			String lopiphientai = ""; // lop IP hien tai
			ResultSet resultlopip = connect.getStatement().executeQuery(selectlopip);
			while (resultlopip.next()) {
				lopiphientai = resultlopip.getString(1);
			}
			resultlopip.close();
			connect.closeStatement();

			// IP
			connect.setStatement();
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
				String ipold = editip; // IP old
				for (String list : danhsachip) {
					if (SortData.compareIP(ipold, list) < 0) {
						cbIP.add(ipold);
						// dừng lại khi đã thêm vào rồi, chỉ cho thêm một lần thôi
						ipold = "255.255.255.255";
					}
					cbIP.add(list);
				}
				if (ipold.compareTo("255.255.255.255") != 0) {
					cbIP.add(ipold);
				}

			} catch (Exception ee) {

			}

			resultip.close();
			connect.closeStatement();

		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
			if (language == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Connect error!\n" + se.toString());
			}
			thongbao.open();
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
					connect.closeStatement();

					// IP
					connect.setStatement();
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
						String[] splitlopip = editip.split("\\.");
						if (lopiphientai.compareTo(splitlopip[2]) == 0) {
							String ipold = editip; // IP old
							for (String list : danhsachip) {
								if (SortData.compareIP(ipold, list) < 0) {
									cbIP.add(ipold);
									// dừng lại khi đã thêm vào rồi, chỉ cho thêm một lần thôi
									ipold = "255.255.255.255";
								}
								cbIP.add(list);
							}
							if (ipold.compareTo("255.255.255.255") != 0) {
								cbIP.add(ipold);
							}
						} else {
							for (String list : danhsachip) {
								cbIP.add(list);
							}
						}
					} catch (Exception ee) {

					}
					resultip.close();
					connect.closeStatement();
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

		// ***************************************************************************************************************************
		// Lưu
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
						} catch (Exception eee) {

						}
						if (!madonvi.isEmpty()) {
							try {
								if (connect == null) {
									connect = new ConnectSQL();
									connect.setConnection();
								}
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
									connect.closeStatement();
								}
								String mahedieuhanh = "", maloaimay = "", matoanha = "", maoffice = "";
								try {
									connect.setStatement();
									// He dieu hanh
									String select = "SELECT  MaHeDieuHanh FROM HeDieuHanh WHERE HeDieuHanh=N'"
											+ cbHedieuhanh.getText() + "'";
									ResultSet resultselect = connect.getStatement().executeQuery(select);
									while (resultselect.next()) {
										mahedieuhanh = resultselect.getString(1);
										break;
									}

									// Don vi
									select = "SELECT  MaDonVi FROM DonVi WHERE DonVi=N'" + comboDonvi.getText() + "'";
									resultselect = connect.getStatement().executeQuery(select);
									while (resultselect.next()) {
										madonvi = resultselect.getString(1);
										break;
									}

									// Office
									select = "SELECT  MaOffice FROM Office WHERE Office=N'" + cbOffice.getText() + "'";
									resultselect = connect.getStatement().executeQuery(select);
									while (resultselect.next()) {
										maoffice = resultselect.getString(1);
										break;
									}

									// Toa nha
									select = "SELECT  MaBuilding FROM Building WHERE Building=N'" + cbToanha.getText()
											+ "'";
									resultselect = connect.getStatement().executeQuery(select);
									while (resultselect.next()) {
										matoanha = resultselect.getString(1);
										break;
									}

									// Loai may
									select = "SELECT  MaLoaiMay FROM LoaiMay WHERE LoaiMay=N'" + cbLoaimay.getText()
											+ "'";
									resultselect = connect.getStatement().executeQuery(select);
									while (resultselect.next()) {
										maloaimay = resultselect.getString(1);
										break;
									}

									// Lop IP
									String lopipbuilding = "";
									select = "SELECT LopIP FROM Building WHERE Building=N'" + cbToanha.getText() + "'";
									resultselect = connect.getStatement().executeQuery(select);
									while (resultselect.next()) {
										lopipbuilding = resultselect.getString(1);
										break;
									}
									resultselect.close();
									connect.closeStatement();

									String lopipofip = cbIP.getText();
									lopipofip = lopipofip.substring(0, lopipofip.lastIndexOf("."));

									if (lopipofip.equals("192.168." + lopipbuilding)) {
										String updateIP = "UPDATE DanhSachIP SET SoThe='" + textSothe.getText()
												+ "',HoTen=N'" + textHoten.getText() + "',MaDonVi='" + madonvi
												+ "',IP='" + cbIP.getText() + "',MaLoaiMay='" + maloaimay + "',Email='"
												+ textEmail.getText() + "',MaHeDieuHanh='" + mahedieuhanh
												+ "',MaOffice='" + maoffice + "',MaBuilding='" + matoanha
												+ "',GhiChu=N'" + textghichu.getText()
												+ "',NgayCapNhat=GETDATE(),UserUpdate='" + userlogin + "' WHERE SoThe='"
												+ editsothe + "'";

										int result = connect.execUpdateQuery(updateIP);
										if (result > 0) {
											cbIP.setText("");
											try {
												iseditsuccess = true;
												MessageBox thongbao = new MessageBox(shell,
														SWT.ICON_INFORMATION | SWT.OK);
												if (language == 0) {
													thongbao.setText("Thông báo");
													thongbao.setMessage("Lưu thành công!");
												} else {
													thongbao.setText("Notice");
													thongbao.setMessage("Save succesful!");
												}
												thongbao.open();
												connect.closeStatement();
												shell.dispose();
											} catch (Exception ex) {

											}
										}
									} else {
										MessageBox thongbao = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK);
										if (language == 0) {
											thongbao.setText("Thông báo");
											thongbao.setMessage(
													"IP: " + cbIP.getText() + " không thuộc lớp IP " + lopipbuilding);
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
										thongbao.setText("Thông báo");
										thongbao.setMessage("Sửa thất bại!\n" + ex.toString());
									} else {
										thongbao.setText("Notice");
										thongbao.setMessage("Edit failed!\n" + ex.toString());
									}
									thongbao.open();
								}

							} catch (Exception se) {
								// se.printStackTrace();
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

					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (language == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Định dạng IP sai!");
						} else {
							thongbao.setText("notice error");
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

	// Lấy dữ liệu mặc định từ dòng chọn của cửa sổ IP List
	public void setDataIPDefault(String sothe, String hoten, String donvi, String ip, String loaimay, String email,
			String hedieuhanh, String office, String toanha, String ghichu) {
		editsothe = sothe;
		edithoten = hoten;
		editdonvi = donvi;
		editip = ip;
		editloaimay = loaimay;
		editemail = email;
		edithedieuhanh = hedieuhanh;
		editoffice = office;
		edittoanha = toanha;
		editghichu = ghichu;

		// cap nhat lai du lieu Ho ten, Don vi neu co thay doi
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "";
			ResultSet result;

			select = "SELECT Data_Person.Person_Name,Data_Department.Department_Name FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
					+ sothe + "' OR Data_Person.Person_ID='0" + sothe + "' OR Data_Person.Person_ID='00" + sothe + "')";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = connect.getStatement().executeQuery(select);

			while (result.next()) {
				edithoten = result.getString(1);
				editdonvi = result.getString(2);
			}
			result.close();
			connect.closeStatement();
			if (edithoten == null || edithoten.isEmpty()) {
				edithoten = hoten;
				editdonvi = donvi;
			}

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

	// kiểm tra xem có thêm thành công ít nhất 1 lý lịch hay không
	public boolean isEditSuccess() {
		return iseditsuccess;
	}
}
