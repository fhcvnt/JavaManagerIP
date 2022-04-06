package QRCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class TestQRCode {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestQRCode window = new TestQRCode();
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
		shell.setSize(422, 383);
		shell.setText("SWT Application");

		Button btnCreateQrCode = new Button(shell, SWT.NONE);
		btnCreateQrCode.setBounds(38, 250, 134, 34);
		btnCreateQrCode.setText("Create QR Code");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNewLabel.setBounds(85, 29, 200, 200);

		Button btnCreate = new Button(shell, SWT.NONE);

		btnCreate.setBounds(233, 259, 75, 25);
		btnCreate.setText("Create");

		btnCreateQrCode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String data = "to ngoc tri";
				QRCodeWriter qrCodeWriter = new QRCodeWriter();
				BitMatrix matrix = null;
				try {
					matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
				} catch (WriterException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// Write to file image
				String outputFile = "D:/image.png";
				Path path = FileSystems.getDefault().getPath(outputFile);
				try {
					MatrixToImageWriter.writeToPath(matrix, "PNG", path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					// Write to byte array
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);

					byte[] pngByteArray = outputStream.toByteArray();

					BufferedImage image = ImageIO.read(new ByteArrayInputStream(pngByteArray));
					Image anotherImage = new Image(Display.getDefault(), AWTBufferedImageSWTImage.convertToSWT(image));
					lblNewLabel.setBackgroundImage(anotherImage);

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BufferedImage image = QRCodeGenerator("To thanh huu",200,200);
				Image anotherImage = new Image(Display.getDefault(), AWTBufferedImageSWTImage.convertToSWT(image));
				lblNewLabel.setBackgroundImage(anotherImage);
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

		try {
			BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
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
