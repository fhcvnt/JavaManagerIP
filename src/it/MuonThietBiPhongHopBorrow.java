package it;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import card.Card;
import image.ResizeImage;
import sql.ConnectSQL;

public class MuonThietBiPhongHopBorrow {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textDonVi;
	private int ngonngu = 1;
	private Text textHoTen;
	private Text textSoThe;
	private CLabel lbImage;;
	private String userlogin = "21608";
	private boolean isborrowsuccess = false;
	private Text textGhiChu;
	private Table table;
	private Text textTheTu;
	private TableEditor tableEditor;
	private ArrayList<Button> buttons;
	private ArrayList<TableItem> itemssave;

	public static void main(String[] args) {
		try {
			MuonThietBiPhongHopBorrow window = new MuonThietBiPhongHopBorrow();
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
		shell.setImage(SWTResourceManager.getImage(MuonThietBiPhongHopBorrow.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1094, 737);
		shell.setText("Borrow meeting room equipment");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Composite composite = new Composite(scrolledComposite, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);
		scrolledComposite.setContent(composite);

		CLabel lbTheTu = new CLabel(composite, SWT.RIGHT);
		lbTheTu.setText("Thẻ từ");
		lbTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTheTu.setBounds(10, 25, 102, 30);

		textTheTu = new Text(composite, SWT.PASSWORD | SWT.BORDER);
		textTheTu.setTextLimit(20);
		textTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTheTu.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textTheTu.setBounds(121, 25, 288, 30);
		textTheTu.setFocus();

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoThe.setBounds(10, 75, 102, 30);

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setBounds(121, 75, 288, 30);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHoTen.setBounds(10, 125, 102, 30);

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setEditable(false);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(121, 125, 288, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 175, 102, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setEditable(false);
		textDonVi.setBounds(121, 175, 288, 30);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		textGhiChu = new Text(composite, SWT.BORDER);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(121, 225, 288, 30);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi chú");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(10, 225, 102, 30);

		lbImage = new CLabel(composite, SWT.BORDER);
		lbImage.setBounds(121, 275, 166, 222);
		lbImage.setText("");

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(255, 215, 0));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(427, 25, 629, 652);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcSTT = new TableColumn(table, SWT.CENTER);
		tbcSTT.setWidth(81);
		tbcSTT.setText("STT");
		tbcSTT.setResizable(false);

		TableColumn tbcCheck = new TableColumn(table, SWT.CENTER);
		tbcCheck.setWidth(86);
		tbcCheck.setText("Chọn");
		tbcCheck.setResizable(false);

		TableColumn tbcMaThietBiPhongHop = new TableColumn(table, SWT.NONE);
		tbcMaThietBiPhongHop.setWidth(0);
		tbcMaThietBiPhongHop.setText("Mã thiết bị");
		tbcMaThietBiPhongHop.setResizable(false);

		TableColumn tbcThietBiPhongHop = new TableColumn(table, SWT.NONE);
		tbcThietBiPhongHop.setWidth(279);
		tbcThietBiPhongHop.setText("Thiết bị phòng họp");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(162);
		tbcGhiChu.setText("Ghi chú");

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			shell.setText("Mượn thiết bị phòng họp");
			lbTheTu.setText("Thẻ từ");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbGhiChu.setText("Ghi chú");

			tbcSTT.setText("STT");
			tbcCheck.setText("Chọn");
			tbcMaThietBiPhongHop.setText("Mã thiết bị");
			tbcThietBiPhongHop.setText("Thiết bị phòng họp");
			tbcGhiChu.setText("Ghi chú");
		} else {
			shell.setText("Borrow meeting room equipment");
			lbTheTu.setText("Card");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbGhiChu.setText("Note");

			tbcSTT.setText("Number");
			tbcCheck.setText("Check");
			tbcMaThietBiPhongHop.setText("Device id");
			tbcThietBiPhongHop.setText("Meeting room equipment");
			tbcGhiChu.setText("Note");
		}
		loadDataTable();
		itemssave = new ArrayList<>();
		// Điền thông tin số thẻ, họ tên, đơn vị sau khi quẹt thẻ, lưu luôn danh sách
		// máy ảnh được mượn
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
					lbImage.setBackground(SWTResourceManager.getColor(240, 240, 240));
					while (result.next()) {
						textHoTen.setText(result.getString(1));
						textDonVi.setText(result.getString(2));

						try {
							Image image = new Image(null, new ImageData(result.getBinaryStream(3)));
							lbImage.setBackground(
									ResizeImage.resize(image, lbImage.getBounds().width, lbImage.getBounds().height));
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

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	// kiểm tra xem có mượn thiết bị phòng họp hay không
	public boolean isBorrowSuccess() {
		return isborrowsuccess;
	}

	public void save() {
		if (!textSoThe.getText().isEmpty()) {
			if (!textHoTen.getText().isEmpty()) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					try {
						String mathietbimuon = "";
						int deviceid = 0;
						String select = "SELECT TOP 1 (SELECT SUBSTRING([MaMuonThietBi],2,8)) FROM [dbo].[IT_ChoMuonThietBiPhongHop] ORDER BY [MaMuonThietBi] DESC";
						ResultSet resultmathietbi = connect.getStatement().executeQuery(select);
						while (resultmathietbi.next()) {
							mathietbimuon = resultmathietbi.getString(1);
						}
						try {
							if (!mathietbimuon.isEmpty()) {
								deviceid = Integer.parseInt(mathietbimuon) + 1;
							} else {
								deviceid = 10000001;
							}
						} catch (Exception ew) {
							deviceid = 10000001;
						}

						if (itemssave.size() == 0) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Bạn chưa chọn thiết bị nào!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("You are not select any device!");
							}
							thongbao.open();
							textTheTu.setText("");
							textTheTu.setFocus();
							textSoThe.setText("");
							textHoTen.setText("");
							textDonVi.setText("");
							return;
						}

						String insert = "";
						String update = "";
						for (int i = 0; i < itemssave.size(); i++) {
							insert = insert
									+ "INSERT INTO [dbo].[IT_ChoMuonThietBiPhongHop] ([MaMuonThietBi],[SoThe],[HoTen],[DonVi],[MaThietBi],[GhiChu],[NguoiChoMuon],[ThoiGianMuon]) VALUES ('"
									+ "B" + deviceid + "','" + textSoThe.getText() + "',N'" + textHoTen.getText()
									+ "',N'" + textDonVi.getText() + "','" + itemssave.get(i).getText(2) + "',N'"
									+ textGhiChu.getText() + "','" + userlogin + "',GETDATE())";
							insert = insert + "\n";
							update = update + "UPDATE [dbo].[IT_ThietBiChoMuon] SET [DaMuon] = 1 WHERE [MaThietBi]='"
									+ itemssave.get(i).getText(2) + "'";
							update = update + "\n";
							deviceid++;
						}
						int result = connect.execUpdateQuery(insert);
						if (result > 0) {
							isborrowsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Mượn thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Borrow suscess!");
							}
							thongbao.open();

							textTheTu.setText("");
							textSoThe.setText("");
							textHoTen.setText("");
							textDonVi.setText("");

							connect.execUpdateQuery(update);
							loadDataTable();
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
			thongbao.open();
		}
	}

	// load du lieu cho table
	public void loadDataTable() {
		table.removeAll();
		try {
			itemssave.clear();
		} catch (Exception e) {
		}
		try {
			int sizetable = buttons.size();
			for (int i = 0; i < sizetable; i++) {
				buttons.get(i).dispose();
			}
		} catch (Exception ex) {
		}
		buttons = new ArrayList<>();

		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();
			table.removeAll();

			String select = "SELECT [MaThietBi],[TenThietBi],[GhiChu] FROM [dbo].[IT_ThietBiChoMuon] WHERE [DaMuon]=0 AND [TrangThai]=1 AND [LoaiThietBi]=3 ORDER BY [TenThietBi] ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;

			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(
						new String[] { stt + "", "", result.getString(1), result.getString(2), result.getString(3) });
				stt++;

				// điền dữ liệu cho combobox
				tableEditor = new TableEditor(table);
				// column check
				tableEditor = new TableEditor(table);
				Button button = new Button(table, SWT.CHECK);
				button.pack();

				buttons.add(button);
				tableEditor.minimumWidth = button.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(button, item, 1);

				button.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						if (button.getSelection()) {
							itemssave.add(item);
						} else {
							itemssave.remove(item);
						}
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub -tu dong tao ra phuong thuc so khai
					}
				});
			}

			result.close();
			state.close();
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
	}
}
