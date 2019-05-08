package view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;

import controller.DataController;
import model.Tournament;
import model.TournamentDataBase;

public class TableComponent {

	private List<Tournament> tournaments;
	private Table table;
	private String[] tableTitles = {"Tournament", "Date", "Sport", "Winner", "Prize", "Income"};
	private Label countRecords;
	private Label allRecords;
	private Label currentPages;
	private Label allPages;
	private int currentPage = 1;
    private int count = 0;
	private TournamentDataBase dataBase;
	private Display display;
    
    public TableComponent(Shell shell, DataController controller) {
    	table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
    	dataBase = new TournamentDataBase();
    	RowData rowData = new RowData();
		rowData.height = 250;
		rowData.width = 600;
		table.setLayoutData(rowData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        for (int currTitle = 0; currTitle < tableTitles.length; currTitle++)
		{
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(100);
			column.setText(tableTitles[currTitle]);
			column.setResizable(false);
		}
        
        ToolBar bar = new ToolBar(shell, SWT.BORDER|SWT.VERTICAL);
        ToolItem generate = new ToolItem(bar, SWT.PUSH);
        generate.setText("Generate records");
                
        generate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
            	List<Tournament> tournamentList = new ArrayList<Tournament>(controller.getListOfTournaments());
            	for(Tournament tournament : tournamentList) {
            		TableItem item = new TableItem(table, SWT.NONE);
                	item.setText(0, tournament.getName());
                	item.setText(1, tournament.getDate());
                	item.setText(2, tournament.getSport());
                	
                	item.setText(4, Integer.toString(tournament.getPrizeAmount()));
                	
            }
            }
        });
        
        
        ToolItem addTournamentButton = new ToolItem(bar, SWT.PUSH);
        addTournamentButton.setText("Add new tournament");
        
        addTournamentButton.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new AddDisplay(display, controller);
			}
		});
        
    }
    
    
    
}

