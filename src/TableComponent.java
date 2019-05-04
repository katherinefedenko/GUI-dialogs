import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class TableComponent {
	
		private Shell parent;
		private Table table;
		private String[] tableTitles = {"Tournament", "Date", "Sport", "Winner", "Prize", "Winner income"};
	
		public TableComponent(Shell parent) {
			this.parent = parent;
			initUI(parent);
		}

		private void initUI(Shell parent) {
			Group group = new Group(parent, SWT.SHADOW_IN);
			group.setLayout(new GridLayout());
			RowData rowData = new RowData();
			rowData.height = 600;
			rowData.width = 600;
			group.setLayoutData(rowData);
			
			table = new Table(group, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
			
			GridData layoutTable = new GridData();
			layoutTable.heightHint = 500;
			layoutTable.widthHint = 500;
			table.setLayoutData(layoutTable);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			for (int currTitle = 1; currTitle < tableTitles.length; currTitle++)
			{
				TableColumn column = new TableColumn(table, SWT.LEFT);
				column.setText(tableTitles[currTitle]);
				column.pack();
			}
		}
		
		public static void main(String[] args) {
			Display display = new Display();
			Shell shell = new Shell(display);
			shell.setLayout(new RowLayout(SWT.FILL));
			shell.setText("Shell");
			shell.setSize(1000, 1000);
			TableComponent tableComponent = new TableComponent(shell);

			shell.open();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			display.dispose();
		}
	}


