/* Sim_system.java */

package eduni.simjava;

import eduni.simanim.Sim_anim;
import eduni.simjava.distributions.Sim_random_obj;
import eduni.simjava.distributions.Generator;
import java.util.ListIterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Date;
import java.util.zip.GZIPOutputStream;
import java.text.NumberFormat;
import java.text.DateFormat;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The <code>SimJava</code> simulation kernel.
 * <p>
 * This class is responsible for managing all aspects of the simulation. Instantiated entities
 * are added to <code>Sim_system</code> which is responsible for synchronising their behaviour.
 * Time progresses on the basis of event scheduling times. These events are held in two queues,
 * instances of <code>Evqueue</code>, the future queue and the deferred queue. All the runtime
 * functionality available to entities is managed by <code>Sim_system</code>.
 * <p>
 * In the previous versions of <code>SimJava</code>, users would use <code>Sim_system</code>
 * only to initialise the simulation, add the entities, link their ports, and finally run the
 * simulation. In version 2.0 <code>Sim_system</code> makes a lot more functionality available
 * to the modeller.
 * <p>
 * Three new aspects of <code>Sim_system</code> are the definition of a transient condition, a
 * termination condition, and the selection of an output analysis method to apply. All these are
 * managed internally by <code>Sim_system</code> so the user is not required to include relevant
 * code in the simulation's entities. To set the <b>transient condition</b> for the simulation
 * the user uses a <code>set_transient_condition</code> method. The simulation's <b>termination
 * condition</b> is set by using a <code>set_termination_condition</code> method. Finally, an
 * <b>output analysis method</b> is selected with one of the <code>set_output_analysis</code>
 * methods.
 * <p>
 * Available transient conditions are:
 * <ul>
 *   <li>
 *     A number of event completions. In this case the simulation is considered to have entered steady state
 *     after a certain number of event service completions at a given entity. Event completions
 *     are signalled to <code>Sim_system</code> with the entities'
 *     <code><a href="Sim_entity.html#sim_completed(eduni.simjava.Sim_event)">sim_completed</a></code> method.
 *   <li>
 *     A specific period of simulated time. After this time period elapses the simulation is considered to
 *     have entered steady state.
 *   <li>
 *     The minimum-maximum method. This method allows <code>Sim_system</code> to try and identify the
 *     transient period based on the minimum-maximum method for a given measure. This method considers
 *     steady state to have been entered once a measure observation has been located which is neither
 *     the minimum nor the maximum of the remaining observations.
 *   <li>
 *     None. This is the default if no condition is specified.
 * </ul>
 * <p>
 * Available termination conditions are:
 * <ul>
 *   <li>
 *     A number of event completions. The simulation is terminated once a given number of events complete
 *     service at a given entity. This condition is similar to the relevant transient condition.
 *   <li>
 *     A specific period of simulated time. After this time period elapses the simulation is terminated.
 *   <li>
 *     A measure's confidence interval accuracy. This method allows <code>Sim_system</code> to run the
 *     simulation until a confidence interval for a given measure has reached a certain accuracy level.
 *     To use this method, the user needs to provide the measure of interest, an output analysis method
 *     which will be used as a variance reduction technique, the confidence level, and the desired accuracy.
 *     The accuracy is the ratio of the confidence interval's halfwidth over the measure's total mean.
 *   <li>
 *     None. As before this is the default if no condition is specified. Non terminating simulations have
 *     meaning only for animation purposes in which case a simulation is used as an elaborate demo of a
 *     system.
 * </ul>
 * <p>
 * Entities may use the <code>running</code> method to check whether the simulation has completed.
 * <p>
 * Finally, the available output analysis methods are:
 * <ul>
 *   <li>
 *     Independent replications. In this case, the simulation run is repeated a number of times
 *     to produce total measurements.
 *   <li>
 *     Batch means. With this method, a single, long run is performed and then the obtained observations
 *     are batched into batches and their means are then used to produce the total measurements. The
 *     number of batches used is the one that gives the least serial correlation between the batch means.
 *     for the majority of measures.
 *   <li>
 *     None. Again this is the default method. In this case no output analysis is performed and only
 *     available sample measurements are produced.
 * </ul>
 * <p>
 * Since version 2.0 of <code>SimJava</code>, <code>Sim_system</code> also allows the modeller greater
 * control over the <b>seeding</b> of the random number generators. The simulation's generators can be created
 * in such a way to allow <code>Sim_system</code> to automatically seed them, ensuring well spaced
 * sample sequences for the generators. The original seed to be used as well as the seed spacing desired
 * can be set with the <code>set_seed_sequence</code> method. If this method is not used, the default
 * seed of 4851 is used to produce seeds spaced by 100000 samples. If independent replications are used
 * as an output analysis method and reseeding of the generators is desired in each subsequent replication,
 * the generators need to be added to their entities. This is accomplished with the entities'
 * <code><a href="Sim_entity.html#add_generator(eduni.simjava.distributions.Generator)">add_generator</a></code> method.
 * Otherwise the previous replication's last seed is used as the next one's initial seed.
 * <p>
 * <code>Sim_system</code> also provides control over the simulation's output. The <b>trace file</b>
 * (<code>sim_trace</code>) can be managed with more detail. The <code>set_trace_detail</code> method
 * can be used to specify whether or not to add the default trace , trace generated by the
 * entities, or trace for specific event types. In this last case, the event tags of interest can be
 * supplied to <code>Sim_system</code> with a <code>track_event</code> method. The level of entity
 * trace included in the trace file can be managed with the <code>set_trace_level</code> method. Finally,
 * tracing can be switched off alltogether with the <code>set_auto_trace</code> method. The default is
 * not to produce a trace file.
 * <p>
 * Another major aspect of the simulation's output is the <b>simulation's report</b>. The report is
 * automatically produced by <code>Sim_system</code> and includes information on the simulation
 * depending on the specified conditions and parameters. The detail level of the report file
 * (<code>sim_report</code>) can be modified with the <code>set_report_detail</code> method. The
 * default information included are the total obtained measurements obtained as well as general
 * information about the simulation run. In the case of no output analysis method having been used,
 * the steady state sample measurements are presented in place of the total measurements. If an
 * output analysis method has been used, <code>set_report_detail</code> can be used to include
 * the sample measurements for each replication (for independent replications), or the sample
 * measurements for each batch (for batch means). Finally, the original seeds used in the random
 * number generators of the simulation can also be included in the report. Since version 2.0,
 * the simulation's report is also available to animated simulations.
 * <p>
 * A <code>generate_graphs</code> method may also be used to generate <b>graphs</b> for the simulation.
 * Graphs are generated for all detailed measures and present the progress of each measure's sample
 * average over the progress of the simulation. Several additional information may also be displayed
 * for each graph such as the mean confidence interval, the transient period, the batches etc. The
 * simulation will produce a <i>".sjg"</i> file containing the simulation data. This file may then
 * be opened with the <code>SimJava Graph Viewer</code>, a graph viewing utility for <code>SimJava</code>
 * simulations.
 * <p>
 * More information on the definition of simulations and the use of <code>Sim_system</code> can be found
 * at the <a href="http://www.dcs.ed.ac.uk/home/simjava/tutorial/index.html#2">SimJava Tutorial</a>.
 * @see         Sim_entity
 * @see         eduni.simanim.Anim_applet
 * @version     2.0, 12 July 2002
 * @author      Costas Simatos
 */
public class Sim_system {

  // The types of termination and transient conditions, and output analysis methods
  /** A constant representing no condition. */
  public static final int NONE = 0;
  /** A constant representing a condition based on event completions. */
  public static final int EVENTS_COMPLETED = 1;
  /** A constant representing a condition based on a time period. */
  public static final int TIME_ELAPSED = 2;
  /** A constant representing the minimum-maximum method for transient period identification. */
  public static final int MIN_MAX = 3;
  /** A constant representing the output analysis method of independent replications. */
  public static final int IND_REPLICATIONS = 4;
  /** A constant representing the output analysis method of batch means. */
  public static final int BATCH_MEANS = 5;
  /** A constant representing a termination condition based on a confidence interval's accuray. */
  public static final int INTERVAL_ACCURACY = 6;

  // Private fields used to store the termination condition parameters
  private static int term_condition = NONE; // The termination condition to be used
  private static int term_entity_id; // The id of the entity containing the measure on which the termination condition is based
  private static int term_event_type; // The event tag of the events to be considered for the termination condition
  private static double term_time = 0.0; // The termination time
  private static double initial_term_time = 0.0; // The termination time speficied by the user
  private static long initial_term_count = 0; // The count of event completions specified by the user for the termination condition
  private static long term_count = 0; // The count of event completions for the termination condition
  private static int[] term_count_bm; // The count of event completions for a termination condition using batch means
  private static List term_times; // Stores the termination times in the case of the minimum-maximum method being used
  private static long term_event_counter = 0; // The counter for termination condtion event completions
  private static long transient_term_event_count = 0; // The count of termination events completed during the transient period
  private static String term_measure; // The measure used in the termination condition
  private static double term_accuracy; // The accuracy for the confidence interval used in the termination condition

  // Private fields used to store the termination comdition parameters
  private static int trans_condition = NONE; // The transient condition being used
  private static int trans_entity_id; // The id of the entity containing the measure on which the transient condition is based
  private static int trans_event_type; // The event tag of events to be considered for the transient condition
  private static double trans_time = -1.0; // The transient time
  private static String trans_measure; // The measure on which the transient conditnion is based
  private static double initial_trans_time = 0.0; // The transient time specified by the user
  private static long trans_count = 0; // The count of events for the transient period estimation
  private static long initial_trans_count = 0; // The count of transient events for the transient period condition specified by the user
  private static long trans_event_counter = 0; // The counter for events that count towards the transient condition

  private static boolean include_transient; // Flag for specifying whether the transient condition should be considered a part of the termination condition or not
  private static boolean in_steady_state = false; // Flag for checking whether the simulation is in steady state

  // Fields concerning output analysis methods
  private static int output_analysis_type = NONE; // The output analysis method being used
  private static boolean incomplete = true; // Flag for telling whether a simulation is complete or not
  private static int replication_count = 10; // The default number of replications to be made
  private static double confidence_level = 0.90; // The default confidence level
  private static List replications; // Stores the data from each replication
  private static double total_time_elapsed = 0.0; // The total simulated time elapsed in all replications
  private static double total_transient_time = 0.0; // The total transient time elapsed in all replications
  private static int min_batches = 10; // The default minimum number of batches
  private static int max_batches = 20; // The default maximum number of batches

  private static Object[] run_data; // Contains all the data obtained from the run

  // Fields for the management of the trace file
  private static int[] trace_tags; // The tags for which trace will be generated
  private static boolean default_trace = true, // Flag for including or not the default trace
                         entity_trace = true, // Flag for including or not the entity trace
                         event_trace = true; // Flag for including or not the event specific trace

  // Private data members
  private static List entities; // The current entity list
  private static List backup; // A backup containing the entities in their initial state
  private static Evqueue future; // The future event queue
  private static Evqueue deferred; // The deferred event queue
  private static double clock;  // Holds the current global sim time
  private static boolean running; // Flag for checking if the simulation is running
  private static Semaphore onestopped; // Semaphore for synchronising entities
  private static Semaphore onecompleted; // Semaphore for registering entities having completed
  private static Sim_output trcout; // The output object for trace messages
  private static int trace_level; // The trace level for which event trace will be generated
  private static boolean auto_trace; // Should we print trace messages?
  private static boolean animation; // Are we running as an animation applet?
  private static Thread simThread; // The thread used for animation purposes
  private static NumberFormat nf; // The animation number format used
  private static HashMap wait_predicates; // The predicates used in entity wait methods

  // Fields concerning the simulation's report
  private static long start_date; // The actual time at which the simulation started
  private static boolean detailed_report = false; // The default is to produce a report that isn't detailed
  private static boolean include_seeds = false; // The default is to produce a report that does not include seed information
  private static List initial_seeds; // The set of initial seeds

  // Private fields used for seed generation
  private static long root_seed = 4851L; // The default seed
  private static int seed_spacing = 100000; // The default seed spacing
  private static Sim_random_obj seed_source = new Sim_random_obj("Seed generator", root_seed); // The seed generator
  private static boolean not_sampled = true; // Flag that is checked to see whether the seed generator has been sampled

  private static boolean anim_stopped = false; // Flag used to check whether (in animated versions) the user has clicked on the stop button

  // Fields concerning graph generation
  private static final String suffix = ".sjg"; // The suffix for SimJava graphs
  private static boolean generate_graphs = false; // Flag used to check whether or not to generate graphs
  private static String graph_file = "sim_graphs" + suffix; // The default graph file to be used

  private static boolean efficient_measure_defined = false; // Flag used to check whether the simulation contains an efficient measure

  /**
   * Do not a constructor for <code>Sim_system</code>. Use an <code>initialise</code> method instead.
   */
  public Sim_system() {
    throw new Sim_exception("Attempt to instantiate Sim_system.");
  }

  /**
   * Initialise the simulation for standalone simulations. This function should be called
   * at the start of the simulation.
   */
  public static void initialise() {
    initialise(new Sim_outfile(), null);
  }

  /**
   * Initialise the simulation for animated simulations. This method should not be directly called
   * by the user.
   * @param out The instance of <code>Sim_anim</code> used for generating the animation
   * @param sim The simulation thread
   */
  public static void initialise(Sim_anim out, Thread sim) {
    animation = true;
    initialise(((Sim_output)out), sim);
  }

  /**
   * Initialise the system to draw <code>simdiag</code> diagrams. This method
   * should be used directly only by expert users.
   * @param out The <code>Sim_output</code> instance that will receive the simulation trace
   * @param sim The simulation thread.
   */
  public static void initialise(Sim_output out, Thread sim) {
    System.out.println("Initialising...");
    entities = new ArrayList();
    future = new Evqueue();
    deferred = new Evqueue();
    wait_predicates = new HashMap();
    clock = 0.0;
    running = false;
    onestopped = new Semaphore(0);
    onecompleted = new Semaphore(0);
    trcout = out;
    trcout.initialise();
    trace_level = 0xff;
    auto_trace = false;
    simThread = sim;
    // Set the default number format
    nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(4);
    nf.setMinimumFractionDigits(2);
  }

  /**
   * Get the number format used for generating times in trace lines.
   * @return The number format
   */
  public static NumberFormat getNumberFormat() { return nf; }

  // The two standard predicates

  /** A standard predicate that matches any event. */
  public static Sim_any_p SIM_ANY = new Sim_any_p();
  /** A standard predicate that does not match any events. */
  public static Sim_none_p SIM_NONE = new Sim_none_p();

  // Public access methods

  /**
   * Get the current simulation time. This method is identical to <code>sim_clock()</code> and
   * is present for compatibility with existing simulations.
   * @return The simulation time
   */
  public static double clock() { return clock; }

  /**
   * Get the current simulation time, as a runtime method for entities.
   * @return The simulation time
   */
  public static double sim_clock() { return clock; }

