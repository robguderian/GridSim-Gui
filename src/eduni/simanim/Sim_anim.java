/** Sim_anim.java */

package eduni.simanim;

import eduni.simjava.Sim_system;
import eduni.simjava.Sim_output;
import eduni.simjava.Sim_reporter;
import eduni.simdiag.Traceable;
import eduni.simdiag.TraceEventObject;
import eduni.simdiag.TraceListener;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.text.NumberFormat;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Panel;

/**
 * The class that manages the simulation in animated mode. Do <b>not</b> use this class directly.
 * @version 1.2, 12 July 2002
 * @author  Costas Simatos
 */
public class Sim_anim extends Panel implements Sim_output, Traceable {
  private int width=100, height=100;    // Our width and height
  private int traceCount;               // How many traces we've seen
  private List events;
  private List entities;
  private Param_type_list ptypes; 
  private String msgString;
  private Image staticImage;       // Off screen buffer for drawing graphics
  private Graphics staticGraphics; // and it's graphics region
  private Color bgcolor = new Color(192, 196, 192);
  private static NumberFormat nf;
  private long lastsimtime;	       // Time for last sim step (ms)
  private List sends = new ArrayList(); // Stores list of send events for messages


  /* Javabeans trace event output */
  private List traceListeners = new ArrayList();
  private TraceEventObject lastTraceEvent;
  /** Do not use this method directly. */
  public synchronized void addTraceListener(TraceListener l) {
    traceListeners.add(l);
  }
  /** Do not use this method directly. */
  public synchronized void removeTraceListener(TraceListener l) {
    traceListeners.remove(l);
  }
  /** Do not use this method directly. */
  public void forwardTrace(TraceEventObject e) {
    List l;
    TraceEventObject weo = new TraceEventObject(this,e);
    synchronized(this) {
      l = (List)((ArrayList)traceListeners).clone();
    }
    int l_size = l.size();
    for (int i=0; i < l_size; i++) {
      TraceListener wl = (TraceListener) l.get(i);
      wl.handleTrace(weo);
    }
  }


  // One time initialisation
  /** Do not use this method directly. */
  public Sim_anim(Anim_applet applet)  {
    super();
    entities = new ArrayList();
    ptypes = new Param_type_list();
    msgString = new String();
    staticImage = null;
    staticGraphics = null;
    this.applet = applet;
    System.gc(); // Now is a good time to tidy up
    // Set the default number format
    nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(4);
    nf.setMinimumFractionDigits(2);
    lastsimtime = 0;
  }

  /** Do not use this method directly. */
  public void link_ports(String e1, String p1, String e2, String p2) {
    Anim_port port1, port2;
    Anim_entity ent1 = find_entity(e1);
    Anim_entity ent2 = find_entity(e2);
    if (ent1==null) {
      System.out.println("Couldn't find anim ent: "+e1);
    } else if (ent2==null) {
      System.out.println("Couldn't find anim ent: "+e2);
    } else {
      port1 = ent1.find_port(p1);
      port2 = ent2.find_port(p2);
      if (port1==null) {
	System.out.println("Couldn't find anim port: "+p1);
      } else if (port2==null) {
	System.out.println("Couldn't find anim port: "+p2);
      } else {
	port1.link_port(port2, true);  port2.link_port(port1, false);
      }
    }
  }

  /** Do not use this method directly. */
  public void add_entity(Anim_entity ent) { entities.add(ent); }

  //
  // Some global image management stuff
  //
  private static List images = new ArrayList();    // Image files we have loaded
  private static List img_names = new ArrayList(); // And the names of the files
  static Anim_applet applet;
  static Image get_image(String name) {
    int index;
    MediaTracker track;
    Image image = null;

    index = img_names.indexOf(name);
    if(index == -1) {
      URL cb = applet.getCodeBase();
      if (cb == null) { 
	  try { cb=new URL("file:./"); } catch (Exception e ) { System.out.println(e); }
      }
      image = applet.getImage(cb, "bitmaps" + System.getProperty("file.separator") + name + ".gif");
      track = new MediaTracker(applet);
      track.addImage(image,0);
      try { track.waitForID(0); }
      catch(InterruptedException e) { System.out.println("Image loaded"); }
      if(image == null)
        System.out.println("Failed to load image :"+name+".gif");
      img_names.add(name);
      images.add(image);
      return(image);
    } else {
      return((Image)images.get(index));
    }
  }


