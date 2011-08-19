/* Sim_port.java */

package eduni.simjava;

import eduni.simanim.Anim_port;

/**
 * This class represents ports which are used to connect entities for event passing.
 * @see         Sim_system
 * @see         Sim_entity
 * @version     1.0, 4 September 1996
 * @author      Ross McNab
 */
public class Sim_port {

  // Private data members
  private String pname;      // The port's name
  private String dest_ename; // The destination entity's name
  private int srce;          // Index of the source entity
  private int deste;         // Index of the destination entity
  private Anim_port aport;

  //
  // Public library interface
  //

  /**
   * Constructor for standalone simulations.
   * @param port_name The name with which to identify this port
   */
  public Sim_port(String port_name) {
    pname = port_name;
    dest_ename = null;
    srce = -1;
    deste = -1;
    aport = null;
  }

  /**
   * Constructor for use with the <code>eduni.simanim</code> package for animations.
   * @param port_name The name with which to identify this port
   * @param image_name The name of the gif graphics file to use for this port's
   *                   icon, (without the ".gif" extension)
   * @param side Which side of the parent entity the port should be drawn on,
   *             one of <code>Anim_port.LEFT</code>, <code>Anim_port.RIGHT</code>, <code>Anim_port.TOP</code>, or
   *             <code>Anim_port.BOTTOM</code>
   * @param pos How many pixels along that side the port should be drawn
   */
  public Sim_port(String port_name, String image_name, int side, int pos) {
    pname = port_name;
    dest_ename = null;
    srce = -1;
    deste = -1;
    // Now anim stuff
    aport = new Anim_port(port_name, image_name);
    aport.set_position(side, pos);
  }

  // Public access methods

  /**
   * Get the name of the destination entity of this port.
   * @return The name of the entity
   */
  public String get_dest_ename() { return dest_ename; }

  /**
   * Get the name of this port.
   * @return The name
   */
  public String get_pname() { return pname; }

  /**
   * Get the unique id number of the destination entity of this port.
   * @return The id number
   */
  public int get_dest() { return deste; }

  /**
   * Get the unique id number of the source entity of this port.
   * @return The id number
   */
  public int get_src() { return srce; }

  //
  // Package level interface
  //

  Anim_port get_aport() { return aport; }
  void set_src(int s) { srce = s; }
  void connect(Sim_entity dest) {
    dest_ename = dest.get_name();
    deste = dest.get_id();
  }
}
