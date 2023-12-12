package heuristic;
import basicdatastructures.Pair;

public class ManhattanHeuristic implements Heuristic{
    @Override
    public double calculate(Pair curr, Pair dest) {
        return Math.abs(dest.first - curr.first)
                + Math.abs(dest.second - curr.second);
    }
}
