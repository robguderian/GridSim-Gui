package eduni.simdiag;

/** Sets scale */
public class GraphSetScale extends GraphEventObject { 
  double xmin,xmax,ymin,ymax;
  public GraphSetScale(Object src, double xmin,double xmax, double ymin, double ymax) {
    super(src);
    this.xmin = xmin;
    this.xmax = xmax;
    this.ymin = ymin;
    this.ymax = ymax;
  }
  public void doit(GraphDiagram  d) {  d.setScale(xmin,xmax,ymin,ymax); }
}
