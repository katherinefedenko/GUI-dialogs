package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import controller.DataController;

public class FindByPrize {

	private Display display;
	private DataController controller;
	private Shell shell;

	public FindByPrize(Display display, DataController controller) {
		this.display = display;
		this.controller = controller;
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		GridLayout gridLayout = new GridLayout();
        gridLayout.marginLeft  = 10;
        gridLayout.marginRight = 5;
        gridLayout.numColumns  = 2;
		shell.setLayout(gridLayout);
		shell.setText("Find record by winner prize or income");
		shell.setSize(300, 200);
		findByPrize();
		shell.open();
	}

	public void findByPrize() {
		Label labelPrize = new Label(shell, SWT.NONE);
		labelPrize.setText("Prize:");
		Text textPrize = new Text(shell, SWT.BORDER);

		/*Label labelIncome = new Label(shell, SWT.NONE);
		labelIncome.setText("Income:");
		Text textIncome = new Text(shell, SWT.BORDER);*/
	
		Button findButton = new Button (shell, SWT.PUSH);
		findButton.setText("Find");
		
		findButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				//String income = textDate.getText();
				int prize = Integer.parseInt(textPrize.getText());
				controller.findByTournamentName(prize);
				
				textPrize.setText("");
				//textIncome.setText("");
			}
				
		});
	}

}
