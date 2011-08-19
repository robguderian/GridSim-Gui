import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import java.util.Calendar;
import gridsim.*;

/*
 * all the 'add job' and 'add machine' and 'assign x job to x grid' stuff
 * will be here.
 * 
 * This will also eventually pump out the config file.
 */

public class GridData {

	
	private ArrayList <Grid> grids;
	private ArrayList <GridUserWrapper> users;
	private ArrayList <GridJob> jobs;
	private ArrayList <GridComputer> computers;
	
	
	private String lastResults = "";
	
	
	public GridData(){
		grids = new ArrayList <Grid> ();
		users = new ArrayList <GridUserWrapper>();
		jobs = new ArrayList <GridJob>();
		computers = new ArrayList <GridComputer>();
	}
	
	
	public int countGrids()
	{
		return grids.size();
	}
	public int countUsers()
	{
		return users.size();
	}
	public int countJobs()
	{	
		int size = 0;
		for (GridJob j : jobs)
			size += j.getCount();
		return size;
	}
	public int countMachines()
	{
		int size = 0;
		for (GridComputer gc : computers)
			size += gc.getCount();
		return size;
	}
	
	
	
	/*
	 * Creates a new Grid Job
	 * returns the job's Id.
	 */
	public boolean addJob(int numInstructions, int inputSize, int outputsize, String name, String growthRate, int count)
	{
		// make sure a job of this name does not already exist
		boolean found = false;
		for (int i = 	0 ; !found && i < jobs.size(); i++)
		{
			if (jobs.get(i).getName().equals(name))
				found = true;
		}
		
		if (!found)
			jobs.add( new GridJob(numInstructions, inputSize, outputsize, name, growthRate, count));
		
		
		return !found;
	}
	
	public boolean addJob(int numInstructions, int inputSize, int outputsize, String name, String growthRate)
	{
		return addJob( numInstructions,  inputSize,  outputsize,  name, growthRate, 1);
	}
	
	public boolean addJob(GridJob j)
	{
		// make sure a job of this name does not already exist
		boolean found = false;
		for (int i = 	0 ; !found && i < jobs.size(); i++)
		{
			if (jobs.get(i).getName().equals(j.getName()))
				found = true;
		}
		
		if (!found)
			jobs.add( j);
		
		
		return !found;
	}
	
	
	public boolean deleteJob (Object name)
	{
		boolean found = false;
		for (int i = 0 ; !found && i < jobs.size(); i++)
		{
			if (jobs.get(i) == name)
			{
				found = true;
				jobs.remove(i);
			}
		}
		
		
		return found;
	}
	public GridJob[] getAllJobs()
	{
		GridJob[] j = new GridJob[jobs.size()];
		j = jobs.toArray(j);
		return j;
	}
	
	public GridJob[] getUnassignedJobs()
	{
		ArrayList<GridJob> list = new ArrayList<GridJob>();
		
		for (int i = 0; i < jobs.size(); i++)
		{
			if (!jobs.get(i).isOwned())
				list.add(jobs.get(i));
		}
		
		return list.toArray(new GridJob[list.size()]);
	}
	
	public GridJob[] getAssignedJobs(GridUserWrapper user)
	{
		ArrayList<GridJob> list = new ArrayList<GridJob>();
		
		for (int i = 0; i < jobs.size(); i++)
		{
			if (jobs.get(i).getOwner() == user)
				list.add(jobs.get(i));
		}
		return list.toArray(new GridJob[list.size()]);

	}
	
	/*
	 * Creates a new machine
	 */
	public boolean addMachine(String name, int count, int numProcs, int speed, int memSize)
	{
	
		boolean found = false;
		for (int i = 0 ; !found && i < computers.size(); i++)
		{
			if (computers.get(i).getName().equals(name))
				found = true;
		}
		if (!found)
			computers.add(new GridComputer(name, count, numProcs, speed, memSize));
		
		return !found;
	}
	
