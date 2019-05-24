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
import org.eclipse.swt.layout.GridLayout;
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
    private DispalyComponents table;
    private TournamentDataBase dataBase;
    private File file;
    private DataController controller;
    private String[] tableTitles = {"Tournament", "Date", "Sport", "Winner", "Prize", "Income"};
    
    public MainFrame(DataController controller) {
    	
		this.controller = controller;
		createMainWindow();
	}
    public void createMainWindow() {
    	shell.setText("Dialog UI");
    	shell.setBounds(150, 100, 1000, 600);
        
        Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 15, 910, 400);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
 
        
        for (int currTitle = 0; currTitle < tableTitles.length; currTitle++)
		{
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(150);
			column.setText(tableTitles[currTitle]);
			column.setResizable(false);
		}

        new DispalyComponents(shell, controller, table);
        new MenuBar(shell, controller, table);
        
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
    
}
