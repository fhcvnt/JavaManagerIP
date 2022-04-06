package itmanagerip;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
import excel.Excel;
import sort.SortData;
import sql.ConnectSQL;

public class PDNMail {
	private ConnectSQL connect;
	private Table table;
	private TableItem[] itemtable;
	private String[] sothexoa;
	private String userlogin = ""; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	protected Shell shell;
	private Composite compositeshell;
	private Text textsothe;
	private Text texthoten;
	private Text textdonvi;
	private Text textmail;
	private int ngonngu = 1;
	private Text textpasword;
	private CCombo cbpermission;

	private TableColumn tbcNumber;
	private TableColumn tbcID;
	private TableColumn tbcPersonName;
	private TableColumn tbcDeparment;
	private TableColumn tbcMail;
	private TableColumn tbcMailName;
	private TableColumn tbcPassword;
	private TableColumn tbcPermission;
	private TableColumn tbcNote;
	private TableColumn tbcDateCreated;
	private TableColumn tbcDateUpdate;
	private TableColumn tbcPersonUpdate;

	// Danh sách các cột trong Excel, mỗi cột sẽ đưa vào một danh sách (11 colunm)
	private ArrayList<String> excelID, excelName, excelDonVi, excelMail, excelMailname, excelPassword, excelPermission,
			excelNote, excelDatecreate, excelDateupdate, excelPersonupdate;

