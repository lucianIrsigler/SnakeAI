package pathfinding;

import basicdatastructures.Grid;
import basicdatastructures.Pair;
import heuristic.Heuristic;
import heuristic.ManhattanHeuristic;
import movement.FloodFill;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BestFirstSearch implements pathfinding {
    private final int rows;
    private final int cols;
    private final FloodFill floodFill;

    public BestFirstSearch(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.floodFill = new FloodFill(rows,cols);
    }

    Pair[] pairs = new Pair[]{
            new Pair(0,-1),//D
            new Pair(-1,0),//L
            new Pair(0,1),//U
            new Pair(1,0),//R
    };

    @Override
    public Pair findPath(Grid grid, Pair src, Pair dest) {
        int max = Integer.MIN_VALUE;

        Pair nextMove = src;
        for (Pair p : pairs){
            Pair neighbour = src.add(p);
            if (neighbour.first<0 || neighbour.first>=rows ||
                    neighbour.second<0 || neighbour.second>=cols){
                continue;
            }
            if (grid.grid[neighbour.first][neighbour.second]==-1){
                continue;
            }

            int numFilled = floodFill.floodFill(grid,neighbour);

            if (numFilled>=max){
                max = numFilled;
                nextMove = neighbour;
            }
        }
        return nextMove;
    }
}
