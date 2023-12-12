package snakeStructures;

import basicdatastructures.Pair;
import enums.Direction;

public class MiscFunctions {

    public static Pair extractPairFromString(String pair){
        String[] points = pair.split(",");
        return new Pair(Integer.parseInt(points[0]),Integer.parseInt(points[1]));
    }

    public static Direction updateDirection(Pair head, Pair tail){
        Direction move = Direction.NO_DIRECTION;
        if (head.second>tail.second){
            move = Direction.DOWN;
        }else if (head.second<tail.second){
            move = Direction.UP;
        }else if (head.first<tail.first){
            move = Direction.LEFT;
        }else if (head.first>tail.first){
            move = Direction.RIGHT;
        }
        return move;
    }

}
