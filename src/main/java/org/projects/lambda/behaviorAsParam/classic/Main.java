package org.projects.lambda.behaviorAsParam.classic;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Apple a1 = new Apple(100, "red");
        Apple a2 = new Apple(140, "green");
        Apple a3 = new Apple(152, "red");
        Apple a4 = new Apple(156, "green");
        Apple a5 = new Apple(250, "green");
        Apple a6 = new Apple(10, "green");
        Apple a7 = new Apple(120, "red");
        List<Apple> inventory = Arrays.asList(a1, a2, a3, a4, a5, a6, a7);

        prettyPrintApple(inventory, new ApplePrettyPrintWeightAndColor());

    }
    public static void prettyPrintApple(List<Apple> inventory, IApplePrettyPrint predicate) {
        for(Apple apple: inventory) {
            String s = predicate.test(apple);
            System.out.println(s);
        }
    }

}