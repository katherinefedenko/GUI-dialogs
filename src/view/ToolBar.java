package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;



public class ToolBar {
	private Display display;
	 
	public ToolBar(Shell shell) {
			
		Menu menuBar = new Menu (shell, SWT.BAR);
		shell.setMenuBar(menuBar);

		MenuItem openItem = new MenuItem (menuBar, SWT.PUSH);
		openItem.setText ("Open");
		
		openItem.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent arg0) {

                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
                fileDialog.setText("Choose file");
                fileDialog.setFilterPath("home:/");
                String[] filterExt = {"*.xml"};
                fileDialog.setFilterExtensions(filterExt);
                String selectedPath = fileDialog.open();
                System.out.print(selectedPath);
            }

        });
			
		MenuItem saveItem = new MenuItem(menuBar, SWT.PUSH);
		saveItem.setText("Save");
		
		MenuItem addItem = new MenuItem(menuBar, SWT.PUSH);
		addItem.setText("Add");
		
		MenuItem searchItem = new MenuItem(menuBar, SWT.PUSH);
		searchItem.setText("Search");
		
		MenuItem deleteItem = new MenuItem(menuBar, SWT.PUSH);
		deleteItem.setText("Delete");
		
		
	}
}
