package view;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;



import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import controller.DataController;
import model.TournamentDataBase;



public class MenuBar {
	
	private Shell shell;
	private DataController controller;
	private TableComponent tableComponent;
	 
	public MenuBar(Shell shell, DataController controller) {
		this.shell = shell;
		this.controller = controller;
		toolBar();
	}
	
	public void toolBar() {
	Menu menuBar = new Menu (shell, SWT.BAR);
	shell.setMenuBar(menuBar);

	MenuItem openItem = new MenuItem (menuBar, SWT.PUSH);
	openItem.setText ("Open");
	
	openItem.addListener (SWT.Selection, new Listener () {
	    

		@Override
	    public void handleEvent (Event e) {
			FileDialog dialogOpen = new FileDialog(shell, SWT.OPEN);
			String fileNameOpen = dialogOpen.open();
			File fileOpen = new File(fileNameOpen);
			try {
				controller.open(fileOpen);
				
			} catch (SAXException | ParserConfigurationException | IOException e1) {
				e1.printStackTrace();
			}
	    }
	});
			
	MenuItem saveItem = new MenuItem(menuBar, SWT.PUSH);
	saveItem.setText("Save");
	
	saveItem.addListener (SWT.Selection, new Listener () {
		@Override
	    public void handleEvent (Event e) {
			FileDialog dialogOpen = new FileDialog(shell, SWT.OPEN);
			String fileNameOpen = dialogOpen.open();
			File fileOpen = new File(fileNameOpen);
			try {
				controller.save(fileOpen);
			} catch (TransformerException | ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	});
	
	MenuItem addItem = new MenuItem(menuBar, SWT.PUSH);
	addItem.setText("Add");
	
	MenuItem searchItem = new MenuItem(menuBar, SWT.PUSH);
	searchItem.setText("Search");
	
	MenuItem deleteItem = new MenuItem(menuBar, SWT.PUSH);
	deleteItem.setText("Delete");
	
	}
}
