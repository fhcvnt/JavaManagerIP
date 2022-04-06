package accessories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import card.Card;
import image.ResizeImage;
import sql.ConnectSQL;

public class NhanSuaThietBiAdd {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoThe;
	private int ngonngu = 0;
	private String userlogin = "21608";
	private boolean isaddsuccess = false;

	private Text textHoTen;
	private Text textNoiDung;
	private Text textSoBPM;
	private Text textDonVi;
	private Text textTheTu;

	public static void main(String[] args) {
		try {
			NhanSuaThietBiAdd window = new NhanSuaThietBiAdd();
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
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(NhanSuaThietBiAdd.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(811, 346);
		shell.setText("Thêm nhận sửa thiết bị");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbTheTu = new CLabel(composite, SWT.RIGHT);
		lbTheTu.setText("Thẻ từ");
		lbTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTheTu.setBounds(10, 20, 100, 30);

		textTheTu = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textTheTu.setTextLimit(32);
		textTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTheTu.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textTheTu.setBounds(118, 20, 290, 30);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(10, 70, 100, 30);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setBounds(118, 70, 80, 30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setTextLimit(6);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHoTen.setBounds(204, 70, 80, 30);

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setEditable(false);
		textHoTen.setTextLimit(50);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(290, 70, 268, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(10, 120, 100, 30);

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setEditable(false);
		textDonVi.setTextLimit(50);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textDonVi.setBounds(118, 120, 440, 30);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoBPM.setBounds(10, 170, 100, 30);

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setTextLimit(20);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setBounds(118, 170, 290, 30);

		CLabel lbNoiDung = new CLabel(composite, SWT.RIGHT);
		lbNoiDung.setText("Nội dung");
		lbNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNoiDung.setBounds(10, 220, 100, 30);

		textNoiDung = new Text(composite, SWT.BORDER);
		textNoiDung.setTextLimit(80);
		textNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNoiDung.setBackground(SWTResourceManager.getColor(255, 250, 205));
		textNoiDung.setBounds(118, 220, 440, 30);

		CLabel lbHinhAnh = new CLabel(composite, SWT.BORDER | SWT.RIGHT);
		lbHinhAnh.setText("");
		lbHinhAnh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHinhAnh.setBounds(585, 20, 172, 230);

		textNoiDung.setFocus();

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Thêm nhận sửa thiết bị");
			lbTheTu.setText("Thẻ từ");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbSoBPM.setText("Số BPM");
			lbNoiDung.setText("Nội dung");

		} else {
			// Tieng Anh
			shell.setText("Add repair device");
			lbTheTu.setText("Card");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbSoBPM.setText("BPM number");
			lbNoiDung.setText("Content");

		}

		// text số thẻ chỉ cho nhập số
		textSoThe.addVerifyListener(new VerifyListener() {
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

		// Điền thông tin số thẻ, họ tên, đơn vị sau khi quẹt thẻ, lưu luôn
		textTheTu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì điền thông tin
				if (e.character == SWT.CR) {
					if (!textTheTu.getText().isEmpty()) {
						try {
							if (connect == null) {
								connect = new ConnectSQL();
								connect.setConnection();
							}
							String select = "";
							ResultSet result;

							select = "SELECT [Data_Person].[Person_ID],[Data_Person].[Magneticcard_ID] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key";

							PreparedStatement ps = connect.getConnection().prepareStatement(select);

							// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
							result = ps.executeQuery();

							textSoThe.setText("");
							textHoTen.setText("");
							textDonVi.setText("");

							while (result.next()) {
								try {
									Long numbercard = Long.parseLong(textTheTu.getText());
									if (Card.getCard(result.getString(2)).equals(numbercard + "")) {
										textSoThe.setText(result.getString(1));
										break;
									}
								} catch (Exception ex) {
								}
							}
							result.close();

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
						save();
					}
				}
			}
		});

		// sự kiện enter ở số thẻ thì lưu
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì điền thông tin
				if (e.character == SWT.CR) {
					save();
				}
			}
		});

		// Sự kiện thay đổi dữ liệu thẻ từ, điền dữ liệu số thẻ, họ tên, đơn vị dựa vào
		// số thẻ
		// ---------------------------------------------------------------------------------------------------
		textTheTu.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				textSoThe.setText("");
				textHoTen.setText("");
				textDonVi.setText("");
			}
		});

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

					select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name],[Data_Person].[Person_Image] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
							+ textSoThe.getText() + "')";

					PreparedStatement ps = connect.getConnection().prepareStatement(select);

					// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
					result = ps.executeQuery();

					textHoTen.setText("");
					textDonVi.setText("");
					lbHinhAnh.setBackground(SWTResourceManager.getColor(240, 240, 240));
					while (result.next()) {
						textHoTen.setText(result.getString(1));
						textDonVi.setText(result.getString(2));

						try {
							Image image = new Image(null, new ImageData(result.getBinaryStream(3)));
							lbHinhAnh.setBackground(ResizeImage.resize(image, lbHinhAnh.getBounds().width,
									lbHinhAnh.getBounds().height));
						} catch (Exception ex) {
						}

					}
					result.close();

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

	public void save() {
		if (!textSoThe.getText().isEmpty()) {
			if (!textHoTen.getText().isEmpty()) {
				if (!textNoiDung.getText().isEmpty()) {

					try {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}
						connect.setStatement();
						try {
							String mathietbisua = "";
							int deviceid = 0;
							String select = "SELECT TOP 1 (SELECT SUBSTRING([MaSuaThietBi],3,8)) FROM [dbo].[LK_SuaThietBi] ORDER BY [MaSuaThietBi] DESC";
							ResultSet resultmathietbi = connect.getStatement().executeQuery(select);
							while (resultmathietbi.next()) {
								mathietbisua = resultmathietbi.getString(1);
							}
							try {
								if (!mathietbisua.isEmpty()) {
									deviceid = Integer.parseInt(mathietbisua) + 1;
								} else {
									deviceid = 10000001;
								}
							} catch (Exception ew) {
								deviceid = 10000001;
							}

							String insert = "INSERT INTO [dbo].[LK_SuaThietBi] ([MaSuaThietBi],[SoBPM],[SoThe],[HoTen],[DonVi],[NoiDung],[ThoiGianDemLenIT],[NguoiCapNhat]) VALUES ('TB"
									+ deviceid + "','" + textSoBPM.getText() + "','" + textSoThe.getText() + "',N'"
									+ textHoTen.getText() + "',N'" + textDonVi.getText() + "',N'"
									+ textNoiDung.getText() + "',GETDATE(),'" + userlogin + "')";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								isaddsuccess = true;
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Thông báo");
									thongbao.setMessage("Thêm thành công!");
								} else {
									thongbao.setText("Notice");
									thongbao.setMessage("Add suscess!");
								}
								thongbao.open();

								textTheTu.setText("");
								textSoThe.setText("");
								textHoTen.setText("");
								textDonVi.setText("");
								textSoBPM.setText("");
								textNoiDung.setText("");

							}

						} catch (Exception ex) {
						}

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
						textTheTu.setText("");
						textSoThe.setText("");
						textHoTen.setText("");
						textDonVi.setText("");
					}
				} else {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Nội dung rỗng!");
					} else {
						thongbao.setText("Notice");
						thongbao.setMessage("Content is empty!");
					}
					textTheTu.setText("");
					textSoThe.setText("");
					textHoTen.setText("");
					textDonVi.setText("");
					thongbao.open();
				}
			} else {
				MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				if (ngonngu == 0) {
					thongbao.setText("Thông báo");
					thongbao.setMessage("Họ tên rỗng!");
				} else {
					thongbao.setText("Notice");
					thongbao.setMessage("Name is empty!");
				}
				textTheTu.setText("");
				textSoThe.setText("");
				textHoTen.setText("");
				textDonVi.setText("");
				thongbao.open();
			}
		} else {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo");
				thongbao.setMessage("Số thẻ rỗng!");
			} else {
				thongbao.setText("Notice");
				thongbao.setMessage("ID is empty!");
			}
			textTheTu.setText("");
			textSoThe.setText("");
			textHoTen.setText("");
			textDonVi.setText("");
			thongbao.open();
		}
	}

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	// kiểm tra xem có thêm thành công không
	public boolean isAddSuccess() {
		return isaddsuccess;
	}
}
