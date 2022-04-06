package it;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

import sort.SortData;
import sql.ConnectSQL;

public class LyLichMayTinhUpdateMaDonVi {
	private Table table;
	private TableItem[] itemtable;
	private String[] malylichupdate;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;

	private Text textDonVi;
	private int ngonngu = 1;
	private Text textMaSoDonVi;
	private String idupdate = "";

	public static void main(String[] args) {
		try {
			LyLichMayTinhUpdateMaDonVi window = new LyLichMayTinhUpdateMaDonVi();
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
		createShell();
		createContents(compositeshell, shell);
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
	protected void createShell() {
		shell = new Shell();
		shell.setText("Cập nhật mã số đơn vị");
		shell.setImage(SWTResourceManager.getImage(LyLichMayTinhUpdateMaDonVi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1362, 734);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Cập nhật mã số đơn vị");
		} else {
			shell.setText("Update department id");
		}

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(new FillLayout());
	}

	protected void createContents(Composite com, Shell shell) {
		Composite composite = new Composite(com, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 13, 106, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setBounds(122, 13, 293, 30);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setTextLimit(50);

		CLabel lbMSoDonVi = new CLabel(composite, SWT.RIGHT);
		lbMSoDonVi.setBounds(421, 13, 161, 30);
		lbMSoDonVi.setText("Mã số đơn vị");
		lbMSoDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textMaSoDonVi = new Text(composite, SWT.BORDER);
		textMaSoDonVi.setTextLimit(12);
		textMaSoDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaSoDonVi.setBounds(588, 13, 211, 30);

		Button btnCapNhat = new Button(composite, SWT.NONE);
		btnCapNhat.setImage(
				SWTResourceManager.getImage(LyLichMayTinhUpdateMaDonVi.class, "/itmanagerip/Icon/button/update.png"));
		btnCapNhat.setBounds(812, 13, 134, 32);
		btnCapNhat.setText("Cập nhật");
		btnCapNhat.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnCapNhat.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnSearch = new Button(composite, SWT.NONE);
		btnSearch.setText("Tìm kiếm");
		btnSearch.setImage(SWTResourceManager.getImage(LyLichMayTinhUpdateMaDonVi.class,
				"/itmanagerip/Icon/button/search-30.png"));
		btnSearch.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnSearch.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnSearch.setBounds(954, 13, 134, 32);

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 55);
		table.setSize(1900, 901);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 130);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(83);
		tbcSTT.setText("STT");

		TableColumn tbcMaLyLich = new TableColumn(table, SWT.NONE);
		tbcMaLyLich.setWidth(178);
		tbcMaLyLich.setText("Mã lý lịch");

		TableColumn tbcDonVi = new TableColumn(table, SWT.NONE);
		tbcDonVi.setWidth(352);
		tbcDonVi.setText("Đơn vị");

		TableColumn tbcMaSoDonVi = new TableColumn(table, SWT.NONE);
		tbcMaSoDonVi.setWidth(251);
		tbcMaSoDonVi.setText("Mã số đơn vị");

		search(shell);
		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			lbDonVi.setText("Đơn vị");
			lbMSoDonVi.setText("Mã số đơn vị");
			btnCapNhat.setText("Cập nhật");
			btnSearch.setText("Tìm kiếm");
			tbcSTT.setText("STT");
			tbcMaLyLich.setText("Mã lý lịch");
			tbcDonVi.setText("Đơn vị");
			tbcMaSoDonVi.setText("Mã số đơn vị");
		} else {
			// Tieng Anh
			lbDonVi.setText("Department");
			lbMSoDonVi.setText("Department id");
			btnCapNhat.setText("Update");
			btnSearch.setText("Search");
			tbcSTT.setText("Number");
			tbcMaLyLich.setText("Profile id");
			tbcDonVi.setText("Department");
			tbcMaSoDonVi.setText("Department id");
		}

		// Sự kiện thay đổi sự chọn lựa của bảng thì sẽ lấy thông tin lên các label
		// ----------------------------------------------------------------------------------------------------------
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] item = table.getSelection();
					textDonVi.setText(item[0].getText(2));
				} catch (Exception ex) {
				}
			}
		});

		// ---------------------------------------------------------------------------------------------------
		btnCapNhat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					if (!textDonVi.getText().isEmpty()) {
						if (!textMaSoDonVi.getText().isEmpty()) {
							idupdate = textMaSoDonVi.getText();
							try {
								String update = "";
								textMaSoDonVi.setText("");
								search(shell);

								table.selectAll();
								itemtable = table.getSelection();
								malylichupdate = new String[itemtable.length];

								for (int i = 0; i < malylichupdate.length; i++) {
									malylichupdate[i] = itemtable[i].getText(1);
								}

								for (int i = 0; i < malylichupdate.length; i++) {
									update = update + "\n" + "UPDATE [dbo].[IT_LyLichMayTinh] SET [MaSoDonVi] = '"
											+ idupdate + i + "' WHERE [MaLyLich]='" + malylichupdate[i]
											+ "' AND [DonVi] =N'" + textDonVi.getText() + "'";
								}

								int result = connect.execUpdateQuery(update);
								if (result > 0) {
									connect.closeStatement();
									MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
									if (ngonngu == 0) {
										thongbao.setText("Thông báo");
										thongbao.setMessage("Cập nhật thành công!");
									} else {
										thongbao.setText("Notice");
										thongbao.setMessage("Update successful!");
									}
									thongbao.open();
									search(shell);
								}
							} catch (Exception ex) {
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Thông báo lỗi");
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
								thongbao.setText("Thông báo lỗi");
								thongbao.setMessage("Mã số đơn vị rỗng!");
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage("Department id is empty!");
							}
							thongbao.open();
						}
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Đơn vị rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Department is empty!");
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

		// ---------------------------------------------------------------------------------------------------
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);
		tbcDonVi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcMaSoDonVi.addListener(SWT.Selection, sort.sortListenerCode);
	}

	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu) {
		this.ngonngu = ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Lý lịch máy tính");
		} else {
			itemtab.setText("Computer profile");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);

		createContents(composite, shell);
	}

	// search
	// --------------------------------------------------------------------------
	public void search(Shell shell) {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();
			table.removeAll();

			String select = "SELECT [MaLyLich],[DonVi],[MaSoDonVi] FROM [dbo].[IT_LyLichMayTinh] WHERE [DonVi] LIKE N'%"
					+ textDonVi.getText() + "%' AND [MaSoDonVi] LIKE '%" + textMaSoDonVi.getText()
					+ "%' ORDER BY [MaLyLich] ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3) });
				stt++;
			}

			result.close();
			state.close();
			connect.closeStatement();

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

	public void setLanguage(int ngonngu) {
		this.ngonngu = ngonngu;
	}
}
