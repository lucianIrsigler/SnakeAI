package snakeStructures;

public class Obstacle extends baseSnakeStructure{
    private String obstacle ="";
    private String[] points;

    public Obstacle(){}

    @Override
    public void update(String newline){
        if (!obstacle.equals(newline)){
            this.obstacle = newline;
            this.points = newline.split(" ");
        }
    }

    public String[] getPoints(){
        return this.points;
    }
}
