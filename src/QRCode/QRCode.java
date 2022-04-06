package QRCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {
	public QRCode() {
	}

	// tạo ảnh QR code
	// -------------------------------------------------------------------------------------
	public static BufferedImage QRCodeGenerator(String content, int height, int width) {
		// content: là nội dung cần tạo mã QR
		// height, width: là chiều cao, chiều rộng của ảnh QR
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
			
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			graphics.setRenderingHints(rh);
			
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
