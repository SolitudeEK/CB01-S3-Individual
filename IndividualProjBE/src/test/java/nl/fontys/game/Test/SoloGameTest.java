package nl.fontys.game.Test;

import nl.fontys.game.Interface.ISoloGame;
import nl.fontys.game.Interface.ISoloGameTest;
import nl.fontys.game.Logic.Snake;
import nl.fontys.game.Logic.SoloGame;
import nl.fontys.game.Object.Field;
import nl.fontys.game.Object.Fruit;
import nl.fontys.game.XtraException.WrongVectorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SoloGameTest {

    @Test
    public void checkSnakeMovementForward()  {
        //Arrange
        ISoloGameTest soloGame =new SoloGame();
        //Act
        soloGame.moveSnake(0);
        //Assert
        Assertions.assertEquals("3 1",soloGame.getSnakePos(0));
    }
    @Test
    public void checkSnakeMovementRight() {
        //Arrange
        ISoloGameTest soloGame= new SoloGame();
        //Act
        soloGame.moveSnake(-1);
        //Assert
        Assertions.assertEquals("2 0",soloGame.getSnakePos(0));
    }
    @Test
    public void checkSnakeMovementLeft() {
        //Arrange
        ISoloGameTest soloGame= new SoloGame();
        //Act
        soloGame.moveSnake(1);
        //Assert
        Assertions.assertEquals("2 2",soloGame.getSnakePos(0));
    }
    @Test
    public void checkSnakeMovementForwardForwardLeft() {
        //Arrange
        ISoloGameTest soloGame= new SoloGame();
        //Act
        soloGame.moveSnake(1);
        soloGame.moveSnake(0);
        soloGame.moveSnake(0);
        //Assert
        Assertions.assertEquals("2 4",soloGame.getSnakePos(0));

    }
    @Test
    public void checkSnakeMovementBody() {
        //Arrange
        ISoloGameTest soloGame= new SoloGame();
        //Act
        soloGame.moveSnake(1);
        soloGame.moveSnake(0);
        soloGame.moveSnake(0);
        //Assert
        Assertions.assertEquals("2 3",soloGame.getSnakePos(1));
    }
    @Test
    public void checkFruitEated()
    {
        //Arrange
        ISoloGameTest soloGame= new SoloGame();
        //Act
        System.out.println(soloGame.frameToRender());
        soloGame.setFruit(3,1);
        soloGame.moveSnake(0);
        soloGame.moveSnake(0);
        System.out.println(soloGame.frameToRender());
        //Assert
        Assertions.assertEquals("2 1",soloGame.getSnakePos(2));
    }
    @Test
    public void checkGameStarted()
    {
        //Arrange
        ISoloGameTest soloGame= new SoloGame();
        //Act
        soloGame.moveSnake(0);
        //Assert
        Assertions.assertEquals(Boolean.TRUE,soloGame.getIsPlaying());
    }
    @Test
    public void checkGameEnded()
    {
        //Arrange
        ISoloGameTest soloGame= new SoloGame();
        //Act
        soloGame.moveSnake(-1);
        //Assert
        Assertions.assertEquals(Boolean.FALSE,soloGame.getIsPlaying());
    }
}
