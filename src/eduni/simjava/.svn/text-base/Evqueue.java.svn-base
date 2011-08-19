/* Evqueue.java */

package eduni.simjava;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class implements the event queue used by <code>Sim_system</code>.
 * <p>
 * The event queue is separated into two subqueues, the future queue and
 * the deferred queue. Newly arrived events are added to the future queue
 * while events that the receiving entity is unable to process are placed
 * in the deferred queue. The current implementation uses a Vector to store
 * the events received.
 * <p>
 * This class is used internally by <code>Sim_system</code> and should not be
 * directly accessed.
 * @see         eduni.simjava.Sim_system
 * @version     0.1, 25 June 1995
 * @author      Ross McNab
 */
public class Evqueue extends LinkedList {

  double max_time = -1.0;

  // Constructors
  /**
   * Allocates a new Evqueue object.
   */
  public Evqueue() {
    super();
  }

  /**
   * Remove and return the event at the top of the queue.
   * @return           The next event.
   */
  public Sim_event pop() {
    return (Sim_event)removeFirst();
  }

  /**
   * Return the event at the top of the queue, without removing it.
   * @return	The next event.
   */
  public Sim_event top() {
    return (Sim_event)getFirst();
  }

  /**
   * Add a new event to the queue. Adding a new event to the queue preserves the
   * temporal order of the events in the queue.
   * @param new_event	The event to be put on the queue.
   */
  public void add_event(Sim_event new_event) {
    double new_event_time = new_event.event_time();
    if (new_event_time >= max_time) {
      add(new_event);
      max_time = new_event_time;
      return;
    }
    ListIterator iterator = listIterator();
    Sim_event event;
    while (iterator.hasNext()) {
      event = (Sim_event)iterator.next();
      if (event.event_time() > new_event_time) {
        iterator.previous();
        iterator.add(new_event);
        return;
      }
    }
    add(new_event);
  }
}
