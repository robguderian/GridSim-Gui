/* Sim_uniform_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the Uniform distribution.
 * @version     1.0, 13 May 2002
 * @author      Costas Simatos
 */

public class Sim_uniform_obj implements ContinuousGenerator {
  private Sim_random_obj source;
  private double mag, min;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param min  The minimum value this instance should generate
   * @param max  The maximum value this instance should generate
   */
  public Sim_uniform_obj(String name, double min, double max) {
    if (min >= max) {
      throw new Sim_parameter_exception("Sim_uniform_obj: The maximum must be greater than the minimum.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.mag = max - min;
    this.min = min;
    this.name = name;
  }

  /**
   * The constructor with which a specific seed is set for the random
   * number generator
   * @param name The name to be associated with this instance
   * @param min  The minimum value this instance should generate
   * @param max  The maximum value this instance should generate
   * @param seed The initial seed for the generator, two instances with
   *             the same seed will generate the same sequence of numbers
   */
  public Sim_uniform_obj(String name, double min, double max, long seed) {
    if (min >= max) {
      throw new Sim_parameter_exception("Sim_uniform_obj: The maximum must be greater than the minimum.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.mag = max - min;
    this.min = min;
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public double sample() {
    return mag * source.sample() + min;
  }

  // Used by other distributions that rely on the Uniform distribution
  static double sample(Sim_random_obj source, double min, double max) {
    return (max-min) * source.sample() + min;
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
