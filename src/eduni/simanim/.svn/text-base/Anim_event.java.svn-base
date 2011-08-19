/* Anim_event.java */

package eduni.simanim;

import java.util.StringTokenizer;

/**
 * This class holds an animation event.
 * @version     1.0, 4 September
 * @author      Ross McNab
 */
class Anim_event {
  // The different types of anim event
  static final int SEND   = 0;
  static final int PARAM  = 1;
  static final int COMMENT  = 2;

  double timestamp;     // When the event should occur
  int type;             // one of the types above
  StringBuffer data;    // the mysterious data (if any)
  Anim_entity src_ent;  // Who sent the event
  Anim_port   src_port; // The port it relates to (if any)

  // The constructor, it gets passed the trace for the event
  Anim_event(String s, Sim_anim anim) {
    StringTokenizer st;

    st = new StringTokenizer(s, ": \n\r\t");
    if(st.nextToken().charAt(0) != 'u')
      System.out.println("Error: Anim_event - I only do 'u:' traces");
    src_ent = anim.find_entity(st.nextToken());
    st.nextToken(); // skip 'at'
    timestamp = (Double.valueOf(st.nextToken())).doubleValue();
    switch(st.nextToken().charAt(0)) {
      case('S'):
        type = SEND;
        src_port = src_ent.find_port(st.nextToken());
        data = new StringBuffer();
        while(st.hasMoreTokens())
          data.append(" " + st.nextToken());
        break;
      case('P'):
        type = PARAM;
        src_port = null;
        data = new StringBuffer();
        while(st.hasMoreTokens())
          data.append(" " + st.nextToken());
        break;
      case('C'):
	type = COMMENT;
	break;
      default:
        System.out.println("Error: Anim_event - Unrecognised event type\n\t"+s);
        break;
    }
  }
}
