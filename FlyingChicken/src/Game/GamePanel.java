package Game;

import GameState.GameStateManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
    //PIXEL FONT
    public static Font customFont;
    
    //DIMENSIONS
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int SCALE = 2;
    
    //TIMER
    public static long startTime = System.currentTimeMillis();
    public static long endTime; 
    public static long time;
    
   //GAME THREAD
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    
    //IMAGE
    private BufferedImage image;
    private Graphics2D g;
    
    //GAME STATE MANAGER
    private GameStateManager gsm;

    public GamePanel()
    {
        super();
        setPreferredSize(new Dimension(1280, 720));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify()
    {
        super.addNotify();
        if(thread == null)
        {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }
	
    private void init()
    {	
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        g = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();
        
        try 
        {
            try
            {
                customFont = Font.createFont(Font.TRUETYPE_FONT, new File("pixelmix.ttf")).deriveFont(20f);
            }
            catch (FontFormatException ex)
            {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            
            //REGISTER FONT
            ge.registerFont(customFont);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g.setFont(customFont);
    }
    
     @Override
    public void run()
    {
        init();
        long wait;
        long start;
        long elapsed;
        
        // GAME LOOP
        while(running)
        {
            start = System.nanoTime();
            
            update();
            draw();
            drawToScreen();
            
            endTime = System.currentTimeMillis();
            time = endTime - startTime;
            //System.out.println("time: " + (time / 1000));
            
            elapsed = System.nanoTime() - start;
            
            wait = targetTime - elapsed / 1000000;
            if(wait < 0) wait = 5;

            try
            {
                Thread.sleep(wait);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void update()
    {
        gsm.update();
    }

    private void draw()
    {
        gsm.draw(g);
    }
    
    private void drawToScreen()
    {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, 1280, 720, null);
        g2.dispose();
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        gsm.keyReleased(e.getKeyCode());
    }
}
