package snakeStructures;

import basicdatastructures.Pair;

public class Apple extends baseSnakeStructure{
    public Pair position;
    private final double maxPoints;
    private double moves;

    private String prevApplePos;

    public Apple() {
        this.position = new Pair(-1, -1);
        this.maxPoints = 5.0;
        this.moves=0;
        this.prevApplePos ="";
    }

    public int getValue() {
        return (int) Math.ceil(maxPoints - (moves * 0.1));
    }

    private void resetMoves() {
        moves = 0;
    }

    @Override
    public void update(String line){
        if (prevApplePos.equals(line)){
            moves++;
            return;
        }
        prevApplePos = line;
        String[] applePosition = line.split(" ");

        Pair newPos = new Pair(Integer.parseInt(applePosition[0]),
                Integer.parseInt(applePosition[1]));

        updatePosition(newPos);
        updateQuad();
        resetMoves();
    }

    private void updatePosition(Pair newPos) {
        position.updateValues(newPos.first, newPos.second);
    }

    private void updateQuad() {
    }


}
