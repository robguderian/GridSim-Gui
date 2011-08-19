/* Sim_from_p.java */

package eduni.simjava;

/**
 * A predicate which selects events from specific entities.
 *
 * @see         Sim_not_from_p
 * @see         Sim_predicate
 * @version     1.1, 12 July 2002
 * @author      Costas Simatos
 */

public class Sim_from_p extends Sim_predicate {
  private int[] ids;

  /**
   * Constructor used to select events that were sent by a specific entity.
   * @param source_id The id number of the source entity
   */
  public Sim_from_p(int source_id) {
    ids = new int[] {source_id};
  }

  /**
   * Constructor used to select events that were sent by any entity from a given set.
   * @param source_ids The set of id numbers of the source entities
   */
   public Sim_from_p(int[] source_ids) {
     ids = source_ids;
   }

  /**
   * The match function called by <code>Sim_system</code>, not used directly by the user
   * @param The event to check
   * @return <code>true</code> if the event matches the predicate, <code>false</code> otherwise
   */
  public boolean match(Sim_event ev) {
    int src = ev.get_src();
    for (int i=0; i < ids.length; i++) {
      if (src == ids[i]) {
        return true;
      }
    }
    return false;
  }
}