	public boolean addMachine(GridComputer c)
	{
	
		boolean found = false;
		for (int i = 0 ; !found && i < computers.size(); i++)
		{
			if (computers.get(i).getName().equals(c.getName()))
				found = true;
		}
		if (!found)
			computers.add(c);
		
		return !found;
	}
	
	
	/*
	 * Creates a machine and immediately adds it to a Grid
	 */
	public void addMachine(int numProcs, int speed, Grid addToGrid)
	{
		
	}
	
	public GridComputer[] getAllMachines()
	{
		return (GridComputer[]) computers.toArray(new GridComputer[computers.size()]);
	}
	
	public boolean deleteMachine(Object name)
	{
		boolean found = false;
		for (int i = 0; i < computers.size() && !found; i++)
		{
			if (computers.get(i) == name)
			{
				found = true;
				computers.remove(i);
			}
		}
		
		return found;
	}
	
	public GridComputer[] getUnassignedMachines()
	{
		ArrayList<GridComputer> comps = new ArrayList<GridComputer>();
		
		for (int i = 0; i < computers.size(); i++)
		{
			if (!computers.get(i).isAssigned())
				comps.add(computers.get(i));
		}
		return comps.toArray(new GridComputer[comps.size()]);
	}
	public GridComputer[] getGridMachines(Grid g)
	{
		ArrayList<GridComputer> comps = new ArrayList<GridComputer>();
		
		for (int i = 0; i < computers.size(); i++)
		{
			if (computers.get(i).isAssignedTo(g))
				comps.add(computers.get(i));
		}
		return comps.toArray(new GridComputer[comps.size()]);
	}
	
	public int countGridMachines(Grid g)
	{
		int count = 0;
		GridComputer[] gComputers = getGridMachines(g);
		for (GridComputer gc : gComputers)
		{
			count += gc.getCount();
		}
		return count;
	}
	
	public boolean addUser(String name)
	{
		// make sure a user of this name does not already exist
		boolean found = false;
		for (int i = 0 ; !found && i < users.size(); i++)
		{
			if (users.get(i).getName().equals(name))
				found = true;
		}
		
		if (!found)
			users.add(new GridUserWrapper(name));
		
		return !found;
	}
	
	public boolean addUser(GridUserWrapper u)
	{
		// make sure a user of this name does not already exist
		boolean found = false;
		for (int i = 0 ; !found && i < users.size(); i++)
		{
			if (users.get(i).getName().equals(u.getName()))
				found = true;
		}
		
		if (!found)
			users.add(u);
		
		return !found;
	}
	
	public boolean deleteUser(Object name)
	{
		/*
		 * Delete the user
		 * What about jobs associated with this user?
		 */
		
		// find the user
		boolean found = false;
		for (int i = 0; i < users.size() && !found; i++)
		{
			if (users.get(i) == name)
			{
				found = true;
				users.remove(i);
			}
		}
		
		return found;
	}
	
	
	public GridUserWrapper getUserByName(String name)
	{
		GridUserWrapper gu = null;
		
		for (GridUserWrapper guw : users)
		{
			if (guw.getName().equals(name))
				gu = guw;
		}
		
		return gu;
	}
	
	public GridUserWrapper[] getAllUsers()
	{
		GridUserWrapper [] names = new GridUserWrapper[ users.size()];
		
		for (int i = 0; i < users.size(); i++)
		{
			names[i] = users.get(i);
		}
		return names;
	}
	
	public int getCountOfJoblessUsers()
	{
		GridJob [] j;
		int jobless = 0;
		for (int i = 0; i < users.size(); i++)
		{
			j = getAssignedJobs(users.get(i));
			if (j.length == 0)
				jobless++;
		}
		return jobless;
	}
	
	public boolean addGrid(String name)
	{
		// make sure a grid of this name does not already exist
		boolean found = false;
		for (int i = 0 ; !found && i < grids.size(); i++)
		{
			if (grids.get(i).getName().equals(name))
				found = true;
		}
		
		if (!found)
			grids.add(new Grid(name));
		
		return !found;
	}
	
