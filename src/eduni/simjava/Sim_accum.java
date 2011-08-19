/* Sim_accum.java */

package eduni.simjava;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for collecting basic statistical data during simulations.
 * <p>
 * This class can be used to manually calculate measurements of
 * interest for state-based, non-continuous measures (see the
 * <a href="http://www.dcs.ed.ac.uk/home/simjava/tutorial/index.html#6">SimJava Tutorial</a> for details).
 * This is a primitive class that is still present in the API for compatibility with existing
 * simulations. Simulations written with the SimJava version 2.0 should make use of <code>Sim_stat</code>
 * instances for their entities if statistics are required.
 * @see         eduni.simjava.Sim_stat
 * @version     1.0, 4 September 1996
 * @version     1.1, 29 May 1997 fwh corrected update() bug.
 * @author      Ross McNab
 */
public class Sim_accum {
  private List intervals;
  private List values;

  /**
   * Allocate a new instance of the class.
   */
  public Sim_accum() {
    intervals = new ArrayList();
    values = new ArrayList();
  }

  /**
   * Allocate a new, named, instance of the class. The name is
   * supplied for <code>SIM++</code> compatibility.
   * @param name The name to be associated with the instance (currently ignored)
   */
  public Sim_accum(String name) {     // For SIM++ compatibility
    new Sim_accum();
  }

  /**
   * Add a new record to the statistics collected so far.
   * @param interval How long the value was held
   * @param value    The value to record
   */
  public void update(double interval, double value) {
    intervals.add(new Double(interval));
    values.add(new Double(value));
  }

  /**
   * Find the minimum value recorded so far
   * @return The minimum value recorded so far or 0.0 if no values have been recorded
   */
  public double min(){
    double ret, val;
    if (intervals.size() == 0)
      ret = 0.0;
    else {
      ret = ((Double)values.get(0)).doubleValue();
      int values_size = values.size();
      for (int i=0; i < values_size; i++) {
        val = ((Double)values.get(i)).doubleValue();
        if (val<ret) ret = val;
      }
    }
    return ret;
  }


  /**
   * Find the maximum value recorded so far
   * @return The maximum value recorded so far or 0.0 if no values have been recorded
   */
  public double max() {
    double ret, val;
    if (intervals.size() == 0)
      ret = 0.0;
    else {
      ret = ((Double)values.get(0)).doubleValue();
      int values_size = values.size();
      for (int i=0; i < values_size; i++) {
        val = ((Double)values.get(i)).doubleValue();
        if (val>ret) ret = val;
      }
    }
    return ret;
  }

  /**
   * Calculates the average value held over the total
   * interval recorded.
   * i.e. <code>Sum_for_all_i(value[i]*interval[i])/total_interval</code>
   * @return The average value
   */
  public double avg() {
    double ret, val, inter;
    if (intervals.size() == 0)
      ret = 0.0;
    else {
      ret = 0.0;
      int intervals_size = intervals.size();
      for (int i=0; i < intervals_size; i++) {
        val = ((Double)values.get(i)).doubleValue();
        inter = ((Double)intervals.get(i)).doubleValue();
        ret += val * inter;
      }
      ret = ret / interval_sum();
    }
    return ret;
  }

  /**
   * Sums all the intervals recorded so far.
   * @return The sum of the intervals
   */
  public double interval_sum() {
    double ret, val;
    if (intervals.size() == 0)
      ret = 0.0;
    else {
      ret = 0.0;
      int intervals_size = intervals.size();
      for (int i=0; i < intervals_size; i++) {
        val = ((Double)intervals.get(i)).doubleValue();
        ret += val;
      }
    }
    return ret;
  }
}

