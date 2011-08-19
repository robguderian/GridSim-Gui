/* Sim_predicate.java */

package eduni.simjava;

/**
 * Predicates are used to select events from the deferred queue.
 * This class is abstract and must be subclassed when writing a new
 * predicate. Some standard predicates are provided, see below:
 * @see         eduni.simjava.Sim_type_p
 * @see         eduni.simjava.Sim_from_p
 * @see         eduni.simjava.Sim_any_p
 * @see         eduni.simjava.Sim_none_p
 * @see         eduni.simjava.Sim_system
 * @version     1.0, 4 September 1996
 * @author      Ross McNab
 */
public abstract class Sim_predicate {
  /**
   * The match function which must be overidden when writing a new
   * predicate. The function is called with each event in the deferred
   * queue as its parameter when a <code>Sim_system.sim_select()</code>
   * call is made by the user.
   * @param event The event to test for a match.
   * @return The function should return <code>true</code> if the
   *         event matches and should be selected, of <code>false</code>
   *         if it doesn't
   */
  public abstract boolean match(Sim_event event);
}
