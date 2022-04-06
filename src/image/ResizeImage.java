package image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ResizeImage {
	public ResizeImage() {

	}

// ***********************************************************************************************************
// Resize hinh anh
	public static Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		image.dispose(); // don't forget about me!
		return scaled;
	}
	
	// Resize hinh anh
		public static Image resizeHasAuthorized(Image image,Image imageAuthorized, int width, int height) {
			Image scaled = new Image(Display.getDefault(), width, height);
			GC gc = new GC(scaled);
			gc.setAntialias(SWT.ON);
			gc.setInterpolation(SWT.HIGH);
			gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
			gc.drawImage(imageAuthorized, 0, 0, imageAuthorized.getBounds().width, imageAuthorized.getBounds().height, 0, 0, width, height);
			gc.dispose();
			image.dispose(); // don't forget about me!
			imageAuthorized.dispose();
			return scaled;
		}
}
