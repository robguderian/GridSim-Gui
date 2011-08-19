/* Sim_stat.java */

package eduni.simjava;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.io.Serializable;

/**
 * A class that provides entities with statistical support.
 * <p>
 * If a modeller is interested in collecting statistical measurements from an entity
 * he needs to create a <code>Sim_stat</code> object, define measures of interest, and
 * set it as the entity's statistics gatherer. The <code>Sim_stat</code> objects defined
 * in the simulation are also accessed by <code>Sim_system</code> in order to apply output
 * analysis as well as certain transient and termination conditions.
 * <p>
 * The first step to adding statistical support to an entity is to create a <code>Sim_stat</code>
 * object in the entity's constructor. Following this, an <code>add_measure</code> method is
 * called to define a measure of interest for the entity. This is repeated for each measure that
 * is of interest. The way in which a measure is defined depends on its type. A measure can be
 * either <b>default</b> or <b>custom</b>.
 * <p>
 * Default measures are measures that can be considered in most simulations. Since their
 * meaning is straightforward, observations for these can be collected automatically based on
 * the internal events within the simulation. All that is required from the modeller is to define
 * the default measures of interest and then let <code>Sim_system</code> include summary statistics
 * based on their observations in the simulation report. The modeller also has access to the measures
 * at runtime via the several measurement calculation methods. The measurements that can be obtained
 * from default measures depend on the measure's type.
 * <p>
 * Custom measures on the other hand are measures with a simulation specific meaning. They are not
 * generally applicable and as such the modeller needs to define their type and the instances at
 * which they will be updated. Apart from the definition of custom measures and their updating, they
 * do not differ in any way from default measures.
 * <p>
 * All measures can be classified in one of the following categories:
 * <ul>
 *   <li>
 *     <b>Rate based</b> measures. These measures are based on event occurances over a time period.
 *     Examples of rate based measures could be an entity's arrival rate, loss rate, and throughput.
 *   <li>
 *     <b>Continuous state based</b> measures. State based measures are based on the entity having certain
 *     levels for given time periods. Continuous state based measures are those for which the
 *     entity constantly has some level, or alternatively, is in a certain state. Examples of
 *     such measures are an entity's queue length and its utilisation.
 *   <li>
 *     <b>Non continuous state based</b> measures. These measures differ to continuous ones because
 *     the entity is not required to constantly be in a certain state. Non continuous state based measures
 *     may be defined by the user as custom measures. No such default measures are provided.
 *   <li>
 *     <b>Interval based</b> measures. These measures are based on time intervals experienced by the entity
 *     or by events passing through the entity. An example of an interval based measure is the average waiting
 *     time of events at a certain entity.
 * </ul>
 * <p>
 * The default measures provided are the following:
 * <ul>
 *   <li>Arrival rate (rate based)
 *   <li>Throughput (rate based)
 *   <li>Queue length (continuous state based)
 *   <li>Utilisation (continuous state based)
 *   <li>Waiting time (interval based)
 *   <li>Service time (interval based)
 *   <li>Residence time (interval based)
 * </ul>
 * <p>
 * The measurements available for each measure type are the following:
 * <ul>
 *   <li>Rate based: Average, event count
 *   <li>State based (continuous and non continuous): Average, variance, standard deviation, minimum,
 *       maximum, exceedence proportions. Exceedence proportions are used to calculate the proportion
 *       of time that the entity's state was higher than each given state. Note that for utilisation, only the
 *       average is available because entities in <code>SimJava</code> are considered to have single
 *       server behaviour.
 *   <li>Interval based: Average, variance, standard deviation, minimum, maximum, exceedence proportions.
 *       In this case the exceedence proportions are used to calculate the proportion of events that
 *       for example experienced a waiting time over each given level.
 * </ul>
 * <p>
 * These measurements are produced by relevant methods of <code>Sim_stat</code>. These may be called at
 * runtime by the user to check a measure's progress, or at the simulation's end by <code>Sim_system</code>
 * in order to apply output analysis and to generate the simulation's report.
 * <p>
 * To calculate exceedence proportions the user calls <code>calc_proportions</code> on the <code>Sim_stat</code>
 * object <b>after</b> the relevant measure has been defined. The levels of interest for which proportions will
 * be calculated are provided with this method.
 * <p>
 * The user also has the ability to specify certain event tags for which the measures defined should
 * be considered. This is made possible through the <code>measure_for</code> method. The ability to
 * select tags of interest is important since events with specific tags are often used as control events
 * between the entities and do not represent actual system jobs. Furthermore, it could be the case that tags
 * differentiate between different types of events, in which case the user may be interested only in
 * measures for one of those types. The tags specified are used to decide whether or not observations should
 * be collected for default measures. In the case of custom measures the specified tags are not considered.
 * This is the case since custom measures are updated by the modeller explicitly.
 * <p>
 * Custom measures may also be used to implement default measures for several different event types. In this
 * case the user defines separate measures for each event type of interest and proceeds to update them during
 * the simulation run.
 * <p>
 * Apart from defining a measure as default or custom, the user has an additional option regarding its
 * calculation efficiency. The default way in which measurements are calculated is through observation
 * collection. Whenever an internal (for default measures) or user called update method (for custom measures) is used, an
 * observation is collected based on the given parameters. The approach of collecting observations for
 * each measure provides the maximum possible information level. Such detailed measures can be used
 * with all output analysis methods, in all transient and termination conditions and also used to produce
 * graphs of the measures' progress through time. However, considerable memory requirements are presented
 * in this case that could be in certain cases prohibiting.
 * <p>
 * For this reason the user has the option to define a measure as being <i>"efficient"</i>. In this case
 * observations are not collected, but measurements are rather dynamically recalculated with each update.
 * This approach minimises the memory requirements and is to be used in cases of large simulations with
 * many entities and measures, which are to have large run lengths. The tradeoff in this case is functionality.
 * When efficient measures are defined batch means can't be used as an output analysis method. Furthermore,
 * the minimum-maximum method of transient period estimation can't be used with an efficient measure. The
 * measurements of variance and standard deviation are not available for these measures and the other
 * measurements may be calculated only for the entire run length or the entire steady state - not for
 * arbitrary time periods. Finally, graphs may not be produced for efficient measures.
 * <p>
 * Which approach is to be used is left to the user's discretion. It is possible however to have a mix of
 * detailed and efficient measures with the above stated limitations. A measure is specified as being
 * efficient by calling a <code>set_efficient</code> method while the <code>Sim_stat</code> object is
 * being setup.
 * <p>
 * A final point of consideration is the order with which the setup steps are carried out for a <code>Sim_stat</code>
 * object:
 * <ul>
 *   <li>The <code>Sim_stat</code> object is instantiated.
 *   <li>The default and custom measures are defined. For each measure that will have exceedence proportions
 *       calculated, the <code>calc_proportions</code> method is called after the measure definition.
 *   <li>Efficient measures are specified using the <code>set_efficient</code> method. The main point here
 *       is that measures must be set as efficient after they have been defined and also after exceedence
 *       proportions have been specified.
 *   <li>The tags of interest are defined. This may be done at any stage.
 *   <li>Finally, the <code>Sim_stat</code> object is set to be the entity's statistics gatherer by using
 *       the <code>set_stat</code> method.
 * </ul>
 * <p>
 * More information on <code>SimJava</code>'s statistical support can be found at the
 * <a href="http://www.dcs.ed.ac.uk/home/simjava/tutorial/index.html#6">SimJava Tutorial</a>.
 * @see Sim_system
 * @see Sim_entity
 * @version 1.0 11 July 2002
 * @author Costas Simatos
 */
public class Sim_stat implements Serializable {

  // Measure types

  /** Constant defining rate based measures. */
  public static final int RATE_BASED = 0;
  /** Constant defining state based measures. */
  public static final int STATE_BASED = 1;
  /** Constant defining interval based measures. */
  public static final int INTERVAL_BASED = 2;

  // Event types
  static final int ARRIVAL = 0;
  static final int END_WAITING = 1;
  static final int END_SERVICE = 2;
  static final int END_RESIDENCE = 3;
  static final int END_HOLD = 4;

  // Measure flags

  // Rate-based measures. Counter measures are measured along with these
  /** Constant specifying the entity's arrival rate. */
  public static final int ARRIVAL_RATE = 0;
  /** Constant specifying the entity's throughput. */
  public static final int THROUGHPUT = 1;
  // State-based measures
  /** Constant specifying the entity's queue length. */
  public static final int QUEUE_LENGTH = 2;
  /** Constant specifying the entity's utilisation. */
  public static final int UTILISATION = 3;
  // Interval-based measures
  /** Constant specifying the entity's event waiting time. */
  public static final int WAITING_TIME = 4;
  /** Constant specifying the entity's event residence time. */
  public static final int RESIDENCE_TIME = 5;
  /** Constant specifying the entity's event service time. */
  public static final int SERVICE_TIME = 6;

  // Default measure names
  private static final String[] measure_names = {"Arrival rate",
                                                 "Throughput",
                                                 "Queue length",
                                                 "Utilisation",
                                                 "Waiting time",
                                                 "Residence time",
                                                 "Service time"};
  // Default measure types
  private static final int[] measure_types = {RATE_BASED,
                                              RATE_BASED,
                                              STATE_BASED,
                                              STATE_BASED,
                                              INTERVAL_BASED,
                                              INTERVAL_BASED,
                                              INTERVAL_BASED};

  private transient List tags, // An array with the event tags of interest
                         counters, // Stores the previous time of update and state value for custom STATE-BASED measures
                         init_counters; // Stores the initial state values for custom STATE-BASED measures
  private List measures, // Contains the measures that are to be calculated
               observations, // Contains the vectors of the measures calculated
               levels; // Stores the levels of exceedence proportions that are desired to be calculated
  private HashMap data; // Holds required data for light measures

  private List annotations; // Holds the annotations. Used by the graph viewing utility.

  // The counters of default counter measures
  private transient int queue_length = 0;
  private transient double prev_time_queue = 0.0,
                           prev_time_util = 0.0,
                           started_busy;
  private transient boolean entity_busy = false;

  // The id and name of the entity in which this Sim_stat instance is held
  private int entity_id;
  private String entity_name;
  private double trans_time = -1;
          double end_time = -1;

  /**
   * The constructor used to create a new <code>Sim_stat</code> object.
   */
  public Sim_stat() {
    observations = new ArrayList();
    levels = new ArrayList();
    measures = new ArrayList();
    tags = new ArrayList();
    counters = new ArrayList();
    init_counters = new ArrayList();
  }

  // Set the entity-name of this Sim_stat
  void set_entity_info(int id, String name) {
    entity_id = id;
    entity_name = name;
  }
  // Get the entity-id of this Sim_stat
  int get_id() { return entity_id; }

  /**
   * Get the name of the entity to which this <code>Sim_stat</code> object belongs to.
   * @return The <code>Sim_stat</code> owner's name
   */
  public String get_name() { return entity_name; }

