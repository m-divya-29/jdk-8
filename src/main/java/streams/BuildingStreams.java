package streams;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String[] args) {
        // Stream.of --
        Stream<String> stringStream = Stream.of("Apple", "Banana", "Pineapple");
        stringStream.map(String::toUpperCase).forEach(System.out::println); // Print strings in uppercase

        // empty stream
        Stream<String> emptyStream = Stream.empty();

        int[] nums = new int[]{1, 2, 3, 5, 6, 7};
        // Arrays.stream(nums).forEach(System.out::println);
        System.out.println(Arrays.stream(nums).sum());

        // Streams from Files
        System.out.println("current directory: " + Paths.get("").toAbsolutePath());
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("pom.xml"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct().count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Unique words = " + uniqueWords);

        // Infinite streams:
        Stream.iterate(10, n -> n * 2)  // 10, 20, 40, 80, 160 etc.
                .limit(10)
                .forEach(System.out::println);

        System.out.println("Fibonacci:: ");
        // Fibonacci tuples: (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13) ...
        Stream.iterate(new int[]{0, 1}, t -> new int[]{ t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println); // Print just each fibonacci
                //.forEach(x -> System.out.print(Arrays.toString(x))); // Print fibonacci tuple.

        // Generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
