package itmanagerip;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
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
import image.ResizeImage;
import sort.SortData;

public class PersonInfo {
	private String db_url = "jdbc:sqlserver://192.168.30.4;databaseName=HRIS;user=ipuser;password=ipuser@202010";
	private Connection conn = null;
	private Statement statement = null;
	private Table table;
	private String idsaveimage = "";

	protected Shell shell;
	private Composite compositeshell;
	private Text textsothe;
	private Text texthoten;
	private CCombo cbGender;
	private Text textBirthdayplace;
	private Text texthome;
	private CCombo cbDonvi;
	private CCombo cbstatus;
	private CLabel lbimage;
	private CLabel lbhotendetail;
	private CLabel lbdonvidetail;
	private CLabel lbdienthoaidetail;
	private CLabel lbgioitinhdetail;
	private Button radiodanglam;
	private Button radiodanghi;
	private int ngonngu = 0;

	public static void main(String[] args) {
		try {
			PersonInfo window = new PersonInfo();
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
		createContents(compositeshell, shell, 0);
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
		shell.setImage(SWTResourceManager.getImage(PersonInfo.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1648, 772);
		shell.setMaximized(true);
		shell.setText("Thông tin nhân viên");

		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fillLayout);

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
		textsothe.setBounds(100, 13, 93, 30);
		textsothe.setTextLimit(12);
		textsothe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textsothe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbhoten = new CLabel(composite, SWT.RIGHT);
		lbhoten.setBounds(199, 13, 106, 30);
		lbhoten.setText("Họ tên");
		lbhoten.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		texthoten = new Text(composite, SWT.BORDER);
		texthoten.setBounds(311, 13, 279, 30);
		texthoten.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		texthoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbGender = new CLabel(composite, SWT.RIGHT);
		lbGender.setText("Giới tính");
		lbGender.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGender.setBounds(10, 63, 78, 30);

		cbGender = new CCombo(composite, SWT.BORDER);
		cbGender.setItems(new String[] { "Nam", "Nữ" });
		cbGender.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		cbGender.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbGender.setBounds(100, 63, 93, 30);

		CLabel lbdonvi = new CLabel(composite, SWT.RIGHT);
		lbdonvi.setBounds(199, 63, 106, 30);
		lbdonvi.setText("Đơn vị");
		lbdonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		cbDonvi = new CCombo(composite, SWT.BORDER);
		cbDonvi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbDonvi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		cbDonvi.setBounds(311, 63, 279, 29);

		CLabel lbBirthdayplace = new CLabel(composite, SWT.RIGHT);
		lbBirthdayplace.setText("Nơi sinh");
		lbBirthdayplace.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbBirthdayplace.setBounds(10, 113, 114, 30);

		textBirthdayplace = new Text(composite, SWT.BORDER);
		textBirthdayplace.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textBirthdayplace.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textBirthdayplace.setBounds(130, 113, 157, 30);

		CLabel lbHome = new CLabel(composite, SWT.RIGHT);
		lbHome.setText("Địa chỉ nhà");
		lbHome.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHome.setBounds(293, 113, 125, 30);

		texthome = new Text(composite, SWT.BORDER);
		texthome.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		texthome.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		texthome.setBounds(424, 113, 166, 30);

		CLabel lbstattus = new CLabel(composite, SWT.RIGHT);
		lbstattus.setText("Trạng thái");
		lbstattus.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbstattus.setBounds(10, 163, 114, 30);

		cbstatus = new CCombo(composite, SWT.BORDER);
		cbstatus.setItems(new String[] { "Đang làm", "Đã nghỉ" });
		cbstatus.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		cbstatus.setBackground(SWTResourceManager.getColor(224, 255, 255));
		cbstatus.setBounds(130, 163, 157, 29);
		cbstatus.select(0);

		Button btnExcel = new Button(composite, SWT.NONE);
		btnExcel.setText("Excel");
		btnExcel.setImage(SWTResourceManager.getImage(PersonInfo.class, "/itmanagerip/Icon/button/excel25.png"));
		btnExcel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnExcel.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btnExcel.setBounds(311, 163, 124, 32);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(PersonInfo.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(441, 163, 149, 32);

		CLabel lbline = new CLabel(composite, SWT.RIGHT);
		lbline.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lbline.setText("");
		lbline.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbline.setBounds(620, 13, 6, 199);

		lbimage = new CLabel(composite, SWT.RIGHT);
		lbimage.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lbimage.setText("");
		lbimage.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbimage.setBounds(643, 13, 150, 200);

		CLabel lbDetailhoten = new CLabel(composite, SWT.RIGHT);
		lbDetailhoten.setText("Họ tên:");
		lbDetailhoten.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		lbDetailhoten.setBounds(818, 13, 137, 30);

		lbhotendetail = new CLabel(composite, SWT.NONE);
		lbhotendetail.setText("");
		lbhotendetail.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbhotendetail.setBounds(970, 13, 374, 30);

		CLabel lbDetaildonvi = new CLabel(composite, SWT.RIGHT);
		lbDetaildonvi.setText("Đơn vị:");
		lbDetaildonvi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		lbDetaildonvi.setBounds(818, 56, 137, 30);

		lbdonvidetail = new CLabel(composite, SWT.NONE);
		lbdonvidetail.setText("");
		lbdonvidetail.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdonvidetail.setBounds(970, 56, 374, 30);

		CLabel lbDetaildienthoai = new CLabel(composite, SWT.RIGHT);
		lbDetaildienthoai.setText("Điện thoại:");
		lbDetaildienthoai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		lbDetaildienthoai.setBounds(818, 99, 137, 30);

		lbdienthoaidetail = new CLabel(composite, SWT.NONE);
		lbdienthoaidetail.setText("");
		lbdienthoaidetail.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbdienthoaidetail.setBounds(970, 99, 374, 30);

		CLabel lbDetailgioitinh = new CLabel(composite, SWT.RIGHT);
		lbDetailgioitinh.setText("Giới tính:");
		lbDetailgioitinh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		lbDetailgioitinh.setBounds(818, 142, 137, 30);

		lbgioitinhdetail = new CLabel(composite, SWT.NONE);
		lbgioitinhdetail.setText("");
		lbgioitinhdetail.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbgioitinhdetail.setBounds(970, 142, 374, 30);

		CLabel lbDetailtrangthai = new CLabel(composite, SWT.RIGHT);
		lbDetailtrangthai.setText("Trạng Thái:");
		lbDetailtrangthai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		lbDetailtrangthai.setBounds(818, 185, 137, 30);

		radiodanglam = new Button(composite, SWT.RADIO);
		radiodanglam.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		radiodanglam.setBounds(974, 185, 130, 30);
		radiodanglam.setText("Đang làm");

		radiodanghi = new Button(composite, SWT.RADIO);
		radiodanghi.setText("Đã nghỉ");
		radiodanghi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		radiodanghi.setBounds(1128, 185, 130, 30);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setHeaderBackground(SWTResourceManager.getColor(0, 250, 154));
		table.setLocation(10, 239);
		table.setSize(1346, 462);
		table.setLayoutData(new RowData(1867, 860));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 310);

		TableColumn tbcNumber = new TableColumn(table, SWT.NONE);
		tbcNumber.setWidth(84);
		tbcNumber.setText("STT");

		TableColumn tbcID = new TableColumn(table, SWT.NONE);
		tbcID.setWidth(86);
		tbcID.setText("Số thẻ");

		TableColumn tbcName = new TableColumn(table, SWT.NONE);
		tbcName.setWidth(194);
		tbcName.setText("Họ tên");

		TableColumn tbcDepartment = new TableColumn(table, SWT.NONE);
		tbcDepartment.setWidth(155);
		tbcDepartment.setText("Đơn vị");

		TableColumn tbcGender = new TableColumn(table, SWT.NONE);
		tbcGender.setWidth(104);
		tbcGender.setText("Giới tính");

		TableColumn tbcBirthday = new TableColumn(table, SWT.NONE);
		tbcBirthday.setWidth(139);
		tbcBirthday.setText("Ngày sinh");

		TableColumn tbcNumberphone = new TableColumn(table, SWT.NONE);
		tbcNumberphone.setWidth(139);
		tbcNumberphone.setText("Điện thoại");

		TableColumn tbcBirthdayplace = new TableColumn(table, SWT.NONE);
		tbcBirthdayplace.setWidth(171);
		tbcBirthdayplace.setText("Nơi sinh");

		TableColumn tbcAddresshome = new TableColumn(table, SWT.NONE);
		tbcAddresshome.setWidth(153);
		tbcAddresshome.setText("Địa chỉ nhà");

		TableColumn tbcNowaddress = new TableColumn(table, SWT.NONE);
		tbcNowaddress.setWidth(153);
		tbcNowaddress.setText("Nơi ở hiện tại");

		TableColumn tbcMagneticcardID = new TableColumn(table, SWT.NONE);
		tbcMagneticcardID.setWidth(139);
		tbcMagneticcardID.setText("Mã thẻ");

		TableColumn tbcDatecomein = new TableColumn(table, SWT.NONE);
		tbcDatecomein.setWidth(139);
		tbcDatecomein.setText("Ngày vào công ty");

		TableColumn tbcNgayNghiViec = new TableColumn(table, SWT.NONE);
		tbcNgayNghiViec.setWidth(139);
		tbcNgayNghiViec.setText("Ngày nghỉ việc");

		TableColumn tbcCmnd = new TableColumn(table, SWT.NONE);
		tbcCmnd.setWidth(123);
		tbcCmnd.setText("CMND");

		TableColumn tbcStatus = new TableColumn(table, SWT.NONE);
		tbcStatus.setWidth(116);
		tbcStatus.setText("Trạng thái");

		radiodanglam.setEnabled(false);
		radiodanghi.setEnabled(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbsothe.setText("Số thẻ");
			lbhoten.setText("Họ tên");
			lbGender.setText("Giới tính");
			lbdonvi.setText("Đơn vị");
			lbBirthdayplace.setText("Nơi sinh");
			lbHome.setText("Địa chỉ nhà");
			lbstattus.setText("Trạng thái");
			btnExcel.setText("Excel");
			btntimkiem.setText("Tìm kiếm");
			lbDetailhoten.setText("Họ tên:");
			lbDetaildonvi.setText("Đơn vị:");
			lbDetaildienthoai.setText("Điện thoại:");
			lbDetailgioitinh.setText("Giới tính:");
			lbDetailtrangthai.setText("Trạng Thái:");
			radiodanglam.setText("Đang làm");
			radiodanghi.setText("Đã nghỉ");
			tbcNumber.setText("STT");
			tbcID.setText("Số thẻ");
			tbcName.setText("Họ tên");
			tbcDepartment.setText("Đơn vị");
			tbcGender.setText("Giới tính");
			tbcBirthday.setText("Ngày sinh");
			tbcNumberphone.setText("Điện thoại");
			tbcBirthdayplace.setText("Nơi sinh");
			tbcAddresshome.setText("Địa chỉ nhà");
			tbcNowaddress.setText("Nơi ở hiện tại");
			tbcMagneticcardID.setText("Mã thẻ");
			tbcDatecomein.setText("Ngày vào công ty");
			tbcNgayNghiViec.setText("Ngày nghỉ việc");
			tbcCmnd.setText("CMND");
			tbcStatus.setText("Trạng thái");

		} else {
			lbsothe.setText("ID");
			lbhoten.setText("Name");
			lbGender.setText("Gender");
			lbdonvi.setText("Department");
			lbBirthdayplace.setText("Birthday place");
			lbHome.setText("Address home");
			lbstattus.setText("Status");
			btnExcel.setText("Excel");
			btntimkiem.setText("Search");
			lbDetailhoten.setText("Name:");
			lbDetaildonvi.setText("Department:");
			lbDetaildienthoai.setText("Number phone:");
			lbDetailgioitinh.setText("Gender:");
			lbDetailtrangthai.setText("Status:");
			radiodanglam.setText("Working");
			radiodanghi.setText("Work end");
			tbcNumber.setText("Number");
			tbcID.setText("ID");
			tbcName.setText("Name");
			tbcDepartment.setText("Department");
			tbcGender.setText("Gender");
			tbcBirthday.setText("Birthday");
			tbcNumberphone.setText("Number phone");
			tbcBirthdayplace.setText("Birthday place");
			tbcAddresshome.setText("Address home");
			tbcNowaddress.setText("Address live");
			tbcMagneticcardID.setText("Magneticcard ID");
			tbcDatecomein.setText("Date come in");
			tbcNgayNghiViec.setText("Date work end");
			tbcCmnd.setText("ID card");
			tbcStatus.setText("Status");
		}

		// Lấy dữ liệu cho Combo đơn vị
		// --------------------------------------------------------------------------------------------------------------------
		try {
			conn = DriverManager.getConnection(db_url);
			statement = conn.createStatement();

			String select = "SELECT Department_Name FROM Data_Department WHERE Department_Name NOT LIKE N'Z%' ORDER BY Department_Name ASC";
			ResultSet result = statement.executeQuery(select);
			while (result.next()) {
				cbDonvi.add(result.getString(1));
			}
			result.close();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi");
				thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
			} else {
				thongbao.setText("Notice error");
				thongbao.setMessage("Connect error!\n" + se.toString());
			}
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se2) {
				// nothing we can do
			}
		}

