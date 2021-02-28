package Game;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{
    
    public static JFrame window;
    
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Flying Chicken");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
