/* Sim_lognorma_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the Lognormal distribution.
 * @version     1.0, 14 May 2002
 * @author      Costas Simatos
 */

public class Sim_lognormal_obj implements ContinuousGenerator {
  private Sim_random_obj source;
  private double mean, std_dev;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param mean The mean of the distribution
   * @param variance The variance of the distribution
   */
  public Sim_lognormal_obj(String name, double mean, double variance) {
    if ((mean <= 0.0) || (variance <= 0.0)) {
      throw new Sim_parameter_exception("Sim_lognormal_obj: The mean and variance must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.mean = mean;
    std_dev = Math.sqrt(variance);
    this.name = name;
  }

  /**
   * The constructor with which a specific seed is set for the random
   * number generator
   * @param name The name to be associated with this instance
   * @param mean The mean of the distribution
   * @param variance The variance of the distribution
   * @param seed The initial seed for the generator, two instances with
   *             the same seed will generate the same sequence of numbers
   */
  public Sim_lognormal_obj(String name, double mean, double variance, long seed) {
    if ((mean <= 0.0) || (variance <= 0.0)) {
      throw new Sim_parameter_exception("Sim_lognormal_obj: The mean and variance must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.mean = mean;
    std_dev = Math.sqrt(variance);
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public double sample() {
    return Math.exp(mean+std_dev*Sim_normal_obj.sample(source, 0.0, 1.0));
  }

  // Used by other distributions that rely on the Lognormal distribution
  static double sample(Sim_random_obj source, double mean, double variance) {
    return Math.exp(mean+Math.sqrt(variance)*Sim_normal_obj.sample(source, 0.0, 1.0));
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
