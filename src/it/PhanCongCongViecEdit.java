package it;

import org.eclipse.swt.SWT;
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

public class PhanCongCongViecEdit {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoThe;
	private Text textHoTen;
	private int ngonngu = 1;
	private Text textTinhTrang;
	private Text textNguyenNhan;
	private Text textXuLy;
	private Text textKetQua;
	private Text textNguoiXuLy;
	private Text textDonVi;
	private String macongviec = "";
	private Button btnCheckDaHoanThanh;

	public static void main(String[] args) {
		try {
			PhanCongCongViecEdit window = new PhanCongCongViecEdit();
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
		shell.setImage(SWTResourceManager.getImage(PhanCongCongViecEdit.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(645, 568);
		shell.setText("Edit work assignment");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(10, 39, 119, 30);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setEnabled(false);
		textSoThe.setBounds(135, 39, 158, 30);
		textSoThe.setTextLimit(6);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setBounds(10, 91, 119, 30);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setEnabled(false);
		textHoTen.setBounds(135, 91, 440, 30);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		textHoTen.setTextLimit(50);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setBounds(10, 142, 120, 30);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setTextLimit(50);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setEnabled(false);
		textDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		textDonVi.setBounds(135, 142, 440, 30);

		CLabel lbTinhTrang = new CLabel(composite, SWT.RIGHT);
		lbTinhTrang.setText("Tình trạng");
		lbTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTinhTrang.setBounds(10, 192, 120, 30);

		textTinhTrang = new Text(composite, SWT.BORDER);
		textTinhTrang.setEnabled(false);
		textTinhTrang.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTinhTrang.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		textTinhTrang.setBounds(136, 192, 439, 30);
		textTinhTrang.setTextLimit(128);

		CLabel lbNguyenNhan = new CLabel(composite, SWT.RIGHT);
		lbNguyenNhan.setText("Nguyên nhân");
		lbNguyenNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguyenNhan.setBounds(10, 241, 120, 30);

		textNguyenNhan = new Text(composite, SWT.BORDER);
		textNguyenNhan.setTextLimit(64);
		textNguyenNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguyenNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguyenNhan.setBounds(136, 241, 439, 30);

		CLabel lbXuLy = new CLabel(composite, SWT.RIGHT);
		lbXuLy.setText("Xử lý");
		lbXuLy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbXuLy.setBounds(10, 292, 120, 30);

		textXuLy = new Text(composite, SWT.BORDER);
		textXuLy.setTextLimit(128);
		textXuLy.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textXuLy.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textXuLy.setBounds(136, 292, 439, 30);

		CLabel lbKetQua = new CLabel(composite, SWT.RIGHT);
		lbKetQua.setText("Kết quả");
		lbKetQua.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbKetQua.setBounds(10, 343, 120, 30);

		textKetQua = new Text(composite, SWT.BORDER);
		textKetQua.setTextLimit(24);
		textKetQua.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textKetQua.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textKetQua.setBounds(136, 343, 439, 30);

		CLabel lbNguoiXuLy = new CLabel(composite, SWT.RIGHT);
		lbNguoiXuLy.setText("Người xử lý");
		lbNguoiXuLy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNguoiXuLy.setBounds(10, 395, 120, 30);

		textNguoiXuLy = new Text(composite, SWT.BORDER);
		textNguoiXuLy.setTextLimit(128);
		textNguoiXuLy.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiXuLy.setEnabled(false);
		textNguoiXuLy.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		textNguoiXuLy.setBounds(136, 395, 439, 30);

		btnCheckDaHoanThanh = new Button(composite, SWT.CHECK);
		btnCheckDaHoanThanh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckDaHoanThanh.setBounds(135, 442, 158, 16);
		btnCheckDaHoanThanh.setText("Đã hoàn thành");

		Button btnLuu = new Button(composite, SWT.NONE);
		btnLuu.setImage(
				SWTResourceManager.getImage(PhanCongCongViecEdit.class, "/itmanagerip/Icon/button/save 30.png"));
		btnLuu.setBounds(321, 456, 109, 35);
		btnLuu.setText("Lưu");
		btnLuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnLuu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		Button btnHuy = new Button(composite, SWT.NONE);
		btnHuy.setImage(SWTResourceManager.getImage(PhanCongCongViecEdit.class, "/itmanagerip/Icon/button/cancel.png"));
		btnHuy.setBounds(443, 456, 120, 35);
		btnHuy.setText("Hủy");
		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));

		btnLuu.setVisible(true);
		btnHuy.setVisible(true);

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Sửa phân công công việc");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbTinhTrang.setText("Tình trạng");
			lbNguyenNhan.setText("Nguyên nhân");
			lbXuLy.setText("Xử lý");
			lbKetQua.setText("Kết quả");
			lbNguoiXuLy.setText("Người xử lý");
			btnCheckDaHoanThanh.setText("Đã hoàn thành");
			btnLuu.setText("Lưu");
			btnHuy.setText("Hủy");

		} else {
			// Tieng Anh
			shell.setText("Edit work assignment");
			lbSoThe.setText("ID");
			lbHoTen.setText("Person name");
			lbDonVi.setText("Department");
			lbTinhTrang.setText("Status");
			lbNguyenNhan.setText("Cause");
			lbXuLy.setText("Resovle");
			lbKetQua.setText("Result");
			lbNguoiXuLy.setText("Handler");
			btnCheckDaHoanThanh.setText("Finish");
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
					connect.setStatement();
					try {
						String ngayhoanthanh = "";
						if (btnCheckDaHoanThanh.getSelection()) {
							ngayhoanthanh = ",[NgayHoanThanh] = GETDATE()";
						} else {
							ngayhoanthanh = ",[NgayHoanThanh] = '19900101'";
						}
						String update = "UPDATE [dbo].[IT_PhanCongXuLyCongViec2] SET [NguyenNhan] = N'"
								+ textNguyenNhan.getText() + "',[XuLy] = N'" + textXuLy.getText() + "'" + ngayhoanthanh
								+ ",[KetQua] = N'" + textKetQua.getText() + "' WHERE [MaCongViec]='" + macongviec + "'";

						int result = connect.execUpdateQuery(update);
						if (result > 0) {
							MessageBox thongbao = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
							if (ngonngu == 0) {
								thongbao.setText("Thông báo");
								thongbao.setMessage("Cập nhật thông tin thành công!");
							} else {
								thongbao.setText("Notice");
								thongbao.setMessage("Update successful!");
							}
							thongbao.open();
							shell.dispose();
						}
						connect.closeStatement();

					} catch (Exception ex) {
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

	public void setData(String idcongviec, String sothe, String hoten, String donvi, String tinhtrang, String nguoixuly,
			String datefinish, String nguyennhan, String xuly, String ketqua) {
		macongviec = idcongviec;
		textSoThe.setText(sothe);
		textHoTen.setText(hoten);
		textDonVi.setText(donvi);
		textTinhTrang.setText(tinhtrang);
		textNguoiXuLy.setText(nguoixuly);
		if (!datefinish.isEmpty()) {
			btnCheckDaHoanThanh.setSelection(true);
		} else {
			btnCheckDaHoanThanh.setSelection(false);
		}
		textNguyenNhan.setText(nguyennhan);
		textXuLy.setText(xuly);
		textKetQua.setText(ketqua);
	}

	public void setLanguage(int language) {
		ngonngu = language;
	}
}
