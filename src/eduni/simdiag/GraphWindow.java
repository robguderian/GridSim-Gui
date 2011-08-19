package eduni.simdiag;

import java.awt.Frame;

/** A graph diagram in a separate window (and thread).
 * <b>Note</b> that it is the GraphDiagram which listens
 * to graph event objects, not the GraphWindow.
 *
 * Usage (from a class which generates GraphObjects)
 * <pre>
 *   GraphWindow gw = new GraphWindow();
 *   addGraphListener( gw.getDiag());
 *   gw.start();
 * </pre>
 */
public class GraphWindow extends Thread {
  Frame f;
  GraphDiagram td;
 
  public GraphWindow() { 
    f = new Frame("Graph Diagram");
    td = new GraphDiagram();
  }
  /** Displays the window */
  public void run() {
    // td.init();
    f.add(td);
    f.pack();
    f.setVisible(true);
  }
  /** Returns the graph diagram for this window */
  public GraphDiagram getDiag() { return td; }
}
