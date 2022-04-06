package it;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class ThietBiChoMuonAdd {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textTenThietBi;
	private CCombo comboTrangThai;
	private CCombo comboLoaiThietBi;
	private int ngonngu = 1;
	private Text textMaThietBi;
	private Text textGhiChu;
	private boolean isaddsuccess = false;

	public static void main(String[] args) {
		try {
			ThietBiChoMuonAdd window = new ThietBiChoMuonAdd();
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
		shell.setImage(SWTResourceManager.getImage(ThietBiChoMuonAdd.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(577, 501);
		shell.setText("Add device");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbMaThietBi = new CLabel(composite, SWT.RIGHT);
		lbMaThietBi.setText("Mã thiết bị");
		lbMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMaThietBi.setBounds(10, 30, 135, 30);

		textMaThietBi = new Text(composite, SWT.BORDER);
		textMaThietBi.setTextLimit(12);
		textMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textMaThietBi.setBounds(151, 30, 358, 30);

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setBounds(10, 79, 135, 30);
		lbTenThietBi.setText("Tên thiết bị");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setBounds(151, 80, 358, 30);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenThietBi.setTextLimit(40);

		CLabel lbTrangThai = new CLabel(composite, SWT.RIGHT);
		lbTrangThai.setBounds(10, 130, 135, 30);
		lbTrangThai.setText("Trạng thái");
		lbTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboTrangThai = new CCombo(composite, SWT.BORDER);
		comboTrangThai.setItems(new String[] { "Đã hư", "Bình thường", "Bị mất" });
		comboTrangThai.setBounds(151, 130, 358, 30);
		comboTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTrangThai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboTrangThai.setTextLimit(20);
		comboTrangThai.select(1);

		CLabel lbLoaiThietBi = new CLabel(composite, SWT.RIGHT);
		lbLoaiThietBi.setText("Loại thiết bị");
		lbLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoaiThietBi.setBounds(10, 179, 135, 30);

		comboLoaiThietBi = new CCombo(composite, SWT.BORDER);
		comboLoaiThietBi.setTextLimit(20);
		comboLoaiThietBi.setItems(new String[] {"Linh kiện", "Máy ảnh", "Máy ảnh Yeezy", "Phòng họp", "Băng đeo tay", "Băng đeo tay Yeezy", "Laptop"});
		comboLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoaiThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboLoaiThietBi.setBounds(151, 180, 358, 30);
		comboLoaiThietBi.select(0);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi chú");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(10, 230, 135, 30);

		textGhiChu = new Text(composite, SWT.BORDER | SWT.MULTI);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(151, 230, 358, 146);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThietBiChoMuonAdd.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(213, 396, 119, 35);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBiChoMuonAdd.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(338, 396, 120, 35);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			shell.setText("Thêm thiết bị");
			lbMaThietBi.setText("Mã thiết bị");
			lbTenThietBi.setText("Tên thiết bị");
			lbTrangThai.setText("Trạng thái");
			lbLoaiThietBi.setText("Loại thiết bị");
			lbGhiChu.setText("Ghi chú");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
		} else {
			shell.setText("Add device");
			lbMaThietBi.setText("Device id");
			lbTenThietBi.setText("Device name");
			lbTrangThai.setText("Status");
			lbLoaiThietBi.setText("Device type");
			lbGhiChu.setText("Note");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
		}

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					if (!textMaThietBi.getText().isEmpty()) {
						if (!textTenThietBi.getText().isEmpty()) {
							if (comboTrangThai.getSelectionIndex() >= 0
									&& comboTrangThai.getSelectionIndex() < comboTrangThai.getItemCount()) {
								if (comboLoaiThietBi.getSelectionIndex() >= 0
										&& comboLoaiThietBi.getSelectionIndex() < comboLoaiThietBi.getItemCount()) {
									try {
										String insert = "INSERT INTO [dbo].[IT_ThietBiChoMuon] ([MaThietBi],[TenThietBi],[TrangThai],[LoaiThietBi],[GhiChu]) VALUES ('"
												+ textMaThietBi.getText() + "',N'" + textTenThietBi.getText() + "' ,"
												+ comboTrangThai.getSelectionIndex() + ","
												+ comboLoaiThietBi.getSelectionIndex() + ",N'" + textGhiChu.getText()
												+ "')";

										int result = connect.execUpdateQuery(insert);
										if (result > 0) {
											isaddsuccess = true;
											textMaThietBi.setText("");
											textTenThietBi.setText("");
											textGhiChu.setText("");
										}
										connect.closeStatement();
									} catch (Exception ex) {
									}
								} else {
									MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
									if (ngonngu == 0) {
										thongbao.setText("Thông báo lỗi");
										thongbao.setMessage("Chọn loại thiết bị sai!");
									} else {
										thongbao.setText("Notice error");
										thongbao.setMessage("Choose the wrong device type!");
									}
									thongbao.open();
								}
							} else {
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Thông báo lỗi");
									thongbao.setMessage("Chọn trạng thái sai!");
								} else {
									thongbao.setText("Notice error");
									thongbao.setMessage("Choose the wrong status!");
								}
								thongbao.open();
							}
						} else {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo lỗi");
								thongbao.setMessage("Tên thiết bị rỗng!");
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage("Device name is empty!");
							}
							thongbao.open();
						}
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Mã thiết bị rỗng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Device id is empty!");
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
	public void setLanguage(int language) {
		ngonngu = language;
	}

	// kiểm tra xem có thêm thành công ít nhất 1 thiết bị hay không
	public boolean isAddSuccess() {
		return isaddsuccess;
	}
}
