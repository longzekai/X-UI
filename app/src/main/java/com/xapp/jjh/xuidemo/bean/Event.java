package com.xapp.jjh.xuidemo.bean;

import java.io.Serializable;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class Event implements Serializable {

    public static final int TYPE_LOCATION_CITY = 1;
    public static final int TYPE_LOCATION_COUNTY = 2;

    private int type;
    private int id;

    public Event() {
    }

    public Event(int type, int id) {
        this.type = type;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
