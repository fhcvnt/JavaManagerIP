package it;

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

public class ThietBiChoMuon {
	private Table table;
	private TableItem[] itemtable;
	private String[] mathietbixoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private Text textThietBi;
	private CCombo comboLoaiThietBi;
	private CCombo comboTrangThai;
	private int ngonngu = 1;

	public static void main(String[] args) {
		try {
			ThietBiChoMuon window = new ThietBiChoMuon();
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
		createContents(compositeshell, shell);
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
		shell.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Device for borrow");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(null);
	}

	protected void createContents(Composite composite, Shell shell) {
		CLabel lbThietBi = new CLabel(composite, SWT.RIGHT);
		lbThietBi.setBounds(10, 13, 97, 30);
		lbThietBi.setText("Thiết bị");
		lbThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textThietBi = new Text(composite, SWT.BORDER);
		textThietBi.setBounds(113, 13, 149, 30);
		textThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textThietBi.setTextLimit(30);

		comboTrangThai = new CCombo(composite, SWT.BORDER);
		comboTrangThai.setItems(new String[] { "Đã hư", "Bình thường", "Bị mất" });
		comboTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTrangThai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboTrangThai.setBounds(270, 13, 168, 30);

		comboLoaiThietBi = new CCombo(composite, SWT.BORDER);
		comboLoaiThietBi.setItems(new String[] {"Linh kiện", "Máy ảnh", "Máy ảnh Yeezy", "Phòng họp", "Băng đeo tay", "Băng đeo tay Yeezy", "Laptop"});
		comboLoaiThietBi.setBounds(446, 13, 168, 30);
		comboLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoaiThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem
				.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(625, 13, 149, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(780, 13, 106, 32);
		btnthem.setText("Thêm");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(892, 13, 97, 32);
		btnsua.setText("Sửa");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(995, 13, 120, 32);
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1121, 13, 109, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/cancel.png"));
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

		TableColumn tbcTenThietBi = new TableColumn(table, SWT.NONE);
		tbcTenThietBi.setWidth(240);
		tbcTenThietBi.setText("Tên thiết bị");

		TableColumn tbcTrangThai = new TableColumn(table, SWT.NONE);
		tbcTrangThai.setWidth(235);
		tbcTrangThai.setText("Trạng thái");

		TableColumn tbcLoaiThietBi = new TableColumn(table, SWT.NONE);
		tbcLoaiThietBi.setWidth(235);
		tbcLoaiThietBi.setText("Loại thiết bị");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(235);
		tbcGhiChu.setText("Ghi chú");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbThietBi.setText("Loại thiết bị");
			lbThietBi.setText("Thiết bị");
			btntimkiem.setText("Tìm kiếm");
			btnthem.setText("Thêm");
			btnsua.setText("Sửa");
			btnxoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			tbcSTT.setText("STT");
			tbcMaThietBi.setText("Mã thiết bị");
			tbcTenThietBi.setText("Tên thiết bị");
			tbcTrangThai.setText("Trạng thái");
			tbcLoaiThietBi.setText("Loại thiết bị");
			tbcGhiChu.setText("Ghi chú");
		} else {
			lbThietBi.setText("Type of device");
			lbThietBi.setText("Device");
			btntimkiem.setText("Search");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			tbcSTT.setText("Number");
			tbcMaThietBi.setText("Device id");
			tbcTenThietBi.setText("Device name");
			tbcTrangThai.setText("Status");
			tbcLoaiThietBi.setText("Device type");
			tbcGhiChu.setText("Note");
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

		// Tìm kiếm sau khi enter ở text thiet bi
		// ******************************************************************************
		textThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Tìm kiếm sau khi enter ở combo trang thai
		// ******************************************************************************
		comboTrangThai.addKeyListener(new KeyAdapter() {
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
		comboLoaiThietBi.addKeyListener(new KeyAdapter() {
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
				ThietBiChoMuonAdd thietbimoi = new ThietBiChoMuonAdd();
				// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
				composite.setEnabled(false);
				thietbimoi.setLanguage(ngonngu);
				thietbimoi.createContents();
				thietbimoi.open();
				composite.setEnabled(true);
				if (thietbimoi.isAddSuccess()) {
					search();
				}
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
						ThietBiChoMuonEdit suathietbi = new ThietBiChoMuonEdit();
						// mở cửa sổ mới thì cửa sổ cũ không cho thao tác
						composite.setEnabled(false);
						suathietbi.createContents();
						suathietbi.setData(ngonngu, itemsua[0].getText(1), itemsua[0].getText(2), itemsua[0].getText(3),
								itemsua[0].getText(4), itemsua[0].getText(5));
						suathietbi.open();
						composite.setEnabled(true);
						if (suathietbi.isEditSuccess()) {
							search();
						}
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
					btnthem.setEnabled(true);
					btnsua.setEnabled(true);
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
					try {
						String delete = "";
						for (int i = 0; i < mathietbixoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[IT_ThietBiChoMuon] WHERE [MaThietBi]='"
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
		tbcTenThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcTrangThai.addListener(SWT.Selection, sort.sortListenerCode);
		tbcLoaiThietBi.addListener(SWT.Selection, sort.sortListenerCode);
		tbcGhiChu.addListener(SWT.Selection, sort.sortListenerCode);
	}

	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu) {
		this.ngonngu = ngonngu;
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Thiết bị cho mượn");
		} else {
			itemtab.setText("Device for borrow");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		composite.setLayout(null);
		itemtab.setControl(composite);

		createContents(composite, shell);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 170);
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

			String trangthai = comboTrangThai.getText().isEmpty() ? ""
					: "AND [TrangThai]=" + comboTrangThai.getSelectionIndex();
			String loaithietbi = comboLoaiThietBi.getText().isEmpty() ? ""
					: " AND [LoaiThietBi]=" + comboLoaiThietBi.getSelectionIndex();
			String select = "SELECT [MaThietBi],[TenThietBi],CASE WHEN [TrangThai]=0 THEN N'Đã hư' WHEN [TrangThai]=1 THEN N'Bình thường' WHEN [TrangThai]=2 THEN N'Bị mất' ELSE '' END AS TrangThai,CASE WHEN [LoaiThietBi]=0 THEN N'Linh kiện' WHEN [LoaiThietBi]=1 THEN N'Máy ảnh' WHEN [LoaiThietBi]=2 THEN N'Máy ảnh Yeezy'  WHEN [LoaiThietBi]=3 THEN N'Phòng họp' WHEN [LoaiThietBi]=4 THEN N'Băng đeo tay' WHEN [LoaiThietBi]=5 THEN N'Băng đeo tay Yeezy' ELSE '' END AS [LoaiThietBi],[GhiChu] FROM [dbo].[IT_ThietBiChoMuon] WHERE [TenThietBi] LIKE N'%"
					+ textThietBi.getText() + "%'" + trangthai + loaithietbi + " ORDER BY [LoaiThietBi] ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5) });
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
