package GameState;

import Game.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class EndScene extends GameState
{
    public static int currentLevel = 0;
    
    private int currentChoice = 0;
    private String[] options = {"MAIN MENU", " CREDITS", "   EXIT"};
    private Color titleColor;
    private Font titleFont;
    private BufferedImage image;
    
    public EndScene(GameStateManager gsm)
    {
        this.gsm = gsm;
        init();
    }
    
    @Override
    public void init()
    {
        try
        {
            titleColor = new Color(128, 128, 128);
            titleFont = new Font("Arial", Font.PLAIN, 25);
            image = ImageIO.read(new FileImageInputStream(new File("EndScene.png")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.setFont(GamePanel.customFont);
        g.drawImage(image, 0, 0, null);
        
        // DRAW MENU OPTIONS
        for(int i = 0; i < options.length; i++)
        {
            if(i == currentChoice)
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);
            
            g.drawString(options[i], 574, 402 + (i * 61));
        }
    }
    
    private void select()
    {
        if(currentChoice == 0)
        {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if(currentChoice == 1)
        {
            gsm.setState(GameStateManager.CREDITS);
        }
        if(currentChoice == 2)
        {
            System.exit(0);
        }
    }   

    @Override
    public void keyPressed(int k)
    {
        if(k == KeyEvent.VK_ENTER)
        {
            select();
        }
        
        if(k == KeyEvent.VK_UP)
        {
            currentChoice--;
            if(currentChoice == -1)
            {
                currentChoice = options.length - 1;
            }
        }
        
        if(k == KeyEvent.VK_DOWN)
        {
            currentChoice++;
            if(currentChoice == options.length)
            {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k)
    {
        
    }
}
