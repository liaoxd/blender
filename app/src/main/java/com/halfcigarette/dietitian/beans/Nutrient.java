package com.halfcigarette.dietitian.beans;



/**
 * Created by MOON on 5/21/2016.
 */
public class Nutrient {
    private String name;
    private String anotherName;
    private String nutrientType;
    private String unit;
    private String efficacy;
    private String effectMore;
    private String effectLess;
    private Food standFood;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public String getNutrientType() {
        return nutrientType;
    }

    public void setNutrientType(String nutrientType) {
        this.nutrientType = nutrientType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getEffectMore() {
        return effectMore;
    }

    public void setEffectMore(String effectMore) {
        this.effectMore = effectMore;
    }

    public String getEffectLess() {
        return effectLess;
    }

    public void setEffectLess(String effectLess) {
        this.effectLess = effectLess;
    }

    public Food getStandFood() {
        return standFood;
    }

    public void setStandFood(Food standFood) {
        this.standFood = standFood;
    }
}
