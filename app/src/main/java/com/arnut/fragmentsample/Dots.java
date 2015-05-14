package com.arnut.fragmentsample;

import java.util.ArrayList;

/**
 * Created by arnut on 5/14/2015 AD.
 */
public class Dots {
    private ArrayList<Dot> dots = new ArrayList<Dot>();




    public interface OnDotsChangeListener{
        void onDotsChange(Dots dots);
    }

    private OnDotsChangeListener onDotsChangeListener;

    public void setOnDotsChangeListener(OnDotsChangeListener onDotsChangeListener) {
        this.onDotsChangeListener = onDotsChangeListener;
    }

    public void insert(Dot dot){
        dots.add(dot);
        notifyDotsChange();
    }

    private void notifyDotsChange() {
        if (this.onDotsChangeListener != null){
            this.onDotsChangeListener.onDotsChange(this);
        }
    }
    public void delete(int position) {
        dots.remove(position);
        notifyDotsChange();
    }

    public void clear(){
        dots.clear();
        notifyDotsChange();
    }
    public void edit(int position, int coordX, int coordY) {
        Dot dot = dots.get(position);
        dot.setCoordX(coordX);
        dot.setCoordY(coordY);
        notifyDotsChange();
    }

    public int size(){
        return dots.size();
    }
    public Dot get(int position){
        return dots.get(position);
    }


}