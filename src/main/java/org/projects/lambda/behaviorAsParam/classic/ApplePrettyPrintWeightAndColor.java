package org.projects.lambda.behaviorAsParam.classic;

public class ApplePrettyPrintWeightAndColor implements IApplePrettyPrint {
    @Override
    public String test(Apple apple) {
        String characteristic = apple.getFruitWeight() > 150 ? "heavy" : "light";
        return
                "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
