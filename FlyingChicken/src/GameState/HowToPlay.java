package GameState;

import Game.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class HowToPlay extends GameState
{
    private int currentChoice = 0;
    private String[] options = {"NEXT LEVEL", "MAIN MENU"};
    private BufferedImage image;
    
    public HowToPlay(GameStateManager gsm)
    {
        this.gsm = gsm;
        init();
    }
    
    @Override
    public void init()
    {
        try 
        {
            image = ImageIO.read(new FileImageInputStream(new File("Controls.png")));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.drawImage(image, 0, 0, null);
    }
    
    @Override
    public void keyPressed(int k)
    {
        if(k == KeyEvent.VK_ESCAPE)
        {   
            currentChoice = 0;
            try 
            {
                image = ImageIO.read(new FileImageInputStream(new File("Controls.png")));
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
            }
            gsm.setState(GameStateManager.MENUSTATE);
        }
        
        if(k == KeyEvent.VK_LEFT)
        {
            currentChoice--;
            if(currentChoice == -1)
            {
                currentChoice = 0;
            }
            try {
                image = ImageIO.read(new FileImageInputStream(new File("Controls.png")));
            } catch (IOException ex) {
                Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(k == KeyEvent.VK_RIGHT)
        {
            currentChoice++;
            if(currentChoice == options.length)
            {
                currentChoice = 1;
            }
            try 
            {
                image = ImageIO.read(new FileImageInputStream(new File("Objectives.png")));
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(int k)
    {
        
    }
}
