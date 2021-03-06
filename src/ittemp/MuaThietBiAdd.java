package ittemp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import date.ConvertDate;
import sql.ConnectSQL;

public class MuaThietBiAdd {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoBPM;
	private int ngonngu = 1;
	private DateTime dateTimeNgayMua;
	private Text textTenFile;
	private Text textGhiChu;
	private Table table;
	private String filename = "";
	private String userlogin = "21608";
	private boolean isaddsuccess = false;

	private TableEditor tableEditor;

	private ArrayList<CCombo> combodevices;

	private ArrayList<Text> textscount;

	private ArrayList<Text> textswarranty;

	private ArrayList<ArrayList<String>> datadevicearray;

	public static void main(String[] args) {
		try {
			MuaThietBiAdd window = new MuaThietBiAdd();
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
		shell.setImage(SWTResourceManager.getImage(MuaThietBiAdd.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1099, 719);
		shell.setText("Add equipment purchases");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setBounds(10, 20, 114, 30);
		lbSoBPM.setText("S??? BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setBounds(130, 20, 202, 30);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setTextLimit(13);

		CLabel lbNgayMua = new CLabel(composite, SWT.RIGHT);
		lbNgayMua.setText("Ng??y mua");
		lbNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayMua.setBounds(338, 20, 114, 30);

		dateTimeNgayMua = new DateTime(composite, SWT.BORDER);
		dateTimeNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayMua.setBounds(458, 20, 131, 30);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(MuaThietBiAdd.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(660, 109, 131, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(MuaThietBiAdd.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(797, 109, 131, 32);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		CLabel lbTenFile = new CLabel(composite, SWT.RIGHT);
		lbTenFile.setText("T??n t???p");
		lbTenFile.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTenFile.setBounds(10, 110, 114, 30);

		textTenFile = new Text(composite, SWT.BORDER);
		textTenFile.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenFile.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenFile.setBounds(130, 110, 459, 30);

		textGhiChu = new Text(composite, SWT.BORDER);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(130, 65, 459, 30);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi ch??");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(10, 65, 114, 30);

		Button btnTaiFileLen = new Button(composite, SWT.NONE);
		btnTaiFileLen.setVisible(true);
		btnTaiFileLen
				.setImage(SWTResourceManager.getImage(MuaThietBiAdd.class, "/itmanagerip/Icon/button/upload30.png"));
		btnTaiFileLen.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTaiFileLen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTaiFileLen.setBounds(595, 109, 59, 32);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(210, 180, 140));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(10, 155, 1062, 512);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(80);
		tbcSTT.setText("STT");

		TableColumn tbcThietBi = new TableColumn(table, SWT.NONE);
		tbcThietBi.setWidth(276);
		tbcThietBi.setText("Thi???t b???");

		TableColumn tbcSoLuong = new TableColumn(table, SWT.NONE);
		tbcSoLuong.setWidth(120);
		tbcSoLuong.setText("S??? l?????ng");

		TableColumn tbcThoiGianBaoHanh = new TableColumn(table, SWT.NONE);
		tbcThoiGianBaoHanh.setWidth(193);
		tbcThoiGianBaoHanh.setText("Th???i gian b???o h??nh");

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Th??m mua thi???t b???");
			lbSoBPM.setText("S??? BPM");
			lbNgayMua.setText("Ng??y mua");
			lbTenFile.setText("T??n t???p");
			lbGhiChu.setText("Ghi ch??");

			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
			tbcSTT.setText("STT");
			tbcThietBi.setText("Thi???t b???");
			tbcSoLuong.setText("S??? l?????ng");
			tbcThoiGianBaoHanh.setText("Th???i gian b???o h??nh");
		} else {
			// Tieng Anh
			shell.setText("Add equipment purchases");
			lbSoBPM.setText("BPM number");
			lbNgayMua.setText("Buy date");
			lbTenFile.setText("File name");
			lbGhiChu.setText("Note");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Number");
			tbcThietBi.setText("Device");
			tbcSoLuong.setText("Count");
			tbcThoiGianBaoHanh.setText("Warranty period");
		}

		datadevicearray = new ArrayList<>();
		// l???y d??? li???u cho combo thi???t b???
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [MaThietBi],[TenThietBi] FROM [dbo].[IT_ThietBi] ORDER BY [TenThietBi] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				ArrayList<String> array = new ArrayList<>();
				array.add(result.getString(1));
				array.add(result.getString(2));
				datadevicearray.add(array);
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

		// upload file
		btnTaiFileLen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] FILTER_NAMES = { "All Files (*.*)" };
				// ??u??i file c?? th??? m???
				String[] FILTER_EXTS = { "*.*" };

				FileDialog dlg = new FileDialog(shell, SWT.OPEN);
				dlg.setFilterNames(FILTER_NAMES);
				dlg.setFilterExtensions(FILTER_EXTS);
				filename = dlg.open();
				if (filename != null) {
					Path path = Paths.get(filename);
					Path tenfile = path.getFileName();

					textTenFile.setText(tenfile.toString());
				}
			}
		});

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
						String insertbuy = "";
						String id = "";
						int idincrease = 0;
						String select = "SELECT TOP 1 (SELECT SUBSTRING([ID],2,8)) FROM [dbo].[IT_MuaThietBi] ORDER BY [ID] DESC";
						ResultSet resultmacamera = connect.getStatement().executeQuery(select);
						while (resultmacamera.next()) {
							id = resultmacamera.getString(1);
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
							if (!combodevices.get(i).getText().isEmpty() && !textscount.get(i).getText().isEmpty()
									&& !textswarranty.get(i).getText().isEmpty()) {
								insertbuy = insertbuy
										+ "INSERT INTO [dbo].[IT_MuaThietBi] ([ID],[SoPhieuBPM],[MaThietBi],[SoLuong],[NgayMua],[ThoiGianBaoHanh],[GhiChu],[UserUpdate]) VALUES ('M"
										+ idincrease + "','" + textSoBPM.getText() + "','"
										+ datadevicearray.get(combodevices.get(i).getSelectionIndex()).get(0) + "',"
										+ textscount.get(i).getText() + ",'"
										+ ConvertDate.saveDateToSQL(dateTimeNgayMua) + "',"
										+ textswarranty.get(i).getText() + ",N'" + textGhiChu.getText() + "','"
										+ userlogin + "')" + "\n";
								idincrease++;
							}
						}

						int resultbuy = connect.execUpdateQuery(insertbuy);
						if (resultbuy > 0) {
							isaddsuccess = true;

							if (!textTenFile.getText().isEmpty()) {
								// ?????c h???t m???t l???n
								byte[] content = Files.readAllBytes(Paths.get(filename));

								String insert = "INSERT INTO [dbo].[IT_FileDinhKem] ([SoPhieuBPM],[TenFile],[FileDinhKem]) VALUES ('"
										+ textSoBPM.getText() + "',N'" + textTenFile.getText() + "',?)";

								PreparedStatement stmt = connect.getConnection().prepareStatement(insert);
								stmt.setBytes(1, content);
								stmt.executeUpdate();
								stmt.close();
							}

							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Th??ng b??o");
								thongbao.setMessage("Th??m th??nh c??ng!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Add suscess!");
							}
							thongbao.open();
							textSoBPM.setText("");
							textGhiChu.setText("");
							textTenFile.setText("");
							loadDataTable();
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
			int sizetable = combodevices.size();
			for (int i = 0; i < sizetable; i++) {
				combodevices.get(i).dispose();
				textscount.get(i).dispose();
				textswarranty.get(i).dispose();
			}
		} catch (Exception ex) {
		}
		combodevices = new ArrayList<>();
		textscount = new ArrayList<>();
		textswarranty = new ArrayList<>();

