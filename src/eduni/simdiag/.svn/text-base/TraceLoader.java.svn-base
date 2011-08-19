package eduni.simdiag;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;

/**
 * TraceLoader 
 * Converts trace from URL to TraceEventListener format
 */
 
public class TraceLoader implements Traceable, Runnable {

  transient Thread thread;
  String urlName;


  /** Create a trace loader with the given url */
  public TraceLoader(String urlName) {
    this.urlName = urlName;
    thread = new Thread(this);
  }

  /** Called after output has been wired */
  public void startLoading() {
    thread.start();
  }

  protected String nextLine(BufferedReader r) {
    try {
      String l = r.readLine();
      return l;
    } catch (Exception e) {
      return null;
    }
  }

  /** Starts reading the trace in from the URL. */
  public void run() {
    URL u;
    try {
      u = new URL(urlName);
    } catch (Exception e) {
      System.out.println("Can't open URL "+urlName);
      return;
    }

    DataInputStream file;
    try {
      file = new DataInputStream(u.openStream());
    } catch (IOException ioe) {
      System.out.println("Couldn't open URL\n");
      return;
    }
    BufferedReader in = new BufferedReader(new InputStreamReader(file));
    String l;

    int numevents = 0;
 
    forwardTrace( new TraceEventObject(this,LAYOUT) );

    while ((l = nextLine(in))!=null) {
      forwardTrace( new TraceEventObject(this,l) );
      numevents += 1;
    }    
    
    forwardTrace( new TraceEventObject(this,DISPLAY) );

    System.out.println("Read in "+numevents+" lines");
  }

  private List traceListeners = new ArrayList();
  /** Javabeans trace event output */
  public synchronized void addTraceListener(TraceListener l) {
    traceListeners.add(l);
  }
  /** Javabeans trace event output */
  public synchronized void removeTraceListener(TraceListener l) {
    traceListeners.remove(l);
  }
  /** Sends trace event onwards to any listeners */
  public void forwardTrace(TraceEventObject e) {
    List l;
    TraceEventObject weo = new TraceEventObject(this,e);
    synchronized(this) { l = (List)((ArrayList)traceListeners).clone(); }
    for (int i=0; i<l.size(); i++) {
      TraceListener wl = (TraceListener) l.get(i);
      wl.handleTrace(weo);
    }
  }  
}
