package ittemp;

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

public class ThietBi {
	private Table table;
	private TableItem[] itemtable;
	private String[] mathietbixoa;
	private ConnectSQL connect;

	protected Shell shell;
	private Text textMaThietBi;
	private Text textTenThietBi;
	private Text textQuyCach;
	private CCombo comboLoaiVatTu;
	private int ngonngu = 1;

	public static void main(String[] args) {
		try {
			ThietBi window = new ThietBi();
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
		shell.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1919, 1050);
		shell.setMaximized(true);
		shell.setText("Devices");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbMaThietBi = new CLabel(composite, SWT.RIGHT);
		lbMaThietBi.setBounds(10, 13, 113, 30);
		lbMaThietBi.setText("M?? Thi???t B???");
		lbMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textMaThietBi = new Text(composite, SWT.BORDER);
		textMaThietBi.setBounds(129, 13, 104, 30);
		textMaThietBi.setTextLimit(12);
		textMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setBounds(236, 13, 113, 30);
		lbTenThietBi.setText("T??n Thi???t B???");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setBounds(355, 13, 190, 30);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbQuyCach = new CLabel(composite, SWT.RIGHT);
		lbQuyCach.setBounds(551, 13, 127, 30);
		lbQuyCach.setText("Quy C??ch");
		lbQuyCach.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textQuyCach = new Text(composite, SWT.BORDER);
		textQuyCach.setBounds(684, 13, 99, 30);
		textQuyCach.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textQuyCach.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("T??m ki???m");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(789, 13, 149, 32);

		CLabel lbLoaiVatTu = new CLabel(composite, SWT.RIGHT);
		lbLoaiVatTu.setText("Lo???i V???t T??");
		lbLoaiVatTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoaiVatTu.setBounds(10, 51, 112, 30);

		comboLoaiVatTu = new CCombo(composite, SWT.BORDER);
		comboLoaiVatTu.setItems(new String[] { "?????nh m???c", "Ti??u hao" });
		comboLoaiVatTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoaiVatTu.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboLoaiVatTu.setBounds(129, 51, 210, 30);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(355, 49, 106, 32);
		btnthem.setText("Th??m");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(467, 49, 92, 32);
		btnsua.setText("S???a");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(565, 49, 113, 32);
		btnxoa.setText("X??a");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(684, 49, 94, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(784, 49, 103, 32);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 91);
		table.setSize(1900, 934);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 152);
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
		tbcMaThietBi.setWidth(170);
		tbcMaThietBi.setText("M?? Thi???t B???");

		TableColumn tbcTenThietBi = new TableColumn(table, SWT.NONE);
		tbcTenThietBi.setWidth(295);
		tbcTenThietBi.setText("T??n Thi???t B???");

		TableColumn tbcQuyCach = new TableColumn(table, SWT.NONE);
		tbcQuyCach.setWidth(146);
		tbcQuyCach.setText("Quy C??ch");

