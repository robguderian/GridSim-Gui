/* Anim_port.java */

package eduni.simanim;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;

/**
 * An animation port, used to store display information about the port for the animation.
 * @version     1.0, 4 September
 * @author      Ross McNab
 */
public class Anim_port {
  private String name;     // The name of this port (used to id it)
  private int side, pos;   // Our position relative to our parent ent
  private Point ppos;      // Our actual x,y position
  private Point lpos;      // the x,y position of our out link
  private int w,h;         // Our width and height
  private Anim_port link;  // The other port it is linked to (if any)
  private Anim_entity parent;
  private boolean primary; // If linked, which one of the two is the primary?
  private double msg_pos;  // Where on the wire the message is
  private String data;     // The data the message is holding
  private Image image;

  /** Attatch the port to the left side of the parent entity */
  public static final int LEFT   = 0;
  /** Attatch the port to the right side of the parent entity */
  public static final int RIGHT  = 1;
  /** Attatch the port to the top side of the parent entity */
  public static final int TOP    = 2;
  /** Attatch the port to the bottom side of the parent entity */
  public static final int BOTTOM = 3;
  public static final String[] enum_side = {
    "LEFT", "RIGHT", "TOP", "BOTTOM"
  };


  //
  // The EDL and EDF interface
  //

  // Constructor
  /**
   * This constructor should not be used directly, use the extended
   * constructor in <code>Sim_port</code> instead.
   */
  public Anim_port(String name, String imagename) {
    this.name = name;
    side = RIGHT;  pos = 0;
    link = null;
    parent = null;
    primary = false;
    msg_pos = -1.0;
    data = new String();
    this.image = Sim_anim.get_image(imagename);
    w = this.image.getWidth(null);
    h = this.image.getHeight(null);
  }

  /**
   * This method should not be used directly.
   */
  public void set_position(int side, int pos) {
    this.side = side;  this.pos = pos;
    if((side < 0) || (side > 3)) {
      System.out.println("Error: Anim_port.set_position() - invalid side "+side );
      this.side = RIGHT; // Default to right
    }
    update_position();
  }

  //
  // The package interface
  //

  // Convert the string 'side' to the enum value (or -1 if not in enum)
  static int parseSide(String side) {
    for(int i=0; i<enum_side.length; i++)
      if(enum_side[i].equals(side)) return i;
    return(-1);
  }

  void update_position() {
    Dimension pd;
    Point     pp;

    if(parent != null) {
      pd = parent.get_size();
      pp = parent.get_position();
      switch(side) {
        case(LEFT):
          ppos = new Point(pp.x-w, pp.y+pos);
          lpos = new Point(ppos.x, ppos.y+h/2);
          break;
        case(RIGHT):
          ppos = new Point(pp.x+pd.width, pp.y+pos);
          lpos = new Point(ppos.x+w, ppos.y+h/2);
          break;
        case(TOP):
          ppos = new Point(pp.x+pos, pp.y-w);
          lpos = new Point(ppos.x+w/2, ppos.y);
          break;
        case(BOTTOM):
          ppos = new Point(pp.x+pos, pp.y+pd.height);
          lpos = new Point(ppos.x+w/2, ppos.y+h);
          break;
      }
    }
  }


  void link_port(Anim_port link, boolean primary) {
    this.link = link;
    this.primary = primary;
  }

  String get_name() { return(name); }
  void move_msg(double pos) { msg_pos = pos; }
  void set_data(String data) { this.data = data; }
  void set_parent(Anim_entity parent) { this.parent = parent; }
  Anim_entity get_parent() { return parent; }
  Point get_link_pos() { return lpos; }
  Point get_port_pos() { return ppos; }

  /** This method should not be used directly */
  public void draw_messages(Graphics g) {
    Point rpos;
    int mx, my;
    if(link != null) {
      rpos = link.get_link_pos();
      if(msg_pos >= 0.0) {
        mx = (int)((1.0 - msg_pos)*lpos.x + msg_pos*rpos.x);
        my = (int)((1.0 - msg_pos)*lpos.y + msg_pos*rpos.y);
        g.setColor(Color.blue);
        g.fillRect(mx, my, 5, 5);
        g.setColor(Color.black);
        g.drawString(data, mx, my);
      }
    }
  }

  void draw(Graphics g) {
    Point rpos;
    // draw me
    g.drawImage(image,ppos.x,ppos.y,null);
    if(link != null) {
      rpos = link.get_link_pos();
      Anim_entity rent  = link.get_parent();
      if (rent!=null) { 
	if (!rent.is_invisible()) {
	  if(primary) {
	    g.setColor(Color.blue);
	    g.drawLine(lpos.x, lpos.y, rpos.x, rpos.y);
	  }
	}
      }
    }
  }

}

