package streams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Reducing {
    public static void main(String[] args) {
        //Sum of all elements in the list
        List<Integer> li = Arrays.asList(1,3,4,5,6,7,7,10);
        int sum = 0;
        // li.stream().forEach(x -> sum += x); //CAN'T DO THIS: only final variables can be used within lambda. so, sum cannot be updated.
        sum = li.stream().reduce(0, (x,y) -> x + y); // 0 is used as the first parameter of lambda (x) and
                                                            // 1 is consumed as second param. the sum 0 + 1 is then accumulated into x and next element from stream as y.
        System.out.println(sum);

        //or we can use Integer.sum that sums two numbers using method reference
        System.out.println("Sum: " + li.stream().reduce(0, Integer::sum));
        System.out.println("product: " + li.stream().reduce(1, (x, y) -> x * y));

        // Optional: overloaded version of reduce. takes no initial value.
        // best used when there maybe no elements in the list.
         List<Integer> li2 = new ArrayList<>(li);
        Optional<Integer> optionalSum = li2.stream().reduce((x, y) -> x + y);
        if(optionalSum.isPresent()) {
            System.out.println("sum = " + optionalSum);
        } else {
            System.out.println("there are no elements to sum!");
        }

        // Find Min and Max in list
        System.out.println("Max element: " +li.stream().reduce(Integer.MIN_VALUE, (x, y) -> Math.max(x, y)));

        // Or
        System.out.print("Min element: ");
        li.stream().reduce((x, y) -> Math.min(x, y)).ifPresent(System.out::println);

        // Examples
        example1();
        example2();

    }

    /**
     * How would you count the number of dishes in a stream using the map and reduce methods
     */
    public static void example1(){
       List<Dish> data = StreamsTest.createDummyData();
        System.out.println("number of dishes = " + data.stream()
                .map(d -> 1) //map each dish to 1.
                .reduce(0, (x, y) -> x + y)
        );

        // Or simply
        System.out.println("Number of dishes using stream.count: " + data.stream().count());
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class Trader{
        String name;
        String city;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static  class Transaction {
        Trader trader;
        int year;
        int value;

        String currency;
        Transaction(int year, int value) {
            this.year = year;
            this.value = value;
        }
        Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

    }

    public  static  void example2() {
        List<Trader> traders = Arrays.asList(new Trader("A1","Kakinada"), new Trader("Z9","Cambridge"), new Trader("AA1","Calcutta"), new Trader("A2","London"), new Trader("F0","Austria"), new Trader("E","Cambridge"), new Trader("C","Cambridge"));

        // 1. Find all transactions in the year 2011 and sort them by value
        List<Transaction> transactions = Arrays.asList(new Transaction( traders.get(0),2011, 100),
                new Transaction( traders.get(0), 2011, 10),
                new Transaction(traders.get(2),2011, 1000),
                new Transaction(traders.get(1),2011, 1100),
                new Transaction(traders.get(3),2011, 200),
                new Transaction(traders.get(4),2011, 30),
                new Transaction(traders.get(5),2011, 19));



        transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparing((Transaction::getValue))).forEach(System.out::println);

        // 2. What are all the unique cities where the traders work?
        System.out.println("------Distinct cities-------");
        traders.stream()
                .map(x -> x.getCity())
                .distinct().forEach(System.out::println);

        // 3. Find all traders from Cambridge and sort them by name.
        System.out.println("-- traders from Cambridge and sort them by name --");
        traders.stream()
                .filter(x -> x.city.equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);

        // 4. Return a string of all traders’ names sorted alphabetically.
        System.out.println(traders.stream()
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .reduce("", (x, y) -> x + y));

        // 5. Are any traders based in Calcutta?
        System.out.println(traders.stream()
                .anyMatch(x -> x.city.equals("Calcutta")) ? "Traders exist based out of Calcutta" : "Traders do not exist based out of Calcutta");

        // 6. Print all transactions’ values from the traders living in Cambridge
        System.out.println("All transactions’ values from the traders living in Cambridge --");
        transactions.stream()
                .filter(i -> i.trader.getCity().equals("Cambridge"))
                .map(x -> x.getValue())
                .forEach(System.out::println);

        // 7. What’s the highest value of all the transactions?
        System.out.println( "Highest value of all transactions: " +transactions.stream()
                        .map(x -> x.getValue())
                .reduce(0, ( x,  y) -> Math.max(x, y)));

        // 8. Find the transaction with the smallest value
        System.out.println("First transaction with smallest value: " +
                transactions.stream()
                        .reduce((x, y) -> x.getValue() < y.getValue() ? x : y).get());

    }
}
