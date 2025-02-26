package org.projects.behaviorAsParam.generics;

import org.projects.behaviorAsParam.classic.Apple;

public class filterByWeight implements IMyPredicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return apple.getFruitWeight() > 150;
    }
}
