package org.projects.lambda.behaviorAsParam.generics;

import org.projects.lambda.behaviorAsParam.classic.Apple;

public class filterByColor implements IMyPredicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor());
    }
}