		try {
			int stt = 1;
			for (int i = 0; i < 20; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", "", "", "" });
				stt++;

				// ??i???n d??? li???u cho combobox
				tableEditor = new TableEditor(table);
				CCombo combo = new CCombo(table, SWT.BORDER);
				combo.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				combodevices.add(combo);

				combo.setText("");
				for (int j = 0; j < datadevicearray.size(); j++) {
					combo.add(datadevicearray.get(j).get(1));
				}

				tableEditor.grabHorizontal = true;
				tableEditor.minimumWidth = combo.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(combo, item, 1);

				// column count
				tableEditor = new TableEditor(table);
				Text text = new Text(table, SWT.NONE | SWT.RIGHT);
				text.setText("");
				text.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				text.setTextLimit(9);
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
				textscount.add(text);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(text, item, 2);

				// column warranty period
				tableEditor = new TableEditor(table);
				Text textwarranty = new Text(table, SWT.NONE | SWT.RIGHT);
				textwarranty.setText("");
				textwarranty.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textwarranty.setTextLimit(4);
				textwarranty.setText("0");
				// textwarranty.set
				// text th???i gian b???o h??nh ch??? cho nh???p s???
				textwarranty.addVerifyListener(new VerifyListener() {
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
				textswarranty.add(textwarranty);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(textwarranty, item, 3);
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

	// ki???m tra xem c?? m?????n m??y ???nh hay kh??ng
	public boolean isAddSuccess() {
		return isaddsuccess;
	}
}
