package nl.fontys.game.Interface;

import java.util.List;

public interface ISnake {
    public Boolean isColide(int x, int y);
    public Boolean isHere(int x, int y);
    public void addSnakePart();
    public String getSnakePos(int element);
    public int getSnakeVector();
    public void moveSnake(int vector);
    public int getSnakePosX(int element);
    public int getSnakePosY(int element);
    public List<Integer> getSnakePos();
}
