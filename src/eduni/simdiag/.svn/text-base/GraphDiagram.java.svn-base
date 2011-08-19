/**
 * GraphDiagram.java
 * simjava graph
 */
 
package eduni.simdiag;

import java.util.List;
import java.util.ArrayList;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Frame;
import java.applet.Applet;

class DPoint {
  public double x,y;
  public DPoint(double xx, double yy) {x=xx;y=yy;}
}

class Linedata {
  String name;
  List points = new ArrayList();// list of points
  public Linedata(String n) { name = n; }
  public void add(double x, double y) {
    points.add(new DPoint(x,y));
  }
  public DPoint getDPoint(int i) { return (DPoint)points.get(i); }
  public String getName() { return name; }
  Color valtocol(int v) {
    if (v==0) return Color.blue;
    if (v==1) return Color.red;
    if (v==2) return Color.darkGray;
    if (v==3) return Color.gray;
    if (v==4) return Color.green;
    if (v==5) return Color.lightGray;
    if (v==6) return Color.magenta;
    if (v==7) return Color.orange;
    if (v==8) return Color.pink;
    if (v==9) return Color.cyan;
    return valtocol(v%10);
  }

  public void draw(Graphics g, int i, GraphPanel p) {
    g.setColor(valtocol(i));
    // Join points.
    for (int j=0; j<points.size()-1; j++) {
      DPoint p1 = (DPoint)points.get(j);
      DPoint p2 = (DPoint)points.get(j+1);
      g.drawLine(p.xscale(p1.x),p.yscale(p1.y),
		 p.xscale(p2.x),p.yscale(p2.y)); 
    }

    int sz = 2; // Cross size.
    for (int j=0; j<points.size(); j++) {
      DPoint p1 = (DPoint)points.get(j);
      int ix = p.xscale(p1.x);
      int iy = p.yscale(p1.y);
      g.drawLine(ix-sz,iy-sz,ix+sz,iy+sz); 
      g.drawLine(ix+sz,iy-sz,ix-sz,iy+sz); 
    }

    // Draw Key (tr)
    int kyspc = 20; int kx = p.getSize().width - 100;
    int ky = (i+2) * kyspc;
    g.drawLine(kx,ky,kx+10,ky);
    g.drawString(name,kx+10,ky);
  }
}

class GraphPanel extends Panel {
  String xax="X",yax="Y";
  List data = new ArrayList(); // List of Linedata
  double xmin=0.0, xmax=1.0, ymin=0.0, ymax=1.0;
  // Offset of axes
  int xoff = 40;
  int yoff = 40;

  /** Return linedata given line name, or null if not found */
  Linedata getLine(String n) {
    for (int i=0; i<data.size(); i++) {
      Linedata ld = (Linedata)data.get(i);
      if (n.compareTo(ld.getName())==0)
	return ld;
    }
    return null;
  }
  GraphPanel() {
    super();
    resetScale();
  }
  void resetScale() {
    xmin=0.0;
    xmax=1.0;
    ymin=0.0;
    ymax=1.0;
  }
 
  public int xscale(double x) {
    int w = getSize().width;
    return (int)(((x-xmin)/(xmax-xmin)) * (w-xoff)) + xoff;
  }
  public int yscale(double y) {
    int h = getSize().height;
    return h-((int)(((y-ymin)/(ymax-ymin)) * (h-yoff)) + yoff);
  }
  public void clear() {
    data.clear();
    resetScale();
  }

  public void setAxes(String xax, String yax) {
    this.xax = xax; this.yax = yax;
  }

  public void setScale(double xmin,double xmax, double ymin, double ymax) {
    this.xmin = xmin;
    this.xmax = xmax;
    this.ymin = ymin;
    this.ymax = ymax;
  }

  public void data(String linename, double x, double y) {
    Linedata ld = getLine(linename);
    if (ld==null) {
      ld = new Linedata(linename);
      data.add(ld);
    }
    ld.add(x,y);
    if (x > xmax) xmax = x;
    if (y > ymax) ymax = y;
    if (x < xmin) xmin = x;
    if (y < ymin) ymin = y;
  }

  public void display() {
    repaint();
  }

  double log10(double d) {
    return Math.log(d)/Math.log(10);
  }

  void drawNotches(Graphics g, int x1, int y1, int x2, int y2, 
		   double v1, double v2) {
    boolean isXaxis = (y1==y2);
    double tsize = v2 - v1;			// e.g. 17.2
    double tgaplog = log10( (double) tsize );	// e.g. 1.2ish
    int tgaprounded = (int)tgaplog;		// e.g. 1
    double tgapfinal = Math.pow( 10.0, (double) tgaprounded ); // e.g.10.0
    // Change to 0 5 10 etc?
    if (tsize/tgapfinal < 3.0) { tgapfinal /= 2.0; }
    
    // Get first point
    double div = 0.5 + (v1 / tgapfinal);
    int idiv = (int) div;
    double firstt1 = idiv * tgapfinal;

    // Draw major notches 
    if (isXaxis) {
      int notchHeight = yoff/10;
      for (double t=firstt1; t<=v2; t+=tgapfinal ) {
	int xp = xscale(t);
	String s = Double.toString(t);
	g.setColor(Color.blue);
	g.drawLine(xp,y1,xp,notchHeight+y1);
	int w = g.getFontMetrics().stringWidth(s);
	int h = g.getFontMetrics().getHeight();
	g.drawString(s,xp-w/2,y1+notchHeight+h);

	g.setColor(Color.gray.brighter());
	g.drawLine(xp,y1,xp,0);
      }
    } else {
      int notchHeight = xoff/10;
      for (double t=firstt1; t<=v2; t+=tgapfinal ) {
	int yp = yscale(t);
	String s = Double.toString(t);
	g.setColor(Color.blue);
	g.drawLine(x1,yp,x1-notchHeight,yp);
	int sw = g.getFontMetrics().stringWidth(s);
	int sh = g.getFontMetrics().getHeight();
	g.drawString(s,x1-sw-notchHeight,yp+sh/2);

	g.setColor(Color.gray.brighter());
	g.drawLine(x1,yp,getSize().width,yp );
      }
    }

  }

