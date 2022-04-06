package accessories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class ThietBiTrongKhoITEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private int ngonngu = 0;
	private Text textGhiChu;
	private String userlogin = "";
	private boolean iseditsuccess = false;

	private Text textTenThietBi;
	private Text textSoBPM;
	private ArrayList<String> madanhmuc;
	private String mathietbisua = "";
	private CCombo comboDanhMuc;
	private CCombo comboTinhTrang;
	private CCombo comboTrangThai;

	public static void main(String[] args) {
		try {
			ThietBiTrongKhoITEdit window = new ThietBiTrongKhoITEdit();
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
		shell.setImage(SWTResourceManager.getImage(ThietBiTrongKhoITEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(621, 434);
		shell.setText("Sửa thiết bị nhập kho");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbDanhMuc = new CLabel(composite, SWT.RIGHT);
		lbDanhMuc.setText("Danh mục");
		lbDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDanhMuc.setBounds(20, 10, 110, 30);

		comboDanhMuc = new CCombo(composite, SWT.BORDER);
		comboDanhMuc.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboDanhMuc.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboDanhMuc.setBounds(140, 10, 422, 30);
		comboDanhMuc.setEditable(false);

		CLabel lbTenThietBi = new CLabel(composite, SWT.RIGHT);
		lbTenThietBi.setText("Tên thiết bị");
		lbTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTenThietBi.setBounds(20, 60, 110, 30);

		textTenThietBi = new Text(composite, SWT.BORDER);
		textTenThietBi.setTextLimit(100);
		textTenThietBi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTenThietBi.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textTenThietBi.setBounds(140, 60, 422, 30);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoBPM.setBounds(16, 110, 110, 30);

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setTextLimit(20);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setBounds(140, 110, 296, 30);

		CLabel lbTinhTrang = new CLabel(composite, SWT.RIGHT);
		lbTinhTrang.setText("Tình trạng");
		lbTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTinhTrang.setBounds(20, 160, 110, 30);

		comboTinhTrang = new CCombo(composite, SWT.BORDER);
		comboTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTinhTrang.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboTinhTrang.setBounds(140, 160, 126, 30);
		comboTinhTrang.setItems(new String[] { "Cũ", "Mới" });
		comboTinhTrang.setEditable(false);
		comboTinhTrang.select(1);

		CLabel lbTrangThai = new CLabel(composite, SWT.RIGHT);
		lbTrangThai.setText("Trạng thái");
		lbTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTrangThai.setBounds(272, 160, 110, 30);

		comboTrangThai = new CCombo(composite, SWT.BORDER);
		comboTrangThai.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		comboTrangThai.setBackground(SWTResourceManager.getColor(224, 255, 255));
		comboTrangThai.setBounds(392, 160, 170, 30);
		comboTrangThai.setItems(new String[] { "Đã phát", "Chưa phát" });
		comboTrangThai.setEditable(false);
		comboTrangThai.select(1);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi chú");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(20, 250, 110, 30);

		textGhiChu = new Text(composite, SWT.BORDER | SWT.MULTI);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(140, 210, 422, 111);

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(ThietBiTrongKhoITEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(199, 340, 120, 32);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(ThietBiTrongKhoITEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(344, 340, 120, 32);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Sửa thiết bị nhập kho");
			lbDanhMuc.setText("Danh mục");
			lbTenThietBi.setText("Tên thiết bị");
			lbSoBPM.setText("Số BPM");
			lbTinhTrang.setText("Tình trạng");
			lbTrangThai.setText("Trạng thái");
			lbGhiChu.setText("Ghi chú");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

		} else {
			// Tieng Anh
			shell.setText("Edit device from warehouse");
			lbDanhMuc.setText("Catalog");
			lbTenThietBi.setText("Device name");
			lbSoBPM.setText("BPM number");
			lbTinhTrang.setText("State");
			lbTrangThai.setText("Status");
			lbGhiChu.setText("Note");
			btnLuu.setText("Save");
			btnHuy.setText("Cancel");

		}

		madanhmuc = new ArrayList<>();
		// lấy dữ liệu cho combo danh mục
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT [MaDanhMuc],[TenDanhMuc] FROM [dbo].[LK_DanhMucThietBi] ORDER BY [TenDanhMuc] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			while (result.next()) {
				comboDanhMuc.add(result.getString(2));
				madanhmuc.add(result.getString(1));
			}
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo lỗi!");
				thongbao.setMessage("Lỗi!\n" + se.toString());
			} else {
				thongbao.setText("Notice error!");
				thongbao.setMessage("Error!\n" + se.toString());
			}
			thongbao.open();
		}

		// Lưu--------------------------------------------------------------------------------------------------------------
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!comboDanhMuc.getText().isEmpty() && !textTenThietBi.getText().isEmpty()) {
						if (connect == null) {
							connect = new ConnectSQL();
							connect.setConnection();
						}
						connect.setStatement();

						String update = "UPDATE [dbo].[LK_ThietBiKhoIT] SET [MaDanhMuc] = "
								+ madanhmuc.get(comboDanhMuc.getSelectionIndex()) + ",[TenThietBi] = N'"
								+ textTenThietBi.getText() + "',[SoBPM] = '" + textSoBPM.getText()
								+ "',[NgayNhapKho] = GETDATE(),[TinhTrang] = " + comboTinhTrang.getSelectionIndex()
								+ ",[TrangThai] = " + comboTrangThai.getSelectionIndex() + ",[GhiChu] = N'"
								+ textGhiChu.getText() + "',[NguoiCapNhat] = '" + userlogin + "' WHERE [MaThietBi] = '"
								+ mathietbisua + "'";

						int resultedit = connect.execUpdateQuery(update);
						if (resultedit > 0) {
							iseditsuccess = true;
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Sửa thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Edit suscess!");
							}
							thongbao.open();

							shell.dispose();
						}

						connect.closeStatement();
					} else {
						MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						if (ngonngu == 0) {
							thongbao.setText("Thông báo lỗi");
							thongbao.setMessage("Danh mục rỗng hoặc tên thiết bị rỗng!");
						} else {
							thongbao.setText("Error");
							thongbao.setMessage("Catalog is empty or device name is empty!");
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
	public void setLanguage(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	// kiểm tra xem có thêm thành công không
	public boolean isEditSuccess() {
		return iseditsuccess;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------
	// set data edit
	public void setData(String mathietbi, String danhmuc, String tenthietbi, String sobpm, String tinhtrang,
			String trangthai, String ghichu) {
		mathietbisua = mathietbi;
		comboDanhMuc.setText(danhmuc);
		textTenThietBi.setText(tenthietbi);
		textSoBPM.setText(sobpm);
		comboTinhTrang.setText(tinhtrang);
		comboTrangThai.setText(trangthai);
		textGhiChu.setText(ghichu);
	}
}
