package excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* Imports */
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
 
/***********************************************************************/
/*** Tests reading and writing SWT Images from an SQLite Database    ***/
/***********************************************************************/
public class ImageTest {
 
    Shell shell;
 
    //Variables to store the current values when editing
    private Canvas personPhoto;
    private Image personImage;
    private int personID = 1;
    private String db_url4 = "jdbc:sqlserver://192.168.30.4;databaseName=HRIS;user=ipuser;password=ipuser@202010";
 
    private double photoWidth = 100;
    private double photoHeight = 100;
 
    //Database connection and statement variables
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
 
    public ImageTest(Shell parent, Connection passedConnection) {
        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
        shell.setLayout(new GridLayout());
        connection = passedConnection;
    }
    public static void main(String[] args) throws SQLException {
    	Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.30.4;databaseName=HRIS;user=ipuser;password=ipuser@202010");
		ImageTest image=new ImageTest(null, conn);
		image.createControlButtons();
	}
 
    private void createControlButtons() {
        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
 
        Button okButton = new Button(composite, SWT.PUSH);
        okButton.setText("OK");
        okButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if(personID > 0){
                    try {
                        PreparedStatement ps = connection.prepareStatement("UPDATE person SET photo = ? " +
                                                                                              "WHERE person_id = ?");
                        ps.setBytes(1, personImage.getImageData().data);
                        ps.setInt(2, personID);
                        ps.executeUpdate();
                        ps.close();
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                } else {
                    try {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO person (photo) VALUES (?)");
                        ps.setBytes(1, personImage.getImageData().data);
                        ps.executeUpdate();
                        ps.close();
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                }               
                shell.close();
            }
        });
 
        Button cancelButton = new Button(composite, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
 
        shell.setDefaultButton(okButton);
    }
 
    private void createTextWidgets(final Display display) {
 
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        shell.setLayout(gridLayout);
        new Label(shell, SWT.NONE).setText("Photo:");
 
        personPhoto = new Canvas(shell, SWT.BORDER);
        GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
        gridData.widthHint = (int)photoWidth;
        gridData.heightHint = (int)photoHeight;
        gridData.verticalSpan = 5;
        gridData.horizontalSpan = 2;
        personPhoto.setLayoutData(gridData);
        personPhoto.redraw();
 
        personPhoto.addPaintListener(new PaintListener() {
            public void paintControl(final PaintEvent event) {
                if (personImage != null) {
                    event.gc.drawImage(personImage, 0, 0);
                }
            }
        });
 
        //Skip a Column
        new Label(shell, SWT.NONE);
 
        Button browse = new Button(shell, SWT.PUSH);
        browse.setText("Browse...");
        gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridData.horizontalIndent = 5;
        browse.setLayoutData(gridData);
        browse.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                String fileName = new FileDialog(shell).open();
                if (fileName != null) {
                    personImage = new Image(display, fileName);
                    personPhoto.redraw();
                }
            }
        });
 
        Button delete = new Button(shell, SWT.PUSH);
        delete.setText("Delete");
        gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, false);
        gridData.horizontalIndent = 5;
        delete.setLayoutData(gridData);
        delete.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                if (personImage != null) {
                    personImage.dispose();
                    personImage = null;
                    personPhoto.redraw();
                }
            }
        });
    }
 
    public void open() {
        Display display = shell.getDisplay();
        //To avoid null pointer exceptions
        personImage = new Image(display,"user.png");
 
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT photo FROM person WHERE person_id = ?");
            ps.setInt(1, personID);
            rs = ps.executeQuery();
            while (rs.next()) {
                //dispose of the current image
                personImage.dispose();
                personImage = new Image(display, (int) photoWidth, (int) photoHeight);
                //Retrieve the photo for this person
                personImage.getImageData().data = rs.getBytes("photo");
            }
            ps.close();
            rs.close();    
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        createTextWidgets(display);
        createControlButtons();
        shell.pack();
        shell.open();
        while(!shell.isDisposed()){
            if(!display.readAndDispatch())
                display.sleep();
        }
    }
}