  //  METHODS FOR THE MODELLER TO SPECIFY THE MEASURES (DEFAULT-CUSTOM) OF INTEREST

  /**
   * Specify a default measure to be calculated.
   * @param measure The <code>int</code> constant defining the default measure of interest
   */
  public void add_measure(int measure) {
    if ((measure < 0) || (measure > 6)) {
      throw new Sim_stat_exception("The measure specified is not a default measure.");
    } else if (is_selected(measure_names[measure])) {
      throw new Sim_stat_exception(measure_names[measure] + " is already being measured for this entity.");
    }
    measures.add(new Object[] {measure_names[measure], new Integer(measure_types[measure])});
    observations.add(new ArrayList());
  }

  /**
   * Specify a (continuous) state, interval, or rate based custom measure to be calculated. If a
   * state based measure is defined the original state is set to <code>0.0</code>.
   * @param measure_name The name of the measure. This must be unique among the entity's measures.
   * @param measure_type The <code>int</code> constant that specifies the measure's type
   */
  public void add_measure(String measure_name, int measure_type) {
    if ((measure_type < 0) || (measure_type > 2)) {
      throw new Sim_stat_exception("The specified measure type is not valid.");
    } else if (is_selected(measure_name)) {
      throw new Sim_stat_exception(measure_name + " is already being measured for this entity.");
    }
    if (measure_type == STATE_BASED) {
      // Set the default level for the STATE-BASED measure
      add_measure(measure_name, measure_type, 0.0);
    } else {
      measures.add(new Object[] {measure_name, new Integer(measure_type)});
      observations.add(new ArrayList());
    }
  }

  /**
   * Specify a continuous state based custom measure with a given initial state.
   * @param measure_name The name of the measure. This must be unique among the entity's measures.
   * @param measure_type The <code>int</code> constant that specifies the measure's type. In this
   *                     case the type must be <code>STATE_BASED</code>.
   * @param level        The initial state of the measure
   */
  public void add_measure(String measure_name, int measure_type, double level) {
    if (measure_type != STATE_BASED) {
      throw new Sim_stat_exception("The specified measure type is not valid.");
    } else if (is_selected(measure_name)) {
      throw new Sim_stat_exception(measure_name + " is already being measured for this entity.");
    }
    measures.add(new Object[] {measure_name, new Integer(measure_type), new Boolean(true)});
    observations.add(new ArrayList());
    counters.add(new Object[] {measure_name, new Double(level), new Double(0.0)});
    init_counters.add(new Object[] {measure_name, new Double(level), new Double(0.0)});
  }

  /**
   * Specify a (continuous or non-continuous) state based custom measure. If the measure is
   * continuous the initial state is set to <code>0.0</code>.
   * @param measure_name The name of the measure. This must be unique among the entity's measures.
   * @param measure_type The <code>int</code> constant that specifies the measure's type. In this
   *                     case the type must be <code>STATE_BASED</code>.
   * @param continuous   <code>true</code> for a continuous measure, <code>false</code> for a
   *                     non-continuous measure.
   */
  public void add_measure(String measure_name, int measure_type, boolean continuous) {
    if (measure_type != STATE_BASED) {
      throw new Sim_stat_exception("The specified measure type is not valid.");
    } else if (is_selected(measure_name)) {
      throw new Sim_stat_exception(measure_name + " is already being measured for this entity.");
    }
    if (continuous) {
      add_measure(measure_name, measure_type, 0.0);
    } else {
      measures.add(new Object[] {measure_name, new Integer(measure_type), new Boolean(false)});
      observations.add(new ArrayList());
    }
  }

  /**
   * Specify a default measure for which exceedence proportions are to be calculated. The measure must
   * be state or interval based but not utilisation.
   * @param measure The <code>int</code> constant defining the default measure of interest
   * @param levels  The set of exceedence proportion levels
   */
  public void calc_proportions(int measure, double[] levels) {
    calc_proportions(measure_names[measure], levels);
  }

