package eduni.simdiag;

/** Clears graph */
public class GraphClearObject extends GraphEventObject { 
  public GraphClearObject(Object src) { super(src); }
  public void doit(GraphDiagram  d) {  d.clear(); }
}
