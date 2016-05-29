package com.halfcigarette.dietitian.beans;

/**
 * Created by dongweihang on 2015/11/23.
 */
public class Cook {
    private String name;
    private Integer sourceid;


    public Cook(String name, Integer sourceid) {
        this.name = name;
        this.sourceid = sourceid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }
}
