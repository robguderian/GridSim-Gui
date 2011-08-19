/* Sim_any_p.java */

package eduni.simjava;

/**
 * A predicate which will match any event on the deferred event queue.
 * There is a publicly accessible instance of this predicate in
 * <code>Sim_system</code>, called <code>Sim_system.SIM_ANY</code>, so
 * no new instances need to be created..
 * @see         eduni.simjava.Sim_predicate
 * @see         eduni.simjava.Sim_system
 * @version     1.0, 4 September 1996
 * @author      Ross McNab
 */

public class Sim_any_p extends Sim_predicate {
  /**
   * Obtains a new instance.
   */
  public Sim_any_p()  {};
  /**
   * The match function called by <code>Sim_system</code>,
   * not used directly by the user
   */
  public boolean match(Sim_event ev) { return true; }
}
