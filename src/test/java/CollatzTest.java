import org.junit.jupiter.api.Test;

import java.util.List;

class CollatzTest {

  @Test
  void step() {
    int n = 5;
    assert Collatz.step(5) == 16;

    n = 2;
    assert Collatz.step(n) == 1;

    n = 1;
    assert Collatz.step(n) == 4;
  }

  @Test
  void endCycle() {
    List<Integer> end = Collatz.end;
    assert end.get(0) == 1;
    assert end.get(1) == 2;
    assert end.get(2) == 4;
  }
}