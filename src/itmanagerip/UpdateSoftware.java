package itmanagerip;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
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

import sql.ConnectSQL;

public class UpdateSoftware {

	protected Shell shell;
	private Composite compositeshell;
	private Text textTentep;
	private Text textPhienban;
	private Table table;
	// Chuỗi kết nối
	private ConnectSQL connect;
	private String filename = "";
	private String fileid = "";
	private int vitrixoa = -1;
	private Text textID;

	public static void main(String[] args) {
		try {
			UpdateSoftware window = new UpdateSoftware();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		shell.setImage(SWTResourceManager.getImage(UpdateSoftware.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1429, 708);
		shell.setMaximized(true);
		shell.setText("Create file update");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		compositeshell = new Composite(shell, SWT.NONE);
	}

	protected void createContents(Composite composite, Shell shell, int ngonngu) {
		CLabel lbID = new CLabel(composite, SWT.NONE);
		lbID.setAlignment(SWT.RIGHT);
		lbID.setText("ID");
		lbID.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbID.setBounds(21, 22, 106, 35);

		textID = new Text(composite, SWT.BORDER);
		textID.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textID.setBounds(152, 22, 158, 35);
		textID.setTextLimit(6);

		CLabel lbTentep = new CLabel(composite, SWT.NONE);
		lbTentep.setAlignment(SWT.RIGHT);
		lbTentep.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbTentep.setBounds(330, 22, 122, 35);
		lbTentep.setText("Tên Tệp");

		textTentep = new Text(composite, SWT.BORDER);
		textTentep.setEnabled(false);
		textTentep.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textTentep.setBounds(471, 22, 324, 35);

		Button btnChontep = new Button(composite, SWT.NONE);
		btnChontep.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnChontep.setImage(SWTResourceManager.getImage(UpdateSoftware.class, "/itmanagerip/Icon/button/upload.png"));
		btnChontep.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.BOLD));
		btnChontep.setBounds(808, 22, 197, 35);
		btnChontep.setText("Chọn Tệp");

		CLabel lbHedieuhanh = new CLabel(composite, SWT.NONE);
		lbHedieuhanh.setAlignment(SWT.RIGHT);
		lbHedieuhanh.setText("Hệ Điều Hành");
		lbHedieuhanh.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbHedieuhanh.setBounds(10, 74, 158, 35);

		CCombo comboHedieuhanh = new CCombo(composite, SWT.BORDER);
		comboHedieuhanh.setItems(new String[] { "Window 32", "Window 64", "Linux 32", "Linux 64" });
		comboHedieuhanh.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		comboHedieuhanh.setBounds(192, 74, 182, 35);
		comboHedieuhanh.select(1);

		CLabel lbPhienban = new CLabel(composite, SWT.NONE);
		lbPhienban.setAlignment(SWT.RIGHT);
		lbPhienban.setText("Phiên Bản");
		lbPhienban.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lbPhienban.setBounds(392, 74, 112, 35);

		textPhienban = new Text(composite, SWT.BORDER);
		textPhienban.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textPhienban.setBounds(529, 74, 259, 35);
		textPhienban.setTextLimit(20);

		Button btnThem = new Button(composite, SWT.NONE);
		btnThem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnThem.setImage(SWTResourceManager.getImage(UpdateSoftware.class, "/itmanagerip/Icon/button/add30.png"));
		btnThem.setText("Thêm");
		btnThem.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.BOLD));
		btnThem.setBounds(802, 74, 136, 35);

		Button btnXoa = new Button(composite, SWT.NONE);
		btnXoa.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnXoa.setText("Xóa");
		btnXoa.setImage(SWTResourceManager.getImage(UpdateSoftware.class, "/itmanagerip/Icon/button/delete.png"));
		btnXoa.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.BOLD));
		btnXoa.setBounds(944, 74, 127, 35);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setText("Lưu");
		btnLuu.setImage(SWTResourceManager.getImage(UpdateSoftware.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnLuu.setBounds(1077, 74, 106, 35);

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setText("Hủy");
		btnHuy.setImage(SWTResourceManager.getImage(UpdateSoftware.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		btnHuy.setBounds(1189, 74, 116, 35);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(255, 165, 0));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		table.setBounds(10, 117, 1364, 542);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 186);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tbcNumber = new TableColumn(table, SWT.NONE);
		tbcNumber.setWidth(109);
		tbcNumber.setText("Number");

		TableColumn tbcID = new TableColumn(table, SWT.NONE);
		tbcID.setWidth(156);
		tbcID.setText("ID");

		TableColumn tbcTentep = new TableColumn(table, SWT.NONE);
		tbcTentep.setWidth(317);
		tbcTentep.setText("Tên Tệp");

		TableColumn tbclHedieuhanh = new TableColumn(table, SWT.NONE);
		tbclHedieuhanh.setWidth(220);
		tbclHedieuhanh.setText("Hệ Điều Hành");

		TableColumn tbclPhienban = new TableColumn(table, SWT.NONE);
		tbclPhienban.setWidth(234);
		tbclPhienban.setText("Phiên Bản");

		TableColumn tbclNgaycapnhat = new TableColumn(table, SWT.NONE);
		tbclNgaycapnhat.setWidth(203);
		tbclNgaycapnhat.setText("Ngày Cập Nhật");

		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		// ngôn ngữ
		// *******************************************************************************************************************************************
		if (ngonngu == 0) {
			lbID.setText("ID");
			lbTentep.setText("Tên Tệp");
			btnChontep.setText("Chọn Tệp");
			lbHedieuhanh.setText("Hệ Điều Hành");
			lbPhienban.setText("Phiên Bản");
			btnThem.setText("Thêm");
			btnXoa.setText("Xóa");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			tbcNumber.setText("STT");
			tbcID.setText("Mã");
			tbcTentep.setText("Tên Tệp");
			tbclHedieuhanh.setText("Hệ Điều Hành");
			tbclPhienban.setText("Phiên Bản");
			tbclNgaycapnhat.setText("Ngày Cập Nhật");
		} else if (ngonngu == 1) {
			lbID.setText("ID");
			lbTentep.setText("File Name");
			btnChontep.setText("Choose File");
			lbHedieuhanh.setText("Operator System");
			lbPhienban.setText("Version");
			btnThem.setText("Add");
			btnXoa.setText("Delete");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			tbcNumber.setText("Number");
			tbcID.setText("ID");
			tbcTentep.setText("File Name");
			tbclHedieuhanh.setText("Operator System");
			tbclPhienban.setText("Version");
			tbclNgaycapnhat.setText("Update Date");
		}

		// Lấy dữ liệu lúc đầu
		// *******************************************************************************************************************************************
		try {
			connect = new ConnectSQL();
			connect.setConnection();
			connect.setStatement();

			// Lấy dữ liệu cho table
			String select = "SELECT FileID,TenFile,HeDieuHanh,PhienBan,ThoiGianCapNhat FROM CapNhatPhanMem ORDER BY ThoiGianCapNhat DESC";
			ResultSet resultthuchien = connect.getStatement().executeQuery(select);
			table.removeAll();
			int stt = 1;
			while (resultthuchien.next()) {
				String ngaycapnhat = resultthuchien.getString(5);
				try {
					ngaycapnhat = ngaycapnhat.substring(8, 10) + "/" + ngaycapnhat.substring(5, 7) + "/"
							+ ngaycapnhat.substring(0, 4);
				} catch (Exception ee) {
					ngaycapnhat = "";
				}
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { stt + "", resultthuchien.getString(1), resultthuchien.getString(2),
						resultthuchien.getString(3), resultthuchien.getString(4), ngaycapnhat });
				stt++;
			}

			resultthuchien.close();

		} catch (Exception se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi");
				thongbao.setMessage(se.toString());
			} else {
				thongbao.setText("Error");
				thongbao.setMessage(se.toString());
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

		// Button Chọn tệp
		// *******************************************************************************************************************************************
		btnChontep.addSelectionListener(new SelectionAdapter() {
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

					textTentep.setText(tenfile.toString());
				}
			}
		});

		// Button Thêm
		// *******************************************************************************************************************************************
		btnThem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!(textTentep.getText().isEmpty() || textPhienban.getText().isEmpty()
						|| comboHedieuhanh.getText().isEmpty() || textID.getText().isEmpty())) {
					try {
						// đọc hết hết một lần
						byte[] content = Files.readAllBytes(Paths.get(filename));
						try {
							connect = new ConnectSQL();
							connect.setConnection();

							String insert = "INSERT INTO CapNhatPhanMem (FileID,TenFile,FileCode,HeDieuHanh,PhienBan,ThoiGianCapNhat) VALUES ('"
									+ textID.getText() + "',N'" + textTentep.getText() + "',?,'"
									+ comboHedieuhanh.getText() + "','" + textPhienban.getText() + "',GETDATE())";

							PreparedStatement stmt = connect.getConnection().prepareStatement(insert);
							stmt.setBytes(1, content);
							int result = stmt.executeUpdate();

							if (result > 0) {
								connect.setStatement();
								// Thêm dữ liệu cho table
								String select = "SELECT FileID,TenFile,HeDieuHanh,PhienBan,ThoiGianCapNhat FROM CapNhatPhanMem ORDER BY ThoiGianCapNhat DESC";
								ResultSet resultthuchien = connect.getStatement().executeQuery(select);
								table.removeAll();

								int stt = 1;
								while (resultthuchien.next()) {
									String ngaycapnhat = resultthuchien.getString(5);
									try {
										ngaycapnhat = ngaycapnhat.substring(8, 10) + "/" + ngaycapnhat.substring(5, 7)
												+ "/" + ngaycapnhat.substring(0, 4);
									} catch (Exception ee) {
										ngaycapnhat = "";
									}
									TableItem item = new TableItem(table, SWT.NONE);
									item.setText(new String[] { stt + "", resultthuchien.getString(1),
											resultthuchien.getString(2), resultthuchien.getString(3),
											resultthuchien.getString(4), ngaycapnhat });
									stt++;
								}

								resultthuchien.close();

								MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Thông báo");
									thongbao.setMessage("Tải tệp lên thành công!");
								} else {
									thongbao.setText("Error");
									thongbao.setMessage("Upload successful!");
								}
								thongbao.open();
								textPhienban.setText("");
								textTentep.setText("");
							}
							stmt.close();

						} catch (Exception se) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo lỗi");
								thongbao.setMessage(se.toString());
							} else {
								thongbao.setText("Error");
								thongbao.setMessage(se.toString());
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
								// nothing we can do
							}
						}

					} catch (Exception ex) {

					}
				}
			}
		});

		// Button Xóa
		// *******************************************************************************************************************************************
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!btnLuu.isVisible()) {
					try {
						TableItem[] itemtable = table.getSelection();
						fileid = itemtable[0].getText(1);
						vitrixoa = table.getSelectionIndex();
						btnLuu.setVisible(true);
						btnHuy.setVisible(true);
						btnThem.setEnabled(false);
						btnChontep.setEnabled(false);
						table.setEnabled(false);
					} catch (Exception ee) {
						fileid = "";
						vitrixoa = -1;
						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						btnThem.setEnabled(true);
						btnChontep.setEnabled(true);
						table.setEnabled(true);
					}
				}
			}
		});

		// Button Lưu
		// *******************************************************************************************************************************************
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnLuu.isVisible()) {
					try {
						connect = new ConnectSQL();
						connect.setConnection();

						String delete = "DELETE FROM CapNhatPhanMem WHERE FileID='" + fileid + "'";
						int resultdelete = connect.execUpdateQuery(delete);
						if (resultdelete > 0) {
							// Xóa 1 dòng trên table
							table.remove(vitrixoa);
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Xóa thành công!");
							} else {
								thongbao.setText("Notification");
								thongbao.setMessage("Delete successful!");
							}
							thongbao.open();
							fileid = "";
							vitrixoa = -1;
							btnLuu.setVisible(false);
							btnHuy.setVisible(false);
							btnThem.setEnabled(true);
							btnChontep.setEnabled(true);
							table.setEnabled(true);
						}

					} catch (Exception se) {
					} finally {
						try {
							if (connect.getStatement() != null) {
								connect.closeStatement();
							}
							if (connect.getConnection() != null) {
								connect.closeConnection();
							}

						} catch (Exception se2) {
							// nothing we can do
						}
					}
				}
			}
		});

		// Button Hủy
		// *******************************************************************************************************************************************
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fileid = "";
				vitrixoa = -1;
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				btnThem.setEnabled(true);
				btnChontep.setEnabled(true);
				table.setEnabled(true);
			}
		});
	}

	// Hien Ctabfolder
	// ***********************************************************************************************************************************
	protected void createContentsTabfolder(CTabFolder tabfolder, Shell shell, String groupname, int ngonngu) {
		CTabItem itemtab = new CTabItem(tabfolder, SWT.CLOSE);

		if (ngonngu == 0) {
			itemtab.setText("Cập nhật");
		} else {
			itemtab.setText("Update");
		}
		Composite composite = new Composite(tabfolder, SWT.NONE);
		itemtab.setControl(composite);
		createContents(composite, shell, ngonngu);
		table.setSize(Display.getDefault().getBounds().width - 20, Display.getDefault().getBounds().height - 230);
	}
}
