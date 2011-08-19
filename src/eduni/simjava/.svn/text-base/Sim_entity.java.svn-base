/* Sim_entity.java */

package eduni.simjava;

import eduni.simanim.Anim_port;
import eduni.simanim.Anim_entity;
import eduni.simanim.Anim_param;
import eduni.simanim.Sim_anim;
import eduni.simjava.distributions.Generator;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the types of entities or processes of the simulation.
 * <p>
 * To define an entity type for the simulation this class needs to be extended.
 * The subclass needs to override <code>body()</code> to define the entity's
 * behaviour. Methods beginning with the prefix <code>sim_</code> are runtime
 * methods to be called within <code>body()</code>.
 * <p>
 * Since version 2.0, entities don't need to check transient or termination
 * conditions. These are all maintained and checked by <code>Sim_system</code>.
 * To check whether the termination condition has been satisfied, entities
 * should use the <code>Sim_system.running()</code> method. As such. entities
 * that exhibit a continuous, looping behaviour should have a <code>body()</code>
 * as follows:
 * <p>
 * <code>
 * ...<br>
 * public void body() {<br>
 * &nbsp;&nbsp;while (Sim_system.running()) {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;// Entity's behaviour<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;}<br>
 * }<br>
 * ...<br>
 * </code>
 * <p>
 * Entities are provided with a selection of runtime methods to implement their behaviour.
 * The main runtime method families are the following:
 * <ul>
 *   <li>
 *     <code>sim_schedule</code> methods. These methods are used to schedule events to other
 *     entities in the simulation.
 *   <li>
 *     <code>sim_wait</code> methods. These methods are used to wait for an incoming event.
 *     Alternatively, <code>sim_select</code> or <code>sim_get_next</code> methods may be
 *     used to check also for events that arrived while the entity was busy.
 *   <li>
 *     <code>sim_pause</code> methods. These methods are used when the entity is inactive.
 *     Examples of such behaviour are timeouts and delay intervals between event scheduling.
 *   <li>
 *     <code>sim_process</code> methods. These methods are used when the entity is to
 *     be considered active. The difference between these methods and those of the
 *     <code>sim_pause</code> family is related to statistical measurements. If utilisation
 *     is being measured by the entity then a <code>sim_process</code> method should be used
 *     when the entity is considered to be busy processing.
 * </ul>
 * <p>
 * In order to collect statistical measurements from entities a <code>Sim_stat</code> object needs
 * to be defined for the entity. This is the object in charge of collecting observations and
 * calculating measurements. Measures of interest could be default of custom. Default measures are
 * those which can be considered in most simulations and whose update can be automatically carried
 * out without user effort. Custom measures on the other hand are measures with simulation specific
 * meaning that need to be updated by the user. An example of an entity defining and updating measures
 * of interest follows:
 * <p>
 * <code>
 * class AnEntityType extends Sim_entity {<br>
 * &nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;Sim_stat stat;<br>
 * &nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;AnEntityType(String name) {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;stat = new Sim_stat();<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;stat.add_measure(Sim_stat.UTILISATION);             // A default measure<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;stat.add_measure(Sim_stat.RESIDENCE_TIME);          // Another default measure<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;stat.add_measure("Loss rate", Sim_stat.RATE_BASED); // A rate-based custom measure<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;set_stat(stat);<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;}<br>
 * &nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;public void body() {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;if (aLossOccured) {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;stat.update("Loss rate", Sim_system.sim_clock()); // The custom measure is updated<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;sim_completed(e); // An event has completed service<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;}<br>
 * &nbsp;&nbsp;...<br>
 * }<br>
 * </code>
 * <p>
 * The <code>sim_completed</code> method seen above, is used to specify when a received event
 * is considered to have completed all service at the entity. This method needs to be called to
 * notify the entity's <code>Sim_stat</code> object and to notify <code>Sim_system</code> that
 * an event has completed service. More details on statistical measurements can be found in
 * the <a href="http://www.dcs.ed.ac.uk/home/simjava/tutorial/index.html#6">SimJava Tutorial</a>.
 * <p>
 * One additional note concerning entities' behaviour has to do with output analysis. In the case
 * of independent replications having been defined as an output analysis method, it could be
 * necessary to provide code with which to reset an entity's fields. This is required since, for
 * each replication, the entities must be reset to their original state. This is not required for
 * primitive types or immutable objects. However if objects such as a <code>Vector</code> have been
 * modified during the simulation run, they should be reset to their original state. This would take
 * place at the end of the <code>body()</code> method. For example:
 * <p>
 * <code>
 * class AnEntityType extends Sim_entity {<br>
 * &nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;Vector aVector = new Vector();<br>
 * &nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;public void body() {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;while (Sim_system.running()) {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;aVector.add(anObject); // The Vector's contents are modified<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;aVector.clear();         // The Vector is reset to its original state<br>
 * &nbsp;&nbsp;}<br>
 * &nbsp;&nbsp;...<br>
 * }<br>
 * </code>
 * <p>
 * @see         Sim_event
 * @see         Sim_stat
 * @see         Sim_system
 * @version     0.2, 11 July 2002
 * @author      Costas Simatos
 */
public class Sim_entity extends Thread implements Cloneable {

  // Private data members
  private String name;       // The entitys name
  private int me;            // The entitys id
  private Sim_event evbuf;   // For incoming events
  private Sim_stat stat;     // The entity's statistics gatherer
  private int state;         // The entity's current state
  private Semaphore restart; // Used by Sim_system to schedule the entity
  private Semaphore reset;   // Used by Sim_system to reset the simulation
  private List ports;        // The entitys outgoing ports
  private Anim_entity aent;  // Hacky Anim_entity pointer
  private List generators;   // The list of sample generators the entity has defined

  /**
   * Creates a new entity.
   * @param name The name to be associated with this entity
   */
  public Sim_entity(String name) {
    if (name.indexOf(" ") != -1) {
      throw new Sim_exception("Sim_entity: Entity names can't contain spaces.");
    }
    this.name = name;
    me = -1;
    state = RUNNABLE;
    restart = new Semaphore(0);
    reset = new Semaphore(0);
    ports = new ArrayList();
    generators = new ArrayList();
    aent = null;
    // Add this to Sim_system automatically
    Sim_system.add(this);
  }

  /**
   *The constructor for use with the <code>eduni.simanim</code> animation package.
   * @param name The name to be associated with this entity
   * @param image_name The name of the gif image file for this entity's
   *                   icon (without the .gif extension).
   * @param x The X co-ordinate at which the entity should be drawn
   * @param y The Y co-ordinate at which the entity should be drawn
   */
  public Sim_entity(String name, String image_name, int x, int y) {
    if (name.indexOf(" ") != -1) {
      throw new Sim_exception("Sim_entity: Entity names can't contain spaces.");
    }
    this.name = name;
    me = -1;
    state = RUNNABLE;
    restart = new Semaphore(0);
    reset = new Semaphore(0);
    ports = new ArrayList();
    generators = new ArrayList();
    // Add this to Sim_system automatically
    Sim_system.add(this);
    // Now anim stuff
    aent = new Anim_entity(name, image_name);
    aent.set_position(x, y);
    ((Sim_anim)Sim_system.get_trcout()).add_entity(aent);
  }

  /**
   * Make entity icon invisible
   */
  public void set_invisible(boolean b) { 
    if (aent!=null) { aent.set_invisible(b); }
  }

  /**
   * Get the name of this entity
   * @return The entity's name
   */
  public String get_name() { return name; }

  /**
   * Get the unique id number assigned to this entity
   * @return The id number
   */
  public int get_id() { return me; }

  /**
   * Get the port through which an event arrived.
   * @param ev The event
   * @return   The port which sent the event or <code>null</code> if
   *           it could not be found
   */
  public Sim_port get_port(Sim_event ev) {
    Sim_port curr;
    int size = ports.size();
    for (int i=0; i < size; i++) {
      curr = (Sim_port)ports.get(i);
      if (ev.get_src() == curr.get_dest()) {
        return curr;
      }
    }
    return null;
  }

  /**
   * Get the port with a given name.
   * @param name The name of the port to search for
   * @return     The port or <code>null</code> if it could not be found
   */
  public Sim_port get_port(String name) {
    Sim_port curr;
    int size = ports.size();
    for (int i=0; i < size; i++) {
      curr = (Sim_port)ports.get(i);
      if (name.compareTo(curr.get_pname()) == 0) {
        return curr;
      }
    }
    System.out.println("Sim_entity: could not find port "+name+
                       " on entity "+this.name);
    return null;
  }

  /**
   * Add a port to this entity.
   * @param port The port to add
   */
  public void add_port(Sim_port port) {
    Anim_port aport;
    ports.add(port);
    port.set_src(this.me);
    if(((aport = port.get_aport()) != null) && (aent != null)) {
      aent.add_port(aport);
    }
  }

  /**
   * Add a parameter to this entity.
   * Used with the <code>eduni.simanim</code> package for animation.
   * @param param The parameter to add
   */
  public void add_param(Anim_param param) {
    aent.add_param(param);
  }

  /**
   * Define a <code>Sim_stat</code> object for this entity.
   * @param param The <code>Sim_stat</code> object
   */
  public void set_stat(Sim_stat stat) {
    this.stat = stat;
    stat.set_entity_info(me, name);
  }

  /**
   * Get the entity's <code>Sim_stat</code> object.
   * @return The <code>Sim_stat</code> object defined for this entity or <code>null</code> if none is defined.
   */
  public Sim_stat get_stat() {
    return stat;
  }

  /**
   * The method which defines the behaviour of the entity. This method
   * should be overidden in subclasses of Sim_entity.
   */
  public void body() {
    System.out.println("Entity "+name+" has no body().");
  }

  /**
   * Write a trace message.
   * @param level The level at which the trace should be printed, used
   *              with <code>Sim_system.set_trace_level()</code> to control
   *              what traces are printed
   * @param msg The message to be printed
   */
  public void sim_trace(int level, String msg) {
    if((level & Sim_system.get_trace_level()) != 0) {
      Sim_system.ent_trace(me, msg);
    }
  }

  /**
   * Signal that an event has completed service.
   * @param e The event that has completed service
   */
  public void sim_completed(Sim_event e) {
    if (!Sim_system.running()) {
      return;
    }
    // Update statistics
    if (stat != null) {
      stat.update(Sim_stat.END_RESIDENCE, e.get_tag(), e.event_time(), Sim_system.sim_clock());
      stat.update(Sim_stat.END_SERVICE, e.get_tag(), e.end_waiting_time(), Sim_system.sim_clock());
    }
    // Notify Sim_system (used for run length determination)
    Sim_system.job_completed(me, e.get_tag());
  }

  // The schedule functions

  /**
   * Send an event to another entity by id number, with data. Note that the tag <code>9999</code> is reserved.
   * @param dest  The unique id number of the destination entity
   * @param delay How long from the current simulation time the event
   *              should be sent
   * @param tag   An user-defined number representing the type of event.
   * @param data  The data to be sent with the event.
   */
  public void sim_schedule(int dest, double delay, int tag, Object data) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.send(me, dest, delay, tag, data);
  }

  /**
   * Send an event to another entity by id number and with <b>no</b> data. Note that the tag <code>9999</code> is reserved.
   * @param dest  The unique id number of the destination entity
   * @param delay How long from the current simulation time the event
   *              should be sent
   * @param tag   An user-defined number representing the type of event.
   */
  public void sim_schedule(int dest, double delay, int tag) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.send(me, dest, delay, tag, null);
  }

  /**
   * Send an event to another entity through a port, with data. Note that the tag <code>9999</code> is reserved.
   * @param dest  The port to send the event through
   * @param delay How long from the current simulation time the event
   *              should be sent
   * @param tag   An user-defined number representing the type of event.
   * @param data  The data to be sent with the event.
   */
  public void sim_schedule(Sim_port dest, double delay, int tag, Object data) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.send(me, dest.get_dest(), delay, tag, data);
  }

  /**
   * Send an event to another entity through a port, with <b>no</b> data. Note that the tag <code>9999</code> is reserved.
   * @param dest  The port to send the event through
   * @param delay How long from the current simulation time the event
   *              should be sent
   * @param tag An user-defined number representing the type of event.
   */
  public void sim_schedule(Sim_port dest, double delay, int tag) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.send(me, dest.get_dest(), delay, tag, null);
  }

  /**
   * Send an event to another entity through a port with a given name, with data. Note that the tag <code>9999</code> is reserved.
   * @param dest  The name of the port to send the event through
   * @param delay How long from the current simulation time the event
   *              should be sent
   * @param tag   An user-defined number representing the type of event.
   * @param data  The data to be sent with the event.
   */
  public void sim_schedule(String dest, double delay, int tag, Object data) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.send(me, get_port(dest).get_dest(), delay, tag, data);
  }

  /**
   * Send an event to another entity through a port with a given name, with <b>no</b> data.
   * Note that the tag <code>9999</code> is reserved.
   * @param dest  The name of the port to send the event through
   * @param delay How long from the current simulation time the event
   *              should be sent
   * @param tag   An user-defined number representing the type of event.
   */
  public void sim_schedule(String dest, double delay, int tag) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.send(me, get_port(dest).get_dest(), delay, tag, null);
  }

  /**
   * Count how many events matching a predicate are waiting in the entity's deferred queue.
   * @param p The event selection predicate
   * @return  The count of matching events
   */
  public int sim_waiting(Sim_predicate p) { return Sim_system.waiting(me, p); }

  /**
   * Count how many events are waiting in the entiy's deferred queue
   * @return The count of events
   */
  public int sim_waiting() {
    return Sim_system.waiting(me, Sim_system.SIM_ANY);
  }

  /**
   * Extract the first event matching a predicate waiting in the entity's deferred queue.
   * @param p   The event selection predicate
   * @param ev  The event matched is copied into <body>ev</body> if it points to a blank event,
   *            or discarded if <code>ev</code> is <code>null</code>
   */
  public void sim_select(Sim_predicate p, Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.select(me, p);
    if((ev != null) && (evbuf != null)) {
      ev.copy(evbuf);
      if (stat != null) {
        stat.update(Sim_stat.END_WAITING, ev.get_tag(), ev.event_time(), Sim_system.sim_clock());
        ev.set_end_waiting_time(Sim_system.sim_clock());
      }
    }
    evbuf = null;       // ADA MSI
  }

  /**
   * Cancel the first event matching a predicate waiting in the entity's future queue.
   * @param p  The event selection predicate
   * @param ev The event matched is copied into <code>ev</code> if it points to a blank event,
               or discarded if <code>ev</code> is <code>null</code>
   * @return   The number of events cancelled (0 or 1)
   */
  public int sim_cancel(Sim_predicate p, Sim_event ev) {
    if (!Sim_system.running()) {
      return 1;
    }
    Sim_system.cancel(me, p);
    if((ev != null) && (evbuf != null)) ev.copy(evbuf);
    if (evbuf != null) { return 1;}  else { return 0; }
  }

  /**
   * Put an event back on the deferred queue.
   * @param ev The event to put back
   */
  public void sim_putback(Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.putback((Sim_event)ev.clone());
  }

  /**
   * Get the first event matching a predicate from the deferred queue, or if none match,
   * wait for a matching event to arrive.
   * @param p    The predicate to match
   * @param ev   The event matched is copied into <code>ev</code> if it points to a blank event,
   *             or discarded if <code>ev</code> is <code>null</code>
   */
  public void sim_get_next(Sim_predicate p, Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    if (sim_waiting(p) > 0) {
      sim_select(p, ev);
    } else {
      sim_wait_for(p,ev);
    }
  }

  /**
   * Get the first event waiting in the entity's deferred queue, or if there are none, wait
   * for an event to arrive.
   * @param ev  The event matched is copied into <code>ev</code> if it points to a blank event,
   * or discarded if <code>ev</code> is <code>null</code>
   */
  public void sim_get_next(Sim_event ev) {
    sim_get_next(Sim_system.SIM_ANY, ev);
  }

  /**
   * Get the id of the currently running entity
   * @return The currently running entity's id number
   */
  public int sim_current() {
    return this.get_id();
  }

  /**
   * Send on an event to an other entity through a port.
   * @param ev The event to send
   * @param p  The port through which to send the event
   */
  public void send_on(Sim_event ev, Sim_port p) {
    sim_schedule(p.get_dest(), 0.0, ev.type(), ev.get_data());
  }

  //
  // Package level methods
  //

  // Package access methods
  int get_state() { return state; }
  Sim_event get_evbuf() { return evbuf; }

  // The entity states
  static final int RUNNABLE = 0;
  static final int WAITING  = 1;
  static final int HOLDING  = 2;
  static final int FINISHED = 3;

  // Package update methods
  void restart() { restart.v(); }
  void set_going() { restart.v(); }
  void set_state(int state) { this.state = state; }
  void set_id(int id) { me = id; }
  void set_evbuf(Sim_event e) { evbuf = e; }
  void poison() {
    // Not used anymore
  }

  // Statistics update methods

  // Used to update default measures after an ARRIVAL event has occured
  void update(int type, int tag, double time_occured) {
    if (stat != null) stat.update(type, tag, time_occured);
  }

  // Used to tidy up the statistics gatherer for this entity.
  void tidy_up_stat() {
    if (stat != null) stat.tidy_up();
  }

  // Used to check if the entity is gathering statistics
  boolean has_stat() {
    return (stat != null);
  }

  /**
   * Get a clone of the entity. This is used when independent replications have been specified
   * as an output analysis method. Clones or backups of the entities are made in the beginning
   * of the simulation in order to reset the entities for each subsequent replication. This method
   * should not be called by the user.
   * @return A clone of the entity
   */
  protected Object clone() throws CloneNotSupportedException {
    Sim_entity copy = (Sim_entity)super.clone();
    copy.set_name(new String(name));
    copy.set_evbuf(null);
    return copy;
  }

  // Used to set a cloned entity's name
  private void set_name(String new_name) { name = new_name; }

  // Resets the statistics gatherer of this entity
  void reset() {
    if (stat != null) {
      stat.reset();
    }
    int size = generators.size();
    for (int i=0; i < size; i++) {
      Generator generator = (Generator)generators.get(i);
      generator.set_seed(Sim_system.next_seed());
    }
  }

  /**
   * Executes the entity's thread. This is an internal method and should not be overriden in
   * subclasses.
   */
  public final void run() {
    Sim_system.paused(); // Tell the system we're up and running
    restart.p();         // Initially we pause 'till we get the go ahead from system
    body();
    state = FINISHED;
    Sim_system.completed();
  }

  /**
   * Add a sample generator to this entity. This method is used in order to allow <code>Sim_system</code>
   * to reseed the generator. This is performed when independent replications have been selected
   * as an output analysis method. If this method is not used for a generator then the seed used in the
   * subsequent replication will be the last one produced by the generator in the previous run.
   * @param generator The sample generator to be added to the entity
   */
  public void add_generator(Generator generator) {
    generators.add(generator);
  }

  // Reseed the generators of this entity
  void reseed_generators() {
    int size = generators.size();
    for (int i=0; i < size; i++) {
      Generator generator = (Generator)generators.get(i);
      generator.set_seed(Sim_system.next_seed());
    }
  }

  // Get the generators defined for this entity
  List get_generators() {
    return generators;
  }

  // Process methods

  /**
   * Set the entity to be active for a given time period.
   * @param delay The time period for which the entity will be active
   */
  public void sim_process(double delay) {
    if (delay < 0.0) {
      throw new Sim_exception("Sim_entity: Negative delay supplied.");
    }
    if (!Sim_system.running()) {
      return;
    }
    double start_time = Sim_system.sim_clock();
    if (stat != null) {
      stat.set_busy(start_time);
    }
    Sim_system.hold(me,delay);
    Sim_system.paused();
    restart.p();
    if (!Sim_system.running()) return;
    if (stat != null) {
      stat.update(Sim_stat.END_HOLD, start_time, Sim_system.sim_clock());
    }
  }

  /**
   * Set the entity to be active until it receives an event. Note that the entity
   * will be interrupted only by <b>future</b> events.
   * @param ev The event to which the arriving event will be copied to
   */
  public void sim_process_until(Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    sim_process_until(Sim_system.SIM_ANY, ev);
  }


  /**
   * Set the entity to be active until it receives an event matching a specific predicate.
   * Note that the entity will be interrupted only by <b>future</b> events.
   * @param p The predicate to match
   * @param ev The event to which the arriving event will be copied to
   */
  public void sim_process_until(Sim_predicate p, Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    double start_time = Sim_system.sim_clock();
    if (stat != null) {
      stat.set_busy(start_time);
    }
    if (Sim_system.default_tracing()) {
      Sim_system.trace(me, "start holding");
    }
    sim_wait_for(p, ev);
    if (!Sim_system.running()) return;
    if (stat != null) {
      stat.update(Sim_stat.END_HOLD, start_time, Sim_system.sim_clock());
    }
  }

  /**
   * Set the entity to be active for a time period or until it is interrupted by the
   * arrival of an event. Note that the entity will be interrupted only by <b>future</b>
   * events.
   * @param delay The time period for which the entity will be active unless interrupted
   * @param ev    The event to which the arriving event will be copied to
   * @return      The time of the specified time period remaining after the arrival occured
   */
  public double sim_process_for(double delay, Sim_event ev) {
    if (delay < 0.0) {
      throw new Sim_exception("Sim_entity: Negative delay supplied.");
    }
    if (!Sim_system.running()) {
      return 0.0;
    } else {
      return sim_process_for(Sim_system.SIM_ANY, delay, ev);
    }
  }

  /**
   * Set the entity to be active for a time period or until it is interrupted by the
   * arrival of an event matching a predicate. Note that the entity will be interrupted only
   * by <b>future</b> events.
   * @param p     The predicate to match
   * @param delay The time period for which the entity will be active unless interrupted
   * @param ev    The event to which the arriving event will be copied to
   * @return      The time of the specified time period remaining after the arrival occured
   */
  public double sim_process_for(Sim_predicate p, double delay, Sim_event ev) {
    if (delay < 0.0) {
      throw new Sim_exception("Sim_entity: Negative delay supplied.");
    }
    if (!Sim_system.running()) {
      return 0.0;
    }
    double start_time = Sim_system.sim_clock();
    double time_left = 0.0;
    if (stat != null) {
      stat.set_busy(start_time);
    }
    if (Sim_system.default_tracing()) {
      Sim_system.trace(me, "start holding");
    }
    sim_schedule(me, delay, 9999); // Send self 'hold done' msg
    sim_wait_for(p, ev);
    if (!Sim_system.running()) return 0.0;
    if (ev.get_tag() != 9999) { // interrupted
      Sim_type_p stp = new Sim_type_p(9999);
      time_left = delay - (ev.event_time() - start_time);
      int success = sim_cancel(stp,null);
    }
    if (stat != null) {
      stat.update(Sim_stat.END_HOLD, start_time, Sim_system.sim_clock());
    }
    if (time_left <= 0.0) {
      return 0.0;
    } else {
      return time_left;
    }
  }

  // PAUSE METHODS

  /**
   * Set the entity to be inactive for a time period.
   * @param delay The time period for which the entity will be inactive
   */
  public void sim_pause(double delay) {
    if (delay < 0.0) {
      throw new Sim_exception("Sim_entity: Negative delay supplied.");
    }
    if (!Sim_system.running()) {
      return;
    }
    Sim_system.pause(me, delay);
    Sim_system.paused();
    restart.p();
  }

  /**
   * Set the entity to be inactive until it receives an event. Note that the
   * entity will be interrupted only by <b>future</b> events.
   * @param ev    The event to which the arriving event will be copied to
   */
  public void sim_pause_until(Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    sim_pause_until(Sim_system.SIM_ANY, ev);
  }

  /**
   * Set the entity to eb inactive until it receives an event matching a specific predicate.
   * Note that the entity will be interrupted only by <b>future</b> events.
   * @param p  The predicate to match
   * @param ev The event to which the arriving event will be copied to
   */
  public void sim_pause_until(Sim_predicate p, Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    if (Sim_system.default_tracing()) {
      Sim_system.trace(me, "start pausing");
    }
    sim_wait_for(p, ev);
  }

  /**
   * Set the entity to be inactive for a time period or until it is interrupted by the arrival of an event.
   * Note that the entity will be interrupted only by <b>future</b> events.
   * @param delay The time period for which the entity will be inactive unless interrupted
   * @param ev    The event to which the arriving event will be copied to
   * @return      The time of the specified time period remaining after the arrival occured
   */
  public double sim_pause_for(double delay, Sim_event ev) {
    if (!Sim_system.running()) {
      return 0.0;
    } else {
      return sim_pause_for(Sim_system.SIM_ANY, delay, ev);
    }
  }

  /**
   * Set the entity to be inactive for a time period or until it is interrupted by the arrival of an event matching
   * a predicate. Note that the entity will be interrupted only by <b>future</b> events.
   * @param p     The predicate to match
   * @param delay The time period for which the entity will be inactive unless interrupted
   * @param ev    The event to which the arriving event will be copied to
   * @return      The time of the specified time period remaining after the arrival occured
   */
  public double sim_pause_for(Sim_predicate p, double delay, Sim_event ev) {
    if (delay < 0.0) {
      throw new Sim_exception("Sim_entity: Negative delay supplied.");
    }
    if (!Sim_system.running()) {
      return 0.0;
    }
    double start_time = Sim_system.sim_clock();
    double time_left = 0.0;
    if (Sim_system.default_tracing()) {
      Sim_system.trace(me, "start pausing");
    }
    sim_schedule(me, delay, 9999); // Send self 'hold done' msg
    sim_wait_for(p, ev);
    if (!Sim_system.running()) return 0.0;
    if (ev.get_tag() != 9999) { // interrupted
      Sim_type_p stp = new Sim_type_p(9999);
      time_left = delay - (ev.event_time() - start_time);
      int success = sim_cancel(stp,null);
    }
    if (time_left <= 0.0) {
      return 0.0;
    } else {
      return time_left;
    }
  }

  // WAIT METHODS

  /**
   * Wait for an event to arrive. Note that this method doesn't check the entity's deferred queue.
   * @param ev The event to which the arriving event will be copied to
   */
  public void sim_wait(Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    do {
      Sim_system.wait(me, Sim_system.SIM_ANY);
      Sim_system.paused();
      restart.p();
      if (!Sim_system.running()) return;
    } while (evbuf==null);
    if ((ev != null) && (evbuf != null)) {
      ev.copy(evbuf);
      if (stat != null) {
        stat.update(Sim_stat.END_WAITING, ev.get_tag(), ev.event_time(), Sim_system.sim_clock());
        ev.set_end_waiting_time(Sim_system.sim_clock());
      }
    }
    evbuf = null;
  }

  /**
   * Wait for an event matching a specific predicate. This method doesn't check the entity's deferred queue.
   * <p>
   * Since 2.0 <code>Sim_syztem</code> checks the predicate for the entity. This avoids unnecessary context
   * switches for non-matching events.
   * @param p  The predicate to match
   * @param ev The event to which the arriving event will be copied to
   */
  public void sim_wait_for(Sim_predicate p, Sim_event ev) {
    if (!Sim_system.running()) {
      return;
    }
    do {
      Sim_system.wait(me, p);
      Sim_system.paused();
      restart.p();
      if (!Sim_system.running()) return;
    } while (evbuf == null);
    if ((ev != null) && (evbuf != null)) {
      ev.copy(evbuf);
    }
    // There in no need to check the predicate since Sim_system has done this for us
    evbuf = null;
    if ((stat != null) && (ev.get_tag() != 9999)) {
      stat.update(Sim_stat.END_WAITING, ev.get_tag(), ev.event_time(), Sim_system.sim_clock());
      ev.set_end_waiting_time(Sim_system.sim_clock());
    }
  }

  /**
   * Wait for an event to arrive or until a time period elapsed. This method doesn't check the entity's deferred queue.
   * @param delay The maximum time to wait
   * @param ev The event to which the arrving event will be copied to
   * @return The time remaining when the arrival occured
   */
  public double sim_wait_for(double delay, Sim_event ev) {
    if (!Sim_system.running()) {
      return 0.0;
    } else {
      return sim_wait_for(Sim_system.SIM_ANY, delay, ev);
    }
  }

  /**
   * Wait for an event matching a specific predicate to arrive or until a time period elapses.
   * This method doesn't check the entity's deferred queue.
   * @param p  The predicate to match
   * @param delay The maximum time period for which to wait
   * @param ev The event to which the arriving event will be copied to
   * @return The time remaining when the arrival occured
   */
  public double sim_wait_for(Sim_predicate p, double delay, Sim_event ev) {
    if (delay < 0.0) {
      throw new Sim_exception("Sim_entity: Negative delay supplied.");
    }
    if (!Sim_system.running()) {
      return 0.0;
    }
    sim_schedule(me, delay, 9999); // Send self 'wait done'
    sim_wait_for(p, ev);
    double time_left = 0.0;
    if (!Sim_system.running()) return 0.0;
    if (ev.get_tag() != 9999) { // interrupted
      Sim_type_p stp = new Sim_type_p(9999);
      time_left = delay - (ev.event_time() - Sim_system.sim_clock());
      int success = sim_cancel(stp, null);
    }
    if (time_left <= 0.0) {
      return 0.0;
    } else {
      return time_left;
    }
  }

  // HOLD METHODS - DEPRECATED

  /**
   * Hold for a time period
   * @param delay The time period for which to hold
   * @deprecated As of SimJava version 2.0, replaced by <code>sim_pause(double delay)</code>.
   *             This method was deprecated because of the new statistical support present to entities. When an
   *             entity holds it must now be specified if the hold corrssponds to the entity being active or
   *             inactive. The original <code>sim_hold()</code> methods are equivalent to their respective
   *             <code>sim_pause()</code> methods.
   */
  public void sim_hold(double delay) {
    if (!Sim_system.running()) {
      return;
    }
    sim_pause(delay);
  }

  /**
   * Hold for a time period or until an event arrives. This method doesn't check the entity's deferred queue.
   * @param delay The maximum time period for which to hold
   * @param ev    The event to which the arriving event will be copied to
   * @return The time remaining when the arrival occured
   * @deprecated As of SimJava version 2.0, replaced by <code>sim_pause_for(double delay, Sim_event ev)</code>.
   *             This method was deprecated because of the new statistical support present to entities. When an
   *             entity holds it must now be specified if the hold corrssponds to the entity being active or
   *             inactive. The original <code>sim_hold()</code> methods are equivalent to their respective
   *             <code>sim_pause()</code> methods.
   */
  public double sim_hold_for(double delay, Sim_event ev) {
    if (!Sim_system.running()) {
      return 0.0;
    } else {
      return sim_pause_for(delay, ev);
    }
  }

}
