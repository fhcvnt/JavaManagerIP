package ittemp;

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

public class ThietBiEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textMaThietBi;
	private Text textTenThietBi;
	private Text textQuyCach;
	private CCombo comboLoaiVatTu;
	private int ngonngu = 1;
	private String mathietbisua = "";

	public static void main(String[] args) {
		try {
			ThietBiEdit window = new ThietBiEdit();
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
		shell.setImage(SWTResourceManager.getImage(ThietBiEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(625, 330);
		shell.setText("Edit devices");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbMaThietBi = new CLabel(composite, SWT.RIGHT);
		lbMaThietBi.setBounds(10, 24, 144, 30);
		lbMaThietBi.setText("Mã Thiết Bị");
		lbMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textMaThietBi = new Text(composite, SWT.BORDER);
		textMaThietBi.setBounds(164, 24, 371, 30);
		textMaThietBi.setTextLimit(12);
		textMaThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textMaThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setBounds(10, 71, 144, 30);
		lbTenThietBi.setText("Tên Thiết Bị");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setBounds(164, 71, 371, 30);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textTenThietBi.setTextLimit(64);

		CLabel lbQuyCach = new CLabel(composite, SWT.RIGHT);
		lbQuyCach.setBounds(10, 118, 144, 30);
		lbQuyCach.setText("Quy Cách");
		lbQuyCach.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textQuyCach = new Text(composite, SWT.BORDER);
		textQuyCach.setBounds(164, 118, 111, 30);
		textQuyCach.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textQuyCach.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textQuyCach.setTextLimit(12);

		CLabel lbExample = new CLabel(composite, SWT.RIGHT);
		lbExample.setAlignment(SWT.LEFT);
		lbExample.setText("Ex: Thùng, hộp, cái, mét, KG,...");
		lbExample.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbExample.setBounds(281, 118, 311, 30);

		CLabel lbLoaiVatTu = new CLabel(composite, SWT.RIGHT);
		lbLoaiVatTu.setText("Loại Vật Tư");
		lbLoaiVatTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbLoaiVatTu.setBounds(10, 165, 143, 30);

		comboLoaiVatTu = new CCombo(composite, SWT.BORDER);
		comboLoaiVatTu.setItems(new String[] { "Định mức", "Tiêu hao" });
		comboLoaiVatTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboLoaiVatTu.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboLoaiVatTu.setBounds(164, 165, 171, 30);
		comboLoaiVatTu.select(0);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(SWTResourceManager.getImage(ThietBiEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(213, 221, 122, 40);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(ThietBiEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(341, 221, 122, 40);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Sửa thiết bị");
			lbMaThietBi.setText("Mã thiết bị");
			lbTenThietBi.setText("Tên thiết bị");
			lbQuyCach.setText("Quy cách");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");
			lbLoaiVatTu.setText("Loại vật tư");

		} else {
			// Tieng Anh
			shell.setText("Edit devices");
			lbMaThietBi.setText("ID");
			lbTenThietBi.setText("Device name");
			lbQuyCach.setText("Specifications ");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");
			lbLoaiVatTu.setText("Type of material");
		}

		// Hủy--------------------------------------------------------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
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

					String update = "UPDATE [dbo].[IT_ThietBi] SET [MaThietBi]='" + textMaThietBi.getText()
							+ "',[TenThietBi]=N'" + textTenThietBi.getText() + "',[QuyCach]=N'" + textQuyCach.getText()
							+ "',[VatTuTieuHao]=" + comboLoaiVatTu.getSelectionIndex() + " WHERE [MaThietBi]='"
							+ mathietbisua + "'";

					if (connect.execUpdateQuery(update) > 0) {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo");
							thongbao.setMessage("Sửa thành công!");
						} else {
							thongbao.setText("Notice");
							thongbao.setMessage("Edit successful!");
						}
						thongbao.open();
						connect.closeStatement();
						shell.dispose();
					}
				} catch (Exception se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo lỗi");
						thongbao.setMessage("Lỗi kết nối!\n" + se.toString());
					} else {
						thongbao.setText("Notice error");
						thongbao.setMessage("Connect error!\n" + se.toString());
					}
					thongbao.open();
				}
			}
		});
	}

	// ngôn ngữ
	public void setLanguage(int language) {
		ngonngu = language;
	}

	// set data edit
	public void setDataEdit(String mathietbi, String tenthietbi, String quycach, int loaivattu) {
		textMaThietBi.setText(mathietbi);
		textTenThietBi.setText(tenthietbi);
		textQuyCach.setText(quycach);
		comboLoaiVatTu.select(loaivattu);
		mathietbisua = mathietbi;
	}
}
