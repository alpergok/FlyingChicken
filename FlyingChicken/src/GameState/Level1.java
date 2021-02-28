package GameState;

import Entity.Clouds;
import Entity.Cat;
import Entity.Eggs;
import Game.GamePanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;

public class Level1 extends GameState
{   
    private BufferedImage image_clouds;
    private BufferedImage image_evilcat;  
    private BufferedImage image_goldenegg;
    private BufferedImage image_kedi;
    private BufferedImage image_background;
    private BufferedImage image_levelTable;
    private BufferedImage image_timeTable;
    private BufferedImage image_scoreTable;
    private Image image_tavuk;
    private Clouds cloud1, cloud2;
    private int zorlukFlag;
    private int eggHiz = 1;
    private int score = 0;
    private int catHiz;
    private int chickenHiz;
    private int cloudHiz;
    private int chickenX = 250;
    
    private ArrayList <Eggs> goldenEggs;
    private ArrayList <Cat> cats;
    private ArrayList <Clouds> clouds;
    
    private Font gameFont;
    int t1 = 0;
    int t2 = 1500;
    int deltaTime;
    
    public Level1(GameStateManager gsm)
    {
        this.gsm = gsm;
        init();
    }
    
    public boolean checkCollapse(int i)
    {
        if(new Rectangle(chickenX+3, 18, 52, 60).intersects(new Rectangle(cats.get(i).getCatX(), cats.get(i).getCatY(),image_evilcat.getWidth()/24+5, image_evilcat.getHeight()/24)) 
                || new Rectangle(chickenX+3, 18, 52, 60).intersects(new Rectangle(cats.get(i).getCatX()+12, cats.get(i).getCatY()+30, image_evilcat.getWidth()/24+12, image_evilcat.getHeight()/24+3)))    
            return true;
        else return false;
    }
    
    public boolean isInDanger(int i)
    {
        if(Math.abs(cats.get(i).getCatY()-10) <= 400 && Math.abs(cats.get(i).getCatX() - chickenX) <= 60)
            return true;
        else return false;
    }
    
    public void clearLevel1()
    {
        score = 0;
        chickenX = 250;
    }
    
