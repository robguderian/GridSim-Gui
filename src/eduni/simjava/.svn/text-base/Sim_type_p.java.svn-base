/* Sim_type_p.java */

package eduni.simjava;

/**
 * A predicate to select events with specific tags.
 *
 * @see         Sim_not_type_p
 * @see         Sim_predicate
 * @version     1.1, 12 July 2002
 * @author      Costas Simatos
 */

public class Sim_type_p extends Sim_predicate {
  private int[] tags;

  /**
   * Constructor used to select events with the tag value <code>t1</code>.
   * @param t1   An event tag value
   */
  public Sim_type_p(int t1) {
    tags = new int[] {t1};
  }

  /**
   * Constructor used to select events with the tag values <code>t1</code> or <code>t2</code>.
   * @param t1   An event tag value
   * @param t2   An event tag value
   * @deprecated This constructor is now replaced by the more flexible <code><a href="Sim_type_p.html#Sim_type_p(int[])">Sim_type_p(int[])</a></code>.
   *             To use this predicate call <code>Sim_type_p(new int[] {t1, t2})</code>.
   */
  public Sim_type_p(int t1, int t2) {
    tags = new int[] {t1, t2};
  }

  /**
   * Constructor used to select events with the tag values <code>t1</code>, <code>t2</code>
   * or <code>t3</code>.
   * @param t1   An event tag value
   * @param t2   An event tag value
   * @param t3   An event tag value
   * @deprecated This constructor is now replaced by the more flexible <code><a href="Sim_type_p.html#Sim_type_p(int[])">Sim_type_p(int[])</a></code>.
   *             To use this predicate call <code>Sim_type_p(new int[] {t1, t2, t3})</code>.
   */
  public Sim_type_p(int t1, int t2, int t3) {
    tags = new int[] {t1, t2, t3};
  }

  /**
   * Constructor used to select events with a tag value equal to any of the specified tags.
   * @param tags The list of tags
   */
  public Sim_type_p(int[] tags) {
    this.tags = tags;
  }

  /**
   * The match function called by <code>Sim_system</code>, not used directly by the user
   */
  public boolean match(Sim_event ev) {
    int tag = ev.get_tag();
    for (int i=0; i < tags.length; i++) {
      if (tag == tags[i]) {
        return true;
      }
    }
    return false;
  }
}
