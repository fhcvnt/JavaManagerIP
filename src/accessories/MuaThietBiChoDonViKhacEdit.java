package accessories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import date.ConvertDate;
import sql.ConnectSQL;

public class MuaThietBiChoDonViKhacEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoBPM;
	private int ngonngu = 1;
	private DateTime dateTimeNgayLamPhieu;
	private Table table;
	private String userlogin = "21608";
	private boolean iseditsuccess = false;
	private String sobpm = "w";
	private String ngaylamphieu = "";

	private TableEditor tableEditor;

	private ArrayList<Text> arraytextnoidung;
	private ArrayList<Text> arraytextsoluong;
	private ArrayList<Text> arraytextsothe;
	private ArrayList<Text> arraytexthoten;
	private ArrayList<CCombo> arraycombodonvi;
	private ArrayList<Button> arraybuttonngayve;
	private ArrayList<Button> arraybuttonngaytongvunhanphieu;
	private ArrayList<DateTime> arraydatetimengaytongvunhanphieu;
	private ArrayList<DateTime> arraydatetimengayve;
	private ArrayList<Button> arraybuttonngaythay;
	private ArrayList<DateTime> arraydatetimengaythay;
	private ArrayList<Text> arraytextghichu;

	private ArrayList<String> arraydonvi = new ArrayList<>();

	public static void main(String[] args) {
		try {
			MuaThietBiChoDonViKhacEdit window = new MuaThietBiChoDonViKhacEdit();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(MuaThietBiChoDonViKhacEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1452, 719);
		shell.setText("Add buy device");
//		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
//				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);
		shell.setMaximized(true);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setBounds(10, 20, 133, 30);
		lbSoBPM.setText("S??? BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setBounds(149, 20, 243, 30);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setTextLimit(20);

		CLabel lbNgayLamPhieu = new CLabel(composite, SWT.RIGHT);
		lbNgayLamPhieu.setText("Ng??y l??m phi???u");
		lbNgayLamPhieu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayLamPhieu.setBounds(398, 20, 173, 30);

		dateTimeNgayLamPhieu = new DateTime(composite, SWT.BORDER);
		dateTimeNgayLamPhieu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayLamPhieu.setBounds(577, 20, 131, 30);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhacEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(719, 20, 131, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhacEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(858, 20, 131, 32);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(10, 70, 1426, 598);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 140);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setResizable(false);
		tbcSTT.setWidth(52);
		tbcSTT.setText("STT");

		TableColumn tbcMaMuaThietBi = new TableColumn(table, SWT.NONE);
		tbcMaMuaThietBi.setWidth(0);
		tbcMaMuaThietBi.setText("M?? mua thi???t b???");
		tbcMaMuaThietBi.setResizable(false);

		TableColumn tbcNoiDung = new TableColumn(table, SWT.NONE);
		tbcNoiDung.setWidth(187);
		tbcNoiDung.setText("N???i dung");

		TableColumn tbcSoLuong = new TableColumn(table, SWT.NONE);
		tbcSoLuong.setWidth(88);
		tbcSoLuong.setText("S??? l?????ng");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(73);
		tbcSoThe.setText("S??? th???");

		TableColumn tbcHoTen = new TableColumn(table, SWT.NONE);
		tbcHoTen.setWidth(201);
		tbcHoTen.setText("H??? t??n");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(220);
		tbcDonVi.setText("????n v???");

		TableColumn tbcCheckNgayTongVuNhanPhieu = new TableColumn(table, SWT.NONE);
		tbcCheckNgayTongVuNhanPhieu.setWidth(40);
		tbcCheckNgayTongVuNhanPhieu.setResizable(false);

		TableColumn tbcNgayTongVuNhanPhieu = new TableColumn(table, SWT.NONE);
		tbcNgayTongVuNhanPhieu.setWidth(205);
		tbcNgayTongVuNhanPhieu.setText("Ng??y t???ng v??? nh???n phi???u");

		TableColumn tbcCheckNgayVe = new TableColumn(table, SWT.NONE);
		tbcCheckNgayVe.setWidth(40);
		tbcCheckNgayVe.setResizable(false);

		TableColumn tbcNgayVe = new TableColumn(table, SWT.NONE);
		tbcNgayVe.setWidth(139);
		tbcNgayVe.setText("Ng??y v???");

		TableColumn tbccheckNgayThay = new TableColumn(table, SWT.NONE);
		tbccheckNgayThay.setWidth(40);
		tbccheckNgayThay.setResizable(false);

		TableColumn tbcNgayThay = new TableColumn(table, SWT.NONE);
		tbcNgayThay.setWidth(144);
		tbcNgayThay.setText("Ng??y thay");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(134);
		tbcGhiChu.setText("Ghi ch??");

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Th??m mua thi???t b???");
			lbSoBPM.setText("S??? BPM");
			lbNgayLamPhieu.setText("Ng??y l??m phi???u");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
			tbcSTT.setText("STT");
			tbcNoiDung.setText("N???i dung");
			tbcSoLuong.setText("S??? l?????ng");
			tbcSoThe.setText("S??? th???");
			tbcHoTen.setText("H??? t??n");
			tbcDonVi.setText("????n v???");
			tbcNgayTongVuNhanPhieu.setText("Ng??y t???ng v??? nh???n phi???u");
			tbcNgayVe.setText("Ng??y v???");
			tbcNgayThay.setText("Ng??y thay");
			tbcGhiChu.setText("Ghi ch??");
		} else {
			// Tieng Anh
			shell.setText("Add buy device");
			lbSoBPM.setText("BPM number");
			lbNgayLamPhieu.setText("Writing date");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			tbcSTT.setText("Num");
			tbcNoiDung.setText("Content");
			tbcSoLuong.setText("Count");
			tbcSoThe.setText("ID");
			tbcHoTen.setText("Name");
			tbcDonVi.setText("Department");
			tbcNgayTongVuNhanPhieu.setText("Purchaser receives the order");
			tbcNgayVe.setText("Return day");
			tbcNgayThay.setText("Replacement date");
			tbcGhiChu.setText("Note");
		}
		textSoBPM.setText(sobpm);
		ConvertDate.setDateTime(dateTimeNgayLamPhieu, ngaylamphieu);

		// l???y d??? li???u cho combo ????n v???
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [Department_Name] FROM [SV4].[HRIS].[dbo].[Data_Department] WHERE [Department_Name] NOT LIKE N'Z%' ORDER BY [Department_Name] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				arraydonvi.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Th??ng b??o l???i!");
				thongbao.setMessage("L???i!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		loadDataTable();

		// L??u--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!textSoBPM.getText().isEmpty()) {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}
						connect.setStatement();
						TableItem[] items = table.getItems();
						String update = "";

						for (int i = 0; i < items.length; i++) {
							if (!arraytextnoidung.get(i).getText().isEmpty()
									&& !arraytextsoluong.get(i).getText().isEmpty()
									&& !arraycombodonvi.get(i).getText().isEmpty()) {
								String ngaytongvunhanphieu = arraybuttonngaytongvunhanphieu.get(i).getSelection()
										? ",[NgayTongVuNhanPhieu] = '"
												+ ConvertDate.saveDateToSQL(arraydatetimengaytongvunhanphieu.get(i))
												+ "'"
										: ",[NgayTongVuNhanPhieu] = NULL";
								String ngayve = arraybuttonngayve.get(i).getSelection()
										? ",[NgayVe] = '" + ConvertDate.saveDateToSQL(arraydatetimengayve.get(i)) + "'"
										: ",[NgayVe] = NULL";
								String ngaythay = arraybuttonngaythay.get(i).getSelection()
										? ",[NgayThayThe] = '" + ConvertDate.saveDateToSQL(arraydatetimengaythay.get(i))
												+ "'"
										: ",[NgayThayThe] = NULL";

								update = update + "UPDATE [dbo].[LK_MuaLinhKien] SET [SoBPM] = '" + textSoBPM.getText()
										+ "',[NoiDung] = N'" + arraytextnoidung.get(i).getText() + "',[SoLuong] = "
										+ arraytextsoluong.get(i).getText() + ",[SoThe] = '"
										+ arraytextsothe.get(i).getText() + "',[HoTen] = N'"
										+ arraytexthoten.get(i).getText() + "',[DonVi] = N'"
										+ arraycombodonvi.get(i).getText() + "',[NgayLamPhieu] = '"
										+ ConvertDate.saveDateToSQL(dateTimeNgayLamPhieu) + "'" + ngaytongvunhanphieu
										+ ngayve + ngaythay + ",[GhiChu] = N'" + arraytextghichu.get(i).getText()
										+ "',[NguoiCapNhat] = '" + userlogin + "' WHERE [MaMuaThietBi]='"
										+ items[i].getText(1) + "'" + "\n";
							}
						}

						int resultbuy = connect.execUpdateQuery(update);
						if (resultbuy > 0) {
							iseditsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Th??ng b??o");
								thongbao.setMessage("C???p nh???t th??nh c??ng!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Update suscess!");
							}
							thongbao.open();
							shell.dispose();
						}
						connect.closeStatement();
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Th??ng b??o l???i");
							thongbao.setMessage("S??? phi???u BPM r???ng!");
						} else {
							thongbao.setText("Error");
							thongbao.setMessage("BPM number is empty!");
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
		});

		// H???y--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

	// load d??? li???u cho table
	public void loadDataTable() {

		table.removeAll();
		try {
			int sizetable = arraytextnoidung.size();
			for (int i = 0; i < sizetable; i++) {
				arraytextnoidung.get(i).dispose();
				arraytextsoluong.get(i).dispose();
				arraytextsothe.get(i).dispose();
				arraytexthoten.get(i).dispose();
				arraycombodonvi.get(i).dispose();
				arraybuttonngaytongvunhanphieu.get(i).dispose();
				arraydatetimengaytongvunhanphieu.get(i).dispose();
				arraybuttonngayve.get(i).dispose();
				arraydatetimengayve.get(i).dispose();
				arraybuttonngaythay.get(i).dispose();
				arraydatetimengaythay.get(i).dispose();
				arraytextghichu.get(i).dispose();
			}
		} catch (Exception ex) {
		}
		arraytextnoidung = new ArrayList<>();
		arraytextsoluong = new ArrayList<>();
		arraytextsothe = new ArrayList<>();
		arraytexthoten = new ArrayList<>();
		arraycombodonvi = new ArrayList<>();
		arraybuttonngayve = new ArrayList<>();
		arraybuttonngaytongvunhanphieu = new ArrayList<>();
		arraydatetimengaytongvunhanphieu = new ArrayList<>();
		arraydatetimengayve = new ArrayList<>();
		arraybuttonngaythay = new ArrayList<>();
		arraydatetimengaythay = new ArrayList<>();
		arraytextghichu = new ArrayList<>();

		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [MaMuaThietBi],[NoiDung],[SoLuong],[SoThe],[HoTen],[DonVi],[NgayTongVuNhanPhieu],[NgayVe],[NgayThayThe],[GhiChu] FROM [dbo].[LK_MuaLinhKien] WHERE [SoBPM]='"
					+ sobpm + "' ORDER BY [MaMuaThietBi] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(
						new String[] { stt + "", result.getString(1), "", "", "", "", "", "", "", "", "", "", "" });
				stt++;

				// column n???i dung
				tableEditor = new TableEditor(table);
				Text textnoidung = new Text(table, SWT.NONE);
				textnoidung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textnoidung.setTextLimit(80);
				textnoidung.setText(result.getString(2));
				arraytextnoidung.add(textnoidung);
				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = textnoidung.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(textnoidung, item, 2);

				// column s??? l?????ng
				tableEditor = new TableEditor(table);
				Text text = new Text(table, SWT.NONE | SWT.RIGHT);
				text.setText(result.getString(3));
				text.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				text.setTextLimit(4);
				// text s??? l?????ng ch??? cho nh???p s???
				text.addVerifyListener(new VerifyListener() {
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
				arraytextsoluong.add(text);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(text, item, 3);

				// column S??? th???
				tableEditor = new TableEditor(table);
				Text textsothe = new Text(table, SWT.NONE | SWT.RIGHT);
				textsothe.setText(result.getString(4));
				textsothe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textsothe.setTextLimit(6);
				// text s??? th??? ch??? cho nh???p s???
				textsothe.addVerifyListener(new VerifyListener() {
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
				arraytextsothe.add(textsothe);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(textsothe, item, 4);

				// column h??? t??n
				tableEditor = new TableEditor(table);
				Text texthoten = new Text(table, SWT.NONE | SWT.LEFT);
				texthoten.setText(result.getString(5));
				texthoten.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				texthoten.setTextLimit(50);
				arraytexthoten.add(texthoten);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(texthoten, item, 5);

				// column ????n v???
				tableEditor = new TableEditor(table);
				CCombo combodepartment = new CCombo(table, SWT.NONE);
				combodepartment.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				combodepartment.setBackground(SWTResourceManager.getColor(255, 255, 255));
				combodepartment.setTextLimit(50);
				combodepartment.setText(result.getString(6));
				for (int k = 0; k < arraydonvi.size(); k++) {
					combodepartment.add(arraydonvi.get(k));
				}
				combodepartment.setEditable(false);
				arraycombodonvi.add(combodepartment);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(combodepartment, item, 6);

				// S??? ki???n thay ?????i d??? li???u s??? th???, ??i???n d??? li???u h??? t??n, ????n v??? d???a v??o s??? th???
				// ---------------------------------------------------------------------------------------------------
				textsothe.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent arg0) {
						try {
							if (connect == null) {
								connect = new ConnectSQL();
								connect.setConnection();
							}
							String select = "";
							ResultSet result;

							select = "SELECT [Data_Person].[Person_Name],[Data_Department].[Department_Name] FROM [SV4].[HRIS].[dbo].[Data_Person],[SV4].[HRIS].[dbo].[Data_Department] WHERE Data_Person.Person_Status=1 AND Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND (Data_Person.Person_ID='"
									+ textsothe.getText() + "')";

							PreparedStatement ps = connect.getConnection().prepareStatement(select);

							// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
							result = ps.executeQuery();
							boolean hasdata = false;
							while (result.next()) {
								texthoten.setText(result.getString(1));
								combodepartment.setText(result.getString(2));
								hasdata = true;
							}
							result.close();
							if (!hasdata) {
								texthoten.setText("");
								combodepartment.setText("");
							}

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

				// column check ng??y t???ng v??? nh???n phi???u
				tableEditor = new TableEditor(table);
				Button btncheckngaytongvunhanphieu = new Button(table, SWT.CHECK);
				btncheckngaytongvunhanphieu.pack();
				arraybuttonngaytongvunhanphieu.add(btncheckngaytongvunhanphieu);
				tableEditor.minimumWidth = btncheckngaytongvunhanphieu.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(btncheckngaytongvunhanphieu, item, 7);

				// column ng??y t???ng v??? nh???n phi???u
				tableEditor = new TableEditor(table);
				DateTime datetimengaytongvunhanphieu = new DateTime(table, SWT.NONE);
				datetimengaytongvunhanphieu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				arraydatetimengaytongvunhanphieu.add(datetimengaytongvunhanphieu);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(datetimengaytongvunhanphieu, item, 8);

				// l???y d??? li???u cho DateTime
				if (result.getString(7) == null) {
					btncheckngaytongvunhanphieu.setSelection(false);
					datetimengaytongvunhanphieu.setEnabled(false);
				} else {
					btncheckngaytongvunhanphieu.setSelection(true);
					ConvertDate.setDateTimefromSQL(datetimengaytongvunhanphieu, result.getString(7));
					datetimengaytongvunhanphieu.setEnabled(true);
				}

				// b???t s??? ki???n check v??o checkbox Ng??y t???ng v??? nh???n phi???u
				btncheckngaytongvunhanphieu.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (btncheckngaytongvunhanphieu.getSelection()) {
							datetimengaytongvunhanphieu.setEnabled(true);
						} else {
							datetimengaytongvunhanphieu.setEnabled(false);
						}
					}
				});

				// column check ng??y v???
				tableEditor = new TableEditor(table);
				Button btncheckngayve = new Button(table, SWT.CHECK);
				btncheckngayve.pack();
				arraybuttonngayve.add(btncheckngayve);
				tableEditor.minimumWidth = btncheckngayve.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(btncheckngayve, item, 9);

				// column ng??y v???
				tableEditor = new TableEditor(table);
				DateTime datetimengayve = new DateTime(table, SWT.NONE);
				datetimengayve.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				arraydatetimengayve.add(datetimengayve);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(datetimengayve, item, 10);

				// l???y d??? li???u cho DateTime
				if (result.getString(8) == null) {
					btncheckngayve.setSelection(false);
					datetimengayve.setEnabled(false);
				} else {
					btncheckngayve.setSelection(true);
					ConvertDate.setDateTimefromSQL(datetimengayve, result.getString(8));
					datetimengayve.setEnabled(true);
				}

				// b???t s??? ki???n check v??o checkbox Ng??y v???
				btncheckngayve.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (btncheckngayve.getSelection()) {
							datetimengayve.setEnabled(true);
						} else {
							datetimengayve.setEnabled(false);
						}
					}
				});

				// column check ng??y thay th???
				tableEditor = new TableEditor(table);
				Button btncheckngaythaythe = new Button(table, SWT.CHECK);
				btncheckngaythaythe.pack();
				arraybuttonngaythay.add(btncheckngaythaythe);
				tableEditor.minimumWidth = btncheckngaythaythe.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(btncheckngaythaythe, item, 11);

				// column ng??y thay th???
				tableEditor = new TableEditor(table);
				DateTime datetimengaythay = new DateTime(table, SWT.NONE);
				datetimengaythay.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				arraydatetimengaythay.add(datetimengaythay);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(datetimengaythay, item, 12);

				// l???y d??? li???u cho DateTime
				if (result.getString(9) == null) {
					btncheckngaythaythe.setSelection(false);
					datetimengaythay.setEnabled(false);
				} else {
					btncheckngaythaythe.setSelection(true);
					ConvertDate.setDateTimefromSQL(datetimengaythay, result.getString(9));
					datetimengaythay.setEnabled(true);
				}

				// b???t s??? ki???n check v??o checkbox Ng??y thay th???
				btncheckngaythaythe.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (btncheckngaythaythe.getSelection()) {
							datetimengaythay.setEnabled(true);
						} else {
							datetimengaythay.setEnabled(false);
						}
					}
				});

				// column ghi ch??
				tableEditor = new TableEditor(table);
				Text textghichu = new Text(table, SWT.NONE | SWT.LEFT);
				textghichu.setText(result.getString(10));
				textghichu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textghichu.setTextLimit(50);
				arraytextghichu.add(textghichu);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(textghichu, item, 13);
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

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	// ki???m tra xem c?? s???a th??nh c??ng kh??ng
	public boolean isEditSuccess() {
		return iseditsuccess;
	}

	// set so BPM
	public void setSoBPMvaNgayLamPhieu(String bpm, String ngaylamphieu) {
		sobpm = bpm;
		// ngaylamphieu c?? d???ng dd/MM/yyyy, 22/01/2022
		this.ngaylamphieu = ngaylamphieu;
	}
}