	public boolean addGrid(Grid g)
	{
		// make sure a grid of this name does not already exist
		boolean found = false;
		for (int i = 0 ; !found && i < grids.size(); i++)
		{
			if (grids.get(i).getName().equals(g.getName()))
				found = true;
		}
		
		if (!found)
			grids.add( g);
		
		return !found;
	}
	
	public boolean deleteGrid(Object name)
	{
		/*
		 * Delete the user
		 * What about machines associated with this grid?
		 */
		
		// find the user
		boolean found = false;
		//JOptionPane.showMessageDialog(null, grids.size());
		for (int i = 0; i < grids.size() && !found; i++)
		{
			//JOptionPane.showMessageDialog(null, grids.get(i) + " "  + name);
			if (grids.get(i) == name)
			{
				found = true;
				grids.remove(i);
			}
		}
		
		return found;
	}
	
	public Grid getGridByName(String name)
	{
		Grid theGrid = null;
		
		for (Grid g : grids)
		{
			if (g.getName().equals(name))
			{
				theGrid = g;
			}
		}
		
		return theGrid;
	}
	
	public Grid[] getGrids()
	{
		Grid[] gridarray = new Grid[grids.size()];
		for (int i = 0; i < grids.size(); i++)
			gridarray[i] = grids.get(i);
		return gridarray;
		
		
	}
	
	public int getUselessGrids()
	{
		int useless = 0;
		GridComputer[] c;
		for (int i = 0; i < grids.size(); i++)
		{
			c = this.getGridMachines(grids.get(i));
			if (c.length == 0)
				useless++;
		}
		
		return useless;
	}
	
