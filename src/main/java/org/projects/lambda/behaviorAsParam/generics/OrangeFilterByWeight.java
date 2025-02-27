package org.projects.lambda.behaviorAsParam.generics;

import org.projects.lambda.behaviorAsParam.classic.Orange;

public class OrangeFilterByWeight implements IMyPredicate<Orange> {
    @Override
    public boolean test(Orange orange) {
        return orange.getFruitWeight() > 30;
    }

}
