package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import controller.DataController;

public class AddDisplay {

	private Display display;
	private DataController controller;
	private Shell shell;

	public AddDisplay(Display display, DataController controller) {
		this.display = display;
		this.controller = controller;
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE );
		GridLayout gridLayout = new GridLayout(9, false);
        gridLayout.numColumns = 2;
        gridLayout.verticalSpacing = 8;
        shell.setLayout(gridLayout);
        shell.setText("Add information about tournament");
        shell.setSize(600, 500);
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
		
		Label labelWinner = new Label (shell, SWT.NONE);
		labelWinner.setText("Winner:");
		
		Text textWinner = new Text (shell, SWT.BORDER);
		
		Label labelPrize = new Label (shell, SWT.NONE);
		labelPrize.setText("Prize:");
		
		Text textPrize = new Text (shell, SWT.BORDER);
		
		Label labelIncome = new Label (shell, SWT.NONE);
		labelIncome.setText("Income:");
		
		Text textIncome = new Text (shell, SWT.BORDER);
		
		Button addButton = new Button (shell, SWT.PUSH);
		addButton.setText("Add");
		
		addButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				String tournament = textTournament.getText();
				String date = textDate.getText();
				String sport = textSport.getText();
				String winner = textWinner.getText();
				int prize = Integer.parseInt(textPrize.getText());
				String income = textIncome.getText();
				controller.addTournament(tournament, sport, prize, date);
				
				textTournament.setText("");
				textDate.setText("");
				textSport.setText("");
				textWinner.setText("");
				textPrize.setText("");
				textIncome.setText("");
			}
		});
		
	}
}
