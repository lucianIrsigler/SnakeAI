package basicdatastructures;

import java.util.Arrays;

public class Grid {
    public int[][] grid;
    public int rows;
    public int cols;

    public Grid(int rows,int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
    }

    public void clearBoard(){
        for (int i = 0; i < rows; i++) {
            Arrays.fill(grid[i], 0);
        }
    }

    public boolean isValid(Pair move){
        int row = move.first;
        int col = move.second;
        return (0<=row)&&(0<=col)&&(row<rows)&&(col<cols);
    }

    public boolean isValidCoords(int x,int y){
        return (0<=x)&&(0<=y)&&(x<rows)&&(y<cols);
    }

    public boolean isBlocked(int x,int y){
        return isValidCoords(x,y) && grid[x][y]==-1;
    }

    public boolean isOpen(int x,int y){
        return isValidCoords(x,y) && grid[x][y]==0;
    }
    public void updatePoint(Pair point, int num){
        if (isValid(point)) {
            grid[point.first][point.second] = num;
        }
    }
}
