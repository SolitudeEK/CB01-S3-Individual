package nl.fontys.game.Interface;

import java.util.List;

public interface IMultiGame {
    public void createFruit();
    public void moveSnakes(int turningGreen, int turningRed);
    public int[] getFruitPos();
    public List<Integer> getSnakeGreenPos();
    public List<Integer> getSnakeRedPos();
    public String getWinner();
    public Boolean getIsPlaying();
}
