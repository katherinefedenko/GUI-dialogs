package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import controller.DataController;
import model.Tournament;

public class FindBySport {

	private Display display;
	private DataController controller;
	private Shell shell;
	private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };

	public FindBySport(Display display, DataController controller) {
		this.display = display;
		this.controller = controller;
		shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 10;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setText("Find record by sport");
		shell.setSize(1000, 500);
		shell.setLayout(rowLayout);
		findBySport();
		shell.open();
	}

	public void findBySport() {
		Label labelSport = new Label(shell, SWT.NONE);
		labelSport.setText("Sport:");
		Text textSport = new Text(shell, SWT.BORDER);

		Button findButton = new Button(shell, SWT.PUSH);
		findButton.setText("Find");

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

		findButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				String sport = textSport.getText();
				List<Tournament> search = controller.findBySport(sport);

				if (search.isEmpty()) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("ERROR!");
					messageError.setMessage("No items accoarding your request");
					messageError.open();
				} else {
					System.out.println(search);
					PageRecords pageRecords = new PageRecords();
					pageRecords.fillTable(shell, search, table);

				}

				textSport.setText("");
			}

		});
	}

}
