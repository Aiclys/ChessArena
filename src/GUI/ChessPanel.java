package com.chess.GUI; 

import com.chess.GUI.Board;
import java.awt.*;
import javax.swing.JPanel;

public class ChessPanel extends JPanel implements Runnable {

  public static final int WIDTH = 1100;
  public static final int HEIGHT = 800;
  int FPS = 60;
  Thread mainThread;
  Board board = new Board();

  public ChessPanel() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.GRAY);
  }

  @Override
  public void run() {
    while(mainThread != null) {
      System.out.println("This test was executed successfully");
    }
     
  }

  public void gameLoop() {

  }

  public void startGame() {
    mainThread = new Thread(this);
    mainThread.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    board.show(g2d);
  }
}
