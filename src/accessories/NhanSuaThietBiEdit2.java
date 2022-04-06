package accessories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import image.ResizeImage;
import sql.ConnectSQL;

public class NhanSuaThietBiEdit2 {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textSoThe;
	private int ngonngu = 0;
	private String userlogin = "21608";
	private boolean iseditsuccess = false;

	private Text textHoTen;
	private Text textNoiDung;
	private Text textSoBPM;
	private Text textDonVi;
	private Text textTheTu;
	private Text textSoTheNguoiNhan;
	private Text textNguoiNhan;
	private Text textDonViNguoiNhan;
	private Text textGhiChu;
	private CLabel lbHinhAnh;

	private String masuathietbi = "";
	private Text textThoiGianGui;
	private CLabel lbThoiGianNhan;
	private Text textThoiGianNhan;

	public static void main(String[] args) {
		try {
			NhanSuaThietBiEdit2 window = new NhanSuaThietBiEdit2();
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
		shell.setImage(SWTResourceManager.getImage(NhanSuaThietBiEdit2.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(811, 629);
		shell.setText("Sửa nhận sửa thiết bị");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width) / 2,
				(Display.getDefault().getBounds().height - shell.getBounds().height) / 4);

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		CLabel lbTheTu = new CLabel(composite, SWT.RIGHT);
		lbTheTu.setText("Thẻ từ");
		lbTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTheTu.setBounds(10, 20, 100, 30);

		textTheTu = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textTheTu.setTextLimit(32);
		textTheTu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textTheTu.setBounds(118, 20, 290, 30);
		textTheTu.setEnabled(false);

		CLabel lbSoThe = new CLabel(composite, SWT.RIGHT);
		lbSoThe.setBounds(10, 70, 100, 30);
		lbSoThe.setText("Số thẻ");
		lbSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));

		textSoThe = new Text(composite, SWT.BORDER);
		textSoThe.setEnabled(false);
		textSoThe.setBounds(118, 70, 80, 30);
		textSoThe.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoThe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoThe.setTextLimit(6);

		CLabel lbHoTen = new CLabel(composite, SWT.RIGHT);
		lbHoTen.setText("Họ tên");
		lbHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHoTen.setBounds(204, 70, 80, 30);

		textHoTen = new Text(composite, SWT.BORDER);
		textHoTen.setEnabled(false);
		textHoTen.setEditable(false);
		textHoTen.setTextLimit(50);
		textHoTen.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHoTen.setBounds(290, 70, 268, 30);

		CLabel lbDonVi = new CLabel(composite, SWT.RIGHT);
		lbDonVi.setText("Đơn vị");
		lbDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonVi.setBounds(10, 120, 100, 30);

		textDonVi = new Text(composite, SWT.BORDER);
		textDonVi.setEnabled(false);
		textDonVi.setEditable(false);
		textDonVi.setTextLimit(50);
		textDonVi.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonVi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textDonVi.setBounds(118, 120, 440, 30);

		CLabel lbSoBPM = new CLabel(composite, SWT.RIGHT);
		lbSoBPM.setText("Số BPM");
		lbSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoBPM.setBounds(10, 170, 100, 30);

		textSoBPM = new Text(composite, SWT.BORDER);
		textSoBPM.setTextLimit(20);
		textSoBPM.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoBPM.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoBPM.setBounds(118, 170, 290, 30);

		CLabel lbNoiDung = new CLabel(composite, SWT.RIGHT);
		lbNoiDung.setText("Nội dung");
		lbNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNoiDung.setBounds(10, 220, 100, 30);

		textNoiDung = new Text(composite, SWT.BORDER);
		textNoiDung.setEnabled(false);
		textNoiDung.setTextLimit(80);
		textNoiDung.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNoiDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNoiDung.setBounds(118, 220, 440, 30);

		CLabel lbThoiGianGui = new CLabel(composite, SWT.RIGHT);
		lbThoiGianGui.setText("Thời gian gửi");
		lbThoiGianGui.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGianGui.setBounds(10, 270, 100, 30);

		textThoiGianGui = new Text(composite, SWT.BORDER);
		textThoiGianGui.setTextLimit(80);
		textThoiGianGui.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textThoiGianGui.setEnabled(false);
		textThoiGianGui.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textThoiGianGui.setBounds(118, 270, 290, 30);

		lbHinhAnh = new CLabel(composite, SWT.BORDER | SWT.RIGHT);
		lbHinhAnh.setText("");
		lbHinhAnh.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbHinhAnh.setBounds(585, 20, 172, 230);

		CLabel lbSoTheNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbSoTheNguoiNhan.setText("Số thẻ");
		lbSoTheNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbSoTheNguoiNhan.setBounds(10, 320, 100, 30);

		textSoTheNguoiNhan = new Text(composite, SWT.BORDER);
		textSoTheNguoiNhan.setEnabled(false);
		textSoTheNguoiNhan.setTextLimit(6);
		textSoTheNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textSoTheNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textSoTheNguoiNhan.setBounds(118, 320, 80, 30);

		CLabel lblNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lblNguoiNhan.setText("Người nhận");
		lblNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lblNguoiNhan.setBounds(204, 320, 148, 30);

		textNguoiNhan = new Text(composite, SWT.BORDER);
		textNguoiNhan.setEnabled(false);
		textNguoiNhan.setTextLimit(50);
		textNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textNguoiNhan.setEditable(false);
		textNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNguoiNhan.setBounds(363, 320, 394, 30);

		CLabel lbDonViNguoiNhan = new CLabel(composite, SWT.RIGHT);
		lbDonViNguoiNhan.setText("Đơn vị");
		lbDonViNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbDonViNguoiNhan.setBounds(10, 370, 100, 30);

