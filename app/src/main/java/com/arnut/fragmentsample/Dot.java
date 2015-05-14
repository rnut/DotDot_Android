package com.arnut.fragmentsample;

import android.graphics.Color;

/**
 * Created by arnut on 5/14/2015 AD.
 */
public class Dot {
    private int coordX;
    private int coordY;
    private FontColor color;
    private int size;

    public Dot(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.color = FontColor.GREEN;
        this.size = 40;
    }

    public Dot(int coordX, int coordY, FontColor color, int size) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.color = color;
        this.size = size;
    }

    public FontColor getColor() {
        return color;
    }

    public void setColor(FontColor color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }



    //enum color
    public enum FontColor {
        RED(Color.RED),
        BLACK(Color.BLACK),
        BLUE(Color.BLUE),
        GREEN(Color.GREEN);

        private int Value;
        private static final int size = FontColor.values().length;

        private FontColor(int colorValue) {
            Value = colorValue;
        }

        public static int getSize() {
            return size;
        }

        public static FontColor getByOrder(int order){
            switch (order){
                case 0:
                    return FontColor.RED;
                case 1:
                    return FontColor.BLACK;
                case 2:
                    return FontColor.BLUE;
                case 3:
                    return FontColor.GREEN;
            }
            return null;
        }

        public int getValue() {
            return Value;
        }

        @Override
        public String toString() {
            return "#"+String.valueOf(Value);
        }
    }
}
