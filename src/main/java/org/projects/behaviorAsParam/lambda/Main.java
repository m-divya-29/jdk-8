package org.projects.behaviorAsParam.lambda;

import org.projects.behaviorAsParam.classic.Apple;
import org.projects.behaviorAsParam.classic.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args)
    {
        Apple a1 = new Apple(100, "red");
        Apple a2 = new Apple(140, "green");
        Apple a3 = new Apple(152, "red");
        Apple a4 = new Apple(156, "green");
        Apple a5 = new Apple(250, "green");
        Apple a6 = new Apple(10, "green");
        Apple a7 = new Apple(120, "red");
        List<Apple> inventory = Arrays.asList(a1, a2, a3, a4, a5, a6, a7);


        List<Apple> res1 = prettyPrintFruit(inventory, ((Apple apple) -> "red".equals(apple.getColor())));
        List<Apple> res2 =  prettyPrintFruit(inventory, apple -> apple.getFruitWeight() >150 );
        Orange o1 = new Orange(15, "orange");
        Orange o2 = new Orange(150, "yellow");

        List<Orange> res3 = prettyPrintFruit(Arrays.asList(o1, o2), orange -> orange.getFruitWeight() > 5);

        System.out.println(res3.size());
    }
public static <T> List<T> prettyPrintFruit(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for(T t: inventory) {
            if(predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
}


}