  /**
   * Get the current number of entities in the simulation.
   * @return The number of entities
   */
  public static int get_num_entities() { return entities.size(); }

  /**
   * Get the current trace level (initially <code>0xff</code>), which
   * controls trace output. This method is identical to <code>get_trace_level()</code> and
   * is present for compatibility with existing simulations.
   * @return The trace level
   */
  public static int get_trc_level() { return trace_level; } // For compatibility with previous versions

  /**
   * Get the current trace level (initially <code>0xff</code>), which
   * controls trace output.
   * @return The trace level
   */
  public static int get_trace_level() { return trace_level; }

  /**
   * Get the entity with a given id.
   * @param id The entity's unique id number
   * @return   The entity, or <code>null</code> if it could not be found
   * @throws Sim_exception If the entity was not found. This error can be left unchecked.
   */
  public static Sim_entity get_entity(int id) {
    if ((id < 0) || (id >= entities.size())) {
      throw new Sim_exception("Entity " + id + " does not exist.");
    }
    return (Sim_entity)entities.get(id);
  }

  /**
   * Get the entity with a given name.
   * @param name The entity's name
   * @return     The entity
   * @throws Sim_exception If the entity was not found. This error can be left unchecked.
   */
  public static Sim_entity get_entity(String name) {
    Sim_entity  ent;
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      ent = (Sim_entity)entities.get(i);
      if (name.compareTo(ent.get_name()) == 0) {
        return ent;
      }
    }
    throw new Sim_exception("Entity " + name + " does not exist.");
  }

  /**
   * Get the id of an entity with a given name.
   * @param name The entity's name
   * @return     The entity's unique id number
   * @throws Sim_exception If the entity was not found. This error can be left unchecked.
   */
  public static int get_entity_id(String name) {
    int id = entities.indexOf(get_entity(name));
    if (id == -1) {
      throw new Sim_exception("Entity " + name + " does not exist.");
    }
    return id;
  }

  /**
   * Get the currently running entity.
   * @return The entity, or <code>null</code> if none are running
   */
  public static Sim_entity current_ent() {
    return (Sim_entity)Sim_entity.currentThread();
  }

  // Public update methods

  /**
   * Set the trace level which controls entity trace output. This method is identical to
   * <code>set_trace_level()</code> and is present for compatibility with existing simulations.
   * @param level The new level
   */
  public static void set_trc_level(int level) { auto_trace = true; trace_level = level; }

  /**
   * Set the trace level which controls entity trace output.
   * @param level The new level
   */
  public static void set_trace_level(int level) { auto_trace = true; trace_level = level; }

  /**
   * Switch the trace messages on and off.
   * @param on If <code>true</code> then the messages are switched on, if <code>false</code>
               they are switched off.
   */
  public static void set_auto_trace(boolean on) { auto_trace = on; }

  /**
   * Add a new entity to the simulation. This is present for compatibility with existing
   * simulations since entities are automatically added to the simulation upon instantiation.
   * @param e The new entity
   */
  public static void add(Sim_entity e) {
    Sim_event evt;
    if (running) {
      // Post an event to make this entity
      evt = new Sim_event(Sim_event.CREATE,clock,current_ent().get_id(),0,0, e);
      future.add_event(evt);
    } else {
      if (e.get_id()==-1) { // Only add once!
	e.set_id(entities.size());
	entities.add(e);
      }
    }
  }

  /**
   * Internal method used to add a new entity to the simulation when the simulation is running.
   * It should <b>not</b> be called from
   * user simulations.
   * @param e The new entity
   */
  static synchronized void add_entity_dynamically(Sim_entity e) {
    e.set_id(entities.size());
    if (e == null) {
      throw new Sim_exception("Adding null entity.");
    } else {
      print_message("Adding: " + e.get_name());
    }
    entities.add(e);
    e.start();
    onestopped.p();
  }

  /**
   * Link the ports of two entities so that events can be scheduled.
   * @param ent1  The name of the first entity
   * @param port1 The name of the port on the first entity
   * @param ent2  The name of the second entity
   * @param port2 The name of the port on the second entity
   */
  public static void link_ports(String ent1, String port1, String ent2, String port2) {
    Sim_port p1,p2;
    Sim_entity e1, e2;
    e1 = get_entity(ent1);
    e2 = get_entity(ent2);
    if (e1 == null) {
      throw new Sim_exception("Sim_system: " + ent1 + " not found.");
    } else if (e2==null) {
      throw new Sim_exception("Sim_system: " + ent2 + " not found.");
    } else {
      p1 = e1.get_port(port1);
      p2 = e2.get_port(port2);
      if (p1 == null) {
        throw new Sim_exception("Sim_system: " + port1 + " not found.");
      } else if (p2==null) {
        throw new Sim_exception("Sim_system: " + port2 + " not found.");
      } else {
	p1.connect(e2);
	p2.connect(e1);
	if(animation) {
	  ((Sim_anim)trcout).link_ports(ent1, port1, ent2, port2);
	}
      }
    }
  }

  /**
   * Internal method used to run one tick of the simulation. This method should <b>not</b> be
   * called in simulations.
   * @return <code>true</code> if the event queue is empty, <code>false</code> otherwise
   */
  public static boolean run_tick() {
    Sim_entity  ent;
    int num_started;
    boolean queue_empty;
    num_started = 0;
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      ent = (Sim_entity)entities.get(i);
      if (ent.get_state() == Sim_entity.RUNNABLE) {
	ent.restart();
	num_started++;
      }
    }
    // Wait for them all to halt
    for (int i=0; i<num_started; i++) {
      onestopped.p();
    }
    // Give everything else a chance
    if (simThread != null) {
      try { simThread.sleep(5); }
      catch(InterruptedException except) {}
    }
    // If there are more future events then deal with them
    if (future.size() > 0) {
      queue_empty = false;
      Sim_event first = future.pop();
      process_event(first);
      // Check if next events are at same time...
      boolean trymore = (future.size()>0);
      while (trymore) {
	Sim_event next = future.top();
	if (next.event_time() == first.event_time()) {
	  process_event(future.pop());
	  trymore = (future.size()>0);
	} else trymore = false;
      }
    } else {
      queue_empty = true;
      running = false;
      print_message("Sim_system: No more future events");
    }
    return queue_empty;
  }


  /**
   * Internal method used to stop the simulation. This method should <b>not</b> be used directly.
   */
  public static void run_stop() {
    print_message("Simulation completed.");
    trcout.close();
  }

  //
  // Package level methods
  //

  /**
   * Get the object to which the simulation trace is sent.
   * @return The trace handler
   */
  public static Sim_output get_trcout() { return trcout; }

  // Entity service methods

  // Called by an entity just before it become non-RUNNABLE
  static void paused() { onestopped.v(); }

  // Used to hold an entity for some time
  static synchronized void hold(int src, double delay) {
    Sim_event e = new Sim_event(Sim_event.HOLD_DONE,clock+delay,src);
    future.add_event(e);
    ((Sim_entity)entities.get(src)).set_state(Sim_entity.HOLDING);
    if (auto_trace && default_trace) {
      trace(src, "start processing");
    }
  }

  // Used to pause an entity for some time
  static synchronized void pause(int src, double delay) {
    Sim_event e = new Sim_event(Sim_event.HOLD_DONE,clock+delay,src);
    future.add_event(e);
    ((Sim_entity)entities.get(src)).set_state(Sim_entity.HOLDING);
    if (auto_trace && default_trace) {
      trace(src, "start pausing");
    }
  }

  // Used to send an event from one entity to another
  static synchronized void send(int src, int dest, double delay, int tag, Object data) {
    if (delay < 0.0) {
      throw new Sim_exception("Sim_system: Send delay can't be negative.");
    }
    Sim_event e = new Sim_event(Sim_event.SEND, clock+delay, src, dest, tag, data);
    if (auto_trace && (default_trace || (event_trace && is_trace_tag(tag)))) {
      trace(src, "scheduling event type "+tag+" for "+
                 ((Sim_entity)entities.get(dest)).get_name()+
                 " with delay "+delay);
    }
    future.add_event(e);
  }

  // Sets an entity's state to be waiting. The predicate used to wait for an event is now
  // passed to Sim_system. Only events that satisfy the predicate will be passed to the
  // entity. This is done to avoid unnecessary context switches.
  static synchronized void wait(int src, Sim_predicate p) {
    ((Sim_entity)entities.get(src)).set_state(Sim_entity.WAITING);
    if (p != SIM_ANY) {
      // If a predicate has been used store it in order to check it
      wait_predicates.put(new Integer(src), p);
    }
    if (auto_trace && default_trace) {
      trace(src,"waiting for an event");
    }
  }

  // Checks if events for a specific entity are present in the deferred event queue
  static synchronized int waiting(int d, Sim_predicate p) {
    int count = 0;
    Sim_event event;
    ListIterator iterator = deferred.listIterator();
    while (iterator.hasNext()) {
      event = (Sim_event)iterator.next();
      if ((event.get_dest() == d) && (p.match(event))) {
        count++;
      }
    }
    return count;
  }

  // Selects an event matching a predicate
  static synchronized void select(int src, Sim_predicate p) {
    Sim_event ev = null;
    boolean found = false;
    ListIterator iterator = deferred.listIterator();
    while (iterator.hasNext()) {
      ev = (Sim_event)iterator.next();
      if (ev.get_dest() == src) {
        if (p.match(ev)) {
          iterator.remove();
          found = true;
          break;
        }
      }
    }
    if (found) {
      ((Sim_entity)entities.get(src)).set_evbuf((Sim_event)ev.clone());
      if (auto_trace && (default_trace || (event_trace &&is_trace_tag(ev.get_tag())))) {
        trace(src,"selected event type " + ev.get_tag() + " (event time was "+ev.event_time()+")");
      }
    } else {
      ((Sim_entity)entities.get(src)).set_evbuf(null);
      if (auto_trace && (default_trace)) {
        trace(src,"no event selected");
      }
    }
  }

  // Removes an event from the event queue
  static synchronized void cancel(int src, Sim_predicate p) {
    Sim_event ev = null;
    boolean found = false;
    int future_size = future.size();
    for (int i=0; i < future_size; i++) {
      ev = (Sim_event)future.get(i);
      if (ev.get_src() == src) {
        if (p.match(ev)) {
          future.remove(i);
          found = true;
          break;
        }
      }
    }
    if (found) {
      ((Sim_entity)entities.get(src)).set_evbuf((Sim_event)ev.clone());
      if (auto_trace && (default_trace || (event_trace && is_trace_tag(ev.get_tag())))) {
        trace(src,"cancelled event type " + ev.get_tag() + " (event time was "+ev.event_time()+")");
      }
    } else {
      ((Sim_entity)entities.get(src)).set_evbuf(null);
      if (auto_trace && default_trace) {
        trace(src,"no event cancelled");
      }
    }
  }

  // Puts an event into the deferred queue
  static synchronized void putback(Sim_event ev) { deferred.add_event(ev); }

  //
  // Private internal methods
  //

  // Processes an event
  private static void process_event(Sim_event e) {
    int dest, src;
    Sim_entity dest_ent;
    // Update the system's clock
    if (e.event_time() < clock) {
      throw new Sim_exception("Sim_system: Past event detected.");
    }
    clock = e.event_time();
    // Ok now process it
    switch(e.get_type()) {
      case(Sim_event.ENULL):
        throw new Sim_exception("Sim_system: Event has a null type.");
      case(Sim_event.CREATE):
        Sim_entity newe = (Sim_entity)e.get_data();
        add_entity_dynamically(newe);
        break;
      case(Sim_event.SEND):
        // Check for matching wait
        dest = e.get_dest();
        if (dest < 0) {
          throw new Sim_exception("Sim_system: Attempt to send to a null entity detected.");
        } else {
          int tag = e.get_tag();
          if (((dest != e.get_src()) || (tag != 9999)) && ((Sim_entity)entities.get(dest)).has_stat()) {
            ((Sim_entity)entities.get(dest)).update(Sim_stat.ARRIVAL, tag, clock);
          }
          dest_ent = (Sim_entity)entities.get(dest);
          if (dest_ent.get_state() == Sim_entity.WAITING) {
            Integer destObj = new Integer(dest);
            Sim_predicate p = (Sim_predicate)wait_predicates.get(destObj);
            if ((p == null) || (tag == 9999) || (p.match(e))) {
              dest_ent.set_evbuf((Sim_event)e.clone());
              dest_ent.set_state(Sim_entity.RUNNABLE);
              wait_predicates.remove(destObj);
            } else {
              deferred.add_event(e);
            }
          } else {
            deferred.add_event(e);
          }
        }
        break;
      case(Sim_event.HOLD_DONE):
        src = e.get_src();
        if (src<0) {
          throw new Sim_exception("Sim_system: Null entity holding.");
        } else {
          ((Sim_entity)entities.get(src)).set_state(Sim_entity.RUNNABLE);
        }
        break;
    }
  }

  /**
   * Set the sample generators' seed sequence. If this method is not called the defaults
   * are 4851 as the root seed and 100000 as the desired sample spacing.
   * @param seed_specing The seed spacing
   * @param root_seed    The seed used to generate all the simulation's seeds
   */
  public static void set_seed_sequence(int seed_spacing, long root_seed) {
    Sim_system.seed_spacing = seed_spacing;
    Sim_system.root_seed = root_seed;
    seed_source.set_seed(root_seed);
    not_sampled = true;
  }

  /**
   * Set the sample generators' seed spacing.
   * @param new_spacing The new seed spacing
   */
  public static void set_spacing(int new_spacing) {
    seed_spacing = new_spacing;
  }

  /**
   * Get the sample generators' seed spacing.
   * @return The seed spacing
   */
  public static int get_spacing() {
    return seed_spacing;
  }

  /**
   * Set the sample generators' root seed. This is the seed used to generate the initial seeds
   * for the simulation's sample generators.
   * @param new_seed The new root seed
   */
  public static void set_seed(long new_seed) {
    root_seed = new_seed;
    seed_source.set_seed(new_seed);
    not_sampled = true;
  }

  /**
   * Get the sample generator's root seed.
   * @return The root seed
   */
  public static long get_root_seed() {
    return root_seed;
  }

  /**
   * Generate the next seed based on the root seed and the seed spacing. This method
   * is automatically called and doesn't need to be included on user simulations.
   * @return The next well spaced seed
   */
  public static long next_seed() {
    long new_seed = seed_source.get_seed();
    if ((new_seed == root_seed) && not_sampled) {
      not_sampled = false;
    } else {
      for (int i=0; i<seed_spacing; i++) {
        seed_source.sample();
      }
      new_seed = seed_source.get_seed();
    }
    return new_seed;
  }

  // Called when an event is considered to have completed service. If the event that completed is the one
  // that determines the run length condition.
  static synchronized void job_completed(int entity, int event_tag) {
    if (term_condition == EVENTS_COMPLETED) {
      if ((entity == term_entity_id) && (event_tag == term_event_type)) {
        term_event_counter++;
        if (trans_condition == MIN_MAX) {
          term_times.add(new Double(clock));
        }
      }
    }
    if (trans_condition == EVENTS_COMPLETED) {
      if ((entity == trans_entity_id) && (event_tag == trans_event_type)) {
        trans_event_counter++;
      }
    }
    if (auto_trace && (default_trace || (event_trace && is_trace_tag(event_tag)))) {
      trace(entity, "event type " + event_tag + " completed service");
    }
  }

  // Called to update the count of collected observations of interest. This is done in the case of
  // a termination condition based on collected observations and interval accuracy.
  static synchronized void observation_collected(int entity, String measure) {
    if ((term_condition == INTERVAL_ACCURACY) &&
        (entity == term_entity_id) &&
        (measure.equals(term_measure)) &&
        in_steady_state) {
      term_event_counter++;
    }
  }

  /**
   * Internal method used to initialise the simulation. This should <b>not</b> be called from user simulations.
   */
  public static void run_initialise() {
    start_date = System.currentTimeMillis();
    if (term_condition == INTERVAL_ACCURACY) {
      switch (output_analysis_type) {
        case IND_REPLICATIONS:
          initial_term_count = 2500;
          break;
        case BATCH_MEANS:
          term_count_bm = new int[2];
          term_count_bm[0] = 600;
          term_count_bm[1] = 800;
          term_count = 800;
          break;
      }
    } else if (term_condition == EVENTS_COMPLETED) {
      term_count = initial_term_count;
    } else {
      term_time = initial_term_time;
    }
    trans_count = initial_trans_count;
    if (output_analysis_type == IND_REPLICATIONS) {
      // Make a backup of the entities. This is done in order to perform independent replications
      backup = new ArrayList();
      Sim_entity ent;
      int entities_size = entities.size();
      for (int i=0; i < entities_size; i++) {
        ent = (Sim_entity)entities.get(i);
        try {
          backup.add((Sim_entity)ent.clone());
        } catch (CloneNotSupportedException ex) {
          throw new Sim_exception("Could not make backup of entity " + ent.get_name() + ".");
        }
      }
    }
    // Get the initial seeds used in the entities.
    initial_seeds = new ArrayList();
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      Sim_entity ent = (Sim_entity)entities.get(i);
      String ent_name = ent.get_name();
      List generators = ent.get_generators();
      if (generators.size() == 0) {
        continue;
      }
      List gens = new ArrayList();
      int generators_size = generators.size();
      for (int j=0; j < generators_size; j++) {
        Generator gen = (Generator)generators.get(j);
        gens.add(new Object[] {gen.get_name(), new Long(gen.get_seed())});
      }
      initial_seeds.add(new Object[] {ent_name, gens});
    }
  }

  /**
   * Internal method used to start the simulation. This method should <b>not</b> be used by user simulations.
   */
  public static void run_start() {
    if (output_analysis_type == IND_REPLICATIONS) {
      int count = 1;
      if (replications != null) {
        count = replications.size() + 1;
      }
      print_message("Performing replication #" + count + ".");
    }
    running = true;
    // Start all the entities' threads
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      ((Sim_entity)entities.get(i)).start();
    }
    // Wait until they're all up and ready
    for (int i=0; i < entities_size; i++) {
      onestopped.p();
    }
    print_message("Entities started.");
  }

  /**
   * Specify a termination condition based on event completions at a specific entity.
   * @param type              The termination condition type. This must be set to <code>EVENTS_COMPLETED</code>.
   * @param entity            The name of the entity whose events will be used for the termination condition
   * @param event_type        The event tag of the events to be counted towards the termination condition
   * @param count             The event completion count that when reached, will satisfy the termination condition
   * @param include_transient <code>true</code> if the number of completions provided should only apply to
   *                          completions in steady state. <code>false</code> if they should apply to the entire
   *                          run length.
   */
  public static void set_termination_condition(int type, String entity, int event_type, long count, boolean include_transient) {
    if (type != EVENTS_COMPLETED) {
      throw new Sim_exception("The method used must be passed a condition based on completed events.");
    } else if (count <= 0) {
      throw new Sim_exception("The count for events completed must be positive.");
    } else if (term_condition != NONE) {
      throw new Sim_exception("You can't specify two termination conditions.");
    }
    term_condition = type;
    term_entity_id = get_entity_id(entity);
    term_event_type = event_type;
    initial_term_count = count;
    Sim_system.include_transient = include_transient;
  }

  /**
   * Specify a termination condition based on the simulation time elapsed.
   * @param type        The termination condition type. This must be set to <code>TIME_ELAPSED</code>.
   * @param time        The simulated time after which the termination condition will be satisfied
   * @param include_transient <code>true</code> if the condition's time period should be considered
   *                          only after steady state has been reached, <code>false</code> if it should
   *                          be considered from the beginning of the simulation.
   */
  public static void set_termination_condition(int type, double time, boolean include_transient) {
    if (type != TIME_ELAPSED) {
      throw new Sim_exception("The method used must be passed a condition based on the elapsed simulation time.");
    } else if (time <= 0.0) {
      throw new Sim_exception("The simulation termination time completed must be positive.");
    } else if (term_condition != NONE) {
      throw new Sim_exception("You can't specify two termination conditions.");
    }
    term_condition = type;
    initial_term_time = time;
    Sim_system.include_transient = include_transient;
  }

  /**
   * Specify a termination condition based on the confidence interval accuracy obtained for a custom measure. This
   * condition is considered only once the transient period has elapsed. The accuracy is the ratio of the confidence
   * interval's halfwidth over the measure's total mean.
   * @param type                 The termination condition type. This must be set to <code>INTERVAL_ACCURACY</code>.
   * @param output_analysis_type The output analysis method to be used as a variance reduction technique
   * @param level                The confidence level for which the confidence interval will be calculated
   * @param accuracy             The accuracy that is required to satisfy the termination condition
   * @param entity               The name of the entity that contains the measure upon which the termination condition is based
   * @param measure              The name of the custom measure upon which the termination condition is based
   */
  public static void set_termination_condition(int type, int output_analysis_type, double level, double accuracy, String entity, String measure) {
    if (type != INTERVAL_ACCURACY) {
      throw new Sim_exception("The method used must be passed a condition based on the accuracy of a confidence interval.");
    } else if ((output_analysis_type != IND_REPLICATIONS) && (output_analysis_type != BATCH_MEANS)) {
      throw new Sim_exception("The output analysis method to be used must be either IND_REPLICATIONS or BATCH_MEANS.");
    } else if ((level <= 0.0) || (level >= 1.0)) {
      throw new Sim_exception("The confidence level must be between 0.0 and 1.0.");
    } else if (accuracy <= 0.0) {
      throw new Sim_exception("The interval accuracy tolerance must be greater than 0.0.");
    } else if (Sim_system.output_analysis_type != NONE) {
      throw new Sim_exception("When using this termination condition no other output analysis method can be specified.");
    } else if (trans_condition == MIN_MAX) {
      throw new Sim_exception("This termination condition can't be used when the minimum-maximum method is used.");
    } else if (term_condition != NONE) {
      throw new Sim_exception("You can't specify two termination conditions.");
    } else if ((output_analysis_type == BATCH_MEANS) && (efficient_measure_defined)) {
      throw new Sim_exception("Batch means may not be used when efficient measures are defined.");
    }
    term_entity_id = get_entity_id(entity);
    Sim_stat stat = ((Sim_entity)entities.get(term_entity_id)).get_stat();
    if (stat == null) {
      throw new Sim_exception("No measures have been defined for " + entity + ".");
    }
    int measure_type = stat.get_type(measure);
    if (measure_type == -1) {
      throw new Sim_exception(measure + " has not been defined for " + entity + ".");
    }
    term_condition = type;
    term_measure = measure;
    Sim_system.output_analysis_type = output_analysis_type;
    confidence_level = level;
    term_accuracy = accuracy;
    replication_count = 5;
  }

  /**
   * Specify a termination condition based on the confidence interval accuracy obtained for a default measure. This
   * condition is considered only once the transient period has elapsed. The accuracy is the ratio of the confidence
   * interval's halfwidth over the measure's total mean.
   * @param type                 The termination condition type. This must be set to <code>INTERVAL_ACCURACY</code>.
   * @param output_analysis_type The output analysis method to be used as a variance reduction technique
   * @param level                The confidence level for which the confidence interval will be calculated
   * @param accuracy             The accuracy that is required to satisfy the termination condition
   * @param entity               The name of the entity that contains the measure upon which the termination condition is based
   * @param measure              The <code>int</code> constant that represents the default measure upon which the termination
   *                             condition is based
   */
  public static void set_termination_condition(int type, int output_analysis_type, double level, double accuracy, String entity, int measure) {
    if (type != INTERVAL_ACCURACY) {
      throw new Sim_exception("The method used must be passed a condition based on the accuracy of a confidence interval.");
    } else if ((output_analysis_type != IND_REPLICATIONS) && (output_analysis_type != BATCH_MEANS)) {
      throw new Sim_exception("The output analysis method to be used must be either IND_REPLICATIONS or BATCH_MEANS.");
    } else if ((level <= 0.0) || (level >= 1.0)) {
      throw new Sim_exception("The confidence level must be between 0.0 and 1.0.");
    } else if (accuracy <= 0.0) {
      throw new Sim_exception("The interval accuracy tolerance must be greater than 0.0.");
    } else if (Sim_system.output_analysis_type != NONE) {
      throw new Sim_exception("When using this termination condition no other output analysis method can be specified.");
    } else if (trans_condition == MIN_MAX) {
      throw new Sim_exception("This termination condition can't be used when the minimum-maximum method is used.");
    } else if (term_condition != NONE) {
      throw new Sim_exception("You can't specify two termination conditions.");
    } else if ((output_analysis_type == BATCH_MEANS) && (efficient_measure_defined)) {
      throw new Sim_exception("Batch means may not be used when efficient measures are defined.");
    }
    term_entity_id = get_entity_id(entity);
    Sim_stat stat = ((Sim_entity)entities.get(term_entity_id)).get_stat();
    if (stat == null) {
      throw new Sim_exception("No measures have been defined for " + entity + ".");
    }
    String measure_name = stat.get_name_default(measure);
    int measure_type = stat.get_type(measure_name);
    if (measure_type == -1) {
      throw new Sim_exception(measure_name + " has not been defined for " + entity + ".");
    }
    term_condition = type;
    term_measure = measure_name;
    Sim_system.output_analysis_type = output_analysis_type;
    confidence_level = level;
    term_accuracy = accuracy;
    replication_count = 5;
  }

  /**
   * Specify a transient condition using the minimum-maximum method for a custom measure.
   * The minimum-maximum method considers the simulation to have entered steady state after the first
   * observation of the given measure is found that is neither the minimum nor the maximum of the remaining
   * observations.
   * @param type    The transient condition type. This must be set to <code>MIN_MAX</code>.
   * @param entity  The name of the entity that contains the measure upon which the minimum-maximum method will be
   *                applied
   * @param measure The name of the custom measure upon which the minimum-maximum method will be applied
   */
  public static void set_transient_condition(int type, String entity, String measure) {
    if (type != MIN_MAX) {
      throw new Sim_exception("The method used must be passed a condition based on minimum-maximum values of observations of a specific entity's measure.");
    } else if (trans_condition != NONE) {
      throw new Sim_exception("You can't specify two transient conditions.");
    } else if ((term_condition == INTERVAL_ACCURACY) || (term_condition == NONE)) {
      throw new Sim_exception("The minimum-maximum method can only be used with a termination of event completions or elapsed time.");
    } else if (efficient_measure_defined) {
      throw new Sim_exception("The minimum-maximum method can't be used with efficient measures.");
    }
    trans_condition = type;
    trans_entity_id = get_entity_id(entity);
    Sim_stat stat = ((Sim_entity)entities.get(trans_entity_id)).get_stat();
    if (stat == null) {
      throw new Sim_exception("No measures have been defined for " + entity + ".");
    }
    int measure_type = stat.get_type(measure);
    if (measure_type == -1) {
      throw new Sim_exception(measure + " has not been defined for " + entity + ".");
    } else if (((measure_type != Sim_stat.STATE_BASED) && (measure_type != Sim_stat.INTERVAL_BASED)) || (measure.equals("Utilisation"))) {
      throw new Sim_exception(measure + " of " + entity + " has invalid type for a transient period estimator.");
    }
    trans_measure = measure;
    term_times = new ArrayList();
  }

  /**
   * Specify a transient condition using the minimum-maximum method for a default measure.
   * The minimum-maximum method considers the simulation to have entered steady state after the first
   * observation of the given measure is found that is neither the minimum nor the maximum of the remaining
   * observations.
   * @param type    The transient condition type. This must be set to <code>MIN_MAX</code>.
   * @param entity  The name of the entity that contains the measure upon which the minimum-maximum method will be
   *                applied
   * @param measure The <code>int</code> constant that represents the default measure upon which the minimum-maximum
   *                method will be applied
   */
  public static void set_transient_condition(int type, String entity, int measure) {
    if (type != MIN_MAX) {
      throw new Sim_exception("The method used must be passed a condition based on minimum-maximum values of observations of a specific entity's measure.");
    } else if (trans_condition != NONE) {
      throw new Sim_exception("You can't specify two transient conditions.");
    } else if ((term_condition == INTERVAL_ACCURACY) || (term_condition == NONE)) {
      throw new Sim_exception("The minimum-maximum method can only be used with a termination of event completions or elapsed time.");
    } else if (efficient_measure_defined) {
      throw new Sim_exception("The minimum-maximum method can't be used with efficient measures.");
    }
    trans_condition = type;
    trans_entity_id = get_entity_id(entity);
    Sim_stat stat = ((Sim_entity)entities.get(trans_entity_id)).get_stat();
    if (stat == null) {
      throw new Sim_exception("No measures have been defined for " + entity + ".");
    }
    String measure_name = stat.get_name_default(measure);
    int measure_type = stat.get_type(measure_name);
    if (measure_type == -1) {
      throw new Sim_exception(measure_name + " has not been defined for " + entity + ".");
    } else if (((measure_type != Sim_stat.STATE_BASED) && (measure_type != Sim_stat.INTERVAL_BASED)) || (measure_name.equals("Utilisation"))) {
      throw new Sim_exception(measure_name + " of " + entity + " has invalid type for a transient period estimator.");
    }
    trans_measure = measure_name;
    term_times = new ArrayList();
  }

  /**
   * Specify a transient condition based on event completions at a specific entity.
   * @param type       The transient condition type. This must be set to <code>EVENTS_COMPLETED</code>.
   * @param entity     The name of the entity whise event completions will be counted towards this condition
   * @param event_type The event tag of the events whose completions will be counted towards this condition
   * @param count      The number of event completions after which the condition will be satisfied
   */
  public static void set_transient_condition(int type, String entity, int event_type, long count) {
    if (type != EVENTS_COMPLETED) {
      throw new Sim_exception("The method used must be passed a condition based on completed events.");
    } else if (count <= 0) {
      throw new Sim_exception("The count for events completed must be positive.");
    } else if (trans_condition != NONE) {
      throw new Sim_exception("You can't specify two transient conditions.");
    }
    trans_condition = type;
    trans_entity_id = get_entity_id(entity);
    trans_event_type = event_type;
    initial_trans_count = count;
  }

  /**
   * Specify a transient condition based on the elapsed simulation time.
   * @param type The transient condition type. This must be set to <code>TIME_ELAPSED</code>.
   * @param time The time period after which the condition will be satisfied
   */
  public static void set_transient_condition(int type, double time) {
    if (type != TIME_ELAPSED) {
      throw new Sim_exception("The method used must be passed a condition based on the elapsed simulation time.");
    } else if (time <= 0.0) {
      throw new Sim_exception("The transient time must be positive.");
    } else if (trans_condition != NONE) {
      throw new Sim_exception("You can't specify two transient conditions.");
    }
    trans_condition = type;
    initial_trans_time = time;
  }

  /**
   * Specify an output analysis method for the simulation. The default, if this method is not used is <code>NONE</code>.
   * If independent replications is selected, 10 replications will be performed. If batch means is selected, the
   * observations will be batched into 10 to 20 batches. The actual number of batches used will be the one that
   * gives the least serial correlation among the batch means.
   * <p>
   * The confidence level that will be used for calculating the simulation's measures' confidence intervals is
   * <code>0.90</code> (<code>90%</code>).
   * @param type The output analysis method
   */
  public static void set_output_analysis(int type) {
    if ((type != NONE) && (type != IND_REPLICATIONS) && (type != BATCH_MEANS)) {
      throw new Sim_exception("The output analysis method must be either NONE, IND_REPLICATIONS or BATCH_MEANS.");
    } else if (term_condition == INTERVAL_ACCURACY) {
      throw new Sim_exception("No additional output analysis method can be specified when using a termination condition based on an interval accuracy.");
    } else if (output_analysis_type != NONE) {
      throw new Sim_exception("You can't specify two output analysis methods.");
    } else if ((output_analysis_type == BATCH_MEANS) && efficient_measure_defined) {
      throw new Sim_exception("Batch means may not be used when efficient measures are defined.");
    }
    output_analysis_type = type;
  }

  /**
   * Specify an output analysis method to produce confidence intervals of a given confidence level.
   * @param type  The output analysis method.
   * @param level The confidence level to be used to produce the confidence intervals for the simulations' measures
   */
  public static void set_output_analysis(int type, double level) {
    if ((type != IND_REPLICATIONS) && (type != BATCH_MEANS)) {
      throw new Sim_exception("The output analysis method must be either IND_REPLICATIONS or BATCH_MEANS.");
    } else if ((level <= 0.0) || (level >= 1.0)) {
      throw new Sim_exception("The confidence level must be between 0.0 and 1.0.");
    } else if (term_condition == INTERVAL_ACCURACY) {
      throw new Sim_exception("No additional output analysis method can be specified when using a termination condition based on an interval accuracy.");
    } else if (output_analysis_type != NONE) {
      throw new Sim_exception("You can't specify two output analysis methods.");
    } else if ((output_analysis_type == BATCH_MEANS) && efficient_measure_defined) {
      throw new Sim_exception("Batch means may not be used when efficient measures are defined.");
    }
    output_analysis_type = type;
    confidence_level = level;
  }

  /**
   * Specify batch means as the simulation's output analysis method. The observation will be batched into
   * <code>min_batches</code> batches up to <code>max_batches</code> batches. The number of batches that
   * will actually be used to produce the confidence intervals will be the one that gives the least serial
   * correlation among the batch means.
   * @param type        The output analysis method. This must be set to <code>BATCH_MEANS</code>.
   * @param min_batches The minimum number of batches that the observations will be batched into
   * @param max_batches The maximum number of batches that the observations will be batched into
   * @param level       The confidence level to be used to produce the confidence intervals for the simulations' measures
   */
  public static void set_output_analysis(int type, int min_batches, int max_batches, double level) {
    if (type != BATCH_MEANS) {
      throw new Sim_exception("The output analysis method must be either IND_REPLICATIONS or BATCH_MEANS.");
    } else if ((min_batches <= 1) || (min_batches > max_batches)) {
      throw new Sim_exception("Invalid minimum and maximum number of batches.");
    } else if ((level <= 0.0) || (level >= 1.0)) {
      throw new Sim_exception("The confidence level must be between 0.0 and 1.0.");
    } else if (term_condition == INTERVAL_ACCURACY) {
      throw new Sim_exception("No additional output analysis method can be specified when using a termination condition based on an interval accuracy.");
    } else if (output_analysis_type != NONE) {
      throw new Sim_exception("You can't specify two output analysis methods.");
    } else if (efficient_measure_defined) {
      throw new Sim_exception("Batch means may not be used when efficient measures are defined.");
    }
    output_analysis_type = type;
    Sim_system.min_batches = min_batches;
    Sim_system.max_batches = max_batches;
    confidence_level = level;
  }

  /**
   * Specify independent replications as an output analysis method.
   * @param type         The output analysis method. This must be set to <code>IND_REPLICATIONS</code>.
   * @param replications The number of replications to perform
   * @param level        The confidence level that will be used to produce the simulation's measures'
   *                     confidence intervals
   */
  public static void set_output_analysis(int type, int replications, double level) {
    if (type != IND_REPLICATIONS) {
      throw new Sim_exception("This method may only be used to specify IND_REPLICATIONS as the output analysis method.");
    } else if (replications < 2) {
      throw new Sim_exception("The number of replications must be greater than 2.");
    } else if ((level <= 0.0) || (level >= 1.0)) {
      throw new Sim_exception("The confidence level must be between 0.0 and 1.0.");
    } else if (term_condition == INTERVAL_ACCURACY) {
      throw new Sim_exception("No additional output analysis method can be specified when using a termination condition based on an interval accuracy.");
    } else if (output_analysis_type != NONE) {
      throw new Sim_exception("You can't specify two output analysis methods.");
    }
    output_analysis_type = type;
    replication_count = replications;
    confidence_level = level;
  }

  /**
   * Set the detail required in the simulation's report. The default is to include only the total measurements
   * and the general simulation information.
   * @param detailed If set to <code>true</code> the report will include the sample measurements from each
   *                 replication (if independent replications has been used as an output analysis method),
   *                 or the sample measurements from each batch (if batch means has been used). Setting this
   *                 to <code>false</code> will only include the total measurements and general simulation
   *                 information.
   * @param seeds    If set to <code>true</code> the report will include the original seeds used in the
   *                 entities' random number generators
   */
  public static void set_report_detail(boolean detailed, boolean seeds) {
    detailed_report = detailed;
    include_seeds = seeds;
  }

  /**
   * Specify that graphs are to be generated in the default graph file. The name of the graph file
   * will be <code>sim_graphs.sjg</code>.
   * @param generate_graphs Set to <code>true</code> if graphs are to be generated, <code>false</code>
   *                        otherwise
   */
  public static void generate_graphs(boolean generate_graphs) {
    Sim_system.generate_graphs = generate_graphs;
  }

  /**
   * Specify that graphs are to be generated in a user specified graph file.
   * @param filename The name of the graph file
   */
  public static void generate_graphs(String filename) {
    generate_graphs = true;
    if (!filename.endsWith(suffix)) {
      filename += suffix;
    }
    graph_file = filename;
  }

  /**
   * Check if the simulation is still running. This method should be used by entities to check if they
   * should continue executing.
   * @return <code>true</code> if the simulation is still running, <code>false</code> otherwise
   */
  public static synchronized boolean running() {
    return running;
  }

  /**
   * Internal method used to tidy up the entities' <code>Sim_stat</code> objects. This method should
   * <b>not</b> be used in user simulations.
   */
  public static void tidy_up_stats() {
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      ((Sim_entity)entities.get(i)).tidy_up_stat();
    }
  }

  /**
   * Internal method used to check if the transient or termination conditions have been satisfied. This
   * method should <b>not</b> be used in user simulations.
   * @return <code>true</code> if the simulation should keep on running, <code>false</code> otherwise
   */
  public static boolean check_conditions() {
    boolean result = true;
    switch (term_condition) {
      case EVENTS_COMPLETED:
        switch (trans_condition) {
          case EVENTS_COMPLETED:
            if (include_transient) {
              // Termination event count considered over the whole simulation run
              if ((trans_event_counter >= trans_count) && (!in_steady_state)) {
                trans_time = clock;
                in_steady_state = true;
                if (efficient_measure_defined) {
                  notify_stats_trans(trans_time);
                }
              }
              if (term_event_counter >= term_count) {
                result = false;
                if (trans_time < 0.0) {
                  print_message("The termination condition has been satisfied but no transient period has been identified.");
                  print_message("The run will complete with no transient period.");
                }
              }
            } else {
              // Termination event count considered over steady state only
              if (in_steady_state) {
                if (term_event_counter >= term_count) {
                  result = false;
                }
              } else {
                if (trans_event_counter >= trans_count) {
                  in_steady_state = true;
                  // The transient phase termination event count
                  transient_term_event_count = term_event_counter;
                  // Start counting termination events from the beginning
                  term_event_counter = 0;
                  trans_time = clock;
                  if (efficient_measure_defined) {
                    notify_stats_trans(trans_time);
                  }
                }
              }
            }
            break;
          case TIME_ELAPSED:
            if (include_transient) {
              // Termination time considered over the whole simulation run
              if ((clock >= initial_trans_time) && (!in_steady_state)) {
                in_steady_state = true;
                trans_time = clock;
                if (efficient_measure_defined) {
                  notify_stats_trans(trans_time);
                }
              }
              if (term_event_counter >= term_count) {
                result = false;
                if (trans_time < 0.0) {
                  print_message("The termination condition has been satisfied but no transient period has been identified.");
                  print_message("The run will complete with no transient period.");
                }
              }
            } else {
              // Termination event count considered over steady state only
              if (in_steady_state) {
                if (term_event_counter >= term_count) {
                  result = false;
                }
              } else {
                if (clock >= initial_trans_time) {
                  in_steady_state = true;
                  trans_time = clock;
                  // The transient phase termination event count
                  transient_term_event_count = term_event_counter;
                  // Start counting termination events from the beginning
                  term_event_counter = 0;
                  if (efficient_measure_defined) {
                    notify_stats_trans(trans_time);
                  }
                }
              }
            }
            break;
          case MIN_MAX:
            if (term_event_counter >= term_count) {
              // The term_count event completions have occurred
              if (include_transient) {
                // The simulation simply completes
                result = false;
                trans_time = (((Sim_entity)entities.get(trans_entity_id)).get_stat()).min_max_time(trans_measure);
                if (trans_time != -1.0) {
                  in_steady_state = true;
                } else {
                  print_message("The termination condition has been satisfied but no transient period has been identified.");
                  print_message("The run will complete with no transient period.");
                }
              } else {
                if (in_steady_state) {
                  result = false;
                } else {
                  // The transient period is computed. The run must be extended by the transient time.
                  trans_time = (((Sim_entity)entities.get(trans_entity_id)).get_stat()).min_max_time(trans_measure);
                  if (trans_time == -1.0) {
                    // The transient period has not yet been overcome.
                    double choice = 0.0;
                    if (!animation) {
                      choice = prompt_modeller(0);
                    }
                    if (choice < 0.0) {
                      result = false;
                    } else {
                      term_count += (long)choice;
                    }
                  } else {
                    // Transient period was estimated and the run was extended
                    in_steady_state = true;
                    transient_term_event_count = 0;
                    int term_times_size = term_times.size();
                    for (int i=0; i < term_times_size; i++) {
                      double next_term_time = ((Double)term_times.get(i)).doubleValue();
                      if (next_term_time < trans_time) {
                        transient_term_event_count++;
                      }
                    }
                    term_event_counter -= transient_term_event_count;
                  }
                }
              }
            }
            break;
          case NONE:
            if (term_event_counter >= term_count) {
              result = false;
            }
            break;
        }
        break;
      case TIME_ELAPSED:
        switch (trans_condition) {
          case EVENTS_COMPLETED:
            if (include_transient) {
              // Termination time considered over the whole simulation run
              if ((trans_event_counter >= trans_count+1) && (!in_steady_state)) {
                trans_time = clock;
                in_steady_state = true;
                if (efficient_measure_defined) {
                  notify_stats_trans(trans_time);
                }
              }
              if (clock >= term_time) {
                result = false;
                if (trans_time < 0.0) {
                  print_message("The termination condition has been satisfied but no transient period has been identified.");
                  print_message("The run will complete with no transient period.");
                }
              }
            } else {
              // Termination time considered over steady state only
              if (in_steady_state) {
                if (clock >= term_time) {
                  result = false;
                }
              } else {
                if (trans_event_counter >= trans_count) {
                  in_steady_state = true;
                  // The transient phase termination event count
                  transient_term_event_count = term_event_counter;
                  // Start counting termination events from the beginning
                  term_event_counter = 0;
                  trans_time = clock;
                  term_time += trans_time;
                  if (efficient_measure_defined) {
                    notify_stats_trans(trans_time);
                  }
                }
              }
            }
            break;
          case TIME_ELAPSED:
            if (include_transient) {
              // Termination time considered over the whole simulation run
              if ((clock >= initial_trans_time) && (!in_steady_state)) {
                in_steady_state = true;
                trans_time = clock;
                if (efficient_measure_defined) {
                  notify_stats_trans(trans_time);
                }
              }
              if (clock >= term_time) {
                result = false;
                if (trans_time < 0.0) {
                  print_message("The termination condition has been satisfied but no transient period has been identified.");
                  print_message("The run will complete with no transient period.");
                }
              }
            } else {
              // Termination time considered over steady state only
              if (in_steady_state) {
                if (clock >= term_time) {
                  result = false;
                }
              } else {
                if (clock >= initial_trans_time) {
                  in_steady_state = true;
                  trans_time = clock;
                  term_time += trans_time;
                  if (efficient_measure_defined) {
                    notify_stats_trans(trans_time);
                  }
                }
              }
            }
            break;
          case MIN_MAX:
            // The run length should complete. Following this the transient period is estimated. Depending on
            // whether or not the simulation termination is based on steady state or the whole run, the
            // run is suitably extended (or not).
            if (clock >= term_time) {
              // The term_time has elapsed
              if (include_transient) {
                // The simulation simply completes
                result = false;
                trans_time = (((Sim_entity)entities.get(trans_entity_id)).get_stat()).min_max_time(trans_measure);
                if (trans_time != -1.0) {
                  in_steady_state = true;
                } else {
                  print_message("The termination condition has been satisfied but no transient period has been identified.");
                  print_message("The run will complete with no transient period.");
                }
              } else {
                if (in_steady_state) {
                  result = false;
                } else {
                  // The transient period is computed. The run must be extended by the transient time.
                  trans_time = (((Sim_entity)entities.get(trans_entity_id)).get_stat()).min_max_time(trans_measure);
                  if (trans_time == -1.0) {
                    // The transient period has not yet been overcome.
                    double choice = prompt_modeller(1);
                    if (choice < 0.0) {
                      result = false;
                    } else {
                      term_time += choice;
                    }
                  } else {
                    // Transient period was estimated and the run was extended
                    term_time += trans_time;
                    in_steady_state = true;
                  }
                }
              }
            }
            break;
          case NONE:
            if (clock >= term_time) {
              result = false;
            }
            break;
        }
        break;
      case INTERVAL_ACCURACY:
        // The transient period must elapse before this termination condition can be applied.
        if (in_steady_state) {
          switch (output_analysis_type) {
            case IND_REPLICATIONS:
              if (term_event_counter >= initial_term_count) {
                result = false;
              }
              break;
            case BATCH_MEANS:
              if (term_event_counter >= term_count) {
                result = false;
              }
              break;
          }
        } else {
          switch (trans_condition) {
            case EVENTS_COMPLETED:
              if (trans_event_counter >= trans_count+1) {
                trans_time = clock;
                in_steady_state = true;
                if (efficient_measure_defined) {
                  notify_stats_trans(trans_time);
                }
              }
              break;
            case TIME_ELAPSED:
              if (clock >= initial_trans_time) {
                in_steady_state = true;
                trans_time = clock;
                if (efficient_measure_defined) {
                  notify_stats_trans(trans_time);
                }
              }
              break;
            case NONE:
              in_steady_state = true;
              break;
          }
        }
        break;
      case NONE:
        // No explicit termination condition has been specified
        switch (trans_condition) {
          case EVENTS_COMPLETED:
            if (trans_event_counter >= trans_count+1) {
              trans_time = clock;
              in_steady_state = true;
              if (efficient_measure_defined) {
                notify_stats_trans(trans_time);
              }
            }
            break;
          case TIME_ELAPSED:
            if (clock >= initial_trans_time) {
              in_steady_state = true;
              trans_time = clock;
              if (efficient_measure_defined) {
                notify_stats_trans(trans_time);
              }
            }
            break;
          case NONE:
            in_steady_state = true;
            break;
        }
        break;
    }
    running = result;
    return result;
  }

  // Prompts the modeller for his decision concerning "problem-cases"
  private static double prompt_modeller(int type) {
    BufferedReader input = null;
    double result = -1.0;
    try {
      // For non animated version
      input = new BufferedReader(new InputStreamReader(System.in));
      String choice;
      switch (type) {
        case 0:
          // Completions for termination and Min Max for warmup.
          // Transient period not included.
          // No warmup has been estimated.
          System.out.println("The transient period could not be estimated. How should the run proceed?");
          System.out.println("1. Complete run immediately with no transient period.");
          System.out.println("2. Extend the run for a number of event completions and try again.");
          System.out.print("Choice: ");
          do {
            choice = input.readLine();
          } while (!choice.equals("1") && !choice.equals("2"));
          if (!choice.equals("1")) {
            long more_events = 0L;
            System.out.println("Event completions for which the run will be extended: ");
            do {
              try {
                more_events = (Long.valueOf(input.readLine())).longValue();
              } catch (NumberFormatException e) {}
            } while (more_events > 0);
            result = more_events;
          }
          break;
        case 1:
          // Time for termination and Min Max for warmup.
          // Transient period not included.
          // No warmup has been estimated.
          System.out.println("The transient period could not be estimated. How should the run proceed?");
          System.out.println("1. Complete run immediately with no transient period.");
          System.out.println("2. Extend the run for a time period and try again.");
          System.out.print("Choice: ");
          do {
            choice = input.readLine();
          } while (!choice.equals("1") && !choice.equals("2"));
          if (!choice.equals("1")) {
            double more_time = 0.0;
            System.out.println("Time for which the run will be extended: ");
            do {
              try {
                more_time = (Double.valueOf(input.readLine())).doubleValue();
              } catch (NumberFormatException e) {}
              break;
            } while (more_time > 0.0);
            result = more_time;
          }
          break;
      }
    } catch (IOException e) {
    } finally {
      try {
        if (input != null) input.close();
      } catch (IOException e) {}
    }
    return result;
  }

  /**
   * Start the simulation running. This should be called after all the entities have been setup
   * and added, and their ports linked.
   */
  public static void run() {
    run_initialise();
    while (incomplete) {
      if (!running) run_start();
      while (check_conditions()) {
        if (run_tick()) {
          break;
        }
      }
      tidy_up_stats();
      apply_variance_reduction();
      end_current_run();
    }
    run_stop();
    generate_report();
    generate_graphs();
  }

  /**
   * Internal method that allows the entities to terminate. This method should <b>not</b> be used
   * in user simulations.
   */
  public static void end_current_run() {
    if (anim_stopped) {
      running = false;
    }
    if (incomplete && (output_analysis_type == BATCH_MEANS) && (!anim_stopped)) {
      // A termination condition of interval accuracy is being used
      running = true;
    } else {
      // Allow all entities to exit their body method
      Sim_entity ent;
      int entities_size = entities.size();
      for (int i=0; i < entities_size; i++) {
        ent = (Sim_entity)entities.get(i);
        if (ent.get_state() != Sim_entity.FINISHED) {
          ent.restart();
        }
      }
      // Wait for all entities to complete
      for (int i=0; i < entities_size; i++) {
        onecompleted.p();
      }
      if (incomplete && (!anim_stopped)) {
        // In the case of independent replications and an incomplete run we reset the simulation
        reset();
      }
    }
  }

  // Used by entities to signal to Sim_system that they have completed
  static void completed() {
    paused();
    onecompleted.v();
  }

  /**
   * Internal method used to apply the selected output analysis or variance reduction technique. This
   * method should <b>not</b> be used in user simulations.
   */
  public static void apply_variance_reduction() {
    switch (output_analysis_type) {
      case IND_REPLICATIONS:
        print_message("Applying output analysis.");
        incomplete = independent_replications();
        break;
      case BATCH_MEANS:
        print_message("Applying output analysis.");
        incomplete = batch_means();
        break;
      case NONE:
        print_message("Gathering simulation data.");
        gather_data();
        incomplete = false;
        break;
    }
  }

  // Gathers the data collected by the single run.
  private static void gather_data() {
    run_data = new Object[5];
    if (trans_time < 0.0) {
      trans_time = 0.0;
    }
    List stats = new ArrayList();
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      stats.add(((Sim_entity)entities.get(i)).get_stat());
    }
    long end_date = System.currentTimeMillis();
    run_data[0] = new long[] {start_date, end_date};
    run_data[1] = new int[] {trans_condition, term_condition, output_analysis_type};
    run_data[2] = new double[] {clock, trans_time};
    run_data[3] = initial_seeds;
    run_data[4] = stats;
  }

  // Apply the independent replications method for output analysis
  private static boolean independent_replications() {
    if (((replications == null) || (replications.size() == 0)) && anim_stopped) {
      // Too little data for output analysis
      output_analysis_type = NONE;
      gather_data();
      return false;
    }
    boolean repeat = false;
    if (replications == null) {
      replications = new ArrayList();
    }
    // Store data of current replication and perform the next one
    Object[] rep_data = new Object[2];
    if (trans_time < 0.0) {
      trans_time = 0.0;
    }
    // Store a copy of the Sim_stat instance of each entity
    List rep_stats = new ArrayList();
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      Sim_stat stat = ((Sim_entity)entities.get(i)).get_stat();
      if (stat == null) {
        rep_stats.add(null);
      } else {
        rep_stats.add(stat.get_stat_copy());
      }
    }
    rep_data[0] = new double[] {clock, trans_time};
    rep_data[1] = rep_stats;
    replications.add(rep_data);
    total_transient_time += trans_time;
    total_time_elapsed += clock;
    if ((replications.size() < replication_count) && (!anim_stopped)) {
      // Start the next replication
      repeat = true;
    } else {
      if ((term_condition == INTERVAL_ACCURACY) && (!anim_stopped)) {
        int replications_size = replications.size();
        double total_mean = 0.0;
        double[] r_means = new double[replications_size];
        for (int i=0; i < replications_size; i++) {
          // For each replication
          Object[] r_data = (Object[])replications.get(i);
          double[] r_info = (double[])r_data[0];
          double r_total_time = r_info[0];
          double r_transient_time = r_info[1];
          // Calculate the mean for the specific replication
          Sim_stat stat = ((Sim_stat)((List)r_data[1]).get(term_entity_id));
          double r_mean;
          if (stat.is_efficient(term_measure)) {
            r_mean = stat.average(term_measure);
          } else {
            r_mean = stat.average(term_measure, r_transient_time, r_total_time);
          }
          r_means[i] = r_mean;
          total_mean += r_mean;
        }
        // Calculate the total mean
        total_mean = (total_mean / replications_size);
        // Calculate the total mean variance
        double total_variance = 0.0;
        for (int i=0; i < replications_size; i++) {
          total_variance += Math.pow((r_means[i] - total_mean), 2.0);
        }
        total_variance = (total_variance / (replications_size - 1)) / replications_size; // s2/n
        // Calculate the total mean standard deviation
        double total_std_dev = Math.sqrt(total_variance);
        double t_value = qt(1.0-(1.0-confidence_level)/2.0, replications_size-1, true);
        double half_width = t_value * total_std_dev;
        double accuracy = half_width / total_mean;
        if (accuracy >= term_accuracy) {
          // We need more replications
          int prev_rep_count = replication_count;
          replication_count = (int)Math.ceil(replications_size*Math.pow(half_width/(term_accuracy*total_mean), 2.0));
          print_message("Performing " + (replication_count - prev_rep_count) + " additional replications.");
          return true;
        }
      }
      // Calculate confidence intervals
      List confidence_intervals = new ArrayList();
      for (int i=0; i < entities_size; i++) {
        // For each entity
        String ent_name = ((Sim_entity)entities.get(i)).get_name();
        Sim_stat ent_stat = ((Sim_entity)entities.get(i)).get_stat();
        if (ent_stat == null) {
          confidence_intervals.add(new Object[] {ent_name, null});
        } else {
          List ent_measures = ent_stat.get_measures();
          List measures = new ArrayList();
          int ent_measures_size = ent_measures.size();
          for (int j=0; j < ent_measures_size; j++) {
            // For each measure being calculated for the entity
            String measure = ((String)((Object[])ent_measures.get(j))[0]);
            Integer measure_type = ((Integer)((Object[])ent_measures.get(j))[1]);
            int m_type = measure_type.intValue();
            double[] measure_levels = ent_stat.get_levels(measure); // null if no levels specified
            double total_count = 0.0;
            double total_minimum = -1.0;
            double total_maximum = -1.0;
            double[] total_proportions;
            if (measure_levels != null) {
              total_proportions = new double[measure_levels.length];
            } else {
              total_proportions = new double[0];
            }
            double total_mean = 0.0;
            int replications_size = replications.size();
            double[] r_means = new double[replications_size];
            for (int k=0; k < replications_size; k++) {
              // For each replication
              Object[] r_data = (Object[])replications.get(k);
              // Calculate the mean for the specific replication
              Sim_stat stat = ((Sim_stat)((List)r_data[1]).get(i));
              if (stat.is_efficient(measure)) {
                double r_mean = stat.average(measure);
                r_means[k] = r_mean;
                total_mean += r_mean;
                switch (m_type) {
                  case Sim_stat.RATE_BASED:
                    total_count += stat.count(measure);
                    break;
                  default: // Sim_stat.STATE_BASED and Sim_stat.INTERVAL_BASED
                    if (!measure.equals("Utilisation")) {
                      double r_max = stat.maximum(measure);
                      double r_min = stat.minimum(measure);
                      if (k == 0) {
                        total_maximum = r_max;
                        total_minimum = r_min;
                      } else {
                        if (r_max >= total_maximum) {
                          total_maximum = r_max;
                        }
                        if (r_min <= total_minimum) {
                          total_minimum = r_min;
                        }
                      }
                      if (measure_levels != null) {
                        double[] r_props = stat.exc_proportion(measure);
                        for (int l=0; l < r_props.length; l++) {
                          total_proportions[l] += r_props[l];
                        }
                      }
                    }
                    break;
                }
              } else {
                double[] r_info = (double[])r_data[0];
                double r_total_time = r_info[0];
                double r_transient_time = r_info[1];
                double r_mean = stat.average(measure, r_transient_time, r_total_time);
                r_means[k] = r_mean;
                total_mean += r_mean;
                switch (m_type) {
                  case Sim_stat.RATE_BASED:
                    total_count += stat.count(measure, r_transient_time, r_total_time);
                    break;
                  default: // Sim_stat.STATE_BASED and Sim_stat.INTERVAL_BASED
                    if (!measure.equals("Utilisation")) {
                      double r_max = stat.maximum(measure, r_transient_time, r_total_time);
                      double r_min = stat.minimum(measure, r_transient_time, r_total_time);
                      if (k == 0) {
                        total_maximum = r_max;
                        total_minimum = r_min;
                      } else {
                        if (r_max >= total_maximum) {
                          total_maximum = r_max;
                        }
                        if (r_min <= total_minimum) {
                          total_minimum = r_min;
                        }
                      }
                      if (measure_levels != null) {
                        double[] r_props = stat.exc_proportion(measure, measure_levels, r_transient_time, r_total_time);
                        for (int l=0; l < r_props.length; l++) {
                          total_proportions[l] += r_props[l];
                        }
                      }
                    }
                    break;
                }
              }
            }
            // Calculate the total mean
            total_mean = (total_mean / replications_size);
            // Calculate the total mean variance
            double total_variance = 0.0;
            for (int k=0; k < replications_size; k++) {
              total_variance += Math.pow((r_means[k] - total_mean), 2.0);
            }
            total_variance = (total_variance / (replications_size - 1)) / replications_size; // s2/n
            // Calculate the total mean standard deviation
            double total_std_dev = Math.sqrt(total_variance);
            double t_value = qt(1.0-(1.0-confidence_level)/2.0, replications_size-1, true);
            Object[] interval;
            switch (m_type) {
              case Sim_stat.RATE_BASED:
                // Calculate the average total count
                total_count = (total_count / replications_size);
                interval = new Object[7];
                break;
              default:
                if (measure.equals("Utilisation")) {
                  interval = new Object[6];
                } else {
                  if (measure_levels != null) {
                    // Calculate total exceedence proportions
                    for (int k=0; k < measure_levels.length; k++) {
                      total_proportions[k] = (total_proportions[k] / replications_size);
                    }
                    interval = new Object[9];
                  } else {
                    interval = new Object[8];
                  }
                }
                break;
            }
            double half_width = t_value * total_std_dev;
            double low_bound = total_mean - half_width;
            double high_bound = total_mean + half_width;
            interval[0] = new Double(low_bound);              // The low bound
            interval[1] = new Double(total_mean);             // The total mean
            interval[2] = new Double(high_bound);             // The high bound
            interval[3] = new Double(total_variance);         // The total mean variance
            if (total_mean == 0.0) {
              interval[4] = new Double(0.0);  // The accuracy
            } else {
              interval[4] = new Double(half_width/total_mean);  // The accuracy
            }
            interval[5] = new Double(total_std_dev);          // The total mean standard deviation
            if (!measure.equals("Utilisation")) {
              switch (m_type) {
                case Sim_stat.RATE_BASED:
                  interval[6] = new Double(total_count); // The total average count
                  break;
                default:
                  interval[6] = new Double(total_maximum); // The total maximum
                  interval[7] = new Double(total_minimum); // The total minimum
                  if (measure_levels != null) {
                    double[][] total_exc_props = new double[measure_levels.length][2];
                    for (int k=0; k < measure_levels.length; k++) {
                      total_exc_props[k][0] = measure_levels[k];
                      total_exc_props[k][1] = total_proportions[k];
                    }
                    interval[8] =  total_exc_props;
                  }
                  break;
              }
            }
            measures.add(new Object[] {measure, measure_type, interval});
          }
          confidence_intervals.add(new Object[] {ent_name, measures});
        }
      }
      // Record the data from the simulation
      run_data = new Object[8];
      long end_date = System.currentTimeMillis();
      run_data[0] = new long[] {start_date, end_date};
      run_data[1] = new int[] {trans_condition, term_condition, output_analysis_type};
      run_data[2] = new double[] {total_time_elapsed, total_transient_time};
      run_data[3] = initial_seeds;
      run_data[4] = new Double(confidence_level);
      run_data[5] = confidence_intervals;             // The total measures and confidence intervals
      run_data[6] = new Integer(replications.size()); // The number of replications performed
      run_data[7] = replications;                     // The data of each individual replication
      repeat = false;
    }
    return repeat;
  }

  // Apply the batch means method for output analysis
  private static boolean batch_means() {
    if (trans_time < 0.0) {
      trans_time = 0.0;
    }
    double steady_state_time = clock - trans_time;
    if ((term_condition == INTERVAL_ACCURACY) && (!anim_stopped)) {
      // This method of simulation termination based on interval accuracy is based on a sequential
      // batch means method proposed by Law and Carson and improved based on the proposals of
      // Schmeiser (1982)
      // Step 2
      Sim_stat stat = ((Sim_entity)entities.get(term_entity_id)).get_stat();
      int batch_count = 400;
      double batch_length = steady_state_time / batch_count;
      double total_mean = 0.0;
      double[] batch_means = new double[batch_count];
      for (int i=0; i < batch_count; i++) {
        double batch_start = trans_time + (i * batch_length);
        double batch_end = trans_time + ((i+1) * batch_length);
        double batch_mean = stat.average(term_measure, batch_start, batch_end);
        batch_means[i] = batch_mean;
        total_mean += batch_mean;
      }
      total_mean = total_mean / batch_count;
      double s_c = serial_correlation(total_mean, batch_means);
      if (s_c > 0.4) {
        // Step 5
        int temp = 2*term_count_bm[0];
        term_count += temp - term_count_bm[1];
        print_message("Collecting " + (temp - term_count_bm[1]) + " additional observations (Total: " + term_count + ").");
        term_count_bm[0] = term_count_bm[1];
        term_count_bm[1] = temp;
        return true;
      } else if (s_c >= 0.0) {
        // Step 3
        batch_count = 200;
        batch_length = steady_state_time / batch_count;
        total_mean = 0.0;
        for (int i=0; i < batch_count; i++) {
          double batch_start = trans_time + (i * batch_length);
          double batch_end = trans_time + ((i+1) * batch_length);
          double batch_mean = stat.average(term_measure, batch_start, batch_end);
          batch_means[i] = batch_mean;
          total_mean += batch_mean;
        }
        total_mean = total_mean / batch_count;
        double s_c_new = serial_correlation(total_mean, batch_means);
        if (s_c_new > s_c) {
          // Step 5
          int temp = 2*term_count_bm[0];
          term_count += temp - term_count_bm[1];
          print_message("Collecting " + (temp - term_count_bm[1]) + " additional observations (Total: " + term_count + ").");
          term_count_bm[0] = term_count_bm[1];
          term_count_bm[1] = temp;
          return true;
        }
      }
      // Step 4
      batch_count = 30; // Schmeiser's suggestion
      batch_length = steady_state_time / batch_count;
      total_mean = 0.0;
      for (int i=0; i < batch_count; i++) {
        double batch_start = trans_time + (i * batch_length);
        double batch_end = trans_time + ((i+1) * batch_length);
        double batch_mean = stat.average(term_measure, batch_start, batch_end);
        batch_means[i] = batch_mean;
        total_mean += batch_mean;
      }
      total_mean = total_mean / batch_count;
      double total_variance = 0.0;
      for (int i=0; i < batch_count; i++) {
        total_variance += Math.pow((batch_means[i] - total_mean), 2.0);
      }
      total_variance = (total_variance / (batch_count-1)) / batch_count; // s2/n
      // Calculate the total mean standard deviation
      double total_std_dev = Math.sqrt(total_variance);
      double t_value = qt(1.0-(1.0-confidence_level)/2.0, batch_count, true);
      double half_width = t_value * total_std_dev;
      double accuracy = half_width / total_mean;
      if (accuracy >= term_accuracy) {
        // Step 5
        int temp = 2*term_count_bm[0];
        term_count += temp - term_count_bm[1];
        print_message("Collecting " + (temp - term_count_bm[1]) + " additional observations.");
        term_count_bm[0] = term_count_bm[1];
        term_count_bm[1] = temp;
        return true;
      } else {
        min_batches = 30;
        max_batches = min_batches;
      }
    }
    // Stores for each batch length, the number of times it was considered best for a measure
    int[] best_length = new int[(max_batches-min_batches)+1];
    List confidence_intervals = new ArrayList();
    int entities_size = entities.size();
    for (int j=0; j < entities_size; j++) {
      String ent_name = ((Sim_entity)entities.get(j)).get_name();
      Sim_stat ent_stat = ((Sim_entity)entities.get(j)).get_stat();
      if (ent_stat == null) {
        confidence_intervals.add(new Object[] {ent_name, null});
      } else {
        List ent_measures = ent_stat.get_measures();
        List measures = new ArrayList();
        int ent_measures_size = ent_measures.size();
        for (int k=0; k < ent_measures_size; k++) {
          // For each measure being calculated for the entity
          String measure = ((String)((Object[])ent_measures.get(k))[0]);
          Integer measure_type = ((Integer)((Object[])ent_measures.get(k))[1]);
          int best_length_correlation = min_batches;
          double best_value_correlation = -1.0;
          List batch_lengths = new ArrayList();
          for (int l=min_batches; l <= max_batches; l++) {
            // For each batch count
            double batch_length = steady_state_time / l;
            double total_mean = 0.0;
            double[] batch_means = new double[l];
            for (int m=0; m < l; m++) {
              double batch_start = trans_time + (m * batch_length);
              double batch_end = trans_time + ((m+1) * batch_length);
              double batch_mean = ent_stat.average(measure, batch_start, batch_end);
              batch_means[m] = batch_mean;
              total_mean += batch_mean;
            }
            total_mean = total_mean / l;
            double total_variance = 0.0;
            for (int m=0; m < l; m++) {
              total_variance += Math.pow((batch_means[m] - total_mean), 2.0);
            }
            total_variance = (total_variance / (l - 1)) / l; // s2/n
            double total_std_dev = Math.sqrt(total_variance);
            double t_value = qt(1.0-(1.0-confidence_level)/2.0, l-1, true);
            double half_width = t_value * total_std_dev;
            double[] interval = new double[5];
            interval[0] = total_mean - half_width;  // The low bound
            interval[1] = total_mean;               // The total mean
            interval[2] = total_mean + half_width;  // The high bound
            interval[3] = total_variance;           // The total variance
            if (total_mean == 0.0) {
              interval[4] = 0.0 ; // The accuracy
            } else {
              interval[4] = half_width / total_mean ; // The accuracy
            }
            batch_lengths.add(interval);
            // Check the correlation
            double serial_correlation = serial_correlation(total_mean, batch_means);
            if (l == min_batches) {
              best_value_correlation = serial_correlation;
            } else {
              if (serial_correlation < best_value_correlation) {
                best_length_correlation = l;
                best_value_correlation = serial_correlation;
              }
            }
          }
          measures.add(new Object[] {measure, measure_type, batch_lengths});
          // Add one to the batch count that produced the best results for the current measure
          best_length[best_length_correlation - min_batches] += 1;
        }
        confidence_intervals.add(new Object[] {ent_name, measures});
      }
    }
    // Now find the batch count that produced the best results
    int index = 0;
    for (int j=1; j < best_length.length; j++) {
      if (best_length[j] > best_length[index]) {
        // More measures found "j" better than "index" as a batch count
        index = j;
      }
    }
    // Produce the final confidence intervals
    int confidence_intervals_size = confidence_intervals.size();
    for (int j=0; j < confidence_intervals_size; j++) {
      List measures = ((List)((Object[])confidence_intervals.get(j))[1]);
      if (measures == null) {
        continue;
      }
      Sim_stat stat = (Sim_stat)((Sim_entity)entities.get(j)).get_stat();
      int measures_size = measures.size();
      for (int k=0; k < measures_size; k++) {
        Object[] measure_data = (Object[])measures.get(k);
        String measure = (String)measure_data[0];
        int m_type = ((Integer)measure_data[1]).intValue();
        double[] measure_levels = stat.get_levels(measure);
        List batch_lengths = (List)measure_data[2];
        double[] best_interval = (double[])batch_lengths.get(index);
        Object[] interval;
        switch (m_type) {
          case Sim_stat.RATE_BASED:
            interval = new Object[7];
            interval[0] = new Double(best_interval[0]); // The low bound
            interval[1] = new Double(best_interval[1]); // The total mean
            interval[2] = new Double(best_interval[2]); // The high bound
            interval[3] = new Double(best_interval[3]); // The total variance
            interval[4] = new Double(best_interval[4]); // The accuracy
            interval[5] = new Double(Math.sqrt(best_interval[3])); // The total standard deviation
            interval[6] = new Double(stat.count(measure, trans_time, clock)); // Total count
            break;
          default:
            if (measure_levels != null) {
              interval = new Object[9];
            } else {
              interval = new Object[8];
            }
            interval[0] = new Double(best_interval[0]); // The low bound
            interval[1] = new Double(best_interval[1]); // The total mean
            interval[2] = new Double(best_interval[2]); // The high bound
            interval[3] = new Double(best_interval[3]); // The variance
            interval[4] = new Double(best_interval[4]); // The accuracy
            interval[5] = new Double(Math.sqrt(best_interval[3])); // The standard deviation
            if (!measure.equals("Utilisation")) {
              interval[6] = new Double(stat.maximum(measure, trans_time, clock)); // The maximum
              interval[7] = new Double(stat.minimum(measure, trans_time, clock)); // The minimum
              if (measure_levels != null) {
                double[] props = stat.exc_proportion(measure, measure_levels, trans_time, clock);
                double[][] exc_proportions = new double[measure_levels.length][2];
                for (int l=0; l < measure_levels.length; l++) {
                  exc_proportions[l][0] = measure_levels[l];
                  exc_proportions[l][1] = props[l];
                }
                interval[8] = exc_proportions;
              }
            }
            break;
        }
        measure_data[2] = interval;
      }
    }
    // Store the Sim_stat instance of each entity
    List run_stats = new ArrayList();
    for (int i=0; i < entities_size; i++) {
      run_stats.add(((Sim_entity)entities.get(i)).get_stat());
    }
    // Store the simulation's data
    run_data = new Object[8];
    long end_date = System.currentTimeMillis();
    run_data[0] = new long[] {start_date, end_date};
    run_data[1] = new int[] {trans_condition, term_condition, output_analysis_type};
    // The end time and transient period time
    run_data[2] = new double[] {clock, trans_time};
    run_data[3] = initial_seeds;
    run_data[4] = new Double(confidence_level);
    // The entities' measures and confidence intervals
    run_data[5] = confidence_intervals;
    // The number of batches and the batch length (as a time period)
    run_data[6] = new Object[] {new Integer(index+min_batches), new Double((clock-trans_time)/(index+min_batches))};
    // The statistics gatherers of each entity
    run_data[7] = run_stats;
    return false;
  }

  // Estimates the serial correlation of the batch means using jackknifing.
  private static double serial_correlation(double total_mean, double[] batch_means) {
    double r_sum1 = 0.0;
    double r_sum2 = 0.0;
    double mean1 = 0.0;
    double mean2 = 0.0;
    int l = batch_means.length;
    int n = l/2;
    for (int m=0; m < l; m++) {
      if (m < (l-1)) {
        r_sum1 += (batch_means[m]-total_mean)*(batch_means[m+1]-total_mean);
      }
      if (m < (n-1)) {
        mean1 += batch_means[m];
      } else {
        mean2 += batch_means[m];
      }
      r_sum2 += Math.pow(batch_means[m]-total_mean, 2.0);
    }
    double r = r_sum1/r_sum2;
    mean1 = mean1/n;
    mean2 = mean2/n;
    double r1_sum1 = 0.0;
    double r1_sum2 = 0.0;
    double r2_sum1 = 0.0;
    double r2_sum2 = 0.0;
    for (int m=0; m < l-1; m++) {
      if (m < (n-1)) {
        r1_sum1 += (batch_means[m]-mean1)*(batch_means[m+1]-mean1);
        r1_sum2 += Math.pow(batch_means[m]-mean1, 2.0);
      } else {
        r2_sum1 += (batch_means[m]-mean2)*(batch_means[m+1]-mean2);
        r2_sum2 += Math.pow(batch_means[m]-mean2, 2.0);
      }
    }
    double r1 = r1_sum1/r1_sum2;
    double r2 = r2_sum1/r2_sum2;
    return (2.0*r)-((r1+r2)/2.0);
  }

  // Computes quantiles of the normal distribution
  private static double qnorm(double p) {
    // ALGORITHM AS 111, APPL.STATIST., VOL.26, 118-121, 1977.
    double split = 0.42,
           a0 = 2.50662823884, a1 = -18.61500062529, a2 = 41.39119773534, a3 = -25.44106049637,
           b1 = -8.47351093090, b2 = 23.08336743743, b3 = -21.06224101826, b4 = 3.13082909833,
           c0 = -2.78718931138, c1 = -2.29796479134, c2 = 4.85014127135, c3 = 2.32121276858,
           d1 = 3.54388924762, d2 = 1.63706781897, q = p - 0.5;
    double r, ppnd = -1.0;
    if (Math.abs(q) <= split) {
      r = q * q;
      ppnd = q*(((a3*r+a2)*r+a1)*r+a0)/((((b4*r+b3)*r+b2)*r+b1)*r+1);
    } else {
      r = p;
      if (q > 0) r = 1 - p;
      if (r > 0) {
        r = Math.sqrt(-Math.log(r));
        ppnd = (((c3*r+c2)*r+c1)*r+c0)/((d2*r+d1)*r+1);
        if (q < 0) ppnd = -ppnd;
        else ppnd = 0;
      }
    }
    return ppnd;
  }

  // Computes quantiles of the Student's t distribution
  private static double qt(double p, int ndf, boolean lower_tail) {
  // Algorithm 396: Student's t-quantiles by
  // G.W. Hill CACM 13(10), 619-620, October 1970
    double P, q, a, b, c, d, y, x, prob;
    boolean neg;
    if ( p <= 0 || p >= 1 || ndf < 1) return -1;
    double eps = 1e-12;
    double M_PI_2 = 1.570796326794896619231321691640; // pi/2
    if ((lower_tail && p > 0.5) || (!lower_tail && p < 0.5)) {
      neg = false;
      P = 2 * (lower_tail ? (1 - p) : p);
    } else {
      neg = true;
      P = 2 * (lower_tail ? p : (1 - p));
    }
    if (Math.abs(ndf - 2) < eps) {
      q = Math.sqrt(2 / (P * (2 - P)) - 2);
    } else if (ndf < 1 + eps) {
      prob = P * M_PI_2;
      q = Math.cos(prob)/Math.sin(prob);
    } else {
      a = 1 / (ndf - 0.5);
      b = 48 / (a * a);
      c = ((20700 * a / b - 98) * a - 16) * a + 96.36;
      d = ((94.5 / (b + c) - 3) / b + 1) * Math.sqrt(a * M_PI_2) * ndf;
      y = Math.pow(d * P, 2 / ndf);
      if (y > 0.05 + a) {
        x = qnorm(0.5 * P);
        y = x * x;
        if (ndf < 5) c += 0.3 * (ndf - 4.5) * (x + 0.6);
        c = (((0.05 * d * x - 5) * x - 7) * x - 2) * x + b + c;
        y = (((((0.4 * y + 6.3) * y + 36) * y + 94.5) / c - y - 3) / b + 1) * x;
        y = a * y * y;
        if (y > 0.002) y = Math.exp(y) - 1;
        else y = (0.5 * y + 1) * y;
      } else {
        y = ((1/(((ndf+6)/(ndf*y)-0.089*d-0.822)*(ndf+2)*3)+0.5/(ndf+4))*y-1)*(ndf+1)/(ndf+2)+1/y;
      }
      q = Math.sqrt(ndf * y);
    }
    if (neg) q = -q;
    return q;
  }

  // Resets the simulation to its original state
  private static void reset() {
    // Clear the event queue
    future.clear();
    deferred.clear();
    entities.clear();
    wait_predicates.clear();
    // Reset all the entities to their original state
    Sim_entity ent, new_ent;
    int backup_size = backup.size();
    for (int i=0; i < backup_size; i++) {
      ent = (Sim_entity)backup.get(i);
      try {
        new_ent = (Sim_entity)ent.clone();
      } catch (CloneNotSupportedException e) {
        throw new Sim_exception("Could not reset entity " + ent.get_name() + ".");
      }
      new_ent.reset();
      entities.add(new_ent);
    }
    // Reset clock
    clock = 0.0;
    // Reset counters and flags for the transient and termination conditions
    term_count = initial_term_count;
    trans_count = initial_trans_count;
    term_time = initial_term_time;
    trans_time = -1.0;
    term_event_counter = 0;
    transient_term_event_count = 0;
    trans_event_counter = 0;
    in_steady_state = false;
    onestopped = new Semaphore(0);
    onecompleted = new Semaphore(0);
    if (term_times != null) {
      term_times.clear();
    }
  }

  /**
   * Internal method used to generate the simulation's report. This method should <b>not</b> be used
   * in user simulations.
   */
  public static void generate_report() {
    Sim_reporter reporter;
    if (animation) {
      reporter = ((Sim_anim)trcout).get_reporter();
    } else {
      reporter = new Sim_reportfile("sim_report");
    }
    reporter.setup_report();
    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
    DateFormat tf = DateFormat.getTimeInstance(DateFormat.LONG);
    // The header
    reporter.append_report("############################################################");
    reporter.append_report("#                                                          #");
    reporter.append_report("#                    SIMULATION REPORT                     #");
    reporter.append_report("#                                                          #");
    reporter.append_report("############################################################");
    reporter.append_report("");
    reporter.append_report("Version: SimJava 2.0");
    reporter.append_report("");
    reporter.append_report("Simulation date:         " + df.format(new Date(((long[])run_data[0])[0])));
    reporter.append_report("Simulation start time:   " + tf.format(new Date(((long[])run_data[0])[0])));
    reporter.append_report("Simulation end time:     " + tf.format(new Date(((long[])run_data[0])[1])));
    reporter.append_report("");
    // The general run information
    reporter.append_report("############################################################");
    reporter.append_report("#            Overall simulation run information            #");
    reporter.append_report("############################################################");
    reporter.append_report("");
    reporter.append_report("Total simulated time:    " + ((double[])run_data[2])[0]);
    reporter.append_report("Total transient time:    " + ((double[])run_data[2])[1]);
    reporter.append_report("Total steady state time: " + (((double[])run_data[2])[0] - ((double[])run_data[2])[1]));
    int trans_condition = ((int[])run_data[1])[0],
        term_condition = ((int[])run_data[1])[1],
        output_analysis_type = ((int[])run_data[1])[2];
    String trans_condition_info, term_condition_info, output_analysis_type_name;
    switch (trans_condition) {
      case EVENTS_COMPLETED:
        trans_condition_info = initial_trans_count + " event completions at " + get_entity(trans_entity_id).get_name();
        break;
      case TIME_ELAPSED:
        trans_condition_info = initial_trans_time + " units of elapsed simulated time";
        break;
      case MIN_MAX:
        trans_condition_info = "Truncation based on minimum and maximum observations for " + trans_measure + " of " + get_entity(trans_entity_id).get_name();
        break;
      default:
        trans_condition_info = "None";
        break;
    }
    switch (term_condition) {
      case EVENTS_COMPLETED:
        term_condition_info = initial_term_count + " event completions at " + get_entity(term_entity_id).get_name();
        break;
      case TIME_ELAPSED:
        term_condition_info = initial_term_time + " units of elapsed simulated time";
        break;
      case INTERVAL_ACCURACY:
        term_condition_info = "Confidence interval accuracy for " + term_measure + " of " + get_entity(term_entity_id).get_name();
        break;
      default:
        term_condition_info = "None";
        break;
    }
    switch (output_analysis_type) {
      case IND_REPLICATIONS:
        output_analysis_type_name = "Independent replications";
        break;
      case BATCH_MEANS:
        output_analysis_type_name = "Batch means";
        break;
      default:
        output_analysis_type_name = "None";
        break;
    }
    reporter.append_report("Transient condition:     " + trans_condition_info);
    reporter.append_report("Termination condition:   " + term_condition_info);
    reporter.append_report("Output analysis method:  " + output_analysis_type_name);
    if (output_analysis_type == IND_REPLICATIONS) {
    reporter.append_report("Confidence level:        " + (Double)run_data[4]);
    reporter.append_report("Replications performed:  " + (Integer)run_data[6]);
    } else if (output_analysis_type == BATCH_MEANS) {
    reporter.append_report("Confidence level:        " + (Double)run_data[4]);
    reporter.append_report("Number of batches:       " + (Integer)((Object[])run_data[6])[0]);
    reporter.append_report("Individual batch length: " + (Double)((Object[])run_data[6])[1]);
    }
    reporter.append_report("");
    // The total measurements and confidence intervals
    if (output_analysis_type != NONE) {
      List confidence_intervals = (List)run_data[5];
      if (measures_exist()) {
        reporter.append_report("############################################################");
        reporter.append_report("#       Total measurements and confidence intervals        #");
        reporter.append_report("############################################################");
        reporter.append_report("");
        int confidence_intervals_size = confidence_intervals.size();
        for (int i=0; i < confidence_intervals_size; i++) {
          Object[] cf_data = (Object[])confidence_intervals.get(i);
          String ent_name = (String)cf_data[0];
          List measures = (List)cf_data[1];
          if (measures == null) {
            continue;
          }
          int length = ent_name.length();
          int space_before = (60-(length+2))/2;
          int space_after = 60 - (space_before+length+2);
          StringBuffer line = new StringBuffer(60);
          for (int j=0; j < space_before; j++) line.append('-');
          line.append(" " + ent_name + " ");
          for (int j=0; j < space_after; j++) line.append('-');
          reporter.append_report(line.toString());
          reporter.append_report("");
          int measures_size = measures.size();
          for (int j=0; j < measures_size; j++) {
            Object[] m_data = (Object[])measures.get(j);
            String m_name = (String)m_data[0];
            int m_type = ((Integer)m_data[1]).intValue();
            Object[] measurements = (Object[])m_data[2];
            reporter.append_report("- " + m_name);
            reporter.append_report("");
            switch (m_type) {
              case Sim_stat.RATE_BASED:
                reporter.append_report("Total mean:          " + ((Double)measurements[1]).doubleValue());
                reporter.append_report("Interval low bound:  " + ((Double)measurements[0]).doubleValue());
                reporter.append_report("Interval high bound: " + ((Double)measurements[2]).doubleValue());
                reporter.append_report("Interval half width: " + (((Double)measurements[2]).doubleValue()-((Double)measurements[0]).doubleValue())/2.0);
                reporter.append_report("Accuracy ratio:      " + ((Double)measurements[4]).doubleValue());
                reporter.append_report("Mean variance:       " + ((Double)measurements[3]).doubleValue());
                reporter.append_report("Mean std deviation:  " + ((Double)measurements[5]).doubleValue());
                reporter.append_report("Average event count: " + ((Double)measurements[6]).doubleValue());
                break;
              default:
                reporter.append_report("Total mean:          " + ((Double)measurements[1]).doubleValue());
                reporter.append_report("Interval low bound:  " + ((Double)measurements[0]).doubleValue());
                reporter.append_report("Interval high bound: " + ((Double)measurements[2]).doubleValue());
                reporter.append_report("Interval half width: " + (((Double)measurements[2]).doubleValue()-((Double)measurements[0]).doubleValue())/2.0);
                reporter.append_report("Accuracy ratio:      " + ((Double)measurements[4]).doubleValue());
                reporter.append_report("Mean variance:       " + ((Double)measurements[3]).doubleValue());
                reporter.append_report("Mean std deviation:  " + ((Double)measurements[5]).doubleValue());
                if (!m_name.equals("Utilisation")) {
                  reporter.append_report("Total maximum:       " + ((Double)measurements[6]).doubleValue());
                  reporter.append_report("Total minimum:       " + ((Double)measurements[7]).doubleValue());
                  if (measurements.length == 9) {
                    reporter.append_report("Total average exceedence proportions:");
                    double[][] total_props = (double[][])measurements[8];
                    for (int k=0; k < total_props.length; k++) {
                      if (k+1 == total_props.length) {
                        reporter.append_report("    " + total_props[k][0] + " < " + m_name + " : " + total_props[k][1]);
                      } else {
                        reporter.append_report("    " + total_props[k][0] + " < " + m_name + " <= " + total_props[k+1][0] + " : " + Math.abs(total_props[k][1]-total_props[k+1][1]));
                      }
                    }
                  }
                }
                break;
            }
            reporter.append_report("");
          }
        }
      }
    } else {
      // A single run with no output analysis
      double total_time = ((double[])run_data[2])[0];
      double trans_time = ((double[])run_data[2])[1];
      List stats = (List)run_data[4];
      if (measures_exist()) {
        reporter.append_report("############################################################");
        reporter.append_report("#               Simulation run measurements                #");
        reporter.append_report("############################################################");
        reporter.append_report("");
        int stats_size = stats.size();
        for (int i=0; i < stats_size; i++) {
          Sim_stat stat = (Sim_stat)stats.get(i);
          if (stat == null) {
            continue;
          }
          String ent_name = stat.get_name();
          int length = ent_name.length();
          int space_before = (60-(length+2))/2;
          int space_after = 60 - (space_before+length+2);
          StringBuffer line = new StringBuffer(60);
          for (int j=0; j < space_before; j++) line.append('-');
          line.append(" " + ent_name + " ");
          for (int j=0; j < space_after; j++) line.append('-');
          reporter.append_report(line.toString());
          reporter.append_report("");
          List measures = stat.get_measures();
          int measures_size = measures.size();
          for (int j=0; j < measures_size; j++) {
            Object[] m_data = (Object[])measures.get(j);
            String m_name = (String)m_data[0];
            int m_type = ((Integer)m_data[1]).intValue();
            double[] levels = stat.get_levels(m_name);
            reporter.append_report("- " + m_name);
            reporter.append_report("");
            switch (m_type) {
              case Sim_stat.RATE_BASED:
                if (stat.is_efficient(m_name)) {
                  reporter.append_report("Sample mean:          " + stat.average(m_name));
                  reporter.append_report("Event count:          " + stat.count(m_name));
                } else {
                  reporter.append_report("Sample mean:          " + stat.average(m_name, trans_time, total_time));
                  reporter.append_report("Event count:          " + stat.count(m_name, trans_time, total_time));
                }
                break;
              default:
                if (stat.is_efficient(m_name)) {
                  reporter.append_report("Sample mean:          " + stat.average(m_name));
                  if (!m_name.equals("Utilisation")) {
                    reporter.append_report("Sample variance:      Not available in efficient mode");
                    reporter.append_report("Sample std deviation: Not available in efficient mode");
                    reporter.append_report("Maximum:              " + stat.maximum(m_name));
                    reporter.append_report("Minimum:              " + stat.minimum(m_name));
                    if (levels != null) {
                      reporter.append_report("Exceedence proportions:");
                      double[] props = stat.exc_proportion(m_name);
                      for (int k=0; k < levels.length; k++) {
                        if (k+1 == levels.length) {
                          reporter.append_report("      " + levels[k] + " < " + m_name + " : " + props[k]);
                        } else {
                          reporter.append_report("      " + levels[k] + " < " + m_name + " <= " + levels[k+1] + " : " + Math.abs(props[k]-props[k+1]));
                        }
                      }
                    }
                  }
                } else {
                  reporter.append_report("Sample mean:          " + stat.average(m_name, trans_time, total_time));
                  if (!m_name.equals("Utilisation")) {
                    reporter.append_report("Sample variance:      " + stat.variance(m_name, trans_time, total_time));
                    reporter.append_report("Sample std deviation: " + stat.std_deviation(m_name, trans_time, total_time));
                    reporter.append_report("Maximum:              " + stat.maximum(m_name, trans_time, total_time));
                    reporter.append_report("Minimum:              " + stat.minimum(m_name, trans_time, total_time));
                    if (levels != null) {
                      reporter.append_report("Exceedence proportions:");
                      double[] props = stat.exc_proportion(m_name, levels, trans_time, total_time);
                      for (int k=0; k < levels.length; k++) {
                        if (k+1 == levels.length) {
                          reporter.append_report("      " + levels[k] + " < " + m_name + " : " + props[k]);
                        } else {
                          reporter.append_report("      " + levels[k] + " < " + m_name + " <= " + levels[k+1] + " : " + Math.abs(props[k]-props[k+1]));
                        }
                      }
                    }
                  }
                }
                break;
            }
            reporter.append_report("");
          }
        }
      }
    }
    if (detailed_report) {
      if (output_analysis_type == IND_REPLICATIONS) {
        // Measurements from individual replication
        reporter.append_report("############################################################");
        reporter.append_report("#           Individual replication measurements            #");
        reporter.append_report("############################################################");
        reporter.append_report("");
        List replications = (List)run_data[7];
        int replications_size = replications.size();
        for (int i=0; i < replications_size; i++) {
          String title = "Replication " + (i+1);
          int length = title.length();
          int space_before = (60-(length+2))/2;
          int space_after = 60 - (space_before+length+2);
          StringBuffer line = new StringBuffer(60);
          line.append('#');
          for (int j=0; j < space_before-1; j++) line.append(' ');
          line.append(" " + title + " ");
          for (int j=0; j < space_after-1; j++) line.append(' ');
          line.append('#');
          reporter.append_report("############################################################");
          reporter.append_report(line.toString());
          reporter.append_report("############################################################");
          reporter.append_report("");
          Object[] r_data = (Object[])replications.get(i);
          double total_time = ((double[])r_data[0])[0];
          double trans_time = ((double[])r_data[0])[1];
          reporter.append_report("Total time:        " + total_time);
          reporter.append_report("Transient time:    " + trans_time);
          reporter.append_report("Steady state time: " + (total_time-trans_time));
          reporter.append_report("");
          List stats = (List)r_data[1];
          int stats_size = stats.size();
          for (int j=0; j < stats_size; j++) {
            Sim_stat stat = (Sim_stat)stats.get(j);
            if (stat == null) {
              continue;
            }
            String ent_name = stat.get_name();
            length = ent_name.length();
            space_before = (60-(length+2))/2;
            space_after = 60 - (space_before+length+2);
            line = new StringBuffer(60);
            for (int k=0; k < space_before; k++) line.append('-');
            line.append(" " + ent_name + " ");
            for (int k=0; k < space_after; k++) line.append('-');
            reporter.append_report(line.toString());
            reporter.append_report("");
            List measures = stat.get_measures();
            int measures_size = measures.size();
            for (int k=0; k < measures_size; k++) {
              Object[] m_data = (Object[])measures.get(k);
              String m_name = (String)m_data[0];
              int m_type = ((Integer)m_data[1]).intValue();
              double[] levels = stat.get_levels(m_name);
              reporter.append_report("- " + m_name);
              reporter.append_report("");
              switch (m_type) {
                case Sim_stat.RATE_BASED:
                  if (stat.is_efficient(m_name)) {
                    reporter.append_report("Sample mean:          " + stat.average(m_name));
                    reporter.append_report("Event count:          " + stat.count(m_name));
                  } else {
                    reporter.append_report("Sample mean:          " + stat.average(m_name, trans_time, total_time));
                    reporter.append_report("Event count:          " + stat.count(m_name, trans_time, total_time));
                  }
                  break;
                default:
                  if (stat.is_efficient(m_name)) {
                    reporter.append_report("Sample mean:          " + stat.average(m_name));
                    if (!m_name.equals("Utilisation")) {
                      reporter.append_report("Sample variance:      Not available in efficient mode");
                      reporter.append_report("Sample std deviation: Not available in efficient mode");
                      reporter.append_report("Maximum:              " + stat.maximum(m_name));
                      reporter.append_report("Minimum:              " + stat.minimum(m_name));
                      if (levels != null) {
                        reporter.append_report("Exceedence proportions:");
                        double[] props = stat.exc_proportion(m_name);
                        for (int l=0; l < levels.length; l++) {
                          if (l+1 == levels.length) {
                            reporter.append_report("      " + levels[l] + " < " + m_name + " : " + props[l]);
                          } else {
                            reporter.append_report("      " + levels[l] + " < " + m_name + " <= " + levels[l+1] + " : " + Math.abs(props[l]-props[l+1]));
                          }
                        }
                      }
                    }
                  } else {
                    reporter.append_report("Sample mean:          " + stat.average(m_name, trans_time, total_time));
                    if (!m_name.equals("Utilisation")) {
                      reporter.append_report("Sample variance:      " + stat.variance(m_name, trans_time, total_time));
                      reporter.append_report("Sample std deviation: " + stat.std_deviation(m_name, trans_time, total_time));
                      reporter.append_report("Maximum:              " + stat.maximum(m_name, trans_time, total_time));
                      reporter.append_report("Minimum:              " + stat.minimum(m_name, trans_time, total_time));
                      if (levels != null) {
                        reporter.append_report("Exceedence proportions:");
                        double[] props = stat.exc_proportion(m_name, levels, trans_time, total_time);
                        for (int l=0; l < levels.length; l++) {
                          if (l+1 == levels.length) {
                            reporter.append_report("      " + levels[l] + " < " + m_name + " : " + props[l]);
                          } else {
                            reporter.append_report("      " + levels[l] + " < " + m_name + " <= " + levels[l+1] + " : " + Math.abs(props[l]-props[l+1]));
                          }
                        }
                      }
                    }
                  }
                  break;
              }
              reporter.append_report("");
            }
          }
        }
      } else if (output_analysis_type == BATCH_MEANS) {
        // Measurements from individual batch means
        reporter.append_report("############################################################");
        reporter.append_report("#              Individual batch measurements               #");
        reporter.append_report("############################################################");
        reporter.append_report("");
        double total_time = ((double[])run_data[2])[0];
        double trans_time = ((double[])run_data[2])[1];
        int batch_count = ((Integer)((Object[])run_data[6])[0]).intValue();
        double batch_length = ((Double)((Object[])run_data[6])[1]).doubleValue();
        for (int i=0; i < batch_count; i++) {
          String title = "Batch " + (i+1);
          int length = title.length();
          int space_before = (60-(length+2))/2;
          int space_after = 60 - (space_before+length+2);
          StringBuffer line = new StringBuffer(60);
          line.append('#');
          for (int j=0; j < space_before-1; j++) line.append(' ');
          line.append(" " + title + " ");
          for (int j=0; j < space_after-1; j++) line.append(' ');
          line.append('#');
          reporter.append_report("############################################################");
          reporter.append_report(line.toString());
          reporter.append_report("############################################################");
          reporter.append_report("");
          List stats = (List)run_data[7];
          double start_time = trans_time + i*batch_length;
          double end_time = trans_time + (i+1)*batch_length;
          reporter.append_report("Start time: " + start_time);
          reporter.append_report("End time:   " + end_time);
          reporter.append_report("");
          int stats_size = stats.size();
          for (int j=0; j < stats_size; j++) {
            Sim_stat stat = (Sim_stat)stats.get(j);
            if (stat == null) {
              continue;
            }
            String ent_name = stat.get_name();
            length = ent_name.length();
            space_before = (60-(length+2))/2;
            space_after = 60 - (space_before+length+2);
            line = new StringBuffer(60);
            for (int k=0; k < space_before; k++) line.append('-');
            line.append(" " + ent_name + " ");
            for (int k=0; k < space_after; k++) line.append('-');
            reporter.append_report(line.toString());
            reporter.append_report("");
            List measures = stat.get_measures();
            int measures_size = measures.size();
            for (int k=0; k < measures_size; k++) {
              Object[] m_data = (Object[])measures.get(k);
              String m_name = (String)m_data[0];
              int m_type = ((Integer)m_data[1]).intValue();
              double[] levels = stat.get_levels(m_name);
              reporter.append_report("- " + m_name);
              reporter.append_report("");
              switch (m_type) {
                case Sim_stat.RATE_BASED:
                  reporter.append_report("Sample mean:          " + stat.average(m_name, start_time, end_time));
                  reporter.append_report("Event count:          " + stat.count(m_name, start_time, end_time));
                  break;
                default:
                  reporter.append_report("Sample mean:          " + stat.average(m_name, start_time, end_time));
                  if (!m_name.equals("Utilisation")) {
                    reporter.append_report("Sample variance:      " + stat.variance(m_name, start_time, end_time));
                    reporter.append_report("Sample std deviation: " + stat.std_deviation(m_name, start_time, end_time));
                    reporter.append_report("Maximum:              " + stat.maximum(m_name, start_time, end_time));
                    reporter.append_report("Minimum:              " + stat.minimum(m_name, start_time, end_time));
                    if (levels != null) {
                      reporter.append_report("Exceedence proportions:");
                      double[] props = stat.exc_proportion(m_name, levels, start_time, end_time);
                      for (int l=0; l < levels.length; l++) {
                        if (l+1 == levels.length) {
                          reporter.append_report("      " + levels[l] + " < " + m_name + " : " + props[l]);
                        } else {
                          reporter.append_report("      " + levels[l] + " < " + m_name + " <= " + levels[l+1] + " : " + Math.abs(props[l]-props[l+1]));
                        }
                      }
                    }
                  }
                  break;
              }
              reporter.append_report("");
            }
          }
        }
      }
    }
    if (include_seeds) {
      // The seeds used in the generators
      List seeds = (List)run_data[3];
      if (seeds.size() != 0) {
        reporter.append_report("############################################################");
        reporter.append_report("#                     Generator seeds                      #");
        reporter.append_report("############################################################");
        reporter.append_report("");
        int seeds_size = seeds.size();
        for (int i=0; i < seeds_size; i++) {
          Object[] s_data = (Object[])seeds.get(i);
          String ent_name = (String)s_data[0];
          int length = ent_name.length();
          int space_before = (60-(length+2))/2;
          int space_after = 60 - (space_before+length+2);
          StringBuffer line = new StringBuffer(60);
          for (int j=0; j < space_before; j++) line.append('-');
          line.append(" " + ent_name + " ");
          for (int j=0; j < space_after; j++) line.append('-');
          reporter.append_report(line.toString());
          reporter.append_report("");
          List s = (List)s_data[1];
          int s_size = s.size();
          for (int j=0; j < s_size; j++) {
            Object[] g_data = (Object[])s.get(j);
            String g_name = (String)g_data[0];
            Long g_seed = (Long)g_data[1];
            reporter.append_report("- " + g_name + ": " + g_seed);
          }
          reporter.append_report("");
        }
      }
    }
    reporter.close_report();
  }

  // Prints a message about the progress of the simulation
  private static void print_message(String message) {
    if (animation) {
      (((Sim_anim)trcout).get_applet()).add_message(message);
    } else {
      System.out.println(message);
    }
  }

  // METHODS USED BY SIM_ANIM

  /**
   * Internal method used to check if the simulation has completed. This method is used for animation
   * purposes and should <b>not</b> be used in user simulations.
   */
  public static boolean incomplete() { return incomplete; }

  /**
   * Internal method used to notify <code>Sim_system</code> that the user has clicked the stop button.
   * This applies to animated simulations and should <b>not</b> be used in user simulations.
   */
  public static void animation_stopped() {
    anim_stopped = true;
  }

  // TRACE METHODS

  /**
   * Set the desired contents of the trace file. The default is to include all trace, if tracing is
   * switched on.
   * @param default_trace If set to <code>true</code> the default trace will be included in the trace file,
   *                      if <code>false</code> it will not
   * @param entity_trace  If set to <code>true</code> the trace generated by the entities will be
   *                      included in the trace file, if <code>false</code> it will not
   * @param event_trace   If set to <code>true</code> the trace file will include trace for the selected
   *                      event types, if <code>false</code> it will not
   */
  public static void set_trace_detail(boolean default_trace, boolean entity_trace, boolean event_trace) {
    auto_trace = true;
    Sim_system.default_trace = default_trace;
    Sim_system.entity_trace = entity_trace;
    Sim_system.event_trace = event_trace;
  }

  /**
   * Generate trace for a specific event type.
   * @param tag The event type
   */
  public static void track_event(int tag) {
    auto_trace = true;
    if (trace_tags == null) {
      trace_tags = new int[1];
      trace_tags[0] = tag;
    } else {
      int[] temp = trace_tags;
      trace_tags = new int[temp.length + 1];
      for (int i=0; i < temp.length; i++) {
        trace_tags[i] = temp[i];
      }
      trace_tags[temp.length] = tag;
    }
  }

  /**
   * Generate trace for a set of specific event types
   * @tags The set of event types
   */
  public static void track_events(int[] tags) {
    auto_trace = true;
    if (trace_tags == null) {
      trace_tags = tags;
    } else {
      int[] temp = trace_tags;
      trace_tags = new int[temp.length + tags.length];
      for (int i=0; i < temp.length; i++) {
        trace_tags[i] = temp[i];
      }
      for (int i=(temp.length-1); i < tags.length; i++) {
        trace_tags[i] = tags[i];
      }
    }
  }

  // Adds a trace message
  static synchronized void trace(int src, String msg) {
    trcout.println("u: " + ((Sim_entity)entities.get(src)).get_name() + " at " + clock + ": " + msg);
  }

  // Called by entities to generate trace messages
  static synchronized void ent_trace(int src, String msg) {
    if ((auto_trace && entity_trace) || animation) trace(src, msg);
  }

  // Checks to see whether this tag is a tag for which tracing is specified
  private static boolean is_trace_tag(int tag) {
    boolean result = false;
    if (trace_tags != null) {
      for (int i=0; i < trace_tags.length; i++) {
        if (trace_tags[i] == tag) {
          result = true;
          break;
        }
      }
    }
    return result;
  }

  // Generate the graph data
  private static void generate_graphs() {
    if (!generate_graphs) return;
    ObjectOutputStream output = null;
    try {
      output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(graph_file)));
      System.out.print("Generating graph data...");
      output.writeObject(run_data);
      output.flush();
      output.close();
      System.out.println("finished.");
    } catch (IOException ioe) {
      System.out.println("Unable to generate graph data: " + ioe.getMessage());
    } finally {
      if (output != null) { try { output.close(); } catch (IOException ioe) {} }
    }
  }

  // Checks to see if measures have been defined in the simulation
  private static boolean measures_exist() {
    List stats;
    if (output_analysis_type == NONE) {
      stats = (List)run_data[4];
    } else if (output_analysis_type == IND_REPLICATIONS) {
      stats = (List)((Object[])(((List)run_data[7]).get(0)))[1];
    } else {
      stats = (List)run_data[7];
    }
    int stats_size = stats.size();
    for (int i=0; i < stats_size; i++) {
      Sim_stat stat = (Sim_stat)stats.get(i);
      if ((stat != null) && (stat.get_measures().size() > 0)) {
        return true;
      }
    }
    return false;
  }

  // Returns the transient time
  static synchronized double get_trans_time() {
    return trans_time;
  }

  // Called by Sim_stat objects to notify Sim_system that an efficient measure has been defined
  static synchronized void efficient_measure_defined() {
    efficient_measure_defined = true;
  }

  // Informs Sim_stat objects of the transient time end. This is used to reset any efficient measures
  private static void notify_stats_trans(double trans_time) {
    int entities_size = entities.size();
    for (int i=0; i < entities_size; i++) {
      Sim_entity entity = (Sim_entity)entities.get(i);
      Sim_stat stat = entity.get_stat();
      if (stat != null) {
        stat.steady_state_reached(trans_time);
      }
    }
  }

  // Check if the default trace is being produce
  static boolean default_tracing() {
   return (auto_trace && default_trace);
  }
}


