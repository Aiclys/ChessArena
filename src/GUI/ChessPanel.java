//package ...;

import java.awt.*;
import javax.swing.JPanel;

public class ChessPanel extends JPanel implements Runnable {

  int FPS = 60;
  Thread mainThread;
  public static final int WIDTH = 1100;
  public static final int HEIGHT = 800;

  public ChessPanel() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);
  }

  @Override
  public void run() {

  }

  public void gameLoop() {

  }

  public void startGame() {
    mainThread = new Thread(this);
    mainThread.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
