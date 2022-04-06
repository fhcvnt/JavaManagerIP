package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class DoiCoSo {

	protected Shell shell;
	private Text text;
	private Label lbResult2;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DoiCoSo window = new DoiCoSo();
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
		shell.setSize(555, 385);
		shell.setText("SWT Application");

		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		text.setBounds(65, 36, 361, 40);
		text.setText("554051654960");

		Label lbResult = new Label(shell, SWT.BORDER);
		lbResult.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lbResult.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lbResult.setBounds(65, 106, 361, 40);

		Button btnConvert = new Button(shell, SWT.NONE);
		btnConvert.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		btnConvert.setBounds(154, 238, 144, 47);
		btnConvert.setText("Convert");

		lbResult2 = new Label(shell, SWT.BORDER);
		lbResult2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbResult2.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lbResult2.setBounds(65, 175, 361, 40);

		btnConvert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String result = Long.toHexString(Long.parseLong(text.getText())) + "";
					lbResult.setText(result.toUpperCase());

					// chuyen thap luc phan sang thap phan
					// result=Long.parseLong(result, 16)+"";
					result = result.substring(4);
					lbResult2.setText(result);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});

	}
}
