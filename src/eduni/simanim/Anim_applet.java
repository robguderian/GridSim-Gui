/* Anim_applet.java */

package eduni.simanim;

import eduni.simjava.Sim_reporter;
import eduni.simjava.Sim_system;
import eduni.simdiag.Traceable;
import eduni.simdiag.TraceEventObject;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.Scrollbar;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Button;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

/**
 * The superclass for all animated simulations.
 * <p>
 * Users that want to add animation to their simulations need to subclass <code>Anim_applet</code>.
 * Following this they should override some or all of the following methods:
 * <ul>
 *   <li>
 *     <code>anim_layout</code>: This method <b>must</b> be overriden to set up the entities and
 *     link their ports. Before setting up the entities, the sample generators' seed sequences could be modified.
 *   <li>
 *     <code>anim_init</code>: This method <b>may</b> be overriden to add GUI components to the
 *     simulation's animation. The way to do this would be to prepare the components and then add them to the
 *     applet. The applet uses a <code>BorderLayout</code>. The user in <code>anim_init</code> may add to
 *     <i>"North"</i>, <i>"East"</i> and <i>"West"</i> (for example <code>this.add("North", anOptionsPanel)</code>).
 *   <li>
 *     <code>anim_completed</code>: This method <b>may</b> be overriden to add code that will be executed once the
 *     simulation completes.
 *   <li>
 *     <code>anim_output</code>: This method <b>may</b> be overriden to control the reporting information of the
 *     animation. Since version 1.3, the simulation's report and simulation messages may be included in animated
 *     simulations.
 *   <li>
 *     <code>sim_setup</code>: This method <b>may</b> be overriden to add simulation conditions and set simulation
 *     parameters. If a transient or termination condition is being used and if an output analysis method is selected
 *     this method <b>must</b> be overriden to call these methods on <code>Sim_system</code>.
 * </ul>
 * <p>
 * More information of how to use these methods and how to add animation to simulations can be found at
 * the <a href="http://www.dcs.ed.ac.uk/home/simjava/tutorial/index.html#10">SimJava Tutorial</a>.
 * @see eduni.simjava.Sim_system
 * @version 1.3, 12 July 2002
 * @author Costas Simatos
 */
