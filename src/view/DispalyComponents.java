package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.DataController;

public class DispalyComponents {

	private Display display;
    
    public DispalyComponents(Shell shell, DataController controller, Table table) {
        
        Button addTournamentButton = new Button(shell, SWT.PUSH);
        addTournamentButton.setText("Add new tournament");
        addTournamentButton.setBounds(10, 420, 120, 30);
        
        addTournamentButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				new AddDisplay(display, controller, shell, table);
			}
		});
        
        ToolBar SearchBar = new ToolBar(shell, SWT.BORDER|SWT.VERTICAL);
        SearchBar.setBounds(140, 420, 120, 80);
        ToolItem itemFindByTournament = new ToolItem(SearchBar, SWT.PUSH);
        itemFindByTournament.setText("Find by tournament");
        
        itemFindByTournament.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new FindByTournament(display, controller);
			}
		});
        
        ToolItem itemFindBySport = new ToolItem(SearchBar, SWT.PUSH);
        itemFindBySport.setText("Find by sport");
        
        itemFindBySport.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new FindBySport(display, controller);
			}
		});
        
        ToolItem itemFindByPrize = new ToolItem(SearchBar, SWT.PUSH);
        itemFindByPrize.setText("Find by prize");
        
        itemFindByPrize.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new FindByPrize(display, controller);
			}
		});
        
        ToolBar DeleteBar = new ToolBar(shell, SWT.BORDER|SWT.VERTICAL);
        DeleteBar.setBounds(270, 420, 140, 80);
        
        ToolItem itemDeleteByTournament = new ToolItem(DeleteBar, SWT.PUSH);
        itemDeleteByTournament.setText("Delete by tournament");
 
        itemDeleteByTournament.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new DeleteByTournament(display, controller, shell, table);
			}
		});
        
        ToolItem itemDeleteBySport = new ToolItem(DeleteBar, SWT.PUSH);
        itemDeleteBySport.setText("Delete by sport");
        
        itemDeleteBySport.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new DeleteBySport(display, controller);
			}
		});
        
        ToolItem itemDeleteByPrize = new ToolItem(DeleteBar, SWT.PUSH);
        itemDeleteByPrize.setText("Delete by prize");
        
        itemDeleteByPrize.addSelectionListener(new SelectionAdapter() {
        	
			public void widgetSelected(SelectionEvent arg0) {
				new DeleteByPrize(display, controller);
			}
		});
               
    }
    
   
}

