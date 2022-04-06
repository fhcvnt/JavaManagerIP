package table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class TableExmaple {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setSize(367, 249);
    shell.setLayout(new FillLayout());
    
    Table table = new Table(shell, SWT.BORDER | SWT.MULTI);
    table.setLinesVisible(true);
    
    table.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
	table.setHeaderBackground(SWTResourceManager.getColor(255, 215, 0));
	table.setHeaderVisible(true);
    
    TableColumn tbcSTT = new TableColumn(table, SWT.NONE);
	tbcSTT.setWidth(83);
	tbcSTT.setText("Combobox");

	TableColumn tbcMamuonThietBi = new TableColumn(table,SWT.NONE);
	tbcMamuonThietBi.setWidth(140);
	tbcMamuonThietBi.setResizable(false);
	tbcMamuonThietBi.setText("Name");

	TableColumn tbcSoThe = new TableColumn(table, SWT.NONE);
	tbcSoThe.setWidth(100);
	tbcSoThe.setText("Check box");
    
    
//    for (int i = 0; i < 3; i++) {
//      TableColumn column = new TableColumn(table, SWT.NONE);
//      column.setWidth(100);
//    }
    for (int i = 0; i < 12; i++) {
      new TableItem(table, SWT.NONE);
    }
    TableItem[] items = table.getItems();
    for (int i = 0; i < items.length; i++) {
    	// column 0
      TableEditor tableEditor = new TableEditor(table);
      CCombo combo = new CCombo(table, SWT.NONE);
      combo.setText("CCombo");
      combo.add("combo item 1");
      combo.add("combo item 2");
      tableEditor.grabHorizontal = true;
      tableEditor.setEditor(combo, items[i], 0);
      
      // column 1
      tableEditor = new TableEditor(table);
      Text text = new Text(table, SWT.NONE);
      text.setText("Text");
      tableEditor.grabHorizontal = true;
      tableEditor.setEditor(text, items[i], 1);
      
      // column 2
      tableEditor = new TableEditor(table);
      Button button = new Button(table, SWT.CHECK);
      button.pack();
      tableEditor.minimumWidth = button.getSize().x;
      tableEditor.horizontalAlignment = SWT.CENTER;
      tableEditor.setEditor(button, items[i], 2);
    }
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}