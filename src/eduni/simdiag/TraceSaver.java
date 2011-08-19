
 
package eduni.simdiag;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.applet.Applet;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * TraceSaver
 * Stores trace events in a file.
 */
public class TraceSaver implements Traceable, TraceListener {

  private PrintWriter trcstream;
  private String fname;
  private boolean isBroke = false;

  /** Creates a trace saver with the given filename */
  public TraceSaver(String fname) {
    this.fname = fname;
    open();
  }

  void open() {
    isBroke = false;
    try {
      if (trcstream!=null)
	trcstream.close();
      trcstream = new PrintWriter(new FileOutputStream(fname));
    } catch(IOException e) {
      isBroke = true;
      System.out.println("TraceSaver: Error - couldn't open trace file");
    } catch (Exception ex) {
      isBroke = true;
      System.out.println("TraceSaver: file error. Probably security checks"+
 " won't allow writing of a tracefile.  " +ex);
    }
  }

  void close() {
    if (!isBroke) trcstream.close();
  }

  /** Responds to a trace input event by writing it on to the file. */
  public void handleTrace(TraceEventObject e) {
    if (e.getCmd() == TRACE) {
      if (!isBroke) trcstream.println(e.getLine());
      // System.out.println("Writing: "+e.getLine());
    } else if (e.getCmd() == LAYOUT) {
      if (!isBroke) open();
    } else if (e.getCmd() == DISPLAY) {
      if (!isBroke) trcstream.flush();
    }
  }
}