public abstract class Anim_applet extends Applet implements Runnable,
ActionListener, AdjustmentListener, Traceable, Sim_reporter {
  Thread simThread;
  // The AWT bits
  protected Sim_anim trace_out;
  Button layoutBut, runBut, stopBut, pauseBut;
  Label speedLabel;
  Scrollbar speedScroll;
  ScrollPane animScroll;
  TextArea reportArea, messagesArea;
  // The options
  int speed = 10;
  boolean paused = false;
  boolean stopped = false;
  boolean do_report = false;
  boolean do_messages = true;
  StringBuffer report;

  // Two functions that should be over-ridden in the sub-class
  // MUST be used to set up all the entities

  /**
   * Setup the simulation. This method <b>must</b> be overriden in the subclass
   * and completed to add the simulation's entities and to link their ports.
   */
  public abstract void anim_layout();

  /**
   * Set up additional GUI components. This method <b>may</b> be overriden to specify
   * additional GUI components in the simulation's applet. This method is called once the applet
   * starts up.
   */
  public void anim_init() {} 
         
  /**
   * Define code to be execute upon simulation completion. This method is called when the simulation completes.
   */
  public void anim_completed() {}          

  /**
   * Internal method used to set up the default GUI components and the entities. This method should <b>not</b>
   * be overriden or used directly.
   */
  public final void init() {
    anim_output();
    Panel controls = new Panel();
    GridBagLayout gb = new GridBagLayout();
    controls.setLayout(gb);
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(2, 2, 2, 2);
    c.weightx = 1.0;
    layoutBut = new Button("Layout");
    layoutBut.addActionListener(this);
    gb.setConstraints(layoutBut, c);
    controls.add(layoutBut);
    runBut = new Button("Run");
    runBut.addActionListener(this);
    gb.setConstraints(runBut, c);
    controls.add(runBut);
    pauseBut = new Button("Pause");
    pauseBut.addActionListener(this);
    gb.setConstraints(pauseBut, c);
    pauseBut.setEnabled(false);
    controls.add(pauseBut);
    c.gridwidth = GridBagConstraints.REMAINDER;
    stopBut = new Button("Stop");
    stopBut.addActionListener(this);
    gb.setConstraints(stopBut, c);
    stopBut.setEnabled(false);
    controls.add(stopBut);
    c.gridwidth = 1;
    speedLabel = new Label("Speed: " + speed);
    gb.setConstraints(speedLabel, c);
    controls.add(speedLabel);
    c.gridwidth = GridBagConstraints.REMAINDER;
    speedScroll = new Scrollbar(Scrollbar.HORIZONTAL, speed, 1, 1, 1000);
    speedScroll.setUnitIncrement(10);
    speedScroll.addAdjustmentListener(this);
    gb.setConstraints(speedScroll, c);
    controls.add(speedScroll);
    if (do_messages) {
      messagesArea = new TextArea(2, 10);
      messagesArea.setEditable(false);
      c.gridwidth = GridBagConstraints.REMAINDER;
      gb.setConstraints(messagesArea, c);
      controls.add(messagesArea);
    }
    if (do_report) {
      reportArea = new TextArea(5, 10);
      reportArea.setEditable(false);
      c.gridwidth = GridBagConstraints.REMAINDER;
      gb.setConstraints(reportArea, c);
      controls.add(reportArea);
    }
    trace_out = new Sim_anim(this);
    animScroll = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
    animScroll.add(trace_out);
    this.setLayout(new BorderLayout(5,5));
    this.add("Center", animScroll);
    this.add("South",  controls);
    // Modeller can add to "North", "East" and "West"
    anim_init();
    do_layout();
  }

  /**
   * Specify simulation conditions, an output analysis method and other simulation parameters.
   * If any of these are used in the simulation this method <b>must</b> be overriden to make
   * the relevant <code>Sim_system</code> calls.
   */
  public void sim_setup() { }

  // Lay out the animation
  private void do_layout() {
    Sim_system.initialise(trace_out, simThread);
    anim_layout();
    trace_out.setup_static_initial();
    trace_out.draw_all_static();
    trace_out.repaint();
    this.repaint();
    trace_out.forwardTrace( new TraceEventObject(trace_out,LAYOUT) );
  }

  // Get the animation speed
  int get_speed() { return speed; }
  // Check to see if the animation is paused
  boolean get_paused() { return paused; }
  // Check to see whether the animation is stopped
  boolean get_stopped() { return stopped; }

  /**
   * Internal method used to pause the animation. Should <b>not</b> be used in user simulations.
   */
  public void pause() {
    paused = true;
    pauseBut.setLabel("Restart");
  }

  /**
   * Internal action handler. Should <b>not</b> be used or overriden in the subclass.
   * @param e The <code>ActionEvent</code>
   */
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == layoutBut) {
      do_layout();
    } else if (source == runBut) {
      sim_setup();
      Sim_system.set_auto_trace(false);
      Sim_system.generate_graphs(false);
      layoutBut.setEnabled(false);
      stopBut.setEnabled(true);
      pauseBut.setEnabled(true);
      paused = false;
      runBut.setEnabled(false);
      if (simThread == null) {
        simThread = new Thread(this);
      }
      simThread.start();
    } else if (source == stopBut) {
      stopped = true;
      paused = false;
      stopBut.setEnabled(false);
      pauseBut.setEnabled(false);
      runBut.setEnabled(false);
    } else if (source == pauseBut) {
      if (paused) {
        paused = false;
        pauseBut.setLabel("Pause");
      } else {
        paused = true;
        pauseBut.setLabel("Restart");
      }
    }
  }

  /**
   * Internal scrollbar event handler. Should <b>not</b> be used or overriden in the subclass.
   * @param e The <code>AdjustmentEvent</code>
   */
  public void adjustmentValueChanged(AdjustmentEvent e) {
    Object source = e.getSource();
    if (source == speedScroll){
      speed = speedScroll.getValue();
      speedLabel.setText("Speed: "+speed);
    }  
  }

  /**
   * Internal method used to run the animation. This method should <b>not</b> be used or overidden in the subclass.
   */
  public final void run() {
    System.gc();                   // Tidy up memory usage
    trace_out.animate(simThread);  // now run anim
    anim_completed();
  }

  /**
   * Internal method used to setup the simulation's report. Should <b>not</b> be used or overriden in the subclass.
   */
  public void setup_report() {
    if (do_report) {
      report = new StringBuffer();
    }
  }

  /**
   * Internal method used to close the simulation's report. Should <b>not</b> be used or overriden in the subclass.
   */
  public void close_report() {
    if (do_report) {
      reportArea.append(report.toString());
    }
  }

  /**
   * Internal method used to add to the simulation's report. Should <b>not</b> be used or overriden in the subclass.
   */
  public void append_report(String line) {
    if (do_report) {
      report.append(line);
      report.append('\n');
    }
  }

  /**
   * Specify the level of reporting the animation should provide. This method <b>may</b> be overriden
   * to specify that the simulation's report and messages should be displayed on the applet.
   * <p>
   * The <b>only</b> code that should be included here are a call to <code>generate_report</code> and
   * a call to <code>generate_messages</code>.
   * <p>
   * The default, if this method is not overriden, is to add only the simulation's messages.
   */
  public void anim_output() {}

  /**
   * Generate the simulation's report. This method should be called within the overriden <code>anim_output</code> method.
   * @param do_report <code>true</code> if the report should be generated, <code>false</code> otherwise.
   */
  public void generate_report(boolean do_report) {
    this.do_report = do_report;
  }

  /**
   * Generate the simulation's messages. This method should be called within the overriden <code>anim_output</code> method.
   * @param do_messages <code>true</code> if the messages should be generated, <code>false</code> otherwise.
   */
  public void generate_messages(boolean do_messages) {
    this.do_messages = do_messages;
  }

  // Called when the animation completes
  void finished() {
    pauseBut.setEnabled(false);
    stopBut.setEnabled(false);
    runBut.setEnabled(false);
  }

  /**
   * Internal method used to generate a message. This method should <b>not</b> be used or overriden in the subclass.
   */
  public void add_message(String msg) {
    if (do_messages) {
      messagesArea.append(msg + "\n");
    }
  }
}