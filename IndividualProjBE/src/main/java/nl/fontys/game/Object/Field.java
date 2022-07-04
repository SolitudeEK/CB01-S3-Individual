package nl.fontys.game.Object;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class Field {
    //TODELETE
    public int getFieldLong() {
        return fieldLong;
    }

    public int getFieldHigh() {
        return fieldHigh;
    }
    //END DELETE
    private int fieldLong=30;
    private int fieldHigh=30;
    private List<int[]> tails= new ArrayList<>();
    private int[][] field;
    @Autowired
    public Field()
    {

    }

    public int getCell(int x, int y)
    {
    return field[y][x];
    }

    public void  buildField()
    {
        field=new int[fieldHigh][fieldLong];
        for(int i=0;i<fieldHigh;i++)
        {
            for(int j=0;j<fieldLong;j++)
            {
                if(j==0||i==0||i==fieldHigh-1||j==fieldLong-1)
                    field[i][j]=1;
                else
                    field[i][j]=0;
            }
        }
    }

    public String showField()
    {
        StringBuilder s= new StringBuilder();
        for(int i=0;i<fieldHigh;i++)
        {
            for(int j=0;j<fieldLong;j++)
            {
              s.append(field[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }
    public int[] getField(int line)
    {
        return field[line].clone();
    }



}
