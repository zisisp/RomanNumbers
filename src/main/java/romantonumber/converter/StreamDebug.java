package romantonumber.converter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamDebug {

  public static void main(String[] args) {
    List<String> numbers = IntStream
        .iterate(0, i -> i + 1)
        .limit(20)
        .filter(n -> n % 2 != 0)
        .filter(n -> n >= 10)
        .mapToObj(n -> n * 10 + "")
        .collect(Collectors.toList());
    for (String number : numbers) {
      System.out.println("number = " + number);
    }
  }

}
