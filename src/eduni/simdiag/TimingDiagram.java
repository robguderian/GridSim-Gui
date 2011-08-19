/**
 * TimingDiagram.java
 * simjava timing diagram
 * Adapted from a C++/Motif version
 * 31 Jan 1998 - making it run in its own thread (so it
 * doesn't need its own window).
 */
 
package eduni.simdiag;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Scrollbar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Font;
import java.applet.Applet;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;

class StringVector {
  protected List v;
  StringVector() { v = new ArrayList(); }
  List getV() { return v; }
  void add(String s) { v.add(s); }
  String get( int i ) { return (String)v.get(i); }
  int find( String s ) {
    String found = null, curr;
    int foundi = 0;
    int v_size = v.size();
    for (int i=0; i < v_size; i++) {
      curr = (String)v.get(i);
      if(s.compareTo(curr) == 0) {
        found = curr;
	foundi = i;
      }
    }
    return foundi;
  }
}

/**
 * This class represents a data type to be displayed in 
 * The timing diagram.
 * @version     1.0, June 1997
 * @author      Fred Howell
 */
class TypeParam {
  String typename;
  protected StringVector labels;
  TypeParam(String buff) {  
    labels = new StringVector();
    StringTokenizer st = new StringTokenizer(buff, " ");
    typename = st.nextToken();
    while (st.hasMoreTokens())
      labels.add(st.nextToken());  
  }
  String getLabel(int i) { return labels.get(i); }
  int get_index(String l) { return labels.find(l); }

  Color valtocol(int v) {
    if (v==0) return Color.blue;
    if (v==1) return Color.cyan;
    if (v==2) return Color.darkGray;
    if (v==3) return Color.gray;
    if (v==4) return Color.green;
    if (v==5) return Color.lightGray;
    if (v==6) return Color.magenta;
    if (v==7) return Color.orange;
    if (v==8) return Color.pink;
    if (v==9) return Color.red;
    if (v==10) return Color.white;
    return valtocol(v%10);
  }
  void drawKey(Graphics g, int x, int y, int w, int h) {
    g.setColor(Color.black);
    g.drawString(typename,x,y+h);

    // Draw blocks for labels too
    String curr;
    int blksize = 20;
    List labelsV = labels.getV();
    int labelsV_size = labelsV.size();
    for (int i=0; i < labelsV_size; i++) {
      int keywidth = w / (labelsV_size+1);
      curr = (String)labelsV.get(i);
      g.setColor(Color.black);
      g.drawString(curr,x + (i+1)*keywidth, y+h);
      g.setColor(valtocol(i));
      g.fillRect(x + (i+1)*keywidth, y , keywidth, h/2);
    }
  }
}

class Types {
  protected List types;
  Types() { types = new ArrayList(); }
  void add(TypeParam tp) { types.add(tp); }
  TypeParam find( String s ) {
    TypeParam found = null, curr;
    int foundi = 0;
    int types_size = types.size();
    for (int i=0; i < types_size; i++) {
      curr = (TypeParam)types.get(i);
      if(s.compareTo(curr.typename) == 0) {
        found = curr;
      }
    }
    return found;
  }
  void drawKeys(Graphics g, int w, int h) {
    TypeParam curr;
    int types_size = types.size();
    for (int i=0; i < types_size; i++) {
      int keyheight = h / types_size;
      curr = (TypeParam)types.get(i);
      curr.drawKey(g,0,i * keyheight, w, (int)(keyheight*0.7));
    }
  }
  List getV() { return types; }
}

class Event {
  double time;
  int stateno;
  Event() { time = -1; stateno = 0; }
  Event(double t, int s) { time = t; stateno = s; }
  boolean isblank() { return (time==-1); }
}


/**
 * This class represents an entry on the diagram
 * including all data.
 * @version     1.0, June 1997
 * @author      Fred Howell
 */
class Entry {

  protected String name;
  protected List events;
  protected TypeParam tp;
  int x,y,w,h;
  double startt, endt;

  Entry(String n, TypeParam tp) { 
    name = n; 
    events = new ArrayList();
    this.tp = tp;
    x = 0; y = 0; w = 100; h = 100;
  }

  TypeParam getType() { return tp; }
  void add(Event e) { events.add(e); }
  void setPosition(int x, int y, int w, int h) {
    this.x = x; 
    this.y = y; 
    this.w = w; 
    this.h = h; 
  }

  int ttox(double t ) {
    return  (int)((w) * (t-startt)/(endt-startt));
  }

  Color valtocol(int v) {
    return tp.valtocol(v);
  }

