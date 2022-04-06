package excel;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.chainsaw.Main;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GetFileName {

	protected Shell shell;
	private Text textFilename;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GetFileName window = new GetFileName();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");

		Button btnGet = new Button(shell, SWT.NONE);
		btnGet.setBounds(103, 189, 96, 42);
		btnGet.setText("Get");

		textFilename = new Text(shell, SWT.BORDER);
		textFilename.setBounds(61, 42, 305, 36);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(61, 105, 305, 36);

		btnGet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					//File a = getJarFile();
					textFilename.setText(System.getProperty("java.class.path"));
					String filename=System.getProperty("java.class.path");
					filename=filename.substring(filename.lastIndexOf('\\')+1);
					text.setText(filename);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private File getJarFile() throws FileNotFoundException,NullPointerException {
		String path = Main.class.getResource(Main.class.getSimpleName() + ".class").getFile();
		if (path.startsWith("/")) {
			throw new FileNotFoundException("This is not a jar file: \n" + path);
		}
		path = ClassLoader.getSystemClassLoader().getResource(path).getFile();

		return new File(path.substring(0, path.lastIndexOf('!')));
	}
}
