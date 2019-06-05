package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import controller.DataController;
import model.Tournament;
import model.TournamentDataBase;

public class MenuBar {

	private Display display;
	private Shell shell;
	private DataController controller;
	private DispalyComponents tableComponent;
	private Table table;
	private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };
	private List<Tournament> tournamentList;
	PageRecords pageRecords;

	public MenuBar(Shell shell, DataController controller, Table table) {
		this.shell = shell;
		this.controller = controller;
		this.table = table;
		toolBar();
	}

	public void toolBar() {
		Menu menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);

		MenuItem openItem = new MenuItem(menuBar, SWT.PUSH);
		openItem.setText("Open");

		openItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event e) {
				FileDialog dialogOpen = new FileDialog(shell, SWT.OPEN);
				String fileNameOpen = dialogOpen.open();
				File fileOpen = new File(fileNameOpen);
				try {
					controller.open(fileOpen);
				} catch (SAXException | ParserConfigurationException | IOException e1) {
					e1.printStackTrace();
				}
				pageRecords = new PageRecords(controller);
				pageRecords.fillTableByPages(shell, controller.getListOfTournaments(), table);
			}
		});

		MenuItem saveItem = new MenuItem(menuBar, SWT.PUSH);
		saveItem.setText("Save");

		saveItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				FileDialog dialogSave = new FileDialog(shell, SWT.SAVE);
				String fileNameSave = dialogSave.open();
				File fileSave = new File(fileNameSave);
				try {
					controller.save(fileSave);
				} catch (TransformerException | ParserConfigurationException e1) {
					e1.printStackTrace();
				}
			}
		});

		MenuItem addItem = new MenuItem(menuBar, SWT.PUSH);
		addItem.setText("Add");
		addItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				new AddDisplay(display, controller, shell, table);
			}
		});

		MenuItem searchItem = new MenuItem(menuBar, SWT.CASCADE);
		searchItem.setText("Search");

		Menu subSearch = new Menu(shell, SWT.DROP_DOWN);
		searchItem.setMenu(subSearch);

		MenuItem subFindByTournament = new MenuItem(subSearch, SWT.PUSH);
		subFindByTournament.setText("Find by tournament");
		subFindByTournament.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				new FindByTournament(display, controller);
			}
		});

		MenuItem subFindBySport = new MenuItem(subSearch, SWT.PUSH);
		subFindBySport.setText("Find by sport");
		subFindBySport.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				new FindBySport(display, controller);
			}
		});

		MenuItem subFindByPrize = new MenuItem(subSearch, SWT.PUSH);
		subFindByPrize.setText("Find by prize");
		subFindByPrize.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				new FindByPrize(display, controller);
			}
		});

		MenuItem deleteItem = new MenuItem(menuBar, SWT.CASCADE);
		deleteItem.setText("Delete");

		Menu subDelete = new Menu(shell, SWT.DROP_DOWN);
		deleteItem.setMenu(subDelete);

		MenuItem subDeleteByTournament = new MenuItem(subDelete, SWT.PUSH);
		subDeleteByTournament.setText("Delete by tournament");
		subDeleteByTournament.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				new DeleteByTournament(display, controller, shell, table);
			}
		});

		MenuItem subDeleteBySport = new MenuItem(subDelete, SWT.PUSH);
		subDeleteBySport.setText("Delete by sport");
		subDeleteBySport.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				new DeleteBySport(display, controller, shell, table);
			}
		});

		MenuItem subDeleteByPrize = new MenuItem(subDelete, SWT.PUSH);
		subDeleteByPrize.setText("Delete by prize");
		subDeleteByPrize.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				new DeleteByPrize(display, controller, shell, table);
			}
		});

	}
}
