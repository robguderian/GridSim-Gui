/* Anim_param.java */

package eduni.simanim;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

/**
 * An animation parameter, parameters are used to display
 * data relating to its parent entity during an animation.
 * @version     1.0, 4 September
 * @version     1.2, fwh, 19 June 1997 - added Param_type
 * @author      Ross McNab, Fred Howell
 */
public class Anim_param {
  private Anim_entity parent;
  private int type;		// Animation mode
  private Param_type ptype;	// Parameter type
  private int ival;		// Integer value
  private String old_value;
  private String value;
  private String name;
  private Point parpos;
  private Point relpos;

  /** Display type: Don't display. */
  public static final int HIDDEN     = 0;
  /** Display type: Show as the entity's bitmap. */
  public static final int STATE      = 1;
  /** Display type: Show value only. */
  public static final int VALUE      = 2;
  /** Display type: Show parameter's name only. */
  public static final int NAME       = 3;
  /** Display type: Show both the parameter's name and its value. */
  public static final int NAME_VALUE = 4;
  public static final String[] enum_type = {
    "HIDDEN", "STATE", "VALUE", "NAME", "NAME_VALUE"
  };

  /**
   * Constructor, with (x, y) co-ordinate.
   * @param name The parameter's name
   * @param type One of the display types above
   * @param value The initial value of the parameter
   * @param x The X co-ordinate of the parameter relative to the left of
   *          its parent entity.
   * @param y The Y co-ordinate of the parameter relative to the top of
   *          its parent entity.
   */
  public Anim_param(String name, int type, String value, int x, int y) {
    this.type = type;    this.name = name;    this.value = value;
    old_value = value;    relpos = new Point(x,y);
    ival = 0;
  }

  /**
   * Constructor, with co-ordinates defaulting to (0, 0).
   * @param name The parameter's name
   * @param type One of the display types above
   * @param value The initial value of the parameter
   */
  public Anim_param(String name, int type, String value) {
    this.type = type;    this.name = name;    this.value = value;   
    old_value = value;
    relpos = new Point(0,0);
    ival = 0;
  }

  /**
   * Constructor, with Param_type object and (x, y) co-ordinate.
   * @param name The parameter's name
   * @param type One of the display types above
   * @param ptype The parameter type object
   * @param x The X co-ordinate of the parameter relative to the left of
   *          its parent entity.
   * @param y The Y co-ordinate of the parameter relative to the top of
   *          its parent entity.
   */
  public Anim_param(String name, int type, Param_type ptype, int x, int y) {
    this.type = type;    this.name = name;    
    relpos = new Point(x,y);
    this.ptype = ptype;
    String[] pvals = ptype.getVals();
    String value;
    if (pvals.length>0) value = pvals[0]; else value = "0";
    this.value = value;
    old_value = value;        
    ival = 0;
  }

  /**
   * Constructor, with co-ordinates defaulting to (0, 0).
   * @param name The parameter's name
   * @param type One of the display types above
   * @param value The initial value of the parameter
   */
  public Anim_param(String name, int type, Param_type ptype) {
    this.type = type;    this.name = name;
    relpos = new Point(0,0);
    this.ptype = ptype;
    String[] pvals = ptype.getVals();
    String value;
    if (pvals.length>0) value = pvals[0]; else value = "0";
    this.value = value;
    old_value = value;   
    ival = 0;
  }

  /**
   * Set value of parameter to string.
   * @param val The value of the parameter
   */
  public void set_value(String val) { 
    old_value = value; value = val; 
    if (ptype!=null) { ival = ptype.getIndex(val); }
  }

  /**
   * Set value of parameter to integer.
   * Note that it sets the value to the ith string value.
   * @param val The value of the parameter
   */
  public void set_value(int val) { 
    old_value = value; 
    if (ptype!=null) if (ptype.getVals().length>val)
      { value = ptype.getVals()[val]; }
    ival = val;
  }

  /** Get string value of parameter. */
  public String get_value() { return(value); }

  /** Get integer value of parameter. */
  public int get_intval() { return(ival); }

  void set_type(int type) { this.type = type; }
  void set_position(int x, int y) { relpos = new Point(x,y); }
  void set_parent(Anim_entity ent) { parent = ent; }

  int get_type() { return(type); }
  Param_type get_ptype() { return(ptype); }
  String get_name() { return(name); }

  // Convert the string 'type' to the enum value (or -1 if not in enum)
  static int parseType(String type) {
    for(int i=0; i<enum_type.length; i++)
      if(enum_type[i].equals(type)) return i;
    return(-1);
  }

  void update_position() {
    parpos = parent.get_position();
  }

  /*
  void drawString(Graphics g, String s, int x, int y) {
    FontMetrics fm = Sim_anim.applet.getFontMetrics(g.getFont());
    if (fm == null) System.out.println("Could not get font metrics");
    int a = fm.getMaxAscent();
    int d = fm.getMaxDescent();
    g.setColor(Color.lightGray);
    g.fillRect(x,y-a,fm.stringWidth(s), a+d);
    g.setColor(Color.black);
    g.drawString(s, x, y);
  }
  */

  void draw(Graphics g) {
    switch(type) {
      case(HIDDEN):
        break;
      case(STATE):
        break;
      case(VALUE):
        g.setColor(Color.lightGray);
        g.drawString(old_value, parpos.x+relpos.x, parpos.y+relpos.y);
        g.setColor(Color.black);
        g.drawString(value, parpos.x+relpos.x, parpos.y+relpos.y);
        break;
      case(NAME):
	g.setColor(Color.black); 
        g.drawString(name, parpos.x+relpos.x, parpos.y+relpos.y);
        break;
      case(NAME_VALUE):
        g.setColor(Color.lightGray);
        g.drawString(name+" = "+old_value, parpos.x+relpos.x, parpos.y+relpos.y);
        g.setColor(Color.black);
        g.drawString(name+" = "+value, parpos.x+relpos.x, parpos.y+relpos.y);
        break;
    }
  }
}