	/*
	 * Run using the given data.
	 */
	public void run()
	{
		// init gridsim
		boolean trace_flag = true; // mean trace GridSim events/activities

        // list of files or processing names to be excluded from any
        //statistical measures
        String[] exclude_from_file = { "" };
        String[] exclude_from_processing = { "" };
        String report_name = null;
        
        ArrayList<GridResource> gridList = new ArrayList<GridResource>();
        ArrayList<GridSimUser> runningUsers = new ArrayList<GridSimUser>();
        int nextMachineID = 0;
        int nextGridletID = 0;
       
        double baud_rate = 100.00;           // communication speed

        
        // Initialize the GridSim package
        GridSim.init(users.size(), Calendar.getInstance(), trace_flag, exclude_from_file,
                exclude_from_processing, report_name);
       
        // loop through all the grids that were requsted.
        // add all the machines to the grids
        for (Grid grid : grids)
        {
            // Keep pointers to all the grids.
            gridList.add(createGrid(grid));
            nextMachineID += this.countGridMachines(grid);        	
        }
        
        // create the users
        // create lists of their jobs to pass in
        for (GridUserWrapper newuser : users)
        {
        	// Get this user's jobs
        	GridJob usersJobs[] = getAssignedJobs(newuser);
        	GridletList jobsToAssign = new GridletList();
        	Gridlet theJob;
        	GridSimUser newGridUser;
			
        	try {
				newGridUser = new GridSimUser(newuser.getName(), baud_rate, grids.size());
				runningUsers.add(newGridUser);
			
	        	for (GridJob j : usersJobs)
	        	{
	        		/*
	        		 * Make the job bigger to simulate memory accesses.
	        		 * The job requires a certain amount of memory.
	        		 * Say that there is 1 write and 1 read for each MB
	        		 */
	        		for (int i = 0; i < j.getCount(); i++)
	        		{
	        			
		        		theJob = new Gridlet(nextGridletID,  j.getNumInstructions() + j.getInputSize() + j.getOutputsize(), j.getInputSize(), j.getOutputsize(), j.getGrowthRate(), j.getGrowthFactor(), j.getName());
		        		if (theJob == null)
		        			System.out.println("null gridlet!");
		        		theJob.setUserID(newGridUser.getUserId());
		        		jobsToAssign.add(theJob);
		        		nextGridletID++;
	        		}
	        	}
	        	
	        	newGridUser.assignJobs(jobsToAssign);
	        	
        	} catch (Exception e) {
				System.out.println("User not created proplerly");
				e.printStackTrace();
			}
			
        }
        
        // Fourth step: Starts the simulation
        GridSim.startGridSimulation();

        // Final step: Prints the Gridlets when simulation is over
        String results = "";
        for (GridSimUser u : runningUsers)
        	results += u.printGridletList();
        lastResults = results;
        
        //JOptionPane.showMessageDialog(null, results);


	}
	
	
	private GridResource createGrid(Grid grid)
	{
        MachineList mList;
        ResourceCharacteristics resConfig;


        
		// Some boilerplate stuff
        double baud_rate = 560.00;           // communication speed
        long seed = 11L*13*17*19*23+1;
        double peakLoad = 0.0;       // the resource load during peak hour
        double offPeakLoad = 0.0;    // the resource load during off-peak hr
        double holidayLoad = 0.0;    // the resource load during holiday
		GridComputer[] computersToAdd = getGridMachines(grid);
    	
    	mList = new MachineList();
    	int nextMachineID = 0;
    	for (GridComputer c : computersToAdd)
    	{
    		for (int i = 0; i < c.getCount(); i++)
    		{
    			mList.add( new Machine(nextMachineID, c.getNumProcs(), c.getMips(), c.getMemSize()));
    			nextMachineID++;
    		}
    	}
    	
    	resConfig = new ResourceCharacteristics(
                grid.getArch(), grid.getOS(), mList, ResourceCharacteristics.SPACE_SHARED,
                grid.getTimeZone(), grid.getCost());

    	
    	/*
    	 * TODO
    	 * make this configurable
    	 */
    	
    	
        // incorporates weekends so the grid resource is on 7 days a week
        LinkedList Weekends = new LinkedList();
        Weekends.add(new Integer(Calendar.SATURDAY));
        Weekends.add(new Integer(Calendar.SUNDAY));

        // incorporates holidays. However, no holidays are set in this example
        LinkedList Holidays = new LinkedList();
        GridResource gridRes = null;
        try
        {
            gridRes = new GridResource(grid.getName(), baud_rate, seed,
                resConfig, peakLoad, offPeakLoad, holidayLoad, Weekends,
                Holidays);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finally, creates one Grid resource and stores " +
        "the properties of a Grid resource named: " + gridRes.get_name());
        System.out.println();

        return gridRes;
	}
	
	public String getLastResults()
	{
		return lastResults;
		
	}
	
	/*
	 * For testing purposes only.
	 * Sets up some users, grids, machines and jobs.
	 * jobs still need to be attached to users, machines to grids
	 */
	public void doTestingSetup()
	{
		this.addGrid("g1");
		//this.addGrid("g2");
		//this.addGrid("g3");
		
		// instructions, insize, outsize, name
		for (int i = 0; i < 100; i++)
			this.addJob(1000,100,100,  "None", "j"+i);
		
		
		// name, howManyToMake, numProcs, MIPS
		this.addMachine("m1", 10, 13, 10, 1000);
		/*this.addMachine("m2", 100, 2, 100);
		this.addMachine("m3", 100, 2, 100);*/
		
		int j = 0;
		for (GridComputer gc : computers)
		{
			gc.assignTo(grids.get(j));
			j = (j+1)%grids.size();
		}
		
		this.addUser("u1");
		/*this.addUser("u2");
		this.addUser("u3");
		this.addUser("u4");
		this.addUser("u5");
		this.addUser("u6");
		this.addUser("u7");
		this.addUser("u8");
		this.addUser("u9");*/
		
		
		j = 0;
		// assign jobs to users
		for (GridJob gj : jobs)
		{
			gj.setOwner(users.get(j));
			j = (j + 1)%users.size();			
		}
		
		
	}
	
	
}
