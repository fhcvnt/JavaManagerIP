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
		lbThietBi.setText("Thi???t b???");
		lbThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textThietBi = new Text(composite, SWT.BORDER);
		textThietBi.setBounds(113, 13, 149, 30);
		textThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textThietBi.setTextLimit(30);

		comboTrangThai = new CCombo(composite, SWT.BORDER);
		comboTrangThai.setItems(new String[] { "???? h??", "B??nh th?????ng", "B??? m???t" });
		comboTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTrangThai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboTrangThai.setBounds(270, 13, 168, 30);

		comboLoaiThietBi = new CCombo(composite, SWT.BORDER);
		comboLoaiThietBi.setItems(new String[] {"Linh ki???n", "M??y ???nh", "M??y ???nh Yeezy", "Ph??ng h???p", "B??ng ??eo tay", "B??ng ??eo tay Yeezy", "Laptop"});
		comboLoaiThietBi.setBounds(446, 13, 168, 30);
		comboLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoaiThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem
				.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("T??m ki???m");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(625, 13, 149, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(780, 13, 106, 32);
		btnthem.setText("Th??m");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(892, 13, 97, 32);
		btnsua.setText("S???a");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(995, 13, 120, 32);
		btnxoa.setText("X??a");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1121, 13, 109, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBiChoMuon.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(1236, 13, 120, 32);
		btnHuy.setText("H???y");
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
		tbcMaThietBi.setText("M?? thi???t b???");

		TableColumn tbcTenThietBi = new TableColumn(table, SWT.NONE);
		tbcTenThietBi.setWidth(240);
		tbcTenThietBi.setText("T??n thi???t b???");

		TableColumn tbcTrangThai = new TableColumn(table, SWT.NONE);
		tbcTrangThai.setWidth(235);
		tbcTrangThai.setText("Tr???ng th??i");

		TableColumn tbcLoaiThietBi = new TableColumn(table, SWT.NONE);
		tbcLoaiThietBi.setWidth(235);
		tbcLoaiThietBi.setText("Lo???i thi???t b???");

		TableColumn tbcGhiChu = new TableColumn(table, SWT.NONE);
		tbcGhiChu.setWidth(235);
		tbcGhiChu.setText("Ghi ch??");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			lbThietBi.setText("Lo???i thi???t b???");
			lbThietBi.setText("Thi???t b???");
			btntimkiem.setText("T??m ki???m");
			btnthem.setText("Th??m");
			btnsua.setText("S???a");
			btnxoa.setText("X??a");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
			tbcSTT.setText("STT");
			tbcMaThietBi.setText("M?? thi???t b???");
			tbcTenThietBi.setText("T??n thi???t b???");
			tbcTrangThai.setText("Tr???ng th??i");
			tbcLoaiThietBi.setText("Lo???i thi???t b???");
			tbcGhiChu.setText("Ghi ch??");
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

		// Event ph??m t???t (ctrl + A)
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t (ctrl + A) ch???n h???t b???ng
				if (e.keyCode == 'a' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
					table.selectAll();
				}
			}
		});

		// T??m ki???m
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search();
			}
		});

		// T??m ki???m sau khi enter ??? button t??m ki???m
		// ******************************************************************************
		btntimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text thiet bi
		// ******************************************************************************
		textThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? combo trang thai
		// ******************************************************************************
		comboTrangThai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? combo thiet bi
		// ******************************************************************************
		comboLoaiThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// Th??m-------------------------------------------------------------------------------------------------
		btnthem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ThietBiChoMuonAdd thietbimoi = new ThietBiChoMuonAdd();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
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

		// S???a---------------------------------------------------------------------------------------------------
		btnsua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					String mathietbisua = "";
					mathietbisua = itemsua[0].getText(1);
					if (!mathietbisua.isEmpty()) {
						ThietBiChoMuonEdit suathietbi = new ThietBiChoMuonEdit();
						// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
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

		// X??a-------------------------------------------------------------------
		btnxoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// L???y c???t s??? m?? thi???t b???
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
							thongbao.setText("Th??ng b??o");
							thongbao.setMessage("Kh??ng c?? d??ng n??o ???????c ch???n!");
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

		// L??u--------------------------------------------------------------------------------------------------------------
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
				btnthem.setEnabled(true);
				btnsua.setEnabled(true);
				btntimkiem.setEnabled(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				table.setEnabled(true);
			}
		});

		// **********************************************************************************
		// S???p x???p table theo m???t c???t ???? ch???n
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
			itemtab.setText("Thi???t b??? cho m?????n");
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
			String select = "SELECT [MaThietBi],[TenThietBi],CASE WHEN [TrangThai]=0 THEN N'???? h??' WHEN [TrangThai]=1 THEN N'B??nh th?????ng' WHEN [TrangThai]=2 THEN N'B??? m???t' ELSE '' END AS TrangThai,CASE WHEN [LoaiThietBi]=0 THEN N'Linh ki???n' WHEN [LoaiThietBi]=1 THEN N'M??y ???nh' WHEN [LoaiThietBi]=2 THEN N'M??y ???nh Yeezy'  WHEN [LoaiThietBi]=3 THEN N'Ph??ng h???p' WHEN [LoaiThietBi]=4 THEN N'B??ng ??eo tay' WHEN [LoaiThietBi]=5 THEN N'B??ng ??eo tay Yeezy' ELSE '' END AS [LoaiThietBi],[GhiChu] FROM [dbo].[IT_ThietBiChoMuon] WHERE [TenThietBi] LIKE N'%"
					+ textThietBi.getText() + "%'" + trangthai + loaithietbi + " ORDER BY [LoaiThietBi] ASC";

			// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
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
	public void setLanguage(int language) {
		ngonngu = language;
	}
}
