package itmanagerip;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class FormLoginManagerIPRemember {
	// Ngày tạo phần mềm 16/10/2020
	private String version = "V72.30.7.20201016";
	private ConnectSQL connect;

	protected Shell shell;
	private Text textTendangnhap;
	private Text textMatkhau;
	private Combo cbNgonngu;
	private Button btnCheckRemember;
	private int ngonngu = 0; // 0: Vietnam, 1: English

	public static void main(String[] args) {
		try {
			FormLoginManagerIPRemember window = new FormLoginManagerIPRemember();
			window.open();
		} catch (Exception e) {
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
		shell = new Shell(SWT.FULL_SELECTION);
		shell.setSize(505, 259);
		shell.setText("Đăng nhập");
		shell.setLocation(400, 300);
		shell.setLocation((Display.getDefault().getBounds().width - 480) / 2,
				(Display.getDefault().getBounds().height - 310) / 2);
		shell.setImage(SWTResourceManager.getImage(FormLoginManagerIPRemember.class, "/itmanagerip/Icon/IP64.png"));

		CLabel lbLogo = new CLabel(shell, SWT.NONE);
		lbLogo.setBackground(SWTResourceManager.getImage(FormLoginManagerIPRemember.class,
				"/itmanagerip/Icon/bieutuong/login124x135.png"));
		lbLogo.setBounds(24, 92, 124, 135);
		lbLogo.setText("");

		CLabel lbTendangnhap = new CLabel(shell, SWT.NONE);
		lbTendangnhap.setAlignment(SWT.RIGHT);
		lbTendangnhap.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbTendangnhap.setBounds(114, 24, 124, 25);
		lbTendangnhap.setText("Tên Đăng Nhập");

		CLabel lbMatkhau = new CLabel(shell, SWT.NONE);
		lbMatkhau.setAlignment(SWT.RIGHT);
		lbMatkhau.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbMatkhau.setBounds(154, 71, 83, 25);
		lbMatkhau.setText("Mật Khẩu");

		CLabel lbNgonngu = new CLabel(shell, SWT.NONE);
		lbNgonngu.setAlignment(SWT.RIGHT);
		lbNgonngu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		lbNgonngu.setBounds(155, 119, 83, 25);
		lbNgonngu.setText("Ngôn Ngữ");

		textTendangnhap = new Text(shell, SWT.BORDER);
		textTendangnhap.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		textTendangnhap.setBounds(244, 24, 240, 25);
		textTendangnhap.setTextLimit(12);

		textMatkhau = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textMatkhau.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		textMatkhau.setBounds(244, 71, 240, 25);
		textMatkhau.setTextLimit(32);

		cbNgonngu = new Combo(shell, SWT.NONE); 
		cbNgonngu.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		cbNgonngu.setItems(new String[] { "Tiếng Việt", "English" });
		cbNgonngu.setBounds(244, 117, 240, 27);
		cbNgonngu.select(0);

		btnCheckRemember = new Button(shell, SWT.CHECK);
		btnCheckRemember.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		btnCheckRemember.setBounds(244, 160, 240, 25);
		btnCheckRemember.setText("Remember");

		Button btnDangnhap = new Button(shell, SWT.NONE);
		btnDangnhap.setImage(
				SWTResourceManager.getImage(FormLoginManagerIPRemember.class, "/itmanagerip/Icon/button/login.png"));
		btnDangnhap.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnDangnhap.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnDangnhap.setBounds(244, 200, 124, 34);
		btnDangnhap.setText("Đăng Nhập");

		Button btnHuy = new Button(shell, SWT.NONE);
		btnHuy.setImage(
				SWTResourceManager.getImage(FormLoginManagerIPRemember.class, "/itmanagerip/Icon/button/cancel.png"));

		btnHuy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHuy.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.BOLD));
		btnHuy.setBounds(374, 200, 110, 34);
		btnHuy.setText("Hủy");

		if (ngonngu == 0) {
			shell.setText("Đăng nhập");
			lbTendangnhap.setText("Tên Đăng Nhập");
			lbMatkhau.setText("Mật Khẩu");
			lbNgonngu.setText("Ngôn Ngữ");
			btnCheckRemember.setText("Ghi nhớ");
			btnDangnhap.setText("Đăng Nhập");
			btnHuy.setText("Hủy");
		} else {
			shell.setText("Login");
			lbTendangnhap.setText("Username");
			lbMatkhau.setText("Password");
			lbNgonngu.setText("Language");
			btnCheckRemember.setText("Remember");
			btnDangnhap.setText("Login");
			btnHuy.setText("Cancel");
		}

		// Kiểm tra cập nhật
		// ********************************************************************************************************************************************************
		// Lấy tên file đang chạy, bởi người dùng có thể đổi tên file chạy nên khi cập
		// nhật sẽ bị sai, bởi vậy ta phải lấy tên người dùng đã đổi để cập nhật
		List<String> filename = new ArrayList<>();
		try {
			// Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
			File f = new File("tempdataxxx.txt");
			FileReader fr = new FileReader(f);
			// Bước 2: Đọc dữ liệu
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				filename.add(line);
			}
			// Bước 3: Đóng luồng
			fr.close();
			br.close();
		} catch (Exception ex) {
		}

		String filenamerun = System.getProperty("java.class.path");
		filenamerun = filenamerun.substring(filenamerun.lastIndexOf('\\') + 1);
		String filename1 = filenamerun;
		String filename2 = "a" + filenamerun;
		String filename3 = "b" + filenamerun;
		try {
			filename1 = filename.get(0).toString();
			filename2 = filename.get(1).toString();
			filename3 = filename.get(2).toString();
		} catch (Exception ex) {

		}
		try {
			File file1 = new File(filename1);
			File file2 = new File(filename2);
			File file3 = new File(filename3);
			if (file3.exists() && file1.exists()) {
				file3.deleteOnExit();
				File filetemp = new File("tempdataxxx.txt");
				if (filetemp.exists()) {
					filetemp.deleteOnExit();
				}
			} else if (file2.exists() && file1.exists()) {
				if (file1.renameTo(file3)) {
					// mở file3
					if (Desktop.isDesktopSupported()) {
						Desktop destop = Desktop.getDesktop();
						if (file3.exists()) {
							shell.dispose();
							destop.open(file3);
							System.exit(0);
						}
					}
				}
			} else if (file2.exists() && file3.exists()) {
				if (file2.renameTo(file1)) {
					// mở file3
					if (Desktop.isDesktopSupported()) {
						Desktop destop = Desktop.getDesktop();
						if (file1.exists()) {
							shell.dispose();
							destop.open(file1);
							System.exit(0);
						}
					}
				}
			}
			// kiem tra xem co phien ban moi khong
			try {
				connect = new ConnectSQL();
				connect.setConnection();
				connect.setStatement();

				String select = "SELECT TOP 1 PhienBan FROM CapNhatPhanMem WHERE HeDieuHanh='Window 64' ORDER BY ThoiGianCapNhat DESC";
				ResultSet result = connect.getStatement().executeQuery(select);
				String phienban = "";
				while (result.next()) {
					phienban = result.getString(1);
				}
				result.close();
				if (version.compareToIgnoreCase(phienban) != 0 && !phienban.isEmpty()) {
					connect.setStatement();
					String selectcapnhat = "SELECT FileCode FROM CapNhatPhanMem WHERE PhienBan='" + phienban
							+ "' AND HeDieuHanh='Window 64'";
					ResultSet ketqua = connect.getStatement().executeQuery(selectcapnhat);
					while (ketqua.next()) {
						FileOutputStream outputStream = new FileOutputStream(filename2);
						byte[] strToBytes = ketqua.getBytes(1);
						outputStream.write(strToBytes);
						outputStream.close();
					}
					ketqua.close();

					try {
						// Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
						File f = new File("tempdataxxx.txt");
						FileWriter fw = new FileWriter(f);
						// Bước 2: Ghi dữ liệu
						String filenamejava1 = System.getProperty("java.class.path");
						filenamejava1 = filenamejava1.substring(filenamejava1.lastIndexOf('\\') + 1);
						String filenamejava2 = "a" + filenamejava1;
						String filenamejava3 = "b" + filenamejava1;
						fw.write(filenamejava1 + "\r\n" + filenamejava2 + "\r\n" + filenamejava3);
						// Bước 3: Đóng luồng
						fw.close();
					} catch (IOException ex) {
					}

					// mở file2
					if (Desktop.isDesktopSupported()) {
						Desktop destop = Desktop.getDesktop();
						if (file2.exists()) {
							shell.dispose();
							destop.open(file2);
							System.exit(0);
						}
					}
				}

			} catch (Exception se) {
			} finally {
				try {
					if (connect.getStatement() != null) {
						connect.closeStatement();
					}
					if (connect.getConnection() != null) {
						connect.closeConnection();
					}

				} catch (Exception se2) {
				}
			}
		} catch (Exception exc) {

		}

		// lấy dữ liệu đăng nhập đã lưu
		// ------------------------------------------------------------------------------------------------------
		List<String> filelogin = new ArrayList<>();
		try {
			// Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
			File f = new File("login.conf");
			FileReader fr = new FileReader(f);
			// Bước 2: Đọc dữ liệu
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				filelogin.add(line);
			}
			// Bước 3: Đóng luồng
			br.close();
			fr.close();

			String username = "";
			String pass = "";
			try {
				username = filelogin.get(0);
				pass = deCode(filelogin.get(1));
				btnCheckRemember.setSelection(true);
			} catch (Exception ex) {
				username = "";
				pass = "";
				btnCheckRemember.setSelection(false);
			}
			textTendangnhap.setText(username);
			textMatkhau.setText(pass);
		} catch (Exception ex) {
		}

		// Button Đăng Nhập
		// ------------------------------------------------------------------------------------------------------
		btnDangnhap.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				login(shell);
			}
		});

		// Đăng nhập sau khi enter ở button Đăng nhập
		// ******************************************************************************
		btnDangnhap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì đăng nhập luôn
				if (e.character == SWT.CR) {
					login(shell);
				}
			}
		});

		// Đăng nhập sau khi enter ở text tên đăng nhập
		// ******************************************************************************
		textTendangnhap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì đăng nhập luôn
				if (e.character == SWT.CR) {
					login(shell);
				}
			}
		});

		// Đăng nhập sau khi enter ở text mật khẩu
		// ******************************************************************************
		textMatkhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì đăng nhập luôn
				if (e.character == SWT.CR) {
					login(shell);
				}
			}
		});

		// Đóng cửa sổ sau khi enter ở button Cancel
		// ******************************************************************************
		btnHuy.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// phím tắt Enter thì đăng nhập luôn
				if (e.character == SWT.CR) {
					shell.dispose();
				}
			}
		});

		// Button Hủy --------------------------------------------------------------
		btnHuy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});

		// Xử lý ngôn ngữ khi thay đổi -------------------------------------------------
		cbNgonngu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = cbNgonngu.getSelectionIndex();
				ngonngu = i;
				if (i == 0) {
					lbTendangnhap.setText("Tên Đăng Nhập");
					lbMatkhau.setText("Mật Khẩu");
					lbNgonngu.setText("Ngôn Ngữ");
					btnCheckRemember.setText("Ghi nhớ");
					btnDangnhap.setText("Đăng Nhập");
					btnHuy.setText("Hủy");
				} else if (i == 1) {
					lbTendangnhap.setText("Username");
					lbMatkhau.setText("Password");
					lbNgonngu.setText("Language");
					btnCheckRemember.setText("Remember");
					btnDangnhap.setText("Login");
					btnHuy.setText("Cancel");
				}
			}
		});

	}

	// *********************************************************************************
	public String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	// Encode
	public static String enCode(String code) {
		String encode = "";
		int len = code.length();
		String kytu = "";
		for (int i = 0; i < len; i++) {
			kytu = kytu + (code.charAt(i) + 19) + ".";
		}
		Random rand = new Random();
		for (int j = 0; j < 49; j++) {
			encode = encode + rand.nextInt(254) + ".";
		}
		encode = encode + kytu;
		for (int j = 0; j < 32; j++) {
			encode = encode + rand.nextInt(254) + ".";
		}
		encode = encode + rand.nextInt(106);
		return encode;
	}

	// Decode
	public static String deCode(String encode) {
		String decode = "";
		try {
			String[] chuoi = encode.split("\\.");
			for (int i = 49; i < chuoi.length - 33; i++) {
				char kytuchuoi = (char) (Integer.parseInt(chuoi[i]) - 19);
				decode = decode + kytuchuoi;
			}

		} catch (Exception e) {

		}
		return decode;
	}

	// -------------------------------------------------------------------------------------------------
	public void login(Shell shell) {

		if (textTendangnhap.getText().isEmpty()) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo");
				thongbao.setMessage("Tên đăng nhập rỗng!");
			} else if (ngonngu == 1) {
				thongbao.setText("Notice");
				thongbao.setMessage("Username is empty!");
			}
			thongbao.open();
		} else if (textMatkhau.getText().isEmpty()) {
			MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			if (ngonngu == 0) {
				thongbao.setText("Thông báo");
				thongbao.setMessage("Mật khẩu rỗng!");
			} else if (ngonngu == 1) {
				thongbao.setText("Notice");
				thongbao.setMessage("Password is empty!");
			}
			thongbao.open();
		} else {
			try {
				connect = new ConnectSQL();
				connect.setConnection();
				connect.setStatement();

				String selectNguoiDung = "SELECT MaNguoiDung,TenDangNhap,MatKhau,TenNhom,TenNguoiDung FROM NguoiDung WHERE TenDangNhap='"
						+ textTendangnhap.getText() + "' AND MatKhau='" + getMD5(textMatkhau.getText()) + "'";
				ResultSet result = connect.getStatement().executeQuery(selectNguoiDung);
				int loginstatus = -1;
				while (result.next()) {
					if (textTendangnhap.getText().equals(result.getString(2))) {
						if (result.getString(4).equals("Disable")) {
							loginstatus = 0;
						} else {
							if (getMD5(textMatkhau.getText()).equals(result.getString(3))) {
								MainWindowManagerIP mainwindow = new MainWindowManagerIP();
								mainwindow.setLanguage(cbNgonngu.getSelectionIndex());
								mainwindow.setGroupName(result.getString(4), result.getString(1),result.getString(5), version);

								if (btnCheckRemember.getSelection()) {
									// ghi nhớ mật khẩu khi đăng nhập
									try {
										String decode = textTendangnhap.getText() + "\r\n"
												+ enCode(textMatkhau.getText());
										FileOutputStream outputStream = new FileOutputStream("login.conf");
										byte[] strToBytes = decode.getBytes();
										outputStream.write(strToBytes);
										outputStream.close();
									} catch (Exception ex) {
									}
								} else {
									// xóa file login.conf nếu không chọn lưu mật khẩu
									try {
										File f = new File("login.conf");
										if (f.exists()) {
											f.deleteOnExit();
										}
									} catch (Exception ex) {
									}
								}
								loginstatus = 1;
								shell.dispose(); // đóng form đăng nhập
								mainwindow.open();
							}
						}
					}
				}
				result.close();

				if (loginstatus == -1) {
					// Đăng nhập thất bại
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Tên đăng nhập hoặc mật khẩu không đúng!");
					} else if (ngonngu == 1) {
						thongbao.setText("Notice");
						thongbao.setMessage("Username or password is wrong!");
					}
					thongbao.open();
				} else if (loginstatus == 0) {
					// Đăng nhập thất bại vì user đang bị khóa disable
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					if (ngonngu == 0) {
						thongbao.setText("Thông báo");
						thongbao.setMessage("Tài khoản \""+textTendangnhap.getText()+"\" đã bị khóa!");
					} else if (ngonngu == 1) {
						thongbao.setText("Notice");
						thongbao.setMessage("\""+textTendangnhap.getText()+"\" account has been locked!");
					}
					thongbao.open();
				}

			} catch (Exception se) {
				MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				if (ngonngu == 0) {
					thongbao.setText("Thông báo lỗi");
					thongbao.setMessage(se.toString());
				} else if (ngonngu == 1) {
					thongbao.setText("Error");
					thongbao.setMessage(se.toString());
				}
				thongbao.open();
			} finally {
				try {
					if (connect.getStatement() != null) {
						connect.closeStatement();
					}
					if (connect.getConnection() != null) {
						connect.closeConnection();
					}

				} catch (Exception se2) {
				}
			}
		}
	}
}
