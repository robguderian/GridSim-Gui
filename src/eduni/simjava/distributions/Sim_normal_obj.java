/* Sim_normal_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the Normal distribution.
 * @version     1.0, 13 May 2002
 * @author      Costas Simatos
 */

public class Sim_normal_obj implements ContinuousGenerator {
  private Sim_random_obj source;
  private double mean, std_dev;
  private boolean not_sampled = true;
  private double u1, u2;
  private static boolean not_sampled_st = true;
  private static double u1_st, u2_st;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param mean The mean of the distribution
   * @param variance The variance of the distribution
   */
  public Sim_normal_obj(String name, double mean, double variance) {
    if (variance <= 0.0) {
      throw new Sim_parameter_exception("Sim_normal_obj: The variance must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.mean = mean;
    this.std_dev = Math.sqrt(variance);
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
  public Sim_normal_obj(String name, double mean, double variance, long seed) {
    if (variance <= 0.0) {
      throw new Sim_parameter_exception("Sim_normal_obj: The variance must be greater than 0.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.mean = mean;
    this.std_dev = Math.sqrt(variance);
    this.name = name;
  }

  /**
   * Generate a new random number. Uses the Box-Muller Method.
   * @return The next random number in the sequence
   */
  public double sample() {
    double result;
    if (not_sampled) {
      u1 = source.sample();
      u2 = source.sample();
      result = mean + std_dev * Math.cos(2 * Math.PI * u1) * Math.sqrt(-2 * Math.log(u2));
    } else {
      result = mean + std_dev * Math.sin(2 * Math.PI * u1) * Math.sqrt(-2 * Math.log(u2));
    }
    return result;
  }

  // Used by other distributions that rely on the Normal distribution
  static double sample(Sim_random_obj source, double mean, double variance) {
    double result;
    double std_dev = Math.sqrt(variance);
    if (not_sampled_st) {
      u1_st = source.sample();
      u2_st = source.sample();
      result = mean + std_dev * Math.cos(2 * Math.PI * u1_st) * Math.sqrt(-2 * Math.log(u2_st));
    } else {
      result = mean + std_dev * Math.sin(2 * Math.PI * u1_st) * Math.sqrt(-2 * Math.log(u2_st));
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
