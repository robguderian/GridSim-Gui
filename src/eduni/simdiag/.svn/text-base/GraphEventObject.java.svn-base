package eduni.simdiag;
 
import java.util.EventObject;

/**
 * The basic graph event class.
 * All graph commands are derived from this and override
 * the doit() method.
 * @see eduni.simdiag.GraphData
 * @see eduni.simdiag.GraphDisplay
 * @see eduni.simdiag.GraphSetAxes
 * @see eduni.simdiag.GraphSetScale
 * @see eduni.simdiag.GraphClearObject
 */

public class GraphEventObject extends EventObject { 
  public GraphEventObject(Object src) { 
    super(src);	
  }
  /** Implements graph commands. 
   * The graph commands override this to do their biz.
   */
  public void doit(GraphDiagram d) {
    System.out.println("Executing a basic command\n");
  }
}
