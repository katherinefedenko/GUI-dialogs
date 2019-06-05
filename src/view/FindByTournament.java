package view;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.DataController;
import model.Tournament;

public class FindByTournament {

	private DataController controller;
	private Shell shell;
	PageRecords pageRecords;
	private Table table;

	public FindByTournament(Display display, DataController controller) {

		this.controller = controller;
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		shell.setText("Find record by tournament");
		shell.setBounds(150, 100, 500, 300);
		findByTournament();
		shell.open();
	}

	public void findByTournament() {
		Label labelTournament = new Label(shell, SWT.NONE);
		labelTournament.setBounds(10, 10, 80, 20);
		labelTournament.setText("Tournament:");

		Text textTournament = new Text(shell, SWT.BORDER);
		textTournament.setBounds(10, 40, 200, 20);

		Label labelDate = new Label(shell, SWT.NONE);
		labelDate.setText("Date:");
		labelDate.setBounds(10, 70, 100, 20);

		Text textDate = new Text(shell, SWT.BORDER);
		textDate.setBounds(10, 90, 200, 20);

		Button findButton = new Button(shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(10, 120, 80, 20);

		findButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {

				String tournament = textTournament.getText();
				LocalDate date = LocalDate.parse(textDate.getText());
				List<Tournament> search = controller.findByTournamentName(tournament, date);

				if (search.isEmpty()) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("ERROR!");
					messageError.setMessage("No items accoarding your request");
					messageError.open();
				} else {
					if (pageRecords == null) {
						pageRecords = new PageRecords(controller);
						shell.setBounds(150, 100, 1000, 600);
						table = pageRecords.createTable(shell);
						table.setBounds(10, 150, 910, 250);
						pageRecords.fillTableByPages(shell, search, table);
					} else {
						pageRecords.fillTableByPages(shell, search, table);
					}

				}

				textTournament.setText("");
				textDate.setText("");
			}

		});
	}

}
