package nl.fontys.game.Logic;

import nl.fontys.game.Interface.ISnake;
import nl.fontys.game.Interface.ISoloGame;
import nl.fontys.game.Interface.ISoloGameTest;
import nl.fontys.game.Object.Field;
import nl.fontys.game.Object.Fruit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SoloGame implements ISoloGame, ISoloGameTest {
    private final ISnake snake;
    private final Field field;
    private final Fruit fruit;
    private Boolean isPlaying=true;
    private int points=0;
    public SoloGame()
    {
        this.snake = new Snake();
        this.fruit= new Fruit();
        this.field = new Field();
        this.field.buildField();
        this.createFruit();
    }
    public Boolean getIsPlaying(){return isPlaying;}
    public void endSoloGame()
    {
        isPlaying = false;
        System.out.println("Your points "+points);
    }
    public void moveSnake(int turning) //throws WrongVectorException
    {
        int direction = snake.getSnakeVector();
        direction+=turning;
        if(direction<0) direction=3;
        if(direction>3) direction=0;
        snake.moveSnake(direction);
        //someCheck obstacle;
        int x=snake.getSnakePosX(0);
        int y=snake.getSnakePosY(0);
        int obstacle=field.getCell(x,y);
        //System.out.println(snake.getSize());
        //System.out.println("b");
        if(fruit.isHere(x,y)) {
            //System.out.println("ba");
            snake.addSnakePart();
            points++;
            this.createFruit();
        }
        if(obstacle==1||snake.isColide(x, y)) {
            this.endSoloGame();
        }

    }
    public String getSnakePos(int element)
    {
       return snake.getSnakePos(element);
    }
    public void setFruit(int x, int y)
    {
        fruit.setLocation(x,y);
    }
    public void createFruit()
     {
         Boolean isCreated=true;
         int x=0,y=0;
         while(isCreated) {
             x = ThreadLocalRandom.current().nextInt(1, field.getFieldLong()-1);
             y = ThreadLocalRandom.current().nextInt(1, field.getFieldHigh()-1);
             isCreated =snake.isHere(x, y);
         }
         fruit.setLocation(x,y);
     }
    public String frameToRender()
    {
        int[][] fld= new int[field.getFieldHigh()][field.getFieldLong()];
        for (int i=0; i<field.getFieldHigh();i++)
            fld[i]=field.getField(i);
        List<Integer> snkPos=snake.getSnakePos();
        for(int i=1; i<snkPos.size();i+=2)
        {
            fld[snkPos.get(i-1)][snkPos.get(i)] = 3;
        }
        StringBuilder s= new StringBuilder();
        for(int i=0;i<field.getFieldHigh();i++) {
            for (int j = 0; j < field.getFieldLong(); j++) {
                s.append(fld[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }
    public int getPoints(){
        return points;
    }
    //public Fruit getFruit(){return fruit;}
    public int[] getFruitPos(){
         int[] arr= new int[2];
         arr[1]=this.fruit.getX();
        arr[0]=this.fruit.getY();
     return arr;
     }
     public List<Integer> getSnakePos(){ return this.snake.getSnakePos();}



}
