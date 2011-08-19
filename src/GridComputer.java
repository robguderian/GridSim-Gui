
/*
 * A computer connected to a Grid
 * 
 * Computers have some number of processors
 * A Wrapper for 'Machine' - which is part of the Gridsim library
 */
public class GridComputer {
	private int count;
	private int mips;
	private int numProcs;
	private int memSize;
	private String name;
	
	
	
	Grid assignedTo;
	
	public GridComputer(String inname, int numComps, int numProcs, int procRating, int memsize){
		// I need to track the ids manually.
		name = inname;
		setCount(numComps);
		this.setNumProcs(numProcs);
		setMips(procRating);
		this.memSize = memsize;	
	}
	
	public String getName()
	{
		return name;
	}
	public void changeName (String name)
	{
		this.name = name;
	}
	public String toString()
	{
		return name;
	}
	
	public boolean isAssigned()
	{
		return assignedTo!=null;
	}
	public boolean isAssignedTo(Grid g)
	{
		return g == assignedTo;
	}
	
	public int getMemSize()
	{
		return memSize;
		
	}
	
	public void setMemSize(int memSize)
	{
		this.memSize = memSize;
	}
	
	public void unassign()
	{
		assignedTo = null;
	}

	public void assignTo(Grid g)
	{
		//JOptionPane.showMessageDialog(null, name + " applied to " + g);
		assignedTo = g;
	}
	
	public String getAssignedTo ()
	{
		if (assignedTo == null)
			return null;
		return assignedTo.getName();
	}
	
	/*
	 * returns the id of the new computer that
	 * is a clone of the first
	 */
	public int copy (GridComputer toCopy)
	{
		return 0;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setMips(int mips) {
		this.mips = mips;
	}

	public int getMips() {
		return mips;
	}

	public void setNumProcs(int numProcs) {
		this.numProcs = numProcs;
	}

	public int getNumProcs() {
		return numProcs;
	}
	

}
