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

	private List<Tournament> tournamentList;
	private Table table;
	private String[] tableTitles = {"Tournament", "Date", "Sport", "Winner", "Prize", "Income"};
	private TournamentDataBase dataBase;
	private Display display;
    
    public TableComponent(Shell shell, DataController controller) {
    	table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
    	dataBase = new TournamentDataBase();
    	RowData rowData = new RowData();
		rowData.height = 250;
		rowData.width = 900;
		table.setLayoutData(rowData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        for (int currTitle = 0; currTitle < tableTitles.length; currTitle++)
		{
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(150);
			column.setText(tableTitles[currTitle]);
			column.setResizable(false);
		}
        
        Button generate = new Button(shell, SWT.PUSH);
        generate.setText("Generate records");
                
        generate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	tournamentList = new ArrayList<Tournament>(controller.getListOfTournaments());
            	for(Tournament tournament : tournamentList) {
            		TableItem item = new TableItem(table, SWT.NONE);
                	item.setText(0, tournament.getName());
                	item.setText(1, tournament.getDate());
                	item.setText(2, tournament.getSport());
                	//item.setText(3, tournament.getWinner());
                	item.setText(4, Integer.toString(tournament.getPrizeAmount()));
                	//item.setText(5, winner.getIncome());
            	}
            }
        });
        
        Button addTournamentButton = new Button(shell, SWT.PUSH);
        addTournamentButton.setText("Add new tournament");
        
        addTournamentButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				new AddDisplay(display, controller);
			}
		});
        
        ToolBar bar = new ToolBar(shell, SWT.BORDER|SWT.VERTICAL);
        ToolItem itemFindByTournament = new ToolItem(bar, SWT.PUSH);
        itemFindByTournament.setText("Find by tournament");
        
        itemFindByTournament.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new FindByTournament(display, controller);
			}
		});
        
        ToolItem itemFindBySport = new ToolItem(bar, SWT.PUSH);
        itemFindBySport.setText("Find by sport");
        
        itemFindBySport.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new FindBySport(display, controller);
			}
		});
        
        ToolItem itemFindByPrize = new ToolItem(bar, SWT.PUSH);
        itemFindByPrize.setText("Find by prize");
        
        itemFindByPrize.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new FindByPrize(display, controller);
			}
		});
        
    }
   
}

