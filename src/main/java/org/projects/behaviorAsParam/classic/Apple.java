package org.projects.behaviorAsParam.classic;

public class Apple {
    private int weight;
    private String color;
    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }
    public int getFruitWeight(){
        return weight;
    }
    public void setWeight(int val){
        this.weight = val;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String col){
        this.color = col;
    }
}
