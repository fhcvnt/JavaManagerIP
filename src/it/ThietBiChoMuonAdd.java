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
		lbMaThietBi.setText("M?? thi???t b???");
		lbMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMaThietBi.setBounds(10, 30, 135, 30);

		textMaThietBi = new Text(composite, SWT.BORDER);
		textMaThietBi.setTextLimit(12);
		textMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textMaThietBi.setBounds(151, 30, 358, 30);

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setBounds(10, 79, 135, 30);
		lbTenThietBi.setText("T??n thi???t b???");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setBounds(151, 80, 358, 30);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenThietBi.setTextLimit(40);

		CLabel lbTrangThai = new CLabel(composite, SWT.RIGHT);
		lbTrangThai.setBounds(10, 130, 135, 30);
		lbTrangThai.setText("Tr???ng th??i");
		lbTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboTrangThai = new CCombo(composite, SWT.BORDER);
		comboTrangThai.setItems(new String[] { "???? h??", "B??nh th?????ng", "B??? m???t" });
		comboTrangThai.setBounds(151, 130, 358, 30);
		comboTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTrangThai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboTrangThai.setTextLimit(20);
		comboTrangThai.select(1);

		CLabel lbLoaiThietBi = new CLabel(composite, SWT.RIGHT);
		lbLoaiThietBi.setText("Lo???i thi???t b???");
		lbLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoaiThietBi.setBounds(10, 179, 135, 30);

		comboLoaiThietBi = new CCombo(composite, SWT.BORDER);
		comboLoaiThietBi.setTextLimit(20);
		comboLoaiThietBi.setItems(new String[] {"Linh ki???n", "M??y ???nh", "M??y ???nh Yeezy", "Ph??ng h???p", "B??ng ??eo tay", "B??ng ??eo tay Yeezy", "Laptop"});
		comboLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoaiThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboLoaiThietBi.setBounds(151, 180, 358, 30);
		comboLoaiThietBi.select(0);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi ch??");
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
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBiChoMuonAdd.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(338, 396, 120, 35);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			shell.setText("Th??m thi???t b???");
			lbMaThietBi.setText("M?? thi???t b???");
			lbTenThietBi.setText("T??n thi???t b???");
			lbTrangThai.setText("Tr???ng th??i");
			lbLoaiThietBi.setText("Lo???i thi???t b???");
			lbGhiChu.setText("Ghi ch??");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");
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

		// L??u--------------------------------------------------------------------------------------------------------------
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
										thongbao.setText("Th??ng b??o l???i");
										thongbao.setMessage("Ch???n lo???i thi???t b??? sai!");
									} else {
										thongbao.setText("Notice error");
										thongbao.setMessage("Choose the wrong device type!");
									}
									thongbao.open();
								}
							} else {
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Th??ng b??o l???i");
									thongbao.setMessage("Ch???n tr???ng th??i sai!");
								} else {
									thongbao.setText("Notice error");
									thongbao.setMessage("Choose the wrong status!");
								}
								thongbao.open();
							}
						} else {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Th??ng b??o l???i");
								thongbao.setMessage("T??n thi???t b??? r???ng!");
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage("Device name is empty!");
							}
							thongbao.open();
						}
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Th??ng b??o l???i");
							thongbao.setMessage("M?? thi???t b??? r???ng!");
						} else {
							thongbao.setText("Notice error");
							thongbao.setMessage("Device id is empty!");
						}
						thongbao.open();
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
				shell.dispose();
			}
		});
	}

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language) {
		ngonngu = language;
	}

	// ki???m tra xem c?? th??m th??nh c??ng ??t nh???t 1 thi???t b??? hay kh??ng
	public boolean isAddSuccess() {
		return isaddsuccess;
	}
}
