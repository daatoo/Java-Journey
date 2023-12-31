import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static  void main(String[] args) {
        Stream<Integer> stream = Stream.of(25, 15, 75, 35, 40, 5, 10, 55, 60);
        stream.parallel().forEach(System.out::println);
    }
}
