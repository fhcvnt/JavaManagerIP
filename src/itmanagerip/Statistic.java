package itmanagerip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class Statistic {

	protected Shell shell;
	private Composite compositeshell;
	private ConnectSQL connect;
	private Table table;

	public static void main(String[] args) {
		try {
			Statistic window = new Statistic();
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
		shell.setImage(SWTResourceManager.getImage(Statistic.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1576, 667);
		shell.setText("Statistic");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setMaximized(true);

		compositeshell = new Composite(shell, SWT.NONE);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		Button btncheckDepartment = new Button(composite, SWT.CHECK);
		btncheckDepartment.setSelection(true);
		btncheckDepartment.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		btncheckDepartment.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btncheckDepartment.setBounds(52, 20, 147, 30);
		btncheckDepartment.setText("Department");

		Button btncheckBuilding = new Button(composite, SWT.CHECK);
		btncheckBuilding.setText("Building");
		btncheckBuilding.setSelection(true);
		btncheckBuilding.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btncheckBuilding.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btncheckBuilding.setBounds(227, 20, 132, 30);

		Button btncheckIP = new Button(composite, SWT.CHECK);
		btncheckIP.setSelection(true);
		btncheckIP.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btncheckIP.setText("IP");
		btncheckIP.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btncheckIP.setBounds(377, 20, 56, 30);

		Button btncheckMachinetype = new Button(composite, SWT.CHECK);
		btncheckMachinetype.setSelection(true);
		btncheckMachinetype.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btncheckMachinetype.setText("Machine Type");
		btncheckMachinetype.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btncheckMachinetype.setBounds(454, 20, 161, 30);

		Button btncheckOperatorsystem = new Button(composite, SWT.CHECK);
		btncheckOperatorsystem.setText("Operator System");
		btncheckOperatorsystem.setSelection(true);
		btncheckOperatorsystem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btncheckOperatorsystem.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btncheckOperatorsystem.setBounds(629, 20, 207, 30);

		Button btncheckOffice = new Button(composite, SWT.CHECK);
		btncheckOffice.setText("Office");
		btncheckOffice.setSelection(true);
		btncheckOffice.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		btncheckOffice.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btncheckOffice.setBounds(859, 20, 98, 30);

		Button btnStatistic = new Button(composite, SWT.NONE);
		btnStatistic.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnStatistic.setImage(SWTResourceManager.getImage(Statistic.class, "/itmanagerip/Icon/button/char30.png"));
		btnStatistic.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnStatistic.setBounds(1008, 18, 161, 35);
		btnStatistic.setText("Statistic");

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		table.setBounds(10, 65, 1900, 930);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcNumber = new TableColumn(table, SWT.NONE);
		tbcNumber.setWidth(83);
		tbcNumber.setText("Number");

		TableColumn tbcDepartment = new TableColumn(table, SWT.NONE);
		tbcDepartment.setWidth(184);
		tbcDepartment.setText("Department");

		TableColumn tbcCountdepartment = new TableColumn(table, SWT.NONE);
		tbcCountdepartment.setWidth(111);
		tbcCountdepartment.setText("Count");

		TableColumn tbcBuilding = new TableColumn(table, SWT.NONE);
		tbcBuilding.setWidth(111);
		tbcBuilding.setText("Building");

		TableColumn tbcCountbuilding = new TableColumn(table, SWT.NONE);
		tbcCountbuilding.setWidth(111);
		tbcCountbuilding.setText("Count");

		TableColumn tbcIP = new TableColumn(table, SWT.NONE);
		tbcIP.setWidth(141);
		tbcIP.setText("IP Class");

		TableColumn tbcCountIP = new TableColumn(table, SWT.NONE);
		tbcCountIP.setWidth(118);
		tbcCountIP.setText("Count");

		TableColumn tbclMachinetype = new TableColumn(table, SWT.NONE);
		tbclMachinetype.setWidth(158);
		tbclMachinetype.setText("Machine Type");

		TableColumn tbclCountMachine = new TableColumn(table, SWT.NONE);
		tbclCountMachine.setWidth(114);
		tbclCountMachine.setText("Count");

		TableColumn tbcOperatorsystem = new TableColumn(table, SWT.NONE);
		tbcOperatorsystem.setWidth(155);
		tbcOperatorsystem.setText("Operator System");

		TableColumn tbcCountoperatorsystem = new TableColumn(table, SWT.NONE);
		tbcCountoperatorsystem.setWidth(114);
		tbcCountoperatorsystem.setText("Count");

		TableColumn tbcOffice = new TableColumn(table, SWT.NONE);
		tbcOffice.setWidth(114);
		tbcOffice.setText("Office");

		TableColumn tbcCountoffice = new TableColumn(table, SWT.NONE);
		tbcCountoffice.setWidth(114);
		tbcCountoffice.setText("Count");

		// language
		// **************************************************************************************************************************************************
		if (ngonngu == 0) {
			btncheckDepartment.setText("Đơn Vị");
			btncheckBuilding.setText("Tòa Nhà");
			btncheckIP.setText("IP");
			btncheckMachinetype.setText("Loại Máy");
			btncheckOperatorsystem.setText("Hệ Điề Hành");
			btncheckOffice.setText("Office");
			btnStatistic.setText("Thống Kê");
			tbcNumber.setText("STT");
			tbcDepartment.setText("Đơn Vị");
			tbcCountdepartment.setText("Số Lượng");
			tbcBuilding.setText("Tòa nhà");
			tbcCountbuilding.setText("Số Lượng");
			tbcIP.setText("Lớp IP");
			tbcCountIP.setText("Số Lượng");
			tbclMachinetype.setText("Loại Máy");
			tbclCountMachine.setText("Số Lượng");
			tbcOperatorsystem.setText("Hệ Điều Hành");
			tbcCountoperatorsystem.setText("Số Lượng");
			tbcOffice.setText("Office");
			tbcCountoffice.setText("Số Lượng");
		} else {
			btncheckDepartment.setText("Department");
			btncheckBuilding.setText("Building");
			btncheckIP.setText("IP");
			btncheckMachinetype.setText("Machine Type");
			btncheckOperatorsystem.setText("Operator System");
			btncheckOffice.setText("Office");
			btnStatistic.setText("Statistic");
			tbcNumber.setText("Number");
			tbcDepartment.setText("Department");
			tbcCountdepartment.setText("Count");
			tbcBuilding.setText("Building");
			tbcCountbuilding.setText("Count");
			tbcIP.setText("IP Class");
			tbcCountIP.setText("Count");
			tbclMachinetype.setText("Machine Type");
			tbclCountMachine.setText("Count");
			tbcOperatorsystem.setText("Operator System");
			tbcCountoperatorsystem.setText("Count");
			tbcOffice.setText("Office");
			tbcCountoffice.setText("Count");
		}

		// button Statistic
		// **************************************************************************************************************************************************
		btnStatistic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					connect = new ConnectSQL();
					connect.setConnection();
					connect.setStatement();

					table.removeAll();
					ArrayList<ArrayList<String>> dsdepartment = new ArrayList<ArrayList<String>>();
					int countdong = 0;
					int dem = 0;
					int countbuilding = 0;
					int countdepartment = 0;
					int countip = 0;
					int countmachine = 0;
					int countoperatorsystem = 0;
					int countoffice = 0;
					int countrowmax = 0;

					// tinh tong so luong IP da dung
					String total = "";
					String selecttotal = "SELECT COUNT(*) AS TOTAL FROM DanhSachIP";
					ResultSet ketqua = connect.getStatement().executeQuery(selecttotal);
					while (ketqua.next()) {
						total = ketqua.getString(1);
					}
					ketqua.close();
					connect.closeStatement();

					// Department
					if (btncheckDepartment.getSelection()) {
						tbcDepartment.setWidth(190);
						tbcCountdepartment.setWidth(90);
						tbcDepartment.setResizable(true);
						tbcCountdepartment.setResizable(true);

						connect.setStatement();
						String select = "SELECT DISTINCT DonVi.DonVi,(SELECT COUNT(ds.MaDonVi) FROM DanhSachIP AS ds WHERE ds.MaDonVi=DanhSachIP.MaDonVi) FROM DanhSachIP,DonVi WHERE DanhSachIP.MaDonVi=DonVi.MaDonVi ORDER BY DonVi.DonVi ASC";
						ResultSet result = connect.getStatement().executeQuery(select);

						while (result.next()) {
							ArrayList<String> currentlist = new ArrayList<String>();
							currentlist.add(result.getString(1));
							currentlist.add(result.getString(2));
							dsdepartment.add(currentlist);
							dem++;
						}
						countdong = dem + 1;
						countdepartment = countdong;
						dem = 0;
						result.close();
						connect.setStatement();
						ArrayList<String> currentlist = new ArrayList<String>();
						currentlist.add("Total");
						currentlist.add(total);
						dsdepartment.add(currentlist);
					} else {
						tbcDepartment.setWidth(0);
						tbcDepartment.setResizable(false);
						tbcCountdepartment.setWidth(0);
						tbcCountdepartment.setResizable(false);
					}
					if (countdong > countrowmax) {
						countrowmax = countdong;
					}

					// Building
					ArrayList<ArrayList<String>> dsbuilding = new ArrayList<ArrayList<String>>();
					if (btncheckBuilding.getSelection()) {
						tbcBuilding.setWidth(190);
						tbcCountbuilding.setWidth(90);
						tbcBuilding.setResizable(true);
						tbcCountbuilding.setResizable(true);

						connect.setStatement();
						String select = "SELECT DISTINCT Building.Building,(SELECT COUNT(ds.MaBuilding) FROM DanhSachIP AS ds WHERE ds.MaBuilding=DanhSachIP.MaBuilding) FROM DanhSachIP,Building WHERE DanhSachIP.MaBuilding=Building.MaBuilding ORDER BY Building.Building ASC";
						ResultSet result = connect.getStatement().executeQuery(select);

						while (result.next()) {
							ArrayList<String> currentlist = new ArrayList<String>();
							currentlist.add(result.getString(1));
							currentlist.add(result.getString(2));
							dsbuilding.add(currentlist);
							dem++;
						}
						countdong = dem + 1;
						countbuilding = countdong;
						dem = 0;
						result.close();
						connect.setStatement();
						ArrayList<String> currentlist = new ArrayList<String>();
						currentlist.add("Total");
						currentlist.add(total);
						dsbuilding.add(currentlist);
					} else {
						tbcBuilding.setWidth(0);
						tbcBuilding.setResizable(false);
						tbcCountbuilding.setWidth(0);
						tbcCountbuilding.setResizable(false);
					}

					if (countdong > countrowmax) {
						countrowmax = countdong;
					}

					// Thong ke IP
					ArrayList<ArrayList<String>> dsip = new ArrayList<ArrayList<String>>();
					if (btncheckIP.getSelection()) {
						tbcIP.setWidth(118);
						tbcCountIP.setWidth(90);
						tbcIP.setResizable(true);
						tbcCountIP.setResizable(true);
						try {
							String bangtam = "SELECT CAST(SUBSTRING(DanhSachIP.IP,9,(SELECT CHARINDEX('.',DanhSachIP.IP,9)-9)) AS INT) AS LopIP INTO ##LopIP FROM DanhSachIP";
							connect.setStatement();
							connect.execUpdateQuery(bangtam);
							String selectbangtam = "SELECT DISTINCT ##LopIP.LopIP,(SELECT COUNT(*) FROM ##LopIP AS LopIP2 WHERE LopIP2.LopIP=##LopIP.LopIP) FROM ##LopIP ORDER BY ##LopIP.LopIP DESC";
							connect.closeStatement();
							connect.setStatement();
							ResultSet resultbangtam = connect.getStatement().executeQuery(selectbangtam);

							while (resultbangtam.next()) {
								ArrayList<String> currentlist = new ArrayList<String>();
								currentlist.add(resultbangtam.getString(1));
								currentlist.add(resultbangtam.getString(2));
								dsip.add(currentlist);
								dem++;
							}
							countip = dem + 1;
							countdong = countdong < countip ? countip : countdong;
							dem = 0;
							resultbangtam.close();

							connect.closeStatement();
							ArrayList<String> currentlistip = new ArrayList<String>();
							currentlistip.add("Total");
							currentlistip.add(total);
							dsip.add(currentlistip);
						} catch (SQLException sqlex) {

						}
					} else {
						tbcIP.setWidth(0);
						tbcIP.setResizable(false);
						tbcCountIP.setWidth(0);
						tbcCountIP.setResizable(false);
					}

					if (countdong > countrowmax) {
						countrowmax = countdong;
					}

					// thong ke Machine Type
					ArrayList<ArrayList<String>> dsmachine = new ArrayList<ArrayList<String>>();
					if (btncheckMachinetype.getSelection()) {
						tbclMachinetype.setWidth(185);
						tbclCountMachine.setWidth(90);
						tbclMachinetype.setResizable(true);
						tbclCountMachine.setResizable(true);

						try {
							String selectloaimay = "SELECT DISTINCT LoaiMay.LoaiMay,(SELECT COUNT(*) FROM DanhSachIP AS DS WHERE DS.MaLoaiMay=DanhSachIP.MaLoaiMay) FROM DanhSachIP,LoaiMay WHERE DanhSachIP.MaLoaiMay=LoaiMay.MaLoaiMay";
							connect.setStatement();
							ResultSet kqloaimay = connect.getStatement().executeQuery(selectloaimay);

							while (kqloaimay.next()) {
								ArrayList<String> currentlist = new ArrayList<String>();
								currentlist.add(kqloaimay.getString(1));
								currentlist.add(kqloaimay.getString(2));
								dsmachine.add(currentlist);
								dem++;
							}
							countmachine = dem + 1;
							countdong = countdong < countmachine ? countmachine : countdong;
							dem = 0;
							kqloaimay.close();
							connect.closeStatement();

							ArrayList<String> currentlist = new ArrayList<String>();
							currentlist.add("Total");
							currentlist.add(total);
							dsmachine.add(currentlist);

						} catch (SQLException sqlee) {

						}
					} else {
						tbclMachinetype.setWidth(0);
						tbclMachinetype.setResizable(false);
						tbclCountMachine.setWidth(0);
						tbclCountMachine.setResizable(false);
					}

					if (countdong > countrowmax) {
						countrowmax = countdong;
					}

					// Operator System
					ArrayList<ArrayList<String>> dsoperatorsytem = new ArrayList<ArrayList<String>>();
					if (btncheckOperatorsystem.getSelection()) {
						tbcOperatorsystem.setWidth(185);
						tbcCountoperatorsystem.setWidth(90);
						tbcOperatorsystem.setResizable(true);
						tbcCountoperatorsystem.setResizable(true);

						connect.setStatement();
						String select = "SELECT DISTINCT HeDieuHanh.HeDieuHanh,(SELECT COUNT(ds.MaHeDieuHanh) FROM DanhSachIP AS ds WHERE ds.MaHeDieuHanh=DanhSachIP.MaHeDieuHanh) FROM DanhSachIP,HeDieuHanh WHERE DanhSachIP.MaHeDieuHanh=HeDieuHanh.MaHeDieuHanh ORDER BY HeDieuHanh.HeDieuHanh ASC";
						ResultSet result = connect.getStatement().executeQuery(select);

						while (result.next()) {
							ArrayList<String> currentlist = new ArrayList<String>();
							currentlist.add(result.getString(1));
							currentlist.add(result.getString(2));
							dsoperatorsytem.add(currentlist);
							dem++;
						}
						countdong = dem + 1;
						countoperatorsystem = countdong;
						dem = 0;
						result.close();
						connect.closeStatement();
						ArrayList<String> currentlist = new ArrayList<String>();
						currentlist.add("Total");
						currentlist.add(total);
						dsoperatorsytem.add(currentlist);
					} else {
						tbcOperatorsystem.setWidth(0);
						tbcOperatorsystem.setResizable(false);
						tbcCountoperatorsystem.setWidth(0);
						tbcCountoperatorsystem.setResizable(false);
					}

					if (countdong > countrowmax) {
						countrowmax = countdong;
					}

					// Office
					ArrayList<ArrayList<String>> dsoffice = new ArrayList<ArrayList<String>>();
					if (btncheckOffice.getSelection()) {
						tbcOffice.setWidth(180);
						tbcCountoffice.setWidth(90);
						tbcOffice.setResizable(true);
						tbcCountoffice.setResizable(true);

						connect.setStatement();
						String select = "SELECT DISTINCT Office.Office,(SELECT COUNT(ds.MaOffice) FROM DanhSachIP AS ds WHERE ds.MaOffice=DanhSachIP.MaOffice) FROM DanhSachIP,Office WHERE DanhSachIP.MaOffice=Office.MaOffice ORDER BY Office.Office ASC";
						ResultSet result = connect.getStatement().executeQuery(select);

						while (result.next()) {
							ArrayList<String> currentlist = new ArrayList<String>();
							currentlist.add(result.getString(1));
							currentlist.add(result.getString(2));
							dsoffice.add(currentlist);
							dem++;
						}
						countdong = dem + 1;
						countoffice = countdong;
						dem = 0;
						result.close();
						connect.closeStatement();
						ArrayList<String> currentlist = new ArrayList<String>();
						currentlist.add("Total");
						currentlist.add(total);
						dsoffice.add(currentlist);
					} else {
						tbcOffice.setWidth(0);
						tbcOffice.setResizable(false);
						tbcCountoffice.setWidth(0);
						tbcCountoffice.setResizable(false);
					}

					if (countdong > countrowmax) {
						countrowmax = countdong;
					}

					// Thống kê
					String[][] thongke = new String[countrowmax][12];
					for (int i = 0; i < countrowmax; i++) {
						for (int j = 0; j < 12; j++) {
							thongke[i][j] = "";
						}
					}
					// Department
					int dong = 0;
					try {
						if (btncheckDepartment.getSelection()) {
							for (ArrayList<String> itemrow : dsdepartment) {
								thongke[dong][0] = itemrow.get(0);
								thongke[dong][1] = itemrow.get(1);
								dong++;
							}
						}
					} catch (Exception arr) {
					}
					// Building
					dong = 0;
					try {
						if (btncheckBuilding.getSelection()) {
							for (ArrayList<String> itemrow : dsbuilding) {
								thongke[dong][2] = itemrow.get(0);
								thongke[dong][3] = itemrow.get(1);
								dong++;
							}
						}
					} catch (Exception arr) {
					}

					// IP
					dong = 0;
					try {
						if (btncheckIP.getSelection()) {
							for (ArrayList<String> itemrow : dsip) {
								thongke[dong][4] = itemrow.get(0);
								thongke[dong][5] = itemrow.get(1);
								dong++;
							}
						}
					} catch (Exception arr) {
					}
					// Machine Type
					dong = 0;
					try {
						if (btncheckMachinetype.getSelection()) {
							for (ArrayList<String> itemrow : dsmachine) {
								thongke[dong][6] = itemrow.get(0);
								thongke[dong][7] = itemrow.get(1);
								dong++;
							}
						}
					} catch (Exception arr) {
					}
					// Operator System
					dong = 0;
					try {
						if (btncheckOperatorsystem.getSelection()) {
							for (ArrayList<String> itemrow : dsoperatorsytem) {
								thongke[dong][8] = itemrow.get(0);
								thongke[dong][9] = itemrow.get(1);
								dong++;
							}
						}
					} catch (Exception arr) {
					}

					// Office
					dong = 0;
					try {
						if (btncheckOffice.getSelection()) {
							for (ArrayList<String> itemrow : dsoffice) {
								thongke[dong][10] = itemrow.get(0);
								thongke[dong][11] = itemrow.get(1);
								dong++;
							}
						}
					} catch (Exception arr) {
					}

					// hien thi len table

					try {
						for (int i = 0; i < countrowmax; i++) {
							TableItem item = new TableItem(table, SWT.NONE);
							// Cot STT
							item.setText(0, (i + 1) + "");
							item.setBackground(0, Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
							try {
								if (btncheckDepartment.getSelection()) {
									item.setText(1, thongke[i][0]);
									item.setText(2, thongke[i][1]);
									if (i < countdepartment - 1) {
										item.setBackground(1, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
									}
									if (i == countdepartment - 1) {
										item.setBackground(1, Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
										item.setBackground(2, Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
									}
								}
							} catch (Exception ae) {

							}
							try {
								if (btncheckBuilding.getSelection()) {
									item.setText(3, thongke[i][2]);
									item.setText(4, thongke[i][3]);
									if (i < countbuilding - 1) {
										item.setBackground(3, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
									}
									if (i == countbuilding - 1) {
										item.setBackground(3, Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
										item.setBackground(4, Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
									}
								}
							} catch (Exception ae) {
							}
							try {
								if (btncheckIP.getSelection()) {
									item.setText(5, thongke[i][4]);
									item.setText(6, thongke[i][5]);
									if (i < countip - 1) {
										item.setBackground(5, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
									}
									if (i == countip - 1) {
										item.setBackground(5, Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
										item.setBackground(6, Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
									}
								}
							} catch (Exception ae) {
							}
							try {
								if (btncheckMachinetype.getSelection()) {
									item.setText(7, thongke[i][6]);
									item.setText(8, thongke[i][7]);
									if (i < countmachine - 1) {
										item.setBackground(7, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
									}
									if (i == countmachine - 1) {
										item.setBackground(7, Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
										item.setBackground(8, Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
									}
								}
							} catch (Exception ae) {
							}
							try {
								if (btncheckOperatorsystem.getSelection()) {
									item.setText(9, thongke[i][8]);
									item.setText(10, thongke[i][9]);
									if (i < countoperatorsystem - 1) {
										item.setBackground(9, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
									}
									if (i == countoperatorsystem - 1) {
										item.setBackground(9, Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
										item.setBackground(10, Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
									}
								}
							} catch (Exception ae) {
							}
							try {
								if (btncheckOffice.getSelection()) {
									item.setText(11, thongke[i][10]);
									item.setText(12, thongke[i][11]);
									if (i < countoffice - 1) {
										item.setBackground(11, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
									}
									if (i == countoffice - 1) {
										item.setBackground(11, Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
										item.setBackground(12, Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
									}
								}
							} catch (Exception ae) {
							}
						}
					} catch (Exception ae) {

					}

				} catch (SQLException se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
					} else {
						thongbao.setText("Notification");
						thongbao.setMessage("Connect error!\n" + se.toString());
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

					} catch (Exception se2) {
					}
				}
			}
		});
	}

	// Hien trong Ctabfolder
	// ************************************************************************************************************************************************************
	protected void showTabFolder(CTabFolder tabfolder, Shell shell, int ngonngu) {
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);
		if (ngonngu == 0) {
			itemtab.setText("Thống kê");
		} else {
			itemtab.setText("Statistic");
		}

		Composite composite = new Composite(tabfolder, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		itemtab.setControl(composite);

		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 175);
	}
}
