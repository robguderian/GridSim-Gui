package eduni.simdiag;

import java.awt.Frame;

/** Timing diagram in a separate window */
public class TimingWindow extends Thread {
  Frame f;
  TimingDiagram td;
 
  public TimingWindow() { 
    f = new Frame("Timing Diagram");
    td = new TimingDiagram();
  }
  public void run() {
    // td.init();
    f.add(td);
    f.pack();
    f.setVisible(true);
  }
  public TimingDiagram getDiag() { return td; }
}
