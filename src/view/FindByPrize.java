package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import controller.DataController;
import model.Tournament;

public class FindByPrize {

	private DataController controller;
	private Shell shell;
	private Table table;
	PageRecords pageRecords;

	public FindByPrize(Display display, DataController controller) {
		this.controller = controller;
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		shell.setText("Find record by prize");
		shell.setBounds(150, 100, 500, 300);
		findByPrize();
		shell.open();
	}

	public void findByPrize() {
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

		Button findButton = new Button(shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(10, 130, 80, 20);

		findButton.addSelectionListener(new SelectionAdapter() {
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
					messageError.setMessage("No items accoarding to your request");
					messageError.open();
				} else {
					if (pageRecords == null) {
						pageRecords = new PageRecords();
						shell.setBounds(150, 100, 1000, 600);
						table = pageRecords.createTable(shell);
						table.setBounds(10, 150, 910, 250);
						pageRecords.fillTableByPages(shell, search, table);
					} else {
						pageRecords.fillTableByPages(shell, search, table);
					}
				}
				textLowerPrize.setText("");
				textUpperPrize.setText("");
				textLowerIncome.setText("");
				textUpperIncome.setText("");
			}

		});
	}

}
