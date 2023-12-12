package heuristic;

import basicdatastructures.Pair;

public class EuclideanHeuristic implements Heuristic{
    @Override
    public double calculate(Pair src, Pair dest){
        return Math.sqrt(Math.pow((src.first - dest.first), 2.0)
                + Math.pow((src.second - dest.second), 2.0));
    }
}