  void drawLabel(Graphics g, int x, int y) {
    // Draw label
    g.setColor(Color.black);
    g.drawString(name,x,y);
  }

  // draw all events from t1 to t2.
  void draw(Graphics g, double t1, double t2) {
    startt = t1; endt = t2;

    // System.out.println("Drawing from "+t1+" to "+t2);

    // Get last event before starttime
    int ptri=0, previ=0;
    boolean found = false;
    int i=0;
    for (i=0; i<events.size() && !found; i++) {
      if (((Event)events.get(i)).time > t1) {
	found = true;
	ptri = i;
	previ = ptri-1; if (previ<0) previ=0;
      }
    }

    // System.out.println("Found start at index "+previ+" out of "+events.size());

    boolean reachedend = false;
    for (; previ<events.size() && !reachedend; previ++) {
      double time1=t1,time2=t2;
      int val = 0;

      ptri = previ+1;

      Event prevevent = (Event)events.get(previ);
      Event ptr;
      if (ptri<events.size()) 
	ptr = (Event)events.get(ptri);
      else ptr = new Event();

      if (!prevevent.isblank()) {
	time1 = prevevent.time;
	val = prevevent.stateno;
      }
      if (!ptr.isblank())       time2 = ptr.time;

      if (time2 > t2) time2 = t2;
      if (time1 > t2) time1 = t2;

      //System.out.println("Drawing: "+ttox(time1)+" "+ttox(time2-time1)+" v="+val+"  t="+time1+" - "+time2);

      g.setColor(valtocol(val));
      g.fillRect(ttox(time1),y, ttox(time2)-ttox(time1),h);

      if (prevevent.time > t2) reachedend = true;
    }

  }
}


/**
 * This class loads and stores all data entries
 * @version     1.0, June 1997
 * @author      Fred Howell
 */

class Entries {
  protected List entries;
  protected Types types;

  double starttime, endtime;

  Entries() {
    entries = new ArrayList();
    types   = new Types();
    starttime = 0.0; 
    endtime   = 1.0;
  }

  /* External commands */
  void reset() {
    entries.clear();
    types.getV().clear();
    starttime = 0.0; 
    endtime   = 1.0;
  }

  /** Add a single trace line 
   * return 1 if successful
   */
  final int TYPES  = 0;
  final int BARS   = 1;
  final int EVENTS = 2;
  int section = TYPES;  
  int addTrace(String l) {
    int success = 1;
    if (l.startsWith("$")) {
      // System.out.println("Changing section\n");
      if ("$types".compareTo(l) == 0)        section = TYPES;
      else if ("$bars".compareTo(l) == 0)    section = BARS;
      else if ("$events".compareTo(l) == 0)  section = EVENTS;
      else { 
	System.out.println("Couldn't read section name "+l);
	success = 0;
      }
    } else if (section==TYPES) {
      // Format: typename flag1 flag2 ...
      types.add(new TypeParam(l));
    } else if (section==BARS) {
      // Format: barname bartype
      StringTokenizer st = new StringTokenizer(l);
      String barname, bartype;
      if (st.hasMoreTokens()) { 
	barname = st.nextToken(); 
	if (st.hasMoreTokens()) { 
	  bartype = st.nextToken(); 
	  TypeParam btp = types.find(bartype);
	  if (btp!=null) {
	    entries.add(new Entry(barname, btp));
	  } else {
	    System.out.println("Couldn't find type "+bartype);
	    success = 0;
	  }
	}
      }
    } else if (section==EVENTS) {
      StringTokenizer st = new StringTokenizer(l, ": \n\r\t");
      if(st.nextToken().charAt(0) != 'u')
	;//System.out.println("Error: Only do 'u:' traces");
      else {
	String barname = st.nextToken();
	Entry e = find(barname);
	if (e==null) 
	  ;// System.out.println("Couldn't find bar:"+barname);
	else {
	  st.nextToken(); // skip 'at'
	  double timestamp = 
	    (Double.valueOf(st.nextToken())).doubleValue();
	  if (timestamp > endtime) endtime = timestamp;
	  switch(st.nextToken().charAt(0)) {
	  case('P'):
	    String val = st.nextToken();
	    int stateno = e.getType().get_index(val);
	    e.add(new Event(timestamp, stateno));
	    break;
	  default:
	    success = 0;
	    ;//System.out.println("Error: event type not P\n\t"+s);
	    break;
	  }
	}
      }
    }
    return success;
  }


