package com.chess;

import com.chess.GUI.Board;
import com.chess.GUI.ChessPanel;
import javax.swing.*;
import java.awt.*;

public class Arena {

    public static void main(String[] args) {
      // Create main Window
      JFrame window = new JFrame("ChessArena");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);

      // Add ChessPanel to the main Window
      ChessPanel cp = new ChessPanel();
      window.add(cp);
      window.pack();

      // Make the main Window visible
      window.setSize(1100, 800);
      window.setLocationRelativeTo(null);
      window.setVisible(true);

    }
}
