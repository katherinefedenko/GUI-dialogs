package view;

import java.awt.Menu;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import org.xml.sax.SAXException;

import controller.DataController;
import model.Tournament;
import model.TournamentDataBase;
import parsers.XmlSaxParser;
import view.MenuBar;


public class MainFrame {
	
	private Display display = new Display();
    private Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
    private TableComponent table;
    private TournamentDataBase dataBase;
    private File file;
    private DataController controller;
    
    public MainFrame(DataController controller) {
    	
		this.controller = controller;
		createMainWindow();
	}
    public void createMainWindow() {
    	
    	shell.setText("Second lab");
        shell.setSize(700, 700);
    
        RowLayout rowLayout = new RowLayout();
        rowLayout.spacing = 10;
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        shell.setLayout(rowLayout);

        new TableComponent(shell, controller);
        new MenuBar(shell, controller);
        
        
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
    
}
