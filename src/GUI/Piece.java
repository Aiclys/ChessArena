//package com.chess.GUI;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class Piece {
    String color;
    int[] currPos;
    boolean alive;
    int value; 
    Image img;
    LinkedList<Piece> pieces = new LinkedList<Piece>();


    public void setColor(String color) {
        this.color = color;
    }

    public void setPos(int[] pos) {
        currPos = pos;
    }

    public void kill() {
        alive = false;
        pieces.remove(this);
    }

    public String getColor() {
        return color;
    }

    public int[] getPos() {
        return currPos;
    }

    public boolean isAlive() {
      return alive;
    }

    public abstract void move();
}
