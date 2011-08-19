/*
 * A job on a grid
 * Jobs are held by users, and run on machines
 * 
 * This is really just a wrapper for a Gridlet
 * Do I need this? Should I inherit from Gridlet, adding what I need?
 */
public class GridJob {

	
	public static final int BIG_JOB =   1000000;
	public static final int MEDIUM_JOB = 500000;
	public static final int SMALL_JOB =  250000;
	
	public static final int LONG_JOB =   1000000;
	public static final int SHORT_JOB =  250000;
	
	private int numInstructions,  inputSize,  outputsize;
	private GridUserWrapper owner;
	private String name;
	private int count = 1;
	private String growthRate;
	private double growthFactor;

	
	public GridJob ()
	{
		this.setNumInstructions(SHORT_JOB);
		this.setInputSize(MEDIUM_JOB); 
		this.setOutputsize(MEDIUM_JOB);
		
	}
	
	public GridJob (int numInstructions, int inputSize, int outputsize, String name, String growthRate, double growthFactor)
	{
		this.setNumInstructions(numInstructions);
		this.setInputSize(inputSize); 
		this.setOutputsize(outputsize);
		this.name = name;
		owner = null;
		this.growthRate = growthRate;
		this.count = 1;
		this.growthFactor = growthFactor;
	}
	
	public GridJob (int numInstructions, int inputSize, int outputsize, String name, String growthRate, double growthFactor,  int count)
	{
		this.setNumInstructions(numInstructions);
		this.setInputSize(inputSize); 
		this.setOutputsize(outputsize);
		this.name = name;
		this.count = count;
		owner = null;
		this.growthRate = growthRate;
		this.growthFactor = growthFactor;
	}
	
	public GridUserWrapper getOwner()
	{
		return owner;
	}
	
	public void setOwner(GridUserWrapper u)
	{
		owner = u;
	}
	
	public boolean isOwned()
	{
		if (owner == null)
			return false;
		return true;
	}
	
	public void changeName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
		
	}
	public String toString()
	{
		return name;
	}

	public void setNumInstructions(int numInstructions) {
		this.numInstructions = numInstructions;
	}

	public int getNumInstructions() {
		return numInstructions;
	}

	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}

	public int getInputSize() {
		return inputSize;
	}

	public void setOutputsize(int outputsize) {
		this.outputsize = outputsize;
	}

	public int getOutputsize() {
		return outputsize;
	}

	public void setCount(int c)
	{
		this.count = c;
	}
	
	public int getCount()
	{
		return count;
	}
	public String getGrowthRate()
	{
		return growthRate;
	}
	
	public void setGrowthRate(String growthRate)
	{
		this.growthRate = growthRate;
	}
	
	public void setGrowthFactor(double f)
	{
		growthFactor = f;
	}
    public double getGrowthFactor()
    {
    	return growthFactor;
    }
}
