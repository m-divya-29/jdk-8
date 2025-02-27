package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * How to select elements of a stream:
 * filter with predicate, filter unique elements, ignore first few, truncate to given size.
 */
class Dish{
    String name;
    Boolean isVeg;
    Dish(){}
    Dish(String name, boolean isVeg){
        this.name = name;
        this.isVeg = isVeg;
    }
    @Override
    public String toString(){
        return name + " is " + (isVeg ? "veg" : "non-veg");
    }
}
public class StreamsTest {
    public static void main(String[] args) {
        List<Dish> data = createDummyData();
        data.stream().forEach(System.out::println);
    }

    private static List<Dish> createDummyData(){
        BiFunction<String, Boolean, Dish> fun = Dish::new;
        List<Dish> result = Stream.concat(IntStream.range(0, 5).mapToObj(i -> fun.apply("🥕VegDish " + i, true)), IntStream.range(5, 10).mapToObj(i -> fun.apply("🍗NonVegDish " + i, false))).toList();

        //result.addAll(IntStream.range(0, 5).mapToObj(i -> fun.apply("VegDish " + i, true)).toList());

        return result;
    }
}
