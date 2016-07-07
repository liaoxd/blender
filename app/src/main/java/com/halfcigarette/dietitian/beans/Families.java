package com.halfcigarette.dietitian.beans;

/**
 * Created by MOON on 5/21/2016.
 */
public class Families {
    private String nickName;
    private String gender;
    private int age;
    private int height;
    private int weight;
    private String irritability;
    private boolean isPregnancy;
    private String timePregnancy;
    //乳母什么鬼
    private String physicalLevel;

    public Families(String gender, int age, int weight) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        nickName = "test";
        height = 170;
        irritability = "null";
        isPregnancy = false;
        timePregnancy = "null";
        physicalLevel = "null";
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getIrritability() {
        return irritability;
    }

    public void setIrritability(String irritability) {
        this.irritability = irritability;
    }

    public String getTimePregnancy() {
        return timePregnancy;
    }

    public void setTimePregnancy(String timePregnancy) {
        this.timePregnancy = timePregnancy;
    }

    public boolean isPregnancy() {
        return isPregnancy;
    }

    public void setPregnancy(boolean pregnancy) {
        isPregnancy = pregnancy;
    }

    public String getPhysicalLevel() {
        return physicalLevel;
    }

    public void setPhysicalLevel(String physicalLevel) {
        this.physicalLevel = physicalLevel;
    }
}
