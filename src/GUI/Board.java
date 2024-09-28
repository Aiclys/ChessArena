// Declare package
//package com.chess.GUI;

// Imports needed for the GUI of the  chess board
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

// Main class declaration
public class Board {
    // Attributes of the board
    int size;
    int[][] boardArr;

    /**
     * This is the constructor of the Board class, 
     * which initializes all of the variables.
    */
    public Board() {
      size = 8;
      boardArr = new int[8][8];
    }

    /**
     * This function makes the board visible.
    */
    public void show() {
    }

    /**
     * This function creates all 64 Fields, making them black or white.
    */
    public void initializeFields() {
      Graphics g;
      //Graphics2D g2d = (Graphics2D) g;  // Creates graphics for the square 
      int count = 1;
      for(int i = 1; i <= 8; i++) {    // For-loop for the rows of the chess board 
        for(int j = 1; j <= 8; j++) {  // For-loop for the columns of the chess board
          if(count%2 == 0) {
              
          } else {

          }
          count++;
        }
      }
    }

    
}
