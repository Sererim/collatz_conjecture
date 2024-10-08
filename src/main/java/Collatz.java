import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Collatz {

  /**
   * Because we will only read from it, synchronization is not needed.
   */
  public final static List<Integer> end = endCycle();

  /**
   * Calculate the next number on the Collatz algorithm's path.
   * We will do it the most efficient way possible.
   * @param n any Integer number, checks for correctness of the number should happen in another place
   * @return next number in the Collatz's path.
   */
  public static Integer step(Integer n) {
    return ((n & 1) == 1)
    ?  (3 * n + 1)
    :  (n >> 1);
  }
  /**
   * Get an end cyclical path for Collatz algorithm.
   * @return an ArrayList of 1, 2, 4.
   */
  private static List<Integer> endCycle() {
    return Collections.unmodifiableList(Arrays.asList(4, 2, 1));
  }

  public static CollatzPath collatzRun(Integer number) {
    CollatzPath collatzPath = new CollatzPath(number);

    while(!collatzPath.foundEnd()) {
      collatzPath.calculate();
    }
    collatzPath.end();
    return collatzPath;
  }

  public static void collatzRun(CollatzPath path) {
    if (!path.foundEnd())
      path.calculate();
    else
      path.end();
  }
}
