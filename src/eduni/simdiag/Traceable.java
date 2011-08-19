package eduni.simdiag;
  
/** The trace command types.
 * @see eduni.simdiag.TraceListener
 * @see eduni.simdiag.TraceEventObject
 */
public interface Traceable {
  /** A single trace line command */
  static final int TRACE  = 0;
  /** The user has pressed Layout */
  static final int LAYOUT = 1;
  /** The user has pressed Run */
  static final int RUN    = 2;
  /** The user has pressed Pause */
  static final int PAUSE  = 3;
  /** The user has pressed Stop */
  static final int STOP   = 4;
  /** Update the display */
  static final int DISPLAY= 5;
}
