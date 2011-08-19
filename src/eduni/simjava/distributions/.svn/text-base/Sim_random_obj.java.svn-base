/* Sim_random_obj.java */

package eduni.simjava.distributions;

/**
 * A random number generator producing pseudorandom numbers based
 * on the value of a specific seed. The generator is a multiplicative
 * linear congruential generator. It generates numbers through the
 * following structure:
 * <p>
 * <code>Y[1] = (742938285*Y[0]) mod (2<sup>31</sup>-1)</code>
 * <p>
 * The seed (<code>Y[0]</code>) provided is used to generate a sequence of pseudorandom
 * numbers uniformly distributed between <code>0</code> and <code>1</code>. The cycle of the generator
 * is <code>2<sup>31</sup>-2</code>.
 * @version 1.0, 13 May 2002
 * @author Costas Simatos
 */

public class Sim_random_obj implements ContinuousGenerator {
  // The multiplier
  private final long a = 742938285;
  // The modulus
  private final long m = 2147483647;
  // The last computed random number
  private long x;
  private String name;

  /**
   * Constructor with which <code>Sim_system</code> is allowed to set the random number
   * generator's seed
   * @param name The name to be associated with this instance
   * @param min  The minimum value this instance should generate
   * @param max  The maximum value this instance should generate
   */
  public Sim_random_obj(String name) {
    x = eduni.simjava.Sim_system.next_seed();
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
  public Sim_random_obj(String name, long seed) {
    x = seed;
    this.name = name;
  }

  /**
   * Generate a new random number.
   * @return The next random number in the sequence
   */
  public double sample() {
    x = (a * x) % m;
    return (double)x / (double)m;
  }

  /**
   * Set the random number generator's seed.
   * @param seed The new seed for the generator
   */
  public void set_seed(long seed) {
    x = seed;
  }

  /**
   * Get the random number generator's seed.
   * @return The generator's seed
   */
  public long get_seed() {
    return x;
  }

  /**
   * Get the random number generator's name.
   * @return The generator's name
   */
  public String get_name() {
    return name;
  }

}
