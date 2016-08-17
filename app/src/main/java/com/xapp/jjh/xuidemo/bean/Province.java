package com.xapp.jjh.xuidemo.bean;

import java.util.List;

/**
 * Created by cyw on 2015/12/31.
 */
public class Province extends Area {
    private int province_id;
    private String province_name;

    private List<City> cities;

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
