package movement;

import basicdatastructures.Grid;
import basicdatastructures.Pair;
import pathfinding.AAsterisk;
import pathfinding.BestFirstSearch;
import snakeStructures.Apple;
import snakeStructures.GameManager;
import snakeStructures.Snake;


public class Movement {

    private final AAsterisk app;
    private final BestFirstSearch bestFirstSearch;
    private final Calculations calculations;

    private boolean panicMode = false;
    public DebugCustom debug = new DebugCustom();

    public Movement(Grid grid){
        this.app = new AAsterisk();
        this.bestFirstSearch = new BestFirstSearch(grid.rows,grid.cols);
        this.calculations = new Calculations(grid);
    }

    private boolean TailEscapeCondition(Pair nextMove,Pair appleToTail, Pair headToTail){
        if (nextMove!=null){
            return appleToTail != null;
        }else return headToTail != null;
    }

    public Pair calculateNextMove(GameManager manager, int mySnakeNum){
        //if not worth going for
        boolean worthGoingFor = calculations.isWorthGoingFor(manager,mySnakeNum);
        Grid grid = manager.getBoard();
        Snake s = manager.getSnake(mySnakeNum);
        Apple apple = manager.getApple();
        Pair nextMove = app.findPath(grid,s.getHead(), apple.position);

        if (!worthGoingFor
            || !TailEscapeCondition(nextMove,app.findPath(grid,apple.position,s.getTail()),
                app.findPath(grid,s.getHead(),s.getTail()))){
            panicMode = true;
        }

        if (panicMode){
            boolean worthGoingForCheck = calculations.isWorthGoingFor(manager,mySnakeNum);
            if (!worthGoingForCheck) {
                nextMove = bestFirstSearch.findPath(grid, s.getHead(), apple.position);
                debug.setCurrentSearch("bestFirstSearch-panic");
                if (nextMove != null) {
                    debug.print();
                    return nextMove;
                }
            }
        }

        debug.setCurrentSearch("A*");

        if (nextMove!=null) {
            debug.print();
        	return nextMove;
        }
        panicMode=true;
        nextMove = bestFirstSearch.findPath(grid,s.getHead(), apple.position);
        debug.setCurrentSearch("bestFirstSearch");

        if (nextMove!=null) {
            debug.print();
            return nextMove;
        }
        return null;
    }


    //bit operators?
    public static String makeMove(Pair currentPos,Pair nextMove){
        if (nextMove==null){
            return "0";
        }

        String move = "0";
        //1->Down,2->Up,3-Left,4->Right
        if (nextMove.second<currentPos.second){
            //up
            move = "0";
        }else if (nextMove.second>currentPos.second){
            //down
            move = "1";
        }else if (nextMove.first<currentPos.first){
            //left
            move = "2";
        }else if (nextMove.first>currentPos.first){
            //right
            move = "3";
        }
        return move;
    }
}