  Types getTypes() { return types; }
  double getStartTime() { return starttime; }
  double getEndTime() { return endtime; }
  void draw(Graphics g, double t1, double t2, int w, int h) {
    int n = entries.size();
    if (n>0) {
      double wavespace  = h / n;
      double waveheight = wavespace * 0.75;
      for (int i=0; i < n; i++) {
	Entry en = (Entry)(entries.get(i));
	en.setPosition(0,i * (int)wavespace, w, (int)waveheight);
	en.draw(g,t1,t2);
      }
    }
  }
  void drawLabels(Graphics g, int w, int h) {
    int n = entries.size();
    if (n>0) {
      double wavespace  = h / n;
      double waveheight = wavespace * 0.5;
      for (int i=0; i < n; i++) {
	Entry en = (Entry)(entries.get(i));
	en.drawLabel(g, 0,i * (int)wavespace +  (int)waveheight );
      }
    }
  }
  Entry find( String s ) {
    Entry found = null, curr;
    int entries_size = entries.size();
    for (int i=0; i < entries_size; i++) {
      curr = (Entry)entries.get(i);
      if (s.compareTo(curr.name) == 0) { found = curr; }
    }
    return found;
  }

}

class Labels extends Panel {
  Entries entries;
  Labels( Entries e ) {
    entries = e;
  }
  public void paint(Graphics g) {
    Dimension d = getSize(); 
    g.setColor(Color.black);
    entries.drawLabels(g, d.width, (int)((double)d.height*0.90));
  }
}

class Key extends Panel {
  Types t;
  Key( Types t ) { this.t = t; }
  public void paint(Graphics g) {
    Dimension d = getSize(); 
    t.drawKeys(g, d.width, d.height);
  }
}

class Controls extends Panel implements ActionListener {
  Button quitb, zoomb, unzoomb, reloadb;
  Diagram d;

  Controls(Diagram d) {
    this.d = d;
    add(quitb=new Button("Quit"));
    add(zoomb=new Button("Zoom"));
    add(unzoomb=new Button("Unzoom"));
    //  add(reloadb=new Button("Reload"));
    quitb.addActionListener(this);    
    zoomb.addActionListener(this);    
    unzoomb.addActionListener(this);    
    //reloadb.addActionListener(this);    
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == quitb) { d.quitb(); }
    if (source == zoomb) { d.zoomb(); }
    if (source == unzoomb) { d.unzoomb(); }
    if (source == reloadb) { d.reloadb(); }
  }
}

/** Creates Graphics area
 */
