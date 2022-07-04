package nl.fontys.game.Test;

import nl.fontys.game.Interface.ISnakeTest;
import nl.fontys.game.Logic.Snake;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SnakeTest {
    @Test
    public void checkInit()
    {
        ISnakeTest snake = new Snake();
        Assertions.assertEquals(snake.getSize(), 2);
    }
    @Test
    public void checkGrow()
    {
        ISnakeTest snake = new Snake();
        snake.addSnakePart();
        snake.addSnakePart();
        Assertions.assertEquals(snake.getSize(), 4);
    }
    @Test
    public void checkMoveForward()
    {
        ISnakeTest snake = new Snake();
        snake.moveSnake(0);
        Assertions.assertEquals(snake.getSnakePosX(0), 3);
        Assertions.assertEquals(snake.getSnakePosY(0), 1);
    }
    @Test
    public void checkMoveRight()
    {
        ISnakeTest snake = new Snake();
        snake.moveSnake(1);
        Assertions.assertEquals(snake.getSnakePosX(0), 2);
        Assertions.assertEquals(snake.getSnakePosY(0), 2);
    }
    @Test
    public void checkMoveLeft()
    {
        ISnakeTest snake = new Snake();
        snake.moveSnake(1);
        snake.moveSnake(-1);
        Assertions.assertEquals(snake.getSnakePosX(0), 2);
        Assertions.assertEquals(snake.getSnakePosY(0), 1);
    }
    @Test
    public void checkColide()
    {
        ISnakeTest snake = new Snake();
        snake.moveSnake(-1);
        Assertions.assertEquals(snake.isColide(2,1), Boolean.TRUE);
    }
    @Test
    public void checkNotColide()
    {
        ISnakeTest snake = new Snake();
        snake.moveSnake(-1);
        Assertions.assertEquals(snake.isColide(20,10), Boolean.FALSE);
    }
    @Test
    public void checkHere()
    {
        ISnakeTest snake = new Snake();
        snake.moveSnake(0);
        Assertions.assertEquals(snake.isHere(3,1), Boolean.TRUE);
    }
    @Test
    public void checkNotHere()
    {
        ISnakeTest snake = new Snake();
        snake.moveSnake(0);
        Assertions.assertEquals(snake.isHere(30,10), Boolean.FALSE);
    }
}
