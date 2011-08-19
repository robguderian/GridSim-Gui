/* Param_type_list.java */

package eduni.simanim;

import java.util.List;
import java.util.ArrayList;

/**
 * A list of all distinct parameter types
 * @version     1.0, 19 June 1997
 * @author      Fred Howell
 */
class Param_type_list {
  List ptypes;
  Param_type_list() {   ptypes = new ArrayList();  }
  /** This method should not be used directly. */
  public void reset() { ptypes.clear(); }
  /** This method should not be used directly. */
  public void add(Param_type p) {
    System.out.println("Adding "+p.getType());
    boolean is_distinct = true;
    int ptypes_size = ptypes.size();
    for (int i=0; (i<ptypes_size) && is_distinct; i++) {
      Param_type pt = (Param_type)ptypes.get(i);
      if (pt.getType().equals(p.getType()))
	  is_distinct = false;
    }
    if (is_distinct) {
      ptypes.add(p);
      System.out.println("Really Adding "+p.getType());
    }
  }
  /** This method should not be used directly. */
  public List getV() { return ptypes; }
}
