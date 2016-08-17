package com.xapp.jjh.xuidemo.bean;

import java.io.Serializable;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/17.
 * ------------------------------------
 */
public class CardData implements Serializable {

    public static final String KEY_CARD_DATA = "card_data";

    private int preColor;
    private int color;
    private String message;

    public CardData() {
    }

    public CardData(String message) {
        this.message = message;
    }

    public CardData(int preColor, int color, String message) {
        this.preColor = preColor;
        this.color = color;
        this.message = message;
    }

    public int getPreColor() {
        return preColor;
    }

    public void setPreColor(int preColor) {
        this.preColor = preColor;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
