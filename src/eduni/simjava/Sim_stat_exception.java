/* Sim_stat_exception.java */

package eduni.simjava;

/**
 * Error thrown when an error occurs in a <code>Sim_stat</code> object.
 * Such errors range from measure update errors to measurement calculation errors.
 * @version     1.0, 19 May 2002
 * @author      Costas Simatos
 */

public class Sim_stat_exception extends Sim_exception {

  /**
   * The constructor for errors with a message.
   * @param name The error's message
   */
  public Sim_stat_exception(String msg) {
    super(msg);
  }

  /**
   * The constructor for errors without a message.
   */
  public Sim_stat_exception() {
    super();
  }
}