  /**
   * Specify a custom measure for which exceedence proportions are to be calculated. The measure must
   * be state or interval based.
   * @param measure The name of the measure
   * @param levels  he set of exceedence proportion levels
   */
  public void calc_proportions(String measure, double[] levels) {
    int id = get_id(measure);
    int type = get_type(id);
    if (id == -1) {
      throw new Sim_stat_exception("The measure specified has not been defined.");
    } else if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || (measure.equals("Utilisation"))) {
      throw new Sim_stat_exception("Exceedence proportions can't be calculated for " + measure);
    }
    this.levels.add(new Object[] {measure, levels});
  }

  /**
   * Specify a set of event tags for which measures are to be considered. This is used for default measures
   * that need to be automatically updated. Events with tags other than the ones specified will not be
   * considered for the defined default measures. If the measure is not called, all the event tags will be
   * considered. Finally, in the case of utilisation, the selected tags are not taken into account and observations
   * are always collected.
   * @param tags The set of event tags of interest
   */
  public void measure_for(int[] tags) {
    Integer tag;
    for (int i=0; i < tags.length; i++) {
      tag = new Integer(tags[i]);
      if (!(this.tags.contains(tag))) {
        this.tags.add(tag);
      }
    }
  }

  /**
   * Mark a default measure as memory efficient.
   * @param measure The <code>int</code> constant defining the default measure of interest
   */
  public void set_efficient(int measure) {
    set_efficient(measure_names[measure]);
  }

  /**
   * Mark a custom measure as memory efficient
   * @param measure The name of the measure
   */
  public void set_efficient(String measure) {
    if (!is_selected(measure)) {
      throw new Sim_stat_exception(measure + " has not been measured for this entity.");
    }
    if (data == null) {
      data = new HashMap();
    }
    if (!data.containsKey(measure)) {
      int type = get_type(get_id(measure));
      switch (type) {
        case RATE_BASED:
          data.put(measure, new Long(0L));
          break;
        case STATE_BASED:
          double initial_level = 0.0;
          Object[] counters = get_counters(measure);
          if (counters != null) {
            // Its a custom state based measure
            initial_level = ((Double)counters[1]).doubleValue();
          }
          if (get_levels(measure) == null) {
            data.put(measure, new Object[] {new double[] {0, 0, 0, 0, initial_level, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}});
          } else {
            data.put(measure, new Object[] {new double[] {0, 0, 0, 0, initial_level, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}, new double[get_levels(measure).length]});
          }
          break;
        case INTERVAL_BASED:
          if (get_levels(measure) == null) {
            data.put(measure, new Object[] {new double[] {0, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 0}});
          } else {
            data.put(measure, new Object[] {new double[] {0, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 0}, new double[get_levels(measure).length]});
          }
          break;
      }
      Sim_system.efficient_measure_defined();
    }
  }

  //  METHODS USED TO UPDATE THE OBSERVATIONS

  // Methods used to update the observations

  // Used to update default measures after an ARRIVAL event has occurred
  void update(int type, int tag, double time_occurred) {
    if (type != ARRIVAL) {
      throw new Sim_stat_exception("Invalid event type.");
    } else if (time_occurred < 0.0) {
      throw new Sim_stat_exception("Invalid event occurance time.");
    }
    if (tags.contains(new Integer(tag)) || (tags.size() == 0)) {
      if (is_selected(measure_names[ARRIVAL_RATE])) {
        if (is_efficient(measure_names[ARRIVAL_RATE])) {
          Long counter = (Long)data.get(measure_names[ARRIVAL_RATE]);
          counter = new Long(counter.longValue()+1);
          data.put(measure_names[ARRIVAL_RATE], counter);
        } else {
          ((List)observations.get(get_id(measure_names[ARRIVAL_RATE]))).add(new Double(time_occurred));
        }
        // Notify Sim_system
        Sim_system.observation_collected(entity_id, measure_names[ARRIVAL_RATE]);
      }
      if (is_selected(measure_names[QUEUE_LENGTH])) {
        if (is_efficient(measure_names[QUEUE_LENGTH])) {
          Object[] values = (Object[])data.get(measure_names[QUEUE_LENGTH]);
          double[] mValues = (double[])values[0];
          if (time_occurred == trans_time) {
            // The time_occurred and mValues[3] are equal to the trans_time
            // the only thing to be done is to increase the level
            mValues[4] += 1; // The previous level
            data.put(measure_names[QUEUE_LENGTH], values);
            Sim_system.observation_collected(entity_id, measure_names[QUEUE_LENGTH]);
            return;
          }
          mValues[0] += mValues[4] * (time_occurred-mValues[3]); // The level sum
          mValues[1] += time_occurred-mValues[3]; // The time sum
          if (values.length == 2) {
            // Exceedence proportions are to be calculated
            double[] pValues = (double[])values[1];
            double[] levels = get_levels(measure_names[QUEUE_LENGTH]);
            for (int i=0; i < levels.length; i++) {
              if (mValues[4] > levels[i]) {
                pValues[i] += time_occurred-mValues[3];
              }
            }
          }
          mValues[2] = mValues[3]; // The previous interval start time
          mValues[3] = time_occurred; // The previous interval end time
          if (mValues[4] < mValues[5]) {
            mValues[5] = mValues[4]; // The minimum
          }
          if (mValues[4] > mValues[6]) {
            mValues[6] = mValues[4]; // The maximum
          }
          mValues[4] += 1; // The previous level
          data.put(measure_names[QUEUE_LENGTH], values);
          Sim_system.observation_collected(entity_id, measure_names[QUEUE_LENGTH]);
        } else {
          ((List)observations.get(get_id(measure_names[QUEUE_LENGTH]))).add(new double[] {queue_length, prev_time_queue, time_occurred});
          queue_length++;
          prev_time_queue = time_occurred;
          // Notify Sim_system
          Sim_system.observation_collected(entity_id, measure_names[QUEUE_LENGTH]);
        }
      }
    }
  }

  // Used to update default measures after an END_WAITING, END_RESIDENCE, END_SERVICE event has occurred
  void update(int type, int tag, double start_time, double end_time) {
    if ((type < 1) || (type > 3)) {
      throw new Sim_stat_exception("Invalid event type.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid event occurance times.");
    }
    if (tags.contains(new Integer(tag)) || (tags.size() == 0)) {
      switch (type) {
        case END_WAITING:
          if (is_selected(measure_names[QUEUE_LENGTH])) {
            if (is_efficient(measure_names[QUEUE_LENGTH])) {
              Object[] values = (Object[])data.get(measure_names[QUEUE_LENGTH]);
              double[] mValues = (double[])values[0];
              if (end_time == trans_time) {
                // The same case as before
                mValues[4] -= 1; // The previous level
                data.put(measure_names[QUEUE_LENGTH], values);
              } else {
                mValues[0] += mValues[4] * (end_time-mValues[3]); // The level sum
                mValues[1] += end_time-mValues[3]; // The time sum
                if (values.length == 2) {
                  // Exceedence proportions are to be calculated
                  double[] pValues = (double[])values[1];
                  double[] levels = get_levels(measure_names[QUEUE_LENGTH]);
                  for (int i=0; i < levels.length; i++) {
                    if (mValues[4] > levels[i]) {
                      pValues[i] += end_time-mValues[3];
                    }
                  }
                }
                mValues[2] = mValues[3]; // The previous interval start time
                mValues[3] = end_time; // The previous interval end time
                if (mValues[4] < mValues[5]) {
                  mValues[5] = mValues[4]; // The minimum
                }
                if (mValues[4] > mValues[6]) {
                  mValues[6] = mValues[4]; // The maximum
                }
                mValues[4] -= 1; // The previous level
                data.put(measure_names[QUEUE_LENGTH], values);
              }
            } else {
                ((List)observations.get(get_id(measure_names[QUEUE_LENGTH]))).add(new double[] {queue_length--, prev_time_queue, end_time});
                prev_time_queue = end_time;
            }
            // Notify Sim_system
            Sim_system.observation_collected(entity_id, measure_names[QUEUE_LENGTH]);
          }
          if (is_selected(measure_names[WAITING_TIME])) {
            if (is_efficient(measure_names[WAITING_TIME])) {
              Object[] values = (Object[])data.get(measure_names[WAITING_TIME]);
              double[] mValues = (double[])values[0];
              mValues[0] += end_time-start_time; // The interval sum
              mValues[3] += 1;
              if ((end_time-start_time) < mValues[1]) {
                mValues[1] = end_time-start_time; // The minimum
              }
              if ((end_time-start_time) > mValues[2]) {
                mValues[2] = end_time-start_time; // The maximum
              }
              if (values.length == 2) {
                // Exceedence proportions are to be calculated
                double[] pValues = (double[])values[1];
                double[] levels = get_levels(measure_names[WAITING_TIME]);
                for (int i=0; i < levels.length; i++) {
                  if ((end_time-start_time) > levels[i]) {
                    pValues[i] += 1;
                  }
                }
              }
              data.put(measure_names[WAITING_TIME], values);
            } else {
              ((List)observations.get(get_id(measure_names[WAITING_TIME]))).add(new double[] {start_time, end_time});
            }
            // Notify Sim_system
            Sim_system.observation_collected(entity_id, measure_names[WAITING_TIME]);
          }
          break;
        case END_RESIDENCE:
          if (is_selected(measure_names[RESIDENCE_TIME])) {
            if (is_efficient(measure_names[RESIDENCE_TIME])) {
              Object[] values = (Object[])data.get(measure_names[RESIDENCE_TIME]);
              double[] mValues = (double[])values[0];
              mValues[0] += end_time-start_time; // The interval sum
              mValues[3] += 1;
              if ((end_time-start_time) < mValues[1]) {
                mValues[1] = end_time-start_time; // The minimum
              }
              if ((end_time-start_time) > mValues[2]) {
                mValues[2] = end_time-start_time; // The maximum
              }
              if (values.length == 2) {
                // Exceedence proportions are to be calculated
                double[] pValues = (double[])values[1];
                double[] levels = get_levels(measure_names[RESIDENCE_TIME]);
                for (int i=0; i < levels.length; i++) {
                  if ((end_time-start_time) > levels[i]) {
                    pValues[i] += 1;
                  }
                }
              }
              data.put(measure_names[RESIDENCE_TIME], values);
            } else {
              ((List)observations.get(get_id(measure_names[RESIDENCE_TIME]))).add(new double[] {start_time, end_time});
            }
            // Notify Sim_system
            Sim_system.observation_collected(entity_id, measure_names[RESIDENCE_TIME]);
          }
          if (is_selected(measure_names[THROUGHPUT])) {
            if (is_efficient(measure_names[THROUGHPUT])) {
              Long counter = (Long)data.get(measure_names[THROUGHPUT]);
              counter = new Long(counter.longValue()+1);
              data.put(measure_names[THROUGHPUT], counter);
            } else {
              ((List)observations.get(get_id(measure_names[THROUGHPUT]))).add(new Double(end_time));
            }
            // Notify Sim_system
            Sim_system.observation_collected(entity_id, measure_names[THROUGHPUT]);
          }
          break;
        case END_SERVICE:
          if (is_selected(measure_names[SERVICE_TIME])) {
            if (is_efficient(measure_names[SERVICE_TIME])) {
              Object[] values = (Object[])data.get(measure_names[SERVICE_TIME]);
              double[] mValues = (double[])values[0];
              mValues[0] += end_time-start_time; // The interval sum
              mValues[3] += 1;
              if ((end_time-start_time) < mValues[1]) {
                mValues[1] = end_time-start_time; // The minimum
              }
              if ((end_time-start_time) > mValues[2]) {
                mValues[2] = end_time-start_time; // The maximum
              }
              if (values.length == 2) {
                // Exceedence proportions are to be calculated
                double[] pValues = (double[])values[1];
                double[] levels = get_levels(measure_names[SERVICE_TIME]);
                for (int i=0; i < levels.length; i++) {
                  if ((end_time-start_time) > levels[i]) {
                    pValues[i] += 1;
                  }
                }
              }
              data.put(measure_names[SERVICE_TIME], values);
            } else {
              ((List)observations.get(get_id(measure_names[SERVICE_TIME]))).add(new double[] {start_time, end_time});
            }
            // Notify Sim_system
            Sim_system.observation_collected(entity_id, measure_names[SERVICE_TIME]);
          }
          break;
      }
    }
  }

  // Used to update default measures after an END_HOLD event has occurred
  void update(int type, double start_time, double end_time) {
    if (type != END_HOLD) {
      throw new Sim_stat_exception("Invalid event type.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid event occurance times.");
    }
    if (is_selected(measure_names[UTILISATION])) {
      if (is_efficient(measure_names[UTILISATION])) {
        Object[] values = (Object[])data.get(measure_names[UTILISATION]);
        double[] mValues = (double[])values[0];
        if (start_time < trans_time) {
          // To take into account the transient period re-initialisation
          start_time = trans_time;
        }
        mValues[0] += end_time-start_time; // The level sum
        mValues[1] += end_time-mValues[3]; // The time sum
        mValues[2] = start_time; // The previous interval start time
        mValues[3] = end_time; // The previous interval end time
        data.put(measure_names[UTILISATION], values);
        entity_busy = false;
        Sim_system.observation_collected(entity_id, measure_names[UTILISATION]);
        Sim_system.observation_collected(entity_id, measure_names[UTILISATION]);
      } else {
        if (prev_time_util != 0.0) {
          List obs = (List)observations.get(get_id(measure_names[UTILISATION]));
          prev_time_util = ((double[])obs.get(obs.size()-1))[2];
          if (prev_time_util > start_time) {
            // This only occurs with batch means as a variance reduction method. The entity
            // started holding before batch means was applied and did not finish. The time
            // from start_time to prev_time_util has been taken care of.
            start_time = prev_time_util;
          }
        }
        int id = get_id(measure_names[UTILISATION]);
        ((List)observations.get(id)).add(new double[] {0.0, prev_time_util, start_time});
        ((List)observations.get(id)).add(new double[] {1.0, start_time, end_time});
        prev_time_util = end_time;
        entity_busy = false;
        // Notify Sim_system
        Sim_system.observation_collected(entity_id, measure_names[UTILISATION]);
        Sim_system.observation_collected(entity_id, measure_names[UTILISATION]);
      }
    }
  }

  /**
   * Update a custom rate based measure.
   * @param measure      The name of the measure
   * @param time_occurred The occurrence time of the event
   * @throws Sim_stat_exception If an update error occurs. This error can be left unchecked.
   */
  public void update(String measure, double time_occurred) {
    if (!Sim_system.running()) {
      return;
    }
    int id = get_id(measure);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " is not being measured.");
    } else if (get_type(id) != RATE_BASED) {
      throw new Sim_stat_exception(measure + " has an invalid type.");
    } else if (time_occurred > Sim_system.sim_clock()) {
      throw new Sim_stat_exception("Attempted to update " + measure + " past the current run time.");
    }
    if (is_efficient(measure)) {
      Long counter = (Long)data.get(measure);
      counter = new Long(counter.longValue()+1);
      data.put(measure, counter);
    } else {
      ((List)observations.get(id)).add(new Double(time_occurred));
    }
    // Notify Sim_system
    Sim_system.observation_collected(entity_id, measure);
  }

  // Used to update custom STATE-BASED measures which are not continuous
  /**
   * Update a custom non-continuous state based measure.
   * @param measure    The name of the measure
   * @param level      The state of the measure for this update
   * @param start_time The interval's start time
   * @param end_time   The interval's end_time
   * @throws Sim_stat_exception If an update error occurs. This error can be left unchecked.
   */
  public void update(String measure, double level, double start_time, double end_time) {
    if (!Sim_system.running()) {
      return;
    }
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " is not being measured.");
    } else if (type != STATE_BASED) {
      throw new Sim_stat_exception(measure + " has an invalid type.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid period specified.");
    } else if (end_time > Sim_system.sim_clock()) {
      throw new Sim_stat_exception("Attempted to update " + measure + " past the current run time.");
    }
    if (is_efficient(measure)) {
      if (end_time < trans_time) {
        return;
      }
      Object[] values = (Object[])data.get(measure);
      double[] mValues = (double[])values[0];
      if (start_time < trans_time) {
        start_time = trans_time;
      }
      mValues[0] += level * (end_time-start_time); // The level sum
      mValues[1] += end_time-start_time;           // The time sum
      if (values.length == 2) {
        // Exceedence proportions are to be calculated
        double[] pValues = (double[])values[1];
        double[] levels = get_levels(measure);
        for (int i=0; i < levels.length; i++) {
          if (level > levels[i]) {
            pValues[i] += end_time-start_time;
          }
        }
      }
      if (level < mValues[5]) {
        mValues[5] = level;                            // The minimum
      }
      if (level > mValues[6]) {
        mValues[6] = level;                            // The maximum
      }
      data.put(measure, values);
    } else {
      if (end_time < trans_time) {
        return;
      }
      ((List)observations.get(id)).add(new double[] {level, start_time, end_time});
    }
    // Notify Sim_system
    Sim_system.observation_collected(entity_id, measure);
  }

  // Used to update custom STATE-BASED measures which are continuous and custom interval based measures
  /**
   * Update a custom continuous state based or custom interval based measure.
   * @param measure  The name of the measure
   * @param first    If the measure is state based this represents the update's level. If the
   *                 measure is interval based it represents the interval's start time.
   * @param end_time If the measure is state based this represents the time at which the entity
   *                 entered the update's state. If the measure is interval based it represents
   *                 the interval's end time.
   * @throws Sim_stat_exception If an update error occurs. This error can be left unchecked.
   */
  public void update(String measure, double first, double end_time) {
    if (!Sim_system.running()) {
      return;
    }
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " is not being measured.");
    } else if ((type != STATE_BASED) && (type != INTERVAL_BASED)) {
      throw new Sim_stat_exception(measure + " has an invalid type.");
    } else if (end_time > Sim_system.sim_clock()) {
      throw new Sim_stat_exception("Attempted to update " + measure + " past the current run time.");
    }
    if (type == STATE_BASED) {
      // The first argument is the level
      if (is_efficient(measure)) {
        Object[] values = (Object[])data.get(measure);
        double[] mValues = (double[])values[0];
        if (end_time < mValues[3]) {
          throw new Sim_stat_exception("Invalid change time specified.");
        }
        if (end_time < trans_time) {
          return;
        } else if (end_time == trans_time) {
          // The same case as before
          mValues[4] = first;
          data.put(measure, values);
          // Notify Sim_system
          Sim_system.observation_collected(entity_id, measure);
          return;
        }
        mValues[0] += mValues[4] * (end_time-mValues[3]); // The level sum
        mValues[1] += end_time-mValues[3]; // The time sum
        if (values.length == 2) {
          // Exceedence proportions are to be calculated
          double[] pValues = (double[])values[1];
          double[] levels = get_levels(measure);
          for (int i=0; i < levels.length; i++) {
            if (mValues[4] > levels[i]) {
              pValues[i] += end_time-mValues[3];
            }
          }
        }
        if (mValues[4] < mValues[5]) {
          mValues[5] = mValues[4]; // The minimum
        }
        if (mValues[4] > mValues[6]) {
          mValues[6] = mValues[4]; // The maximum
        }
        mValues[2] = mValues[3];
        mValues[3] = end_time;
        mValues[4] = first;
        data.put(measure, values);
      } else {
        Object[] counters = get_counters(measure);
        double prev_level = ((Double)counters[1]).doubleValue();
        double prev_time = ((Double)counters[2]).doubleValue();
        if (end_time < trans_time) {
          return;
        }
        ((List)observations.get(id)).add(new double[] {prev_level, prev_time, end_time});
        set_counters(measure, first, end_time);
      }
    } else {
      // The first argument is the interval's start time
      if ((first < 0.0) || (end_time < 0.0) || (first > end_time)) {
        throw new Sim_stat_exception("Invalid period specified.");
      }
      if (is_efficient(measure)) {
        Object[] values = (Object[])data.get(measure);
        double[] mValues = (double[])values[0];
        if (end_time < trans_time) {
          return;
        }
        mValues[0] += end_time-first; // The interval sum
        mValues[3] += 1;
        if ((end_time-first) < mValues[1]) {
          mValues[1] = end_time-first; // The minimum
        }
        if ((end_time-first) > mValues[2]) {
          mValues[2] = end_time-first; // The maximum
        }
        if (values.length == 2) {
          // Exceedence proportions are to be calculated
          double[] pValues = (double[])values[1];
          double[] levels = get_levels(measure);
          for (int i=0; i < levels.length; i++) {
            if ((end_time-first) > levels[i]) {
              pValues[i] += 1;
            }
          }
        }
        data.put(measure, values);
      } else {
        if (end_time < trans_time) {
          return;
        }
        ((List)observations.get(id)).add(new double[] {first, end_time});
      }
    }
    // Notify Sim_system
    Sim_system.observation_collected(entity_id, measure);
  }

  // Called when the run completes to update the queue length and utilisation with their final values.
  // Also updated are the custom STATE-BASED measures.
  void tidy_up() {
    double clock = Sim_system.sim_clock();
    end_time = clock;
    if (is_selected(measure_names[QUEUE_LENGTH])) {
      if (is_efficient(measure_names[QUEUE_LENGTH])) {
        Object[] values = (Object[])data.get(measure_names[QUEUE_LENGTH]);
        double[] mValues = (double[])values[0];
        if (mValues[3] < clock) {
          mValues[0] += mValues[4] * (clock-mValues[3]); // The level sum
          mValues[1] += clock-mValues[3];                // The time sum
          if (values.length == 2) {
            // Exceedence proportions are to be calculated
            double[] pValues = (double[])values[1];
            double[] levels = get_levels(measure_names[QUEUE_LENGTH]);
            for (int i=0; i < levels.length; i++) {
              if (mValues[4] > levels[i]) {
                pValues[i] += clock-mValues[3];
              }
            }
          }
          mValues[2] = mValues[3];  // The previous interval start time
          mValues[3] = clock;       // The previous interval end time
          if (mValues[4] < mValues[5]) {
            mValues[5] = mValues[4];  // The minimum
          }
          if (mValues[4] > mValues[6]) {
            mValues[6] = mValues[4];  // The maximum
          }
          data.put(measure_names[QUEUE_LENGTH], values);
          // Notify Sim_system
          Sim_system.observation_collected(entity_id, measure_names[QUEUE_LENGTH]);
        }
      } else {
        if (prev_time_queue < clock) {
          ((List)observations.get(get_id(measure_names[QUEUE_LENGTH]))).add(new double[] {queue_length, prev_time_queue, clock});
          prev_time_queue = clock;
          // Notify Sim_system
          Sim_system.observation_collected(entity_id, measure_names[QUEUE_LENGTH]);
        }
      }
    }
    if (is_selected(measure_names[UTILISATION])) {
      int id = get_id(measure_names[UTILISATION]);
      if (entity_busy) {
        if (is_efficient(measure_names[UTILISATION])) {
          Object[] values = (Object[])data.get(measure_names[UTILISATION]);
          double[] mValues = (double[])values[0];
          if (started_busy < trans_time) {
            mValues[0] += clock-trans_time; // The level sum
            mValues[2] = trans_time;        // The previous interval start time
          } else {
            mValues[0] += clock-started_busy; // The level sum
            mValues[2] = started_busy;        // The previous interval start time
          }
          mValues[1] += clock-mValues[3];   // The time sum
          mValues[3] = clock;               // The previous interval end time
          data.put(measure_names[UTILISATION], values);
        } else {
          ((List)observations.get(id)).add(new double[] {0.0, prev_time_util, started_busy});
          ((List)observations.get(id)).add(new double[] {1.0, started_busy, clock});
          prev_time_util = clock;
        }
        // Notify Sim_system
        Sim_system.observation_collected(entity_id, measure_names[UTILISATION]);
        Sim_system.observation_collected(entity_id, measure_names[UTILISATION]);
      } else {
        if (is_efficient(measure_names[UTILISATION])) {
          Object[] values = (Object[])data.get(measure_names[UTILISATION]);
          double[] mValues = (double[])values[0];
          if (mValues[3] < clock) {
            mValues[1] += clock-mValues[3];
            mValues[3] = clock;
            data.put(measure_names[UTILISATION], values);
          }
        } else {
          if (prev_time_util < clock) {
            ((List)observations.get(id)).add(new double[] {0.0, prev_time_util, clock});
          }
        }
        // Notify Sim_system
        Sim_system.observation_collected(entity_id, measure_names[UTILISATION]);
      }
    }
    int measures_size = measures.size();
    for (int i=0; i < measures_size; i++) {
      Object[] next = (Object[])measures.get(i);
      if (next.length == 3) {
        // Its a custom STATE-BASED measure
        if (((Boolean)next[2]).booleanValue()) {
          // Its continuous
          if (is_efficient((String)next[0])) {
            String measure = (String)next[0];
            Object[] values = (Object[])data.get(measure);
            double[] mValues = (double[])values[0];
            if (mValues[3] < clock) {
              mValues[0] += mValues[4] * (clock-mValues[3]); // The level sum
              mValues[1] += clock-mValues[3];                // The time sum
              if (values.length == 2) {
                // Exceedence proportions are to be calculated
                double[] pValues = (double[])values[1];
                double[] levels = get_levels(measure);
                for (int j=0; j < levels.length; j++) {
                  if (mValues[4] > levels[j]) {
                    pValues[j] += clock-mValues[3];
                  }
                }
              }
              if (mValues[4] < mValues[5]) {
                mValues[5] = mValues[4]; // The minimum
              }
              if (mValues[4] > mValues[6]) {
                mValues[6] = mValues[4]; // The maximum
              }
              mValues[2] = mValues[3];
              mValues[3] = clock;
              data.put(measure, values);
            }
          } else {
            int id = get_id((String)next[0]);
            Object[] counters = get_counters((String)next[0]);
            double prev_level = ((Double)counters[1]).doubleValue();
            double prev_time = ((Double)counters[2]).doubleValue();
            if (prev_time < clock) {
              ((List)observations.get(id)).add(new double[] {prev_level, prev_time, clock});
              set_counters((String)next[0], prev_time, clock);
              // Notify Sim_system
              Sim_system.observation_collected(entity_id, (String)next[0]);
            }
          }
        }
      }
    }
  }

  // Used to signal when the entity has started to hold. Used for calculating the tidying-up utilisation
  void set_busy(double time) {
    entity_busy = true;
    started_busy = time;
  }

  // Called by Sim_system to reset the efficient measures' data once the steady state has been reached
  void steady_state_reached(double trans_time) {
    if (data == null) {
      return;
    }
    Set keyset = data.keySet();
    Collection valueset = data.values();
    Iterator keys = keyset.iterator();
    Iterator values = valueset.iterator();
    while (keys.hasNext()) {
      String measure = (String)keys.next();
      int type = get_type(measure);
      if (type == RATE_BASED) {
        Long value = (Long)values.next();
        value = new Long(0L);
        data.put(measure, value);
      } else if (type == STATE_BASED) {
        Object[] value = (Object[])values.next();
        if (value.length == 2) {
          value = new Object[] {new double[] {0, 0, trans_time, trans_time, ((double[])value[0])[4], Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}, new double[get_levels(measure).length]};
        } else {
          value = new Object[] {new double[] {0, 0, trans_time, trans_time, ((double[])value[0])[4], Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}};
        }
        data.put(measure, value);
      } else {
        Object[] value = (Object[])values.next();
        if (value.length == 2) {
          value = new Object[] {new double[] {0, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 0}, new double[get_levels(measure).length]};
        } else {
          value = new Object[] {new double[] {0, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 0}};
        }
        data.put(measure, value);
      }
    }
    this.trans_time = trans_time;
  }

  //  METHODS USED TO CALCULATE MEASUREMENTS FROM THE COLLECTED OBSERVATION

  // Calculation methods for the whole simulation run - default measures

  /**
   * Get the event count for a default rate based measure. In the case of efficient measures,
   * before steady state is reached the count applies to the entire run length up to that point.
   * If steady state has been reached the count applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the count applies to the entire run length.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The event count
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public int count(int measure) {
    if (is_efficient(measure_names[measure])) {
      return count_efficient(measure_names[measure]);
    } else {
      return count(measure_names[measure], 0.0, Sim_system.sim_clock());
    }
  }

  /**
   * Get the sample average for a default measure. In the case of efficient measures,
   * before steady state is reached the average applies to the entire run length up to that point.
   * If steady state has been reached the average applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the average applies to the entire run length.
   * <p>
   * The measure provided may not be the utilisation.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The sample average
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double average(int measure) {
    if (is_efficient(measure_names[measure])) {
      return average_efficient(measure_names[measure]);
    } else {
      return average(measure_names[measure], 0.0, Sim_system.sim_clock());
    }
  }

  /**
   * Get the maximum observation for a default state or interval based measure. In the case of efficient
   * measures, before steady state is reached the maximum applies to the entire run length up to that point.
   * If steady state has been reached the maximum applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the maximum applies to the entire run length.
   * <p>
   * The measure provided may not be the utilisation.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The maximum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double maximum(int measure) {
    if (is_efficient(measure_names[measure])) {
      return maximum_efficient(measure_names[measure]);
    } else {
      return maximum(measure_names[measure], 0.0, Sim_system.sim_clock());
    }
  }

  /**
   * Get the minimum observation for a default state or interval based measure. In the case of efficient
   * measures, before steady state is reached the minimum applies to the entire run length up to that point.
   * If steady state has been reached the minimum applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the minimum applies to the entire run length.
   * <p>
   * The measure provided may not be utilisation.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The minimum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double minimum(int measure) {
    if (is_efficient(measure_names[measure])) {
      return minimum_efficient(measure_names[measure]);
    } else {
      return minimum(measure_names[measure], 0.0, Sim_system.sim_clock());
    }
  }

  /**
   * Get the sample variance for a default state or interval based measure. The variance can't be obtained
   * for a measure when it is defined as efficient. Furthermore, the measure provided may not be the
   * utilisation. The variance applies to the entire run length.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The sample variance
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double variance(int measure) {
    if (is_efficient(measure_names[measure])) {
      throw new Sim_stat_exception(measure_names[measure] + " can't produce variance as an efficient measure.");
    }
    return variance(measure_names[measure], 0.0, Sim_system.sim_clock());
  }

  /**
   * Get the sample standard deviation for a default state or interval based measure. The standard deviation
   * can't be obtained for a measure when it is defined as efficient. Furthermore, the measure provided may
   * not be the utilisation. The standard deviation applies to the entire run length.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The sample standard deviation.
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double std_deviation(int measure) {
    if (is_efficient(measure_names[measure])) {
      throw new Sim_stat_exception(measure_names[measure] + " can't produce standard deviation as an efficient measure.");
    }
    return Math.sqrt(variance(measure_names[measure], 0.0, Sim_system.sim_clock()));
  }

  /**
   * Get the exceedence proportions for arbitrary levels, for a default state or interval based measure.
   * The proportions apply to the entire run length. The measure provided may not be utilisation.
   * @param measure The <code>int</code> constant representing the default measure
   * @param levels  The levels for which exceedence proportions will be calculated
   * @return The set of exceedence proportions
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double[] exc_proportion(int measure, double[] levels) {
    if (is_efficient(measure_names[measure])) {
      throw new Sim_stat_exception(measure_names[measure] + " can't produce exceedence proportions with arbitrary levels as an efficient measure.");
    }
    return exc_proportion(measure_names[measure], levels, 0.0, Sim_system.clock());
  }

  /**
   * Get an exceedence proportion for a default state or interval based measure. The proportion applies
   * to the entire run length. The measure provided may not be the utilisation.
   * @param measure The <code>int</code> constant representing the default measure
   * @param levels  The level for which the exceedence proportion will be calculated
   * @return The exceedence proportion
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double exc_proportion(int measure, double level) {
    if (is_efficient(measure_names[measure])) {
      throw new Sim_stat_exception(measure_names[measure] + " can't produce exceedence proportions with arbitrary levels as an efficient measure.");
    }
    return exc_proportion(measure_names[measure], level, 0.0, Sim_system.sim_clock());
  }

  /**
   * Get the exceedence proportions for the defined levels for a default state or rate based measure.
   * In the case of efficient measures, before steady state is reached the proportions apply to the
   * entire run length up to that point. If steady state has been reached they apply to the steady
   * state up to that point.
   * <p>
   * If the provided measure isn't efficient the average applies to the entire run length.
   * <p>
   * The measure provided may not be the utilisation.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The set of exceedence proportions
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double[] exc_proportion(int measure) {
    double[] levels = get_levels(measure);
    if (levels == null) {
      throw new Sim_stat_exception("No exceedence proportion levels have been specified for " + measure_names[measure]);
    }
    if (is_efficient(measure_names[measure])) {
      return exc_proportion_efficient(measure_names[measure]);
    } else {
      return exc_proportion(measure_names[measure], levels, 0.0, Sim_system.sim_clock());
    }
  }

  // Calculation methods for the whole simulation run - custom measures

  /**
   * Get the event count for a custom rate based measure. In the case of efficient measures,
   * before steady state is reached the count applies to the entire run length up to that point.
   * If steady state has been reached the count applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the count applies to the entire run length.
   * @param measure The name of the custom measure
   * @return The event count
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public int count(String measure) {
    if (is_efficient(measure)) {
      return count_efficient(measure);
    } else {
      return count(measure, 0.0, Sim_system.clock());
    }
  }

  /**
   * Get the sample average for a custom measure. In the case of efficient measures,
   * before steady state is reached the average applies to the entire run length up to that point.
   * If steady state has been reached the average applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the average applies to the entire run length.
   * @param measure The name of the custom measure
   * @return The sample average
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double average(String measure) {
    if (is_efficient(measure)) {
      return average_efficient(measure);
    } else {
      return average(measure, 0.0, Sim_system.sim_clock());
    }
  }

  /**
   * Get the maximum observation for a custom state or interval based measure. In the case of efficient
   * measures, before steady state is reached the maximum applies to the entire run length up to that point.
   * If steady state has been reached the maximum applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the maximum applies to the entire run length.
   * @param measure The name of the custom measure
   * @return The maximum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double maximum(String measure) {
    if (is_efficient(measure)) {
      return maximum_efficient(measure);
    } else {
      return maximum(measure, 0.0, Sim_system.sim_clock());
    }
  }

  /**
   * Get the minimum observation for a custom state or interval based measure. In the case of efficient
   * measures, before steady state is reached the minimum applies to the entire run length up to that point.
   * If steady state has been reached the minimum applies to the steady state up to that point.
   * <p>
   * If the provided measure isn't efficient the minimum applies to the entire run length.
   * @param measure The name of the custom measure
   * @return The minimum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double minimum(String measure) {
    if (is_efficient(measure)) {
      return minimum_efficient(measure);
    } else {
      return minimum(measure, 0.0, Sim_system.sim_clock());
    }
  }

  /**
   * Get the sample variance for a custom state or interval based measure. The variance can't be obtained
   * for a measure when it is defined as efficient. The variance applies to the entire run length.
   * @param measure The name of the custom measure
   * @return The sample variance
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double variance(String measure) {
    if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't produce variance as an efficient measure.");
    }
    return variance(measure, 0.0, Sim_system.sim_clock());
  }

  /**
   * Get the sample standard deviation for a custom state or interval based measure. The standard deviation
   * can't be obtained for a measure when it is defined as efficient. The standard deviation applies to the
   * entire run length.
   * @param measure The name of the ustom measure
   * @return The sample variance
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double std_deviation(String measure) {
    if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't produce standard deviation as an efficient measure.");
    }
    return Math.sqrt(variance(measure, 0.0, Sim_system.sim_clock()));
  }

  /**
   * Get the exceedence proportions for arbitrary levels, for a custom state or interval based measure.
   * The proportions apply to the entire run length.
   * @param measure The name of the custom measure
   * @param levels  The levels for which exceedence proportions will be calculated
   * @return The set of exceedence proportions
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double[] exc_proportion(String measure, double[] levels) {
    if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't produce exceedence proportions with arbitrary levels as an efficient measure.");
    }
    return exc_proportion(measure, levels, 0.0, Sim_system.clock());
  }

  /**
   * Get an exceedence proportion for a custom state or interval based measure. The proportion applies
   * to the entire run length.
   * @param measure The name of the custom measure
   * @param levels  The level for which the exceedence proportion will be calculated
   * @return The exceedence proportion
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double exc_proportion(String measure, double level) {
    if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't produce exceedence proportions with arbitrary levels as an efficient measure.");
    }
    return exc_proportion(measure, level, 0.0, Sim_system.sim_clock());
  }

  /**
   * Get the exceedence proportions for the defined levels for a custom state or rate based measure.
   * In the case of efficient measures, before steady state is reached the proportions apply to the
   * entire run length up to that point. If steady state has been reached they apply to the steady
   * state up to that point.
   * <p>
   * If the provided measure isn't efficient the average applies to the entire run length.
   * @param measure The name of the custom measure
   * @return The set of exceedence proportions
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double[] exc_proportion(String measure) {
    double[] levels = get_levels(measure);
    if (levels == null) {
      throw new Sim_stat_exception("No exceedence proportion levels have been specified for " + measure);
    }
    if (is_efficient(measure)) {
      return exc_proportion_efficient(measure);
    } else {
      return exc_proportion(measure, levels, 0.0, Sim_system.sim_clock());
    }
  }

  // Calculation methods for a period of time - default measures

  /**
   * Get the event count for a default rate based measure, applying to a specific time interval.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The event count
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public int count(int measure, double start_time, double end_time) {
    return count(measure_names[measure], start_time, end_time);
  }

  /**
   * Get the sample average for a default measure, applying to a specific time interval.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The sample average
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double average(int measure, double start_time, double end_time) {
    return average(measure_names[measure], start_time, end_time);
  }

  /**
   * Get the maximum observation for a default state or interval based measure, applying to a specific time interval.
   * The provided measure may not be the utilisation.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The maximum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double maximum(int measure, double start_time, double end_time) {
    return maximum(measure_names[measure], start_time, end_time);
  }

  /**
   * Get the minimum observation for a default state or interval based measure, applying to a specific time interval.
   * The provided measure may not be the utilisation.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The minimum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double minimum(int measure, double start_time, double end_time) {
    return minimum(measure_names[measure], start_time, end_time);
  }

  /**
   * Get the sample variance for a default state or interval based measure, applying to a specific time interval.
   * The provided measure may not be the utilisation.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The sample variance observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double variance(int measure, double start_time, double end_time) {
    return variance(measure_names[measure], start_time, end_time);
  }

  /**
   * Get the sample standard deviation for a default state or interval based measure, applying to a specific
   * time interval. The provided measure may not be the utilisation.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The sample standard deviation observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double std_deviation(int measure, double start_time, double end_time) {
    return std_deviation(measure_names[measure], start_time, end_time);
  }

  /**
   * Get the exceedence proportions for a set of levels, for a default state or interval based measure,
   * applying to a specific time interval. The provided measure may not be the utilisation.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param levels     The set of levels for which exceedence proportions will be calculated
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The set of exceedence proportions
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double[] exc_proportion(int measure, double[] levels, double start_time, double end_time) {
    return exc_proportion(measure_names[measure], levels, start_time, end_time);
  }

  /**
   * Get an exceedence proportion for a specific level, for a default state or interval based measure,
   * applying to a specific time interval. The provided measure may not be the utilisation.
   * @param measure    The <code>int</code> constant representing the default measure
   * @param level      The level for which the exceedence proportion will be calculated
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The exceedence proportion
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double exc_proportion(int measure, double level, double start_time, double end_time) {
    return exc_proportion(measure_names[measure], level, start_time, end_time);
  }

  // Calculation methods for a period of time - custom measures

  /**
   * Get the event count for a custom rate based measure, applying to a specific time interval.
   * @param measure    The name of the custom measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The event count
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public int count(String measure, double start_time, double end_time) {
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " has not been measured for this entity.");
    } else if (type != RATE_BASED) {
      throw new Sim_stat_exception(measure + " must be a rate-based measure.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid period specified.");
    } else if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't be an efficient measure and be used with this method.");
    }
    int[] indices = observation_count(id, start_time, end_time);
    if (indices[1] == -1) {
      return 0;
    }
    return (indices[1]-indices[0])+1;
  }

  /**
   * Get the sample average for a custom measure, applying to a specific time interval.
   * @param measure    The name of the custom measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The sample average
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double average(String measure, double start_time, double end_time) {
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " has not been measured for this entity.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid period specified.");
    } else if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't be an efficient measure and be used with this method.");
    }
    double result;
    if (type == RATE_BASED) {
      // Rate-based measures
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      if ((end_time - start_time) == 0.0) {
        result = 0.0;
      } else {
        result = ((indices[1]-indices[0])+1)/(end_time-start_time);
      }
    } else if (type == STATE_BASED) {
      // State-based measures
      Object[] measure_array = (Object[])measures.get(id);
      boolean continuous = true;
      if (measure_array.length == 3) {
        // The measure is custom
        continuous = ((Boolean)measure_array[2]).booleanValue();
      }
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double[] first = (double[])data.get(indices[0]);
      double sum;
      double time_period = 0.0;
      if (first[1] < start_time) {
        sum = first[0]*(first[2]-start_time);
        time_period += first[2]-start_time;
      } else {
        sum = first[0]*(first[2]-first[1]);
        time_period += first[2]-first[1];
      }
      for (int i=indices[0]+1; i < indices[1]; i++) {
        double[] next = (double[])data.get(i);
        sum += next[0]*(next[2]-next[1]);
        time_period += (next[2]-next[1]);
      }
      double[] last = (double[])data.get(indices[1]);
      if (continuous) {
        sum += last[0]*(end_time-last[1]);
        time_period += end_time-last[1];
      } else {
        if (last[2] > end_time) {
          sum += last[0]*(end_time-last[1]);
          time_period += end_time-last[1];
        } else {
          sum += last[0]*(last[2]-last[1]);
          time_period += last[2]-last[1];
        }
      }
      if (time_period == 0.0) {
        result = 0.0;
      } else {
        result = sum/time_period;
      }
    } else {
      // Interval-based measures
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double sum = 0.0;
      for (int i=indices[0]; i <= indices[1]; i++) {
        double[] next = (double[])data.get(i);
        sum += next[1]-next[0];
      }
      if (((indices[1]-indices[0])+1) == 0) {
        result = 0.0;
      } else {
        result = sum/((indices[1]-indices[0])+1);
      }
    }
    return result;
  }

  /**
   * Get the maximum observation for a custom state or interval based measure, applying to a specific time interval.
   * @param measure    The name of the custom measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The maximum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double maximum(String measure, double start_time, double end_time) {
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " has not been measured for this entity.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid period specified.");
    } else if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || measure.equals("Utilisation")) {
      throw new Sim_stat_exception("Invalid measure type. Must be state-based or interval-based.");
    } else if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't be an efficient measure and be used with this method.");
    }
    double result = 0.0;
    if (type == STATE_BASED) {
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      for (int i = indices[0]; i <= indices[1]; i++) {
        double[] next = (double[])data.get(i);
        if (next[0] > result) {
          result = next[0];
        }
      }
    } else {
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      for (int i = indices[0]; i <= indices[1]; i++) {
        double[] next = (double[])data.get(i);
        if ((next[1]-next[0]) > result) {
          result = next[1]-next[0];
        }
      }
    }
    return result;
  }

  /**
   * Get the minimum observation for a custom state or interval based measure, applying to a specific time interval.
   * @param measure    The name of the custom measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The minimum observation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double minimum(String measure, double start_time, double end_time) {
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " has not been measured for this entity.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid period specified.");
    } else if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || measure.equals("Utilisation")) {
      throw new Sim_stat_exception("Invalid measure type. Must be state-based or interval-based.");
    } else if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't be an efficient measure and be used with this method.");
    }
    double result;
    if (type == STATE_BASED) {
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double[] first = (double[])data.get(indices[0]);
      result = first[0];
      for (int i = indices[0]+1; i <= indices[1]; i++) {
        double[] next = (double[])data.get(i);
        if (next[0] < result) {
          result = next[0];
        }
      }
    } else {
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double[] first = (double[])data.get(indices[0]);
      result = first[1]-first[0];
      for (int i = indices[0]+1; i <= indices[1]; i++) {
        double[] next = (double[])data.get(i);
        if ((next[1]-next[0]) < result) {
          result = next[1]-next[0];
        }
      }
    }
    return result;
  }

  /**
   * Get the sample variance for a custom state or interval based measure, applying to a specific time interval.
   * @param measure    The name of the custom measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The sample variance
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double variance(String measure, double start_time, double end_time) {
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " has not been measured for this entity.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid period specified.");
    } else if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || measure.equals("Utilisation")) {
      throw new Sim_stat_exception("Invalid measure type. Must be state-based or interval-based.");
    } else if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't be an efficient measure and be used with this method.");
    }
    double result;
    if (type == STATE_BASED) {
      Object[] measure_array = (Object[])measures.get(id);
      boolean continuous = true;
      if (measure_array.length == 3) {
        // The measure is custom
        continuous = ((Boolean)measure_array[2]).booleanValue();
      }
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double[] first = (double[])data.get(indices[0]);
      double sum;
      double time_period = 0.0;
      if (first[1] < start_time) {
        sum = Math.pow(first[0], 2.0)*(first[2]-start_time);
        time_period += first[2]-start_time;
      } else {
        sum = Math.pow(first[0], 2.0)*(first[2]-first[1]);
        time_period += first[2]-first[1];
      }
      for (int i=indices[0]+1; i < indices[1]; i++) {
        double[] next = (double[])data.get(i);
        sum += Math.pow(next[0], 2.0)*(next[2]-next[1]);
        time_period += next[2]-next[1];
      }
      double[] last = (double[])data.get(indices[1]);
      if (continuous) {
        sum += Math.pow(last[0], 2.0)*(end_time-last[1]);
        time_period += end_time-last[1];
      } else {
        if (last[2] > end_time) {
          sum += Math.pow(last[0], 2.0)*(end_time-last[1]);
          time_period += end_time-last[1];
        } else {
          sum += Math.pow(last[0], 2.0)*(last[2]-last[1]);
          time_period += last[2]-last[1];
        }
      }
      if (time_period == 0.0) {
        result = 0.0;
      } else {
        result = (sum/time_period) - Math.pow(average(measure, start_time, end_time), 2.0);
      }
    } else {
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double sum = 0.0;
      for (int i=indices[0]; i <= indices[1]; i++) {
        double[] next = (double[])data.get(i);
        sum += Math.pow(next[1]-next[0], 2.0);
      }
      double mean = ((indices[1]-indices[0])+1) * Math.pow(average(measure, start_time, end_time), 2.0);
      if ((indices[1]-indices[0]) == 0) {
        result = 0.0;
      } else {
        result = (sum-mean) / (indices[1]-indices[0]);
      }
    }
    return result;
  }

  /**
   * Get the sample standard deviation for a custom state or interval based measure, applying to a specific
   * time interval.
   * @param measure    The name of the custom measure
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The sample standard deviation
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double std_deviation(String measure, double start_time, double end_time) {
    return Math.sqrt(variance(measure, start_time, end_time));
  }

  /**
   * Get the exceedence proportions for a set of levels, for a custom state or interval based measure,
   * applying to a specific time interval.
   * @param measure    The name of the custom measure
   * @param levels     The set of levels for which exceedence proportions will be calculated
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The set of exceedence proportions
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double[] exc_proportion(String measure, double[] levels, double start_time, double end_time) {
    int id = get_id(measure);
    int type = get_type(id);
    if (id < 0) {
      throw new Sim_stat_exception(measure + " has not been measured for this entity.");
    } else if ((start_time < 0.0) || (end_time < 0.0) || (start_time > end_time)) {
      throw new Sim_stat_exception("Invalid period specified.");
    } else if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || measure.equals("Utilisation")) {
      throw new Sim_stat_exception("Invalid measure type. Must be state-based or interval-based.");
    } else if (is_efficient(measure)) {
      throw new Sim_stat_exception(measure + " can't be an efficient measure and be used with this method.");
    }
    double[] result = new double[levels.length];
    for (int i=0; i < levels.length; i++) {
      result[i] = exc_proportion(measure, levels[i], start_time, end_time);
    }
    return result;
  }

  /**
   * Get an exceedence proportions for a specific level, for a custom state or interval based measure,
   * applying to a specific time interval.
   * @param measure    The name of the custom measure
   * @param level      The level for which the exceedence proportion will be calculated
   * @param start_time The interval's start time
   * @param end_time   The interval's end time
   * @return The exceedence proportion
   * @throws Sim_stat_exception If there is an error in the passed parameters. This error can be left unchecked.
   */
  public double exc_proportion(String measure, double level, double start_time, double end_time) {
    int id = get_id(measure);
    int type = get_type(id);
    double result;
    if (type == STATE_BASED) {
    // The proportion of time that the STATE-BASED measure exceeded "level"
      Object[] measure_array = (Object[])measures.get(id);
      boolean continuous = true;
      if (measure_array.length == 3) {
        // The measure is custom
        continuous = ((Boolean)measure_array[2]).booleanValue();
      }
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double[] first = (double[])data.get(indices[0]);
      double sum = 0.0;
      double time_period = 0.0;
      if (first[1] < start_time) {
        time_period += first[2]-start_time;
        if (first[0] > level) {
          sum = first[2]-start_time;
        }
      } else {
        time_period += first[2]-first[1];
        if (first[0] > level) {
          sum = first[2]-first[1];
        }
      }
      for (int i=indices[0]+1; i < indices[1]; i++) {
        double[] next = (double[])data.get(i);
        time_period += next[2]-next[1];
        if (next[0] > level) {
          sum += next[2]-next[1];
        }
      }
      double[] last = (double[])data.get(indices[1]);
      if (continuous) {
        time_period += end_time-last[1];
        if (last[0] > level) {
          sum += end_time-last[1];
        }
      } else {
        if (last[2] > end_time) {
          time_period += end_time-last[1];
          if (last[0] > level) {
            sum += end_time-last[1];
          }
        } else {
          time_period += last[2]-last[1];
          if (last[0] > level) {
            sum += last[2]-last[1];
          }
        }
      }
      if (time_period == 0.0) {
        result = 0.0;
      } else {
        result = sum/time_period;
      }
    } else {
    // The proportion of jobs for which an interval measure exceeded a threshold
      int[] indices = observation_count(id, start_time, end_time);
      if (indices[1] == -1) {
        return 0.0;
      }
      List data = (List)observations.get(id);
      double sum = 0.0;
      for (int i=indices[0]; i <= indices[1]; i++) {
        double[] next = (double[])data.get(i);
        if ((next[1]-next[0]) > level) {
          sum++;
        }
      }
      if (((indices[1]-indices[0])+1) == 0) {
        result = 0.0;
      } else {
        result = sum/((indices[1]-indices[0])+1);
      }
    }
    return result;
  }

  // Methods for efficient measure measurements

  // The event count
  private int count_efficient(String measure) {
    int type = get_type(measure);
    if (type != RATE_BASED) {
      throw new Sim_stat_exception(measure + " must be a rate-based measure.");
    }
    return (int)(((Long)data.get(measure)).longValue());
  }

  // The sample average
  private double average_efficient(String measure) {
    int type = get_type(measure);
    if (type == RATE_BASED) {
      if (trans_time < 0.0) {
        trans_time = 0.0;
      }
      if (end_time < 0.0) {
        // Method called at run time
        return ((double)((Long)data.get(measure)).longValue())/(Sim_system.sim_clock()-trans_time);
      } else {
        // Method called after run has completed
        return ((double)((Long)data.get(measure)).longValue())/(end_time-trans_time);
      }
    } else if (type == STATE_BASED) {
      double[] values = (double[])((Object[])data.get(measure))[0];
      if (values[1] == 0.0) {
        return 0.0;
      } else {
        return values[0]/values[1];
      }
    } else {
      double[] values = (double[])((Object[])data.get(measure))[0];
      if (values[3] == 0.0) {
        return 0.0;
      } else {
        return values[0]/values[3];
      }
    }
  }

  // The maximum observation
  private double maximum_efficient(String measure) {
    int type = get_type(measure);
    if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || measure.equals("Utilisation")) {
      throw new Sim_stat_exception("Invalid measure type. Must be state-based or interval-based.");
    }
    double max;
    if (type == STATE_BASED) {
      max = ((double[])((Object[])data.get(measure))[0])[6];
    } else {
      max = ((double[])((Object[])data.get(measure))[0])[2];
    }
    if (max == Double.NEGATIVE_INFINITY) {
      return 0.0;
    } else {
      return max;
    }
  }

  // The minimum observation
  private double minimum_efficient(String measure) {
    int type = get_type(measure);
    if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || measure.equals("Utilisation")) {
      throw new Sim_stat_exception("Invalid measure type. Must be state-based or interval-based.");
    }
    double min;
    if (type == STATE_BASED) {
      min = ((double[])((Object[])data.get(measure))[0])[5];
    } else {
      min = ((double[])((Object[])data.get(measure))[0])[1];
    }
    if (min == Double.POSITIVE_INFINITY) {
      return 0.0;
    } else {
      return min;
    }
  }

  // The defined exceedence proportions
  private double[] exc_proportion_efficient(String measure) {
    int type = get_type(measure);
    if (((type != STATE_BASED) && (type != INTERVAL_BASED)) || measure.equals("Utilisation")) {
      throw new Sim_stat_exception("Invalid measure type. Must be state-based or interval-based.");
    }
    if (type == STATE_BASED) {
      double[] values = ((double[])((Object[])data.get(measure))[1]);
      double time = ((double[])((Object[])data.get(measure))[0])[1];
      double[] result = new double[values.length];
      for (int i=0; i < values.length; i++) {
        if (time == 0.0) {
          result[i] = 0.0;
        } else {
          result[i] = values[i]/time;
        }
      }
      return result;
    } else {
      double[] counters = ((double[])((Object[])data.get(measure))[1]);
      double total = ((double[])((Object[])data.get(measure))[0])[3];
      double[] result = new double[counters.length];
      for (int i=0; i < counters.length; i++) {
        if (total == 0.0) {
          result[i] = 0.0;
        } else {
          result[i] = counters[i]/total;
        }
      }
      return result;
    }
  }


  // UTILITY METHODS, GETTERS, SETTERS

  // Returns [i,j] where i and j are the first and last indices of the observations vector
  // to be included in the measurement. Used by providing the measure's name.
  int[] observation_count(String measure_name, double start_time, double end_time) {
    return observation_count(get_id(measure_name), start_time, end_time);
  }

  // Returns [i,j] where i and j are the first and last indices of the observations vector
  // to be included in the measurement. Used by providing the measure's id.
  int[] observation_count(int measure_id, double start_time, double end_time) {
    List data = (List)observations.get(measure_id);
    double time;
    int type = get_type(measure_id);
    int data_size = data.size();
    int low_index = 0,
        high_index = data_size-1;
    Object[] measure_array = (Object[])measures.get(measure_id);
    boolean continuous = true;
    if (measure_array.length == 3) {
      // The measure is custom
      continuous = ((Boolean)measure_array[2]).booleanValue();
    }
    // Find low index
    for (int i=0; i < data_size; i++) {
      Object next = data.get(i);
      if (type == RATE_BASED) {
        if (((Double)next).doubleValue() >= start_time) {
          low_index = i;
          break;
        }
      } else if (type == STATE_BASED) {
        if (((double[])next)[2] > start_time) {
          low_index = i;
          break;
        }
      } else {
        if (((double[])next)[1] >= start_time) {
          low_index = i;
          break;
        }
      }
    }
    // Find high index
    for (int i=low_index; i < data_size; i++) {
      Object next = data.get(i);
      if (type == RATE_BASED) {
        time = ((Double)next).doubleValue();
        if (time > end_time) {
          if (i == low_index) {
            high_index = i;
          } else {
            high_index = i-1;
          }
          break;
        }
      } else if (type == STATE_BASED) {
        if ((((double[])next)[1] <= end_time) && (((double[])next)[2] >= end_time)) {
          high_index = i;
          break;
        } else if ((((double[])next)[1] >= end_time) && (((double[])next)[2] > end_time)) {
          if (i == low_index) {
            high_index = i;
          } else {
            high_index = i-1;
          }
          break;
        }
      } else {
        if (((double[])next)[1] > end_time) {
          if (i == low_index) {
            high_index = i;
          } else {
            high_index = i-1;
          }
          break;
        }
      }
    }
    return new int[] {low_index, high_index};
  }

  /**
   * Get all the data contained in the <code>Sim_stat</code> object. The data is a two sized
   * array of <code>Lists</code>. The first element of the array contains the measures' information:
   * <p>
   * <code>List {Object[] {String measure_name, Integer measure_type [, Boolean is_continuous]}, ... }</code>
   * <p>
   * The second element contains the measures' observations:
   * <ul>
   *   <li>
   *     <code>List {Double occurrence_time, Double occurrence_time, ... }</code> - for rate based measures
   *   <li>
   *     <code>List {double[] {state, start_time, end_time}, double[] {state, start_time, end_time}, ... }</code> - for state based measures
   *   <li>
   *     <code>List {double[] {start_time, end_time}, double[] {start_time, end_time}, ... }</code> - for interval based measures
   * </ul>
   * @return The <code>Sim_stat</code>'s data as described above
   */
  public List[] get_data() {
    return new List[] {measures, observations};
  }

  /**
   * Get the information for the measures defined in the <code>Sim_stat</code> object.
   * <p>
   * The information returned in the following form:
   * <p>
   * <code>List {Object[] {String measure_name, Integer measure_type [, Boolean is_continuous]}, ... }</code>
   * @return The <code>Sim_stat</code>'s measure information as described above
   */
  public List get_measures() {
    return measures;
  }

  /**
   * Get the information for the non-efficient measures defined in the <code>Sim_stat</code> object.
   * <p>
   * The information is returned in the following form:
   * <p>
   * <code>List {Object[] {String measure_name, Integer measure_type [, Boolean is_continuous]}, ... }</code>
   * @return The <code>Sim_stat</code>'s measure information as described above
   */
  public List get_detailed_measures() {
    List result = new ArrayList();
    int measures_size = measures.size();
    for (int i=0; i < measures_size; i++) {
      Object[] mData = (Object[])measures.get(i);
      if (!is_efficient((String)mData[0])) {
        result.add(mData);
      }
    }
    return result;
  }

  /**
   * Check to see whether a default measure is specified to be efficient.
   * @param measure The <code>int</code> constant representing the default measure
   * @return <code>true</code> if the measure is efficient, <code>false</code> otherwise
   */
  public boolean is_efficient(int measure) {
    if (data == null) {
      return false;
    } else {
      return data.containsKey(measure_names[measure]);
    }
  }

  /**
   * Check to see whether a custom measure is specified to be efficient.
   * @param measure The name of the measure
   * @return <code>true</code> if the measure is efficient, <code>false</code> otherwise
   */
  public boolean is_efficient(String measure) {
    if (data == null) {
      return false;
    } else {
      return data.containsKey(measure);
    }
  }

  /**
   * Get the exceedence proportion levels defined for a default measure.
   * @param measure The <code>int</code> constant representing the default measure
   * @return The set of levels or <code>null</code> if none have been specified
   */
  public double[] get_levels(int measure) {
    return get_levels(measure_names[measure]);
  }

  /**
   * Get the exceedence proportion levels defined for a custom measure.
   * @param measure The name of the custom measure
   * @return The set of levels or <code>null</code> if none have been specified
   */
  public double[] get_levels(String measure) {
    int levels_size = levels.size();
    for (int i=0; i < levels_size; i++) {
      Object[] next = (Object[])levels.get(i);
      if (((String)next[0]).equals(measure)) {
        return (double[])next[1];
      }
    }
    return null;
  }

  // Checks to see whether the given measure has been defined or not
  private boolean is_selected(String measure) {
    int measures_size = measures.size();
    for (int i=0; i < measures_size; i++) {
      Object[] next = (Object[])measures.get(i);
      if (((String)next[0]).equals(measure)) {
        return true;
      }
    }
    return false;
  }

  // Gets the id - index of the given measure
  private int get_id(String measure_name) {
    int measures_size = measures.size();
    for (int i=0; i < measures_size; i++) {
      Object[] next = (Object[])measures.get(i);
      if (((String)next[0]).equals(measure_name)) {
        return i;
      }
    }
    return -1;
  }

  // Gets the type of the measure with the given id
  private int get_type(int measure) {
    if (measure == -1) {
      return -1;
    }
    return ((Integer)((Object[])measures.get(measure))[1]).intValue();
  }

  // Used by other classes to get a measure's type
  int get_type(String measure) {
    return get_type(get_id(measure));
  }

  // Gets the name of the measure with the given id
  private String get_name(int measure) {
    return (String)((Object[])measures.get(measure))[0];
  }

  // Gets the name of the given default measure
  String get_name_default(int measure) {
    return measure_names[measure];
  }

  // Gets the counters for a custom STATE_BASED measure
  private Object[] get_counters(String measure) {
    int counters_size = counters.size();
    for (int i=0; i < counters_size; i++) {
      Object[] next = (Object[])counters.get(i);
      if (((String)next[0]).equals(measure)) {
        return next;
      }
    }
    return null;
  }

  // Replaces the counters for a custom STATE_BASED measure
  private void set_counters(String measure, double level, double time) {
    int counters_size = counters.size();
    for (int i=0; i < counters_size; i++) {
      Object[] next = (Object[])counters.get(i);
      if (((String)next[0]).equals(measure)) {
        counters.remove(i);
        counters.add(new Object[] {measure, new Double(level), new Double(time)});
        break;
      }
    }
  }

  // Returns the time length of the transient period if the min-max method is being used.
  double min_max_time(String measure) {
    int id = get_id(measure);
    int type = get_type(id);
    double result = -1.0;
    double current_value;
    double[] next, other;
    boolean found_other_min = false;
    boolean found_other_max = false;
    boolean found_steady_state = false;
    List data = (List)observations.get(id);
    int data_size = data.size();
    for (int i=1; i < data_size-1; i++) {
      next = (double[])data.get(i);
      if (type == STATE_BASED) {
        current_value = next[0];
        for (int j=i+1; j < data_size; j++) {
          other = (double[])data.get(j);
          if (other[0] < current_value) {
            found_other_min = true;
          } else if (other[0] > current_value) {
            found_other_max = true;
          }
          if (found_other_min && found_other_max) {
            found_steady_state = true;
            break;
          }
        }
        if (found_steady_state) {
          result = next[1];
          break;
        } else {
          found_other_min = false;
          found_other_max = false;
        }
      } else {
        current_value = next[1]-next[0];
        for (int j=i+1; j < data_size; j++) {
          other = (double[])data.get(j);
          if ((other[1]-other[0]) < current_value) {
            found_other_min = true;
          } else if ((other[1]-other[0]) > current_value) {
            found_other_max = true;
          }
          if (found_other_min && found_other_max) {
            found_steady_state = true;
            break;
          }
        }
        if (found_steady_state) {
          result = next[0];
          break;
        } else {
          found_other_min = false;
          found_other_max = false;
        }
      }
    }
    return result;
  }

  // Used to reset the Sim_stat instance to its exact state before observations were recorded
  void reset() {
    queue_length = 0;
    prev_time_queue = 0.0;
    prev_time_util = 0.0;
    entity_busy = false;
    observations.clear();
    counters.clear();
    int measures_size = measures.size();
    for (int i=0; i < measures_size; i++) {
      observations.add(new ArrayList());
    }
    int init_counters_size = init_counters.size();
    for (int i=0; i < init_counters_size; i++) {
      Object[] counter = (Object[])init_counters.get(i);
      counters.add(new Object[] {counter[0], counter[1], counter[2]});
    }
    if (data != null) {
      Set efficient_measures_set = data.keySet();
      Iterator efficient_measures = efficient_measures_set.iterator();
      int data_size = data.size();
      String[] measures = new String[data_size];
      for (int i=0; i < data_size; i++) {
        measures[i] = (String)efficient_measures.next();
      }
      data.clear();
      for (int i=0; i < measures.length; i++) {
        set_efficient(measures[i]);
      }
    }
    end_time = -1.0;
    trans_time = -1.0;
  }

  //  METHODS FOR CLONING

  // Returns a copy of the Sim_stat object
  Sim_stat get_stat_copy() {
    Sim_stat stat = new Sim_stat();
    stat.set_entity_info(entity_id, entity_name);
    stat.set_measures((List)((ArrayList)measures).clone());
    stat.set_observations((List)((ArrayList)observations).clone());
    stat.set_levels((List)((ArrayList)levels).clone());
    stat.set_tags((List)((ArrayList)tags).clone());
    if (data != null) stat.set_data((HashMap)data.clone());
    stat.set_end_time(end_time);
    stat.set_trans_time(trans_time);
    return stat;
  }

  void set_measures(List measures) { this.measures = measures; }
  void set_observations(List observations) { this.observations = observations; }
  void set_levels(List levels) { this.levels = levels; }
  void set_tags(List tags) { this.tags = tags; }
  void set_data(HashMap data) { this.data = data; }
  void set_end_time(double end_time) { this.end_time = end_time; }
  void set_trans_time(double trans_time) { this.trans_time = trans_time; }

  //  METHODS USED BY THE GRAPH VIEWER

  /**
   * Get a series of sample averages, as well as the minimum and maximum observation for a measure
   * based for a set of time values. This method is used by the <code>SimJava Graph Viewer</code> in
   * order to efficiently calculate a set of sample averages. The form of the data obtained is:
   * <p>
   * <code>Object[] {double[] averages, Double min, Double max}</code>
   * <p>
   * This method should <b>not</b> be used in simulations.
   * @param measure The name of the measure
   * @param times   The set of time values
   * @return        The averages, the minimum and the maximum in the form describde above
   */
  public Object[] averages(String measure, double[] times) {
    int id = get_id(measure);
    int type = get_type(id);
    double[] values = new double[times.length];
    List data = (List)observations.get(id);
    double start_time = times[0];
    double end_time = times[times.length-1];
    double min, max;
    int data_size = data.size();

    if (type == RATE_BASED) {
      // Rate-based measures
      values[0] = 0.0;
      min = 0.0;
      max = 0.0;
      int index = 0;
      double next;
      for (int i=0; i < data_size; i++) {
        next = ((Double)data.get(i)).doubleValue();
        if (next >= times[0]) {
          index = i;
          break;
        }
      }
      int sum = 0;
      for (int i=1; i < times.length; i++) {
        while ((index < data_size) &&
               (((next = ((Double)data.get(index)).doubleValue())) <= times[i])) {
          index++;
          sum++;
        }
        values[i] = sum / (times[i]-times[0]);
        if (values[i] < min) {
          min = values[i];
        }
        if (values[i] > max) {
          max = values[i];
        }
      }
    } else if (type == STATE_BASED) {
      // State-based measures
      values[0] = 0.0;
      min = 0.0;
      max = 0.0;
      int index = 0;
      double prev_sum_level = 0.0;
      double sum_level = 0.0;
      double prev_sum_time = 0.0;
      double sum_time = 0.0;
      double[] next = null;
      for (int i=0; i < data_size; i++) {
        next = (double[])data.get(i);
        if (next[2] > times[0]) {
          index = i;
          if (next[1] < start_time) {
            index++;
            sum_level = next[0]*(next[2]-times[0]);
            sum_time = next[2]-times[0];
          }
          break;
        }
      }
      for (int i=1; i < times.length; i++) {
        while ((index < data_size) && (((double[])data.get(index))[1] < times[i])) {
          next = (double[])data.get(index++);
          prev_sum_time = sum_time;
          prev_sum_level = sum_level;
          sum_level += next[0]*(next[2]-next[1]);
          sum_time += next[2]-next[1];
        }
        if (index == 0) {
          values[i] = 0.0;
        } else {
          if (next[2] > times[i]) {
            values[i] = (prev_sum_level + next[0]*(times[i]-next[1])) / (prev_sum_time + (times[i]-next[1]));
          } else {
            values[i] = sum_level / sum_time;
          }
        }
        if (values[i] < min) {
          min = values[i];
        }
        if (values[i] > max) {
          max = values[i];
        }
      }
    } else {
      // Interval-based measures
      values[0] = 0.0;
      min = 0.0;
      max = 0.0;
      int index = 0;
      double sum = 0.0;
      double[] next;
      for (int i=0; i < data_size; i++) {
        next = (double[])data.get(i);
        if (next[1] >= times[0]) {
          index = i;
          break;
        }
      }
      int count = 0;
      for (int i=1; i < times.length; i++) {
        while ((index < data_size) && (((double[])data.get(index))[1] <= times[i])) {
          next = (double[])data.get(index++);
          sum += next[1]-next[0];
          count++;
        }
        if (count == 0) {
          values[i] = 0.0;
        } else {
          values[i] = sum / count;
        }
        if (values[i] < min) {
          min = values[i];
        }
        if (values[i] > max) {
          max = values[i];
        }
      }
    }
    return new Object[] {values, new Double(min), new Double(max)};
  }

  /**
   * Add an annotation to a measure's graph. This method is used by the <code>SimJava Graph Viewer</code>
   * to make annotations. It should <b>not</b> be used in simulations.
   * @param measure    The name of the measure
   * @param annotation The text, and time and value pair of the annotation
   */
  public void addAnnotation(String measure, Object[] annotation) {
    if (annotations == null) {
      annotations = new ArrayList();
      List annotationList = new ArrayList();
      annotationList.add(annotation);
      annotations.add(new Object[] {measure, annotationList});
    } else {
      int annotations_size = annotations.size();
      for (int i=0; i < annotations_size; i++) {
        Object[] aData = (Object[])annotations.get(i);
        if (((String)aData[0]).equals(measure)) {
          ((List)aData[1]).add(annotation);
          return;
        }
      }
      List annotationList = new ArrayList();
      annotationList.add(annotation);
      annotations.add(new Object[] {measure, annotationList});
    }
  }

  /**
   * Get a measure's graph annotations. This method is used by the <code>SimJava Graph Viewer</code> to
   * retrieve a graph's annotations. It should <b>not</b> be used in simulations.
   * @param measure The name of the measure
   * @returm        A <code>List</code> containing the graph's annotations
   */
  public List getAnnotations(String measure) {
    if (annotations != null) {
      int annotations_size = annotations.size();
      for (int i=0; i < annotations_size; i++) {
        Object[] aData = (Object[])annotations.get(i);
        if (((String)aData[0]).equals(measure)) {
          return (List)aData[1];
        }
      }
    }
    return null;
  }

  /**
   * Get the number of detailed measures defined in this <code>Sim_stat</code> object.
   * @return The number of defined detailed (non-efficient) measures
   */
  public int detailed_measure_count() {
    if (data == null) {
      return measures.size();
    } else {
      return measures.size()-data.size();
    }
  }
}







