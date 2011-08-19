/* Sim_reportfile.java */

package eduni.simjava;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to produce the simulation's report when running in standalone mode. It produces
 * a file and is accessed to append information to it.
 * <p>
 * This class is used internally by <code>Sim_system</code> to produce the simulation's report and
 * should not be used by the user.
 * @see Sim_system
 * @version 1.0 11 July 2002
 * @author Costas Simatos
 */
public class Sim_reportfile implements Sim_reporter {
  PrintWriter output;
  String filename;

  /**
   * Create the report file with the given name.
   * @param The name of the report file
   */
  public Sim_reportfile (String filename) {
    this.filename = filename;
  }

  /**
   * Sets up the report file at the beginning of the simulation.
   */
  public void setup_report() {
    try {
      output = new PrintWriter(new FileWriter(filename));
    } catch (IOException ioe) {}
  }

  /**
   * Append a line of information to the report file.
   * @line The line of information to append
   */
  public void append_report(String line) {
    output.println(line);
  }

  /**
   * Closes the report file upon simulation termination.
   */
  public void close_report() {
    if (output != null) {
      output.flush();
      output.close();
    }
  }
}