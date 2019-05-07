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
        
        Button generate = new Button(shell, SWT.PUSH);
        generate.setText("Generate");
        generate.setBounds(200, 500, 100, 30);
        //TableComponent table = new TableComponent(shell, controller);
        generate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
            	List<Tournament> tournamentList = new ArrayList<Tournament>(controller.getListOfTournaments());
            	for(Tournament tournament : tournamentList) {
            		System.out.println(tournament.getName());
                	TableItem item = new TableItem(table, SWT.NONE);
                	
                	item.setText(0, tournament.getName());
                	item.setText(1, tournament.getDate());
                	item.setText(2, tournament.getSport());
                	
                	item.setText(4, Integer.toString(tournament.getPrizeAmount()));
                	
            }
            }
        });
        
    }
    
    
    
}

