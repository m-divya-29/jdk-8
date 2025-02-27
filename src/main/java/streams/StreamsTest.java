package streams;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * How to select elements of a stream:
 * filter with predicate, filter unique elements, ignore first few, truncate to given size.
 */
class Dish{
    String name;
    Boolean isVegetarian;
    Dish(){}
    Dish(String name, boolean isVegetarian){
        this.name = name;
        this.isVegetarian = isVegetarian;
    }
    boolean isVeg(){
        return isVegetarian;
    }
    @Override
    public String toString(){
        return name + " is " + (isVegetarian ? "veg" : "non-veg");
    }
}
public class StreamsTest {
    public static void main(String[] args) {
        List<Dish> data = createDummyData();
        // data.stream().forEach(System.out::println);

        //Filter with Predicate

        //filter veg
        List<Dish> vegMenu = data.stream().filter(Dish::isVeg).collect(toList());
        vegMenu.forEach(System.out::println);

        //filter non-veg
        List<Dish> nonVegMenu = data.stream().filter(dish -> !dish.isVeg()).collect(toList());
        nonVegMenu.forEach(System.out::println);
    }

    private static List<Dish> createDummyData(){
        BiFunction<String, Boolean, Dish> fun = Dish::new;
        List<Dish> result = Stream.concat(IntStream.range(0, 5).mapToObj(i -> fun.apply("🥕 VegDish " + i, true)), IntStream.range(5, 10).mapToObj(i -> fun.apply("🍗 NonVegDish " + i, false))).toList();

        //result.addAll(IntStream.range(0, 5).mapToObj(i -> fun.apply("VegDish " + i, true)).toList());

        return result;
    }
}
