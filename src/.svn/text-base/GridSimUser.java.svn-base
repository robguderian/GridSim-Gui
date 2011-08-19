import java.util.*;
import gridsim.*;

/** Taken from Example 5, the gridsim example
 * Example5 class creates Gridlets and sends them to many grid resource
 * entities
 */
public class GridSimUser extends GridSim
{
    private Integer ID_;
    private String name_;
    private GridletList list_;
    private GridletList receiveList_;
    private int totalResource_;


    /**
     * Allocates a new Example5 object
     * @param name  the Entity name of this object
     * @param baud_rate     the communication speed
     * @param total_resource    the number of grid resources available
     * @throws Exception This happens when creating this entity before
     *                   initializing GridSim package or the entity name is
     *                   <tt>null</tt> or empty
     * @see gridsim.GridSim#Init(int, Calendar, boolean, String[], String[],
     *          String)
     */
    GridSimUser(String name, double baud_rate, int total_resource)
            throws Exception
    {
        super(name, baud_rate);
        this.name_ = name;
        this.totalResource_ = total_resource;
       // this.receiveList_; = new GridletList();

        // Gets an ID for this entity
        this.ID_ = new Integer( getEntityId(name) );
        System.out.println("Creating a grid user entity with name = " +
                name + ", and id = " + this.ID_);
        this.receiveList_ = new GridletList();

        
    }

    /**
     * The core method that handles communications among GridSim entities
     */
    public void body()
    {
        int resourceID[] = new int[this.totalResource_];
        double resourceCost[] = new double[this.totalResource_];
        String resourceName[] = new String[this.totalResource_];

        LinkedList resList;
        ResourceCharacteristics resChar;

        System.out.println("There is " + this.totalResource_ + " resources");
        
        
        // waiting to get list of resources. Since GridSim package uses
        
        // multi-threaded environment, your request might arrive earlier
        // before one or more grid resource entities manage to register
        // themselves to GridInformationService (GIS) entity.
        // Therefore, it's better to wait in the first place
        while (true)
        {
            // need to pause for a while to wait GridResources finish
            // registering to GIS
            super.gridSimHold(1.0);    // hold by 1 second

            resList = super.getGridResourceList();
            if (resList.size() == this.totalResource_)
                break;
            else
            {
                System.out.println(this.name_ +
                        ":Waiting to get list of resources ...");
            }
        }


        // a loop to get all the resources available
        int i = 0;
        for (i = 0; i < this.totalResource_; i++)
        {
            // Resource list contains list of resource IDs not grid resource
            // objects.
            resourceID[i] = ( (Integer)resList.get(i) ).intValue();

            // Requests to resource entity to send its characteristics
            super.send(resourceID[i], GridSimTags.SCHEDULE_NOW,
                    GridSimTags.RESOURCE_CHARACTERISTICS, this.ID_);

            // waiting to get a resource characteristics
            resChar = (ResourceCharacteristics) super.receiveEventObject();
            resourceName[i] = resChar.getResourceName();
            resourceCost[i] = resChar.getCostPerSec();

            System.out.println(this.name_ +
                    ":Received ResourceCharacteristics from " +
                    resourceName[i] + ", with id = " + resourceID[i]);

            // record this event into "stat.txt" file
            super.recordStatistics("\"Received ResourceCharacteristics " +
                    "from " + resourceName[i] + "\"", "");
        }

        Gridlet gridlet;
        String info;

        // a loop to get one Gridlet at one time and sends it to a random grid
        // resource entity. Then waits for a reply
        int id = 0;
        for (i = 0; i < this.list_.size(); i++)
        {
            gridlet = (Gridlet) this.list_.get(i);
            info = "Gridlet_" + gridlet.getGridletID();

            id = GridSimRandom.intSample(this.totalResource_);
            System.out.println(this.name_ + ":Sending " + info + " to " +
                    resourceName[id] + " with id = " + resourceID[id] + " - " + resList.get(id).toString());

            // Sends one Gridlet to a grid resource specified in "resourceID"
            super.gridletSubmit(gridlet, resourceID[id]);

            // Recods this event into "stat.txt" file for statistical purposes
            super.recordStatistics("\"Submit " + info + " to " +
                    resourceName[id] + "\"", "");

            // waiting to receive a Gridlet back from resource entity
        }
        for (i = 0; i < this.list_.size(); i++)
        {
            gridlet = super.gridletReceive();
            System.out.println(this.name_ + ":Receiving Gridlet " + gridlet.getGridletID() + "  that cost " + gridlet.getProcessingCost() +" started at " + gridlet.getSubmissionTime() + " ended at " + gridlet.getFinishTime());

            // Recods this event into "stat.txt" file for statistical purposes
            super.recordStatistics("\"Received Gridlet_" + gridlet.getGridletID() +  " from " + resourceName[id] + "\"", gridlet.getProcessingCost());

            // stores the received Gridlet into a new GridletList object
            this.receiveList_.add(gridlet);
        }

        // shut down all the entities, including GridStatistics entity since
        // we used it to record certain events.
        super.shutdownGridStatisticsEntity();
        super.shutdownUserEntity();
        super.terminateIOEntities();
        System.out.println(this.name_ + ":%%%% Exiting body()");
    }

 

    /**
     * Gets the list of Gridlets
     * @return a list of Gridlets
     */
    public GridletList getGridletList() {
        return this.receiveList_;
    }
   


    /**
     * Prints the Gridlet objects
     * @param list  list of Gridlets
     */
    public String printGridletList()
    {
    	GridletList list = getGridletList();
        int size = list.size();
        Gridlet gridlet;
        String text = "";
        String indent = "    ";
        int totalCost = 0;
        double timeTaken = 0;
        double totalTime = 0;
        
        text += ("\n\n========== OUTPUT : " + this.name_ + " =========\n");
        text += ("Gridlet ID" + indent + "Name" + indent + "STATUS" + indent +
                "Resource ID" + indent + "Cost" + indent + "Time Taken\n");

        for (int i = 0; i < size; i++)
        {
            gridlet = (Gridlet) list.get(i);
            text += (indent + gridlet.getGridletID() + indent + gridlet.getName()
                    + indent);

            if (gridlet.getGridletStatus() == Gridlet.SUCCESS)
            	text += ("SUCCESS");
            timeTaken = gridlet.getFinishTime()-gridlet.getSubmissionTime();
            text += ( indent + indent + gridlet.getResourceID() +
                    indent + indent + gridlet.getProcessingCost() + indent + timeTaken +"\n");
            totalCost += gridlet.getProcessingCost();
            totalTime += timeTaken;
            
            
        }
        text += "-------------------------------------\nTotal Cost: " +  totalCost ;
        text += "\nAverage Time: " + totalTime/(double)size  + "\n\n";
        
        return text;
    }
    
    public int getUserId()
    {
    	return ID_;
    }
    
    public void assignJobs(GridletList list)
    {
    	this.list_ = list;
    	System.out.println("Creating " + this.list_.size() + " Gridlets");
    }


} // end class

