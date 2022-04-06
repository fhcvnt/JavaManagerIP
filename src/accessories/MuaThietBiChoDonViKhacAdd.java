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

public class MuaThietBiChoDonViKhacAdd {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoBPM;
	private int ngonngu = 1;
	private DateTime dateTimeNgayLamPhieu;
	private Table table;
	private String userlogin = "21608";
	private boolean isaddsuccess = false;

	private TableEditor tableEditor;

	private ArrayList<Text> arraytextnoidung;
	private ArrayList<Text> arraytextsoluong;
	private ArrayList<Text> arraytextsothe;
	private ArrayList<Text> arraytexthoten;
	private ArrayList<CCombo> arraycombodonvi;
	private ArrayList<Text> arraytextghichu;

	private ArrayList<String> arraydonvi = new ArrayList<>();
	private ArrayList<Integer> vitridaluu;// vị trí các dòng trong table đã lưu để khi lưu chỉ xóa trắng những dòng đó
											// thôi

	public static void main(String[] args) {
		try {
			MuaThietBiChoDonViKhacAdd window = new MuaThietBiChoDonViKhacAdd();
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
		shell.setImage(SWTResourceManager.getImage(MuaThietBiChoDonViKhacAdd.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1253, 719);
		shell.setText("Add buy device");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setBounds(10, 20, 133, 30);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setBounds(149, 20, 243, 30);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setTextLimit(20);

		CLabel lbNgayLamPhieu = new CLabel(composite, SWT.RIGHT);
		lbNgayLamPhieu.setText("Ngày làm phiếu");
		lbNgayLamPhieu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayLamPhieu.setBounds(398, 20, 173, 30);

		dateTimeNgayLamPhieu = new DateTime(composite, SWT.BORDER);
		dateTimeNgayLamPhieu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayLamPhieu.setBounds(577, 20, 131, 30);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhacAdd.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(719, 20, 131, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(MuaThietBiChoDonViKhacAdd.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(858, 20, 131, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(154, 205, 50));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(10, 70, 1227, 598);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setResizable(false);
		tbcSTT.setWidth(52);
		tbcSTT.setText("STT");

		TableColumn tbcNoiDung = new TableColumn(table, SWT.NONE);
		tbcNoiDung.setWidth(232);
		tbcNoiDung.setText("Nội dung");

		TableColumn tbcSoLuong = new TableColumn(table, SWT.NONE);
		tbcSoLuong.setWidth(91);
		tbcSoLuong.setText("Số lượng");

		TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
		tbcSoThe.setWidth(80);
		tbcSoThe.setText("Số thẻ");

		TableColumn tbcHoTen = new TableColumn(table, SWT.NONE);
		tbcHoTen.setWidth(229);
		tbcHoTen.setText("Họ tên");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(249);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(156);
		tbcGhiChu.setText("Ghi chú");

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Thêm mua thiết bị");
			lbSoBPM.setText("Số BPM");
			lbNgayLamPhieu.setText("Ngày làm phiếu");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			tbcSTT.setText("STT");
			tbcNoiDung.setText("Nội dung");
			tbcSoLuong.setText("Số lượng");
			tbcSoThe.setText("Số thẻ");
			tbcHoTen.setText("Họ tên");
			tbcDonVi.setText("Đơn vị");
			tbcGhiChu.setText("Ghi chú");
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
			tbcGhiChu.setText("Note");
		}

		vitridaluu = new ArrayList<>();

		// lấy dữ liệu cho combo đơn vị
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
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		loadDataTable();

		// Lưu--------------------------------------------------------------------------------------------------------------
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
						String insertbuy = "";
						String id = "";
						int idincrease = 0;
						String select = "SELECT TOP 1 (SELECT SUBSTRING([MaMuaThietBi],3,8)) FROM [dbo].[LK_MuaLinhKien] ORDER BY [MaMuaThietBi] DESC";
						ResultSet resultmalinhkienmua = connect.getStatement().executeQuery(select);
						while (resultmalinhkienmua.next()) {
							id = resultmalinhkienmua.getString(1);
						}
						try {
							if (!id.isEmpty()) {
								idincrease = Integer.parseInt(id) + 1;
							} else {
								idincrease = 10000001;
							}
						} catch (Exception ew) {
							idincrease = 10000001;
						}
						for (int i = 0; i < items.length; i++) {
							if (!arraytextnoidung.get(i).getText().isEmpty()
									&& !arraytextsoluong.get(i).getText().isEmpty()
									&& Integer.parseInt(arraytextsoluong.get(i).getText()) > 0
									&& !arraycombodonvi.get(i).getText().isEmpty()) {
								insertbuy = insertbuy
										+ "INSERT INTO [dbo].[LK_MuaLinhKien] ([MaMuaThietBi],[SoBPM],[NoiDung],[SoLuong],[SoThe],[HoTen],[DonVi],[NgayLamPhieu],[GhiChu],[NguoiCapNhat]) VALUES ('LK"
										+ idincrease + "','" + textSoBPM.getText() + "',N'"
										+ arraytextnoidung.get(i).getText() + "'," + arraytextsoluong.get(i).getText()
										+ ",'" + arraytextsothe.get(i).getText() + "',N'"
										+ arraytexthoten.get(i).getText() + "',N'" + arraycombodonvi.get(i).getText()
										+ "','" + ConvertDate.saveDateToSQL(dateTimeNgayLamPhieu) + "',N'"
										+ arraytextghichu.get(i).getText() + "','" + userlogin + "')" + "\n";
								idincrease++;
								vitridaluu.add(i);
							}
						}

						int resultbuy = connect.execUpdateQuery(insertbuy);
						if (resultbuy > 0) {
							isaddsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Thêm thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Add suscessful!");
							}
							thongbao.open();
							textSoBPM.setText("");

							// kiểm tra để xóa đi những dòng tron bảng đã được lưu, chừa lại những dòng
							// không được lưu
							for (int k = 0; k < vitridaluu.size(); k++) {
								arraytextnoidung.get(vitridaluu.get(k)).setText("");
								arraytextsoluong.get(vitridaluu.get(k)).setText("");
								arraytextsothe.get(vitridaluu.get(k)).setText("");
								arraytexthoten.get(vitridaluu.get(k)).setText("");
								arraycombodonvi.get(vitridaluu.get(k)).setText("");
								arraytextghichu.get(vitridaluu.get(k)).setText("");
							}
							vitridaluu.clear();
						}
						connect.closeStatement();
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Số phiếu BPM rỗng!");
						} else {
							thongbao.setText("Error");
							thongbao.setMessage("BPM number is empty!");
						}
						thongbao.open();
					}
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

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

	// load dữ liệu cho table
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
				arraytextghichu.get(i).dispose();
			}
		} catch (Exception ex) {
		}
		arraytextnoidung = new ArrayList<>();
		arraytextsoluong = new ArrayList<>();
		arraytextsothe = new ArrayList<>();
		arraytexthoten = new ArrayList<>();
		arraycombodonvi = new ArrayList<>();
		arraytextghichu = new ArrayList<>();

		try {
			int stt = 1;
			for (int i = 0; i < 30; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", "", "", "" });
				stt++;

				// column nội dung
				tableEditor = new TableEditor(table);
				Text textnoidung = new Text(table, SWT.NONE);
				textnoidung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textnoidung.setTextLimit(80);
				arraytextnoidung.add(textnoidung);
				textnoidung.setText("");
				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = textnoidung.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(textnoidung, item, 1);

				// column số lượng
				tableEditor = new TableEditor(table);
				Text text = new Text(table, SWT.NONE | SWT.RIGHT);
				text.setText("");
				text.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				text.setTextLimit(4);
				// text số lượng chỉ cho nhập số
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
				tableEditor.setEditor(text, item, 2);

				// column Số thẻ
				tableEditor = new TableEditor(table);
				Text textsothe = new Text(table, SWT.NONE | SWT.RIGHT);
				textsothe.setText("");
				textsothe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textsothe.setTextLimit(6);
				// text số thẻ chỉ cho nhập số
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
				tableEditor.setEditor(textsothe, item, 3);

				// column họ tên
				tableEditor = new TableEditor(table);
				Text texthoten = new Text(table, SWT.NONE | SWT.LEFT);
				texthoten.setText("");
				texthoten.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				texthoten.setTextLimit(50);
				arraytexthoten.add(texthoten);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(texthoten, item, 4);

				// column đơn vị
				tableEditor = new TableEditor(table);
				CCombo combodepartment = new CCombo(table, SWT.NONE);
				combodepartment.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				combodepartment.setBackground(SWTResourceManager.getColor(255, 255, 255));
				combodepartment.setTextLimit(50);
				for (int k = 0; k < arraydonvi.size(); k++) {
					combodepartment.add(arraydonvi.get(k));
				}
				combodepartment.setEditable(false);
				arraycombodonvi.add(combodepartment);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(combodepartment, item, 5);

				// Sự kiện thay đổi dữ liệu số thẻ, điền dữ liệu họ tên, đơn vị dựa vào số thẻ
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

							// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
							result = ps.executeQuery();

							while (result.next()) {
								texthoten.setText(result.getString(1));
								combodepartment.setText(result.getString(2));
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

				// column ghi chú
				tableEditor = new TableEditor(table);
				Text textghichu = new Text(table, SWT.NONE | SWT.LEFT);
				textghichu.setText("");
				textghichu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textghichu.setTextLimit(50);
				arraytextghichu.add(textghichu);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(textghichu, item, 6);
			}

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
