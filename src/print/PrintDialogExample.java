package print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PrintDialogExample {
  Display d;

  Shell s;

  PrintDialogExample() {
    d = new Display();
    s = new Shell(d);
    s.setSize(400, 400);
    
    s.setText("A PrintDialog Example");
    s.setLayout(new FillLayout(SWT.VERTICAL));
    final Text t = new Text(s, SWT.BORDER | SWT.MULTI);
    final Button b = new Button(s, SWT.PUSH | SWT.BORDER);
    b.setText("Print");
    b.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        PrintDialog printDialog = new PrintDialog(s, SWT.NONE);
        printDialog.setText("Print");
        PrinterData printerData = printDialog.open();
        if (!(printerData == null)) {
          Printer p = new Printer(printerData);
          p.startJob("PrintJob");
          p.startPage();
          Rectangle trim = p.computeTrim(0, 0, 0, 0);
          Point dpi = p.getDPI();
          int leftMargin = dpi.x + trim.x;
          int topMargin = dpi.y / 2 + trim.y;
          GC gc = new GC(p);
          Font font = gc.getFont();
          String printText = t.getText();
          gc.stringExtent(printText);
          gc.drawString(printText, leftMargin, topMargin
              + font.getFontData()[0].getHeight());
          p.endPage();
          gc.dispose();
          p.endJob();
          p.dispose();
        }

      }
    });
    s.open();

    while (!s.isDisposed()) {
      if (!d.readAndDispatch())
        d.sleep();
    }
    d.dispose();
  }

  public static void main(String[] argv) {
    new PrintDialogExample();
  }
}