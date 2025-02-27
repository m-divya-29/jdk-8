package org.projects.lambda.behaviorAsParam.classic;

public class ApplePrettyPrintWeight implements IApplePrettyPrint {
    @Override
    public String test(Apple apple) {
        return "A apple of weight " + apple.getFruitWeight();
    }
}
