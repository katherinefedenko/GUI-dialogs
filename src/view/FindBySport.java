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

public class FindBySport {

	private Display display;
	private DataController controller;
	private Shell shell;

	public FindBySport(Display display, DataController controller) {
		this.display = display;
		this.controller = controller;
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		GridLayout gridLayout = new GridLayout();
        gridLayout.marginLeft  = 10;
        gridLayout.marginRight = 5;
        gridLayout.numColumns  = 2;
		shell.setLayout(gridLayout);
		shell.setText("Find record by kind of sport");
		shell.setSize(300, 200);
		findBySport();
		shell.open();
	}

	public void findBySport() {
		Label labelSport = new Label(shell, SWT.NONE);
		labelSport.setText("Sport:");
		Text textSport = new Text(shell, SWT.BORDER);

		Button findButton = new Button (shell, SWT.PUSH);
		findButton.setText("Find");
		
		findButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				String sport = textSport.getText();
				controller.findBySport(sport);
				
				textSport.setText("");
			}
				
		});
	}

}
