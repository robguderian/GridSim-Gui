/* Sim_reporter.java */

package eduni.simjava;

/**
 * This interface defines the functionality that must be present in the simulation's reporter.
 * The reporter is the class that will be responsible for outputing the simulation's report.
 * @see         eduni.simjava.Sim_system
 * @see         eduni.simjava.Sim_reportfile
 * @see         eduni.simanim.Anim_applet
 * @version     1.0, 22 July 2002
 * @author      Costas Simatos
 */
public interface Sim_reporter {

  /**
   * Called by <code>Sim_system</code> to setup the simulation's report.
   */
  public void setup_report();

  /**
   * Called by <code>Sim_system</code> to append information to the simulation's report.
   * @param line The line of information to append to the report
   */
  public void append_report(String line);

  /**
   * Called by <code>Sim_system</code> to close the simulation's report.
   */
  public void close_report();
}