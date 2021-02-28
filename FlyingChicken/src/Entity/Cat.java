package Entity;

public class Cat
{
    private int catX;
    private int catY;
    private int catHiz;
    
    public Cat(int hiz)
    {
        catX = (int) getRandom(250, 950);
        catY = 750;
        catHiz = hiz; 
    }
    
    public static double getRandom(double min, double max)
    {
        double x = (int)(Math.random() * ((max-min)+ 1)) + min;
        return x;
    }

    public int getCatX()
    {
        return catX;
    }

    public int getCatY()
    {
        return catY;
    }

    public void setCatX()
    {
        this.catX += this.catHiz;
    }

    public void setCatY()
    {
        this.catY -= this.catHiz;
    }

    public void setCatHiz(int catHiz)
    {
        this.catHiz = catHiz;
    }
}
