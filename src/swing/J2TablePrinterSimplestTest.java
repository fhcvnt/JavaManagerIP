package swing;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import printerworks.J2Printer;
import printerworks.J2TablePrinter;

class J2TablePrinterSimplestTest {

	static public void main(String args[]) {

		Object[] columns = { "This", "is", "a", "JTable" };
		Object[][] data = { { "table", "", "", "" }, { "", "data", "", "" }, { "", "", "goes", "" },
				{ "", "", "", "here" } };
		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 83));

		JFrame frame = new JFrame("J2TablePrinter test");
		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setVisible(true);

		J2Printer printer = new J2Printer();
		printer.setSeparatePrintThread(false);
		J2TablePrinter tablePrinter = new J2TablePrinter(table);
		printer.addPageable(tablePrinter);

		printer.showPrintPreviewDialog();

		// System.exit(0);
	}
}
