import javax.swing.JPanel;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

        private boolean play = false;
        private int score = 0;
        private int totalBricks = 21;
        private Timer timer;
        private int delay = 8;
        private int playerX = 310;
        private int ballPosX = 120;
        private int ballPY = 350;
        private int ballXDir = -1;
        private int ballYDir = -2;

        private MapGenerator map; 

        public GamePlay() {
            map = new MapGenerator(3,7);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(delay,this);
            timer.start();
             }

        public void paint(Graphics g) {
            g.setColor(Color.black);
            g.fillRect(1, 1, 692, 592);

            map.draw((Graphics2D)g);

            g.setColor(Color.yellow);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0, 0, 692, 3);
            g.fillRect(684, 0, 3, 592);

            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString(""+score,600,30);

            g.setColor(Color.green);
            g.fillRect(playerX, 550, 100, 8);

            g.setColor(Color.blue);
            g.fillOval(ballPosX, ballPY, 20, 20);

            if(ballPY > 585) {
                play = false;
                ballPosX = 275;
                ballPY = 275;
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
       timer.start();
       if(play) { 
        if(new Rectangle(ballPosX, ballPY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
            ballYDir = -ballYDir;
        }

        A: for(int i = 0; i < map.map.length; i++) {
            for(int j = 0; j < map.map[i].length; j++) {
                if(map.map[i][j] > 0) {
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;
                    int brickX = j*brickWidth + 80;
                    int brickY = i*brickHeight + 50;

                   
                    Rectangle ballRect = new Rectangle(ballPosX,ballPY,20,20);
                    Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);

                    if(ballRect.intersects(rect)) {
                        map.setBrickValue(0, i, j);
                        totalBricks--;
                        score += 5;

                        if(ballPosX + 19 <= rect.x || ballPosX + 1 >= rect.x + rect.width) {
                            ballXDir = - ballXDir;
                        }
                        else {
                            ballYDir = -ballYDir;
                        }
                        break A;
                        
                    }


                    
                }
            }
        }
      
        ballPosX += ballXDir;
        ballPY += ballYDir;
       if(ballPosX < 0) {
        ballXDir = -ballXDir;
       } 
       if(ballPY < 0) {
        ballYDir = -ballYDir;
       }
       if(ballPosX > 670) {
        ballXDir = -ballXDir;
       }
    } 
    repaint();
       
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        if(playerX >= 580){
            playerX = 580;
        }
        else {
            moveRight();
        }
    }
    if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        if(playerX <= 10){
            playerX = 10;
        }
        else {
            moveLeft();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

 }
