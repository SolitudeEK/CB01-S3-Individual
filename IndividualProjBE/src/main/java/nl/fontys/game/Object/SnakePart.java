package nl.fontys.game.Object;

import org.springframework.stereotype.Component;

@Component
public class SnakePart {
    private int positionX;
    private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public SnakePart(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public SnakePart(){}
    public void setPostion(int x, int y)
    {
        positionX=x;
        positionY=y;
    }

}
