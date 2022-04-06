package itmanagerip;

import java.net.InetAddress;
import java.util.GregorianCalendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class CheckIP {

	protected Shell shellCheckIp;
	private static Text textIPPing;
	private String ip = "192.168.30.123";
	private int demclose=-1;

	public static void main(String[] args) {
		try {
			CheckIP window = new CheckIP();
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
		shellCheckIp.open();
		shellCheckIp.layout();

		textIPPing.setText("Sending Ping Request to " + ip);
		int dem = 0;
		try {
			while (dem < 5) {
				getPingIP(textIPPing, ip,demclose,shellCheckIp).setDaemon(true);
				getPingIP(textIPPing, ip,demclose,shellCheckIp).start();
				demclose=dem;
				dem++;
			}
		} catch (Exception e) {
		}

		while (!shellCheckIp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellCheckIp = new Shell(SWT.MIN);
		shellCheckIp.setImage(SWTResourceManager.getImage(CheckIP.class, "/itmanagerip/Icon/button/get ip25.png"));
		shellCheckIp.setSize(437, 667);
		shellCheckIp.setText("Check IP");

		textIPPing = new Text(shellCheckIp, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textIPPing.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		textIPPing.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textIPPing.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		textIPPing.setBounds(10, 10, 406, 614);
		textIPPing.setEditable(false);
	}

	public void setIP(String ipaddress) {
		ip = ipaddress;
	}

	// Kiểm tra định dạng IP
	// ==============================================================================================================
	public static boolean validateIP(final String ip) {
		String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

		return ip.matches(PATTERN);
	}

	// Đa luồng
	public static Thread getPingIP(Text text, String ip,int demclosethread,Shell shell) {
		final Text theText = text;
		return new Thread() {
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						try {
							String textping = theText.getText();
							String ipping = ip;
							if (validateIP(ipping)) {
								String ipAddress = ipping;

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
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} catch (Exception ee) {

						}
						try {
							if(demclosethread==3) {
								shell.dispose();
							}
						}catch(Exception exception) {
							
						}
					}
				});

			}
		};
	}

}
