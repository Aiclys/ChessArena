// Declare package
package com.chess.GUI;

// Imports needed for the GUI of the  chess board
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

// Main class declaration
public class Board {
  // Attributes of the board
  final int SIZE = 8;
  final int SQUARE_SIZE = 100;
  String[] colors;

  /**
   * This is the constructor of the Board class,
   * which initializes all of the variables.
   */
  public Board(String[] colors) {
    this.colors = colors;
  }

  /**
   * This function makes the board visible and
   * draws 64 squares, either white or black
   */
  public void show(Graphics2D g2d) {

    int count = 0;

    for (int row = 1; row <= SIZE; row++) {

      for (int col = 1; col <= SIZE; col++) {

        if (count == 0) {
          g2d.setColor(Color.BLACK);
          count = 1;
        } else {
          g2d.setColor(Color.WHITE);
          count = 0;
        }
        g2d.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        count++;
      }
      count = (count = 0) ? 1 : 0;
    }
  }

}