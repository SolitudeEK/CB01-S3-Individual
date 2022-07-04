package nl.fontys.game.Logic;

import nl.fontys.game.Interface.ISnake;
import nl.fontys.game.Interface.ISnakeRed;
import nl.fontys.game.Interface.ISnakeTest;
import nl.fontys.game.Object.SnakePart;
import nl.fontys.game.XtraException.WrongVectorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Snake implements ISnake, ISnakeRed, ISnakeTest {
    private List<SnakePart> snake=new ArrayList<>();
    @Autowired
    public Snake()
    {
        snake.add(new SnakePart(2,1));
        snake.add(new SnakePart(1,1));
    }
    public void snakeSetRed()
    {
        snake.remove(0);
        snake.remove(0);
        snake.add(new SnakePart(27,1));
        snake.add(new SnakePart(28,1));
    }
     public int getSnakeVector() //throws WrongVectorException
     {
         int x =snake.get(0).getPositionX();
         int y =snake.get(0).getPositionY();
         int x2 =snake.get(1).getPositionX();
         int y2 =snake.get(1).getPositionY();
         x=x-x2;
         y=y-y2;
         if(x==1&&y==0) return 0;
         else if(x==0&&y==1) return 1;
         else if(x==-1&&y==0) return 2;
         else if(x==0&&y==-1) return 3;
         //else throw new WrongVectorException();
         return 0;
     }
     public void moveSnake(int vector)
     {
         int x,xOld,xCurrent,y,yCurrent,yOld;
         if(vector==0) { x=1; y=0; }
         else if(vector==1) { x=0; y=1; }
         else if(vector==2)  { x=-1; y=0; }
         else { x=0; y=-1; }
         xCurrent =snake.get(0).getPositionX();
         yCurrent =snake.get(0).getPositionY();
         snake.get(0).setPostion(xCurrent+x, yCurrent+y);
         for(int i=1;i<snake.size();i++)
         {
             xOld =snake.get(i).getPositionX();
             yOld =snake.get(i).getPositionY();
             snake.get(i).setPostion(xCurrent,yCurrent);
             xCurrent=xOld;
             yCurrent=yOld;
         }
     }
     public String getSnakePos(int element)
     {
         int x =snake.get(element).getPositionX();
         int y =snake.get(element).getPositionY();
         return x+" "+y;
     }
     public List<Integer> getSnakePos()
     {
         List<Integer> poses = new ArrayList<>();
         for (SnakePart sp: snake)
         {
             poses.add(sp.getPositionY());
             poses.add(sp.getPositionX());
         }
         return poses;
     }
     public int getSnakePosX(int element)
     {
         return snake.get(element).getPositionX();
     }
     public int getSnakePosY(int element)
    {
        return snake.get(element).getPositionY();
    }
     public void addSnakePart()
     {
         int x=snake.get(snake.size()-1).getPositionX();
         int y=snake.get(snake.size()-1).getPositionY();
         snake.add(new SnakePart(x,y));
     }
    public Integer getSize()
    {
        return snake.size();
    }
    public Boolean isHere(int x, int y)
    {
        for(var snakePart : snake ){
            if(snakePart.getPositionX()==x&&snakePart.getPositionY()==y) return true;
        }
        return false;
    }
    public Boolean isColide(int x, int y)
    {
        for(int i=1;i<snake.size();i++){
            if(snake.get(i).getPositionX()==x&&snake.get(i).getPositionY()==y) return true;
        }
        return false;
    }
 }


