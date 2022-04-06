package broken;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class SelectToInsertTable {
	private ConnectSQL connect;

	protected Shell shell;
	private Composite compositeshell;
	private int ngonngu = 1;

	public static void main(String[] args) {
		try {
			SelectToInsertTable window = new SelectToInsertTable();
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
		createShell();
		createContents(compositeshell, shell);
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

	protected void createShell() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(SelectToInsertTable.class, "/itmanagerip/Icon/IP64.png"));
		shell.setSize(1000, 700);
		shell.setMaximized(true);
		shell.setText("Select to insert table");

		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fl_shell);

		compositeshell = new Composite(shell, SWT.EMBEDDED);
		compositeshell.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		compositeshell.setLayout(new FillLayout());
	}

	protected void createContents(Composite com, Shell shell) {
		Composite composite = new Composite(com, SWT.EMBEDDED);
		composite.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		composite.setLayout(null);

		LocalDate date = java.time.LocalDate.now();
		date = date.plusDays(-30);

		Button btntimkiem = new Button(composite, SWT.NONE);
		btntimkiem.setImage(
				SWTResourceManager.getImage(SelectToInsertTable.class, "/itmanagerip/Icon/button/search-30.png"));
		btntimkiem.setText("Tìm kiếm");
		btntimkiem.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btntimkiem.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
		btntimkiem.setBounds(231, 115, 149, 32);

		// Tìm kiếm
		btntimkiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search();
			}
		});

	}

	// select ở bảng này đồng thời insert vào bảng khác
	// --------------------------------------------------------------------------
	public void search() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();

			String select = "select * from IT_PhanCongXuLyCongViec order by MaCongViec ASC";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			String insert = "";
			while (result.next()) {
				insert = insert + "\n"
						+ "INSERT INTO [dbo].[IT_PhanCongXuLyCongViec2] ([MaCongViec],[Ngay],[DonVi],[SoThe],[HoTen],[TinhTrang],[NguyenNhan],[XuLy],[NgayHoanThanh],[KetQua],[NguoiXuLy],[NguoiPhanCong]) VALUES ('"
						+ result.getString(1) + "','" + result.getString(2) + " 00:00:00.000',N'" + result.getString(3) + "','"
						+ result.getString(4) + "',N'" + result.getString(5) + "',N'" + result.getString(6) + "',N'"
						+ result.getString(7) + "',N'" + result.getString(8) + "','" + result.getString(9) + " 00:00:01.000',N'"
						+ result.getString(10) + "',N'"+result.getString(11)+"','"+result.getString(12)+"')";
			}

			result.close();
			state.close();
			connect.closeStatement();

			connect.setStatement();
			connect.execUpdateQuery(insert);

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

	// select ở bảng này đồng thời insert vào bảng khác
	// --------------------------------------------------------------------------
	public void search1() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();

			String select = "SELECT [ID],[SoPhieuBPM],[TenThietBi],[SoLuong],[NgayMua],[NgayNhapKhoIT],[DaNhapKhoIT],[GhiChu],[UserUpdate] FROM [dbo].[IT_MuaThietBi]";

			// Xử lý dữ liệu tìm kiếm, hiển thị lên Table
			ResultSet result = state.executeQuery(select);
			String insert = "";
			while (result.next()) {
				insert = insert + "\n"
						+ "INSERT INTO [dbo].[IT_MuaThietBiIT] ([ID],[SoPhieuBPM],[TenThietBi],[SoLuong],[NgayMua],[NgayNhapKhoIT],[DaNhapKhoIT],[GhiChu],[UserUpdate]) VALUES ('"
						+ result.getString(1) + "','" + result.getString(2) + "',N'" + result.getString(3) + "',"
						+ result.getString(4) + ",'" + result.getString(5) + "','" + result.getString(6) + "',"
						+ result.getString(7) + ",N'" + result.getString(8) + "','" + result.getString(9) + "')";
			}

			result.close();
			state.close();
			connect.closeStatement();

			connect.setStatement();
			connect.execUpdateQuery(insert);

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
}
