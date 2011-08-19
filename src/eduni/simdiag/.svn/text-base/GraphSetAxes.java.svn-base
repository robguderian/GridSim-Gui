package eduni.simdiag;

/** Stores a command to set the axes labels for this graph.
 * 
 */
public class GraphSetAxes extends GraphEventObject { 
  String xax,yax;
  /** Constructs a setaxes command.
   * @param xax The label of the X axis.
   * @param yax The label of the Y axis.
   */

  public GraphSetAxes(Object src, String xax, String yax){
    super(src);
    this.xax = xax; this.yax=yax;
  }
  /** Sets the axes labels of the given diagram.
   * Called by the recipient of the command.
   */
  public void doit(GraphDiagram  d) {  d.setAxes(xax,yax); }
}
