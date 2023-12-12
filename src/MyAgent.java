import snakeStructures.*;
import za.ac.wits.snake.DevelopmentAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyAgent extends DevelopmentAgent {
    public static void main(String[] args) {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }


    private final String version = "1.0.6";

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            int rows = Integer.parseInt(temp[1]);
            int cols = Integer.parseInt(temp[2]);
            GameManager manager = new GameManager(rows,cols,nSnakes);

            while (true) {
                String line = br.readLine();
                manager.resetBoard();

                if (line==null) {
                    continue;
                }

                if (line.contains("Game Over")) {
                    System.out.println("log "+version);
                    break;
                }

                //do stuff with apples
                manager.updateApple(line);

                // read in obstacles and do something with them!
                int nObstacles = 3;
                for (int i = 0; i < nObstacles; i++) {
                    String obs = br.readLine();
                    manager.updateObstacle(i,obs);
                }

                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    manager.updateSnake(i,snakeLine);
                }
                manager.drawObjects(mySnakeNum);
                String move = manager.getNextMove(mySnakeNum);
                System.out.println(move);
            }
        } catch (IOException e) {
            System.out.println("log "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}