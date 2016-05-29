package com.halfcigarette.dietitian.beans;

import java.util.List;

/**
 * Created by MOON on 5/27/2016.
 */
public class Restraint {
    private List<Food> foods;
    private String reason;
    private String tag;
    private String harmLevel;

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHarmLevel() {
        return harmLevel;
    }

    public void setHarmLevel(String harmLevel) {
        this.harmLevel = harmLevel;
    }
}