		textDonViNguoiNhan = new Text(composite, SWT.BORDER);
		textDonViNguoiNhan.setEnabled(false);
		textDonViNguoiNhan.setTextLimit(50);
		textDonViNguoiNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textDonViNguoiNhan.setEditable(false);
		textDonViNguoiNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textDonViNguoiNhan.setBounds(118, 370, 440, 30);

		lbThoiGianNhan = new CLabel(composite, SWT.RIGHT);
		lbThoiGianNhan.setText("Thời gian nhận");
		lbThoiGianNhan.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbThoiGianNhan.setBounds(10, 420, 122, 30);

		textThoiGianNhan = new Text(composite, SWT.BORDER);
		textThoiGianNhan.setEnabled(false);
		textThoiGianNhan.setTextLimit(255);
		textThoiGianNhan.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textThoiGianNhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textThoiGianNhan.setBounds(138, 420, 420, 30);

		CLabel lbGhiChu = new CLabel(composite, SWT.RIGHT);
		lbGhiChu.setText("Ghi chú");
		lbGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbGhiChu.setBounds(10, 470, 100, 30);

		textGhiChu = new Text(composite, SWT.BORDER);
		textGhiChu.setTextLimit(255);
		textGhiChu.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textGhiChu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textGhiChu.setBounds(118, 470, 639, 30);

		Button btnSave = new Button(composite, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(NhanSuaThietBiEdit2.class, "/itmanagerip/Icon/button/save25.png"));
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSave.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btnSave.setBounds(302, 522, 122, 35);
		btnSave.setText("Save");

		// Save
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				save();
			}
		});

		textTheTu.setFocus();

		// đặt tên theo ngôn ngữ
		// ********************************************************************************************************
		if (ngonngu == 0) {
			// Tieng Viet
			shell.setText("Sửa nhận sửa thiết bị");
			lbTheTu.setText("Thẻ từ");
			lbSoThe.setText("Số thẻ");
			lbHoTen.setText("Họ tên");
			lbDonVi.setText("Đơn vị");
			lbSoBPM.setText("Số BPM");
			lbNoiDung.setText("Nội dung");
			lbThoiGianGui.setText("Thời gian gửi");
			lbSoTheNguoiNhan.setText("Số thẻ");
			lblNguoiNhan.setText("Người nhận");
			lbDonViNguoiNhan.setText("Đơn vị");
			lbThoiGianNhan.setText("Thời gian nhận");
			lbGhiChu.setText("Ghi chú");
			btnSave.setText("Lưu");

		} else {
			// Tieng Anh
			shell.setText("Edit repair device");
			lbTheTu.setText("Card");
			lbSoThe.setText("ID");
			lbHoTen.setText("Name");
			lbDonVi.setText("Department");
			lbSoBPM.setText("BPM number");
			lbNoiDung.setText("Content");
			lbThoiGianGui.setText("Sending time");
			lbSoTheNguoiNhan.setText("ID");
			lblNguoiNhan.setText("Receiver");
			lbDonViNguoiNhan.setText("Department");
			lbThoiGianNhan.setText("Receiving time");
			lbGhiChu.setText("Note");
			btnSave.setText("Save");

		}

	}

	public void save() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			try {
				String insert = "UPDATE [dbo].[LK_SuaThietBi] SET [SoBPM]='" + textSoBPM.getText() + "',[GhiChu] = N'"
						+ textGhiChu.getText() + "',[NguoiCapNhat] = '" + userlogin + "' WHERE [MaSuaThietBi]='"
						+ masuathietbi + "'";

				int result = connect.execUpdateQuery(insert);
				if (result > 0) {
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

			} catch (Exception ex) {
			}

			connect.closeStatement();
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

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language, String user) {
		ngonngu = language;
		userlogin = user;
	}

	public void setData(String masuathietbi, String sothe, String hoten, String donvi, String bpm, String noidung,
			String thoigiangui, String sothenguoinhan, String nguoinhan, String donvinguoinhan, String thoigiannhan,
			String ghichu) {
		this.masuathietbi = masuathietbi;
		textSoThe.setText(sothe);
		textHoTen.setText(hoten);
		textDonVi.setText(donvi);
		textSoBPM.setText(bpm);
		textNoiDung.setText(noidung);
		textThoiGianGui.setText(thoigiangui);

		textSoTheNguoiNhan.setText(sothenguoinhan);
		textNguoiNhan.setText(nguoinhan);
		textDonViNguoiNhan.setText(donvinguoinhan);
		textThoiGianNhan.setText(thoigiannhan);
		textGhiChu.setText(ghichu);

		// hinh anh
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			String select = "";
			ResultSet result;

			select = "SELECT [Data_Person].[Person_Image] FROM [SV4].[HRIS].[dbo].[Data_Person] WHERE Data_Person.Person_Status=1 AND Data_Person.Person_ID='"
					+ sothenguoinhan + "'";

			PreparedStatement ps = connect.getConnection().prepareStatement(select);

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			result = ps.executeQuery();

			lbHinhAnh.setBackground(SWTResourceManager.getColor(240, 240, 240));
			while (result.next()) {

				try {
					Image image = new Image(null, new ImageData(result.getBinaryStream(1)));
					lbHinhAnh.setBackground(
							ResizeImage.resize(image, lbHinhAnh.getBounds().width, lbHinhAnh.getBounds().height));
				} catch (Exception ex) {
				}

			}
			result.close();

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

	// kiểm tra xem có sửa thành công không
	public boolean isEditSuccess() {
		return iseditsuccess;
	}
}