		// Sự kiện thay đổi sự chọn lựa của bảng thì sẽ lấy thông tin lên các label
		// ----------------------------------------------------------------------------------------------------------
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] item = table.getSelection();
					if (item.length > 0) {
						// có chọn ít nhất 1 dòng trong bảng
						lbhotendetail.setText(item[0].getText(2));
						lbdonvidetail.setText(item[0].getText(3));
						lbdienthoaidetail.setText(item[0].getText(6));
						lbgioitinhdetail.setText(item[0].getText(4));
						if (item[0].getText(14).equals("Đang làm")) {
							radiodanglam.setSelection(true);
							radiodanghi.setSelection(false);
						} else if (item[0].getText(14).equals("Đã nghỉ")) {
							radiodanglam.setSelection(false);
							radiodanghi.setSelection(true);
						} else {
							radiodanglam.setSelection(false);
							radiodanghi.setSelection(false);
						}

						String select = "SELECT Person_Image FROM Data_Person WHERE Person_ID='" + item[0].getText(1)
								+ "'";
						try {
							conn = DriverManager.getConnection(db_url);
							statement = conn.createStatement();
							ResultSet result = statement.executeQuery(select);
							boolean hasimage = false; // có hình ảnh lưu trên server hay không
							while (result.next()) {
								try {
									Image image = new Image(null, new ImageData(result.getBinaryStream(1)));
									if (image.getBounds().height < (float) image.getBounds().width) {
										lbimage.setBackground(ResizeImage.resize(image, 150, 200));
									} else if (image.getBounds().height / (float) image.getBounds().width >= (float) 4
											/ 3) {
										lbimage.setBackground(ResizeImage.resize(image, 150,
												image.getBounds().height * 150 / image.getBounds().width));
									} else if (image.getBounds().height
											/ (float) image.getBounds().width >= (float) 1.12) {
										lbimage.setBackground(ResizeImage.resize(image,
												image.getBounds().width * 200 / image.getBounds().height, 200));
									} else {
										lbimage.setBackground(ResizeImage.resize(image, 150, 200));
									}
									hasimage = true; // có hình trên server
								} catch (Exception ex) {
									idsaveimage = "";
								}
								idsaveimage = item[0].getText(1);
								break;
							}

							// kiểm tra nếu không có hình trên hệ thống thì cho label hình ảnh rỗng
							if (!hasimage) {
								lbimage.setBackground(SWTResourceManager.getColor(240, 240, 240));
							}

							result.close();
							statement.close();
							conn.close();
						} catch (Exception exp) {
							idsaveimage = "";
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo lỗi");
								thongbao.setMessage("Lỗi kết nối!\n" + exp.toString());
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage("Connect error!\n" + exp.toString());
							}
						}
					} else {
						// chọn ngoài dòng chứa dữ liệu
						lbhotendetail.setText("");
						lbdonvidetail.setText("");
						lbdienthoaidetail.setText("");
						lbgioitinhdetail.setText("");
						radiodanglam.setSelection(false);
						radiodanghi.setSelection(false);
						lbimage.setBackground(SWTResourceManager.getColor(240, 240, 240));
					}
				} catch (Exception ex) {

				}
			}
		});

		// Sự kiện double click vào hình ảnh sẽ lưu hình
		lbimage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					conn = DriverManager.getConnection(db_url);
					statement = conn.createStatement();
					String select = "SELECT Person_Image FROM Data_Person WHERE Person_ID='" + idsaveimage + "'";

					String[] FILTER_NAMES = { "PDF (*.jpg)" };
					// đuôi file có thể mở
					String[] FILTER_EXTS = { "*.jpg" };

					FileDialog dlg = new FileDialog(shell, SWT.SAVE);
					dlg.setFilterNames(FILTER_NAMES);
					dlg.setFilterExtensions(FILTER_EXTS);
					dlg.setFileName(idsaveimage);
					String filename = dlg.open(); // ten file luu
					if (filename != null) {
						ResultSet result = statement.executeQuery(select);
						while (result.next()) {
							FileOutputStream outputStream = new FileOutputStream(filename);
							byte[] strToBytes = result.getBytes(1);
							outputStream.write(strToBytes);
							outputStream.close();
						}
						result.close();
						statement.close();
						conn.close();
						MessageBox thongbao = new MessageBox(shell, SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Lưu hình thành công!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("Save image successful!");
						}
						thongbao.open();
					}

				} catch (Exception ex) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					thongbao.setText("Error");
					thongbao.setMessage(ex.toString());
					thongbao.open();
				}
			}
		});

		// Tìm kiếm
		// --------------------------------------------------------------------------------------------------------------------
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// Tìm kiếm sau khi enter ở text số thẻ
		// ******************************************************************************
		textsothe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở text họ tên
		// ******************************************************************************
		texthoten.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		cbGender.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		cbDonvi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		textBirthdayplace.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		texthome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		cbstatus.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Tìm kiếm sau khi enter ở button Tìm kiếm
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Xuất Excel sau khi enter ở button Excel
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// nhấn phím Enter thì tìm kiếm luôn
				if (e.character == SWT.CR) {
					if (ngonngu == 0) {
						Excel.exportExcelTable(table, shell, ngonngu, "Danh sách nhân viên");
					} else {
						Excel.exportExcelTable(table, shell, ngonngu, "List Person HR");
					}
				}
			}
		});

		// Excel
		// ********************************************************************************************************************************************************************
		btnExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ngonngu == 0) {
					Excel.exportExcelTable(table, shell, ngonngu, "Danh sách nhân viên");
				} else {
					Excel.exportExcelTable(table, shell, ngonngu, "List Person HR");
				}
			}

		});

		// Sort data
		// *********************************************************************************************************************************************************************
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcID.addListener(SWT.Selection, sort.sortListenerCode);
		tbcName.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDepartment.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGender.addListener(SWT.Selection, sort.sortListenerCode);
		tbcBirthday.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNumberphone.addListener(SWT.Selection, sort.sortListenerCode);
		tbcBirthdayplace.addListener(SWT.Selection, sort.sortListenerCode);
		tbcAddresshome.addListener(SWT.Selection, sort.sortListenerCode);
		tbcNowaddress.addListener(SWT.Selection, sort.sortListenerCode);
		tbcMagneticcardID.addListener(SWT.Selection, sort.sortListenerCode);
		tbcDatecomein.addListener(SWT.Selection, sort.sortListenerDate);
		tbcNgayNghiViec.addListener(SWT.Selection, sort.sortListenerDate);
		tbcCmnd.addListener(SWT.Selection, sort.sortListenerCode);
		tbcStatus.addListener(SWT.Selection, sort.sortListenerCode);
	}

	// Hiện trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu) {
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Thông tin nhân viên");
		} else {
			itemtab.setText("Person info");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);
		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 350);
	}

	// ------------------------------------------------------------------------------------------------
	public void search(Shell shell) {
		try {
			conn = DriverManager.getConnection(db_url);
			statement = conn.createStatement();
			String select = "";
			ResultSet result;
			table.removeAll();

			// xóa các dữ liệu cho các label hiển thị thông tin
			lbhotendetail.setText("");
			lbdonvidetail.setText("");
			lbdienthoaidetail.setText("");
			lbgioitinhdetail.setText("");
			radiodanglam.setSelection(false);
			radiodanghi.setSelection(false);
			lbimage.setBackground(SWTResourceManager.getColor(240, 240, 240));

			// text find
			String sothe = textsothe.getText().isEmpty() ? ""
					: " AND Data_Person.Person_ID LIKE '%" + textsothe.getText() + "%'";
			String hoten = texthoten.getText().isEmpty() ? ""
					: " AND Data_Person.Person_Name LIKE N'%" + texthoten.getText() + "%'";
			String gender = "";
			if (cbGender.getText().equals("Nam")) {
				gender = "1";
			} else if (cbGender.getText().equals("Nữ")) {
				gender = "0";
			} else {
				gender = "";
			}
			String gioitinh = gender.isEmpty() ? "" : " AND Data_Person.Gender='" + gender + "'";
			String donvi = cbDonvi.getText().isEmpty() ? ""
					: " AND Data_Department.Department_Name=N'" + cbDonvi.getText() + "'";

			String noisinh = textBirthdayplace.getText().isEmpty() ? ""
					: " AND Data_Person_Detail.Birth_Place LIKE N'%" + textBirthdayplace.getText() + "%'";
			String diachinha = texthome.getText().isEmpty() ? ""
					: " AND Data_Person.Home_Address LIKE N'%" + texthome.getText() + "%'";
			String status = cbstatus.getText().equals("Đang làm") ? "1" : "0";
			String trangthai = cbstatus.getText().isEmpty() ? "" : " AND Data_Person.Person_Status='" + status + "'";

			select = "SELECT Data_Person.Person_ID,Data_Person.Person_Name,Data_Department.Department_Name,Data_Person.Gender,Data_Person.Birthday,Data_Person_Detail.Mobilephone_Number,Data_Person_Detail.Birth_Place,Data_Person.Home_Address,Data_Person_Detail.Address_Live,Data_Person.Magneticcard_ID,Data_Person.Date_Come_In,Data_Person.Date_Work_End,Data_Person.ID,Data_Person.Person_Status FROM Data_Person,Data_Department,Data_Person_Detail WHERE Data_Person.Department_Serial_Key=Data_Department.Department_Serial_Key AND Data_Person.Person_ID=Data_Person_Detail.Person_ID"
					+ sothe + hoten + gioitinh + donvi + noisinh + diachinha + trangthai
					+ " ORDER BY Data_Person.Person_ID ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = statement.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				String gioitinhgender = result.getString(4);
				gioitinhgender = gioitinhgender.equals("1") ? "Nam" : gioitinhgender.equals("0") ? "Nữ" : "";

				String trangthaistatus = result.getString(14);
				trangthaistatus = trangthaistatus.equals("1") ? "Đang làm"
						: trangthaistatus.equals("0") ? "Đã nghỉ" : "";

				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						gioitinhgender, ConvertDate.convertDate(result.getString(5)), result.getString(6),
						result.getString(7), result.getString(8), result.getString(9), result.getString(10),
						ConvertDate.convertDate(result.getString(11)), ConvertDate.convertDate(result.getString(12)),
						result.getString(13), trangthaistatus });
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
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception se2) {
			}
		}
	}
}
