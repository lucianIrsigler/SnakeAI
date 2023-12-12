package pathfinding;

import basicdatastructures.Grid;
import basicdatastructures.Pair;

public interface pathfinding {
    Pair findPath(Grid grid, Pair src, Pair dest);
}
