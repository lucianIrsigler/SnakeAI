package movement;

import basicdatastructures.Grid;
import basicdatastructures.Pair;
import heuristic.Heuristic;
import heuristic.ManhattanHeuristic;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {

    private final Heuristic heuristic = new ManhattanHeuristic();
    int rows;
    int columns;

    public FloodFill(int x,int y){
        this.rows=x;
        this.columns=y;
    }

    public int floodFill(Grid grid, Pair src){
        Pair[] pairs = new Pair[]{
                new Pair(0,1),
                new Pair(1,0),
                new Pair(0,-1),
                new Pair(-1,0),
        };

        Queue<Pair> queue = new LinkedList<>();
        boolean[][]visited = new boolean[rows][columns];
        queue.add(src);
        visited[src.first][src.second]=true;
        int numFilled = -1;


        while (!queue.isEmpty()){
            Pair top = queue.poll();
            numFilled++;
            for (Pair p : pairs){
                Pair neighbour = top.add(p);
                if (heuristic.calculate(src,neighbour)>14){
                    continue;
                }

                if ((0>neighbour.first)||(0>neighbour.second)||(neighbour.first>=rows)||(columns<=neighbour.second)||
                        grid.grid[neighbour.first][neighbour.second]!=0){
                    continue;
                }
                if (!visited[neighbour.first][neighbour.second]){
                    visited[neighbour.first][neighbour.second]=true;
                    queue.add(neighbour);
                }
            }
        }

        return numFilled;
    }
}
