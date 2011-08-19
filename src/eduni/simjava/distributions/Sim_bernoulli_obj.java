/* Sim_bernoulli_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the Bernoulli distribution.
 * @version     1.0, 14 May 2002
 * @author      Costas Simatos
 */

public class Sim_bernoulli_obj implements DiscreteGenerator {
  private Sim_random_obj source;
  private double prob;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param prob The probability of success
   */
  public Sim_bernoulli_obj(String name, double prob) {
    if ((prob < 0.0) || (prob > 1.0)) {
      throw new Sim_parameter_exception("Sim_bernoulli_obj: The probability of success must be between 0 and 1.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.prob = prob;
    this.name = name;
  }

  /**
   * The constructor with which a specific seed is set for the random
   * number generator
   * @param name The name to be associated with this instance
   * @param prob The probability of success
   * @param seed The initial seed for the generator, two instances with
   *             the same seed will generate the same sequence of numbers
   */
  public Sim_bernoulli_obj(String name, double prob, long seed) {
    if ((prob < 0.0) || (prob > 1.0)) {
      throw new Sim_parameter_exception("Sim_bernoulli_obj: The probability of success must be between 0 and 1.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.prob = prob;
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public long sample() {
    long result = 0;
    if (source.sample() <= prob) {
      result = 1;
    }
    return result;
  }

  // Used by other distributions that rely on the Bernoulli distribution
  static long sample(Sim_random_obj source, double prob) {
    long result = 0;
    if (source.sample() <= prob) {
      result = 1;
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
