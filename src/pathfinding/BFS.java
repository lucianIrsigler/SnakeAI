package pathfinding;

import basicdatastructures.Grid;
import basicdatastructures.Pair;
import heuristic.ManhattanHeuristic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    int rows;
    int cols;

    private final ManhattanHeuristic heuristic = new ManhattanHeuristic();

    Pair[] pairs = new Pair[]{
            new Pair(0,-1),//L
            new Pair(-1,0),
            new Pair(1,0),
            new Pair(0,1)
    };


    public BFS(int rows,int cols){
        this.rows=rows;
        this.cols=cols;
    }


    public Pair executeBFS(Grid grid, Pair src, Pair dest){
        boolean[][] visited = new boolean[rows][cols];
        Pair[][] parents = new Pair[rows][cols];
        visited[src.first][src.second]=true;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(src);
        boolean found = false;

        while (!queue.isEmpty()){
            Pair top = queue.poll();

            if (dest.first==top.first && dest.second==top.second){
                found=true;
                break;
            }

            for (Pair p : pairs){
                Pair neighbour =  top.add(p);

                if (neighbour.first<0 || neighbour.first>=rows ||
                        neighbour.second<0 || neighbour.second>=rows){
                    continue;
                }

                if (grid.isBlocked(neighbour.first, neighbour.second)){
                    continue;
                }

                if (!visited[neighbour.first][neighbour.second]){
                    visited[neighbour.first][neighbour.second] = true;
                    parents[neighbour.first][neighbour.second] = top;
                    queue.add(neighbour);
                }
            }
        }

        //double ended, so we can poll from the back
        Deque<Pair> outputQueue = new LinkedList<>();
        if (found){
            Pair tmp = dest;
            while (tmp!=src){
                outputQueue.add(tmp);
                tmp = parents[tmp.first][tmp.second];
            }
        }

        return outputQueue.pollLast();
    }

}
