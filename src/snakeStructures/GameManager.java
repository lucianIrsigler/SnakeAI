package snakeStructures;

import basicdatastructures.Grid;
import basicdatastructures.Pair;
import pathfinding.AAsterisk;
import movement.AvailableMoves;
import movement.Calculations;
import movement.Movement;

import java.util.*;

public class GameManager {
    private final Drawing drawing = new Drawing();
    private final Movement movement;
    private final AAsterisk app = new AAsterisk();

    private final Calculations calculations = new Calculations();
    private final Grid board;
    private final Apple apple;
    private final Snake[] snakes;
    private final Obstacle[] obstacles;
    public int numTurns;

    private TreeMap<Integer,Integer> snakeDistances = new TreeMap<>();

    public GameManager(int rows, int cols, int nSnakes) {
        numTurns = 0;
        board = new Grid(rows, cols);
        apple = new Apple();
        snakes = new Snake[nSnakes];
        obstacles = new Obstacle[10];
        movement = new Movement(board);

        for (int i = 0; i < nSnakes; i++) {
            snakes[i] = new Snake(i);
        }
        for (int i = 0; i < 3; i++) {
            obstacles[i] = new Obstacle();
        }
        for (Snake s: snakes) {
            snakeDistances.put(s.snakeNum,0);
        }
    }

    public void resetBoard() {
        board.clearBoard();
        numTurns++;
    }

    public void updateSnake(int snakeIndex, String snakeLine) {
        Snake s = snakes[snakeIndex];
        s.update(snakeLine);

        Stack<Pair> temp = app.aStarSearchStack(board, s.getHead(), apple.position);

        if (temp==null){
            snakeDistances.put(s.snakeNum,100000);
            return;
        }
        snakeDistances.put(s.snakeNum, temp.size());

    }

    public void updateApple(String appleLine) {
        apple.update(appleLine);
    }


    public void updateObstacle(int obsIndex, String obsLine) {
        obstacles[obsIndex].update(obsLine);
    }

    public void drawObjects(int mySnakeNum) {
        for (Obstacle obstacle : obstacles) {
            drawing.draw(obstacle, board, -1);
        }

        for (Snake snake : snakes) {
            drawing.draw(snake, board, -1);
            board.updatePoint(snake.getHead(),-1);
            board.updatePoint(snake.getTail(),-1);
            if (snake.snakeNum == mySnakeNum) {
                continue;
            }

            //if less than 4, then close enough to be a threat
            if (calculations.calculateSnakeDistance(snakes[mySnakeNum], snake)<=4) {
            for (Pair p : AvailableMoves.availMoves) {
                Pair pointAheadOfHead = snake.getHead().add(p);
                board.updatePoint(pointAheadOfHead, -1);
            }
            }
        }
    }

    public Snake getSnake(int index) {
        return snakes[index];
    }

    public Apple getApple() {
        return apple;
    }

    public Grid getBoard() {
        return board;
    }
    public TreeMap<Integer,Integer> getSnakeDistances(){
        return snakeDistances;
    }

    public String getNextMove(int mySnakeNum){
        Pair nextMove;
        nextMove = movement.calculateNextMove(this, mySnakeNum);
        return Movement.makeMove(snakes[mySnakeNum].getHead(), nextMove);
    }

}
