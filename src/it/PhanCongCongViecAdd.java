package it;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

public class PhanCongCongViecAdd {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoThe;
	private Text textHoTen;
	private CCombo comboDonVi;
	private int ngonngu = 1;
	private Text textTinhTrang;
	private CCombo comboNguoiXuLy;
	private String userlogin = "21608";

	public static void main(String[] args) {
		try {
			PhanCongCongViecAdd window = new PhanCongCongViecAdd();
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
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(PhanCongCongViecAdd.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(650, 403);
		shell.setText("Add work assignment");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(10, 39, 119, 30);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setBounds(135, 39, 158, 30);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(10, 91, 119, 30);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setBounds(135, 91, 440, 30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setTextLimit(50);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 142, 120, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboDonVi = new CCombo(composite, SWT.BORDER);
		comboDonVi.setBounds(136, 142, 439, 30);
		comboDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDonVi.setBackground(SWTResourceManager.getColor(224, 255, 255));

		CLabel lbTinhTrang = new CLabel(composite, SWT.RIGHT);
		lbTinhTrang.setText("Tình trạng");
		lbTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTinhTrang.setBounds(10, 192, 120, 30);

		textTinhTrang = new Text(composite, SWT.BORDER);
		textTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTinhTrang.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTinhTrang.setBounds(136, 192, 439, 30);
		textTinhTrang.setTextLimit(128);

		CLabel lbNguoiXuLy = new CLabel(composite, SWT.RIGHT);
		lbNguoiXuLy.setText("Người xử lý");
		lbNguoiXuLy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiXuLy.setBounds(10, 244, 120, 30);

		comboNguoiXuLy = new CCombo(composite, SWT.BORDER);
		comboNguoiXuLy.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboNguoiXuLy.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboNguoiXuLy.setBounds(136, 244, 439, 30);
		comboNguoiXuLy.setEditable(false);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(PhanCongCongViecAdd.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(194, 292, 109, 35);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PhanCongCongViecAdd.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(316, 292, 120, 35);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Thêm phân công công việc");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbTinhTrang.setText("Tình trạng");
			lbNguoiXuLy.setText("Người xử lý");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

		} else {
			// Tieng Anh
			shell.setText("Add work assignment");
			lbSoThe.setText("ID");
			lbHoTen.setText("Person name");
			lbDonVi.setText("Department");
			lbTinhTrang.setText("Status");
			lbNguoiXuLy.setText("Handler");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
		}

		// lấy dữ liệu cho combo thiết bị
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [DonVi] FROM [dbo].[DonVi] ORDER BY [DonVi] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboDonVi.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// lấy dữ liệu cho combo người xử lý
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [TenNguoiDung] FROM [dbo].[NguoiDung] WHERE [TenNhom] NOT IN ('Manager','Translate','Disable') ORDER BY [TenNguoiDung] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboNguoiXuLy.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị dựa vào số thẻ
		// ---------------------------------------------------------------------------------------------------
		textSoThe.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					String select = "";
					ResultSet result;

					select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
							+ textSoThe.getText() + "' OR Data_Person.Person_ID='0" + textSoThe.getText()
							+ "' OR Data_Person.Person_ID='00" + textSoThe.getText() + "')";

					PreparedStatement ps = connect.getConnection().prepareStatement(select);

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = ps.executeQuery();

					textHoTen.setText("");
					comboDonVi.setText("");

					while (result.next()) {
						textHoTen.setText(result.getString(1));
						comboDonVi.setText(result.getString(2));
					}
					result.close();

				} catch (SQLException se) {
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

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					try {
						String macongviec = "";
						String select = "SELECT TOP 1 (SELECT SUBSTRING([MaCongViec],3,8)) FROM [dbo].[IT_PhanCongXuLyCongViec2] ORDER BY [MaCongViec] DESC";
						ResultSet resultmacongviec = connect.getStatement().executeQuery(select);
						while (resultmacongviec.next()) {
							macongviec = resultmacongviec.getString(1);
						}
						try {
							if (!macongviec.isEmpty()) {
								macongviec = "CV" + (Integer.parseInt(macongviec) + 1);
							} else {
								macongviec = "CV10000001";
							}
						} catch (Exception ew) {
							macongviec = "CV10000001";
						}

						String insert = "INSERT INTO [dbo].[IT_PhanCongXuLyCongViec2] ([MaCongViec],[Ngay],[DonVi],[SoThe],[HoTen],[TinhTrang],[NguoiXuLy],[NguoiPhanCong]) VALUES ('"
								+ macongviec + "',GETDATE(),N'" + comboDonVi.getText() + "','" + textSoThe.getText()
								+ "',N'" + textHoTen.getText() + "',N'" + textTinhTrang.getText() + "',N'"
								+ comboNguoiXuLy.getText() + "','" + userlogin + "')" + "\n"
								+ "INSERT INTO [dbo].[IT_SlideShowPhanCongXuLyCongViec] ([MaCongViec],[ThoiGianCapNhat]) VALUES ('"
								+ macongviec + "',GETDATE())";
						
						
						// ghi dữ liệu vào bảng thông báo để thông báo cho người xử lý
						String insertnotification="INSERT INTO [dbo].[IT_HienThiThongBao] ([MaCongViec],[TrangThai]) VALUES ('"+macongviec+"',0)";
						
						// insert vào cả 2 bảng công việc và thông báo
						String insertsql=insert+"\n"+insertnotification;

						int result = connect.execUpdateQuery(insertsql);
						if (result > 0) {
//							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
//							if (ngonngu == 0) {
//								thongbao.setText("Thông báo");
//								thongbao.setMessage("Thêm thành công!");
//							} else {
//								thongbao.setText("Notice");
//								thongbao.setMessage("Add successful!");
//							}
//							thongbao.open();
							shell.dispose();
						}
						connect.closeStatement();

					} catch (Exception ex) {
					}

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

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

	public void setData(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}
}
