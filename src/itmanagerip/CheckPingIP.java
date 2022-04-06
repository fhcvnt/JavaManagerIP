package itmanagerip;

import java.net.InetAddress;
import java.util.GregorianCalendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class CheckPingIP {

	protected Shell shell;
	private static Text textIP;
	private static Text textIPPing;
	private String textping = "";
	private String ipAddress;
	private int dem = -1; // nếu dem=-1 thì dừng lại, ngược lại cho ping chạy tiếp, dừng lại khi nhấn
							// Cancel

	public static void main(String[] args) {
		try {
			CheckPingIP window = new CheckPingIP();
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
		shell = new Shell();
		shell.setSize(555, 667);
		shell.setText("IP Scanner");

		CLabel lblIp = new CLabel(shell, SWT.NONE);
		lblIp.setText("IP");
		lblIp.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblIp.setAlignment(SWT.RIGHT);
		lblIp.setBounds(23, 6, 51, 25);

		textIP = new Text(shell, SWT.BORDER);
		textIP.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		textIP.setBounds(80, 6, 226, 25);
		textIP.setTextLimit(15);

		Button btnPing = new Button(shell, SWT.NONE);
		btnPing.setText("Ping");
		btnPing.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnPing.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.BOLD));
		btnPing.setBounds(329, 0, 87, 37);

		textIPPing = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textIPPing.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		textIPPing.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textIPPing.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		textIPPing.setBounds(10, 49, 519, 575);
		textIPPing.setEditable(false);

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnCancel.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.BOLD));
		btnCancel.setBounds(425, 0, 87, 37);

		// Xử lý Ping
		// =========================================================================================================================
		btnPing.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dem = 3;
				try {
					ipAddress = textIP.getText();
					textIPPing.setText("Sending Ping Request to " + ipAddress);
					while (dem > -1) {
						getTask2(textIPPing, dem).start();
						if (dem < 0) {
							break;
						}
						dem--;
					}
				} catch (Exception ee) {

				}
			}

		});

		// Xử lý Cancel
		// =========================================================================================================================
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dem = -1; // dừng lại ping
				textping = "";
				textIPPing.setText(textping);
				System.out.println(dem);
			}
		});

	}

	// Kiểm tra định dạng IP
	// ==============================================================================================================
	public static boolean validateIP(final String ip) {
		String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

		return ip.matches(PATTERN);
	}

	// Đa luồng
	public static Thread getTask2(Text text, int dem2) {
		final Text theText = text;
		int dem = dem2;
		return new Thread() {
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						String textping = theText.getText();
						String ipping = textIP.getText();
						if (validateIP(ipping)) {
							String ipAddress = ipping;
							// textping = textping + "Sending Ping Request to " + ipAddress + "";

							try {
								InetAddress inet = InetAddress.getByName(ipAddress);
								textIPPing.setText("");

								theText.setText(textping);

								long finish = 0;
								long start = new GregorianCalendar().getTimeInMillis();

								if (inet.isReachable(300)) {
									finish = new GregorianCalendar().getTimeInMillis();
									textping = textping + "\r\n" + "Reply from " + ipping + ": time < "
											+ (finish - start + 1) + "ms";
								} else {
									textping = textping + "\r\n" + "Request time out.";
								}
							} catch (Exception ex) {
							}
							theText.setText(textping);
							theText.setTopIndex(theText.getLineCount() - 1);
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							if (dem < 0) {
								return;
							}
							e.printStackTrace();
						}
					}
				});

			}
		};
	}

}
