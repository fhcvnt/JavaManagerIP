package it;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

public class NhanVienMuonCameraYeezyAdd {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textHoTen;
	private int ngonngu = 1;
	private Text textSoThe;
	private boolean isaddsuccess = false;
	private Text textDonVi;
	private Text textTheTu;

	public static void main(String[] args) {
		try {
			NhanVienMuonCameraYeezyAdd window = new NhanVienMuonCameraYeezyAdd();
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
		shell.setImage(SWTResourceManager.getImage(NhanVienMuonCameraYeezyAdd.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(577, 339);
		shell.setText("Add person");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("S??? th???");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(10, 30, 110, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBounds(126, 30, 119, 30);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(10, 79, 110, 30);
		lbHoTen.setText("H??? t??n");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setBounds(126, 80, 409, 30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setTextLimit(50);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 130, 110, 30);
		lbDonVi.setText("????n v???");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setTextLimit(50);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setBounds(126, 130, 409, 30);

		CLabel lbTheTu = new CLabel(composite, SWT.RIGHT);
		lbTheTu.setText("Th??? t???");
		lbTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTheTu.setBounds(10, 181, 110, 30);

		textTheTu = new Text(composite, SWT.BORDER);
		textTheTu.setTextLimit(50);
		textTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTheTu.setBounds(126, 181, 409, 30);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(NhanVienMuonCameraYeezyAdd.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(185, 236, 119, 35);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(NhanVienMuonCameraYeezyAdd.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(310, 236, 120, 35);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			shell.setText("Th??m nh??n vi??n");
			lbSoThe.setText("S??? th???");
			lbHoTen.setText("H??? t??n");
			lbDonVi.setText("????n v???");
			lbTheTu.setText("Th??? t???");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
		} else {
			shell.setText("Add person");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbTheTu.setText("Magnetic card");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
		}

		// S??? ki???n thay ?????i d??? li???u s??? th???, ??i???n d??? li???u h??? t??n, ????n v??? d???a v??o s??? th???
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

					select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name],[Data_Person].[Magneticcard_ID] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND Data_Person.Person_ID='"
							+ textSoThe.getText() + "'";

					PreparedStatement ps = connect.getConnection().prepareStatement(select);

					// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
					result = ps.executeQuery();

					textHoTen.setText("");
					textDonVi.setText("");

					while (result.next()) {
						textHoTen.setText(result.getString(1));
						textDonVi.setText(result.getString(2));
						textTheTu.setText(result.getString(3));
					}
					result.close();

				} catch (SQLException se) {
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

		// L??u khi enter ??? text s??? th???
		textSoThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					save();
				}
			}
		});

		// H???y--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});

		// L??u--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				save();
			}
		});
	}

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language) {
		ngonngu = language;
	}

	// ki???m tra xem c?? th??m th??nh c??ng ??t nh???t 1 thi???t b??? hay kh??ng
	public boolean isAddSuccess() {
		return isaddsuccess;
	}

	// save
	public void save() {

		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			if (!textSoThe.getText().isEmpty()) {
				if (!textHoTen.getText().isEmpty()) {
					if (!textDonVi.getText().isEmpty()) {
						try {
							String insert = "INSERT INTO [dbo].[IT_NhanVienMuonCameraYeezy] ([SoThe],[HoTen],[DonVi],[MaTheTu]) VALUES ('"
									+ textSoThe.getText() + "',N'" + textHoTen.getText() + "',N'" + textDonVi.getText()
									+ "','" + textTheTu.getText() + "')";

							int result = connect.execUpdateQuery(insert);
							if (result > 0) {
								isaddsuccess = true;
								textSoThe.setText("");
								textHoTen.setText("");
								textDonVi.setText("");
								textTheTu.setText("");
							}
							connect.closeStatement();
						} catch (Exception ex) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Th??ng b??o l???i");
								thongbao.setMessage(ex.toString());
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage(ex.toString());
							}
							thongbao.open();
						}
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Th??ng b??o l???i");
							thongbao.setMessage("????n v??? r???ng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Department is empty!");
						}
						thongbao.open();
					}
				} else {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Th??ng b??o l???i");
						thongbao.setMessage("H??? t??n r???ng!");
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Name is empty!");
					}
					thongbao.open();
				}
			} else {
				MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				if (ngonngu == 0) {
					thongbao.setText("Th??ng b??o l???i");
					thongbao.setMessage("S??? th??? r???ng!");
				} else {
					thongbao.setText("Notice error");
					thongbao.setMessage("ID is empty!");
				}
				thongbao.open();
			}
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
}