class Diagram extends Panel implements MouseMotionListener,
  MouseListener, AdjustmentListener {
  Image backingIm;
  Graphics backg;
  Dimension backingSize;
  Color bg;
  Scrollbar sb;
    
  Entries entries;

  int startx, starty;
  int Xpos, Opos;
  boolean movingX, movingO;
  double startt, endt;
  int keySpace = 50;

  Diagram( ) {
    bg = new Color(50,50,60);
    setBackground(bg);
    startx=0; starty=0;
    Xpos = 0; Opos = 0;
    movingX = false; movingO = false;
 
    entries = new Entries();
    startt = 0.0; 
    endt   = entries.getEndTime();
    addMouseMotionListener(this);
    addMouseListener(this);
  }

  void set_sb(Scrollbar s) { sb = s; s.addAdjustmentListener(this); }

  /* External commands */
  void reLayout() { 
    entries.reset();
  } 
  void processTrace(String t) {
    entries.addTrace(t);
  }
  void display() {
    unzoomb();	// Resets start + end times.
    //    drawData();
    //repaint();
  }

  /* Utility methods */
  double xtot(int x) {
    return startt + ((double)x * (endt-startt) / (double)backingSize.width);
  }
  int ttox(double t ) {
    return  (int)(((double)backingSize.width) * (t-startt)/(endt-startt));
  }

  /* Push button response methods */
  void quitb() {
    System.exit(0);
  }
  void zoomb() {
    int leftm = Xpos;
    int rightm = Opos;
    if (leftm > rightm) { leftm = Opos; rightm = Xpos; }
    if (rightm-leftm>5) {
      startt = xtot(leftm);
      endt = xtot(rightm);
    } else { // Default is zoom *2
      endt = startt + (endt-startt)/2;
    }
    drawData();
    repaint();
    
    sbset();
  }
  void sbset() {
    double theend = entries.getEndTime();
    int s1 =(int)( 1000.0 * (startt / theend));
    int s2 =(int)( 1000.0 * ((endt-startt) / theend));
    sb.setValues(s1, s2, 0, 1000);   
  }
  void sbmoved() {
    int v = sb.getValue();
    double t = ((double)v / 1000.0) * entries.getEndTime();
    double delta = startt - t;
    startt = t;
    endt = endt - delta;
    drawData();
    repaint();
  }
  void unzoomb() {
    startt = 0.0;
    endt   = entries.getEndTime();
    drawData();
    sbset();
    repaint();
  }
  void reloadb() {
  }

  Entries getEntries() { return entries; }
		 
  public void paint(Graphics g) {
    Dimension d = getSize();
    if ((backingIm==null) || (d.width != backingSize.width) ||
	(d.height != backingSize.height)) {
      backingIm = createImage(d.width, d.height);
      backingSize = d;
      backg = backingIm.getGraphics();
      keySpace = (int)((double)backingSize.height * 0.10); //10% space used for timeline
      drawData();
    }

    g.drawImage(backingIm, 0,0, null);
    drawLines(g);
  }

  double log10(double d) {
    return Math.log(d)/Math.log(10);
  }
 
  void drawNotches(Graphics g, int x1, int y1, int x2, int y2, 
                   double v1, double v2) {
    boolean isXaxis = (y1==y2);
    double tsize = v2 - v1;                     // e.g. 17.2
    double tgaplog = log10( (double) tsize );   // e.g. 1.2ish
    int tgaprounded = (int)tgaplog;             // e.g. 1
    double tgapfinal = Math.pow( 10.0, (double) tgaprounded ); // e.g.10.0
    // Change to 0 5 10 etc?
    if (tsize/tgapfinal < 3.0) { tgapfinal /= 2.0; }
    
    // Get first point
    double div = 0.5 + (v1 / tgapfinal);
    int idiv = (int) div;
    double firstt1 = idiv * tgapfinal;
 
   
    g.setColor(Color.gray);
    g.drawLine(x1,y1,x2,y2);

    int notchHeight = keySpace/5;
    for (double t=firstt1; t<=v2; t+=tgapfinal ) {
      int xp = ttox(t);
      String s = Double.toString(t);
      g.setColor(Color.gray);
      g.drawLine(xp,y1,xp,notchHeight+y1);
      int w = g.getFontMetrics().stringWidth(s);
      int h = g.getFontMetrics().getHeight();
      int stry = y1+notchHeight+h; 
      if (stry>y1+keySpace) stry = y1+keySpace;
      g.drawString(s,xp-w/2,stry);
      
      g.setColor(Color.gray.darker());
      g.drawLine(xp,y1,xp,0);
    } 
  }
 

  void drawLines(Graphics g) {
    g.setXORMode(bg);
    g.setColor(Color.blue);
    g.drawLine(Opos,0,Opos,backingSize.height);
    g.setColor(Color.red);
    g.drawLine(Xpos,0,Xpos,backingSize.height);
    g.setPaintMode();
  }	

  void drawData() {
    backg.setColor(bg);
    backg.translate(0,0);
    backg.fillRect(0,0,backingSize.width,backingSize.height);
    drawNotches(backg,0, backingSize.height-keySpace,
		backingSize.width, backingSize.height-keySpace,startt,endt);
    entries.draw(backg, startt, endt,backingSize.width,
		 backingSize.height - keySpace);
  }
  
  int abs(int a) { return (a<0) ? -a : a; }
  
  public void mouseDragged(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    if (movingX) {
      drawLines(getGraphics());
      Xpos = x; 
      drawLines(getGraphics());
    } else if (movingO) {
      drawLines(getGraphics());
      Opos = x;
      drawLines(getGraphics());
    }
  }
  public void mouseMoved(MouseEvent e) {  }
  public void mouseClicked(MouseEvent e) {  }
  public void mousePressed(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
      // Clicked right
      int lp = Xpos; int rp = Opos;
      if (rp<lp) { lp = Opos; rp = Xpos; }
      if ((x>=lp) && x<=rp)
	zoomb();
      else
	unzoomb();
    } else {
      drawLines(getGraphics());
      movingO = false;
      movingX = false;
      if (abs(x-Xpos) < abs(x-Opos)) {
	Xpos = x;
	movingX = true;
      } else {
	Opos = x;
	movingO = true;
      }
      drawLines(getGraphics());
    }
  }
  public void mouseReleased(MouseEvent e) {  }
  public void mouseEntered(MouseEvent e) {  }
  public void mouseExited(MouseEvent e) {  }
  /** Respond to scroll bar events */
  public void adjustmentValueChanged(AdjustmentEvent e) {
    sbmoved();
  }
}


