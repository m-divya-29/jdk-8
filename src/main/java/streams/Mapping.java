package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapping {
    public static void main(String[] args) {
        example4();
    }
    /*
        how could you return a list of all the unique characters for a list of words?
        Example: ["Hello", "World"] o/p: "HeloWrd"
     */
    public static void example1(){
        List<String> words = Arrays.asList("Hello", "World", "learn", "java", "java");
        //attempt 1: split word to get chars and then apply distinct on it.
       List<String[]> li =  words.stream() // get the stream to iterate over
               .map(word -> word.split("")). //get chars from each word as an array of strings.
                       distinct() // get distinct instances of arrays. (never works since they will always be unique)
               .collect(Collectors.toList());

       //print to see --
        //checks for unique array objects not characters!☹️
        li.stream().forEach(l -> System.out.println(Arrays.toString(l))); // prints each string as an array of chars.
        //problem is at map(word -> word.split("")) --> returns String[] and thus map returns Stream<String[]>

        //Failed attempt 2
        List<Stream<String>>  li2 =  words.stream() // get the stream to iterate over
                .map(word -> word.split("")) //get stream<str[]> from each word as an array.
                .map(Arrays::stream) // get stream over each str[]
                .distinct() // get distinct instances of arrays. (never works since they will always be unique)
                .collect(Collectors.toList());

        //FlatMap -> Flattens each generated stream into a single stream.
        List<String>  li3 =  words.stream() // get the stream to iterate over
                .map(word -> word.split("")) //get stream<str[]> from each word as an array.
                .flatMap(Arrays::stream) // flatten the streams over each str[]  -> stream<str>
                .distinct() // get distinct str.
                .collect(Collectors.toList());
            //print to verify
            print(li3);

    }

    //. Given a list of numbers, how would you return a list of the square of each number?
    // For example, given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25]
    public static void example2(){
        List<Double> li = Arrays.asList(1d,2d,3d,4d,5d);
        List<Double> res = li.stream()
                .map(Math::sqrt)
                .collect(Collectors.toList());

        res.stream().forEach(System.out::println);

            //or
            li.stream().map(n -> n * n).collect(Collectors.toList());
    }

    //Given two lists of numbers, how would you return all pairs of numbers? For example, given a
    //list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
    // For simplicity, you can represent a pair as an array with two element
    public static void example3(){
        List<Integer> l1 = Arrays.asList(1,2,3);
        List<Integer> l2 = Arrays.asList(4,5);

        // List<Integer[]> res = l1.stream()
        // .map(i -> l2.stream().map(j -> new int[]{i, j})) --> doesn't work. creates a Stream<Stream<Integer[]>>
        // .collect(Collectors.toList());

         List<int[]> res = l1.stream()
         .flatMap(i -> l2.stream().map(j -> new int[]{i, j})) //--> doesn't work. creates a Stream<Stream<Integer[]>>
         .collect(Collectors.toList());

         res.stream().forEach(x -> System.out.println(Arrays.toString(x)));
    }

    // Extend example3 to return only pairs whose sum is divisible by 3
    public static void example4(){
        List<Integer> l1 = Arrays.asList(1,2,3);
        List<Integer> l2 = Arrays.asList(4,5);

        List<int[]> result = l1.stream() //stream<Integer>
                .flatMap(i -> l2.stream().map(j -> new int[]{i, j})) //Stream<int[]>
                .filter(x -> Arrays.stream(x).sum() % 3 == 0)
                .collect(Collectors.toList());
        result.stream().forEach(x -> System.out.println(Arrays.toString(x)));

            //or --
        List<Integer[]> result2 = l1.stream()
                .flatMap(i -> l2.stream()
                        .filter(j -> ( i + j) % 3 == 0)
                        .map(j -> new Integer[]{i,j})
                ).collect(Collectors.toList());
        printArr(result2);
    }

    public static <T> void print(List<T> li) {
        li.stream().forEach(System.out::println);
    }
    public static <T> void printArr(List<T[]> li) {
        li.stream().forEach(x -> System.out.println(Arrays.toString(x)));
    }
}
