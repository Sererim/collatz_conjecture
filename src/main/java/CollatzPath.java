import java.util.ArrayList;

public class CollatzPath {
  private final Integer start;
  private       Integer current;
  private       ArrayList<Integer> road = new ArrayList<>();
  private       Boolean finish = false;

  public CollatzPath(Integer start) {
    this.start = start;
    this.current = start;
  }

  public void calculate() {
    current = Collatz.step(current);
    if (isAtEnd()) {
      road.add(-1);
      road.addAll(Collatz.end);
      finish = true;
      return;
    } else if (isInPaths()) {
      road.add(current);
      road.add(-1);
      road.addAll(
        MapOfPaths.paths.get(this.current)
      );
      finish = true;
      return;
    }
    road.add(current);
  }

  private boolean isAtEnd() {
    return (Collatz.end.contains(this.current))
    ? true
    : false;
  }

  private boolean isInPaths() {
    return MapOfPaths.isInPath(this.current);
  }

  public boolean foundEnd() {
    return finish;
  }

  public void end() {
    MapOfPaths.paths.put(
      this.start,
      this.road
    );
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Start : " + this.start + "\n");
    builder.append("[ ");
    for (Integer number : this.road) {
      if (number == -1) {
        builder.append(Colors.RED);
      } else {
        builder.append(number).append(" ");
      }
    }
    builder.append(Colors.RESET).append(" ]");
    return builder.toString();
  }
}
