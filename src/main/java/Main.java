import java.util.*;
import java.util.concurrent.*;

public class Main {

  public static void main(String... args) {
    try {
      run();
    } catch (Exception e) {
      print("Weird " + e.getMessage());
    }
  }

  private static void run() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);

    print("Enter number to calculate Collatz path for.");
    Integer n = getN(scanner);

    print("Enter number of Threads to use for calculation, 10 is default.");
    Integer threads = getThreads(scanner);

    Stack<Integer> numberStack = generateStack(n);

    ExecutorService executor = Executors.newFixedThreadPool(threads);

    CollatzPath mainPath = new CollatzPath(n);

    while (!numberStack.isEmpty() && !mainPath.foundEnd()) {
      for (int i = 0; i < threads; i++) {
        Integer number = numberStack.pop();
        executor.submit(() -> {
          print(Collatz.collatzRun(number).toString());
        });
      }
      executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
      Collatz.collatzRun(mainPath);
    }
    executor.shutdownNow();
    print(mainPath.toString());

    scanner.close();
  }

  private static Integer getN(Scanner scanner) {
    int n;
    while (true) {
      try {
        n = scanner.nextInt();
        break;
      } catch (Exception e) {
        print("Not a number!");
        scanner.next();
      }
    }
    return n;
  }

  private static Integer getThreads(Scanner scanner) {
    int threadNumber = 10;
    try {
      threadNumber = scanner.nextInt();
    } catch (Exception e) {
      print("Not a number!");
    }
    return threadNumber;
  }

  private static Stack<Integer> generateStack(Integer n) {
    Stack<Integer> numbers = new Stack<>();
    for (int k = n - 1; k > 0; k--) {
      numbers.add(k);
    }
    return numbers;
  }

  private static void print(String msg) {
    System.out.println(msg);
  }
}
