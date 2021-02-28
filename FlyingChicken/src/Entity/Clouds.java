package Entity;

public class Clouds
{
    private int X;
    private int Y;
    private int cloudHiz;

    public Clouds(int hiz)
    {
        this.X = 0;
        this.Y = 740;
        this.cloudHiz = hiz;
    }

    public Clouds(int firstY, int hiz)
    {
        this.X = 0;
        this.Y = firstY;
        this.cloudHiz = hiz;
    }

    public int getX()
    {
        return X;
    }

    public int getY()
    {
        return Y;
    }

    public void setHiz()
    {
        this.Y -= cloudHiz;
    }
    
    public void setY(int y)
    {
        this.Y = y;
    }

    public void setCloudHiz(int cloudHiz)
    {
        this.cloudHiz = cloudHiz;
    }     
}
