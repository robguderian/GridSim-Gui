/* Sim_none_p.java */

package eduni.simjava;

/**
 * A predicate which will <b>not</b> match any event on the
 * deferred event queue.
 * There is a publicly accessible instance of this predicate in the
 * Sim_system class, called Sim_system.SIM_NONE, so the user does
 * not need to create any new instances.
 * @see         eduni.simjava.Sim_predicate
 * @see         eduni.simjava.Sim_system
 * @version     1.0, 4 September 1996
 * @author      Ross McNab
 */
public class Sim_none_p extends Sim_predicate {
  /**
   * Constructor.
   */
  public Sim_none_p()  {};

  /**
   * The match function called by <code>Sim_system</code>, not used directly by the user.
   * @param The event to check
   * @return <code>true</code> if the event matches the predicate, <code>false</code> otherwise
   */
  public boolean match(Sim_event ev) { return false; }
}
