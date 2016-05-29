package com.halfcigarette.dietitian.beans;

/**
 * Created by dongweihang on 2015/11/25.
 */
public class People {
    //true为男性，false为女性
    private boolean sex;
    private Integer age;
    private Integer weight;

    public People(boolean sex, Integer age, Integer weight) {
        this.sex = sex;
        this.age = age;
        this.weight = weight;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
