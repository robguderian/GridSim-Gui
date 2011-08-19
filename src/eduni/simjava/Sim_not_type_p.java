/* Sim_not_type_p.java */

package eduni.simjava;

/**
 * A predicate to select events that don't match specific tags.
 *
 * @see         Sim_type_p
 * @see         Sim_predicate
 * @version     1.0, 12 July 2002
 * @author      Costas Simatos
 */

public class Sim_not_type_p extends Sim_predicate {
  private int[] tags;

  /**
   * Constructor used to select events whose tags don't match a given tag.
   * @param tag   An event tag value
   */
  public Sim_not_type_p(int tag) {
    tags = new int[] {tag};
  }

  /**
   * Constructor used to select events whose tag values don't match any of the given tags.
   * @param tags The list of tags
   */
  public Sim_not_type_p(int[] tags) {
    this.tags = tags;
  }

  /**
   * The match function called by <code>Sim_system</code>, not used directly by the user
   */
  public boolean match(Sim_event ev) {
    int tag = ev.get_tag();
    for (int i=0; i < tags.length; i++) {
      if (tag == tags[i]) {
        return false;
      }
    }
    return true;
  }
}
