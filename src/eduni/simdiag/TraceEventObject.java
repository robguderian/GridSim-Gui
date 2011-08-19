package eduni.simdiag;
 
import java.util.EventObject;

/**
 * Timing diagram event object.
 * This stores a single event for updating a timing diagram.
 * The event format is described in the
 * <a href="../design_doc/index.html#simdiag">design document</a>
 * @see eduni.simdiag.TimingDiagram
 * @see eduni.simdiag.TraceListener
 */
 
public class TraceEventObject extends EventObject implements Traceable { 

  String traceline;
  int cmd;

  /** Creates a trace event object with a string */
  public TraceEventObject(Object o, String traceline) {
    super(o);
    set(traceline);
  }

  /** Creates a trace event object from another */
  public TraceEventObject(Object o, TraceEventObject t) {
    super(o);
    set(t);
  }

  /** Creates a trace event command 
   * @see eduni.simdiag.Traceable
   */
  public TraceEventObject(Object o, int cmd) {
    super(o);
    set(cmd);
  }
  
  /** Sets this command to a trace line string */
  public void set(String traceline) {
    this.traceline = traceline;
    cmd = TRACE;
  }
  /** Sets this command to a trace object */
  public void set(TraceEventObject t) {
    this.traceline = t.traceline;
    this.cmd = t.cmd;
  }
  /** Sets this command to one of the Traceable commands */
  public void set(int cmd) {
    this.traceline = "null";
    this.cmd = cmd;
  }

  /** Returns the trace line */
  public String getLine() { return traceline; }
  /** Returns the trace command */
  public int    getCmd()  { return cmd; }
}