    public boolean checkHit(int j)
    {
        for(int i=0; i < cats.size(); i++)
        {
            if(new Rectangle(goldenEggs.get(j).getX()+16, goldenEggs.get(j).getY()+45, image_goldenegg.getWidth()/5, image_goldenegg.getHeight()/5).intersects(new Rectangle(cats.get(i).getCatX(), cats.get(i).getCatY(),image_evilcat.getWidth()/24+5, image_evilcat.getHeight()/24)) 
                || new Rectangle(goldenEggs.get(j).getX()+16, goldenEggs.get(j).getY()+45, image_goldenegg.getWidth()/5, image_goldenegg.getHeight()/5).intersects(new Rectangle(cats.get(i).getCatX()+12, cats.get(i).getCatY()+30, image_evilcat.getWidth()/24+19, image_evilcat.getHeight()/24+3)))    
            {
                cats.remove(i);
                score++;
                if(score >= 15)
                {
                    CutScene.currentLevel = 1;
                    clearLevel1();
                    gsm.setState(GameStateManager.CUTSCENE);
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void init()
    {
        try
        {
            image_levelTable = ImageIO.read(new FileImageInputStream(new File("LevelLabel.png")));
            image_timeTable = ImageIO.read(new FileImageInputStream(new File("TimeLabel.png")));
            image_scoreTable = ImageIO.read(new FileImageInputStream(new File("ScoreLabel.png")));     //SCORE TABLE IMGAGE
            image_clouds = ImageIO.read(new FileImageInputStream(new File("GameBackground.png")));     //BACKGROUND IMAGE
            image_tavuk = new ImageIcon("ChickenGIF.gif").getImage();                                  //CHICKEN GIF
            image_goldenegg = ImageIO.read(new FileImageInputStream(new File("GoldenEgg.png")));       //GOLDEN EGG IMAGE
            image_kedi = ImageIO.read(new FileImageInputStream(new File("Cat.png")));                  //INNOCENT CATS
            image_evilcat = ImageIO.read(new FileImageInputStream(new File("EvilCat.png")));           //EVIL CATS
        }
        catch (Exception e) 
        {
            //JOptionPane.ERROR_MESSAGE("Error");
        }
        
        clouds = new ArrayList<Clouds>();
        cats = new ArrayList<Cat>();
        goldenEggs = new ArrayList<Eggs>();
        
        cloudHiz = 3;
        cloud1 = new Clouds(0, cloudHiz);
        cloud2 = new Clouds(cloud1.getY()+1720, cloudHiz);
        catHiz = 3;
        chickenHiz = 20;
        deltaTime = 2000;
        zorlukFlag = 0;
        GamePanel.startTime = System.currentTimeMillis();
    }

    @Override
    public void update() 
    {   
        if(zorlukFlag == 0 && GamePanel.time / 1000 >= 15)
        {   
            zorlukFlag = 1;
            catHiz += 2;
            cloudHiz += 2;
            deltaTime = 1500;
        }
        //Her deltaTime kadar sürede bir kedi spawn
        if(t2-t1 >= deltaTime)                             
        {
            cats.add(new Cat(catHiz));
            t2=0; 
        }
        t2+=20;
        
        //Ekran dışına çıkan kedileri siler
        for(int i=0; i<cats.size();i++)                     
        {
            if(cats.get(i).getCatY() <= -100) 
            {
                cats.remove(i);
               if(score > 0 ) score--;
            }
        }
        
        //Ekran dışına çıkan yumurtaları siler
        for(int i=0; i < goldenEggs.size(); i++)
        {
            if(goldenEggs.get(i).getY() >= 720)
                goldenEggs.remove(i);
        }
        
        //Background hareketi
        if(cloud1.getY() <= -1720) 
        {
            cloud1.setY(1710);
        }
        if(cloud2.getY() <= -1720) 
        {
            cloud2.setY(1710);
        }
    }

    @Override
    public void draw(Graphics2D g)
    {
        for(int i=0; i < clouds.size(); i++)
        {
            g.drawImage(image_clouds, clouds.get(i).getX(), clouds.get(i).getY(), null);
            clouds.get(i).setHiz();
        }
        
        g.drawImage(image_background, 0, 0, null);   
        g.drawImage(image_clouds, cloud1.getX(), cloud1.getY(), null);
        g.drawImage(image_clouds, cloud2.getX(), cloud2.getY(), null);
        cloud1.setHiz();
        cloud2.setHiz();
        
       for(int i=0;i<cats.size();i++)
       {     
            if(checkCollapse(i))
            {
                FailScene.currentLevel = 1;
                clearLevel1();
                gsm.setState(GameStateManager.FAILSCENE);
            }
            else
            {
                if(isInDanger(i))
                    g.drawImage(image_evilcat, cats.get(i).getCatX(), (cats.get(i).getCatY()), image_evilcat.getWidth()/12, image_evilcat.getHeight()/12, null);
                else        
                    g.drawImage(image_kedi, cats.get(i).getCatX(), (cats.get(i).getCatY()), image_kedi.getWidth()/12, image_kedi.getHeight()/12, null);
               /* 
                //HITBOX TEST:
                g.drawRect(chickenX+3, 18, 52, 60);
                g.setColor(Color.white);
                g.drawRect(cats.get(i).getCatX()+12, cats.get(i).getCatY()+30, image_evilcat.getWidth()/24+19, image_evilcat.getHeight()/24+3);
                g.drawRect(cats.get(i).getCatX(), cats.get(i).getCatY(), image_evilcat.getWidth()/24+7, image_evilcat.getHeight()/24);
               */
                cats.get(i).setCatY();
            }
        }
      
       for(int j=0; j < goldenEggs.size(); j++)
       {
            /*
            //EGG HITBOX TEST
            g.setColor(Color.white);
            g.drawRect(goldenEggs.get(j).getX()+16, goldenEggs.get(j).getY()+45, image_goldenegg.getWidth()/5, image_goldenegg.getHeight()/5);
            */
            if(checkHit(j))
                goldenEggs.remove(j);
            else
            {
                g.drawImage(image_goldenegg, goldenEggs.get(j).getX()+16, goldenEggs.get(j).getY()+45, image_goldenegg.getWidth()/5, image_goldenegg.getHeight()/5, null);
                goldenEggs.get(j).setY();
            }
        }
       gameFont = GamePanel.customFont.deriveFont(35f);
       g.setFont(gameFont);
       g.setColor(Color.BLACK);
       //DRAW CHICKEN
       g.drawImage(image_tavuk, chickenX, 15, null);
       
       //LEVEL YAZDIRMA
       g.drawImage(image_levelTable, 45, 400, null);
       g.drawString((String.valueOf(1)), 135, 520);
       
       //SCORE YAZDIRMA
       g.drawImage(image_scoreTable, 1050, 82, null);
       g.drawString((String.valueOf(score)), 1137, 200);
       
       //TIMER YAZDIRMA
       g.drawImage(image_timeTable, 45, 82, null);
       g.drawString(String.format("%.2f" ,(double)GamePanel.time / 1000), 95, 200);
    }

    @Override
    public void keyPressed(int k)
    {
        if(k == KeyEvent.VK_LEFT)
        {
            if(chickenX <= 250)
                chickenX = 250;
            else
                chickenX -= chickenHiz;
        }
        
        else if (k == KeyEvent.VK_RIGHT)
        {
            if(chickenX >= 975)
                chickenX = 975;
            else
                chickenX += chickenHiz;
        }
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_DOWN)
        {
           goldenEggs.add(new Eggs(chickenX + 5, 15));
        }
    }

    @Override
    public void keyReleased(int k)
    {
        
    }
}
