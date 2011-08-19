package eduni.simdiag;
 
import java.util.EventListener;

/**
 * Graph event listener interface. <p>
 * Implemented by graph drawing routine.
 * @see eduni.simdiag.GraphDiagram
 */
  
public interface GraphListener extends EventListener {
  /** Responds to a single graph event */
  void handleGraph(GraphEventObject teo);
}