/**
 * This class represents the timing diagram application.
 * Note that it does not provide its own frame, it has
 * to be included in another frame. 
 * The <a href="eduni.simdiag.TimingWindow.html">timing window</a>
 * embeds this into a window of its own.
 *<p>
 * This applet listens to trace events arriving, and also
 * passes them on to any registered listeners added using the 
 * standard <a href="#addTraceListener">addTraceListener</a> method.
 *
 * @version     1.0, June 1997
 * @author      Fred Howell
 */
public class TimingDiagram extends Applet implements 
  TraceListener, Traceable, Runnable {
  transient Thread thread;
  Diagram diag;
  Labels labels;
  Key keys;
  Scrollbar hscroll;

  /** Constructs a timing diagram */
  public TimingDiagram() {
    super();
    thread = new Thread(this);
    thread.start();
    //    init();
  }
  /** Draws diagram */
  public void run() {
    init();
  }

  private List traceListeners = new ArrayList();
  private TraceEventObject lastTraceEvent;

  /** Adds a listener to the trace stream */
  public synchronized void addTraceListener(TraceListener l) {
    traceListeners.add(l);
  }
  /** Removes a trace listener */
  public synchronized void removeTraceListener(TraceListener l) {
    traceListeners.remove(l);
  }
  /** Processes the trace events which arrive. */
  public void handleTrace(TraceEventObject e) {
    lastTraceEvent = e;
    // System.out.println("Trace event arrived "+e.getCmd()+" "+e.getLine());
    if (e.getCmd() == TRACE) {
      diag.processTrace(e.getLine());
    } else if (e.getCmd() == LAYOUT) {
      diag.reLayout();
    } else if (e.getCmd() == DISPLAY) {
      diag.display();
      labels.repaint();
      keys.repaint();

    }

    forwardTrace(e);
  }
  /** Sends trace events on to any listeners. */
  public void forwardTrace(TraceEventObject e) {
    List l;
    synchronized(this) { l = (List)((ArrayList)traceListeners).clone(); }
    TraceEventObject teo = new TraceEventObject(this,e);
    for (int i=0; i<l.size(); i++) {
      TraceListener tl = (TraceListener) l.get(i);
      tl.handleTrace(teo);
    }
  }

  /** Displays the graph */
  public void display() {
    diag.repaint();
    labels.repaint();
    keys.repaint();
  }


  /** Sets up timing diagram display */
  public void init() {
 
    /* The components */
    diag = new Diagram();
    labels = new Labels(diag.getEntries());
    Scrollbar vscroll= new Scrollbar(Scrollbar.VERTICAL, 0, 1000, 0, 0);
    hscroll= new Scrollbar(Scrollbar.HORIZONTAL, 0, 1000, 0, 0);
    diag.set_sb(hscroll);
    keys = new Key(diag.getEntries().getTypes());
    Controls buttons = new Controls(diag);

    /* Putting the components into the gridbag */
    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    setFont(new Font("TimesRoman", Font.PLAIN, 14));
    setLayout(gb);
    
    c.fill = GridBagConstraints.BOTH;
    c.gridwidth = 3;
    c.weightx = 3.0;
    c.weighty = 10.0;
    add(labels); gb.setConstraints(labels,c);
    c.weightx = 10.0;
    add(diag); gb.setConstraints(diag,c);
    c.weightx = 0.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    add(vscroll); gb.setConstraints(vscroll,c);

    // Row 2 - just a hscroll
    Panel spc1 = new Panel(); Panel spc2 = new Panel();
    
    c.gridwidth  = 3;
    c.weightx = 3.0;
    c.weighty = 0.0;
    add(spc1); gb.setConstraints(spc1,c);
    c.weightx = 10.0;
    add(hscroll); gb.setConstraints(hscroll,c);
    c.weightx = 0.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    add(spc2);    gb.setConstraints(spc2,c);

    // Rows 3 and 4
      
    c.gridwidth  = 1;
    c.weightx = 1.0;
    c.weighty = 5.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    add(keys); gb.setConstraints(keys, c);

    c.gridwidth  = 1;
    c.weightx = 1.0;
    c.weighty = 0.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    add(buttons); gb.setConstraints(buttons, c);
  }


  /** Example standalone application code.
   * Creates a timing diagram and wires a trace loader to its input.
   */
  public static void main(String args[]) {
    Frame f = new Frame("Timing Diagram Example");
    TimingDiagram ex1 = new TimingDiagram();
    TraceLoader tl = new TraceLoader("http://www.dcs.ed.ac.uk/home/fwh/tmp.trace");
    tl.addTraceListener(ex1);
    tl.startLoading();
    f.add("Center", ex1);
    f.pack();
    f.setSize(600,300);
    f.show();
  }
}
