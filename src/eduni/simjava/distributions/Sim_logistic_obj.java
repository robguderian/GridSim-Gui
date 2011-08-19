/* Sim_logistic_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the Logistic distribution.
 * @version     1.0, 14 May 2002
 * @author      Costas Simatos
 */

public class Sim_logistic_obj implements ContinuousGenerator {
  private Sim_random_obj source;
  private double location, scale;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param location The location of the distribution
   * @param scale The scale of the distribution
   */
  public Sim_logistic_obj(String name, double location, double scale) {
    if (scale <= 0.0) {
      throw new Sim_parameter_exception("Sim_logistic_obj: The scale parameter must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.location = location;
    this.scale = scale;
    this.name = name;
  }

  /**
   * The constructor with which a specific seed is set for the random
   * number generator
   * @param name The name to be associated with this instance
   * @param location The location of the distribution
   * @param scale The scale of the distribution
   * @param seed The initial seed for the generator, two instances with
   *             the same seed will generate the same sequence of numbers
   */
  public Sim_logistic_obj(String name, double location, double scale, long seed) {
    if (scale <= 0.0) {
      throw new Sim_parameter_exception("Sim_logistic_obj: The scale parameter must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.location = location;
    this.scale = scale;
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public double sample() {
    return location-scale*Math.log((1/source.sample())-1);
  }

  // Used by other distributions that rely on the Logistic distribution
  static double sample(Sim_random_obj source, double location, double scale) {
    return location-scale*Math.log((1/source.sample())-1);
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
