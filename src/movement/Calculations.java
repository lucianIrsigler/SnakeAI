package movement;

import basicdatastructures.Grid;
import heuristic.ManhattanHeuristic;
import snakeStructures.Apple;
import snakeStructures.GameManager;
import snakeStructures.Snake;

import java.util.*;

/**
 * Used to calculate things like quadrants etc
 */
public class Calculations {
    public int gridX;
    public int gridY;
    private final ManhattanHeuristic heuristic = new ManhattanHeuristic();
    public int xAxis;
    public int yAxis;

    private void fillMap(){
    }


    public Calculations(){
    }


    public Calculations(Grid grid){
        this.gridX=grid.rows;
        this.gridY=grid.cols;

         xAxis = gridX / 2;
         yAxis = gridY / 2;
         fillMap();

    }


    public boolean isWorthGoingFor(GameManager manager, int snakeNum){
        return  agentReachingAppleFirst(manager,snakeNum);
    }
    /**
     * Checks if the given snakeNum will reach the apple first
     * @param manager game manager
     * @param snakeNum snakeNum to check
     * @return if the snakeStructures.Snake will reach the apple first
     */
    private boolean agentReachingAppleFirst(GameManager manager, int snakeNum){
        Apple a = manager.getApple();
        TreeMap<Integer,Integer>snakeDistances = manager.getSnakeDistances();
        Collection<Integer> values = snakeDistances.values();
        Integer minValue = Collections.min(values);
        int n = Collections.frequency(values,minValue);
        //if n>1 then snake will collide
        return (n == 1)
                && Objects.equals(snakeDistances.get(snakeNum), minValue)
                && AppleWorthGoingFor(a);
    }

    private boolean AppleWorthGoingFor(Apple a){
        return a.getValue()>=0;
    }

    /*
        If snake heads are less than 3 Manhattan units way, they are close
     */
    public int calculateSnakeDistance(Snake s1, Snake s2){
        return (int)heuristic.calculate(s1.getHead(),s2.getHead());
    }



}
