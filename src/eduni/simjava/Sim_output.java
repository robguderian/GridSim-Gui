/* Sim_output.java */

package eduni.simjava;

/**
 * The interface that a trace output class must provide.
 * @see         Sim_system
 * @version     1.0, 4 September 1996
 * @author      Ross McNab
 */
public interface Sim_output {

  /**
   * Called by <code>Sim_system</code> before the simulation starts.
   */
  public void initialise();

  /**
   * Called by <code>Sim_system</code> to output a trace line.
   * @param msg The trace message
   */
  public void println(String msg);

  /**
   * Called by <code>Sim_system</code> at the end of the simulation.
   */
  public void close();
}
