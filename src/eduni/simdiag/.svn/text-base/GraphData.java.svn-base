package eduni.simdiag;

import java.util.StringTokenizer;

/** Stores a single data point.
 *
 * Each data point has a line name and X and Y coordinates.
 */
public class GraphData extends GraphEventObject { 
  public String linename;
  public double x,y;

  /** Constructs data elem from a single text line
   * Format: linename x y
   * e.g. "total_time 10 123.45"
   */
  public GraphData(Object src, String l) {
    super(src);
    StringTokenizer st = new StringTokenizer(l, " ");
    try {
      linename = st.nextToken();
      x = (Double.valueOf(st.nextToken())).doubleValue();
      y = (Double.valueOf(st.nextToken())).doubleValue();
    } catch( Exception e) {
      System.out.println("Graphdata Couldn't parse "+l);
    }
  }

  /** Constructs data elem from linename, x and y
   */
  public GraphData(Object src, String linename, double x, double y){
    super(src);
    this.linename = linename; 
    this.x = x; this.y = y;
  }

  /** Called by recipient to implement command */
  public void doit(GraphDiagram  d) {  d.data(linename,x,y); }
}
