package org.projects.behaviorAsParam.generics;

import org.projects.behaviorAsParam.classic.Orange;

public class OrangeFilterByWeight implements IMyPredicate<Orange> {
    @Override
    public boolean test(Orange orange) {
        return orange.getFruitWeight() > 30;
    }

}
