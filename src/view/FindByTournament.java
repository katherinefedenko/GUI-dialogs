package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import controller.DataController;
import model.TournamentDataBase;
import model.Tournament;

public class FindByTournament {

	private Display display;
	private DataController controller;
	private Shell shell;
	private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };
	PageRecords pageRecords;

	public FindByTournament(Display display, DataController controller) {

		this.display = display;
		this.controller = controller;

		shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 10;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setText("Find record by tournament");
		shell.setSize(1000, 500);
		shell.setLayout(rowLayout);

		findByTournament();
		shell.open();
	}

	public void findByTournament() {
		Label labelTournament = new Label(shell, SWT.NONE);
		labelTournament.setText("Tournament:");
		Text textTournament = new Text(shell, SWT.BORDER);

		Label labelDate = new Label(shell, SWT.NONE);
		labelDate.setText("Date:");
		Text textDate = new Text(shell, SWT.BORDER);

		Button findButton = new Button(shell, SWT.PUSH);
		findButton.setText("Find");

		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		RowData rowData = new RowData();
		rowData.height = 250;
		rowData.width = 900;
		table.setLayoutData(rowData);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		for (int currTitle = 0; currTitle < tableTitles.length; currTitle++) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(150);
			column.setText(tableTitles[currTitle]);
			column.setResizable(false);
		}

		findButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {

				String tournament = textTournament.getText();
				String date = textDate.getText();
				List<Tournament> search = controller.findByTournamentName(tournament, date);

				if (search.isEmpty()) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("ERROR!");
					messageError.setMessage("No items accoarding your request");
					messageError.open();
				} else {
					pageRecords = new PageRecords();
					pageRecords.fillTable(shell, search, table);
				}

				textTournament.setText("");
				textDate.setText("");
			}

		});
	}

}
