package com.halfcigarette.dietitian.beans;

/**
 * Created by dongweihang on 2015/11/12.
 */
public class NutritionElement {
    private String name;
    private double content;
    private double standard;

    public NutritionElement(String name, double content, double standard) {
        this.name = name;
        this.content = content;
        this.standard = standard;
    }

    public NutritionElement() {
    }

    public NutritionElement(String name, double content) {

        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    public double getStandard() {
        return standard;
    }

    public void setStandard(double standard) {
        this.standard = standard;
    }
}
