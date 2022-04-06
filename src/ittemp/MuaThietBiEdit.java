package ittemp;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
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

public class MuaThietBiEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoBPM;
	private int ngonngu = 1;
	private DateTime dateTimeNgayMua;
	private Text textTenFile;
	private Table table;
	private String filename = "";
	private String userlogin = "21608";
	private boolean iseditsuccess = false;
	private String sobpm = "";

	private TableEditor tableEditor;
	private ArrayList<Text> textswarranty;
	private ArrayList<Text> textsnote;
	private ArrayList<Button> buttons;

	public static void main(String[] args) {
		try {
			MuaThietBiEdit window = new MuaThietBiEdit();
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
		shell.setImage(SWTResourceManager.getImage(MuaThietBiEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1139, 677);
		shell.setText("Edit equipment purchases");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setBounds(10, 20, 114, 30);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setEditable(false);
		textSoBPM.setBounds(130, 20, 202, 30);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setTextLimit(13);

		CLabel lbNgayMua = new CLabel(composite, SWT.RIGHT);
		lbNgayMua.setText("Ngày mua");
		lbNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayMua.setBounds(338, 20, 114, 30);

		dateTimeNgayMua = new DateTime(composite, SWT.BORDER);
		dateTimeNgayMua.setEnabled(false);
		dateTimeNgayMua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayMua.setBounds(458, 20, 131, 30);

		CLabel lbTenFile = new CLabel(composite, SWT.RIGHT);
		lbTenFile.setText("Tên tệp");
		lbTenFile.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTenFile.setBounds(10, 64, 114, 30);

		textTenFile = new Text(composite, SWT.BORDER);
		textTenFile.setEditable(false);
		textTenFile.setTextLimit(255);
		textTenFile.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenFile.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenFile.setBounds(130, 65, 459, 30);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(MuaThietBiEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(660, 64, 131, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(MuaThietBiEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(797, 64, 131, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		Button btnTaiFileXuong = new Button(composite, SWT.NONE);
		btnTaiFileXuong.setVisible(true);
		btnTaiFileXuong
				.setImage(SWTResourceManager.getImage(MuaThietBiEdit.class, "/itmanagerip/Icon/button/download30.png"));
		btnTaiFileXuong.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTaiFileXuong.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTaiFileXuong.setBounds(595, 64, 59, 32);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(210, 180, 140));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setBounds(10, 110, 1100, 512);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(80);
		tbcSTT.setText("STT");
		tbcSTT.setResizable(false);

		TableColumn tbcID = new TableColumn(table, SWT.NONE);
		tbcID.setWidth(0);
		tbcID.setText("ID");
		tbcID.setResizable(false);

		TableColumn tbcThietBi = new TableColumn(table, SWT.NONE);
		tbcThietBi.setWidth(276);
		tbcThietBi.setText("Thiết bị");

		TableColumn tbcSoLuong = new TableColumn(table, SWT.NONE);
		tbcSoLuong.setWidth(120);
		tbcSoLuong.setText("Số lượng");

		TableColumn tbcThoiGianBaoHanh = new TableColumn(table, SWT.NONE);
		tbcThoiGianBaoHanh.setWidth(193);
		tbcThoiGianBaoHanh.setText("Thời gian bảo hành");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.LEFT);
		tbcGhiChu.setWidth(193);
		tbcGhiChu.setText("Ghi chú");

		TableColumn tbcNhapKhoIT = new TableColumn(table, SWT.CENTER);
		tbcNhapKhoIT.setWidth(169);
		tbcNhapKhoIT.setText("Nhập kho IT");

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Sửa mua thiết bị");
			lbSoBPM.setText("Số BPM");
			lbNgayMua.setText("Ngày mua");
			lbTenFile.setText("Tên tệp");

			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			tbcSTT.setText("STT");
			tbcThietBi.setText("Thiết bị");
			tbcSoLuong.setText("Số lượng");
			tbcThoiGianBaoHanh.setText("Thời gian bảo hành");
			tbcGhiChu.setText("Ghi chú");
			tbcNhapKhoIT.setText("Nhập kho IT");
		} else {
			// Tieng Anh
			shell.setText("Edit equipment purchases");
			lbSoBPM.setText("BPM number");
			lbNgayMua.setText("Buy date");
			lbTenFile.setText("File name");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

			tbcSTT.setText("Number");
			tbcThietBi.setText("Device");
			tbcSoLuong.setText("Count");
			tbcThoiGianBaoHanh.setText("Warranty period");
			tbcGhiChu.setText("Note");
			tbcNhapKhoIT.setText("Import IT warehouse");
		}
		textSoBPM.setText(sobpm);
		buttons = new ArrayList<>();
		textswarranty = new ArrayList<>();
		textsnote = new ArrayList<>();

		loadDataTable();

		// download file
		btnTaiFileXuong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!textTenFile.getText().isEmpty()) {
					try {
						connect = new ConnectSQL();
						connect.setConnection();
						connect.setStatement();

						String[] FILTER_NAMES = { "All Files (*.*)" };
						// đuôi file có thể mở
						String[] FILTER_EXTS = { "*.*" };

						FileDialog dlg = new FileDialog(shell, SWT.SAVE);
						dlg.setFilterNames(FILTER_NAMES);
						dlg.setFilterExtensions(FILTER_EXTS);
						dlg.setFileName(textTenFile.getText());
						filename = dlg.open();

						String select = "SELECT [FileDinhKem] FROM [dbo].[IT_FileDinhKem] WHERE [SoPhieuBPM]='" + sobpm
								+ "'";
						ResultSet ketqua = connect.getStatement().executeQuery(select);
						boolean savesuccess = false;
						while (ketqua.next()) {
							FileOutputStream outputStream = new FileOutputStream(filename);
							byte[] strToBytes = ketqua.getBytes(1);
							outputStream.write(strToBytes);
							outputStream.close();
							savesuccess = true;
						}
						ketqua.close();
						connect.closeStatement();
						if (savesuccess) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Tệp được lưu thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("File saved successfully!");
							}
							thongbao.open();
						}
					} catch (Exception ex) {
					}
				}
			}
		});

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
						String update = "";

						for (int i = 0; i < items.length; i++) {
							if (buttons.get(i).getSelection() && buttons.get(i).getEnabled()) {
								update = update + "UPDATE [dbo].[IT_MuaThietBi] SET [ThoiGianBaoHanh] = "
										+ textswarranty.get(i).getText()
										+ ",[NgayNhapKhoIT] = GETDATE(),[DaNhapKhoIT] = 1,[GhiChu] = N'"
										+ textsnote.get(i).getText() + "',[UserUpdate] = '" + userlogin
										+ "' WHERE [ID]='" + items[i].getText(1) + "'" + "\n";
							}
						}

						int resultbuy = connect.execUpdateQuery(update);
						if (resultbuy > 0) {
							iseditsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Cập nhật thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Update suscess!");
							}
							thongbao.open();
							loadDataTable();
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
			int size = buttons.size();
			for (int i = 0; i < size; i++) {
				buttons.get(i).dispose();
				textswarranty.get(i).dispose();
				textsnote.get(i).dispose();
			}
			buttons = new ArrayList<>();
			textswarranty = new ArrayList<>();
			textsnote = new ArrayList<>();
		} catch (Exception exc) {
		}

		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [IT_MuaThietBi].[ID],[IT_ThietBi].[TenThietBi],[IT_MuaThietBi].[SoLuong],[IT_MuaThietBi].[NgayMua],[IT_MuaThietBi].[ThoiGianBaoHanh],[IT_MuaThietBi].[GhiChu],[IT_FileDinhKem].[TenFile],[IT_MuaThietBi].[DaNhapKhoIT] FROM [dbo].[IT_MuaThietBi] LEFT JOIN [dbo].[IT_FileDinhKem] ON [IT_MuaThietBi].[SoPhieuBPM]=[IT_FileDinhKem].[SoPhieuBPM] LEFT JOIN [dbo].[IT_ThietBi] ON [IT_MuaThietBi].[MaThietBi]=[IT_ThietBi].[MaThietBi] WHERE [IT_MuaThietBi].[SoPhieuBPM]='"
					+ sobpm + "'";
			ResultSet result = connect.getStatement().executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3), "",
						result.getString(6), "" });
				stt++;
				ConvertDate.setDateTime(dateTimeNgayMua, ConvertDate.convertDate(result.getString(4)));
				String tenfile = result.getString(7) == null ? "" : result.getString(7);
				textTenFile.setText(tenfile);
				// column warranty period
				tableEditor = new TableEditor(table);
				Text textwarranty = new Text(table, SWT.NONE | SWT.RIGHT);
				textwarranty.setText("");
				textwarranty.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textwarranty.setTextLimit(4);
				textwarranty.setText(result.getString(5));
				// textwarranty.set
				// text thời gian bảo hành chỉ cho nhập số
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
				tableEditor.setEditor(textwarranty, item, 4);

				// text ghi chu
				tableEditor = new TableEditor(table);
				Text textnote = new Text(table, SWT.NONE | SWT.LEFT);
				textnote.setText("");
				textnote.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
				textnote.setTextLimit(255);
				textnote.setText(result.getString(6));

				textsnote.add(textnote);
				tableEditor.grabHorizontal = true;
				tableEditor.setEditor(textnote, item, 5);

				// column Nhap kho IT
				// column check
				tableEditor = new TableEditor(table);
				Button button = new Button(table, SWT.CHECK);
				button.pack();
				if (result.getString(8).equals("1")) {
					button.setSelection(true);
					button.setEnabled(false);
					textnote.setEditable(false);
					textnote.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					textwarranty.setEditable(false);
					textwarranty.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				}
				buttons.add(button);
				tableEditor.minimumWidth = button.getSize().x;
				tableEditor.horizontalAlignment = SWT.CENTER;
				tableEditor.setEditor(button, item, 6);
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

	// kiểm tra xem có mượn máy ảnh hay không
	public boolean isEditSuccess() {
		return iseditsuccess;
	}

	// set so BPM
	public void setSoBPM(String bpm) {
		sobpm = bpm;
		textSoBPM.setText(sobpm);
	}
}
