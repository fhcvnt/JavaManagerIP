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

public class DanhSachThietBiMayTinhEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textLoaiThietBi;
	private CCombo comboThietBi;
	private int ngonngu = 1;
	private Text textMaThietBi;
	private String mathietbisua;

	public static void main(String[] args) {
		try {
			DanhSachThietBiMayTinhEdit window = new DanhSachThietBiMayTinhEdit();
			window.createContents();
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
		shell.setImage(SWTResourceManager.getImage(DanhSachThietBiMayTinhEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(484, 304);
		shell.setText("Edit type of device");
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
		textMaThietBi.setTextLimit(9);
		textMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textMaThietBi.setBounds(151, 30, 245, 30);

		CLabel lbLoaiThietBi = new CLabel(composite, SWT.RIGHT);
		lbLoaiThietBi.setBounds(10, 79, 135, 30);
		lbLoaiThietBi.setText("Lo???i thi???t b???");
		lbLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textLoaiThietBi = new Text(composite, SWT.BORDER);
		textLoaiThietBi.setBounds(151, 79, 245, 30);
		textLoaiThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textLoaiThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textLoaiThietBi.setTextLimit(30);

		CLabel lbThietBi = new CLabel(composite, SWT.RIGHT);
		lbThietBi.setBounds(10, 130, 135, 30);
		lbThietBi.setText("Thi???t b???");
		lbThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		comboThietBi = new CCombo(composite, SWT.BORDER);
		comboThietBi.setItems(new String[] { "CPU", "Hard Drive", "Mainboard", "Monitor", "Printer", "RAM", "UPS" });
		comboThietBi.setBounds(151, 130, 245, 30);
		comboThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboThietBi.setTextLimit(16);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinhEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(151, 183, 119, 35);
		btnLuu.setText("L??u");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(DanhSachThietBiMayTinhEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(276, 183, 120, 35);
		btnHuy.setText("H???y");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		// ?????t t??n theo ng??n ng???
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("S???a lo???i thi???t b???");
			lbMaThietBi.setText("M?? thi???t b???");
			lbLoaiThietBi.setText("Lo???i thi???t b???");
			lbThietBi.setText("Thi???t b???");
			btnLuu.setText("L??u");
			btnHuy.setText("H???y");

		} else {
			// Tieng Anh
			shell.setText("Edit type of device");
			lbMaThietBi.setText("Device id");
			lbLoaiThietBi.setText("Type of device");
			lbThietBi.setText("Device");
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
						if (!textLoaiThietBi.getText().isEmpty()) {
							if (comboThietBi.getSelectionIndex() >= 0
									&& comboThietBi.getSelectionIndex() < comboThietBi.getItemCount()) {
								try {
									String insert = "UPDATE [dbo].[IT_DanhSachThietBiMayTinh] SET [MaThietBiMayTinh] = '"
											+ textMaThietBi.getText() + "',[LoaiThietBi] = '"
											+ textLoaiThietBi.getText() + "',[TenLoaiThietBi] = '"
											+ comboThietBi.getText() + "' WHERE [MaThietBiMayTinh]='" + mathietbisua
											+ "'";

									int result = connect.execUpdateQuery(insert);
									if (result > 0) {
										MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
										if (ngonngu == 0) {
											thongbao.setText("Th??ng b??o");
											thongbao.setMessage("S???a th??nh c??ng!");
										} else {
											thongbao.setText("Notice error");
											thongbao.setMessage("Edit successful!");
										}
										thongbao.open();
										shell.dispose();
									}
									connect.closeStatement();
								} catch (Exception ex) {
								}
							} else {
								MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
								if (ngonngu == 0) {
									thongbao.setText("Th??ng b??o l???i");
									thongbao.setMessage("Ch???n thi???t b??? sai!");
								} else {
									thongbao.setText("Notice error");
									thongbao.setMessage("Choose the wrong device!");
								}
								thongbao.open();
							}
						} else {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Th??ng b??o l???i");
								thongbao.setMessage("Lo???i thi???t b??? r???ng!");
							} else {
								thongbao.setText("Notice error");
								thongbao.setMessage("Device type is empty!");
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
	public void setData(int language, String id, String loai, String tenthietbi) {
		ngonngu = language;
		textMaThietBi.setText(id);
		textLoaiThietBi.setText(loai);
		comboThietBi.setText(tenthietbi);
		mathietbisua = id;
	}
}
