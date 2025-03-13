package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import streams.Reducing.Transaction;


public class Collecting {
    public static void main(String[] args) {
        List<Reducing.Trader> traders = Arrays.asList(new Reducing.Trader("A1","Kakinada"), new Reducing.Trader("Z9","Cambridge"), new Reducing.Trader("AA1","Calcutta"), new Reducing.Trader("A2","London"), new Reducing.Trader("F0","Austria"), new Reducing.Trader("E","Cambridge"), new Reducing.Trader("C","Cambridge"));

        List<Reducing.Transaction> transactions = Arrays.asList(
                new Reducing.Transaction( traders.get(0),2011, 100, "YEN"),
                new Reducing.Transaction( traders.get(0), 2011, 10, "INR"),
                new Reducing.Transaction(traders.get(2),2011, 1000, "USD"),
                new Reducing.Transaction(traders.get(1),2011, 1100, "INR"),
                new Reducing.Transaction(traders.get(3),2011, 200, "USD"),
                new Reducing.Transaction(traders.get(4),2011, 30, "USD"),
                new Reducing.Transaction(traders.get(5),2011, 19, "INR"));

        // 1. Collecting transactions by currency
        Map<String, List<Transaction>> mp = transactions.stream().collect(Collectors.groupingBy(Reducing.Transaction::getCurrency));
        Stream.of(mp).forEach(System.out::println);

        // 2. Count number of dishes in the menu using collect method.
        List<Dish> menu = StreamsTest.createDummyData();
        long totalDishes = menu.stream().collect(Collectors.counting());

        // 3.  Finding maximum and minimum in a stream of values - find max calorie, min calorie dish
        Comparator<Dish> dishCalorieComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxCalorieDish = menu.stream().collect(Collectors.maxBy(dishCalorieComparator));
        System.out.println("max calorie dish:--- " + maxCalorieDish.get().getCalories()); // 201

        //min dish:
        Optional<Dish> minCalorieDish = menu.stream().collect(Collectors.minBy(dishCalorieComparator));
        System.out.println("max calorie dish:--- " + minCalorieDish.get().getCalories()); // 19

        // 4. Summing
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("total calories on the menu: " + totalCalories);

        // 5. Average
        double averageCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println("Average calories on the menu: " + averageCalories);

        // 6. Summary
        IntSummaryStatistics menuStats = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("Summary of the menu items:" + menuStats);

        // 7. joining strings
        String joinedDishes = menu.stream().map(Dish::toString).collect(Collectors.joining());
        System.out.println("Joined dishes: " + joinedDishes);
            // joining with delimiter
            String joinedDishes2 = menu.stream().map(Dish::toString).collect(Collectors.joining("\n"));
            System.out.println("Joined dishes: " + joinedDishes2);

        // 8. Generalized reduction
        // find total calories
        int totalCals = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i,j) -> i + j));
        System.out.println("total cals by reducing: " + totalCals);

        // highest calorie dish
        Optional<Dish> highestCalorieDish = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println("highest calorie dish: " + highestCalorieDish.get().getCalories());

        // Finding total calories in other ways:
        int total1 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        int total2 = menu.stream().mapToInt(Dish::getCalories).sum();

        System.out.println(total1 + " = " +total2);

        // 9. equivalents of String shortMenu = menu.stream().map(Dish::getName).collect(joining())
        System.out.println("short menu versions:");

        String shortMenu1 = menu.stream().map(Dish::getName).reduce("", (x, y) -> x + y);
        String shortMenu2 = menu.stream().collect(Collectors.reducing("", Dish::getName, (d1, d2) -> d1 + d2));
        System.out.println(shortMenu1 + "\n" + shortMenu2);

    }
}
