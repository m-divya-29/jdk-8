package streams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericalStream {
    public static void main(String[] args) {
        // create a stream of Pythagorean triples.
        // triples of numbers (a, b, c) satisfy the formula a * a + b * b = c * c where a, b, and c
        //are integers. For example, (3, 4, 5) is a valid Pythagorean triple because 3 * 3 + 4 * 4 = 5 * 5 or 9
        //+ 16 = 25.

        // create Stream<int[]> such that: int[]{a*a, b*b, a*a + b*b} ...

        Stream<int[]> stream = IntStream.rangeClosed(1, 100) // a
               .boxed() // convert IntStream to Stream<Object>
                .flatMap(a -> // Stream<Stream<int[]>> to Stream<int[]>
                        IntStream.rangeClosed(a + 1, 100) // b
                                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0) // check if it is not decimal.
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)})
                );
         stream.limit(10)
                .forEach(x -> System.out.println(Arrays.toString(x)));

    }
}
