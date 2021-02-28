package Entity;

public class Eggs
{
    private int X;
    private int Y;
    private int eggHiz;

    public Eggs(int x, int y)
    {
        this.X = x;
        this.Y = y;
        this.eggHiz = 5;
    }

    public int getX()
    {
        return X;
    }

    public int getY()
    {
        return Y;
    }

    public void setX(int x)
    {
        this.X = x;
    }

    public void setY()
    {
        this.Y += eggHiz;
    }
}
