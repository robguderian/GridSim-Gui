import javax.swing.GroupLayout;
import javax.swing.JComponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/*import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
*/


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class HomeScreen extends javax.swing.JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Shell shell;

	private Button exitButton;
	private Button runButton;
	private Label healthTopLabel;
	private Label numOfMachines;
	private Label numOfUsersLabel;
	private Button updateMachinesButton;
	private Button updateUsersButton;
	private Button updateJobsButton;
	private Button updateGridsButton;
	private Button refreshStatsButton;
	private Button assignComputersToGrids;
	private Button assignJobsToUsers;
	
	private Button importData;
	private Button exportData;

	
	private Label numOfMachinesDisplay;
	private Label numOfUsersDisplay;
	private Label numOfJobsDisplay;
	private Label numOfGridsDisplay;
	private Label numOfJobsLabel;
	private Label numOfGridsLabel;
	
	private Label numOrphanedJobs;
	private Label numOrphanedMachines;
	private Label numUnusedUsers;
	private Label numUnusedGrids;
	
	private Label numOrphanedJobsLabel;
	private Label numOrphanedMachinesLabel;
	private Label numUnusedUsersLabel;
	private Label numUnusedGridsLabel;
	
	/*
	 * Instances of all the worker windows.
	 * All the windows are created at load, then hidden.
	 */
	private AddGrid gridWindow;
	private AddMachine machineWindow;
	private AddUser userWindow;
	private AddJob jobWindow;
	private MachinesToGrid machinesToGridWindow;
	private JobsToUsers jobsToUsersWindow;
	private ResultsWindow resultsWindow;
	
	/*
	 * The data store
	 */
	private GridData data;

	/**
	 * @param args
	 */
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				HomeScreen inst = new HomeScreen();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}*/
	
	public HomeScreen() {
		super();
		data = new GridData();
		initGUI();
	}
	
	private void initGUI(){
		Display display = new Display();
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		thisLayout.setHorizontalGroup(thisLayout.createParallelGroup());
		thisLayout.setVerticalGroup(thisLayout.createParallelGroup());

	/*	{
			this.setSize(128, 78);
		}
	
*/
		
		
		/*
		 * Open and hide all the helper windows
		 */
		gridWindow = new AddGrid(data);
		jobWindow = new AddJob(data);
		userWindow = new AddUser(data);
		machineWindow = new AddMachine(data);
		machinesToGridWindow = new MachinesToGrid(data);
		jobsToUsersWindow = new JobsToUsers(data);
		resultsWindow = new ResultsWindow();
		
		
		gridWindow.setVisible(false);
		jobWindow.setVisible(false);
		userWindow.setVisible(false);
		machineWindow.setVisible(false);
		machinesToGridWindow.setVisible(false);
		jobsToUsersWindow.setVisible(false);
		resultsWindow.setVisible(false);
		
		shell = new Shell(display);
		shell.setText("GridSim Configurer");
		shell.setSize(700, 319);
		{
			exitButton = new Button(shell, SWT.PUSH | SWT.CENTER);
			exitButton.setText("Exit");
			exitButton.setBounds(628, 255, 60, 30);
			exitButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					exitButtonWidgetSelected(evt);
				}
			});
		}
		{
			runButton = new Button(shell, SWT.PUSH | SWT.CENTER);
			runButton.setText("Run!");
			runButton.setBounds(327, 255, 60, 30);
			runButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					runButtonWidgetSelected(evt);
				}
			});
		}
		{
			healthTopLabel = new Label(shell, SWT.NONE);
			healthTopLabel.setText("Grid Simulation Health:");
			healthTopLabel.setBounds(105, 55, 135, 15);
		}
		{
			numOfGridsLabel = new Label(shell, SWT.NONE);
			numOfGridsLabel.setText("Number of Grids:");
			numOfGridsLabel.setBounds(120, 87, 150, 15);
		}
		{
			numOfMachines = new Label(shell, SWT.NONE);
			numOfMachines.setText("Number of Machines:");
			numOfMachines.setBounds(120, 135, 150, 15);
		}
		{
			numOfJobsLabel = new Label(shell, SWT.NONE);
			numOfJobsLabel.setText("Number of Jobs:");
			numOfJobsLabel.setBounds(120, 103, 150, 15);
		}
		{
			numOfUsersLabel = new Label(shell, SWT.NONE);
			numOfUsersLabel.setText("Number of Users:");
			numOfUsersLabel.setBounds(120, 118, 150, 15);
		}
		{
			numOrphanedJobsLabel = new Label(shell, SWT.NONE);
			numOrphanedJobsLabel.setText("Number of orphaned Jobs:");
			numOrphanedJobsLabel.setBounds(75, 155, 200, 15);
		}
		{
			numOrphanedJobs = new Label(shell, SWT.NONE);
			numOrphanedJobs.setText("0");
			numOrphanedJobs.setBounds(276, 155, 51, 16);
		}
		{
			numOrphanedMachinesLabel = new Label(shell, SWT.NONE);
			numOrphanedMachinesLabel.setText("Number of orphaned Machines:");
			numOrphanedMachinesLabel.setBounds(75, 170,  200, 15);
		}
		{
			numOrphanedMachines = new Label(shell, SWT.NONE);
			numOrphanedMachines.setText("0");
			numOrphanedMachines.setBounds(276, 170, 51, 16);
		}

		{
			numUnusedUsersLabel = new Label(shell, SWT.NONE);
			numUnusedUsersLabel.setText("Number of unused users:");
			numUnusedUsersLabel.setBounds(75, 185,  200, 15);
		}
		
		{
			numUnusedUsers = new Label(shell, SWT.NONE);
			numUnusedUsers.setText("0");
			numUnusedUsers.setBounds(276, 185, 51, 16);
		}
		{
			numUnusedGrids = new Label(shell, SWT.NONE);
			numUnusedGrids.setText("0");
			numUnusedGrids.setBounds(276, 200, 51, 16);
		}
		{
			numUnusedGridsLabel = new Label(shell, SWT.NONE);
			numUnusedGridsLabel.setText("Number of unused Grids:");
			numUnusedGridsLabel.setBounds(75, 200,  200, 15);
		}
		
		
		{
			numOfGridsDisplay = new Label(shell, SWT.NONE);
			numOfGridsDisplay.setText("0");
			numOfGridsDisplay.setBounds(276, 87, 51, 16);
		}
		
		{
			numOfJobsDisplay = new Label(shell, SWT.NONE);
			numOfJobsDisplay.setText("0");
			numOfJobsDisplay.setBounds(276, 104, 51, 16);
		}
		{
			numOfUsersDisplay = new Label(shell, SWT.NONE);
			numOfUsersDisplay.setText("0");
			numOfUsersDisplay.setBounds(276, 118, 51, 16);
		}
		{
			numOfMachinesDisplay = new Label(shell, SWT.NONE);
			numOfMachinesDisplay.setText("0");
			numOfMachinesDisplay.setBounds(276, 135, 51, 16);
		}
		{
			updateGridsButton = new Button(shell, SWT.PUSH | SWT.CENTER);
			updateGridsButton.setText("Update Grids");
			updateGridsButton.setBounds(330, 80, 121, 28);
			updateGridsButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					updateGridsButtonWidgetSelected(evt);
				}
			});
		}
		{
			updateJobsButton = new Button(shell, SWT.PUSH | SWT.CENTER);
			updateJobsButton.setText("Update Jobs");
			updateJobsButton.setBounds(330, 110, 121, 28);
			updateJobsButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					updateJobsButtonWidgetSelected(evt);
				}
			});
		}
		{
			updateUsersButton = new Button(shell, SWT.PUSH | SWT.CENTER);
			updateUsersButton.setText("Update Users");
			updateUsersButton.setBounds(330, 140, 121, 28);
			updateUsersButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					updateUsersButtonWidgetSelected(evt);
				}
			});
		}
		{
			updateMachinesButton = new Button(shell, SWT.PUSH | SWT.CENTER);
			updateMachinesButton.setText("Update Machines");
			updateMachinesButton.setBounds(330, 170, 121, 28);
			updateMachinesButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					updateMachinesButtonWidgetSelected(evt);
				}
			});
		}
		
		{
			refreshStatsButton = new Button(shell, SWT.PUSH | SWT.CENTER);
			refreshStatsButton.setText("Refresh stats");
			refreshStatsButton.setBounds(120, 220, 121, 28);
			refreshStatsButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					refreshStatsButtonWidgetSelected(evt);
				}
			});
		}
		{
			importData = new Button(shell, SWT.PUSH | SWT.CENTER);
			importData.setText("Import Configuration");
			importData.setBounds(400, 210, 200, 28);
			importData.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					importSelected(evt);
				}
			});
		}
		{
			exportData = new Button(shell, SWT.PUSH | SWT.CENTER);
			exportData.setText("Export Configuration");
			exportData.setBounds(400, 235, 200, 28);
			exportData.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					exportSelected(evt);
				}
			});
		}
		
		{
			assignComputersToGrids = new Button(shell, SWT.PUSH | SWT.CENTER);
			assignComputersToGrids.setText("Assign Computers To Grids");
			assignComputersToGrids.setBounds(450, 110, 200, 28);
			assignComputersToGrids.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					assignComputersToGridsWidgetSelected(evt);
				}
			});
		}
		{
			assignJobsToUsers = new Button(shell, SWT.PUSH | SWT.CENTER);
			assignJobsToUsers.setText("Assign Jobs To Users");
			assignJobsToUsers.setBounds(450, 140, 200, 28);
			assignJobsToUsers.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					assignJobsToUsersWidgetSelected(evt);
				}
			});
		}
		
		
		
		// do testing setup.
		//data.doTestingSetup(); 

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();

	}
	
	private void exitButtonWidgetSelected(SelectionEvent evt) {
		System.out.println("exitButton.widgetSelected, event="+evt);
		
		shell.close();
				
	}
	private void runButtonWidgetSelected(SelectionEvent evt) {
		data.run();
		resultsWindow.displayText(data.getLastResults());
		resultsWindow.setVisible(true);
	}
	
	private void updateGridsButtonWidgetSelected(SelectionEvent evt) {
		
		//inst.setLocationRelativeTo(null);
		gridWindow.refresh();
		gridWindow.setVisible(true);
		
	}
	
	private void updateJobsButtonWidgetSelected(SelectionEvent evt) {
		jobWindow.refresh();
		jobWindow.setVisible(true);
	}
	
	private void updateUsersButtonWidgetSelected(SelectionEvent evt) {
		userWindow.refresh();
		userWindow.setVisible(true);
	}
	
	private void updateMachinesButtonWidgetSelected(SelectionEvent evt) {
		machineWindow.refresh();
		machineWindow.setVisible(true);
	}
	
	private void refreshStatsButtonWidgetSelected(SelectionEvent evt){
		// refresh stats
		numOfMachinesDisplay.setText("" + data.countMachines());
		numOfUsersDisplay.setText("" + data.countUsers());
		numOfJobsDisplay.setText("" + data.countJobs());
		numOfGridsDisplay.setText("" + data.countGrids());
		
		
		numOrphanedJobs.setText("" + data.getUnassignedJobs().length);
		numOrphanedMachines.setText("" + data.getUnassignedMachines().length);
		numUnusedUsers.setText("" + data.getCountOfJoblessUsers());
		numUnusedGrids.setText("" + data.getUselessGrids());
		
	}
	
	
	private void importSelected(SelectionEvent evt){
	
		DataReaderWriter.readFile(data, shell);
		
		refreshStatsButtonWidgetSelected(null);
		
	}
	
	private void exportSelected(SelectionEvent evt){
		// refresh stats
		DataReaderWriter.write(data, shell);
		
	}
	
	private void assignComputersToGridsWidgetSelected(SelectionEvent evt){
		machinesToGridWindow.setVisible(true);
		machinesToGridWindow.refresh();
	}
	private void assignJobsToUsersWidgetSelected(SelectionEvent evt){
		jobsToUsersWindow.setVisible(true);
		jobsToUsersWindow.refresh();
	}
}
