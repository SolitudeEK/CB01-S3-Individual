package nl.fontys.game.Object;

public class Fruit {
    private int x,y;

    public Fruit()
    {
        this.x=0;
        this.y=0;
    }
    public void setLocation(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    public Boolean isHere(int x, int y)
    {
        if(this.x == x && this.y == y)
            return true;
        return false;
    }
    public int getX(){return x;}
    public int getY(){return y;}
}
