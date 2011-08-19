import java.util.ArrayList;

/*
 * Represents users of the grid.
 * Users basically just have names and jobs currently
 * Could be extended to have budgets
 */
public class GridUserWrapper {

	
	private String name;
	private ArrayList<GridJob> jobs;
	
	public GridUserWrapper(String name)
	{
		this.name = name;
		jobs = new ArrayList<GridJob>();
	}
	
	public boolean addJob(GridJob j)
	{
		// TODO: add a job to the arraylist, return true if done
		// properly.
		
		/*
		 * TODO: check to see if this job is already owned?
		 */
		
		jobs.add(j);
		
		return false;
	}
	
	public String getName()
	{
		return name;
		
	}
	
	public void changeName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}

}
