package view;

import java.time.LocalDate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import controller.DataController;

public class AddDisplay {

	private DataController controller;
	private Shell shell;
	private Shell mainShell;
	private Table table;
	PageRecords pageRecords;

	public AddDisplay(Display display, DataController controller, Shell mainShell, Table table) {
		this.mainShell = mainShell;
		this.table = table;
		this.controller = controller;
		
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE );
		GridLayout gridLayout = new GridLayout();
        gridLayout.marginLeft  = 10;
        gridLayout.marginRight = 5;
        gridLayout.numColumns  = 2;
        shell.setLayout(gridLayout);
        shell.setText("Add information about tournament");
        shell.setSize(400, 400);
        addDisplay();
        shell.open();
	}

	public void addDisplay() {
		Label labelTournament = new Label (shell, SWT.NONE);
		labelTournament.setText("Tournament:");
		Text textTournament = new Text (shell, SWT.BORDER);
		
		Label labelDate = new Label (shell, SWT.NONE);
		labelDate.setText("Date:");
		Text textDate = new Text (shell, SWT.BORDER);
		
		Label labelSport = new Label (shell, SWT.NONE);
		labelSport.setText("Sport:");
		Text textSport = new Text (shell, SWT.BORDER);
		
		Label labelFirstName = new Label (shell, SWT.NONE);
		labelFirstName.setText("FirstName:");
		Text textFirstName = new Text (shell, SWT.BORDER);
		
		Label labelLastName = new Label (shell, SWT.NONE);
		labelLastName.setText("LastName:");
		Text textLastName = new Text (shell, SWT.BORDER);
		
		Label labelPrize = new Label (shell, SWT.NONE);
		labelPrize.setText("Prize:");
		Text textPrize = new Text (shell, SWT.BORDER);
		
		/*Label labelIncome = new Label (shell, SWT.NONE);
		labelIncome.setText("Income:");
		Text textIncome = new Text (shell, SWT.BORDER);
		*/
		Button addButton = new Button (shell, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				String tournament = textTournament.getText();
				LocalDate date = LocalDate.parse(textDate.getText());
				String sport = textSport.getText();
				String firstName = textFirstName.getText();
				String lastName = textLastName.getText();
				int prize = Integer.parseInt(textPrize.getText());
				//int income = Integer.parseInt(textIncome.getText());
				
				controller.addTournament(tournament, sport, prize, date, firstName, lastName);
				pageRecords = new PageRecords();
				pageRecords.fillTableByPages(mainShell, controller.getListOfTournaments(), table);
				
				textTournament.setText("");
				textDate.setText("");
				textSport.setText("");
				textFirstName.setText("");
				textLastName.setText("");
				textPrize.setText("");
				//textIncome.setText("");
			}
		});
		
	}
}
