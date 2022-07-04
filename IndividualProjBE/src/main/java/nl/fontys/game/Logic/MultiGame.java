package nl.fontys.game.Logic;

import nl.fontys.game.Interface.IMultiGame;
import nl.fontys.game.Interface.IMultiGameTest;
import nl.fontys.game.Interface.ISnake;
import nl.fontys.game.Interface.ISnakeRed;
import nl.fontys.game.Object.Field;
import nl.fontys.game.Object.Fruit;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MultiGame implements IMultiGame, IMultiGameTest {
    private final ISnake snakeGreen;
    private final ISnakeRed snakeRed;
    private final Field field;
    private final Fruit fruit;
    private Boolean isPlaying=true;
    private String winner="";
    public MultiGame(){
        this.field=new Field();
        this.fruit= new Fruit();
        this.snakeGreen=new Snake();
        this.snakeRed=new Snake();
        this.snakeRed.snakeSetRed();
        this.field.buildField();
        this.createFruit();
    }
    //PHYSIC
    public void createFruit()
    {
        Boolean isColideGreen=true;
        Boolean isColideRed=true;
        int x=0,y=0;
        while(isColideGreen||isColideRed) {
            x = ThreadLocalRandom.current().nextInt(1, field.getFieldLong()-1);
            y = ThreadLocalRandom.current().nextInt(1, field.getFieldHigh()-1);
            isColideGreen =snakeGreen.isHere(x, y);
            isColideRed=snakeRed.isHere(x,y);
        }
        fruit.setLocation(x,y);
    }
    public void moveSnakes(int turningGreen, int turningRed) //throws WrongVectorException
    {
        //Green moving
        int dirGren = snakeGreen.getSnakeVector();
        dirGren+=turningGreen;
        if(dirGren<0) dirGren=3;
        if(dirGren>3) dirGren=0;
        snakeGreen.moveSnake(dirGren);
        //Red moving
        int dirRed = snakeRed.getSnakeVector();
        dirRed+=turningRed;
        if(dirRed<0) dirRed=3;
        if(dirRed>3) dirRed=0;
        snakeRed.moveSnake(dirRed);
        //Gatting position green
        int xGreen=snakeGreen.getSnakePosX(0);
        int yGreen=snakeGreen.getSnakePosY(0);
        int obstacleGreen=field.getCell(xGreen,yGreen);
        //Gatting position red
        int xRed=snakeRed.getSnakePosX(0);
        int yRed=snakeRed.getSnakePosY(0);
        int obstacleRed=field.getCell(xRed,yRed);
        //Checking colisons
        if(fruit.isHere(xGreen,yGreen)) {
            snakeGreen.addSnakePart();
            this.createFruit();
        }
        else if(fruit.isHere(xRed,yRed)){
            snakeRed.addSnakePart();
            this.createFruit();
        }
        if(xGreen==xRed&&yGreen==yRed){
            winner="Draw!";
            this.endGame();
        }
        else if(obstacleGreen==1||snakeGreen.isColide(xGreen, yGreen)||snakeRed.isColide(xGreen, yGreen)){//
            System.out.println(obstacleGreen+"\n"+snakeGreen.isColide(xGreen, yGreen)+"\n"+snakeRed.isColide(xGreen, yGreen));
            winner="Congrats, Red win";
            this.endGame();
        }
        else if (obstacleRed==1||snakeRed.isColide(xRed,yRed)||snakeGreen.isColide(xRed,yRed)) {//
            System.out.println(obstacleRed+"\n"+snakeRed.isColide(xRed,yRed)+"\n"+ snakeGreen.isColide(xRed,yRed));
            winner = "Congrats, Green win";
            this.endGame();
        }
    }
    public void endGame()
    {
        this.isPlaying = false;
        System.out.println(winner);
    }
    //GETTERS FOR RENDER
    public int[] getFruitPos(){
        int[] arr= new int[2];
        arr[1]=this.fruit.getX();
        arr[0]=this.fruit.getY();
        return arr;
    }
    public List<Integer> getSnakeGreenPos(){ return this.snakeGreen.getSnakePos();}
    public List<Integer> getSnakeRedPos(){ return this.snakeRed.getSnakePos();}
    public String getWinner(){return this.winner;}
    public Boolean getIsPlaying(){ return  this.isPlaying;}
}
