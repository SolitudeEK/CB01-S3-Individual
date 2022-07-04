package nl.fontys.game.Test;

import nl.fontys.game.Interface.IMultiGameTest;
import nl.fontys.game.Logic.MultiGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultiGameTest {

    @Test
    public void TestColide(){
        IMultiGameTest multiGame = new MultiGame();
        for(int i=0; i < 15; i++)
        {
            multiGame.moveSnakes(0,0);
        }
        Assertions.assertEquals(multiGame.getIsPlaying(), Boolean.FALSE);
    }

    @Test
    public void TestInit(){
        IMultiGameTest multiGame = new MultiGame();
        for(int i=0; i < 15; i++)
        {
            multiGame.moveSnakes(0,0);
        }
        Assertions.assertEquals(multiGame.getWinner(), "Congrats, Red win");
    }
}
