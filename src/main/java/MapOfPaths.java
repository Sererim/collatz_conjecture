import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class MapOfPaths {
  // We will hold all the paths here.
  public static ConcurrentHashMap<Integer, ArrayList<Integer>> paths = new ConcurrentHashMap<>();

  public static boolean isInPath(Integer n) {
    return (paths.getOrDefault(n, null) != null);
  }
}
