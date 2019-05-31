package view;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import controller.DataController;
import model.Tournament;

public class DeleteBySport {
	
		private DataController controller;
		private Shell shell;
		private Shell mainShell;
		private Table mainTable;
		PageRecords pageRecords;

		public DeleteBySport(Display display, DataController controller, Shell mainShell, Table mainTable) {
			this.mainShell = mainShell;
			this.controller = controller;
			this.mainTable = mainTable;
			shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
			RowLayout rowLayout = new RowLayout();
			rowLayout.spacing = 10;
			rowLayout.marginLeft = 10;
			rowLayout.marginTop = 10;
			shell.setText("Delete record by tournament or date");
			shell.setSize(500, 300);
			shell.setLayout(rowLayout);
			deleteBySport();
			shell.open();
		}

		public void deleteBySport() {
			Label labelSport = new Label(shell, SWT.NONE);
			labelSport.setText("Sport:");
			Text textSport = new Text(shell, SWT.BORDER);

			/*Label labelDate = new Label(shell, SWT.NONE);
			labelDate.setText("Winner:");
			Text textDate = new Text(shell, SWT.BORDER);
			*/

			Button deleteButton = new Button(shell, SWT.PUSH);
			deleteButton.setText("Delete");

			deleteButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					String sport = textSport.getText();
					
					List<Tournament> search = controller.findBySport(sport);
					
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
						PageRecords pageRecords = new PageRecords(controller);
						//Table recreateMainTable = pageRecords.createTable(mainShell, mainTable);
						pageRecords.fillTableByPages(mainShell, controller.getListOfTournaments(), mainTable);
						
					}
					textSport.setText("");
				}

			});
		}
}
