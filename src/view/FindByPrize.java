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
		Label labelPrize = new Label(shell, SWT.NONE);
		labelPrize.setText("Prize:");
		labelPrize.setBounds(10, 10, 80, 20);
		Text textPrize = new Text(shell, SWT.BORDER);
		textPrize.setBounds(10, 40, 200, 20);

		/*
		 * Label labelIncome = new Label(shell, SWT.NONE);
		 * labelIncome.setText("Income:"); 
		 * Text textIncome = new Text(shell,
		 * SWT.BORDER);
		 */

		Button findButton = new Button(shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(10, 120, 80, 20);

		findButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				// String income = textDate.getText();
				int prize = Integer.parseInt(textPrize.getText());
				List<Tournament> search = controller.findByPrize(prize);

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
				textPrize.setText("");
				// textIncome.setText("");
			}

		});
	}

}
