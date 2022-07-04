package nl.fontys.game.Interface;

import java.util.List;

public interface ISoloGameTest {
    public Boolean getIsPlaying();
    public void endSoloGame();
    public void moveSnake(int turning);
    public int getPoints();
    public List<Integer> getSnakePos();
    public int[] getFruitPos();
    public void setFruit(int x, int y);
    public String frameToRender();
    public String getSnakePos(int element);
}
