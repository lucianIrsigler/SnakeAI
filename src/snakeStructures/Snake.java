package snakeStructures;

import basicdatastructures.Pair;
import enums.Direction;

import java.util.ArrayList;
import java.util.Arrays;

public class Snake extends baseSnakeStructure{
    private Pair head = new Pair(-1,-1);
    private Pair tail = new Pair(-1,-1);

    public int snakeNum;
    public boolean isAlive;
    public int kills;
    public int length;
    public ArrayList<String>bodyParts = new ArrayList<>();
    public Direction directionMoving;
    public Snake(int snakeNum){
        this.snakeNum=snakeNum;
    }


    public Pair getHead(){
        return head;
    }

    public Pair getTail(){
        return tail;
    }

    //alive/dead length kills headX,headY bodyX,bodyY bodyX,bodyY ... tailX,tailY
    @Override
    public void update(String snakeLine) {
        String[] snakeArgs = snakeLine.split(" ");
        bodyParts.clear();

        //update alive/dead status
        isAlive = snakeArgs[0].equals("alive");
        if (isAlive) {
            length = Integer.parseInt(snakeArgs[1]);
            kills = Integer.parseInt(snakeArgs[2]);
            head = MiscFunctions.extractPairFromString(snakeArgs[3]);
            tail = MiscFunctions.extractPairFromString(snakeArgs[snakeArgs.length - 1]);

            //update body
            bodyParts.addAll(Arrays.asList(snakeArgs).subList(3, snakeArgs.length));

            //update direction
            directionMoving = MiscFunctions.updateDirection(head, tail);
        }
    }

}
