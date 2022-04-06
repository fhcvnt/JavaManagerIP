package QRCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeDevice {

	protected Shell shell;
	private Text textDeivice;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			QRCodeDevice window = new QRCodeDevice();
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
		shell.setSize(733, 409);
		shell.setText("SWT Application");

		Label lbDevice = new Label(shell, SWT.NONE);
		lbDevice.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lbDevice.setBounds(440, 52, 100, 100);

		Button btnCreate = new Button(shell, SWT.NONE);

		btnCreate.setBounds(312, 130, 75, 25);
		btnCreate.setText("Create");

		textDeivice = new Text(shell, SWT.BORDER | SWT.MULTI);
		textDeivice.setText(
				"Tên Thiết Bị: CPU\r\nTên – ST: \r\nSĐT: \r\nĐơn vị: \r\nNgày sử dụng: Ngày Sử dụng\r\nNgày Mua: Ngày Sử dụng\t\r\nMã số thiết bị: CPU");
		textDeivice.setFont(SWTResourceManager.getFont("Times New Roman", 13, SWT.NORMAL));
		textDeivice.setBounds(40, 52, 229, 200);

		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BufferedImage image = QRCodeGenerator(textDeivice.getText(), 100, 100);
				Image anotherImage = new Image(Display.getDefault(), AWTBufferedImageSWTImage.convertToSWT(image));
				lbDevice.setBackgroundImage(anotherImage);
			}
		});

	}

	// -------------------------------------------------------------------------------------
	private BufferedImage QRCodeGenerator(String content, int height, int width) {
		Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);

		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hintMap.put(EncodeHintType.MARGIN, 0);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BufferedImage bufferedImage = null;

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);

		try {
			BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			bufferedImage.createGraphics();

			Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, height);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
		} catch (WriterException e) {

		}

		return bufferedImage;
	}
}
