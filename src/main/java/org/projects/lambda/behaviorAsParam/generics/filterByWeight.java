package org.projects.lambda.behaviorAsParam.generics;

import org.projects.lambda.behaviorAsParam.classic.Apple;

public class filterByWeight implements IMyPredicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return apple.getFruitWeight() > 150;
    }
}
