/* Sim_f_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator based on the F-distribution.
 * @version     1.0, 14 May 2002
 * @author      Costas Simatos
 */

public class Sim_f_obj implements ContinuousGenerator {
  private Sim_random_obj source;
  private long num_deg_freedom, den_deg_freedom;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param num_deg_freedom The numerator degrees of freedom for the distribution
   * @param den_deg_freedom The denominator degrees of freedom for the distribution
   */
  public Sim_f_obj(String name, long num_deg_freedom, long den_deg_freedom) {
    if ((num_deg_freedom <= 0L) || (den_deg_freedom <= 0L)) {
      throw new Sim_parameter_exception("Sim_f_obj: The degrees of freedom must be positive integers.");
    }
    source = new Sim_random_obj("Internal PRNG");
    this.num_deg_freedom = num_deg_freedom;
    this.den_deg_freedom = den_deg_freedom;
    this.name = name;
  }

  /**
   * The constructor with which a specific seed is set for the random
   * number generator
   * @param name The name to be associated with this instance
   * @param num_deg_freedom The numerator degrees of freedom for the distribution
   * @param den_deg_freedom The denominator degrees of freedom for the distribution
   * @param seed The initial seed for the generator, two instances with
   *             the same seed will generate the same sequence of numbers
   */
  public Sim_f_obj(String name, long num_deg_freedom, long den_deg_freedom, long seed) {
    if ((num_deg_freedom <= 0L) || (den_deg_freedom <= 0L)) {
      throw new Sim_parameter_exception("Sim_f_obj: The degrees of freedom must be positive integers.");
    }
    source = new Sim_random_obj("Internal PRNG", seed);
    this.num_deg_freedom = num_deg_freedom;
    this.den_deg_freedom = den_deg_freedom;
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public double sample() {
    return (Sim_chisquare_obj.sample(source, num_deg_freedom)/num_deg_freedom) /
           (Sim_chisquare_obj.sample(source, den_deg_freedom)/den_deg_freedom);
  }

  // Used by other distributions that rely on the F-distribution
  static double sample(Sim_random_obj source, long num_deg_freedom, long den_deg_freedom) {
    return (Sim_chisquare_obj.sample(source, num_deg_freedom)/num_deg_freedom) /
           (Sim_chisquare_obj.sample(source, den_deg_freedom)/den_deg_freedom);
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
