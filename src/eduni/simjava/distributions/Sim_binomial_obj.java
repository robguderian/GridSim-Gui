/* Sim_binomial_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the Binomial distribution.
 * @version     1.0, 14 May 2002
 * @author      Costas Simatos
 */

public class Sim_binomial_obj implements DiscreteGenerator {
  private Sim_random_obj source;
  private double prob;
  private long trials;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param prob The probability of success in a trial
   * @param trials The number of trials
   */
  public Sim_binomial_obj(String name, double prob, long trials) {
    if (prob <= 0.0) {
      throw new Sim_parameter_exception("Sim_binomial_obj: The probability of success must be between 0 and 1.");
    } else if (trials <= 0L) {
      throw new Sim_parameter_exception("Sim_binomial_obj: The number of trials must be a positive integer.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.prob = prob;
    this.trials = trials;
    this.name = name;
  }

  /**
   * The constructor with which a specific seed is set for the random
   * number generator
   * @param name The name to be associated with this instance
   * @param prob The probability of success
   * @param trials The number of trials
   * @param seed The initial seed for the generator, two instances with
   *             the same seed will generate the same sequence of numbers
   */
  public Sim_binomial_obj(String name, double prob, long trials, long seed) {
    if ((prob <= 0.0) || (trials <= 0.0)) {
      throw new Sim_parameter_exception("Sim_binomial_obj: The probability of success must be between 0 and 1.");
    } else if (trials < 0L) {
      throw new Sim_parameter_exception("Sim_binomial_obj: The number of trials must be a positive integer.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.prob = prob;
    this.trials = trials;
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public long sample() {
    long sum = 0L;
    for (long i=0L; i < trials; i++) {
      if (source.sample() < prob) {
        sum++;
      }
    }
    return sum;
  }

  // Used by other distributions that rely on the Binomial distribution
  static long sample(Sim_random_obj source, double prob, long trials) {
    long sum = 0L;
    for (long i=0L; i < trials; i++) {
      if (source.sample() < prob) {
        sum++;
      }
    }
    return sum;
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