		TableColumn tbcLoaiVatTu = new TableColumn(table, SWT.NONE);
		tbcLoaiVatTu.setWidth(157);
		tbcLoaiVatTu.setText("Lo???i v???t T??");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Thi???t b???");
			lbMaThietBi.setText("M?? thi???t b???");
			lbTenThietBi.setText("T??n thi???t b???");
			lbQuyCach.setText("Quy c??ch");
			btnthem.setText("Th??m");
			btnsua.setText("S???a");
			btnxoa.setText("X??a");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
			lbLoaiVatTu.setText("Lo???i v???t t??");
			btntimkiem.setText("T??m ki???m");
			tbcSTT.setText("STT");
			tbcMaThietBi.setText("M?? thi???t b???");
			tbcTenThietBi.setText("T??n thi???t b???");
			tbcQuyCach.setText("Quy c??ch");
			tbcLoaiVatTu.setText("Lo???i v???t t??");

		} else {
			// Tieng Anh
			shell.setText("Devices");
			lbMaThietBi.setText("ID");
			lbTenThietBi.setText("Device name");
			lbQuyCach.setText("Specifications ");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			lbLoaiVatTu.setText("IP");
			btntimkiem.setText("Search");
			tbcSTT.setText("Number");
			tbcMaThietBi.setText("ID");
			tbcTenThietBi.setText("Device name");
			tbcQuyCach.setText("Specifications");
			tbcLoaiVatTu.setText("Type of material");
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

		// T??m ki???m sau khi enter ??? text MaThietBi
		// ******************************************************************************
		textMaThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text TenThietBi
		// ******************************************************************************
		textTenThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text QuyCach
		// ******************************************************************************
		textQuyCach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? comboLoaiVatTu
		// ******************************************************************************
		comboLoaiVatTu.addKeyListener(new KeyAdapter() {
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
				ThietBiNew thietbimoi = new ThietBiNew();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				thietbimoi.setLanguage(ngonngu);
				thietbimoi.open();
				composite.setEnabled(true);
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
						ThietBiEdit suathietbi = new ThietBiEdit();
						// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
						composite.setEnabled(false);
						int loaivattu = -1;
						if (itemsua[0].getText(4).equalsIgnoreCase("Ti??u hao")) {
							loaivattu = 1;
						} else {
							loaivattu = 0;
						}
						suathietbi.setLanguage(ngonngu);
						suathietbi.createContents();
						suathietbi.setDataEdit(itemsua[0].getText(1), itemsua[0].getText(2), itemsua[0].getText(3),
								loaivattu);
						suathietbi.open();
						composite.setEnabled(true);
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
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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
					connect.setStatement();
					try {
						String delete = "";
						for (int i = 0; i < mathietbixoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[IT_ThietBi] WHERE [MaThietBi]='"
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
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Th??ng b??o l???i");
						thongbao.setMessage("L???i k???t n???i!\n" + se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Connect error!\n" + se.toString());
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
		tbcQuyCach.addListener(SWT.Selection, sort.sortListenerCode);
		tbcLoaiVatTu.addListener(SWT.Selection, sort.sortListenerCode);

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
			String loaivattu = "";
			if (comboLoaiVatTu.getSelectionIndex() == -1) {
				loaivattu = "";
			} else {
				loaivattu = " AND [VatTuTieuHao]=" + comboLoaiVatTu.getSelectionIndex();
			}

			String select = "SELECT [MaThietBi],[TenThietBi],[QuyCach],CASE [VatTuTieuHao] WHEN 0 THEN N'?????nh m???c' ELSE N'Ti??u hao' END AS 'LoaiVatTu' FROM [dbo].[IT_ThietBi] WHERE [MaThietBi] LIKE '%"
					+ textMaThietBi.getText() + "%' AND ([TenThietBi] LIKE N'%" + textTenThietBi.getText()
					+ "%' OR [dbo].[convertUnicodetoASCII]([TenThietBi]) LIKE '%" + textTenThietBi.getText()
					+ "%') AND ([QuyCach] LIKE N'%" + textQuyCach.getText()
					+ "%' OR [dbo].[convertUnicodetoASCII]([QuyCach]) LIKE '%" + textQuyCach.getText() + "%')"
					+ loaivattu;

			// X??? l?? d??? li???u t??m ki???m, hi???n th??? l??n Table
			ResultSet result = state.executeQuery(select);
			int stt = 1;
			while (result.next()) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", result.getString(1), result.getString(2), result.getString(3),
						result.getString(4) });
				stt++;
			}

			result.close();
			state.close();
			connect.closeStatement();

		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Th??ng b??o l???i");
				thongbao.setMessage("L???i k???t n???i!\n" + se.toString());
			} else {
				thongbao.setText("Notice error");
				thongbao.setMessage("Connect error!\n" + se.toString());
			}
			thongbao.open();
		}
	}

	// Hi???n trong CTabFolder
	// -------------------------------------------------------------------------------------------------------------------
	public void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu) {

		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		itemtab.setText("Devices");

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		itemtab.setControl(composite);

		CLabel lbMaThietBi = new CLabel(composite, SWT.RIGHT);
		lbMaThietBi.setBounds(10, 13, 113, 30);
		lbMaThietBi.setText("M?? Thi???t B???");
		lbMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textMaThietBi = new Text(composite, SWT.BORDER);
		textMaThietBi.setBounds(129, 13, 104, 30);
		textMaThietBi.setTextLimit(12);
		textMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setBounds(236, 13, 113, 30);
		lbTenThietBi.setText("T??n Thi???t B???");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setBounds(355, 13, 190, 30);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		CLabel lbQuyCach = new CLabel(composite, SWT.RIGHT);
		lbQuyCach.setBounds(551, 13, 127, 30);
		lbQuyCach.setText("Quy C??ch");
		lbQuyCach.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textQuyCach = new Text(composite, SWT.BORDER);
		textQuyCach.setBounds(684, 13, 99, 30);
		textQuyCach.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textQuyCach.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("T??m ki???m");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(789, 13, 149, 32);

		CLabel lbLoaiVatTu = new CLabel(composite, SWT.RIGHT);
		lbLoaiVatTu.setText("Lo???i V???t T??");
		lbLoaiVatTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoaiVatTu.setBounds(10, 51, 112, 30);

		comboLoaiVatTu = new CCombo(composite, SWT.BORDER);
		comboLoaiVatTu.setItems(new String[] { "?????nh m???c", "Ti??u hao" });
		comboLoaiVatTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoaiVatTu.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboLoaiVatTu.setBounds(129, 51, 210, 30);

		Button btnthem = new Button(composite, SWT.NONE);
		btnthem.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/add30.png"));
		btnthem.setBounds(355, 49, 106, 32);
		btnthem.setText("Th??m");
		btnthem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnthem.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnsua = new Button(composite, SWT.NONE);
		btnsua.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/edit.png"));
		btnsua.setBounds(467, 49, 92, 32);
		btnsua.setText("S???a");
		btnsua.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnsua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnxoa = new Button(composite, SWT.NONE);
		btnxoa.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/delete.png"));
		btnxoa.setBounds(565, 49, 113, 32);
		btnxoa.setText("X??a");
		btnxoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnxoa.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(684, 49, 94, 32);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBi.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(784, 49, 103, 32);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setLocation(10, 91);
		table.setSize(1900, 934);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 200);
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
		tbcMaThietBi.setWidth(170);
		tbcMaThietBi.setText("M?? Thi???t B???");

		TableColumn tbcTenThietBi = new TableColumn(table, SWT.NONE);
		tbcTenThietBi.setWidth(295);
		tbcTenThietBi.setText("T??n Thi???t B???");

		TableColumn tbcQuyCach = new TableColumn(table, SWT.NONE);
		tbcQuyCach.setWidth(146);
		tbcQuyCach.setText("Quy C??ch");

		TableColumn tbcLoaiVatTu = new TableColumn(table, SWT.NONE);
		tbcLoaiVatTu.setWidth(157);
		tbcLoaiVatTu.setText("Lo???i v???t T??");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			itemtab.setText("Thi???t b???");
			lbMaThietBi.setText("M?? thi???t b???");
			lbTenThietBi.setText("T??n thi???t b???");
			lbQuyCach.setText("Quy c??ch");
			btnthem.setText("Th??m");
			btnsua.setText("S???a");
			btnxoa.setText("X??a");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
			lbLoaiVatTu.setText("Lo???i v???t t??");
			btntimkiem.setText("T??m ki???m");
			tbcSTT.setText("STT");
			tbcMaThietBi.setText("M?? thi???t b???");
			tbcTenThietBi.setText("T??n thi???t b???");
			tbcQuyCach.setText("Quy c??ch");
			tbcLoaiVatTu.setText("Lo???i v???t t??");

		} else {
			// Tieng Anh
			itemtab.setText("Devices");
			lbMaThietBi.setText("ID");
			lbTenThietBi.setText("Device name");
			lbQuyCach.setText("Specifications ");
			btnthem.setText("Add");
			btnsua.setText("Edit");
			btnxoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			lbLoaiVatTu.setText("IP");
			btntimkiem.setText("Search");
			tbcSTT.setText("Number");
			tbcMaThietBi.setText("ID");
			tbcTenThietBi.setText("Device name");
			tbcQuyCach.setText("Specifications");
			tbcLoaiVatTu.setText("Type of material");
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

		// T??m ki???m sau khi enter ??? text MaThietBi
		// ******************************************************************************
		textMaThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text TenThietBi
		// ******************************************************************************
		textTenThietBi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? text QuyCach
		// ******************************************************************************
		textQuyCach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ph??m t???t Enter th?? t??m ki???m
				if (e.character == SWT.CR) {
					search();
				}
			}
		});

		// T??m ki???m sau khi enter ??? comboLoaiVatTu
		// ******************************************************************************
		comboLoaiVatTu.addKeyListener(new KeyAdapter() {
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
				ThietBiNew thietbimoi = new ThietBiNew();
				// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
				composite.setEnabled(false);
				thietbimoi.setLanguage(ngonngu);
				thietbimoi.open();
				composite.setEnabled(true);
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
						ThietBiEdit suathietbi = new ThietBiEdit();
						// m??? c???a s??? m???i th?? c???a s??? c?? kh??ng cho thao t??c
						composite.setEnabled(false);
						int loaivattu = -1;
						if (itemsua[0].getText(4).equalsIgnoreCase("Ti??u hao")) {
							loaivattu = 1;
						} else {
							loaivattu = 0;
						}
						suathietbi.setLanguage(ngonngu);
						suathietbi.createContents();
						suathietbi.setDataEdit(itemsua[0].getText(1), itemsua[0].getText(2), itemsua[0].getText(3),
								loaivattu);
						suathietbi.open();
						composite.setEnabled(true);
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
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
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
					connect.setStatement();
					try {
						String delete = "";
						for (int i = 0; i < mathietbixoa.length; i++) {
							delete = delete + "\n" + "DELETE FROM [dbo].[IT_ThietBi] WHERE [MaThietBi]='"
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
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Th??ng b??o l???i");
						thongbao.setMessage("L???i k???t n???i!\n" + se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Connect error!\n" + se.toString());
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
		tbcQuyCach.addListener(SWT.Selection, sort.sortListenerCode);
		tbcLoaiVatTu.addListener(SWT.Selection, sort.sortListenerCode);

	}
}
