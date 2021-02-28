package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Credits extends GameState
{
    private BufferedImage image;
    
    public Credits(GameStateManager gsm)
    {
        this.gsm = gsm;
        init();
    }
    
    @Override
    public void init()
    {
        try 
        {
            image = ImageIO.read(new FileImageInputStream(new File("Credits.png")));
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
            gsm.setState(GameStateManager.MENUSTATE);
    }

    @Override
    public void keyReleased(int k)
    {
        
    }
}
