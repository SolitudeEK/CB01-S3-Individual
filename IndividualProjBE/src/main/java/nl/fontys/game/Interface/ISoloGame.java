package nl.fontys.game.Interface;

import java.util.List;

public interface ISoloGame {
    public Boolean getIsPlaying();
    public void endSoloGame();
    public void moveSnake(int turning);
    public int getPoints();
    public List<Integer> getSnakePos();
    public int[] getFruitPos();
}
