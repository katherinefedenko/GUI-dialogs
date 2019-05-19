package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import model.Tournament;
import model.TournamentDataBase;

public class PageRecords {
	private List<Tournament> tournamentList;
	private Table table;
	private String[] tableTitles = {"Tournament", "Date", "Sport", "Winner", "Prize", "Income"};
	private Shell shell;
	private Label countRecords;
	
	public PageRecords() {        
  
	}
	public void createTable(Shell shell, List<Tournament> searchList, Table table) {
        for (int currTitle = 0; currTitle < tableTitles.length; currTitle++)
		{
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(150);
			column.setText(tableTitles[currTitle]);
			column.setResizable(false);
		}
        for(Tournament tournament : searchList) {
    		TableItem item = new TableItem(table, SWT.NONE);
    		System.out.println(tournament.getName());
        	item.setText(0, tournament.getName());
        	item.setText(1, tournament.getDate());
        	item.setText(2, tournament.getSport());
        	//item.setText(3, tournament.getWinner());
        	item.setText(4, Integer.toString(tournament.getPrizeAmount()));
        	//item.setText(5, winner.getIncome());
    	}
	}
}
