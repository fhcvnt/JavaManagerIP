package itmanagerip;

import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
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

import excel.Excel;
import sort.SortData;
import sql.ConnectSQL;

public class Wifi {
	private ConnectSQL connect;
	private String userlogin = "admin"; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật
	private Table table;
	private TableItem[] itemtable;
	private String[] sothexoa;

	protected Shell shell;
	private Composite compositeshell;
	private Text textID;
	private Text textManufacturer;
	private Text textDepartment;
	private Text textIP;
	private Text textFloor;
	private int ngonngu = 1;
	private Text textIPlan;
	private Text textBuilding;

	private String grouplogin = ""; // nếu là User, Translate thì không cho chỉnh sửa

	public static void main(String[] args) {
		try {
			Wifi window = new Wifi();
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
		createContentsShell();
		createContents(compositeshell, shell, ngonngu);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContentsShell() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Wifi");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		CLabel lbID = new CLabel(composite, SWT.RIGHT);
		lbID.setBounds(10, 13, 78, 30);
		lbID.setText("ID");
		lbID.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textID = new Text(composite, SWT.BORDER);
		textID.setBounds(100, 13, 78, 30);
		textID.setTextLimit(12);
		textID.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textID.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbManufacturer = new CLabel(composite, SWT.RIGHT);
		lbManufacturer.setBounds(184, 13, 144, 30);
		lbManufacturer.setText("Manufacturer");
		lbManufacturer.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textManufacturer = new Text(composite, SWT.BORDER);
		textManufacturer.setBounds(333, 13, 125, 30);
		textManufacturer.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textManufacturer.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbDepartment = new CLabel(composite, SWT.RIGHT);
		lbDepartment.setBounds(464, 13, 113, 30);
		lbDepartment.setText("Department");
		lbDepartment.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textDepartment = new Text(composite, SWT.BORDER);
		textDepartment.setBounds(588, 13, 214, 30);
		textDepartment.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDepartment.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/button/add30.png"));
		btnAdd.setBounds(808, 11, 106, 32);
		btnAdd.setText("Add");
		btnAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnAdd.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/button/edit.png"));
		btnEdit.setBounds(920, 11, 92, 32);
		btnEdit.setText("Edit");
		btnEdit.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnEdit.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/button/delete.png"));
		btnDelete.setBounds(1018, 11, 113, 32);
		btnDelete.setText("Delete");
		btnDelete.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnDelete.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnSave = new Button(composite, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/button/save 30.png"));
		btnSave.setBounds(1137, 11, 94, 32);
		btnSave.setText("Save");
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/button/cancel.png"));
		btnCancel.setBounds(1237, 11, 103, 32);
		btnCancel.setText("Cancel");
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		CLabel lbBuilding = new CLabel(composite, SWT.RIGHT);
		lbBuilding.setText("Bulding");
		lbBuilding.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbBuilding.setBounds(10, 51, 113, 30);

		textBuilding = new Text(composite, SWT.BORDER);
		textBuilding.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textBuilding.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textBuilding.setBounds(129, 51, 156, 30);

		CLabel lbFloor = new CLabel(composite, SWT.RIGHT);
		lbFloor.setText("Floor");
		lbFloor.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbFloor.setBounds(291, 51, 113, 30);

		textFloor = new Text(composite, SWT.BORDER);
		textFloor.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textFloor.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textFloor.setBounds(410, 51, 70, 30);
		textFloor.setTextLimit(2);

		CLabel lbIPwan = new CLabel(composite, SWT.RIGHT);
		lbIPwan.setText("IP WAN");
		lbIPwan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbIPwan.setBounds(486, 51, 92, 30);

		textIP = new Text(composite, SWT.BORDER);
		textIP.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textIP.setBounds(584, 51, 134, 30);

		CLabel lbIPlan = new CLabel(composite, SWT.RIGHT);
		lbIPlan.setText("IP LAN");
		lbIPlan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbIPlan.setBounds(727, 51, 106, 30);

		textIPlan = new Text(composite, SWT.BORDER);
		textIPlan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textIPlan.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textIPlan.setBounds(839, 51, 147, 30);

		Button btnSearch = new Button(composite, SWT.NONE);
		btnSearch.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/button/search-30.png"));
		btnSearch.setText("Search");
		btnSearch.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btnSearch.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btnSearch.setBounds(992, 51, 137, 32);

		Button btnExcel = new Button(composite, SWT.NONE);
		btnExcel.setText("Excel");
		btnExcel.setImage(SWTResourceManager.getImage(Wifi.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnExcel.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExcel.setBounds(1135, 51, 104, 32);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 89);
		table.setSize(1883, 883);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 176);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcNumber = new TableColumn(table, SWT.NONE);
		tbcNumber.setWidth(78);
		tbcNumber.setText("Number");

		TableColumn tbcID = new TableColumn(table, SWT.NONE);
		tbcID.setWidth(73);
		tbcID.setText("ID");

		TableColumn tbcName = new TableColumn(table, SWT.NONE);
		tbcName.setWidth(111);
		tbcName.setText("Wifi Name");

		TableColumn tbcDepartment = new TableColumn(table, SWT.NONE);
		tbcDepartment.setWidth(125);
		tbcDepartment.setText("Department");

		TableColumn tbcManufacturer = new TableColumn(table, SWT.NONE);
		tbcManufacturer.setWidth(130);
		tbcManufacturer.setText("Manufacturer");

		TableColumn tbcBuilding = new TableColumn(table, SWT.NONE);
		tbcBuilding.setWidth(88);
		tbcBuilding.setText("Building");

		TableColumn tbcFloor = new TableColumn(table, SWT.NONE);
		tbcFloor.setWidth(67);
		tbcFloor.setText("Floor");

		TableColumn tbcWifiName24GHz = new TableColumn(table, SWT.NONE);
		tbcWifiName24GHz.setWidth(123);
		tbcWifiName24GHz.setText("SSID 2.4GHz");

		TableColumn tbcPassword24 = new TableColumn(table, SWT.NONE);
		tbcPassword24.setWidth(112);
		tbcPassword24.setText("Password");

		TableColumn tbcWifiName5GHz = new TableColumn(table, SWT.NONE);
		tbcWifiName5GHz.setWidth(105);
		tbcWifiName5GHz.setText("SSID 5GHz");

		TableColumn tbcPassword5 = new TableColumn(table, SWT.NONE);
		tbcPassword5.setWidth(115);
		tbcPassword5.setText("Password");

		TableColumn tbcIPwan = new TableColumn(table, SWT.NONE);
		tbcIPwan.setWidth(131);
		tbcIPwan.setText("IP WAN");

		TableColumn tbcIPlan = new TableColumn(table, SWT.NONE);
		tbcIPlan.setWidth(115);
		tbcIPlan.setText("IP LAN");

		TableColumn tbcLink = new TableColumn(table, SWT.NONE);
		tbcLink.setWidth(115);
		tbcLink.setText("Link");

		TableColumn tbcUsername = new TableColumn(table, SWT.NONE);
		tbcUsername.setWidth(115);
		tbcUsername.setText("Username");

		TableColumn tbcPassword = new TableColumn(table, SWT.NONE);
		tbcPassword.setWidth(115);
		tbcPassword.setText("Password");

		TableColumn tbcDateupdate = new TableColumn(table, SWT.NONE);
		tbcDateupdate.setWidth(115);
		tbcDateupdate.setText("Date update");

		TableColumn tbcPersonupdate = new TableColumn(table, SWT.NONE);
		tbcPersonupdate.setWidth(115);
		tbcPersonupdate.setText("Person update");

		btnSave.setVisible(false);
		btnCancel.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbID.setText("Mã Wifi");
			lbManufacturer.setText("Nhà Sản Xuất");
			lbDepartment.setText("Đơn vị");
			btnAdd.setText("Thêm");
			btnEdit.setText("Sửa");
			btnDelete.setText("Xóa");
			btnSave.setText("Lưu");
			btnCancel.setText("Hủy");
			lbBuilding.setText("Tòa Nhà");
			lbIPwan.setText("IP WAN");
			lbFloor.setText("Tầng");
			lbIPlan.setText("IP LAN");
			btnSearch.setText("Tìm kiếm");
			tbcNumber.setText("STT");
			tbcID.setText("Mã Wifi");
			tbcName.setText("Tên Wifi");
			tbcManufacturer.setText("Nhà Sản Xuất");
			tbcDepartment.setText("Đơn vị");
			tbcBuilding.setText("Tòa Nhà");
			tbcFloor.setText("Tầng");
			tbcWifiName24GHz.setText("Tên Wifi 2.4GHz");
			tbcPassword24.setText("Mật khẩu");
			tbcWifiName5GHz.setText("Tên Wifi 5GHz");
			tbcPassword5.setText("Mật khẩu");
			tbcIPwan.setText("IP WAN");
			tbcIPlan.setText("IP LAN");
			tbcLink.setText("Link");
			tbcUsername.setText("Tên đăng nhập");
			tbcPassword.setText("Mật khẩu");
			tbcDateupdate.setText("Ngày cập nhật");
			tbcPersonupdate.setText("Người cập nhật");
		} else {
			// Tieng Anh
			lbID.setText("ID");
			lbManufacturer.setText("Manufacturer");
			lbDepartment.setText("Department");
			btnAdd.setText("Add");
			btnEdit.setText("Edit");
			btnDelete.setText("Delete");
			btnSave.setText("Save");
			btnCancel.setText("Cancel");
			lbIPwan.setText("IP WAN");
			lbBuilding.setText("Building");
			lbFloor.setText("Floor");
			lbIPlan.setText("IP LAN");
			btnSearch.setText("Search");
			tbcNumber.setText("Number");
			tbcID.setText("ID");
			tbcName.setText("Wifi Name");
			tbcManufacturer.setText("Manufacturer");
			tbcDepartment.setText("Department");
			tbcBuilding.setText("Building");
			tbcFloor.setText("Floor");
			tbcWifiName24GHz.setText("SSID 2.4GHz");
			tbcPassword24.setText("Password");
			tbcWifiName5GHz.setText("SSID 5GHz");
			tbcPassword5.setText("Password");
			tbcIPwan.setText("IP WAN");
			tbcIPlan.setText("IP LAN");
			tbcLink.setText("Link");
			tbcUsername.setText("Username");
			tbcPassword.setText("Password");
			tbcDateupdate.setText("Date update");
			tbcPersonupdate.setText("Person update");
		}

		// nếu là User, Translate thì không cho chỉnh sửa, xuất Excel
		if (grouplogin.equals("User") || grouplogin.equals("Translate")) {
			btnAdd.setVisible(false);
			btnEdit.setVisible(false);
			btnDelete.setVisible(false);
			btnExcel.setVisible(false);
		}

		// tầng chỉ cho nhập số
		textFloor.addVerifyListener(new VerifyListener() {
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

		// Event phím tắt (ctrl + A)
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt (ctrl + A) chọn hết bảng
				if (e.keyCode == 'a' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					table.selectAll();
				}
			}
		});

		// Tìm kiếm
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// Xóa-------------------------------------------------------------------------------------------------------------------------------
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {

					// Lấy cột số thẻ Code
					itemtable = table.getSelection();
					sothexoa = new String[itemtable.length];

					for (int i = 0; i < sothexoa.length; i++) {
						sothexoa[i] = itemtable[i].getText(1);
					}
					if (sothexoa.length > 0) {
						btnAdd.setEnabled(false);
						btnEdit.setEnabled(false);
						btnExcel.setEnabled(false);
						btnSearch.setEnabled(false);
						btnSave.setVisible(true);
						btnCancel.setVisible(true);
						table.setEnabled(false);
					}

				} catch (Exception ex) {
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(true);
					btnExcel.setEnabled(true);
					btnSearch.setEnabled(true);
					btnSave.setVisible(false);
					btnCancel.setVisible(false);
					table.setEnabled(true);
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Không có dòng nào được chọn!");
					} else {
						thongbao.setText("Notice");
						thongbao.setMessage("No line is selected!");
					}
					thongbao.open();
				}
			}
		});

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnExcel.setEnabled(true);
				btnSearch.setEnabled(true);
				btnSave.setVisible(false);
				btnCancel.setVisible(false);
				table.setEnabled(true);
			}
		});

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					connect = new ConnectSQL();
					connect.setConnection();

					try {
						String delete = "";
						for (int i = 0; i < sothexoa.length; i++) {
							delete = delete + "\n" + "DELETE Wifi WHERE Code='" + sothexoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						connect.closeStatement();
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnExcel.setEnabled(true);
						btnSearch.setEnabled(true);
						btnSave.setVisible(false);
						btnCancel.setVisible(false);
						table.setEnabled(true);
					} catch (Exception ex) {
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

		// Sửa-----------------------------------------------------------------------------------------------------------------------------
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String sothesua = "";
					sothesua = itemsua[0].getText(1);
					if (!sothesua.isEmpty()) {
						EditWifi editwifi = new EditWifi();
						// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
						composite.setEnabled(false);
						editwifi.createContents();
						editwifi.setDataDefault(itemsua[0].getText(1), itemsua[0].getText(5), itemsua[0].getText(11));
						editwifi.setLanguage(ngonngu, userlogin);
						editwifi.open();
						composite.setEnabled(true);

						// Cập nhật lại dữ liệu
						search(shell);
					}

				} catch (Exception ex) {
				}
			}
		});

		// Thêm--------------------------------------------------------------------------------------------------------------------------
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewWifi newwifi = new NewWifi();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				newwifi.setLanguage(ngonngu, userlogin);
				newwifi.open();
				composite.setEnabled(true);
				// Cập nhật lại dữ liệu sau khi thêm, gọi lại tìm kiếm
				search(shell);
			}
		});

		// Excel
		// **************************************************************************************************************************
		btnExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Excel.exportExcelTable(table, shell, ngonngu, "Wifi");
			}

		});

		// Sort data
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcID.addListener(SWT.Selection, sort.sortListenerCode);
		tbcName.addListener(SWT.Selection, sort.sortListenerCode);
		tbcManufacturer.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDepartment.addListener(SWT.Selection, sort.sortListenerCode);
		tbcBuilding.addListener(SWT.Selection, sort.sortListenerCode);
		tbcFloor.addListener(SWT.Selection, sort.sortListenerCode);
		tbcWifiName24GHz.addListener(SWT.Selection, sort.sortListenerCode);
		tbcPassword24.addListener(SWT.Selection, sort.sortListenerCode);
		tbcWifiName5GHz.addListener(SWT.Selection, sort.sortListenerCode);
		tbcPassword5.addListener(SWT.Selection, sort.sortListenerCode);
		tbcIPwan.addListener(SWT.Selection, sort.sortListenerIP);
		tbcIPlan.addListener(SWT.Selection, sort.sortListenerIP);
		tbcLink.addListener(SWT.Selection, sort.sortListenerCode);
		tbcUsername.addListener(SWT.Selection, sort.sortListenerCode);
		tbcPassword.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDateupdate.addListener(SWT.Selection, sort.sortListenerDate);
		tbcPersonupdate.addListener(SWT.Selection, sort.sortListenerCode);

	}

	// Hiện trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String groupname, String userlogin, int ngonngu) {
		this.userlogin = userlogin;
		this.ngonngu = ngonngu;
		grouplogin = groupname;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		itemtab.setText("Wifi");

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);
		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 200);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public void search(Shell shell) {

		try {
			connect = new ConnectSQL();
			connect.setConnection();
			connect.setStatement();
			String select = "";
			ResultSet result;
			table.removeAll();

			// text find
			String floor = textFloor.getText().isEmpty() ? "" : " AND Wifi.Floors = '" + textFloor.getText() + "'";

			// xử lý tìm theo lớp IP, nếu textIP nhận vào là lớp IP ví dụ: 30 thay vì nhận
			// được là 30.1 thì ta tìm theo lớp IP chứ không tìm theo IP
			String ipwan = textIP.getText().isEmpty() ? "" : " AND #DSIP.IP LIKE '%" + textIP.getText() + "'";
			int lopipwan = -1;
			try {
				lopipwan = Integer.parseInt(textIP.getText());
			} catch (NumberFormatException ne) {

			}
			if (lopipwan >= 0) {
				ipwan = textIP.getText().isEmpty() ? "" : " AND #DSIP.IP LIKE '%" + "%.%." + lopipwan + "." + "%'";
			}

			String iplan = textIPlan.getText().isEmpty() ? "" : " AND Wifi.IPLAN LIKE '%" + textIPlan.getText() + "'";
			int lopiplan = -1;
			try {
				lopiplan = Integer.parseInt(textIPlan.getText());
			} catch (NumberFormatException ne) {

			}
			if (lopiplan >= 0) {
				iplan = textIPlan.getText().isEmpty() ? "" : " AND Wifi.IPLAN LIKE '%" + "%.%." + lopiplan + "." + "%'";
			}
			String building = textBuilding.getText().isEmpty() ? ""
					: " AND (#DSIP.Building LIKE N'%" + textBuilding.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](#DSIP.Building) LIKE '%" + textBuilding.getText()
							+ "%')";
			String department = textDepartment.getText().isEmpty() ? ""
					: " AND (#DSIP.DonVi LIKE N'%" + textDepartment.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII](#DSIP.DonVi) LIKE '%" + textDepartment.getText()
							+ "%')";

			// sử dụng bảng tạm và left join
			select = "SELECT DanhSachIP.SoThe,DanhSachIP.HoTen,DonVi.DonVi,DanhSachIP.IP,Building.Building INTO #DSIP FROM DanhSachIP LEFT JOIN DonVi ON DanhSachIP.MaDonVi=DonVi.MaDonVi LEFT JOIN Building ON DanhSachIP.MaBuilding=Building.MaBuilding";
			select = select + "\n"
					+ "SELECT Wifi.Code,#DSIP.HoTen,#DSIP.DonVi,Wifi.Manufacturer,#DSIP.Building,Wifi.Floors,Wifi.WifiName24GHz,Wifi.Pass24GHz,Wifi.WifiName5GHz,Wifi.Pass5GHz,#DSIP.IP,Wifi.IPLAN,Wifi.LINK,Wifi.Username,Wifi.Pass,Wifi.NgayCapNhat,NguoiDung.TenNguoiDung FROM Wifi LEFT JOIN #DSIP ON Wifi.Code=#DSIP.SoThe LEFT JOIN NguoiDung ON Wifi.UserUpdate=NguoiDung.MaNguoiDung WHERE Wifi.Code LIKE '%"
					+ textID.getText() + "%' AND Wifi.Manufacturer LIKE '%" + textManufacturer.getText() + "%'" + floor
					+ ipwan + iplan + building + department;

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = connect.getStatement().executeQuery(select);
			int stt = 1;
			while (result.next()) {
				// định dạng lại ngày cập nhật thành dạng dd/MM/yyyy
				String ngaycapnhat = result.getString(16);
				try {
					ngaycapnhat = ngaycapnhat.substring(8, 10) + "/" + ngaycapnhat.substring(5, 7) + "/"
							+ ngaycapnhat.substring(0, 4);
				} catch (IndexOutOfBoundsException ie) {
					ngaycapnhat = "";
				}

				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						result.getString(8), result.getString(9), result.getString(10), result.getString(11),
						result.getString(12), result.getString(13), result.getString(14), result.getString(15),
						ngaycapnhat, result.getString(17) });
				stt++;
			}
			result.close();
			connect.closeStatement();
			connect.closeConnection();
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
