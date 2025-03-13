package streams;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Grouping {
    enum CaloricLevel  {DIET, NORMAL, FAT};

    public static void main(String[] args) {
        // 1. classify the dishes in the menu according to veg or not, putting the ones containing meat in a group, the ones with fish in another group, and all others in a third group.
        List<Dish> menu = StreamsTest.createDummyData();
        Map<Boolean, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getIsVegetarian));


        // 2. classify as “diet” all dishes with 400 calories or fewer, set to “normal” the dishes having between 400 and 700 calories, and set to “fat” the ones with more than 700 calories.
        Map<CaloricLevel, List<Dish>> caloricLevelListMap =
                menu.stream().collect(Collectors.groupingBy( dish -> {
                    return dish.getCalories() <= 400 ?  CaloricLevel.NORMAL :
                    dish.getCalories() > 700 ? CaloricLevel.FAT : CaloricLevel.DIET;
                        }
                ));
        System.out.println(caloricLevelListMap);

        // 3. Multi level
        Map<Boolean, Map<CaloricLevel, List<Dish>>> dishesByVegGroupedByCaloricLevel =
                menu.stream().collect(Collectors.groupingBy( // first level groupingBy
                        Dish::getIsVegetarian, Collectors.groupingBy( dish -> { // second level groupingBy
                            return dish.getCalories() <= 400 ?  CaloricLevel.NORMAL :
                                    dish.getCalories() > 700 ? CaloricLevel.FAT : CaloricLevel.DIET;
                        })
                ));
        System.out.println("dishesByVegGroupedByCaloricLevel : " + dishesByVegGroupedByCaloricLevel);

        // 4. grouping by and counting
        Map<Boolean, Long> dishesCountMap = menu.stream().collect(Collectors.groupingBy(Dish::getIsVegetarian, Collectors.counting()));
        System.out.println("dishesCountMap: " + dishesCountMap);
        
        // 5. highest-calorie dish in the menu by type
        Map<Boolean, Optional<Dish>> highestCalorieDishInMenu =
                menu.stream().collect(Collectors.groupingBy(
                        Dish::getIsVegetarian,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                ));
            // if there’s no Dish in the menu for a given type, that
            //type won’t have an Optional.empty() as value; it won’t be present at all as a key in the Map. The
            //groupingBy collector lazily adds a new key in the grouping Map only the first time it finds an
            //element in the stream, producing that key when applying on it the grouping criteria being used.
    }
}
