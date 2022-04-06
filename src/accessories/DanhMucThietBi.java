package accessories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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

public class DanhMucThietBi {
	private Table table;
	private TableItem[] itemtable;
	private String[] masuadanhmuc;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Composite composite;
	private int ngonngu = 0;
	private Text textDanhMuc;
	private String grouplogin = ""; // nếu là User thì không cho xóa
	private Button btnXoa;
	private int status = -1; // 0: thêm, 1: sửa, 2: xóa
	private int stt = 0;
	private Button btntimkiem;
	private Button btnThem;
	private Button btnSua;
	private Button btnLuu;
	private Button btnHuy;
	private TableItem[] itemsua;
	private String madanhmucsua = "";

	public static void main(String[] args) {
		try {
			DanhMucThietBi window = new DanhMucThietBi();
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
		shell.setText("Danh mục thiết bị");
		shell.setImage(SWTResourceManager.getImage(DanhMucThietBi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1279, 1050);
		shell.setMaximized(true);
		if (ngonngu == 0) {
			shell.setText("Danh mục thiết bị");
		} else {
			shell.setText("Catalog of device");
		}

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(new FillLayout());
	}

	protected void createContents(Composite com, Shell shell) {

		composite = new Composite(com, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbDanhMuc = new CLabel(composite, SWT.RIGHT);
		lbDanhMuc.setText("Danh mục");
		lbDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDanhMuc.setBounds(10, 12, 118, 30);

		textDanhMuc = new Text(composite, SWT.BORDER);
		textDanhMuc.setTextLimit(30);
		textDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDanhMuc.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		// textDanhMuc.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textDanhMuc.setBounds(140, 12, 260, 30);

		btntimkiem = new Button(composite, SWT.BORDER | SWT.CENTER);
		btntimkiem
				.setImage(SWTResourceManager.getImage(DanhMucThietBi.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(410, 12, 50, 32);

		btnThem = new Button(composite, SWT.BORDER | SWT.CENTER);
		btnThem.setImage(SWTResourceManager.getImage(DanhMucThietBi.class, "/itmanagerip/Icon/button/add30.png"));
		btnThem.setBounds(470, 12, 50, 32);
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnSua = new Button(composite, SWT.BORDER | SWT.CENTER);
		btnSua.setImage(SWTResourceManager.getImage(DanhMucThietBi.class, "/itmanagerip/Icon/button/edit.png"));
		btnSua.setBounds(530, 12, 50, 32);
		btnSua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnSua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnXoa = new Button(composite, SWT.BORDER | SWT.CENTER);
		btnXoa.setImage(SWTResourceManager.getImage(DanhMucThietBi.class, "/itmanagerip/Icon/button/delete.png"));
		btnXoa.setBounds(590, 12, 50, 32);
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnLuu = new Button(composite, SWT.BORDER | SWT.CENTER);
		btnLuu.setImage(SWTResourceManager.getImage(DanhMucThietBi.class, "/itmanagerip/Icon/button/save25.png"));
		btnLuu.setBounds(650, 12, 50, 32);
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnHuy = new Button(composite, SWT.BORDER | SWT.CENTER);
		btnHuy.setImage(SWTResourceManager.getImage(DanhMucThietBi.class, "/itmanagerip/Icon/button/cancel22.png"));
		btnHuy.setBounds(710, 12, 50, 32);
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 54);
		table.setSize(750, 950);
		table.setSize(750, Display.getDefault().getBounds().height - 130);
		table.setLayoutData(new RowData(1867, 860));
		table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 215, 0));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
		tbcSTT.setWidth(60);
		tbcSTT.setText("STT");
		tbcSTT.setResizable(false);

		TableColumn tbcMaDanhMuc = new TableColumn(table, SWT.NONE);
		tbcMaDanhMuc.setWidth(0);
		tbcMaDanhMuc.setResizable(false);
		tbcMaDanhMuc.setText("Mã danh mục");

		TableColumn tbcDanhMuc = new TableColumn(table, SWT.NONE);
		tbcDanhMuc.setWidth(400);
		tbcDanhMuc.setText("Danh Mục");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		search(shell);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbDanhMuc.setText("Danh mục");

			tbcSTT.setText("STT");
			tbcDanhMuc.setText("Danh mục");

		} else {
			lbDanhMuc.setText("Category");

			tbcSTT.setText("Num");
			tbcDanhMuc.setText("Category");
		}

		// nếu là User thì không cho xóa
		if (grouplogin.equals("User")) {
			btnXoa.setVisible(false);
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

		// Tìm kiếm sau khi enter ở text danh mục. Nếu ở chế độ lưu thì lưu luôn
		// ******************************************************************************
		textDanhMuc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					if (status == 0) {
						save();
					} else if (status == 1) {
						edit();
					} else {
						search(shell);
					}
				}
			}
		});

		// Tìm kiếm
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search(shell);
			}
		});

		// Tìm kiếm sau khi enter ở button tìm kiếm
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search(shell);
				}
			}
		});

		// Add-------------------------------------------------------------------------------------------------
		btnThem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnLuu.setVisible(true);
				btnLuu.setLocation(410, 12);

				btnHuy.setVisible(true);
				btnHuy.setLocation(470, 12);

				btntimkiem.setVisible(false);
				btnThem.setVisible(false);
				btnSua.setVisible(false);
				btnXoa.setVisible(false);

				// textDanhMuc.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				textDanhMuc.setBackground(SWTResourceManager.getColor(224, 255, 255));
				status = 0;
			}
		});

		// Edit---------------------------------------------------------------------------------------------------
		btnSua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				itemsua = table.getSelection();
				madanhmucsua = itemsua[0].getText(1);
				textDanhMuc.setText(itemsua[0].getText(2));
				if (itemsua.length > 0) {
					status = 1;

					btnLuu.setVisible(true);
					btnLuu.setLocation(410, 12);

					btnHuy.setVisible(true);
					btnHuy.setLocation(470, 12);

					btntimkiem.setVisible(false);
					btnThem.setVisible(false);
					btnSua.setVisible(false);
					btnXoa.setVisible(false);

					textDanhMuc.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					// textDanhMuc.setBackground(SWTResourceManager.getColor(224, 255, 255));
				}
			}
		});

		// Xóa-------------------------------------------------------------------
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Lấy cột số mã thiết bị
					itemtable = table.getSelection();
					masuadanhmuc = new String[itemtable.length];

					for (int i = 0; i < masuadanhmuc.length; i++) {
						masuadanhmuc[i] = itemtable[i].getText(1);
					}
					if (masuadanhmuc.length > 0) {
						btnThem.setEnabled(false);
						btnSua.setEnabled(false);
						btntimkiem.setEnabled(false);
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						table.setEnabled(false);
						status = 2;
					} else {
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

				} catch (Exception ex) {
					btnThem.setEnabled(true);
					btnSua.setEnabled(true);
					btntimkiem.setEnabled(true);
					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
					table.setEnabled(true);
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
					if (status == 0) {
						save();
					} else if (status == 1) {
						// Sửa
						edit();
					} else if (status == 2) {
						// xóa
						try {
							String delete = "";
							for (int i = 0; i < masuadanhmuc.length; i++) {
								delete = delete + "\n" + "DELETE FROM [dbo].[LK_DanhMucThietBi] WHERE [MaDanhMuc]='"
										+ masuadanhmuc[i] + "'";
							}

							int result = connect.execUpdateQuery(delete);
							if (result > 0) {
								table.remove(table.getSelectionIndices());
							}
							connect.closeStatement();
							btnThem.setEnabled(true);
							btnSua.setEnabled(true);
							btntimkiem.setEnabled(true);
							btnLuu.setVisible(false);
							btnHuy.setVisible(false);
							table.setEnabled(true);
						} catch (Exception ex) {
						}
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
				cancel();
			}
		});

		// mo cua so sua khi double click vao dong cua bang
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				edit();
			}
		});

		// **********************************************************************************
		// Sắp xếp table theo một cột đã chọn
		SortData sort = new SortData();
		sort.setTable(table);
		sort.setNumber(true);

		tbcDanhMuc.addListener(SWT.Selection, sort.sortListenerCode);
	}

	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu) {
		this.ngonngu = ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Danh mục thiết bị");
		} else {
			itemtab.setText("Catalog of device");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(new FillLayout());
		itemtab.setControl(composite);

		createContents(composite, shell);
		table.setSize(750, Display.getDefault().getBounds().height - 170);
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

			String select = "SELECT MaDanhMuc,TenDanhMuc FROM LK_DanhMucThietBi WHERE TenDanhMuc LIKE N'%"
					+ textDanhMuc.getText() + "%' ORDER BY TenDanhMuc ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2) });
				stt++;
			}
			if (stt == 1) {
				stt = 0;
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

	// edit-------------------------------------------------------------------------------------
	public void edit() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			// sửa
			// MaDanhMuc,TenDanhMuc FROM LK_DanhMucThietBi
			if (!textDanhMuc.getText().trim().isEmpty()) {
				String update = "UPDATE LK_DanhMucThietBi SET TenDanhMuc=N'" + textDanhMuc.getText()
						+ "' WHERE MaDanhMuc=" + madanhmucsua;
				if (connect.execUpdateQuery(update) > 0) {
					itemsua[0].setText(2, textDanhMuc.getText());
					cancel();
				}
			}

			connect.closeStatement();

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

	// save-------------------------------------------------------------------------------------------------------------------------------------------------------
	public void save() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			if (status == 0) {
				connect.setStatement();
				// thêm
				// MaDanhMuc,TenDanhMuc FROM LK_DanhMucThietBi
				if (!textDanhMuc.getText().trim().isEmpty()) {
					int id = 0;
					String selectid = "SELECT TOP 1 MaDanhMuc FROM LK_DanhMucThietBi ORDER BY MaDanhMuc DESC";
					ResultSet result = connect.getStatement().executeQuery(selectid);
					while (result.next()) {
						try {
							id = Integer.parseInt(result.getString(1));
						} catch (Exception ex) {
						}
					}
					id++;
					result.close();

					String insert = "INSERT INTO LK_DanhMucThietBi (MaDanhMuc,TenDanhMuc) VALUES(" + id + ",N'"
							+ textDanhMuc.getText() + "')";
					if (connect.execUpdateQuery(insert) > 0) {
						if (stt == 0) {
							stt = 1;
						}
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(new String[] { stt + "", id + "", textDanhMuc.getText() });
						textDanhMuc.setText("");
						stt++;
					}
				}

				connect.closeStatement();
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

	// cancel------------------------------------------------------------------------------------------------------------------------------------------------
	public void cancel() {
		btntimkiem.setVisible(true);
		btntimkiem.setEnabled(true);
		btntimkiem.setLocation(410, 12);
		btnThem.setVisible(true);
		btnThem.setEnabled(true);
		btnThem.setLocation(470, 12);
		btnSua.setVisible(true);
		btnSua.setEnabled(true);
		btnSua.setLocation(530, 12);
		btnXoa.setVisible(true);
		btnXoa.setEnabled(true);
		btnXoa.setLocation(590, 12);

		btnLuu.setVisible(false);
		btnLuu.setLocation(650, 12);
		btnHuy.setVisible(false);
		btnHuy.setLocation(710, 12);

		textDanhMuc.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setEnabled(true);
		status = -1;
		textDanhMuc.setText("");
	}

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language) {
		ngonngu = language;
	}
}
