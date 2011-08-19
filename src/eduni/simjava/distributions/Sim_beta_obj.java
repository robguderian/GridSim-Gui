/* Sim_beta_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the Beta distribution.
 * @version     1.0, 14 May 2002
 * @author      Costas Simatos
 */

public class Sim_beta_obj implements ContinuousGenerator {
  private Sim_random_obj source;
  private double shape_a, shape_b;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param shape_a The a shape parameter of the distribution
   * @param shape_b The b shape parameter of the distribution
   */
  public Sim_beta_obj(String name, double shape_a, double shape_b) {
    if ((shape_a <= 0.0) || (shape_b <= 0.0)) {
      throw new Sim_parameter_exception("Sim_beta_obj: The shape parameters must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.shape_a = shape_a;
    this.shape_b = shape_b;
    this.name = name;
  }

  /**
   * The constructor with which a specific seed is set for the random
   * number generator
   * @param name The name to be associated with this instance
   * @param shape_a The a shape parameter of the distribution
   * @param shape_b The b shape parameter of the distribution
   * @param seed The initial seed for the generator, two instances with
   *             the same seed will generate the same sequence of numbers
   */
  public Sim_beta_obj(String name, double shape_a, double shape_b, long seed) {
    if ((shape_a <= 0.0) || (shape_b <= 0.0)) {
      throw new Sim_parameter_exception("Sim_beta_obj: The shape parameters must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.shape_a = shape_a;
    this.shape_b = shape_b;
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public double sample() {
    double result;
    if (shape_a == 1.0) {
      result = 1.0-Math.pow(source.sample(), 1.0/shape_b);
    } else if (shape_b == 1.0) {
      result = Math.pow(source.sample(), 1.0/shape_a);
    } else if ((shape_a > 1.0) || (shape_b > 1.0)) {
      // Cheng's method (1978)
      double alpha = shape_a + shape_b;
      double beta;
      double min = Math.min(shape_a, shape_b);
      if (min <= 1.0) {
        beta = 1.0/min;
      } else {
        beta = Math.sqrt((alpha - 2.0)/(2.0*shape_a*shape_b - alpha));
      }
      double gamma = shape_a + 1.0/shape_b;
      double u1, u2, u3, v, w, el1, el2;
      do {
        u1 = source.sample();
        u2 = source.sample();
        u3 = source.sample();
        v = beta*Math.log(u1/(1.0-u1));
        w = alpha*Math.exp(v);
        el1 = alpha*Math.log(alpha/(beta+w)) + gamma*v - Math.log(4.0);
        el2 = Math.log(u1*u2*u3);
      } while(el1 < el2);
      result = w/(beta+w);
    } else {
      // Berman's method (1970)
      double u, v, x, y;
      do {
        u = source.sample();
        v = source.sample();
        x = Math.pow(u, 1.0/shape_a);
        y = Math.pow(v, 1.0/shape_b);
      } while((x + y) > 1.0);
      result = x/(x+y);
    }
    return result;
  }

  // Used by other distributions that rely on the Beta distribution
  static double sample(Sim_random_obj source, double shape_a, double shape_b) {
    double result;
    if (shape_a == 1.0) {
      result = 1.0-Math.pow(source.sample(), 1.0/shape_b);
    } else if (shape_b == 1.0) {
      result = Math.pow(source.sample(), 1.0/shape_a);
    } else if ((shape_a > 1.0) || (shape_b > 1.0)) {
      // Cheng's method (1978)
      double alpha = shape_a + shape_b;
      double beta;
      double min = Math.min(shape_a, shape_b);
      if (min <= 1.0) {
        beta = 1.0/min;
      } else {
        beta = Math.sqrt((alpha - 2.0)/(2.0*shape_a*shape_b - alpha));
      }
      double gamma = shape_a + 1.0/shape_b;
      double u1, u2, u3, v, w, el1, el2;
      do {
        u1 = source.sample();
        u2 = source.sample();
        u3 = source.sample();
        v = beta*Math.log(u1/(1.0-u1));
        w = alpha*Math.exp(v);
        el1 = alpha*Math.log(alpha/(beta+w)) + gamma*v - Math.log(4.0);
        el2 = Math.log(u1*u2*u3);
      } while(el1 < el2);
      result = w/(beta+w);
    } else {
      // Berman's method (1970)
      double u, v, x, y;
      do {
        u = source.sample();
        v = source.sample();
        x = Math.pow(u, 1.0/shape_a);
        y = Math.pow(v, 1.0/shape_b);
      } while((x + y) > 1.0);
      result = x/(x+y);
    }
    return result;
  }


  /**
   * Set the random number generator's seed.
   * @param seed The new seed for the generator
   */
  public void set_seed(long seed) {
    source.set_seed(seed);
  }

  /**
   * Get the random number generator's seed.
   * @return The generator's seed
   */
  public long get_seed() {
    return source.get_seed();
  }

  /**
   * Get the random number generator's name.
   * @return The generator's name
   */
  public String get_name() {
    return name;
  }

}
