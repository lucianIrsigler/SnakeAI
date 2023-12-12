package basicdatastructures;

import java.util.Objects;

/**
 * Represents a basic x,y pair
 */
public class Pair {
    public int first;
    public int second;
    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }

    //copy constructor
    public Pair(Pair another) {
        this.first = another.first;
        this.second = another.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "("+this.first+","+this.second+")";
    }
    public boolean equals(Pair obj) {
        return (this.first==obj.first)&&(this.second==obj.second);
    }
    public Pair add(Pair other) {
        return new Pair(this.first+other.first, this.second+other.second);
    }

    public void updateValues(int x, int y){
        this.first = x;
        this.second = y;
    }
}
