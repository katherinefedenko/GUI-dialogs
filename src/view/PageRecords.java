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


public class PageRecords {
	private String[] tableTitles = { "Tournament", "Date", "Sport", "Winner", "Prize", "Income" };
	private List<Tournament> tournamentList;
	private DataController controller;
	private Table table;
	private Label countRecords;
	private Label allRecords;
	private Label currentPage;
	private Label allPages;
	private int numberOfCurrentPage = 1;
    private int count = 0;
    private Text countPages;
    private Label countPagesText;
    private Button lastPage;
    private Button nextPage;
    private Button prevPage;
    private Button firstPage;
    
    public PageRecords(DataController controller) {
    	this.controller = controller;
    }
	
	public void fillTable(Shell shell, List<Tournament> tournamentList, Table table) {
		countRecords = new Label(shell, SWT.NONE);
		countRecords.setBounds(420, 420, 110, 30);
		countRecords.setText("Records on page: " + 0);
		
		allRecords = new Label(shell, SWT.NONE);
		allRecords.setBounds(535, 420, 100, 30);
		allRecords.setText("Records at all: " + 0);
		
		currentPage = new Label(shell, SWT.NONE);
		currentPage.setBounds(635, 420, 90, 30);
		currentPage.setText("Current page: " + 1);
				
		allPages = new Label(shell, SWT.NONE);
		allPages.setBounds(735, 420, 80, 30);
		allPages.setText("Pages at all: " + numberOfCurrentPage);
		
        countPagesText = new Label(shell, SWT.NONE);
        countPagesText.setText("Input count of records:");
        countPagesText.setBounds(820, 420, 120, 30);
		
        countPages = new Text(shell, SWT.BORDER);
        countPages.setText("5");
        countPages.setBounds(950, 420, 30, 20);
        
        if (!countPages.getText().isEmpty()) {
            table.removeAll();
            count = Integer.parseInt(countPages.getText());
            if (count <= tournamentList.size()) {
            	setRecordList(tournamentList, table, 0, count);
            	countRecords.setText("Records on page: " + count);
            	allPages.setText("Pages at all: " + (int)Math.ceil((double)tournamentList.size() / (double)count));
            } else {
            	setRecordList(tournamentList, table, 0, tournamentList.size());
            	countRecords.setText("Records on page: " + tournamentList.size());
            	allPages.setText("Pages at all: " + 1);
            }
            allRecords.setText("Records at all: " + tournamentList.size());
    		currentPage.setText("Current page: " + 1);
            numberOfCurrentPage = 1;
        }
        createButtons(shell, table, tournamentList);
		
	}
	
	public void createButtons(Shell shell, Table table, List<Tournament> tournamentList) {
		firstPage = new Button(shell, SWT.PUSH);
        firstPage.setText("First page");
        firstPage.setBounds(420, 470, 100, 30);
        firstPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (numberOfCurrentPage == 1) {
            		return;
            	} else {
            		if (!countPages.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countPages.getText());
            			setRecordList(controller.getListOfTournaments(), table, 0, count);
            			countRecords.setText("Records on page: " + count);
            			allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count));
            			numberOfCurrentPage = 1;
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			currentPage.setText("Current page: " + numberOfCurrentPage);
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
            	if (numberOfCurrentPage == 1) {
            		return;
            	} else {
            		if (!countPages.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countPages.getText());
            			setRecordList(controller.getListOfTournaments(), table, (numberOfCurrentPage - 2) * count, (numberOfCurrentPage - 1) * count);
            			countRecords.setText("Records on page: " + count);
            			allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count));
            			numberOfCurrentPage--;
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			currentPage.setText("Current page: " + numberOfCurrentPage);
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
            	if (tournamentList.size() - numberOfCurrentPage * count <= 0) {
            		return;
            	} else {
            		if (!countPages.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countPages.getText());
            			if (count <= controller.getListOfTournaments().size() - numberOfCurrentPage * count) {
            				setRecordList(controller.getListOfTournaments(), table, numberOfCurrentPage * count, numberOfCurrentPage * count + count);
            				countRecords.setText("Records on page: " + count);
            				allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count));
            			} else {
            				setRecordList(controller.getListOfTournaments(), table, numberOfCurrentPage * count, controller.getListOfTournaments().size());
            				countRecords.setText("Records on page: " + (controller.getListOfTournaments().size() - numberOfCurrentPage * count));
            				allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count));
            			}
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			numberOfCurrentPage++;
            			currentPage.setText("Current page: " + numberOfCurrentPage);
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
            	if (numberOfCurrentPage == (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count)) {
            		return;
            	} else {
            		if (!countPages.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countPages.getText());
            			numberOfCurrentPage = (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count);
           				setRecordList(controller.getListOfTournaments(), table, (numberOfCurrentPage - 1) * count, controller.getListOfTournaments().size());
            			countRecords.setText("Records on page: " + (tournamentList.size() - (numberOfCurrentPage - 1) * count));
            			allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count));
            			allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
            			currentPage.setText("Current page: " + numberOfCurrentPage);
            		} 
            	}
            }
        });
        
        countPages.addKeyListener(new KeyAdapter(){	
        	@Override        		
        	public void keyPressed(KeyEvent e){
        		if(e.keyCode == SWT.CR){
        			if (!countPages.getText().isEmpty()) {
        				table.removeAll();
        				count = Integer.parseInt(countPages.getText());
        				if (count <= controller.getListOfTournaments().size()) {
        					setRecordList(controller.getListOfTournaments(), table, 0, count);
        					countRecords.setText("Records on page: " + count);
        					allPages.setText("Pages at all: " + (int)Math.ceil((double)controller.getListOfTournaments().size() / (double)count));
        				} else {
        					setRecordList(controller.getListOfTournaments(), table, 0, controller.getListOfTournaments().size());
        					countRecords.setText("Records on page: " + controller.getListOfTournaments().size());
        					allPages.setText("Pages at all: " + 1);
        				}
        				allRecords.setText("Records at all: " + controller.getListOfTournaments().size());
        				currentPage.setText("Current page: " + 1);
        				numberOfCurrentPage = 1;
        			}
        		}
        	}
        });
	}

	public void setRecordList (List<Tournament> tournamentList, Table table, int from, int to) {
		if(from > to) return;
		List<Tournament> partTournamentList = tournamentList.subList(from, to);
		for (Tournament tournament : partTournamentList) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, tournament.getName());
			item.setText(1, tournament.getDate());
			item.setText(2, tournament.getSport());
			// item.setText(3, tournament.getWinner());
			item.setText(4, Integer.toString(tournament.getPrizeAmount()));
			// item.setText(5, winner.getIncome());
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
