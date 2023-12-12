package basicdatastructures;

public class Cell {
    //Might need to remove this current pair
    public Pair current;
    public Pair parent;
    // f = g + h, where h is heuristic
    public double f, g, h;
    public Cell()
    {
        current=new Pair(-1,-1);
        parent = new Pair(-1, -1);
        f = -1;
        g = -1;
        h = -1;
    }

    public Cell(Pair current,Pair parent, double f, double g, double h) {
        this.current=current;
        this.parent = parent;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    public void updateValues(Pair current, Pair parent, double f, double g, double h){
        this.current = current;
        this.parent = parent;
        this.f = f;
        this.g = g;
        this.h = h;
    }
}