	public static void main(String[] args) {
		try {
			PDNMail window = new PDNMail();
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
		shell.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Mail");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		CLabel lbsothe = new CLabel(composite, SWT.RIGHT);
		lbsothe.setBounds(10, 13, 78, 30);
		lbsothe.setText("Số thẻ");
		lbsothe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textsothe = new Text(composite, SWT.BORDER);
		textsothe.setBounds(100, 13, 78, 30);
		textsothe.setTextLimit(12);
		textsothe.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textsothe.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbhoten = new CLabel(composite, SWT.RIGHT);
		lbhoten.setBounds(184, 13, 78, 30);
		lbhoten.setText("Họ tên");
		lbhoten.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		texthoten = new Text(composite, SWT.BORDER);
		texthoten.setBounds(268, 13, 190, 30);
		texthoten.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		texthoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbdonvi = new CLabel(composite, SWT.RIGHT);
		lbdonvi.setBounds(464, 13, 113, 30);
		lbdonvi.setText("Đơn vị");
		lbdonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textdonvi = new Text(composite, SWT.BORDER);
		textdonvi.setBounds(588, 13, 214, 30);
		textdonvi.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textdonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(808, 11, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(920, 11, 92, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(1018, 11, 113, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1137, 11, 94, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1237, 11, 103, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		CLabel llpassword = new CLabel(composite, SWT.RIGHT);
		llpassword.setText("Mật khẩu");
		llpassword.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		llpassword.setBounds(10, 49, 106, 30);

		textpasword = new Text(composite, SWT.BORDER);
		textpasword.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textpasword.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textpasword.setBounds(130, 49, 167, 30);

		CLabel lbpermission = new CLabel(composite, SWT.RIGHT);
		lbpermission.setText("Quyền");
		lbpermission.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbpermission.setBounds(303, 49, 130, 30);

		cbpermission = new CCombo(composite, SWT.BORDER);
		cbpermission.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		cbpermission.setItems(new String[] { "Mail nội bộ", "Mail ngoài" });
		cbpermission.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbpermission.setBounds(439, 49, 167, 30);

		CLabel lbmail = new CLabel(composite, SWT.RIGHT);
		lbmail.setText("Mail");
		lbmail.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbmail.setBounds(612, 49, 106, 30);

		textmail = new Text(composite, SWT.BORDER);
		textmail.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textmail.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textmail.setBounds(724, 49, 190, 30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(920, 49, 149, 32);

		Button btnExport = new Button(composite, SWT.NONE);
		btnExport.setText("Export");
		btnExport.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExport.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnExport.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnExport.setBounds(1075, 49, 106, 32);

		Button btnImport = new Button(composite, SWT.NONE);
		btnImport.setText("Import");
		btnImport.setImage(SWTResourceManager.getImage(PDNMail.class, "/itmanagerip/Icon/menu/import.png"));
		btnImport.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnImport.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnImport.setBounds(1187, 49, 106, 32);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 87);
		table.setSize(1883, 883);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 152);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(154, 205, 50));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		tbcNumber = new TableColumn(table, SWT.NONE);
		tbcNumber.setWidth(83);
		tbcNumber.setText("STT");

		tbcID = new TableColumn(table, SWT.NONE);
		tbcID.setWidth(102);
		tbcID.setText("Số thẻ");

		tbcPersonName = new TableColumn(table, SWT.NONE);
		tbcPersonName.setWidth(196);
		tbcPersonName.setText("Họ tên");

		tbcDeparment = new TableColumn(table, SWT.NONE);
		tbcDeparment.setWidth(146);
		tbcDeparment.setText("Đơn vị");

		tbcMail = new TableColumn(table, SWT.NONE);
		tbcMail.setWidth(172);
		tbcMail.setText("Mail");

		tbcMailName = new TableColumn(table, SWT.NONE);
		tbcMailName.setWidth(172);
		tbcMailName.setText("Tên mail");

		tbcPassword = new TableColumn(table, SWT.NONE);
		tbcPassword.setWidth(172);
		tbcPassword.setText("Mật khẩu");

		tbcPermission = new TableColumn(table, SWT.NONE);
		tbcPermission.setWidth(124);
		tbcPermission.setText("Quyền");

		tbcNote = new TableColumn(table, SWT.NONE);
		tbcNote.setWidth(147);
		tbcNote.setText("Ghi chú");

		tbcDateCreated = new TableColumn(table, SWT.NONE);
		tbcDateCreated.setWidth(148);
		tbcDateCreated.setText("Ngày tạo");

		tbcDateUpdate = new TableColumn(table, SWT.NONE);
		tbcDateUpdate.setWidth(115);
		tbcDateUpdate.setText("Ngày cập nhật");

		tbcPersonUpdate = new TableColumn(table, SWT.NONE);
		tbcPersonUpdate.setWidth(149);
		tbcPersonUpdate.setText("Người cập nhật");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbsothe.setText("Số thẻ");
			lbhoten.setText("Họ tên");
			lbdonvi.setText("Đơn vị");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			llpassword.setText("Mật khẩu");
			lbpermission.setText("Quyền");
			lbmail.setText("Mail");
			btntimkiem.setText("Tìm kiếm");
			btnExport.setText("Xuất");
			tbcNumber.setText("STT");
			tbcID.setText("Số thẻ");
			tbcPersonName.setText("Họ tên");
			tbcDeparment.setText("Đơn vị");
			tbcMail.setText("Mail");
			tbcMailName.setText("Tên mail");
			tbcPassword.setText("Mật khẩu");
			tbcPermission.setText("Quyền");
			tbcNote.setText("Ghi chú");
			tbcDateCreated.setText("Ngày tạo");
			tbcDateUpdate.setText("Ngày cập nhật");
			tbcPersonUpdate.setText("Người cập nhật");

		} else {
			lbsothe.setText("ID");
			lbhoten.setText("Name");
			lbdonvi.setText("Deparment");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			llpassword.setText("Password");
			lbpermission.setText("Permission");
			lbmail.setText("Mail");
			btntimkiem.setText("Search");
			btnExport.setText("Export");
			tbcNumber.setText("Number");
			tbcID.setText("ID");
			tbcPersonName.setText("Name");
			tbcDeparment.setText("Deparment");
			tbcMail.setText("Full mail");
			tbcMailName.setText("Mail name");
			tbcPassword.setText("Password");
			tbcPermission.setText("Permission");
			tbcNote.setText("Note");
			tbcDateCreated.setText("Date created");
			tbcDateUpdate.setText("Date update");
			tbcPersonUpdate.setText("Person update");
		}

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
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// goi ham tim kiem
				search();
			}
		});

		// Xóa-------------------------------------------------------------------------------------------------------------------------------
		btnxoa.addSelectionListener(new SelectionAdapter() {
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
						btnthem.setEnabled(false);
						btnsua.setEnabled(false);
						btnExport.setEnabled(false);
						btntimkiem.setEnabled(false);
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						table.setEnabled(false);
					}

				} catch (Exception ex) {
					btnthem.setEnabled(true);
					btnsua.setEnabled(true);
					btnExport.setEnabled(true);
					btntimkiem.setEnabled(true);
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
					table.setEnabled(true);
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnthem.setEnabled(true);
				btnsua.setEnabled(true);
				btnExport.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					connect = new ConnectSQL();
					connect.setConnection();

					try {
						String delete = "";
						for (int i = 0; i < sothexoa.length; i++) {
							delete = delete + "\n" + "DELETE Mail WHERE ID='" + sothexoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						btnthem.setEnabled(true);
						btnsua.setEnabled(true);
						btnExport.setEnabled(true);
						btntimkiem.setEnabled(true);
						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
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
				} finally {
					try {
						if (connect.getStatement() != null) {
							connect.closeStatement();
						}
						if (connect.getConnection() != null) {
							connect.closeConnection();
						}
					} catch (SQLException se2) {
						// nothing we can do
					}
				}
			}
		});

		// Sửa-----------------------------------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String sothesua = "";
					sothesua = itemsua[0].getText(1);
					if (!sothesua.isEmpty()) {
						EditMail editmail = new EditMail();
						// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
						composite.setEnabled(false);
						editmail.setDataMailDefault(itemsua[0].getText(1), itemsua[0].getText(2), itemsua[0].getText(3),
								itemsua[0].getText(4), itemsua[0].getText(5), itemsua[0].getText(6),
								itemsua[0].getText(7), itemsua[0].getText(8), itemsua[0].getText(9));
						editmail.setLanguage(ngonngu, userlogin);
						editmail.open();
						composite.setEnabled(true);

						if (editmail.isEditSuccess()) {
							search();
						}
					}

				} catch (Exception ex) {
				}
			}
		});

		// Thêm--------------------------------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewMail newmail = new NewMail();
				// mở cửa sổ mới thì cửa sổ củ không cho thao tác
				composite.setEnabled(false);
				newmail.setLanguage(ngonngu, userlogin);
				newmail.open();
				composite.setEnabled(true);

				if (newmail.isAddSuccess()) {
					search();
				}
			}
		});

		// Export
		// ********************************************************************************************************************************************************************
		btnExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Danh sách người dùng sử dụng mail");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "List user use mail");
				}
			}
		});

		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				importExcel(shell);
			}
		});

		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcID.addListener(SWT.Selection, sort.sortListenerCode);
		tbcPersonName.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDeparment.addListener(SWT.Selection, sort.sortListenerCode);
		tbcMail.addListener(SWT.Selection, sort.sortListenerCode);
		tbcMailName.addListener(SWT.Selection, sort.sortListenerCode);
		tbcPassword.addListener(SWT.Selection, sort.sortListenerCode);
		tbcPermission.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNote.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDateCreated.addListener(SWT.Selection, sort.sortListenerDate);
		tbcDateUpdate.addListener(SWT.Selection, sort.sortListenerDate);
		tbcPersonUpdate.addListener(SWT.Selection, sort.sortListenerCode);
	}

	// Hiện trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, String userlogin, int ngonngu) {
		this.userlogin=userlogin;
		this.ngonngu=ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		itemtab.setText("Mail");

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);

		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 200);
	}

	// ============================================================
	public void search() {

		try {
			connect = new ConnectSQL();
			connect.setConnection();
			connect.setStatement();
			String select = "";
			ResultSet result;
			table.removeAll();

			// text find
			String sothe = textsothe.getText().isEmpty() ? "" : " AND [Mail].[ID] LIKE '%" + textsothe.getText() + "%'";
			String hoten = texthoten.getText().isEmpty() ? ""
					: " AND ([Data_Person].[Person_Name] LIKE N'%" + texthoten.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII]([Data_Person].[Person_Name]) LIKE '%"
							+ texthoten.getText() + "%')";
			String donvi = textdonvi.getText().isEmpty() ? ""
					: " AND ([Data_Department].[Department_Name] LIKE N'%" + textdonvi.getText()
							+ "%' OR [dbo].[convertUnicodetoASCII]([Data_Department].[Department_Name]) LIKE '%"
							+ textdonvi.getText() + "%')";

			String matkhau = textpasword.getText().isEmpty() ? ""
					: " AND [Mail].[Passwords] LIKE '%" + textpasword.getText() + "%'";

			String quyen = cbpermission.getText().isEmpty() ? ""
					: " AND [Mail].[Permission] LIKE N'%" + cbpermission.getText() + "%'";

			String mail = textmail.getText().isEmpty() ? ""
					: " AND [Mail].[FullMail] LIKE '%" + textmail.getText() + "%'";

			// sử dụng bảng tạm và left join
			select = "SELECT [Mail].[ID],[Data_Person].[Person_Name],[Data_Department].[Department_Name] ,[Mail].[FullMail] ,[Mail].[MailName] ,[Mail].[Passwords] ,[Mail].[Permission] ,[Mail].[Note],[Mail].[DateCreated] ,[Mail].[DateUpdate],[NguoiDung].[TenNguoiDung] FROM [dbo].[Mail] LEFT JOIN [SV4].[HRIS].[dbo].[Data_Person] ON [Mail].[ID]=[Data_Person].[Person_ID] COLLATE Chinese_PRC_CI_AS AND [Data_Person].[Person_Status]=1 LEFT JOIN [SV4].[HRIS].[dbo].[Data_Department] ON [Data_Person].[Department_Serial_Key]=[Data_Department].[Department_Serial_Key] LEFT JOIN [dbo].[NguoiDung] ON [Mail].[UserUpdate] =[NguoiDung].[MaNguoiDung] OR [Mail].[UserUpdate] =[NguoiDung].[TenDangNhap] WHERE 1=1"
					+ sothe + hoten + donvi + matkhau + quyen + mail
					+ " ORDER BY [Data_Department].[Department_Name] DESC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = connect.getStatement().executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						result.getString(8), result.getString(9), ConvertDate.convertDate(result.getString(10)),
						result.getString(11) });
				stt++;
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
		} finally {
			try {
				if (connect.getStatement() != null) {
					connect.closeStatement();
				}
				if (connect.getConnection() != null) {
					connect.closeConnection();
				}
			} catch (SQLException se2) {
				// nothing we can do
			}
		}
	}

	// Đọc file excel ghi vào mảng
	// ====================================================================================================================
	private void readXLSFile(String filename) {
		try {
			InputStream ExcelFileToRead = new FileInputStream(filename);
			HSSFWorkbook worbook = new HSSFWorkbook(ExcelFileToRead);

			// khai bao
			excelID = new ArrayList<String>();
			excelName = new ArrayList<String>();
			excelDonVi = new ArrayList<String>();
			excelMail = new ArrayList<String>();
			excelMailname = new ArrayList<String>();
			excelPassword = new ArrayList<String>();
			excelPermission = new ArrayList<String>();
			excelNote = new ArrayList<String>();
			excelDatecreate = new ArrayList<String>();
			excelDateupdate = new ArrayList<String>();
			excelPersonupdate = new ArrayList<String>();

			HSSFSheet sheet = worbook.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {
					continue;
				}
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					cell = (HSSFCell) cells.next();

					try {
						if (cell.getColumnIndex() == 2) {
							excelID.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 3) {
							excelName.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 4) {
							excelDonVi.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 5) {
							excelMail.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 6) {
							excelMailname.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 7) {
							excelPassword.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 8) {
							excelPermission.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 9) {
							excelNote.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 10) {
							excelDatecreate.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 11) {
							excelDateupdate.add(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 12) {
							excelPersonupdate.add(cell.getStringCellValue());
						}
					} catch (Exception exc) {

					}
				}
			}
			worbook.close();
		} catch (Exception eee) {

		}
	}

	// Import
	// ===================================================================
	public void importExcel(Shell shell) {
		String filename = null;
		try {
			String[] FILTER_NAMES = { "Excel (*.xls)", "Excel (*.xlsx)" };
			// đuôi file có thể mở
			String[] FILTER_EXTS = { "*.xls", "*.xlsx" };

			FileDialog dlg = new FileDialog(shell, SWT.OPEN);
			dlg.setFilterNames(FILTER_NAMES);
			dlg.setFilterExtensions(FILTER_EXTS);
			filename = dlg.open();

			if (dlg.getFileName().substring(dlg.getFileName().length() - 3, dlg.getFileName().length()).equals("xls")) {
				// xls
				// open file excel save to ArrayList
				readXLSFile(filename);
//				ArrayList<Integer> cotkhongdung=new ArrayList<>();
//				cotkhongdung.add(1);
//				ImportExcel.readXLSFile(filename, 12, cotkhongdung);
			}
//			} else {
//				readXLSXFile(filename);
//			}
			connect = new ConnectSQL();
			connect.setConnection();

			for (int i = 0; i < excelID.size(); i++) {
				try {
					connect.setStatement();

					String insert = "INSERT INTO Mail ( ID ,FullMail ,MailName ,Passwords,Permission,Note,DateCreated,UserUpdate ) VALUES  ( '"
							+ excelID.get(i).toString() + "' ,'" + excelMail.get(i).toString() + "' ,N'"
							+ excelMailname.get(i).toString() + "' ,'" + excelPassword.get(i).toString() + "',N'"
							+ excelPermission.get(i).toString() + "',N'" + excelNote.get(i).toString() + "','"
							+ excelDatecreate.get(i).toString() + "','" + excelPersonupdate.get(i).toString() + "' )";
					connect.execUpdateQuery(insert);

				} catch (Exception se) {
					try {
						// insert that bai
						connect.setStatement();
						String insert = "INSERT INTO Mail ( ID ,FullMail ,MailName ,Passwords,Permission,Note,DateCreated ) VALUES  ( '"
								+ excelID.get(i).toString() + "' ,'" + excelMail.get(i).toString() + "' ,N'"
								+ excelMailname.get(i).toString() + "' ,'" + excelPassword.get(i).toString() + "',N'"
								+ excelPermission.get(i).toString() + "',N'" + excelNote.get(i).toString() + "','"
								+ excelDatecreate.get(i).toString() + "' )";
						connect.execUpdateQuery(insert);
					} catch (Exception exx) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Nhập không thành công!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("Import failed!");
						}
					}
				}
			}

			MessageBox thongbao = new MessageBox(shell, SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo");
				thongbao.setMessage("Nhập thành công!");
			} else {
				thongbao.setText("Notice");
				thongbao.setMessage("Import successful!");
			}

			thongbao.open();

		} catch (Exception exce) {
			if (filename != null) {
				MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				if (ngonngu == 0) {
					thongbao.setText("Thông báo");
					thongbao.setMessage("Nhập không thành công!");
				} else {
					thongbao.setText("Notice");
					thongbao.setMessage("Import failed!");
				}
				thongbao.open();
			}
		} finally {
			try {
				if (connect.getStatement() != null) {
					connect.closeStatement();
				}
				if (connect.getConnection() != null) {
					connect.closeConnection();
				}
			} catch (Exception se2) {
			}
		}
	}
}
