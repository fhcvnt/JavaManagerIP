package notification;

import java.sql.ResultSet;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import sql.ConnectSQL;

public class XuLyDeHienThiThongBao {

	protected Shell shell;
	private ConnectSQL connect;
	private int thoigiantimer = 10000; // 10 giây kiểm tra cơ sở dữ liệu một lần xem có thông báo mới không
	private String sothe = "";
	private String hoten = "";
	private String donvi = "";
	private String tinhtrang = "";
	private String macongviec = "";

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			XuLyDeHienThiThongBao window = new XuLyDeHienThiThongBao();
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
		createContents("21608");
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
	public void createContents(String userlogin) {
//		shell = new Shell();
//		shell.setSize(450, 300);
//		shell.setText("SWT Application");

		// Kiểm tra xem có thông báo cho mình không
		// ------------------------------------------------------------------------------
		Runnable timer = new Runnable() {

			@Override
			public void run() {
				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					String select = "SELECT [SoThe],[HoTen],[DonVi],[TinhTrang],[IT_HienThiThongBao].[MaCongViec] FROM [dbo].[IT_HienThiThongBao],[dbo].[IT_PhanCongXuLyCongViec2],[dbo].[NguoiDung] WHERE [IT_HienThiThongBao].[MaCongViec]=[IT_PhanCongXuLyCongViec2].[MaCongViec] AND [IT_PhanCongXuLyCongViec2].[NguoiXuLy]=[NguoiDung].[TenNguoiDung] AND CAST([Ngay] AS DATE)=CAST(GETDATE() AS DATE) AND [NguoiXuLy]=[NguoiDung].[TenNguoiDung] AND [NguoiDung].[MaNguoiDung]='"
							+ userlogin + "' AND [IT_HienThiThongBao].[TrangThai]=0";
					ResultSet result = connect.getStatement().executeQuery(select);
					macongviec = "";
					while (result.next()) {
						sothe = result.getString(1);
						hoten = result.getString(2);
						donvi = result.getString(3);
						tinhtrang = result.getString(4);
						macongviec = result.getString(5);
					}
					if (!macongviec.isEmpty()) {
						HienThiThongBao thongbao = new HienThiThongBao();
						thongbao.createContents();
						thongbao.setDataLabel(sothe, hoten, donvi, tinhtrang, macongviec);
						thongbao.open();
					}

					result.close();
					connect.closeStatement();
				} catch (Exception se) {
				}

				Display.getDefault().timerExec(thoigiantimer, this);
			}
		};
		Display.getDefault().timerExec(thoigiantimer, timer);

	}
}
