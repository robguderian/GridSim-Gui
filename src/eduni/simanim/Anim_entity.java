/* Anim_entity.java */

package eduni.simanim;

import eduni.simjava.Sim_system;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;

/**
 * An animation entity, used to store display information about an entity for the animation.
 * @version     1.0, 4 September
 * @author      Ross McNab
 */

public class Anim_entity {
  private String name;
  private int x, y;
  private int w, h;
  private List ports;
  private List params;
  private Image image = null;
  private String base_img_name;
  private boolean invisible = false;

  /**
   * This constuctor should not be used directly, use the extended
   * constructor in <code>Sim_entity</code> instead.
   */
  public Anim_entity(String name, String base_img) {
    this.name = name;
    this.image = Sim_anim.get_image(base_img);
    this.base_img_name = base_img;
    ports = new ArrayList();
    params = new ArrayList();
    this.set_position(0,0);
  }

  /**
   * This method should not be used directly.
   */
  public void add_port(Anim_port port) {
    ports.add(port);
    port.set_parent(this);
    port.update_position();
  }

  /**
   * This method should not be used directly.
   */
  public void add_param(Anim_param param) {
    if(param.get_type() == Anim_param.STATE) {
      this.image = Sim_anim.get_image(base_img_name+"."+param.get_value());
      this.set_position(x,y);
    }
    params.add(param);
    param.set_parent(this);
    param.update_position();
    // Add to paramlist...
    Sim_anim a = (Sim_anim)Sim_system.get_trcout();
    Param_type pt = param.get_ptype();
    if (pt != null) a.add_param_type(pt);
  }

  /**
   * This method should not be used directly.
   */
  public void set_position(int x, int y) {
    this.x = x;  this.y = y;
    if(image != null) {
      w = this.image.getWidth(null);
      h = this.image.getHeight(null);
    }
    // now tell all the ports we've moved
    int ports_size = ports.size();
    for (int i=0; i < ports_size; i++) {
      ((Anim_port)ports.get(i)).update_position();
    }

    // now tell all the params we've moved
    int params_size = params.size();
    for (int i=0; i < params_size; i++) {
      ((Anim_param)params.get(i)).update_position();
    }
  }

  //
  // The package interface
  //

  // Update our internal parameters from the passed string
  void set_params(String param_string) {
    Anim_param param;
    StringTokenizer st;
    st = new StringTokenizer(param_string);
    int params_size = params.size();
    for (int i=0; i < params_size; i++) {
      param = (Anim_param)params.get(i);
      if (!st.hasMoreTokens())
        System.out.println("Error: Anim_entity.set_params - not enought tokens");
      if (param.get_type() == Anim_param.STATE) {
        param.set_value(st.nextToken());
        this.image = Sim_anim.get_image(base_img_name+"."+param.get_value());
        this.set_position(x,y);
      } else {
        param.set_value(st.nextToken());
      }
    }    
  }

  // Get state bar specifier in timing diagram format
  // Only does 1st. If no state, returns null.
  String get_bar_string() {
    int params_size = params.size();
    for (int i=0; i < params_size; i++) {
      Param_type p = ((Anim_param)params.get(i)).get_ptype();
      if (p!=null) return new String(name+" "+p.getType());
    }
    return null;
  }

  void draw_messages(Graphics g) {
    // Call draw_messages(g) on all my ports
    int ports_size = ports.size();
    for (int i=0; i < ports_size; i++) {
       ((Anim_port)ports.get(i)).draw_messages(g);
    }
  }

  void draw(Graphics g) {
    // Draw me
    if (!invisible) {
      g.setColor(Color.lightGray);
      g.fillRect(x,y,w,h);
      g.drawImage(image,x,y,null);
      // Call draw on my ports
      int ports_size = ports.size();
      for (int i=0; i < ports_size; i++) {
	((Anim_port)ports.get(i)).draw(g);
      }
      // Call draw on my parameters
      int params_size = params.size();
      for (int i=0; i < params_size; i++) {
	((Anim_param)params.get(i)).draw(g);
      }
    }
  }

  // Some package access functions
  String get_name() { return(name); }
  Dimension get_size() { return new Dimension(w, h); }
  Point get_position() { return new Point(x, y); }
  Point get_end_position() { return new Point(x+w, y+h); }

  /** This method should not be used directly. */
  public void set_invisible(boolean b) { invisible = b; }
  /** This method should not be used directly. */
  public boolean is_invisible() { return invisible; }

  // If we have a port called port_name then return it
  Anim_port find_port(String port_name) {
    Anim_port port;
    int ports_size = ports.size();
    for (int i=0; i < ports_size; i++) {
      port = (Anim_port)ports.get(i);
      if(port.get_name().equals(port_name)) return port;
    }
    return null;
  }
}
