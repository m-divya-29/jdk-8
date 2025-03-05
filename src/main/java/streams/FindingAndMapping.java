package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * finding whether some elements in a set of data match a given property
 * allMatch, anyMatch, noneMatch, findFirst, findAny.
 */
public class FindingAndMapping {
    public static void main(String[] args) {
        //anyMatch example
        List<Dish> dummyData = StreamsTest.createDummyData();
        System.out.println(dummyData.stream().anyMatch(Dish::isVeg)); //o/p: true
        if(dummyData.stream().anyMatch(Dish::isVeg)) {
            System.out.println("There is a veg dish!"); //op: There is a veg dish!
        }

        List<Dish> onlyVegs = dummyData.stream().filter(Dish::isVeg).toList();
        //allMatch example
        if(onlyVegs.stream().allMatch(Dish::isVeg)) {
            System.out.println("all are Veg dishes!");
        }

        onlyVegs = dummyData.stream().filter(x -> !x.isVegetarian).collect(Collectors.toList());
        //noneMatch
        if(onlyVegs.stream().noneMatch(Dish::isVeg)) {
            System.out.println("none of the dishes are veg!");
        }

        //FindAny
        Optional<Dish> optionalDish = dummyData.stream().filter(Dish::isVeg).findAny();
        if(optionalDish.isPresent()) {
            System.out.println("found dish: " + optionalDish.get().name);
        } else {
            System.out.println("no dish found.");
        }
        //ifPresent method example --
        optionalDish.ifPresent(System.out::println);

        //or else method example --
        optionalDish = dummyData.stream().filter(x -> x.name.equals("doesn't exist")).findAny();
        Optional<Dish> t = Optional.of(optionalDish.orElse(new Dish("dish coming soon...", true)));
        System.out.println(t);

        //find first
        List<Integer> li = Arrays.asList(1,9,6,3,2);
        Optional<Integer> firstSquareDivisibleBy3 = li.stream().filter(x -> x % 3 == 0).findFirst(); ;
        firstSquareDivisibleBy3.ifPresent(System.out::println); //9

    }
}
