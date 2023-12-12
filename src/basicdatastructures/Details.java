package basicdatastructures;

public class Details {
    // Creating a shortcut for tuple<int, int, int> type
    public double value;
    public int x;
    public int y;

    public Details(double value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public Details(double value, Pair point) {
        this.value = value;
        this.x = point.first;
        this.y = point.second;
    }
}
