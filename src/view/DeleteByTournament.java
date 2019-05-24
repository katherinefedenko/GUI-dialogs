package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import controller.DataController;
import model.Tournament;

public class DeleteByTournament {

	private Display display;
	private DataController controller;
	private Shell shell;
	private Table table;
	private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };
	PageRecords pageRecords;

	public DeleteByTournament(Display display, DataController controller, Table table) {
		this.table = table;
		this.display = display;
		this.controller = controller;
		shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 10;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setText("Delete record by tournament or date");
		shell.setSize(1000, 500);
		shell.setLayout(rowLayout);
		deleteByTournament();
		shell.open();
	}

	public void deleteByTournament() {
		Label labelTournament = new Label(shell, SWT.NONE);
		labelTournament.setText("Tournament:");
		Text textTournament = new Text(shell, SWT.BORDER);

		Label labelDate = new Label(shell, SWT.NONE);
		labelDate.setText("Date:");
		Text textDate = new Text(shell, SWT.BORDER);

		Button deleteButton = new Button(shell, SWT.PUSH);
		deleteButton.setText("Delete");

		deleteButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				String tournament = textTournament.getText();
				String date = textDate.getText();
				List<Tournament> search = controller.findByTournamentName(tournament, date);

				if (search.isEmpty()) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("ERROR!");
					messageError.setMessage("No items accoarding request");
					messageError.open();
				} else {
					int removeTournamentAmount = controller.removeTournament(search);
					MessageBox messageError = new MessageBox(shell, SWT.ICON_INFORMATION);
					messageError.setText("DONE!");
					messageError.setMessage(removeTournamentAmount + " record(s) was/were removed");
					messageError.open();
					PageRecords pageRecords = new PageRecords();
					pageRecords.fillTable(shell, controller.getListOfTournaments(), table);
				}
				textTournament.setText("");
				textDate.setText("");

			}

		});
	}

}
