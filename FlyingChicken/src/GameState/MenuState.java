package GameState;

import Game.GamePanel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class MenuState extends GameState
{
    private Image image;
    private int currentChoice = 0;
    private String[] options = {"   START", "HOW TO PLAY", "  CREDITS", "    EXIT"};
    
    public MenuState(GameStateManager gsm)
    {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init()
    {
        try 
        {   
            image = ImageIO.read(new FileImageInputStream(new File("MainMenuBackground.png")));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
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
        
        // draw menu options
        for(int i = 0; i < options.length; i++)
        {
            if(i == currentChoice)
                g.setColor(Color.RED);
            else
                g.setColor(Color.LIGHT_GRAY);
            
            g.drawString(options[i], 561, 402 + (i * 61));
        }
    }
    private void select()
    {
        if(currentChoice == 0)
        {
            gsm.setState(GameStateManager.LEVEL1);
        }
        if(currentChoice == 1)
        {
            gsm.setState(GameStateManager.HOWTOPLAY);
        }
        if(currentChoice == 2)
        {
            gsm.setState(GameStateManager.CREDITS);
        }
        if(currentChoice == 3)
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
