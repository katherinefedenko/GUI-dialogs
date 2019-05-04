package view;

import java.awt.Menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import org.xml.sax.SAXException;

import model.Tournament;
import xml.XmlSaxParser;
import view.ToolBar;

public class MainFrame {
	private Display display = new Display();
    private Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
    private String[] tableTitles = {"Tournament", "Date", "Sport", "Winner", "Prize", "Income"};
    
    public MainFrame() throws ParserConfigurationException, SAXException, IOException {
    	shell.setText("PPvIS 2");
        shell.setSize(550, 550);

        RowLayout rowLayout = new RowLayout();
        rowLayout.spacing = 10;
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        shell.setLayout(rowLayout);
        
        ToolBar toolbar = new ToolBar(shell);

        Table table = new Table(shell, SWT.BORDER);
        RowData rowData = new RowData();
		rowData.height = 250;
		rowData.width = 400;
		table.setLayoutData(rowData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        for (int currTitle = 0; currTitle < tableTitles.length; currTitle++)
		{
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(120);
			column.setText(tableTitles[currTitle]);
			column.pack();
		}
        
        List<Tournament> tournamentList = new ArrayList<Tournament>(new XmlSaxParser().getTournamentList());
        
        for(Tournament tournament : tournamentList) {
        	TableItem item = new TableItem(table, SWT.NONE);
        	item.setText(0, tournament.getName());
        	item.setText(1, tournament.getDate());
        	item.setText(2, tournament.getSport());
        	
        	item.setText(4, Integer.toString(tournament.getPrizeAmount()));
        	
        	
        }
        
       

        
        
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
    
}
