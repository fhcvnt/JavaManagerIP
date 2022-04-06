package it;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

public class DanhSachThietBiMayTinh {
	private Table table;
	private TableItem[] itemtable;
	private String[] mathietbixoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Text textLoaiThietBi;
	private CCombo comboThietBi;
	private int ngonngu = 1;

	public static void main(String[] args) {
		try {
			DanhSachThietBiMayTinh window = new DanhSachThietBiMayTinh();
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
		shell.setImage(SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Type of device");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbLoaiThietBi = new CLabel(composite, SWT.RIGHT);
		lbLoaiThietBi.setBounds(10, 13, 135, 30);
		lbLoaiThietBi.setText("Loại thiết bị");
		lbLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textLoaiThietBi = new Text(composite, SWT.BORDER);
		textLoaiThietBi.setBounds(151, 13, 149, 30);
		textLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textLoaiThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textLoaiThietBi.setTextLimit(30);

		CLabel lbThietBi = new CLabel(composite, SWT.RIGHT);
		lbThietBi.setBounds(306, 13, 106, 30);
		lbThietBi.setText("Thiết bị");
		lbThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboThietBi = new CCombo(composite, SWT.BORDER);
		comboThietBi.setItems(new String[] { "CPU", "Hard Drive", "Mainboard", "Monitor", "Printer", "RAM", "UPS" });
		comboThietBi.setBounds(418, 13, 201, 30);
		comboThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(625, 13, 149, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(780, 13, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(892, 13, 97, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(995, 13, 120, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1121, 13, 109, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1236, 13, 120, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 56);
		table.setSize(1900, 925);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 125);
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

		TableColumn tbcMaThietBi = new TableColumn(table, SWT.NONE);
		tbcMaThietBi.setWidth(153);
		tbcMaThietBi.setText("Mã thiết bị");

		TableColumn tbcLoaiThietBi = new TableColumn(table, SWT.NONE);
		tbcLoaiThietBi.setWidth(213);
		tbcLoaiThietBi.setText("Loại thiết bị");

		TableColumn tbcThietBi = new TableColumn(table, SWT.NONE);
		tbcThietBi.setWidth(235);
		tbcThietBi.setText("Thiết bị");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Loại thiết bị");
			lbLoaiThietBi.setText("Loại thiết bị");
			lbThietBi.setText("Thiết bị");
			btntimkiem.setText("Tìm kiếm");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			tbcSTT.setText("STT");
			tbcMaThietBi.setText("Mã thiết bị");
			tbcLoaiThietBi.setText("Loại thiết bị");
			tbcThietBi.setText("Thiết bị");

		} else {
			// Tieng Anh
			shell.setText("Type of device");
			lbLoaiThietBi.setText("Type of device");
			lbThietBi.setText("Device");
			btntimkiem.setText("Search");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			tbcSTT.setText("Number");
			tbcMaThietBi.setText("Device id");
			tbcLoaiThietBi.setText("Type of device");
			tbcThietBi.setText("Device");
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
				search();
			}
		});

		// Tìm kiếm sau khi enter ở button tìm kiếm
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở text loai thiet bi
		// ******************************************************************************
		textLoaiThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở combo thiet bi
		// ******************************************************************************
		comboThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Thêm-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DanhSachThietBiMayTinhAdd thietbimoi = new DanhSachThietBiMayTinhAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				thietbimoi.setLanguage(ngonngu);
				thietbimoi.createContents();
				thietbimoi.open();
				composite.setEnabled(true);
				search();
			}
		});

		// Sửa---------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String mathietbisua = "";
					mathietbisua = itemsua[0].getText(1);
					if (!mathietbisua.isEmpty()) {
						DanhSachThietBiMayTinhEdit suathietbi = new DanhSachThietBiMayTinhEdit();
						// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
						composite.setEnabled(false);
						suathietbi.createContents();
						suathietbi.setData(ngonngu, itemsua[0].getText(1), itemsua[0].getText(2),
								itemsua[0].getText(3));
						suathietbi.open();
						composite.setEnabled(true);
						search();
					}

				} catch (Exception ex) {
				}
			}
		});

		// Xóa-------------------------------------------------------------------
		btnxoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Lấy cột số mã thiết bị
					itemtable = table.getSelection();
					mathietbixoa = new String[itemtable.length];

					for (int i = 0; i < mathietbixoa.length; i++) {
						mathietbixoa[i] = itemtable[i].getText(1);
					}
					if (mathietbixoa.length > 0) {
						btnthem.setEnabled(false);
						btnsua.setEnabled(false);
						btntimkiem.setEnabled(false);
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						table.setEnabled(false);
					}

				} catch (Exception ex) {
					btnthem.setEnabled(true);
					btnsua.setEnabled(true);
					btntimkiem.setEnabled(true);
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
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

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					try {
						String delete = "";
						for (int i = 0; i < mathietbixoa.length; i++) {
							delete = delete + "\n"
									+ "DELETE FROM [dbo].[IT_DanhSachThietBiMayTinh] WHERE [MaThietBiMayTinh]='"
									+ mathietbixoa[i] + "'";
						}

						int result = connect.execUpdateQuery(delete);
						if (result > 0) {
							table.remove(table.getSelectionIndices());
						}
						connect.closeStatement();
						btnthem.setEnabled(true);
						btnsua.setEnabled(true);
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
				}
			}
		});

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnthem.setEnabled(true);
				btnsua.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcMaThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcLoaiThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcThietBi.addListener(SWT.Selection, sort.sortListenerCode);

	}

	// search
	// --------------------------------------------------------------------------
	public void search() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();
			table.removeAll();

			String select = "SELECT [MaThietBiMayTinh],[LoaiThietBi],[TenLoaiThietBi] FROM [dbo].[IT_DanhSachThietBiMayTinh] WHERE [LoaiThietBi] LIKE '%"
					+ textLoaiThietBi.getText() + "%' AND [TenLoaiThietBi] LIKE '%" + comboThietBi.getText()
					+ "%' ORDER BY [TenLoaiThietBi] ASC";

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

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language) {
		ngonngu = language;
	}
}
