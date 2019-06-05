package view;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import controller.DataController;
import model.Tournament;

public class DeleteByPrize {
	
		private DataController controller;
		private Shell shell;
		private Shell mainShell;
		private Table mainTable;
		PageRecords pageRecords;

		public DeleteByPrize(Display display, DataController controller, Shell mainShell, Table mainTable) {
			this.controller = controller;
			this.mainShell = mainShell;
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
			Label labelLowerPrize = new Label(shell, SWT.NONE);
			labelLowerPrize.setText("Lower prize:");
			labelLowerPrize.setBounds(10, 10, 80, 20);

			Text textLowerPrize = new Text(shell, SWT.BORDER);
			textLowerPrize.setBounds(10, 40, 200, 20);

			Label labelUpperPrize = new Label(shell, SWT.NONE);
			labelUpperPrize.setText("Upper prize:");
			labelUpperPrize.setBounds(220, 10, 80, 20);

			Text textUpperPrize = new Text(shell, SWT.BORDER);
			textUpperPrize.setBounds(220, 40, 200, 20);

			Label labelLowerIncome = new Label(shell, SWT.NONE);
			labelLowerIncome.setText("Lower income:");
			labelLowerIncome.setBounds(10, 70, 80, 20);

			Text textLowerIncome = new Text(shell, SWT.BORDER);
			textLowerIncome.setBounds(10, 100, 200, 20);

			Label labelUpperIncome = new Label(shell, SWT.NONE);
			labelUpperIncome.setText("Upper income:");
			labelUpperIncome.setBounds(220, 70, 80, 20);

			Text textUpperIncome = new Text(shell, SWT.BORDER);
			textUpperIncome.setBounds(220, 100, 200, 20);

			Button deleteButton = new Button(shell, SWT.PUSH);
			deleteButton.setText("Delete");

			deleteButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					int lowerIncome;
					int upperIncome;
					int lowerPrize;
					int upperPrize;
					try {
						lowerIncome = Integer.parseInt(textLowerIncome.getText());
						upperIncome = Integer.parseInt(textUpperIncome.getText());
					} catch (NumberFormatException ex) {
						lowerIncome = 0;
						upperIncome = 0;

					}
					try {
						lowerPrize = Integer.parseInt(textLowerPrize.getText());
						upperPrize = Integer.parseInt(textUpperPrize.getText());
					} catch (NumberFormatException ex) {
						lowerPrize = 0;
						upperPrize = 0;
					}

					List<Tournament> search = controller.findByPrize(lowerPrize, upperPrize, lowerIncome, upperIncome);

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
					textLowerPrize.setText("");
					textUpperPrize.setText("");
					textLowerIncome.setText("");
					textUpperIncome.setText("");
				}

			});
		}



}
