package pathfinding;

import basicdatastructures.Cell;
import basicdatastructures.Details;
import basicdatastructures.Grid;
import basicdatastructures.Pair;
import heuristic.Heuristic;
import heuristic.ManhattanHeuristic;
import movement.AvailableMoves;

import java.util.PriorityQueue;
import java.util.Stack;

//https://www.youtube.com/watch?v=PzEWHH2v3TE
//https://codegym.cc/groups/posts/a-search-algorithm-in-java

public class AAsterisk implements pathfinding{

    public AAsterisk(){}
    // Creating an open list
    Stack<Pair> path = new Stack<>();

    private final Heuristic heuristic = new ManhattanHeuristic();
    double calculateHeuristicValue(Heuristic heuristic,Pair src, Pair dest)
    {
        return heuristic.calculate(src,dest);
    }

    private boolean isDest(Pair pos, Pair dest){
        if (pos==null || dest==null){
            return false;
        }
        return pos.equals(dest) || pos == dest;
    }

    public Stack<Pair> returnPath(Cell[][] cellDetails, Pair dest){
        path.clear();
        int row = dest.first;
        int col = dest.second;
        Cell c = cellDetails[row][col];

        do{
            path.push(c.current);
            if (c.parent==null){
                break;
            }
            c = cellDetails[c.parent.first][c.parent.second];
        }
        while (c.parent!=null);

        return path;
    }

    @Override
    public Pair findPath(Grid grid, Pair src, Pair dest){
        aStarSearchImplementation(grid,src,dest);
        if (path.isEmpty()){
            return null;
        }
        return path.pop();
    }

    public Stack<Pair> aStarSearchStack(Grid grid, Pair src, Pair dest){
        aStarSearchImplementation(grid,src,dest);
        return path;
    }


    private void aStarSearchImplementation(Grid grid, Pair src, Pair dest) {
        if (!grid.isValid(src)){
            return;
        }
        if (isDest(src, dest)) {
            return;
        }

        int rows = grid.rows;
        int cols = grid.cols;

        boolean[][] closedList = new boolean[rows][cols];
        Cell[][] cellDetails = new Cell[rows][cols];
        PriorityQueue<Details> openList = new PriorityQueue<>(
                (o1, o2) -> (int) Math.round(o1.value - o2.value));

        int currX = src.first;
        int currY = src.second;
        cellDetails[currX][currY] = new Cell(src,null, 0.0, 0.0, 0.0);

        openList.add(new Details(0.0, currX, currY));
        while (!openList.isEmpty()) {
            //get top element
            Details p = openList.peek();
            currX = p.x;
            currY = p.y;
            //removes head from queue
            openList.poll();
            closedList[currX][currY] = true;

            // Generating all the 4 neighbors of the cell
            for (Pair pair : AvailableMoves.availMoves) {
                Pair neighbour = new Pair( currX+pair.first,currY+pair.second);
                int neighbourX = neighbour.first;
                int neighbourY = neighbour.second;
                if (grid.isValidCoords(neighbourX,neighbourY)){

                    if (cellDetails[neighbourX] == null) {
                        cellDetails[neighbourX] = new Cell[cols];
                    }

                    if (cellDetails[neighbourX][neighbourY] == null) {
                        cellDetails[neighbourX][neighbourY] = new Cell();
                    }

                    if (isDest(neighbour, dest)) {
                        cellDetails[neighbourX][neighbourY].parent = new Pair(currX, currY);
                        cellDetails[neighbourX][neighbourY].current = neighbour;
                        path = returnPath(cellDetails,dest);
                        return;
                    }
                    else if (!closedList[neighbourX][neighbourY]
                            && grid.isOpen(neighbourX,neighbourY)) {
                        double gNew, hNew, fNew;
                        gNew = cellDetails[currX][currY].g + 1.0;
                        hNew = calculateHeuristicValue(heuristic,neighbour,dest);
                        fNew = gNew + hNew;

                        if (cellDetails[neighbourX][neighbourY].f == -1
                                || cellDetails[neighbourX][neighbourY].f > fNew) {
                            openList.add(new Details(fNew, neighbour));

                            //updates parent
                            cellDetails[neighbourX][neighbourY].updateValues(
                                    neighbour,
                                    new Pair(currX, currY),fNew,gNew,hNew
                            );
                        }
                    }
                }
            }
        }
    }



}