  void drawAxes(Graphics g ) {
    int w = getSize().width;
    int h = getSize().height;

    g.setColor(Color.blue);
    g.drawLine(xoff, h-yoff, w, h-yoff);
    g.drawLine(xoff, h-yoff, xoff, 0);
    g.drawString(xax,w/2,h-yoff/3);
    g.drawString(yax,xoff/3,h/2);

    // Draw notches
    drawNotches(g,xoff, h-yoff, w, h-yoff,xmin,xmax);
    drawNotches(g,xoff, h-yoff, xoff, 0 ,ymin,ymax);

    g.setColor(Color.blue.brighter());
    g.drawLine(xoff, h-yoff+1, w, h-yoff+1);
    g.drawLine(xoff+1, h-yoff, xoff+1, 0);
  }


  Image offscreen;
  Dimension offscreensize=new Dimension();
  Graphics offg;

  /** Plot the graph */
  public void paint(Graphics g) {
    Dimension d = getSize();
    if ((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
      offscreen = createImage(d.width, d.height);
      offscreensize = d;
      offg = offscreen.getGraphics();
    }

    int w = getSize().width;
    int h = getSize().height;
    System.out.println("Painting "+w+" "+h);
    offg.setColor(Color.lightGray.darker());
    offg.fillRect(0,0,w,h);
    offg.setColor(Color.lightGray);
    offg.fillRect(xoff,0,w,h-yoff);
    drawAxes(offg);
 
    for (int i=0; i<data.size(); i++) {
      Linedata ld = (Linedata)data.get(i);
      ld.draw(offg,i,this);
    }
    g.drawImage(offscreen, 0, 0, null);
  }
}

/**
 * This class represents the graph application.
 * @version     1.0, July 1997
 * @author      Fred Howell
 */
public class GraphDiagram extends Applet implements GraphListener, Runnable {
  transient Thread thread;

  GraphPanel diag;

  /** Constructs a graph which runs in its own thread. 
   */
  public GraphDiagram() {
    super();
    thread = new Thread(this);
    thread.start();
  }

  /** Draws graph */
  public void run() {
    init();
  }
  /** Graph beans event interface */
  private List graphListeners = new ArrayList();

  /** Adds a graph listener to the output. */
  public synchronized void addGraphListener(GraphListener l) {
    graphListeners.add(l);
  }
  /** Removes a graph listener from the output. */
  public synchronized void removeGraphListener(GraphListener l) {
    graphListeners.remove(l);
  }
  /** Deals with incoming graph events */
  public void handleGraph(GraphEventObject e) { 
    e.doit(this);
    forwardGraph(e);
  }
  /** Sends graph events to any listeners */
  public void forwardGraph(GraphEventObject e) {
    List l;
    synchronized(this) { l = (List)((ArrayList)graphListeners).clone(); }
    //  GraphEventObject teo = new GraphEventObject(this,e);
    // Not bothering to clone the object, make sure sender doesn't change
    // it.
    for (int i=0; i<l.size(); i++) {
      GraphListener tl = (GraphListener) l.get(i);
      tl.handleGraph(e);
    }
  }

  /** Sets up graph diagram display */
  public void init() {
    setLayout(new GridLayout(1,1));
    diag = new GraphPanel();
    add(diag);
  }

  /** Returns the panel associated with this graph */
  public GraphPanel get_diag() { return diag; }

  /** Clears the graph */
  public void clear() {
    diag.clear();
    diag.repaint();
  }

  /** Sets the axes labels */
  public void setAxes(String xax, String yax) {
    System.out.println("Setting axes to "+xax+" "+yax);
    diag.setAxes(xax,yax);
  }

  /** Sets the scale */
  public void setScale(double xmin,double xmax, double ymin, double ymax) {
    diag.setScale(xmin,xmax,ymin,ymax);
  }

  /** Adds a data element */
  public void data(String linename, double x, double y) {
    System.out.println("Data "+linename+" "+x+" "+y);
    diag.data(linename,x,y);
  }

  /** Displays the graph */
  public void display() {
    diag.repaint();
  }


  /** Constructs an example using the graph, by connecting a
   * GraphEqn object to its input to display sine waves.
   */
  public static void main(String args[]) {
    Frame f = new Frame("Graph Diagram Example");
    GraphDiagram ex1 = new GraphDiagram();
    // GraphLoader tl = new GraphLoader("file:/home/fwh/export/tmp.graph");
    // tl.addGraphListener(ex1);
    // tl.startLoading();
    GraphEqn ge = new GraphEqn();
    ge.addGraphListener(ex1);
    ge.startRunning();

    f.add("Center", ex1);
    f.pack();
    f.setSize(600,300);
    f.show();
  }
}
