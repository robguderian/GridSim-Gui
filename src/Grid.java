
/*
 * The object that represents a grid.
 * Will be held in an arraylist to represent multiple grids
 * 
 * Grids have machines, which have processors
 * 
 * This is really a wrapper for GridResource
 *   - I might have to extend this to create it's subclasses too.
 *   
 *   
 *   
 *   For now there will be a lot of hard-coded stuff. 
 *   That will be fixed later
 */
public class Grid {
	
	private String name;
	
	// TODO - make these options.
	String arch = "Sun Ultra";      // system architecture
    String os = "Solaris";          // operating system
    double time_zone = 9.0;         // time zone this resource located
    double cost = 3.0;              // the cost of using this resource

	
	public Grid(String name)
	{
		this.name = name;

	}
	
	public String getName()
	{
		return name;
	}
	public String toString(){
		return name;
	}
	
	public String getArch()
	{
		return arch;
	}
	public String getOS()
	{
		return os;
	}
	public double getTimeZone()
	{
		return time_zone;
	}
	public double getCost()
	{
		return cost;
	}

	public void changeName(String name)
	{
		this.name = name;
	}
}
