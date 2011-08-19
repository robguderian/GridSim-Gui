import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/*
 * Creates a config file based on the data object passed
 * Reads in a config file and appends to the data file passed
 */

public class DataReaderWriter {
	
	// Define constants to put into the
	// wrapper objects. Change the values with 
	// the given data that is still coming.
	
	// Machine defaults
	private static final int numProcs = 1;
	private static final int mips = 100;
	private static final int count = 1;
	private static final int memSize = 1024;
	private static final String name = "tempName";
	private static final String growthRate = "None";
	
	//  job defaults
	private static final int numInstructions = 1000;
	private static final  int inputSize = 10;
	private static final int outputsize = 10; 

	
	
	public static void write(GridData d, Shell parent)
	{
		FileWriter fileWriterOut;
		PrintWriter fileOut;

		try 
		{
			String filename = new FileDialog(parent, SWT.SAVE).open();
			
			if (filename != null && !filename.equals(""))
				{
				fileWriterOut = new FileWriter(filename, true);
				fileOut = new PrintWriter(fileWriterOut);
		
				for (Grid g : d.getGrids())
				{
					fileOut.println("[Grid " + g.getName() + "]\n");
				}
				
				for (GridComputer gc : d.getAllMachines())
				{
					fileOut.println("[Machine " + gc.getName() + "]");
					fileOut.println("mips " + gc.getMips());
					fileOut.println("count " + gc.getCount());
					fileOut.println("numProcs " + gc.getNumProcs());
					fileOut.println("memSize " + gc.getMemSize());
					
					
					if (gc.isAssigned())
						fileOut.println("assignTo " + gc.getAssignedTo());
					fileOut.println();
				}
				
				for (GridUserWrapper gu : d.getAllUsers())
				{
					fileOut.println("[User " + gu.getName() + "]");
					fileOut.println();
				}
				for (GridJob gj : d.getAllJobs())
				{
					fileOut.println("[Job " + gj.getName() + "]");
					fileOut.println("instructions " + gj.getNumInstructions());
					fileOut.println("count " + gj.getCount());
					fileOut.println("inputSize " + gj.getInputSize());
					fileOut.println("outputSize " + gj.getOutputsize());
					fileOut.println("growthRate " + gj.getGrowthRate());
					fileOut.println("growthFactor " + gj.getGrowthFactor() );
					
					
					GridUserWrapper owner = gj.getOwner();
					if (owner != null)
						fileOut.println("assignTo " + gj.getOwner());
					fileOut.println();
				}
				
				fileOut.close();
			}

				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public static void readFile(GridData d, Shell parent) {
		
		
	    //fd.setFile(fileType);
	    //fd.setDirectory(defDir);
	    //fd.setLocation(50, 50);
		

		
		try { 
				//fd.open();
		    	String filename = new FileDialog(parent).open();//fd.getFileName();
		    	System.out.println(filename);
				readFile(new FileReader (filename), d);
		} catch (Exception e) {

			System.out.println("IO Error: " + e.getMessage());
		}

		
		
	}
	
	
	private static void readFile(FileReader inFile, GridData d) {

		BufferedReader lines = new BufferedReader(inFile);
		String line = null;
		String workingLine;
		String [] parts;
		
		Grid tempGrid;
		GridUserWrapper tempUser;
		GridComputer tempMachine;
		GridJob tempJob;

		try 
		{ 
			line = lines.readLine();
			while (line != null)
			{
				// read in the config file.
				if (line.startsWith("["))
				{
					// begin adding to new object
					// trim off the []s
					line = line.trim();
					workingLine = line.substring(1, line.length()-1);
					parts = workingLine.split("\\s+");
					
					if (parts[0].equals("Grid"))
					{
						tempGrid = new Grid(parts[1]);
						// Read in all grid values
						line = lines.readLine();
						while (line != null && !line.trim().equals(""))
						{
							parts = line.split("\\s+");

							if (parts[0].equals("name"))
								tempGrid.changeName(parts[1]);
							
							line = lines.readLine();
						}
						// put the grid into the system.
						d.addGrid(tempGrid);
						tempGrid = null;
					
					}
					else if (parts[0].equals("Machine"))
					{
						tempMachine = new GridComputer(parts[1],  count,  numProcs,  mips, memSize);
						// Read in all grid values
						line = lines.readLine();
						while (line != null && !line.trim().equals(""))
						{
							parts = line.split("\\s+");

							if (parts[0].equals("name"))
								tempMachine.changeName(parts[1]);
							if (parts[0].equals("mips"))
								tempMachine.setMips(Integer.parseInt(parts[1]));
							if (parts[0].equals("count"))
								tempMachine.setCount(Integer.parseInt(parts[1]));
							if (parts[0].equals("numProcs"))
								tempMachine.setNumProcs(Integer.parseInt(parts[1]));
							if (parts[0].equals("memSize"))
								tempMachine.setMemSize(Integer.parseInt(parts[1]));
							
							if (parts[0].equals("assignTo"))
							{
								Grid tempg = d.getGridByName(parts[1]);
								if (tempg != null)
									tempMachine.assignTo(tempg);
							}
							
							
							line = lines.readLine();
						}
						// put the grid into the system.
						d.addMachine(tempMachine);
						tempMachine = null;
							
					}
					else if (parts[0].equals("User"))
					{
						tempUser = new GridUserWrapper(parts[1]);
						line = lines.readLine();
						while (line != null && !line.trim().equals(""))
						{
							parts = line.split("\\s+");

							if (parts[0].equals("name"))
								tempUser.changeName(parts[1]);
							
							line = lines.readLine();
						}
						d.addUser(tempUser);
						tempUser = null;
					}
					else if (parts[0].equals("Job"))
					{
						tempJob = new GridJob( numInstructions,  inputSize,  outputsize, parts[1], growthRate, 1);
						line = lines.readLine();
						while (line != null && !line.trim().equals(""))
						{
							parts = line.split("\\s+");

							if (parts[0].equals("name"))
								tempJob.changeName(parts[1]);
							if (parts[0].equals("instructions"))
								tempJob.setNumInstructions(Integer.parseInt(parts[1]));
							if (parts[0].equals("count"))
								tempJob.setCount(Integer.parseInt(parts[1]));
							if (parts[0].equals("inputSize"))
								tempJob.setInputSize(Integer.parseInt(parts[1]));
							if (parts[0].equals("outputSize"))
								tempJob.setOutputsize(Integer.parseInt(parts[1]));
							if (parts[0].equals("growthRate"))
							{
								String rate = parts[1];
								for (int i = 2; i < parts.length; i++)
									rate += " " + parts[i];
								tempJob.setGrowthRate(rate);
							}
							if (parts[0].equals("assignTo"))
							{
								GridUserWrapper owner = d.getUserByName(parts[1]);
								tempJob.setOwner(owner);
							}
							if (parts[0].equals("growthFactor"))
								tempJob.setGrowthFactor(Double.parseDouble(parts[1]));
							
							line = lines.readLine();
						}
						d.addJob(tempJob);
						tempJob = null;
					}
						
					
				}
				line = lines.readLine();
			}
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
