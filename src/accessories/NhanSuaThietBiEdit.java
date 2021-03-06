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

public class NhanSuaThietBiEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoThe;
	private int ngonngu = 0;
	private String userlogin = "21608";
	private boolean iseditsuccess = false;

	private Text textHoTen;
	private Text textNoiDung;
	private Text textSoBPM;
	private Text textDonVi;
	private Text textTheTu;
	private Text textSoTheNguoiNhan;
	private Text textNguoiNhan;
	private Text textDonViNguoiNhan;
	private Text textGhiChu;

	private String masuathietbi = "";
	private Text textThoiGianGui;

	public static void main(String[] args) {
		try {
			NhanSuaThietBiEdit window = new NhanSuaThietBiEdit();
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
		shell.setImage(SWTResourceManager.getImage(NhanSuaThietBiEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(811, 535);
		shell.setText("S???a nh???n s???a thi???t b???");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbTheTu = new CLabel(composite, SWT.RIGHT);
		lbTheTu.setText("Th??? t???");
		lbTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTheTu.setBounds(10, 20, 100, 30);

		textTheTu = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textTheTu.setTextLimit(32);
		textTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTheTu.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textTheTu.setBounds(118, 20, 290, 30);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(10, 70, 100, 30);
		lbSoThe.setText("S??? th???");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setEnabled(false);
		textSoThe.setBounds(118, 70, 80, 30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setTextLimit(6);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setText("H??? t??n");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHoTen.setBounds(204, 70, 80, 30);

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setEnabled(false);
		textHoTen.setEditable(false);
		textHoTen.setTextLimit(50);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(290, 70, 268, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setText("????n v???");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(10, 120, 100, 30);

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setEnabled(false);
		textDonVi.setEditable(false);
		textDonVi.setTextLimit(50);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textDonVi.setBounds(118, 120, 440, 30);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setText("S??? BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoBPM.setBounds(10, 170, 100, 30);

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setTextLimit(20);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setBounds(118, 170, 290, 30);

		CLabel lbNoiDung = new CLabel(composite, SWT.RIGHT);
		lbNoiDung.setText("N???i dung");
		lbNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNoiDung.setBounds(10, 220, 100, 30);

		textNoiDung = new Text(composite, SWT.BORDER);
		textNoiDung.setEnabled(false);
		textNoiDung.setTextLimit(80);
		textNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNoiDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNoiDung.setBounds(118, 220, 440, 30);

		CLabel lbThoiGianGui = new CLabel(composite, SWT.RIGHT);
		lbThoiGianGui.setText("Th???i gian g???i");
		lbThoiGianGui.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGianGui.setBounds(10, 270, 100, 30);

		textThoiGianGui = new Text(composite, SWT.BORDER);
		textThoiGianGui.setTextLimit(80);
		textThoiGianGui.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textThoiGianGui.setEnabled(false);
		textThoiGianGui.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textThoiGianGui.setBounds(118, 270, 290, 30);

		CLabel lbHinhAnh = new CLabel(composite, SWT.BORDER | SWT.RIGHT);
		lbHinhAnh.setText("");
		lbHinhAnh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHinhAnh.setBounds(585, 20, 172, 230);

		CLabel lbSoTheNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbSoTheNguoiNhan.setText("S??? th???");
		lbSoTheNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoTheNguoiNhan.setBounds(10, 320, 100, 30);

		textSoTheNguoiNhan = new Text(composite, SWT.BORDER);
		textSoTheNguoiNhan.setTextLimit(6);
		textSoTheNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoTheNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoTheNguoiNhan.setBounds(118, 320, 80, 30);

		CLabel lblNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lblNguoiNhan.setText("Ng?????i nh???n");
		lblNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lblNguoiNhan.setBounds(204, 320, 148, 30);

		textNguoiNhan = new Text(composite, SWT.BORDER);
		textNguoiNhan.setTextLimit(50);
		textNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiNhan.setEditable(false);
		textNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguoiNhan.setBounds(363, 320, 394, 30);

		CLabel lbDonViNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbDonViNguoiNhan.setText("????n v???");
		lbDonViNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonViNguoiNhan.setBounds(10, 370, 100, 30);

		textDonViNguoiNhan = new Text(composite, SWT.BORDER);
		textDonViNguoiNhan.setTextLimit(50);
		textDonViNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonViNguoiNhan.setEditable(false);
		textDonViNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textDonViNguoiNhan.setBounds(118, 370, 639, 30);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi ch??");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(10, 420, 100, 30);

		textGhiChu = new Text(composite, SWT.BORDER);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(118, 420, 639, 30);

		textTheTu.setFocus();

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("S???a nh???n s???a thi???t b???");
			lbTheTu.setText("Th??? t???");
			lbSoThe.setText("S??? th???");
			lbHoTen.setText("H??? t??n");
			lbDonVi.setText("????n v???");
			lbSoBPM.setText("S??? BPM");
			lbNoiDung.setText("N???i dung");
			lbThoiGianGui.setText("Th???i gian g???i");
			lbSoTheNguoiNhan.setText("S??? th???");
			lblNguoiNhan.setText("Ng?????i nh???n");
			lbDonViNguoiNhan.setText("????n v???");
			lbGhiChu.setText("Ghi ch??");

		} else {
			// Tieng Anh
			shell.setText("Edit repair device");
			lbTheTu.setText("Card");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbSoBPM.setText("BPM number");
			lbNoiDung.setText("Content");
			lbThoiGianGui.setText("Sending time");
			lbSoTheNguoiNhan.setText("ID");
			lblNguoiNhan.setText("Receiver");
			lbDonViNguoiNhan.setText("Department");
			lbGhiChu.setText("Note");

		}

		// text s??? th??? ch??? cho nh???p s???
		textSoTheNguoiNhan.addVerifyListener(new VerifyListener() {
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

		// ??i???n th??ng tin s??? th???, h??? t??n, ????n v??? sau khi qu???t th???, l??u lu??n
		textTheTu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? ??i???n th??ng tin
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

							// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
							result = ps.executeQuery();

							textSoTheNguoiNhan.setText("");
							textNguoiNhan.setText("");
							textDonViNguoiNhan.setText("");

							while (result.next()) {
								try {
									Long numbercard = Long.parseLong(textTheTu.getText());
									if (Card.getCard(result.getString(2)).equals(numbercard + "")) {
										textSoTheNguoiNhan.setText(result.getString(1));
										break;
									}
								} catch (Exception ex) {
								}
							}
							result.close();

						} catch (Exception se) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Th??ng b??o l???i");
								thongbao.setMessage("L???i!\n" + se.toString());
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

		// s??? ki???n enter ??? s??? th??? th?? l??u
		textSoTheNguoiNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? ??i???n th??ng tin
				if (e.character == SWT.CR) {
					save();
				}
			}
		});

		// S??? ki???n thay ?????i d??? li???u th??? t???, ??i???n d??? li???u s??? th???, h??? t??n, ????n v??? d???a v??o
		// s??? th???
		// ---------------------------------------------------------------------------------------------------
		textTheTu.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				textSoTheNguoiNhan.setText("");
				textNguoiNhan.setText("");
				textDonViNguoiNhan.setText("");
			}
		});

		// S??? ki???n thay ?????i d??? li???u s??? th???, ??i???n d??? li???u h??? t??n, ????n v??? d???a v??o s??? th???
		// ---------------------------------------------------------------------------------------------------
		textSoTheNguoiNhan.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					String select = "";
					ResultSet result;

					select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name],[Data_Person].[Person_Image] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
							+ textSoTheNguoiNhan.getText() + "')";

					PreparedStatement ps = connect.getConnection().prepareStatement(select);

					// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
					result = ps.executeQuery();

					textNguoiNhan.setText("");
					textDonViNguoiNhan.setText("");
					lbHinhAnh.setBackground(SWTResourceManager.getColor(240, 240, 240));
					while (result.next()) {
						textNguoiNhan.setText(result.getString(1));
						textDonViNguoiNhan.setText(result.getString(2));

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
						thongbao.setText("Th??ng b??o l???i");
						thongbao.setMessage("L???i!\n" + se.toString());
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
		if (!textSoTheNguoiNhan.getText().isEmpty()) {
			if (!textNguoiNhan.getText().isEmpty()) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					try {
						String insert = "UPDATE [dbo].[LK_SuaThietBi] SET [SoBPM]='" + textSoBPM.getText()
								+ "',[SoTheNguoiNhan] = '" + textSoTheNguoiNhan.getText() + "',[HoTenNguoiNhan] = N'"
								+ textNguoiNhan.getText() + "',[DonViNguoiNhan] = N'" + textDonViNguoiNhan.getText()
								+ "',[ThoiGianNhan] = GETDATE(),[GhiChu] = N'" + textGhiChu.getText()
								+ "',[NguoiCapNhat] = '" + userlogin + "' WHERE [MaSuaThietBi]='" + masuathietbi + "'";

						int result = connect.execUpdateQuery(insert);
						if (result > 0) {
							iseditsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Th??ng b??o");
								thongbao.setMessage("S???a th??nh c??ng!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Edit suscess!");
							}
							thongbao.open();

							shell.dispose();

						}

					} catch (Exception ex) {
					}

					connect.closeStatement();
				} catch (Exception se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Th??ng b??o l???i");
						thongbao.setMessage("L???i!\n" + se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Error!\n" + se.toString());
					}
					thongbao.open();
					textTheTu.setText("");
					textSoTheNguoiNhan.setText("");
					textNguoiNhan.setText("");
					textDonViNguoiNhan.setText("");
				}
			} else {
				MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				if (ngonngu == 0) {
					thongbao.setText("Th??ng b??o");
					thongbao.setMessage("S??? th??? kh??ng h???p l???!");
				} else {
					thongbao.setText("Notice");
					thongbao.setMessage("Invalid id!");
				}
				textTheTu.setText("");
				textSoTheNguoiNhan.setText("");
				textNguoiNhan.setText("");
				textDonViNguoiNhan.setText("");
				thongbao.open();
			}
		} else {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (!textTheTu.getText().isEmpty()) {
				if (ngonngu == 0) {
					thongbao.setText("Th??ng b??o");
					thongbao.setMessage("Th??? kh??ng h???p l???!");
				} else {
					thongbao.setText("Notice");
					thongbao.setMessage("Invalid card!");
				}
			} else {
				if (ngonngu == 0) {
					thongbao.setText("Th??ng b??o");
					thongbao.setMessage("S??? th??? r???ng!");
				} else {
					thongbao.setText("Notice");
					thongbao.setMessage("ID is empty!");
				}
			}
			textTheTu.setText("");
			textSoTheNguoiNhan.setText("");
			textNguoiNhan.setText("");
			textDonViNguoiNhan.setText("");
			thongbao.open();
		}
	}

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	public void setData(String masuathietbi, String sothe, String hoten, String donvi, String bpm, String noidung,
			String thoigiangui) {
		this.masuathietbi = masuathietbi;
		textSoThe.setText(sothe);
		textHoTen.setText(hoten);
		textDonVi.setText(donvi);
		textSoBPM.setText(bpm);
		textNoiDung.setText(noidung);
		textThoiGianGui.setText(thoigiangui);
	}

	// ki???m tra xem c?? s???a th??nh c??ng kh??ng
	public boolean isEditSuccess() {
		return iseditsuccess;
	}
}
