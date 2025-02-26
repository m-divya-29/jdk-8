package org.projects.behaviorAsParam.generics;

import org.projects.behaviorAsParam.classic.Apple;

public class filterByColor implements IMyPredicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor());
    }
}
