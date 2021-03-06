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
		lbLoaiThietBi.setText("Lo???i thi???t b???");
		lbLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textLoaiThietBi = new Text(composite, SWT.BORDER);
		textLoaiThietBi.setBounds(151, 13, 149, 30);
		textLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textLoaiThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textLoaiThietBi.setTextLimit(30);

		CLabel lbThietBi = new CLabel(composite, SWT.RIGHT);
		lbThietBi.setBounds(306, 13, 106, 30);
		lbThietBi.setText("Thi???t b???");
		lbThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboThietBi = new CCombo(composite, SWT.BORDER);
		comboThietBi.setItems(new String[] { "CPU", "Hard Drive", "Mainboard", "Monitor", "Printer", "RAM", "UPS" });
		comboThietBi.setBounds(418, 13, 201, 30);
		comboThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("T??m ki???m");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(625, 13, 149, 32);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(780, 13, 106, 32);
		btnthem.setText("Th??m");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(892, 13, 97, 32);
		btnsua.setText("S???a");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(995, 13, 120, 32);
		btnxoa.setText("X??a");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(1121, 13, 109, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinh.class, "/itmanagerip/Icon/button/cancel.png"));
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

		TableColumn tbcLoaiThietBi = new TableColumn(table, SWT.NONE);
		tbcLoaiThietBi.setWidth(213);
		tbcLoaiThietBi.setText("Lo???i thi???t b???");

		TableColumn tbcThietBi = new TableColumn(table, SWT.NONE);
		tbcThietBi.setWidth(235);
		tbcThietBi.setText("Thi???t b???");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Lo???i thi???t b???");
			lbLoaiThietBi.setText("Lo???i thi???t b???");
			lbThietBi.setText("Thi???t b???");
			btntimkiem.setText("T??m ki???m");
			btnthem.setText("Th??m");
			btnsua.setText("S???a");
			btnxoa.setText("X??a");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
			tbcSTT.setText("STT");
			tbcMaThietBi.setText("M?? thi???t b???");
			tbcLoaiThietBi.setText("Lo???i thi???t b???");
			tbcThietBi.setText("Thi???t b???");

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

		// T??m ki???m sau khi enter ??? text loai thiet bi
		// ******************************************************************************
		textLoaiThietBi.addKeyListener(new KeyAdapter() {
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
		comboThietBi.addKeyListener(new KeyAdapter() {
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
				DanhSachThietBiMayTinhAdd thietbimoi = new DanhSachThietBiMayTinhAdd();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				thietbimoi.setLanguage(ngonngu);
				thietbimoi.createContents();
				thietbimoi.open();
				composite.setEnabled(true);
				search();
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
						DanhSachThietBiMayTinhEdit suathietbi = new DanhSachThietBiMayTinhEdit();
						// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
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
						thongbao.setText("Th??ng b??o");
						thongbao.setMessage("Kh??ng c?? d??ng n??o ???????c ch???n!");
					} else {
						thongbao.setText("Notice");
						thongbao.setMessage("No line is selected!");
					}
					thongbao.open();
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

			// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
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
