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

import controller.DataController;
import model.Tournament;
import model.TournamentDataBase;

public class PageRecords {
	private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };
	private DataController controller;
	
	public void fillTable(Shell shell, List<Tournament> tournamentList, Table table) {
		for (Tournament tournament : tournamentList) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, tournament.getName());
			item.setText(1, tournament.getDate());
			item.setText(2, tournament.getSport());
			// item.setText(3, tournament.getWinner());
			item.setText(4, Integer.toString(tournament.getPrizeAmount()));
			// item.setText(5, winner.getIncome());
		}
	}

	/*public Table createTable(Shell shell) {
		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 15, 910, 300);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		for (int currTitle = 0; currTitle < tableTitles.length; currTitle++) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(150);
			column.setText(tableTitles[currTitle]);
			column.setResizable(false);
		}
		return table;
	}*/
}
