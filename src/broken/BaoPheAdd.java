package broken;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import date.ConvertDate;
import sql.ConnectSQL;

public class BaoPheAdd {
	private ConnectSQL connect;

	protected Shell shell;
	private int ngonngu = 1;
	private DateTime dateTimeNgayBaoPhe;
	private Text textTenFile;
	private Text textGhiChu;
	private String filename = "";
	private String userlogin = "";
	private boolean isaddsuccess = false;

	public static void main(String[] args) {
		try {
			BaoPheAdd window = new BaoPheAdd();
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
		shell.setImage(SWTResourceManager.getImage(BaoPheAdd.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(706, 376);
		shell.setText("Thêm báo phế");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbNgayBaoPhe = new CLabel(composite, SWT.RIGHT);
		lbNgayBaoPhe.setText("Ngày báo phế");
		lbNgayBaoPhe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgayBaoPhe.setBounds(10, 30, 207, 30);

		dateTimeNgayBaoPhe = new DateTime(composite, SWT.BORDER);
		dateTimeNgayBaoPhe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		dateTimeNgayBaoPhe.setBounds(223, 30, 151, 30);

		CLabel lbTenFile = new CLabel(composite, SWT.RIGHT);
		lbTenFile.setText("Tên tệp");
		lbTenFile.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTenFile.setBounds(10, 80, 114, 30);

		textTenFile = new Text(composite, SWT.BORDER);
		textTenFile.setEditable(false);
		textTenFile.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenFile.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenFile.setBounds(130, 80, 459, 30);

		Button btnTaiFileLen = new Button(composite, SWT.NONE);
		btnTaiFileLen.setVisible(true);
		btnTaiFileLen.setImage(SWTResourceManager.getImage(BaoPheAdd.class, "/itmanagerip/Icon/button/upload30.png"));
		btnTaiFileLen.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnTaiFileLen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnTaiFileLen.setBounds(595, 80, 59, 32);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi chú");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(0, 170, 124, 30);

		textGhiChu = new Text(composite, SWT.BORDER | SWT.MULTI);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(130, 130, 524, 120);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(BaoPheAdd.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(220, 270, 130, 35);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(BaoPheAdd.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(370, 270, 130, 35);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Thêm báo phế");
			lbNgayBaoPhe.setText("Ngày báo phế");
			lbGhiChu.setText("Ghi chú");
			lbTenFile.setText("Tên tệp");

			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
		} else {
			// Tieng Anh
			shell.setText("Add destruction");
			lbNgayBaoPhe.setText("Date of destruction");
			lbGhiChu.setText("Note");
			lbTenFile.setText("File name");

			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
		}

		// upload file
		btnTaiFileLen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] FILTER_NAMES = { "All Files (*.*)" };
				// đuôi file có thể mở
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

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!textTenFile.getText().isEmpty()) {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}
						connect.setStatement();
						String insert = "";
						String id = "";
						int idincrease = 0;
						String select = "SELECT TOP 1 (SELECT SUBSTRING([ID],3,8)) FROM [dbo].[BP_BaoPhe] ORDER BY [ID] DESC";
						ResultSet resultid = connect.getStatement().executeQuery(select);
						while (resultid.next()) {
							id = resultid.getString(1);
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
						// đọc hết một lần
						byte[] content = Files.readAllBytes(Paths.get(filename));

						insert = insert
								+ "INSERT INTO [dbo].[BP_BaoPhe]([ID],[NgayBaoPhe],[TenFile],[FileDinhKem],[GhiChu],[UserUpdate]) VALUES ('BP"
								+ idincrease + "','" + ConvertDate.saveDateToSQL(dateTimeNgayBaoPhe) + "',N'"
								+ textTenFile.getText() + "',?,N'" + textGhiChu.getText() + "','" + userlogin + "')";

						PreparedStatement stmt = connect.getConnection().prepareStatement(insert);
						stmt.setBytes(1, content);
						int result = stmt.executeUpdate();
						stmt.close();

						if (result > 0) {
							isaddsuccess = true;

							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Thêm thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Add suscess!");
							}
							thongbao.open();
							textGhiChu.setText("");
							textTenFile.setText("");
						}
						connect.closeStatement();
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Tên file rỗng!");
						} else {
							thongbao.setText("Error");
							thongbao.setMessage("File name is empty!");
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

	// -------------------------------------------------------------------------------------------
	public void setData(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	// kiểm tra xem thêm thành công hay không
	public boolean isAddSuccess() {
		return isaddsuccess;
	}
}
