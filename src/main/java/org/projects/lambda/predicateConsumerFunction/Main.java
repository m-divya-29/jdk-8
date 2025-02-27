package org.projects.lambda.predicateConsumerFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {
        /*
         * Predicate: a single test method that takes input T and returns boolean.
         */
        List<String> li = Arrays.asList("lambdas", "in", "action"); //filter list by size --
        li = filterData(li, (s) -> s.length() > 2);
        //System.out.println(li.size());
        li = filterData(li, (s) -> s.startsWith("l") ); //filter list by start char --
        //System.out.println(li.size());

        /*
         * Consumer: Just an out of box interface with method accept that returns nothing/void
         */
        List<String> li2 = Arrays.asList("this" , "is", "test", "data");
        // processData(li2, (str -> System.out.print(str + str.length() + " " ))); //print each string followed by its length.
       // processData(li2, (str -> System.out.print(str.toUpperCase() + " "))); //print each string in upper

        /*
            Function: just an out of box interface with method apply that has input type T and return type R.
         */
        List<Integer> lengths = getLengthsOfStrings(li2, (str -> str.length()));

        //print them
        processData(lengths, (i -> System.out.println(i)));
    }
    public  static <T> List<T> filterData(List<T> li, Predicate<T> predicate) {
        List<T> ans = new ArrayList<T>();
        for(T s: li) {
            if(predicate.test(s))
                ans.add(s);
        }
        return  ans;

    }

    public static <T> void processData(List<T> li, Consumer<T> consumer){
        for(T str: li) {
            consumer.accept(str);
        }
    }


    public static <T, R> List<R> getLengthsOfStrings(List<T> li, Function<T, R> function) {
        List<R> result = new ArrayList<R>();
        for(T t: li) {
            result.add(function.apply(t));
        }

        return result;
    }
}
