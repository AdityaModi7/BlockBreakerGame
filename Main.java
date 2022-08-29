import javax.swing.JFrame;

public class Main {
    public static void main(String [] args) {
        GamePlay gamePlay = new GamePlay();
        JFrame game = new JFrame();
        game.setBounds(10,10,700,600);
        game.setTitle("BlockBreaker");
        game.setResizable(false);
        game.setVisible(true);
        game.add(gamePlay);
         }
}
