package itmanagerip;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import sql.ConnectSQL;

public class GetIPCheckPing {
	private ConnectSQL connect;

	protected Shell shell;
	private Text textLayIP;
	private Table table;
	private int language = 1;
	private Text textIPPing;
	private String ipping = "192.168.30.123"; // IP dùng để ping thử xem có chạy không
	private InetAddress inet;
	private String ipAddress;
	private Runnable timer;
	private String textping = "";

	public void setLanguage(int ngonngu) {
		language = ngonngu;
	}

	public int getLanguage() {
		return language;
	}

	public static void main(String[] args) {
		try {
			GetIPCheckPing window = new GetIPCheckPing();
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
		shell = new Shell(SWT.MIN);
		shell.setImage(SWTResourceManager.getImage(GetIPCheckPing.class, "/itmanagerip/Icon/button/get ip25.png"));
		shell.setSize(845, 640);
		shell.setText("Get IP");

		textLayIP = new Text(shell, SWT.BORDER);
		textLayIP.setBackground(SWTResourceManager.getColor(224, 255, 255));
		textLayIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		textLayIP.setBounds(67, 21, 82, 35);
		textLayIP.setTextLimit(3);

		CLabel lbIP = new CLabel(shell, SWT.NONE);
		lbIP.setAlignment(SWT.RIGHT);
		lbIP.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lbIP.setBounds(10, 21, 51, 35);
		lbIP.setText("IP");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		table.setBounds(10, 62, 403, 529);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(82);
		tblclmnStt.setText("STT");

		TableColumn tblclmnIp = new TableColumn(table, SWT.NONE);
		tblclmnIp.setWidth(260);
		tblclmnIp.setText("IP");

		Button btnGetIP = new Button(shell, SWT.NONE);
		btnGetIP.setImage(SWTResourceManager.getImage(GetIPCheckPing.class, "/itmanagerip/Icon/button/get ip.png"));
		btnGetIP.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnGetIP.setFont(SWTResourceManager.getFont("Times New Roman", 16, SWT.BOLD));
		btnGetIP.setBounds(172, 20, 142, 35);
		btnGetIP.setText("Get IP");

		Button btnCheck = new Button(shell, SWT.NONE);
		btnCheck.setImage(SWTResourceManager.getImage(GetIPCheckPing.class, "/itmanagerip/Icon/button/check30.png"));
		btnCheck.setText("Check");
		btnCheck.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnCheck.setFont(SWTResourceManager.getFont("Times New Roman", 16, SWT.BOLD));
		btnCheck.setBounds(427, 21, 155, 35);

		Label label = new Label(shell, SWT.SEPARATOR);
		label.setBounds(419, 62, 2, 529);

		textIPPing = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textIPPing.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textIPPing.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		textIPPing.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		textIPPing.setBounds(427, 62, 396, 529);

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setImage(SWTResourceManager.getImage(GetIPCheckPing.class, "/itmanagerip/Icon/button/cancel30.png"));
		btnCancel.setText("Cancel");
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 16, SWT.BOLD));
		btnCancel.setBounds(599, 19, 148, 37);

		// xu ly ngon ngu
		// *******************************************************************************
		if (getLanguage() == 0) {
			// Tieng Viet
			btnGetIP.setText("Lấy IP");
			shell.setText("Lấy IP");
		} else {
			// Tieng Anh
			btnGetIP.setText("Get IP");
			shell.setText("Get IP");
		}

		// lớp IP chỉ cho nhập số
		textLayIP.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		// Xy ly Get IP
		// =========================================================================================
		btnGetIP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					if (connect == null) {
						connect = new ConnectSQL();
						connect.setConnection();
					}
					connect.setStatement();
					String select = "SELECT IP FROM DanhSachIP WHERE IP LIKE '%." + textLayIP.getText()
							+ ".%' ORDER BY IP ASC";
					ResultSet result = connect.getStatement().executeQuery(select);
					try {
						if (!textLayIP.getText().isEmpty() && Integer.parseInt(textLayIP.getText()) >= 0
								&& Integer.parseInt(textLayIP.getText()) <= 255) {
							table.removeAll();
							int stt = 0;
							ArrayList<String> danhsachip = new ArrayList<String>();
							String chuoiip = "192.168.";
							String lopip = textLayIP.getText();
							for (int i = 1; i <= 254; i++) {
								danhsachip.add(chuoiip + lopip + "." + i);
							}

							ArrayList<String> danhsachipcsdl = new ArrayList<String>();

							while (result.next()) {
								danhsachipcsdl.add(result.getString(1));
							}

							danhsachip.removeAll(danhsachipcsdl);
							for (String list : danhsachip) {
								stt++;
								TableItem item = new TableItem(table, SWT.NONE);
								item.setText(new String[] { stt + "", list });
							}
						}
					} catch (Exception ee) {
					}
					result.close();
					connect.closeStatement();
				} catch (Exception se) {
					MessageBox thongbao = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					if(language==0) {
						thongbao.setText("Thông báo lỗi!");
						thongbao.setMessage("Lỗi!\n"+se.toString());
					}else {
						thongbao.setText("Notice error!");
						thongbao.setMessage("Error!\n"+se.toString());
					}
					thongbao.open();
				}
			}
		});

		// Check IP, Ping IP
		btnCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemsua = table.getSelection();
					ipping = itemsua[0].getText(1);
					textping = "";
					if (!ipping.isEmpty()) {
						ipAddress = ipping;
						textping = textping + "Sending Ping Request to " + ipAddress + "";

						timer = new Runnable() {
							public void run() {
								try {
									inet = InetAddress.getByName(ipAddress);
									textIPPing.setText("");
									// inet = InetAddress.getByName(ipAddress);

									textIPPing.setText(textping);

									long finish = 0;
									long start = new GregorianCalendar().getTimeInMillis();

									if (inet.isReachable(5000)) {
										finish = new GregorianCalendar().getTimeInMillis();
										textping = textping + "\n" + "Reply from " + ipping + ": time < "
												+ (finish - start + 1) + "ms";
									} else {
										textping = textping + "\n" + "Request time out.";
									}
								} catch (Exception ex) {
								}
								textIPPing.setText(textping);
								textIPPing.setTopIndex(textIPPing.getLineCount() - 1);

								Display.getDefault().timerExec(1000, this);
							}
						};
						Display.getDefault().timerExec(1000, timer);
					}
				} catch (Exception exc) {

				}
			}
		});

		// button Cancel
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Display.getDefault().timerExec(-1, timer);
				} catch (Exception ex) {
				}
				ipping = "";
				textIPPing.setText(ipping);
			}
		});
	}
}
