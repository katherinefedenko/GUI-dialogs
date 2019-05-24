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

public class DeleteByPrize {
	
		private Display display;
		private DataController controller;
		private Shell shell;
		private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };
		PageRecords pageRecords;

		public DeleteByPrize(Display display, DataController controller) {

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
			deleteBySport();
			shell.open();
		}

		public void deleteBySport() {
			Label labelPrize = new Label(shell, SWT.NONE);
			labelPrize.setText("Prize:");
			Text textPrize = new Text(shell, SWT.BORDER);

			/*Label labelDate = new Label(shell, SWT.NONE);
			labelDate.setText("Winner:");
			Text textDate = new Text(shell, SWT.BORDER);
			*/

			Button deleteButton = new Button(shell, SWT.PUSH);
			deleteButton.setText("Delete");

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

			deleteButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					int prize = Integer.parseInt(textPrize.getText());
					List<Tournament> search = controller.findByPrize(prize);
					
					if (search.isEmpty()) {
						MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
						messageError.setText("ERROR!");
						messageError.setMessage("No items accoarding request");
						messageError.open();
					} else {
						int removeTournamentAmount = controller.removeStudent(search);
						MessageBox messageError = new MessageBox(shell, SWT.ICON_INFORMATION);
						messageError.setText("DONE!");
						messageError.setMessage(removeTournamentAmount + " record(s) was/were removed");
						messageError.open();
						PageRecords pageRecords = new PageRecords();
						pageRecords.fillTable(shell, controller.getListOfTournaments(), table);
						
					}
					textPrize.setText("");
					

				}

			});
		}



}