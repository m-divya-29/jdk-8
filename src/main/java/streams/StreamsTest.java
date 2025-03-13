package streams;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * How to select elements of a stream:
 * filter with predicate, filter unique elements, ignore first few, truncate to given size.
 */
@Getter
class Dish{
    String name;
    Boolean isVegetarian;
    int calories;

    Dish(){}
    Dish(String name, boolean isVegetarian, int calories){
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.calories = calories;
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
        List<Dish> data = new ArrayList<>(createDummyData());
        // data.stream().forEach(System.out::println);

        //Filter with Predicate

        //filter veg
        List<Dish> vegMenu = data.stream().filter(Dish::isVeg).collect(toList());
        //vegMenu.forEach(System.out::println);

        //filter non-veg
        List<Dish> nonVegMenu = data.stream().filter(dish -> !dish.isVeg()).collect(toList());
        //nonVegMenu.forEach(System.out::println);

        //Filter unique elements
        data.add(new Dish("🥕 VegDish 2", true, 10));
        
        //add duplicate dishes.
        Dish x = new Dish("duplicate dummy", true, 1000);

        data.add(x);
        data.add(x);

        System.out.println(data.stream().distinct().count() != data.size()); // true
    }


    public interface TriFunction<T, U, V, R>{
        R apply(T t, U u, V v);
    }
    public static List<Dish> createDummyData(){
        TriFunction<String, Boolean, Integer, Dish> fun = Dish::new;
        List<Dish> result = Stream.concat(IntStream.range(0, 5).mapToObj(i -> fun.apply("🥕 VegDish " + i, true, 19)), IntStream.range(5, 10).mapToObj(i -> fun.apply("🍗 NonVegDish " + i, false, 201))).toList();
        return result;
    }
}
