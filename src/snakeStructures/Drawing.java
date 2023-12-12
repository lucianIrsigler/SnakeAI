package snakeStructures;

import basicdatastructures.Grid;

public class Drawing {
    public Drawing() {}

    public <T> void draw(T obj, Grid grid, int num){
        if (obj instanceof Snake s){
            drawSnake(grid, s,num);
        }else if (obj instanceof Obstacle o){
            drawObject(grid,o,num);
        }
    }

    private void drawObject(Grid grid,Obstacle obstacle, int num){
        String[] points = obstacle.getPoints();

        for (int i=0,j=1;i<points.length-1;i++,j++){
            drawLine(grid,points[i],points[j],num);
        }
    }

    private void drawSnake(Grid grid, Snake s, int num){
        for (int i=0,j=1;i<s.bodyParts.size()-1;i++,j++){
            drawLine(grid,s.bodyParts.get(i),s.bodyParts.get(j),num);
        }
    }
    private void drawLine(Grid grid,String point1, String point2,int num){
        String[] s1 = point1.split(",");
        int x1 = parseString(s1[0]);
        int y1 = parseString(s1[1]);

        String[] s2 = point2.split(",");
        int x2 = parseString(s2[0]);
        int y2 = parseString(s2[1]);

        if (x1==x2){
            drawVerticalLine(grid,x1,y1,y2,num);
        }else if (y1==y2){
            drawHorizontalLine(grid,x1,x2,y1,num);
        }
    }

    private void drawVerticalLine(Grid grid, int x, int minY, int maxY, int num) {
        int min = Math.min(minY, maxY);
        int max = Math.max(minY, maxY);

        for (int y = min; y <= max; y++) {
            grid.grid[x][y] = num;
        }
    }
    private void drawHorizontalLine(Grid grid, int minX, int maxX, int y, int num) {
        int min = Math.min(minX, maxX);
        int max = Math.max(minX, maxX);

        for (int x = min; x <= max; x++) {
            grid.grid[x][y] = num;
        }
    }

    private static int parseString(String s){
        return Integer.parseInt(s);
    }
}