  // Initialisation for each anim run
  /** Do not use this method directly. */
  public void initialise() {
    traceCount = 0;
    events = new ArrayList();
    entities = new ArrayList();
    ptypes.reset();
  }

  /** Do not use this method directly. */
  public void setup_static_initial() {
    boolean changed = false;
    width = 10;
    height = 10;
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      Point ent_end = ((Anim_entity)entities.get(i)).get_end_position();
      int x = (int)ent_end.getX();
      int y = (int)ent_end.getY();
      if (x > width) {
        width = x;
        changed = true;
      }
      if (y > height) {
        height = y;
        changed = true;
      }
    }
    if (changed) {
      width += 10;
      height += 50;
    }
    staticImage = createImage(width, height);
    staticGraphics = staticImage.getGraphics();
    staticGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 12));
    this.setSize(width, height);
    set_message("Initialising");
  }

  private void setup_static() {
    staticImage = createImage(width, height);
    staticGraphics = staticImage.getGraphics();
    staticGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 12));
    this.setSize(width, height);
  }

  // Register param type. Called from Anim_entity whenever add_param() called.
  void add_param_type(Param_type pt) {  ptypes.add(pt); }

  /** Do not use this method directly. */
  public void genTraceHeader() {
    TraceEventObject t = new TraceEventObject(this,LAYOUT);
    forwardTrace(t);
    t.set("$types");
    forwardTrace(t);
    // Get from ptypes...
    List pv = ptypes.getV();
    int pv_size = pv.size();
    for (int i=0; i<pv_size; i++) {
      Param_type pt = (Param_type)pv.get(i);
      String s = new String(pt.getSpec());
      t.set(s);
      forwardTrace(t);
    }
    t.set("$bars");
    forwardTrace( t );
    // From entity list...
    int entities_size = entities.size();
    for (int i=0; i<entities_size; i++) {
      Anim_entity ae = (Anim_entity)entities.get(i);
      String s = ae.get_bar_string();
      if (s != null){
	t.set(ae.get_bar_string());
	forwardTrace(t);
      }
    }
    t.set("$events");
    forwardTrace(t);
  }
  /** Do not use this method directly. */
  public void genTraceTail() {
    forwardTrace( new TraceEventObject(this,DISPLAY) );
  }
  /** Do not use this method directly. */
  public void dispTrace() {
    forwardTrace( new TraceEventObject(this,DISPLAY) );
  }

  // Accept a trace line from the simulation, and store it
  /** Do not use this method directly. */
  public void println(String msg) {
    long delay = System.currentTimeMillis();
    traceCount++;
    events.add(new Anim_event(msg, this));
    delay = System.currentTimeMillis() - delay;
    // Forward to any listeners
    forwardTrace( new TraceEventObject(this,msg) );
  }

  // Called by the simulation when it is done
  /** Do not use this method directly. */
  public void close() {
  }

  // Search for an entity called ent_name
  Anim_entity find_entity(String ent_name) {
    Anim_entity ent;
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
       ent = (Anim_entity)entities.get(i);
       if(ent.get_name().equals(ent_name)) return ent;
    }
    // System.out.println("Error: find_entity() could not find: "+ent_name);
    return null;
  }

  /** Do not use this method directly. */
  public void paint(Graphics g) {
    // Check if we've been resized or just brand new
    if((staticImage == null) || (width != this.getSize().width) || (height != this.getSize().height)) {
      setup_static();
      draw_all_static();
    }
    // just slap the static buffer down
    g.drawImage(staticImage,0,0,this);
    draw_messages(g);
  }

  void draw_messages(Graphics g) {
    int sends_size = sends.size();
    for (int i=0; i < sends_size; i++) {
      Anim_port port = (Anim_port)sends.get(i);
      port.draw_messages(g);
    }
  }

  /** Do not use this method directly. */
  public void update(Graphics g) {
    paint(g);
  }
  
  // To force the painting of an update
  // Call repaint() then give painting thread a chance to do it
  void show_update(Thread simThread) {
    repaint();
    if (simThread != null) {
      do {
        try {
          simThread.sleep(applet.get_speed());
        } catch(InterruptedException except) {}
      } while (applet.get_paused());
    }
  }

  // Draw all of the initial static ent stuff
  /** Do not use this method directly. */
  public void draw_all_static() {
    staticGraphics.setColor(bgcolor);
    staticGraphics.fillRect(0,0,width,height);
    staticGraphics.setColor(Color.black);
    staticGraphics.drawString(msgString, 5, height-5);
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      ((Anim_entity)entities.get(i)).draw(staticGraphics);
    }
  }

  // redraw the msgString
  /** Do not use this method directly. */
  public void set_message(String new_msg) {
    staticGraphics.setColor(bgcolor);
    staticGraphics.drawString(msgString, 5, height-5);
    staticGraphics.setColor(Color.black);
    staticGraphics.drawString(new_msg, 5, height-5);
    msgString = new_msg;
  }

  // The main animation loop
  // Called by the applet after the simulation has finished
  // fwh - Updated to run the simulation too.
  /** Do not use this method directly. */
  public void animate(Thread simThread) {
    int anim_steps = 10, i, ev_index;
    double timestamp = 0.0;
    Anim_event ev;
    Anim_port port;
    draw_all_static();
    // Generate trace info
    genTraceHeader();
    // Set simulation going
    Sim_system.run_initialise();
    ev_index = 0;
    int upidx = 0; // Extra update for time if no animated events
    while (Sim_system.incomplete()) {
      if (!Sim_system.running()) Sim_system.run_start();
      while (Sim_system.check_conditions() && !(applet.get_stopped())) {
        if (Sim_system.run_tick()) {
          break;
        }
        // Run One Simulation Step
        long simdelay = System.currentTimeMillis();
        simdelay = System.currentTimeMillis() - simdelay;
        sends.clear();
        // Delay For Animation Time
        long anim_time = applet.get_speed() - simdelay;
        if (anim_time < 1) anim_time = 1;
        if (anim_time>1) {
          try {
            simThread.sleep(anim_time);
          } catch(InterruptedException except) {}
        }
        upidx++;
        if (upidx == 100) {
          upidx = 0;
          set_message("Running: sim time = " + nf.format(Sim_system.clock()));
          show_update(simThread);
        }
        // Update the state
        int events_size = events.size();
        while (ev_index < events_size) {
          ev = (Anim_event)events.get(ev_index);
          timestamp = ev.timestamp;
          // Process all events with the same timestamp
          while (ev_index < events_size) {
            ev = (Anim_event)events.get(ev_index);
            if (ev.timestamp > timestamp) break;
            switch (ev.type) {
              case(Anim_event.SEND):
                sends.add(ev.src_port);
                ev.src_port.set_data(ev.data.toString());
                break;
              case(Anim_event.PARAM):
                ev.src_ent.set_params(ev.data.toString());
                ev.src_ent.draw(staticGraphics);
                break;
            }
            ev_index++;
          }
          // Ok repaint these updates
          set_message("Running: sim time = " + nf.format(timestamp));
          dispTrace();
          show_update(simThread);
          // Only animate sends if speed > 1
          if (applet.get_speed() > 1) {
            // Now animate all the sends we encountered
            if (sends.size() > 0) {
              for (i=0; i<=anim_steps; i++) {
                int sends_size = sends.size();
                for (int j=0; j < sends_size; j++) {
                  port = (Anim_port)sends.get(j);
                  if (i == anim_steps) {
                    port.move_msg(-1.0);
                  } else {
                    port.move_msg((1.0*i)/anim_steps);
                  }
                }
                show_update(simThread);
              }
            }
          }
        }
      }
      Sim_system.tidy_up_stats();
      if (applet.get_stopped()) {
        Sim_system.animation_stopped();
      }
      Sim_system.apply_variance_reduction();
      Sim_system.end_current_run();
      if (applet.get_stopped()) break;
    }
    // Our work here is done:
    Sim_system.run_stop();
    show_update(simThread);
    set_message("Sim completed: sim time = " + nf.format(timestamp));
    draw_all_static();
    genTraceTail();
    show_update(simThread);
    Sim_system.generate_report();
    applet.finished();
  }

  /** Do not use this method directly. */
  public Sim_reporter get_reporter() {
    return applet;
  }

  /** Do not use this method directly. */
  public Anim_applet get_applet() {
    return applet;
  }
}
