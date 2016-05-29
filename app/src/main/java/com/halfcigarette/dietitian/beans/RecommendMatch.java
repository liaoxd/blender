package com.halfcigarette.dietitian.beans;

import java.util.List;

/**
 * Created by MOON on 5/27/2016.
 */
public class RecommendMatch {
    private List<Food> foods;
    private String efficacy;
    private String tag;
    private Cook cook;

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }
}
