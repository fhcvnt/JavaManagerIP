package print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class PrintCompositet {

	protected Shell shell;
	private Text text;
	private String jobName = "aaaaa";

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PrintCompositet window = new PrintCompositet();
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
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(1056, 1194);
		shell.setText("SWT Application");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBounds(10, 41, 768, 1122);

		text = new Text(composite, SWT.BORDER);
		text.setBounds(20, 39, 76, 21);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(63, 103, 55, 15);
		lblNewLabel.setText("New Label");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		lblNewLabel_1.setBounds(20, 981, 55, 15);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("New Label");
		lblNewLabel_2.setBounds(703, 995, 55, 15);

		Button btnPrint = new Button(shell, SWT.NONE);
		btnPrint.setBounds(82, 10, 75, 25);
		btnPrint.setText("Print");

		btnPrint.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				Image image = new Image(Display.getDefault(), 500, 500);
//				GC imageGC = new GC(image);
//				composite.print(imageGC);

//				PrintDialog dialog = new PrintDialog(shell, SWT.NONE);
//				PrinterData printerData = dialog.open();
//				print(composite);

				GC gccomposite = new GC(composite);
				Rectangle eclipseWindow = composite.getBounds();
				Image image = new Image(Display.getDefault(), eclipseWindow);
				gccomposite.copyArea(image, eclipseWindow.x, eclipseWindow.y);
				gccomposite.dispose();

				ImageData imageData = image.getImageData();

				PrintDialog dialog = new PrintDialog(shell, SWT.NULL);
				PrinterData printerData = dialog.open();

				Printer printer = new Printer(printerData);

				Point screenDPI = Display.getDefault().getDPI();
				Point printerDPI = printer.getDPI();
				int scaleFactor = printerDPI.x / screenDPI.x;

				Rectangle trim = printer.computeTrim(0, 0, 0, 0);

				if (printer.startJob("fileName")) {
					if (printer.startPage()) {
						GC gc = new GC(printer);
						Image printerImage = new Image(printer, imageData);

						// Draw the image
						gc.drawImage(printerImage, 0, 0, imageData.width, imageData.height, -trim.x, -trim.y,
								scaleFactor * imageData.width, scaleFactor * imageData.height);

						// Clean up
						printerImage.dispose();
						gc.dispose();
						printer.endPage();
					}
				}
				// End the job and dispose the printer
				printer.endJob();
				printer.dispose();

			}
		});

	}

	public void ChartPrintJob(String jobName) {
		this.jobName = jobName;
	}

	public void print(Composite elementToPrint) {
		PrintDialog dialog = new PrintDialog(elementToPrint.getShell());
		PrinterData printerData = dialog.open();
		if (printerData == null) {
			return; // Anwender hat abgebrochen.
		}
		startPrintJob(elementToPrint, printerData);
	}

	protected void startPrintJob(Composite elementToPrint, PrinterData printerData) {
		Printer printer = new Printer(printerData);
		try {
			printer.startJob(jobName);

			GC gc = new GC(printer);
			try {
				Rectangle printArea = getPrintableArea(printer, SWT.BORDER);
				printer.startPage();
				printComposite(elementToPrint, gc, printArea);
				printer.endPage();
			} finally {
				gc.dispose();
			}
			printer.endJob();

		} finally {
			printer.dispose();
		}
	}

	private Rectangle getPrintableArea(Printer printer, double safetyBorder) {
		int safetyBorderWidth = (int) (safetyBorder * printer.getDPI().x);
		int safetyBorderHeight = (int) (safetyBorder * printer.getDPI().y);

		Rectangle trim = printer.computeTrim(0, 0, 0, 0);
		int trimLeft = -trim.x;
		int trimTop = -trim.y;
		int trimRight = trim.x + trim.width;
		int trimBottom = trim.y + trim.height;

		int marginLeft = Math.max(trimLeft, safetyBorderWidth);
		int marginRight = Math.max(trimRight, safetyBorderWidth);
		int marginTop = Math.max(trimTop, safetyBorderHeight);
		int marginBottom = Math.max(trimBottom, safetyBorderHeight);

		int availWidth = printer.getClientArea().width - marginLeft - marginRight;
		int availHeight = printer.getClientArea().height - marginTop - marginBottom;
		return new Rectangle(marginLeft, marginTop, availWidth, availHeight);
	}

	private void printComposite(Composite elementToPrint, GC gc, Rectangle printArea) {
		Image image = new Image(elementToPrint.getDisplay(), elementToPrint.getSize().x, elementToPrint.getSize().y);
		try {
			GC imageGC = new GC(image);
			try {
				elementToPrint.print(imageGC);
				Point fittedSize = calcFittedSize(printArea, elementToPrint.getSize());
				gc.drawImage(image, 0, 0, elementToPrint.getSize().x, elementToPrint.getSize().y, printArea.x,
						printArea.y, fittedSize.x, fittedSize.y);

			} finally {
				imageGC.dispose();
			}
		} finally {
			image.dispose();
		}
	}

	/**
	 * The object to print should be scaled up or down to fit horizontally and
	 * vertically the available space.
	 * 
	 * @param printArea
	 * @param originalSize
	 * 
	 * @return the fitted size of the object to print
	 */
	Point calcFittedSize(Rectangle printArea, Point originalSize) {
		double scaleFactor = Math.min((double) printArea.height / originalSize.y,
				(double) printArea.width / originalSize.x);
		int trgHeight = (int) Math.ceil(originalSize.y * scaleFactor);
		int trgWidth = (int) Math.ceil(originalSize.x * scaleFactor);
		return new Point(trgWidth, trgHeight);
	}
}
