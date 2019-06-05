package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import controller.DataController;
import model.Tournament;
import model.Winner;


public class PageRecords {
	private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };
	private List<Tournament> tournamentList;
	
	private Winner winner;
	private DataController controller;
	private Table table;
	private Label pageRecords;
	private Label allRecords;
	private Label currentPage;
	private Label allPages;
	private int currPage = 1;
    private int count = 0;
    private Text recordsAmount;
    private Label recordsAmountInput;
    private Button lastPage;
    private Button nextPage;
    private Button prevPage;
    private Button firstPage;
    
    public PageRecords(DataController controller) {
    	this.controller = controller;
    }
	
	public void fillTableByPages(Shell shell, List<Tournament> tournamentList, Table table) {
		pageRecords = new Label(shell, SWT.NONE);
		pageRecords.setBounds(420, 420, 110, 30);
		pageRecords.setText("Records on page: " + 0);
		
		allRecords = new Label(shell, SWT.NONE);
		allRecords.setBounds(535, 420, 100, 30);
		allRecords.setText("All records: " + 0);
		
		currentPage = new Label(shell, SWT.NONE);
		currentPage.setBounds(635, 420, 90, 30);
		currentPage.setText("Current page: " + 1);
				
		allPages = new Label(shell, SWT.NONE);
		allPages.setBounds(735, 420, 80, 30);
		allPages.setText("All pages: " + currPage);
		
        recordsAmountInput = new Label(shell, SWT.NONE);
        recordsAmountInput.setText("Set records amount:");
        recordsAmountInput.setBounds(820, 420, 120, 30);
		
        recordsAmount = new Text(shell, SWT.BORDER);
        recordsAmount.setText("5");
        recordsAmount.setBounds(950, 420, 30, 20);
        
        if (!recordsAmount.getText().isEmpty()) {
            table.removeAll();
            count = Integer.parseInt(recordsAmount.getText());
            if (count <= tournamentList.size()) {
            	fillTableRecords(tournamentList, table, 0, count);
            	pageRecords.setText("Records on page: " + count);
            	allPages.setText("Pages at all: " + (int)Math.ceil((double)tournamentList.size() / count));
            } else {
            	fillTableRecords(tournamentList, table, 0, tournamentList.size());
            	pageRecords.setText("Records on page: " + tournamentList.size());
            	allPages.setText("Pages at all: " + 1);
            }
            allRecords.setText("Records at all: " + tournamentList.size());
    		currentPage.setText("Current page: " + 1);
            currPage = 1;
        }
        controlButtons(shell, table, tournamentList);
		
	}
	
	public void controlButtons(Shell shell, Table table, List<Tournament> tournamentList) {
		firstPage = new Button(shell, SWT.PUSH);
        firstPage.setText("First page");
        firstPage.setBounds(420, 470, 100, 30);
        firstPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (currPage == 1) {
            		return;
            	} else {
            		if (!recordsAmount.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(recordsAmount.getText());
            			fillTableRecords(controller.getListOfTournaments(), table, 0, count);
            			pageRecords.setText("Records on page: " + count);
            			allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() /count));
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			//currPage = 1;
            			currentPage.setText("Current page: " + 1);
            		} 
            	}
            }
        });
        
        prevPage = new Button(shell, SWT.PUSH);
        prevPage.setText("Prev page");
        prevPage.setBounds(530, 470, 100, 30);
        prevPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (currPage == 1) {
            		return;
            	} else {
            		if (!recordsAmount.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(recordsAmount.getText());
            			fillTableRecords(controller.getListOfTournaments(), table, (currPage - 2) * count, (currPage - 1) * count);
            			pageRecords.setText("Records on page: " + count);
            			allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / count));
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			currPage--;
            			currentPage.setText("Current page: " + currPage);
            		} 
            	}
            }
        });
        
		nextPage = new Button(shell, SWT.PUSH);
        nextPage.setText("Next page");
        nextPage.setBounds(640, 470, 100, 30);
        nextPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (tournamentList.size() - currPage * count <= 0) {
            		return;
            	} else {
            		if (!recordsAmount.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(recordsAmount.getText());
            			if (count <= controller.getListOfTournaments().size() - currPage * count) {
            				fillTableRecords(controller.getListOfTournaments(), table, currPage * count, currPage * count + count);
            				pageRecords.setText("Records on page: " + count);
            				allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / count));
            			} else {
        					fillTableRecords(controller.getListOfTournaments(), table, currPage * count, controller.getListOfTournaments().size());
        					pageRecords.setText("Records on page: " + (controller.getListOfTournaments().size() - currPage * count));
        					allPages.setText("Pages at all: " +(int)Math.ceil((double)controller.getListOfTournaments().size() / count));
        				}
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			currPage++;
            			currentPage.setText("Current page: " + currPage);
            		} 
            	}
            }
        });
        
        lastPage = new Button(shell, SWT.PUSH);
        lastPage.setText("Last page");
        lastPage.setBounds(750, 470, 100, 30);
        lastPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (currPage == (int)Math.ceil((double)controller.getListOfTournaments().size() / count)) {
            		return;
            	} else {
            		if (!recordsAmount.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(recordsAmount.getText());
            			currPage = (int)Math.ceil((double)controller.getListOfTournaments().size() / count);
           				fillTableRecords(controller.getListOfTournaments(), table, (currPage - 1) * count, controller.getListOfTournaments().size());
            			pageRecords.setText("Records on page: " + (tournamentList.size() - (currPage - 1) * count));
            			allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / count));
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			currentPage.setText("Current page: " + currPage);
            		} 
            	}
            }
        });
        
        recordsAmount.addKeyListener(new KeyAdapter(){	
        	@Override        		
        	public void keyPressed(KeyEvent e){
        		if(e.keyCode == SWT.CR){
        			if (!recordsAmount.getText().isEmpty()) {
        				table.removeAll();
        				count = Integer.parseInt(recordsAmount.getText());
        				if (count <= controller.getListOfTournaments().size()) {
        					fillTableRecords(controller.getListOfTournaments(), table, 0, count);
        					pageRecords.setText("Records on page: " + count);
        					allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / count));
        				} else {
        					fillTableRecords(controller.getListOfTournaments(), table, 0, controller.getListOfTournaments().size());
        					pageRecords.setText("Records on page: " + controller.getListOfTournaments().size());
        					allPages.setText("Pages at all: " + 1);
        				}
        				allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
        				currPage = 1;
        				currentPage.setText("Current page: " + currPage);
        				
        			}
        		}
        	}
        });
	}

	public void fillTableRecords (List<Tournament> tournamentList, Table table, int from, int to) {
		if(from > to) return;
		List<Tournament> partTournamentList = tournamentList.subList(from, to);
		for (Tournament tournament : partTournamentList) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, tournament.getName());
			item.setText(1, tournament.getDate());
			item.setText(2, tournament.getSport());
			item.setText(3, tournament.getFullName());
			item.setText(4, Integer.toString(tournament.getPrizeAmount()));
			item.setText(5, Integer.toString(tournament.getIncome()));
		}
	}
	public Table createTable(Shell shell) {
		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 15, 910, 300);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		for (int currTitle = 0; currTitle < tableTitles.length; currTitle++) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(150);
			column.setText(tableTitles[currTitle]);
			column.setResizable(false);
		}
		return table;
	}
}
