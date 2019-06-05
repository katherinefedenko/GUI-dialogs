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

public class FindBySport {

	private DataController controller;
	private Shell shell;
	private Table table;
	PageRecords pageRecords;
	
	public FindBySport(Display display, DataController controller) {
		this.controller = controller;
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		shell.setText("Find record by sport");
		shell.setBounds(150, 100, 500, 300);
		findBySport();
		shell.open();
	}

	public void findBySport() {
		Label labelSport = new Label(shell, SWT.NONE);
		labelSport.setText("Sport:");
		labelSport.setBounds(10, 10, 80, 20);
		
		Text textSport = new Text(shell, SWT.BORDER);
		textSport.setBounds(10, 40, 200, 20);
		
		Label labelFirstName = new Label(shell, SWT.NONE);
		labelFirstName.setText("First Name:");
		labelFirstName.setBounds(10, 70, 100, 20);

		Text textFirstName = new Text(shell, SWT.BORDER);
		textFirstName.setBounds(10, 100, 200, 20);
		
		Label labelLastName = new Label(shell, SWT.NONE);
		labelLastName.setText("Last Name:");
		labelLastName.setBounds(10, 130, 100, 20);

		Text textLastName = new Text(shell, SWT.BORDER);
		textLastName.setBounds(10, 160, 200, 20);

		Button findButton = new Button(shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(10, 190, 80, 20);

		findButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				String sport = textSport.getText();
				String firstName = textFirstName.getText();
				String lastName = textLastName.getText();
				List<Tournament> search = controller.findBySport(sport, firstName, lastName);

				if (search.isEmpty()) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("ERROR!");
					messageError.setMessage("No items accoarding to your request");
					messageError.open();
				} else {
					if(pageRecords == null) {
						pageRecords = new PageRecords(controller);
						shell.setBounds(150, 100, 1000, 600);
						table = pageRecords.createTable(shell);
						table.setBounds(10, 150, 910, 250);
						pageRecords.fillTableByPages(shell, search, table);
					}
					else {
						pageRecords.fillTableByPages(shell, search, table);
					}
				}
				textSport.setText("");
			}

		});
	}

}
