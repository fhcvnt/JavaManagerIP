package notification;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class HienThiThongBao {

	protected Shell shell;
	private ConnectSQL connect;
	private CLabel lbSoThe2;
	private CLabel lbHoTen2;
	private CLabel lbDonVi2;
	private CLabel lbTinhTrang2;
	private String macongviec = "";
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HienThiThongBao window = new HienThiThongBao();
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
		shell = new Shell(SWT.ON_TOP);
		shell.setSize(990, 714);
		// shell.setSize(886, 489);
		shell.setText("Thông báo xử lý sự cố!");
		shell.setMaximized(true);
		shell.setFullScreen(true);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite compositeshell = new Composite(shell, SWT.NONE);
		compositeshell.setLayout(null);

		Composite composite = new Composite(compositeshell, SWT.NONE);
		composite.setBounds((Display.getDefault().getBounds().width - 960) / 2,
				(Display.getDefault().getBounds().height - 430) / 4, 960, 430);
		composite.setLayout(null);

		text = new Text(composite, SWT.BORDER);
		text.setBounds(35, 350, 0, 0);

		Button btnOK = new Button(composite, SWT.NONE);
		btnOK.setBounds(250, 350, 180, 50);
		btnOK.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnOK.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		btnOK.setText("OK");

		lbTinhTrang2 = new CLabel(composite, SWT.BORDER);
		lbTinhTrang2.setBounds(230, 270, 728, 50);
		lbTinhTrang2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lbTinhTrang2.setText("may mo khong len");
		lbTinhTrang2.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));

		lbDonVi2 = new CLabel(composite, SWT.BORDER);
		lbDonVi2.setBounds(230, 210, 728, 50);
		lbDonVi2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lbDonVi2.setText("IT");
		lbDonVi2.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));

		lbHoTen2 = new CLabel(composite, SWT.BORDER | SWT.SHADOW_IN);
		lbHoTen2.setBounds(230, 150, 728, 50);
		lbHoTen2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lbHoTen2.setText("To Ngoc Tri");
		lbHoTen2.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));

		lbSoThe2 = new CLabel(composite, SWT.BORDER | SWT.SHADOW_IN);
		lbSoThe2.setBounds(230, 90, 728, 50);
		lbSoThe2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lbSoThe2.setText("21608");
		lbSoThe2.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));

		CLabel lbTinhTrang = new CLabel(composite, SWT.BORDER | SWT.SHADOW_IN);
		lbTinhTrang.setBounds(20, 270, 200, 50);
		lbTinhTrang.setText("Tình trạng:");
		lbTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		lbTinhTrang.setAlignment(SWT.RIGHT);

		CLabel lbDonVi = new CLabel(composite, SWT.BORDER | SWT.SHADOW_IN);
		lbDonVi.setBounds(20, 210, 200, 50);
		lbDonVi.setText("Đơn vị:");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		lbDonVi.setAlignment(SWT.RIGHT);

		CLabel lbHoTen = new CLabel(composite, SWT.BORDER | SWT.SHADOW_IN);
		lbHoTen.setBounds(20, 150, 200, 50);
		lbHoTen.setText("Họ tên:");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		lbHoTen.setAlignment(SWT.RIGHT);

		CLabel lbSoThe = new CLabel(composite, SWT.BORDER | SWT.SHADOW_IN);
		lbSoThe.setBounds(20, 90, 200, 50);
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		lbSoThe.setAlignment(SWT.RIGHT);
		lbSoThe.setText("Số thẻ:");

		CLabel lbTieuDe = new CLabel(composite, SWT.NONE);
		lbTieuDe.setBounds(28, 10, 930, 60);
		lbTieuDe.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lbTieuDe.setFont(SWTResourceManager.getFont("Times New Roman", 25, SWT.BOLD));
		lbTieuDe.setText("Bạn được phân công đi xử lý sự cố cho người dùng sau:");
		
//		shell.setEnabled(true);
//		compositeshell.setEnabled(true);
//		composite.setEnabled(true);
//		Display.getDefault().getActiveShell().setEnabled(true);

		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				OK();
			}
		});

		// tắt thông báo sau khi enter ở btnOK
		// ******************************************************************************
		btnOK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					OK();
				}
			}
		});

		// tắt thông báo sau khi enter
		// ******************************************************************************
		composite.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					OK();
				}
			}
		});

		// tắt thông báo sau khi enter
		// ******************************************************************************
		compositeshell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì tìm kiếm
				if (e.character == SWT.CR) {
					OK();
				}
			}
		});

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------
	public void OK() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();

			String delete = "DELETE FROM [dbo].[IT_HienThiThongBao] WHERE [MaCongViec]='" + macongviec + "'";
			connect.execUpdateQuery(delete);
			connect.closeStatement();
			shell.dispose();

		} catch (Exception se) {
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------
	public void setDataLabel(String sothe, String hoten, String donvi, String tinhtrang, String macv) {
		lbSoThe2.setText(sothe);
		lbHoTen2.setText(hoten);
		lbDonVi2.setText(donvi);
		lbTinhTrang2.setText(tinhtrang);
		macongviec = macv;
	}
}
